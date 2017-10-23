package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import  java.util.*;

import cgtjr.academics.math.geometry.*;


public class PrscrbUpdtr
{

   private ShapePnt bndry1;
   private NdlElmnts elmntNodes;
   private double yMax;
   private double yMin;
   private double xMax;
   private double xMin;
   private double zMax;
   private double zMin;

   public PrscrbUpdtr(ShapePnt myBoundary1,NdlElmnts myElmntNds)
   {
      bndry1 = myBoundary1;
      elmntNodes = myElmntNds;
      yMax =0;
      yMin = 0;
      xMax=0;
      xMin=0;
      zMax=0;
      zMin=0;
   }
   public void setYMin(double myYMin)
   {
       yMin = myYMin;
   }
   public void setMax(double myYMax)
   {
       yMin = myYMax;
   }
   public void setCrdntShp(MeshShp myCrdntShp)
   {
      bndry1 = myCrdntShp;
   }
   public void setNdlElmnts(NdlElmnts myNdlElmnts)
   {
       elmntNodes = myNdlElmnts;
   }
   public void updt(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         updt(aPoint);
      }

   }
   /*
   if(shpBndryActn != null)
      {
          shpBndryActn.prfrmActn(isInABndry, myPoint);
      }*/
   public void updt(Pnt myPoint)
   {
      float c1 = myPoint.getX1();
      float c2 = myPoint.getY1();
      float c3 = myPoint.getZ1();

      int anIndex = myPoint.getCounter();

      elmntNodes.insrtXGlbl(c1,anIndex);
      elmntNodes.insrtYGlbl(c2,anIndex);
      elmntNodes.insrtZGlbl(c3,anIndex);
      
      if(c2 == 4)
      {
         elmntNodes.insrtPrscrbNdIndx(anIndex,100);         
      }else if(c2==0){
         elmntNodes.insrtPrscrbNdIndx(anIndex,0);
      }else{
         elmntNodes.insrtFrNdIndx(anIndex);
      }
      //elmntNodes.insrtNdLstIndx(anIndex);
      /*
      ShpBndry shpBoundary = bndry1.getBoundaryShape();
       int aCount = shpBoundary.numberOfVertices();
      for(int i=0;i<aCount;i++)
      {
         ShpBndry aShpBndry = (ShpBndry)shpBoundary.retrieveVertex(anIndex);
         if(aShpBndry.isInBndry(c1,c2,c3))
         {
            double aValue = aShpBndry.getValue();
            elmntNodes.insrtPrscrbNdIndx(anIndex,aValue);
         }
      }*/

   }
}