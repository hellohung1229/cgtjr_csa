/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

/**
 *
 * @author finitesystem
 */

import cgtjr.academics.math.algebra.Gaussian2D;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import java.lang.Math;
import java.util.ArrayList;

public class CentroidHG {
    //This code is in need of major revisions (copied from HistogramOG)
    private int centroid[];
    private double[] cntrdHistogram;
    private double[] grdntHistogram;    
    private double[] cntrdDstnces;
    private int numberOfBins;
    private double binSize;
    private double maxBinAngle;
    private double maxRealAngle;
    private double maxGradient;
    private int maxGradientBinIndex;
    private double angleWghtedSum;
    private double gradientSum;
    private double angleWghtdAvg;
    private int totalCount;
    private double gradientAvg;
    private double glblVctrX;
    private double glblVctrY;    
    private int avgX;
    private int avgY;
    private int sumX;
    private int sumY;
    private ArrayList hogPosArrayList; 
    private int imageWidth;
    private int imageHeight;
    private int totalGradient;
    private int maxDstnceBinIndex;
    private static double rotationAngleSum;
    
   // private int featurePnt[];
    
    /**
     * @param nbins -- number of bins (one for each orientation)
     */
    public CentroidHG(int nbins) {
        numberOfBins = nbins;
        cntrdHistogram = new double[nbins];
        grdntHistogram = new double[nbins];
        cntrdDstnces = new double[nbins];
        
        this.binSize = 2.0*Math.PI/nbins;
        centroid = new int[2];
        hogPosArrayList = new ArrayList();
    }
    public CentroidHG(int myImageWidth,int myImageHeight) {
        this(8);
        centroid = new int[2];
        hogPosArrayList = new ArrayList();        
        imageWidth = myImageWidth;
        imageHeight = myImageHeight;
    }
    // default with 8 bins
    public CentroidHG() {
        this(8);
        centroid = new int[2];
        hogPosArrayList = new ArrayList();        
    }
    public static CentroidHG[][] retrieveHOGGrid(int myXBlocks,int myYBlocks){
        CentroidHG HOGGrid[][] = new CentroidHG[myXBlocks][myYBlocks];
        return HOGGrid;
    }
    private void cntrdHGInsert(double phi, double gradient) {
       //cntrdHGInsert(phi, gradient,1.0/127.0);
       cntrdHGInsert(phi, gradient,1.0/128.0);        
    }
    private void cntrdHGInsert(double phi, double gradient,double myScale) {
        //System.out.println("HistogramOG.insert(): insert local hog .............................................");        
        double aPhiAngle = phi+binSize/2.0;
        
        //Note: this may conflict with temporal gradient threshold!!!!!
        //gradient = (gradient <= 20) ?  0: gradient ;
        
        if(gradient == 0){
        }

        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI + aPhiAngle;
        }        
        
        int binIndex = (int)((aPhiAngle)/binSize);        
        //histogram[binIndex] += gradient;
        double scaledGradient = gradient*myScale;
        cntrdHistogram[binIndex] += scaledGradient;    
        totalGradient+=scaledGradient;
        //histogram[binIndex] += 1;//scaledGradient;        
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", cntrdHistogram["+binIndex+"] = "+cntrdHistogram[binIndex]);
        //updateMaxGradientAngle(cntrdHistogram[binIndex],binIndex,aPhiAngle);
        //updteAngleViaWghtdAvg(aPhiAngle,scaledGradient);
    }
    private void hogInsert(double phi, double gradient,double myScale) {
        //System.out.println("HistogramOG.insert(): insert local hog .............................................");        
        double aPhiAngle = phi+binSize/2.0;
        
        //Note: this may conflict with temporal gradient threshold!!!!!
        //gradient = (gradient <= 20) ?  0: gradient ;
        
        if(gradient == 0){
            //return;
        }

        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI + aPhiAngle;
        }        
       
        int binIndex = (int)((aPhiAngle)/binSize);        
        //histogram[binIndex] += gradient;
        double scaledGradient = gradient*myScale;
        grdntHistogram[binIndex] += scaledGradient;     
        totalGradient+=scaledGradient;        
        //histogram[binIndex] += 1;//scaledGradient;
        
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", cntrdHistogram["+binIndex+"] = "+cntrdHistogram[binIndex]);

        updateMaxGradientAngle(grdntHistogram[binIndex],binIndex,aPhiAngle);
        updteAngleViaWghtdAvg(aPhiAngle,scaledGradient);
    }    
    /*
    private void insertByAngle(double phi, double gradient) {
        System.out.println("HistogramOG.insertByAngle(): insert global hog .............................................");
        double aPhiAngle = phi;//+binSize/2;        
               //System.out.println("HistogramOG: phi = "+phi);
        if(gradient == 0){
            //return;
        }        

        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI + aPhiAngle;
        }        
        int binIndex;
        binIndex = (int)((aPhiAngle)/binSize);        
        //histogram[binIndex] += gradient;
        double scaledGradient = gradient;
        cntrdHistogram[binIndex] += scaledGradient;
        
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", cntrdHistogram["+binIndex+"] = "+cntrdHistogram[binIndex]);

        updateMaxGradientAngle(cntrdHistogram[binIndex],binIndex,aPhiAngle);  
        updteAngleViaWghtdAvg(aPhiAngle,scaledGradient);
    }*/
   
    private void updteAngleViaWghtdAvg(double myPhiAngle,double myGradient){
        //totalCount++;        
        angleWghtedSum += myPhiAngle*myGradient;
        gradientSum += myGradient;  
        angleWghtdAvg = angleWghtedSum/gradientSum;
        //angleWghtdAvg = angleWghtedSum/totalCount;
        gradientAvg = gradientSum/totalCount;
        //System.out.println("HistogramOG.updteAngleViaWghtdAvg(): myPhiAngle = "+ myPhiAngle+", angleSum = "+angleWghtedSum+", gradientSum = "+gradientSum+", angleWghtAvg = "+angleWghtdAvg+", gradientAvg = "+gradientAvg+", totalCount = "+totalCount);
    }
    public void updateCentroid(HOGPosition myHOGPosition){
        int aX = (int)myHOGPosition.getX();
        int aY = (int)myHOGPosition.getY();

        totalCount++; 
        hogPosArrayList.add(myHOGPosition);        
        sumX +=  aX; 
        sumY +=  aY;
        avgX = sumX/totalCount;
        avgY = sumY/totalCount;
        centroid[0] = avgX;
        centroid[1] = avgY;
    }
    public void updateCentroidHG(){    
        int aSize = hogPosArrayList.size();
        for(int i=0;i<aSize;i++){
            HOGPosition aHOGPosition = (HOGPosition)hogPosArrayList.get(i);
            int aX = (int)aHOGPosition.getX();
            int aY = (int)aHOGPosition.getY();
            double anAngle = computeAngle(aX,aY);
            double aMag = computeMag(aX,aY);
            
            cntrdHGInsert(anAngle, aMag);
            updteMaxCntrdDstnce(anAngle,aMag);
        }
    }
    public void updateHOG(){    
        int centroidX = centroid[0];
        int centroidY = centroid[1];
        double sigma = 80;
        Gaussian2D aGaussian2D = new Gaussian2D(centroidX,centroidY,sigma);
        int aSize = hogPosArrayList.size();
        for(int i=0;i<aSize;i++){
            HOGPosition aHOGPosition = (HOGPosition)hogPosArrayList.get(i);
            HistogramOG aHistogramOG[][] = (HistogramOG[][])aHOGPosition.getHog();
            int aX = (int)aHOGPosition.getX();
            int aY = (int)aHOGPosition.getY();
            double aScale = aGaussian2D.computeGssn(aX,aY);
            //System.out.println("CentroidHG: histogram value = "+aHistogramOG[0][0]);
            //aClusterGlblTrackFltr : binddHistogramOG(aHistogramOG[0][0],aScale);
            addHistogramOG(aHistogramOG[0][0],1);
        }
    }
    public double computeAngle(int myX,int myY){        
        int aY = myY-centroid[1];
        int aX = myX-centroid[0];        
        double anAngle = Math.atan2(aY,aX);
        //System.out.println("CentroidHG : x = "+aX+", y = "+aY+", anAngle = "+anAngle);
        return anAngle;
    }
    public double computeMag(int myX,int myY){
        int aY = centroid[1];
        int aX = centroid[0];   
        double aMagnitude = PntTool.getDistance(aX, aY, myX, myY);
        return aMagnitude;
    }
            
    public double getAngleWghtAvg(){
        System.out.println("HistogramOG: angleWghtAvg = "+angleWghtdAvg);
        return angleWghtdAvg;
    }
    public double getGradientAvg(){
        System.out.println("HistogramOG: gradientAvg = "+gradientAvg);
        return gradientAvg;
    }      
    public double computeMaxGradient() {
        double max = 0.0;
        for(int i=0;i<numberOfBins;i++) {
            if(cntrdHistogram[i] > max) max = cntrdHistogram[i];
        }
        return max;
    }
    public void updateMaxRealAngle(double myMaxGradient,double myRealAngle){
        //System.out.println("HistogramOG : max gradient = "+maxGradient+", new gradient = "+myMaxGradient+", index = "+myGradientBinIndex+", angle = "+ getAngleByIndex(myGradientBinIndex));
        if(myMaxGradient > maxGradient){
            maxGradient = myMaxGradient;
            maxRealAngle = myRealAngle;
        }
    }
    public void updateMaxGradientAngle(double myMaxGradient,int myGradientBinIndex,double myRealAngle){
        //System.out.println("HistogramOG : max gradient = "+maxGradient+", new gradient = "+myMaxGradient+", index = "+myGradientBinIndex+", angle = "+ getAngleByIndex(myGradientBinIndex));
        if(myMaxGradient > maxGradient){
            maxGradient = myMaxGradient;
            maxGradientBinIndex = myGradientBinIndex;
            maxBinAngle = getAngleByIndex(myGradientBinIndex);
            maxRealAngle = myRealAngle;
        }
    }
    public void updteMaxCntrdDstnce(double phi,double myMgntde){
        //System.out.println("HistogramOG : max gradient = "+maxGradient+", new gradient = "+myMaxGradient+", index = "+myGradientBinIndex+", angle = "+ getAngleByIndex(myGradientBinIndex));
        double aPhiAngle = phi+binSize/2.0;
        
        //Note: this may conflict with temporal gradient threshold!!!!!
        //gradient = (gradient <= 20) ?  0: gradient ;

        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI + aPhiAngle;
        }        
        
        int binIndex = (int)((aPhiAngle)/binSize);
        //System.out.println("CentroidHG: binIndex = "+binIndex+", aPhiAngle = "+aPhiAngle+", binSize = "+binSize);
        if(myMgntde > cntrdDstnces[binIndex]){
            cntrdDstnces[binIndex] = myMgntde;
            maxDstnceBinIndex = binIndex;
            //System.out.println("CentroidHG: binIndex = "+binIndex+", aPhiAngle = "+aPhiAngle+", myMgntde = "+myMgntde);
        }
    }    
    public double rtrveCntrdFtreMaxDstnce(){
        return cntrdDstnces[maxDstnceBinIndex];
    }
    public int rtrveGrdntIndexViaDstnce(){
        int maxGradIndex1 = getMaxGradientBinIndex();
        double aMaxGrdnt1 = getMaxGradient();
        double aGrdnt1 = getGrdntMagByIndex(maxGradIndex1);        
                
        int maxGradIndex2 = rtrvePlus180Index(maxGradIndex1);
        double aGrdnt2 = getGrdntMagByIndex(maxGradIndex2);
        //System.out.println("CentroidHG:  max gradient = "+aMaxGrdnt1+" gradient["+maxGradIndex1+"] = "+aGrdnt1+", gradient["+maxGradIndex2+"] = "+aGrdnt2);
        
        int maxGradIndex3 = rtrveMaxDstnceBinIndex(maxGradIndex1,maxGradIndex2);
        //System.out.println("CentroidHG: gradient index 1 = "+maxGradIndex1+", gradient index 2 = "+maxGradIndex2+", max distance index 3 = "+maxGradIndex3);
        return maxGradIndex3;       
    }
    public int rtrvePlus180Index(int myIndex1){
        int anIndex2 = 0;
        int indexPlus180 = numberOfBins/2;
        if(myIndex1 + indexPlus180 <  numberOfBins){
            anIndex2 = myIndex1 + indexPlus180;
        }else{
            anIndex2 = myIndex1 - indexPlus180;        
        }
        return anIndex2;        
    }
    public int rtrveMaxDstnceBinIndex(int myIndex1,int myIndex2){
        int anIndex = 0;
        //System.out.println("CentroidHG: cntrdDstnces["+myIndex1+"] = "+cntrdDstnces[myIndex1]+", cntrdDstnces["+myIndex2+"] = "+cntrdDstnces[myIndex2]);
        if(cntrdDstnces[myIndex1] >= cntrdDstnces[myIndex2]){
            anIndex = myIndex1;
        }else if(cntrdDstnces[myIndex1] < cntrdDstnces[myIndex2]){
            anIndex = myIndex2;
        }
        return anIndex;
    }    
    public double getMaxGradient()
    {
        return maxGradient;
    }
    public int getMaxGradientBinIndex(){
        return maxGradientBinIndex;
    }
    public void setMaxGradientBinIndex(int myIndex){
        maxGradientBinIndex = myIndex;
    }    
    public double getMaxGradientAngle(){
        return maxBinAngle;
    }
    public double getAngleByIndex(int myBinIndex) {
        return myBinIndex * binSize;
    }  
    public double getDistMagByIndex(int bin) {
        return cntrdHistogram[bin];
    }    
    public double getGrdntMagByIndex(int bin) {
        return grdntHistogram[bin];
    }
    public double getGradientByAngle(double phi) {
        double aPhiAngle = phi;   
        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI+phi;
        }          
        int binIndex;
        binIndex = (int)(aPhiAngle/binSize);        
        return cntrdHistogram[binIndex];
    }
    public double computeBinAngle(double myAngle){
        double aPhiAngle = myAngle;
        /*
        if(phi >= 2*Math.PI) {
            aPhiAngle = phi%(2*Math.PI);
        }*/
        if(myAngle < 0){
            aPhiAngle = 2*Math.PI+myAngle;
        }
        int binIndex = (int)(aPhiAngle/binSize);
        double angle = (binIndex*binSize);
        return angle;            
    }
    public double[] getCntrdHistogram() {
        return cntrdHistogram;
    }
    public double[] getGrdntHistogram() {
        return grdntHistogram;
    }    
    public int getNumberOfBins() {
        return numberOfBins;
    }
    public double getBinSize() {
        return binSize;
    }
    /*
    public void addHistogramOG(CentroidHG myHistogramOG){
        int nmbrBins = myHistogramOG.getNumberOfBins();

        for(int i=0;i<nmbrBins;i++){
            double anAngle = myHistogramOG.getAngleByIndex(i);
            double aGradient = myHistogramOG.getDistMagByIndex(i);
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            insertByAngle(anAngle,aGradient);         
        }
        //double aPhiAngle = myHistogramOG.getAngleWghtAvg();  
        //double aGradient = myHistogramOG.getGradientAvg();          
        //updteAngleViaWghtdAvg(aPhiAngle,aGradient);
    }*/
    public void addHistogramOG(HistogramOG myHistogramOG,double myScale){
        int nmbrBins = myHistogramOG.getNumberOfBins();

        for(int i=0;i<nmbrBins;i++){
            double anAngle = myHistogramOG.getAngleByIndex(i);
            double aGradient = myHistogramOG.getGradientByIndex(i);
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            hogInsert(anAngle,aGradient,myScale);         
        }
    }    
    /*
    public void updateCentroid(CentroidHG myHistogramOG){
        int nmbrBins = myHistogramOG.getNumberOfBins();

        for(int i=0;i<nmbrBins;i++){
            double anAngle = myHistogramOG.getAngleByIndex(i);
            double aGradient = myHistogramOG.getDistMagByIndex(i);
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            insertByAngle(anAngle,aGradient);         
        }
        //double aPhiAngle = myHistogramOG.getAngleWghtAvg();  
        //double aGradient = myHistogramOG.getGradientAvg();          
        //updteAngleViaWghtdAvg(aPhiAngle,aGradient);
    }*/
    public double[] computeOrientation(){
        //Note this should be converted to real time insertion processing!!!        
        int aMaxGradientIndex = rtrveGrdntIndexViaDstnce();
        return computeOrientation(aMaxGradientIndex);
    }    
    public double[] computeOrientation2(){
        //Note this should be converted to real time insertion processing!!!        
        int aMaxGradientIndex = getMaxGradientBinIndex();
        //System.out.println("CentroidHG : max index = "+aMaxGradientIndex);
        return computeOrientation(aMaxGradientIndex);
    }        
    public double[] computeOrientation(int myIndex){
        //Note this should be converted to real time insertion processing!!!        
        int nmbrBins = getNumberOfBins();

        //for(int i=0;i<nmbrBins;i++){
        //int aMaxGradientIndex = getMaxGradientBinIndex();
        int aMaxGradientIndex = myIndex;
    
        int leftIndex = aMaxGradientIndex+1;
        int rightIndex = aMaxGradientIndex-1;    
        
        if(leftIndex >= nmbrBins){
            leftIndex = leftIndex - nmbrBins;
        }
        if(rightIndex < 0){
            rightIndex = nmbrBins + rightIndex;
        }        
        //System.out.println("CentroidHG.computeOrientation(): max index = "+aMaxGradientIndex+", leftIndex = "+leftIndex+", rightIndex = "+rightIndex);
    
        double aMaxGradient = getMaxGradient();        
        double aMaxGradientAngle = getAngleByIndex(aMaxGradientIndex);        
        int aMaxGradientX1 = (int)(aMaxGradient*Math.cos(aMaxGradientAngle));
        int aMaxGradientY1 = -(int)(aMaxGradient*Math.sin(aMaxGradientAngle));
        
        double aLeftGradient = getDistMagByIndex(leftIndex);
        double aLeftAngle = getAngleByIndex(leftIndex);        
        int aLeftGradientX1 = (int)(aLeftGradient*Math.cos(aLeftAngle));
        int aLeftGradientY1 = -(int)(aLeftGradient*Math.sin(aLeftAngle));
        
        double aRightGradient = getDistMagByIndex(rightIndex);
        double aRightAngle = getAngleByIndex(rightIndex);        
        int aRightGradientX1 = (int)(aRightGradient*Math.cos(aRightAngle));
        int aRightGradientY1 = -(int)(aRightGradient*Math.sin(aRightAngle));        
        
        int totalX = aMaxGradientX1+aLeftGradientX1+aRightGradientX1;      
        int totalY = aMaxGradientY1+aLeftGradientY1+aRightGradientY1;             
        
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            //insertByAngle(anAngle,aGradient);
        glblVctrX = totalX;
        glblVctrY = totalY;
            
        //}   
        double aGlblVctr[] = {glblVctrX,glblVctrY};
        return aGlblVctr;
    }
    public double[] compute180OrientationOld(){
        //Note this should be converted to real time insertion processing!!!        
        int nmbrBins = getNumberOfBins()/2;

        for(int i=0;i<nmbrBins;i++){
            double anAngle = getAngleByIndex(i);
            double aGradient = getDistMagByIndex(i);
            int x1 = (int)(aGradient*Math.cos(anAngle));
            int y1 = -(int)(aGradient*Math.sin(anAngle));             
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            //insertByAngle(anAngle,aGradient);
            glblVctrX += x1;
            glblVctrY += y1;
            
        }   
        double aGlblVctr[] = {glblVctrX,glblVctrY};
        return aGlblVctr;
    }
    public double getGlblVctrX(){
        return glblVctrX;
    }
    public double getGlblVctrY(){
        return glblVctrY;
    }
    public double compareBins8(CentroidHG myHOG2) {
        return compareBins8(this,myHOG2);
    }    
    public static double compareBins8(CentroidHG myHOG1, CentroidHG myHOG2) {
        //int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        int anIndexDiff = computeIndexDiffViaDstnce(myHOG1, myHOG2);
        
        int anIndex0 = rtrveBinOffSet(0,anIndexDiff,8);        
        int anIndex1 = rtrveBinOffSet(1,anIndexDiff,8);
        int anIndex2 = rtrveBinOffSet(2,anIndexDiff,8);
        int anIndex3 = rtrveBinOffSet(3,anIndexDiff,8);
        int anIndex4 = rtrveBinOffSet(4,anIndexDiff,8);
        int anIndex5 = rtrveBinOffSet(5,anIndexDiff,8);
        int anIndex6 = rtrveBinOffSet(6,anIndexDiff,8);
        int anIndex7 = rtrveBinOffSet(7,anIndexDiff,8);
        
        double hogDiff0 = computeHogBinDiff(myHOG1, myHOG2, anIndex0, 0);
        double hogDiff1 = computeHogBinDiff(myHOG1, myHOG2, anIndex1, 1);
        double hogDiff2 = computeHogBinDiff(myHOG1, myHOG2, anIndex2, 2);
        double hogDiff3 = computeHogBinDiff(myHOG1, myHOG2, anIndex3, 3);
        double hogDiff4 = computeHogBinDiff(myHOG1, myHOG2, anIndex4, 4);
        double hogDiff5 = computeHogBinDiff(myHOG1, myHOG2, anIndex5, 5);
        double hogDiff6 = computeHogBinDiff(myHOG1, myHOG2, anIndex6, 6);
        double hogDiff7 = computeHogBinDiff(myHOG1, myHOG2, anIndex7, 7);
        
        double sumHOGDiff = hogDiff0 * hogDiff0 + hogDiff1 * hogDiff1 + hogDiff2 * hogDiff2 + hogDiff3 * hogDiff3
                + hogDiff4 * hogDiff4 + hogDiff5 * hogDiff5 + hogDiff6 * hogDiff6 + hogDiff7 * hogDiff7;
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }
    public static double computeBinDiffViaCompare(CentroidHG myHOG1, CentroidHG myHOG2) {
        //int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();        
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff1 = hog2MaxIndex - hog1MaxIndex;
        int hogIndexDiff2 = 0;
        
        double binDiff = 0;
        
        double aDiff1 =  computeBinsDiff8(myHOG1, myHOG2,hogIndexDiff1);
        
        int aDiff1Plus4 = rtrveBinOffSet(hog2MaxIndex,4,8);
        System.out.println("CentroidHG :: hog1MaxIndex = "+hog1MaxIndex+", hog2MaxIndex = "+hog2MaxIndex+", aDiff1Plus4 = "+aDiff1Plus4);
        hogIndexDiff2 = hog1MaxIndex - aDiff1Plus4;
        
        double aDiff2 = computeBinsDiff8(myHOG1, myHOG2,hogIndexDiff2);
        System.out.println("CentroidHG : aDiff1 = "+aDiff1+", aDiff2 = "+aDiff2);
            
        if(aDiff1 <= aDiff2){
            binDiff = aDiff1;
        }else{
            hog1MaxIndex = hog2MaxIndex;
            myHOG1.setMaxGradientBinIndex(hog2MaxIndex); 
            binDiff = aDiff2;
        }

        double glblHOG1[] = myHOG1.computeOrientation2();
        double glblHOG2[] = myHOG2.computeOrientation2();
        
        double anAngle1 =  Math.atan2(glblHOG1[1],glblHOG1[0]);
        double anAngle2 =  Math.atan2(glblHOG2[1],glblHOG2[0]);
        //System.out.println("CentroidHG: selected hog1MaxIndex =  "+hog1MaxIndex+", hog2MaxIndex = "+hog2MaxIndex);
        
        rotationAngleSum += Math.abs(anAngle1 - anAngle2);
        //System.out.println("CentroidHG : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2+", rotationAngleSum = "+rotationAngleSum);
        
       //System.out.println(rotationAngleSum);
        return binDiff;
    }    
    public static double computeBinDiffViaCompare2(CentroidHG myHOG1, CentroidHG myHOG2) {
        //int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();        
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff1 = hog2MaxIndex - hog1MaxIndex;
        
        double binDiff = Math.abs(hogIndexDiff1);
        

        
        //int aDiff1Plus4 = rtrveBinOffSet(hog2MaxIndex,4,8);
        //System.out.println("CentroidHG :: hog1MaxIndex = "+hog1MaxIndex+", hog2MaxIndex = "+hog2MaxIndex+", binDiff = "+binDiff);
        //hogIndexDiff2 = hog1MaxIndex - aDiff1Plus4;
        
        //double aDiff2 = computeBinsDiff8(myHOG1, myHOG2,hogIndexDiff2);
        //System.out.println("CentroidHG : aDiff1 = "+aDiff1+", aDiff2 = "+aDiff2);
            
        if(binDiff == 4){
            hog1MaxIndex = hog2MaxIndex;
            myHOG1.setMaxGradientBinIndex(hog2MaxIndex); 
        }
        
        double glblHOG1[] = myHOG1.computeOrientation2();
        double glblHOG2[] = myHOG2.computeOrientation2();
        
        double anAngle1 =  Math.atan2(glblHOG1[1],glblHOG1[0]);
        double anAngle2 =  Math.atan2(glblHOG2[1],glblHOG2[0]);
        //System.out.println("CentroidHG: selected hog1MaxIndex =  "+hog1MaxIndex+", hog2MaxIndex = "+hog2MaxIndex);
        
        rotationAngleSum += Math.abs(anAngle1 - anAngle2);
        //System.out.println("CentroidHG : anAngle1 = "+anAngle1+", anAngle2 = "+anAngle2+", rotationAngleSum = "+rotationAngleSum);
        
        System.out.println(rotationAngleSum);
        return binDiff;
    }    
    public static double computeAngleDiffViaCompare(CentroidHG myHOG1, CentroidHG myHOG2) {
        //int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();        
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;
        double angleDiff = 0;
        
        double aDiff1 =  computeBinsDiff8(myHOG1, myHOG2,hogIndexDiff);
        
        int aDiff1Plus4 = rtrveBinOffSet(hog1MaxIndex,hogIndexDiff+4,8);
        
        double aDiff2 =  computeBinsDiff8(myHOG1, myHOG2,aDiff1Plus4);
                
        if(aDiff1 <= aDiff2){
            double angle1[] = myHOG1.computeOrientation(hog1MaxIndex);
            double angle2[] = myHOG2.computeOrientation(hog2MaxIndex);  
            angleDiff = angle2[0]-angle1[0];
        }else{
            double angle1[] = myHOG1.computeOrientation(hog1MaxIndex);
            double angle2[] = myHOG2.computeOrientation(hogIndexDiff+4);   
            angleDiff = angle2[0]-angle1[0];
        }     
        return angleDiff;
    }      
    public static double compareDiffBins8(CentroidHG myHOG1, CentroidHG myHOG2) {
        //int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        double aDiff1 =  computeBinsDiff8(myHOG1, myHOG2,anIndexDiff);
        double aDiff2 =  computeBinsDiff8(myHOG1, myHOG2,anIndexDiff+4);
        if(aDiff1 <= aDiff2){
            return aDiff1;
        }else{
            return aDiff2;
        }
    }
    public static double computeBinsDiff8(CentroidHG myHOG1, CentroidHG myHOG2,int myIndexDiff){
        int anIndex0 = rtrveBinOffSet(0,myIndexDiff,8);        
        int anIndex1 = rtrveBinOffSet(1,myIndexDiff,8);
        int anIndex2 = rtrveBinOffSet(2,myIndexDiff,8);
        int anIndex3 = rtrveBinOffSet(3,myIndexDiff,8);
        int anIndex4 = rtrveBinOffSet(4,myIndexDiff,8);
        int anIndex5 = rtrveBinOffSet(5,myIndexDiff,8);
        int anIndex6 = rtrveBinOffSet(6,myIndexDiff,8);
        int anIndex7 = rtrveBinOffSet(7,myIndexDiff,8);
        
        double hogDiff0 = computeHogBinDiff(myHOG1, myHOG2, anIndex0, 0);
        double hogDiff1 = computeHogBinDiff(myHOG1, myHOG2, anIndex1, 1);
        double hogDiff2 = computeHogBinDiff(myHOG1, myHOG2, anIndex2, 2);
        double hogDiff3 = computeHogBinDiff(myHOG1, myHOG2, anIndex3, 3);
        double hogDiff4 = computeHogBinDiff(myHOG1, myHOG2, anIndex4, 4);
        double hogDiff5 = computeHogBinDiff(myHOG1, myHOG2, anIndex5, 5);
        double hogDiff6 = computeHogBinDiff(myHOG1, myHOG2, anIndex6, 6);
        double hogDiff7 = computeHogBinDiff(myHOG1, myHOG2, anIndex7, 7);
        
        double sumHOGDiff = hogDiff0 * hogDiff0 + hogDiff1 * hogDiff1 + hogDiff2 * hogDiff2 + hogDiff3 * hogDiff3
                + hogDiff4 * hogDiff4 + hogDiff5 * hogDiff5 + hogDiff6 * hogDiff6 + hogDiff7 * hogDiff7;
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }    
    private static double computeHogBinDiff(CentroidHG myHOG1, CentroidHG myHOG2, int myIndex1, int myIndex2) {
        //double hogDiff1 = myHOG1.getDistMagByIndex(myIndex1);
        //double hogDiff2 = myHOG2.getDistMagByIndex(myIndex2);
        double hogDiff1 = myHOG1.getGrdntMagByIndex(myIndex1);
        double hogDiff2 = myHOG2.getGrdntMagByIndex(myIndex2);
        double hogDiff = hogDiff1 - hogDiff2;
        //System.out.println("CentroidHG : hogDiff1 = "+hogDiff1+", hogDiff2 = "+hogDiff2+", hogDiff = "+hogDiff);
        return hogDiff;
    }  
    private static double computeHogBinDiff2(CentroidHG myHOG1, CentroidHG myHOG2, int myIndex1, int myIndex2) {
        double hogDiff1 = myHOG1.getGrdntMagByIndex(myIndex1);
        double hogDiff2 = myHOG2.getGrdntMagByIndex(myIndex2);
        double hogDiff = hogDiff1 - hogDiff2;
        //System.out.println("HistogramOG : hogDiff1 = "+hogDiff1+", hogDiff2 = "+hogDiff2+", hogDiff = "+hogDiff);
        return hogDiff;
    }  
    public static int computeHogBinIndexDiff(CentroidHG myHOG1, CentroidHG myHOG2) {
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;
        //System.out.println("HistogramOG : hogIndexDiff = "+hogIndexDiff);
        return hogIndexDiff;
    }   
    public static int computeHogBinIndexDiff2(CentroidHG myHOG1, CentroidHG myHOG2) {
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;
        //System.out.println("HistogramOG : hogIndexDiff = "+hogIndexDiff);
        return hogIndexDiff;
    }    
    public static int computeIndexDiffViaDstnce(CentroidHG myHOG1, CentroidHG myHOG2) {
        int hog1MaxIndex = myHOG1.rtrveGrdntIndexViaDstnce();
        int hog2MaxIndex = myHOG2.rtrveGrdntIndexViaDstnce();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;

        //System.out.println("CentroidHG.computeIndexDiffViaDstnce() : hogIndexDiff = "+hogIndexDiff);
        return hogIndexDiff;
    }    
    public static int rtrveBinOffSet(int myIndex,int myIndexDiff,int myNumberBins){        
        int aTotal = myIndex + myIndexDiff;
        
        if(aTotal < 0){
            aTotal = myNumberBins + aTotal;
        }else if(aTotal >= myNumberBins){
            aTotal = aTotal - myNumberBins;
        }
        return aTotal;
    }
    public static double compareHog1x1BinDiff(CentroidHG myHOG1[][], CentroidHG myHOG2[][]) {
        double hogDiff00 = compareBins8(myHOG1[0][0], myHOG2[0][0]);
        double sumSquaredHOGDiff = hogDiff00;        
        return sumSquaredHOGDiff;
    }    
    public static double compareHog1x1BinDiff(CentroidHG myHOG1, CentroidHG myHOG2) {
        double hogDiff00 = compareBins8(myHOG1, myHOG2);
        double sumSquaredHOGDiff = hogDiff00;        
        return sumSquaredHOGDiff;
    }    
    public int getAvgX() {
        return avgX;
    }

    public void setAvgX(int xAvgPOs) {
        this.avgX = xAvgPOs;
    }

    public int getAvgY() {
        return avgY;
    }

    public void setAvgY(int yAvgPos) {
        this.avgY = yAvgPos;
    }
    public int[] getCentroid(){
        return centroid;
    }
    public double getMaxRealAngle(){
        return maxRealAngle;
    }
    public int getTotalCount(){
       return totalCount;   
    }
    public int getChrctrstcScale(){
        return getTotalCount();
    }
    public int getImageWidth(){
        return imageWidth;
    }
    public int getImageHeight(){
        return imageHeight;
    }
    public double getTotalGradient(){
        return totalGradient;
    }
    public double computeChrctrstcScale(int myWidth,int myHeight){       
        //double aScale = (10000.0)*(totalGradient*numberOfBins)/(double)(myWidth*myHeight);
        double aScaleCount = (10000.0)*(totalCount*numberOfBins)/(double)(myWidth*myHeight);        
        //System.out.println("CentroidHG : Characteristic scale : "+aScale+", width = "+myWidth+", height = "+myHeight); 
        //System.out.println("CentroidHG : Characteristic scale : "+aScaleCount+", width = "+myWidth+", height = "+myHeight);         
        return aScaleCount;
    }
    public static double cmpteTrnsltnDstnce(CentroidHG myCentroidHG1,CentroidHG myCentroidHG2){
        int centroid1[] = myCentroidHG1.getCentroid();
        int centroid2[] = myCentroidHG2.getCentroid();        
        
        double aDistance = PntTool.getDistance(centroid1[0], centroid1[1], centroid2[0], centroid2[1]);
        return aDistance;
    }
}