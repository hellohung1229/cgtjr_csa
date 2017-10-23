/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.draw;

import cgtjr.academics.general.ImageTool;

/**
 *
 * @author finitesystem
 */
public class ImageDrawData {

    private boolean isPixelDrawn[];
    private int imagePixels[];
    private int bgPixelColor;
    private int reservedColor1;
    private int reservedColor2;
    private int reservedColor3;
    private int reservedColor4;
    private int imageWidth;
    private int imageHeight;

    public ImageDrawData(int myWidth, int myHeight) {
        //isPixelDrawn = new boolean[myWidth * myHeight];
        imageWidth = myWidth;
        imageHeight = myHeight;
        imagePixels = new int[myWidth * myHeight];
        bgPixelColor = 0x00000000;
        reservedColor1 = 0xf1234567;
        reservedColor2 = 0xf1234567;
        reservedColor3 = 0xf1234567;
        reservedColor4 = 0xf1234567;
    }

    public void updatePixels(int myImagePixels[], int myIndex) {
        //if (imagePixels[myIndex] == bgPixelColor) {
            imagePixels[myIndex] = myImagePixels[myIndex];
        //}
    }

    public void updatePixels(int myImagePixels[], int myX, int myY, int myWidth1, int myWidth2) {
        int index1 = ImageTool.rtrvIndex(myX, myY, myWidth1);
        int index2 = ImageTool.rtrvIndex(myX, myY, myWidth2);
        if (myX >= 0 && myY >= 0 && myX < imageWidth && myY < imageHeight && imagePixels[index1] == bgPixelColor) {
            imagePixels[index1] = myImagePixels[index2];
        }
    }

    public void drawData(int myColor, int myIndex) {
        try {
            imagePixels[myIndex] = myColor;
        } catch (java.lang.ArrayIndexOutOfBoundsException aiobe) {
            //System.out.println(aiobe.getMessage());
        }
        //isPixelDrawn[myIndex] = true;
    }

    public void drawData(int myColor, int myX, int myY) {


        if (myX >= 0 && myY >= 0 && myX < imageWidth && myY < imageHeight) {
        int anIndex = ImageTool.rtrvIndex(myX, myY, imageWidth);
        //System.out.println("drawData : anIndex = "+anIndex);
            try {
                imagePixels[anIndex] = myColor;
            } catch (java.lang.ArrayIndexOutOfBoundsException aiobe) {
                //System.out.println(aiobe.getMessage());
            }
        }
        //isPixelDrawn[myIndex] = true;
    }

    public void drawData(int myColor, int myX, int myY, int myWidth) {

        if (myX >= 0 && myY >= 0 && myX < imageWidth && myY < imageHeight) {
            int anIndex = ImageTool.rtrvIndex(myX, myY, imageWidth);
            try {
                imagePixels[anIndex] = myColor;
            } catch (java.lang.ArrayIndexOutOfBoundsException aiobe) {
                //System.out.println(aiobe.getMessage());
            }
        }
        //isPixelDrawn[myIndex] = true;
    }

    public void drawDataViaCondition(int myColor, int myIndex) {
        try {
            if (imagePixels[myIndex] != reservedColor1
                    && imagePixels[myIndex] != reservedColor2 && imagePixels[myIndex] != reservedColor3
                    && imagePixels[myIndex] != reservedColor4) {

                imagePixels[myIndex] = myColor;
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException aiobe) {
            //System.out.println(aiobe.getMessage());
        }
        //isPixelDrawn[myIndex] = true;
    }

    public int getReservedColor1() {
        return reservedColor1;
    }

    public void setReservedColor1(int reservedColor1) {
        this.reservedColor1 = reservedColor1;
    }

    public int getReservedColor2() {
        return reservedColor2;
    }

    public void setReservedColor2(int reservedColor2) {
        this.reservedColor2 = reservedColor2;
    }

    public int getReservedColor3() {
        return reservedColor3;
    }

    public void setReservedColor3(int reservedColor3) {
        this.reservedColor3 = reservedColor3;
    }

    public int getReservedColor4() {
        return reservedColor4;
    }

    public void setReservedColor4(int reservedColor4) {
        this.reservedColor4 = reservedColor4;
    }

    public void setPixelColor(int myColor) {
        bgPixelColor = myColor;
    }

    public int getPixelColor() {
        return bgPixelColor;
    }

    public int[] getImagePixels() {
        return imagePixels;
    }
}