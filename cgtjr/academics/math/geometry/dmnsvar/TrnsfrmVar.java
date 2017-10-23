package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.DataVar;
import java.util.HashMap;


public class TrnsfrmVar extends DataVar
{
   private String DATANAME 		= "trnsfrm";
   private String dmnsnIdKey     = "trnsfrmid";
   private String trnsltXKey      = "translate-r";
   private String trnsltYKey      = "translate-s";
   private String trnsltZKey      = "translate-t";
   private String scaleXKey		= "scale-r";
   private String scaleYKey		= "scale-s";
   private String scaleZKey		= "scale-t";
   private String rotateXKey       = "rotate-r";
   private String rotateYKey       = "rotate-s";
   private String rotateZKey       = "rotate-t";

   public final String dscrptnKey	= "dscrptn";

   private String idVal = "";
   private double trnsltXVal;
   private double trnsltYVal;
   private double trnsltZVal;

   private double scaleXVal;
   private double scaleYVal;
   private double scaleZVal;
   private double rotateXVal;
   private double rotateYVal;
   private double rotateZVal;

   private String dscrptnVal	= "";

   public String getDataName()
   {
      return DATANAME;
   }
   public String getDmnsnIdKey()
   {
      return dmnsnIdKey;
   }
   public String getTrnsltXKey()
   {
      return trnsltXKey;
   }
   public String getTrnsltYKey()
   {
      return trnsltYKey;
   }
   public String getTrnsltZKey()
   {
      return trnsltZKey;
   }
   public String getScaleXKey()
   {
      return scaleXKey;
   }
   public String getScaleYKey()
   {
      return scaleYKey;
   }
   public String getScaleZKey()
   {
      return scaleZKey;
   }
   public String getRotateXKey()
   {
      return rotateXKey;
   }
   public String getRotateYKey()
   {
      return rotateYKey;
   }
   public String getRotateZKey()
   {
      return rotateZKey;
   }
   public String getDscrptnKey()
   {
      return dscrptnKey;
   }
   public void setIdVal(String aIdVal)
   {
      idVal = aIdVal;
   }
   public void setTrnsltXVal(double aTrnsltXVal)
   {
      trnsltXVal = aTrnsltXVal;
   }
   public void setTrnsltYVal(double anTrnsltYVal)
   {
      trnsltYVal = anTrnsltYVal;
   }
   public void setTrnsltZVal(double anTrnsltZVal)
   {
      trnsltZVal= anTrnsltZVal;
   }
   public void setScaleXVal(double anScaleXVal)
   {
      scaleXVal = anScaleXVal;
   }
   public void setScaleYVal(double aScaleYVal)
   {
      scaleYVal = aScaleYVal;
   }
   public void setScaleZVal(double aScaleZVal)
   {
      scaleZVal = aScaleZVal;
   }
   public void setRotateXVal(double anRotateXVal)
   {
      rotateXVal = anRotateXVal;
   }
   public void setRotateYVal(double aRotateYVal)
   {
      rotateYVal = aRotateYVal;
   }
   public void setRotateZVal(double aRotateZVal)
   {
      rotateZVal = aRotateZVal;
   }
   public void setDscrptnVal(String aDscrptnVal)
   {
      dscrptnVal = aDscrptnVal;
   }

   public String getIdVal()
   {
      return idVal;
   }
   public double getTrnsltXVal()
   {
      return trnsltXVal;
   }
   public double getTrnsltZVal()
   {
      return trnsltZVal;
   }
   public double getTrnsltYVal()
   {
      return trnsltYVal;
   }
   public double getScaleXVal()
   {
      return scaleXVal;
   }
   public double getScaleYVal()
   {
      return scaleYVal;
   }
   public double getScaleZVal()
   {
      return scaleZVal;
   }
   public double getRotateXVal()
   {
      return rotateXVal;
   }
   public double getRotateYVal()
   {
      return rotateYVal;
   }
   public double getRotateZVal()
   {
      return rotateZVal;
   }
   public String getDscrptnVal()
   {
      return dscrptnVal;
   }
   public HashMap rtrvHshMp()
   {
      return null;
   }

   
}