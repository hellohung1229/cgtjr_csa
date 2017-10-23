/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class YIntnstyFltr extends GraySclFltr {

    private ImageFilter intnstyFltr;
    private int intnstyValues[];
    private int hsiValues[];
    private int intnstyLvls = 256;

    public YIntnstyFltr() {
    }

    public YIntnstyFltr(String myFileName) {
        super(myFileName);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        //System.out.println("GrySclFltr: filename = "+myFileName);
    }

    public YIntnstyFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        super(myPixelData, myImageWidth, myImageHeight);
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);
        initialize(myImageWidth, myImageHeight);
        //BufferedImage aBufferedImage = ImageTool.rtrvImage(myPixelData, aWidth, aHeight);
        //System.out.println("GrySclFltr: filename = "+myFileName);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        hsiValues = new int[aWidth * aHeight];
        intnstyValues = intnstyFltr.getOrgnlVls();
    }

    public void setIntnstyFltr(ImageFilter myImageFltr) {
        intnstyFltr = myImageFltr;
    }

    public void filter(int myOriginalValues[], int i) {

        int aPixelValue = ColorSpectra.rtrv000000ff(myOriginalValues[i]);
        //int aPixelValue = 0x00;Uncomment to create intensity image without highlight.
        if (aPixelValue == 0xff) {
            hsiValues[i] = intnstyValues[i];
        } else {
            hsiValues[i] = ColorSpectra.updateYIntnsty(intnstyValues[i], 0.005f);
        }
    }

    public int[] getFltrdData() {
        return hsiValues;
    }
}