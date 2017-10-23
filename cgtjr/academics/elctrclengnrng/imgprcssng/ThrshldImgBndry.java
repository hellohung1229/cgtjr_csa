/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
/**
 *
 * @author clayton g thomas jr
 */
public class ThrshldImgBndry extends ImageBndry
{
   private int pixelData[];
   private int index = 1;
   private int threshold = 255;
   private float xOffset;
   private float yOffset;
   
   public ThrshldImgBndry(int myPixelData[],int myWidth,int myHeight,int myXOffSet,int myYOffSet)
   {
      super(new DmnsnVar());
      //System.out.println("ThrshldImgBndry: width = "+myWidth+", height = "+myHeight);
      super.setImgPxlData(myPixelData, myWidth, myHeight);
      xOffset = myXOffSet;
      yOffset = myYOffSet;
            
   }
   public void setPixelData(int myPixelData[],int myWidth,int myHeight)
   {
       super.setImgPxlData(myPixelData, myWidth, myHeight);
   }
   public boolean isInBndry(double r, double t, double p) 
   {
      boolean inBndry = false;
      int aX = (int)r;
      int aY = (int)t;
      int aZ = (int)p;
      int aWidth = getImgWidth();
      int aHeight = getImgHeight();
      
      if(r < 0 || r >= aWidth || t < 0 || t >= aHeight || aZ != 0) return inBndry;
      
      //System.out.println("ThrshldImgBndry: x = "+aX+", y = "+aY);
      int myIndex = ImageTool.rtrvIndex(aX, aY, aWidth);
      
      int imageData[] = getImgPixels();
      
      int aColor = ColorCode.rtrvGrayff(imageData[myIndex]);

      if(aZ == 0 && aColor != threshold)
      {
         inBndry = true;
      }
      //System.out.println("ThrshldImgBndry: width = "+aWidth+", aX = "+aX+", aY = "+aY+", aColor = "+aColor+", isInBoundary = "+inBndry);
      return inBndry;
   }
}