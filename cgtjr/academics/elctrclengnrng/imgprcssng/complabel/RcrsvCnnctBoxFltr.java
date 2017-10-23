/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.math.geometry.linepnts.LineDraw;

/**
 *
 * @author clayton g thomas jr
 */
public class RcrsvCnnctBoxFltr extends ImageFilter {

    private int indexValues[];
    private int dataValues[];
    private int nmbrCmpnnts;
    private int colorDelta;
    private int labelIndex;
    private int inputValues[];
    private int width;
    private int height;
    private int clrdCmpnnts[];
    private int cmpnntColor;
    private int cmpnntGrp[];
    private static int groupXMax;
    private static int groupXMin;
    private static int groupYMax;
    private static int groupYMin;
    private static boolean boxReady;

    public RcrsvCnnctBoxFltr() {
        nmbrCmpnnts = 20;
        colorDelta = 255 / nmbrCmpnnts;
    }

    public int rtrvColor() {
        return rtrvColor(rtrvNextIndex());
    }

    public int rtrvColor(int myIndex) {
        int anIndex = myIndex % 20;
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
        width = getImageWidth();
        height = getImageHeight();
        indexValues = new int[width * height];
        //dataValues = new int[width * height];
        ImageFilter anImageFilter = getInputFilter();
        if (anImageFilter != null) {
            inputValues = anImageFilter.getFltrdData();
        }
        clrdCmpnnts = new int[width * height];
    }

    public void filter(int myOriginalValues[], int i) {
        if (inputValues != null) {
            dataValues = inputValues;
            compLabel(i);
        } else {
            dataValues = myOriginalValues;
            compLabel(i);
        }
        drawSquare();
        resetBounds();
        boxReady = false;
    }

    public void resetBounds() {
        groupXMax = -Integer.MAX_VALUE;
        groupXMin = Integer.MAX_VALUE;
        groupYMax = -Integer.MAX_VALUE;
        groupYMin = Integer.MAX_VALUE;
    }

    public void drawSquare() {
        if (boxReady) {
            System.out.println("groupXMin = "+groupXMin+", groupXmax = "+groupXMax+", groupYMin = "+groupYMin+", groupYMax = "+groupYMax);
            LineDraw.drawLine(groupXMin, groupYMin, groupXMax, groupYMin, clrdCmpnnts, width, height,cmpnntColor);
            LineDraw.drawLine(groupXMin,groupYMin,groupXMin,groupYMax, clrdCmpnnts, width, height,cmpnntColor);        
            LineDraw.drawLine(groupXMax,groupYMin,groupXMax,groupYMax, clrdCmpnnts, width, height,cmpnntColor);        
            LineDraw.drawLine(groupXMin,groupYMax,groupXMax,groupYMax, clrdCmpnnts, width, height,cmpnntColor);        
        }
    }
    public void updateBounds(int x, int y) {
        if (x >= groupXMax) {
            groupXMax = x;
        }
        if (x <= groupXMin) {
            groupXMin = x;
        }
        if (y >= groupYMax) {
            groupYMax = y;
        }
        if (y <= groupYMin) {
            groupYMin = y;
        }
    }

    public void compLabel(int i) {
        int aColor1 = ColorSpectra.rtrv000000ff(dataValues[i]);
        int aColor2 = ColorSpectra.rtrv000000ff(clrdCmpnnts[i]);        
        if (aColor1 == 0xff && aColor2 == 0x00) {
            cmpnntColor = rtrvColor();
        }
        int x = ImageTool.rtrvXPstn(i, width);
        int y = ImageTool.rtrvYPstn(i, width);
        compLabel(x, y, cmpnntColor);
    }

    void compLabel(int i, int j, int m) {

        int pixelValue1 = getPixel(dataValues, i, j);
        int pixelValue2 = getPixel(clrdCmpnnts, i, j);
        if (pixelValue1 == 0xff && pixelValue2 == 0x00) {
            boxReady = true;
            updateBounds(i, j);
            setPixel(clrdCmpnnts, i, j, m);
            //getPixel(i, j);
            compLabel(i - 1, j - 1, m);
            compLabel(i - 1, j, m);
            compLabel(i - 1, j + 1, m);
            compLabel(i, j - 1, m);
            compLabel(i, j + 1, m);
            compLabel(i + 1, j - 1, m);
            compLabel(i + 1, j, m);
            compLabel(i + 1, j + 1, m);
        }
    }
    private int getPixel(int i, int j) {
        return getPixel(dataValues, i, j);
    }
    private int getPixel(int data[], int i, int j) {
        int anIndex = 0;
        if (i >= 0 && j >= 0 && i < width && j < height) {
            anIndex = ImageTool.rtrvIndex(i, j, width);
            int aColor = ColorSpectra.rtrv000000ff(data[anIndex]);
            return aColor;
        } else {
            return 0x00;
        }
    }
    private void setPixel(int i, int j, int m) {
        setPixel(dataValues, i, j, m);
    }
    private void setPixel(int dataValues[], int i, int j, int m) {
        int anIndex = 0;
        if (i >= 0 && j >= 0 && i < width && j < height) {
            anIndex = ImageTool.rtrvIndex(i, j, width);
            dataValues[anIndex] = m;
        }
    }
    /*
     * public void cmpnntLabeling(int myOriginalValues[], int i) { int aTopIndex
     * = i - getImageWidth(); int leftIndex = i - 1;//Note: requires
     * modification int aWidth = getImageWidth(); int aHeight =
     * getImageHeight(); int indexValue = 0;
     *
     * //int leftColor = myOriginalValues[leftIndex] & 0x00ffffff; //int
     * topColor = myOriginalValues[aTopIndex] & 0x00ffffff; int color =
     * myOriginalValues[i] & 0x00ffffff;
     *
     * if (i > 0 && isInBounds1x1(leftIndex, aWidth, aHeight) &&
     * (myOriginalValues[leftIndex] & 0x00ffffff) != 0x00000000) { indexValue =
     * indexValues[leftIndex]; indexValues[i] = indexValue; dataValues[i] =
     * rtrvColor(indexValue); //System.out.println("CnnctCmpnntLbl,left:
     * dataValues[" + i + "]" + dataValues[i] +
     * ",myOriginalValues["+leftIndex+"]="+myOriginalValues[leftIndex]+",
     * indexValue=" + indexValue); } else if (i > 0 && isInBounds1x1(aTopIndex,
     * aWidth, aHeight) && (myOriginalValues[aTopIndex] & 0x00ffffff) !=
     * 0x00000000) { indexValue = indexValues[aTopIndex]; indexValues[i] =
     * indexValue; dataValues[i] = rtrvColor(indexValue);
     * //System.out.println("CnnctCmpnntLbl,top: dataValues[" + i + "]" +
     * dataValues[i] + ", indexValue=" + indexValue); } else if ((color &
     * 0x00ffffff) != 0x00000000) { indexValue = rtrvNextIndex(); indexValues[i]
     * = indexValue; dataValues[i] = rtrvColor(indexValue);
     * //System.out.println("CnnctCmpnntLbl,new: dataValues[" + i + "]" +
     * dataValues[i] + ", indexValue=" + indexValue); } else { dataValues[i] =
     * 0x00000000;//rtrvColor(indexValue);
     * //System.out.println("CnnctCmpnntLbl,left:
     * dataValues["+i+"]"+dataValues[i]+", indexValue="+indexValue); } }
     */

    public int[] getFltrdData() {
        return clrdCmpnnts;
    }
}