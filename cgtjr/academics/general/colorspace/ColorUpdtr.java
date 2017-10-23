package cgtjr.academics.general.colorspace;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;
import java.awt.Color;

import cgtjr.academics.math.geometry.*;

public class ColorUpdtr
{
   private double values[];
   private double maxValue;
   private double minValue;
   private double minColor;
   private double maxColor;
   private double clrValue;
   private ShapePnt shp;
   private double offSet;

   public ColorUpdtr(ShapePnt myShape,double myMinValue, double myMaxValue)
   {
      shp = myShape;
      minValue = myMinValue;
      maxValue = myMaxValue;
   }
   public ColorUpdtr(double myValues[],double myMaxValue)
   {
      values = myValues;
      minValue = 0;
      maxValue = myMaxValue;
   }
   public ColorUpdtr(double myValues[],double myMinValue,double myMaxValue)
   {
      values = myValues;
      minValue = myMinValue;
      maxValue = myMaxValue;
   }
   public void setOffSet(double myOffSet)
   {
     offSet = myOffSet;
   }
   public double getOffSet()
   {
      return offSet;
   }
   public void update(Pnt myPoint)
   {
      int aColor = 0;
      int anIndex = myPoint.getCounter();
      double aValue = values[anIndex];
      //myPoint.setValue(aValue);
      //clrValue = (Math.abs(minValue)+aValue)/(maxValue-minValue);
      clrValue = (aValue-minValue)/(maxValue-minValue);
      System.out.println("ColorUpdtr: color = "+clrValue+",value="+aValue+",minValue="+minValue+",maxValue="+maxValue);

      /*
      if(clrValue < 0)
         clrValue = 0;
      
      if(clrValue > maxValue)
         clrValue = maxValue;
      */
      if(clrValue >= -0.00001 && clrValue <= -0.00001 )
         aColor = Color.HSBtoRGB((float).1, .00f, .00f);
      else
         aColor = Color.HSBtoRGB((float)clrValue, .500f, .500f);
      myPoint.setColor(aColor);
      //myPoint.setValue(values[anIndex]);
      
   }
   public void update1(Pnt myPoint)
   {
      int aColor = 0;
      int anIndex = myPoint.getCounter();
      double aValue = myPoint.getValue();
      //myPoint.setValue(aValue);
      //clrValue = (Math.abs(minValue)+aValue)/(maxValue-minValue);
      clrValue = (aValue - minValue)/(maxValue-minValue);

      updateColors(clrValue);
      
      if(clrValue < 0)
         clrValue = 0;
      if(clrValue > 1)
         clrValue = 1;

      System.out.println("ColorUpdtr: color = "+clrValue+",value="+aValue+",minValue="+minValue+",maxValue="+maxValue);

      clrValue += offSet;
      if(clrValue == 0)
         aColor = Color.HSBtoRGB((float)clrValue, .00f, .00f);
      else
         aColor = Color.HSBtoRGB((float)clrValue, .300f, .700f);
      myPoint.setColor(aColor);
      //myPoint.setValue(values[anIndex]);
      //System.out.println("ColorUpdtr: color = "+clrValue+",value="+aValue+",minValue="+minValue+",maxValue="+maxValue);
   }
   public void update(ShapePnt myShape)
   {
      Vector aVector = (Vector)myShape.getNodes();
      update(aVector);
   }
   public void update(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         update(aPoint);
      }
   }
   public void update()
   {
      Vector aVector = (Vector)shp.getNodes();
      update1(aVector);
   }
   public void update1(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         update1(aPoint);
      }
   }
   public void updateColors(double aColor){
      if(aColor < minColor){
          minColor = aColor;
      } else if(aColor > maxColor){
          maxColor = aColor;
      }
      System.out.println("ColorUpdtr: minColor = "+minColor+", maxColor = "+maxColor);
   }
}