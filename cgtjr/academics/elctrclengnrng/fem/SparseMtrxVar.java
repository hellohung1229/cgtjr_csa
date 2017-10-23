package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.general.DataVar;

public class SparseMtrxVar extends DataVar {

    private static String dataNmKey = "sparsematrix";
    private String bandWidthKey = "bandwidth";
    private double sprsMtrx[][];
    private int bandWidthVal;

    public int updteBandWidthVal() {
        int aBandWidth = 0;
        int rows = sprsMtrx.length;
        int cols = sprsMtrx[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                aBandWidth = Math.abs(i - j);
                if (sprsMtrx[i][j] != 0 && aBandWidth > bandWidthVal) {
                    System.out.println("SparseMtrxVar: sprseMtrx = "+sprsMtrx[i][j]+", aBandWidth = "+aBandWidth+", i = "+i+",j = "+j);
                    bandWidthVal = aBandWidth;
                }
            }
        }
        return bandWidthVal;
    }
    public double[][] getSprsMtrx() {
        return sprsMtrx;
    }
    public void setSprsMtrx(double mySprsMtrx[][]) {
        sprsMtrx = mySprsMtrx;
    }
    public static void setDataNmKey(String dataNmKey) {
        SparseMtrxVar.dataNmKey = dataNmKey;
    }
    public static String getDataNmKey() {
        return dataNmKey;
    }
    public void setBandWidthVal(int bandWidthVal) {
        this.bandWidthVal = bandWidthVal;
    }

    public String getBandWidthKey() {
        return bandWidthKey;
    }

    public int getBandWidthVal() {
        return bandWidthVal;
    }
}