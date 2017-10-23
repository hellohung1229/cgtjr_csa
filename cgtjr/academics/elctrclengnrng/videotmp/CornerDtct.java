package cgtjr.academics.elctrclengnrng.videotmp;

import cgtjr.academics.elctrclengnrng.video.shapepnt.TrckrBndry;
import cgtjr.academics.elctrclengnrng.video.shapepnt.HmnCmptrDcsnBndry;
import java.awt.*;
import java.util.*;

public class CornerDtct extends GrdntFilter {

    private int gradientXSqrd[];
    private int gradientYSqrd[];
    private int gradientXY[];
    private int greyStructure[][];
    private int crnrAndOrgnl[];
    private int cornerData[];
    private Vector aVector1 = null;
    private Vector aVector2 = null;
    private Vector aVector3 = null;
    private Vector eigenValues1 = null;
    private Vector eigenValues2 = null;
    private Vector coordIndexValue = null;
    //private Component imageComponent;
    private final int neighborSize = 3;
    private int minHeight = 0;
    private int maxHeight = 0;
    //private int minWidth = 0;
    //private int maxWidth = 0;
    private int cornerDispersion = 3;
    private int threshold = 1000;
    int neighborHood[][];
    int lambda1 = 0;
    int lambda2 = 0;
    int tau = 0;
    double cornerMatrix[][];
    private Vector eigenVector1 = null;
    private Vector eigenVector2 = null;
    private Vector indexVector = null;
    private Vector eigenVector[] = null;
    private double answer[] = null;
    //private Object anObject[] = new Object[3];
    //private static HmnCmptrDcsnBndry trackerBndry;
    //private double eigenValues[];

    public void initialize(int myImageWidth, int myImageHeight) {
        //super(new SobelKernel());
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        cornerMatrix = new double[aLength][4];
        cornerData = new int[aLength];
        crnrAndOrgnl = new int[aLength];
        gradientXSqrd = new int[aLength];
        gradientYSqrd = new int[aLength];
        gradientXY = new int[aLength];
        //eigenVector = new Vector[3];

        /*
         * if(trackerBndry == null) { trackerBndry = new HmnCmptrDcsnBndry();
      }
         */

    }

    public void start(int myImageWidth, int myImageHeight) {
        //super(new SobelKernel());
        super.start(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        cornerMatrix = new double[aLength][4];
        cornerData = new int[aLength];
        crnrAndOrgnl = new int[aLength];
        gradientXSqrd = new int[aLength];
        gradientYSqrd = new int[aLength];
        gradientXY = new int[aLength];
        //eigenVector = new Vector[3];

    }

    public void filter(int myGryVls[], int i) {
        super.filter(myGryVls, i);
        cornerDetect(myGryVls, i);
    }
    /*
     * public void updateTrckrInfo(int i) { int x = rtrvXPstn(i); int y =
     * rtrvYPstn(i); trackerBndry.updtBndry(x, y, 0);
   }
     */

    public void crnrDtctFilter(int grayValues[], int i) {
        cornerDetect(grayValues, i);
        //buildCornerMatrix(int aGradientHor[],int aGradientVer[],int i);      
    }

    public Image cornerDetect(int grayValues[], int i) {
        int imageWidth = getImageWidth();
        int imageHeight = getImageHeight();
        int aGradientHor[] = this.getGrdntHrzntl();
        int aGradientVer[] = this.getGrdntVrtcl();
        this.crnrAndOrgnl[i] = grayValues[i];
        int j = 0;
        //for(int i=aMinHeight;i< imageLength-aMaxHeight ;i++)
        //{
        if (i - imageWidth - 1 >= 0) {
            j = i - imageWidth - 1;
            updateCornerMatrix(j);
            solveEigenValues(j);
        }
        return null;
    }

    public Vector retrieveEigenValues1() {
        return eigenValues1;
    }

    public Vector retrieveEigenValues2() {
        return eigenValues2;
    }

    public void updateCornerMatrix(int j) {
        int imageWidth = getImageWidth();
        int imageHeight = getImageHeight();
        int aGradientHor[] = this.getGrdntHrzntl();
        int aGradientVer[] = this.getGrdntVrtcl();
        int imageLength = imageWidth * imageHeight;
        int aMinHeight = minHeight * imageWidth;
        int aMaxHeight = maxHeight * imageWidth;

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

            cornerMatrix[j][0] = gradientXSqrd[j];
            cornerMatrix[j][1] = gradientXY[j];
            cornerMatrix[j][2] = gradientXY[j];
            cornerMatrix[j][3] = gradientYSqrd[j];
            /*
             * System.out.println("CornerDetect: cornerMatrix[j][0]"+
             * cornerMatrix[j][0]+","+ cornerMatrix[j][1]+","+
             * cornerMatrix[j][2]+","+ cornerMatrix[j][3]);
             */
        }
    }

    public void solveEigenValues(int i) {
        double answer[] = solveEigenValues(cornerMatrix[i]);
        //System.out.println("CornerDetect: answer[1] = "+answer[1]+",threshold = "+threshold);
        if (answer[1] >= threshold) {
            //updateTrckrInfo(i);
            //anObject = insertSort(answer[0],answer[1],i);
            //eigenVector[0] = (Vector)anObject[0];
            //eigenVector[1] = (Vector)anObject[1];
            //eigenVector[2] = (Vector)anObject[2];
            //isCorner[i] = true;
            updtCrnrAndOrgnlPxls(i);
            //updtCornerPixels(i);
        }

    }

    public double[] solveEigenValues(double aCornerMatrix[]) {
        double answer[] = new double[2];
        double answerTmp1 = 0.0;
        double answerTmp2 = 0.0;
        int secOrdPart = 1;
        double firstOrdPart = -1 * (aCornerMatrix[0] + aCornerMatrix[3]);
        double constPart = aCornerMatrix[0] * aCornerMatrix[3] - aCornerMatrix[1] * aCornerMatrix[2];
        double sqrtPart = Math.sqrt(firstOrdPart * firstOrdPart - 4 * secOrdPart * constPart);
        answerTmp1 = (-1 * firstOrdPart + sqrtPart) / (2 * secOrdPart);
        answerTmp2 = (-1 * firstOrdPart - sqrtPart) / (2 * secOrdPart);
        if (answerTmp1 >= answerTmp2) {
            answer[0] = answerTmp1;
            answer[1] = answerTmp2;
        } else {
            answer[0] = answerTmp2;
            answer[1] = answerTmp1;
        }
        //System.out.println("answer1 = "+answer[0]+" answer2 = "+answer[1]);
        return answer;
    }

    public void updtCrnrAndOrgnlPxls(int i) {
        updtCrnrAndOrgnlPxls(i, 0x00e4e4e4);
    }

    public void updtCrnrAndOrgnlPxls(int i, int myColor) {
        int imageWidth = getImageWidth();
        //crnrAndOrgnl = this.getOrgnlValues();
        if (isInBounds3x3(i)) {
            crnrAndOrgnl[i - imageWidth - 1] = myColor;
            crnrAndOrgnl[i - imageWidth] = myColor;
            crnrAndOrgnl[i - imageWidth + 1] = myColor;
            crnrAndOrgnl[i - 1] = myColor;
            //crnrAndOrgnl[i] = 0x00ff0000;
            crnrAndOrgnl[i + 1] = myColor;
            crnrAndOrgnl[i + imageWidth - 1] = myColor;
            crnrAndOrgnl[i + imageWidth] = myColor;
            crnrAndOrgnl[i + imageWidth + 1] = myColor;
        }
    }

    public void updtCornerPixels(int i) {
        int imageWidth = getImageWidth();
        if (isInBounds3x3(i)) {
            cornerData[i - imageWidth - 1] = 0x00ff0000;
            cornerData[i - imageWidth] = 0x00ff0000;
            cornerData[i - imageWidth + 1] = 0x00ff0000;
            cornerData[i - 1] = 0x00ff0000;
            //cornerData[i] = 0x00ff0000;
            cornerData[i + 1] = 0x00ff0000;
            cornerData[i + imageWidth - 1] = 0x00ff0000;
            cornerData[i + imageWidth] = 0x00ff0000;
            cornerData[i + imageWidth + 1] = 0x00ff0000;
        }
    }

    public void setThreshold(int myThreshold) {
        threshold = myThreshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public double[][] getCornerMatrix() {
        return this.cornerMatrix;
    }

    public Object[] insertSort(double myValue1, double myValue2, int myIndex) {
        Object anObject[] = new Object[3];

        double aValue = 0.0;
        Double aDoubleValue = null;
        //Double aDoubleValue = null;
        if (aVector1 == null) {
            aVector1 = new Vector();
            aVector2 = new Vector();
            aVector3 = new Vector();
            aVector1.add(new Double(myValue1));
            aVector2.add(new Double(myValue2));
            aVector3.add(new Integer(0));
            anObject[0] = aVector1;
            anObject[1] = aVector2;
            anObject[2] = aVector3;
            return anObject;
        }
        int aSize = aVector2.size();
        for (int i = 0; i < aSize; i++) {
            aDoubleValue = (Double) aVector2.elementAt(i);
            //aDoubleValue = (Double)myVector1.elementAt(i);
            aValue = aDoubleValue.doubleValue();
            if (myValue2 >= aValue) {
                aVector1.insertElementAt(new Double(myValue1), i);
                aVector2.insertElementAt(new Double(myValue2), i);
                aVector3.insertElementAt(new Integer(myIndex), i);
                //System.out.println("value = "+myValue+" index = "+myIndex);
                anObject[0] = aVector1;
                anObject[1] = aVector2;
                anObject[2] = aVector3;
                return anObject;
            }
        }
        anObject[0] = aVector1;
        anObject[1] = aVector2;
        anObject[2] = aVector3;
        return anObject;
    }

    public int[] getFltrdData() {
        return crnrAndOrgnl;
    }

    public int[] getCornerImage() {
        return crnrAndOrgnl;
    }

    public void finish() {
        super.finish();
        //double aCenter[] = trackerBndry.cmptCenter();
        /*
         * int xMax = (int)trackerBndry.getXMax(); int yMax =
         * (int)trackerBndry.getYMax(); int xMin = (int)trackerBndry.getXMin();
         * int yMin = (int)trackerBndry.getYMin();
         *
         * int xMaxyMaxIndex = rtrvIndex(xMax, yMax); int xMinyMaxIndex =
         * rtrvIndex(xMin, yMax); int xMaxyMinIndex = rtrvIndex(xMax, yMin); int
         * xMinyMinIndex = rtrvIndex(xMin, yMin); int xCntryCntrIndex =
         * rtrvIndex(xCntr,yCntr);
         * updtCrnrAndOrgnlPxls(xMaxyMaxIndex,0x0000ff00);
         * updtCrnrAndOrgnlPxls(xMinyMaxIndex,0x0000ff00);
         * updtCrnrAndOrgnlPxls(xMaxyMinIndex,0x0000ff00);
         * updtCrnrAndOrgnlPxls(xMinyMinIndex,0x0000ff00);
         * updtCrnrAndOrgnlPxls(xCntryCntrIndex,0x0000ff00);
         */
        //int xCntr = (int) aCenter[0];
        //int yCntr = (int) aCenter[1];
        /*
        int xCntryCntrIndex = rtrvIndex(xCntr, yCntr);
        
        if (isInBounds3x3(xCntryCntrIndex)) {
            trackerBndry.drawBndry(crnrAndOrgnl, getImageWidth(), getImageHeight(), 0x00ffff00);
        }

        trackerBndry.setXPos2(xCntr);
        trackerBndry.setYPos2(yCntr);
        trackerBndry.setTime2();
        double vlcty[] = trackerBndry.cmptVlcty();
        trackerBndry = new HmnCmptrDcsnBndry();
        trackerBndry.setXPos1(xCntr);
        trackerBndry.setYPos1(yCntr);
        trackerBndry.setTime1();
        * 
        */
        //String info = "xmin = "+xMin+", xmax = "+xMax+", ymin = "+yMin+", ymax = "+yMax+", x center = "+aCenter[0]+", y center = "+aCenter[1]+", x vel.="+(int)vlcty[0]+", y vel.="+(int)vlcty[1];
        //TrckrTxtFld.setText(info);
    }
}