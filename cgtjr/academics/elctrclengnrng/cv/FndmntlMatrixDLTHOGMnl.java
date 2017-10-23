/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.cv.transforms.ManualCrrspndnce;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.lnralgbra.BckwrdSub;
import cgtjr.academics.math.lnralgbra.GssnElmntn;
import cgtjr.academics.math.lnralgbra.Matrix;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author clayton g thomas jr
 */
public class FndmntlMatrixDLTHOGMnl extends HOGCornerDetectOptmzd {

    private double matrixANx9[][];
    private HOGPosition matchHOGPosition1;
    private HOGPosition matchHOGPosition2;
    private HOGPosition matchHOGPosition3;
    private HOGPosition matchHOGPosition4;
    private HOGPosition matchHOGPosition5;
    private HOGPosition matchHOGPosition6;
    private HOGPosition matchHOGPosition7;
    private HOGPosition matchHOGPosition8;
    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;

    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    private HashMap hogMatchHashMap;
    private ImageDrawData imageDrawPixel;
    private ManualCrrspndnce aManualCrrspndnce;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        xMin = 0;
        yMin = 0;
        xMax = myImageWidth;
        yMax = myImageHeight;

        hogMatchHashMap = new HashMap();
        
        matrixANx9 = new double[8][9];
        setMatchDstnceThrshld(25);
        imageDrawPixel = getImageDrawData();
        aManualCrrspndnce = new ManualCrrspndnce(getImageWidth(), getImageHeight());
    }

    public void filter(int[] grayValues, int i) {
        super.filter(grayValues, i);      
        
        if (getFrameIndex() > 1 && i == 0) {
            System.out.println("FndmntlMatrixDLTHOGMnt: frame index = "+getFrameIndex());
            computeEcldnTrnsfrmtn();
        }
    }

    public void computeEcldnTrnsfrmtn() {
        matrixANx9 = aManualCrrspndnce.crteCrrspndnceMatrix();
        Matrix.print(matrixANx9);
        matchHOGPosition1 = aManualCrrspndnce.getaHOGPosition2_1();
        matchHOGPosition2 = aManualCrrspndnce.getaHOGPosition2_2();
        matchHOGPosition3 = aManualCrrspndnce.getaHOGPosition2_3();
        matchHOGPosition4 = aManualCrrspndnce.getaHOGPosition2_4();
        matchHOGPosition5 = aManualCrrspndnce.getaHOGPosition2_5();
        matchHOGPosition6 = aManualCrrspndnce.getaHOGPosition2_6();
        matchHOGPosition7 = aManualCrrspndnce.getaHOGPosition2_7();
        matchHOGPosition8 = aManualCrrspndnce.getaHOGPosition2_8();        

        drawHOGArrow2(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition2, matchHOGPosition2.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition3, matchHOGPosition3.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition4, matchHOGPosition4.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition5, matchHOGPosition5.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition6, matchHOGPosition6.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition7, matchHOGPosition7.rtrveHOGMatch());
        drawHOGArrow2(matchHOGPosition8, matchHOGPosition8.rtrveHOGMatch());

        double aMatrix[] = computeFundamental(matrixANx9);
        System.out.println("FndmntlMatrixDLTHOGMnl: fundamental matrxi 1x9 ...");
        Matrix.print(aMatrix);
        
        double aFndmtl3x3[][] = Matrix.convert9x1To3x3Add1(aMatrix);
        System.out.println("FndmntlMatrixDLTHOGMnl: fundamental matrxi 3x3 ...");
        Matrix.print(aFndmtl3x3);
        
        double anEpipole1[] = computeEpipole(aFndmtl3x3);        
        System.out.println("ReconstructCrrspndnce: ... epipole 1");
        Matrix.print(anEpipole1);
        
        double aFndmtl3x3T[][] = Matrix.transpose(aFndmtl3x3);
        System.out.println("FndmntlMatrixDLTHOGMnl: fundamental matrxi transposed 3x3 ...");
        Matrix.print(aFndmtl3x3T);
        
         double anEpipole2[] = computeEpipole(aFndmtl3x3T);         
        System.out.println("ReconstructCrrspndnce: ... epipole 2");
        Matrix.print(anEpipole2);
    }

    private double[] computeFundamental(double myMatrix[][]) {
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();
        //Matrix.print(myMatrix);
        double matrixC[][] = aGssnElmntn.process(myMatrix);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);
        Matrix.print(x);
        return x;
    }
    public void computeEpipoles(){
        /*
        double aFndmtl3x3[][] = Matrix.convert9x1To3x3Add1(aFndmtl1x9);
        double anEpipole1[] = computeEpipole(aFndmtl3x3);
        System.out.println("ReconstructCrrspndnce: ... epipole 1");
        Matrix.print(anEpipole1);
        double aFndmtl3x3T[][] = Matrix.transpose(aFndmtl3x3);
        double anEpipole2[] = computeEpipole(aFndmtl3x3T);        
        System.out.println("ReconstructCrrspndnce: ... epipole 2");
        Matrix.print(anEpipole2);
        */ 
    }
    private double[] computeEpipole(double myFndmntl[]) {
        double aFndmntl[][] = Matrix.convert9x1To3x3(myFndmntl);
        double aFndmntlT[][] = Matrix.transpose(aFndmntl);
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();
        //Matrix.print(aFndmntlT);
        double matrixC[][] = aGssnElmntn.process(aFndmntlT);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC); 
        return x;
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

    private double[][] updateMatrixNx9(double aMatrixA2nx9[][], double myMatrixA[][], int n2) {
        //double aMatrixA2nx9[][] = new double[8][9];        
        aMatrixA2nx9[0 + n2][0] = myMatrixA[0][0];
        aMatrixA2nx9[0 + n2][1] = myMatrixA[0][1];
        aMatrixA2nx9[0 + n2][2] = myMatrixA[0][2];
        aMatrixA2nx9[0 + n2][3] = myMatrixA[0][3];
        aMatrixA2nx9[0 + n2][4] = myMatrixA[0][4];
        aMatrixA2nx9[0 + n2][5] = myMatrixA[0][5];
        aMatrixA2nx9[0 + n2][6] = myMatrixA[0][6];
        aMatrixA2nx9[0 + n2][7] = myMatrixA[0][7];
        aMatrixA2nx9[0 + n2][8] = myMatrixA[0][8];
        return aMatrixA2nx9;
    }

    public double getDistance(HOGPosition myHOGPosition, int myX, int myY) {
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        float aDistance = PntTool.getDistance(aX, aY, myX, myY);
        return aDistance;
    }

    public void drawHOGArrow2(HOGPosition matchedHOGPosition, HOGPosition myHOGPosition) {
        drawArrow(matchedHOGPosition, myHOGPosition);
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

    @Override
    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        /*
         updtCornerPixels(x1,y1,0xff00ff00);
         updtCornerPixels(x2,y1,0xff00ff00);
         updtCornerPixels(x1,y2,0xff00ff00);
         updtCornerPixels(x2,y2,0xff00ff00);
         System.out.println("DLTHOG: x1 = "+x1+", y1 = "+y1);
         System.out.println("DLTHOG: x2 = "+x2+", y1 = "+y1);        
         System.out.println("DLTHOG: x1 = "+x1+", y2 = "+y2);
         System.out.println("DLTHOG: x2 = "+x2+", y2 = "+y2);                
         */
    }
}