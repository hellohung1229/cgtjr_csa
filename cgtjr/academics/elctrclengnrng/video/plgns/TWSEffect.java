package cgtjr.academics.elctrclengnrng.video.plgns;

import java.awt.Dimension;
import javax.media.*;
import javax.media.format.*;
//import javax.media.format.audio.*;

public class TWSEffect implements Effect {

   /** The effect name **/
   private static String EffectName = "GainEffect";
   /** chosen input Format **/
   protected VideoFormat inputFormat;
   /** chosen output Format **/
   protected VideoFormat outputFormat;
   /** supported input Formats **/
   protected Format[] supportedInputFormats = new Format[0];
   /** supported output Formats **/
   protected Format[] supportedOutputFormats = new Format[0];
   /** selected Gain **/
   protected float gain = 2.0F;

           //Format format = null;//tracks[i].getFormat();
            //Dimension size = ((VideoFormat) format).getSize();
            float frameRate = 20;//((VideoFormat) format).getFrameRate();
            int w = 780;//(size.width % 8 == 0 ? size.width : (int) (size.width / 8) * 8);
            int h = 420;//(size.height % 8 == 0 ? size.height : (int) (size.height / 8) * 8);

   /** initialize the formats **/
   public TWSEffect() {


      supportedInputFormats =        
              new Format[]{
                   new VideoFormat(VideoFormat.JPEG,
                    new Dimension(w, h),
                    Format.NOT_SPECIFIED,
                    Format.byteArray,
                    frameRate)
              };
      supportedOutputFormats = null;
      /*new Format[]{
                 new VideoFormat(VideoFormat.JPEG_RTP,
                    new Dimension(w, h),
                    Format.NOT_SPECIFIED,
                    Format.byteArray,
                    frameRate)
              };*/
   }

   /** get the resources needed by this effect **/
   public void open() throws ResourceUnavailableException {
   }

   /** free the resources allocated by this codec **/
   public void close() {
   }

   /** reset the codec **/
   public void reset() {
   }

   /** no controls for this simple effect **/
   public Object[] getControls() {
      return (Object[]) new Control[0];
   }

   /**
    * Return the control based on a control type for the effect.
    **/
   public Object getControl(String controlType) {
      try {
         Class cls = Class.forName(controlType);
         Object cs[] = getControls();
         for (int i = 0; i < cs.length; i++) {
            if (cls.isInstance(cs[i])) {
               return cs[i];
            }
         }
         return null;
      } catch (Exception e) { // no such controlType or such control
         return null;
      }
   }

   /************** format methods *************/
   /** set the input format **/
   public Format setInputFormat(Format input) {
// the following code assumes valid Format
      return (new Format[1])[0];
   }

   /** set the output format **/
   public Format setOutputFormat(Format output) {
      return (new Format[1])[0];
   }

   /** get the input format **/
   protected Format getInputFormat() {
      return (new Format[1])[0];
   }

   /** get the output format **/
   protected Format getOutputFormat() {
      //return outputFormat;
      return (new Format[1])[0];
   }

   /** supported input formats **/
   public Format[] getSupportedInputFormats() {
      return (new Format[0]);
   }

   /** output Formats for the selected input format **/
   public Format[] getSupportedOutputFormats(Format in) {
      return new Format[0];
   }

   /** gain accessor method **/
   public void setGain(float newGain) {
   }

   /** return effect name **/
   public String getName() {
      return EffectName;
   }

   /** do the processing **/
   public int process(Buffer inputBuffer, Buffer outputBuffer) {
      System.out.println("TWSEffect : test ... ");
      return BUFFER_PROCESSED_OK;
   }

   /**
    * Utility: validate that the Buffer object's data size is at least
    * newSize bytes.
    * @return array with sufficient capacity
    **/
   protected byte[] validateByteArraySize(Buffer buffer, int newSize) {
      
      Object objectArray = buffer.getData();
      byte[] typedArray;
      if (objectArray instanceof byte[]) { // is correct type AND not null
         typedArray = (byte[]) objectArray;
         if (typedArray.length >= newSize) { // is sufficient capacity
            return typedArray;
         }
      }
      System.out.println(getClass().getName() +
              " : allocating byte[" + newSize + "] ");
      typedArray = new byte[newSize];
      buffer.setData(typedArray);
      return typedArray;
       
   }

   /** utility: update the output buffer fields **/
   protected void updateOutput(Buffer outputBuffer,
           Format format, int length, int offset) {

   }
}