package cgtjr.academics.elctrclengnrng.jmf_camera;

import cgtjr.academics.elctrclengnrng.video.general.VideoRndrr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.SDCLShpTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.SSDImgBndry;
import cgtjr.academics.elctrclengnrng.video.*;
import cgtjr.academics.elctrclengnrng.video.plgns.TWSEffect;
import cgtjr.academics.general.FileNameVar;
import java.awt.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.format.*;
import javax.media.control.TrackControl;
import javax.media.control.QualityControl;
import java.io.*;
import javax.swing.JFrame;

public class CptreFltrTrnsmt {

   private PointParser vdPointParser;
   private static boolean debugDeviceList = false;
   // Input MediaLocator
   // Can be a file or http or capture source
   //private static String defaultVideoDeviceName = "WDM Image Capture";
   private static String				defaultVideoDeviceName = "Logitech USB Video Camera";   
   //private static String				defaultVideoDeviceName = "Logitech";
   private static String defaultAudioDeviceName = "DirectSoundCapture";
   //////--private static String defaultVideoFormatString = "size=720x480, encoding=dvsd, maxdatalength=120000";
   //private static String				defaultVideoFormatString = "size=160x120, encoding=yuv, maxdatalength=28800";
   private static String				defaultVideoFormatString = "size=320x240, encoding=rgb, maxdatalength=230400";   

   //private static String				defaultAudioFormatString = "linear, 16000.0 hz, 8-bit, mono, unsigned";

   //private static String				defaultAudioFormatString = "linear, 8000.0 hz, 16-bit, mono, signed";
   //private static String				defaultAudioFormatString = "linear, 8000.0 hz, 16-bit, mono, littleendian, signed";
   private static String defaultAudioFormatString = "linear, 8000.0 hz, 8-bit, mono, unsigned";
   private static CaptureDeviceInfo captureVideoDevice = null;
   private static CaptureDeviceInfo captureAudioDevice = null;
   private static VideoFormat captureVideoFormat = null;
   private static AudioFormat captureAudioFormat = null;
   private MediaLocator locator;
   private String ipAddress;
   private String port;
   private Processor processor = null;
   private DataSink rtptransmitter = null;
   private DataSource dataOutput = null;
   private VideoRndrr myVideoRndrr;

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

   public CptreFltrTrnsmt(MediaLocator locator,
           String ipAddress,
           String port) {

      this.locator = locator;
      this.ipAddress = ipAddress;
      this.port = port;
   }

   public CptreFltrTrnsmt() {
      this.locator = locator;
      this.ipAddress = "127.0.0.1";
      this.port = "42050";
      FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg",10,10,0,30,40,0,280,270,0,10,10,1,180,140,0);
      ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);
      SDCLShpTracker aShpTracker = new SDCLShpTracker(aShpBndry);
      vdPointParser = new PointParser(aShpTracker);
      myVideoRndrr = new VideoRndrr(vdPointParser);
   }

   /**
    * Starts the transmission. Returns null if transmission started ok.
    * Otherwise it returns a string with the reason why the setup failed.
    */
   public synchronized String start() {
      String result;

      // Create a processor for the specified media locator
      // and program it to output JPEG/RTP
      result = createProcessor();
      if (result != null) {
         return result;
      }

      // Create an RTP session to transmit the output of the
      // processor to the specified IP address and port no.
      result = createTransmitter();
      if (result != null) {
         processor.close();
         processor = null;
         return result;
      }

      // Start the transmission
      processor.start();

      return null;
   }

   /**
    * Stops the transmission if already started
    */
   public void stop() {
      synchronized (this) {
         if (processor != null) {
            processor.stop();
            processor.close();
            processor = null;
            rtptransmitter.close();
            rtptransmitter = null;
         }
      }
   }

   private String createProcessor() {
      Stdout.log("get list of all media devices ...");
      java.util.Vector deviceListVector = CaptureDeviceManager.getDeviceList(null);
      if (deviceListVector == null) {
         Stdout.log("... error: media device list vector is null, program aborted");
         System.exit(0);
      }
      System.out.println("CptreFltrTrnsmt: size = " + deviceListVector.size());
      for (int x = 0; x < deviceListVector.size(); x++) {
         // display device name
         CaptureDeviceInfo deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
         String deviceInfoText = deviceInfo.getName();
         System.out.println("CptreFltrTrnsmt: deviceInfoText = " + deviceInfoText);
         if (debugDeviceList) {
            Stdout.log("device " + x + ": " + deviceInfoText);
         }

         System.out.println("CptreFltrTrnsmt: dn = " + deviceInfo.getName() + ", ddn = " + defaultVideoDeviceName);

         Format deviceFormat[] = deviceInfo.getFormats();
         for (int y = 0; y < deviceFormat.length; y++) {
            // serach for default video device
            System.out.println("CptreFltrTrnsmt: dn = " + deviceInfo.getName() + ", ddn = " + defaultVideoDeviceName);
            if (captureVideoDevice == null) {
               if (deviceFormat[y] instanceof VideoFormat) {
                  System.out.println("CptreFltrTrnsmt: defaultVideoDeviceName = "+defaultVideoDeviceName+", deviceFormat[y] "+deviceFormat[y]);
                  if (deviceInfo.getName().indexOf(defaultVideoDeviceName) >= 0) {
                     captureVideoDevice = deviceInfo;
                     Stdout.log(">>> capture video device = " + deviceInfo.getName());
                  }
               }
            }
            // search for default video format
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
      if (debugDeviceList) {
         System.exit(0);
      }
      // setup video data source
      // -----------------------
      System.out.println("CaptureTrnsmit: Test 1");
      MediaLocator videoMediaLocator = captureVideoDevice.getLocator();
      System.out.println("CaptureTrnsmit: Test 1b");
      DataSource videoDataSource = null;
      try {
         videoDataSource = javax.media.Manager.createDataSource(videoMediaLocator);
      } catch (IOException ie) {
         Stdout.logAndAbortException(ie);
      } catch (NoDataSourceException nse) {
         Stdout.logAndAbortException(nse);
      }
      if (!DeviceInfo.setFormat(videoDataSource, captureVideoFormat)) {
         Stdout.log("Error: unable to set video format - program aborted");
         System.exit(0);
      }
      System.out.println("CaptureTrnsmit: Test 2");
      // setup audio data source
      // -----------------------
      MediaLocator audioMediaLocator = captureAudioDevice.getLocator();
      DataSource audioDataSource = null;
      try {
         audioDataSource = javax.media.Manager.createDataSource(audioMediaLocator);
      } catch (IOException ie) {
         Stdout.logAndAbortException(ie);
      } catch (NoDataSourceException nse) {
         Stdout.logAndAbortException(nse);
      }

      if (!DeviceInfo.setFormat(audioDataSource, captureAudioFormat)) {
         Stdout.log("Error: unable to set audio format - program aborted");
         System.exit(0);
      }
      // merge the two data sources
      // --------------------------
      System.out.println("CaptureTrnsmit: Test 3");
      DataSource mixedDataSource = null;
      try {
         DataSource dArray[] = new DataSource[2];
         dArray[0] = videoDataSource;
         dArray[1] = audioDataSource;
         mixedDataSource = javax.media.Manager.createMergingDataSource(dArray);
      } catch (IncompatibleSourceException ise) {
         Stdout.logAndAbortException(ise);
      }

      // create a new processor
      // ----------------------
      // setup output file format  ->> msvideo
      FileTypeDescriptor outputType = new FileTypeDescriptor(FileTypeDescriptor.MSVIDEO);
      System.out.println("CaptureTrnsmit: Test 4");
      //Format outputFormat[] = new Format[2];
      Format outputFormat[] = new Format[1];
      //outputFormat[0] = new VideoFormat(VideoFormat.INDEO50);
      outputFormat[0] = new VideoFormat(VideoFormat.JPEG_RTP);
      //outputFormat[1] = new AudioFormat(AudioFormat.GSM_MS /* LINEAR */);
      //outputFormat[1] = new AudioFormat(AudioFormat.LINEAR /* LINEAR */);
      //ProcessorModel processorModel = new ProcessorModel(mixedDataSource, outputFormat, outputType);
      //Processor processor = null;
      try {
         //processor = Manager.createRealizedProcessor(processorModel);
         processor = Manager.createProcessor(videoDataSource);

      } catch (IOException e) {
         Stdout.logAndAbortException(e);
      } catch (NoProcessorException e) {
         Stdout.logAndAbortException(e);
      }
      //catch (CannotRealizeException e) { Stdout.logAndAbortException(e); }
      //DataSource source = processor.getDataOutput();

      processor.configure();

      // Wait for it to configure
      boolean result = waitForState(processor, Processor.Configured);
      if (result == false) {
         return "Couldn't configure processor";
      }

      // Get the tracks from the processor
      //TrackControl[] tracks = processor.getTrackControls();

      TrackControl[] tracks = processor.getTrackControls();
      //setCodec(tracks, new TWSEffect());
      setRenderer(tracks, myVideoRndrr);
      // Do we have atleast one track?

      if (tracks == null || tracks.length < 1) {
         return "Couldn't find tracks in processor";
      }

      boolean programmed = false;

      // Search through the tracks for a video track
      for (int i = 0; i < tracks.length; i++) {
         Format format = tracks[i].getFormat();
         if (tracks[i].isEnabled() &&
                 format instanceof VideoFormat &&
                 !programmed) {

            // Found a video track. Try to program it to output JPEG/RTP
            // Make sure the sizes are multiple of 8's.
            Dimension size = ((VideoFormat) format).getSize();
            float frameRate = ((VideoFormat) format).getFrameRate();
            int w = (size.width % 8 == 0 ? size.width : (int) (size.width / 8) * 8);
            int h = (size.height % 8 == 0 ? size.height : (int) (size.height / 8) * 8);
            VideoFormat jpegFormat = new VideoFormat(VideoFormat.JPEG_RTP,
                    new Dimension(w, h),
                    Format.NOT_SPECIFIED,
                    Format.byteArray,
                    frameRate);
            //tracks[i].setFormat(jpegFormat);
            System.err.println("Video transmitted as:");
            System.err.println("  " + jpegFormat);
            // Assume succesful
            programmed = true;
         } else {
            tracks[i].setEnabled(false);
         }
      }
      if (!programmed) {
         return "Couldn't find video track";
      }
      ////setRenderer(tracks, myVideoRndrr);
      
      // Set the output content descriptor to RAW_RTP
      ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
      processor.setContentDescriptor(cd);
      //setCodec(tracks, new TWSEffect());
      // Realize the processor. This will internally create a flow
      // graph and attempt to create an output datasource for JPEG/RTP
      // video frames.
      result = waitForState(processor, Controller.Realized);
      if (result == false) {
         return "Couldn't realize processor";
      }
      // Set the JPEG quality to .5.
      setJPEGQuality(processor, 0.5f);
      // Get the output data source of the processor
      dataOutput = processor.getDataOutput();
      //processor.configure();
      // Wait for it to configure


      
      return null;
   }
   public void setCodec(TrackControl[] myTrackControl,
                           Effect myEffect)
   {
      int aSize = myTrackControl.length;
      Effect anEffect[] = new Effect[1];
      anEffect[0] = myEffect;
      try{
         for(int i=0;i<aSize;i++)
         {
            Format aFormat = myTrackControl[i].getFormat();
            System.out.println("CptrFltrTrnsmt.setCodec():"+myTrackControl[i].getFormat());
            if(aFormat.toString().startsWith("H263"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("RGB"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("CVID"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("MPEG"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("MP4V"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("mpeglayer3"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("XVID"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("DVSD"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }else if(aFormat.toString().startsWith("JPEG"))
            {
               myTrackControl[i].setCodecChain(anEffect);
            }
         }
      }catch(javax.media.UnsupportedPlugInException upe){
         System.err.println("VideoAnalyzer:"+upe.getMessage());
      }
   }
   public void setRenderer(TrackControl[] myTrackControl,
                           VideoRndrr myVideoRndrr)
   {
      int aSize = myTrackControl.length;
      try{
         for(int i=0;i<aSize;i++)
         {
            Format aFormat = myTrackControl[i].getFormat();
            System.out.println("CptreFltrTrnsmt.setRenderer():"+myTrackControl[i].getFormat());
            if(aFormat.toString().startsWith("H263"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("RGB"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("CVID"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("MPEG"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("MP4V"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("mpeglayer3"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("XVID"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("DVSD"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }else if(aFormat.toString().startsWith("JPEG"))
            {
               myTrackControl[i].setRenderer(myVideoRndrr);
            }
         }
      }catch(javax.media.UnsupportedPlugInException upe){
         System.err.println("VideoAnalyzer:"+upe.getMessage());
      }
   }

   // Creates an RTP transmit data sink. This is the easiest way to create
   // an RTP transmitter. The other way is to use the RTPSessionManager API.
   // Using an RTP session manager gives you more control if you wish to
   // fine tune your transmission and set other parameters.
   private String createTransmitter() {
      // Create a media locator for the RTP data sink.
      // For example:
      //    rtp://129.130.131.132:42050/video
      String rtpURL = "rtp://" + ipAddress + ":" + port + "/video";
      MediaLocator outputLocator = new MediaLocator(rtpURL);
      System.out.println(rtpURL);
      // Create a data sink, open it and start transmission. It will wait
      // for the processor to start sending data. So we need to start the
      // output data source of the processor. We also need to start the
      // processor itself, which is done after this method returns.
      try {
         rtptransmitter = Manager.createDataSink(dataOutput, outputLocator);
         rtptransmitter.open();
         rtptransmitter.start();
         dataOutput.start();
         processor.start();
      } catch (MediaException me) {
         return "Couldn't create RTP data sink";
      } catch (IOException ioe) {
         return "Couldn't create RTP data sink";
      }

      return null;
   }

   /**
    * Setting the encoding quality to the specified value on the JPEG encoder.
    * 0.5 is a good default.
    */
   void setJPEGQuality(Player p, float val) {

      Control cs[] = p.getControls();
      QualityControl qc = null;
      VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

      // Loop through the controls to find the Quality control for
      // the JPEG encoder.
      for (int i = 0; i < cs.length; i++) {

         if (cs[i] instanceof QualityControl &&
                 cs[i] instanceof Owned) {
            Object owner = ((Owned) cs[i]).getOwner();

            // Check to see if the owner is a Codec.
            // Then check for the output format.
            if (owner instanceof Codec) {
               Format fmts[] = ((Codec) owner).getSupportedOutputFormats(null);
               for (int j = 0; j < fmts.length; j++) {
                  if (fmts[j].matches(jpegFmt)) {
                     qc = (QualityControl) cs[i];
                     qc.setQuality(val);
                     System.err.println("- Setting quality to " +
                             val + " on " + qc);
                     break;
                  }
               }
            }
            if (qc != null) {
               break;
            }
         }
      }
   }
   /****************************************************************
    * Convenience methods to handle processor's state changes.
    ****************************************************************/
   private Integer stateLock = new Integer(0);
   private boolean failed = false;

   Integer getStateLock() {
      return stateLock;
   }

   void setFailed() {
      failed = true;
   }

   private synchronized boolean waitForState(Processor p, int state) {
      System.out.println("CptreFltrTrnsmt : waitForState ... ");
      p.addControllerListener(new StateListener());
      failed = false;

      // Call the required method on the processor
      if (state == Processor.Configured) {
         p.configure();
      } else if (state == Processor.Realized) {
         p.realize();
      }

      // Wait until we get an event that confirms the
      // success of the method, or a failure event.
      // See StateListener inner class
      while (p.getState() < state && !failed) {
         synchronized (getStateLock()) {
            try {
               getStateLock().wait();
            } catch (InterruptedException ie) {
               return false;
            }
         }
      }

      if (failed) {
         return false;
      } else {
         return true;
      }
   }

   /****************************************************************
    * Inner Classes
    ****************************************************************/
   class StateListener implements ControllerListener {

      public void controllerUpdate(ControllerEvent ce) {

         // If there was an error during configure or
         // realize, the processor will be closed
         if (ce instanceof ControllerClosedEvent) {
            setFailed();
         }

         // All controller events, send a notification
         // to the waiting thread in waitForState method.
         if (ce instanceof ControllerEvent) {
            synchronized (getStateLock()) {
               getStateLock().notifyAll();
            }
         }
      }
   }

   /****************************************************************
    * Sample Usage for CptreFltrTrnsmt class
    ****************************************************************/
   public static void main(String[] args) {
      //JFrame aFrame = new JFrame();
      //aFrame.setSize(720,480);
      
   	//Panel panel = new Panel();
	   //panel.setLayout( null );

	   //panel.setBounds(0, 0, 720, 480);
      // We need three parameters to do the transmission
      // For example,
      // java CptreFltrTrnsmt file:/C:/media/test.mov  129.130.131.132 42050

      if (args.length < 3) {
         //System.err.println("Usage: CptreFltrTrnsmt <sourceURL> <destIP> <destPort>");
         //System.exit(-1);
      }

      // Create a video transmit object with the specified params.
      //CptreFltrTrnsmt vt = new CptreFltrTrnsmt(new MediaLocator(args[0]),
      //        args[1],
      //        args[2]);
      // Start the transmission
      CptreFltrTrnsmt vt = new CptreFltrTrnsmt();
      String result = vt.start();


      //Component aComponent = vt.rtrvVslCmpnt();
      //panel.add(aComponent);
      //aFrame.add(aComponent);
      // result will be non-null if there was an error. The return
      // value is a String describing the possible error. Print it.
      if (result != null) {
         System.err.println("Error : " + result);
         System.exit(0);
      }
      System.err.println("Start transmission for 60 seconds...");

      // Transmit for 60 seconds and then close the processor
      // This is a safeguard when using a capture data source
      // so that the capture device will be properly released
      // before quitting.
      // The right thing to do would be to have a GUI with a
      // "Stop" button that would call stop on CptreFltrTrnsmt
      try {
         Thread.currentThread().sleep(60000);
      } catch (InterruptedException ie) {
      }

      // Stop the transmission
      vt.stop();

      System.err.println("...transmission ended.");

      System.exit(0);
   }

   public void Fatal(String myStrng)
   {
      System.err.println(myStrng);
   }
   public Panel rtrvPanel()
   {
      return panel;
   }
}