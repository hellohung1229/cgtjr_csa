/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import java.util.Arrays;

/**
 *
 * @author finitesystem
 */
public class HOGPosition {
    private HistogramOG hog[][];
    private int positionIndex = 0;
    private double x;
    private double y;
    private HOGPosition matchedHOGPosition;
    private int gridID;
    private Object trackBoundary;
    
    public HOGPosition(HistogramOG[][] hog, int positionIndex,int myWidth) {
        this.hog = hog;
        this.positionIndex = positionIndex;
        int aX = ImageTool.rtrvXPstn(positionIndex, myWidth);
        int aY = ImageTool.rtrvYPstn(positionIndex, myWidth);        
        setX(aX);
        setY(aY);
    }
    
    public HOGPosition(HistogramOG[][] hog, int positionIndex) {
        this.hog = hog;
        this.positionIndex = positionIndex;
    }
    public HOGPosition(double myX,double myY) {
        this.hog = new HistogramOG[1][1]; 
        this.hog[0][0] = new HistogramOG();         
        setX(myX);
        setY(myY);                
    }    

    public HOGPosition() {
        this.hog = new HistogramOG[1][1]; 
        this.hog[0][0] = new HistogramOG();           
    }    
    
    public Object getTrackBoundary() {
        return trackBoundary;
    }

    public void setTrackBoundary(Object myTrackBoundary) {
        this.trackBoundary = myTrackBoundary;
    }
    
    public int getGridID() {
        return gridID;
    }
    public void setGridID(int myGridID) {
        this.gridID = myGridID;
    }    
    public void connectHOGMatch(HOGPosition myHOGPosition){
        matchedHOGPosition = myHOGPosition;
    }
    public HOGPosition rtrveHOGMatch(){
        return matchedHOGPosition;
    }
    public double getX() {
        return x;
    }
    public void setX(double aX) {
        this.x = aX;
    }
    public double getY() {
        return y;
    }
    public void setY(double aY) {
        this.y = aY;
    }
    /*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.deepHashCode(this.hog);
        hash = 59 * hash + this.positionIndex;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HOGPositioin other = (HOGPositioin) obj;
        if (!Arrays.deepEquals(this.hog, other.hog)) {
            return false;
        }
        if (this.positionIndex != other.positionIndex) {
            return false;
        }
        return true;
    }*/
    public HistogramOG[][] getHog() {
        return hog;
    }
    public void setHog(HistogramOG[][] hog) {
        this.hog = hog;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }
    public static double computePixelDistance(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2,int myImageWidth) {
        int anImageWidth = myImageWidth;
        int anIndex1 = myHOGPosition1.getPositionIndex();
        int anIndex2 = myHOGPosition2.getPositionIndex();
        int x1 = ImageTool.rtrvXPstn(anIndex1, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex1, anImageWidth);
        int x2 = ImageTool.rtrvXPstn(anIndex2, anImageWidth);
        int y2 = ImageTool.rtrvYPstn(anIndex2, anImageWidth);
        double aDistance = PntTool.getDistance(x1, y1, x2, y2);
        return aDistance;
    }
    public void addHOG(HOGPosition myHOGPosition){
        HistogramOG aHistogramOG[][] = myHOGPosition.getHog();
        //if(hog == null || hog[0][0] == null) return;   
        hog[0][0].addHistogramOG(aHistogramOG[0][0]);
    }
    public void updateCentroid(HOGPosition myHOGPosition){
        HistogramOG aHistogramOG[][] = myHOGPosition.getHog();
        //if(hog == null || hog[0][0] == null) return;   
        hog[0][0].addHistogramOG(aHistogramOG[0][0]);
    }    
    @Override
    public String toString() {
        return "HOGPositioin{" + "hog=" + hog + ", positionIndex=" + positionIndex + '}';
    }    
}