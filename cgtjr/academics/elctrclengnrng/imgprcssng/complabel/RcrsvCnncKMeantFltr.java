/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.math.geometry.linepnts.LineDraw;

/**
 *
 * @author clayton g thomas jr
 */
public class RcrsvCnncKMeantFltr extends ImageFilter {

    private int indexValues[];
    private int dataValues[];
    private int outputValues[];
    private int nmbrCmpnnts;
    private int colorDelta;
    private int labelIndex;
    private int inputValues[];
    private int width;
    private int height;
    private int clrdCmpnnts[];
    private int cmpnntColor;
    private int cmpnntGrp[];
    private static int groupXMax;
    private static int groupXMin;
    private static int groupYMax;
    private static int groupYMin;
    private static boolean boxReady;
    private static int nmbrDtctd;
    private ImageDrawData ccLabelInfo;
    
    public RcrsvCnncKMeantFltr() {
        nmbrCmpnnts = 20;
        colorDelta = 255 / nmbrCmpnnts;                   
    }

    public int rtrvColor() {
        return rtrvColor(rtrvNextIndex());
    }

    public int rtrvColor(int myIndex) {
        int anIndex = myIndex % 20;
        float aColor = (anIndex * colorDelta) / 255.0f;
        int rgbColor[] = ColorSpectra.cnvrtHSBToRGB(aColor);
        int rgb = ((rgbColor[0] & 0x000000ff) << 16)
                | ((rgbColor[1] & 0x000000ff) << 8)
                | ((rgbColor[2] & 0x000000ff));
        return rgb;
    }

    public int rtrvNextIndex() {
        labelIndex = labelIndex + 1;
        return labelIndex;
    }
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        width = getImageWidth();
        height = getImageHeight();
        indexValues = new int[width * height];
        ImageFilter anImageFilter = getInputFilter();
        ccLabelInfo = new ImageDrawData(myImageWidth, myImageHeight); 
        //imageDrawData.setReservedColor1(reservedColor1);
        //imageDrawData.setReservedColor2(reservedColor2);   
    }
    public void filter(int myOriginalValues[], int i) {
        dataValues = myOriginalValues;
        compLabel(i);
    }
    public void compLabel(int i) {
        int aColor1 = ColorSpectra.rtrv000000ff(dataValues[i]);
        if (aColor1 >= 0x5f) {
            cmpnntColor = rtrvColor();
            int x = ImageTool.rtrvXPstn(i, width);
            int y = ImageTool.rtrvYPstn(i, width);
            compLabel(x, y, cmpnntColor);
        }
    }
    public boolean isDataPixel(int myDataValues[],int i,int j){
        int anIndex = ImageTool.rtrvIndex(i, j, width);
        boolean isData = false;
        
        if(myDataValues[anIndex] != 0x00000000){
            isData = true;
        }
        return isData;
    }
    void compLabel(int i, int j, int m) {

        if (i >= 0 && i < width && j >= 0 && j < height) {
            int ccLabelData[] = ccLabelInfo.getImagePixels(); 
            if(isDataPixel(ccLabelData,i,j) == true){
                return;
            }
            int pixelValue1 = getPixel(dataValues, i, j);
            if (pixelValue1 == 0xff||pixelValue1 >= 0x5f) {
                //int ccLabelData[] = ccLabelInfo.getImagePixels();
                //setPixel(ccLabelData, i, j, m);
                ccLabelInfo.drawData(m, i, j);
                
                compLabel(i - 1, j - 1, m);
                compLabel(i - 1, j, m);
                compLabel(i - 1, j + 1, m);
                compLabel(i, j - 1, m);
                compLabel(i, j + 1, m);
                compLabel(i + 1, j - 1, m);
                compLabel(i + 1, j, m);
                compLabel(i + 1, j + 1, m);
            }
        }
    }

    private int getPixel(int i, int j) {
        return getPixel(dataValues, i, j);
    }

    private int getPixel(int data[], int i, int j) {
        int anIndex = 0;
        if (i >= 0 && j >= 0 && i < width && j < height) {
            anIndex = ImageTool.rtrvIndex(i, j, width);
            //int aColor = ColorSpectra.rtrv000000ff(data[anIndex]);
            int aColor = (data[anIndex] & 0x000000ff);
            return aColor;
        } else {
            return 0x00;
        }
    }

    private void setPixel(int i, int j, int m) {
        setPixel(dataValues, i, j, m);
    }

    private void setPixel(int dataValues[], int i, int j, int m) {
        int anIndex = 0;
        if (i >= 0 && j >= 0 && i < width && j < height) {
            anIndex = ImageTool.rtrvIndex(i, j, width);
            dataValues[anIndex] = m;
        }
    }

    public int[] getFltrdData() {
        //return dataValues;
        return ccLabelInfo.getImagePixels();
    }
}