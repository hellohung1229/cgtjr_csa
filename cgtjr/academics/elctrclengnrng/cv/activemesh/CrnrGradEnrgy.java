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


public class CrnrGradEnrgy extends CrnrEnrgyFltr
{
   private double minCrnrE;
   private int crnrIndex;

   public void filter3x3(int dataValues[],int i)
   {
      //super.filter(dataValues,i);
      //////grdntFilter27x27(dataValues,i);
      //////setCheckRegion(false);
      crnrGrdntDtct9x9(dataValues,i);
      
      cmptInitEnrgy(i);
      cmptCrnrs9x9(i);
   }
   public void crnrGrdntDtct9x9(int myGrayValues[],int anIndex)
   {
      int aWidthx3 = 3;//////////*getImageWidth();
      crnrGrdntDtct3x3(myGrayValues,anIndex-aWidthx3-1);
      crnrGrdntDtct3x3(myGrayValues,anIndex-aWidthx3);
      crnrGrdntDtct3x3(myGrayValues,anIndex-aWidthx3+1);
      crnrGrdntDtct3x3(myGrayValues,anIndex-1);
      crnrGrdntDtct3x3(myGrayValues,anIndex);
      crnrGrdntDtct3x3(myGrayValues,anIndex+1);
      crnrGrdntDtct3x3(myGrayValues,anIndex+aWidthx3-1);
      crnrGrdntDtct3x3(myGrayValues,anIndex+aWidthx3);
      crnrGrdntDtct3x3(myGrayValues,anIndex+aWidthx3+1);
   }
   public void crnrGrdntDtct3x3(int myGrayValues[],int anIndex)
   {
       /*
      if(!isInBounds3x3(anIndex))
      {
         return;
      }*/
      int aWidth = 1;////getImageWidth();
      crnrGrdntDtct(myGrayValues,anIndex-aWidth-1);
      crnrGrdntDtct(myGrayValues,anIndex-aWidth);
      crnrGrdntDtct(myGrayValues,anIndex-aWidth+1);
      crnrGrdntDtct(myGrayValues,anIndex-1);
      crnrGrdntDtct(myGrayValues,anIndex);
      crnrGrdntDtct(myGrayValues,anIndex+1);
      crnrGrdntDtct(myGrayValues,anIndex+aWidth-1);
      crnrGrdntDtct(myGrayValues,anIndex+aWidth);
      crnrGrdntDtct(myGrayValues,anIndex+aWidth+1);
   }
   public void crnrGrdntDtct(int myGrayValues[],int myIndex)
   {
      //////////cornerDetect(myGrayValues, myIndex);
      cmptGrdnt(myIndex);
   }

   public void cmptInitEnrgy(int anIndex)
   {
      double energy1 = cmptGrdnt(anIndex);
      double energy2 = cmptCrnr(anIndex);
      double totalEnrgy = energy1+energy2;
      setMinCrnrE(totalEnrgy);
      setCrnrIndex(anIndex);
   }
   public void updtEnrgy9x9(int anIndex)
   {
       int aWidthx3 = 3;//////////*getImageWidth();
       //System.out.println("CrnrEnrgyFltr width = "+aWidthx3);
       updtEnrgy3x3(anIndex-aWidthx3-3);
       updtEnrgy3x3(anIndex-aWidthx3);
       updtEnrgy3x3(anIndex-aWidthx3+3);
       updtEnrgy3x3(aWidthx3-3);
       updtEnrgy3x3(anIndex);
       updtEnrgy3x3(aWidthx3+3);
       updtEnrgy3x3(anIndex+aWidthx3-3);
       updtEnrgy3x3(anIndex+aWidthx3);
       updtEnrgy3x3(anIndex+aWidthx3+3);
   }
   public void updtEnrgy3x3(int anIndex)
   {
       int aWidth = 1;//////////////getImageWidth();
       double energyCrnr1 = cmptCrnr(anIndex-aWidth-1);
       int energyGrad1 = cmptGrdnt(anIndex-aWidth-1);
       updtCrnrEnergy(energyCrnr1+energyGrad1,anIndex-aWidth-1);
       double energyCrnr2 = cmptCrnr(anIndex-aWidth);
       int energyGrad2 = cmptGrdnt(anIndex-aWidth);
       updtCrnrEnergy(energyCrnr2+energyGrad2,anIndex-aWidth);
       double energyCrnr3 = cmptCrnr(anIndex-aWidth+1);
       int energyGrad3 = cmptGrdnt(anIndex-aWidth+1);
       updtCrnrEnergy(energyCrnr3+energyGrad3,anIndex-aWidth+1);
       double energyCrnr4 = cmptCrnr(anIndex-1);
       int energyGrad4 = cmptGrdnt(anIndex-1);
       updtCrnrEnergy(energyCrnr4+energyGrad4,anIndex-1);
       double energyCrnr6 = cmptCrnr(anIndex+1);
       int energyGrad6 = cmptGrdnt(anIndex+1);
       updtCrnrEnergy(energyCrnr6+energyGrad6,anIndex+1);
       double energyCrnr7 = cmptCrnr(anIndex+aWidth-1);
       int energyGrad7 = cmptGrdnt(anIndex+aWidth-1);
       updtCrnrEnergy(energyCrnr7+energyGrad7,anIndex+aWidth-1);
       double energyCrnr8 = cmptCrnr(anIndex+aWidth);
       int energyGrad8 = cmptGrdnt(anIndex+aWidth);
       updtCrnrEnergy(energyCrnr8+energyGrad8,anIndex+aWidth);
       double energyCrnr9 = cmptCrnr(anIndex+aWidth+1);
       int energyGrad9 = cmptGrdnt(anIndex+aWidth+1);
       updtCrnrEnergy(energyCrnr9+energyGrad9,anIndex+aWidth+1);
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