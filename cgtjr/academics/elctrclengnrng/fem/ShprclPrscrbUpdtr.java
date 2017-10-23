package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import  java.util.*;

import cgtjr.academics.math.geometry.*;


public class ShprclPrscrbUpdtr
{

   private ShapePnt bndry1;
   private NdlElmnts elmntNodes;

   public ShprclPrscrbUpdtr(NdlElmnts myElmntNds,ShapePnt myBoundary1)
   {
      bndry1 = myBoundary1;
      elmntNodes = myElmntNds;
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