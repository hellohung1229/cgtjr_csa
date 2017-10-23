/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.video.SSDCornerFltr;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;

/**
 *
 * @author clayton g thomas jr
 */
public class CrrltnCrnrBndry extends ImageBndry
{
   private SSDCornerFltr crrltnFltr;
   
   public CrrltnCrnrBndry(DmnsnVar myDmnsnVar)
   {
      super(myDmnsnVar);
      crrltnFltr = new SSDCornerFltr();    
   }
   public void initialize(int myWidth,int myHeight)
   {
      crrltnFltr.initialize(myWidth, myHeight);       
   }
   public ImageFilter getFilter()
   {
      return crrltnFltr;
   }
   public void setImgPxlData(int myPixelData[],int myWidth,int myHeight)
   {
      super.setImgPxlData(myPixelData, myWidth, myHeight);
      crrltnFltr.initialize(myWidth, myHeight); 
   }
   public boolean isInBndry(double r, double t, double p) 
   {
      boolean inBndry = false;
      int aX = (int)r;
      int aY = (int)t;
      int aWidth = getImgWidth();
      int anIndex = ImageTool.rtrvIndex(aX, aY, aWidth);
      int imgPxlData[] = getImgPixels();
      crrltnFltr.filter(imgPxlData,anIndex);
      
      System.out.println("OptclFlwBndry: x = "+aX+", aY = "+aY+", getEigenValue = "+crrltnFltr.getEigenValue(anIndex)+", threshold= "+crrltnFltr.getThreshold());
      if(p == 0 && super.isInBndry(r, t, p) && (crrltnFltr.getEigenValue(anIndex) < crrltnFltr.getThreshold()))
      {
         inBndry = true;
      }
      //System.out.println("GrdntBndry: x = "+aX+", aY = "+aY+", isInBndry = "+inBndry+", gradient = "+crrltnFltr.getGrdntMgntdAvg9x9(anIndex));
      return inBndry;
   } 
}