package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilterOptmzd;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.lnralgbra.Matrix;

public class CornerDetectViaGrdnt extends GrdntFilterOptmzd {

    /*
    private int gradientXSqrd[];
    private int gradientYSqrd[];
    private int gradientXY[];

    private int cornerDataPixel[];

    private int threshold = 16000;
    private int neighborHood[][];

    private double cornerMtrx[][][];

    private boolean isCornerRegion[];
    private int cornerRegionIndx[];
    private int originalData[];
    private boolean checkRegion = true;
    private int cornerIndex[];
    private int cornerCount;
    private double maxEigenValue[];
    private double lclMaxEgnVls[];
    private int lclMaxIndex[];
    private int lclWndwDmnsn;
    private int fltrDmnsn;
    private int glblWndwWidth;
    private int glblWndwHeight;
    private double eigenValue1[];
    private double eigenValue2[];
    private double answerTmp2[];
    private ImageDrawData imageDrawData;
    private int reservedColor1 = 0xffffff00;
    private int reservedColor2 = 0xff0000ff;
    private boolean displayCorners = true;
    private int aLength;
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);

        aLength = myImageHeight * myImageWidth;

        cornerDataPixel = new int[aLength];
        //crnrAndOrgnl = new int[aLength];
        gradientXSqrd = new int[aLength];
        gradientYSqrd = new int[aLength];
        gradientXY = new int[aLength];
        ///eigenVector = new Vector[3];
        isCornerRegion = new boolean[aLength];
        cornerRegionIndx = new int[aLength];
        maxEigenValue = new double[aLength];
        eigenValue1 = new double[aLength];
        eigenValue2 = new double[aLength];

        lclMaxEgnVls = new double[aLength];
        lclMaxIndex = new int[aLength];

        cornerIndex = new int[aLength];
        originalData = getOrgnlValues();

        ///cornerIndex = new int[aLength];
        cornerCount = 0;
        cornerMtrx = new double[aLength][2][2];
        lclWndwDmnsn = 9;
        fltrDmnsn = 3;
        glblWndwWidth = myImageWidth / lclWndwDmnsn;
        glblWndwHeight = myImageHeight / lclWndwDmnsn;
        ///eigenValues = new double[aLength];
        imageDrawData = new ImageDrawData(myImageWidth, myImageHeight);
        imageDrawData.setReservedColor1(reservedColor1);
        imageDrawData.setReservedColor2(reservedColor2);
    }

    public boolean getDisplayCorners() {
        return displayCorners;
    }

    public void setDisplayCorners(boolean myDisplayCorners) {
        this.displayCorners = myDisplayCorners;
    }

    public int getLclWndwDmnsn() {
        return lclWndwDmnsn;
    }

    public int getGlblWndwWidth() {
        return glblWndwWidth;
    }

    public void setThreshold(int myThreshold) {
        threshold = myThreshold;
    }

    public int getThreshold() {
        return threshold;
    }

    
    public int[] getFltrdData() {
        return imageDrawData.getImagePixels();
    }

    public ImageDrawData getImageDrawData() {
        return imageDrawData;
    }

    public int[] getCornerData() {
        return cornerDataPixel;
    }

    public void setCornerData(int myPixelData[]) {
        cornerDataPixel = myPixelData;
    }

    public int getCornerRegionIndx(int myIndex1) {
        return cornerRegionIndx[myIndex1];
    }

    public int getCornerCount() {
        return cornerCount;
    }

    public void setCornerRegion(int i) {
        setCornerRegion(i, true);
    }

    public void setCornerRegionIndx(int i) {
        setCornerRegionIndx(i, i);
    }

    public void setCornerRegionIndx(int i, int myValue) {
        int imageWidth = getImageWidth();
        if (isInBounds3x3(i)) {
            cornerRegionIndx[i - imageWidth - 1] = myValue;
            cornerRegionIndx[i - imageWidth] = myValue;
            cornerRegionIndx[i - imageWidth + 1] = myValue;
            cornerRegionIndx[i - 1] = myValue;
            cornerRegionIndx[i] = myValue;
            cornerRegionIndx[i + 1] = myValue;
            cornerRegionIndx[i + imageWidth - 1] = myValue;
            cornerRegionIndx[i + imageWidth] = myValue;
            cornerRegionIndx[i + imageWidth + 1] = myValue;
        }
    }

    public void filter(int grayValues[], int i) {
           cornerDetect(grayValues, i);
    }


    public void cornerDetect(int grayValues[], int i) {
        cornerDataPixel[i] = grayValues[i];
        imageDrawData.updatePixels(grayValues, i);
        //if (isInBounds3x3(i) == true) {
        //super.filter3x3(grayValues, i);//NOTE: should be filter9x9???
        super.filter(grayValues, i);//NOTE: should be filter9x9???  
        double grdntMag = getGrdntMgntd(i);
        
        if (getTemporalGradientValues(i) > getTmprlGrdntThrshld()) {
            //System.out.println("CornerDetectionOptmzd:getTemporalGradientValues(i)="+getTemporalGradientValues(i)+", getTmprlGrdntThrshld() = "+getTmprlGrdntThrshld());
            cornerMtrx[i] = updateCornerMatrix(i);
            solveEigenValues(cornerMtrx[i], i);
        }
    }

    public void cornerDetect3x3In9x9(int grayValues[], int i) {
        cornerDataPixel[i] = grayValues[i];
        imageDrawData.updatePixels(grayValues, i);
        if (isInBounds3x3(i) == true) {
            super.grdntFilter9x9(grayValues, i);//NOTE: should be filter9x9???
            if (getGrdntMgntdAvg3x3(i) > 100) {
                cornerMtrx[i] = updateCornerMatrix(i);
                solveEigenValues(cornerMtrx[i], i);
            }
        }
    }

    public void cornerDetect3x3In27x27(int grayValues[], int i) {
        cornerDataPixel[i] = grayValues[i];
        imageDrawData.updatePixels(grayValues, i);
        if (isInBounds3x3(i) == true) {
            super.grdntFilter27x27(grayValues, i);//NOTE: should be filter9x9???
            cornerMtrx[i] = updateCornerMatrix(i);
            solveEigenValues(cornerMtrx[i], i);
        }
    }

    public void cornerDetect9x9(int i) {
        cornerDetect9x9(cornerDataPixel, i);
    }

    public void cornerDetect9x9(int grayValues[], int i) {
        //System.out.println("CornerDetect: test 1: cornerData.length "+cornerData.length+", grayValues.length = "+grayValues.length+", x = "+i);
        cornerDataPixel[i] = grayValues[i];
        if (isInBounds3x3(i) == true) {
            super.grdntFilter27x27(grayValues, i);
            cornerMtrx[i] = updtCrnrMtrx9x9(i);
            solveEigenValues(cornerMtrx[i], i);
        }
    }

    public double[][] updtCrnrMtrx9x9(int anIndex) {
        double crnrMtrxTmp[][] = new double[2][2];
        double crnrMtrx9x9[][] = new double[2][2];
        int aWidthx3 = 3 * getImageWidth();
        crnrMtrxTmp = updateCornerMatrix(anIndex - aWidthx3 - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex - aWidthx3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex - aWidthx3 + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex + aWidthx3 - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex + aWidthx3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = updateCornerMatrix(anIndex + aWidthx3 + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);

        int x = rtrvXPstn(anIndex);
        int y = rtrvYPstn(anIndex);
        //System.out.println("CornerDetect: x = "+x+", y = "+y);
        //Matrix.print(crnrMtrx9x9);
        //cornerMtrx[anIndex][0][0] = crnrMtrx9x9[0][0];
        //cornerMtrx[anIndex][0][1] = crnrMtrx9x9[0][1];
        //cornerMtrx[anIndex][1][0] = crnrMtrx9x9[1][0];
        //cornerMtrx[anIndex][1][1] = crnrMtrx9x9[1][1];

        return crnrMtrx9x9;
    }

    public double[][] updateCornerMatrix(int j) {
        double cornerMatrix[][] = new double[2][2];
        int imageWidth = getImageWidth();
        int imageHeight = getImageHeight();
        int aGradientHor[] = this.getGrdntHrzntl();
        int aGradientVer[] = this.getGrdntVrtcl();
        //int imageLengthI = imageWidth*imageHeight;

        if (isInBounds3x3(j)) {
            gradientXSqrd[j] =
                    aGradientHor[j - imageWidth - 1] * aGradientHor[j - imageWidth - 1]
                    + aGradientHor[j - imageWidth] * aGradientHor[j - imageWidth]
                    + aGradientHor[j - imageWidth + 1] * aGradientHor[j - imageWidth + 1]
                    + aGradientHor[j - 1] * aGradientHor[j - 1]
                    + aGradientHor[j] * aGradientHor[j]
                    + aGradientHor[j + 1] * aGradientHor[j + 1]
                    + aGradientHor[j + imageWidth - 1] * aGradientHor[j + imageWidth - 1]
                    + aGradientHor[j + imageWidth] * aGradientHor[j + imageWidth]
                    + aGradientHor[j + imageWidth + 1] * aGradientHor[j + imageWidth + 1];

            gradientYSqrd[j] =
                    aGradientVer[j - imageWidth - 1] * aGradientVer[j - imageWidth - 1]
                    + aGradientVer[j - imageWidth] * aGradientVer[j - imageWidth]
                    + aGradientVer[j - imageWidth + 1] * aGradientVer[j - imageWidth + 1]
                    + aGradientVer[j - 1] * aGradientVer[j - 1]
                    + aGradientVer[j] * aGradientVer[j]
                    + aGradientVer[j + 1] * aGradientVer[j + 1]
                    + aGradientVer[j + imageWidth - 1] * aGradientVer[j + imageWidth - 1]
                    + aGradientVer[j + imageWidth] * aGradientVer[j + imageWidth]
                    + aGradientVer[j + imageWidth + 1] * aGradientVer[j + imageWidth + 1];

     
            cornerMatrix[0][0] = gradientXSqrd[j];
            cornerMatrix[1][1] = gradientYSqrd[j];

        }

        return cornerMatrix;
    }

    public double[][][] getCornerMtrx() {
        return cornerMtrx;
    }

    public double[][] getCornerMtrx(int myIndex) {
        return cornerMtrx[myIndex];
    }

    public void solveEigenValues(double myCrnrMtrx[][], int i) {
        double answer[] = solveEigenValues(myCrnrMtrx);
        eigenValue1[i] = answer[0];
        eigenValue2[i] = answer[1];
        double maxAnswer[] = arrngeMaxValue(answer);
        //System.out.println("CornerDetectOptmzd: answer[1] = "+answer[1]+",i="+i+",threshold = "+threshold+",checkregion="+checkRegion);
        if (maxAnswer[1] >= threshold && !isInCornerRegion3x3(i)) {
            maxEigenValue[i] = maxAnswer[1];

            if(getDisplayCorners()){
               updtCornerPixels(i);
            }
            setCornerRegion(i);
            cornerCount++;
        }
    }

    public void setCheckRegion(boolean myCheckRegion) {
        checkRegion = myCheckRegion;
    }

    public int getCornerIndex(int myIndex) {
        return cornerIndex[myIndex];
    }

    public void setEigenValue(int myIndex, double myValue) {
        maxEigenValue[myIndex] = myValue;
    }

    public double getEigenValue(int myIndex) {
        return maxEigenValue[myIndex];
    }

    public double getEigenValue1(int myIndex) {
        return eigenValue1[myIndex];
    }

    public double getEigenValue2(int myIndex) {
        return eigenValue2[myIndex];
    }

    public void setCornerRegion(int i, boolean myTorF) {
        if (isInBounds3x3(i)) {
            int imageWidth = getImageWidth();
            isCornerRegion[i - imageWidth - 1] = myTorF;
            isCornerRegion[i - imageWidth] = myTorF;
            isCornerRegion[i - imageWidth + 1] = myTorF;
            isCornerRegion[i - 1] = myTorF;
            isCornerRegion[i] = myTorF;
            isCornerRegion[i + 1] = myTorF;
            isCornerRegion[i + imageWidth - 1] = myTorF;
            isCornerRegion[i + imageWidth] = myTorF;
            isCornerRegion[i + imageWidth + 1] = myTorF;
        }
    }

    public boolean isInCornerRegion(int myIndex1) {
        boolean inBounds = false;

        if (isCornerRegion[myIndex1] == true) {
            inBounds = true;;
        }
        return inBounds;
    }
    public boolean isInCornerRegion3x3(int myIndex){
        int imageWidth = getImageWidth();
        boolean isInRegion = isInCornerRegion(myIndex - imageWidth - 1)||
            isInCornerRegion(myIndex- imageWidth)||
            isInCornerRegion(myIndex - 1)||
            isInCornerRegion(myIndex)||
            isInCornerRegion(myIndex + 1)||
            isInCornerRegion(myIndex + imageWidth - 1)||
            isInCornerRegion(myIndex + imageWidth)||
            isInCornerRegion(myIndex + imageWidth + 1);
        return isInRegion;        
    }
    public double[] solveEigenValues(double aCornerMatrix[][]) {
        double answer[] = new double[2];
        double answerTmp1 = 0.0;
        double answerTmp2 = 0.0;
        int secOrdPart = 1;
        double firstOrdPart = -1 * (aCornerMatrix[0][0] + aCornerMatrix[1][1]);
        double constPart = aCornerMatrix[0][0] * aCornerMatrix[1][1] - aCornerMatrix[0][1] * aCornerMatrix[1][0];
        double sqrtPart = Math.sqrt(firstOrdPart * firstOrdPart - 4 * secOrdPart * constPart);
        answerTmp1 = (-1 * firstOrdPart + sqrtPart) / (2 * secOrdPart);
        answerTmp2 = (-1 * firstOrdPart - sqrtPart) / (2 * secOrdPart);

        answer[0] = answerTmp1;
        answer[1] = answerTmp2;

        return answer;
    }

    public double[] arrngeMaxValue(double myValue[]) {
        double answer[] = new double[2];

        if (myValue[0] >= myValue[1]) {
            answer[0] = myValue[0];
            answer[1] = myValue[1];
        } else {
            answer[0] = myValue[1];
            answer[1] = myValue[0];
        }
        return answer;
    }

    public void updtCrnrAndOrgnlPxls(int i) {

        //crnrAndOrgnl = this.getOrgnlValues();
        if (isInBounds3x3(i)) {

        }
    }
    public void updtCornerPixels(int x,int y,int myColor) {
        int anIndex = ImageTool.rtrvIndex(x, y, getImageWidth());
        updtCornerPixels(cornerDataPixel, anIndex, myColor);
    }
    public void updtCornerPixels(int i) {
        updtCornerPixels(cornerDataPixel, i);
    }
    public void updtCornerPixels(int cornerDataPixel[], int i) {
        updtCornerPixels(cornerDataPixel,i,reservedColor1);
    }
    public void updtCornerPixels(int cornerDataPixel[], int i,int aColor) {
        int imageWidth = getImageWidth();
        //System.out.println("CornerDetect:test 1: update corner index = "+i);
        if (isInBounds3x3(i)) {
 
            imageDrawData.drawData(aColor, i - imageWidth - 1);
            imageDrawData.drawData(aColor, i - imageWidth);
            imageDrawData.drawData(aColor, i - imageWidth + 1);
            imageDrawData.drawData(aColor, i - 1);
            //imageDrawData.drawData(aColor, i);
            imageDrawData.drawData(aColor, i + 1);
            imageDrawData.drawData(aColor, i + imageWidth - 1);
            imageDrawData.drawData(aColor, i + imageWidth);
            imageDrawData.drawData(aColor, i + imageWidth + 1);
        }
    }
    public void updtLclMax(double myValue, int myIndex) {

        int imgWidth = getImageWidth();
        int imgHeight = getImageHeight();

        int myX = rtrvXPstn(myIndex);
        int myY = rtrvYPstn(myIndex);

        int wndwX = myX / lclWndwDmnsn;
        int wndwY = myY / lclWndwDmnsn;
        int glblIndex = ImageTool.rtrvIndex(wndwX, wndwY, glblWndwWidth);
        //System.out.println("CornerDetect: lclWndwDmnsn = "+lclWndwDmnsn+", glblWndwWidth="+glblWndwWidth);
        //System.out.println("CornerDetect: lclMaxEgnVls["+glblIndex+"] = "+lclMaxEgnVls[glblIndex]+"myValue = "+myValue);
        //System.out.println("CornerDetect: x="+myX+", y = "+myY+", glblX = "+glblX+", glblY = "+glblY + ", glblIndex = "+ glblIndex);

        if (myValue > lclMaxEgnVls[glblIndex]) {
            lclMaxEgnVls[glblIndex] = myValue;
            int prvsIndex = lclMaxIndex[glblIndex];
            lclMaxIndex[glblIndex] = myIndex;
            maxEigenValue[prvsIndex] = 0;
            eigenValue1[prvsIndex] = 0;
            eigenValue2[prvsIndex] = 0;
            //System.out.println("CornerDetect: removing index = "+prvsIndex+"updating lclMaxEgnVls["+glblIndex+"] = "+lclMaxEgnVls[glblIndex]);
    
            updtCornerPixels(myIndex);
        }
    }

    public int getLclMaxIndex(int myIndex) {
        return lclMaxIndex[myIndex];
    }

    public double getLclMaxEgnVls(int myIndex) {
        return lclMaxEgnVls[myIndex];
    }

    public void updtCrnrPxls(int myIndex) {
        int myX = rtrvXPstn(myIndex);
        int myY = rtrvYPstn(myIndex);
        //int glblIndex = ImageTool.rtrvIndex(glblX, glblY,glblWndwWidth);   
        if (myX > 0 && ((myX) % lclWndwDmnsn == 0) && myY > 0 && ((myY) % lclWndwDmnsn == 0)) {
            int glblX = myX / lclWndwDmnsn - 1;
            int glblY = myY / lclWndwDmnsn - 1;
            int glblIndex = ImageTool.rtrvIndex(glblX, glblY, glblWndwWidth);
            //System.out.println("CornerDetect: glblX = "+glblX+", glblY="+glblY+", glblIndex = "+glblIndex+", index = "+lclMaxIndex[glblIndex]);
            updtCornerPixels(lclMaxIndex[glblIndex]);
        }
    }

    public boolean isLclWndwCmplte(int myIndex) {
        boolean isCmplt = false;

        int imgWidth = getImageWidth();
        int imgHeight = getImageHeight();

        int myX = rtrvXPstn(myIndex);
        int myY = rtrvYPstn(myIndex);
        if (myX % lclWndwDmnsn == 0 && myY % lclWndwDmnsn == 0) {
            isCmplt = true;
        }
        return isCmplt;
    }

    public int getLclWndwIndx(int myIndex) {
        int myX = rtrvXPstn(myIndex);
        int myY = rtrvYPstn(myIndex);

        int glblX = myX / lclWndwDmnsn;
        int glblY = myY / lclWndwDmnsn;
        int glblIndex = ImageTool.rtrvIndex(glblX, glblY, glblWndwWidth);
        return lclMaxIndex[glblIndex];
    }
    */ 
}