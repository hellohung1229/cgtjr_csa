/*
 * @(#)VideoRndrr.java
 *
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

//import cgtjr.academics.elctrclengnrng.video.OpticalFlowFltr;
import java.awt.Image;

/**
 * Renderer for RGB images using AWT Image.
 */
public class ImageRndrr implements RndrrPrcss
{
   private ImageFilter imgFilter;
   
   private FrameParser videoParser;
    
   //private CornerDetect_2 videoOpticalFlowFltr;
   private Image anImage ;
   private int startOffset;
   private int stopOffset;      

   public ImageRndrr() 
   {
      //imgFilter = new GrdntFilter();
      //imgFilter = new CornerDetect(); 
      //imgFilter = new OpticalFlowFltr();
      videoParser = new ImgArryParser(imgFilter);              
   }
   public ImageRndrr(ImageFilter myImageFilter) 
   {
      imgFilter = myImageFilter;
      videoParser = new ImgArryParser(imgFilter);         
   }   
   public ImageRndrr(FrameParser myFrameParser) 
   {
      videoParser = myFrameParser;              
   }
   public void setParser(FrameParser myParser)
   {
      videoParser = myParser;
   }
   public void setImgFilter(ImageFilter myImageFilter){
       imgFilter = myImageFilter;
   }
   public void process(Image myImage) 
   {
      //destImage = component.createImage(sourceImage);  
      videoParser.setTopOffset(startOffset);
      videoParser.setBottomOffset(stopOffset);
      System.out.println("ImageRndrr: startOffset = "+startOffset+", stopOffset = "+stopOffset);
      videoParser.setInputImage(myImage);
      //imgFilter.setOtptPxlData(myImage);      
      videoParser.bgnFrames();
      videoParser.parse();
      videoParser.endFrames();
      anImage = videoParser.getOutputImage();
   }
   public Image getOutputImage()
   {
      //System.out.println("ImageRndrr: width = " + anImage.getWidth(null));       
      return anImage;      
   }
    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startIndex) {
        this.startOffset = startIndex;
    }

    public int getStopOffset() {
        return stopOffset;
    }

    public void setStopOffset(int stopIndex) {
        this.stopOffset = stopIndex;
    }      
}
