/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author finitesystem
 */
public class UpSample extends YSclFltr{
    private int downSampledData[];
    private int imageIndex;
    private double xScale;
    private double yScale;    
    private ImageDrawData imageDrawData;     
    private int scaledWidth;
    private int scaledHeight ;
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        int aLength = myImageWidth * myImageHeight;
        xScale = 2;
        yScale = 2;    
        scaledWidth = (int)(xScale*myImageWidth);
        scaledHeight = (int)(yScale*myImageHeight);        
        int sampleLength = scaledWidth*scaledHeight;
        downSampledData = new int[sampleLength];  

        imageDrawData = new ImageDrawData(myImageWidth, myImageHeight);        
        frameIndex++;
    }
    public void setXScale(double myScale){
        xScale = myScale;
    }
    public void filter(int myOriginalValues[], int i) { 
        
        int aWidth = getImageWidth();        
        int x1 = ImageTool.rtrvXPstn(i,aWidth);
        int y1 = ImageTool.rtrvYPstn(i,aWidth);
    
        //System.out.println("UpSample frameIndex = "+frameIndex);
        if(frameIndex ==1 ){
            downSampledData[i] = myOriginalValues[i];
            imageDrawData.updatePixels(downSampledData,x1,y1,aWidth,aWidth);            
        }else{
            int x2 = (int)(x1*xScale);
            int y2 = (int)(y1*yScale);
            imageIndex = ImageTool.rtrvIndex(x2, y2, scaledWidth);  
        //System.out.println("UpSample : x2 = "+x2+", y2 = "+y2);
            downSampledData[imageIndex] = myOriginalValues[i];   
            imageDrawData.updatePixels(downSampledData, x2,y2,aWidth,scaledWidth);        
            updateInterpolate(downSampledData,aWidth,x2-2,y2-2,x2,y2,x2,y2);            
        } 

    }
    public int[] getFltrdData() {
        return imageDrawData.getImagePixels();
    }
    public void updateInterpolate(int data[],int myWidth,int x1,int y1,int x2,int y2,int x3,int y3){
        if(x3>1 && y3 > 1 && x3%xScale == 0 && y3%yScale == 0){
            int intrplte1 =  interpolate(data,x1,y1,x2,y2,x3-1,y3);
            int intrplte2 =  interpolate(data,x1,y1,x2,y2,x3,y3-1);
            int intrplte3 =  interpolate(data,x1,y1,x2,y2,x3-1,y3-1);            
            int intrplte4 =  interpolate(data,x1,y1,x2,y2,x3-2,y3-1);
            int intrplte5 =  interpolate(data,x1,y1,x2,y2,x3-1,y3-2); 
            
            //System.out.println("Upsample : x3-1 = "+(x3-1)+", y3 = "+y3);
            imageDrawData.drawData(intrplte1,x3-1,y3,myWidth);            
            imageDrawData.drawData(intrplte2,x3,y3-1,myWidth);             
            imageDrawData.drawData(intrplte3,x3-1,y3-1,myWidth);                         
            imageDrawData.drawData(intrplte4,x3-2,y3-1,myWidth);                       
            imageDrawData.drawData(intrplte5,x3-1,y3-2,myWidth); 
        }
    }
    public int interpolate(int data[],int x1,int y1,int x2,int y2,int x3,int y3){
                
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
        
        int value33r = (value11r*(x2-x3)*(y2-y3)+value21r*(x3-x1)*(y2-y3)+value12r*(x2-x3)*(y3-y1)+value22r*(x3-x1)*(y3-y1))/((x2-x1)*(y2-y1)); 
        int value33g = (value11g*(x2-x3)*(y2-y3)+value21g*(x3-x1)*(y2-y3)+value12g*(x2-x3)*(y3-y1)+value22g*(x3-x1)*(y3-y1))/((x2-x1)*(y2-y1));
        int value33b = (value11b*(x2-x3)*(y2-y3)+value21b*(x3-x1)*(y2-y3)+value12b*(x2-x3)*(y3-y1)+value22b*(x3-x1)*(y3-y1))/((x2-x1)*(y2-y1));
        int value33rgb = ColorSpectra.cnvrtRGBINToRGBINT(value33r,value33g,value33b);
        return value33rgb;
    }
}