/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.LineDraw;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author clayton g thomas jr
 */
public class CnnctCmpnntBoxLbl extends ImageFilter {

    private int indexValues[];
    private int labelColor[];
    private int nmbrCmpnnts;
    private int colorDelta;
    private int labelIndex;
    private int inputValues[];
    private int colorList[] = {0x00ff0000, 0x0000ff00, 0x000000ff};
    private static int groupXMax;
    private static int groupXMin;
    private static int groupYMax;
    private static int groupYMin;
    private HashMap colorBoxHashMap;
    private int aWidth;
    private int aHeight;
    private int cnnctdCmpnntData[];
    
    Dimension aBox;
    RectBoundary aCmpnntRectangle;

    public CnnctCmpnntBoxLbl() {
        nmbrCmpnnts = 100;
        colorDelta = 255 / nmbrCmpnnts;
    }

    public int rtrvColor() {
        return rtrvColor(rtrvNextIndex());
    }

    public int rtrvColor(int myIndex) {
        //int anIndex = myIndex % 20;
        float aColor = (myIndex * colorDelta) / 255.0f;
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
        aWidth = getImageWidth();
        aHeight = getImageHeight();
        indexValues = new int[aWidth * aHeight];
        labelColor = new int[aWidth * aHeight];
        ImageFilter anImageFilter = getInputFilter();
        colorBoxHashMap = new HashMap();
        if (anImageFilter != null) {
            inputValues = anImageFilter.getFltrdData();
        }
    }

    public void filter(int myOriginalValues[], int i) {

        cnnctdCmpnntData = myOriginalValues;
        if (inputValues != null) {
            cmpnntLabeling(inputValues, i);
        } else {
            cmpnntLabeling(myOriginalValues, i);
        }
    }

    public void cmpnntLabeling(int myOriginalValues[], int i) {
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();

        int topIndex = i - aWidth;
        int topLeftIndex = i - aWidth - 1;
        int topRightIndex = i - aWidth + 1;
        int leftIndex = i - 1;//Note: requires modification
        int indexValue = 0;

        //int leftColor = myOriginalValues[leftIndex] & 0x00ffffff;
        //int topColor = myOriginalValues[aTopIndex] & 0x00ffffff;      
        int color = myOriginalValues[i] & 0x00ffffff;

        if (i > 0 && isInBounds1x1(leftIndex, aWidth, aHeight) && (myOriginalValues[leftIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[leftIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            updateBoxHashMap(myOriginalValues,i,labelColor[i]);
            //System.out.println("CnnctCmpnntLbl,left: labelColor[" + i + "]" + labelColor[i] + ",myOriginalValues["+leftIndex+"]="+myOriginalValues[leftIndex]+", indexValue=" + indexValue);
        } else if (i > 0 && isInBounds1x1(topIndex, aWidth, aHeight) && (myOriginalValues[topIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[topIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,top: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
            updateBoxHashMap(myOriginalValues,i,labelColor[i]);
        } else if (i > 0 && isInBounds1x1(topRightIndex, aWidth, aHeight) && (myOriginalValues[topRightIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[topRightIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            updateBoxHashMap(myOriginalValues,i,labelColor[i]);
            //System.out.println("CnnctCmpnntLbl,top: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
        } else if (i > 0 && isInBounds1x1(topLeftIndex, aWidth, aHeight) && (myOriginalValues[topLeftIndex] & 0x00ffffff) != 0x00000000) {
            indexValue = indexValues[topLeftIndex];
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            updateBoxHashMap(myOriginalValues,i,labelColor[i]);            
            //System.out.println("CnnctCmpnntLbl,top: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
        } else if ((color & 0x00ffffff) != 0x00000000) {
            indexValue = rtrvNextIndex();
            indexValues[i] = indexValue;
            labelColor[i] = rtrvColor(indexValue);
            updateBoxHashMap(myOriginalValues,i,labelColor[i]);            
            //System.out.println("CnnctCmpnntLbl,new: labelColor[" + i + "]" + labelColor[i] + ", indexValue=" + indexValue);
        } else {
            labelColor[i] = 0x00000000;//rtrvColor(indexValue);
            //System.out.println("CnnctCmpnntLbl,left: labelColor["+i+"]"+labelColor[i]+", indexValue="+indexValue);            
        }
    }

    public void updateBoxHashMap(int myData[], int myIndex,int myColor) {
        int aColor = myColor;
        int x = ImageTool.rtrvXPstn(myIndex, getImageWidth());
        int y = ImageTool.rtrvYPstn(myIndex, getImageWidth());
        
        RectBoundary aCmpnntRectangle1 = (RectBoundary) colorBoxHashMap.get(new Color(aColor));

        if (aCmpnntRectangle1 != null) {
            aCmpnntRectangle1.updtBndry(x, y);
            //aCmpnntRectangle1.setColor(myColor);
        } else {
            RectBoundary aCmpnntRectangle2 = new RectBoundary();
            aCmpnntRectangle2.updtBndry(x, y);
            aCmpnntRectangle2.setColor(myColor);
            colorBoxHashMap.put(new Color(aColor), aCmpnntRectangle2);
        }
    }
    public void printBoxes() {
        Set aSet = colorBoxHashMap.keySet();
        Iterator anIterator = aSet.iterator();
        while (anIterator.hasNext()) {
            Color aColor = (Color) anIterator.next();
            RectBoundary aCmpnntRectangle1 = (RectBoundary) colorBoxHashMap.get(aColor);
            aCmpnntRectangle1.drawSquare(labelColor, aWidth, aHeight,aColor.getRGB());
        }
    }
    public void drawSquare(int clrdCmpnnts[],int myIndex) {
        int width = getImageWidth();
        int height = getImageHeight();
        int cmpnntColor = 0;

        //System.out.println("groupXMin = " + groupXMin + ", groupXmax = " + groupXMax + ", groupYMin = " + groupYMin + ", groupYMax = " + groupYMax);

        LineDraw.drawLine(groupXMin, groupYMin, groupXMax, groupYMin, clrdCmpnnts, width, height, cmpnntColor);
        LineDraw.drawLine(groupXMin, groupYMin, groupXMin, groupYMax, clrdCmpnnts, width, height, cmpnntColor);
        LineDraw.drawLine(groupXMax, groupYMin, groupXMax, groupYMax, clrdCmpnnts, width, height, cmpnntColor);
        LineDraw.drawLine(groupXMin, groupYMax, groupXMax, groupYMax, clrdCmpnnts, width, height, cmpnntColor);
    }

    public int[] getFltrdData() {
        return labelColor;
    }

    @Override
    public void finish() {
        super.finish();
        printBoxes();
    }
    
}