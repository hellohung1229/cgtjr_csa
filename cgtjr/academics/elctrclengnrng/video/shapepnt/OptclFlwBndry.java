/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.video.OpticalFlowFltr;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;

/**
 *
 * @author clayton g thomas jr
 */
public class OptclFlwBndry extends ImageBndry
{
   private OpticalFlowFltr optclFlwFltr;
   
   public OptclFlwBndry(DmnsnVar myDmnsnVar)
   {
      super(myDmnsnVar);
      optclFlwFltr = new OpticalFlowFltr();      
   }
   public void initialize(int myWidth,int myHeight)
   {
      optclFlwFltr.initialize(myWidth, myHeight);       
   }
   public ImageFilter getFilter()
   {
      return optclFlwFltr;
   }
   public void setImgPxlData(int myPixelData[],int myWidth,int myHeight)
   {
      super.setImgPxlData(myPixelData, myWidth, myHeight);
      optclFlwFltr.initialize(myWidth, myHeight); 
   }
   public boolean isInBndry(double r, double t, double p) 
   {
      boolean inBndry = false;
      int aX = (int)r;
      int aY = (int)t;
      int aWidth = getImgWidth();
      int anIndex = ImageTool.rtrvIndex(aX, aY, aWidth);
      int imgPxlData[] = getImgPixels();
      optclFlwFltr.filter(imgPxlData,anIndex);
      
      //System.out.println("OptclFlwBndry: getEigenValue = "+optclFlwFltr.getEigenValue(anIndex)+", threshold= "+optclFlwFltr.getThreshold());
      if(p == 0 && super.isInBndry(r, t, p) && (optclFlwFltr.getEigenValue(anIndex) < optclFlwFltr.getThreshold()))
      {
         inBndry = true;
      }
      //System.out.println("GrdntBndry: x = "+aX+", aY = "+aY+", isInBndry = "+inBndry+", gradient = "+optclFlwFltr.getGrdntMgntdAvg9x9(anIndex));
      return inBndry;
   } 
}