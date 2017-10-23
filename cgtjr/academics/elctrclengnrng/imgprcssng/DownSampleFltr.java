/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;

/**
 *
 * @author clayton g thomas jr
 */
public class DownSampleFltr extends ImageFilter {

    private int resizedValues[];
    private float percent = 0.75f;
    private int orgnlWidth;
    private int orgnlHeight;
    private int imageWidth;
    private int imageHeight;    
    private ImageDrawData imageDrawData;     
    
    public DownSampleFltr() {
    }

    public DownSampleFltr(String myFileName) {
        /*
        super(myFileName);
        imageWidth = (int) (percent * getImageWidth());
        imageHeight = (int) (percent * getImageHeight());
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        resizedValues = new int[imageWidth * imageHeight];        
        * 
        */
    }
    public DownSampleFltr(int myPixelData[], int myImageWidth, int myImageHeight) {
        /*
        super(myPixelData, myImageWidth, myImageHeight);
        orgnlWidth = myImageWidth;
        orgnlHeight = myImageHeight;
        imageWidth = (int) (percent * myImageWidth);
        imageHeight = (int) (percent * myImageHeight);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        resizedValues = new int[imageWidth * imageHeight];
        * 
        */
    }

    public void initialize(int myImageWidth, int myImageHeight) {
        orgnlWidth = myImageWidth;
        orgnlHeight = myImageHeight;  
        imageWidth = (int) (percent * myImageWidth) ;
        imageHeight = (int) (percent * myImageHeight);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);  
        //super.initialize(aWidth, aHeight);
        resizedValues = new int[imageWidth * imageHeight]; 
        
    }

    public void filter(int myOriginalValues[], int i) {

         /* * 
        int x = ImageTool.rtrvXPstn(i, orgnlWidth);
        int y = ImageTool.rtrvYPstn(i, orgnlWidth);
        int resizedX = (int)(percent * x);
        int resizedY = (int)(percent * y);        
        int index = (int) ImageTool.rtrvIndex(resizedX,resizedY,imageWidth);        
        //resizedValues[index] = myOriginalValues[i];
      
        */
    }

    public int[] getFltrdData() {
        return resizedValues;
    }
}