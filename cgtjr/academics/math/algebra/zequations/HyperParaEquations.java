package cgtjr.academics.math.algebra.zequations;


public class HyperParaEquations extends ZEquations
{
   public final static String name = "hyper-para";
   public final static String eqtnStrng = "z=(y^2)/(a^2)-(x^2)/(b^2)";

   public HyperParaEquations()
   {
      setXScale(.800f);
      setYScale(.800f);
   }
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
      float aXScale = getXScale();
      float aYScale = getYScale();
      float aXShift = getXShift();
      float aYShift = getYShift();
      float anAmplt = getAmplitude();
      double answer = anAmplt*(aYScale*(y-aYShift)*(y-aYShift)-aXScale*(x-aXShift)*(x-aXShift));
      return answer;
   }
}