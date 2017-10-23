/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.texture;

/**
 *
 * @author clayton g thomas jr
 */
public class LawTxtrKrnl {

    private int krnlMask[][];
    private int defaultWidth = 3;
    private int defaultHeight = 3;
    private int maskSum;
    private final static int L5 = 1;
    private final static int E5 = 2;
    private final static int S5 = 3;
    private final static int R5 = 4;
    private final static int L5V[] = {1, 4, 6, 4, 1};
    private final static int E5V[] = {-1, -2, 0, 2, 1};
    private final static int S5V[] = {-1, 0, 2, 0, -1};
    private final static int R5V[] = {1, -4, 6, -4, 1};

    public LawTxtrKrnl() {
        intlzKrnl3x3();
    }
    public static int getL5()
    {
        return L5;
    }
    public static int getE5()
    {
        return E5;
    }
    public static int getS5()
    {
        return S5;
    }
    public static int getR5()
    {
        return R5;
    }
    public void intlzKrnl3x3() {
        krnlMask = new int[defaultWidth][defaultHeight];

        krnlMask[0][0] = 1;
        krnlMask[0][1] = 1;
        krnlMask[0][2] = 1;
        krnlMask[1][0] = 1;
        krnlMask[1][1] = 1;
        krnlMask[1][2] = 1;
        krnlMask[2][0] = 1;
        krnlMask[2][1] = 1;
        krnlMask[2][2] = 1;

        maskSum = 9;
    }

    public int[][] getKrnl(int myTxtr1, int myTxtr2) {
        int txtrV1[] = null;
        int txtrV2[] = null;        
        int krnlMask[][] = new int[5][5];  
        
        if (myTxtr1 == 1) {
            txtrV1 = L5V;
        } else if (myTxtr1 == 2) {
            txtrV1 = E5V;
        } else if (myTxtr1 == 3) {
            txtrV1 = S5V;
        } else if (myTxtr1 == 4) {
            txtrV1 = R5V;
        }
        if (myTxtr2 == 1) {
            txtrV2 = L5V;
        } else if (myTxtr2 == 2) {
            txtrV2 = E5V;
        } else if (myTxtr2 == 3) {
            txtrV2 = S5V;
        } else if (myTxtr2 == 4) {
            txtrV2 = R5V;
        }        
        for(int i=0;i<5;i++)
        {
            krnlMask[i][0] = txtrV1[i]*txtrV2[0];
            krnlMask[i][1] = txtrV1[i]*txtrV2[1];            
            krnlMask[i][2] = txtrV1[i]*txtrV2[2];            
            krnlMask[i][3] = txtrV1[i]*txtrV2[3];
            krnlMask[i][4] = txtrV1[i]*txtrV2[4];                        
        }
        return krnlMask;
    }

    public void intlzKrnl5x5() {
        krnlMask = new int[5][5];

        krnlMask[0][0] = 1;
        krnlMask[0][1] = 1;
        krnlMask[0][2] = 1;
        krnlMask[0][3] = 1;
        krnlMask[0][4] = 1;
        krnlMask[1][0] = 1;
        krnlMask[1][1] = 1;
        krnlMask[1][2] = 1;
        krnlMask[1][3] = 1;
        krnlMask[1][4] = 1;
        krnlMask[2][0] = 1;
        krnlMask[2][1] = 1;
        krnlMask[2][2] = 1;
        krnlMask[2][3] = 1;
        krnlMask[2][4] = 1;
        krnlMask[3][0] = 1;
        krnlMask[3][1] = 1;
        krnlMask[3][2] = 1;
        krnlMask[3][3] = 1;
        krnlMask[3][4] = 1;
        krnlMask[4][0] = 1;
        krnlMask[4][1] = 1;
        krnlMask[4][2] = 1;
        krnlMask[4][3] = 1;
        krnlMask[4][4] = 1;

        maskSum = 25;
    }

    public int[][] getKrnlMask() {
        return krnlMask;
    }

    public int getMaskSum() {
        return maskSum;
    }
}