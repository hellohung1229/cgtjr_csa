/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.texture;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.YSclFltr;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class LawTxtrFltr extends YSclFltr {

    private int grayValues[];
    private int txtreValues[];
    private int imageWidth;
    private int imageHeight;
    private int textureKrnl[][];
    private int cnvlvsum;
    private double masksum;
    private int inputValues[];

    public LawTxtrFltr() {
        LawTxtrKrnl aLawTxtrKrnl = new LawTxtrKrnl();
        textureKrnl = aLawTxtrKrnl.getKrnl(LawTxtrKrnl.getE5(), LawTxtrKrnl.getE5());
    }
    public LawTxtrFltr(int myKrnl1,int myKrnl2) {
        LawTxtrKrnl aLawTxtrKrnl = new LawTxtrKrnl();
        textureKrnl = aLawTxtrKrnl.getKrnl(myKrnl1,myKrnl2);
    }    

    public LawTxtrFltr(String myFileName) {
        super(myFileName);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        txtreValues = new int[aWidth * aHeight];
        imageWidth = aWidth;
        imageHeight = aHeight;
        LawTxtrKrnl aLawTxtrKrnl = new LawTxtrKrnl();
        textureKrnl = aLawTxtrKrnl.getKrnlMask();
        //System.out.println("GrySclFltr: filename = "+myFileName);
        //grayValues = getFltrdData();      
    }

    public LawTxtrFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        LawTxtrKrnl aLawTxtrKrnl = new LawTxtrKrnl();
        textureKrnl = aLawTxtrKrnl.getKrnlMask();
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        txtreValues = new int[myImageWidth * myImageHeight];
        imageWidth = myImageWidth;
        imageHeight = myImageHeight;
        ImageFilter inputFilter = getInputFilter();
        if (inputFilter != null) {
            inputValues = inputFilter.getFltrdData();
        }
    }
    public void filter(int myOriginalValues[], int i) {
        if (inputValues != null) {
            super.filter(inputValues, i);
        } else {
            super.filter(myOriginalValues, i);
        }
        grayValues = super.getFltrdData();
        int imageWidth2 = 2 * imageWidth;
        if (ImageFilter.isInBounds5x5(i, imageWidth, imageHeight)) {
            //System.out.println("MedianFilter: orignalValues["+i+"]="+myOriginalValues[i]+", grayValues["+i+"] = "+grayValues[i]+", textureKrnl[1][1] = "+textureKrnl[1][1]);
            cnvlvsum =
                    grayValues[i - imageWidth2 - 2] * textureKrnl[0][0]
                    + grayValues[i - imageWidth2 - 1] * textureKrnl[0][1]
                    + grayValues[i - imageWidth2] * textureKrnl[0][2]
                    + grayValues[i - imageWidth2 + 1] * textureKrnl[0][3]
                    + grayValues[i - imageWidth2 + 2] * textureKrnl[0][4]
                    + grayValues[i - imageWidth - 2] * textureKrnl[1][0]
                    + grayValues[i - imageWidth - 1] * textureKrnl[1][1]
                    + grayValues[i - imageWidth] * textureKrnl[1][2]
                    + grayValues[i - imageWidth + 1] * textureKrnl[1][3]
                    + grayValues[i - imageWidth + 2] * textureKrnl[1][4]
                    + grayValues[i - 2] * textureKrnl[2][0]
                    + grayValues[i - 1] * textureKrnl[2][1]
                    + grayValues[i] * textureKrnl[2][2]
                    + grayValues[i + 1] * textureKrnl[2][3]
                    + grayValues[i + 2] * textureKrnl[2][4]
                    + grayValues[i + imageWidth - 2] * textureKrnl[3][0]
                    + grayValues[i + imageWidth - 1] * textureKrnl[3][1]
                    + grayValues[i + imageWidth] * textureKrnl[3][2]
                    + grayValues[i + imageWidth + 1] * textureKrnl[3][3]
                    + grayValues[i + imageWidth + 2] * textureKrnl[3][4]
                    + grayValues[i + imageWidth2 - 2] * textureKrnl[4][0]
                    + grayValues[i + imageWidth2 - 1] * textureKrnl[4][1]
                    + grayValues[i + imageWidth2] * textureKrnl[4][2]
                    + grayValues[i + imageWidth2 + 1] * textureKrnl[4][3]
                    + grayValues[i + imageWidth2 + 2] * textureKrnl[4][4];
            txtreValues[i] = ColorSpectra.cvrtYToGray(cnvlvsum);
        }
    }

    public int[] getFltrdData() {
        return txtreValues;
    }

    public int[] getGryVls() {
        return grayValues;
    }

    public void setGryVls(int myGryVls[]) {
        grayValues = myGryVls;
    }
}