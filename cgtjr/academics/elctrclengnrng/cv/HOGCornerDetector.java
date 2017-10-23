/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 *
 * @author finitesystem
 */
public class HOGCornerDetector extends CornerDetectGrdnt {
    private ImageDrawData imagePixelData;
    private int gridSize;
    private int nmbrXBlocks;
    private int nmbrYBlocks;
    private HistogramOG hogradients[][];
    private int dscrptrOffset;
    private int descriptorIndex;
    private int descriptorWidth;
    private Object descriptors[];
    private int featureIndex;
    private int fatureMatchIndex;
    private boolean featureSearch;
    private int searchWindowSize = 27;
    private ArrayList hogArrayList;
    private ArrayList localHogArrayListPast;
    private ArrayList matchIndexAL;
    private double hogDstnceThrshld;
    private double lclPntTrnsltnDstnceThrshld = 10;
    private boolean isMatch;
    private HOGPosition hogPosition;

    private HashMap hogMatchHashMap;
    private boolean drawOverlap;
    private boolean isDrawArrow = true;
            
    public HOGCornerDetector(){}
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        
        //if(hogArrayList == null){
            hogArrayList = new ArrayList();
        //}else{
            //hogArrayList.clear();
        //}

        if(hogMatchHashMap == null){
           hogMatchHashMap = new HashMap();
        }else{
            hogMatchHashMap.clear();
        }
        if (localHogArrayListPast == null) {
            localHogArrayListPast = new ArrayList();
        }
        if(matchIndexAL == null){
            matchIndexAL = new ArrayList();            
        }else{
            matchIndexAL.clear();
        }
        if(descriptors == null){
           descriptors = new Object[aLength];
        }
        
        gridSize = 27;
        nmbrXBlocks = 1;
        nmbrYBlocks = 1;

        //hogradients = HistogramOG.retrieveHOGGrid(nmbrXBlocks, nmbrYBlocks);

        //imagePixelData = drawOverlap?getImageDrawData():new ImageDrawData(myImageWidth,myImageHeight);
        imagePixelData = getImageDrawData();        
        //imagePixelData.setReservedColor3(0x000000ff);
        hogDstnceThrshld = 2.5;
        //System.out.println("HOGCornerDetectOptmzd: test frame# = "+getFrameIndex());
        //setThreshold(500);
        //setIsDrawArrow(true);
    }    
    public void setMaxMatchDstnce(int myMatchDistance){
        lclPntTrnsltnDstnceThrshld = myMatchDistance;
    }

    /*
    public void initializeHOG() {
    for (int i = 0; i < nmbrXBlocks; i++) {
    for (int j = 0; j < nmbrYBlocks; j++) {
    hogradients[i][j] = new HistogramOG();
    }
    }
    }*/
    public boolean isDrawOverlap() {
        return drawOverlap;
    }

    public void setDrawOverlap(boolean myDrawOverlap) {
        this.drawOverlap = myDrawOverlap;
    }

    public boolean getIsDrawArrow() {
        return isDrawArrow;
    }

    public void setDisplayArrows(boolean myIsDrawArrow) {
        this.isDrawArrow = myIsDrawArrow;
    }

    public HOGPosition getHOGPosition(){
        return hogPosition;
    }
    public HistogramOG[][] createHOG1x1() {
        HistogramOG hog[][] = new HistogramOG[1][1];
        hog[0][0] = new HistogramOG(8);
        return hog;
    }
    public HashMap getHogMatchHashMap(){
        return hogMatchHashMap;
    }
    public HistogramOG[][] createHOG1x1(int myNumberBins) {
        HistogramOG hog[][] = new HistogramOG[1][1];
        hog[0][0] = new HistogramOG(myNumberBins);
        return hog;
    }
    public HistogramOG[][] createHOG3x3() {
        HistogramOG hog[][] = new HistogramOG[3][3];
        hog[0][0] = new HistogramOG();
        hog[0][1] = new HistogramOG();
        hog[0][2] = new HistogramOG();
        hog[1][0] = new HistogramOG();
        hog[1][1] = new HistogramOG();
        hog[1][2] = new HistogramOG();
        hog[2][0] = new HistogramOG();
        hog[2][1] = new HistogramOG();
        hog[2][2] = new HistogramOG();
        return hog;
    }
    public void filter(int myGrayValues[], int i) {
        super.filter(myGrayValues,i);
        createHOG(myGrayValues,i);
    }
    public void createHOG(int myGrayValues[], int i) {

        isMatch = false;
        if (getEigenValue(i) >= getEigenThreshold() && getTmprlGrdntValue() > getTmprlGrdntThrshld()) {
            //super.cornerDetect(grayValues, i);
            descriptorHOGBin1x1Grid3x3(myGrayValues, i);
        }        
    }    
    public boolean getIsMatch(){
        return isMatch;
    }
    public void setIsMatch(boolean myIsMatch){
        isMatch = myIsMatch;
    }    
    public void histogramInsert27x27(int myGrayValues[], int anIndex) {
        int aWidthx9 = 9 * getImageWidth();
        histogramInsert9x9(myGrayValues, anIndex - aWidthx9 - 9);
        histogramInsert9x9(myGrayValues, anIndex - aWidthx9);
        histogramInsert9x9(myGrayValues, anIndex - aWidthx9 + 9);
        histogramInsert9x9(myGrayValues, anIndex - 9);
        histogramInsert9x9(myGrayValues, anIndex);
        histogramInsert9x9(myGrayValues, anIndex + 9);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx9 - 9);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx9);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx9 + 9);
    }
    public void descriptorsHOG1x1(int myGrayValues[], int anIndex) {
        hogradients = createHOG1x1();//check
        featureIndex = anIndex;

        gridSize = 9;
        int aWidthx4 = 4 * getImageWidth();
        dscrptrOffset = aWidthx4 + 4;
        descriptorWidth = 9;
        histogramInsert9x9(myGrayValues, anIndex);

        HOGPosition aHOGPosition = new HOGPosition(hogradients, anIndex);

        //drawArrowByAngle(aHOGPosition);

        hogArrayList.add(aHOGPosition);
        compute(aHOGPosition, localHogArrayListPast);
    }
    public void descriptorHOGBin1x1Grid3x3(int myGrayValues[], int anIndex) {
        hogradients = createHOG1x1(8);//check
        featureIndex = anIndex;

        gridSize = 3;
        int aWidthx = getImageWidth();
        dscrptrOffset = aWidthx + 1;
        descriptorWidth = 3;

        histogramInsert3x3(myGrayValues, anIndex);

        //HOGPosition aHOGPosition = new HOGPosition(hogradients, anIndex);
        hogPosition = new HOGPosition(hogradients, anIndex);        

        //drawArrowByAngle(aHOGPosition);
        try{
            hogArrayList.add(hogPosition);
        }catch(ArrayIndexOutOfBoundsException aiobe){
            System.err.println("HOGCornerDetector ... update code !!!");
        }
        //computeHOG1x1(hogPosition, localHogArrayListPast);
    }
    public void descriptorsHOG27x27(int myGrayValues[], int anIndex) {
        hogradients = createHOG3x3();//check
        featureIndex = anIndex;

        gridSize = 9;
        int aWidthx14 = 14 * getImageWidth();
        dscrptrOffset = aWidthx14 + 14;
        descriptorWidth = 27;
        histogramInsert27x27(myGrayValues, anIndex);

        HOGPosition aHOGPosition = new HOGPosition(hogradients, anIndex);

        hogArrayList.add(aHOGPosition);
        compute(aHOGPosition, localHogArrayListPast);
    }
    public void descriptorsHOG9x9(int myGrayValues[], int anIndex) {
        hogradients = createHOG3x3();
        featureIndex = anIndex;
        gridSize = 3;
        int aWidthx5 = 5 * getImageWidth();
        dscrptrOffset = aWidthx5 + 5;
        descriptorWidth = 9;
        histogramInsert9x9(myGrayValues, anIndex);
        descriptors[descriptorIndex] = hogradients;
        descriptorIndex++;
    }
    public void histogramInsert9x9(int myGrayValues[], int anIndex) {
        int aWidthx3 = 3 * getImageWidth();
        histogramInsert3x3(myGrayValues, anIndex - aWidthx3 - 3);
        histogramInsert3x3(myGrayValues, anIndex - aWidthx3);
        histogramInsert3x3(myGrayValues, anIndex - aWidthx3 + 3);
        histogramInsert3x3(myGrayValues, anIndex - 3);
        histogramInsert3x3(myGrayValues, anIndex);
        histogramInsert3x3(myGrayValues, anIndex + 3);
        histogramInsert3x3(myGrayValues, anIndex + aWidthx3 - 3);
        histogramInsert3x3(myGrayValues, anIndex + aWidthx3);
        histogramInsert3x3(myGrayValues, anIndex + aWidthx3 + 3);
    }
    public void histogramInsert3x3(int myGrayValues[], int anIndex) {
        /*
         if (!isInBounds3x3(anIndex)) {
         return;
         }*/
        int aWidth = getImageWidth();
        //System.out.println("HOGCornerDetectOptmzd Begin : ******************************");
        histogramInsert(myGrayValues, anIndex - aWidth - 1);
        //System.out.println("HOGFeatureFilter : anIndex - aWidth - 1"+(anIndex - aWidth - 1));
        histogramInsert(myGrayValues, anIndex - aWidth);
        histogramInsert(myGrayValues, anIndex - aWidth + 1);
        histogramInsert(myGrayValues, anIndex - 1);
        histogramInsert(myGrayValues, anIndex);
        histogramInsert(myGrayValues, anIndex + 1);
        histogramInsert(myGrayValues, anIndex + aWidth - 1);
        histogramInsert(myGrayValues, anIndex + aWidth);
        histogramInsert(myGrayValues, anIndex + aWidth + 1);
        //System.out.println("HOGCornerDetectOptmzd End : ******************************");        
    }

    public void histogramInsert(int myGrayValues[], int anIndex) {

        int descriptorStart = featureIndex - dscrptrOffset;

        if (descriptorStart <= 0) {
            return;
        }

        grdntFilter(myGrayValues, anIndex);
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();

        //int featureX = ImageTool.rtrvXPstn(featureIndex, anImageWidth);
        //int featureY = ImageTool.rtrvYPstn(featureIndex, anImageWidth);
        //int featureIndex = ImageTool.rtrvIndex(featureX, featureY, anImageWidth);
        //System.out.println("HOGFeatureFilter : feature index 2 = "+featureIndex);


        int dscrptrX = ImageTool.rtrvXPstn(descriptorStart, anImageWidth);
        int dscrptrY = ImageTool.rtrvYPstn(descriptorStart, anImageWidth);

        //System.out.println("HOGFeatureFilter : dscrptrX = "+dscrptrX+", dscrptrY = "+dscrptrY);
        int x = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        int y = ImageTool.rtrvYPstn(anIndex, anImageWidth);

        int iGridIndex = (x - dscrptrX - 1) / (gridSize);
        int jGridIndex = (y - dscrptrY - 1) / (gridSize);

        if (iGridIndex < 0 || jGridIndex < 0) {
            return;
        }

        double myAngle = getOrntnAngle(anIndex);
        double myMagnitude = getGrdntMgntd(anIndex);
        double myMagnitudeScaled = myMagnitude;
        
        //double myHrntl = getGrdntHrzntl(anIndex);
        //double myVrtcl = getGrdntVrtcl(anIndex);
        
        // System.out.println("HOGFilter x = "+x+", y = "+y+"myHrntl = "+myHrntl+", myVrtcl = "+myVrtcl+", angle = "+myAngle+", magnitude = "+myMagnitude+", iGridIndex = " + iGridIndex + ", jGridIndex = " + jGridIndex + ", gridSize = " + gridSize + ", index = " + anIndex + ", featureIndex = " + featureIndex + ", descriptorStart = " + descriptorStart + ", dscrptrOffset = " + dscrptrOffset);
        //System.out.println("HOGFilter iGridIndex = " + iGridIndex + ", jGridIndex = " + jGridIndex + ", x = " + x + ", y = " + y + ", dscrptrX = " + dscrptrX + ", dscrptrY = " + dscrptrY + ", featureX = " + featureX + ", featureY = " + featureY);
        //hog[iGridIndex][jGridIndex].insert(myAngle, myMagnitude);
        //myAngle = Math.PI/4;
        //myMagnitude = .5;
        //System.out.println("HOGFeatureFilter magnitude = "+myMagnitude);

        try {
            //hogradients[iGridIndex][jGridIndex].insert(myAngle, myMagnitudeScaled, 1.0 / 127.0);
            //TODO: parameter values should be configurable
            hogradients[iGridIndex][jGridIndex].insert(myAngle, myMagnitudeScaled, 1.0 / 256.0);            
            int myPixelData[] = imagePixelData.getImagePixels();
            int aColor = 0x0072818B;

            //drawData(myPixelData, anIndex, iGridIndex, jGridIndex, myAngle, dscrptrX, dscrptrY,aColor);
            if(anIndex == featureIndex){
                //imagePixelData.drawData(0x00BEE2E7, anIndex);                               
            }            
        } catch (ArrayIndexOutOfBoundsException aiobe) {
        }
    }

    public void drawArrow(HOGPosition myHOGPosition1, HOGPosition myHOGPosition2) {
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();
        int anIndex1 = myHOGPosition1.getPositionIndex();
        int anIndex2 = myHOGPosition2.getPositionIndex();
        int x1 = ImageTool.rtrvXPstn(anIndex1, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex1, anImageWidth);
        int x2 = ImageTool.rtrvXPstn(anIndex2, anImageWidth);
        int y2 = ImageTool.rtrvYPstn(anIndex2, anImageWidth);

        int myPixelData[] = imagePixelData.getImagePixels();
        //System.out.println("HOGCornerDetect draw : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2+" drawarrow = "+getIsDrawArrow());
        //ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        if(getIsDrawArrow()){
           ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x00ff0000,0x00ff0000);
        }
   }

    public void drawArrowByAngle(HOGPosition myHOGPosition1) {
        double gradientMag = myHOGPosition1.getHog()[0][0].getMaxGradient();
        double gradientAngle = myHOGPosition1.getHog()[0][0].getMaxGradientAngle();

        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();
        int anIndex1 = myHOGPosition1.getPositionIndex();

        int x1 = ImageTool.rtrvXPstn(anIndex1, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex1, anImageWidth);
        int x2 = x1 + (int) (gradientMag * Math.cos(gradientAngle));
        int y2 = y1 - (int) (gradientMag * Math.sin(gradientAngle));

        int myPixelData[] = imagePixelData.getImagePixels();
        //System.out.println("HOGFeatureSqnceFilter : x1 = " + x1 + "y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2);
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x000000ff, 0x000000ff);
    }
    public void drawData(int[] myGrayValues, int anIndex, int myIGridIndex, int myJGridIndex, double myAngle, int myDscrptrX, int myDscrptrY, int myColor) {
        int anImageWidth = getImageWidth();

        double myTotalMag = 0.5*hogradients[myIGridIndex][myJGridIndex].getGradientByAngle(myAngle);
        //double myTotalMag = 5;

        int x1 = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex, anImageWidth);

        int xGridCenter = (int) (myIGridIndex * gridSize + .5 * gridSize + 1);
        int yGridCenter = (int) (myJGridIndex * gridSize + .5 * gridSize + 1);

        //System.out.println("HOGFeatureFilter : xGridCenter = "+xGridCenter+", yGridCenter = "+yGridCenter);
        double anAngle = hogradients[myIGridIndex][myJGridIndex].computeBinAngle(myAngle);
        int xGridPos = (int) (myTotalMag * Math.cos(anAngle));
        int yGridPos = (int) (myTotalMag * Math.sin(anAngle));

        //System.out.println("mag = "+myTotalMag+", angle = "+anAngle);
        //int xPos = myDscrptrX + xGridCenter + xGridPos;
        //int yPos = myDscrptrY + yGridCenter - yGridPos;
        int xPos = x1  + xGridPos;
        int yPos = y1  - yGridPos;
        int pixelColor = 0x0000ff00;
        if (myTotalMag < 1) {
            pixelColor = 0x000000ff;
        } else {
            pixelColor = 0x000000ff;
        }

        //int centerIndex = ImageTool.rtrvIndex(xGridCenter, yGridCenter, anImageWidth);
        int magIndex = ImageTool.rtrvIndex(xPos, yPos, anImageWidth);
        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());ss
        //System.out.println("SADCornerRctfdFltr: index = "+myIndex+", matchingIndex = "+matchingIndex+", imgWidth = "+imgWidth+", x2 = "+x2+", x1 = "+x1+", y2 = "+y2+", y1= "+y1+", dispX = "+dispX+", dispY = "+dispY+", pixelColor = "+pixelColor);
        //int myPixelData[] = imagePixelData.getImagePixels();
        //ArrowCrtr.drawArrow(xGridCenter, yGridCenter, xPos, yPos, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);

        //System.out.println("HOGFilter : Angle = "+myAngle+", Bin Angle = "+anAngle+", totalMag = "+myTotalMag+", xgridcenter = "+xGridCenter +", y grid center = "+yGridCenter+" xGridPos = "+xGridPos+", yGridPos = "+yGridPos+ ", xPos = "+xPos + ", yPos = "+yPos);

        imagePixelData.drawData(myColor, magIndex);
        //imagePixelData.drawData(pixelColor, magIndex);
        //imagePixelData.drawDataViaCondition(pixelColor, magIndex);

        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }    
   /*
    public void drawData(int[] myGrayValues, int anIndex, int myIGridIndex, int myJGridIndex, double myAngle, int myDscrptrX, int myDscrptrY, int myColor) {
        int anImageWidth = getImageWidth();

        double myTotalMag = hogradients[myIGridIndex][myJGridIndex].getGradientByAngle(myAngle);
        //double myTotalMag = 5;

        int x1 = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        int y1 = ImageTool.rtrvYPstn(anIndex, anImageWidth);

        int xGridCenter = (int) (myIGridIndex * gridSize + .5 * gridSize + 1);
        int yGridCenter = (int) (myJGridIndex * gridSize + .5 * gridSize + 1);

        //System.out.println("HOGFeatureFilter : xGridCenter = "+xGridCenter+", yGridCenter = "+yGridCenter);
        double anAngle = hogradients[myIGridIndex][myJGridIndex].computeBinAngle(myAngle);
        int xGridPos = (int) (myTotalMag * Math.cos(anAngle));
        int yGridPos = (int) (myTotalMag * Math.sin(anAngle));

        //System.out.println("mag = "+myTotalMag+", angle = "+anAngle);
        int xPos = myDscrptrX + xGridCenter + xGridPos;
        int yPos = myDscrptrY + yGridCenter - yGridPos;

        int pixelColor = 0x0000ff00;
        if (myTotalMag < 1) {
            pixelColor = 0x000000ff;
        } else {
            pixelColor = 0x000000ff;
        }

        //int centerIndex = ImageTool.rtrvIndex(xGridCenter, yGridCenter, anImageWidth);
        int magIndex = ImageTool.rtrvIndex(xPos, yPos, anImageWidth);
        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());ss
        //System.out.println("SADCornerRctfdFltr: index = "+myIndex+", matchingIndex = "+matchingIndex+", imgWidth = "+imgWidth+", x2 = "+x2+", x1 = "+x1+", y2 = "+y2+", y1= "+y1+", dispX = "+dispX+", dispY = "+dispY+", pixelColor = "+pixelColor);
        //int myPixelData[] = imagePixelData.getImagePixels();
        //ArrowCrtr.drawArrow(xGridCenter, yGridCenter, xPos, yPos, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);

        //System.out.println("HOGFilter : Angle = "+myAngle+", Bin Angle = "+anAngle+", totalMag = "+myTotalMag+", xgridcenter = "+xGridCenter +", y grid center = "+yGridCenter+" xGridPos = "+xGridPos+", yGridPos = "+yGridPos+ ", xPos = "+xPos + ", yPos = "+yPos);

        imagePixelData.drawData(myColor, magIndex);
        //imagePixelData.drawData(pixelColor, magIndex);
        //imagePixelData.drawDataViaCondition(pixelColor, magIndex);

        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }*/

    public double compute(HOGPosition myHOGPosition, ArrayList myHOGArrayList2) {
        double ecldnDstnceSqrd = 0;
        double ecldnDstnce = Double.MAX_VALUE;
        double ecldnDstnceTmp = 0;
        double minEcldnDstnce = 0;
        int matchedIndex = 0;
        int arrayIndex = 0;
        HOGPosition matchedHOGPosition = null;
        int hogPosition1 = myHOGPosition.getPositionIndex();
        //System.out.println("HOGFeatureSqnceFilter : arraylist = "+myHOGArrayList2.size());
        HistogramOG[][] aHOG1 = myHOGPosition.getHog();
        Iterator myIterator = myHOGArrayList2.iterator();
        while (myIterator.hasNext()) {
            HOGPosition aHOGPosition = (HOGPosition) myIterator.next();
            int hogPosition2 = aHOGPosition.getPositionIndex();
            HistogramOG[][] aHOG2 = aHOGPosition.getHog();

            ecldnDstnceSqrd = computeHog1x1BinDiff26(aHOG1, aHOG2);
            ecldnDstnceTmp = Math.sqrt(ecldnDstnceSqrd);

            //System.out.println("HOGFeatureOrientFilter : hogDstnceThrshld = " + hogDstnceThrshld);
            if (ecldnDstnceTmp <= hogDstnceThrshld && ecldnDstnceTmp < ecldnDstnce) {
                matchedHOGPosition = aHOGPosition;
                matchedIndex = aHOGPosition.getPositionIndex();
                ecldnDstnce = ecldnDstnceTmp;
            }
            //System.out.println("HOGFeatureSqnceFilter: HOGPosition1 = "+hogPosition1+", hogPosition2 = "+hogPosition2+", ecldnDstnceTmp = "+ecldnDstnceTmp+", hogDstnceThrshld = "+hogDstnceThrshld+" , ecldnDstnce = "+ecldnDstnce);
        }

        //ecldnDstnce = Math.sqrt(ecldnDstnceSqrd);
        //System.out.println("HOGFeatureSqnceFltr: ecldnDstnce = "+ecldnDstnce+", hogDstnceThrshld = "+hogDstnceThrshld);
        if (ecldnDstnce <= hogDstnceThrshld && matchedHOGPosition != null) {
            //(myHOGPosition, matchedHOGPosition);
        }
        return ecldnDstnce;
    }

    //Remove matches for faster enumeration
    public double computeHOG1x1(HOGPosition myHOGPosition, ArrayList myHOGArrayList2) {
        double ecldnDstnceSqrd = 0;
        double ecldnDstnce = Double.MAX_VALUE;
        double ecldnDstnceTmp = 0;
        double minEcldnDstnce = 0;
        int matchedIndex = 0;
        int arrayIndex = 0;
        HOGPosition matchedHOGPosition = null;
        int hogPosition1 = myHOGPosition.getPositionIndex();
        //System.out.println("HOGFeatureSqnceFilter : arraylist = "+myHOGArrayList2.size());
        HistogramOG[][] aHOG1 = myHOGPosition.getHog();
        Iterator myIterator = myHOGArrayList2.iterator();
        //System.out.println("HOGPosition "+myHOGPosition.getPositionIndex()+", "+myHOGPosition.getGridID()+", "+myHOGPosition.getX()+", "+myHOGPosition.getY());
        while (myIterator.hasNext()) {
            HOGPosition aHOGPosition = (HOGPosition) myIterator.next();
            int hogPosition2 = aHOGPosition.getPositionIndex();
            HistogramOG[][] aHOG2 = aHOGPosition.getHog();

            ecldnDstnceSqrd = computeHog1x1BinDiff(aHOG1, aHOG2);
            ecldnDstnceTmp = Math.sqrt(ecldnDstnceSqrd);

            //System.out.println("HOGFeatureOrientFilter : hogDstnceThrshld = "+hogDstnceThrshld);
            //System.out.println("HOGPosition2 "+aHOGPosition.getPositionIndex()+", "+aHOGPosition.getGridID()+", "+aHOGPosition.getX()+", "+aHOGPosition.getY()); 
            //System.out.println("HOGCornerDetector: HOGPosition1 = "+hogPosition1+", hogPosition2 = "+hogPosition2+", ecldnDstnceTmp = "+ecldnDstnceTmp+", hogDstnceThrshld = "+hogDstnceThrshld+" , ecldnDstnce = "+ecldnDstnce);            
            if (ecldnDstnceTmp <= hogDstnceThrshld && ecldnDstnceTmp < ecldnDstnce) {
                matchedHOGPosition = aHOGPosition;
                matchedIndex = aHOGPosition.getPositionIndex();
                ecldnDstnce = ecldnDstnceTmp;
            }
            //System.out.println("HOGFeatureSqnceFilter: HOGPosition1 = "+hogPosition1+", hogPosition2 = "+hogPosition2+", ecldnDstnceTmp = "+ecldnDstnceTmp+", hogDstnceThrshld = "+hogDstnceThrshld+" , ecldnDstnce = "+ecldnDstnce);
        }
        //ecldnDstnce = Math.sqrt(ecldnDstnceSqrd);
        //System.out.println("HOGFeatureSqnceFltr: ecldnDstnce = "+ecldnDstnce+", hogDstnceThrshld = "+hogDstnceThrshld);
       
        if (matchedHOGPosition != null) {
            //TODO : could check distance prior to comparing HOG euclidian distance
            double aPixelDistance = HOGPosition.computePixelDistance(matchedHOGPosition, myHOGPosition, getImageWidth());
            if (aPixelDistance < lclPntTrnsltnDstnceThrshld) {
                //System.out.println("HOGCornerDetector: pixeldistance = "+aPixelDistance+", threshold = "+lclPntTrnsltnDstnceThrshld);
                //hogMatchHashMap.put(matchedHOGPosition, myHOGPosition);
                isMatch = true;//TODO: Should convert to object oriented approach HOGPosition.isMatch !!!
                //matchedHOGPosition.connectHOGMatch(myHOGPosition);
                myHOGPosition.connectHOGMatch(matchedHOGPosition);
                //matchIndexAL.add(new Integer(matchedIndex));
                //HOGPosition aHOGPosition2 = (HOGPosition) myHOGArrayList2.get(matchedIndex);                
                drawHOGArrow(matchedHOGPosition,myHOGPosition );
            }
        }        
        return ecldnDstnce;
    }    
    public void drawHOGArrow(HOGPosition matchedHOGPosition,HOGPosition myHOGPosition ){
         //(matchedHOGPosition,myHOGPosition );
    }    
    //Move these functions to HOGPosition or HistogramOG
    private double computeHogBinDiff(HistogramOG myHOG1, HistogramOG myHOG2, int myIndex1, int myIndex2) {
        double hogDiff1 = myHOG1.getGradientByIndex(myIndex1);
        double hogDiff2 = myHOG2.getGradientByIndex(myIndex2);
        double hogDiff = hogDiff1 - hogDiff2;
        //System.out.println("HOGFeatureSqnceFltr : hogDiff1 = "+hogDiff1+", hogDiff2 = "+hogDiff2+", hogDiff = "+hogDiff);
        return hogDiff;
    }
    /*
     public double computeHog1x1BinDiff26(HistogramOG myHOG1, HistogramOG myHOG2) {
     double hogDiff0 = computeHogBinDiff(myHOG1, myHOG2, 0, 0);
     double sumHOGDiff = hogDiff0 * hogDiff0;
     //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
     return sumHOGDiff;
     }*/

    public double computeHog1x1BinDiff26(HistogramOG myHOG1, HistogramOG myHOG2) {
        double sumHOGDiff = 0;
        int hog1MaxIndex = myHOG1.getMaxGradientBinIndex();
        int hog2MaxIndex = myHOG2.getMaxGradientBinIndex();
        int hogIndexDiff = hog2MaxIndex - hog1MaxIndex;
        int i2 = 0;

        for (int i = 0; i < 26; i++) {
            i2 = i + hogIndexDiff;
            if (i2 >= 26) {
                i2 = i2 - 26;
            } else if (i2 < 0) {
                i2 = 26 + i2;
            }
            sumHOGDiff += computeHogBinDiff(myHOG1, myHOG2, i, i2);
        }
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }
    public double compute8BinDiff(HistogramOG myHOG1, HistogramOG myHOG2) {
        double hogDiff0 = computeHogBinDiff(myHOG1, myHOG2, 0, 0);
        double hogDiff1 = computeHogBinDiff(myHOG1, myHOG2, 1, 1);
        double hogDiff2 = computeHogBinDiff(myHOG1, myHOG2, 2, 2);
        double hogDiff3 = computeHogBinDiff(myHOG1, myHOG2, 3, 3);
        double hogDiff4 = computeHogBinDiff(myHOG1, myHOG2, 4, 4);
        double hogDiff5 = computeHogBinDiff(myHOG1, myHOG2, 5, 5);
        double hogDiff6 = computeHogBinDiff(myHOG1, myHOG2, 6, 6);
        double hogDiff7 = computeHogBinDiff(myHOG1, myHOG2, 7, 7);
        double sumHOGDiff = hogDiff0 * hogDiff0 + hogDiff1 * hogDiff1 + hogDiff2 * hogDiff2 + hogDiff3 * hogDiff3
                + hogDiff4 * hogDiff4 + hogDiff5 * hogDiff5 + hogDiff6 * hogDiff6 + hogDiff7 * hogDiff7;
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }

    public double compute(HistogramOG myHOG1[][], HistogramOG myHOG2[][]) {
        double hogDiff00 = compute8BinDiff(myHOG1[0][0], myHOG2[0][0]);
        double hogDiff01 = compute8BinDiff(myHOG1[0][1], myHOG2[0][1]);
        double hogDiff02 = compute8BinDiff(myHOG1[0][2], myHOG2[0][2]);
        double hogDiff10 = compute8BinDiff(myHOG1[1][0], myHOG2[1][0]);
        double hogDiff11 = compute8BinDiff(myHOG1[1][1], myHOG2[1][1]);
        double hogDiff12 = compute8BinDiff(myHOG1[1][2], myHOG2[1][2]);
        double hogDiff20 = compute8BinDiff(myHOG1[2][0], myHOG2[2][0]);
        double hogDiff21 = compute8BinDiff(myHOG1[2][1], myHOG2[2][1]);
        double hogDiff22 = compute8BinDiff(myHOG1[2][2], myHOG2[2][2]);

        double sumHOGDiff = hogDiff00 + hogDiff01 + hogDiff02 + hogDiff10 + hogDiff11 + hogDiff12 + hogDiff20 + hogDiff21 + hogDiff22;
        //System.out.println("HOGFeatureSqnceFilter : "+sumHOGDiff);
        return sumHOGDiff;
    }

    public double computeHog1x1BinDiff26(HistogramOG myHOG1[][], HistogramOG myHOG2[][]) {
        double hogDiff00 = computeHog1x1BinDiff26(myHOG1[0][0], myHOG2[0][0]);
        double sumHOGDiff = hogDiff00 * hogDiff00;
        return sumHOGDiff;
    }

    public double computeHog1x1BinDiff(HistogramOG myHOG1[][], HistogramOG myHOG2[][]) {
        double hogDiff00 = compute8BinDiff(myHOG1[0][0], myHOG2[0][0]);
        double sumHOGDiff = hogDiff00 * hogDiff00;
        return sumHOGDiff;
    }
    //TODO: Convert to object oriented approach HOGPosition.getFeatureMatchIndex ?!!!
    public int getFatureMatchIndex() {
        return fatureMatchIndex;
    }
    //TODO: Convert to object oriented approach HOGPosition.setFatureMatchIndex ?!!!
    public void setFatureMatchIndex(int fatureMatchIndex) {
        this.fatureMatchIndex = fatureMatchIndex;
    }

    public boolean getFeatureSearch() {
        return featureSearch;
    }

    public void setFeatureSearch(boolean myFeatureSearch) {
        featureSearch = myFeatureSearch;
    }
    public ArrayList getHOGArrayListPast() {
        return localHogArrayListPast;
    }
    /*
    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }*/
    public void finish() {
        super.finish();
        localHogArrayListPast = hogArrayList;
    }
}