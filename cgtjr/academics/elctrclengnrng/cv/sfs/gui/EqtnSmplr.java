package cgtjr.academics.elctrclengnrng.cv.sfs.gui;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.Img2DYTrns;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import java.util.Vector;


public class EqtnSmplr implements PntInsrtActn
{
   private Img2DYTrns image2DYTrns;
   private float zScale;
   private int aWidth;
   private int aHeight;
   private float zOffSet = 4;
   
   public EqtnSmplr(int myWidth,int myHeight)
   {
      zScale = .25f;
      aWidth = myWidth;
      aHeight = myHeight;
      System.out.println("EqtnSmplr: aWidth = "+aWidth+", aHeight= "+aHeight);
   }
   public void setXOffSet(int myX)
   {
      image2DYTrns.setXOffSet(myX);
   }
   public void setYOffSet(int myY)
   {
      image2DYTrns.setYOffSet(myY);
   }
   public void nodeCmpltAction(Pnt myPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {   
   }
   public void nodeOriginAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(aPnt);
   }
   public void nodeInitAction(Pnt aPnt,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(myPnt7);
   }
   public void nodeCreateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(myPnt7);
   }
   public void updtHght(Pnt aPnt)
   {
      //System.out.println("EqtnSmplr:z = "+aPnt.getZ1()+", -48*zScale = "+(-48*zScale));     
      if(aPnt.getZ1() <(-48*zScale))
      {
         int anIndex1 = (int)aPnt.getX1();
         int anIndex2 = (int)aPnt.getY1();
         double xValue = 80*((double)anIndex1/aWidth);
         double yValue = 80*((double)anIndex2/aHeight);
      
         float myHght = 4*((float)Math.sin(xValue)+(float)Math.cos(yValue+Math.PI/2))+zOffSet;

         aPnt.setZ1(-myHght);
         aPnt.setColor(0x00eeee00);
         //System.out.println("EqtnSmplr: x = "+anIndex1+", y = "+anIndex2+", xValue = "+xValue+", yValue="+yValue+" , z = "+ myHght);
      }
   }
   public void nodeUpdateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //System.out.println("ImgSmplr.nodeUpdateAction() : ... ");
   }
   public void updtHght(Vector myVector)
   {
       int size = myVector.size();
       for(int i=0;i<size;i++)
       {
          Pnt aPnt = (Pnt)myVector.get(i);
          updtHght(aPnt);
       }
   }
}