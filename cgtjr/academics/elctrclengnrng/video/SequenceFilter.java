/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */
public class SequenceFilter {

    private static int prevImgData[];
    private static int crrntImgData[];
    private static int frameNumber;

    /*
    private int imgWidth;
    private int imgHeight;
    private int firstFrame[];
    private int prvsData1[];
    private int prvsData2[];
    private int grayValues[];
    */
    public static void initialize(int myImageWidth, int myImageHeight) {

        int aLength = myImageHeight * myImageWidth;
        crrntImgData = new int[aLength];

        //grayValues = getGryVls();
        /*
        if (prevImgData == null) {
            prevImgData = new int[aLength];
        }
        if (firstFrame == null) {
            firstFrame = new int[aLength];
        }
        if (prvsData1 == null) {
            prvsData1 = new int[aLength];
        }
        if (prvsData2 == null) {
            prvsData2 = new int[aLength];
        }*/
    }

    public static void filter(int dataValues[], int i) {
        crrntImgData = dataValues;
        
        //setPrevImgData(dataValues, i);
        //updatePixels(dataValues, i);
        //super.cornerDetect(dataValues, i);
    }
    /*
    public void setFirstFrameData(int myValues[], int i) {
        //System.out.println("OpticalFlowFltr: frameNumber = "+frameNumber+", i = "+i);
        if (frameNumber == 3) {
            firstFrame[i] = myValues[i];
            //System.out.println("OpticalFlowFltr: setting first frame = " + frameNumber + ", data i = " + i);
        }
    }

    public void setCrrntImgData(int i) {
        crrntImgData[i] = grayValues[i];
    }
    public void setPrevImgData(int i) {
        if (frameNumber % 2 == 0) {
            prvsData2[i] = grayValues[i];
        } else {
            if (grayValues == null) {
                //System.out.println("grayValues = null");
            }
            if (prvsData1 == null) {
                //System.out.println("prvsData1 = null");
            }
            prvsData1[i] = grayValues[i];
        }
    }

    public void setPrevImgData(int grayValues[], int i) {
        if (frameNumber % 2 == 0) {
            prvsData2[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        } else {
            if (grayValues == null) {
                //System.out.println("grayValues = null");
            }
            if (prvsData1 == null) {
                //System.out.println("prvsData1 = null");
            }
            prvsData1[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        }
    }
    public int[] getPrvsData() {
        if (frameNumber % 2 == 0) {
            return prvsData2;
        } else {
            return prvsData1;
        }
    }*/
    public static void setFrameNumber(int myFrameNumber) {
        frameNumber = myFrameNumber;
    }
    public static int getFrameNumber() {
        return frameNumber;
    }
    public static void incrmtFrmNmbr() {
        frameNumber++;
    }
    public static void dcrmntFrmNmbr() {
        frameNumber--;
    }
    public static int[] getPrvsImgData() {
       return prevImgData;
    }    
    public static void startPrsng() {
        System.out.println("SequenceFilter: start parsing frame = " + frameNumber);        
    }
    public static void finish() {
        System.out.println("SequenceFilter: finish parsing frame = " + frameNumber);
        //prevImgData = getPrvsData();
        prevImgData = crrntImgData;
    }
}