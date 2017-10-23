/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author Nit
 */
public class GssnSVD 
{
   private static double matrix1[][] = {{5,1,3,1,4,7,9,1,20},
                      {10,2,3,4,1,2,6,8,2},
                      {2,1,1,4,8,1,2,6,5},
                      {1,2,4,5,1,8,1,2,8},
                      {1,1,2,1,10,1,8,1,10},
                      {1,2,1,4,1,4,7,4,1},
                      {5,10,10,1,9,10,4,1,3},
                      {7,8,3,1,1,4,1,8,1},
                      {8,10,4,10,9,1,10,1,9}};
   
   
   public static void main(String args[]){
      GssnElmntn aGssnElmntn = new GssnElmntn();
      //Matrix.print(matrix1);
      double aGssnMtrix[][] = aGssnElmntn.process(matrix1);
      //Matrix.print(matrix1);      
   }   
   public double[][] createATA(double myMatrix[][]){
      double aMatrix1[][] = Matrix.transpose(myMatrix);
      double aMatrix2[][] = Matrix.mltplyMxMxMxM(aMatrix1,myMatrix);      
      return aMatrix2;
   }
   public double[][] createAAT(double myMatrix[][]){
      double aMatrix1[][] = Matrix.transpose(myMatrix);
      double aMatrix2[][] = Matrix.mltplyMxMxMxM(myMatrix,aMatrix1);      
      return aMatrix2;
   }
   
}