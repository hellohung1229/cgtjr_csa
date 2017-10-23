/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.draw;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.elctrclengnrng.imgprcssng.Round;

/**
 *
 * @author clayton g thomas jr
 */
public class DigitPxls {

    
    private static int numbers[][];
    private static int comma[];
    private static int imgNmbrWdth = 9;
    private static int imgNmbrHght = 9;
    private static int nmbrIndex = 40;

    public DigitPxls(){
        numbers = new int[10][];
        numbers[0] = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
        numbers[1] = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
        numbers[2] = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
        numbers[3] = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
        numbers[4] = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
        numbers[5] = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
        numbers[6] = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
        numbers[7] = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
        numbers[8] = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
        numbers[9] = ImageTool.rtrv1DPxls("data/images/numbers/nine.png");
        comma = ImageTool.rtrv1DPxls("data/images/numbers/colon.png");        
    }
    /*
    static {
        numbers = new int[10][];
        numbers[0] = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
        numbers[1] = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
        numbers[2] = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
        numbers[3] = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
        numbers[4] = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
        numbers[5] = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
        numbers[6] = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
        numbers[7] = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
        numbers[8] = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
        numbers[9] = ImageTool.rtrv1DPxls("data/images/numbers/nine.png");
        comma = ImageTool.rtrv1DPxls("data/images/numbers/colon.png");
    }*/
    public static int[] getComma() {
        return comma;
    }
    /*
     public DigitPntPixels(int myImgPixels[],int myImgWdth)
     {

     //imgPixels = myImgPixels;
     //imgDtWdth = myImgWdth;
      
     numbers = new int[9][];
     numbers[0] = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
     numbers[1] = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
     numbers[2] = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
     numbers[3] = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
     numbers[4] = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
     numbers[5] = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
     numbers[6] = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
     numbers[7] = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
     numbers[8] = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
     }*/
    /*
     public static void update(Pnt myPoint7,int imgPixels[],int imgDtWdth)
     {
     //TODO: look for number formats
     int aX = (int)myPoint7.getX1();
     int aY = (int)myPoint7.getY1();
     int aColor = myPoint7.getColor();
     //int aColor = 0x00ee0000;
     int anIndex = ImageTool.rtrvIndex(aX,aY,imgDtWdth);
     //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
     ImageTool.updtImgPixel(anIndex, aColor, imgPixels);
     }*/

    public static void draw27x27(int myImgPxls[], int myPxls[], int imgDtWdth, int imgDtHght, int myIndex1, int myIndex2) {
        int aWidth9 = 9 * imgDtWdth;
        int aNmbrWidth9 = 9 * imgNmbrWdth;
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth9 - 9, myIndex2 - aNmbrWidth9 - 9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth9, myIndex2 - aNmbrWidth9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth9 + 9, myIndex2 - aNmbrWidth9 + 9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - 9, myIndex2 - 9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1, myIndex2);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + 9, myIndex2 + 9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth9 - 9, myIndex2 + aNmbrWidth9 - 9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth9, myIndex2 + aNmbrWidth9);
        draw9x9(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth9 + 9, myIndex2 + aNmbrWidth9 + 9);
    }

    public static void draw9x9(int myImgPxls[], int myPxls[], int imgDtWdth, int imgDtHght, int myIndex1, int myIndex2) {
        int aWidth3 = 3 * imgDtWdth;
        int aNmbrWidth3 = 3 * imgNmbrWdth;
        //System.out.println("NumberDrawActn: index2 = "+myIndex2+" , nmbr width = "+aNmbrWidth3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth3 - 3, myIndex2 - aNmbrWidth3 - 3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth3, myIndex2 - aNmbrWidth3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - aWidth3 + 3, myIndex2 - aNmbrWidth3 + 3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 - 3, myIndex2 - 3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1, myIndex2);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + 3, myIndex2 + 3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth3 - 3, myIndex2 + aNmbrWidth3 - 3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth3, myIndex2 + aNmbrWidth3);
        draw3x3(myImgPxls, myPxls, imgDtWdth, imgDtHght, myIndex1 + aWidth3 + 3, myIndex2 + aNmbrWidth3 + 3);
    }

    public static int[] rtrvNmbr(int myNmbr) {
        if (myNmbr >= 0 && myNmbr <= 9) {
            return numbers[myNmbr];
        } else {
            return numbers[0];
        }
    }

    public static void draw3x3(int myImgPxls[], int myPxls[], int imgDtWdth, int imgDtHght, int myIndex1, int myIndex2) {
        //check boundary for borth image and numbers
        int aColor = 0;
        //int anIndex = ImageTool.rtrvIndex(aX,aY,width);
        //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);

        if (isPxlOn(myPxls, myIndex2 - imgNmbrWdth - 1)) {
            aColor = getPixel(myPxls, myIndex2 - imgNmbrWdth - 1);
            updtImgPxls(myIndex1 - imgDtWdth - 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if (isPxlOn(myPxls, myIndex2 - imgNmbrWdth)) {
            aColor = getPixel(myPxls, myIndex2 - imgNmbrWdth);
            updtImgPxls(myIndex1 - imgDtWdth, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 - imgNmbrWdth + 1))) {
            aColor = getPixel(myPxls, myIndex2 - imgNmbrWdth + 1);
            updtImgPxls(myIndex1 - imgDtWdth + 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 - 1))) {
            aColor = getPixel(myPxls, myIndex2 - 1);
            updtImgPxls(myIndex1 - 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2))) {
            aColor = getPixel(myPxls, myIndex2);
            updtImgPxls(myIndex1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 + 1))) {
            aColor = getPixel(myPxls, myIndex2 + 1);
            updtImgPxls(myIndex1 + 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 + imgNmbrWdth - 1))) {
            aColor = getPixel(myPxls, myIndex2 + imgNmbrWdth - 1);
            updtImgPxls(myIndex1 + imgDtWdth - 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 + imgNmbrWdth))) {
            aColor = getPixel(myPxls, myIndex2 + imgNmbrWdth);
            updtImgPxls(myIndex1 + imgDtWdth, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
        if ((isPxlOn(myPxls, myIndex2 + imgNmbrWdth + 1))) {
            aColor = getPixel(myPxls, myIndex2 + imgNmbrWdth + 1);
            updtImgPxls(myIndex1 + imgDtWdth + 1, aColor, myImgPxls, imgDtWdth, imgDtHght);
        }
    }

    public static void updtImgPxls(int myIndex1, int myColor, int myImgPixels[], int imgDtWdth, int imgDtHght) {
        int x = ImageTool.rtrvXPstn(myIndex1, imgDtWdth);
        int y = ImageTool.rtrvYPstn(myIndex1, imgDtWdth);

        if (ImageFilter.isInBounds1x1(x, y, imgDtWdth, imgDtHght)) {
            //System.out.println("NumberDrawActn: x = "+x+", y = "+y+", index = "+myIndex1+", color = "+myColor);
            myImgPixels[myIndex1] = myColor;
            //myImgPixels[myIndex1] = 0x000000dd;           
        }
    }

    public static int getPixel(int myPxls[], int myIndex1) {
        if (isPxlOn(myPxls, myIndex1)) {
            //System.out.println("NumberDrawActn: myPxls["+myIndex1+"]="+myPxls[myIndex1]);
            return myPxls[myIndex1];
        }
        return 0;
    }

    public static boolean isPxlOn(int myPxls[], int myIndex) {
        boolean isPixelOn = false;
        int x = ImageTool.rtrvXPstn(myIndex, imgNmbrWdth);
        int y = ImageTool.rtrvYPstn(myIndex, imgNmbrWdth);

        //System.out.println("NumberDrawActn.isPxlOn(): index = "+myIndex+", x = "+x+",y="+y);
        boolean isInBnds = ImageFilter.isInBounds1x1(x, y, imgNmbrWdth, imgNmbrHght);
        //int pxlClr = ColorSpectra.rtrv000000ff(myPxls[myIndex]);
        //System.out.println("NumberDrawActn: myIndex="+myIndex+", isInBnds = "+isInBnds+", myPxls["+123+"]="+pxlClr);
        if (myIndex < 0) {
            isPixelOn = false;
        } else if (isInBnds == true && ColorSpectra.rtrv000000ff(myPxls[myIndex]) > 1) {
            //System.out.println("NumberDrawActn: x = "+x+",y="+y+", myIndex="+myIndex+", isInBnds = "+isInBnds+", myPxls["+myIndex+"]="+pxlClr);
            isPixelOn = true;
        }
        return isPixelOn;
    }

    public void setImgNmbrWdth(int myWidth) {
        imgNmbrWdth = myWidth;
    }

    public static void drawDigit(int imgPixels[], int imgDtWdth, int imgDtHght, int myX, int myY, int myNmbr) {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
        int x = myX;//(int)myPoint7.getX1();
        int y = myY;//(int)myPoint7.getY1();       

        int xLength = Round.getDgtLngth(myNmbr);

        //int xLength = Round.getDgtLngth(x);
        int yLength = Round.getDgtLngth(y);

        int x1 = Round.getXDgtPxlPstn(1, xLength);
        int x2 = Round.getXDgtPxlPstn(2, xLength);
        int x3 = Round.getXDgtPxlPstn(3, xLength);

        //int x4 = Round.getYDgtPxlPstn(1,yLength);
        //int x5 = Round.getYDgtPxlPstn(2,yLength);       
        //int x6 = Round.getYDgtPxlPstn(3,yLength);       

        if (x + x1 < 0 || x + x1 > imgDtWdth || x + x2 < 0 || x + x2 > imgDtWdth) {
            return;
        }
        //int glblIndx = myPoint7.getCounter();       
        //int aLength = Round.getDgtLngth(glblIndx);     

        //System.out.println("NumberDrawActn: image width = "+imgDtWdth+", x = "+x+", x1 = "+x1+", x2 = "+x2);              
        int aDigitX1 = Round.getLength2Digit(myNmbr, 1);
        int aDigitX2 = Round.getLength2Digit(myNmbr, 2);
        //int aDigitX3 = Round.getLength2Digit(myNmbr, 3);

        //int aDigitY1 = Round.getLength3Digit(y,1);
        //int aDigitY2 = Round.getLength3Digit(y,2);
        //int aDigitY3 = Round.getLength3Digit(y,3);

        //////int pxlDataX1[] = DigitPxls.rtrvNmbr(aDigitX1);
        //////int pxlDataX2[] = DigitPxls.rtrvNmbr(aDigitX2);
        //int pxlDataX3[] = DigitPxls.rtrvNmbr(aDigitX3);

        //int pxlDataY1[] = DigitPxls.rtrvNmbr(aDigitY1);       
        //int pxlDataY2[] = DigitPxls.rtrvNmbr(aDigitY2);
        //int pxlDataY3[] = DigitPxls.rtrvNmbr(aDigitY3);       
        int comma[] = DigitPntPixels.getComma();

        //System.out.println("NumberDrawActn: aDigit0 = "+aDigit0+", aDigit1 = "+aDigit1);       
        //System.out.println("NumberDrawActn: global index = "+glblIndx+", digit length = "+aLength);

        int myIndexX1 = ImageTool.rtrvIndex(x + x1, y, imgDtWdth);
        int myIndexX2 = ImageTool.rtrvIndex(x + x2, y, imgDtWdth);
        //int myIndexX3 = ImageTool.rtrvIndex(x + x3, y, imgDtWdth);

        //int myIndexY1 = ImageTool.rtrvIndex(x+x4, y, imgDtWdth);
        //int myIndexY2 = ImageTool.rtrvIndex(x+x5, y, imgDtWdth);       
        //int myIndexY3 = ImageTool.rtrvIndex(x+x6, y, imgDtWdth);

        //int pxlData[] = DigitPntPixels.rtrvNmbr(glblIndx);
        int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);
        //draw9x9(imgPixels,pxlData,myIndex1,aDigit0);

        /////draw9x9(imgPixels, pxlDataX1, imgDtWdth, imgDtHght, myIndexX1, nmbrIndex);
        /////draw9x9(imgPixels, pxlDataX2, imgDtWdth, imgDtHght, myIndexX2, nmbrIndex);
        //draw9x9(imgPixels, pxlDataX3, imgDtWdth, imgDtHght, myIndexX3, nmbrIndex);
        //DigitPntPixels.draw9x9(imgPixels,comma,imgDtWdth,imgDtHght,myIndex,nmbrIndex);        
        //DigitPntPixels.draw9x9(imgPixels,pxlDataY1,imgDtWdth,imgDtHght,myIndexY1,nmbrIndex);
        //DigitPntPixels.draw9x9(imgPixels,pxlDataY2,imgDtWdth,imgDtHght,myIndexY2,nmbrIndex);       
        //DigitPntPixels.draw9x9(imgPixels,pxlDataY3,imgDtWdth,imgDtHght,myIndexY3,nmbrIndex);            

        //System.out.println("NumberDrawActn: index1 = "+myIndex+", nmbrIndex = "+nmbrIndex);       
    }
}