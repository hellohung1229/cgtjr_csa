/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.video.CrrltnCrnrFltr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.TrackerShpPntLstnr;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.NodePnts;
import cgtjr.academics.math.graph.AdjcncyMatrix9x9;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class NCCGridBlckActn extends GlblIndxDrwActn implements TrackerShpPntLstnr {

    private CrrltnCrnrFltr crrltnCornerFltr;
    //private Pnt leftSamplePnt;
    //private Pnt rightSamplePnt;
    private int frameNmbr;
    private float error = 0.65f;
    private int sampleHeight1 = 216;
    private int sampleHeight2 = 220;
    private int sampleHeight3 = 224;
    private boolean foundSamplePnt1;
    private boolean foundSamplePnt2;
    private int yWindow1 = 30;
    private int yWindow2 = 15;
    private int xWindow = 30;
    private ArrayList leftArrayList;
    private ArrayList rightArrayList;
    private TreeSet leftTreeSet;
    private TreeSet rightTreeSet;//Consider order according to eigenvalues
    private double scaleValue = 1;

    public NCCGridBlckActn(CrrltnCrnrFltr myCrrltnCornerFltr) {
        crrltnCornerFltr = myCrrltnCornerFltr;
        leftArrayList = new ArrayList();
        rightArrayList = new ArrayList();
    }

    /*
     * public void setImgPxlData(int myImgPixels[], int myImgWidth, int
     * myImgHeight) { super.setImgPixels(myImgPixels);
     * super.setImgDtWdth(myImgWidth); super.setImgDtHght(myImgHeight);
     * frameNmbr++; }
     */
    public void setImgPixels(int myImgPixels[]) {
        super.setImgPixels(myImgPixels);

        crrltnCornerFltr.finish();
        frameNmbr++;
        //crrltnCornerFltr.setCornerData(myImgPixels);
    }

    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeCmpltAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
        //updateCornerPixels(aPoint);
        //crrltCorners(aPoint);
    }

    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
        //updateCornerPixels(myPoint7);
        //crrltCorners(myPoint7);
    }

    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
        //updateCornerPixels(aPoint);   
        //updateCornerPixels(myPoint7);           
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
        //updateCornerPixels(aPoint);        
    }

    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    public void crrltCorners(Pnt myPnt) {

        //System.out.println("NCCGridBlckActn.compareLeft() : frame number = " + crrltnCornerFltr.getFrameNumber() + ", framenmbr = " + frameNmbr);
        if (frameNmbr > 1) {
            updateSamplePnts(myPnt);
            //compareCorners(myPnt);
        }
    }

    public void compareCorners(Pnt myPnt) {
        if (NodePnts.isLeftEdge(myPnt)) {
            compareLeft(myPnt);
        } else if (NodePnts.isRightEdge(myPnt)) {
            compareRight(myPnt);
        }
    }

    public void compareLeft(Pnt myPnt) {

        double nccValueTmp = 0;
        int aWidth = getImgDtWdth();

        int myX1 = (int) myPnt.getX1();
        int myY1 = (int) myPnt.getY1();

        //int myIndex1 = ImageTool.rtrvIndex(myX1, myY1, aWidth);

        //int imgPixels[] = crrltnCornerFltr.getPrvsData();
        //crrltnCornerFltr.cornerDetect9x9(imgPixels, myIndex1);
        //crrltnCornerFltr.cornerDetect9x9(getImgPixels(), myIndex1);

        //double eigenValue2 = crrltnCornerFltr.getEigenValue(myIndex1);
        //double eigenValue3 = crrltnCornerFltr.getEigenValue1(myIndex1);
        //double eigenValue4 = crrltnCornerFltr.getEigenValue2(myIndex1);
        //System.out.println("NCCCrnrActn.compareLeft(): anEigenValue2 = " + eigenValue2 + ", 10*threshhold = " + scaleValue * crrltnCornerFltr.getThreshold() + ", pnt = " + myPnt + ", index = " + myIndex1);
        //if (eigenValue2 >= scaleValue * crrltnCornerFltr.getThreshold()) {
        //int arraySize = leftArrayList.size();
        //Pnt leftPnt = null;
        //Pnt leftPntTmp = null;
        //for (int i = 0; i < arraySize; i++) {
        //leftPntTmp = (Pnt) leftArrayList.get(i);
        int myLeftX = (int) myPnt.getX1();
        int myLeftY = (int) myPnt.getY1();
        int myLeftIndex = ImageTool.rtrvIndex(myLeftX, myLeftY, aWidth);
        crrltnCornerFltr.cmptNCC27x27Dstnc27x27(myLeftIndex);
        int indexValue = crrltnCornerFltr.getIndexValue();
        float xPos = ImageTool.rtrvXPstn(indexValue, aWidth);
        float yPos = ImageTool.rtrvYPstn(indexValue, aWidth);
        double aNCCValue = crrltnCornerFltr.getNCCValue();
        if (myPnt != null && aNCCValue >= 0.7) {
            ArrowCrtr.drawArrow(myPnt.getX1(), myPnt.getY1(), xPos, yPos, getImgPixels(), getImgDtWdth(), getImgDtHght(), 0x00ffff00, 0x00ffff00);
        }
        //System.out.println("CHECKING MATCH x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myLeftX + ", y2 = " + myLeftY + " ncc value = " + aNCCValue + ", max error = " + error + " !!!");
        //}
        //}
    }

    public void compareRight(Pnt myPnt) {
        //System.out.println("NCCGridBlckActn.compareRight() : frame number = " + crrltnCornerFltr.getFrameNumber());
        double nccValueTmp = 0;
        int aWidth = getImgDtWdth();

        int myX1 = (int) myPnt.getX1();
        int myY1 = (int) myPnt.getY1();
        //int myIndex1 = ImageTool.rtrvIndex(myX1, myY1, aWidth);

        //int imgPixels[] = crrltnCornerFltr.getPrvsData();
        //crrltnCornerFltr.cornerDetect9x9(imgPixels, myIndex1);

        //double eigenValue2 = crrltnCornerFltr.getEigenValue(myIndex1);
        //double eigenValue3 = crrltnCornerFltr.getEigenValue1(myIndex1);
        //double eigenValue4 = crrltnCornerFltr.getEigenValue2(myIndex1);
        //System.out.println("NCCCrnrActn.compareRight(): anEigenValue2 = " + eigenValue2 + ", 2*threshhold = " + scaleValue * crrltnCornerFltr.getThreshold() + ", pnt = " + ", pnt = " + myPnt + ", index = " + myIndex1);

        //if (eigenValue2 >= scaleValue * crrltnCornerFltr.getThreshold()) {
        //int arraySize = rightArrayList.size();
        //Pnt rightPnt = null;
        //Pnt rightPntTmp = null;
        //for (int i = 0; i < arraySize; i++) {
        //rightPntTmp = (Pnt) rightArrayList.get(i);
        int myRightX = (int) myPnt.getX1();
        int myRightY = (int) myPnt.getY1();
        int myRightIndex = ImageTool.rtrvIndex(myRightX, myRightY, aWidth);

        crrltnCornerFltr.cmptNCC27x27Dstnc27x27(myRightIndex);
        int indexValue = crrltnCornerFltr.getIndexValue();
        float xPos = ImageTool.rtrvXPstn(indexValue, aWidth);
        float yPos = ImageTool.rtrvYPstn(indexValue, aWidth);
        double aNCCValue = crrltnCornerFltr.getNCCValue();
        if (myPnt != null && aNCCValue >= 0.7) {
            ArrowCrtr.drawArrow(myPnt.getX1(), myPnt.getY1(), xPos, yPos, getImgPixels(), getImgDtWdth(), getImgDtHght(), 0x00ffff00, 0x00ffff00);
        }
        //}
        //}
    }

    public void updateSamplePnts(Pnt myPnt) {

        double anEigenValue1 = 0;
        double anEigenValue2 = 0;
        double eigenValue3 = 0;
        double eigenValue4 = 0;
        int aHeight = (int) myPnt.getY1();
        //System.out.println("CrrltnCornerActn: framenmbr = "+frameNmbr+", x = "+myPnt.getX1()+", y = "+myPnt.getY1());
        int x1 = (int) myPnt.getX1();
        int y1 = (int) myPnt.getY1();
        myPnt.setColor(0x00ffffff);
        int width = getImgDtWdth();
        int height = getImgDtHght();
        int index = ImageTool.rtrvIndex(x1, y1, width);
        if(x1 == 246 && y1 == 154)
        {
            updateCornerPixels(myPnt);
        }
        //System.out.println("CrrltnCornerActn: x1 = " + x1 + ", y1 = " + y1 + ", eigenValue = " + anEigenValue1);
        //if (NodePnts.isLeftEdge(myPnt)) {
        int aMatrix[][] = AdjcncyMatrix9x9.circuitA4(myPnt);
        
        if (AdjcncyMatrix9x9.isTopLeft(aMatrix) || AdjcncyMatrix9x9.isBottomLeft(aMatrix)) {
            //crrltnCornerFltr.cornerDetect9x9(getImgPixels(), index);
            //crrltnCornerFltr.cornerDetect9x9(index);            
            anEigenValue1 = crrltnCornerFltr.getEigenValue(index);
            //System.out.println("NCCCrnrActn.updateSamplePnts(): anEigenValue1 = " + anEigenValue1 + ", threshhold = " + crrltnCornerFltr.getThreshold());
            //if (anEigenValue1 >= scaleValue * crrltnCornerFltr.getThreshold()) {
            //leftSamplePnt = myPnt;

            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", left x = " + myPnt.getX1() + ", eigenValue = " + anEigenValue1);
            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", left y = " + myPnt.getY1() + ", eigenValue = " + anEigenValue1);
            eigenValue3 = crrltnCornerFltr.getEigenValue1(index);
            eigenValue4 = crrltnCornerFltr.getEigenValue2(index);
            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4);
            foundSamplePnt1 = true;
            //leftArrayList.add(myPnt);
            //updateCornerPixels(myPnt);
            compareLeft(myPnt);

            //}
        }

        //if (NodePnts.isRightEdge(myPnt)) {
        if (AdjcncyMatrix9x9.isTopRight(aMatrix) || AdjcncyMatrix9x9.isBottomRight(aMatrix)) {        
            //crrltnCornerFltr.cornerDetect9x9(index);
            anEigenValue2 = crrltnCornerFltr.getEigenValue(index);
            //System.out.println("NCCCrnrActn.updateSamplePnts(): anEigenValue2 = " + anEigenValue1 + ", threshhold = " + crrltnCornerFltr.getThreshold());
            //if (anEigenValue2 >= scaleValue * crrltnCornerFltr.getThreshold()) {
            //rightSamplePnt = myPnt;
            foundSamplePnt2 = true;

            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", right x = " + myPnt.getX1() + ", eigenValue = " + anEigenValue2);
            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", right y = " + myPnt.getY1() + ", eigenValue = " + anEigenValue2);
            eigenValue3 = crrltnCornerFltr.getEigenValue1(index);
            eigenValue4 = crrltnCornerFltr.getEigenValue2(index);
            //System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4);
            //rightArrayList.add(myPnt);
            //updateCornerPixels(myPnt);
            compareRight(myPnt);
            //}
        }
    }
    public void updateCornerPixels(Pnt myPnt) {

        int x1 = (int) myPnt.getX1();
        int y1 = (int) myPnt.getY1();
        int width = getImgDtWdth();
        int height = getImgDtHght();
        int index = ImageTool.rtrvIndex(x1, y1, width);
        //double eigenValue = crrltnCornerFltr.getEigenValue(index);
        int dataPixels[] = getImgPixels();
        crrltnCornerFltr.updtCornerPixels(dataPixels, index);
        //crrltnCornerFltr.filter(dataPixels,index);
    }
    public void shapePntTrackActn(ShapePnt myShapePnt) {
        Vector aVector = myShapePnt.getNodes();
        crrltCorners(aVector );
        
    }
    public void crrltCorners(Vector myVector ){
        Pnt aPnt = null;
        int aSize = myVector.size();
        for(int i=0;i<aSize;i++)
        {
            aPnt = (Pnt)myVector.get(i);
            crrltCorners(aPnt);
        }
    }
    
}