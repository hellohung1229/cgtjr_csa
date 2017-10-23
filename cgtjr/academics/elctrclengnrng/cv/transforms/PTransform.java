/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.transforms;

import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author cgthomasjr
 */
public class PTransform {
    
    private double PMatrix[][];    
    private double rotation[][];       
    
    public PTransform(){
       PMatrix = new double[3][4];       
    }
    public PTransform(double myPMatrix[][]){
       PMatrix = myPMatrix;       
    }
    /*
    public PTransform(KConstraints myKConstraints){
       //HMatrix = myHMatrix;       
    }    
    public double[][] computeHomography(KConstraints myKConstraints){
        myKConstraints.getVanishingLine();
        //myKConstraints.
        return null;
    }*/
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

        x2[0] = PMatrix[0][0]*myX1[0]+PMatrix[0][1]*myX1[1]+PMatrix[0][2]*myX1[2]+PMatrix[0][3]*myX1[3];
        x2[1] = PMatrix[1][0]*myX1[0]+PMatrix[1][1]*myX1[1]+PMatrix[1][2]*myX1[2]+PMatrix[0][3]*myX1[3];                
        x2[2] = PMatrix[2][0]*myX1[0]+PMatrix[2][1]*myX1[1]+PMatrix[2][2]*myX1[2]+PMatrix[0][3]*myX1[3];  

        x3[0] = x2[0]/x2[2];//Math.round(x2[0]/x2[2]);
        x3[1] = x2[1]/x2[2];//Math.round(x2[1]/x2[2]);
        x3[2] = x2[2]/x2[2];//Math.round(x2[2]/x2[2]);

        System.out.println("HTransform.computeTransformation() .... ");
        //Matrix.print(myX1);        
        //Matrix.print(PMatrix);  
        //Matrix.print(x3);        
        return x3;
    } 
    public double[][] getPMatrix() {
        return PMatrix;
    }
    public void setPMatrix(double[][] myPMatrix) {
        this.PMatrix = myPMatrix;
    }   
}