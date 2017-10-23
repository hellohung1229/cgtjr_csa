/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class KMeanClstrFltr extends ImageFilter {

    private int inputValues[];
    private int meanR[] = {255,0  ,0  ,255 ,255};
    private int meanG[] = {0  ,255,0  ,255 ,255};
    private int meanB[] = {0  ,0  ,255,00  ,255}; 
    private int nmbrOfClstrs;
    private int clstrIndex;
    private int colorDelta;
    private int pixelClstrNmbr[];

    public KMeanClstrFltr() {
        nmbrOfClstrs = meanB.length;
        colorDelta = 255 / nmbrOfClstrs;        
    }
    public int rtrvNextIndex() {
        clstrIndex++;
        return clstrIndex;
    }
    public int rtrvColor(int myIndex) {
        int anIndex = myIndex % nmbrOfClstrs;
        float aColor = (anIndex * colorDelta) / 255.0f;
        int rgbColor[] = ColorSpectra.cnvrtHSBToRGB(aColor);

        int rgb = ((rgbColor[0] & 0x000000ff) << 16)
                | ((rgbColor[1] & 0x000000ff) << 8)
                | ((rgbColor[2] & 0x000000ff));

        return rgb;
    }
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        pixelClstrNmbr = new int[aWidth * aHeight];
    }
    public void filter(int myOriginalValues[], int i) {
        if (inputValues != null) {
            cluster(inputValues, i);
        } else {
            cluster(myOriginalValues, i);
        }
    }
    public void cluster(int myOriginalValues[], int i) {

        int valueR = ColorSpectra.rtrvRed(myOriginalValues[i]);
        int valueG = ColorSpectra.rtrvGreen(myOriginalValues[i]);
        int valueB = ColorSpectra.rtrvBlue(myOriginalValues[i]);        
        
        int dstnce = Integer.MAX_VALUE;
        int dstnceTmp = Integer.MAX_VALUE;
        int clstrNmbr = 0;

        for (int j = 0; j < nmbrOfClstrs; j++) {
            dstnceTmp = (valueR-meanR[j])*(valueR-meanR[j])+(valueG-meanG[j])*(valueG-meanG[j])+(valueB-meanB[j])*(valueB-meanB[j]);
            
            if (dstnceTmp <= dstnce) {
                dstnce = dstnceTmp;
                clstrNmbr = j;
                pixelClstrNmbr[i] = rtrvColor(j);
            }
        }
    }
    public int[] getFltrdData() {
        return pixelClstrNmbr;
    }
}