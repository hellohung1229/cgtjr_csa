/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;

public class DisparityRctfdFltr extends SADCornerRctfdFltr {


    private int disparityMapData[];
    private int imgWidth;
    private int imgHeight;       

    private int grayValues[];
    
    private ImageDrawData imagePixelData;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        imgWidth = myImageWidth;        
        imgHeight = myImageHeight;
        disparityMapData = new int[aLength];     
        imagePixelData = getImageDrawData();        
    }
    public void filter(int dataValues[], int i) {
        //crrntImgData = dataValues;
        //setPrevImgData(dataValues,i);
        //updatePixels(dataValues, i);
        super.filter(dataValues, i);
        if (frameIndex > 1) {
            disparityFltr(dataValues,i);            
        }        
    } 
    public void disparityFltr(int dataValues[], int i) {        
           drawData(i);
    }
    
    public void drawData(int myIndex) {
        super.drawData(myIndex);
        int aMatchingIndex = getMatchingIndex();
        int x1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int x2 = ImageTool.rtrvXPstn(aMatchingIndex, imgWidth);
        int y2 = ImageTool.rtrvYPstn(aMatchingIndex, imgWidth);

        int dispX = Math.abs(x2 - x1);
        int dispY = y2 - y1;

        int pixelColor = (dispX*126*2)/243;

        //imagePixelData.drawData(pixelColor,myIndex);
        //System.out.println("DisparityRctfdFltr: imgWidth = "+imgWidth+", x2 = "+x2+", x1 = "+x1+", y2 = "+y2+", y1= "+y1+", dispX = "+dispX+", dispY = "+dispY+", pixelColor = "+pixelColor);
        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());
        //int myPixelData[] = imagePixelData.getImagePixels();
        //if(PntTool.getDistance(x1, y1, x2, y2) < 200)
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, imgWidth, imgHeight,0x0050e60d,0x0050e60d);
        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }       
    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }    
    public void startPrsng() {
    }
    public void finish() {
        System.out.println("DisparityRctfdFltr: finish parsing frame = " + frameIndex);
    }
}