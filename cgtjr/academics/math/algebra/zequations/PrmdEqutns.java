package cgtjr.academics.math.algebra.zequations;

public class PrmdEqutns extends ZEquations
{
   public final static String name = "cylndrcncv";
   public final static String eqtnStrng = "z=m*max(x,y)+b";

   public PrmdEqutns()
   {
      setAmplitude(-1);
   }
   public static String getName()
   {
      return name;
   }
   public float getMaxZValue()
   {
      return (float)Math.sqrt(10);
   }
   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
   public double computeZValues(double x,double y)
   {
      float m = -4/3;
      float b = 4;
      float aXScale = getXScale();
      float aYScale = getYScale();
      float aXShift = getXShift();
      float aYShift = getYShift();
      float anAmplt = getAmplitude();
      double answer = -1*anAmplt*m*Math.max(Math.abs(x-aXShift),Math.abs(y-aYShift))+b;
      return answer;
   }
}