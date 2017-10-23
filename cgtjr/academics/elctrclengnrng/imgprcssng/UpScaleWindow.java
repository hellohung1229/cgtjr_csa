/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author finitesystem
 */
public class UpScaleWindow extends CornerDetect {

    private int upSampledData[];
    private int upOctaveData[];
    private int imageIndex;
    private int octaveIndex;
    private double scaleValue;
    private double yScale;
    private double xUpScale;
    private double yUpScale;    
    private ImageDrawData imageDrawData;
    private int scaledWidth;
    private int scaledHeight;
    private int octaveNumber;
    private int octaveWidth;
    private int octaveHeight;
    private int octaveLength;
    private int imageWidth;
    private int imageHeight;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        imageWidth = myImageWidth;
        imageHeight = myImageHeight;
        int aLength = myImageWidth * myImageHeight;
        scaleValue = 1.5;
        octaveNumber = 2;
        scaledWidth = (int) (octaveNumber * myImageWidth);
        scaledHeight = (int) (octaveNumber * myImageHeight);
        octaveWidth = myImageWidth;
        octaveHeight = myImageHeight;
        octaveLength = octaveWidth * octaveHeight;

        int sampleLength = scaledWidth * scaledHeight;

        upSampledData = new int[sampleLength];
        upOctaveData = new int[octaveLength];

        imageDrawData = new ImageDrawData(myImageWidth, myImageHeight);
        xUpScale = 2;
        yUpScale = 2;    
    }
    public void setScaleValue(double myScale) {
        scaleValue = myScale;
    }
    public void filter(int myOriginalValues[], int i) {
        filter9x9(myOriginalValues,i);
    }
    public void filter3x3(int myOriginalValues[], int i) {
        super.filter(myOriginalValues, i);
        upOctaveData[i] = myOriginalValues[i];
        if (getEigenValue(i) >= getThreshold()) {
            filterScale3x3(myOriginalValues, i,2);
            interpolate3x3Dgnl(upOctaveData, i, i,2);
            interpolate3x3HV(upOctaveData, i, i,2);
            filterScale3x3(upOctaveData, i,(int)(scaleValue/2));       
        }
    }
    public void filter9x9(int myOriginalValues[], int i) {
        super.filter(myOriginalValues, i);
        upOctaveData[i] = myOriginalValues[i];
        if (getEigenValue(i) >= getThreshold()) {
            filterScale9x9(myOriginalValues, i,2);
            interpolate9x9Dgnl(upOctaveData, i, i,2);
            interpolate9x9HV(upOctaveData, i, i,2);
            filterScale9x9(upOctaveData, i,(int)(scaleValue/2));       
        }
    }
    private void filterScale9x9(int[] myOriginalValues, int myIndex, int myCenterIndex){
        int aWidthx3 = 3 * getImageWidth();
        filterScale3x3(myOriginalValues, myIndex - aWidthx3 - 3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex - aWidthx3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex - aWidthx3 + 3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex - 3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex + 3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex + aWidthx3 - 3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex + aWidthx3,myCenterIndex);
        filterScale3x3(myOriginalValues, myIndex + aWidthx3 + 3,myCenterIndex);        
    }
    private void filterScale(int[] myOriginalValues, int i, int centerIndex, int myScale) {
        int aWidth = getImageWidth();
        //int scaledWidth = (int)(xScale*aWidth);
        int aXScale = myScale;
        int aYScale = myScale;        
        int x1 = ImageTool.rtrvXPstn(i, aWidth);
        int y1 = ImageTool.rtrvYPstn(i, aWidth);

        if (frameIndex == 1) {
            //upSampledData[i] = myOriginalValues[i];
            imageDrawData.updatePixels(myOriginalValues, x1, y1, aWidth, aWidth);
        } else {
            int xCenter = ImageTool.rtrvXPstn(centerIndex, aWidth);
            int yCenter = ImageTool.rtrvYPstn(centerIndex, aWidth);

            int xDiff = x1 - xCenter;
            int yDiff = y1 - yCenter;

            int xOffSet = (int) (xDiff * aXScale);
            int yOffSet = (int) (yDiff * aYScale);

            //imageIndex = ImageTool.rtrvIndex(x2, y2, scaledWidth);  
            int x2 = xCenter + xOffSet;
            int y2 = yCenter + yOffSet;

            //upSampledData[imageIndex] = myOriginalValues[i];   
            if (x1 >= 0 && y1 >= 0 && x1 < imageWidth && y1 < imageHeight && x2 >= 0 && y2 >= 0 && x2 < octaveWidth && y2 < octaveHeight) {
                octaveIndex = ImageTool.rtrvIndex(x2, y2, imageWidth);
                //System.out.println("UpScaleWindow frameIndex = "+frameIndex+", octaveIndex = "+octaveIndex+", octaveLength = "+octaveLength+", x2 = "+x2+", y2 = "+y2);
                System.out.println("UpSample : xCenter = " + xCenter + ", yCenter = " + yCenter + ", x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2 + ", index = " + i + ", centerIndex = " + centerIndex + " xoffset = " + xOffSet + ", yoffset = " + yOffSet + ", xDiff = " + xDiff + ", yDiff = " + yDiff);
                upOctaveData[octaveIndex] = myOriginalValues[i];

                //imageDrawData.updatePixels(upSampledData, x2,y2,aWidth,scaledWidth);
                imageDrawData.updatePixels(upOctaveData, x2, y2, aWidth, imageWidth);
                //updateInterpolate(upSampledData,aWidth,x2-2,y2-2,x2,y2,x2,y2);
                //interpolate3x3Dgnl(upOctaveData,i);
                //interpolate3x3HV(upOctaveData,i);
            }
        }
    }
    public void filterScale3x3(int myGrayValues[], int anIndex,int myScale) {
        int aWidth = getImageWidth();
        filterScale(myGrayValues, anIndex - aWidth - 1, anIndex, myScale);
        filterScale(myGrayValues, anIndex - aWidth, anIndex, myScale);
        filterScale(myGrayValues, anIndex - aWidth + 1, anIndex, myScale);
        filterScale(myGrayValues, anIndex - 1, anIndex, myScale);
        filterScale(myGrayValues, anIndex, anIndex, myScale);
        filterScale(myGrayValues, anIndex + 1, anIndex, myScale);
        filterScale(myGrayValues, anIndex + aWidth - 1, anIndex, myScale);
        filterScale(myGrayValues, anIndex + aWidth, anIndex, myScale);
        filterScale(myGrayValues, anIndex + aWidth + 1, anIndex, myScale);
    }
    public int[] getFltrdData() {
        if (frameIndex == 1) {
            return imageDrawData.getImagePixels();
        } else {
            return upOctaveData;
        }
    }
    /*
    public void updateInterpolate(int data[], int myWidth, int x1, int y1, int x2, int y2, int x3, int y3) {
        if (x3 > 1 && y3 > 1 && x3 % xScale == 0 && y3 % yScale == 0) {
            int intrplte1 = interpolate(data, x1, y1, x2, y2, x3 - 1, y3);
            int intrplte2 = interpolate(data, x1, y1, x2, y2, x3, y3 - 1);
            int intrplte3 = interpolate(data, x1, y1, x2, y2, x3 - 1, y3 - 1);
            int intrplte4 = interpolate(data, x1, y1, x2, y2, x3 - 2, y3 - 1);
            int intrplte5 = interpolate(data, x1, y1, x2, y2, x3 - 1, y3 - 2);

            //System.out.println("Upsample : x3-1 = "+(x3-1)+", y3 = "+y3);
            imageDrawData.drawData(intrplte1, x3 - 1, y3);

            imageDrawData.drawData(intrplte2, x3, y3 - 1, myWidth);
            imageDrawData.drawData(intrplte3, x3 - 1, y3 - 1, myWidth);
            imageDrawData.drawData(intrplte4, x3 - 2, y3 - 1, myWidth);
            imageDrawData.drawData(intrplte5, x3 - 1, y3 - 2, myWidth);
        }
    }*/
    /*
    public int interpolate(int data[], int x1, int y1, int x2, int y2, int x3, int y3) {
        int imageIndex11 = ImageTool.rtrvIndex(x1, y1, scaledWidth);
        int imageIndex12 = ImageTool.rtrvIndex(x1, y2, scaledWidth);
        int imageIndex21 = ImageTool.rtrvIndex(x2, y1, scaledWidth);
        int imageIndex22 = ImageTool.rtrvIndex(x2, y2, scaledWidth);

        int value11 = data[imageIndex11];
        int value12 = data[imageIndex12];
        int value21 = data[imageIndex21];
        int value22 = data[imageIndex22];

        int value11r = ColorSpectra.rtrvRed(value11);
        int value11g = ColorSpectra.rtrvGreen(value11);
        int value11b = ColorSpectra.rtrvBlue(value11);

        int value12r = ColorSpectra.rtrvRed(value12);
        int value12g = ColorSpectra.rtrvGreen(value12);
        int value12b = ColorSpectra.rtrvBlue(value12);

        int value21r = ColorSpectra.rtrvRed(value21);
        int value21g = ColorSpectra.rtrvGreen(value21);
        int value21b = ColorSpectra.rtrvBlue(value21);

        int value22r = ColorSpectra.rtrvRed(value22);
        int value22g = ColorSpectra.rtrvGreen(value22);
        int value22b = ColorSpectra.rtrvBlue(value22);

        int value33r = (value11r * (x2 - x3) * (y2 - y3) + value21r * (x3 - x1) * (y2 - y3) + value12r * (x2 - x3) * (y3 - y1) + value22r * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33g = (value11g * (x2 - x3) * (y2 - y3) + value21g * (x3 - x1) * (y2 - y3) + value12g * (x2 - x3) * (y3 - y1) + value22g * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33b = (value11b * (x2 - x3) * (y2 - y3) + value21b * (x3 - x1) * (y2 - y3) + value12b * (x2 - x3) * (y3 - y1) + value22b * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33rgb = ColorSpectra.cnvrtRGBINToRGBINT(value33r, value33g, value33b);
        return value33rgb;
    }*/

    public void interpolate3x3Dgnl(int myGrayValues[], int myIndex, int centerIndex,int myScale) {
        int aWidth = getImageWidth();
        int x1 = ImageTool.rtrvXPstn(myIndex, aWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, aWidth);
        int aScale = myScale;
        
        if (frameIndex == 1) {
            //upSampledData[i] = myOriginalValues[i];
        } else {
            int xCenter = ImageTool.rtrvXPstn(centerIndex, aWidth);
            int yCenter = ImageTool.rtrvYPstn(centerIndex, aWidth);

            int xDiff = x1 - xCenter;
            int yDiff = y1 - yCenter;

            int xOffSet = (int) (xDiff * aScale);
            int yOffSet = (int) (yDiff * aScale);

            //imageIndex = ImageTool.rtrvIndex(x2, y2, scaledWidth);  
            int x2 = xCenter + xOffSet;
            int y2 = yCenter + yOffSet;

            if (x1 >= 0 && y1 >= 0 && x1 < imageWidth && y1 < imageHeight && x2 >= 0 && y2 >= 0 && x2 < octaveWidth && y2 < octaveHeight) {

                System.out.println("UpScaleWindow : ");

                interpolateDgnl(myGrayValues, myIndex - aWidth - 1);
                interpolateDgnl(myGrayValues, myIndex - aWidth + 1);
                interpolateDgnl(myGrayValues, myIndex + aWidth - 1);
                interpolateDgnl(myGrayValues, myIndex + aWidth + 1);
            }
        }
    }
    public void interpolate9x9Dgnl(int myGrayValues[], int myIndex, int myCenterIndex,int myScale) {
        int aWidthx3 = 3 * getImageWidth();
        interpolate3x3Dgnl(myGrayValues, myIndex - aWidthx3 - 3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex - aWidthx3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex - aWidthx3 + 3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex - 3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex + 3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex + aWidthx3 - 3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex + aWidthx3,myCenterIndex,myScale);
        interpolate3x3Dgnl(myGrayValues, myIndex + aWidthx3 + 3,myCenterIndex,myScale);                
    }    
    public void interpolate9x9HV(int myGrayValues[], int myIndex, int myCenterIndex,int myScale) {
        int aWidthx3 = 3 * getImageWidth();
        interpolate3x3HV(myGrayValues, myIndex - aWidthx3 - 3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex - aWidthx3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex - aWidthx3 + 3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex - 3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex + 3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex + aWidthx3 - 3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex + aWidthx3,myCenterIndex,myScale);
        interpolate3x3HV(myGrayValues, myIndex + aWidthx3 + 3,myCenterIndex,myScale);                
    }
    public void interpolateDgnl(int data[], int myIndex) {
        int x3 = ImageTool.rtrvXPstn(myIndex, imageWidth);
        int y3 = ImageTool.rtrvYPstn(myIndex, imageWidth);
        //data[myIndex] = 0x000000ff;
        int interpolate1 = interpolateDgnl(data, x3 - 1, y3 - 1, x3 + 1, y3 + 1, x3, y3);
        //imageDrawData.updatePixels(data,x3,y3,imageWidth,imageWidth); 
    }

    public void interpolate3x3HV(int myGrayValues[], int myIndex, int centerIndex,int myScale) {
        int aWidth = getImageWidth();

        int x1 = ImageTool.rtrvXPstn(myIndex, aWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, aWidth);

        int aScale = myScale;
        
        if (frameIndex == 1) {
            //upSampledData[i] = myOriginalValues[i];
        } else {
            int xCenter = ImageTool.rtrvXPstn(centerIndex, aWidth);
            int yCenter = ImageTool.rtrvYPstn(centerIndex, aWidth);

            int xDiff = x1 - xCenter;
            int yDiff = y1 - yCenter;

            int xOffSet = (int) (xDiff * aScale);
            int yOffSet = (int) (yDiff * aScale);

            //imageIndex = ImageTool.rtrvIndex(x2, y2, scaledWidth);  
            int x2 = xCenter + xOffSet;
            int y2 = yCenter + yOffSet;

            if (x1 >= 0 && y1 >= 0 && x1 < imageWidth && y1 < imageHeight && x2 >= 0 && y2 >= 0 && x2 < octaveWidth && y2 < octaveHeight) {

                System.out.println("UpScaleWindow : ");

                interpolateHV(myGrayValues, myIndex - aWidth);

                interpolateHV(myGrayValues, myIndex - 1);
                //interpolateHV(myGrayValues, myIndex);
                interpolateHV(myGrayValues, myIndex + 1);

                interpolateHV(myGrayValues, myIndex + aWidth);
            }
        }
    }

    public void interpolateHV(int data[], int myIndex) {
        int x3 = ImageTool.rtrvXPstn(myIndex, imageWidth);
        int y3 = ImageTool.rtrvYPstn(myIndex, imageWidth);
        int interpolate1 = interpolateHV(data, x3 - 1, y3 - 1, x3 + 1, y3 + 1, x3, y3);
        //imageDrawData.drawData(interpolate1, x3, y3, octaveWidth);
    }

    public int interpolateHrzntl(int data[], int myIndex) {
        int x3 = ImageTool.rtrvXPstn(myIndex, imageWidth);
        int y3 = ImageTool.rtrvYPstn(myIndex, imageWidth);
        return interpolateHrzntl(data, x3 - 2, y3 - 2, x3 + 2, y3 + 2, x3, y3);
    }

    public int interpolate3x3Dgnl(int data[], int x1, int y1, int x2, int y2, int x3, int y3) {
        return interpolateDgnl(data, x1, y1, x2, y2, x3, y3);
    }

    public int interpolateDgnl(int data[], int x1, int y1, int x2, int y2, int x3, int y3) {
        int imageIndex11 = ImageTool.rtrvIndex(x1, y1, imageWidth);
        int imageIndex13 = ImageTool.rtrvIndex(x1, y2, imageWidth);
        int imageIndex31 = ImageTool.rtrvIndex(x2, y1, imageWidth);
        int imageIndex33 = ImageTool.rtrvIndex(x2, y2, imageWidth);

        int imageIndexTmp = ImageTool.rtrvIndex(x3, y3, imageWidth);

        int value11 = data[imageIndex11];
        int value12 = data[imageIndex13];
        int value21 = data[imageIndex31];
        int value22 = data[imageIndex33];

        int value11r = ColorSpectra.rtrvRed(value11);
        int value11g = ColorSpectra.rtrvGreen(value11);
        int value11b = ColorSpectra.rtrvBlue(value11);

        int value12r = ColorSpectra.rtrvRed(value12);
        int value12g = ColorSpectra.rtrvGreen(value12);
        int value12b = ColorSpectra.rtrvBlue(value12);

        int value21r = ColorSpectra.rtrvRed(value21);
        int value21g = ColorSpectra.rtrvGreen(value21);
        int value21b = ColorSpectra.rtrvBlue(value21);

        int value22r = ColorSpectra.rtrvRed(value22);
        int value22g = ColorSpectra.rtrvGreen(value22);
        int value22b = ColorSpectra.rtrvBlue(value22);

        int value33r = (value11r * (x2 - x3) * (y2 - y3) + value21r * (x3 - x1) * (y2 - y3) + value12r * (x2 - x3) * (y3 - y1) + value22r * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33g = (value11g * (x2 - x3) * (y2 - y3) + value21g * (x3 - x1) * (y2 - y3) + value12g * (x2 - x3) * (y3 - y1) + value22g * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33b = (value11b * (x2 - x3) * (y2 - y3) + value21b * (x3 - x1) * (y2 - y3) + value12b * (x2 - x3) * (y3 - y1) + value22b * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        int value33rgb = ColorSpectra.cnvrtRGBINToRGBINT(value33r, value33g, value33b);

        //System.out.println("UpScaleWindwo : r = "+value33r+", g = "+value33g+", b = "+value33b);
        data[imageIndexTmp] = value33rgb;
        //data[imageIndexTmp] = 0x000000ff;  
        return value33rgb;
    }

    public int interpolateHV(int data[], int x1, int y1, int x2, int y2, int x3, int y3) {
        int imageIndex1 = ImageTool.rtrvIndex(x1 + 1, y1, imageWidth);
        int imageIndex2 = ImageTool.rtrvIndex(x1, y2 + 1, imageWidth);
        int imageIndex3 = ImageTool.rtrvIndex(x2, y1 + 1, imageWidth);
        int imageIndex4 = ImageTool.rtrvIndex(x2 - 1, y2, imageWidth);

        int imageIndexTmp = ImageTool.rtrvIndex(x3, y3, imageWidth);

        int value1 = data[imageIndex1];
        int value2 = data[imageIndex2];
        int value3 = data[imageIndex3];
        int value4 = data[imageIndex4];

        int value1r = ColorSpectra.rtrvRed(value1);
        int value1g = ColorSpectra.rtrvGreen(value1);
        int value1b = ColorSpectra.rtrvBlue(value1);

        int value2r = ColorSpectra.rtrvRed(value2);
        int value2g = ColorSpectra.rtrvGreen(value2);
        int value2b = ColorSpectra.rtrvBlue(value2);

        int value3r = ColorSpectra.rtrvRed(value3);
        int value3g = ColorSpectra.rtrvGreen(value3);
        int value3b = ColorSpectra.rtrvBlue(value3);

        int value4r = ColorSpectra.rtrvRed(value4);
        int value4g = ColorSpectra.rtrvGreen(value4);
        int value4b = ColorSpectra.rtrvBlue(value4);

        //int value33r = (value11r * (x2 - x3) * (y2 - y3) + value21r * (x3 - x1) * (y2 - y3) + value12r * (x2 - x3) * (y3 - y1) + value22r * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        //int value33g = (value11g * (x2 - x3) * (y2 - y3) + value21g * (x3 - x1) * (y2 - y3) + value12g * (x2 - x3) * (y3 - y1) + value22g * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        //int value33b = (value11b * (x2 - x3) * (y2 - y3) + value21b * (x3 - x1) * (y2 - y3) + value12b * (x2 - x3) * (y3 - y1) + value22b * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));

        int valuer1 = value2r * (x2 - x3) / (x2 - x1) + value3r * (x3 - x1) / (x2 - x1);
        int valuer2 = value1r * (y2 - y3) / (y2 - y1) + value4r * (y3 - y1) / (y2 - y1);
        int valuer = (valuer1 + valuer2) / 2;

        int valueg1 = value2g * (x2 - x3) / (x2 - x1) + value3g * (x3 - x1) / (x2 - x1);
        int valueg2 = value1g * (y2 - y3) / (y2 - y1) + value4g * (y3 - y1) / (y2 - y1);
        int valueg = (valueg1 + valueg2) / 2;

        int valueb1 = value2b * (x2 - x3) / (x2 - x1) + value3b * (x3 - x1) / (x2 - x1);
        int valueb2 = value1b * (y2 - y3) / (y2 - y1) + value4b * (y3 - y1) / (y2 - y1);
        int valueb = (valueb1 + valueb2) / 2;

        int value33rgb = ColorSpectra.cnvrtRGBINToRGBINT(valuer, valueg, valueb);
        System.out.println("UpScaleWindwo : r = " + valuer + ", g = " + valueg + ", b = " + valueb);
        data[imageIndexTmp] = value33rgb;

        return value33rgb;
    }
    

    public int interpolateHrzntl(int data[], int x1, int y1, int x2, int y2, int x3, int y3) {
        int imageIndex11 = ImageTool.rtrvIndex(x1, y1, imageWidth);
        int imageIndex13 = ImageTool.rtrvIndex(x1, y2, imageWidth);
        int imageIndex31 = ImageTool.rtrvIndex(x2, y1, imageWidth);
        int imageIndex33 = ImageTool.rtrvIndex(x2, y2, imageWidth);

        int imageIndexTmp = ImageTool.rtrvIndex(x3, y3, imageWidth);

        int value11 = data[imageIndex11];
        int value12 = data[imageIndex13];
        int value21 = data[imageIndex31];
        int value22 = data[imageIndex33];

        int value11r = ColorSpectra.rtrvRed(value11);
        int value11g = ColorSpectra.rtrvGreen(value11);
        int value11b = ColorSpectra.rtrvBlue(value11);

        int value12r = ColorSpectra.rtrvRed(value12);
        int value12g = ColorSpectra.rtrvGreen(value12);
        int value12b = ColorSpectra.rtrvBlue(value12);

        int value21r = ColorSpectra.rtrvRed(value21);
        int value21g = ColorSpectra.rtrvGreen(value21);
        int value21b = ColorSpectra.rtrvBlue(value21);

        int value22r = ColorSpectra.rtrvRed(value22);
        int value22g = ColorSpectra.rtrvGreen(value22);
        int value22b = ColorSpectra.rtrvBlue(value22);

        //int value33r = (value11r * (x2 - x3) * (y2 - y3) + value21r * (x3 - x1) * (y2 - y3) + value12r * (x2 - x3) * (y3 - y1) + value22r * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        //int value33g = (value11g * (x2 - x3) * (y2 - y3) + value21g * (x3 - x1) * (y2 - y3) + value12g * (x2 - x3) * (y3 - y1) + value22g * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));
        //int value33b = (value11b * (x2 - x3) * (y2 - y3) + value21b * (x3 - x1) * (y2 - y3) + value12b * (x2 - x3) * (y3 - y1) + value22b * (x3 - x1) * (y3 - y1)) / ((x2 - x1) * (y2 - y1));

        int value33r1 = value11r * (x2 - x3) / (x2 - x1) + value21r * (x3 - x1) / (x2 - x1);
        int value33r2 = value12r * (x2 - x3) / (x2 - x1) + value22r * (x3 - x1) / (x2 - x1);
        int value33r = value33r1 * (y2 - y3) / (y2 - y1) + value33r2 * (y3 - y1) / (y2 - y1);

        int value33g1 = value11g * (x2 - x3) / (x2 - x1) + value21g * (x3 - x1) / (x2 - x1);
        int value33g2 = value12g * (x2 - x3) / (x2 - x1) + value22g * (x3 - x1) / (x2 - x1);
        int value33g = value33g1 * (y2 - y3) / (y2 - y1) + value33g2 * (y3 - y1) / (y2 - y1);

        int value33b1 = value11b * (x2 - x3) / (x2 - x1) + value21b * (x3 - x1) / (x2 - x1);
        int value33b2 = value12b * (x2 - x3) / (x2 - x1) + value22b * (x3 - x1) / (x2 - x1);
        int value33b = value33b1 * (y2 - y3) / (y2 - y1) + value33b2 * (y3 - y1) / (y2 - y1);

        int value33rgb = ColorSpectra.cnvrtRGBINToRGBINT(value33r, value33g, value33b);
        data[imageIndexTmp] = value33rgb;
        return value33rgb;
    }
}