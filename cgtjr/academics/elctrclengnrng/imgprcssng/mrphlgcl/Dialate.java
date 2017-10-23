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
public class Dialate {

    private StrctElmnt strctElmnt;
    private ImageSet nonDltdImgSet;
    private ImageSet dltdImgSet;
    private boolean isDialated;
    //private int imageData[];

    public Dialate() {
        dltdImgSet = new ImageSet();
        nonDltdImgSet = new ImageSet();
    }

    public Dialate(ImageSet myImageSet) {
        nonDltdImgSet = myImageSet;
        int width = nonDltdImgSet.getWidth();
        int height = nonDltdImgSet.getHeight();
        int imageData[] = nonDltdImgSet.getImageData();
        dltdImgSet = new ImageSet(imageData, width, height);
    }

    public void setStrctElmnt(StrctElmnt myStrctElmnt) {
        strctElmnt = myStrctElmnt;
    }

    public void setOutputImgSet(ImageSet myImageSet) {
        dltdImgSet = myImageSet;
    }

    public void setInputImgSet(ImageSet myImageSet) {
        nonDltdImgSet = myImageSet;
    }

    public ImageSet getInputImgSet() {
        return nonDltdImgSet;
    }

    public ImageSet getOutputImgSet() {
        return dltdImgSet;
    }

    public void dialate(int myIndex, ImageSet myImgSet, StrctElmnt myStrctElmnt) {

        setInputImgSet(myImgSet);
        int i = ImageTool.rtrvXPstn(myIndex, myImgSet.getWidth());
        int j = ImageTool.rtrvYPstn(myIndex, myImgSet.getWidth());

        isDialated = false;
        int aWidth = myStrctElmnt.getWidth();
        int aHeight = myStrctElmnt.getHeight();
        boolean isOnElmnt = false;
        boolean isOnImg = false;
        boolean isElmntOnImg = false;
        int xOffSet = -1 * aWidth / 2;
        int yOffSet = -1 * aHeight / 2;

        for (int a = 0; a < aWidth; a++) {
            for (int b = 0; b < aHeight; b++) {
                int x = i + xOffSet + a;
                int y = j + yOffSet + b;
                isOnElmnt = myStrctElmnt.isPxlOn(a, b);
                isOnImg = myImgSet.isPxlOn(x, y);
                isElmntOnImg = myImgSet.isPxlOn(i, j);
                //System.out.println("Dialate: a = "+a+", b = "+b+", x = "+x+", y = "+y);
                //System.out.println("Dialate: isOnImg = "+isOnImg+", isOnElmnt = "+isOnElmnt);
                if (isElmntOnImg == true && isDialated == false) {
                    isDialated = true;
                }
                if (isOnElmnt == true && isDialated == true && x >= 0 && x < myImgSet.getWidth() && y >= 0 && y < myImgSet.getHeight()) {
                    int anIndex = ImageTool.rtrvIndex(x, y, myImgSet.getWidth());
                    int imageData[] = dltdImgSet.getImageData();
                    imageData[anIndex] = 0x00ffffff;
                    dltdImgSet.setImageData(imageData);
                }
                if (isElmntOnImg == false) {
                    break;
                }
            }
            if (isElmntOnImg == false) {
                break;
            }
        }
    }

    public int[] getImageData() {
        int imageData[] = dltdImgSet.getImageData();
        System.out.println("Dialate: imageData.length = " + imageData.length);
        return imageData;
    }
}