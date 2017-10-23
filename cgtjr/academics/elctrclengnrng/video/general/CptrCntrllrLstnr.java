package cgtjr.academics.elctrclengnrng.video.general;

import cgtjr.academics.elctrclengnrng.video.shapepnt.ShpTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.color.ColorDiffBndry;
import java.awt.*;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.media.*;
import javax.media.control.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.jmf_camera.DeviceInfo;
import cgtjr.academics.elctrclengnrng.jmf_camera.Stdout;
import cgtjr.academics.general.FileNameVar;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;

public class CptrCntrllrLstnr implements ControllerListener, Runnable {

   private static boolean debugDeviceList = false;

   //private static String				defaultVideoDeviceName = "Microsoft WDM Image Capture";
   private static String defaultVideoDeviceName = "WDM Image Capture";
   //private static String				defaultVideoDeviceName = "Logitech USB Video Camera";
   private static String defaultAudioDeviceName = "DirectSoundCapture";

   //private static String				defaultAudioDeviceName = "JavaSound audio capture";
   //private static String				defaultVideoFormatString = "size=176x144, encoding=yuv, maxdatalength=38016";
   //private static String defaultVideoFormatString = "size=720x480, encoding=dvsd, maxdatalength=120000";
   private static String defaultVideoFormatString = "size=320x240, encoding=rgb, maxdatalength=230400";   
   //private static String				defaultVideoFormatString = "size=160x120, encoding=yuv, maxdatalength=28800";

   //private static String				defaultAudioFormatString = "linear, 16000.0 hz, 8-bit, mono, unsigned";

   //private static String				defaultAudioFormatString = "linear, 8000.0 hz, 16-bit, mono, signed";
   //private static String				defaultAudioFormatString = "linear, 8000.0 hz, 16-bit, mono, littleendian, signed";
   private static String defaultAudioFormatString = "linear, 8000.0 hz, 8-bit, mono, unsigned";
   private static CaptureDeviceInfo captureVideoDevice = null;
   private static CaptureDeviceInfo captureAudioDevice = null;
   private static VideoFormat captureVideoFormat = null;
   private static AudioFormat captureAudioFormat = null;
   private Integer stateLock = new Integer(0);
   private boolean failed = false;
   private Processor player = null;
   private Component visualComponent = null;
   private Component controlComponent = null;
   private Component progressBar = null;
   private Panel panel = null;
   private int controlPanelHeight = 0;
   private int videoWidth = 0;
   private int videoHeight = 0;
   private String mediaFile = null;
   private boolean realized = false;
   private boolean configured = false;
   private VideoRndrr videoRender;
   private FrameParser videoParser;

   private FileNameVar aDmnsnVar;// = new FileNameVar("data/images/textures/white3_3x3.jpg",10,10,0,30,40,0,280,270,0,10,10,1,180,140,0);
   private ImageBndry aShpBndry;//= new SSDImgBndry(aDmnsnVar);
   private ShpTracker aShpTracker;// = new ShpTracker(aShpBndry);
   PointParser vdPointParser;// = new PointParser(aShpTracker);

   public CptrCntrllrLstnr() {
      aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg",0,0,0,10,10,0,330,230,0,5,5,1,170,120,0);
      //aShpBndry = new SSDImgBndry(aDmnsnVar);
      aShpBndry = new ColorDiffBndry(aDmnsnVar);      
      aShpTracker = new ShpTracker(aShpBndry);
      vdPointParser = new PointParser(aShpTracker);
      videoParser = vdPointParser;
   }
   public ShpTracker getShpTracker()
   {
      return aShpTracker;
   }
   public void setMediaFile(String myMediaFile) {
      mediaFile = myMediaFile;
   }

   public void setParser(FrameParser myParser) {
      videoParser = myParser;
   }

   public void setVideoRender(VideoRndrr myVideoRender) {
      videoRender = myVideoRender;
   }

   public Panel getPanel()
   {
      return panel;
   }
   /**
    * Read the applet file parameter and create the media
    * player.
    */
   public Panel strtApplctn() {
      System.out.println("VideoAnalyzer.strtApplctn file " + mediaFile);
      panel = new Panel();
      panel.setLayout(null);

      panel.setBounds(0, 0, 320, 240);

      MediaLocator mrl = null;

      Stdout.log("get list of all media devices ...");
      java.util.Vector deviceListVector = CaptureDeviceManager.getDeviceList(null);
      if (deviceListVector == null) {
         Stdout.log("... error: media device list vector is null, program aborted");
         System.exit(0);
      }
      if (deviceListVector.size() == 0) {
         Stdout.log("... error: media device list vector size is 0, program aborted");
         System.exit(0);
      }

      for (int x = 0; x < deviceListVector.size(); x++) {
         // display device name
         CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
         String deviceInfoText = deviceInfo.getName();
         if (debugDeviceList) {
            Stdout.log("device " + x + ": " + deviceInfoText);
         }

         // display device formats
         Format deviceFormat[] = deviceInfo.getFormats();
         for (int y = 0; y < deviceFormat.length; y++) {
            // serach for default video device
            if (captureVideoDevice == null) {
               if (deviceFormat[y] instanceof VideoFormat) {
                  if (deviceInfo.getName().indexOf(defaultVideoDeviceName) >= 0) {
                     captureVideoDevice = deviceInfo;
                     Stdout.log(">>> capture video device = " + deviceInfo.getName());
                  }
               }
            }

            // search for default video format
            System.out.println("TestQuickCamPro: DeviceInfo.formatToString(deviceFormat[y])=" + DeviceInfo.formatToString(deviceFormat[y]));
            if (captureVideoDevice == deviceInfo) {
               if (captureVideoFormat == null) {
                  if (DeviceInfo.formatToString(deviceFormat[y]).indexOf(defaultVideoFormatString) >= 0) {
                     captureVideoFormat = (VideoFormat) deviceFormat[y];
                     Stdout.log(">>> capture video format = " + DeviceInfo.formatToString(deviceFormat[y]));
                  }
               }
            }

            // serach for default audio device
            if (captureAudioDevice == null) {
               if (deviceFormat[y] instanceof AudioFormat) {
                  if (deviceInfo.getName().indexOf(defaultAudioDeviceName) >= 0) {
                     captureAudioDevice = deviceInfo;
                     Stdout.log(">>> capture audio device = " + deviceInfo.getName());
                  }
               }
            }

            // search for default audio format
            if (captureAudioDevice == deviceInfo) {
               if (captureAudioFormat == null) {
                  if (DeviceInfo.formatToString(deviceFormat[y]).indexOf(defaultAudioFormatString) >= 0) {
                     captureAudioFormat = (AudioFormat) deviceFormat[y];
                     Stdout.log(">>> capture audio format = " + DeviceInfo.formatToString(deviceFormat[y]));
                  }
               }
            }

            if (debugDeviceList) {
               Stdout.log(" - format: " + DeviceInfo.formatToString(deviceFormat[y]));
            }
         }
      }
      Stdout.log("... list completed.");

      // if args[x] = "-dd" terminate now
      // --------------------------------
      if (debugDeviceList) {
         System.exit(0);
      }


      // setup video data source
      // -----------------------
      MediaLocator videoMediaLocator = captureVideoDevice.getLocator();
      try {
         // Create a media locator from the file name
         // Create an instance of a player for this media
         mrl = videoMediaLocator;
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
               Thread.currentThread().sleep(100L);
               ;
            } catch (InterruptedException e) {
               // ignore
            }
         }
         player.setContentDescriptor(null);
         TrackControl theTrackControl[] = player.getTrackControls();
         VideoRndrr anotherVideoRndrr = new VideoRndrr();
         anotherVideoRndrr.setParser(videoParser);
         setRenderer(theTrackControl, anotherVideoRndrr);
      } catch (MalformedURLException e) {
         Fatal("Invalid media file URL!");
      } catch (IOException e) {
         Fatal("IO exception creating player for " + mrl);
      }
      start();
      return panel;
   }

   public Panel strtApplctn2() {
      System.out.println("VideoAnalyzer.strtApplctn file " + mediaFile);
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
               Thread.currentThread().sleep(100L);
               ;
            } catch (InterruptedException e) {
               // ignore
            }
         }
         player.setContentDescriptor(null);
         TrackControl theTrackControl[] = player.getTrackControls();

         VideoRndrr anotherVideoRndrr = new VideoRndrr();
         anotherVideoRndrr.setParser(videoParser);
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
           VideoRndrr myVideoRndrr) {
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
            }else if (aFormat.toString().startsWith("DVSD")) {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }
         }
      } catch (javax.media.UnsupportedPlugInException upe) {
         System.err.println("VideoAnalyzer:" + upe.getMessage());
      }
   }

   /**
    * Start media file playback. This function is called the
    * first time that the Applet runs and every
    * time the user re-enters the page.
    */
   public void start() {
      if (player != null) {
         player.start();
      }
   }

   /**
    * Stop media file playback and release resource before
    * leaving the page.
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
    * This controllerUpdate function must be defined in order to
    * implement a ControllerListener interface. This
    * function will be called whenever there is a media event
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
            //validate();
            }
         }
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
    public void run() {        
        strtApplctn();
    }
}
