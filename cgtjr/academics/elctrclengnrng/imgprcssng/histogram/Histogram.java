/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.histogram;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class Histogram extends ImageFilter {

    private int hstgrmValues[];
    private int cmbnValues[];

    public Histogram() {
    }

    public Histogram(String myFileName) {
        super(myFileName);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        hstgrmValues = new int[256];
        cmbnValues = new int[aWidth * aHeight];
    }

    public Histogram(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        hstgrmValues = new int[256];
        cmbnValues = new int[myImageWidth * myImageHeight];
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        hstgrmValues = new int[256];
        cmbnValues = new int[aWidth * aHeight];
    }
    public void filter(int myOriginalValues[], int i) {
        cmbnValues[i] = ColorSpectra.cnvrtRGBGry8(myOriginalValues[i]);
        int intensity = cmptHistogram(myOriginalValues, i);
        hstgrmValues[intensity]++;
    }
    public int cmptHistogram(int myOriginalValues[], int i) {
        int orValue = ColorSpectra.cnvrtRGBGry8(myOriginalValues[i]);
        return orValue;
    }
    public int[] getHistogram() {
        return hstgrmValues;
    }
    public int cmptL1(int myHstgrm1[], int myHstgrm2[]) {
        int aLength = myHstgrm1.length;
        int results = 0;
        for (int i = 0; i < aLength; i++) {
            results += Math.abs(myHstgrm1[i] - myHstgrm2[i]);
            System.out.println("Histogram: myHstgrm1["+i+"]="+myHstgrm1[i]+", myHstgrm2["+i+"]="+myHstgrm2[i]);
        }
        System.out.println("Histogram: results = "+results);
        return results;
    }
    public int[] getFltrdData() {
        return cmbnValues;
    }
}