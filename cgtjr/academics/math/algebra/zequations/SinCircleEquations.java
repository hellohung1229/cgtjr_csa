package cgtjr.academics.math.algebra.zequations;


public class SinCircleEquations extends ZEquations
{
   private final static String name = "sincircle";
   private final static String eqtnStrng = "z=sin(.5*(x*x+y*y))";
   private static double maxZValue = 1;

   public static String getName()
   {
      return name;
   }
   public double getMaxZValue()
   {
      return maxZValue;
   }
   public double computeZValues(double x,double y)
   {
      return Math.sin(.5*(x*x+y*y));
   }
   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
}