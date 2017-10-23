/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.linepnts.LineDraw;

import java.util.ArrayList;

/**
 *
 * @author clayton g thomas jr
 */
public class RectBndryPntArrayList extends ArrayList {

    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;
    private int zMax;
    private int zMin;
    private int color;
    private int xSum;
    private int ySum;
    private int zSum;    
    private int xAvg;
    private int yAvg;
    private int zAvg;    
    private int boxID;
    
    public RectBndryPntArrayList(Pnt myPnt) {
        super();
        
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        setXAvg((int)myPnt.getX1());
        setYAvg((int)myPnt.getY1());  
        updtBndry(myPnt);
        //System.out.println("ShpBndry: constructor xmax =" + xMax+", x min = "+xMin);
    }
    public int getBoxID() {
        return boxID;
    }
    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }    
    public void updateXSum(int myXSum){
        xSum  += myXSum;
    }
    public void updateYSum(int myYSum){
        ySum  += myYSum;
    }
    public void updateZSum(int myZSum){
        zSum  += myZSum;
    }    
    public void updateXAvg(int myX){
        xSum  = xAvg + myX;
        xAvg = xSum/2;
    }
    public void updateYAvg(int myY){
        ySum  = yAvg + myY;
        yAvg = ySum/2;
    }
    public void updateZAvg(int myZ){
        zSum  = zAvg + myZ;
        zAvg = zSum/2;        
    }    

    public int getXAvg() {
        return xAvg;
    }

    public int getYAvg() {
        return yAvg;
    }

    public int getZAvg() {
        return zAvg;
    }
    
    public int getXSum() {
        return xSum;
    }

    public void setXSum(int xSum) {
        this.xSum = xSum;
    }

    public int getYSum() {
        return ySum;
    }

    public void setYSum(int ySum) {
        this.ySum = ySum;
    }

    public int getZSum() {
        return zSum;
    }

    public void setZSum(int zSum) {
        this.zSum = zSum;
    }

    public int getXMax() {
        return xMax;
    }

    public void setXMax(int xMax) {
        this.xMax = xMax;
    }

    public int getXMin() {
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
    public int getZMax() {
        return zMax;
    }

    public void setZMax(int zMax) {
        this.zMax = zMax;
    }      
    public int retrieveCenterX(){
        int aCenterX = (int)((getXMax()-getXMin())/2);
        return aCenterX;
    }
    public int retrieveCenterY(){
        int aCenterY = (int)((getYMax()-getYMin())/2);
        return aCenterY;
    }   
    public int retrieveCenterZ(){
        int aCenterZ = (int)((getZMax()-getZMin())/2);
        return aCenterZ;
    }      
    public void setColor(int myColor) {
        color = myColor;
    }

    public int getColor() {
        return color;
    }
    public boolean add(Object myObject){
        Pnt aPnt = (Pnt)myObject;
        return updtBndry(aPnt);
        //return super.add(myObject);
    }
    public boolean updtBndry(Pnt myPnt) {
        //super.add(myPnt);
        updateXAvg((int)myPnt.getX1());
        updateYAvg((int)myPnt.getY1());  
        return updtBndry((int) myPnt.getX1(), (int) myPnt.getY1());
        //return super.add(myPnt);
    }
    private boolean updtBndry(int xPos, int yPos) {
        return updtBndry(xPos,yPos,0);
    }
    private boolean updtBndry(int xPos, int yPos,int zPos) {
        if(xPos >= xMax) {
            setXMax(xPos);
        }else if (xPos <= xMin) {
            setXMin(xPos);
        }
        if(yPos >= yMax) {
            setYMax(yPos);
        }else if (yPos <= yMin) {
            setYMin(yPos);
        }
        if(zPos <= zMin) {
            setZMin(zPos);
        }else if(zPos >= zMax) {
            setZMax(zPos);
        }                
        Pnt aPnt = new Pnt(xPos,yPos,zPos);        
        return super.add(aPnt);        
    }

    public void drawSquare(int clrdCmpnnts[], int myWidth, int myHeight, int myColor) {
        int width = myWidth;
        int height = myHeight;

        color = myColor;

        //System.out.println("CmpnntRectangle: XMin = " + xMin + ", xMax = " + xMax + ", yMin = " + yMin + ", yMax = " + yMax + ", width = " + width + ", height = " + height + ", color = " + color);

        LineDraw.drawLine(xMin, yMin, xMax, yMin, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMin, yMin, xMin, yMax, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMax, yMin, xMax, yMax, clrdCmpnnts, width, height, color);
        LineDraw.drawLine(xMin, yMax, xMax, yMax, clrdCmpnnts, width, height, color);
    }

    public void setXAvg(int myXAvg) {
        this.xAvg = myXAvg;
    }

    public void setYAvg(int myYAvg) {
        this.yAvg = myYAvg;
    }

    public void setZAvg(int myZAvg) {
        this.zAvg = myZAvg;
    }
}