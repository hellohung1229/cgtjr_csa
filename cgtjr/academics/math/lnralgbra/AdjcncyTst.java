/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author Nit
 */
public class AdjcncyTst 
{
   private static int matrix1[][] = {{0,1,0,1,0,0,0,0,0},
                      {1,0,1,0,1,0,0,0,0},
                      {0,1,0,0,0,1,0,0,0},
                      {1,0,0,0,1,0,1,0,0},
                      {0,1,0,1,0,1,0,1,0},
                      {0,0,1,0,1,0,0,0,1},
                      {0,0,0,1,0,0,0,1,0},
                      {0,0,0,0,1,0,1,0,1},
                      {0,0,0,0,0,1,0,1,0}};
   
   private static int matrix2[][] = new int[9][9];
   private static int matrix3[][] = new int[9][9];   
   
   public static void main(String args[])
   {
      matrix2 = Matrix.mltplyMxMxMxM(matrix1,matrix1);
      matrix3 = Matrix.mltplyMxMxMxM(matrix1,matrix2);      
      //Matrix.print(matrix1);
      //Matrix.print(matrix2);
      //Matrix.print(matrix3);      
   }
   
}
