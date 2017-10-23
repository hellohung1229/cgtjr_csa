package cgtjr.academics.elctrclengnrng.imgprcssng;

public class SumSqrDiff1 //extends GrdntFilter
{

    private int ssdPixels[];
    private int pixelData[];
    private int frameData[];
    private boolean isFrameDiff;
    private double tolerance;
    private double ssdValue;
    private YSclFltr aGrySclFltr1;
    private YSclFltr aGrySclFltr2;

    //motiontest14.mov, white3_3x3.jpg tolerance 85, 4 directions
    public SumSqrDiff1() {
        tolerance = 80;
        isFrameDiff = true;
    }

    public SumSqrDiff1(String myFileName1) {
        aGrySclFltr1 = new YSclFltr(myFileName1);
        tolerance = 80;
    }

    public SumSqrDiff1(String myFileName1, String myFileName2) {
        aGrySclFltr1 = new YSclFltr(myFileName1);
        aGrySclFltr2 = new YSclFltr(myFileName2);
        tolerance = 80;
    }

    public void setPixelData(int myPixelData[], int myWidth, int myHeight) {
        if (isFrameDiff == true && aGrySclFltr1 == null) {
            aGrySclFltr1 = new YSclFltr(myPixelData, myWidth, myHeight);
            aGrySclFltr2 = new YSclFltr(myPixelData, myWidth, myHeight);
        } else {
            aGrySclFltr2 = new YSclFltr(myPixelData, myWidth, myHeight);
        }
    }

    public void filter3x3(int myX, int myY) {
        try {
            if (isFrameDiff == true) {
                aGrySclFltr1.gryFltr3x3(myX, myY);
                frameData = aGrySclFltr1.rtrvFltrdData3x3(myX, myY);
                pixelData = aGrySclFltr2.rtrvFltrdData3x3(myX, myY);
                cmptSSD(frameData, pixelData);
            } else {
                aGrySclFltr1.gryFltr3x3(1, 1);
                ssdPixels = aGrySclFltr1.rtrvFltrdData3x3(1, 1);
                pixelData = aGrySclFltr2.rtrvFltrdData3x3(myX, myY);
                cmptSSD(ssdPixels, pixelData);
            }
        } catch (ArrayIndexOutOfBoundsException aiob) {
        }
    }

    public double cmptSSD(int myData1[], int myData2[]) {
        double aValue1 = 0.0;
        double aValue2 = 0.0;

        int i = 0;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ++i;
        aValue1 = (myData1[i] - myData2[i]) * (myData1[i] - myData2[i]);
        aValue2 += Math.sqrt(Math.abs(aValue1));
        //System.out.println("SumSqrDiff: ssd pixel = "+myData1[i]+" pixel value = "+myData2[i]);
        ssdValue = aValue2 / 9;
        //System.out.println("SumSqrDiff: ssd value = "+ssdValue+", tolerance = "+tolerance);
        return ssdValue;
    }

    public boolean isInTlrnc() {
        boolean TorF = false;
        //System.out.println("SumSqrDiff: ssdValue = "+ssdValue+", tolerance = "+tolerance);
        if (ssdValue <= tolerance) {
            TorF = true;
        }
        return TorF;
    }

    public void setPixelData(int myPixelData[]) {
        pixelData = myPixelData;
    }
    /*
     * public boolean isInBounds3x3(int myX,int myY) { return
     * aGrySclFltr2.isInBounds3x3(myX,myY);
   }
     */

    public void setTolerance(double myTolerance) {
        tolerance = myTolerance;
    }
}