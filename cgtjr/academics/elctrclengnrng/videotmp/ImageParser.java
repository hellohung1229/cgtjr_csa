package cgtjr.academics.elctrclengnrng.videotmp;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.gui.*;
import cgtjr.academics.math.geometry.shapebndry.DcsnBndry;
import java.net.*;
import java.awt.*;

public class ImageParser //extends Filter //implements ImageObserver
{
   public static final String type = "ImageParser";

   private Image inputImage;
   private Image outputImage;

   private int rgbColors[];

   private int imageLength;

   private int imageWidth = 0;
   private int imageHeight = 0;

   private ImageFilter prsdImageFilter;
   private static boolean isInitialized = false;
   private DcsnBndry aDcsnBndry;

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
      aDcsnBndry = new DcsnBndry();
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
         //return;
      }
      imageWidth = 0;
      imageHeight = 0;

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

         aDcsnBndry.setXMin(40);
         aDcsnBndry.setXMax(imageWidth-40);
         aDcsnBndry.setYMin(10);
         aDcsnBndry.setYMax(imageHeight-80);
         aDcsnBndry.setZMin(0);
         aDcsnBndry.setZMax(0);

      }
      //System.out.println("ImageParser.initialize() : inputImage = "+inputImage);
      isInitialized = true;
   }
   public void parse()
   {

      UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,rgbColors);
      //prsdImageFilter.startPrsng(imageHeight,imageWidth);
      prsdImageFilter.initialize(imageWidth, imageHeight);
      //for(int j=topStart;j<imageLength-bttmEnd;j=j+3)
      int aXMin = (int)aDcsnBndry.getXMin();
      int aYMin = (int)aDcsnBndry.getYMin();
      int aXMax = (int)aDcsnBndry.getXMax();
      int aYMax = (int)aDcsnBndry.getYMax();

      int aStart = ImageTool.rtrvIndex(aXMin, aYMin, imageWidth);
      int aStop = ImageTool.rtrvIndex(aXMax, aYMax, imageWidth);
      int aXPos = 0;
      int aYPos = 0;
      //System.out.println("ImageParser: aStart = "+aStart+", aStop = "+aStop);
      for(int j=aStart;j<aStop;j=j+1)
      {
         aXPos = ImageTool.rtrvXPstn(j, imageWidth);
         aYPos = ImageTool.rtrvYPstn(j, imageWidth);
         if(aDcsnBndry.isInBndry(aXPos,aYPos,0))
         {
            prsdImageFilter.filter(rgbColors,j);
         }else{
            j = j+imageWidth-(int)aDcsnBndry.cmptWidth();
         }
      }
      prsdImageFilter.finish();
      int imgData[] = prsdImageFilter.getFltrdData();

      //System.out.println("ImageParser length = "+imgData.length+", width = "+imageWidth+", height = "+imageHeight);
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