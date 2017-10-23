package cgtjr.academics.math.trgnmtry;

public class TrigFnctn2
{
   public static double sinh(double myValue)
   {
      double aValue = (Math.pow(Math.E,myValue)-Math.pow(Math.E,-1*myValue))/2;
      /*
      if(aValue == Double.POSITIVE_INFINITY)       
      {
         aValue = Double.MAX_VALUE;
      }else if(aValue == Double.NEGATIVE_INFINITY){
         aValue = -1*Double.MAX_VALUE;
      }
      //System.out.println("ExctSltn: sinh = "+aValue);
      */
      return aValue;
   }
   public static double cosh(double myValue)
   {
      double aValue = (Math.pow(Math.E,myValue)+Math.pow(Math.E,-1*myValue))/2;
      /*
      if(aValue == Double.POSITIVE_INFINITY)       
      {
         aValue = Double.MAX_VALUE;
      }else if(aValue == Double.NEGATIVE_INFINITY){
         aValue = -1*Double.MAX_VALUE;
      }
      //System.out.println("ExctSltn: sinh = "+aValue);
      */
      return aValue;
   }
}