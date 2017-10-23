package cgtjr.academics.general;


import cgtjr.academics.general.gui.UgotImage;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.net.*;
import java.applet.*;
import java.util.ArrayList;

public class ImageTool
{
   private final String fileName;
   private final int imageHeight;
   private final int imageWidth;
   private final int imagePixels[];
   private final BufferedImage image;

   public ImageTool(String myFileName)
   {
      fileName = myFileName;
      image = rdImageFile(fileName);
      imageHeight = image.getHeight();
      imageWidth = image.getWidth();
      imagePixels = rtrv1DPxls(image);
   }
   public int[] getImagePixels()
   {
      return imagePixels;
   }
   public int getImageHeight()
   {
      return imageHeight;
   }
   public int getImageWidth()
   {
      return imageWidth;
   }
   public static int[][] rtrv2DPxls(Image myInputImage)
   {
      int imageWidth = 0;
      int imageHeight = 0;
      Image inputImage = myInputImage;
      int oneDPixels[] = null;
      int twoDPixels[][] = null;
      if(inputImage != null)
      {  
         while(imageWidth < 1 || imageHeight < 1)
         {
            imageWidth = inputImage.getWidth(null);  
            imageHeight = inputImage.getHeight(null);
         }
         oneDPixels = new int[imageWidth*imageHeight];
         twoDPixels = new int[imageWidth][imageHeight];
         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,oneDPixels);          
      }
      int oneDPixelTmp[] = rmvLeadBits(oneDPixels);
      twoDPixels = ArrayTool.retrieveTwoDArray(oneDPixelTmp,imageWidth,imageHeight);
      return twoDPixels;
   }
   public static int[] rtrv1DPxls(String myFileName,Applet myApplet)
   {
      Image inputImage = rdImageFile(myFileName,myApplet);
      int pixels[] = rtrv1DPxls(inputImage);
      return pixels;
   }
   public static String getImageBaseDirPath()
   {       
      String value = null;      
      try{
         value = System.getProperty("user.dir");
         //System.out.println("Themeset property = "+value);         
      }catch(Exception RE){
      }
      if(value != null  )
      {
         //value = "res/";
          value = "file:///"+value+"/build/classes/res/";          
      }else{
         //value = "/";
         value = ThemeSet.getDocumentBase();
      }
      String baseImageDirPath = value;
      System.out.println("ThemeSet : baseImageDirPath = "+baseImageDirPath);

      return baseImageDirPath;
   }   
   public static int[] rtrv1DPxls(String myFileName)
   {
      Image inputImage = null;
      int oneDPixels[] = null;
      int imageWidth;
      int imageHeight;
      
      //URL filename = new Object().getClass().getClassLoader().getResource(myFileName);
      //System.out.println("ImageTool : URL = "+filename.toString());
      String value = null;      
      try{
         value = System.getProperty("user.dir");   
      }catch(Exception RE){
      }
      if(value != null  )
      {
         myFileName = "file:"+myFileName;
      }else{
         myFileName = ThemeSet.getDocumentBase()+myFileName;
         System.out.println("ImageTool : filename = "+myFileName);
      }
      String baseImageDirPath = value;
      /*
      if(myFileName != null && myFileName.startsWith("file:"))
      {
         myFileName = myFileName;
      }else {
         myFileName = "file:"+myFileName;
         //myFileName = "http:"+myFileName;          
      }*/
      inputImage = UgotImage.createImage(myFileName);
      if(inputImage == null)
      {  
         System.out.println("ImageTool : file read error!");
         return null;
      }
      imageWidth = inputImage.getWidth(null);
      imageHeight = inputImage.getHeight(null);
      while(imageWidth < 1 || imageHeight < 1)
      {
         imageWidth = inputImage.getWidth(null);  
         imageHeight = inputImage.getHeight(null);
      }
      oneDPixels = new int[imageWidth*imageHeight];
      UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,oneDPixels); 
      return oneDPixels;
   }
   public static int[] rtrv1DPxls(Image myInputImage)
   {
      int imageWidth = 0;
      int imageHeight = 0;
      Image inputImage = myInputImage;
      int oneDPixels[] = null;

      if(inputImage != null)
      {  
         while(imageWidth < 1 || imageHeight < 1)
         {
            imageWidth = inputImage.getWidth(null);  
            imageHeight = inputImage.getHeight(null);
         }
         oneDPixels = new int[imageWidth*imageHeight];
         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,oneDPixels);          
      }      
      return oneDPixels;
   }
   public static int[] rtrv1DPxls(Image myInputImage,int pixelData[])
   {
      int imageWidth = 0;
      int imageHeight = 0;
      Image inputImage = myInputImage;

      if(inputImage != null)
      {  
         while(imageWidth < 1 || imageHeight < 1)
         {
            imageWidth = inputImage.getWidth(null);  
            imageHeight = inputImage.getHeight(null);
         }

         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,pixelData);          
      }
      
      return pixelData;
   }
   public static BufferedImage rtrvImage(int myPixelData[][])
   {
      int imageHeight = myPixelData.length;
      int imageWidth = myPixelData[0].length;
      int oneDArray[] = ArrayTool.retrieveOneDArray(myPixelData);
      BufferedImage outputImage = (BufferedImage)UgotImage.createRGBImage(oneDArray,imageWidth,imageHeight,true);
      return outputImage;
   }
   public static BufferedImage rtrvImage(int myPixelData[],int myWidth,int myHeight)
   {
      int imageHeight = myHeight;
      int imageWidth = myWidth;
      BufferedImage outputImage = (BufferedImage)UgotImage.createRGBImage(myPixelData,imageWidth,imageHeight,true);
      return outputImage;
   }   
   public static int[] rmvLeadBits(int myData[])
   {
      int myLength = myData.length;
      int aData[] = new int[myLength];
      for(int i=0;i<myLength;i++)
      {
         aData[i] = myData[i] & 0x00ffffff;
         //System.out.println("Filter.rmvLeadBits befoer:"+myData[i]+" , after:"+aData[i]);
      }
      return aData;
   }
   public static int[] rmvLeadBits(double myData[])
   {
      int myLength = myData.length;
      int aData[] = new int[myLength];
      for(int i=0;i<myLength;i++)
      {
         aData[i] = ((int)myData[i]) & 0x00ffffff;
         //System.out.println("Filter.rmvLeadBits befoer:"+myData[i]+" , after:"+aData[i]);
      }
      return aData;
   }
   public static void wrtImageFile(int myData[],int myWidth,int myHeight)
   {
      wrtImageFile(myData,myWidth,myHeight,"newimage.jpg");
   }
   public static void wrtImageFile(int myData[],int myWidth,int myHeight,String myFileName)
   {
      BufferedImage outputImage = (BufferedImage)UgotImage.createRGBImage(myData,myWidth,myHeight,true);
      try {
         File file = new File("newimage.jpg"); 
         ImageIO.write(outputImage, "jpg", file); 
      }catch (IOException e) { }
   }
   public static BufferedImage wrtImageFile(String myFileName,int myPixelData[][])
   {
      int imageWidth = myPixelData[0].length;
      int imageHeight = myPixelData.length;
      int myPixelData1D[] = ArrayTool.retrieveOneDArray(myPixelData);
      return wrtImageFile(myFileName,myPixelData1D,imageWidth,imageHeight);
   }
   public static BufferedImage wrtImageFile(String myFileName,int  myPixelData[],int myWidth,int myHeight)
   {
      BufferedImage outputImage = (BufferedImage)UgotImage.createRGBImage(myPixelData,myWidth,myHeight,true);
      wrtImageFile(myFileName,outputImage);
      return outputImage;
   }   
   public static void wrtImageFile(String myFileName,BufferedImage myImage)
   {
          try {
             File file = new File(myFileName); 
             ImageIO.write(myImage, "jpg", file); 
          }catch (IOException e) { } 
   }
   public static void wrtDataFile(String myFilename,String myData)
   {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(myFilename));
         out.write(myData);
         out.close();
      }catch (IOException e) {
      }
   } 
   public static Image rdImageFile(String myFileName,Applet myApplet)
   {
      Image inputImage = null;
      URL base = null; 
      MediaTracker mt = new MediaTracker(myApplet);
      try { 
         base = myApplet.getDocumentBase(); 
      }catch (Exception e) {} 
         inputImage = myApplet.getImage(base,myFileName); 
         mt.addImage(inputImage,0);
      try { 
         mt.waitForAll(); 
      }catch (InterruptedException e) {} 
      return inputImage;
   }
   public static BufferedImage rdImageFile(String fileName)
   {
      System.out.println("filename = "+fileName);
      BufferedImage aBufferedImage = null;
      try {
         File file = new File(fileName); 
         
         aBufferedImage = ImageIO.read(file); 
      }catch (IOException e) {
         System.err.println("ImageTool.rdImageFile(): "+e.getMessage());
      }
      return aBufferedImage;
   }
   public static BufferedImage rdImageURL(String fileName)
   {
      File file = null;
      URL aURL = null;
      BufferedImage aBufferedImage = null;

         try{
            aURL = new URL(fileName);
            aBufferedImage = ImageIO.read(aURL); 
         }catch(Exception e){
            System.out.println("Filter.readImageFile(): ... "+e.getMessage());
         }

      return aBufferedImage;
   }
   public static int[][] getTwoDImage(String fileName)
   {
      int rgbColors[] = null;
      int imageWidth = 0;
      int imageHeight = 0;
      int transparency = 0;
      int red = 0;
      int green = 0;
      int blue = 0;
      int imageLength = 0;
      int xCoordinate = 0;
      int yCoordinate = 0;
      int zCoordinate = 0;
      int imageHeightTmp = 0;
      
      Image inputImage = UgotImage.createImage(fileName);
      if(inputImage != null)
      {
         while(imageWidth < 1 || imageHeight < 1)
         {
            imageWidth = inputImage.getWidth(null);  
            imageHeight = inputImage.getHeight(null);
         }
         rgbColors = new int[imageWidth*imageHeight];
         imageLength = imageWidth * imageHeight;
         UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,rgbColors);                   
      }
      return getTwoDImage(rgbColors,imageWidth,imageHeight);
   }
   public static int[][] getTwoDImage(int aOneDImage[],int anImageWidth,int anImageHeight)
   {  
      int widthIndex  = 0;
      int heightIndex = 0;
      int myImageLength = anImageWidth * anImageHeight;
      int myTwoDImage[][] = new int[anImageHeight][anImageWidth];      

      for(int lengthIndex=0;lengthIndex<myImageLength;lengthIndex++)
      {                            
         myTwoDImage[heightIndex][widthIndex] = aOneDImage[lengthIndex];
         widthIndex++;
         heightIndex = lengthIndex / anImageWidth;
         if( (widthIndex % anImageWidth) == 0)
         {
            widthIndex = 0;
         }
      }
      return myTwoDImage;
   }
   public static ArrayList getImgArryLst(String myFiles[],Applet myApplet)
   {
      ArrayList imgArryLst = new ArrayList(); 
      int aLength = myFiles.length;
      for(int i=0;i<aLength;i++)
      {
         Image aBffrdImg = ImageTool.rdImageFile(myFiles[i], myApplet);          
         imgArryLst.add(aBffrdImg);             
      }
      return imgArryLst;
   }   
   public void updtImgPixel(int myX,int myY,int myColor)
   {
      int myIndex = rtrvImgIndx(myX,myY);
      updtImgPixel(myIndex,myColor,imagePixels);
   }
   public static void updtImgPixel(int myIndex,int myColor,int myPixels[])
   {
      myPixels[myIndex] = myColor;
   }
   public int rtrvImgIndx(int myX,int myY)
   {
      int aWidth = getImageWidth();
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
   public static int rtrvPixel(int myData[],int myWidth,int myLength,int myX,int myY)
   {
       int anIndex = rtrvIndex(myX,myY,myWidth);
       int data = myData[anIndex];
       return data;
   }
   public static int rtrvIndex(int myX,int myY,int myWidth)
   {
      int aWidth = myWidth;
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
   public static int rtrvIndex(int myX,int myY,int myWidth,int myHeight)
   {
      
      int aWidth = myWidth;
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }   
   public static int rtrvXPstn(int myIndex,int myImageWidth)
   {
      int x = myIndex % myImageWidth;
      return x;
   }
   public static int rtrvYPstn(int myIndex,int myImageWidth)
   {
      
      int y = myIndex / myImageWidth;
      return y;
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getImageWidth();
      return y;
   }
}