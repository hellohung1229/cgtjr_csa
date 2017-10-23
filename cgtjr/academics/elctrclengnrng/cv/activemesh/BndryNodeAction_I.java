package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;


public interface BndryNodeAction_I
{
   public void nodeOriginAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeInitAction(Pnt aPnt,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeCreateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeUpdateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeCmpltAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber);
}