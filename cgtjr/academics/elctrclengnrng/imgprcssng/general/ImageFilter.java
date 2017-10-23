package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;

public class ImageFilter {

    private int imageWidth;
    private int imageHeight;
    //private int hrzntlData[];
    //private int vrtclData[];
    //private int fltrdData[];
    private int inptPxlData[];
    private int otptPxlData[];
    private int orgnlValues[];// = myIntlValues[i];
    protected static int frameIndex = 0;
    private ImageFilter inputFilter;
    private ImageDrawData imageDrawData;
    private int reservedColor1 = 0xffffff00;
    private int reservedColor2 = 0xff00cc00;

    public ImageFilter() {
    }

    public ImageFilter(String myFileName) {
        ImageTool anImageTool = new ImageTool(myFileName);
        inptPxlData = anImageTool.getImagePixels();
        imageWidth = anImageTool.getImageWidth();
        imageHeight = anImageTool.getImageHeight();
    }

    public ImageFilter(int myPixelData[], int myImageWidth, int myImageHeight) {
        inptPxlData = myPixelData;
        imageWidth = myImageWidth;
        imageHeight = myImageHeight;
    }

    public void setInputFilter(ImageFilter myInputFilter) {
        inputFilter = myInputFilter;
    }

    public ImageFilter getInputFilter() {
        return inputFilter;
    }

    public int[] getInptPxlData() {
        return inptPxlData;
    }

    public void setImputPxlData(int myInputPxls[]) {
        inptPxlData = myInputPxls;
    }

    public void setImageWidth(int myWidth) {
        imageWidth = myWidth;
    }

    public void setImageHeight(int myHeight) {
        imageHeight = myHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setOtptPxlData(int myOtptPxlData[]) {
        otptPxlData = myOtptPxlData;
        //System.out.println("ImageFilter.setOtptPxlData(): otptPxlData[1]"+otptPxlData[1]);
    }

    public int[] getOtptPxlData() {
        //System.out.println("ImageFilter.getOtptPxlData(): otptPxlData[1]"+otptPxlData[1]);
        return otptPxlData;
    }

    //abstract public int[] getFltrdData();
    //{
    //    return fltrdData;
    //}
    public int[] getImageData() {
        return inptPxlData;
    }

    /*
     public int[] getHrzntlData() {
     return hrzntlData;
     }
     public int[] getVrtclData() {
     return vrtclData;
     }*/
    public void initializeImgeFltr(int myImageWidth, int myImageHeight) {
        this.initialize(myImageWidth, myImageHeight);
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        setImageWidth(myImageWidth);
        setImageHeight(myImageHeight);

        if (imageDrawData == null) {
            imageDrawData = new ImageDrawData(myImageWidth, myImageHeight);
            imageDrawData.setReservedColor1(reservedColor1);
            imageDrawData.setReservedColor2(reservedColor2);
        }
        frameIndex++;
    }

    public boolean isInBounds3x3(int myX, int myY) {

        boolean inBounds = false;
        System.out.println("ImageFilter: width = " + imageWidth);
        int x9 = (myX) % imageWidth;
        int y9 = (myX) / imageWidth;

        int x0 = (myY - imageWidth - 1) % imageWidth;
        int y0 = (myY - imageWidth - 1) / imageWidth;

        int x8 = (myY + imageWidth + 1) % imageWidth;
        int y8 = (myY + imageWidth + 1) / imageWidth;

        if (x9 >= x0 && x9 < x8 && y9 >= y0 && y9 < y8) {
            inBounds = true;
        }
        return inBounds;
    }

    public boolean isInBounds3x3(int myIndex) {
        return isInBounds3x3(myIndex, getImageWidth(), getImageHeight());
    }

    public static boolean isInBounds1x1(int myIndex, int myImgWidth, int myImgHeight) {
        boolean inBounds = false;
        int aLength = myImgWidth * myImgHeight;
        if (myIndex >= 0 && myIndex < aLength) {
            inBounds = true;
        }
        return inBounds;
    }

    public static boolean isInBounds1x1(int myX, int myY, int myImgWidth, int myImgHeight) {
        boolean inBounds = false;
        int aLength = myImgWidth * myImgHeight;
        if (myX >= 0 && myX < myImgWidth && myY >= 0 && myY < myImgHeight) {
            inBounds = true;
        }
        return inBounds;
    }

    public static boolean isInBounds5x5(int myIndex, int myImgWidth, int myImgHeight) {
        int aWidth2 = 2 * myImgWidth;
        boolean isIn = true;
        isIn = isIn & isInBound(myIndex - aWidth2 - 2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - aWidth2 - 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - aWidth2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - aWidth2 + 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - aWidth2 + 2, myImgWidth, myImgHeight);

        isIn = isIn & isInBound(myIndex - myImgWidth - 2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - myImgWidth - 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - myImgWidth, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - myImgWidth + 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - myImgWidth + 2, myImgWidth, myImgHeight);

        isIn = isIn & isInBound(myIndex - 2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex - 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + 2, myImgWidth, myImgHeight);

        isIn = isIn & isInBound(myIndex + myImgWidth - 2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + myImgWidth - 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + myImgWidth, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + myImgWidth + 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + myImgWidth + 2, myImgWidth, myImgHeight);

        isIn = isIn & isInBound(myIndex + aWidth2 - 2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + aWidth2 - 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + aWidth2, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + aWidth2 + 1, myImgWidth, myImgHeight);
        isIn = isIn & isInBound(myIndex + aWidth2 + 2, myImgWidth, myImgHeight);
        return isIn;
    }

    public static boolean isInBound(int myIndex, int myImgWidth, int myImgHeight) {
        int x = ImageTool.rtrvXPstn(myIndex, myImgWidth);
        int y = ImageTool.rtrvYPstn(myIndex, myImgWidth);
        if (x >= 0 && x < myImgWidth && y >= 0 && y < myImgHeight) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInBound(int myIndex, int myImgWidth, int myImgHeight, int myLeftBound, int myTopBound, int myRightBound, int myBottomBound) {
        int x = ImageTool.rtrvXPstn(myIndex, myImgWidth);
        int y = ImageTool.rtrvYPstn(myIndex, myImgWidth);
        if (x >= myLeftBound && x < myRightBound && y >= myTopBound && y < myBottomBound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInBounds3x3(int myIndex, int myImgWidth, int myImgHeight) {
        //System.out.println("ImageFilter: width = "+ myImgWidth);      
        boolean inBounds = false;
        try {

            int x0 = (myIndex - myImgWidth - 1) % myImgWidth;
            int y0 = (myIndex - myImgWidth - 1) / myImgWidth;
            int x1 = (myIndex - myImgWidth) % myImgWidth;
            int y1 = (myIndex - myImgWidth) / myImgWidth;
            int x2 = (myIndex - myImgWidth + 1) % myImgWidth;
            int y2 = (myIndex - myImgWidth + 1) / myImgWidth;
            int x3 = (myIndex - 1) % myImgWidth;
            int y3 = (myIndex - 1) / myImgWidth;
            int x4 = (myIndex) % myImgWidth;
            int y4 = (myIndex) / myImgWidth;
            int x5 = (myIndex + 1) % myImgWidth;
            int y5 = (myIndex + 1) / myImgWidth;
            int x6 = (myIndex + myImgWidth - 1) % myImgWidth;
            int y6 = (myIndex + myImgWidth - 1) / myImgWidth;
            int x7 = (myIndex + myImgWidth) % myImgWidth;
            int y7 = (myIndex + myImgWidth) / myImgWidth;
            int x8 = (myIndex + myImgWidth + 1) % myImgWidth;
            int y8 = (myIndex + myImgWidth + 1) / myImgWidth;

            if (myIndex >= myImgWidth * myImgHeight) {
                inBounds = false;
            } else if (x0 >= 0 && x0 < myImgWidth && y0 >= 0 && y0 < myImgHeight
                    && x1 >= 0 && x1 < myImgWidth && y1 >= 0 && y1 < myImgHeight
                    && x2 >= 0 && x2 < myImgWidth && y2 >= 0 && y2 < myImgHeight
                    && x3 >= 0 && x3 < myImgWidth && y3 >= 0 && y3 < myImgHeight
                    && x4 >= 0 && x4 < myImgWidth && y4 >= 0 && y4 < myImgHeight
                    && x5 >= 0 && x5 < myImgWidth && y5 >= 0 && y5 < myImgHeight
                    && x6 >= 0 && x6 < myImgWidth && y6 >= 0 && y6 < myImgHeight
                    && x7 >= 0 && x7 < myImgWidth && y7 >= 0 && y7 < myImgHeight
                    && x8 >= 0 && x8 < myImgWidth && y8 >= 0 && y8 < myImgHeight) {
                inBounds = true;
            }
        } catch (ArithmeticException anArithmeticException) {
            inBounds = false;
        }

        return inBounds;
    }

    public int[] rtrvPixels3x3(int myX, int myY) {
        return rtrvPixels3x3(myX, myY, inptPxlData);
    }

    public int[] rtrvFltrdData3x3(int myX, int myY) {
        return rtrvPixels3x3(myX, myY, inptPxlData);
    }

    public int[] rtrvPixels3x3(int myX, int myY, int myData[]) {
        int pixels[] = new int[9];
        int index0 = rtrvIndex(myX - 1, myY - 1);
        int index1 = rtrvIndex(myX, myY - 1);
        int index2 = rtrvIndex(myX + 1, myY - 1);
        int index3 = rtrvIndex(myX - 1, myY);
        int index4 = rtrvIndex(myX, myY);
        int index5 = rtrvIndex(myX + 1, myY);
        int index6 = rtrvIndex(myX - 1, myY + 1);
        int index7 = rtrvIndex(myX, myY + 1);
        int index8 = rtrvIndex(myX + 1, myY + 1);
        pixels[0] = myData[index0];
        pixels[1] = myData[index1];
        pixels[2] = myData[index2];
        pixels[3] = myData[index3];
        pixels[4] = myData[index4];
        pixels[5] = myData[index5];
        pixels[6] = myData[index6];
        pixels[7] = myData[index7];
        pixels[8] = myData[index8];
        return pixels;
    }

    public int[] rtrvPixels3x3(int myX, int myY, short myData[]) {
        int pixels[] = new int[9];
        int index0 = rtrvIndex(myX - 1, myY - 1);
        int index1 = rtrvIndex(myX, myY - 1);
        int index2 = rtrvIndex(myX + 1, myY - 1);
        int index3 = rtrvIndex(myX - 1, myY);
        int index4 = rtrvIndex(myX, myY);
        int index5 = rtrvIndex(myX + 1, myY);
        int index6 = rtrvIndex(myX - 1, myY + 1);
        int index7 = rtrvIndex(myX, myY + 1);
        int index8 = rtrvIndex(myX + 1, myY + 1);
        pixels[0] = myData[index0];
        pixels[1] = myData[index1];
        pixels[2] = myData[index2];
        pixels[3] = myData[index3];
        pixels[4] = myData[index4];
        pixels[5] = myData[index5];
        pixels[6] = myData[index6];
        pixels[7] = myData[index7];
        pixels[8] = myData[index8];
        return pixels;
    }

    public int rtrvIndex(int myX, int myY) {
        int aWidth = getImageWidth();
        int anIndex = myY * aWidth + myX;
        //System.out.println("ImageFilter: x = "+myX+",y = "+myY+", index = "+anIndex);
        return anIndex;
    }

    public int rtrvXPstn(int myIndex) {
        int x = myIndex % getImageWidth();
        return x;
    }

    public int rtrvYPstn(int myIndex) {
        int y = myIndex / getImageWidth();
        return y;
    }

    public void finish() {
    }

    public void start(int b, int a) {
    }

    public int[] getOrgnlVls() {
        return inptPxlData;
    }

    public void filter(int myValue[], int i) {
        getImageDrawData().updatePixels(myValue, i);

        //inptPxlData[i] = myValue[i];
    }
    public void filter(int myValue[]) {
        int aLength = myValue.length;
        for(int i = 0;i<aLength;i++){          
            getImageDrawData().updatePixels(myValue, i);
        }
        //inptPxlData[i] = myValue[i];
    }    
    //public abstract void filter(int myHVData[],int myHrzntlData[],int myVrtclData[],int i);

    public int[] getFltrdData() {
        //System.out.println("ImageFilter : test");
        return imageDrawData.getImagePixels();
    }

    public ImageDrawData getImageDrawData() {
        return imageDrawData;
    }

    public static int getFrameIndex() {
        return frameIndex;
    }

    public static void setFrameIndex(int myFrameIndex) {
        ImageFilter.frameIndex = myFrameIndex;
    }
}