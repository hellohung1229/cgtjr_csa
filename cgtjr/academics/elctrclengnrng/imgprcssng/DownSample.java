/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author finitesystem
 */
public class DownSample extends ImageFilter {
    private int downSampledData[];
    private int imageIndex;
    private double xScale;
    private double yScale;
    private ImageDrawData imageDrawData;
    private int scaledWidth;
    private int scaledHeight;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        int aLength = myImageWidth * myImageHeight;
        xScale = .5;
        yScale = .5;
        scaledWidth = (int) Math.round(xScale * myImageWidth);
        scaledHeight = (int)Math.round(yScale * myImageHeight);
        int sampleLength = scaledWidth * scaledHeight;
        downSampledData = new int[sampleLength];

        imageDrawData = new ImageDrawData(myImageWidth, myImageHeight);
        frameIndex++;
    }

    public void setXScale(double myScale) {
        xScale = myScale;
    }

    public void filter(int myOriginalValues[], int i) {
        int aWidth = getImageWidth();
        //int scaledWidth = (int)(xScale*aWidth);
        int x1 = ImageTool.rtrvXPstn(i, aWidth);
        int y1 = ImageTool.rtrvYPstn(i, aWidth);
        
        if (frameIndex == 1) {
            //downSampledData[i] = myOriginalValues[i];
            imageDrawData.updatePixels(myOriginalValues, x1, y1, aWidth, aWidth);
        } else {
            int x2 = (int) (x1 * xScale);
            int y2 = (int) (y1 * yScale);
            imageIndex = ImageTool.rtrvIndex(x2, y2, scaledWidth);
            downSampledData[imageIndex] = myOriginalValues[i];
            imageDrawData.updatePixels(downSampledData, x2, y2, aWidth, scaledWidth);
        }
    }

    public int[] getFltrdData() {
        return imageDrawData.getImagePixels();
    }
}