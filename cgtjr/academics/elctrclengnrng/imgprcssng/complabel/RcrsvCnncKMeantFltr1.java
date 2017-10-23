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
public class RcrsvCnncKMeantFltr1 extends ImageFilter {

    private static int dataValues[];
    private int nmbrCmpnnts;
    private int colorDelta;
    private int labelIndex;
    private int width;
    private int height;
    private int cmpnntColor;
    private ImageDrawData ccLabelInfo;

    public RcrsvCnncKMeantFltr1() {
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
        //indexValues = new int[width * height];
        //ImageFilter anImageFilter = getInputFilter();
     
        ccLabelInfo = new ImageDrawData(myImageWidth, myImageHeight);
        //imageDrawData.setReservedColor1(reservedColor1);
        //imageDrawData.setReservedColor2(reservedColor2);   
    }

    public void filter(int myOriginalValues[], int i) {
        dataValues = myOriginalValues;
        compLabel(myOriginalValues,i);
    }

    public void compLabel(int myDataValues[], int i) {
        //int aColor1 = ColorSpectra.rtrv000000ff(dataValues[i]);
        int ccLabelData[] = ccLabelInfo.getImagePixels();
        //int pixelValue1 = getPixel(dataValues, i, j);
        
        int aColor = ColorSpectra.cnvrtRGBGry8(myDataValues[i]);
    System.out.println("compLabel 1: ccLabelData[i] = "+ccLabelData[i]+", aColor = "+aColor+", i = "+i);   
        if (ccLabelData[i] == 0 && aColor >= 20) {
            int aCmpnntColor = rtrvColor();
            int x = ImageTool.rtrvXPstn(i, width);
            int y = ImageTool.rtrvYPstn(i, width);
            compLabel(myDataValues,x, y, 0x00ff0000);
        }

    }

    public boolean isColorPixel(int myDataValues[], int i, int j) {

        int anIndex = ImageTool.rtrvIndex(i, j, width);
        boolean isData = false;

        if (myDataValues[anIndex] != 0x00000000) {
            isData = true;
        }
        return isData;
    }

    void compLabel(int myPixelValues[],int i, int j, int m) {

        if (i >= 0 && i < width && j >= 0 && j < height) {
            int aCCLabelData[] = ccLabelInfo.getImagePixels();
            //int aColor = myDataColor;
            int anIndex = ImageTool.rtrvIndex(i, j, width);
            int aColor = ColorSpectra.cnvrtRGBGry8(myPixelValues[anIndex]);
       System.out.println("compLabel 2: ccLabelData[i] = "+aCCLabelData[anIndex]+", aColor = "+aColor+ " i= "+i+" j = "+j);   
                
            if (aCCLabelData[anIndex] == 0x00000000 && aColor > 20) {
               
                System.out.println("compLabel2b aCCLabelData[anIndex] = "+aCCLabelData[anIndex]);
            
            //int pixelValue1 = getPixel(dataValues, i, j);
            //int aCCLabelData[] = ccLabelInfo.getImagePixels();

            //int aCCLabelData[] = ccLabelInfo.getImagePixels();
            //setPixel(aCCLabelData, i, j, m);
            ccLabelInfo.drawData(m, i, j);
           
             compLabel(myPixelValues,i - 1, j - 1, 0x0000ff00);
            
             compLabel(myPixelValues,i - 1, j, 0x0000ff00);
             compLabel(myPixelValues,i - 1, j + 1, 0x0000ff00);
             compLabel(myPixelValues,i, j - 1, 0x0000ff00);
             compLabel(myPixelValues,i, j + 1, 0x0000ff00);
             compLabel(myPixelValues,i + 1, j - 1, 0x0000ff00);
             compLabel(myPixelValues,i + 1, j, 0x0000ff00);
             compLabel(myPixelValues,i + 1, j + 1, 0x0000ff00);
            /* */
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
            int aColor = (data[anIndex]);
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