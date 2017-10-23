/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.geometry.linepnts.LineDraw;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author clayton g thomas jr
 */
public class BoxBndry extends ArrayList {

    private double xMax;
    private double xMin;
    private double yMax;
    private double yMin;
    private double zMax;
    private double zMin;
    private int color;
    private double xSum;
    private double ySum;
    private double zSum;    
    private double xAvg;
    private double yAvg;
    private double zAvg;    
    private int boxID;
    
    public BoxBndry(double myPnt[]) {
        super();
        
        xMax = -Double.MAX_VALUE;
        yMax = -Double.MAX_VALUE;
        zMax = -Double.MAX_VALUE;      
        
        xMin = Double.MAX_VALUE;
        yMin = Double.MAX_VALUE;        
        zMin = Double.MAX_VALUE;
  
        //setXAvg((int)myPnt[0]);
        //setYAvg((int)myPnt[1]);  
        //setZAvg((int)myPnt[2]);        
        //System.out.println("ShpBndry: constructor xmax =" + xMax+", x min = "+xMin);
    }
    public BoxBndry() {
        super();
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;
        zMax = -Integer.MAX_VALUE;      
        
        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;        
        zMin = Integer.MAX_VALUE;        
    }
    public int getBoxID() {
        return boxID;
    }
    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }    
    public void updateXSum(double myXSum){
        xSum  += myXSum;
    }
    public void updateYSum(double myYSum){
        ySum  += myYSum;
    }
    public void updateZSum(double myZSum){
        zSum  += myZSum;
    }    
    public void updateXAvg(double myX){
        xSum  = xAvg + myX;
        xAvg = xSum/2;
    }
    public void updateYAvg(double myY){
        ySum  = yAvg + myY;
        yAvg = ySum/2;
    }
    public void updateZAvg(double myZ){
        zSum  = zAvg + myZ;
        zAvg = zSum/2;        
    }    
    public double getXAvg() {
        return xAvg;
    }
    public double getYAvg() {
        return yAvg;
    }
    public double getZAvg() {
        return zAvg;
    }
    
    public double getXSum() {
        return xSum;
    }

    public void setXSum(double xSum) {
        this.xSum = xSum;
    }

    public double getYSum() {
        return ySum;
    }

    public void setYSum(double ySum) {
        this.ySum = ySum;
    }

    public double getZSum() {
        return zSum;
    }

    public void setZSum(double zSum) {
        this.zSum = zSum;
    }

    public double getXMax() {
        return xMax;
    }

    public void setXMax(double xMax) {
        this.xMax = xMax;
    }

    public double getXMin() {
        return xMin;
    }

    public void setXMin(double xMin) {
        this.xMin = xMin;
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {
        this.yMax = yMax;
    }
    public double getYMin() {
        return yMin;
    }
    public void setYMin(double yMin) {
        this.yMin = yMin;
    }
    public double getZMin() {
        return zMin;
    }
    public void setZMin(double zMin) {
        this.zMin = zMin;
    }    
    public double getZMax() {
        return zMax;
    }
    public void setZMax(double zMax) {
        this.zMax = zMax;
    }      
    public double retrieveCenterX(){
        double aCenterX = (double)((getXMax()-getXMin())/2);
        return aCenterX;
    }
    public double retrieveCenterY(){
        double aCenterY = (double)((getYMax()-getYMin())/2);
        return aCenterY;
    }   
    public double retrieveCenterZ(){
        double aCenterZ = (double)((getZMax()-getZMin())/2);
        return aCenterZ;
    }      
    public void setColor(int myColor) {
        color = myColor;
    }
    public int getColor() {
        return color;
    }
    public boolean updateBndry(double myPnt[]) {
        //super.add(myPnt);
        updateXAvg((double)myPnt[0]);
        updateYAvg((double)myPnt[1]);  
        updateZAvg((double)myPnt[2]);          
        updateBndry((double) myPnt[0], (double) myPnt[1],(double)myPnt[2]);    
        return super.add(myPnt);            
    }
    public void updateBndry(ArrayList myWrldCrdnts) {
        Iterator anIterator = myWrldCrdnts.iterator();
        while(anIterator.hasNext()){
            double aWrldC[] = (double[])anIterator.next();
            updateBndry(aWrldC[0],aWrldC[1],aWrldC[2]);
        }
        
        System.out.println("BoxBdry : xmin = "+getXMin()+", xmax = "+getXMax()+",ymin = "+getYMin()+", ymax = "+getYMax()+", zmin = "+getZMin()+ ", zmax = "+ getZMax());
       
    }    
    /*
    private boolean updateBndry(int xPos, int yPos) {
        return updateBndry(xPos,yPos,0);
    }*/
    private void updateBndry(double xPos, double yPos,double zPos) {
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
    }
    public CubeCreator rtrveCube(){
        return new CubeCreator(xMin,xMax,yMin,yMax,zMin,zMax);
    }
    public void drawSquare(int clrdCmpnnts[], int myWidth, int myHeight, int myColor) {
        int width = myWidth;
        int height = myHeight;
        color = myColor;
        //System.out.println("CmpnntRectangle: XMin = " + xMin + ", xMax = " + xMax + ", yMin = " + yMin + ", yMax = " + yMax + ", width = " + width + ", height = " + height + ", color = " + color);
        //LineDraw.drawLine(xMin, yMin, xMax, yMin, clrdCmpnnts, width, height, color);
        //LineDraw.drawLine(xMin, yMin, xMin, yMax, clrdCmpnnts, width, height, color);
        //LineDraw.drawLine(xMax, yMin, xMax, yMax, clrdCmpnnts, width, height, color);
        //LineDraw.drawLine(xMin, yMax, xMax, yMax, clrdCmpnnts, width, height, color);
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
    public double getXWidth(){
        return getXMax()-getXMin();
    }
    public double getYHeight(){
        return getYMax()-getYMin();
    }
    public double getZLength(){
        return getZMax()-getZMin();
    }
}