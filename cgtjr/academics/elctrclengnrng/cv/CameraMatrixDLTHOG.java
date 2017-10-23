/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

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
public class CameraMatrixDLTHOG extends HOGCornerDetectOptmzd {

    private double xc1 = 135;
    private double yc1 = 215;
    private double uc1 = 0;
    private double vc1 = 0;
    private double wc1 = 0;
    
    private double xc2 = 138;
    private double yc2 = 147;
    private double uc2 = 0;//meters
    private double vc2 = 3.81;
    private double wc2 = 0;

    private double xc3 = 316;
    private double yc3 = 200;
    private double uc3 = 7.61;
    private double vc3 = 0;
    private double wc3 = -.76;

    private double xc4 = 92;
    private double yc4 = 58;
    private double uc4 = 7.61;
    private double vc4 = 0;
    private double wc4 = -13.77;
    
    private double xc5 = 99;
    private double yc5 = 52;
    private double uc5 = 9.13;//meters
    private double vc5 = 0;
    private double wc5 = -14.53;
    
    private double xc6 = 101;
    private double yc6 = 22;
    private double uc6 = 9.13;//meters
    private double vc6 = 3.11f;
    private double wc6 = -14.53f;
    private int numberOfPoints1;
    private int numberOfPoints2;
    private int numberOfPoints3;
    private int numberOfPoints4;
    private int centroidx1;
    private int centroidy1;
    private int centroidx2;
    private int centroidy2;
    private double distance1 = 20;
    private double distance2 = 20;
    private double distance3 = 20;
    private double distance4 = 20;
    private float minDistance;
    private HashMap hogMatchHashMap;
    private double matrixA1[][];
    private double matrixA2[][];
    private double matrixA3[][];
    private double matrixA4[][];
    private double matrixA5[][];
    private double matrixA6[][]; 
    
    private double matrixB1[][];
    private double matrixB2[][];
    private double matrixB3[][];
    private double matrixB4[][];
    private double matrixA2nx12[][];
    private double matrixA2nx8[][];
    private double matrixB2nx1[][];
    private HOGPosition matchHOGPosition1;
    private HOGPosition matchHOGPosition2;
    private HOGPosition matchHOGPosition3;
    private HOGPosition matchHOGPosition4;
    private ImageDrawData imageDrawPixels;
    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;
    private boolean shouldDraw;
    private double allOnesMatrix[][] = {{1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.

        int x1 = myImageWidth / 4;
        int y1 = myImageHeight / 4;
        int x2 = 3 * myImageWidth / 4;
        int y2 = 3 * myImageHeight / 4;

        minDistance = PntTool.getDistance(0, 0, x1, y1);
        hogMatchHashMap = new HashMap();

        matrixA1 = new double[2][12];
        matrixA2 = new double[2][12];
        matrixA3 = new double[2][12];
        matrixA4 = new double[2][12];
        matrixA5 = new double[2][12];        
        matrixA6 = new double[1][12];        
        matrixA2nx12 = new double[11][12];
        //setMatchDstnceThrshld(25);
        imageDrawPixels = getImageDrawData();

        distance1 = minDistance;
        distance2 = minDistance;
        distance3 = minDistance;
        distance4 = minDistance;
    }

    public void filter(int[] grayValues, int i) {
        super.filter(grayValues, i);
        //HOGPosition aHOGPosition = getHOGPosition();
        if (i == 1) {
            computeEcldnTrnsfrmtn();
        }
    }

    public double computeEcldnTrnsfrmtn() {
        matrixA1 = rtrveMatrix2x12(xc1, yc1, uc1, vc1, wc1);
        matrixA2nx12 = createMatrix2nx12(matrixA2nx12, matrixA1, 0);

        matrixA2 = rtrveMatrix2x12(xc2, yc2, uc2, vc2, wc2);
        matrixA2nx12 = createMatrix2nx12(matrixA2nx12, matrixA2, 2);
        
        matrixA3 = rtrveMatrix2x12(xc3, yc3, uc3, vc3, wc3);
        matrixA2nx12 = createMatrix2nx12(matrixA2nx12, matrixA3, 4);
        
        matrixA4 = rtrveMatrix2x12(xc4, yc4, uc4, vc4, wc4);
        matrixA2nx12 = createMatrix2nx12(matrixA2nx12, matrixA4, 6);        

        matrixA5 = rtrveMatrix2x12(xc5, yc5, uc5, vc5, wc5);
        matrixA2nx12 = createMatrix2nx12(matrixA2nx12, matrixA5, 8);

        matrixA6 = rtrveMatrix1x12(xc6, yc6, uc6, vc6, wc6);
        matrixA2nx12 = createMatrix1nx12(matrixA2nx12, matrixA6, 10);
        
        aGssnElmntn = new GssnElmntn();        
        aBckwrdSub = new BckwrdSub();
        //Matrix.print(matrixA2nx12);
        double matrixC[][] = aGssnElmntn.process(matrixA2nx12);
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        //Matrix.print(aPivot);
        double x[] = aBckwrdSub.solve(matrixC);
        //Matrix.print(x);
        return 0;
    }

    private double[][] rtrveMatrix2x12(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        HOGPosition aHOGPosition1 = myHOGPosition1;
        HOGPosition aHOGPosition2 = myHOGPosition2;

        if (aHOGPosition1 == null) {
            System.out.println("aHOGPosition1 is null");
        }
        if (aHOGPosition2 == null) {
            System.out.println("aHOGPosition2 is null");
        }

        double x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        double y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        double x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        double y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        return rtrveMatrix2x12(x1, y1, x2, y2, 0);
    }

    private double[][] rtrveMatrix2x12(double x1, double y1, double x2, double y2, double z2) {
        //System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        double aMatrixA[][] = new double[2][12];
        double w = 1;
        aMatrixA[0][0] = 0;
        aMatrixA[0][1] = 0;
        aMatrixA[0][2] = 0;
        aMatrixA[0][3] = 0;
        aMatrixA[0][4] = -w * x2;
        aMatrixA[0][5] = -w * y2;
        aMatrixA[0][6] = -w * z2;
        aMatrixA[0][7] = -w * w;
        aMatrixA[0][8] = y1 * x2;
        aMatrixA[0][9] = y1 * y2;
        aMatrixA[0][10] = y1 * z2;
        aMatrixA[0][11] = y1 * w;
        aMatrixA[1][0] = w * x2;
        aMatrixA[1][1] = w * y2;
        aMatrixA[1][2] = w * z2;
        aMatrixA[1][3] = w * w;
        aMatrixA[1][4] = 0;
        aMatrixA[1][5] = 0;
        aMatrixA[1][6] = 0;
        aMatrixA[1][7] = 0;
        aMatrixA[1][8] = -x1 * x2;
        aMatrixA[1][9] = -x1 * y2;
        aMatrixA[1][10] = -x1 * z2;
        aMatrixA[1][11] = -x1 * w;

        return aMatrixA;
    }
    private double[][] rtrveMatrix1x12(double x1, double y1, double x2, double y2, double z2) {
        //System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        double aMatrixA[][] = new double[2][12];
        double w = 1;
        aMatrixA[0][0] = 0;
        aMatrixA[0][1] = 0;
        aMatrixA[0][2] = 0;
        aMatrixA[0][3] = 0;
        aMatrixA[0][4] = -w * x2;
        aMatrixA[0][5] = -w * y2;
        aMatrixA[0][6] = -w * z2;
        aMatrixA[0][7] = -w * w;
        aMatrixA[0][8] = y1 * x2;
        aMatrixA[0][9] = y1 * y2;
        aMatrixA[0][10] = y1 * z2;
        aMatrixA[0][11] = y1 * w;

        return aMatrixA;
    }    

    private double[][] createMatrix2x8(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[2][8];
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

        System.out.println("DLTHOG Match : x1 = " + x1 + ",y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2);
        System.out.println("DLTHOG number of matches :" + numberOfPoints1 + ", " + numberOfPoints2 + ", " + numberOfPoints3 + ", " + numberOfPoints4);
        aMatrixA[0][0] = 0;
        aMatrixA[0][1] = 0;
        aMatrixA[0][2] = 0;
        aMatrixA[0][3] = -w * x1;
        aMatrixA[0][4] = -w * y1;
        aMatrixA[0][5] = -w * w;
        aMatrixA[0][6] = y2 * x1;
        aMatrixA[0][7] = y2 * y1;
        //aMatrixA[0][8] = y2 * w;
        aMatrixA[1][0] = w * x1;
        aMatrixA[1][1] = w * y1;
        aMatrixA[1][2] = w * w;
        aMatrixA[1][3] = 0;
        aMatrixA[1][4] = 0;
        aMatrixA[1][5] = 0;
        aMatrixA[1][6] = -x2 * x1;
        aMatrixA[1][7] = -x2 * y1;
        //aMatrixA[1][8] = -x2 * w;

        return aMatrixA;
    }

    private double[][] createMatrix2x1(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[2][1];
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

        System.out.println("DLTHOG Match : x1 = " + x1 + ",y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2);
        System.out.println("DLTHOG number of matches :" + numberOfPoints1 + ", " + numberOfPoints2 + ", " + numberOfPoints3 + ", " + numberOfPoints4);
        aMatrixA[0][0] = -y2 * w;
        aMatrixA[1][0] = x2 * w;

        return aMatrixA;
    }

    public double[][] getMatrixA(HashMap myHashMap) {
        double aMatrixA[][] = new double[2][12];
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

    public double[][] rtrveMatrixA2nx12(double myMatrixA1[][], double myMatrixA2[][], double myMatrixA3[][], double myMatrixA4[][], int n2) {
        double aMatrixA2nx12[][] = new double[8][12];
        aMatrixA2nx12 = createMatrix2nx12(aMatrixA2nx12, myMatrixA1, 0);
        aMatrixA2nx12 = createMatrix2nx12(aMatrixA2nx12, myMatrixA2, 2);
        aMatrixA2nx12 = createMatrix2nx12(aMatrixA2nx12, myMatrixA3, 4);
        aMatrixA2nx12 = createMatrix2nx12(aMatrixA2nx12, myMatrixA4, 6);
        return aMatrixA2nx12;
    }

    private double[][] createMatrix2nx12(double aMatrixA2nx12[][], double myMatrixA[][], int n2) {
        //double aMatrixA2nx12[][] = new double[8][12];        
        aMatrixA2nx12[0 + n2][0] = myMatrixA[0][0];
        aMatrixA2nx12[0 + n2][1] = myMatrixA[0][1];
        aMatrixA2nx12[0 + n2][2] = myMatrixA[0][2];
        aMatrixA2nx12[0 + n2][3] = myMatrixA[0][3];
        aMatrixA2nx12[0 + n2][4] = myMatrixA[0][4];
        aMatrixA2nx12[0 + n2][5] = myMatrixA[0][5];
        aMatrixA2nx12[0 + n2][6] = myMatrixA[0][6];
        aMatrixA2nx12[0 + n2][7] = myMatrixA[0][7];
        aMatrixA2nx12[0 + n2][8] = myMatrixA[0][8];
        aMatrixA2nx12[0 + n2][9] = myMatrixA[0][9];
        aMatrixA2nx12[0 + n2][10] = myMatrixA[0][10];
        aMatrixA2nx12[0 + n2][11] = myMatrixA[0][11];

        aMatrixA2nx12[1 + n2][0] = myMatrixA[1][0];
        aMatrixA2nx12[1 + n2][1] = myMatrixA[1][1];
        aMatrixA2nx12[1 + n2][2] = myMatrixA[1][2];
        aMatrixA2nx12[1 + n2][3] = myMatrixA[1][3];
        aMatrixA2nx12[1 + n2][4] = myMatrixA[1][4];
        aMatrixA2nx12[1 + n2][5] = myMatrixA[1][5];
        aMatrixA2nx12[1 + n2][6] = myMatrixA[1][6];
        aMatrixA2nx12[1 + n2][7] = myMatrixA[1][7];
        aMatrixA2nx12[1 + n2][8] = myMatrixA[1][8];
        aMatrixA2nx12[1 + n2][9] = myMatrixA[1][9];
        aMatrixA2nx12[1 + n2][10] = myMatrixA[1][10];
        aMatrixA2nx12[1 + n2][11] = myMatrixA[1][11];

        return aMatrixA2nx12;
    }
    private double[][] createMatrix1nx12(double aMatrixA2nx12[][], double myMatrixA[][], int n2) {
        //double aMatrixA2nx12[][] = new double[8][12];        
        aMatrixA2nx12[0 + n2][0] = myMatrixA[0][0];
        aMatrixA2nx12[0 + n2][1] = myMatrixA[0][1];
        aMatrixA2nx12[0 + n2][2] = myMatrixA[0][2];
        aMatrixA2nx12[0 + n2][3] = myMatrixA[0][3];
        aMatrixA2nx12[0 + n2][4] = myMatrixA[0][4];
        aMatrixA2nx12[0 + n2][5] = myMatrixA[0][5];
        aMatrixA2nx12[0 + n2][6] = myMatrixA[0][6];
        aMatrixA2nx12[0 + n2][7] = myMatrixA[0][7];
        aMatrixA2nx12[0 + n2][8] = myMatrixA[0][8];
        aMatrixA2nx12[0 + n2][9] = myMatrixA[0][9];
        aMatrixA2nx12[0 + n2][10] = myMatrixA[0][10];
        aMatrixA2nx12[0 + n2][11] = myMatrixA[0][11];

        return aMatrixA2nx12;
    }
    private double[][] createMatrix2nx1(double aMatrixB2nx1[][], double myMatrixB[][], int n2) {
        //double aMatrixB2nx1[][] = new double[8][12];        
        aMatrixB2nx1[0 + n2][0] = myMatrixB[0][0];
        aMatrixB2nx1[0 + n2][1] = myMatrixB[0][1];
        aMatrixB2nx1[0 + n2][2] = myMatrixB[0][2];
        aMatrixB2nx1[0 + n2][3] = myMatrixB[0][3];
        aMatrixB2nx1[0 + n2][4] = myMatrixB[0][4];
        aMatrixB2nx1[0 + n2][5] = myMatrixB[0][5];
        aMatrixB2nx1[0 + n2][6] = myMatrixB[0][6];
        aMatrixB2nx1[0 + n2][7] = myMatrixB[0][7];
        aMatrixB2nx1[0 + n2][8] = myMatrixB[0][8];
        aMatrixB2nx1[1 + n2][0] = myMatrixB[1][0];
        aMatrixB2nx1[1 + n2][1] = myMatrixB[1][1];
        aMatrixB2nx1[1 + n2][2] = myMatrixB[1][2];
        aMatrixB2nx1[1 + n2][3] = myMatrixB[1][3];
        aMatrixB2nx1[1 + n2][4] = myMatrixB[1][4];
        aMatrixB2nx1[1 + n2][5] = myMatrixB[1][5];
        aMatrixB2nx1[1 + n2][6] = myMatrixB[1][6];
        aMatrixB2nx1[1 + n2][7] = myMatrixB[1][7];
        aMatrixB2nx1[1 + n2][8] = myMatrixB[1][8];
        return aMatrixB2nx1;
    }

    public double getDistance(HOGPosition myHOGPosition, int myX, int myY) {
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        float aDistance = PntTool.getDistance(aX, aY, myX, myY);
        return aDistance;
    }
    /*
    @Override
    public void drawHOGArrow(HOGPosition matchedHOGPosition, HOGPosition myHOGPosition) {
        if (shouldDraw == true) {
            drawArrow(matchedHOGPosition, myHOGPosition);
        }
    }*/

    @Override
    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        updtCornerPixels((int)xc1,(int)yc1, 0xff00ff00);
        updtCornerPixels((int)xc2,(int)yc2, 0xff00ff00);
        updtCornerPixels((int)xc3,(int)yc3, 0xff00ff00);
        updtCornerPixels((int)xc4,(int)yc4, 0xff00ff00);        
        updtCornerPixels((int)xc5,(int)yc5, 0xff00ff00);
        updtCornerPixels((int)xc6,(int)yc6, 0xff00ff00);
        System.out.println("DLTHOG: xc1 = " + xc1 + ", yc1 = " + yc1);
        System.out.println("DLTHOG: xc2 = " + xc2 + ", yc2 = " + yc2);
        System.out.println("DLTHOG: xc3 = " + xc3 + ", yc3 = " + yc3);
        System.out.println("DLTHOG: xc4 = " + xc4 + ", yc4 = " + yc4);        
        System.out.println("DLTHOG: xc5 = " + xc5 + ", yc5 = " + yc5);
        System.out.println("DLTHOG: xc6 = " + xc6 + ", yc6 = " + yc6);
    }
}