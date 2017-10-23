package cgtjr.academics.elctrclengnrng.cv.sfs;

import java.awt.image.*;
import java.util.*;

public class Illuminant
{
   private double x;
   private double y;
   private double z;
   private double tilt;
   private double slant;
   private double illumination[];

   public Illuminant()
   {
      illumination = new double[3];
      x = 0.0;
      y = 0.0;
      z = 0.0;
   }
   public Illuminant(double myTau,double mySigma)
   {
      illumination = new double[3];
      x = Math.cos(myTau)*Math.sin(mySigma);
      y = Math.sin(myTau)*Math.cos(mySigma);
      z = Math.cos(myTau);
   }
   public Illuminant(double myX,double myY,double myZ)
   {
      illumination = new double[3];
      x = myX;
      y = myY;
      z = myZ;
   }
   public double getX()
   {
      return x;
   }
   public double getY()
   {
      return y;
   }
   public double getZ()
   {
      return z;
   }
   public double getTilt()
   {
      return tilt;
   }
   public double getSlant()
   {
      return slant;
   }
   public void setIlluminant(double myTau,double mySigma)
   {
      x = Math.cos(myTau)*Math.sin(mySigma);
      y = Math.sin(myTau)*Math.cos(mySigma);
      z = Math.cos(myTau);
   }
   public void setIlluminant(String myXYZString)
   {
      StringTokenizer aStringTokenizer = new StringTokenizer(myXYZString);
      String xString = aStringTokenizer.nextToken();
      String yString = aStringTokenizer.nextToken();
      String zString = aStringTokenizer.nextToken();
      x = new Double(xString).doubleValue();
      y = new Double(yString).doubleValue();
      z = new Double(zString).doubleValue();
   }
   public double[] getIllumination()
   {
      illumination[0] = x;
      illumination[1] = y;
      illumination[2] = z;
      return illumination;
   }
}