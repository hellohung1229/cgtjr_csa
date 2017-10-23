/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.lnralgbra.BckwrdSub;
import cgtjr.academics.math.lnralgbra.GssnElmntn;
import cgtjr.academics.math.lnralgbra.Matrix;
import cgtjr.academics.math.lnralgbra.QRTrnspseGrmSchmdt;

/**
 *
 * @author clayton g thomas jr
 */
public class CameraMatrixDLT// extends HOGCornerDetectOptmzd 
{
    private double xc1 = 28;
    private double yc1 = 192;
    private double uc1 = 0.0;
    private double vc1 = 3.66;
    private double wc1 = 0.0;
    
    private double xc2 = 303;
    private double yc2 = 167;
    private double uc2 = 10.97;//meters
    private double vc2 = 4.88;
    private double wc2 = 0.0;

    private double xc3 = 92;
    private double yc3 = 58;
    private double uc3 = 10.16;
    private double vc3 = 39.01;
    private double wc3 = 0.0;

    private double xc4 = 142;
    private double yc4 = 58;
    private double uc4 = 12.6;
    private double vc4 = 39.01;
    private double wc4 = 0.0;
    
    private double xc5 = 308;
    private double yc5 = 104;
    private double uc5 = 10.97;//meters
    private double vc5 = 4.88;
    private double wc5 = 5.49;
    
    private double xc6 = 307;
    private double yc6 = 79;
    private double uc6 = 14.02;//meters
    private double vc6 = 12.19;
    private double wc6 = 5.49;
            
    
    private double matrixA1[][];
    private double matrixA2[][];
    private double matrixA3[][];
    private double matrixA4[][];
    private double matrixA5[][];
    private double matrixA6[][]; 
        
    private double matrixA2nx12[][];
    
    private double rotation[][];
    private double calibration[][];

    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;
    
    private double PMatrix2D[][];
    private double PMatrix2DF[][];    

    public CameraMatrixDLT() {
        matrixA1 = new double[2][12];
        matrixA2 = new double[2][12];
        matrixA3 = new double[2][12];
        matrixA4 = new double[2][12];
        matrixA5 = new double[2][12];        
        matrixA6 = new double[1][12];        
        matrixA2nx12 = new double[11][12];
    }

    public double[][] compute() {
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
        PMatrix2D = Matrix.convert11x1To3x4(x);
        //System.out.println("CameraMatrixDLT ... matrix solution");
        //Matrix.print(PMatrix2D);
        
        double aPMatrix3x3[][] = Matrix.retrieve3x3(PMatrix2D);
        PMatrix2DF = Matrix.flipud3x3(aPMatrix3x3);
        
        QRTrnspseGrmSchmdt aQRTrnspseGrmSchmdt = new QRTrnspseGrmSchmdt();
        aQRTrnspseGrmSchmdt.compute3x3(PMatrix2D);

        
        rotation = aQRTrnspseGrmSchmdt.getQ();           
        calibration = aQRTrnspseGrmSchmdt.getR();
    

        return PMatrix2D;
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
        aMatrixA[0][11] = -y1 * w;
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
        aMatrixA[1][11] = x1 * w;

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
        aMatrixA[0][11] = -y1 * w;

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

        
        //Matrix.print(aMatrixA2nx12);
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
        
        //Matrix.print(aMatrixA2nx12);
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
    public double[] computeTransformation(double mPMatrix[][],double myX1[]){
        double x2[] = new double[3];
        x2[0] = mPMatrix[0][0]*myX1[0]+mPMatrix[0][1]*myX1[1]+mPMatrix[0][2]*myX1[2]+mPMatrix[0][3]*myX1[3];
        x2[1] = mPMatrix[1][0]*myX1[0]+mPMatrix[1][1]*myX1[1]+mPMatrix[1][2]*myX1[2]+mPMatrix[1][3]*myX1[3];                
        x2[2] = mPMatrix[2][0]*myX1[0]+mPMatrix[2][1]*myX1[1]+mPMatrix[2][2]*myX1[2]+mPMatrix[2][3]*myX1[3];        
        return x2;
    }    
    public float[] computeTransformation(double mPMatrix[][],float myX1[]){
        float x2[] = new float[3];
        x2[0] = (float)(mPMatrix[0][0]*myX1[0]+mPMatrix[0][1]*myX1[1]+mPMatrix[0][2]*myX1[2]+mPMatrix[0][3]*myX1[3]);
        x2[1] = (float)(mPMatrix[1][0]*myX1[0]+mPMatrix[1][1]*myX1[1]+mPMatrix[1][2]*myX1[2]+mPMatrix[1][3]*myX1[3]);                
        x2[2] = (float)(mPMatrix[2][0]*myX1[0]+mPMatrix[2][1]*myX1[1]+mPMatrix[2][2]*myX1[2]+mPMatrix[2][3]*myX1[3]);        
        return x2;
    } 
    public double[] computeTransformation(double myX1[]){
        double x2[] = new double[3];
        double x3[] = new double[3];        

        x2[0] = PMatrix2D[0][0]*myX1[0]+PMatrix2D[0][1]*myX1[1]+PMatrix2D[0][2]*myX1[2]+PMatrix2D[0][3]*myX1[3];
        x2[1] = PMatrix2D[1][0]*myX1[0]+PMatrix2D[1][1]*myX1[1]+PMatrix2D[1][2]*myX1[2]+PMatrix2D[1][3]*myX1[3];                
        x2[2] = PMatrix2D[2][0]*myX1[0]+PMatrix2D[2][1]*myX1[1]+PMatrix2D[2][2]*myX1[2]+PMatrix2D[2][3]*myX1[3];  

        x3[0] = x2[0]/x2[2];//Math.round(x2[0]/x2[2]);
        x3[1] = x2[1]/x2[2];//Math.round(x2[1]/x2[2]);
        x3[2] = x2[2]/x2[2];//Math.round(x2[2]/x2[2]);

        //System.out.println("CameraMatrixDLT.computeTransformation() .... ");
        //Matrix.print(myX1);        
        //Matrix.print(x2);        
        //Matrix.print(PMatrix2D);  
        //Matrix.print(x3);        
        return x3;
    }
    public double[] computeLocation(double myX1[]){
        double x2[] = new double[3];
        double x3[] = new double[4];        

        double P3x3[][] = new double[3][3];
        P3x3[0][0] = PMatrix2D[0][0];
        P3x3[1][0] = PMatrix2D[1][0];        
        P3x3[2][0] = PMatrix2D[2][0];        
        P3x3[0][1] = PMatrix2D[0][1];
        P3x3[1][1] = PMatrix2D[1][1];        
        P3x3[2][1] = PMatrix2D[2][1];
        P3x3[0][2] = PMatrix2D[0][3];
        P3x3[1][2] = PMatrix2D[1][3];        
        P3x3[2][2] = PMatrix2D[2][3];        
              
        double PI3x3[][] = Matrix.inverse(P3x3);
        
        x2[0] = PI3x3[0][0]*myX1[0]+PI3x3[0][1]*myX1[1]+PI3x3[0][2]*myX1[2];
        x2[1] = PI3x3[1][0]*myX1[0]+PI3x3[1][1]*myX1[1]+PI3x3[1][2]*myX1[2];                
        x2[2] = PI3x3[2][0]*myX1[0]+PI3x3[2][1]*myX1[1]+PI3x3[2][2]*myX1[2];  

        x3[0] = x2[0]/x2[2];//Math.round(x2[0]/x2[2]);
        x3[1] = x2[1]/x2[2];//Math.round(x2[1]/x2[2]);
        x3[2] = 0;
        x3[3] = x2[2]/x2[2];//Math.round(x2[2]/x2[2]);

        //System.out.println("CameraMatrixDLT.computeLocation() start .... ");
        //Matrix.print(myX1);        
        //Matrix.print(x2);        
        //Matrix.print(PI3x3);  
        //Matrix.print(x3);        
        //System.out.println("CameraMatrixDLT.computeLocation() end .... ");
        return x3;
    }    
    public float[] computeTransformation(float myX1[]){
        float x2[] = new float[3];
        float x3[] = new float[3];        

        x2[0] = (float)(PMatrix2D[0][0]*myX1[0]+PMatrix2D[0][1]*myX1[1]+PMatrix2D[0][2]*myX1[2]+PMatrix2D[0][3]*myX1[3]);
        x2[1] = (float)(PMatrix2D[1][0]*myX1[0]+PMatrix2D[1][1]*myX1[1]+PMatrix2D[1][2]*myX1[2]+PMatrix2D[1][3]*myX1[3]);                
        x2[2] = (float)(PMatrix2D[2][0]*myX1[0]+PMatrix2D[2][1]*myX1[1]+PMatrix2D[2][2]*myX1[2]+PMatrix2D[2][3]*myX1[3]);  

        x3[0] = x2[0]/x2[2];//Math.round(x2[0]/x2[2]);
        x3[1] = x2[1]/x2[2];//Math.round(x2[1]/x2[2]);
        x3[2] = x2[2]/x2[2];//Math.round(x2[2]/x2[2]);

        //System.out.println("HTransform.computeTransformation() .... ");
        //Matrix.print(myX1);        
        //Matrix.print(PMatrix2D);  
        //Matrix.print(x3);        
        return x3;
    }    
    public CubeCreator cameraTransform(CubeCreator myCubeCreater){
       
       CubeCreator aCubeCreater = new CubeCreator(myCubeCreater.getWidth(),myCubeCreater.getLength(),myCubeCreater.getHeight()); 
       
       float p1[] = {(float)myCubeCreater.getX1(),(float)myCubeCreater.getY1(),(float)myCubeCreater.getZ1(),1};
       float p2[] = {(float)myCubeCreater.getX2(),(float)myCubeCreater.getY2(),(float)myCubeCreater.getZ2(),1};
       float p3[] = {(float)myCubeCreater.getX3(),(float)myCubeCreater.getY3(),(float)myCubeCreater.getZ3(),1};       
       float p4[] = {(float)myCubeCreater.getX4(),(float)myCubeCreater.getY4(),(float)myCubeCreater.getZ4(),1};       
       float p5[] = {(float)myCubeCreater.getX5(),(float)myCubeCreater.getY5(),(float)myCubeCreater.getZ5(),1};       
       float p6[] = {(float)myCubeCreater.getX6(),(float)myCubeCreater.getY6(),(float)myCubeCreater.getZ6(),1};       
       float p7[] = {(float)myCubeCreater.getX7(),(float)myCubeCreater.getY7(),(float)myCubeCreater.getZ7(),1};       
       float p8[] = {(float)myCubeCreater.getX8(),(float)myCubeCreater.getY8(),(float)myCubeCreater.getZ8(),1};  
       p1 = computeTransformation(p1);
       p2 = computeTransformation(p2);       
       p3 = computeTransformation(p3);
       p4 = computeTransformation(p4);       
       p5 = computeTransformation(p5);       
       p6 = computeTransformation(p6);       
       p7 = computeTransformation(p7);       
       p8 = computeTransformation(p8);       
       aCubeCreater.setX1(p1[0]);aCubeCreater.setY1(p1[1]);aCubeCreater.setZ1(p1[2]);
       aCubeCreater.setX2(p2[0]);aCubeCreater.setY2(p2[1]);aCubeCreater.setZ2(p2[2]);       
       aCubeCreater.setX3(p3[0]);aCubeCreater.setY3(p3[1]);aCubeCreater.setZ3(p3[2]);       
       aCubeCreater.setX4(p4[0]);aCubeCreater.setY4(p4[1]);aCubeCreater.setZ4(p4[2]);
       aCubeCreater.setX5(p5[0]);aCubeCreater.setY5(p5[1]);aCubeCreater.setZ5(p5[2]);       
       aCubeCreater.setX6(p6[0]);aCubeCreater.setY6(p6[1]);aCubeCreater.setZ6(p6[2]);       
       aCubeCreater.setX7(p7[0]);aCubeCreater.setY7(p7[1]);aCubeCreater.setZ7(p7[2]);       
       aCubeCreater.setX8(p8[0]);aCubeCreater.setY8(p8[1]);aCubeCreater.setZ8(p8[2]);       
       return aCubeCreater;       
    }
    public CubeCreator computeTransform(double myMatrix[][],CubeCreator myCubeCreater){
              
       float p1[] = {(float)myCubeCreater.getX1(),(float)myCubeCreater.getY1(),(float)myCubeCreater.getZ1(),1};
       float p2[] = {(float)myCubeCreater.getX2(),(float)myCubeCreater.getY2(),(float)myCubeCreater.getZ2(),1};
       float p3[] = {(float)myCubeCreater.getX3(),(float)myCubeCreater.getY3(),(float)myCubeCreater.getZ3(),1};       
       float p4[] = {(float)myCubeCreater.getX4(),(float)myCubeCreater.getY4(),(float)myCubeCreater.getZ4(),1};       
       float p5[] = {(float)myCubeCreater.getX5(),(float)myCubeCreater.getY5(),(float)myCubeCreater.getZ5(),1};       
       float p6[] = {(float)myCubeCreater.getX6(),(float)myCubeCreater.getY6(),(float)myCubeCreater.getZ6(),1};       
       float p7[] = {(float)myCubeCreater.getX7(),(float)myCubeCreater.getY7(),(float)myCubeCreater.getZ7(),1};       
       float p8[] = {(float)myCubeCreater.getX8(),(float)myCubeCreater.getY8(),(float)myCubeCreater.getZ8(),1};  
       
       CubeCreator aCubeCreater = new CubeCreator(Math.abs(p1[0]-p2[0]),Math.abs(p1[1]-p4[1]),Math.abs(p1[2]-p6[2])); 
       
       p1 = computeTransformation(myMatrix,p1);
       p2 = computeTransformation(myMatrix,p2);       
       p3 = computeTransformation(myMatrix,p3);
       p4 = computeTransformation(myMatrix,p4);       
       p5 = computeTransformation(myMatrix,p5);       
       p6 = computeTransformation(myMatrix,p6);       
       p7 = computeTransformation(myMatrix,p7);       
       p8 = computeTransformation(myMatrix,p8);       
       aCubeCreater.setX1(p1[0]);aCubeCreater.setY1(p1[1]);aCubeCreater.setZ1(p1[2]);
       aCubeCreater.setX2(p2[0]);aCubeCreater.setY2(p2[1]);aCubeCreater.setZ2(p2[2]);       
       aCubeCreater.setX3(p3[0]);aCubeCreater.setY3(p3[1]);aCubeCreater.setZ3(p3[2]);       
       aCubeCreater.setX4(p4[0]);aCubeCreater.setY4(p4[1]);aCubeCreater.setZ4(p4[2]);
       aCubeCreater.setX5(p5[0]);aCubeCreater.setY5(p5[1]);aCubeCreater.setZ5(p5[2]);       
       aCubeCreater.setX6(p6[0]);aCubeCreater.setY6(p6[1]);aCubeCreater.setZ6(p6[2]);       
       aCubeCreater.setX7(p7[0]);aCubeCreater.setY7(p7[1]);aCubeCreater.setZ7(p7[2]);       
       aCubeCreater.setX8(p8[0]);aCubeCreater.setY8(p8[1]);aCubeCreater.setZ8(p8[2]);       
       return aCubeCreater;       
    }    
    public CubeCreator rotateCube(CubeCreator myCubeCreater){
       float p1[] = {(float)myCubeCreater.getX1(),(float)myCubeCreater.getY1(),(float)myCubeCreater.getZ1()};
       float p2[] = {(float)myCubeCreater.getX2(),(float)myCubeCreater.getY2(),(float)myCubeCreater.getZ2()};
       float p3[] = {(float)myCubeCreater.getX3(),(float)myCubeCreater.getY3(),(float)myCubeCreater.getZ3()};       
       float p4[] = {(float)myCubeCreater.getX4(),(float)myCubeCreater.getY4(),(float)myCubeCreater.getZ4()};       
       float p5[] = {(float)myCubeCreater.getX5(),(float)myCubeCreater.getY5(),(float)myCubeCreater.getZ5()};       
       float p6[] = {(float)myCubeCreater.getX6(),(float)myCubeCreater.getY6(),(float)myCubeCreater.getZ6()};       
       float p7[] = {(float)myCubeCreater.getX7(),(float)myCubeCreater.getY7(),(float)myCubeCreater.getZ7()};       
       float p8[] = {(float)myCubeCreater.getX8(),(float)myCubeCreater.getY8(),(float)myCubeCreater.getZ8()};  
       p1 = rotatePoint(p1);
       p2 = rotatePoint(p2);       
       p3 = rotatePoint(p3);
       p4 = rotatePoint(p4);       
       p5 = rotatePoint(p5);       
       p6 = rotatePoint(p6);       
       p7 = rotatePoint(p7);       
       p8 = rotatePoint(p8);       
       myCubeCreater.setX1(p1[0]);myCubeCreater.setY1(p1[1]);myCubeCreater.setZ1(p1[2]);
       myCubeCreater.setX2(p2[0]);myCubeCreater.setY2(p2[1]);myCubeCreater.setZ2(p2[2]);       
       myCubeCreater.setX3(p3[0]);myCubeCreater.setY3(p3[1]);myCubeCreater.setZ3(p3[2]);       
       myCubeCreater.setX4(p4[0]);myCubeCreater.setY4(p4[1]);myCubeCreater.setZ4(p4[2]);
       myCubeCreater.setX5(p5[0]);myCubeCreater.setY5(p5[1]);myCubeCreater.setZ5(p5[2]);       
       myCubeCreater.setX6(p6[0]);myCubeCreater.setY6(p6[1]);myCubeCreater.setZ6(p6[2]);       
       myCubeCreater.setX7(p7[0]);myCubeCreater.setY7(p7[1]);myCubeCreater.setZ7(p7[2]);       
       myCubeCreater.setX8(p8[0]);myCubeCreater.setY8(p8[1]);myCubeCreater.setZ8(p8[2]);       
       return myCubeCreater;       
    }
    public float[] rotatePoint(float myX1[]){
        float x2[] = new float[3];
        x2[0] = (float)(rotation[0][0]*myX1[0]+rotation[0][1]*myX1[1]+rotation[0][2]*myX1[2]);
        x2[1] = (float)(rotation[1][0]*myX1[0]+rotation[1][1]*myX1[1]+rotation[1][2]*myX1[2]);                
        x2[2] = (float)(rotation[2][0]*myX1[0]+rotation[2][1]*myX1[1]+rotation[2][2]*myX1[2]);                        
        return x2;
    }
    public double[] computeHeightWidth(double my2DPt1[],double my2DPt2[],double my3DPt1[]){ 
        return computeHeightWidth(my2DPt1,my2DPt2,my3DPt1,PMatrix2D);
    }
    public double[] computeHeightWidth(double my2DPt1[],double my2DPt2[],double my3DPt1[],double myCM[][]){        
        double a3DPt2[] = new double[4];
        double aL1[] = Matrix.crssPrdct3x1x3x1(my2DPt1, my2DPt2);
        double aV1[] = Matrix.mltply3x4x4x1(myCM, my3DPt1);
        //double aV2[] = Matrix.mltply3x4x4x1(myCM, my3DPoint2);
        
        double term1_1 = aL1[0];
        double term2_1 = aV1[1]*myCM[2][2] - aV1[2]*myCM[1][2];        
        double z1 = term1_1/term2_1;

        double term1_2 = aL1[1];        
        double term2_2 = aV1[1]*myCM[1][2] - aV1[2]*myCM[0][2];        
        double z2 = term1_2/term2_2;
        
        double term1_3 = aL1[1];        
        double term2_3 = aV1[1]*myCM[1][2] - aV1[2]*myCM[0][2];        
        double z3 = term2_3/term1_3;        
        
        a3DPt2[0] = my3DPt1[0]; 
        a3DPt2[1] = my3DPt1[1];
        a3DPt2[2] = z3;
        a3DPt2[3] = 1;
        //System.out.println("z1 = "+z1+", z2 = "+z2+", z3 = "+z3);
        return a3DPt2;
    }
    public double[] computeZ(double myTopPt[],double myX,double myY){  
        return computeZ(myTopPt,myX,myY,PMatrix2D);
    }
    public double computeWidth(double myHeight,float myXMin,float myXMax,float myYMin,float myYMax){
        float x1 = myXMin;
        float x2 = myXMax; 
        float y1 = myYMin;
        float y2 = myYMax; 

        float widthHeightRatio = (x2-x1)/(y2-y1);
        
       double objectWidth = widthHeightRatio*myHeight;
       return objectWidth;
    }    
    public double[] computeZ(double myTopPt[],double myX,double myY,double myCM[][]){        

        //double aV2[] = Matrix.mltply3x4x4x1(myCM, my3DPoint2);
        
        //double z1 = (myTopPt[0]-(myCM[0][0]*myX + myCM[0][1]*myY+myCM[0][3]))/myCM[0][2];
        
        double z1 = (myTopPt[0]*(myCM[2][0]*myX + myCM[2][1]*myY+myCM[2][3])-(myCM[0][0]*myX+myCM[0][1]*myY+myCM[0][3]))/(myCM[0][2]-myTopPt[0]*myCM[2][2]);
        double z2 = (myTopPt[1]*(myCM[2][0]*myX + myCM[2][1]*myY+myCM[2][3])-(myCM[1][0]*myX+myCM[1][1]*myY+myCM[1][3]))/(myCM[1][2]-myTopPt[1]*myCM[2][2]);

        double z3 = (myTopPt[0]*(myCM[2][0]*myX + myCM[2][1]*myY+myCM[2][3])-(myCM[0][0]*myX+myCM[0][1]*myY+myCM[0][3]))/(myCM[0][2]-myTopPt[0]*myCM[2][2]);        
        double z4 = (myTopPt[1]*(myCM[2][0]*myX + myCM[2][1]*myY+myCM[2][3])-(myCM[1][0]*myX+myCM[1][1]*myY+myCM[1][3]))/(myCM[1][2]-myTopPt[1]*myCM[2][2]);

        
        //System.out.println("x = "+ myTopPt[0]+", y = "+myTopPt[1]+", X = "+myX+", Y = "+myY+", Z = "+z1+", or Z = "+z2);
        double aTopDPt2[] = {myX,myY,z2,1};
        return aTopDPt2;
    }    
    public double[][] getCameraMatrix() {
        return PMatrix2D;
    }
    
    /*
    public void setPMatrix2D(double[][] myPMatrix2D) {
        this.PMatrix2D = myPMatrix2D;
    }*/

    public double[][] getPMatrix2DF() {
        return PMatrix2DF;
    }

    public void setPMatrix2DF(double[][] myPMatrix2DF) {
        this.PMatrix2DF = myPMatrix2DF;
    }    

    public double[][] getRotation() {
        return rotation;
    }
    public void setRotation(double[][] myRotation) {
        this.rotation = myRotation;
    }
    public double[][] getCalibration() {
        return calibration;
    }
    public void setCalibration(double[][] myCalibration) {
        this.calibration = myCalibration;
    }    
}