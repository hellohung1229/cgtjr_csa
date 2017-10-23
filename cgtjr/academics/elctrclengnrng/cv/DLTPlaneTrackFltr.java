/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.cv.transforms.HTransform;
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
public class DLTPlaneTrackFltr extends HOGCornerDetectOptmzd {
    private ImageDrawData imagePixelData;    
    /*
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    private int x3;
    private int y3;
    private int x4;
    private int y4;
    */
    private int numberOfPoints1;
    private int numberOfPoints2;
    private int numberOfPoints3;
    private int numberOfPoints4;    
    
    private float minDistance;

    private double matrixA1[][];
    private double matrixA2[][];
    private double matrixA3[][];
    private double matrixA4[][];
        
    private double matrixA2nx9[][];
    
    private HOGPosition aHOGPosition1;
    private HOGPosition aHOGPosition2;
    private HOGPosition aHOGPosition3;
    private HOGPosition aHOGPosition4;

    private HOGPosition aHOGPosition5;
    private HOGPosition aHOGPosition6;
    private HOGPosition aHOGPosition7;
    private HOGPosition aHOGPosition8;

    
    private HOGPosition matchHOGPosition1;
    private HOGPosition matchHOGPosition2;
    private HOGPosition matchHOGPosition3;
    private HOGPosition matchHOGPosition4;
    
    private ImageDrawData imageDrawPixels;
    
    private GssnElmntn aGssnElmntn;
    private BckwrdSub aBckwrdSub;
    private boolean shouldDraw;
    private double HMatrix[];
    private double HMatrix2D[][];    
    private HTransform sceneHomography;
    
    private double allOnesMatrix[][] = {{1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1}};
    
    private double p1x1 = 193;
    private double p1y1 = 128;
    private double p1x2 = 193;
    private double p1y2 = 169;
    private double p1x3 = 218;
    private double p1y3 = 169;
    private double p1x4 = 218;
    private double p1y4 = 128;
    
    private double p2x1 = 192;
    private double p2y1 = 141;
    private double p2x2 = 193;
    private double p2y2 = 169;
    private double p2x3 = 218;
    private double p2y3 = 169;
    private double p2x4 = 213;
    private double p2y4 = 141;
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        System.out.println("DLTPlaneTrackFltr : frame number = "+getFrameIndex());
        imagePixelData = getImageDrawData();
        /*
        x1 = myImageWidth / 4;
        y1 = myImageHeight / 4;
        x2 = 3 * myImageWidth / 4;
        y2 = 3 * myImageHeight / 4;

        minDistance = PntTool.getDistance(0, 0, x1, y1);
        */
        
        matrixA1 = new double[2][9];
        matrixA2 = new double[2][9];
        matrixA3 = new double[2][9];
        matrixA4 = new double[2][9];
        matrixA2nx9 = new double[8][9];
        
        setMatchDstnceThrshld(25);              

        if(aHOGPosition5 == null){
            //int anIndex5 = ImageTool.rtrvIndex(p1x1, p1y1, myImageWidth, myImageHeight);    
            aHOGPosition5 = new HOGPosition(getP2x1(),getP2y1());
        } 
        if(aHOGPosition6 == null){            
            //int anIndex6 = ImageTool.rtrvIndex(p1x2,p1y2, myImageWidth, myImageHeight);                
            aHOGPosition6 = new HOGPosition(getP2x2(),getP2y2());
        } 
        if(aHOGPosition7 == null){
            //int anIndex7 = ImageTool.rtrvIndex(p1x3,p1y3, myImageWidth, myImageHeight);                
            aHOGPosition7 = new HOGPosition(getP2x3(),getP2y3());
        }         
        if(aHOGPosition8 == null){
            //int anIndex8 = ImageTool.rtrvIndex(p1x4,p1y4, myImageWidth, myImageHeight);                            
            aHOGPosition8 = new HOGPosition(getP2x4(),getP2y4());
        }                        
        if(aHOGPosition1 == null){
            //int anIndex1 = ImageTool.rtrvIndex(p2x1,p2y1, myImageWidth, myImageHeight);            
            aHOGPosition1 = new HOGPosition(getP1x1(),getP1y1());
            aHOGPosition1.connectHOGMatch(aHOGPosition5);        
        } 
        if(aHOGPosition2 == null){
            //int anIndex2 = ImageTool.rtrvIndex(p2x2,p2y2, myImageWidth, myImageHeight);            
            aHOGPosition2 = new HOGPosition(getP1x2(),getP1y2());
            aHOGPosition2.connectHOGMatch(aHOGPosition6);
        } 
        if(aHOGPosition3 == null){
            //int anIndex3 = ImageTool.rtrvIndex(p2x3,p2y3, myImageWidth, myImageHeight);            
            aHOGPosition3 = new HOGPosition(getP1x3(),getP1y3());
            aHOGPosition3.connectHOGMatch(aHOGPosition7);            
        }         
        if(aHOGPosition4 == null){
            //int anIndex4 = ImageTool.rtrvIndex(p2x4,p2y4, myImageWidth, myImageHeight);             
            aHOGPosition4 = new HOGPosition(getP1x4(),getP1y4());
            aHOGPosition4.connectHOGMatch(aHOGPosition8);
        }   
        if(sceneHomography == null){
            sceneHomography = new HTransform();
        }
        if(HMatrix2D == null){
           computeEcldnTrnsfrmtn(aHOGPosition1);
           computeEcldnTrnsfrmtn(aHOGPosition2);
           computeEcldnTrnsfrmtn(aHOGPosition3);
           computeEcldnTrnsfrmtn(aHOGPosition4);
           HMatrix = solveEquations();    
           HMatrix2D = createHMatrix(HMatrix);        
           sceneHomography.setHMatrix(HMatrix2D);
        }
    }
    public void filter(int[] grayValues, int i) {
        //super.filter(grayValues, i); 
        //HOGPosition aHOGPosition = getHOGPosition();

        if(getFrameIndex()==2){
           computeView(grayValues,i);
        }
    }
    public void computeView(int dataValues[],int i){
        int x = ImageTool.rtrvXPstn(i, getImageWidth());
        int y = ImageTool.rtrvYPstn(i, getImageWidth());        
        double aX1[] = new double[3];
        aX1[0] = x;
        aX1[1] = y;
        aX1[2] = 1;
        double aX2[] = sceneHomography.computeTransformation(aX1);
        
        if(aX2[0] >= 0 && aX2[1] >= 0 && aX2[0] < getImageWidth() && aX2[1] < getImageHeight()) {
           int anIndex = ImageTool.rtrvIndex((int)aX2[0],(int)aX2[1],getImageWidth(),getImageHeight());
        //int myPixelData[] = imagePixelData.getImagePixels();
        //myPixelData[anIndex] = dataValues[i];
        //System.out.println("DLTPlaneTrackFltr : index = "+anIndex +", color = "+dataValues[i]);
           System.out.println("DLTPlaneTrackFltr : x1 = "+aX1[0] +", y1 = "+aX1[1]+", x2 = "+aX2[0]+", y2 = "+aX2[1]);        
           imagePixelData.drawData(dataValues[i], anIndex);
        }
    }
    public double computeEcldnTrnsfrmtn(HOGPosition myHOGPosition) {
        //super.computeHOG1x1(myHOGPosition, myHOGArrayList2); //To change body of generated methods, choose Tools | Templates.
        /*
        boolean aMatch = super.getIsMatch();
        if (aMatch == false) {
            return 0;
        }*/

        //double aDistance1 = 0;
        //double aDistance2 = 0;
        //double aDistance3 = 0;
        //double aDistance4 = 0;
        
        //System.out.println("DLTHOG: a distance = "+getDistance(myHOGPosition, x1, y1)+" distance 1 = "+distance1);
        //System.out.println("DLTHOG: a x1 = "+x1+", y1 = "+y1);
        //System.out.println("DLTHOG: a x2 = "+x2+", y2 = "+y2);
        
        if (numberOfPoints1 == 0) {
            System.out.println("EuclideanTransFrameApp: test1");
            matchHOGPosition1 = myHOGPosition;
            matrixA1 = rtrveMatrix2x9(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
            //matrixB1 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA1, 0);
            //Matrix.print(matrixA2nx9);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB1, 0);            
            numberOfPoints1++;

            shouldDraw = true;
            drawHOGArrow(matchHOGPosition1,matchHOGPosition1.rtrveHOGMatch());
            shouldDraw = false;

        } else if (numberOfPoints2 == 0) {
            System.out.println("EuclideanTransFrameApp: test2");
            matchHOGPosition2 = myHOGPosition;
            matrixA2 = rtrveMatrix2x9(matchHOGPosition2, matchHOGPosition2.rtrveHOGMatch());

            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA2, 2);
            //Matrix.print(matrixA2nx9);

            numberOfPoints2++;

            //updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition2,matchHOGPosition2.rtrveHOGMatch());
            shouldDraw = false;
            
        } else if (numberOfPoints3 == 0) {
            System.out.println("EuclideanTransFrameApp: test3");            
            matchHOGPosition3 = myHOGPosition;
            matrixA3 = rtrveMatrix2x9(matchHOGPosition3, matchHOGPosition3.rtrveHOGMatch());
            //matrixB3 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA3, 4);
            //Matrix.print(matrixA2nx9);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB3, 4);            
            numberOfPoints3++;

            //updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition3,matchHOGPosition3.rtrveHOGMatch());
            shouldDraw = false;

        } else if (numberOfPoints4 == 0) {
            System.out.println("EuclideanTransFrameApp: test4");            
            matchHOGPosition4 = myHOGPosition;
            matrixA4 = rtrveMatrix2x9(matchHOGPosition4, matchHOGPosition4.rtrveHOGMatch());
            //matrixB4 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA4, 6);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB4, 6);            
            numberOfPoints4++;

            shouldDraw = true;
            drawHOGArrow(matchHOGPosition4,matchHOGPosition4.rtrveHOGMatch());
            shouldDraw = false;
        }
        return 0;
    }
    private double[] solveEquations(){
        double x[] = null;        
        if (numberOfPoints1 > 0 && numberOfPoints2 > 0 && numberOfPoints3 > 0 && numberOfPoints4 > 0) {
            aGssnElmntn = new GssnElmntn();
            aBckwrdSub = new BckwrdSub();                       
                       
            //Matrix.print(matrixA2nx9);
            double matrixC[][] = aGssnElmntn.process(matrixA2nx9); 
            //Matrix.print(matrixC);
            int aPivot[] = aGssnElmntn.getPivot();
            x = aBckwrdSub.solve(matrixC);
            //Matrix.print(x);
        }        
        return x;
    }
    private double[][] rtrveMatrix2x9(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[2][9];
        int w = 1;

        HOGPosition aHOGPosition1 = myHOGPosition1;
        HOGPosition aHOGPosition2 = myHOGPosition2;
        
        if(aHOGPosition1==null){
            System.out.println("aHOGPosition1 is null");
        }
        if(aHOGPosition2==null){
            System.out.println("aHOGPosition2 is null");
        }
        
        double x1 = aHOGPosition1.getX();//ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        double y1 = aHOGPosition1.getY();//ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        double x2 = aHOGPosition2.getX();//ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        double y2 = aHOGPosition2.getY();//ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        
        System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        aMatrixA[0][0] = 0;
        aMatrixA[0][1] = 0;
        aMatrixA[0][2] = 0;
        aMatrixA[0][3] = -w * x1;
        aMatrixA[0][4] = -w * y1;
        aMatrixA[0][5] = -w * w;
        aMatrixA[0][6] = y2 * x1;
        aMatrixA[0][7] = y2 * y1;
        aMatrixA[0][8] = -1*(y2 * w);
        aMatrixA[1][0] = w * x1;
        aMatrixA[1][1] = w * y1;
        aMatrixA[1][2] = w * w;
        aMatrixA[1][3] = 0;
        aMatrixA[1][4] = 0;
        aMatrixA[1][5] = 0;
        aMatrixA[1][6] = -x2 * x1;
        aMatrixA[1][7] = -x2 * y1;
        aMatrixA[1][8] = -1*(-x2 * w);
        //Matrix.print(aMatrixA);
        return aMatrixA;
    }
    /*
    private double[][] createMatrix2x8(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[2][8];
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
        
        System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
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
    }*/
    private double[][] createHMatrix(double myMatrix[]){
        double aHMatrix[][] = new double[3][3];
        aHMatrix[0][0] = myMatrix[0];
        aHMatrix[0][1] = myMatrix[1];        
        aHMatrix[0][2] = myMatrix[2];        
        aHMatrix[1][0] = myMatrix[3];
        aHMatrix[1][1] = myMatrix[4];        
        aHMatrix[1][2] = myMatrix[5];
        aHMatrix[2][0] = myMatrix[6];
        aHMatrix[2][1] = myMatrix[7];        
        aHMatrix[2][2] = -1;//myMatrix[8];
        return aHMatrix;
    }
    /*
    private double[][] createMatrix2x1(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[2][1];
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
        
        System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
        aMatrixA[0][0] = -y2 * w;
        aMatrixA[1][0] = x2 * w;

        return aMatrixA;
    } */   
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

    public double[][] rtrveMatrixA2nx9(double myMatrixA1[][], double myMatrixA2[][], double myMatrixA3[][], double myMatrixA4[][], int n2) {
        double aMatrixA2nx9[][] = new double[8][9];
        aMatrixA2nx9 = createMatrix2nx9(aMatrixA2nx9, myMatrixA1, 0);
        aMatrixA2nx9 = createMatrix2nx9(aMatrixA2nx9, myMatrixA2, 2);
        aMatrixA2nx9 = createMatrix2nx9(aMatrixA2nx9, myMatrixA3, 4);
        aMatrixA2nx9 = createMatrix2nx9(aMatrixA2nx9, myMatrixA4, 6);
        return aMatrixA2nx9;
    }

    private double[][] createMatrix2nx9(double aMatrixA2nx9[][], double myMatrixA[][], int n2) {
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
        aMatrixA2nx9[1 + n2][0] = myMatrixA[1][0];
        aMatrixA2nx9[1 + n2][1] = myMatrixA[1][1];
        aMatrixA2nx9[1 + n2][2] = myMatrixA[1][2];
        aMatrixA2nx9[1 + n2][3] = myMatrixA[1][3];
        aMatrixA2nx9[1 + n2][4] = myMatrixA[1][4];
        aMatrixA2nx9[1 + n2][5] = myMatrixA[1][5];
        aMatrixA2nx9[1 + n2][6] = myMatrixA[1][6];
        aMatrixA2nx9[1 + n2][7] = myMatrixA[1][7];
        aMatrixA2nx9[1 + n2][8] = myMatrixA[1][8];
        //Matrix.print(aMatrixA2nx9);
        return aMatrixA2nx9;
    }
    /*
    private double[][] createMatrix2nx1(double aMatrixB2nx1[][], double myMatrixB[][], int n2) {
        //double aMatrixB2nx1[][] = new double[8][9];        
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
    }*/   

    public double getDistance(HOGPosition myHOGPosition, int myX, int myY) {
        int anIndex = myHOGPosition.getPositionIndex();
        int aX = ImageTool.rtrvXPstn(anIndex, getImageWidth());
        int aY = ImageTool.rtrvYPstn(anIndex, getImageWidth());
        float aDistance = PntTool.getDistance(aX, aY, myX, myY);
        return aDistance;
    }                    
    public void drawHOGArrow(HOGPosition matchedHOGPosition,HOGPosition myHOGPosition ){
         if(shouldDraw == true){
            drawArrow(matchedHOGPosition,myHOGPosition );
         }
    } 
    @Override
    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        //updtCornerPixels(x1,y1,0xff00ff00);
        //updtCornerPixels(x2,y1,0xff00ff00);
        //updtCornerPixels(x1,y2,0xff00ff00);
        //updtCornerPixels(x2,y2,0xff00ff00);
        //System.out.println("DLTHOG: x1 = "+x1+", y1 = "+y1);
        //System.out.println("DLTHOG: x2 = "+x2+", y1 = "+y1);        
        //System.out.println("DLTHOG: x1 = "+x1+", y2 = "+y2);
        //System.out.println("DLTHOG: x2 = "+x2+", y2 = "+y2);                
    }
    public int[] getFltrdData() {
        System.out.println("DLTPlaneTrackFltr.getFltrData()");
        return imagePixelData.getImagePixels();
    }

    public double getP1x1() {
        return p1x1;
    }

    public void setP1x1(double  myP1x1) {
        this.p1x1 = myP1x1;
    }

    public double getP1y1() {
        return p1y1;
    }

    public void setP1y1(double  myP1y1) {
        this.p1y1 = myP1y1;
    }
    public double getP1x2() {
        return p1x2;
    }
    public void setP1x2(double  myP1x2) {
        this.p1x2 = myP1x2;
    }
    public double getP1y2() {
        return p1y2;
    }
    public void setP1y2(double  myP1y2) {
        this.p1y2 = myP1y2;
    }
    public double getP1x3() {
        return p1x3;
    }
    public void setP1x3(double  myP1x3) {
        this.p1x3 = myP1x3;
    }
    public double getP1y3() {
        return p1y3;
    }
    public void setP1y3(double  myP1y3) {
        this.p1y3 = myP1y3;
    }
    public double getP1x4() {
        return p1x4;
    }
    public void setP1x4(double  myP1x4) {
        this.p1x4 = myP1x4;
    }
    public double getP1y4() {
        return p1y4;
    }
    public void setP1y4(double  myP1y4) {
        this.p1y4 = myP1y4;
    }
    public double getP2x1() {
        return p2x1;
    }
    public void setP2x1(double  myP2x1) {
        this.p2x1 = myP2x1;
    }
    public double getP2y1() {
        return p2y1;
    }
    public void setP2y1(double  myP2y1) {
        this.p2y1 = myP2y1;
    }
    public double getP2x2() {
        return p2x2;
    }
    public void setP2x2(double  myP2x2) {
        this.p2x2 = myP2x2;
    }
    public double getP2y2() {
        return p2y2;
    }
    public void setP2y2(double  myP2y2) {
        this.p2y2 = myP2y2;
    }
    public double getP2x3() {
        return p2x3;
    }
    public void setP2x3(double  myP2x3) {
        this.p2x3 = myP2x3;
    }
    public double getP2y3() {
        return p2y3;
    }
    public void setP2y3(double  myP2y3) {
        this.p2y3 = myP2y3;
    }
    public double getP2x4() {
        return p2x4;
    }
    public void setP2x4(double  myP2x4) {
        this.p2x4 = myP2x4;
    }
    public double getP2y4() {
        return p2y4;
    }
    public void setP2y4(double  myP2y4) {
        this.p2y4 = myP2y4;
    }   
}