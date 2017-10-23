/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.sound;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.media.j3d.BackgroundSound;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.MediaContainer;
import javax.media.j3d.PointSound;
import javax.media.j3d.Sound;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.View;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point2f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class SoundBug extends Applet implements ActionListener {

  // Scene graph items
  SimpleUniverse u;

  Switch spheresSwitch;

  Switch soundSwitch;

  // temp image grabber
  boolean isApplication;

  Canvas3D canvas;

  View view;

  // Sound items
  String soundNoneString = "No sound";

  String soundBackgroundString = "Background";

  String soundPointString = "Point";

  String soundConeString = "Cone";

  String soundBackgroundActionString = "SBackground";

  String soundPointActionString = "SPoint";

  JRadioButton soundNoneButton;

  JRadioButton soundBackgroundButton;

  JRadioButton soundPointButton;

  JRadioButton soundConeButton;

  BackgroundSound soundBackground;

  PointSound soundPoint;

  static final int SOUND_NONE = Switch.CHILD_NONE;

  static final int SOUND_BACKGROUND = 0;

  static final int SOUND_POINT = 1;

  // Temporaries that are reused
  Transform3D tmpTrans = new Transform3D();

  Vector3f tmpVector = new Vector3f();

  AxisAngle4f tmpAxisAngle = new AxisAngle4f();

  // colors
  Color3f red = new Color3f(1.0f, 0.0f, 0.0f);

  Color3f black = new Color3f(0.0f, 0.0f, 0.0f);

  Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

  Color3f grey = new Color3f(0.1f, 0.1f, 0.1f);

  Color3f skyBlue = new Color3f(0.6f, 0.7f, 0.9f);

  // geometric constant
  Point3f origin = new Point3f();

  Vector3f yAxis = new Vector3f(0.0f, 1.0f, 0.0f);

  // NumberFormat to print out floats with only two digits
  NumberFormat nf;

  /*
   * Set up the sound switch. The child values are: SOUND_BACKGROUND:
   * BackgroundSound SOUND_POINT: PointSound
   */
  void setupSounds() {
    soundSwitch = new Switch(Switch.CHILD_NONE);
    soundSwitch.setWhichChild(Switch.CHILD_NONE);
    soundSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

    // set up the BoundingSphere for all the sounds
    BoundingSphere bounds = new BoundingSphere(new Point3d(), 100.0);

    // Set up the sound media container
    java.net.URL soundURL = null;
    //String soundFile = "techno_machine.au";
    String soundFile = "file:C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\sound\\techno_machine.au";
    try {
      java.net.URL codeBase = null;
      try {
        codeBase = getCodeBase();
      } catch (Exception ex) {
        // got an exception, probably running as an application,
        // keep code base null...
      }
      if (codeBase != null) {
        //soundURL = new java.net.URL(codeBase.toString() + soundFile);
        soundURL = new java.net.URL(soundFile);          
      }
    } catch (java.net.MalformedURLException ex) {
      System.out.println(ex.getMessage());
      System.exit(1);
    }
    if (soundURL == null) { // application, try file URL
      try {
        //soundURL = new java.net.URL("file:./" + soundFile);
        soundURL = new java.net.URL(soundFile);        
      } catch (java.net.MalformedURLException ex) {
        System.out.println(ex.getMessage());
        System.exit(1);
      }
    }
    System.out.println("soundURL = " + soundURL);
    MediaContainer soundMC = new MediaContainer(soundURL);

    // set up the Background Sound
    soundBackground = new BackgroundSound();
    soundBackground.setCapability(Sound.ALLOW_ENABLE_WRITE);
    soundBackground.setSoundData(soundMC);
    soundBackground.setSchedulingBounds(bounds);
    soundBackground.setEnable(true);
    soundBackground.setLoop(Sound.INFINITE_LOOPS);
    soundSwitch.addChild(soundBackground);

    // set up the point sound
    soundPoint = new PointSound();
    soundPoint.setCapability(Sound.ALLOW_ENABLE_WRITE);
    soundPoint.setSoundData(soundMC);
    soundPoint.setSchedulingBounds(bounds);
    soundPoint.setEnable(true);
    soundPoint.setLoop(Sound.INFINITE_LOOPS);
    soundPoint.setPosition(-5.0f, -5.0f, 0.0f);
    Point2f[] distGain = new Point2f[2];
    // set the attenuation to linearly decrease volume from max at
    // source to 0 at a distance of 5m
    distGain[0] = new Point2f(0.0f, 1.0f);
    distGain[1] = new Point2f(5.0f, 0.0f);
    soundPoint.setDistanceGain(distGain);
    soundSwitch.addChild(soundPoint);

  }

  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    Object source = e.getSource();
    if (action == soundNoneString) {
      soundSwitch.setWhichChild(SOUND_NONE);
    } else if (action == soundBackgroundActionString) {
      soundSwitch.setWhichChild(SOUND_BACKGROUND);
    } else if (action == soundPointActionString) {
      soundSwitch.setWhichChild(SOUND_POINT);
    }
  }

  BranchGroup createSceneGraph() {
    // Create the root of the branch graph
    BranchGroup objRoot = new BranchGroup();

    // Add the primitives to the scene
    setupSounds();
    objRoot.addChild(soundSwitch);

    return objRoot;
  }

  public SoundBug(boolean isApplication) {
    this.isApplication = isApplication;
  }

  public SoundBug() {
    this(false);
  }

  public void init() {

    // set up a NumFormat object to print out float with only 3 fraction
    // digits
    nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(3);

    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse
        .getPreferredConfiguration();

    canvas = null;
    canvas = new Canvas3D(config);
    add("Center", canvas);

    // Create a simple scene and attach it to the virtual universe
    BranchGroup scene = createSceneGraph();
    u = new SimpleUniverse(canvas);

    // set up sound
    u.getViewer().createAudioDevice();

    // get the view
    view = u.getViewer().getView();

    // Get the viewing platform
    ViewingPlatform viewingPlatform = u.getViewingPlatform();

    // Move the viewing platform back to enclose the -4 -> 4 range
    double viewRadius = 4.0; // want to be able to see circle
    // of viewRadius size around origin
    // get the field of view
    double fov = u.getViewer().getView().getFieldOfView();

    // calc view distance to make circle view in fov
    float viewDistance = (float) (viewRadius / Math.tan(fov / 2.0));
    tmpVector.set(0.0f, 0.0f, viewDistance);// setup offset
    tmpTrans.set(tmpVector); // set trans to translate
    // move the view platform
    viewingPlatform.getViewPlatformTransform().setTransform(tmpTrans);

    // add an orbit behavior to move the viewing platform
    OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.STOP_ZOOM);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    orbit.setSchedulingBounds(bounds);
    viewingPlatform.setViewPlatformBehavior(orbit);

    u.addBranchGraph(scene);

    add("South", soundPanel());
  }

  JPanel soundPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1));

    // create the buttons
    soundNoneButton = new JRadioButton(soundNoneString);
    soundBackgroundButton = new JRadioButton(soundBackgroundString);
    soundPointButton = new JRadioButton(soundPointString);
    //soundConeButton = new JRadioButton(soundConeString);

    // set up the action commands
    soundNoneButton.setActionCommand(soundNoneString);
    soundBackgroundButton.setActionCommand(soundBackgroundActionString);
    soundPointButton.setActionCommand(soundPointActionString);
    //soundConeButton.setActionCommand(soundConeString);

    // add the buttons to a group so that only one can be selected
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(soundNoneButton);
    buttonGroup.add(soundBackgroundButton);
    buttonGroup.add(soundPointButton);
    //buttonGroup.add(soundConeButton);

    // register the applet as the listener for the buttons
    soundNoneButton.addActionListener(this);
    soundBackgroundButton.addActionListener(this);
    soundPointButton.addActionListener(this);
    // soundConeButton.addActionListener(this);

    // add the buttons to the panel
    panel.add(soundNoneButton);
    panel.add(soundBackgroundButton);
    panel.add(soundPointButton);
    //panel.add(soundConeButton);

    // set the default
    soundNoneButton.setSelected(true);

    return panel;
  }

  public void destroy() {
    u.removeAllLocales();
  }

  // The following allows SoundBug to be run as an application
  // as well as an applet
  //
  public static void main(String[] args) {
    new MainFrame(new SoundBug(true), 600, 900);
  }
}