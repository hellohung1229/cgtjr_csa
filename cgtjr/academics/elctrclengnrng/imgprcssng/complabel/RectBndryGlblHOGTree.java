/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.cv.CentroidHG;
import cgtjr.academics.elctrclengnrng.cv.CentroidHGPstn;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.cv.HistogramOG;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import cgtjr.academics.math.geometry.linepnts.LineDraw;
import cgtjr.academics.math.lnralgbra.MathVector;
import cgtjr.academics.physics.Kinematics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author clayton g thomas jr
 */
public class RectBndryGlblHOGTree extends TreeSet {

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
    private HOGPosition cntrHOGPosition;
    private double hogDstnceThrshld = 80000000000.0;
    private CentroidHGPstn centroidPos;
    private int imageWidth;
    private int imageHeight;
    private double trnsltnPhase;
    private double trnsltnMgntde;
    private double chrctrstcScale;
    private double rotationAngle;
    private double rotationAngleSum;
    private double distanceSum;
    private double scaleSum;
    private Kinematics kinematicData;
    private int minWidth = 4;
    private int minHeight = 4;
    private int glblTrnsltnDstnceThrshld = 4000;
    
    
    public RectBndryGlblHOGTree() {
        super();
        cntrHOGPosition = new HOGPosition();  
        centroidPos = new CentroidHGPstn();
        
        mathVectorMap = new HashMap();

        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        kinematicData = new Kinematics();
    }    
    public RectBndryGlblHOGTree(HOGPosition myHOGPosition) {
        super(new YBndryComparator());
        mathVectorMap = new HashMap();
        cntrHOGPosition = new HOGPosition();        
        centroidPos = new CentroidHGPstn();
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        setXAvg((int) myHOGPosition.getX());//This may need updating
        setYAvg((int) myHOGPosition.getY());//This may need updating
        centroidPos.updateCentroid(myHOGPosition);
        
        //System.err.println("RectBndryHOGArrayList : possible error"); 
        HistogramOG ah[][] = myHOGPosition.getHog();
        System.out.println("RectBndryGlblHOGTree : gradient by index= "+ah[0][0].getGradientByIndex(0));
        System.out.println("RectBndryGlblHOGTree : gradient by angle = "+ah[0][0].getGradientByAngle(45));
        System.out.println("RectBndryGlblHOGTree : max gradient = "+ah[0][0].getMaxGradient());
        
        System.out.println("RectBndryGlblHOGTree: "+myHOGPosition.getHog());
        updtBndry(myHOGPosition);
    }
    public RectBndryGlblHOGTree(HOGPosition myHOGPosition,int myImageWidth,int myImageHeight) {
        super(new YBndryComparator());
        mathVectorMap = new HashMap();
        cntrHOGPosition = new HOGPosition();        
        centroidPos = new CentroidHGPstn(myImageWidth,myImageHeight);
        xMax = -Integer.MAX_VALUE;
        yMax = -Integer.MAX_VALUE;

        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;

        setXAvg((int) myHOGPosition.getX());//This may need updating
        setYAvg((int) myHOGPosition.getY());//This may need updating
        centroidPos.updateCentroid(myHOGPosition);
        
        //System.err.println("RectBndryHOGArrayList : possible error");        
        updtBndry(myHOGPosition);
    }   
    public void updateBoundaryRfrnce(HOGPosition myHOGPosition) {
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
        int aCenterX = (int) ((getXMax() + getXMin()) / 2);
        return aCenterX;
    }

    public int retrieveCenterY() {
        int aCenterY = (int) ((getYMax() + getYMin()) / 2);
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

    public void mergeBndry(RectBndryGlblHOGTree myRectBndryGlblHOGTree) {
    //TODO: this method could be replaced with direct modification of max & min boundary values
        Iterator anIterator = myRectBndryGlblHOGTree.iterator();
        while(anIterator.hasNext()){
            HOGPosition aHOGPosition = (HOGPosition) anIterator.next();
            updtBndry(aHOGPosition);
        }
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

        updateXAvg((int)Math.round(myHOGPosition.getX()));
        updateYAvg((int)Math.round(myHOGPosition.getY()));
        updateBoundaryRfrnce(myHOGPosition);
        insertTrackID(myHOGPosition);
        cntrHOGPosition.addHOG(myHOGPosition);
        cntrHOGPosition.setX((getXMax()+getXMin())/2);
        cntrHOGPosition.setY((getYMax()+getYMin())/2); 
        
        centroidPos.updateCentroid(myHOGPosition);        
     
        if(centroidPos.getTrackBoundary() == null){
           centroidPos.setTrackBoundary(this);
        }
        if(cntrHOGPosition.getTrackBoundary() == null){
           cntrHOGPosition.setTrackBoundary(this);
        }
        
        return super.add(myHOGPosition);
    }
    public void insertTrackID(HOGPosition myHOGPosition) {
        int x = (int)myHOGPosition.getX();
        int y = (int)myHOGPosition.getY();
        String trackID = "";

        MathVector aHOGMathVector = MathVector.createUnitVector(x, y, 0);
        HOGPosition aHOGPosition = myHOGPosition.rtrveHOGMatch();
        if (aHOGPosition != null) {
            RectBndryGlblHOGTree trackerBoundary = (RectBndryGlblHOGTree) aHOGPosition.getTrackBoundary();
            
            if(trackerBoundary != null){             
               trackID = "" + trackerBoundary.getBoundaryID();
            }else{
               trackID = "-1";
               //System.out.println("RectBndryHOGTree : trackerBoundary = null");                
            }
            if(mathVectorMap == null) 
            {
                System.err.println("RectBndryHOGTree : trackerBoundary = null");
            }
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
    public void updateTrackIDViaGlblHOG(TreeMap aTreeMap, int myImageWidth, int myImageHeight){
        
        if(aTreeMap == null){
            return;
        }
        CentroidHGPstn aHOGPosition1 = getCentroidHGPstn();
        CentroidHG aCentroidHG = aHOGPosition1.getCentroidHG();
        double aMaxDistance1 = aCentroidHG.rtrveCntrdFtreMaxDstnce();
        
        RectBndryGlblHOGTree aRectBndryHOGArrayTree = null;

        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        HOGPosition aGlblHOGPosition1 = getCntrHOGPosition();
        HOGPosition matchedHOGPosition = null;
        double ecldnDstnce = Double.MAX_VALUE;
        int matchedIndex = 0;

        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        //System.out.println("RectBndryGlblHOGTree: HOG1, "+getBoundaryID()+", "+aGlblHOGPosition1.getX()+", "+aGlblHOGPosition1.getY());
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            aRectBndryHOGArrayTree = (RectBndryGlblHOGTree) aTreeMap.get(aGroupKey);
            
            CentroidHGPstn aHOGPosition2 = aRectBndryHOGArrayTree.getCentroidHGPstn();
            CentroidHG aCentroidHG2 = aHOGPosition2.getCentroidHG();
            double aMaxDistance2 = aCentroidHG2.rtrveCntrdFtreMaxDstnce();
        
            HOGPosition aGlblHOGPosition2 = aRectBndryHOGArrayTree.getCntrHOGPosition();
            //Update code ... ?
            double hogEcldnDstnceTmp = HistogramOG.compareHog1x1BinDiff
                    (aGlblHOGPosition1.getHog(),aGlblHOGPosition2.getHog(),aMaxDistance1,aMaxDistance2);
            double aTrnsltnDistance = CentroidHG.cmpteTrnsltnDstnce(aCentroidHG, aCentroidHG2);
            //Note: hogDstnceThrshld should vary with scale
            //System.out.println("RectBndryGlblHOGTree: HOG2, "+aRectBndryHOGArrayTree.getBoundaryID()+", "+aGlblHOGPosition2.getX()+", "+aGlblHOGPosition2.getY());
            //System.out.println("RectBndryGlblHOGTree: test 1: ecldnDstnceTmp = "+hogEcldnDstnceTmp+", ecldnDstnce = "+ecldnDstnce+", hogDstnceThrshld = "+hogDstnceThrshld+". translation dist. = "+aTrnsltnDistance+", glblTrnsltnDstnceThrshld = "+glblTrnsltnDstnceThrshld);
            if (hogEcldnDstnceTmp <= hogDstnceThrshld && hogEcldnDstnceTmp <= ecldnDstnce && aTrnsltnDistance < glblTrnsltnDstnceThrshld) {
               //System.out.println("REctBndryGlblHOGTree: Possible match ..."+getBoundaryID()+" & "+aRectBndryHOGArrayTree.getBoundaryID());
                //System.out.println("RectBndryGlblHOGTree: aTrnsltnDistance = "+aTrnsltnDistance+", glblTrnsltnDstnceThrshld = "+glblTrnsltnDstnceThrshld);
                matchedHOGPosition = aRectBndryHOGArrayTree.getCntrHOGPosition();
                matchedIndex = matchedHOGPosition.getPositionIndex();
                ecldnDstnce = hogEcldnDstnceTmp;
                if (matchedHOGPosition != null) {
                   //System.out.println("REctBndryGlblHOGTree: Final match ..."+getBoundaryID()+" & "+aRectBndryHOGArrayTree.getBoundaryID() +", color "+aRectBndryHOGArrayTree.getColor());
                   aGlblHOGPosition1.connectHOGMatch(matchedHOGPosition);
                   setColor(aRectBndryHOGArrayTree.getColor());
                   //setColor(100);
                }                 
            }            
        }          
           
    }   
    public void updateTrackIDViaCHG(TreeMap aTreeMap){
        
        if(aTreeMap == null){
            return;
        }
        
        RectBndryGlblHOGTree aRectBndryHOGArrayTree = null;

        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        CentroidHGPstn aCntrdHGPstn1 = getCentroidHGPstn();
        CentroidHGPstn matchedCntrdPstn = null;
        //CentroidHGPstn aCentroidPosition1 = getCentroidHGPstn();
        //CentroidHGPstn matchedCentroidHGPstn = null;
        
        double ecldnDstnce = Double.MAX_VALUE;
        int matchedIndex = 0;

        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        double ecldnDstnceTmp = 0;
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            aRectBndryHOGArrayTree = (RectBndryGlblHOGTree) aTreeMap.get(aGroupKey);
            //CentroidHGPstn aCentroidHGPstn2 = aRectBndryHOGArrayTree.getCentroidHGPstn();
            CentroidHGPstn aCentroidPosition2 = aRectBndryHOGArrayTree.getCentroidHGPstn();
            //ecldnDstnceTmp = CentroidHG.compareHog1x1BinDiff
            ecldnDstnceTmp = CentroidHG.computeBinDiffViaCompare2
                    (aCntrdHGPstn1.getCentroidHG(),aCentroidPosition2.getCentroidHG());
            //System.out.println("RectBndryGlblHOGTree: test 1: ecldnDstnceTmp = "+ecldnDstnceTmp+", ecldnDstnce = "+ecldnDstnce+", hogDstnceThrshld = "+hogDstnceThrshld);
            if (ecldnDstnceTmp <= hogDstnceThrshld && ecldnDstnceTmp <= ecldnDstnce) {
                matchedCntrdPstn = aCentroidPosition2;
                matchedIndex = aCentroidPosition2.getPositionIndex();
                ecldnDstnce = ecldnDstnceTmp;
            }            
        }          
        if (matchedCntrdPstn != null) {
            //System.out.println("REctBndryGlblHOGTree: Found match ...");
            aCntrdHGPstn1.connectHOGMatch(matchedCntrdPstn);
            RectBndryGlblHOGTree aRectBndryGlblHOGTree = (RectBndryGlblHOGTree)matchedCntrdPstn.getTrackBoundary();
            setRotationAngleSum(aRectBndryGlblHOGTree.getRotationAngleSum());
            setDistanceSum(aRectBndryGlblHOGTree.getDistanceSum());
            setScaleSum(aRectBndryGlblHOGTree.getScaleSum());            
        }  else{
            //System.err.println("RectBndryGlblHOGTree: no match!!! ecldnDstnceTmp = "+ecldnDstnceTmp+", ecldnDstnce = "+ecldnDstnce+", hogDstnceThrshld = "+hogDstnceThrshld);            
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
                RectBndryGlblHOGTree aRectBndryHOGArrayList = (RectBndryGlblHOGTree) aHOGMatch.getTrackBoundary();
                boundaryID = aRectBndryHOGArrayList.getBoundaryID();
            }
        }
    }
    public void drawSquare(int clrdCmpnnts[], int myWidth, int myHeight, int myColor) {
        int width = myWidth;
        int height = myHeight;
        //color = myColor;
        LineDraw.drawLine(xMin, yMin, xMax, yMin, clrdCmpnnts, width, height, myColor);
        LineDraw.drawLine(xMin, yMin, xMin, yMax, clrdCmpnnts, width, height, myColor);
        LineDraw.drawLine(xMax, yMin, xMax, yMax, clrdCmpnnts, width, height, myColor);
        LineDraw.drawLine(xMin, yMax, xMax, yMax, clrdCmpnnts, width, height, myColor);
    }
    public void updataCentroidHG(){
        CentroidHG aCentroidHG = centroidPos.getCentroidHG();
        int pnt[] = aCentroidHG.getCentroid();
        centroidPos.setX(pnt[0]);
        centroidPos.setY(pnt[1]);        
        aCentroidHG.updateCentroidHG();   
        aCentroidHG.updateHOG();
    }
    public void drawCentroidPnt(int clrdCmpnnts[], int myWidth, int myColor) {
        int width = myWidth;
        int color = myColor;
        CentroidHG aCentroidHG = centroidPos.getCentroidHG();
        //aCentroidHG.updateCentroidHG();//This code should be modified to check if already updated
        //aCentroidHG.updateHOG();
        
        int aCentroid[] = aCentroidHG.getCentroid();
        int aX = aCentroid[0];
        int aY = aCentroid[1];
        
        int anIndex = ImageTool.rtrvIndex(aX, aY, myWidth);
        //System.out.println("RectBndryGlblHOGTree.drawCentroid(): x = "+ aX+", y = "+aY);    

        clrdCmpnnts[anIndex+myWidth+1] = 0x00ff0000;
        clrdCmpnnts[anIndex+myWidth] = 0x00ff0000;           
        clrdCmpnnts[anIndex+myWidth-1] = 0x00ff0000;      
        clrdCmpnnts[anIndex-1] = 0x00ff0000;       
        clrdCmpnnts[anIndex] = 0x00ff00a00;        
        clrdCmpnnts[anIndex+1] = 0x00ff0000;           
        clrdCmpnnts[anIndex-myWidth+1] = 0x00ff0000;
        clrdCmpnnts[anIndex-myWidth] = 0x00ff0000;           
        clrdCmpnnts[anIndex-myWidth-1] = 0x00ff0000;        
    }    
    public void drawGlblHOGTrnsltn(int myPixelData[], int myImageWidth,int myImageHeight,int myColor) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;
        //int anIndex1 = myHOGPosition1.getPositionIndex();
        //int anIndex2 = myHOGPosition2.getPositionIndex();
        HOGPosition aHOGPosition1 = getCntrHOGPosition();
        HOGPosition aHOGPosition2 = aHOGPosition1.rtrveHOGMatch();
        
        if( aHOGPosition2 == null){
            return;
        }
        
        int x1 = (int)aHOGPosition1.getX();
        int y1 = (int)aHOGPosition1.getY();
        int x2 = (int)aHOGPosition2.getX();
        int y2 = (int)aHOGPosition2.getY();

        //trnsltnMgntde = PntTool.getDistance(x1, y1, x1+x1-x2,y1+y1-y2);
        trnsltnMgntde = PntTool.getDistance(x1, y1, x2,y2);
        trnsltnPhase = Math.atan2(y1-y2,x1-x2);
        distanceSum +=  trnsltnMgntde;
        
        int aPixelData[] = myPixelData;
        //System.out.println("RectBndryGlblHOGTree draw : x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        ArrowCrtr.drawArrow(x1, y1, x1+(int)(0.5*(x1-x2)), y1+(int)(0.5*(y1-y2)), aPixelData, anImageWidth, anImageHeight, myColor,myColor);
    }
    public void drawCntrdTrnsltn(int myPixelData[], int myImageWidth,int myImageHeight,int myColor) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;
        //int anIndex1 = myHOGPosition1.getPositionIndex();
        //int anIndex2 = myHOGPosition2.getPositionIndex();
        CentroidHGPstn aCentroidPosition1 = getCentroidHGPstn();
        CentroidHGPstn aCentroidPosition2 = aCentroidPosition1.rtrveHOGMatch();
        
        if(aCentroidPosition1 == null || aCentroidPosition2 == null){
            //System.out.println("RectBndryGlblHOGTree: null found");
            return;
        }
        
        int x1 = (int)aCentroidPosition1.getX();
        int y1 = (int)aCentroidPosition1.getY();
        int x2 = (int)aCentroidPosition2.getX();
        int y2 = (int)aCentroidPosition2.getY();

        trnsltnMgntde = PntTool.getDistance(x1, y1, x1+x1-x2,y1+y1-y2);
        trnsltnPhase = Math.atan2(y1-y2,x1-x2);
        distanceSum += trnsltnMgntde;
        int aPixelData[] = myPixelData;
        //System.out.println("RectBndryGlblHOGTree draw : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2+", x1-x2 = "+(x1-x2)+", y1-y2 = "+(y1-y2));
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        //ArrowCrtr.drawArrow(x1, y1, x1+x1-x2, y1+y1-y2, aPixelData, anImageWidth, anImageHeight, myColor,myColor);
    }
    public void drawGlblHOGAngle(int myPixelData[], int myImageWidth,int myImageHeight) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;

        HOGPosition aHOGPosition1 = getCntrHOGPosition();
        
        if(aHOGPosition1 == null){
            return;
        }
        
        int x1 = (int)aHOGPosition1.getX();
        int y1 = (int)aHOGPosition1.getY();
        HistogramOG aHOG[][] = aHOGPosition1.getHog();
        double anAngle = aHOG[0][0].getAngleWghtAvg();
        double aGradient = aHOG[0][0].getGradientAvg();
        int x2 = (int)(50*Math.cos(anAngle));
        int y2 = -(int)(50*Math.sin(anAngle));        

        int aPixelData[] = myPixelData;
        System.out.println("HOGCornerDetect draw angle : aGradient = "+aGradient+", anAngle = "+anAngle);        
        System.out.println("HOGCornerDetect draw angle : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        ArrowCrtr.drawArrow(x1, y1, x1+x2, y1+y2, aPixelData, anImageWidth, anImageHeight, 0x00ff00ff,0x00ff00ff);
    }
    public void drawGlblHOGOrnttn(int myPixelData[], int myImageWidth,int myImageHeight) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;

        HOGPosition aHOGPosition1 = getCntrHOGPosition();
        CentroidHGPstn aCntrdHGPstn1 = getCentroidHGPstn();        
        
        if(aHOGPosition1 == null){
            return;
        }
        CentroidHG aCentroidHG = aCntrdHGPstn1.getCentroidHG();        
        double glblHOG[] = aCentroidHG.computeOrientation();
        int x1 = (int)aHOGPosition1.getX();
        int y1 = (int)aHOGPosition1.getY();
        //HistogramOG aHOG[][] = aHOGPosition1.getHog();
        //double glblHOG[] = aHOG[0][0].computeOrientation();
        
        int x2 = (int)glblHOG[0];
        int y2 = -(int)glblHOG[1];        

        int aPixelData[] = myPixelData;
             
        //System.out.println("RectBndryGlblHOGTree draw angle : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        ArrowCrtr.drawLine(x1, y1, x1+x2, y1+y2, aPixelData, anImageWidth, anImageHeight, 0x00ff00ff,0x00ff00ff);
    }    
    public void drawMaxGrdntAngle(int myPixelData[], int myImageWidth,int myImageHeight,int myColor) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;

        HOGPosition aHOGPosition1 = getCntrHOGPosition();
        
        if(aHOGPosition1 == null){
            return;
        }
        
        int x1 = (int)aHOGPosition1.getX();
        int y1 = (int)aHOGPosition1.getY();
        
        HistogramOG aHOG[][] = aHOGPosition1.getHog();
        
        double aMaxGradient = aHOG[0][0].getMaxGradient();        
        double aMaxGradientAngle = aHOG[0][0].getMaxGradientAngle();      
        int aMaxGradientAngleIndex = aHOG[0][0].getMaxGradientBinIndex();
        double anAngle = aHOG[0][0].getAngleByIndex(aMaxGradientAngleIndex);
        
        //System.out.println("RectBndryGlbHOGTree: gradient index = "+aMaxGradientAngleIndex+",index anAngle = "+anAngle+", max gradient angle index = "+aMaxGradientAngleIndex+", max angle =  "+aMaxGradientAngle);
        
        rotationAngle = aMaxGradientAngle;
        int aMaxGradientX1 = (int)(aMaxGradient*Math.cos(aMaxGradientAngle));
        int aMaxGradientY1 = -(int)(aMaxGradient*Math.sin(aMaxGradientAngle));
                
        int x2 = (int)aMaxGradientX1;
        int y2 = -(int)aMaxGradientY1;        

        int aPixelData[] = myPixelData;
             
        //System.out.println("RectBndryGlblHOGTree draw angle : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        ArrowCrtr.drawArrow(x1, y1, x1+x2, y1+y2, aPixelData, anImageWidth, anImageHeight, myColor,myColor);
    }     
    public void drawMaxCntrdHOG(int myPixelData[], int myImageWidth,int myImageHeight) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;

        CentroidHGPstn aHOGPosition1 = getCentroidHGPstn();
        CentroidHG aCentroidHG = aHOGPosition1.getCentroidHG();
        
        if(aHOGPosition1 == null || aCentroidHG == null){
            return;
        }
        
        int x1 = (int)retrieveCenterX();
        int y1 = (int)retrieveCenterY();
        
        double aGrdntHistogram[] = aCentroidHG.getGrdntHistogram();
        
        double aMaxGradient = aCentroidHG.getMaxGradient();        
        double aMaxGradientAngle = aCentroidHG.getMaxGradientAngle();        
        int aMaxGradientX1 = (int)(aMaxGradient*Math.cos(aMaxGradientAngle));
        int aMaxGradientY1 = -(int)(aMaxGradient*Math.sin(aMaxGradientAngle));
                
        int x2 = (int)aMaxGradientX1;
        int y2 = -(int)aMaxGradientY1;        

        int aPixelData[] = myPixelData;
             
        //System.out.println("RectBndryGlblHOGTree draw angle : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        ArrowCrtr.drawArrow(x1, y1, x1+x2, y1+y2, aPixelData, anImageWidth, anImageHeight, 0x0000ffff,0x0000ffff);
    }  
    public double cmpteChrctrstcScale(int myImageWidth, int myImageHeight) {
        int anImageWidth = myImageWidth;
        int anImageHeight = myImageHeight;
        //int aScale = 1000 * size() / (anImageWidth*anImageHeight);
        CentroidHGPstn aHOGPosition1 = getCentroidHGPstn();
        CentroidHG aCentroidHG = aHOGPosition1.getCentroidHG();
        
        if(aHOGPosition1 == null || aCentroidHG == null){
            return 0;
        }                
        chrctrstcScale = aCentroidHG.computeChrctrstcScale(myImageWidth,myImageHeight);
        return chrctrstcScale;    
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
    public HOGPosition getCntrHOGPosition() {
        return cntrHOGPosition;
    }
    public void setCntrHOGPosition(HOGPosition myCntrHOGPosition) {
        this.cntrHOGPosition = myCntrHOGPosition;
    }        
    public CentroidHGPstn getCentroidHGPstn(){
        return centroidPos;
    }
    public double getTrnsltnPhase() {
        return trnsltnPhase;
    }
    public void setTrnsltnPhase(double trnsltnPhase) {
        this.trnsltnPhase = trnsltnPhase;
    }

    public double getTrnsltnMgntde() {
        return trnsltnMgntde;
    }

    public void setTrnsltnMgntde(double trnsltnMgntde) {
        this.trnsltnMgntde = trnsltnMgntde;
    }

    public double getChrctrstcScale() {
        return chrctrstcScale;
    }

    public void setChrctrstcScale(double chrctrstcScale) {
        this.chrctrstcScale = chrctrstcScale;
    }

    public double getRotationAngle() {
        CentroidHGPstn aCentroidPStn = getCentroidHGPstn();
        CentroidHG aCentroidHG = aCentroidPStn.getCentroidHG();
        int anIndex = aCentroidHG.rtrveGrdntIndexViaDstnce();
        rotationAngle = aCentroidHG.getAngleByIndex(anIndex);
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public double getRotationAngleSum() {
        return rotationAngleSum;
    }

    public void setRotationAngleSum(double myRotationAngleSum) {
        this.rotationAngleSum = myRotationAngleSum;
    }

    public double getDistanceSum() {
        return distanceSum;
    }

    public void setDistanceSum(double myDistanceSum) {
        this.distanceSum = myDistanceSum;
    }

    public double getScaleSum() {
        return scaleSum;
    }

    public void setScaleSum(double myScaleSum) {
        this.scaleSum = myScaleSum;
    }
    public double computeRotationSum() {

        //int anIndex1 = myHOGPosition1.getPositionIndex();
        //int anIndex2 = myHOGPosition2.getPositionIndex();
        HOGPosition aHOGPosition1 = getCntrHOGPosition();
        HOGPosition aHOGPosition2 = aHOGPosition1.rtrveHOGMatch();
        
        if(aHOGPosition1 == null || aHOGPosition2 == null){
            //System.err.println("RectBndryGlblHOGTree null detected: HOGPosition1 = "+aHOGPosition1+", HOGPosition2 = " +aHOGPosition2);
            return rotationAngleSum;
        }
        
        RectBndryGlblHOGTree aRectBndryGlblHOGTree1 = (RectBndryGlblHOGTree)aHOGPosition1.getTrackBoundary();
        RectBndryGlblHOGTree aRectBndryGlblHOGTree2 = (RectBndryGlblHOGTree)aHOGPosition2.getTrackBoundary();

        double anAngle1 =  aRectBndryGlblHOGTree1.getRotationAngle();
        double anAngle2 =  aRectBndryGlblHOGTree2.getRotationAngle();
        //System.out.println("RectBndryGlblHOGTree : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2);
        rotationAngleSum += Math.abs(anAngle1 - anAngle2);
        double aRotation[] = {0,0,rotationAngleSum};
        //kinematicData.setRotation(aRotation);                
        return rotationAngleSum;
    }
    public double computeRotationSum2() {

        //int anIndex1 = myHOGPosition1.getPositionIndex();
        //int anIndex2 = myHOGPosition2.getPositionIndex();
        CentroidHGPstn aCntrdHGPstn1 = getCentroidHGPstn();
        CentroidHGPstn aCntrdHGPstn2 = aCntrdHGPstn1.rtrveHOGMatch();
        
        if(aCntrdHGPstn1 == null || aCntrdHGPstn2 == null){
            //System.err.println("RectBndryGlblHOGTree null detected: CntrdHGPstn1 = "+aCntrdHGPstn1+", CntrdHGPstn2 = " +aCntrdHGPstn2);
            return rotationAngleSum;
        }        
        
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree1 = (RectBndryGlblHOGTree)aCntrdHGPstn1.getTrackBoundary();
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree2 = (RectBndryGlblHOGTree)aCntrdHGPstn2.getTrackBoundary();
              
        double glblHOG1[] = aCntrdHGPstn1.getCentroidHG().computeOrientation2();
        double glblHOG2[] = aCntrdHGPstn2.getCentroidHG().computeOrientation2();
        
        double anAngle1 =  Math.atan2(glblHOG1[1],glblHOG1[0]);
        double anAngle2 =  Math.atan2(glblHOG2[1],glblHOG2[0]);
        
        //System.out.println("RectBndryGlblHOGTree : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2);
        rotationAngleSum += Math.abs(anAngle1 - anAngle2);
        double aRotation[] = {0,0,rotationAngleSum};
        //kinematicData.setRotation(aRotation);                
        return rotationAngleSum;
    }
    public double computeDistanceSum() {

        CentroidHGPstn aCntrdHGPstn1 = getCentroidHGPstn();
        CentroidHGPstn aCntrdHGPstn2 = aCntrdHGPstn1.rtrveHOGMatch();
        
        if(aCntrdHGPstn1 == null || aCntrdHGPstn2 == null){
            System.err.println("RectBndryGlblHOGTree null detected: CntrdHGPstn1 = "+aCntrdHGPstn1+", CntrdHGPstn2 = " +aCntrdHGPstn2);
            return rotationAngleSum;
        }
        
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree1 = (RectBndryGlblHOGTree)aCntrdHGPstn1.getTrackBoundary();
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree2 = (RectBndryGlblHOGTree)aCntrdHGPstn2.getTrackBoundary();
              
        double glblHOG1[] = aCntrdHGPstn1.getCentroidHG().computeOrientation();
        double glblHOG2[] = aCntrdHGPstn2.getCentroidHG().computeOrientation();
        
        double anAngle1 =  Math.atan2(glblHOG1[1],glblHOG1[0]);
        double anAngle2 =  Math.atan2(glblHOG2[1],glblHOG2[0]);
        
        //System.out.println("RectBndryGlblHOGTree : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2);
        rotationAngleSum += Math.abs(anAngle1 - anAngle2);
        double aRotation[] = {0,0,rotationAngleSum};
        //kinematicData.setRotation(aRotation);                
        return rotationAngleSum;
    }    
    public double computeScaleSum(int myImageWidth,int myImageHeight) {

        //int anIndex1 = myHOGPosition1.getPositionIndex();
        //int anIndex2 = myHOGPosition2.getPositionIndex();
        CentroidHGPstn aCntrdHGPstn1 = getCentroidHGPstn();
        CentroidHGPstn aCntrdHGPstn2 = aCntrdHGPstn1.rtrveHOGMatch();
        
        if(aCntrdHGPstn1 == null || aCntrdHGPstn2 == null){
            //System.err.println("RectBndryGlblHOGTree null detected: CntrdHGPstn1 = "+aCntrdHGPstn1+", CntrdHGPstn2 = " +aCntrdHGPstn2);
            return scaleSum;
        }
        
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree1 = (RectBndryGlblHOGTree)aCntrdHGPstn1.getTrackBoundary();
        //RectBndryGlblHOGTree aRectBndryGlblHOGTree2 = (RectBndryGlblHOGTree)aCntrdHGPstn2.getTrackBoundary();
              
        double aScale1 = aCntrdHGPstn1.getCentroidHG().computeChrctrstcScale(myImageWidth,myImageHeight);
        double aScale2 = aCntrdHGPstn2.getCentroidHG().computeChrctrstcScale(myImageWidth,myImageHeight);
        
        //System.out.println("RectBndryGlblHOGTree : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2);
        scaleSum += (aScale1 - aScale2);
       
        return scaleSum;
    }    

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }    
    public boolean bndrySizeCheck(int myWidth, int myHeight) {
        int aWidth = getXMax() - getXMin();
        int aHeight = getYMax() - getYMin();
        if (aWidth >= myWidth && aHeight >= myHeight) {
            return true;
        } else {
            return false;
        }
    }    
}