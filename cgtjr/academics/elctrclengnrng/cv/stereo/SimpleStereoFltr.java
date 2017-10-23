/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.stereo;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.video.CrrltnCrnrFltr;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author finitesystem
 */
public class SimpleStereoFltr extends CrrltnCrnrFltr{

    private int xLeft;
    private int xRight;
    private double zDistance;
    private double focalLength;
    private int disparity;
    private double translation[] = {-3.3385294685524420,0.0048752222149917465,-0.10621612775258191};
    private double baseline = translation[0];
    private double cxLeft = 335.1;
    private double cxRight = 240.2;
  
    public void filter(int myImageData[],int i){
        super.filter(myImageData, i);
    
        computeZ(i);
        
    }
    public void cmptVelocity(int myIndex) {

        int myIndex2 = getIndexValue();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        
        int x1 = ImageTool.rtrvXPstn(myIndex, aWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, aWidth);

        int x2 = ImageTool.rtrvXPstn(myIndex2, aWidth);
        int y2 = ImageTool.rtrvYPstn(myIndex2, aWidth);

        int vx = x2 - x1;
        int vy = y2 - y1;

        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());
        //System.out.println("CrrltnMatch: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnMatch: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }       
    public double getBaseline() {
        return baseline;
    }
    public int getxLeft() {
        return xLeft;
    }
    public int getxRight() {
        return xRight;
    }
    public double getZDistance() {
        return zDistance;
    }
    public double getFocalLength() {
        return focalLength;
    }

    public int getDisparity() {
        return disparity;
    }

    public void setBaseline(double baseline) {
        this.baseline = baseline;
    }

    public void setxLeft(int xLeft) {
        this.xLeft = xLeft;
    }

    public void setxRight(int xRight) {
        this.xRight = xRight;
    }

    public void setZDistance(double zDistance) {
        this.zDistance = zDistance;
    }

    public void setFocalLength(double focalLength) {
        this.focalLength = focalLength;
    }

    public void setDisparity(int disparity) {
        this.disparity = disparity;
    }
    public double computeZ(){
        double aZDistance = focalLength*baseline/(xLeft-xRight);
        return aZDistance;        
    }    
    public double computeZ(int myIndex){
        
        int myIndex2 = getIndexValue();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();
        
        int x1 = ImageTool.rtrvXPstn(myIndex, aWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, aWidth);

        int x2 = ImageTool.rtrvXPstn(myIndex2, aWidth);
        int y2 = ImageTool.rtrvYPstn(myIndex2, aWidth);

        xLeft = -(x1 - (int)cxLeft);
        xRight = -(x2 - (int)cxRight);
        
        focalLength = 534.71330014344005;//5.3471330014344005e+002 
                
        double aZDistance = focalLength*baseline/(xLeft-xRight);
        //System.out.println("x1 = "+x1+", x2 = "+x2+", cxLeft = "+cxLeft+", xLeft = "+xLeft+", cxRight = "+cxRight+", xRight = "+xRight+","+aZDistance);
        return aZDistance;        
    }    
}