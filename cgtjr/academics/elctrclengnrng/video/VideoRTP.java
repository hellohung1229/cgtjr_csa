/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

import java.io.IOException;
import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoProcessorException;
import javax.media.NotRealizedError;
import javax.media.Processor;
import javax.media.control.FormatControl;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

/**
 *
 * @author clayton g thomas jr
 */
public class VideoRTP {

   public static void main(String ars[]) {
// First find a capture device that will capture linear audio
// data at 8bit 8Khz
      Processor processor = null;
      AudioFormat format = new AudioFormat(AudioFormat.LINEAR,
              8000,
              8,
              1);
      Vector devices = CaptureDeviceManager.getDeviceList(format);
      CaptureDeviceInfo di = null;
      if (devices.size() > 0) {
         di = (CaptureDeviceInfo) devices.elementAt(0);
      } else {
         // exit if we could not find the relevant capturedevice.
         System.exit(-1);
      }
      // Create a processor for this capturedevice & exit if we
      // cannot create it
      try {
         processor = Manager.createProcessor(di.getLocator());
      } catch (IOException e) {
         System.exit(-1);
      } catch (NoProcessorException e) {
         System.exit(-1);
      }
// configure the processor
      processor.configure();
// block until it has been configured
      try{
         Thread.sleep(1000);
      }catch(Exception ioe){
         
      }
      processor.setContentDescriptor(
              new ContentDescriptor(ContentDescriptor.RAW));
      TrackControl track[] = processor.getTrackControls();
      boolean encodingOk = false;
// Go through the tracks and try to program one of them to
// output gsm data.
      for (int i = 0; i < track.length; i++) {
         if (!encodingOk && track[i] instanceof FormatControl) {
            if (((FormatControl) track[i]).setFormat(new AudioFormat(AudioFormat.GSM_RTP,
                    8000,
                    8,
                    1)) == null) {
               track[i].setEnabled(false);
            } else {
               encodingOk = true;
            }
         } else {
// we could not set this track to gsm, so disable it
            track[i].setEnabled(false);
         }
      }
// At this point, we have determined where we can send out
// gsm data or not.
// realize the processor
      if (encodingOk) {
         processor.realize();
         //System.out.println("VideoRTP: realized = "+processor..Realized);
         while(processor.Realized < 0)
         {

         }
// block until realized.
// get the output datasource of the processor and exit
// if we fail
         DataSource ds = null;

         try {
            ds = processor.getDataOutput();
         } catch (NotRealizedError e) {
            System.exit(-1);
         }
// hand this datasource to manager for creating an RTP
// datasink our RTP datasimnk will multicast the audio
         try {
            String url = "rtp://127.0.0.1:49150/audio/1";
            MediaLocator m = new MediaLocator(url);
            DataSink d = Manager.createDataSink(ds, m);
            d.open();
            d.start();
         } catch (Exception e) {
            System.exit(-1);
         }
      }

   }
}
