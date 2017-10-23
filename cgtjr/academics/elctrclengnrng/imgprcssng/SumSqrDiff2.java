package cgtjr.academics.elctrclengnrng.imgprcssng;

public class SumSqrDiff2 //extends GrdntFilter
{
   private int ssdPixels[];
   private int pixelData[];

   private double tolerance;
   private double ssdValue;
   private YSclFltr aGrySclFltr1;
   private YSclFltr aGrySclFltr2;
   private int txtrTst;
   //motiontest14.mov, white3_3x3.jpg tolerance 85, 4 directions
   public SumSqrDiff2(String myFileName1)
   {
      aGrySclFltr1 = new YSclFltr(myFileName1);
      tolerance = 100;
   }
   public SumSqrDiff2(String myFileName1,String myFileName2)
   {
      aGrySclFltr1 = new YSclFltr(myFileName1);
      aGrySclFltr2 = new YSclFltr(myFileName2);
      tolerance = 100;
   }
   public void setPixelData(int myPixelData[],int myWidth,int myHeight)
   {
      aGrySclFltr2 = new YSclFltr(myPixelData,myWidth,myHeight);
   }
   public void filter3x3(int myX,int myY)
   {
      aGrySclFltr1.gryFltr3x3(1,1);
      //TODO:  fix code
      try{
         aGrySclFltr2.gryFltr3x3(myX,myY);
         ssdPixels = aGrySclFltr1.rtrvFltrdData3x3(1,1);
         pixelData = aGrySclFltr2.rtrvFltrdData3x3(myX, myY);
         cmptSSD(ssdPixels,pixelData);
      }catch(ArrayIndexOutOfBoundsException ai){
         
      }

   }
   public double cmptSSD(int myData1[],int myData2[])
   {
      double aValue1 = 0.0;
      double aValue2 = 0.0;
      double aValue3 = 0.0;
      
      int i = 0;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ++i;
      aValue1 = (myData1[i]-myData2[i])*(myData1[i]-myData2[i]);
      aValue2 += Math.sqrt(Math.abs(aValue1));
      //System.out.println("SumSqrDiff2: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
      ssdValue = aValue2/9;
      //System.out.println("SumSqrDiff2: ssd value = "+ssdValue+", tolerance = "+tolerance);
      return ssdValue;
   }
   public boolean isInTlrnc()
   {
      boolean TorF = false;

      if(ssdValue <= tolerance)
      {
         TorF = true;
      }
      return TorF;
   }
   public void setPixelData(int myPixelData[])
   {
      pixelData = myPixelData;
   }
   public boolean isInBounds3x3(int myX,int myY)
   {
      return aGrySclFltr2.isInBounds3x3(myX,myY);
   }
   public void setTolerance(double myTolerance)
   {
      tolerance = myTolerance;
   }
}