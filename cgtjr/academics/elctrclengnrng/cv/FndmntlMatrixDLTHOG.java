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
public class FndmntlMatrixDLTHOG extends HOGCornerDetectOptmzd {
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
    private boolean shouldDraw;
    
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    private HashMap hogMatchHashMap;
    private ImageDrawData imageDrawPixel;
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        xMin = 0;
        yMin = 0;
        xMax = myImageWidth;
        yMax = myImageHeight;
        
        hogMatchHashMap = new HashMap();

        matrixA1 = new double[1][9];
        matrixA2 = new double[1][9];
        matrixA3 = new double[1][9];
        matrixA4 = new double[1][9];
        matrixA5 = new double[1][9];
        matrixA6 = new double[1][9];
        matrixA7 = new double[1][9];
        matrixA8 = new double[1][9];

        matrixANx9 = new double[8][9];
        setMatchDstnceThrshld(25);
        imageDrawPixel = getImageDrawData();
    }

    public void filter(int[] grayValues, int i) {
        super.filter(grayValues, i); 
        HOGPosition aHOGPosition = getHOGPosition();
        computeEcldnTrnsfrmtn(aHOGPosition,xMin,xMax,yMin,yMax);
    }

    public boolean isInQuadrant(HOGPosition myHOGPosition, int myXMin,int myXMax,int myYMin,int myYMax,int myQuadNmbr){
        boolean isInQuad = false;
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        int aWidth = myXMax - myXMin;
        int aHeight = myYMax - myYMin;
        int xMid = myXMin + aWidth/2;
        int yMid = myYMin + aHeight/2;
        
        if(myQuadNmbr == 1 && aX >= myXMin && aX <= xMid && aY >= myYMin && aY <= yMid){
           isInQuad = true;   
        }else if(myQuadNmbr == 2 && aX >= xMid && aX <= myXMax && aY >= myYMin && aY <= yMid){
           isInQuad = true;   
        }else if(myQuadNmbr == 3 && aX >= myXMin && aX <= xMid && aY >= yMid && aY <= myYMax){
           isInQuad = true;   
        }else if(myQuadNmbr == 4 && aX >= xMid && aX <= myXMax && aY >= yMid && aY <= myYMax){
           isInQuad = true;   
        }
        return isInQuad;
    }
    public void computeEcldnTrnsfrmtn(HOGPosition myHOGPosition, int myXMin,int myXMax,int myYMin,int myYMax) {
        int anObjectWidth = myXMax - myXMin;
        int anObjectHeight = myYMax - myYMin;
        int x1 = anObjectWidth / 4;
        int y1 = anObjectHeight / 4;
        int x2 = 3 * anObjectWidth / 4;
        int y2 = 3 * anObjectHeight / 4;
        int xc = myXMin + (myXMax - myXMin)/2;
        int yc = myYMin + (myYMax - myYMin)/2;        
    
        boolean aMatch = super.getIsMatch();
        if (aMatch == false) {
            return ;
        }
        System.out.println("FndmntlMatrixDLTHOG: myXMin = "+myXMin+", myXMax = "+myXMax+", myYMin = "+myYMin+", myYMax = "+myYMax);
        if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,1)) && numberOfPoints1 == 0) {
            matchHOGPosition1 = myHOGPosition;
            matrixA1 = rtrveMatrix1x9(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA1, 0);        
            numberOfPoints1++;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition1,matchHOGPosition1.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test1");
            return;
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,2)) && numberOfPoints2 == 0) {
            matchHOGPosition2 = myHOGPosition;
            matrixA2 = rtrveMatrix1x9(matchHOGPosition2, matchHOGPosition2.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA2, 1);
            numberOfPoints2++;
            //updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition2,matchHOGPosition2.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test2");
            return;// distance2;
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,1)) && numberOfPoints3 == 0) {
            matchHOGPosition3 = myHOGPosition;
            matrixA3 = rtrveMatrix1x9(matchHOGPosition3, matchHOGPosition3.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA3, 2);
            numberOfPoints3++;
            //updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition3,matchHOGPosition3.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test3");            
            return;// distance3;            
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,2)) && numberOfPoints4 == 0) {
            matchHOGPosition4 = myHOGPosition;
            matrixA4 = rtrveMatrix1x9(matchHOGPosition4, matchHOGPosition4.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA4, 3);
            numberOfPoints4++;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition4,matchHOGPosition4.rtrveHOGMatch());
            shouldDraw = false;            
            System.out.println("EuclideanTransFrameApp: test4");            
            return;// distance4;      
        }else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,3)) && numberOfPoints5 == 0) {
            matchHOGPosition5 = myHOGPosition;
            matrixA5 = rtrveMatrix1x9(matchHOGPosition5, matchHOGPosition5.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA5, 4);
            numberOfPoints5++;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition5,matchHOGPosition5.rtrveHOGMatch());
            shouldDraw = false;
            return;// distance1;
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,4)) && numberOfPoints6 == 0) {
            matchHOGPosition6 = myHOGPosition;
            matrixA6 = rtrveMatrix1x9(matchHOGPosition6, matchHOGPosition6.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA6, 5);
            numberOfPoints6++;
            //updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition6,matchHOGPosition6.rtrveHOGMatch());
            shouldDraw = false;
            return;// distance2;
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,3)) && numberOfPoints7 == 0) {
            matchHOGPosition7 = myHOGPosition;
            matrixA7 = rtrveMatrix1x9(matchHOGPosition7, matchHOGPosition7.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA7, 6);
            numberOfPoints7++;
            //updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition7,matchHOGPosition7.rtrveHOGMatch());
            shouldDraw = false;
            return;// distance3;            
        } else if ((isInQuadrant(myHOGPosition, myXMin,myXMax,myYMin,myYMax,4)) && numberOfPoints8 == 0) {
            matchHOGPosition8 = myHOGPosition;
            matrixA8 = rtrveMatrix1x9(matchHOGPosition8, matchHOGPosition8.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA8,7);
            numberOfPoints8++;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition8,matchHOGPosition8.rtrveHOGMatch());
            shouldDraw = false;            
            return;
        }
        if (numberOfPoints1 > 0 && numberOfPoints2 > 0 && numberOfPoints3 > 0 && numberOfPoints4 > 0 &&
            numberOfPoints5 > 0 && numberOfPoints6 > 0 && numberOfPoints7 > 0 && numberOfPoints8 > 0)                
        {
           double aMatrix[] = computeFundamental(matrixANx9);
        }
        return;
    }
    private double[] computeFundamental(double myMatrix[][]){
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
    private void computeEpipole(double myFndmntl[]){
        double aFndmntl[][] = Matrix.convert9x1To3x3(myFndmntl);
        double aFndmntlT[][] = Matrix.transpose(aFndmntl);
        aGssnElmntn = new GssnElmntn();
        aBckwrdSub = new BckwrdSub();                                              
        //Matrix.print(aFndmntlT);
        double matrixC[][] = aGssnElmntn.process(aFndmntlT); 
        //Matrix.print(matrixC);
        int aPivot[] = aGssnElmntn.getPivot();
        double x[] = aBckwrdSub.solve(matrixC);                        
        //Matrix.print(x);        
    }
    private double[][] computeCamera1(){
        double IMatrix[][] = Matrix.I3x3();
        double aCameraMatrix[][] = Matrix.concatenate3x3To3x1(IMatrix,new double[3]);
        return aCameraMatrix;
    }
    private double[][] computeCamera2(double myFndmntl[][],double myEpple[]){
        double aSkew[][] = Matrix.createSkewMatrix3x3(myEpple);
        double aMatrix[][] = Matrix.multiply3x3(aSkew, myFndmntl);
        double aCameraMatrix[][] = Matrix.concatenate3x3To3x1(aMatrix, myEpple);
        return aCameraMatrix;
    }
    private double[][] cmpteTrngltnMtrx(double myImgPnt1[],double myImgPnt2[],double myCmraMtrx1[][],double myCmraMtrx2[][]){

        double aMatrixA[][] = new double[4][4];        
        double b1[] = Matrix.scale1x4(myCmraMtrx1[2],myImgPnt1[0]);
        aMatrixA[0] = Matrix.subtract4x1(b1, myCmraMtrx1[0]);
        double b2[] = Matrix.scale1x4(myCmraMtrx1[2],myImgPnt1[1]);
        aMatrixA[1] = Matrix.subtract4x1(b2, myCmraMtrx1[1]);   
        
        double c1[] = Matrix.scale1x4(myCmraMtrx2[2],myImgPnt2[0]);
        aMatrixA[2] = Matrix.subtract4x1(c1, myCmraMtrx2[0]);
        double c2[] = Matrix.scale1x4(myCmraMtrx1[2],myImgPnt1[1]);
        aMatrixA[3] = Matrix.subtract4x1(c2, myCmraMtrx2[1]);
        
        aMatrixA[0][3] = -1*aMatrixA[0][3];
        aMatrixA[1][3] = -1*aMatrixA[1][3];
        aMatrixA[2][3] = -1*aMatrixA[2][3];
        aMatrixA[3][3] = -1*aMatrixA[3][3];        
                
        return aMatrixA;
    }   
    public double[] cmpteWrldCrdnte(double myImgPnt1[],double myImgPnt2[],double myCmraMtrx1[][],double myCmraMtrx2[][]){
        double aTrngleMatrix[][] = cmpteTrngltnMtrx(myImgPnt1,myImgPnt2,myCmraMtrx1,myCmraMtrx2);
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
        
        if(aHOGPosition1==null){
            System.out.println("aHOGPosition1 is null");
        }
        if(aHOGPosition2==null){
            System.out.println("aHOGPosition2 is null");
        }
        
        int x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        int y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        
        //System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        aMatrixA[0][0] = x2*x1;
        aMatrixA[0][1] = x2*y1;
        aMatrixA[0][2] = x2;
        aMatrixA[0][3] = y2*x1;
        aMatrixA[0][4] = y2*y1;
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
        /*
        aMatrixA2nx9[1 + n2][0] = myMatrixA[1][0];
        aMatrixA2nx9[1 + n2][1] = myMatrixA[1][1];
        aMatrixA2nx9[1 + n2][2] = myMatrixA[1][2];
        aMatrixA2nx9[1 + n2][3] = myMatrixA[1][3];
        aMatrixA2nx9[1 + n2][4] = myMatrixA[1][4];
        aMatrixA2nx9[1 + n2][5] = myMatrixA[1][5];
        aMatrixA2nx9[1 + n2][6] = myMatrixA[1][6];
        aMatrixA2nx9[1 + n2][7] = myMatrixA[1][7];
        aMatrixA2nx9[1 + n2][8] = myMatrixA[1][8];
        */
        return aMatrixA2nx9;
    }
    public double getDistance(HOGPosition myHOGPosition, int myX, int myY) {
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        float aDistance = PntTool.getDistance(aX, aY, myX, myY);
        return aDistance;
    }     
    /*
    public void drawHOGArrow(HOGPosition matchedHOGPosition,HOGPosition myHOGPosition ){
         if(shouldDraw == true){
            drawArrow(matchedHOGPosition,myHOGPosition );
         }
    }*/
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
        updtCornerPixels(x1,y1,0xff00ff00);
        updtCornerPixels(x2,y1,0xff00ff00);
        updtCornerPixels(x1,y2,0xff00ff00);
        updtCornerPixels(x2,y2,0xff00ff00);
        System.out.println("DLTHOG: x1 = "+x1+", y1 = "+y1);
        System.out.println("DLTHOG: x2 = "+x2+", y1 = "+y1);        
        System.out.println("DLTHOG: x1 = "+x1+", y2 = "+y2);
        System.out.println("DLTHOG: x2 = "+x2+", y2 = "+y2);                
    }    
}