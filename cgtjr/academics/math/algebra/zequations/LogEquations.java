package cgtjr.academics.math.algebra.zequations;

public class LogEquations extends ZEquations
{
   public final static String name = "log";
   public final static String eqtnStrng = "log(y*y+x*x)";

   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
   public static String getName()
   {
      return name;
   }
   public double computeZValues(double x,double y)
   {
      return Math.log(y*y+x*x);
   }
}