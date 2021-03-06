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
public class DLTHOG extends HOGCornerDetectOptmzd {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private int x4;
    private int y4;
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
    
    private double matrixB1[][];
    private double matrixB2[][];
    private double matrixB3[][];
    private double matrixB4[][];
    
    private double matrixA2nx9[][];
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
    
    private double allOnesMatrix[][] = {{1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1},
                      {1,1,1,1,1,1,1,1,1}};
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.

        x1 = myImageWidth / 4;
        y1 = myImageHeight / 4;
        x2 = 3 * myImageWidth / 4;
        y2 = 3 * myImageHeight / 4;

        minDistance = PntTool.getDistance(0, 0, x1, y1);
        hogMatchHashMap = new HashMap();

        matrixA1 = new double[2][9];
        matrixA2 = new double[2][9];
        matrixA3 = new double[2][9];
        matrixA4 = new double[2][9];
        matrixA2nx9 = new double[8][9];
        setMatchDstnceThrshld(25);
        imageDrawPixels = getImageDrawData();                

        distance1 = minDistance;
        distance2 = minDistance;
        distance3 = minDistance;
        distance4 = minDistance;    
    }

    public void filter(int[] grayValues, int i) {
        super.filter(grayValues, i); 
        HOGPosition aHOGPosition = getHOGPosition();
        computeEcldnTrnsfrmtn(aHOGPosition);
    }

    public double computeEcldnTrnsfrmtn(HOGPosition myHOGPosition) {
        //super.computeHOG1x1(myHOGPosition, myHOGArrayList2); //To change body of generated methods, choose Tools | Templates.

        boolean aMatch = super.getIsMatch();
        if (aMatch == false) {
            return 0;
        }

        double aDistance1 = 0;
        double aDistance2 = 0;
        double aDistance3 = 0;
        double aDistance4 = 0;
        
        //System.out.println("DLTHOG: a distance = "+getDistance(myHOGPosition, x1, y1)+" distance 1 = "+distance1);
        //System.out.println("DLTHOG: a x1 = "+x1+", y1 = "+y1);
        //System.out.println("DLTHOG: a x2 = "+x2+", y2 = "+y2);
        
        if ((aDistance1 = getDistance(myHOGPosition, x1, y1)) <= distance1 && numberOfPoints1 == 0) {
            matchHOGPosition1 = myHOGPosition;
            matrixA1 = rtrveMatrix2x9(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
            //matrixB1 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA1, 0);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB1, 0);            
            numberOfPoints1++;
            distance1 = aDistance1;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition1,matchHOGPosition1.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test1");
            return distance1;
        } else if ((aDistance2 = getDistance(myHOGPosition, x2, y1)) <= distance2 && numberOfPoints2 == 0) {
            matchHOGPosition2 = myHOGPosition;
            matrixA2 = rtrveMatrix2x9(matchHOGPosition2, matchHOGPosition2.rtrveHOGMatch());
            //matrixB2 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA2, 2);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB2, 2);            
            numberOfPoints2++;
            distance2 = aDistance2;
            updtCornerPixels(x2, y1,0xff00ff00);
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition2,matchHOGPosition2.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test2");
            return distance2;
        } else if ((aDistance3 = getDistance(myHOGPosition, x1, y2)) <= distance3 && numberOfPoints3 == 0) {
            matchHOGPosition3 = myHOGPosition;
            matrixA3 = rtrveMatrix2x9(matchHOGPosition3, matchHOGPosition3.rtrveHOGMatch());
            //matrixB3 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());
            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA3, 4);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB3, 4);            
            numberOfPoints3++;
            distance3 = aDistance3;
            updtCornerPixels(x1,y2,0xff00ff00);            
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition3,matchHOGPosition3.rtrveHOGMatch());
            shouldDraw = false;
            System.out.println("EuclideanTransFrameApp: test3");            
            return distance3;            
        } else if ((aDistance4 = getDistance(myHOGPosition, x2, y2)) <= distance4 && numberOfPoints4 == 0) {
            matchHOGPosition4 = myHOGPosition;
            matrixA4 = rtrveMatrix2x9(matchHOGPosition4, matchHOGPosition4.rtrveHOGMatch());
            //matrixB4 = createMatrix2x1(matchHOGPosition1, matchHOGPosition1.rtrveHOGMatch());            
            matrixA2nx9 = createMatrix2nx9(matrixA2nx9, matrixA4, 6);
            //matrixB2nx1 = createMatrix2nx9(matrixB2nx1, matrixB4, 6);            
            numberOfPoints4++;
            distance4 = aDistance4;
            shouldDraw = true;
            drawHOGArrow(matchHOGPosition4,matchHOGPosition4.rtrveHOGMatch());
            shouldDraw = false;            
            System.out.println("EuclideanTransFrameApp: test4");            
            return distance4;      
        }
        if (numberOfPoints1 > 0 && numberOfPoints2 > 0 && numberOfPoints3 > 0 && numberOfPoints4 > 0) {
            aGssnElmntn = new GssnElmntn();
            aBckwrdSub = new BckwrdSub();                       
                       
            //Matrix.print(matrixA2nx9);
            double matrixC[][] = aGssnElmntn.process(matrixA2nx9); 
            //Matrix.print(matrixC);
            int aPivot[] = aGssnElmntn.getPivot();
            double x[] = aBckwrdSub.solve(matrixC);
            //Matrix.print(x);
            return 0;
        }
        return 0;
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
        
        int x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), getImageWidth());
        int x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        int y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), getImageWidth());
        
        //System.out.println("DLTHOG Match : x1 = "+x1+",y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //System.out.println("DLTHOG number of matches :"+numberOfPoints1+", "+numberOfPoints2+", "+numberOfPoints3+", "+numberOfPoints4);
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

        return aMatrixA;
    }
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
    }
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
        return aMatrixA2nx9;
    }
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
    }    

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