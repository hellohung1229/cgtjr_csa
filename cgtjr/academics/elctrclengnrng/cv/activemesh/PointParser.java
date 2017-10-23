package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PntParser;
import cgtjr.academics.general.gui.*;
import java.net.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

public class PointParser// implements PntParser
{
   public static final String type = "ImageParser";

   private Image inputImage;
   private Image outputImage;

   private int imageData1[];
   private int imageData2[];
   private int imageLength;

   private int imageWidth = 0;
   private int imageHeight = 0;
  
   private ActiveMesh meshPoint;

   private static boolean isInitialized = false;
   private BoxBndry aBoxBndry;
   
   public PointParser()
   {
      //352x288
      meshPoint = new ActiveMesh(340,240,0);
      //meshPoint = new ActiveMesh(160,110,0);
   }

   public PointParser(String fileName,ActiveMesh myPoint)
   {
      meshPoint = myPoint;
      inputImage = UgotImage.createImage(fileName);
   }
   public PointParser(Image myInputImage,ActiveMesh myPoint)
   {
      meshPoint = myPoint;
      inputImage = myInputImage;
   }
   public PointParser(ActiveMesh myPoint)
   {
      meshPoint = myPoint;
   }
   public void setMeshPoint(ActiveMesh myMeshPoint)
   {
      meshPoint = myMeshPoint;
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
   public void bgnFrames()
   {
      /*
      if(isInitialized == true)
      {
         return;
      }
      isInitialized = true;
      */
      if(inputImage != null)
      {
         imageWidth = inputImage.getWidth(null);
         imageHeight = inputImage.getHeight(null);
         while(imageWidth <= 1 || imageHeight <=1)
         {
            imageWidth = inputImage.getWidth(null);
            imageHeight = inputImage.getHeight(null);
         }
         imageData1 = new int[imageWidth*imageHeight];
         imageData2 = new int[imageWidth*imageHeight];
         imageLength = imageWidth * imageHeight;
      }
      //ImageSmplr anImageSmplr = new ImageSmplr(imageData1,imageWidth,imageHeight);

      //meshPoint.setBoundaryShape(aBoxBndry);
      //meshPoint.createInitCoordinates();
      //meshPoint.createCoordinateMesh();
   }
   public void strtPrsng()
   {
   }
   public void fnshPrsng()
   {
   }
   public void endFrames()
   {
      meshPoint.setCntrlNmbr(meshPoint.getCntrlNmbr()+1);
   }
   public void parse()
   {
      UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,imageData1);
      UgotImage.handlepixels(inputImage,0,0,imageWidth,imageHeight,imageData2);
      meshPoint.setImageData(imageData1,imageWidth,imageHeight);
      meshPoint.setImgDtCpy(imageData2,imageWidth,imageHeight);
      //meshPoint.setBoundaryShape(imageWidth,imageHeight,0);
      //meshPoint.setNodeTrvrslActn(ptImageTrvsl);
      //meshPoint.strtNodeTrvrsl();
      //meshPoint.trvrsEdges();
      //////////////////////////////meshPoint.trvrsNodes();
      //meshPoint.startVideo
      //meshPoint.startFrame(imageHeight,imageWidth);
      //for(int j=0;j<imageLength;j++)
      //{
         //meshPoint.filter(imageData1,j);
      //}
      //meshPoint.finishFrame();
      //meshPoint.finishVideo();
      int imgData[] = meshPoint.getFltrdData();

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