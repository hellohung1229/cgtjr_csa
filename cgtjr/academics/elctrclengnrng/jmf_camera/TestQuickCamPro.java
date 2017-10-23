/******************************************************
 * File: TestQuickCamPro.java
 * created 24.07.2001 21:40:13 by David Fischer, fischer@d-fischer.com
 *
 * Description: this test program will capture the video and audio stream
 * from a Logitech QuickCam� Pro 3000 USB camera for 10 seconds and stores
 * it on a file, named "testcam.avi". You can use the microsoft windows
 * media player to display this file.
 *
 * operating system: Windows 2000
 * required hardware:  Logitech QuickCam� Pro 3000
 * required software: jdk 1.3 or jdk1.4 plus jmf2.1.1 (www.javasoft.com)
 *
 * source files: DeviceInfo.java, MyDataSinkListener.java,
 *               Stdout.java, TestQuickCamPro.java
 *
 * You can just start this program with "java TestQuickCamPro"
 *
 * hint: please make shure that you setup first the logitech camerea drives
 * and jmf2.1.1 correctly. "jmf.jar" must be part of your CLASSPATH.
 *
 * useful links:
 * - http://java.sun.com/products/java-media/jmf/2.1.1/index.html
 * - http://java.sun.com/products/java-media/jmf/2.1.1/solutions/index.html
 *
 * with some small modifications, this program will work with any USB camera.
 */
package cgtjr.academics.elctrclengnrng.jmf_camera;

import java.awt.Dimension;
import java.io.*;

import javax.media.*;
import javax.media.control.TrackControl;
import javax.media.format.*;
import javax.media.protocol.*;

public class TestQuickCamPro {

   private static boolean debugDeviceList = false;

   private static String				defaultVideoDeviceName = "Microsoft WDM Image Capture";
   //private static String defaultVideoDeviceName = "WDM Image Capture";
   //private static String				defaultVideoDeviceName = "Logitech USB Video Camera";
   //private static String defaultAudioDeviceName = "DirectSoundCapture";

   private static String				defaultAudioDeviceName = "JavaSound audio capture";
   //private static String				defaultVideoFormatString = "size=176x144, encoding=yuv, maxdatalength=38016";
   //private static String defaultVideoFormatString = "size=720x480, encoding=dvsd, maxdatalength=120000";
   private static String				defaultVideoFormatString = "size=160x120, encoding=yuv, maxdatalength=28800";

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

   public static void main(String args[]) {
      TestQuickCamPro aTestQuickCamPro = new TestQuickCamPro();
      aTestQuickCamPro.proc(args);
   }

   public void proc(String args[]) {
      // get command line arguments
      for (int x = 0; x < args.length; x++) {
         // -dd = debug devices list -> display list of all media devices - and exit
         if (args[x].toLowerCase().compareTo("-dd") == 0) {
            debugDeviceList = true;
         }
      }

      // get a list of all media devices, search default devices and formats, and print it out if args[x] = "-dd"
      // --------------------------------------------------------------------------------------------------------

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
      System.out.println("deviceListVector.size() == " + deviceListVector.size() + "debugDeviceList ==" + debugDeviceList);
      for (int x = 0; x < deviceListVector.size(); x++)
      {
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

      // setup output video and audio data format
      //Format outputFormat[] = new Format[2];
      Format outputFormat[] = new Format[1];
      //outputFormat[0] = new VideoFormat(VideoFormat.INDEO50);
      outputFormat[0] = new VideoFormat(VideoFormat.JPEG_RTP);
      //outputFormat[1] = new AudioFormat(AudioFormat.GSM_MS /* LINEAR */);
      //outputFormat[1] = new AudioFormat(AudioFormat.LINEAR /* LINEAR */);

      Dimension size = new Dimension(720, 480);//((VideoFormat)format).getSize();
      float frameRate = 30;//((VideoFormat)format).getFrameRate();
      int w = (size.width % 8 == 0 ? size.width : (int) (size.width / 8) * 8);
      int h = (size.height % 8 == 0 ? size.height : (int) (size.height / 8) * 8);
      VideoFormat jpegFormat = new VideoFormat(VideoFormat.JPEG_RTP,
              new Dimension(w, h),
              Format.NOT_SPECIFIED,
              Format.byteArray,
              frameRate);
      outputFormat[0] = jpegFormat;
      // create processor
      //ProcessorModel processorModel = new ProcessorModel(mixedDataSource, outputFormat, outputType);
      ProcessorModel processorModel = new ProcessorModel(videoDataSource, outputFormat, outputType);
      Processor processor = null;
      try {
         //processor = Manager.createRealizedProcessor(processorModel);
         processor = Manager.createProcessor(videoDataSource);

      } catch (IOException e) {
         Stdout.logAndAbortException(e);
      } catch (NoProcessorException e) {
         Stdout.logAndAbortException(e);
      }
      //catch (CannotRealizeException e) { Stdout.logAndAbortException(e); }
      waitForState(processor, Controller.Realized);
      // get the output of the processor
      //processor.realize();
      //processor.start();
      TrackControl[] tracks = processor.getTrackControls();

      for (int i = 0; i < tracks.length; i++) {
         Format format = tracks[i].getFormat();
         if (tracks[i].isEnabled() &&
                 format instanceof VideoFormat) {
            System.out.println("Test 1");
            tracks[i].setFormat(jpegFormat);
         }
      }

      DataSource source = processor.getDataOutput();

      // create a File protocol MediaLocator with the location
      // of the file to which bits are to be written
      MediaLocator dest = new MediaLocator("file:testcam.avi");

      String rtpURL = "rtp://" + "127.0.0.1" + ":" + "42050" + "/video";
      //String rtpURL = "rtp://" + "127.0.0.1" + ":" + "42050" + "/audio";
      MediaLocator outputLocator = new MediaLocator(rtpURL);
      // create a datasink to do the file

      DataSink dataSink = null;
      MyDataSinkListener dataSinkListener = null;
      try {
         dataSink = Manager.createDataSink(source, outputLocator);
         //dataSink = Manager.createDataSink(source, dest);
         dataSinkListener = new MyDataSinkListener();
         dataSink.addDataSinkListener(dataSinkListener);
         dataSink.open();
      } catch (IOException e) {
         Stdout.logAndAbortException(e);
      } catch (NoDataSinkException e) {
         Stdout.logAndAbortException(e);
      } catch (SecurityException e) {
         Stdout.logAndAbortException(e);
      }

      // now start the datasink and processor
      try {
         dataSink.start();
      //source.start();
      } catch (IOException e) {
         Stdout.logAndAbortException(e);
      }

      //processor.start();

      Stdout.log("starting capturing ...");
      try {
         Thread.currentThread().sleep(10000);
      } catch (InterruptedException ie) {
      }	// capture for 10 seconds
      Stdout.log("... capturing done");

      // stop and close the processor when done capturing...
      // close the datasink when EndOfStream event is received...
      processor.stop();
      processor.close();

      dataSinkListener.waitEndOfStream(10);
      dataSink.close();

      Stdout.log("[all done]");
   }

   private synchronized boolean waitForState(Processor p, int state) {
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

   void setFailed() {
      failed = true;
   }

   Integer getStateLock() {
      return stateLock;
   }
}