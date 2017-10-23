/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.video.OpticalFlowFltr;
import cgtjr.academics.general.FileNameVar;

/**
 *
 * @author clayton g thomas jr
 */
public class ImageDiffBndry extends ImageBndry
{
   private OpticalFlowFltr optclFlwFltr;
   

   public ImageDiffBndry(FileNameVar myDmnsnVar)
   {
      super(myDmnsnVar);
      optclFlwFltr = new OpticalFlowFltr();
      
   }
   public void initialize(int myWidth,int myHeight)
   {
      optclFlwFltr.initialize(myWidth, myHeight);       
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
      optclFlwFltr.bckGrndFrmFltr(imgPxlData,anIndex);
      
      float average = optclFlwFltr.getFrm1TmprGrdntDiff();
      if(p == 0 && super.isInBndry(r, t, p) && (Math.abs(average) > 20))
      {
         inBndry = true;
      }
      System.out.println("ImageDiffBndry: x = "+aX+", aY = "+aY+", isInBndry = "+inBndry+", diff = "+average);
      return inBndry;
   }
 
}
