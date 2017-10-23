package cgtjr.academics.chmstry.general.units;

public class Cnvrsn
{
   public Cnvrsn(){}

   public static float getFloatValue(String aStrValue)
   {
      float floatValue = 0f;
      if(aStrValue.trim().equals(""))
      {
         aStrValue = "0";
      }
      Float aFloat = new Float(aStrValue);
      floatValue = aFloat.floatValue();
      return floatValue;
   }
   public static int getIntValue(String aStrValue)
   {
      int intValue = 0;
      if(aStrValue.trim().equals(""))
      {
         aStrValue = "0";
      }
      Integer anInteger = new Integer(aStrValue);
      intValue = anInteger.intValue();
      return intValue;
   }
   public static float statCoulombToCoulombMeter(float aStatCoulomb)
   {
      float aValue = (float)Math.pow(10,-11)*aStatCoulomb/(2.9979f);
      return aValue;
   }
   public static float debyeToStatCoulomb(float aDebye)
   {
      float aValue = (float)Math.pow(10,-18)*aDebye;
      return aValue;
   }
   public static float angstromToMeter(float anAngstrom)
   {
      float aValue = (float)Math.pow(10,-10)*anAngstrom;
      return aValue;
   }
   
}