package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.video.TmprlGrdntFltr;

public class GrdntFilterParserTmp
{

    private int rgbColors[];
    private int rgbValues[];

    private int grdntVrtcl[];
    private int grdntHrzntl[];
    private int grdntHVTtl;

    private int grdntMgntd[];

    private int grdntMgntdSqrdTtl;

    private int grdntMgntdTtl;
    private int grayValuesTtl;
    private int grayValuesSqrdTtl;
    private double ornttnAngle[];
    private int imageWidth;
    private int imageHeight;
    private int count;
    private int krnlHrzntl[][];
    private int krnlVrtcl[][];
    private int imageLength;

    //private int myGrayValues[];
    private GrdntKrnl aGenericHVKrnl;// = new GrdntKrnl();
    private YSclFltrTmp ySclFilter;
    
    public GrdntFilterParserTmp(YSclFltrTmp mySclFltr) {
        ySclFilter = mySclFltr;
    }
    public void initialize(int myWidth, int myHeight) {
        this.intlzeGrdntFilter(myWidth, myHeight);
    }
    public void intlzeGrdntFilter(int myWidth, int myHeight) {
        int aHeight = myHeight;
        int aWidth = myWidth;

        imageLength = aWidth * aHeight;
        imageWidth = aWidth;
        imageHeight = aHeight;
        
        if(aGenericHVKrnl == null){
           aGenericHVKrnl = new GrdntKrnl();
           krnlHrzntl = aGenericHVKrnl.getHrzntlKrnl();
           krnlVrtcl = aGenericHVKrnl.getVrtclKrnl();
        }
        
        grdntVrtcl = new int[aWidth * aHeight];
        grdntHrzntl = new int[aWidth * aHeight];
        grdntMgntd = new int[aWidth * aHeight];
        ornttnAngle = new double[aWidth * aHeight];
           
        imageLength = aWidth * aHeight;
        imageWidth = aWidth;
        imageHeight = aHeight;   
    }

    public void filter1x1(int myIntlValues[], int i) {
        grdntFilter(myIntlValues, i);
    }

    /*
    public void filter3x3(int myX, int myY) {
        int anIndex = rtrvIndex(myX, myY);
        filter3x3(anIndex);
    }*/
    /*
    public void filter3x3(int myIndex) {
        int pixelData[] = getInptPxlData();
        filter3x3(pixelData, myIndex);
    }*/

    public void filter3x3(int myIntlValues[], int i) {
        grdntFilter3x3(myIntlValues, i);
    }
    /*
    public void grdntFilter(int myIntlValues[], int i) {
        grdntFilter(myIntlValues, i, ySclFilter.getImageWidth(), ySclFilter.getImageHeight());
    }*/

    public void filter(int myIntlValues[], int i) {
       //gradientGryFilter(myIntlValues,i);
    }
    public void gradientGryFilter(int myIntlValues[], int i){

        //Modification of this code is required ... try call to getGryVls(index) instead
        //myGrayValues = getGryVls();
        grdntFilter(myIntlValues, i);
        //grdntFilter3x3(myIntlValues, i);        
    }    
    /*
    public void gradientGryFilter(int myIntlValues[], int i){
        super.filter(myIntlValues, i);
        myGrayValues = getGryVls();
        grdntGryFltr3x3(myIntlValues, i);                
    }*/
    /*
    public void grdntFilter(int myIntlValues[], int i, int imageWidth, int imageHeight) {
        try {
            //orgnlValues[i] = myIntlValues[i];
            grdntHrzntl[i] =
            //int grdntHrzntl = 
                    (myGrayValues[i - imageWidth - 1] * krnlHrzntl[0][0]
                    + myGrayValues[i - imageWidth] * krnlHrzntl[0][1]
                    + myGrayValues[i - imageWidth + 1] * krnlHrzntl[0][2]
                    + myGrayValues[i - 1] * krnlHrzntl[1][0]
                    + myGrayValues[i] * krnlHrzntl[1][1]
                    + myGrayValues[i + 1] * krnlHrzntl[1][2]
                    + myGrayValues[i + imageWidth - 1] * krnlHrzntl[2][0]
                    + myGrayValues[i + imageWidth] * krnlHrzntl[2][1]
                    + myGrayValues[i + imageWidth + 1] * krnlHrzntl[2][2]);

            grdntVrtcl[i] =
            //int grdntVrtcl =
                    (myGrayValues[i - imageWidth - 1] * krnlVrtcl[0][0]
                    + myGrayValues[i - imageWidth] * krnlVrtcl[0][1]
                    + myGrayValues[i - imageWidth + 1] * krnlVrtcl[0][2]
                    + myGrayValues[i - 1] * krnlVrtcl[1][0]
                    + myGrayValues[i] * krnlVrtcl[1][1]
                    + myGrayValues[i + 1] * krnlVrtcl[1][2]
                    + myGrayValues[i + imageWidth - 1] * krnlVrtcl[2][0]
                    + myGrayValues[i + imageWidth] * krnlVrtcl[2][1]
                    + myGrayValues[i + imageWidth + 1] * krnlVrtcl[2][2]);

            grdntMgntd[i] =
                    (int) Math.sqrt(Math.abs((grdntHrzntl[i] * grdntHrzntl[i]) + (grdntVrtcl[i] * grdntVrtcl[i])));

            ornttnAngle[i] = Math.atan2(grdntVrtcl[i],grdntHrzntl[i]);

        } catch (ArrayIndexOutOfBoundsException aiob) {
        }

        count++;
    }*/
    public void grdntFilter(int myIntlValues[]) {
        int aLength = myIntlValues.length;
        for(int j=0;j<aLength;j++){
            grdntFilter(myIntlValues,j);
        }
    }
    public void grdntFilter(int myIntlValues[], int i) {
        
        try {           
            //orgnlValues[i] = myIntlValues[i];
              
            grdntHrzntl[i] =
            //int grdntHrzntl = 
                    //(ySclFilter.getGryVls(i - imageWidth - 1) * krnlHrzntl[0][0]
                    //+ ySclFilter.getGryVls(i - imageWidth) * krnlHrzntl[0][1]
                    //+ ySclFilter.getGryVls(i - imageWidth + 1) * krnlHrzntl[0][2]
                    + ySclFilter.getGryVls(i - 1) * krnlHrzntl[1][0]
                    + ySclFilter.getGryVls(i) * krnlHrzntl[1][1]
                    + ySclFilter.getGryVls(i + 1) * krnlHrzntl[1][2];
                    //+ ySclFilter.getGryVls(i + imageWidth - 1) * krnlHrzntl[2][0]
                    //+ ySclFilter.getGryVls(i + imageWidth) * krnlHrzntl[2][1]
                    //+ ySclFilter.getGryVls(i + imageWidth + 1) * krnlHrzntl[2][2]);

            grdntVrtcl[i] =
            //int grdntVrtcl =
                    //(ySclFilter.getGryVls(i - imageWidth - 1) * krnlVrtcl[0][0]
                    + ySclFilter.getGryVls(i - imageWidth) * krnlVrtcl[0][1]
                    //+ ySclFilter.getGryVls(i - imageWidth + 1) * krnlVrtcl[0][2]
                    //+ ySclFilter.getGryVls(i - 1) * krnlVrtcl[1][0]
                    + ySclFilter.getGryVls(i) * krnlVrtcl[1][1]
                    //+ ySclFilter.getGryVls(i + 1) * krnlVrtcl[1][2]
                    //+ ySclFilter.getGryVls(i + imageWidth - 1) * krnlVrtcl[2][0]
                    + ySclFilter.getGryVls(i + imageWidth) * krnlVrtcl[2][1];
                    //+ ySclFilter.getGryVls(i + imageWidth + 1) * krnlVrtcl[2][2]);

            grdntMgntd[i] =
                    (int) Math.sqrt(Math.abs((grdntHrzntl[i] * grdntHrzntl[i]) + (grdntVrtcl[i] * grdntVrtcl[i])));

            ornttnAngle[i] = Math.atan2(grdntVrtcl[i],grdntHrzntl[i]);
            
            if(grdntMgntd[i] > 10){
               ySclFilter.getImageDrawData().updatePixels(grdntMgntd, i);
            }
        } catch (ArrayIndexOutOfBoundsException aiob) {
        }
        
        count++;
    }

    public void grdntGryFilter(int myIntlValues[], int i, int imageWidth, int imageHeight) {

        try {

            //orgnlValues[i] = myIntlValues[i];

            grdntHrzntl[i] =
                    + grdntGryFilter(myIntlValues, i - 1) * krnlHrzntl[1][0]
                    + grdntGryFilter(myIntlValues, i) * krnlHrzntl[1][1]
                    + grdntGryFilter(myIntlValues, i + 1) * krnlHrzntl[1][2];

            grdntVrtcl[i] =
                    +grdntGryFilter(myIntlValues, i - imageWidth) * krnlVrtcl[0][1]
                    + grdntGryFilter(myIntlValues, i) * krnlVrtcl[1][1]
                    + grdntGryFilter(myIntlValues, i + imageWidth) * krnlVrtcl[2][1];
 
            grdntMgntd[i] =
                    (int) Math.sqrt(Math.abs((grdntHrzntl[i] * grdntHrzntl[i]) + (grdntVrtcl[i] * grdntVrtcl[i])));
            ornttnAngle[i] = Math.atan2(grdntHrzntl[i], grdntVrtcl[i]);
        } catch (ArrayIndexOutOfBoundsException aiob) {
        }
        count++;
    }
    public int grdntGryFilter(int myIntlValues[], int i) {
        return ySclFilter.gryFltr1x1(myIntlValues, i);
    }
    public void grdntGryFltr3x3(int myGrayValues[], int anIndex) {

        int aWidth = ySclFilter.getImageWidth();
        int aHeight = ySclFilter.getImageHeight();
        
        grdntGryFilter(myGrayValues, anIndex - aWidth - 1,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex - aWidth,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex - aWidth + 1,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex - 1,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex + 1,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex + aWidth - 1,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex + aWidth,aWidth,aHeight);
        grdntGryFilter(myGrayValues, anIndex + aWidth + 1,aWidth,aHeight);
    }
    public void grdntFilter3x3(int myGrayValues[], int anIndex) {
        int aWidth = ySclFilter.getImageWidth();

        grdntFilter(myGrayValues, anIndex - aWidth - 1);
        grdntFilter(myGrayValues, anIndex - aWidth);
        grdntFilter(myGrayValues, anIndex - aWidth + 1);
        grdntFilter(myGrayValues, anIndex - 1);
        grdntFilter(myGrayValues, anIndex);
        grdntFilter(myGrayValues, anIndex + 1);
        grdntFilter(myGrayValues, anIndex + aWidth - 1);
        grdntFilter(myGrayValues, anIndex + aWidth);
        grdntFilter(myGrayValues, anIndex + aWidth + 1);
    }

    public void grdntFilter9x9(int myGrayValues[], int anIndex) {
        int aWidthx3 = 3 * ySclFilter.getImageWidth();
        grdntFilter3x3(myGrayValues, anIndex - aWidthx3 - 3);
        grdntFilter3x3(myGrayValues, anIndex - aWidthx3);
        grdntFilter3x3(myGrayValues, anIndex - aWidthx3 + 3);
        grdntFilter3x3(myGrayValues, anIndex - 3);
        grdntFilter3x3(myGrayValues, anIndex);
        grdntFilter3x3(myGrayValues, anIndex + 3);
        grdntFilter3x3(myGrayValues, anIndex + aWidthx3 - 3);
        grdntFilter3x3(myGrayValues, anIndex + aWidthx3);
        grdntFilter3x3(myGrayValues, anIndex + aWidthx3 + 3);
    }

    public void grdntFilter18x18(int myGrayValues[], int anIndex) {

        grdntFilter9x9(myGrayValues, anIndex);
        grdntFilter9x9(myGrayValues, anIndex);
        grdntFilter9x9(myGrayValues, anIndex);
        grdntFilter9x9(myGrayValues, anIndex);
    }

    public void grdntFilter27x27(int myGrayValues[], int anIndex) {
        int aWidthx3 = 9 * ySclFilter.getImageWidth();
        grdntFilter9x9(myGrayValues, anIndex - aWidthx3 - 9);
        grdntFilter9x9(myGrayValues, anIndex - aWidthx3);
        grdntFilter9x9(myGrayValues, anIndex - aWidthx3 + 9);
        grdntFilter9x9(myGrayValues, anIndex - 9);
        grdntFilter9x9(myGrayValues, anIndex);
        grdntFilter9x9(myGrayValues, anIndex + 9);
        grdntFilter9x9(myGrayValues, anIndex + aWidthx3 - 9);
        grdntFilter9x9(myGrayValues, anIndex + aWidthx3);
        grdntFilter9x9(myGrayValues, anIndex + aWidthx3 + 9);
    }
    /*
     public double getHrzntlSqrdAvg() {
     double aValue = grdntHrzntlSqrdTtl / count;
     return aValue;
     }

     public double getVrtclSqrdAvg() {
     double aValue = grdntVrtclSqrdTtl / count;
     return aValue;
     }

     public double getHrzntlAvg() {
     double aValue = grdntHrzntlTtl / count;
     //System.out.println("GrdnFilter : grdntHrzntlTtl/count = "+aValue+", count = "+count);
     return aValue;
     }

     public double getVrtclAvg() {
     double aValue = grdntVrtclTtl / count;
     //System.out.println("GrdnFilter : grdntVrtclTtl/count ="+aValue+"count = "+count);
     return aValue;
     }
     */

    public double rtrvExpcttn() {
        //System.out.println("GrdntFilter.rtrvExpcttn(): grdntMgntdTtl = "+grdntMgntdTtl+", grdntMgntdSqrdTtl = "+grdntMgntdSqrdTtl+",count = "+count);
        double aValue = Math.sqrt(grdntMgntdSqrdTtl / count - (grdntMgntdTtl * grdntMgntdTtl) / (count * count));
        return aValue;
    }

    public double rtrvExpctIllmnt() {
        double aValue1 = 6 * Math.PI * Math.PI * grayValuesSqrdTtl / count;
        double aValue2 = 48 * (grayValuesTtl * grayValuesTtl) / (count * count);
        double aValue3 = Math.sqrt(aValue1 - aValue2);
        return aValue3;
    }

    public double rtrvGrdntMgntdAvg() {
        double aValue = grdntMgntdTtl / count;
        return aValue;
    }

    public double rtrvSlant() {
        double aValue1 = grayValuesTtl / count;
        double aValue2 = rtrvExpctIllmnt();
        double cosSigma = 4 * aValue1 / aValue2;
        double sigma = Math.acos(cosSigma);
        return sigma;
    }

    /*
     public double rtrvTilt() {
     double aGrdntYAvg = getHrzntlAvg();
     double aGrdntXAvg = getVrtclAvg();

     double tanTau = aGrdntYAvg / aGrdntXAvg;
     double tau = Math.atan(tanTau);
     return tau;
     }

     public void displaySlntTlt() {
     double z = Math.sin(rtrvSlant());
     double y = Math.sin(rtrvTilt());
     double x = Math.cos(rtrvTilt());
     //System.out.println("slant = "+rtrvSlant()+", tilt = "+rtrvTilt()+"x = "+x+", y = "+y+", z = "+z);
     }

     public double[] rtrvIllmnt() {
     double aTilt = rtrvTilt();
     double aSlant = rtrvSlant();
     double x = Math.cos(aTilt) * Math.sin(aSlant);
     double y = Math.sin(aTilt) * Math.sin(aSlant);
     double z = Math.cos(aSlant);
     //System.out.println("GrdntFilter:x = "+x+", y = "+y+", z = "+z);
     double anIlluminant[] = {x, y, z};
     return anIlluminant;
     }

     public double rtrvAlbedo() {
     double aValue = rtrvExpctIllmnt() / Math.PI;
     return aValue;
     }*/
    public int[] getGrdnt() {
        return rgbValues;
    }

    public int[] getGrdntMgntd() {
        return grdntMgntd;
    }

    public int getGrdntMgntd(int myIndex) {
        //return myGrayValues;
        if (!ySclFilter.isInBounds3x3(myIndex)) {
            return 0;
        }
        return grdntMgntd[myIndex];
    }

    public double getGrdntMgntdAvg3x3(int myIndex) {
        double anAvg = getGrdntMgntdTtl3x3(myIndex) / 9;
        return anAvg;
    }

    public double getGrdntMgntdAvg9x9(int myIndex) {
        double anAvg = getGrdntMgntdTtl9x9(myIndex) / 81;
        return anAvg;
    }

    public int getGrdntMgntdTtl3x3(int myIndex) {
        //return myGrayValues;
        int aWidth = ySclFilter.getImageWidth();
        int total = 0;
        total += getGrdntMgntd(myIndex - aWidth - 1);
        total += getGrdntMgntd(myIndex - aWidth);
        total += getGrdntMgntd(myIndex - aWidth + 1);
        total += getGrdntMgntd(myIndex - 1);
        total += getGrdntMgntd(myIndex);
        total += getGrdntMgntd(myIndex + 1);
        total += getGrdntMgntd(myIndex + aWidth - 1);
        total += getGrdntMgntd(myIndex + aWidth);
        total += getGrdntMgntd(myIndex + aWidth + 1);
        return total;
    }

    public int getGrdntMgntdTtl9x9(int myIndex) {
        int aWidth = 3 * ySclFilter.getImageWidth();
        int total = 0;
        total += getGrdntMgntdTtl3x3(myIndex - aWidth - 3);
        total += getGrdntMgntdTtl3x3(myIndex - aWidth);
        total += getGrdntMgntdTtl3x3(myIndex - aWidth + 3);
        total += getGrdntMgntdTtl3x3(myIndex - 3);
        total += getGrdntMgntdTtl3x3(myIndex);
        total += getGrdntMgntdTtl3x3(myIndex + 3);
        total += getGrdntMgntdTtl3x3(myIndex + aWidth - 3);
        total += getGrdntMgntdTtl3x3(myIndex + aWidth);
        total += getGrdntMgntdTtl3x3(myIndex + aWidth + 3);
        return total;
    }

    public void setGrdntMgntd(int myGrdntMgntd[]) {
        grdntMgntd = myGrdntMgntd;
    }

    public int[] getGrdntHrzntl() {
        return grdntHrzntl;
    }
    public int getGrdntHrzntl(int myIndex) {
        return grdntHrzntl[myIndex];
    }
    public int[] getGrdntVrtcl() {
        return grdntVrtcl;
    }
    public int getGrdntVrtcl(int myIndex) {
        return grdntVrtcl[myIndex];
    }
    /*
    public int[] getFltrdData() {
        //return myGrayValues;
        //return orgnlValues;
        return grdntMgntd;
    }*/

    public int getGrdntHVTtl() {
        return grdntHVTtl;
    }

    /*
    public int[] getOrgnlValues() {
        return orgnlValues;
    }*/

    /*
     public double[] getGrdHrzUnit() {
     return grdHrzUnit;
     }
     public double[] getGrdVrtUnit() {
     return grdVrtUnit;
     }*/
    public double getOrntnAngle(int myIndex) {
        return ornttnAngle[myIndex];
    }

    public double[] getOrntnAngle() {
        return ornttnAngle;
    }

    public void finish() {

    }
}