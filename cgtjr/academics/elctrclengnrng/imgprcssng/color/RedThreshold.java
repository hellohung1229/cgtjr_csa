/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.color;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class RedThreshold extends ImageFilter {

    private int redMin = 40;
    private int redMax = 255;
    private int greenMin;
    private int greenMax;
    private int blueMin;
    private int blueMax;
    private int filteredData[];
    private static int outputData[];
    private boolean invrsDcsn = true;

    public RedThreshold() {
    }
    public RedThreshold(String myFileName) {
    }
    public RedThreshold(int myPixelData[], int myImageWidth, int myImageHeight) {
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
        filterColor(myOriginalValues, i);
    }
    public void filterColor(int myOriginalValues[], int i) {
        int input = myOriginalValues[i];
        int output = 0;
        int red = ColorSpectra.rtrvRed(input);
        if (isInRange(red)) {
            filteredData[i] = 0x00ffffff;
        } else {
            filteredData[i] = 0;
        }
    }
    public boolean isInRange(int r) {
        boolean inRange = false;

        if (r >= redMin && r <= redMax) {
            inRange = true;
        }
        if (invrsDcsn == true) {
            inRange = !inRange;
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
}