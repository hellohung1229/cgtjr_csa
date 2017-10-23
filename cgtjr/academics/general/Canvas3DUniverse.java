package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*; 
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Matrix4d;

import com.sun.j3d.loaders.lw3d.Lw3dLoader;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.Cylinder;

public class Canvas3DUniverse extends Applet implements ActionListener
{
   private Canvas3D theCanvas3D;

   private java.net.URL filename;
   private SimpleUniverse u;
   private String theFile;

   private TransformGroup sceneTransform;

   private VirtualInputDevice theDevice;
   private BranchGroup theSceneRoot;

   private TextArea theOutputTextArea;
   private BranchGroup theBranchGroup ;
   private Panel thePanel;

   private boolean filterFlag; 
  
   TransformGroup objTrans = null;
   private BranchGroup waveSourceGroup;
   private Background bgNode;

   public TransformGroup retrieveMainGroup()
   {
      return objTrans;
   }
   public Canvas3DUniverse() {}
   public Canvas3DUniverse(java.net.URL url) 
   {
      filename = url;
   }
   public Canvas3DUniverse(String aFile)
   {
      theFile = aFile;
   }
   public Canvas3D retrieveCanvas3D()
   {
      return theCanvas3D;
   }
   public Canvas3DUniverse(VirtualInputDevice myDevice) 
   {      
      theDevice = myDevice;
   }  
   public void setFilterFlag(boolean aFilterFlag)
   {
      filterFlag = aFilterFlag;
   }
   public boolean getFilterFlag()
   {
      return filterFlag;
   }
   public void init() 
   {
      if (filename == null) {
	   try {
	      java.net.URL path = getCodeBase();
            String aString = getParameter("file");
            filename = new java.net.URL(aString);
            System.out.println("Canvas3DUniverse.init() : filename = "+aString);
	   }catch (java.net.MalformedURLException ex) {
	      System.err.println(ex.getMessage());
	      ex.printStackTrace();
	      System.exit(1);
	   }
      }
      beganLayout();
   }
   public Canvas3D beganLayout()
   {
      GraphicsConfiguration config =
      SimpleUniverse.getPreferredConfiguration();
      Color3f bgColor = new Color3f(1.0f, 1.0f, 1.0f);
      bgNode = new Background(bgColor);
      theCanvas3D = new Canvas3D(config);
      return theCanvas3D;
   }
   public void buildScene()
   {
      buildScene(theCanvas3D,theDevice);
   }
   public void buildScene(Canvas3D myCanvas3D,VirtualInputDevice myDevice)
   {
      theSceneRoot = new BranchGroup();

	// Create a basic universe setup and the root of our scene
	u = new SimpleUniverse(myCanvas3D);

      u.getViewer().getPhysicalEnvironment().addInputDevice(myDevice);

      BoundingSphere bounds =
	      new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

      ViewingPlatform viewingPlatform = u.getViewingPlatform();
      sceneTransform = viewingPlatform.getViewPlatformTransform();

      SensorBehavior s = new SensorBehavior( sceneTransform , myDevice.getSensor(0) );
	s.setSchedulingBounds( new BoundingSphere
                       ( new Point3d(0.0,0.0,0.0), Float.MAX_VALUE ));

      theSceneRoot.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
      theSceneRoot.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE );
      theSceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      theSceneRoot.setCapability(BranchGroup.ALLOW_DETACH);
      Color3f bgColor = new Color3f(1.0f,1.0f,1.0f);
      bgNode = new Background(bgColor);
      bgNode.setApplicationBounds(bounds);
      theSceneRoot.insertChild(bgNode,0);
      theSceneRoot.insertChild(s,1);

      BranchGroup lightGroup = createLighting();
      theSceneRoot.insertChild(lightGroup,2);
      objTrans = new TransformGroup();
         objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
         objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
         //objTrans.setCapability(TransformGroup.ALLOW_APPEARANCE_READ);
         //objTrans.setCapability(TransformGroup.ALLOW_APPEARANCE_WRITE);
         objTrans.setCapability(BranchGroup.ALLOW_DETACH);

         objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);


      MouseRotate behavior = new MouseRotate(objTrans);
      objTrans.addChild(behavior);
      behavior.setSchedulingBounds(bounds);
      //objTrans.addChild(myBranchGroup);
      theBranchGroup = new BranchGroup();
      theBranchGroup.addChild(objTrans);
      theBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
      theBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      theBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
      theSceneRoot.insertChild(theBranchGroup,2);

	View theView = u.getViewer().getView();
      theView.setBackClipDistance(1000);
	u.addBranchGraph(theSceneRoot);
    }
    public void renderScene(Scene loaderScene)
    {
      BranchGroup sceneGroup = null;
      theBranchGroup = new BranchGroup();
      theBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
      theBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);

	if(loaderScene.getSceneGroup() != null) 
      {
         sceneGroup = loaderScene.getSceneGroup();
         sceneGroup.setCapability(BranchGroup.ALLOW_DETACH);
         sceneGroup.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
         sceneGroup.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
         sceneGroup.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);

         sceneTransform.setTransform(setViewpoint());
 
         objTrans = new TransformGroup();
         objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         objTrans.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
         objTrans.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
         objTrans.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
         objTrans.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
         objTrans.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

         BoundingSphere bounds =
	      new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
         MouseRotate behavior = new MouseRotate(objTrans);
         objTrans.addChild(behavior);
         behavior.setSchedulingBounds(bounds);
         objTrans.addChild(sceneGroup);
         theBranchGroup.addChild(objTrans);

         theSceneRoot.removeChild(2);
         theSceneRoot.insertChild(theBranchGroup,2);
      }
    }
    public void loadScene(BranchGroup myBranchGroup)
    {
      BranchGroup sceneGroup = null;
      theBranchGroup = new BranchGroup();
      theBranchGroup.setCapability(BranchGroup.ALLOW_DETACH );
      theBranchGroup.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);

      sceneTransform.setTransform(setViewpoint());
 
      TransformGroup objTrans = new TransformGroup();
      objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
      objTrans.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
      BoundingSphere bounds =
	      new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
      MouseRotate behavior = new MouseRotate(objTrans);
      objTrans.addChild(behavior);
      behavior.setSchedulingBounds(bounds);
      objTrans.addChild(myBranchGroup);
      theBranchGroup.addChild(objTrans);

      theSceneRoot.removeChild(3);
      theSceneRoot.insertChild(theBranchGroup,3);
    }
    public void updateScene(BranchGroup myBranchGroup)
    {
      objTrans.addChild(myBranchGroup);
    }
    private Transform3D setViewpoint() {
       Transform3D viewTrans = new Transform3D();
       Transform3D eyeTrans = new Transform3D();

       Vector3f pos = new Vector3f(0,0,10);
       eyeTrans.set(pos);
       viewTrans.mul(eyeTrans);

       return viewTrans;
    }
    private BranchGroup setupLighting() {
       BranchGroup lightBG = new BranchGroup();
       BoundingSphere lightBounds = new BoundingSphere(new Point3d(), Double.MAX_VALUE);
       DirectionalLight headLight =
                new DirectionalLight(new Color3f(1.0f,1.0f,1.0f),
                                     new Vector3f(0,0,-1));
       headLight.setCapability(Light.ALLOW_STATE_WRITE);
       headLight.setInfluencingBounds(lightBounds);

       lightBG.addChild(headLight);

       return lightBG;
    }
   public BranchGroup createScene()
   { 
     BranchGroup waveSourceRoot = new BranchGroup();
     waveSourceRoot.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
     BranchGroup lightBranchGroup = createLighting();
     waveSourceGroup = new BranchGroup();

     waveSourceGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
     waveSourceGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
     waveSourceGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

     waveSourceRoot.addChild(lightBranchGroup);
     waveSourceRoot.addChild(waveSourceGroup);
     waveSourceRoot.addChild(new Cylinder(0x004422,1));
     return waveSourceRoot;
   }
  public BranchGroup createLighting() {

    BranchGroup objRoot = new BranchGroup();
    objRoot.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
    DirectionalLight light1 = new DirectionalLight(light1Color,
        light1Direction);
    light1.setInfluencingBounds(bounds);
    objRoot.addChild(light1);

    PointLight pointLightNode1 = new PointLight();
    pointLightNode1.setPosition(100,4,4);
    pointLightNode1.setInfluencingBounds(bounds);
    objRoot.addChild(pointLightNode1);

    PointLight pointLightNode2 = new PointLight();
    pointLightNode2.setPosition(-100,4,4);
    pointLightNode2.setInfluencingBounds(bounds);
    objRoot.addChild(pointLightNode2);

    return objRoot;
  }

    public void actionPerformed(ActionEvent e)
    {
    }
    public BranchGroup retrieveRootNode()
    {
       return theSceneRoot;
    }
    public TransformGroup retrieveTransformGroup()
    {
       return objTrans;
    }
    public void destroy() {
	u.cleanup();
    }
}