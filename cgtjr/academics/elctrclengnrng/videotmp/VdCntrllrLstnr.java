package cgtjr.academics.elctrclengnrng.videotmp;

import cgtjr.academics.elctrclengnrng.video.TrckrTxtFld;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.*;
import javax.media.control.TrackControl;
import javax.swing.JApplet;

//import com.sun.media.util.JMFSecurity;
/**
 * This is a Java Applet that demonstrates how to create a simple media player
 * with a media event listener. It will play the media clip right away and
 * continuously loop.
 *
 * <!-- Sample HTML <applet code=VideoAnalyzer width=320 height=300> <param
 * name=file value="sun.avi"> </applet> -->
 */
public class VdCntrllrLstnr extends JApplet implements ControllerListener {

    // media Processor
    private Processor player = null;
    // component in which video is playing
    private Component visualComponent = null;
    // controls gain, position, start, stop
    private Component controlComponent = null;
    // displays progress during download
    private Component progressBar = null;
    private boolean firstTime = true;
    private long CachingSize = 0L;
    private Panel panel = null;
    private int controlPanelHeight = 0;
    private int videoWidth = 0;
    private int videoHeight = 0;
    private String mediaFile = null;
    private URL url = null;
    private boolean realized = false;
    private boolean configured = false;
    private Container aContainer;
    private static TrckrTxtFld trckrTxtFld;

    public VdCntrllrLstnr() {
        aContainer = this.getContentPane();
        aContainer.setLayout(new BorderLayout());
        trckrTxtFld = new TrckrTxtFld();
    }

    public VdCntrllrLstnr(String myFileName) {
        mediaFile = myFileName;
    }

    public static void main(String args[]) {
        Frame f = new Frame("Video Processor");
        VdCntrllrLstnr aVideoAnalyzer = new VdCntrllrLstnr("bookshelf4.mov");
        Panel aVideoPanel = aVideoAnalyzer.beganApplication();
        f.add("Center", aVideoPanel);
        f.setSize(300, 300);
        f.show();
    }

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {

        if ((mediaFile = getParameter("FILE")) == null) {
            Fatal("Invalid media file parameter");
        }
        try {
            url = new URL(getDocumentBase(), mediaFile);
            mediaFile = url.toExternalForm();
        } catch (MalformedURLException mue) {
        }

        Panel aPanel = beganApplication();
        add(aPanel, BorderLayout.CENTER);
        add(trckrTxtFld.getTxtFld(), BorderLayout.SOUTH);
    }

    public Panel beganApplication() {
        if (url == null) {
            //url = CommandLine.retrieveURL(mediaFile);
            //mediaFile = url.toExternalForm();
        }
        //setLayout(null);
        setBackground(Color.white);
        panel = new Panel();
        panel.setLayout(null);

        panel.setBounds(0, 0, 320, 240);

        MediaLocator mrl = null;

        try {
            // Create a media locator from the file name
            if ((mrl = new MediaLocator(mediaFile)) == null) {
                Fatal("Can't build URL for " + mediaFile);
            }
            // Create an instance of a player for this media
            try {
                if (mrl != null) {
                    player = Manager.createProcessor(mrl);
                    player.addControllerListener(this);

                }
            } catch (NoPlayerException e) {
                System.out.println(e);
            }
            player.configure();
            while (!configured) {
                try {
                    Thread.currentThread().sleep(100L);;
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            player.setContentDescriptor(null);
            TrackControl theTrackControl[] = player.getTrackControls();

            //System.out.println("VideoAnalyzer:"+theTrackControl[1].getFormat());

            VideoCompRenderer anotherVideoCompRenderer = new VideoCompRenderer();

            setRenderer(theTrackControl, anotherVideoCompRenderer);

        } catch (MalformedURLException e) {
            Fatal("Invalid media file URL!");
        } catch (IOException e) {
            Fatal("IO exception creating player for " + mrl);
        }
        // This applet assumes that its start() calls 
        // player.start(). This causes the player to become
        // realized. Once realized, the applet will get
        // the visual and control panel components and add
        // them to the Applet. These components are not added
        // during init() because they are long operations that
        // would make us appear unresposive to the user.
        start();
        return panel;
    }

    public void setRenderer(TrackControl[] myTrackControl,
            VideoCompRenderer myVideoCompRenderer) {
        int aSize = myTrackControl.length;
        try {
            for (int i = 0; i < aSize; i++) {
                Format aFormat = myTrackControl[i].getFormat();
                System.out.println("VideoAnalyzer:" + myTrackControl[i].getFormat());
                if (aFormat.toString().startsWith("H263")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("RGB")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("CVID")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("MPEG")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("MP4V")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("mpeglayer3")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                } else if (aFormat.toString().startsWith("XVID")) {
                    myTrackControl[i].setRenderer(myVideoCompRenderer);
                    System.out.println("VideoAnalyzer: test 10");
                }
            }
        } catch (javax.media.UnsupportedPlugInException upe) {
            System.err.println("VideoAnalyzer:" + upe.getMessage());
        }
    }

    /**
     * Start media file playback. This function is called the first time that
     * the Applet runs and every time the user re-enters the page.
     */
    public void start() {
        if (player != null) {
            player.start();
        }
    }

    /**
     * Stop media file playback and release resource before leaving the page.
     */
    public void stop() {
        //$ System.out.println("Applet.stop() is called");
        if (player != null) {
            player.stop();
            player.deallocate();
        }
    }

    public void destroy() {
        player.close();
    }

    /**
     * This controllerUpdate function must be defined in order to implement a
     * ControllerListener interface. This function will be called whenever there
     * is a media event
     */
    public synchronized void controllerUpdate(ControllerEvent event) {
        // If we're getting messages from a dead player, 
        // just leave
        if (player == null) {
            return;
        }
        if (event instanceof RealizeCompleteEvent) {
            realized = true;
        } else if (event instanceof ConfigureCompleteEvent) {
            configured = true;
        }
        // When the player is Realized, get the visual 
        // and control components and add them to the Applet
        if (event instanceof RealizeCompleteEvent) {
            if (progressBar != null) {
                panel.remove(progressBar);
                progressBar = null;
            }

            int width = 320;
            int height = 0;
            if (controlComponent == null) {
                if ((controlComponent =
                        player.getControlPanelComponent()) != null) {

                    controlPanelHeight = controlComponent.getPreferredSize().height;
                    panel.add(controlComponent);
                    height += controlPanelHeight;
                }
            }
            if (visualComponent == null) {
                if ((visualComponent =
                        player.getVisualComponent()) != null) {
                    panel.add(visualComponent);
                    Dimension videoSize = visualComponent.getPreferredSize();
                    videoWidth = videoSize.width;
                    videoHeight = videoSize.height;
                    width = videoWidth;
                    height += videoHeight;
                    visualComponent.setBounds(0, 0, videoWidth, videoHeight);
                }
            }

            panel.setBounds(0, 0, width, height);
            if (controlComponent != null) {
                controlComponent.setBounds(0, videoHeight,
                        width, controlPanelHeight);
                controlComponent.invalidate();
            }
        } else if (event instanceof CachingControlEvent) {
            if (player.getState() > Controller.Realizing) {
                return;
            }
            // Put a progress bar up when downloading starts, 
            // take it down when downloading ends.
            CachingControlEvent e = (CachingControlEvent) event;
            CachingControl cc = e.getCachingControl();

            // Add the bar if not already there ...
            if (progressBar == null) {
                if ((progressBar = cc.getControlComponent()) != null) {
                    panel.add(progressBar);
                    panel.setSize(progressBar.getPreferredSize());
                    validate();
                }
            }
        } else if (event instanceof EndOfMediaEvent) {
            // We've reached the end of the media; rewind and
            // start over
            player.setMediaTime(new Time(0));
            player.start();
        } else if (event instanceof ControllerErrorEvent) {
            // Tell TypicalPlayerApplet.start() to call it a day
            //player = null;
            //Fatal(((ControllerErrorEvent)event).getMessage());
        } else if (event instanceof ControllerClosedEvent) {
            panel.removeAll();
        }
    }

    void Fatal(String s) {
        // Applications will make various choices about what
        // to do here. We print a message
        System.err.println("FATAL ERROR: " + s);
        throw new Error(s); // Invoke the uncaught exception
        // handler System.exit() is another
        // choice.	
    }
}
