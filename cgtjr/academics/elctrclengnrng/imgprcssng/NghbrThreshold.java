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
public class NghbrThreshold extends ImageFilter {

    private int minValue = 80; //80
    private int maxValue = 200; //200
    private int filteredData[];
    private static int outputData[];

    public NghbrThreshold() {
    }

    public NghbrThreshold(String myFileName) {
    }

    public NghbrThreshold(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        initialize(myImageWidth, myImageHeight);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        filteredData = new int[myImageWidth * myImageHeight];
        outputData = filteredData;
    }

    public void filter(int myOriginalValues[], int i) {
        threshHold(myOriginalValues, i);
    }

    public void threshHold(int myOriginalValues[], int i) {

        boolean inRange = threshHoldAll(myOriginalValues, i);
        if (inRange) {
            filteredData[i] = 0x00ffffff;
        } else {
            filteredData[i] = 0;
        }
    }

    public boolean threshHoldAll(int myOriginalValues[], int i) {
        boolean inRange = false;
        if (!isInBounds3x3(i)) {
            return false;
        }
        int imageWidth = getImageWidth();
        boolean inRange1 = cmprThreshold(myOriginalValues, i - imageWidth - 1);
        boolean inRange2 = cmprThreshold(myOriginalValues, i - imageWidth);
        boolean inRange3 = cmprThreshold(myOriginalValues, i - imageWidth + 1);
        boolean inRange4 = cmprThreshold(myOriginalValues, i - 1);
        boolean inRange5 = cmprThreshold(myOriginalValues, i);
        boolean inRange6 = cmprThreshold(myOriginalValues, i + 1);
        boolean inRange7 = cmprThreshold(myOriginalValues, i + imageWidth - 1);
        boolean inRange8 = cmprThreshold(myOriginalValues, i + imageWidth);
        boolean inRange9 = cmprThreshold(myOriginalValues, i + imageWidth + 1);
        if (inRange1 && inRange2 && inRange3 && inRange4 && inRange5
                && inRange6 && inRange7 && inRange8 && inRange9) {
            inRange = true;
        }
        return inRange;
    }

    public boolean cmprThreshold(int myOriginalValues[], int i) {
        int input = myOriginalValues[i];
        int hue = ColorSpectra.convertRGBToY(input);
        if (isInRange(hue)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInRange(int r) {
        boolean inRange = false;
        if (r >= minValue && r <= maxValue) {
            inRange = true;
        }
        return inRange;
    }

    public int[] getFltrdData() {
        //System.out.println("RedThreshold: filteredData:length = "+filteredData.length);
        return filteredData;
    }

    public static int[] getOutputData() {
        return outputData;
    }
    public void setMinValue(int myMinValue)
    {
        minValue = myMinValue;
    }
    public void setMaxValue(int myMaxValue)
    {
        maxValue = myMaxValue;
    }
}