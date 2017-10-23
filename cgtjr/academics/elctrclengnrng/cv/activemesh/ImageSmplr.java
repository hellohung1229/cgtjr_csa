package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;

public class ImageSmplr implements BndryNodeAction_I
{
   private Object meshPoints[];
   private int rgbColors[];
   private int imageWidth;
   private int imageHeight;
   private int indexCnt;
   
   public ImageSmplr(int myImage[],int myWidth,int myHeight)
   {
      rgbColors = myImage;
      imageWidth = myWidth;
      imageHeight = myHeight;
      int aLength = myWidth*myHeight;
      meshPoints = new Object[aLength];
   }
   public int getImageWidth()
   {
      return imageWidth;
   }
   public int getImageHeight()
   {
      return imageHeight;
   }
   public void nodeOriginAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //crtBrghtnssImage(aPnt,null,null,null);
   }
   public void nodeInitAction(Pnt aPnt,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //crtBrghtnssImage(aPnt,null,null,myPnt7);
      //crtBrghtnssImage(myPnt7,null,null,aPnt);
   }
   public void nodeCreateAction(Pnt myICPnt,Pnt myCCPnt,Pnt mySCPnt,Pnt myNCPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //crtBrghtnssImage(myICPnt,myCCPnt,mySCPnt,myNCPnt);
      meshPoints[indexCnt] = myNCPnt;
      indexCnt++;
   }
   public void nodeUpdateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //int initialY = myIndex / aWidth;
   }
   public void nodeCmpltAction(Pnt myPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //System.out.println("ImageSmplr.nodeCmpltAction");
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getImageWidth();
      return y;
   }
   public int getIndexCnt()
   {
      return indexCnt;
   }
}