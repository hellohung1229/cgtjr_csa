/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.math.geometry.linepnts.LineDraw;
import cgtjr.academics.math.lnralgbra.MathVector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author clayton g thomas jr
 */
public class RectBndryHOGTree extends TreeSet {

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
    private int boundaryID;
    private int maxTrackID = -1;
    private float maxMagnitude;
    private HashMap mathVectorMap;


    public RectBndryHOGTree() {
        super();
        mathVectorMap = new HashMap();
               
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

    }    
    public RectBndryHOGTree(HOGPosition myHOGPosition) {
        super(new YBndryComparator());
        mathVectorMap = new HashMap();
        
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        setXAvg((int) myHOGPosition.getX());
        setYAvg((int) myHOGPosition.getY());
        
        //System.err.println("RectBndryHOGArrayList : possible error");        
        updtBndry(myHOGPosition);
    }
   
    public void updateBoxRfrnce(HOGPosition myHOGPosition) {
        myHOGPosition.setTrackBoundary(this);
    }

    public void updateHOGGridID(HOGPosition myHOGPosition) {
        myHOGPosition.setGridID(boundaryID);
    }

    public int getBoundaryID() {
        return boundaryID;
    }

    public void setBoundaryID(int myBoundaryID) {
        this.boundaryID = myBoundaryID;
    }

    public void updateXSum(int myXSum) {
        xSum += myXSum;
    }

    public void updateYSum(int myYSum) {
        ySum += myYSum;
    }

    public void updateZSum(int myZSum) {
        zSum += myZSum;
    }

    public void updateXAvg(int myX) {
        xSum = xAvg + myX;
        xAvg = xSum / 2;
    }

    public void updateYAvg(int myY) {
        ySum = yAvg + myY;
        yAvg = ySum / 2;
    }

    public void updateZAvg(int myZ) {
        zSum = zAvg + myZ;
        zAvg = zSum / 2;
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

    public int getXWidth(){
        return getXMax()-getXMin();
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
    public int getYHeight(){
        return getYMax()-getYMin();
    }
    public int retrieveCenterX() {
        int aCenterX = (int) ((getXMax() - getXMin()) / 2);
        return aCenterX;
    }

    public int retrieveCenterY() {
        int aCenterY = (int) ((getYMax() - getYMin()) / 2);
        return aCenterY;
    }

    public void setColor(int myColor) {
        color = myColor;
    }

    public int getColor() {
        return color;
    }

    public boolean add(Object myObject) {
        HOGPosition aHOGPosition = (HOGPosition) myObject;
        return updtBndry(aHOGPosition);
        //return super.add(myObject);
    }

    public boolean updtBndry(HOGPosition myHOGPosition) {
        //super.add(myHOGPosition);

        int xPos = (int)myHOGPosition.getX();
        int yPos = (int)myHOGPosition.getY();
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

        updateXAvg((int) myHOGPosition.getX());
        updateYAvg((int) myHOGPosition.getY());
        updateBoxRfrnce(myHOGPosition);
        insertTrackID(myHOGPosition);
        return super.add(myHOGPosition);
    }
    public void updateCenterHOG(HOGPosition myHOGPosition){
        
    }
    public void insertTrackID(HOGPosition myHOGPosition) {
        int x = (int)myHOGPosition.getX();
        int y = (int)myHOGPosition.getY();
        String trackID = "";

        MathVector aHOGMathVector = MathVector.createUnitVector(x, y, 0);
        HOGPosition aHOGPosition = myHOGPosition.rtrveHOGMatch();
        if (aHOGPosition != null) {
            RectBndryHOGTree trackerBoundary = (RectBndryHOGTree) aHOGPosition.getTrackBoundary();
            
            if(trackerBoundary != null){             
               trackID = "" + trackerBoundary.getBoundaryID();
            }else{
               trackID = "-1";
               //System.out.println("RectBndryHOGTree : trackerBoundary = null");                
            }
            if(mathVectorMap == null) System.out.println("RectBndryHOGTree : trackerBoundary = null");
            MathVector aMathVector = (MathVector) mathVectorMap.get(trackID);

            if (aMathVector != null) {
                aMathVector.add(aHOGMathVector);
            } else {
                mathVectorMap.put(trackID, aHOGMathVector);
            }
        }
    }
    public void updateTrackID(){
        Set aKeySet = mathVectorMap.keySet();
        Iterator anIterator = aKeySet.iterator();
        float magnitude = 0;
        while (anIterator.hasNext()) {
            String aTrackID = (String)anIterator.next();
            MathVector aMathVector = (MathVector)mathVectorMap.get(aTrackID);
            magnitude = aMathVector.getMagnitude();
            
            if(magnitude >= maxMagnitude){
                maxMagnitude = magnitude;
                maxTrackID = Integer.parseInt(aTrackID);
            }            
  
        }    
        if(maxTrackID != -1){
            boundaryID = maxTrackID;
            maxTrackID = -1;
        }        
    }
    public void updateTrackInfo() {
        HOGPosition aHOGPosition = null;
        Iterator anIterator2 = iterator();
        while (anIterator2.hasNext()) {
            aHOGPosition = (HOGPosition) anIterator2.next();
            System.out.println("feature location : " + aHOGPosition.getX() + "," + aHOGPosition.getY());
            HOGPosition aHOGMatch = aHOGPosition.rtrveHOGMatch();
            if (aHOGMatch != null) {
                RectBndryHOGTree aRectBndryHOGArrayList = (RectBndryHOGTree) aHOGMatch.getTrackBoundary();
                boundaryID = aRectBndryHOGArrayList.getBoundaryID();
            }
        }
    }

    public void drawSquare(int clrdCmpnnts[], int myWidth, int myHeight, int myColor) {
        int width = myWidth;
        int height = myHeight;

        color = myColor;

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