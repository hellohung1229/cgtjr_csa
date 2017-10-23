/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.general;

/**
 *
 * @author clayton g thomas jr
 */

import java.awt.image.*;

public class Img2DYTrns
{
   private BufferedImage sampleImage;

   private float data[][];
   private int imageWidth;
   private int imageHeight;
   private int xOffSet;
   private int yOffSet;

   public Img2DYTrns(float myData[][],int myImageWidth,int myImageHeight)
   {
      data = myData;
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
   }
   public void setXOffSet(int myX)
   {
      xOffSet = myX;
   }
   public void setYOffSet(int myY)
   {
      yOffSet = myY;
   }
   public float getData(int myX,int myY)
   {
      float aDataValue = 0;
      if(myX < 0 || myX >= imageWidth || myY < 0 || myY >= imageHeight)
      {
         aDataValue = 0;
      }else{
         int yIndex = -1*(myY-yOffSet) + imageHeight - 1;
         int xIndex = myX-xOffSet;
         aDataValue = data[yIndex][xIndex];
      }
      return aDataValue;
   }
}