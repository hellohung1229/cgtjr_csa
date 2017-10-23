package cgtjr.academics.elctrclengnrng.cv.sfs;


public class SnthtcPnlVar
{
   public static final String TABLENAME 		= "snthtcimg";
   public static final String snthtcimgIdKey 	= "snthtcimgid";
   public static final String equationKey 	= "equation";
   public static final String imageWidthKey     = "imagewidth";
   public static final String imageHeightKey    = "imageheight";
   public static final String xIntensityKey	= "xintensity";//non-profit,sole prop, etc.
   public static final String yIntensityKey	= "yintensity";
   public static final String zIntensityKey	= "zintensity";
   public static final String descriptionKey	= "description";
   public static final String resetKey 		= "reset";//active,inactive,dormant,default   
   public static final String enterKey 		= "enter";//active,inactive,dormant,default   

   private String snthtcImgIdValue 		= "";
   private String equationValue 		= "";
   private String imageWidthValue    	= "";
   private String imageHeightValue    	= "";
   private String xIntensityValue		= "";
   private String yIntensityValue		= "";   
   private String zIntensityValue		= "";  
   private String descriptionValue		= "";
   private String resetValue 			= "";
   private String enterValue 			= "";


   public static String getTableName()
   {
      return TABLENAME;
   }
   public static String getSnthtcimgIdKey()
   {
      return snthtcimgIdKey;
   }
   public static String getImageWidthKey()
   {
      return imageWidthKey;
   }
   public static String getImageHeightKey()
   {
      return imageHeightKey;
   }
   public static String getXIntensityKey()
   {
      return xIntensityKey;
   }
   public static String getYIntensityKey()
   {
      return yIntensityKey;
   }
   public static String getZIntensityKey()
   {
      return zIntensityKey;
   }
   public static String getDescriptionKey()
   {
      return descriptionKey;
   }
   public static String getResetKey()
   {
      return resetKey;
   }
   public static String getEnterKey ()
   {
      return enterKey;
   }
   public void setSnthtcImgIdValue(String aSnthtcImgIdValue)
   {
      snthtcImgIdValue = aSnthtcImgIdValue;
   }
   public void setEquationValue(String aEquationValue)
   {
      equationValue = aEquationValue;
   }
   public void setImageWidthValue(String anImageWidthValue)
   {
      imageWidthValue = anImageWidthValue;
   }
   public void setImageHeightValue(String anImageHeightValue)
   {
      imageHeightValue= anImageHeightValue;
   }
   public void setXIntensityValue(String anXIntensityValue)
   {
      xIntensityValue = anXIntensityValue;
   }
   public void setYIntensityValue(String aYIntensityValue)
   {
      yIntensityValue = aYIntensityValue;
   }
   public void setZIntensityValue(String aZIntensityValue)
   {
      zIntensityValue = aZIntensityValue;
   }
   public void setResetValue(String aResetValue)
   {
      resetValue = aResetValue;
   }
   public void setEnterValue(String aEnterValue)
   {
      enterValue = aEnterValue;
   }
   public void setDescriptionValue(String aDescriptionValue)
   {
      descriptionValue = aDescriptionValue;
   }
   public String getEquationValue()
   {
      return equationValue;
   }
   public String getImageHeightValue()
   {
      return imageHeightValue;
   }
   public String getImageWidthValue()
   {
      return imageWidthValue;
   }
   public String getXIntensityValue()
   {
      return xIntensityValue;
   }
   public String getZIntensityValue()
   {
      return zIntensityValue;
   }
   public String getYIntensityValue()
   {
      return yIntensityValue;
   }
   public String getEnterValue()
   {
      return enterValue;
   }
   public String getResetValue()
   {
      return resetValue;
   }
   public String getDescriptionValue()
   {
      return descriptionValue;
   }
}