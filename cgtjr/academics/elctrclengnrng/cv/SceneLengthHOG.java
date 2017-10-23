/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterHeightHOGFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.lnralgbra.BckwrdSub;
import cgtjr.academics.math.lnralgbra.GssnElmntn;
import cgtjr.academics.math.lnralgbra.MathVector;
//import cgtjr.academics.math.lnralgbra.Matrix;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author clayton g thomas jr
 */
//public class SceneLengthHOG extends HOGCornerDetectOptmzd {
public class SceneLengthHOG extends ClusterHeightHOGFltr {    
    private ImageDrawData imagePixelData;
    
    private float heightA = 20;
    private float heightB;    
    
    private float v1x = 49.58f;
    private float v1y = 2785.68f;
    private float v1c = 1;
    
    private float v2x = -107.8f;
    private float v2y = -68.66f;
    private float v2c = 1;
    
    private float v3x = 1428.23f;
    private float v3y = 163.99f;
    private float v3c = 1;    

    
    //private float t1x = 250;
    //private float t1y = 80;
    //private float t1c = 1;    
    private float t1x = 155;
    private float t1y = 113;
    private float t1c = 1;    
    
    //private float t2x = 309;
    //private float t2y = 105;
    //private float t2c = 1;    
    private float t2x = 309;
    private float t2y = 105;
    private float t2c = 1;    
    
    //private float b1x = 246;
    //private float b1y = 134;
    //private float b1c = 1;    
    private float b1x = 155;
    private float b1y = 140;
    private float b1c = 1;    

    private float b2x = 303;
    private float b2y = 167;
    private float b2c = 1;
    //private float la;
    //private float lb;
    //private float lc;
    
            
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

    
        MathVector vanishPoint1;
        MathVector vanishPoint2;        
        MathVector vanishPoint3;
        MathVector vLineGround;
        MathVector basePoint1;
        MathVector basePoint2;           
        MathVector topPoint1;
        MathVector topPoint2;           
        
        MathVector vanishLine_;
        MathVector vanishPointU;  

        MathVector vanishLine2;
        
        MathVector transferPoint_;
        MathVector transferPoint;
        
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

        //hogMatchHashMap = new HashMap();

        matrixA1 = new double[2][12];
        matrixA2 = new double[2][12];
        matrixA3 = new double[2][12];
        matrixA4 = new double[2][12];
        matrixA5 = new double[2][12];        
        matrixA6 = new double[1][12];        
        matrixA2nx12 = new double[11][12];
        setMatchDstnceThrshld(25);
        imageDrawPixels = getImageDrawData();

        //distance1 = minDistance;
        //distance2 = minDistance;
        //distance3 = minDistance;
        //distance4 = minDistance;
    }

    public void filter(int[] grayValues, int i) {
        super.filter(grayValues, i);
        //HOGPosition aHOGPosition = getHOGPosition();
        
        //System.out.println("SceneLengthHOG : test 2");
        if (i == 0) {
            computeEcldnTrnsfrmtn();
        }
    }

    public double computeEcldnTrnsfrmtn() {

        //System.out.println("SceneLengthHOG : test 5");    
        vanishPoint1 = new MathVector(v1x,v1y,v1c);
        vanishPoint2 = new MathVector(v2x,v2y,v2c);        
        vanishPoint3 = new MathVector(v3x,v3y,v3c);
        vLineGround = MathVector.crssPrdct(vanishPoint2, vanishPoint3);
        basePoint1 = new MathVector(b1x,b1y,b1c);
        basePoint2 = new MathVector(b2x,b2y,b2c);           
        topPoint1 = new MathVector(t1x,t1y,t1c);
        topPoint2 = new MathVector(t2x,t2y,t2c);           
        
        vanishLine_ = MathVector.crssPrdct(basePoint1, basePoint2);
        vanishPointU = MathVector.crssPrdct(vanishLine_, vLineGround);  

        vanishLine2 = MathVector.crssPrdct(vanishPoint1, basePoint2);
        
        transferPoint_ = MathVector.crssPrdct(topPoint1, vanishPointU);
        transferPoint = MathVector.crssPrdct(transferPoint_, vanishLine2);

        MathVector vLineGroundScaled = new MathVector(vLineGround);
        vLineGroundScaled.scale((float)(1.0/vLineGround.getZ2()));        
        //System.out.println("SceneLengthHOG : vLineGroundScaled , x = "+vLineGroundScaled.getX2()+", y = "+vLineGroundScaled.getY2()+", z = "+vLineGroundScaled.getZ2());        
        
        MathVector vanishLineScaled_ = new MathVector(vanishLine_);
        vanishLineScaled_.scale((float)(1.0/vanishLine_.getZ2()));        
        //System.out.println("SceneLengthHOG : vanishLineScaled_ , x = "+vanishLineScaled_.getX2()+", y = "+vanishLineScaled_.getY2()+", z = "+vanishLineScaled_.getZ2());

        MathVector vanishPointScaleU = new MathVector(vanishPointU);
        vanishPointScaleU.scale((float)(1.0/vanishPointU.getZ2()));
        //System.out.println("SceneLengthHOG : vanishPointScaleU , x = "+vanishPointScaleU.getX2()+", y = "+vanishPointScaleU.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishLine2Scaled_ = new MathVector(vanishLine_);
        vanishLine2Scaled_.scale((float)(1.0/vanishLine_.getZ2()));
        //System.out.println("SceneLengthHOG : vanishLine2Scaled_ , x = "+vanishLine2Scaled_.getX2()+", y = "+vanishLine2Scaled_.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishPointScaleU2 = MathVector.crssPrdct(vanishLine2Scaled_, vLineGround); 
        vanishPointScaleU.scale((float)(1.0/vanishPointScaleU2.getZ2()));
        //System.out.println("SceneLengthHOG : vanishPointScaleU2 , x = "+vanishPointScaleU2.getX2()+", y = "+vanishPointScaleU2.getY2()+", z = "+vanishPointScaleU2.getZ2());
        
        MathVector transferPointScaled_ = MathVector.crssPrdct(topPoint1, vanishPointScaleU); 
        transferPointScaled_.scale((float)(1.0/transferPointScaled_.getZ2()));
        //System.out.println("SceneLengthHOG : transferPointScaled_ , x = "+transferPointScaled_.getX2()+", y = "+transferPointScaled_.getY2()+", z = "+transferPointScaled_.getZ2());
        
        MathVector transferPointScaled = MathVector.crssPrdct(transferPointScaled_,vanishLine2); 
        transferPointScaled.scale((float)(1.0/transferPointScaled.getZ2()));
        //System.out.println("SceneLengthHOG : transferPointScaled , x = "+transferPointScaled.getX2()+", y = "+transferPointScaled.getY2()+", y = "+transferPointScaled.getZ2());
                
        float b2 = 0;
        float t1 = MathVector.getDistance(basePoint2,transferPoint);
        float t1Scaled = MathVector.getDistance(basePoint2,transferPointScaled);        
        float t2 = MathVector.getDistance(basePoint2,topPoint2);
        float v = MathVector.getDistance(basePoint2,vanishPoint1);        

        //System.out.println("SceneLengthHOG : b2 = "+b2);        
        //System.out.println("SceneLengthHOG : t1 = "+t1);
        //System.out.println("SceneLengthHOG : t1Scale = "+t1Scaled);
        //System.out.println("SceneLengthHOG : t2 = "+t2);
        //System.out.println("SceneLengthHOG : v = "+v);
        
        float H2x2[][] = {{1,0},{1,-v}};
        
        float distanceT10 = H2x2[0][0]*t1Scaled+H2x2[0][1]*1;
        float distanceT11 = H2x2[1][0]*t1Scaled+H2x2[1][1]*1;
        float distanceT1 = distanceT10/distanceT11;
        
        float distanceT20 = H2x2[0][0]*t2+H2x2[0][1]*1;
        float distanceT21 = H2x2[1][0]*t2+H2x2[1][1]*1;                
        float distanceT2 = distanceT20/distanceT21;        
        float ratioD1ToD2 = distanceT1/distanceT2;        
        heightB = heightA*(distanceT1/distanceT2);        
        //System.out.println("SceneLengthHOG: ratioD1ToD2 = "+ratioD1ToD2);
        //System.out.println("SceneLength: height b = "+heightB);
        
        float n = t1Scaled*(v-t2);
        float d = t2*(v-t1Scaled);
        float ratio = n/d;                         
        heightB = heightA*(n/d);
        //System.out.println("SceneLengthHOG: ratio = "+ratio);        
        //System.out.println("SceneLength: height b = "+heightB);
         
        return 0;
    }
    private float[] computeHomography2x2x2x1(float myH2x2[][],float myVector2x1[][]){
       float aVector[] = new float[2];       
       aVector[0] = myH2x2[0][0]*myVector2x1[0][0];
       aVector[1] = myH2x2[0][1]*myVector2x1[0][1];
       return aVector;
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

        //System.out.println("DLTHOG Match : x1 = " + x1 + ",y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2);
        //System.out.println("DLTHOG number of matches :" + numberOfPoints1 + ", " + numberOfPoints2 + ", " + numberOfPoints3 + ", " + numberOfPoints4);
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

        //System.out.println("DLTHOG Match : x1 = " + x1 + ",y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2);
        //System.out.println("DLTHOG number of matches :" + numberOfPoints1 + ", " + numberOfPoints2 + ", " + numberOfPoints3 + ", " + numberOfPoints4);
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

    public void drawHOGArrow(HOGPosition matchedHOGPosition, HOGPosition myHOGPosition) {
        if (shouldDraw == true) {
            drawArrow(matchedHOGPosition, myHOGPosition);
        }
    }

    

    public void finish1() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        /*
        updtCornerPixels((int)xc1,(int)yc1, 0xff00ff00);
        updtCornerPixels((int)xc2,(int)yc2, 0xff00ff00);
        updtCornerPixels((int)xc3,(int)yc3, 0xff00ff00);
        updtCornerPixels((int)xc4,(int)yc4, 0xff00ff00);        
        updtCornerPixels((int)xc5,(int)yc5, 0xff00ff00);
        updtCornerPixels((int)xc6,(int)yc6, 0xff00ff00);
        */
        System.out.println("SceneLengthHOG : ... test");
        System.out.println("SceneLengthHOG : ... vanishPoint2: x = "+vanishPoint2.getX2()+", y="+vanishPoint2.getY2());        
        System.out.println("SceneLengthHOG : ... vanishPoint3: x = "+vanishPoint3.getX2()+", y="+vanishPoint3.getY2());                
        System.out.println("SceneLengthHOG : ... basepoint1: x = "+basePoint1.getX2()+", y="+basePoint1.getY2());                
        System.out.println("SceneLengthHOG : ... topPoint1: x = "+topPoint1.getX2()+", y="+topPoint1.getY2());             
        System.out.println("SceneLengthHOG : ... basepoint2: x = "+basePoint2.getX2()+", y="+basePoint2.getY2());                
        System.out.println("SceneLengthHOG : ... topPoint2: x = "+topPoint2.getX2()+", y="+topPoint2.getY2());             
        
        int imageData[] = imageDrawPixels.getImagePixels();
        //int imageData[] = getFltrdData();
        //getImageData();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        
        LineCrtr.drawLine(vanishPoint2, vanishPoint3, imageData,aWidth,aHeight,0x0000ffff);
        LineCrtr.drawLine(basePoint1, topPoint1, imageData,aWidth,aHeight,0x0000ffff);
        LineCrtr.drawLine(basePoint2, topPoint2, imageData,aWidth,aHeight,0x0000ffff);        
        
        //LineCrtr.drawLine(vanishPoint1, vanishPoint2, imageData,aWidth,aHeight,0x000000ff);
        //LineCrtr.drawLine(vanishPoint1, basePoint2, imageData,aWidth,aHeight,0x000000ff);
        
        /*
        System.out.println("DLTHOG: xc1 = " + xc1 + ", yc1 = " + yc1);
        System.out.println("DLTHOG: xc2 = " + xc2 + ", yc2 = " + yc2);
        System.out.println("DLTHOG: xc3 = " + xc3 + ", yc3 = " + yc3);
        System.out.println("DLTHOG: xc4 = " + xc4 + ", yc4 = " + yc4);        
        System.out.println("DLTHOG: xc5 = " + xc5 + ", yc5 = " + yc5);
        System.out.println("DLTHOG: xc6 = " + xc6 + ", yc6 = " + yc6);
        */
    }
}