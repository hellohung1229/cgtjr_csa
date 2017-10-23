package cgtjr.academics.math.trgnmtry;

/**
 * A class for computing sinh and cosh functions
 * @author clayton g thomas jr
 */
public class TrigFnctn
{
   /**
    * computes the sinh of the angle
    * @param myValue - the value of the angle in radians
    * @return the the sinh value
    */
   public static double sinh(double myValue)
   {
      double aValue = (Math.pow(Math.E,myValue)-Math.pow(Math.E,-1*myValue))/2;
      return aValue;
   }
   /**
    * computes the cosh of the angle
    * @param myValue - the value of the angle in radians
    * @return the cosh value
    */
   public static double cosh(double myValue)
   {
      double aValue = (Math.pow(Math.E,myValue)+Math.pow(Math.E,-1*myValue))/2;
      return aValue;
   }
}