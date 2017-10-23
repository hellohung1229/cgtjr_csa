package cgtjr.academics.elctrclengnrng.cv.sfs;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.Img1DYTrns;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import java.util.Vector;

public class ImgRdSmplr implements PntInsrtActn
{  
   private Img1DYTrns image1DYTrns;
   private int imageWidth;
   private int imageHeight;

   public ImgRdSmplr(int[] myOutputData,int myWidth,int myHeight)
   {
      imageWidth = myWidth;
      imageHeight = myHeight;
      image1DYTrns = new Img1DYTrns(myOutputData,myWidth,myHeight);
   }
   public ImgRdSmplr(int[] myOutputData,int myWidth,int myHeight,int myXOffSet,int myYOffSet)
   {
      image1DYTrns = new Img1DYTrns(myOutputData,myWidth,myHeight,myXOffSet,myYOffSet);
   }
   public void nodeCmpltAction(Pnt myPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {   
   }
   public void nodeOriginAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
   }
   public void nodeInitAction(Pnt aPnt,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
   }
   public void nodeCreateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      prcssNodes(myPnt7);
   }
   public void nodeUpdateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
   }
   public void prcssNodes(Vector myVector)
   {
       Pnt aPnt = null;
       int aSize = myVector.size();
       for(int i=0;i<aSize;i++)
       {
          aPnt = (Pnt)myVector.get(i);
          prcssNodes(aPnt);
       }       
   }
   public void prcssNodes(Pnt myPnt7)
   {
      int x = (int)myPnt7.getX1();
      int y = (int)myPnt7.getY1();
 
      int myColor = image1DYTrns.getData(x,y);
      myPnt7.setColor(0x00ccccccc);       
   }
}