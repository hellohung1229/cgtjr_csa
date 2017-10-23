package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilterParserTmp;
import cgtjr.academics.elctrclengnrng.imgprcssng.YSclFltrTmp;
import cgtjr.academics.elctrclengnrng.video.TmprlGrdntFltrTmp;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.lnralgbra.Matrix;

public class CornerDetectGrdntTmp  {

    private int gradientXSqrd;
    private int gradientYSqrd;
    private int gradientXY;
    //private int greyStructure[][];
    //private int crnrAndOrgnl[];
    private int cornerDataPixel[];
    //private int threshold = 100;
    //private int threshold = 1200;  //Lane Detection 
    private int threshold = 800;
    private int neighborHood[][];
    //double cornerMatrix[][];
    //double crnrMtrx9x9[][];
    private double cornerMtrx[][];
    //private double answer[];
    //private Object anObject[] = new Object[3];
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

    private boolean displayCorners = false;
    private int aLength;
    private TmprlGrdntFltrTmp tmprlGrdntFltrTmp;
    private YSclFltrTmp ySclFltrTmp;
    private GrdntFilterParserTmp grdntFilterParser;
    
    public CornerDetectGrdntTmp(YSclFltrTmp myYSclFltrTmp,TmprlGrdntFltrTmp myTmprlGrdntFltrTmp,GrdntFilterParserTmp myGrdntFilterParserTmp){
        ySclFltrTmp = myYSclFltrTmp;
        tmprlGrdntFltrTmp = myTmprlGrdntFltrTmp;
        grdntFilterParser = myGrdntFilterParserTmp;
    }
    public void initialize(int myImageWidth, int myImageHeight) {
        //super(new SobelKernel());
        //super.initialize(myImageWidth, myImageHeight);
        aLength = myImageHeight * myImageWidth;
        //cornerMatrix = new double[2][2];
        //crnrMtrx9x9 = new double[2][2];
        //Note: this conflicts with CornerDtctDrwActn

        //if(cornerDataPixel == null){
        //cornerDataPixel = new int[aLength];
        //}
        //crnrAndOrgnl = new int[aLength];
        //if(gradientXSqrd == null){
        //gradientXSqrd = new int[aLength];
        //}
        //if(gradientYSqrd == null){
        //gradientYSqrd = new int[aLength];            
        //}
        //if(gradientXY == null){
        //gradientXY = new int[aLength];            
        //}

        ///eigenVector = new Vector[3];
        //if(isCornerRegion == null){
           isCornerRegion = new boolean[aLength];            
        //}

        //if(cornerRegionIndx == null){
        //cornerRegionIndx = new int[aLength];            
        //}

        if(maxEigenValue == null){
          maxEigenValue = new double[aLength];            
        }        

        //if(eigenValue1 == null){
        //eigenValue1 = new double[aLength];            
        //}        

        //if(eigenValue2 == null){        
        //eigenValue2 = new double[aLength];
        //}
        
        //if(lclMaxEgnVls == null){
        //lclMaxEgnVls = new double[aLength];            
        //}

        //if(lclMaxIndex == null){
        //lclMaxIndex = new int[aLength];            
        //}

        //if(cornerIndex == null){
           //cornerIndex = new int[aLength];            
        //}

        
        //originalData = getOrgnlValues();

        ///cornerIndex = new int[aLength];
        cornerCount = 0;

        //if(cornerMtrx == null){
        //cornerMtrx = new double[2][2];            
        //}

        lclWndwDmnsn = 9;
        fltrDmnsn = 3;
        glblWndwWidth = myImageWidth / lclWndwDmnsn;
        glblWndwHeight = myImageHeight / lclWndwDmnsn;
        ///eigenValues = new double[aLength];
        
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

    public void setEigenThreshold(int myThreshold) {
        threshold = myThreshold;
    }

    public int getEigenThreshold() {
        return threshold;
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

    /*
    public void setCornerRegionIndx(int i) {
        setCornerRegionIndx(i, i);
    }

    public void setCornerRegionIndx(int i, int myValue) {
        int imageWidth = ySclFltrTmp.getImageWidth();
        if (ySclFltrTmp.isInBounds3x3(i)) {
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
    }*/
    public void filter(int myGrayValues[]) {
       int aLength = myGrayValues.length;
       for(int i=0;i<aLength;i++){
           filter(myGrayValues,i);
       }
    }
    public void filter(int myGrayValues[], int i) {
        //super.filter(grayValues, i);
        cornerDetect(myGrayValues, i);
    }
    /*
     * public void cornerDetect9x9(int myGrayValues[],int anIndex) { int
     * aWidthx3 = 3*getImageWidth();
     * cornerDetect3x3(myGrayValues,anIndex-aWidthx3-3);//change 1 to 3 ??!
     * cornerDetect3x3(myGrayValues,anIndex-aWidthx3);
     * cornerDetect3x3(myGrayValues,anIndex-aWidthx3+3);
     * cornerDetect3x3(myGrayValues,anIndex-3);
     * cornerDetect3x3(myGrayValues,anIndex);
     * cornerDetect3x3(myGrayValues,anIndex+3);
     * cornerDetect3x3(myGrayValues,anIndex+aWidthx3-3);
     * cornerDetect3x3(myGrayValues,anIndex+aWidthx3);
     * cornerDetect3x3(myGrayValues,anIndex+aWidthx3+3); } public void
     * cornerDetect3x3(int myGrayValues[],int anIndex) { int aWidth =
     * getImageWidth(); cornerDetect(myGrayValues,anIndex-aWidth-1);
     * cornerDetect(myGrayValues,anIndex-aWidth);
     * cornerDetect(myGrayValues,anIndex-aWidth+1);
     * cornerDetect(myGrayValues,anIndex-1); cornerDetect(myGrayValues,anIndex);
     * cornerDetect(myGrayValues,anIndex+1);
     * cornerDetect(myGrayValues,anIndex+aWidth-1);
     * cornerDetect(myGrayValues,anIndex+aWidth);
     * cornerDetect(myGrayValues,anIndex+aWidth+1);
     }
     */

    public void cornerDetect(int myPixelValues[], int i) {
        if (tmprlGrdntFltrTmp.getTemporalGradientValues(i) > tmprlGrdntFltrTmp.getTmprlGrdntThrshld()) {
        //cornerDataPixel[i] = myPixelValues[i];

        //gradientGryFilter(pixelValues, i);
        //double grdntMag = grdntFilterParser.getGrdntMgntd(i);
        
            cornerMtrx = createCornerMatrix(i);
            solveEigenValues(cornerMtrx, i);
        }
    }

    /*
    public void cornerDetect3x3In9x9(int grayValues[], int i) {
        cornerDataPixel[i] = grayValues[i];
        ySclFltrTmp.getImageDrawData().updatePixels(grayValues, i);
        if (ySclFltrTmp.isInBounds3x3(i) == true) {
            grdntFilterParser.grdntFilter9x9(grayValues, i);//NOTE: should be filter9x9???
            if (grdntFilterParser.getGrdntMgntdAvg3x3(i) > 100) {
                cornerMtrx[i] = createCornerMatrix(i);
                solveEigenValues(cornerMtrx[i], i);
            }
        }
    }*/

    /*
    public void cornerDetect3x3In27x27(int grayValues[], int i) {
        cornerDataPixel[i] = grayValues[i];
        ySclFltrTmp.getImageDrawData().updatePixels(grayValues, i);
        if (ySclFltrTmp.isInBounds3x3(i) == true) {
            grdntFilterParser.grdntFilter27x27(grayValues, i);//NOTE: should be filter9x9???
            cornerMtrx[i] = createCornerMatrix(i);
            solveEigenValues(cornerMtrx[i], i);
        }
    }

    public void cornerDetect9x9(int i) {
        cornerDetect9x9(cornerDataPixel, i);
    }

    public void cornerDetect9x9(int grayValues[], int i) {
        //System.out.println("CornerDetect: test 1: cornerData.length "+cornerData.length+", grayValues.length = "+grayValues.length+", x = "+i);
        cornerDataPixel[i] = grayValues[i];
        if (ySclFltrTmp.isInBounds3x3(i) == true) {
            grdntFilterParser.grdntFilter27x27(grayValues, i);
            cornerMtrx[i] = updtCrnrMtrx9x9(i);
            solveEigenValues(cornerMtrx[i], i);
        }
    }

    public double[][] updtCrnrMtrx9x9(int anIndex) {
        double crnrMtrxTmp[][] = new double[2][2];
        double crnrMtrx9x9[][] = new double[2][2];
        int aWidthx3 = 3 * ySclFltrTmp.getImageWidth();
        crnrMtrxTmp = createCornerMatrix(anIndex - aWidthx3 - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex - aWidthx3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex - aWidthx3 + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex + aWidthx3 - 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex + aWidthx3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
        crnrMtrxTmp = createCornerMatrix(anIndex + aWidthx3 + 3);
        crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);

        int x = ySclFltrTmp.rtrvXPstn(anIndex);
        int y = ySclFltrTmp.rtrvYPstn(anIndex);
        //System.out.println("CornerDetect: x = "+x+", y = "+y);
        //Matrix.print(crnrMtrx9x9);
        //cornerMtrx[anIndex][0][0] = crnrMtrx9x9[0][0];
        //cornerMtrx[anIndex][0][1] = crnrMtrx9x9[0][1];
        //cornerMtrx[anIndex][1][0] = crnrMtrx9x9[1][0];
        //cornerMtrx[anIndex][1][1] = crnrMtrx9x9[1][1];

        return crnrMtrx9x9;
    }*/

    public double[][] createCornerMatrix(int j) {
        double cornerMatrix[][] = new double[2][2];
        int imageWidth = ySclFltrTmp.getImageWidth();
        int imageHeight = ySclFltrTmp.getImageHeight();
        int aGradientHor[] = grdntFilterParser.getGrdntHrzntl();
        int aGradientVer[] = grdntFilterParser.getGrdntVrtcl();
        //int imageLengthI = imageWidth*imageHeight;

        //if (ySclFltrTmp.isInBounds3x3(j)) {
         try{
           gradientXSqrd =
                    aGradientHor[j - imageWidth - 1] * aGradientHor[j - imageWidth - 1]
                    + aGradientHor[j - imageWidth] * aGradientHor[j - imageWidth]
                    + aGradientHor[j - imageWidth + 1] * aGradientHor[j - imageWidth + 1]
                    + aGradientHor[j - 1] * aGradientHor[j - 1]
                    + aGradientHor[j] * aGradientHor[j]
                    + aGradientHor[j + 1] * aGradientHor[j + 1]
                    + aGradientHor[j + imageWidth - 1] * aGradientHor[j + imageWidth - 1]
                    + aGradientHor[j + imageWidth] * aGradientHor[j + imageWidth]
                    + aGradientHor[j + imageWidth + 1] * aGradientHor[j + imageWidth + 1];

            gradientYSqrd =
                    aGradientVer[j - imageWidth - 1] * aGradientVer[j - imageWidth - 1]
                    + aGradientVer[j - imageWidth] * aGradientVer[j - imageWidth]
                    + aGradientVer[j - imageWidth + 1] * aGradientVer[j - imageWidth + 1]
                    + aGradientVer[j - 1] * aGradientVer[j - 1]
                    + aGradientVer[j] * aGradientVer[j]
                    + aGradientVer[j + 1] * aGradientVer[j + 1]
                    + aGradientVer[j + imageWidth - 1] * aGradientVer[j + imageWidth - 1]
                    + aGradientVer[j + imageWidth] * aGradientVer[j + imageWidth]
                    + aGradientVer[j + imageWidth + 1] * aGradientVer[j + imageWidth + 1];

            /*
             gradientXY[j] =
             aGradientVer[j - imageWidth - 1] * aGradientHor[j - imageWidth - 1]
             + aGradientVer[j - imageWidth] * aGradientHor[j - imageWidth]
             + aGradientVer[j - imageWidth + 1] * aGradientHor[j - imageWidth + 1]
             + aGradientVer[j - 1] * aGradientHor[j - 1]
             + aGradientVer[j] * aGradientHor[j]
             + aGradientVer[j + 1] * aGradientHor[j + 1]
             + aGradientVer[j + imageWidth - 1] * aGradientHor[j + imageWidth - 1]
             + aGradientVer[j + imageWidth] * aGradientHor[j + imageWidth]
             + aGradientVer[j + imageWidth + 1] * aGradientHor[j + imageWidth + 1];

             cornerMatrix[0][1] = gradientXY[j];
             cornerMatrix[1][0] = gradientXY[j];

             */
            cornerMatrix[0][0] = gradientXSqrd;
            cornerMatrix[1][1] = gradientYSqrd;
         }catch(ArrayIndexOutOfBoundsException aiobe){
             
         }
            /*
             * System.out.println("CornerDetect: cornerMatrix[j][0]"+
             * cornerMatrix[j][0]+","+ cornerMatrix[j][1]+","+
             * cornerMatrix[j][2]+","+ cornerMatrix[j][3]);
             */
        //}
        //cornerMtrx[j][0][0] = cornerMatrix[0][0];
        //cornerMtrx[j][0][1] = cornerMatrix[0][1];
        //cornerMtrx[j][1][0] = cornerMatrix[1][0];
        //cornerMtrx[j][1][1] = cornerMatrix[1][1];

        return cornerMatrix;
    }

    /*
    public double[][][] getCornerMtrx() {
        return cornerMtrx;
    }

    public double[][] getCornerMtrx(int myIndex) {
        return cornerMtrx[myIndex];
    }*/

    /*
     * public void slvEgenVls(int i) { solveEigenValues(cornerMatrix,i); }
     * public void solveEigenValues(int i) { solveEigenValues(cornerMatrix,i);
     }
     */
    public void solveEigenValues(double myCrnrMtrx[][], int i) {
        double answer[] = solveEigenValues(myCrnrMtrx);
        //eigenValue1[i] = answer[0];
        //eigenValue2[i] = answer[1];
        double maxAnswer[] =  arrngeMaxValue(answer);
        //System.out.println("CornerDetectOptmzd: answer[1] = "+answer[1]+",i="+i+",threshold = "+threshold+",checkregion="+checkRegion);
        if (maxAnswer[1] >= threshold && !isInCornerRegion3x3(i)) {
            maxEigenValue[i] = maxAnswer[1];

            //if(getDisplayCorners()){
               updtCornerPixels(i);
            //}
            setCornerRegion(i);
            cornerCount++;
        }else{
            maxEigenValue[i] = 0;
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
        try{
            int imageWidth = ySclFltrTmp.getImageWidth();
            isCornerRegion[i - imageWidth - 1] = myTorF;
            isCornerRegion[i - imageWidth] = myTorF;
            isCornerRegion[i - imageWidth + 1] = myTorF;
            isCornerRegion[i - 1] = myTorF;
            //isCornerRegion[i] = myTorF;
            isCornerRegion[i + 1] = myTorF;
            isCornerRegion[i + imageWidth - 1] = myTorF;
            isCornerRegion[i + imageWidth] = myTorF;
            isCornerRegion[i + imageWidth + 1] = myTorF;
        }catch(ArrayIndexOutOfBoundsException aiobe){
            
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
        int imageWidth = ySclFltrTmp.getImageWidth();
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
        //if (answerTmp1 >= answerTmp2) {
        answer[0] = answerTmp1;
        answer[1] = answerTmp2;
        //} else {
        //answer[0] = answerTmp2;
        //answer[1] = answerTmp1;
        //}
        //System.out.println("answer1 = "+answer[0]+" answer2 = "+answer[1]);
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
    /*
     * public double[] solveEigenValues(double aCornerMatrix[]) { double
     * answer[] = new double[2]; double answerTmp1 = 0.0; double answerTmp2 =
     * 0.0; int secOrdPart = 1; double firstOrdPart =
     * -1*(aCornerMatrix[0]+aCornerMatrix[3]); double constPart =
     * aCornerMatrix[0]*aCornerMatrix[3] - aCornerMatrix[1]*aCornerMatrix[2];
     * double sqrtPart = Math.sqrt(firstOrdPart*firstOrdPart -
     * 4*secOrdPart*constPart); answerTmp1 = (-1*firstOrdPart +
     * sqrtPart)/(2*secOrdPart); answerTmp2 = (-1*firstOrdPart -
     * sqrtPart)/(2*secOrdPart); if(answerTmp1 >= answerTmp2) { answer[0] =
     * answerTmp1; answer[1] = answerTmp2; }else{ answer[0] = answerTmp2;
     * answer[1] = answerTmp1; } //System.out.println("answer1 = "+answer[0]+"
     * answer2 = "+answer[1]); return answer;
     }
     */
    /*
    public void updtCrnrAndOrgnlPxls(int i) {

        //crnrAndOrgnl = this.getOrgnlValues();
        if (ySclFltrTmp.isInBounds3x3(i)) {
            //crnrAndOrgnl[i-imageWidth-1] = 0x00ff0000;
            //crnrAndOrgnl[i-imageWidth] = 0x00ff0000;
            //crnrAndOrgnl[i-imageWidth+1] = 0x00ff0000;
            //crnrAndOrgnl[i-1] = 0x00ff0000;
            //crnrAndOrgnl[i] = 0x0000ff00;
            //crnrAndOrgnl[i+1] = 0x00ff0000;
            //crnrAndOrgnl[i+imageWidth-1] = 0x00ff0000;
            //crnrAndOrgnl[i+imageWidth] = 0x00ff00ff;
            //crnrAndOrgnl[i+imageWidth+1] = 0x00ff00ff;
        }
    }*/
    /*
    public void updtCornerPixels(int x,int y,int myColor) {
        int anIndex = ImageTool.rtrvIndex(x, y, ySclFltrTmp.getImageWidth());
        updtCornerPixels(cornerDataPixel, anIndex, myColor);
    }*/
    public void updtCornerPixels(int i) {
        updtCornerPixels(cornerDataPixel, i);
    }
    public void updtCornerPixels(int cornerDataPixel[], int i) {
        updtCornerPixels(cornerDataPixel,i,0x0000cc00);
    }
    public void updtCornerPixels(int cornerDataPixel[], int i,int aColor) {
        int imageWidth = ySclFltrTmp.getImageWidth();
        //System.out.println("CornerDetect:test 1: update corner index = "+i);
        try{
            //System.out.println("CornerDetectGrdnt: corner index = "+i+", aColor = "+aColor);          
            /*
             cornerDataPixel[i - imageWidth - 1] = 0x00ff0000;
             cornerDataPixel[i - imageWidth] = 0x00ff0000;
             cornerDataPixel[i - imageWidth + 1] = 0x00ff0000;
             cornerDataPixel[i - 1] = 0x00ff0000;
             cornerDataPixel[i] = 0x000000ff;
             cornerDataPixel[i + 1] = 0x00ff0000;
             cornerDataPixel[i + imageWidth - 1] = 0x00ff0000;
             cornerDataPixel[i + imageWidth] = 0x00ff0000;
             cornerDataPixel[i + imageWidth + 1] = 0x00ff0000;
            */
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i - imageWidth - 1);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i - imageWidth);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i - imageWidth + 1);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i - 1);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i + 1);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i + imageWidth - 1);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i + imageWidth);
            ySclFltrTmp.getImageDrawData().drawData(0x0000aa00, i + imageWidth + 1);
        }catch(ArrayIndexOutOfBoundsException aiobe){
            
        }
    }
    /*
    public void updtLclMax(double myValue, int myIndex) {

        int imgWidth = ySclFltrTmp.getImageWidth();
        int imgHeight = ySclFltrTmp.getImageHeight();

        int myX = ySclFltrTmp.rtrvXPstn(myIndex);
        int myY = ySclFltrTmp.rtrvYPstn(myIndex);

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
            //updtCrnrPxls(myX,myY);     
            updtCornerPixels(myIndex);
        }
    }*/

    public int getLclMaxIndex(int myIndex) {
        return lclMaxIndex[myIndex];
    }

    public double getLclMaxEgnVls(int myIndex) {
        return lclMaxEgnVls[myIndex];
    }

    /*
    public void updtCrnrPxls(int myIndex) {
        int myX = ySclFltrTmp.rtrvXPstn(myIndex);
        int myY = ySclFltrTmp.rtrvYPstn(myIndex);
        //int glblIndex = ImageTool.rtrvIndex(glblX, glblY,glblWndwWidth);   
        if (myX > 0 && ((myX) % lclWndwDmnsn == 0) && myY > 0 && ((myY) % lclWndwDmnsn == 0)) {
            int glblX = myX / lclWndwDmnsn - 1;
            int glblY = myY / lclWndwDmnsn - 1;
            int glblIndex = ImageTool.rtrvIndex(glblX, glblY, glblWndwWidth);
            //System.out.println("CornerDetect: glblX = "+glblX+", glblY="+glblY+", glblIndex = "+glblIndex+", index = "+lclMaxIndex[glblIndex]);
            updtCornerPixels(lclMaxIndex[glblIndex]);
        }
    }*/
    /*
    public boolean isLclWndwCmplte(int myIndex) {
        boolean isCmplt = false;

        int imgWidth = ySclFltrTmp.getImageWidth();
        int imgHeight = ySclFltrTmp.getImageHeight();

        int myX = ySclFltrTmp.rtrvXPstn(myIndex);
        int myY = ySclFltrTmp.rtrvYPstn(myIndex);
        if (myX % lclWndwDmnsn == 0 && myY % lclWndwDmnsn == 0) {
            isCmplt = true;
        }
        return isCmplt;
    }*/
    /*
    public int getLclWndwIndx(int myIndex) {
        int myX = ySclFltrTmp.rtrvXPstn(myIndex);
        int myY = ySclFltrTmp.rtrvYPstn(myIndex);

        int glblX = myX / lclWndwDmnsn;
        int glblY = myY / lclWndwDmnsn;
        int glblIndex = ImageTool.rtrvIndex(glblX, glblY, glblWndwWidth);
        return lclMaxIndex[glblIndex];
    }*/
    
    /*
     * public void finishPrsng() { prvsEigenValues = getPrvsEigenValues();
     * setPrvsCrnrCount(0);
     }
     */

    public TmprlGrdntFltrTmp getTmprlGrdntFltrTmp() {
        return tmprlGrdntFltrTmp;
    }

    public void setTmprlGrdntFltrTmp(TmprlGrdntFltrTmp myTmprlGrdntFltrTmp) {
        this.tmprlGrdntFltrTmp = myTmprlGrdntFltrTmp;
    }

    public YSclFltrTmp getySclFltrTmp() {
        return ySclFltrTmp;
    }

    public void setySclFltrTmp(YSclFltrTmp myYSclFltrTmp) {
        this.ySclFltrTmp = myYSclFltrTmp;
    }

    public GrdntFilterParserTmp getGrdntFilterParser() {
        return grdntFilterParser;
    }

    public void setGrdntFilterParser(GrdntFilterParserTmp myGrdntFilterParser) {
        this.grdntFilterParser = myGrdntFilterParser;
    }
}