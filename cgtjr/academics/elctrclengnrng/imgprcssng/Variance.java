package cgtjr.academics.elctrclengnrng.imgprcssng;


import java.util.*;


//TODO code clean up
public class Variance// implements BndryNodeAction_I
{
   private double minValue;
   private double maxValue;
   private double mean;
   private int count;
   private double sum;
   private Vector aVector;
   private double average;
   private double stndrdDvtn;

   public Variance(double myValue[])
   {
      minValue = Float.MAX_VALUE;
      maxValue = -1*Float.MAX_VALUE;
      cmptStdrdDvtn(myValue);
   }
   public void setMinValue(float myMinValue)
   {
      minValue = myMinValue;
   }
   public void setMaxValue(float myMaxValue)
   {
      maxValue = myMaxValue;
   }
   public double getMinValue()
   {
      return minValue;
   }
   public double getMaxValue()
   {
      return maxValue;
   }
   public void updtMinMaxVal(double myValue)
   {
       if(myValue <= minValue)
       {
           minValue = myValue;
       }
       if(myValue >= maxValue)
       {
           maxValue = myValue;
       }
   }
   public double cmptStdrdDvtn(double myValue[])
   {
      double anAverage = cmptAvrg(myValue);
      return cmptStdrdDvtn(myValue,anAverage);
   }
   private double cmptStdrdDvtn(double myValue[],double myAverage)
   {
      int aCount = myValue.length;

      double aValue = 0;
      double variance = 0;
      for(int i=0;i<count;i++)
      {
         aValue = myValue[i];
         variance += (aValue - myAverage)*(aValue - myAverage)/aCount;
      }
      stndrdDvtn = (float)Math.sqrt(variance);
      //System.out.println("VltgDffrncUpdtr: sum = "+sum+",stndrdDvtn="+stndrdDvtn+",average="+average+", max="+maxValue+",min="+minValue);
      return stndrdDvtn;
   }
   public double getAverage()
   {
      return average;
   }
   private double cmptAvrg(double myValue[])
   {
      sum = 0;
      int aLength = myValue.length;
      setCount(aLength);
      for(int i= 0;i<aLength;i++)
      {
         updtMinMaxVal(myValue[i]);
         sum = sum + myValue[i];
      }
      average = sum/aLength;
      return average;
   }
   private void setCount(int myCount)
   {
      count = myCount;
   }
   public double rtrvStndrdDvtnMaxAvrg()
   {
      double maxValue = average+stndrdDvtn;
      return maxValue;
   }
   public double rtrvStndrdDvtnMinAvrg()
   {
      double minValue = average-stndrdDvtn;
      return minValue;
   }
   /*
   public void update(Vector myVector)
   {
      Point aPoint = null;
      int aSize = myVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Point)myVector.elementAt(i);
         update(aPoint);
      }
   }
   public void update(Shape myShape)
   {
      Point aPoint = null;
      aVector = myShape.getNodes();
      int aSize = aVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Point)aVector.elementAt(i);
         update(aPoint);
      }
   }
   private void update(Point myPoint7)
   {
      double aValue = myPoint7.getValue();
      updtMinMaxVal(aValue);
   }*/

}