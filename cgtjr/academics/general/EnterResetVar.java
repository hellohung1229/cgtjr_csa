package cgtjr.academics.math.geometry;


public class EnterResetVar
{
   public static final String TABLENAME 		= "enterReset";
   public static final String enterResetIdKey 	= "enterResetid";
   public static final String enterResetKey 	= "enterReset";
   public static final String enterKey		= "enter";
   public static final String resetKey		= "reset";

   private String enterResetIdValue = "";
   private String enterResetValue 	= "";
   private String enterValue		= "";
   private String resetValue		= "";   

   public static String getTableName()
   {
      return TABLENAME;
   }
   public static String getEnterResetIdKey()
   {
      return enterResetIdKey;
   }
   public static String getEnterKey()
   {
      return enterKey;
   }
   public static String getResetKey()
   {
      return resetKey;
   }
   public void setEnterResetIdValue(String aEnterResetIdValue)
   {
      enterResetIdValue = aEnterResetIdValue;
   }
   public void setEnterResetValue(String aEnterResetValue)
   {
      enterResetValue = aEnterResetValue;
   }
   public void setEnterValue(String anEnterValue)
   {
      enterValue = anEnterValue;
   }
   public void setResetValue(String aResetValue)
   {
      resetValue = aResetValue;
   }
   public String getEnterValue()
   {
      return enterValue;
   }
   public String getResetValue()
   {
      return resetValue;
   }
}