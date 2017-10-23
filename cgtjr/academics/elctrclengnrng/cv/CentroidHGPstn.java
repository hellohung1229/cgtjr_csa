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
public class CentroidHGPstn {
    private CentroidHG centroidHG;
    private int positionIndex = 0;
    private double x;
    private double y;
    private CentroidHGPstn matchedHOGPosition;
    private int gridID;
    private Object trackBoundary;
    
    /*
    public CentroidHGPstn(CentroidHG centroidHG, int positionIndex,int myWidth) {
        this.centroidHG = centroidHG;
        this.positionIndex = positionIndex;
        int aX = ImageTool.rtrvXPstn(positionIndex, myWidth);
        int aY = ImageTool.rtrvYPstn(positionIndex, myWidth);        
        setX(aX);
        setY(aY);
    }*/
    public CentroidHGPstn(CentroidHG histogram, int myX,int myY) {
        this.centroidHG = histogram;
        int aX =  myX;
        int aY =  myY;        
        setX(aX);
        setY(aY);
    }    
    
    public CentroidHGPstn(CentroidHG histogram, int positionIndex) {
        this.centroidHG = histogram;
        this.positionIndex = positionIndex;
    }
    public CentroidHGPstn(double myX,double myY) {
        this.centroidHG = new CentroidHG(); 
        //this.centroidHG = new CentroidHG();         
        setX(myX);
        setY(myY);                
    }    

    public CentroidHGPstn() {
        this.centroidHG = new CentroidHG(); 
        //this.centroidHG[0][0] = new CentroidHG();           
    }    
    public CentroidHGPstn(double myX,double myY,int myImageWidth,int myImageHeight) {
        this.centroidHG = new CentroidHG(myImageWidth,myImageHeight);      
        setX(myX);
        setY(myY);                
    }    

    public CentroidHGPstn(int myImageWidth,int myImageHeight) {
        this.centroidHG = new CentroidHG(); 
        //this.centroidHG[0][0] = new CentroidHG();           
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
    public void connectHOGMatch(CentroidHGPstn myHOGPosition){
        matchedHOGPosition = myHOGPosition;
    }
    public CentroidHGPstn rtrveHOGMatch(){
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
    public CentroidHG getCentroidHG() {
        return centroidHG;
    }
    public void setCentroidHG(CentroidHG histogram) {
        this.centroidHG = histogram;
    }

    public int getPositionIndex() {
        return positionIndex;
    }
    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }
    public static double cmpteTrnsltnDstnce(CentroidHGPstn myHOGPosition1, CentroidHGPstn myHOGPosition2,int myImageWidth) {
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
    public void addCentroidHG(CentroidHGPstn myHOGPosition){
        CentroidHG aCentroidHG = myHOGPosition.getCentroidHG();
        //if(centroidHG == null || centroidHG[0][0] == null) return;   
        //histogram[0][0].addCentroidHG(aCentroidHG[0][0]);
    }
    public void updateCentroid(CentroidHGPstn myHOGPosition){
        CentroidHG aCentroidHG = myHOGPosition.getCentroidHG();
        //if(centroidHG == null || centroidHG[0][0] == null) return;   
        //histogram[0][0].addCentroidHG(aCentroidHG[0][0]);
    }    
    public void updateCentroid(CentroidHG myCentroidHG){
        CentroidHG aCentroidHG = myCentroidHG;
        //if(centroidHG == null || centroidHG[0][0] == null) return;   
        //histogram[0][0].addCentroidHG(aCentroidHG[0][0]);
    }
    public void updateCentroid(HOGPosition myHOGPosition){
        //int aX = (int)myHOGPosition.getX();
        //int aY = (int)myHOGPosition.getY();
        centroidHG.updateCentroid(myHOGPosition);
    }   
    /*
    public void updateCentroid(int myX,int myY){
        //centroidHG.updateCentroid(myX, myY);
    }*/       
    @Override
    public String toString() {
        return "HOGPositioin{" + "histogram=" + centroidHG + ", positionIndex=" + positionIndex + '}';
    }    
}