/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author finitesystem
 */
public class GramSchmidt {

    int n = 0;
    double r[][] = new double[3][3];
    double q[][] = new double[3][3];

    //double a[] = new double[8];
    public void cmpteQRByGrmSchmdt(double a[][]) {

        int rLength = a.length;
        int cLength = a[0].length;
        r = new double[cLength][cLength];
        q = new double[rLength][cLength];
                
        for (int k = 0; k < cLength; k++) {
            r[k][k] = cmpteMag(a, k);
            for (int b = 0; b < rLength; b++) {
                q[b][k] = (1 / r[k][k]) * a[b][k];
            }
            for (int j = k + 1; j < cLength; j++) {
                for (int t = 0; t < rLength; t++) {
                    r[k][j] += q[t][k] * a[t][j];
                }
                for (int s = 0; s < rLength; s++) {
                    a[s][j] = a[s][j] - r[k][j] * q[s][k];
                }
            }
        }
    }
    public double cmpteMag(double a[][], int myColumn) {
        int aLength = a.length;
        double aSum = 0;
        double aMag = 0;
        for (int i = 0; i < aLength; i++) {
            aSum += a[i][myColumn] * a[i][myColumn];
        }
        aMag = Math.sqrt(aSum);
        return aMag;
    }
    public double multiply(double q[][], double a[][], int myColumn) {
        int aLength = a.length;
        double aSum = 0;
        double aMag = 0;
        for (int i = 0; i < aLength; i++) {
            aSum += q[i][myColumn] * a[i][myColumn];
        }
        return aSum;
    }
    public double[][] retrieveQ(){
        return q;
    }
    public double[][] retrieveR(){
        return r;
    }
}