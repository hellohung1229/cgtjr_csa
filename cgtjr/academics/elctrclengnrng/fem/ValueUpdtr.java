package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;

import cgtjr.academics.math.geometry.*;

public class ValueUpdtr
{
   private double values[];

   public ValueUpdtr(double myValues[])
   {
      values = myValues;
   }
   public void update(Pnt myPoint)
   {
      int anIndex = myPoint.getCounter();
      //double aValue = Math.abs(values[anIndex]);
      //myPoint.setValue(aValue);
      myPoint.setValue(values[anIndex]);
      //System.out.println("ValueUpdtr: color = "+clrValue+",value="+aValue+",minValue="+minValue+",maxValue="+maxValue);
   }
   public void update(ShapePnt myShape)
   {
      Vector aVector = (Vector)myShape.getNodes();
      update(aVector);
   }
   public void update(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         update(aPoint);
      }
   }
}