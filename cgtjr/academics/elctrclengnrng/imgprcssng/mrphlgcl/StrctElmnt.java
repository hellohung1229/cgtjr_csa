/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import java.awt.image.BufferedImage;

/**
 *
 * @author clayton g thomas jr
 */
public class StrctElmnt {

    private boolean isIntrction;
    private int threshold = 0xff;
    private int strctElmnt[] = {0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,
        0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff, 0x00ffffff,};
    private int width;
    private int height;
    private BufferedImage elmntBffrdImage;

    public StrctElmnt(int myWidth, int myHeight) {
        width = myWidth;
        height = myHeight;
        int aLength = width * height;
        strctElmnt = new int[aLength];
        for (int i = 0; i < aLength; i++) {
            strctElmnt[i] = 0x00ffffff;
        }
    }

    public StrctElmnt() {
        width = 12;
        height = 12;
    }

    public StrctElmnt(String aFileName) {
        elmntBffrdImage = ImageTool.rdImageFile(aFileName);
        width = elmntBffrdImage.getWidth();
        height = elmntBffrdImage.getHeight();
        strctElmnt = ImageTool.rtrv1DPxls(elmntBffrdImage);
    }

    public boolean isPxlOn(int x, int y) {
        boolean isPixel = false;
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return isPixel;
        }

        int anIndex = ImageTool.rtrvIndex(x, y, width);
        int aColor = strctElmnt[anIndex];
        int aBinColor = ColorSpectra.cnvrtRGBGry8(aColor);
        //System.out.println("StrctElmnt.isPxlOn(): aBinColor = "+aBinColor +", threshold = "+threshold);        
        if (aBinColor == threshold) {
            isPixel = true;
        }
        return isPixel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getImageData() {
        return strctElmnt;
    }
}
