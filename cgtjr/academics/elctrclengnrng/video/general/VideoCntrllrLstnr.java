package cgtjr.academics.elctrclengnrng.video.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.*;
import javax.media.control.TrackControl;
import javax.media.renderer.VideoRenderer;

public class VideoCntrllrLstnr implements ControllerListener {

    private Processor player = null;
    private Component visualComponent = null;
    private Component controlComponent = null;
    private Component progressBar = null;
    private Panel panel = null;
    private int controlPanelHeight = 0;
    private int videoWidth = 0;
    private int videoHeight = 0;
    private URL mediaFile = null;
    private boolean realized = false;
    private boolean configured = false;
    private VideoRndrr videoRender;
    private FrameParser videoParser;
    private VideoRenderer anotherVideoRndrr;
    
    public VideoCntrllrLstnr(URL myMediaFile) {
        mediaFile = myMediaFile;
        anotherVideoRndrr = new VideoRndrr();
    }
    public VideoCntrllrLstnr(FrameParser myFrameParser,URL myMediaFile) {
        anotherVideoRndrr = new VideoRndrr();
        videoParser = myFrameParser;
        mediaFile = myMediaFile;
    }
    public VideoCntrllrLstnr(VideoRenderer myVideoRndrr,FrameParser myFrameParser,URL myMediaFile) {
        anotherVideoRndrr = myVideoRndrr;
        videoParser = myFrameParser;
        mediaFile = myMediaFile;
    }
    public void setMediaFile(URL myMediaFile) {
        mediaFile = myMediaFile;
    }

    public void setParser(FrameParser myParser) {
        videoParser = myParser;
    }

    public void setVideoRender(VideoRndrr myVideoRender) {
        videoRender = myVideoRender;
    }

    /**
     * Read the applet file parameter and create the media player.
     */
    public Panel strtApplctn() {
        System.out.println("VideoAnalyzer.strtApplctn file " + mediaFile);
        panel = new Panel();
        panel.setLayout(null);

        panel.setBounds(0, 0, 320, 240);

        MediaLocator mrl = null;

        try {
            // Create a media locator from the file name
            mrl = new MediaLocator(mediaFile);
            /*
             if ((mrl = new MediaLocator(mediaFile)) == null) {
             Fatal("Can't build URL for " + mediaFile);
             }
             */
            // Create an instance of a player for this media
            try {
                //if (mrl != null) {
                //player = Manager.createProcessor(mrl);
                player = Manager.createProcessor(mediaFile);
                //}
            } catch (NoPlayerException e) {
                System.out.println("VideoCntrllrLstnr : " + e);
            }
            player.addControllerListener(this);
                            
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
            
            ((ParserIntrfce)anotherVideoRndrr).setParser(videoParser);
            setRenderer(theTrackControl, anotherVideoRndrr);
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

    public Processor rtrvPlayer() {
        return player;
    }

    public void setRenderer(TrackControl[] myTrackControl,
            VideoRenderer myVideoRndrr) {
        int aSize = myTrackControl.length;
        try {
            for (int i = 0; i < aSize; i++) {
                Format aFormat = myTrackControl[i].getFormat();
                System.out.println("VideoAnalyzer:" + myTrackControl[i].getFormat());
                if (aFormat.toString().startsWith("H263")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("RGB")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("CVID")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("MPEG")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("MP4V")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("mpeglayer3")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                } else if (aFormat.toString().startsWith("XVID")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
                }else if (aFormat.toString().startsWith("MJPG")) {
                    myTrackControl[i].setRenderer(myVideoRndrr);
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

    public void close() {
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

            /*
            // Add the bar if not already there ...
            if (progressBar == null) {
                if ((progressBar = cc.getControlComponent()) != null) {
                    panel.add(progressBar);
                    panel.setSize(progressBar.getPreferredSize());
                    //validate();
                }
            }*/
        } else if (event instanceof EndOfMediaEvent) {
            // We've reached the end of the media; rewind and
            // start over
            player.setMediaTime(new Time(0));
            player.start();
        } else if (event instanceof ControllerErrorEvent) {
            // Tell TypicalPlayerApplet.start() to call it a day
            player = null;
            Fatal(((ControllerErrorEvent) event).getMessage());
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
