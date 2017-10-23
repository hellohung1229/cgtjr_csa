/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.math.geometry.linepnts.LineDraw;

/**
 *
 * @author clayton g thomas jr
 */
public class BoxBoundary {

    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;
    private int zMax;
    private int zMin;
    private int color;

    public BoxBoundary() {

        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        //System.out.println("ShpBndry: constructor xmax =" + xMax+", x min = "+xMin);
    }

    public double getXMax() {
        return xMax;
    }

    public void setXMax(int xMax) {
        this.xMax = xMax;
    }

    public double getXMin() {
        return xMin;
    }

    public void setXMin(int xMin) {
        this.xMin = xMin;
    }

    public int getYMax() {
        return yMax;
    }

    public void setYMax(int yMax) {
        this.yMax = yMax;
    }

    public int getYMin() {
        return yMin;
    }
    public void setYMin(int yMin) {
        this.yMin = yMin;
    }
    public int getZMin() {
        return zMin;
    }
    public void setZMin(int zMin) {
        this.zMin = zMin;
    }    
    public void setZMax(int zMax) {
        this.zMax = zMax;
    }     
    public int getZMax() {
        return zMax;
    }         
    public void setColor(int myColor){
        color = myColor;
    }
    public int getColor(){
        return color;
    }
            
    public void updtBndry(int xPos, int yPos,int zPos) {

        if (xPos >= xMax) {
            setXMax(xPos);
        }
        if (yPos >= yMax) {
            setYMax(yPos);
        }
        if (xPos <= xMin) {
            setXMin(xPos);
        }
        if (yPos <= yMin) {
            setYMin(yPos);
        }
        if (zPos >= zMax) {
            setZMax(zPos);
        }      
        if (zPos <= zMin) {
            setZMin(zPos);
        }              
    }
    public void drawSquare(int clrdCmpnnts[],int myWidth,int myHeight,int myColor) {
        int width = myWidth;
        int height = myHeight;

        color = myColor;
        
        System.out.println("CmpnntRectangle: XMin = " + xMin + ", xMax = " + xMax + ", yMin = " + yMin + ", yMax = " + yMax+", width = "+width+", height = "+height+", color = "+color);

        LineDraw.drawLine(xMin, yMin, xMax, yMin, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMin, yMin, xMin, yMax, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMax, yMin, xMax, yMax, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMin, yMax, xMax, yMax, clrdCmpnnts, width, height, color);
        //LineDraw.drawLine(zMax, yMin, xMax, yMax, clrdCmpnnts, width, height, color);
        //LineDraw.drawLine(xMin, yMax, xMax, yMax, clrdCmpnnts, width, height, color);        
    }    
}