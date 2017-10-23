/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.transforms;

import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author cgthomasjr
 */
public class ManualCrrspndnce {

    private double matrixA1[][];
    private double matrixA2[][];
    private double matrixA3[][];
    private double matrixA4[][];
    private double matrixA5[][];
    private double matrixA6[][];
    private double matrixA7[][];
    private double matrixA8[][];
        
    private double matrixANx9[][];
    
    private int numberOfPoints1;
    private int numberOfPoints2;
    private int numberOfPoints3;
    private int numberOfPoints4;    
         
    private HOGPosition aHOGPosition1_1;
    private HOGPosition aHOGPosition1_2;
    private HOGPosition aHOGPosition1_3;
    private HOGPosition aHOGPosition1_4;
    private HOGPosition aHOGPosition1_5;
    private HOGPosition aHOGPosition1_6;
    private HOGPosition aHOGPosition1_7;
    private HOGPosition aHOGPosition1_8;    

    private HOGPosition aHOGPosition2_1;
    private HOGPosition aHOGPosition2_2;
    private HOGPosition aHOGPosition2_3;
    private HOGPosition aHOGPosition2_4;
    private HOGPosition aHOGPosition2_5;
    private HOGPosition aHOGPosition2_6;
    private HOGPosition aHOGPosition2_7;
    private HOGPosition aHOGPosition2_8;    
    
    private HOGPosition matchHOGPosition1;
    private HOGPosition matchHOGPosition2;
    private HOGPosition matchHOGPosition3;
    private HOGPosition matchHOGPosition4;
    
    private double p1x1 = 128;
    private double p1y1 = 111;
    private double p1x2 = 129;
    private double p1y2 = 183;
    private double p1x3 = 7;
    private double p1y3 = 215;
    private double p1x4 = 183;
    private double p1y4 = 69;
    
    private double p1x5 = 221;
    private double p1y5 = 69;
    private double p1x6 = 174;
    private double p1y6 = 162;
    private double p1x7 = 241;
    private double p1y7 = 210;
    private double p1x8 = 335;
    private double p1y8 = 268;    
    
    private double p2x1 = 149;
    private double p2y1 = 126;
    private double p2x2 = 150;
    private double p2y2 = 197;
    private double p2x3 = 32;
    private double p2y3 = 229;
    private double p2x4 = 203;
    private double p2y4 = 84;

    private double p2x5 = 240;
    private double p2y5 = 84;
    private double p2x6 = 194;
    private double p2y6 = 176;
    private double p2x7 = 262;
    private double p2y7 = 225;
    private double p2x8 = 360;
    private double p2y8 = 286;    
    
    private int imageWidth;
    private int imageHeight;

    public ManualCrrspndnce(int myImageWidth, int myImageHeight) {
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
    }    

    public double[][] crteCrrspndnceMatrix(){
        if(aHOGPosition2_1 == null){
            aHOGPosition2_1 = new HOGPosition(getP2x1(),getP2y1());
        } 
        if(aHOGPosition2_2 == null){            
            aHOGPosition2_2 = new HOGPosition(getP2x2(),getP2y2());
        } 
        if(aHOGPosition2_3 == null){
            aHOGPosition2_3 = new HOGPosition(getP2x3(),getP2y3());
        }         
        if(aHOGPosition2_4 == null){
            aHOGPosition2_4 = new HOGPosition(getP2x4(),getP2y4());
        }       
        if(aHOGPosition2_5 == null){
            aHOGPosition2_5 = new HOGPosition(getP2x5(),getP2y5());
        } 
        if(aHOGPosition2_6 == null){
            aHOGPosition2_6 = new HOGPosition(getP2x6(),getP2y6());
        }         
        if(aHOGPosition2_7 == null){            
            aHOGPosition2_7 = new HOGPosition(getP2x7(),getP2y7());
        } 
        if(aHOGPosition2_8 == null){
            aHOGPosition2_8 = new HOGPosition(getP2x8(),getP2y8());
        }                                         
        if(aHOGPosition1_1 == null){
            aHOGPosition1_1 = new HOGPosition(getP1x1(),getP1y1());
            aHOGPosition2_1.connectHOGMatch(aHOGPosition1_1);        
            matrixA1 = rtrveMatrix1x9(aHOGPosition2_1, aHOGPosition2_1.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA1, 0);                    
        }         
        if(aHOGPosition1_2 == null){
            aHOGPosition1_2 = new HOGPosition(getP1x2(),getP1y2());
            aHOGPosition2_2.connectHOGMatch(aHOGPosition1_2);
            matrixA2 = rtrveMatrix1x9(aHOGPosition2_2, aHOGPosition2_2.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA2, 1);                                
        } 
        if(aHOGPosition1_3 == null){
            aHOGPosition1_3 = new HOGPosition(getP1x3(),getP1y3());
            aHOGPosition2_3.connectHOGMatch(aHOGPosition1_3);         
            matrixA3 = rtrveMatrix1x9(aHOGPosition2_3, aHOGPosition2_3.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA3, 2);                                          
        }         
        if(aHOGPosition1_4 == null){
            aHOGPosition1_4 = new HOGPosition(getP1x4(),getP1y4());
            aHOGPosition2_4.connectHOGMatch(aHOGPosition1_4);
            matrixA4 = rtrveMatrix1x9(aHOGPosition2_4, aHOGPosition2_4.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA4, 3);                                          
        }   
        if(aHOGPosition1_5 == null){
            aHOGPosition1_5 = new HOGPosition(getP1x5(),getP1y5());
            aHOGPosition2_5.connectHOGMatch(aHOGPosition1_5);        
            matrixA5 = rtrveMatrix1x9(aHOGPosition2_5, aHOGPosition2_5.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA5, 4);                                          
        }         
        if(aHOGPosition1_6 == null){
            aHOGPosition1_6 = new HOGPosition(getP1x6(),getP1y6());
            aHOGPosition2_6.connectHOGMatch(aHOGPosition1_6);
            matrixA6 = rtrveMatrix1x9(aHOGPosition2_6, aHOGPosition2_6.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA6, 5);                                          
        } 
        if(aHOGPosition1_7 == null){
            aHOGPosition1_7 = new HOGPosition(getP1x7(),getP1y7());
            aHOGPosition2_7.connectHOGMatch(aHOGPosition1_7);            
            matrixA7 = rtrveMatrix1x9(aHOGPosition2_7, aHOGPosition2_7.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA7, 6);                                         
        }         
        if(aHOGPosition1_8 == null){
            aHOGPosition1_8 = new HOGPosition(getP1x8(),getP1y8());
            aHOGPosition2_8.connectHOGMatch(aHOGPosition1_8);
            matrixA8 = rtrveMatrix1x9(aHOGPosition2_8, aHOGPosition2_8.rtrveHOGMatch());
            matrixANx9 = updateMatrixNx9(matrixANx9, matrixA8, 7);                                         
            
        }           
        return matrixANx9;
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

    public double getP1x5() {
        return p1x5;
    }

    public void setP1x5(double p1x5) {
        this.p1x5 = p1x5;
    }

    public double getP1y5() {
        return p1y5;
    }

    public void setP1y5(double p1y5) {
        this.p1y5 = p1y5;
    }

    public double getP1x6() {
        return p1x6;
    }

    public void setP1x6(double p1x6) {
        this.p1x6 = p1x6;
    }

    public double getP1y6() {
        return p1y6;
    }

    public void setP1y6(double p1y6) {
        this.p1y6 = p1y6;
    }

    public double getP1x7() {
        return p1x7;
    }

    public void setP1x7(double p1x7) {
        this.p1x7 = p1x7;
    }

    public double getP1y7() {
        return p1y7;
    }

    public void setP1y7(double p1y7) {
        this.p1y7 = p1y7;
    }

    public double getP1x8() {
        return p1x8;
    }

    public void setP1x8(double p1x8) {
        this.p1x8 = p1x8;
    }

    public double getP1y8() {
        return p1y8;
    }

    public void setP1y8(double p1y8) {
        this.p1y8 = p1y8;
    }

    public double getP2x5() {
        return p2x5;
    }

    public void setP2x5(double p2x5) {
        this.p2x5 = p2x5;
    }

    public double getP2y5() {
        return p2y5;
    }

    public void setP2y5(double p2y5) {
        this.p2y5 = p2y5;
    }

    public double getP2x6() {
        return p2x6;
    }

    public void setP2x6(double p2x6) {
        this.p2x6 = p2x6;
    }

    public double getP2y6() {
        return p2y6;
    }

    public void setP2y6(double p2y6) {
        this.p2y6 = p2y6;
    }

    public double getP2x7() {
        return p2x7;
    }

    public void setP2x7(double p2x7) {
        this.p2x7 = p2x7;
    }

    public double getP2y7() {
        return p2y7;
    }

    public void setP2y7(double p2y7) {
        this.p2y7 = p2y7;
    }

    public double getP2x8() {
        return p2x8;
    }

    public void setP2x8(double p2x8) {
        this.p2x8 = p2x8;
    }

    public double getP2y8() {
        return p2y8;
    }

    public void setP2y8(double p2y8) {
        this.p2y8 = p2y8;
    }
    private double[][] rtrveMatrix1x9(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        double aMatrixA[][] = new double[1][9];

        HOGPosition aHOGPosition1 = myHOGPosition1;
        HOGPosition aHOGPosition2 = myHOGPosition2;
        
        if(aHOGPosition1==null){
            System.err.println("Correspondence: aHOGPosition1 is null!");
        }
        if(aHOGPosition2==null){
            System.err.println("Correspondence: aHOGPosition2 is null!");
        }
        
        int x1 = (int)aHOGPosition1.getX();
        int y1 = (int)aHOGPosition1.getY();
        int x2 = (int)aHOGPosition2.getX();
        int y2 = (int)aHOGPosition2.getY();
        
        //int x1 = ImageTool.rtrvXPstn(aHOGPosition1.getPositionIndex(), imageWidth);
        //int y1 = ImageTool.rtrvYPstn(aHOGPosition1.getPositionIndex(), imageWidth);
        //int x2 = ImageTool.rtrvXPstn(aHOGPosition2.getPositionIndex(), imageWidth);
        //int y2 = ImageTool.rtrvYPstn(aHOGPosition2.getPositionIndex(), imageWidth);
        
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

    public HOGPosition getaHOGPosition1_1() {
        return aHOGPosition1_1;
    }

    public HOGPosition getaHOGPosition1_2() {
        return aHOGPosition1_2;
    }

    public HOGPosition getaHOGPosition1_3() {
        return aHOGPosition1_3;
    }

    public HOGPosition getaHOGPosition1_4() {
        return aHOGPosition1_4;
    }

    public HOGPosition getaHOGPosition1_5() {
        return aHOGPosition1_5;
    }

    public HOGPosition getaHOGPosition1_6() {
        return aHOGPosition1_6;
    }

    public HOGPosition getaHOGPosition1_7() {
        return aHOGPosition1_7;
    }

    public HOGPosition getaHOGPosition1_8() {
        return aHOGPosition1_8;
    }

    public HOGPosition getaHOGPosition2_1() {
        return aHOGPosition2_1;
    }

    public HOGPosition getaHOGPosition2_2() {
        return aHOGPosition2_2;
    }

    public HOGPosition getaHOGPosition2_3() {
        return aHOGPosition2_3;
    }

    public HOGPosition getaHOGPosition2_4() {
        return aHOGPosition2_4;
    }

    public HOGPosition getaHOGPosition2_5() {
        return aHOGPosition2_5;
    }

    public HOGPosition getaHOGPosition2_6() {
        return aHOGPosition2_6;
    }

    public HOGPosition getaHOGPosition2_7() {
        return aHOGPosition2_7;
    }

    public HOGPosition getaHOGPosition2_8() {
        return aHOGPosition2_8;
    }    
}