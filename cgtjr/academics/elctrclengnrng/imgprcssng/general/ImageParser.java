package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.general.gui.*;
import java.net.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//NOTE: This class should implement the same interface as ImgFrmeParser
public class ImageParser //extends Filter //implements ImageObserver
{
   public static final String type = "ImageParser";

   private Image inputImage;
   private Image outputImage;

   private int rgbColors[];

   private int imageLength;
   private int red[];
   private int green[];
   private int blue[];

   private int imageWidth = 0;
   private int imageHeight = 0;
  
   private ImageFilter prsdImageFilter;
   private static boolean isInitialized = false;

   public ImageParser(String fileName,ImageFilter myImageFilter)
   {
      prsdImageFilter = myImageFilter;   
      inputImage = UgotImage.createImage(fileName);         
   }
   public ImageParser(Image myInputImage,ImageFilter myImageFilter)
   {
      prsdImageFilter = myImageFilter;
      inputImage = myInputImage;
   }
   public ImageParser(ImageFilter myImageFilter)
   {
      prsdImageFilter = myImageFilter;            
   }
   public void setImage(String fileName)
   {
      inputImage = UgotImage.createImage(fileName);
   }
   public void setImage(URL fileName)
   {
      inputImage = UgotImage.createImage(fileName);
   }
   public void setImage(Image anImage)
   {
      inputImage = anImage;
   }
   public void initialize()
   {
      if(isInitialized == true)
      {
         return;
      }
      imageWidth = 0;
      imageHeight = 0;
      int transparency = 0;

      if(inputImage != null)
      {
         int c = 0;
         imageWidth = inputImage.getWidth(null);
         imageHeight = inputImage.getHeight(null);
         while(imageWidth <= 1 || imageHeight <=1)
         {
            imageWidth = inputImage.getWidth(null);
            imageHeight = inputImage.getHeight(null);

         }
         rgbColors = new int[imageWidth*imageHeight];
         imageLength = imageWidth * imageHeight;

      }
      isInitialized = true;
   }
   public void parse()
   {
      /*
      imageWidth = 0;
      imageHeight = 0;
      int transparency = 0;

      if(inputImage != null)
      {
         imageWidth = inputImage.getWidth(null);  
         imageHeight = inputImage.getHeight(null);
         while(imageWidth <= 1 || imageHeight <=1)
         {
            imageWidth = inputImage.getWidth(null);  
            imageHeight = inputImage.getHeight(null);
         }
         rgbColors = new int[imageWidth*imageHeight];
         imageLength = imageWidth * imageHeight;
         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,rgbColors);            
      }*/
         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,rgbColors);
      //prsdImageFilter.startPrsng(imageHeight,imageWidth);
      prsdImageFilter.initialize(imageWidth, imageHeight);
      /*
      for(int j=0;j<imageLength;j++)
      {
         prsdImageFilter.filter(rgbColors,j);
      }*/
      prsdImageFilter.filter(rgbColors,0);
      prsdImageFilter.finish();
      int imgData[] = prsdImageFilter.getFltrdData();

      System.out.println("ImageParser length = "+imgData.length+", width = "+imageWidth+", height = "+imageHeight);
      outputImage = UgotImage.createRGBImage(imgData,imageWidth,imageHeight,true);
   }
   
   public Image getOutputImage()
   {
      return outputImage;
   }
   public Image getOutputImage(Component aComponent)
   {
      Graphics aGraphics = null;
      Image anImage = null;

      if(aComponent != null)
      {
        anImage = aComponent.createImage(imageWidth,imageHeight);
        aGraphics = anImage.getGraphics();
        aGraphics.drawImage(outputImage, 0, 0, null);
      }else{
         anImage = outputImage;
      }
      return anImage;
   }
}