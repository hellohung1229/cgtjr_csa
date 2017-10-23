package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import  java.util.*;

import cgtjr.academics.math.geometry.*;


public class PrscrbNdUpdtr
{
   private Vector prscrbBndrs;
   private NdlElmnts elmntNodes;


   public PrscrbNdUpdtr(Vector myShpBndrs,NdlElmnts myElmntNds)
   {
      prscrbBndrs = myShpBndrs;
      elmntNodes = myElmntNds;

   }
   public void setNdlElmnts(NdlElmnts myNdlElmnts)
   {
       elmntNodes = myNdlElmnts;
   }
   public void updt(ShapePnt myShape)
   {
      Vector aVector = myShape.getNodes();
      Pnt aPoint = null;
      int aSize = aVector.size();

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
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

      int aSize = prscrbBndrs.size();
      for(int i=0;i<aSize;i++)
      {
         ShpBndry aShpBndry = (ShpBndry)prscrbBndrs.elementAt(i);
         if(aShpBndry.isInBndry(c1, c2, c3) == true)
         {
            double aValue = aShpBndry.getValue();
            myPoint.setValue(aValue);
         }
      }
   }
}