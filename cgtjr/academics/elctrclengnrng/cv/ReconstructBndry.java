/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.BoxBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.lnralgbra.BckwrdSub;
import cgtjr.academics.math.lnralgbra.GssnElmntn;
import cgtjr.academics.math.lnralgbra.Matrix;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author clayton g thomas jr
 */
public class ReconstructBndry {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int numberOfPoints1;
    private int numberOfPoints2;
    private int numberOfPoints3;
    private int numberOfPoints4;
    private int numberOfPoints5;
    private int numberOfPoints6;
    private int numberOfPoints7;
    private int numberOfPoints8;
    private double matrixA1[][];
    private double matrixA2[][];
    private double matrixA3[][];
    private double matrixA4[][];
    private double matrixA5[][];
    private double matrixA6[][];
    private double matrixA7[][];
    private double matrixA8[][];
    private double matrixANx9[][];
    private double matrixA8x8[][];
    private final double oneVec[] = {-1, -1, -1, -1, -1, -1, -1, -1};
    private HOGPosition matchHOGPosition1;
    private HOGPosition matchHOGPosition2;
    private HOGPosition matchHOGPosition3;
    private HOGPosition matchHOGPosition4;
    private HOGPosition matchHOGPosition5;
    private HOGPosition matchHOGPosition6;
    private HOGPosition matchHOGPosition7;
    private HOGPosition matchHOGPosition8;
    private HOGPosition matchHOGPositionTmp;
    private HOGPosition matchHOGPosition[];
    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;
    private boolean shouldDraw;
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;
    private HashMap hogMatchHashMap;
    private ImageDrawData imagePixelData;
    private int imageWidth;
    private int imageHeight;
    private int rowIndex;
    private double clbrtnMatrix[][];
    private double wrldC1[];
    private double wrldC2[];
    private double wrldC3[];
    private double wrldC4[];
    private double wrldC5[];
    private double wrldC6[];
    private double wrldC7[];
    private double wrldC8[];
    private ArrayList wrldCrdnts;
    private BoxBndry aBoxBndry;

    public ReconstructBndry(int myImageWidth, int myImageHeight) {
        //Consider creating a constructor: ReconstructBndry(cameraMatrix); therefore pass width and height as parameter
        imageWidth = myImageWidth;
        imageHeight = myImageHeight;

        matrixA1 = new double[1][9];
        matrixA2 = new double[1][9];
        matrixA3 = new double[1][9];
        matrixA4 = new double[1][9];
        matrixA5 = new double[1][9];
        matrixA6 = new double[1][9];
        matrixA7 = new double[1][9];
        matrixA8 = new double[1][9];

        matrixANx9 = new double[8][9];
        matrixA8x8 = new double[8][8];

        wrldCrdnts = new ArrayList();
        matchHOGPosition = new HOGPosition[8];

    }

    public ImageDrawData getImagePixelData() {
        return imagePixelData;
    }

    public void setImagePixelData(ImageDrawData myImagePixelData) {
        this.imagePixelData = myImagePixelData;
    }

    public boolean isInQuadrant(HOGPosition myHOGPosition, int myXMin, int myXMax, int myYMin, int myYMax, int myQuadNmbr) {
        boolean isInQuad = false;
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        int aWidth = myXMax - myXMax;
        int aHeight = myYMax - myYMin;
        int xMid = myXMin + aWidth / 2;
        int yMid = myYMin + aHeight / 2;

        if (myQuadNmbr == 1 && aX >= myXMin && aX <= xMid && aY >= myYMin && aY <= yMid) {
            isInQuad = true;
        } else if (myQuadNmbr == 2 && aX >= xMid && aX <= myXMax && aY >= myYMin && aY <= yMid) {
            isInQuad = true;
        } else if (myQuadNmbr == 3 && aX >= myXMin && aX <= xMid && aY >= yMid && aY <= myYMax) {
            isInQuad = true;
        } else if (myQuadNmbr == 4 && aX >= xMid && aX <= myXMax && aY >= yMid && aY <= myYMax) {
            isInQuad = true;
        }
        return isInQuad;
    }

    public void computePrjctveTrnsfrmtn(RectBndryHOGArrayList myRectBndryHOGArrayList, double myClbrtnMtrx[][]) {
        int aXMin = myRectBndryHOGArrayList.getXMin();
        int aXMax = myRectBndryHOGArrayList.getXMax();
        int aYMin = myRectBndryHOGArrayList.getYMin();
        int aYMax = myRectBndryHOGArrayList.getYMax();
        System.out.println("ReconstructBndry : size = " + myRectBndryHOGArrayList.size());
        int aSize = myRectBndryHOGArrayList.size();
        for (int i = 0; i < aSize; i++) {
            System.out.println("ReconstructBndry i = " + i);
            HOGPosition myHOGPosition = (HOGPosition) myRectBndryHOGArrayList.get(i);
            computePrjctveTrnsfrmtn(myHOGPosition, aXMin, aXMax, aYMin, aYMax, myClbrtnMtrx);
        }
        rowIndex = 0;
    }

    private void computePrjctveTrnsfrmtn(HOGPosition myHOGPosition, int myXMin, int myXMax, int myYMin, int myYMax, double myClbrtnMtrx[][]) {
        int anObjectWidth = myXMax - myXMin;
        int anObjectHeight = myYMax - myYMin;
        int x1 = anObjectWidth / 2;
        int y1 = anObjectHeight / 2;
        int x2 = 3 * anObjectWidth / 2;
        int y2 = 3 * anObjectHeight / 2;
        int xc = myXMin + (myXMax - myXMin) / 2;
        int yc = myYMin + (myYMax - myYMin) / 2;
        //clbrtnMatrix = myClbrtnMtrx;

        HOGPosition hogMatch = myHOGPosition.rtrveHOGMatch();
        if (hogMatch == null) {

            System.out.println("REconstructBndry ... NULL !!!");
            //return;
        }
        System.out.println("ReconstructBndry: myXMin = "+myXMin+", myXMax = "+myXMax+", myYMin = "+myYMin+", myYMax = "+myYMax);
        if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 1)) && numberOfPoints1 == 0) {

            matchHOGPosition[rowIndex] = myHOGPosition;
            matchHOGPosition1 = myHOGPosition;
            matrixA1 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA1, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA1, rowIndex);

            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            rowIndex++;
            numberOfPoints1++;
            System.out.println("FndmntlMatrixDLT: test1");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 2)) && numberOfPoints2 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matchHOGPosition2 = myHOGPosition;
            matrixA2 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA2, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA2, rowIndex);
            //updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            numberOfPoints2++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test2");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 1)) && numberOfPoints3 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matchHOGPosition3 = myHOGPosition;
            matrixA3 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA3, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA3, rowIndex);
            //updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;

            numberOfPoints3++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test3");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 2)) && numberOfPoints4 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA4 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA4, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA4, rowIndex);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;

            numberOfPoints4++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test4");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 3)) && numberOfPoints5 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA5 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA5, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA5, rowIndex);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;

            numberOfPoints5++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test5");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 4)) && numberOfPoints6 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA6 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA6, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA6, rowIndex);
            //updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            numberOfPoints6++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test6");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 3)) && numberOfPoints7 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA7 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA7, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA7, rowIndex);
            //updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            numberOfPoints7++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test7");
        } else if ((isInQuadrant(myHOGPosition, myXMin, myXMax, myYMin, myYMax, 4)) && numberOfPoints8 == 0) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA8 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA8, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA8, rowIndex);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            numberOfPoints8++;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test8");
        } else if (rowIndex <= 7) {
            matchHOGPosition[rowIndex] = myHOGPosition;
            matrixA8 = rtrveMatrix1x9(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            matrixANx9 = Matrix.insertRowNx9(matrixANx9, matrixA8, rowIndex);
            matrixA8x8 = Matrix.insertRowNx8(matrixA8x8, matrixA8, rowIndex);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition[rowIndex], matchHOGPosition[rowIndex].rtrveHOGMatch());
            shouldDraw = false;
            rowIndex++;
            System.out.println("FndmntlMatrixDLT: test9");
        }
        System.out.println("ReconstructBndry : rowIndex  = " + rowIndex);
        if (rowIndex > 7) {
            System.out.println("ReconstructBndry ... ");
            //Matrix.print(matrixA8x8);
            //double aFndmntl[] = computeFundamental(matrixANx9);
            double aFndmntl[] = computeFundamental2(matrixA8x8, oneVec);
            Matrix.print(aFndmntl);

            double aFndmntl3x3[][] = Matrix.convert9x1To3x3Add1(aFndmntl);
            double anEssntl[][] = cmpteEssntlMtrx(aFndmntl3x3, myClbrtnMtrx);
            //double calibrationMatrix[][] =  
            //Matrix.print(aFndmntl3x3);
            
            chckEpplrCnstrnt(aFndmntl3x3);
            
            //System.exit(0);
            
            double anEpipole[] = computeEpipole(aFndmntl3x3);
            //Matrix.print(anEpipole);

            double anEpipoleN[] = Matrix.scale3x1(anEpipole, 1.0 / anEpipole[2]);
            System.out.println("ReconstructionBndry : e1 = " + anEpipole[0] + "," + anEpipole[1] + "," + anEpipole[2]);
            System.out.println("ReconstructionBndry : normalized e1 = " + anEpipoleN[0] + "," + anEpipoleN[1] + "," + anEpipoleN[2]);
            double aCamera1[][] = computeCamera1();
            //double aCamera2[][] = computeCamera2(aFndmntl3x3,anEpipole);
            double aCamera2[][] = computeCamera2(anEssntl, anEpipole);

            double anImgPnt1_1[] = {matchHOGPosition[0].getX(), matchHOGPosition[0].getY(), 1};
            double anImgPnt1_2[] = {matchHOGPosition[0].rtrveHOGMatch().getX(), matchHOGPosition[0].rtrveHOGMatch().getY(), 1};
            wrldC1 = cmpteWrldCrdnte(anImgPnt1_1, anImgPnt1_2, aCamera1, aCamera2);
            double anImgPnt2_1[] = {matchHOGPosition[1].getX(), matchHOGPosition[1].getY(), 1};
            double anImgPnt2_2[] = {matchHOGPosition[1].rtrveHOGMatch().getX(), matchHOGPosition[1].rtrveHOGMatch().getY(), 1};
            wrldC2 = cmpteWrldCrdnte(anImgPnt2_1, anImgPnt2_2, aCamera1, aCamera2);
            double anImgPnt3_1[] = {matchHOGPosition[2].getX(), matchHOGPosition[2].getY(), 1};
            double anImgPnt3_2[] = {matchHOGPosition[2].rtrveHOGMatch().getX(), matchHOGPosition[2].rtrveHOGMatch().getY(), 1};
            wrldC3 = cmpteWrldCrdnte(anImgPnt3_1, anImgPnt3_2, aCamera1, aCamera2);
            double anImgPnt4_1[] = {matchHOGPosition[3].getX(), matchHOGPosition[3].getY(), 1};
            double anImgPnt4_2[] = {matchHOGPosition[3].rtrveHOGMatch().getX(), matchHOGPosition[3].rtrveHOGMatch().getY(), 1};
            wrldC4 = cmpteWrldCrdnte(anImgPnt4_1, anImgPnt4_2, aCamera1, aCamera2);
            double anImgPnt5_1[] = {matchHOGPosition[4].getX(), matchHOGPosition[4].getY(), 1};
            double anImgPnt5_2[] = {matchHOGPosition[4].rtrveHOGMatch().getX(), matchHOGPosition[4].rtrveHOGMatch().getY(), 1};
            wrldC5 = cmpteWrldCrdnte(anImgPnt4_1, anImgPnt4_2, aCamera1, aCamera2);
            double anImgPnt6_1[] = {matchHOGPosition[5].getX(), matchHOGPosition[5].getY(), 1};
            double anImgPnt6_2[] = {matchHOGPosition[5].rtrveHOGMatch().getX(), matchHOGPosition[5].rtrveHOGMatch().getY(), 1};
            wrldC6 = cmpteWrldCrdnte(anImgPnt6_1, anImgPnt6_2, aCamera1, aCamera2);
            double anImgPnt7_1[] = {matchHOGPosition[6].getX(), matchHOGPosition[6].getY(), 1};
            double anImgPnt7_2[] = {matchHOGPosition[6].rtrveHOGMatch().getX(), matchHOGPosition[6].rtrveHOGMatch().getY(), 1};
            wrldC7 = cmpteWrldCrdnte(anImgPnt7_1, anImgPnt7_2, aCamera1, aCamera2);
            double anImgPnt8_1[] = {matchHOGPosition[7].getX(), matchHOGPosition[7].getY(), 1};
            double anImgPnt8_2[] = {matchHOGPosition[7].rtrveHOGMatch().getX(), matchHOGPosition[7].rtrveHOGMatch().getY(), 1};
            wrldC8 = cmpteWrldCrdnte(anImgPnt8_1, anImgPnt8_2, aCamera1, aCamera2);

            wrldCrdnts.add(wrldC1);
            wrldCrdnts.add(wrldC2);
            wrldCrdnts.add(wrldC3);
            wrldCrdnts.add(wrldC4);
            wrldCrdnts.add(wrldC5);
            wrldCrdnts.add(wrldC6);
            wrldCrdnts.add(wrldC7);
            wrldCrdnts.add(wrldC8);

            System.out.println("ReconstructBndry : test 10");
            aBoxBndry = new BoxBndry();
            aBoxBndry.updateBndry(wrldCrdnts);

        }
        return;
    }

    private double[] computeFundamental(double myMatrix[][]) {
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();
        double matrixC[][] = aGssnElmntn.process(myMatrix);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);
        return x;
    }

    private double[] computeFundamental2(double myMatrix[][], double myVec[]) {

        System.out.println("ReconstructBndry.computeFundamental2() ... matrix");
        //Matrix.print(myMatrix);
        System.out.println("ReconstructBndry.computeFundamental2() ... vector");
        //Matrix.print(myVec);
        double aMatrixI[][] = Matrix.inverse(myMatrix);
        System.out.println("ReconstructBndry.computeFundamental2() ... inverse matrix");
        //Matrix.print(aMatrixI);

        double aSolution[] = Matrix.mltply8x8x8x1(aMatrixI, myVec);
        System.out.println("ReconstructBndry.computeFundamental2() ... solution");
        //Matrix.print(aSolution);
        return aSolution;
    }

    private double[] computeEpipole(double myFndmntl[][]) {
        double aFndmntlT[][] = Matrix.transpose(myFndmntl);
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();
        //Matrix.print(aFndmntlT);
        double matrixC[][] = aGssnElmntn.process(aFndmntlT);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);
        return x;
        //Matrix.print(x);        
    }

    private double[][] computeCamera1() {
        double IMatrix[][] = Matrix.I3x3();
        double aCameraMatrix[][] = Matrix.concatenate3x3To3x1(IMatrix, new double[3]);
        return aCameraMatrix;
    }

    private double[][] computeCamera2(double myFndmntl[][], double myEpple[]) {
        double aSkew[][] = Matrix.createSkewMatrix3x3(myEpple);
        double aMatrix[][] = Matrix.multiply3x3(aSkew, myFndmntl);
        double aCameraMatrix[][] = Matrix.concatenate3x3To3x1(aMatrix, myEpple);
        return aCameraMatrix;
    }

    private double[][] cmpteTrngltnMtrx(double myImgPnt1[], double myImgPnt2[], double myCmraMtrx1[][], double myCmraMtrx2[][]) {

        double aMatrixA[][] = new double[4][4];
        double b1[] = Matrix.scale1x4(myCmraMtrx1[2], myImgPnt1[0]);
        aMatrixA[0] = Matrix.subtract4x1(b1, myCmraMtrx1[0]);
        double b2[] = Matrix.scale1x4(myCmraMtrx1[2], myImgPnt1[1]);
        aMatrixA[1] = Matrix.subtract4x1(b2, myCmraMtrx1[1]);

        double c1[] = Matrix.scale1x4(myCmraMtrx2[2], myImgPnt2[0]);
        aMatrixA[2] = Matrix.subtract4x1(c1, myCmraMtrx2[0]);
        double c2[] = Matrix.scale1x4(myCmraMtrx1[2], myImgPnt1[1]);
        aMatrixA[3] = Matrix.subtract4x1(c2, myCmraMtrx2[1]);

        aMatrixA[0][3] = -1 * aMatrixA[0][3];
        aMatrixA[1][3] = -1 * aMatrixA[1][3];
        aMatrixA[2][3] = -1 * aMatrixA[2][3];
        aMatrixA[3][3] = -1 * aMatrixA[3][3];

        return aMatrixA;
    }

    public double[] cmpteWrldCrdnte(double myImgPnt1[], double myImgPnt2[], double myCmraMtrx1[][], double myCmraMtrx2[][]) {
        double aTrngleMatrix[][] = cmpteTrngltnMtrx(myImgPnt1, myImgPnt2, myCmraMtrx1, myCmraMtrx2);
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();
        //Matrix.print(aTrngleMatrix);
        double matrixC[][] = aGssnElmntn.process(aTrngleMatrix);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);
        //Matrix.print(x); 
        return x;
    }

    private double[][] rtrveMatrix1x9(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[1][9];
        int w = 1;

        HOGPosition aHOGPosition1 = myHOGPosition1;
        HOGPosition aHOGPosition2 = myHOGPosition2;

        if (aHOGPosition1 == null) {
            System.out.println("aHOGPosition1 is null");
        }
        if (aHOGPosition2 == null) {
            System.out.println("aHOGPosition2 is null");
        }

        int x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        int y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());

        //System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        aMatrixA[0][0] = x2 * x1;
        aMatrixA[0][1] = x2 * y1;
        aMatrixA[0][2] = x2;
        aMatrixA[0][3] = y2 * x1;
        aMatrixA[0][4] = y2 * y1;
        aMatrixA[0][5] = y2;
        aMatrixA[0][6] = x1;
        aMatrixA[0][7] = y1;
        aMatrixA[0][8] = -1;

        return aMatrixA;
    }

    public double[][] getMatrixA(HashMap myHashMap) {
        double aMatrixA[][] = new double[2][9];
        int w = 1;

        Set aKeyset = myHashMap.keySet();
        Iterator anIterator = aKeyset.iterator();
        if (anIterator.hasNext()) {
            HOGPosition aHOGPosition1 = (HOGPosition) anIterator.next();
            HOGPosition aHOGPosition2 = (HOGPosition) myHashMap.get(aHOGPosition1);
            int x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
            int y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
            int x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
            int y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
            aMatrixA[0][0] = 0;
            aMatrixA[0][1] = 0;
            aMatrixA[0][2] = 0;
            aMatrixA[0][3] = -w * x1;
            aMatrixA[0][4] = -w * y1;
            aMatrixA[0][5] = -w * w;
            aMatrixA[0][6] = y2 * x1;
            aMatrixA[0][7] = y2 * y1;
            aMatrixA[0][8] = y2 * w;
            aMatrixA[1][0] = w * x1;
            aMatrixA[1][1] = w * y1;
            aMatrixA[1][2] = w * w;
            aMatrixA[1][3] = 0;
            aMatrixA[1][4] = 0;
            aMatrixA[1][5] = 0;
            aMatrixA[1][6] = -x2 * x1;
            aMatrixA[1][7] = -x2 * y1;
            aMatrixA[1][8] = -x2 * w;
        }
        return aMatrixA;
    }

    public double[][] cmpteEssntlMtrx(double myFndmntlMtrx[][], double myClbrtnMtrx[][]) {
        double aClbrtnMtrxT[][] = Matrix.transpose3x3(myClbrtnMtrx);
        double aFndmntlClbrtnMtrx[][] = Matrix.multiply3x3(myFndmntlMtrx, myClbrtnMtrx);
        double aClbrtnFndmntlClbrtn[][] = Matrix.multiply3x3(aClbrtnMtrxT, aFndmntlClbrtnMtrx);
        return aClbrtnFndmntlClbrtn;

    }

    public double getDistance(HOGPosition myHOGPosition, int myX, int myY) {
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        float aDistance = PntTool.getDistance(aX, aY, myX, myY);
        return aDistance;
    }

    public void chckEpplrCnstrnt(double myFndmntl[][]) {
        for (int i = 0; i < matrixA8x8.length; i++) {
            double myPnt1[] = {matrixANx9[i][6], matrixANx9[i][7], 1};
            double myPnt2[] = {matrixANx9[i][2], matrixANx9[i][5], 1};
            double aSolution = chckEpplrCnstrnt(myPnt1, myFndmntl, myPnt2);
            System.out.println("ReconstructBndry: check epipole, x = ("+matrixANx9[i][6]+","+matrixANx9[i][7]+"), x' = ("+matrixANx9[i][2]+","+matrixANx9[i][5]+"), x'^TFx = "+aSolution);
        }
        //return aSolution;
    }

    public double chckEpplrCnstrnt(double myPnt1[], double myFndmntl[][], double myPnt2[]) {
        double sltn1[] = Matrix.mltply3x3x3x1(myFndmntl, myPnt1);
        double sltn2 = Matrix.mltply3x1x3x1(myPnt2, sltn1);
        return sltn2;
    }

    public void drawHOGArrow(HOGPosition matchedHOGPosition, HOGPosition myHOGPosition) {
        if (shouldDraw == true) {
            drawArrow(matchedHOGPosition, myHOGPosition);
        }
    }

    public int getXMax() {
        return xMax;
    }

    public void setXMax(int myXMax) {
        this.xMax = myXMax;
    }

    public int getXMin() {
        return xMin;
    }

    public void setXMin(int xMin) {
        this.xMin = xMin;
    }

    public int getYMax() {
        return yMax;
    }

    public void setYMax(int myYMax) {
        this.yMax = myYMax;
    }

    public int getYMin() {
        return yMin;
    }

    public void setYMin(int myYMin) {
        this.yMin = myYMin;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public double[] getWrldC1() {
        return wrldC1;
    }

    public double[] getWrldC2() {
        return wrldC2;
    }

    public double[] getWrldC3() {
        return wrldC3;
    }

    public double[] getWrldC4() {
        return wrldC4;
    }

    public double[] getWrldC5() {
        return wrldC5;
    }

    public double[] getWrldC6() {
        return wrldC6;
    }

    public double[] getWrldC7() {
        return wrldC7;
    }

    public double[] getWrldC8() {
        return wrldC8;
    }

    public ArrayList rtrveWrldCrdnts() {
        return wrldCrdnts;
    }

    public CubeCreator rtrve3DBox() {
        if (aBoxBndry != null) {
            return aBoxBndry.rtrveCube();
        } else {
            return null;
        }
    }

    public void drawArrow(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();
        int anIndex1 = myHOGPosition1.getPositionIndex();
        int anIndex2 = myHOGPosition2.getPositionIndex();
        int x1 = ImageTool.rtrvXPstn(anIndex1, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex1, anImageWidth);
        int x2 = ImageTool.rtrvXPstn(anIndex2, anImageWidth);
        int y2 = ImageTool.rtrvYPstn(anIndex2, anImageWidth);

        int myPixelData[] = imagePixelData.getImagePixels();

        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x00ff0000, 0x00ff0000);

    }
}