/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.activemesh;
/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.imgprcssng.*;


public class CrnrEnrgyFltr extends GrdntEnrgyFltr
{
   private double minCrnrE;
   private int crnrIndex;

   public void filter3x3(int dataValues[],int i)
   {
      //super.filter(dataValues,i);
      ///////grdntFilter27x27(dataValues,i);
      ///////setCheckRegion(false);
      ///////cornerDetect9x9(dataValues,i);//double check this function
      
      cmptCrnrsIntl(i);
      cmptCrnrs9x9(i);
   }
   /*
   public void filter9x9(int dataValues[],int i)
   {
      //super.filter(dataValues,i);
      crnrFilter9x9(dataValues,i);
      cmptCrnrsIntl(i);
      cmptCrnrs9x9(i);
   }*/
   public void cmptCrnrsIntl(int anIndex)
   {
      double energyCrv5 = cmptCrnr(anIndex);
      setMinCrnrE(energyCrv5);
      setCrnrIndex(anIndex);
   }
   public void cmptCrnrs9x9(int anIndex)
   {
       int aWidthx3 = 3;//////*getImageWidth();
       //System.out.println("CrnrEnrgyFltr width = "+aWidthx3);
       cmptCrnrs3x3(anIndex-aWidthx3-3);
       cmptCrnrs3x3(anIndex-aWidthx3);
       cmptCrnrs3x3(anIndex-aWidthx3+3);
       cmptCrnrs3x3(aWidthx3-3);
       cmptCrnrs3x3(anIndex);
       cmptCrnrs3x3(aWidthx3+3);
       cmptCrnrs3x3(anIndex+aWidthx3-3);
       cmptCrnrs3x3(anIndex+aWidthx3);
       cmptCrnrs3x3(anIndex+aWidthx3+3);
   }
   public void cmptCrnrs3x3(int anIndex)
   {
       int aWidth = 1;///////getImageWidth();
       //System.out.println("GradientFilter: aWidth = "+aWidth);
       //int anIndex = cmptImageIndex(myCntrIndex);
       double energyCrv1 = cmptCrnr(anIndex-aWidth-1);
       updtCrnrEnergy(energyCrv1,anIndex-aWidth-1);
       double energyCrv2 = cmptCrnr(anIndex-aWidth);
       updtCrnrEnergy(energyCrv2,anIndex-aWidth);
       double energyCrv3 = cmptCrnr(anIndex-aWidth+1);
       updtCrnrEnergy(energyCrv3,anIndex-aWidth+1);
       double energyCrv4 = cmptCrnr(anIndex-1);
       updtCrnrEnergy(energyCrv4,anIndex-1);
       //int energyCrv5 = cmptCrnr(anIndex);
       //updtCrnrEnergy(energyCrv5,anIndex);
       double energyCrv6 = cmptCrnr(anIndex+1);
       updtCrnrEnergy(energyCrv6,anIndex+1);
       double energyCrv7 = cmptCrnr(anIndex+aWidth-1);
       updtCrnrEnergy(energyCrv7,anIndex+aWidth-1);
       double energyCrv8 = cmptCrnr(anIndex+aWidth);
       updtCrnrEnergy(energyCrv8,anIndex+aWidth);
       double energyCrv9 = cmptCrnr(anIndex+aWidth+1);
       updtCrnrEnergy(energyCrv9,anIndex+aWidth+1);
       //System.out.println("CrnrEngryFltr: test 2");
   }
   public double cmptCrnr(int myIndex)
   {
      if(false)///////////!isInBounds3x3(myIndex))
      {
         return 1;
      }else{
         double aValue = -1;/////////////*getEigenValue(myIndex);
         int grad[] = null;///////////getGrdnt();
         //System.out.println("CrnrEngyFltr.cmptCrnr: eigen = "+aValue+", grad = "+grad[myIndex]);
         return aValue;
      }
   }
   public void updtCrnrEnergy(double myEnergy,int myIndex)
   {
      //System.out.println("CrnrEnrgyFltr: energy = "+myEnergy+", min energy= "+minCrnrE+", index = "+myIndex+", min index = "+crnrIndex);
      if(myEnergy < minCrnrE)
      {
         minCrnrE = myEnergy;
         crnrIndex = myIndex;
      }
      //System.out.println("CrnrEnrgyFltr: index = "+myIndex+", new index= "+crnrIndex);
   }
   public void setMinCrnrE(double myMinCrnrE)
   {
      minCrnrE = myMinCrnrE;
   }
   public void setCrnrIndex(int myCrnrIndex)
   {
      crnrIndex = myCrnrIndex;
   }
   public int getCrnrIndex()
   {
      //System.out.println("CrnrEnrgyFltr: crnrIndex = "+crnrIndex);
      return crnrIndex;
   }
}