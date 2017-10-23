/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;

/**
 * @author finitesystem
 */
public class HOGFilter extends GrdntFilter {

    private ImageDrawData imagePixelData;
    private int gridSize;
    private int nmbrXBlocks;
    private int nmbrYBlocks;
    private HistogramOG hog[][];

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        gridSize = 27;

        nmbrXBlocks = myImageWidth / gridSize + 1;

        nmbrYBlocks = myImageHeight / gridSize + 1;

        hog = HistogramOG.retrieveHOGGrid(nmbrXBlocks, nmbrYBlocks);
        //imagePixelData = new ImageDrawData(myImageWidth, myImageHeight);
        imagePixelData = getImagePixelData();
        initializeHOG();
    }
    /*
     public void histogramInsert3x3(int myGrayValues[], int anIndex) {
     if (!isInBounds3x3(anIndex)) {
     return;
     }
     int aWidth = getImageWidth();

     histogramInsert(myGrayValues, anIndex - aWidth - 1);
     histogramInsert(myGrayValues, anIndex - aWidth);
     histogramInsert(myGrayValues, anIndex - aWidth + 1);
     histogramInsert(myGrayValues, anIndex - 1);
     histogramInsert(myGrayValues, anIndex);
     histogramInsert(myGrayValues, anIndex + 1);
     histogramInsert(myGrayValues, anIndex + aWidth - 1);
     histogramInsert(myGrayValues, anIndex + aWidth);
     histogramInsert(myGrayValues, anIndex + aWidth + 1);
     }*/

    public void initializeHOG() {
        for (int i = 0; i < nmbrXBlocks; i++) {
            for (int j = 0; j < nmbrYBlocks; j++) {
                hog[i][j] = new HistogramOG();
            }
        }
    }
    public void filter(int grayValues[], int i) {
        //imagePixelData.updatePixels(grayValues, i);
        super.grdntFilter(grayValues, i);
        if (isInBounds3x3(i)) {
            histogramInsert(grayValues, i);
            drawData(grayValues, i);
        }
    }
    public void histogramInsert27x27(int myGrayValues[], int anIndex) {
        int aWidthx3 = 9 * getImageWidth();
        histogramInsert9x9(myGrayValues, anIndex - aWidthx3 - 9);
        histogramInsert9x9(myGrayValues, anIndex - aWidthx3);
        histogramInsert9x9(myGrayValues, anIndex - aWidthx3 + 9);
        histogramInsert9x9(myGrayValues, anIndex - 9);
        histogramInsert9x9(myGrayValues, anIndex);
        histogramInsert9x9(myGrayValues, anIndex + 9);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx3 - 9);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx3);
        histogramInsert9x9(myGrayValues, anIndex + aWidthx3 + 9);
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
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();
        int x = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        int y = ImageTool.rtrvYPstn(anIndex, anImageWidth);
        int iGridIndex = x / (gridSize);
        int jGridIndex = y / (gridSize);
        double myAngle = getOrntnAngle(anIndex);
        double myMagnitude = getGrdntMgntd(anIndex);

        if(iGridIndex == 12 && jGridIndex == 7){
          System.out.println("HOGFilter gridSize = " + gridSize + ", iGridIndex = " + iGridIndex + ", jGridIndex = " + jGridIndex + ", x = " + x + ", y = " + y+", angle = "+myAngle+", magnitude = "+myMagnitude);       
        }
        hog[iGridIndex][jGridIndex].insert(myAngle, myMagnitude);        

    }

    public void drawData(int myGrayValues[], int anIndex) {
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();
        int x = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        int y = ImageTool.rtrvYPstn(anIndex, anImageWidth);
        int iGridIndex = x / (gridSize);
        int jGridIndex = y / (gridSize);

        //if(x ==iGridIndex * gridSize || x == iGridIndex * gridSize+gridSize-1||y ==jGridIndex * gridSize || y == jGridIndex * gridSize+gridSize-1)
          //  {
           // imagePixelData.drawData(0x00ffff00,x,y);                        
            //}
        
        //if(!(iGridIndex == 12 && jGridIndex== 7)) return;
                
        double myAngle = getOrntnAngle(anIndex);

        double myTotalMag = hog[iGridIndex][jGridIndex].getGradientByAngle(myAngle);

        //int x1 = ImageTool.rtrvXPstn(anIndex, anImageWidth);
        //int y1 = ImageTool.rtrvYPstn(anIndex, anImageWidth);

        int xGridCenter = (int) (iGridIndex * gridSize + .5 * gridSize + .5);
        int yGridCenter = (int) (jGridIndex * gridSize + .5 * gridSize + .5);

        double anAngle = hog[iGridIndex][jGridIndex].computeBinAngle(myAngle);
        int xGridPos = (int) (myTotalMag * Math.cos(anAngle));
        int yGridPos = (int) (myTotalMag * Math.sin(anAngle));

        int xPos = xGridCenter + xGridPos;
        int yPos = yGridCenter - yGridPos;

        int hogIndex = ImageTool.rtrvIndex(xPos, yPos, anImageWidth);
        int pixelColor = 0x00ff0000;
        //int centerIndex = ImageTool.rtrvIndex(xGridCenter, yGridCenter, anImageWidth);
        int centerIndex = ImageTool.rtrvIndex(xPos, yPos, anImageWidth);
        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());ss
        //System.out.println("SADCornerRctfdFltr: index = "+myIndex+", matchingIndex = "+matchingIndex+", imgWidth = "+imgWidth+", x2 = "+x2+", x1 = "+x1+", y2 = "+y2+", y1= "+y1+", dispX = "+dispX+", dispY = "+dispY+", pixelColor = "+pixelColor);
        //int myPixelData[] = imagePixelData.getImagePixels();
        //ArrowCrtr.drawArrow(xGridCenter, yGridCenter, xPos, yPos, myPixelData, anImageWidth, anImageHeight, 0x0050e60d, 0x0050e60d);
        
        //System.out.println("HOGFilter : x = "+x+", y = "+y+", Angle = "+myAngle+", Bin Angle = "+anAngle+", totalMag = "+myTotalMag+", xgridcenter = "+xGridCenter +", y grid center = "+yGridCenter+" xGridPos = "+xGridPos+", yGridPos = "+yGridPos+ ", xPos = "+xPos + ", yPos = "+yPos);
        
        imagePixelData.drawData(pixelColor, hogIndex);
            

        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }

    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }
    public void finish() {
        super.finish();
        //System.out.println("GrdntFilter: albedo = "+rtrvExpcttn());
    }
}