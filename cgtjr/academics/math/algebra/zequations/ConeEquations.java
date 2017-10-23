package cgtjr.academics.math.algebra.zequations;

public class ConeEquations extends ZEquations
{
   public final static String name = "cone";
   public final static String eqtnStrng = "z=sqrt(x*x+y*y)";

   public static String getName()
   {
      return name;
   }
   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
   public double computeZValues(double x,double y)
   {
      return Math.sqrt(x*x+y*y);
   }
}