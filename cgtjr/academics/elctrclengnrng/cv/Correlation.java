package cgtjr.academics.elctrclengnrng.cv;

public class Correlation
{   
   public static double cmptCrrltn(int myData1[],int myData2[])
   {
      double anAvrg1 = cmptAvrg(myData1);
      double anAvrg2 = cmptAvrg(myData2);
      double aCrrltn = cmptCrrltn(myData1,myData2,anAvrg1,anAvrg2);
      return aCrrltn;
   }
   public static double cmptCrrltn(int myData1[],int myData2[],double myAvg1,double myAvg2)
   {
      int aLength = myData1.length;
      int aLengthDbl = aLength;
      double aCorrelation = 0.0;
      double aValue1 = 0.0;
      double aValue2 = 0.0;
      double aValue3 = 0.0;
      
      for(int i=0;i<aLength;i++)
      {
         aValue1 += (myData1[i]-myAvg1)*(myData2[i]-myAvg2);
         aValue2 += (myData1[i]-myAvg1)*(myData1[i]-myAvg1);
         aValue3 += (myData2[i]-myAvg2)*(myData2[i]-myAvg2);
      }
      aCorrelation = (1.0/aLengthDbl)*aValue1/(Math.sqrt((1.0/aLengthDbl)*aValue2)*Math.sqrt((1.0/aLengthDbl)*aValue3));
      return aCorrelation;
   }
   public static double cmptCrrltn(double myData1[],double myData2[],double myAvg1,double myAvg2)
   {
      int aLength = myData1.length;
      int aLengthDbl = aLength;
      double aCorrelation = 0.0;
      double aValue1 = 0.0;
      double aValue2 = 0.0;
      double aValue3 = 0.0;
      
      for(int i=0;i<aLength;i++)
      {
         aValue1 += (myData1[i]-myAvg1)*(myData2[i]-myAvg2);
         aValue2 += (myData1[i]-myAvg1)*(myData1[i]-myAvg1);
         aValue3 += (myData2[i]-myAvg2)*(myData2[i]-myAvg2);
      }
      aCorrelation = (1.0/aLengthDbl)*aValue1/(Math.sqrt((1.0/aLengthDbl)*aValue2)*Math.sqrt((1.0/aLengthDbl)*aValue3));
      return aCorrelation;
   }
   public static double cmptAvrg(int myData[])
   {
      int aLength = myData.length;
      double aTotal = 0;
      double anAverage;
      
      for(int i=0;i<aLength;i++)
      {
         aTotal += myData[i];
      }
      anAverage = aTotal/aLength;
      return anAverage;
   }
   public static double cmptAvrg(double myData[])
   {
      int aLength = myData.length;
      double aTotal = 0;
      double anAverage;
      
      for(int i=0;i<aLength;i++)
      {
         aTotal += myData[i];
      }
      anAverage = aTotal/aLength;
      return anAverage;
   }
}