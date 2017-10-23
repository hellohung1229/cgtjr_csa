package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;


public interface PntInsrtActn
{
   public void nodeOriginAction(Pnt aPoint,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeInitAction(Pnt aPoint,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeCreateAction(Pnt aPoint,Pnt myPoint4,Pnt myPoint6,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeUpdateAction(Pnt aPoint,Pnt myPoint4,Pnt myPoint6,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber);
   public void nodeCmpltAction(Pnt aPoint,int myDepthCounter,int myDepth,int myTotalNumber);
}