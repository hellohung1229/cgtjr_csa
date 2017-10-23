/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.texture;

import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author clayton g thomas jr
 */
public class TxtrEnergy {

    public static float cmptEnergy(float myMatrix[][]) {
        int rows = myMatrix.length;
        int cols = myMatrix[0].length;

        float total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += myMatrix[i][j] * myMatrix[i][j];
            }
        }
        return total;
    }

    public static float cmptEntropy(float myMatrix[][]) {
        int rows = myMatrix.length;
        int cols = myMatrix[0].length;

        float total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (myMatrix[i][j] != 0) {
                    total += myMatrix[i][j] * Math.log(myMatrix[i][j]);
                }
            }
        }
        return total;
    }

    public static float cmptContrast(float myMatrix[][]) {
        int rows = myMatrix.length;
        int cols = myMatrix[0].length;

        float total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += (i - j) * (i - j) * myMatrix[i][j];
            }
        }
        return total;
    }

    public static float cmptHomogeneity(float myMatrix[][]) {
        int rows = myMatrix.length;
        int cols = myMatrix[0].length;

        float total = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total += myMatrix[i][j] / (1 + Math.abs(i - j));
            }
        }
        return total;
    }

    public static void main(String args[]) {
        float matrix[][] = {{0.0f, 0.0f, 0.33f}, {0.33f, 0.0f, 0.33f}, {0.0f, 0.0f, 0.0f}};
        float energy = cmptEnergy(matrix);
        float entropy = cmptEntropy(matrix);
        float contrast = cmptContrast(matrix);
        float homogeneity = cmptHomogeneity(matrix);
        System.out.println("TxtrEnergy: energy = " + energy);
        System.out.println("TxtrEnergy: entropy = " + entropy);
        System.out.println("TxtrEnergy: contrast = " + contrast);
        System.out.println("TxtrEnergy: homogeneity = " + homogeneity);
    }
}
