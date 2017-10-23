/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author Nit
 */
public class QRTrnspseGrmSchmdtTest {

    private static double matrix1[][] = {{5, 1, 3, 1, 4, 7, 9, 1, 20},
        {10, 2, 3, 4, 1, 2, 6, 8, 2},
        {2, 1, 1, 4, 8, 1, 2, 6, 5},
        {1, 2, 4, 5, 1, 8, 1, 2, 8},
        {1, 1, 2, 1, 10, 1, 8, 1, 10},
        {1, 2, 1, 4, 1, 4, 7, 4, 1},
        {5, 10, 10, 1, 9, 10, 4, 1, 3},
        {7, 8, 3, 1, 1, 4, 1, 8, 1},
        {8, 10, 4, 10, 9, 1, 10, 1, 9}};
    
    private static double matrix3[][] = {
        {1, 2, 1, 1, 0, 1, 14.4, 1, 100},
        {1.9, 2, 1, 1, 1.2, 1, 1, 1, 1},
        {1, 1, 3, 1, 1, 1, 1, 0, 1},
        {1, 120, 1, 4, 1, 12, 1, 1, 1},
        {1, 0, 1, 1, 5, 1, 1, 1, 1},
        {1, 190, 2.4, 1, 1, 6, 1, 1, 1},
        {1, 10, 1, 0.5, 1, 1, 7, 1, 1.6},
        {1.85, 1, 0, 1, 1, 0, 1, 8, 1}};

    private static double matrix2[][] = {{1, 1, 1, 1, 4.1, 1, 1, 1, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 20, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 1, 1.4, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 0, 1, 1}};
    
    private static double matrix1a[][] = {{1, 2, 3}, {2, 1, 0}, {3, 0, 3}};
    
    private static double matrix2a[][] = {{353.553,339.645,277.744}, {-103.528,23.3212,459.607}, {0.707107, -0.353553, 0.612372}};    

    public static void main(String args[]) {
        //Matrix.print(matrix2a);
        QRTrnspseGrmSchmdt aQRTrnspseGrmSchmdt = new QRTrnspseGrmSchmdt();
        aQRTrnspseGrmSchmdt.compute3x3(matrix2a);
        double q[][] = aQRTrnspseGrmSchmdt.getQTF();
        double r[][] = aQRTrnspseGrmSchmdt.getRTF();


        //Matrix.print(q);
        //Matrix.print(r);
    }
}