/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;
/**
 *
 * @author clayton g thomas jr
 */
public class YSclFltrTmp extends ImageFilter {

    private int grayValues[];
    //private int imageWidth;

    public YSclFltrTmp() {
    }
    public void intlzeYsclFltr(int myImageWidth, int myImageHeight){
        if(grayValues == null){
           grayValues = new int[myImageWidth*myImageHeight];
        }     
    }
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        this.intlzeYsclFltr(myImageWidth, myImageHeight);
    }
    public void filter(int myOriginalValues[], int i) {
        //filter3x3(myOriginalValues,i);
        super.filter(myOriginalValues, i);
        gryFltr5x5(myOriginalValues, i);
    }
    /*
    public void filter(int myOriginalValues[]) {
        //filter3x3(myOriginalValues,i);
        int aLength = myOriginalValues.length;
        
        for(int j = 0;j<aLength;j++){
           super.filter(myOriginalValues, j);
           gryFltr5x5(myOriginalValues, j);
        }
    }*/
    public void filter3x3(int myOriginalValues[], int i) {
        //grayValues[i] = myOrignalValues[i];
        //grdntFilter(grayValues,i);      
        gryFltr3x3(myOriginalValues, i);
    }
    public void gryFltr3x3(int myX, int myY) {
        int anIndex = rtrvIndex(myX, myY);
        gryFltr3x3(anIndex);
    }
    public void gryFltr3x3(int i) {
        gryFltr3x3(getInptPxlData(), i);
    }
    public void gryFltr3x3(int originalValues[], int i) {
        int imageWidth = getImageWidth();
        //grayValues = getOtptPxlData();
        //System.out.println("ClrCnvrtFlter:test1: grayValue = "+grayValues[i]+", i="+i);
        //if (isInBounds3x3(i)) {
        try{
            //if ((i - imageWidth - 1) > 0 && (i + imageWidth + 1) < getImageHeight() * getImageWidth()) {
            //System.out.println("ClrCnvrtFlter:test2: grayValue = "+grayValues[i]+", i="+i);
            //System.out.println("ClrCnvrtFlter:grayValue = originalValues["+i+"]"+originalValues[i]);
            grayValues[i - imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth - 1]);
            grayValues[i - imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth]);
            grayValues[i - imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth + 1]);
            grayValues[i - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - 1]);
            grayValues[i] = (short)ColorSpectra.convertRGBToY(originalValues[i]);
            grayValues[i + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + 1]);
            grayValues[i + imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth - 1]);
            grayValues[i + imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth]);
            grayValues[i + imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth + 1]);
            //System.out.println("ClrCnvrtFlter:grayValue = grayValues["+i+"]"+grayValues[i]);
        }catch(ArrayIndexOutOfBoundsException aiobe){            
        }
        //}
    }
    public int gryFltr1x1(int myOriginalValues[], int i) {             
        try {
            grayValues[i] = ColorSpectra.convertRGBToY(myOriginalValues[i]);
            getImageDrawData().updatePixels(grayValues, i);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
        }
        return grayValues[i];
    }
    public void gryFltr1x1(int myOriginalValues[]) {
        //filter3x3(myOriginalValues,i);
        int aLength = myOriginalValues.length;
        //System.out.println("YscclFtrTmp: aLength = "+aLength);
        for(int j = 0;j<aLength;j++){
           //super.filter(myOriginalValues, j);
           gryFltr1x1(myOriginalValues, j);           
        }
    }
    /*
    public void gryFltr5x5(int originalValues[], int i) {
        int imageWidth = getImageWidth();
        //grayValues = getOtptPxlData();
        //System.out.println("ClrCnvrtFlter:test1: grayValue = "+grayValues[i]+", i="+i);
        try {
            //System.out.println("ClrCnvrtFlter:test2: grayValue = "+grayValues[i]+", i="+i);
            //System.out.println("ClrCnvrtFlter:grayValue = originalValues["+i+"]"+originalValues[i]);
            grayValues[i - 2 * imageWidth - 2] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2 * imageWidth - 2]);
            grayValues[i - 2 * imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2 * imageWidth - 1]);
            grayValues[i - 2 * imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2 * imageWidth]);
            grayValues[i - 2 * imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2 * imageWidth + 1]);
            grayValues[i - 2 * imageWidth + 2] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2 * imageWidth + 2]);

            grayValues[i - imageWidth - 2] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth - 2]);
            grayValues[i - imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth - 1]);
            grayValues[i - imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth]);
            grayValues[i - imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth + 1]);
            grayValues[i - imageWidth + 2] = (short)ColorSpectra.convertRGBToY(originalValues[i - imageWidth + 2]);

            grayValues[i - 2] = (short)ColorSpectra.convertRGBToY(originalValues[i - 2]);
            grayValues[i - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i - 1]);
            grayValues[i] = (short)ColorSpectra.convertRGBToY(originalValues[i]);
            grayValues[i + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + 1]);
            grayValues[i + 2] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2]);

            grayValues[i + imageWidth - 2] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth - 2]);
            grayValues[i + imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth - 1]);
            grayValues[i + imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth]);
            grayValues[i + imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth + 1]);
            grayValues[i + imageWidth + 2] = (short)ColorSpectra.convertRGBToY(originalValues[i + imageWidth + 2]);

            grayValues[i + 2 * imageWidth - 2] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2 * imageWidth - 2]);
            grayValues[i + 2 * imageWidth - 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2 * imageWidth - 1]);
            grayValues[i + 2 * imageWidth] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2 * imageWidth]);
            grayValues[i + 2 * imageWidth + 1] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2 * imageWidth + 1]);
            grayValues[i + 2 * imageWidth + 2] = (short)ColorSpectra.convertRGBToY(originalValues[i + 2 * imageWidth + 2]);
            //grayValues = originalValues;
            //System.out.println("ClrCnvrtFlter:grayValue = grayValues["+i+"]"+grayValues[i]);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
        }
    } */ 
    public void gryFltr5x5(int originalValues[], int i) {
        int imageWidth = getImageWidth();
        //grayValues = getOtptPxlData();
        //System.out.println("ClrCnvrtFlter:test1: grayValue = "+grayValues[i]+", i="+i);
        try {
            //System.out.println("ClrCnvrtFlter:test2: grayValue = "+grayValues[i]+", i="+i);
            //System.out.println("ClrCnvrtFlter:grayValue = originalValues["+i+"]"+originalValues[i]);
            for(int j=-2;j<=2;j++){
                for(int k=-2;k<=2;k++){
                   grayValues[i - j * imageWidth - k] = ColorSpectra.convertRGBToY(originalValues[i - j * imageWidth - k]);
                }
            }
            //grayValues = originalValues;
            //System.out.println("ClrCnvrtFlter:grayValue = grayValues["+i+"]"+grayValues[i]);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
        }
    }      
    public int[] rtrvFltrdData3x3(int myX, int myY) {
        return rtrvPixels3x3(myX, myY, grayValues);
    }

    public int[] getGryVls() {
        //return null;
        return grayValues;
    }
    public int getGryVls(int myIndex) {
        return grayValues[myIndex];
    }    
    public void setGryVls(int myGryVls[]) {
        grayValues = myGryVls;
    }
}