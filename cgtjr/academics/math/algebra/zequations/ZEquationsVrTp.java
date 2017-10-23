package cgtjr.academics.math.algebra.zequations;

import cgtjr.academics.elctrclengnrng.imgprcssng.*;

import java.awt.image.*;

public class ZEquationsVrTp
{
   private String name;

   private double radius;

   private double maxZ;
   private double z[][];
   
   private String equation;
   private float x;
   private float y;
   private float xShift;
   private float yShift;
   private float yScale;
   private float xScale;
   private double zScale;
   private float amplitude;
   private float minAmpltd;
   private float maxAmpltd;

   private String eqtnName;

   private String description;

   public final static String eqtnStrng = "log(y*y+x*x)";

   public ZEquationsVrTp()
   {
      amplitude = 1;
      xScale = 1;
      yScale = 1;
      zScale = 0.01;
   }
   public double[][] rtrvZValues()
   {
      return z;
   }
   public double getMaxZ()
   {
      return maxZ;
   }
   public void setMaxZ(double myMaxZ)
   {
      maxZ = myMaxZ;
   }
   public double[][] getZ()
   {
      return z;
   }
   public void setZ(double myZ[][])
   {
      z = myZ;
   }
   public double getZScale()
   {
      return zScale;
   }
   public void setZScale(double myZScale)
   {
      zScale = myZScale;
   }
   public void setEquation(String anEquation)
   {
      equation = anEquation;
   }
   public void setAmplitude(float anAmplitude)
   {
      amplitude = anAmplitude;
   }
   public void setMinAmpltd(float aMinAmpltd)
   {
      minAmpltd = aMinAmpltd;
   }
   public void setMaxAmpltd(float aMaxAmpltd)
   {
      maxAmpltd = aMaxAmpltd;
   }
   public void setX(float aX)
   {
      x = aX;
   }
   public void setY(float anY)
   {
      y = anY;
   }
   public void setXShift(float anXShift)
   {
      xShift= anXShift;
   }
   public float getX()
   {
      return x;
   }
   public float getY()
   {
      return y;
   }
   public float getXShift()
   {
      return xShift;
   }
   public void setYShift(float anYShift)
   {
      yShift = anYShift;
   }
   public void setYScale(float aYScale)
   {
      yScale = aYScale;
   }
   public void setXScale(float aXScale)
   {
      xScale = aXScale;
   }
   public float getYShift()
   {
      return yShift;
   }
   public float getYScale()
   {
      return yScale;
   }
   public float getXScale()
   {
      return xScale;
   }
   public float getAmplitude()
   {
      return amplitude;
   }
   public float getMinAmpltd()
   {
      return minAmpltd;
   }
   public float getMaxAmpltd()
   {
      return maxAmpltd;
   }
   public String getEquation()
   {
      return equation;
   }
   public String getEqtnName()
   {
      return eqtnName;
   }
   public String getDescription()
   {
      return description;
   }
   public static String getEqtnStrng()
   {
      return eqtnStrng;
   }
}