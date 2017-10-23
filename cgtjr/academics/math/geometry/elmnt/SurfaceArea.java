/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.elmnt;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.util.Vector;

/**
 *
 * @author Nit
 */
public class SurfaceArea 
{
   public static double cmptAreaByCrssPrdct(Vector myVector)
   {
      QuadPnts aQuadPnts = null;
      
      double aTotal = 0;
      int aSize = myVector.size();
      //System.out.println("SurfaceArea: count = "+aSize);
      for(int i=0;i<aSize;i++)
      {         
         aQuadPnts  = (QuadPnts)myVector.get(i);
         aTotal += cmptAreaByCrssPrdct(aQuadPnts);                 
      }
      return aTotal;
   }
   public static double cmptAreaByCrssPrdct(QuadPnts myQuadPnts)
   {
      Pnt aPoint0 = myQuadPnts.getPoint0();
      Pnt aPoint1 = myQuadPnts.getPoint1();
      Pnt aPoint2 = myQuadPnts.getPoint2();      
      Pnt aPoint3 = myQuadPnts.getPoint3();      
      double area = cmptAreaByCrssPrdct(aPoint1, aPoint2, aPoint3, aPoint3);
      
      //System.out.println("SurfaceArea : area = "+area);
      return area;
   }   
   public static double cmptAreaByCrssPrdct(Pnt aPoint1,Pnt aPoint2,Pnt aPoint3,Pnt aPoint4)
   {
      //System.out.println("SurfaceArea: "+aPoint1+","+aPoint2+","+aPoint3+","+aPoint4);
      float aFloatVle[] = MathVector.crssPrdctFlt(aPoint1, aPoint2, aPoint1, aPoint4);
      float aMgntde = MathVector.getMagnitude(aFloatVle);
      return aMgntde;
   }
}