package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;

import java.util.*;


public class PntSampler implements PntInsrtActn
{
   Vector nodeActnVector;

   public PntSampler()
   {
      nodeActnVector = new Vector();
   }
   public void nodeOriginAction(Pnt aPoint,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      PntInsrtActn aPntInsrtActn = null;
      int aSize = nodeActnVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPntInsrtActn = (PntInsrtActn)nodeActnVector.elementAt(i);
         aPntInsrtActn.nodeOriginAction(aPoint,myDepthCounter,myDepth,myTotalNumber);
      }
      //System.out.println("MeshSmplr.nodeOriginAction() : ... ");
      //checkBoundaries(aPoint,aPoint,aPoint,aPoint,myDepthCounter,myDepth,myTotalNumber);
   }
   public void nodeInitAction(Pnt aPoint,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //System.out.println("MeshSmplr.nodeInitAction() : ... ");
      //checkBoundaries(aPoint,myPoint7,myPoint7,myPoint7,myDepthCounter,myDepth,myTotalNumber);
      PntInsrtActn aPntInsrtActn = null;
      int aSize = nodeActnVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPntInsrtActn = (PntInsrtActn)nodeActnVector.elementAt(i);
         aPntInsrtActn.nodeInitAction(aPoint,myPoint7,myDepthCounter,myDepth,myTotalNumber);
      }
   }
   public void nodeCreateAction(Pnt aPoint,Pnt myPoint4,Pnt myPoint6,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //System.out.println("MeshSmplr.nodeCreateAction() : ... ");
      //checkBoundaries(aPoint,myPoint4,myPoint6,myPoint7,myDepthCounter,myDepth,myTotalNumber);  
      PntInsrtActn aPntInsrtActn = null;
      int aSize = nodeActnVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPntInsrtActn = (PntInsrtActn)nodeActnVector.elementAt(i);
         aPntInsrtActn.nodeCreateAction(aPoint,myPoint4,myPoint6,myPoint7,myDepthCounter,myDepth,myTotalNumber);
      }
   }
   public void nodeUpdateAction(Pnt aPoint,Pnt myPoint4,Pnt myPoint6,Pnt myPoint7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      PntInsrtActn aPntInsrtActn = null;
      int aSize = nodeActnVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPntInsrtActn = (PntInsrtActn)nodeActnVector.elementAt(i);
         aPntInsrtActn.nodeUpdateAction(aPoint,myPoint4,myPoint6,myPoint7,myDepthCounter,myDepth,myTotalNumber);
      }
      //System.out.println("MeshSmplr.nodeUpdateAction() : ... ");
   }
   public void nodeCmpltAction(Pnt myPoint,int myDepthCounter,int myDepth,int myTotalNumber)
   {   
      PntInsrtActn aPntInsrtActn = null;
      int aSize = nodeActnVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPntInsrtActn = (PntInsrtActn)nodeActnVector.elementAt(i);
         aPntInsrtActn.nodeCmpltAction(myPoint,myDepthCounter,myDepth,myTotalNumber);
      }
   }
   public void insrtSampler(PntInsrtActn myPntInsrtActn)
   {
      nodeActnVector.add(myPntInsrtActn);
   }
}