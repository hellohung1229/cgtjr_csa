/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.general.FileNameVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;

/**
 *
 * @author clayton g thomas jr
 */
public class SSDImgBndry extends ImageBndry
{
   private SumSqrDiff ssd[];
   private int index = 1;
   
   public SSDImgBndry(String myFileName1,String myFileName2)
   {
      super(new DmnsnVar());
      ssd = new SumSqrDiff[10];
      ssd[0] = new SumSqrDiff(myFileName1,myFileName2);
   }
   public SSDImgBndry(String myFileName1)
   {
      super(new DmnsnVar());
      ssd = new SumSqrDiff[10];
      ssd[0] = new SumSqrDiff(myFileName1);
      ssd[1] = new SumSqrDiff(myFileName1);
   }
   public SSDImgBndry(FileNameVar myDmnsnVar)
   {
      super(myDmnsnVar);
      ssd = new SumSqrDiff[10];
      String aFileName = myDmnsnVar.getFileNameVal();
      ssd[0] = new SumSqrDiff(aFileName);
   }
   public SSDImgBndry(FileNameVar myFileNameVar1,FileNameVar myFileNameVar2)
   {
      super(new DmnsnVar());
      String aFileName1 = myFileNameVar1.getFileNameVal();
      String aFileName2 = myFileNameVar2.getFileNameVal();
      ssd[0] = new SumSqrDiff(aFileName1,aFileName2);
      //myFileNameVar2.
   }
   public void setImgPxlData(int myPixelData[],int myWidth,int myHeight)
   {
      setPixelData(myPixelData,myWidth,myHeight);
   }
   public void setPixelData(int myPixelData[],int myWidth,int myHeight)
   {
     if(ssd[0] != null)
     {
        ssd[0].setPixelData(myPixelData,myWidth,myHeight);
     }
     if(ssd[1] != null){
        ssd[1].setPixelData(myPixelData,myWidth,myHeight);
     }
     if(ssd[2] != null){
        ssd[2].setPixelData(myPixelData,myWidth,myHeight);
     }if(ssd[3] != null){
        ssd[3].setPixelData(myPixelData,myWidth,myHeight);
     }
   }
   public boolean isInBndry(double r, double t, double p) 
   {
      boolean inBndry = false;
      int aX = (int)r;
      int aY = (int)t;
      //if(ssd[0].isInBounds3x3(aX,aY))
      //{
      //ssd[0].isInBounds3x3(aX,aY);
         ssd[0].filter3x3(aX,aY);
         //ssd[1].filter3x3(aX,aY);
      //}else{
       //  return inBndry;
     // }
      //System.out.println("SSDImgBndry: r = "+r+",t="+t+", p = "+p);
      if(super.isInBndry(r, t, p) && p == 0 && isInTlrnc())
      //if(r> 1 && t > 1 && r < 80 && t < 70 && p == 0)
      {
         inBndry = true;
      }
      return inBndry;
   }
   public boolean isInTlrnc(){
      boolean inTlrnc = false;
      
      if(ssd[0] != null && ssd[0].isInTlrnc() == true)
      {
         inTlrnc = true;
         return inTlrnc;
      }
      if(ssd[1] != null && ssd[1].isInTlrnc() == true)
      {
         inTlrnc = true;
         return inTlrnc;
      }
      /*
      if(ssd[2] != null && ssd[2].isInTlrnc() == false)
      {
         inTlrnc = false;
      }
      if(ssd[3] != null && ssd[3].isInTlrnc() == false)
      {
         inTlrnc = false;
      }
      if(ssd[4] != null && ssd[4].isInTlrnc() == false)
      {
         inTlrnc = false;
      }*/
      return inTlrnc;
   }
   public void insrtSSD(String myFileName)
   {
      int index = ssd.length;
      ssd[index] = new SumSqrDiff(myFileName);      
   }
   public void insrtSSD(SumSqrDiff mySumSqrDiff)
   {
      //System.out.println("SSDImgBndry : index = "+index);
      ssd[index] = mySumSqrDiff;
      ++index;
   }
}
