/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl;

import cgtjr.academics.general.ImageTool;

/**
 *
 * @author clayton g thomas jr
 */
public class Erode {

    private StrctElmnt strctElmnt;
    private ImageSet nonErddImgSet;
    private ImageSet erddImgSet;
    private boolean notEroded;
    //private int imageData[];

    public Erode() {
        erddImgSet = new ImageSet();
        nonErddImgSet = new ImageSet();
    }

    public Erode(ImageSet myImageSet) {
        nonErddImgSet = myImageSet;
        int width = nonErddImgSet.getWidth();
        int height = nonErddImgSet.getHeight();
        int imageData[] = nonErddImgSet.getImageData();
        erddImgSet = new ImageSet(imageData, width, height);
    }

    public void setStrctElmnt(StrctElmnt myStrctElmnt) {
        strctElmnt = myStrctElmnt;
    }

    public void setOutputImgSet(ImageSet myImageSet) {
        erddImgSet = myImageSet;
    }

    public void setInputImgSet(ImageSet myImageSet) {
        nonErddImgSet = myImageSet;
    }

    public ImageSet getInputImgSet() {
        return nonErddImgSet;
    }

    public ImageSet getOutputImgSet() {
        return erddImgSet;
    }

    public void erode(int myIndex, ImageSet myImgSet, StrctElmnt myStrctElmnt) {

        setInputImgSet(myImgSet);
        int i = ImageTool.rtrvXPstn(myIndex, myImgSet.getWidth());
        int j = ImageTool.rtrvYPstn(myIndex, myImgSet.getWidth());

        boolean inRegion = false;
        boolean keepRgn = true;
        int aWidth = myStrctElmnt.getWidth();
        int aHeight = myStrctElmnt.getHeight();
        boolean isOnElmnt = false;
        boolean isOnImg = false;
        int xOffSet = -1 * aWidth / 2;
        int yOffSet = -1 * aHeight / 2;

        for (int a = 0; a < aWidth; a++) {
            for (int b = 0; b < aHeight; b++) {
                int x = i + xOffSet + a;
                int y = j + yOffSet + b;
                isOnElmnt = myStrctElmnt.isPxlOn(a, b);
                isOnImg = myImgSet.isPxlOn(x, y);

                if (isOnElmnt == true && isOnImg == true) {
                    inRegion = true;
                    keepRgn = keepRgn & inRegion;
                } else if (isOnElmnt == true && isOnImg == false) {
                    inRegion = false;
                    keepRgn = keepRgn & inRegion;
                    break;
                }

                //System.out.println("Erode: a = " + a + ", b = " + b + ", x = " + x + ", y = " + y);
                //System.out.println("Erode: isOnImg = " + isOnImg + ", isOnElmnt = " + isOnElmnt);
                //System.out.println("Erode: keepRgn = " + keepRgn);
            }
            if(keepRgn == false)
            {
                break;
            }
        }
        if (keepRgn == true) {
            int imageData[] = erddImgSet.getImageData();
            imageData[myIndex] = 0x00ffffff;
            erddImgSet.setImageData(imageData);
        } else {
            //int imageData[] = erddImgSet.getImageData();
            //imageData[myIndex] = 0x00000000;
            //erddImgSet.setImageData(imageData);        
        }
        //erddImgSet.setWidth(myImgSet.getWidth());
        //erddImgSet.setHeight(myImgSet.getHeight());
    }

    public int[] getImageData() {
        int imageData[] = erddImgSet.getImageData();
        System.out.println("Erode: imageData.length = " + imageData.length);
        return imageData;
    }
}