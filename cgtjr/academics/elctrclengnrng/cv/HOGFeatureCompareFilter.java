/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author finitesystem
 */
public class HOGFeatureCompareFilter extends CornerDetect {

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
    private ArrayList hogArrayListPast;
    private ArrayList matchIndexAL;
    private double ecldnDstnceThrshld;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        hogArrayList = new ArrayList();

        if (hogArrayListPast == null) {
            hogArrayListPast = new ArrayList();
        }
        matchIndexAL = new ArrayList();
        int aLength = myImageHeight * myImageWidth;
        gridSize = 27;
        descriptors = new Object[aLength];

        nmbrXBlocks = 3;

        nmbrYBlocks = 3;

        hogradients = HistogramOG.retrieveHOGGrid(nmbrXBlocks, nmbrYBlocks);
        //imagePixelData = new ImageDrawData(myImageWidth, myImageHeight);
        imagePixelData = getImageDrawData();
        initializeHOG();
        //ecldnDstnceThrshld = 
    }

    public void initializeHOG() {
        for (int i = 0; i < nmbrXBlocks; i++) {
            for (int j = 0; j < nmbrYBlocks; j++) {
                hogradients[i][j] = new HistogramOG();
            }
        }
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

    public void filter(int grayValues[], int i) {
        int xTemp = ImageTool.rtrvXPstn(i, getImageWidth());
        int yTemp = ImageTool.rtrvYPstn(i, getImageWidth());        
       
        imagePixelData.updatePixels(grayValues, i);
        /*
        int xTemp = ImageTool.rtrvXPstn(i, getImageWidth());
        int yTemp = ImageTool.rtrvYPstn(i, getImageWidth());
        //if(xTemp == 75 && yTemp == 40 ){
        //super.cornerDetect3x3In27x27(grayValues, i);
        */
        if(xTemp % searchWindowSize == 0 && yTemp % searchWindowSize == 0) {
            featureSearch = true;
        }
        if (featureSearch) {
            super.cornerDetect3x3In27x27(grayValues, i);
        }
        if (getEigenValue(i) >= getThreshold()) {
            descriptorsHOG27x27(grayValues, i);
            featureSearch = false;
            //drawData(grayValues, i);
        }
        /*if (xTemp == 75 && yTemp == 40) {
            descriptorsHOG27x27(grayValues, i);
            featureSearch = false;
            //drawData(grayValues, i);
        }*/        
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
    public void descriptorsHOG27x27(int myGrayValues[], int anIndex) {
        hogradients = createHOG3x3();
        featureIndex = anIndex;
        //System.out.println("HOGFeatureFilter : featureIndex = "+featureIndex);
        gridSize = 9;
        int aWidthx14 = 14 * getImageWidth();
        dscrptrOffset = aWidthx14 + 14;
        descriptorWidth = 27;
        histogramInsert27x27(myGrayValues, anIndex);
        //descriptors[descriptorIndex] = hog;        
        //descriptorIndex++;
        HOGPosition aHOGPosition = new HOGPosition(hogradients, anIndex);
        //System.out.println("HOGFeatureSqnceFilter position index = "+aHOGPosition.getPositionIndex());
        hogArrayList.add(aHOGPosition);
        compute(aHOGPosition, hogArrayListPast);
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
        if (!isInBounds3x3(anIndex)) {
            return;
        }
        int aWidth = getImageWidth();

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
    }

    public void histogramInsert(int myGrayValues[], int anIndex) {

        int descriptorStart = featureIndex - dscrptrOffset;

        if (descriptorStart <= 0) {
            return;
        }
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();

        int featureX = ImageTool.rtrvXPstn(featureIndex, anImageWidth);
        int featureY = ImageTool.rtrvYPstn(featureIndex, anImageWidth);
        int featureIndex = ImageTool.rtrvIndex(featureX, featureY, anImageWidth);
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
        double myMagnitudeScaled = myMagnitude / 127;
        //System.out.println("HOGFilter gridSize = "+gridSize+", index = "+anIndex+", featureIndex = " + featureIndex + ", descriptorStart = " + descriptorStart + ", dscrptrOffset = " + dscrptrOffset);
        //System.out.println("HOGFilter iGridIndex = " + iGridIndex + ", jGridIndex = " + jGridIndex + ", x = " + x + ", y = " + y+", dscrptrX = "+dscrptrX+", dscrptrY = "+dscrptrY+", featureX = "+featureX+", featureY = "+featureY);
        //hog[iGridIndex][jGridIndex].insert(myAngle, myMagnitude);
        //myAngle = Math.PI/4;
        //myMagnitude = .5;
        //System.out.println("HOGFeatureFilter magnitude = "+myMagnitude);

        hogradients[iGridIndex][jGridIndex].insert(myAngle, myMagnitudeScaled, 1.0);
        drawData(myGrayValues, anIndex, iGridIndex, jGridIndex, myAngle, dscrptrX, dscrptrY);
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
        //System.out.println("HOGFeatureSqnceFilter : x1 = "+x1+"y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
    }

    public void drawData(int myGrayValues[], int anIndex, int myIGridIndex, int myJGridIndex, double myAngle, int myDscrptrX, int myDscrptrY) {
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

        //imagePixelData.drawData(pixelColor, magIndex);
        imagePixelData.drawDataViaCondition(pixelColor, magIndex);

        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }
    public double compute(HOGPosition myHOGPosition, ArrayList myHOGArrayList2) {
        double ecldnDstnceSqrd = 0;
        double ecldnDstnce = Double.MAX_VALUE;
        double ecldnDstnceTmp = 0;
        double minEcldnDstnce = 0;
        int matchedIndex = 0;
        int arrayIndex = 0;
        HOGPosition matchedHOGPosition = null;
            int hogPosition1 =     myHOGPosition.getPositionIndex();
        //System.out.println("HOGFeatureSqnceFilter : arraylist = "+myHOGArrayList2.size());
        HistogramOG[][] aHOG1 = myHOGPosition.getHog();
        Iterator myIterator = myHOGArrayList2.iterator();
        while (myIterator.hasNext()) {
            HOGPosition aHOGPosition = (HOGPosition) myIterator.next();
            int hogPosition2 =     aHOGPosition.getPositionIndex();
            HistogramOG[][] aHOG2 = aHOGPosition.getHog();

            ecldnDstnceSqrd = compute(aHOG1, aHOG2);
            ecldnDstnceTmp = Math.sqrt(ecldnDstnceSqrd);

            System.out.println("HOGFeatureOrientFilter : ecldnDstnceThrshld = "+ecldnDstnceThrshld);
            if (ecldnDstnceTmp <= ecldnDstnceThrshld && ecldnDstnceTmp < ecldnDstnce) {
                matchedHOGPosition = aHOGPosition;
                matchedIndex = aHOGPosition.getPositionIndex();
                ecldnDstnce = ecldnDstnceTmp;
            }
            //System.out.println("HOGFeatureSqnceFilter: HOGPosition1 = "+hogPosition1+", hogPosition2 = "+hogPosition2+", ecldnDstnceTmp = "+ecldnDstnceTmp+", ecldnDstnceThrshld = "+ecldnDstnceThrshld+" , ecldnDstnce = "+ecldnDstnce);
        }

        //ecldnDstnce = Math.sqrt(ecldnDstnceSqrd);
        //System.out.println("HOGFeatureSqnceFltr: ecldnDstnce = "+ecldnDstnce+", ecldnDstnceThrshld = "+ecldnDstnceThrshld);
        if (ecldnDstnce <= ecldnDstnceThrshld && matchedHOGPosition != null) {
            //matchIndexAL.add(new Integer(matchedIndex));
            //HOGPosition aHOGPosition2 = (HOGPosition) myHOGArrayList2.get(matchedIndex);
            drawArrow(myHOGPosition, matchedHOGPosition);
        }
        return ecldnDstnce;
    }
    private double compute(HistogramOG myHOG1, HistogramOG myHOG2, int myIndex1, int myIndex2) {
        double hogDiff1 = myHOG1.getGradientByIndex(myIndex1);
        double hogDiff2 = myHOG2.getGradientByIndex(myIndex2);
        double hogDiff = hogDiff1 - hogDiff2;
        
        //System.out.println("HOGFeatureSqnceFltr : hogDiff1 = "+hogDiff1+", hogDiff2 = "+hogDiff2+", hogDiff = "+hogDiff);
        return hogDiff;
    }
    public double computeBins8(HistogramOG myHOG1, HistogramOG myHOG2) {
        double hogDiff0 = compute(myHOG1, myHOG2, 0, 0);
        double hogDiff1 = compute(myHOG1, myHOG2, 1, 1);
        double hogDiff2 = compute(myHOG1, myHOG2, 2, 2);
        double hogDiff3 = compute(myHOG1, myHOG2, 3, 3);
        double hogDiff4 = compute(myHOG1, myHOG2, 4, 4);
        double hogDiff5 = compute(myHOG1, myHOG2, 5, 5);
        double hogDiff6 = compute(myHOG1, myHOG2, 6, 6);
        double hogDiff7 = compute(myHOG1, myHOG2, 7, 7);
        double sumHOGDiff = hogDiff0 * hogDiff0 + hogDiff1 * hogDiff1 + hogDiff2 * hogDiff2 + hogDiff3 * hogDiff3
                + hogDiff4 * hogDiff4 + hogDiff5 * hogDiff5 + hogDiff6 * hogDiff6 + hogDiff7 * hogDiff7;
        //System.out.println("HOGFeatureSqnceFilter : sumHOGDiff = "+sumHOGDiff);
        return sumHOGDiff;
    }
    public double compute(HistogramOG myHOG1[][], HistogramOG myHOG2[][]) {
        double hogDiff00 = computeBins8(myHOG1[0][0], myHOG2[0][0]);
        double hogDiff01 = computeBins8(myHOG1[0][1], myHOG2[0][1]);
        double hogDiff02 = computeBins8(myHOG1[0][2], myHOG2[0][2]);
        double hogDiff10 = computeBins8(myHOG1[1][0], myHOG2[1][0]);
        double hogDiff11 = computeBins8(myHOG1[1][1], myHOG2[1][1]);
        double hogDiff12 = computeBins8(myHOG1[1][2], myHOG2[1][2]);
        double hogDiff20 = computeBins8(myHOG1[2][0], myHOG2[2][0]);
        double hogDiff21 = computeBins8(myHOG1[2][1], myHOG2[2][1]);
        double hogDiff22 = computeBins8(myHOG1[2][2], myHOG2[2][2]);

        double sumHOGDiff = hogDiff00 + hogDiff01 + hogDiff02 + hogDiff10 + hogDiff11 + hogDiff12 + hogDiff20 + hogDiff21 + hogDiff22;
        //System.out.println("HOGFeatureSqnceFilter : "+sumHOGDiff);
        return sumHOGDiff;
    }
    public int getFatureMatchIndex() {
        return fatureMatchIndex;
    }
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
        return hogArrayListPast;
    }

    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }

    public void finish() {
        super.finish();
        hogArrayListPast = hogArrayList;
        //System.out.println("GrdntFilter: albedo = "+rtrvExpcttn());
    }
}