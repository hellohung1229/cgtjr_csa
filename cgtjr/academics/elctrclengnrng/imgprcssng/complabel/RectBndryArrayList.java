/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.math.geometry.linepnts.LineDraw;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author clayton g thomas jr
 */
public class RectBndryArrayList extends ArrayList {

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
    
    public RectBndryArrayList() {
        super();
        
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        //System.out.println("ShpBndry: constructor xmax =" + xMax+", x min = "+xMin);
    }    
    public RectBndryArrayList(Point myPoint) {
        super();
        
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        setXAvg((int)myPoint.getX());
        setYAvg((int)myPoint.getY());  
        updtBndry(myPoint);
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
    public int retrieveCenterX(){
        int aCenterX = (int)((getXMax()-getXMin())/2);
        return aCenterX;
    }
    public int retrieveCenterY(){
        int aCenterY = (int)((getYMax()-getYMin())/2);
        return aCenterY;
    }    
    public void setColor(int myColor) {
        color = myColor;
    }

    public int getColor() {
        return color;
    }
    public boolean add(Object myObject){
        Point aPoint = (Point)myObject;
        return updtBndry(aPoint);
        //return super.add(myObject);
    }
    public boolean updtBndry(Point myPoint) {
        //super.add(myPoint);
        updateXAvg((int)myPoint.getX());
        updateYAvg((int)myPoint.getY());  
        return updtBndry((int) myPoint.getX(), (int) myPoint.getY());
        //return super.add(myPoint);
    }

    public boolean updtBndry(int xPos, int yPos) {
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
        Point aPoint = new Point(xPos, yPos);        
        return super.add(aPoint);        
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