/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.color;

import cgtjr.academics.elctrclengnrng.imgprcssng.GraySclFltr;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class RGBtoHSIFltr extends GraySclFltr {

    private int rgbValues[];

    public RGBtoHSIFltr() {
    }

    public RGBtoHSIFltr(String myFileName) {
        super(myFileName);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
    }

    public RGBtoHSIFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        initialize(myImageWidth, myImageHeight);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        rgbValues = new int[aWidth * aHeight];
    }
    public void filter(int myOriginalValues[], int i) {
        int aPixelValue = ColorSpectra.cnvrtHSIToRGB(myOriginalValues[i]);
        rgbValues[i] = aPixelValue;
    }
    public int[] getFltrdData() {
        return rgbValues;
    }
}