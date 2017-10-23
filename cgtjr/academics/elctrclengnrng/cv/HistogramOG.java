/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

/**
 *
 * @author finitesystem
 */

import java.lang.Math;

public class HistogramOG {
    
    private double[] histogram;
    private int numberOfBins;
    private double binSize;
    private double maxBinAngle;
    private double maxRealAngle;
    private double realAngleAvg;
    private double maxGradient;
    private int maxGradientBinIndex;
    private double angleWghtedSum;
    private double gradientSum;
    private double angleWghtdAvg;
    private int totalCount;
    private double gradientAvg;
    private double orientations[];
    private double glblVctrX;
    private double glblVctrY;    
            
    
    /**
     * @param nbins -- number of bins (one for each orientation)
     */
    public HistogramOG(int nbins) {
        numberOfBins = nbins;
        histogram = new double[nbins];
        this.binSize = 2.0*Math.PI/nbins;
    }

    // default with 8 bins
    public HistogramOG() {
        this(8);
    }
    public static HistogramOG[][] retrieveHOGGrid(int myXBlocks,int myYBlocks){
        HistogramOG HOGGrid[][] = new HistogramOG[myXBlocks][myYBlocks];
        return HOGGrid;
    }
    public void insert(double phi, double gradient) {
       //(phi, gradient,1.0/127.0);
       insert(phi, gradient,1.0/512.0);        
    }
    public void insert(double phi, double gradient,double myScale) {
        //System.out.println("HistogramOG.insert(): insert local hog .............................................");        
        double aPhiAngle = phi+binSize/2.0;
        
        //Note: this may conflict with temporal gradient threshold!!!!!
        //gradient = (gradient <= 20) ?  0: gradient ;
        
        if(gradient == 0){
            //return;
        }
        /*
        if(phi >= 2*Math.PI) {
            aPhiAngle = phi%(2*Math.PI);
        }*/
        //System.out.println("HistogramOG: phi = "+phi);
        if(aPhiAngle > 2 * Math.PI){
            aPhiAngle = aPhiAngle - 2*Math.PI;
        }else if(aPhiAngle < 0){
            aPhiAngle = 2*Math.PI + aPhiAngle;
        }        
        int binIndex;
        binIndex = (int)((aPhiAngle)/binSize);        
        //histogram[binIndex] += gradient;
        double scaledGradient = gradient*myScale;
        histogram[binIndex] += scaledGradient;        
        //histogram[binIndex] += 1;//scaledGradient;
        
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", histogram["+binIndex+"] = "+histogram[binIndex]);

        updateMaxGradientAngle(histogram[binIndex],binIndex,aPhiAngle);
        updteAngleViaWghtdAvg(aPhiAngle,scaledGradient);
    }
    public void insertByAngle(double phi, double gradient) {
        //System.out.println("HistogramOG.insertByAngle(): insert global hog .............................................");
        double aPhiAngle = phi;//+binSize/2;
        
        /*
        if(phi >= 2*Math.PI) {
            aPhiAngle = phi%(2*Math.PI);
        }*/
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
        double scaledGradient = 0.05*gradient;
        histogram[binIndex] += scaledGradient;
        
        //System.out.println("HistogramOG: phi = "+phi+", modified phi = "+aPhiAngle+", binSize = "+binSize+", binIndex = "+binIndex+", nmbrBins = "+numberOfBins+", gradient = "+gradient+", histogram["+binIndex+"] = "+histogram[binIndex]);

        updateMaxGradientAngle(histogram[binIndex],binIndex,aPhiAngle);  
        updteAngleViaWghtdAvg(aPhiAngle,scaledGradient);

    }      
    private void updteAngleViaWghtdAvg(double myPhiAngle,double myGradient){
        totalCount++;        
        angleWghtedSum += myPhiAngle*myGradient;
        gradientSum += myGradient;  
        angleWghtdAvg = angleWghtedSum/gradientSum;
        //angleWghtdAvg = angleWghtedSum/totalCount;
        gradientAvg = gradientSum/totalCount;
        //System.out.println("HistogramOG.updteAngleViaWghtdAvg(): myPhiAngle = "+ myPhiAngle+", angleSum = "+angleWghtedSum+", gradientSum = "+gradientSum+", angleWghtAvg = "+angleWghtdAvg+", gradientAvg = "+gradientAvg+", totalCount = "+totalCount);
    }
    public double getAngleWghtAvg(){
        //System.out.println("HistogramOG: angleWghtAvg = "+angleWghtdAvg);
        return angleWghtdAvg;
    }
    public double getGradientAvg(){
        //System.out.println("HistogramOG: gradientAvg = "+gradientAvg);
        return gradientAvg;
    }      
    public double computeMaxGradient() {
        double max = 0.0;
        for(int i=0;i<numberOfBins;i++) {
            if(histogram[i] > max) max = histogram[i];
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
    public double getMaxGradient()
    {
        return maxGradient;
    }
    
    public int getMaxGradientBinIndex(){
        return maxGradientBinIndex;
    }
    public double getMaxGradientAngle(){
        return maxBinAngle;
    }
    public double getAngleByIndex(int myBinIndex) {
        return myBinIndex * binSize;
    }    
    public double getGradientByIndex(int bin) {
        return histogram[bin];
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
        return histogram[binIndex];
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
    public double[] getHistogram() {
        return histogram;
    }
    public int getNumberOfBins() {
        return numberOfBins;
    }
    public double getBinSize() {
        return binSize;
    }
    public void addHistogramOG(HistogramOG myHistogramOG){
        int nmbrBins = myHistogramOG.getNumberOfBins();

        for(int i=0;i<nmbrBins;i++){
            double anAngle = myHistogramOG.getAngleByIndex(i);
            double aGradient = myHistogramOG.getGradientByIndex(i);
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            insertByAngle(anAngle,aGradient);         
        }
        //double aPhiAngle = myHistogramOG.getAngleWghtAvg();  
        //double aGradient = myHistogramOG.getGradientAvg();  
        
        //updteAngleViaWghtdAvg(aPhiAngle,aGradient);
    }
    public void updateCentroid(HistogramOG myHistogramOG){
        int nmbrBins = myHistogramOG.getNumberOfBins();

        for(int i=0;i<nmbrBins;i++){
            double anAngle = myHistogramOG.getAngleByIndex(i);
            double aGradient = myHistogramOG.getGradientByIndex(i);
            //System.out.println("HistogramOG.addHistogramOG : nmbr bins ="+ nmbrBins+",anAngle = "+anAngle+", gradient = "+aGradient);
            insertByAngle(anAngle,0.5*aGradient);         
        }
        //double aPhiAngle = myHistogramOG.getAngleWghtAvg();  
        //double aGradient = myHistogramOG.getGradientAvg();  
        
        //updteAngleViaWghtdAvg(aPhiAngle,aGradient);
    }    
    public double[] computeOrientation(){
        //Note this should be converted to real time insertion processing!!!        
        int nmbrBins = getNumberOfBins();

        //for(int i=0;i<nmbrBins;i++){
        int aMaxGradientIndex = getMaxGradientBinIndex();
        //int aMaxGradientIndex = rtrveGrdntIndexViaDstnce();
        
        int leftIndex = aMaxGradientIndex+1;
        int rightIndex = aMaxGradientIndex-1;    
        
        if(leftIndex >= nmbrBins){
            leftIndex = leftIndex - nmbrBins;
        }
        if(rightIndex < 0){
            rightIndex = nmbrBins + rightIndex;
        }        

        double aMaxGradient = getMaxGradient();        
        double aMaxGradientAngle = getMaxGradientAngle();        
        int aMaxGradientX1 = (int)(aMaxGradient*Math.cos(aMaxGradientAngle));
        int aMaxGradientY1 = -(int)(aMaxGradient*Math.sin(aMaxGradientAngle));
        
        double aLeftGradient = getGradientByIndex(leftIndex);
        double aLeftAngle = getAngleByIndex(leftIndex);        
        int aLeftGradientX1 = (int)(aLeftGradient*Math.cos(aLeftAngle));
        int aLeftGradientY1 = -(int)(aLeftGradient*Math.sin(aLeftAngle));
        
        double aRightGradient = getGradientByIndex(rightIndex);
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
            double aGradient = getGradientByIndex(i);
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
    public double compareBins8(HistogramOG myHOG2) {
        return compareBins8(this,myHOG2);
    }    
    public static double compareBins8(HistogramOG myHOG1, HistogramOG myHOG2) {
       return compareBins8(myHOG1,myHOG2,1,1);
    }
    public static double compareBins8(HistogramOG myHOG1, HistogramOG myHOG2,double myMaxDistance1,double myMaxDistance2) {
        int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        
        int anIndex0 = rtrveBinOffSet(0,anIndexDiff,8);        
        int anIndex1 = rtrveBinOffSet(1,anIndexDiff,8);
        int anIndex2 = rtrveBinOffSet(2,anIndexDiff,8);
        int anIndex3 = rtrveBinOffSet(3,anIndexDiff,8);
        int anIndex4 = rtrveBinOffSet(4,anIndexDiff,8);
        int anIndex5 = rtrveBinOffSet(5,anIndexDiff,8);
        int anIndex6 = rtrveBinOffSet(6,anIndexDiff,8);
        int anIndex7 = rtrveBinOffSet(7,anIndexDiff,8);
        
        double hogDiff0 = computeHogBinDiff(myHOG1, myHOG2, anIndex0, 0, myMaxDistance1, myMaxDistance2);
        double hogDiff1 = computeHogBinDiff(myHOG1, myHOG2, anIndex1, 1, myMaxDistance1, myMaxDistance2);
        double hogDiff2 = computeHogBinDiff(myHOG1, myHOG2, anIndex2, 2, myMaxDistance1, myMaxDistance2);
        double hogDiff3 = computeHogBinDiff(myHOG1, myHOG2, anIndex3, 3, myMaxDistance1, myMaxDistance2);
        double hogDiff4 = computeHogBinDiff(myHOG1, myHOG2, anIndex4, 4, myMaxDistance1, myMaxDistance2);
        double hogDiff5 = computeHogBinDiff(myHOG1, myHOG2, anIndex5, 5, myMaxDistance1, myMaxDistance2);
        double hogDiff6 = computeHogBinDiff(myHOG1, myHOG2, anIndex6, 6, myMaxDistance1, myMaxDistance2);
        double hogDiff7 = computeHogBinDiff(myHOG1, myHOG2, anIndex7, 7, myMaxDistance1, myMaxDistance2);
        double sumHOGDiff = hogDiff0 * hogDiff0 + hogDiff1 * hogDiff1 + hogDiff2 * hogDiff2 + hogDiff3 * hogDiff3
                + hogDiff4 * hogDiff4 + hogDiff5 * hogDiff5 + hogDiff6 * hogDiff6 + hogDiff7 * hogDiff7;
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }
    public static double compareBins8(HistogramOG myHOG1, HistogramOG myHOG2,CentroidHG myCntrdHG1, CentroidHG myCntrdHG2) {
        int anIndexDiff = computeHogBinIndexDiff(myHOG1, myHOG2);
        
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
    private static double computeHogBinDiff(HistogramOG myHOG1, HistogramOG myHOG2, int myIndex1, int myIndex2) {
       return computeHogBinDiff(myHOG1,myHOG2,myIndex1,myIndex2,1,1);
    }
    private static double computeHogBinDiff(HistogramOG myHOG1, HistogramOG myHOG2, int myIndex1, int myIndex2,double myScale1,double myScale2) {        
        double hogDiff1 = myHOG1.getGradientByIndex(myIndex1);
        double hogDiff2 = myHOG2.getGradientByIndex(myIndex2);
        double hogDiff = hogDiff1 - hogDiff2*myScale1/myScale2;
        //System.out.println("HistogramOG : hogDiff1 = "+hogDiff1+", hogDiff2 = "+hogDiff2+", hogDiff = "+hogDiff);
        return hogDiff;
    }  
    public static int computeHogBinIndexDiff(HistogramOG myHOG1, HistogramOG myHOG2) {
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;

        //System.out.println("HistogramOG.computeHogBinIndexDiff() : hogIndexDiff = "+hogIndexDiff);
        return hogIndexDiff;
    }    
    public static int computeHogBinIndexDiff(HistogramOG myHOG1, HistogramOG myHOG2,CentroidHG myCntrdHG1,CentroidHG myCntrdHG2) {
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        
        int cntrdIndex1 = myCntrdHG1.rtrveGrdntIndexViaDstnce();
        int cntrdIndex2 = myCntrdHG2.rtrveGrdntIndexViaDstnce();
        
        
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;

        //System.out.println("HistogramOG.computeHogBinIndexDiff() : hogIndexDiff = "+hogIndexDiff);
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
  
    public static double compareHog1x1BinDiff(HistogramOG myHOG1[][], HistogramOG myHOG2[][]) {
        double hogDiff00 = compareBins8(myHOG1[0][0], myHOG2[0][0]);
        double sumSquaredHOGDiff = hogDiff00;        
        return sumSquaredHOGDiff;
    }    
    
    public static double compareHog1x1BinDiff(HistogramOG myHOG1[][], HistogramOG myHOG2[][],double myMaxDistance1,double myMaxDistance2) {
        double hogDiff00 = compareBins8(myHOG1[0][0], myHOG2[0][0]);
        double sumSquaredHOGDiff = hogDiff00;        
        return sumSquaredHOGDiff;
    }     
    public static double compareHog1x1BinDiff(HistogramOG myHOG1[][], HistogramOG myHOG2[][],CentroidHG myCntrdHG1, CentroidHG myCntrdHG2) {
        double hogDiff00 = compareBins8(myHOG1[0][0], myHOG2[0][0]);
        double sumSquaredHOGDiff = hogDiff00;        
        return sumSquaredHOGDiff;
    }       
}