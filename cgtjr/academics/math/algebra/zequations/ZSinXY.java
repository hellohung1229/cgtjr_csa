package cgtjr.academics.math.algebra.zequations;

import cgtjr.academics.math.algebra.zequations.ZEquations2;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.Vector;


public class ZSinXY extends ZEquations2
{
   private final static String name = "sincircle";
   private final static String eqtnStrng = "z=sin(.5*(x*x+y*y))";
   private static double maxZValue = 1;
   private float xShift = 10;
   private float yShift = 10;   
   public ZSinXY(){}
   public static String getName()
   {
      return name;
   }
   public double getMaxZValue()
   {
      return maxZValue;
   }
   public double cmptZVls(double x,double y)
   {
      return 2*Math.sin(.25*((x)*(x)+y*y));
      //return Math.sqrt(Math.abs(x)+Math.abs(y));       
   }
   public double updtZVls(Pnt aPoint)
   {
      double aX = aPoint.getX1();
      double aY = aPoint.getY1();
      double anAnswer = cmptZVls(aX,aY);
      aPoint.setZ1((float)anAnswer);
      return anAnswer;
   }   
   public void updtZVls(Vector aVector)
   {
      Pnt aPoint = null;
      int aSize = aVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.get(i);
         updtZVls(aPoint);
      }
   }
   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
}