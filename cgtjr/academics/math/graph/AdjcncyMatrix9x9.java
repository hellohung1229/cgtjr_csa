/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.graph;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author clayton g thomas jr
 */
public class AdjcncyMatrix9x9 {

    private int matrix[][];

    public AdjcncyMatrix9x9() {
        matrix = new int[9][9];
    }

    public void insert(int i, int j, int myValue) {
        matrix[i][j] = myValue;
    }

    public void increment(int i, int j) {
        matrix[i][j] = matrix[i][j] + 1;
    }

    public static void increment(int i, int j, int myMatrix[][]) {
        myMatrix[i][j] = myMatrix[i][j] + 1;
        System.out.println("AdjcncyMatrix9x9: matrix["+i+"]["+j+"] = "+myMatrix[i][j]);
    }

    public static int[][] circuitA4(Pnt myPnt) {
        //Note: convert matrix to static and 
        int matrix[][] = new int[9][9];
        Pnt aRightPnt = myPnt.getPosXDrctn();
        Pnt aLeftPnt = myPnt.getNegXDrctn();
        Pnt aTopPnt = myPnt.getNegYDrctn();
        Pnt aBottomPnt = myPnt.getPosYDrctn();
         System.out.println("AdjacencyMatrix: pnt = "+myPnt);
        if (aRightPnt != null && aTopPnt != null) {
            Pnt aYPnt = aRightPnt.getNegYDrctn();
            Pnt aXPnt = aTopPnt.getPosXDrctn();   
            System.out.println("x pnt = "+aXPnt+", y pnt = "+aYPnt);
            if (aYPnt != null && aXPnt != null) {
                increment(4, 4, matrix);
                increment(5, 5, matrix);
                increment(2, 2, matrix);
                increment(1, 1, matrix);
            }
        }
       
        if (aTopPnt != null && aLeftPnt != null) {
            if (aTopPnt.getNegXDrctn() != null && aLeftPnt.getNegYDrctn() != null) {
                increment(4, 4, matrix);
                increment(1, 1, matrix);
                increment(0, 0, matrix);
                increment(3, 3, matrix);
            }
        }
         if (aLeftPnt != null && aBottomPnt != null) {
            if (aLeftPnt.getPosYDrctn() != null && aBottomPnt.getNegXDrctn() != null) {
                increment(4, 4, matrix);
                increment(3, 3, matrix);
                increment(6, 6, matrix);
                increment(7, 7, matrix);
            }
        }
        if (aBottomPnt != null && aRightPnt != null) {
            if (aBottomPnt.getPosXDrctn() != null && aRightPnt.getPosYDrctn() != null) {
                increment(4, 4, matrix);
                increment(7, 7, matrix);
                increment(8, 8, matrix);
                increment(5, 5, matrix);
            }
        }
        //Matrix.print(matrix);
        return matrix;
    }

    public static boolean isTopRight(int myMatrix[][]) {
        boolean tORf = false;
        if (myMatrix[4][4] == 3 && myMatrix[2][2] == 0) {
            tORf = true;
        }
        return tORf;
    }
    public static boolean isTopLeft(int myMatrix[][]) {
        boolean tORf = false;
        if (myMatrix[4][4] == 3 && myMatrix[0][0] == 0) {
            tORf = true;
        }
        return tORf;
    }   
    public static boolean isBottomRight(int myMatrix[][]) {
        boolean tORf = false;
        if (myMatrix[4][4] == 3 && myMatrix[8][8] == 0) {
            tORf = true;
        }
        return tORf;
    }        
    public static boolean isBottomLeft(int myMatrix[][]) {
        boolean tORf = false;
        if (myMatrix[4][4] == 3 && myMatrix[6][6] == 0) {
            tORf = true;
        }
        return tORf;
    }        
}