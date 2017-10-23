/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class CnnctCmpnntLbl extends ImageFilter {

    private int indexValues[];
    private int labelColor[];
    private int nmbrCmpnnts;
    private int colorDelta;
    private int labelIndex;
    private int inputValues[];
    private int colorList[] = {0x00ff0000, 0x0000ff00, 0x000000ff};

    

    public CnnctCmpnntLbl() {
        nmbrCmpnnts = 20;
        colorDelta = 255 / nmbrCmpnnts;
    }
    public int rtrvColor() {
        return rtrvColor(rtrvNextIndex());
    }
    public int rtrvColor(int myIndex) {        
        int anIndex = myIndex%20;
        float aColor = (anIndex * colorDelta) / 255.0f;
        int rgbColor[] = ColorSpectra.cnvrtHSBToRGB(aColor);
        //int rgbColor = colorList[myIndex];
        int rgb = ((rgbColor[0] & 0x000000ff) << 16)
                | ((rgbColor[1] & 0x000000ff) << 8)
                | ((rgbColor[2] & 0x000000ff));
        //ColorSpectra.cnvrtRGBGry(rgb);
        return rgb;
    }
    public int rtrvNextIndex() {
        labelIndex = labelIndex + 1;

        return labelIndex;
    }
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        indexValues = new int[aWidth * aHeight];
        labelColor = new int[aWidth * aHeight];
        ImageFilter anImageFilter = getInputFilter();
        if (anImageFilter != null) {
            inputValues = anImageFilter.getFltrdData();
        }
    }
    public void filter(int myOriginalValues[], int i) {

        if (inputValues != null) {
            cmpnntLabeling(inputValues, i);
        } else {
            cmpnntLabeling(myOriginalValues, i);
        }
    }
    public void cmpnntLabeling(int myOriginalValues[], int i) {
        int aTopIndex = i - getImageWidth();
        int leftIndex = i - 1;//Note: requires modification
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        int indexValue = 0;

        //int leftColor = myOriginalValues[leftIndex] & 0x00ffffff;
        //int topColor = myOriginalValues[aTopIndex] & 0x00ffffff;      
        int color = myOriginalValues[i] & 0x00ffffff;

        if (i > 0 && isInBounds1x1(leftIndex, aWidth, aHeight) && (myOriginalValues[leftIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[leftIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,left: labelColor[" + i + "]" + labelColor[i] + ",myOriginalValues["+leftIndex+"]="+myOriginalValues[leftIndex]+", indexValue=" + indexValue);
        } else if (i > 0 && isInBounds1x1(aTopIndex, aWidth, aHeight) && (myOriginalValues[aTopIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[aTopIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,top: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
        } else if ((color & 0x00ffffff) != 0x00000000) {
            indexValue = rtrvNextIndex();
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,new: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
        } else {
            labelColor[i] = 0x00000000;//rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,left: labelColor["+i+"]"+labelColor[i]+", indexValue="+indexValue);            
        }
    }

    public int[] getFltrdData() {
        return labelColor;
    }
}