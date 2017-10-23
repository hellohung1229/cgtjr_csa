/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.general;

/**
 *
 * @author clayton g thomas jr
 */

import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import java.awt.image.BufferedImage;

public class Img1DYTrns
{
   private BufferedImage sampleImage;

   private int data[];
   private int imageWidth;
   private int imageHeight;
   private int xOffSet;
   private int yOffSet;

   public Img1DYTrns(int myData[],int myImageWidth,int myImageHeight)
   {
      data = myData;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
   }
   public Img1DYTrns(int myData[],int myImageWidth,int myImageHeight,int myXOffSet,int myYOffSet)
   {
      data = myData;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      xOffSet = myXOffSet;
      yOffSet = myYOffSet;
   }
   public void setXOffSet(int myX)
   {
      xOffSet = myX;
   }
   public void setYOffSet(int myY)
   {
      yOffSet = myY;
   }
   public int getData(int myX,int myY)
   {
      int aDataValue = 0;
      if(myX < 0 || myX >= imageWidth || myY < 0 || myY >= imageHeight)
      {
         aDataValue = 0;
      }else{
         int yIndex = -1*(myY) + imageHeight-1;
         int xIndex = myX;
         //System.out.println("Img1DYTrns: x = "+myX+",y = "+myY+", xIndex = "+xIndex+", yIndex="+yIndex);
         int anIndex = getIndex(xIndex,yIndex);
         aDataValue = ColorCode.convertRGBToGray(data[anIndex]);
         //aDataValue = data[anIndex];
      }
      return aDataValue;
   }
   public int getData8(int myX,int myY)
   {
      int aDataValue = 0;
      if(myX < 0 || myX >= imageWidth || myY < 0 || myY >= imageHeight)
      {
         aDataValue = 0;
      }else{
         int yIndex = -1*(myY) + imageHeight-1;
         int xIndex = myX;

         int anIndex = getIndex(xIndex,yIndex);
         aDataValue = ColorCode.convertRGBToGray(data[anIndex]);
         //System.out.println("Img1DYTrns.getData8(): value = "+aDataValue+", x = "+myX+",y = "+myY+", xIndex = "+xIndex+", yIndex="+yIndex);
         //aDataValue = data[anIndex];
      }
      return aDataValue;
   }
   public int getIndex(int myX,int myY)
   {
      int aWidth = imageWidth;
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
}