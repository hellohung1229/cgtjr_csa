/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.transforms;

import cgtjr.academics.elctrclengnrng.cv.KConstraints;
import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author cgthomasjr
 */
public class HTransform {
    
    private double HMatrix[][];    
    
    public HTransform(){
       HMatrix = new double[3][3];       
    }
    public HTransform(double myHMatrix[][]){
       HMatrix = myHMatrix;       
    }
    public HTransform(KConstraints myKConstraints){
       //HMatrix = myHMatrix;       
    }    
    public double[][] computeHomography(KConstraints myKConstraints){
        myKConstraints.getVanishingLine();
        //myKConstraints.
        return null;
    }
    public double[] computeTransformation(double myHMatrix[][],double myX1[]){
        double x2[] = new double[3];
        x2[0] = myHMatrix[0][0]*myX1[0]+myHMatrix[0][1]*myX1[1]+myHMatrix[0][2]*myX1[2];
        x2[1] = myHMatrix[1][0]*myX1[0]+myHMatrix[1][1]*myX1[1]+myHMatrix[1][2]*myX1[2];                
        x2[2] = myHMatrix[2][0]*myX1[0]+myHMatrix[2][1]*myX1[1]+myHMatrix[2][2]*myX1[2];        
        return x2;
    }    
    public double[] computeTransformation(double myX1[]){
        double x2[] = new double[3];
        double x3[] = new double[3];        

        x2[0] = HMatrix[0][0]*myX1[0]+HMatrix[0][1]*myX1[1]+HMatrix[0][2]*myX1[2];
        x2[1] = HMatrix[1][0]*myX1[0]+HMatrix[1][1]*myX1[1]+HMatrix[1][2]*myX1[2];                
        x2[2] = HMatrix[2][0]*myX1[0]+HMatrix[2][1]*myX1[1]+HMatrix[2][2]*myX1[2];  

        x3[0] = x2[0]/x2[2];//Math.round(x2[0]/x2[2]);
        x3[1] = x2[1]/x2[2];//Math.round(x2[1]/x2[2]);
        x3[2] = x2[2]/x2[2];//Math.round(x2[2]/x2[2]);

        //System.out.println("HTransform.computeTransformation() .... ");
        //Matrix.print(myX1);        
        //Matrix.print(HMatrix);  
        //Matrix.print(x3);        
        return x3;
    } 
    public double[][] getHMatrix() {
        return HMatrix;
    }
    public void setHMatrix(double[][] myHMatrix) {
        this.HMatrix = myHMatrix;
    }   
}