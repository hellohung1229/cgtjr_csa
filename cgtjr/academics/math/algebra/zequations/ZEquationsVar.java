package cgtjr.academics.math.algebra.zequations;

import cgtjr.academics.elctrclengnrng.imgprcssng.*;

import java.awt.image.*;

public abstract class ZEquationsVar implements Equations
{
   public static final String CLASSNAME 		= "zequations";
   public static final String zEquationIdKey 	= "zequationid";
   public static final String equationKey 	= "equation";
   public static final String xKey 			= "x";
   public static final String yKey    		= "y";
   public static final String xShiftKey    	= "xShift";
   public static final String yShiftKey		= "yShift";
   public static final String yScaleKey		= "yScale";
   public static final String xScaleKey		= "xScale";
   public static final String amplitudeKey	= "amplitude";
   public static final String minAmpltdKey	= "minAmpltd";
   public static final String maxAmpltdKey	= "maxAmpltd";
   public static final String zKey			= "z";
   public static final String eqtnNameKey	= "eqtnName";
   public static final String descriptionKey	= "description";

   private String zEquationIdValue;
   private String equationValue;
   private String xValue;
   private String yValue;
   private String xShiftValue;
   private String yShiftValue;
   private String yScaleValue;
   private String xScaleValue;
   private String amplitudeValue;
   private String minAmpltdValue;
   private String maxAmpltdValue;
   private String zValue;
   private String eqtnNameValue;
   private String descriptionValue;

   private String zEquationId;
   private String equation;
   private float x;
   private float y;
   private float xShift;
   private float yShift;
   private float yScale;
   private float xScale;
   private float amplitude;
   private float minAmpltd;
   private float maxAmpltd;
   private float z;
   private float eqtnName;
   private float description;

   public ZEquationsVar()
   {
   }
   public static String getClassName()
   {
      return CLASSNAME;
   }
   public static String getZEquationIdKey()
   {
      return zEquationIdKey;
   }
   public static String getEquationKey()
   {
      return equationKey;
   }
   public static String getXKey()
   {
      return xKey;
   }
   public static String getYKey()
   {
      return yKey;
   }
   public static String getXShiftKey()
   {
      return xShiftKey;
   }
   public static String getYShiftKey()
   {
      return yShiftKey;
   }
   public static String getYScaleKey()
   {
      return yScaleKey;
   }
   public static String getXScaleKey()
   {
      return xScaleKey;
   }
   public static String getDescriptionKey()
   {
      return descriptionKey;
   }
   public static String getAmplitudeKey()
   {
      return amplitudeKey;
   }
   public static String getMinAmpltdKey()
   {
      return minAmpltdKey;
   }
   public static String getMaxAmpltdKey()
   {
      return maxAmpltdKey;
   }
   public static String getZKey()
   {
      return zKey;
   }
   public static String getEqtnNameKey()
   {
      return eqtnNameKey;
   }

   public void setZequationIdValue(String aZequationIdValue)
   {
      zEquationIdValue = aZequationIdValue;
   }
   public void setEquationValue(String anEquationValue)
   {
      equationValue = anEquationValue;
   }
   public void setAmplitudeValue(String anAmplitudeValue)
   {
      amplitudeValue = anAmplitudeValue;
   }
   public void setMinAmpltdValue(String aMinAmpltdValue)
   {
      minAmpltdValue = aMinAmpltdValue;
   }
   public void setMaxAmpltdValue(String aMaxAmpltdValue)
   {
      maxAmpltdValue = aMaxAmpltdValue;
   }
   public void setXValue(String aXValue)
   {
      xValue = aXValue;
   }
   public void setYValue(String anYValue)
   {
      yValue = anYValue;
   }
   public void setXShiftValue(String anXShiftValue)
   {
      xShiftValue= anXShiftValue;
   }
   public void setZValue(String anZValue)
   {
      zValue = anZValue;
   }
   public void setEqtnNameValue(String aEqtnNameValue)
   {
      eqtnNameValue = aEqtnNameValue;
   }
   public String getZequationIdValue()
   {
      return zEquationIdValue;
   }
   public String getZValue()
   {
      return zValue;
   }
   public String getEqtnNameValue()
   {
      return eqtnNameValue;
   }
   public void setDescriptionValue(String aDescriptionValue)
   {
      descriptionValue = aDescriptionValue;
   }
   public String getXValue()
   {
      return xValue;
   }
   public String getYValue()
   {
      return yValue;
   }
   public String getXShiftValue()
   {
      return xShiftValue;
   }
   public void setYShiftValue(String anYShiftValue)
   {
      yShiftValue = anYShiftValue;
   }
   public void setYScaleValue(String aYScaleValue)
   {
      yScaleValue = aYScaleValue;
   }
   public void setXScaleValue(String aXScaleValue)
   {
      xScaleValue = aXScaleValue;
   }
   public String getYShiftValue()
   {
      return yShiftValue;
   }
   public String getYScaleValue()
   {
      return yScaleValue;
   }
   public String getXScaleValue()
   {
      return xScaleValue;
   }
   public String getAmplitudeValue()
   {
      return amplitudeValue;
   }
   public String getMinAmpltdValue()
   {
      return minAmpltdValue;
   }
   public String getMaxAmpltdValue()
   {
      return maxAmpltdValue;
   }
   public String getEquationValue()
   {
      return equationValue;
   }
   public String getDescriptionValue()
   {
      return descriptionValue;
   }
}