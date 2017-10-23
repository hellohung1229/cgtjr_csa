package cgtjr.academics.math.algebra.zequations;

public class ClndrCncvEqutns extends ZEquations
{
   public final static String name = "cylndrcncv";
   public final static String eqtnStrng = "z=a*sqrt(1-x^2/b^2)";

   public ClndrCncvEqutns()
   {
      setAmplitude(-1);
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
      float a = 4;
      float b = 4;
      float aXScale = getXScale();
      float aYScale = getYScale();
      float aXShift = getXShift();
      float aYShift = getYShift();
      float anAmplt = getAmplitude();
      x = ((double)Math.round(10000*x))/10000;
      y = ((double)Math.round(10000*y))/10000;
      double answer = -1*anAmplt*Math.sqrt(9-aXScale*(x-aXShift)*(x-aXShift));
      System.out.println("ClndrCncvEqtn: x = "+x+",y="+y+"answer = "+answer);
      return answer;
   }
}