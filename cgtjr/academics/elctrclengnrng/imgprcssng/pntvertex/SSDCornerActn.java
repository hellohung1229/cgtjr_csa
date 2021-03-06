/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.video.SSDCornerFltr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.elmnt.NodePnts;

/**
 *
 * @author clayton g thomas jr
 */
public class SSDCornerActn extends GlblIndxDrwActn {

    private SSDCornerFltr crrltnCornerFltr;
    private Pnt leftSamplePnt;
    private Pnt rightSamplePnt;
    private int frameNmbr;
    private int error = 100;
    private int sampleHeight1 = 220;
    private int sampleHeight2 = 220;
    private int sampleHeight3 = 220;
    private boolean foundSamplePnt1;
    private boolean foundSamplePnt2;
    private int yWindow1 = 30;
    private int yWindow2 = 15;
    private int xWindow = 30;
    private double anEigenValue1;
    private double anEigenValue2;
    private double eigenValue3;
    private double eigenValue4;

    public SSDCornerActn(SSDCornerFltr myCrrltnCornerFltr) {
        crrltnCornerFltr = myCrrltnCornerFltr;
    }

    /*
     * public void setImgPxlData(int myImgPixels[], int myImgWidth, int
     * myImgHeight) { super.setImgPixels(myImgPixels);
     * super.setImgDtWdth(myImgWidth); super.setImgDtHght(myImgHeight);
     * frameNmbr++;
    }
     */

    public void setImgPixels(int myImgPixels[]) {
        super.setImgPixels(myImgPixels);
        crrltnCornerFltr.finish();
        frameNmbr++;
        //crrltnCornerFltr.setCornerData(myImgPixels);
    }
    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //updateCornerPixels(aPoint);
        //updateCornerPixels(aPoint);
        crrltCorners(aPoint);
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
        if (frameNmbr % 2 == 1) {
            updateSamplePnts(myPnt);
        } else {
            compareCorners(myPnt);
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

        if (foundSamplePnt1 == false) {
            return;
        }
        int aWidth = getImgDtWdth();
        int myX1 = (int) leftSamplePnt.getX1();
        int myY1 = (int) leftSamplePnt.getY1();

        int myX2 = (int) myPnt.getX1();
        int myY2 = (int) myPnt.getY1();

        int myIndex1 = ImageTool.rtrvIndex(myX1, myY1, aWidth);
        int myIndex2 = ImageTool.rtrvIndex(myX2, myY2, aWidth);

        //crrltnCornerFltr.cornerDetect9x9(getImgPixels(), myIndex1);
        //double eigenValue1 = crrltnCornerFltr.getEigenValue(myIndex1);

        crrltnCornerFltr.cornerDetect9x9(getImgPixels(), myIndex2);
        double eigenValue2 = crrltnCornerFltr.getEigenValue(myIndex2);
        double eigenValue3 = crrltnCornerFltr.getEigenValue1(myIndex2);
        double eigenValue4 = crrltnCornerFltr.getEigenValue2(myIndex2);

        double anError = crrltnCornerFltr.cmptSSD27x27(myIndex1, myIndex2);
        if (myY2 >= myY1 - yWindow2 && myY2 <= myY1 + yWindow1) {
            System.out.println("attempt left match e1 = " + anEigenValue1 + ", e2 = " + eigenValue2 + ", x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2 + ", anError = " + anError);
            System.out.println("attempt left match eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4 + ", x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2 + ", anError = " + anError);
        }
        if (anError <= error) {
            System.out.println("left match x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2);
        }
    }
    public void compareRight(Pnt myPnt) {
        if (foundSamplePnt2 == false) {
            return;
        }
        int aWidth = getImgDtWdth();
        int myX1 = (int) rightSamplePnt.getX1();
        int myY1 = (int) rightSamplePnt.getY1();

        int myX2 = (int) myPnt.getX1();
        int myY2 = (int) myPnt.getY1();

        int myIndex1 = ImageTool.rtrvIndex(myX1, myY1, aWidth);
        int myIndex2 = ImageTool.rtrvIndex(myX2, myY2, aWidth);

        //crrltnCornerFltr.cornerDetect9x9(getImgPixels(), myIndex1);
        //double eigenValue1 = crrltnCornerFltr.getEigenValue(myIndex1);

        crrltnCornerFltr.cornerDetect9x9(getImgPixels(), myIndex2);
        double eigenValue2 = crrltnCornerFltr.getEigenValue(myIndex2);

        //double eigenValue2 = crrltnCornerFltr.getEigenValue(myIndex2);
        double eigenValue3 = crrltnCornerFltr.getEigenValue1(myIndex2);
        double eigenValue4 = crrltnCornerFltr.getEigenValue2(myIndex2);
        double anError = crrltnCornerFltr.cmptSSD27x27(myIndex1, myIndex2);

        if (myY2 >= myY1 - yWindow2 && myY2 <= myY1 + yWindow1) {
            System.out.println("attempt right match e1 = " + anEigenValue2 + ", e2 = " + eigenValue2 + ", x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2 + ", anError = " + anError);
            System.out.println("attempt right match eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4 + ", x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2 + ", anError = " + anError);
        }
        if (anError <= error) {
            System.out.println("right match x1 = " + myX1 + ", y1 = " + myY1 + ", x2 = " + myX2 + ", y2 = " + myY2);
        }
    }

    public void updateSamplePnts(Pnt myPnt) {
        int aHeight = (int) myPnt.getY1();
        //System.out.println("CrrltnCornerActn: framenmbr = "+frameNmbr+", x = "+myPnt.getX1()+", y = "+myPnt.getY1());
        int x1 = (int) myPnt.getX1();
        int y1 = (int) myPnt.getY1();
        int width = getImgDtWdth();
        int height = getImgDtHght();
        int index = ImageTool.rtrvIndex(x1, y1, width);
        crrltnCornerFltr.cornerDetect9x9(getImgPixels(), index);

        System.out.println("CrrltnCornerActn: x1 = " + x1 + ", y1 = " + y1 + ", eigenValue = " + anEigenValue1);
        if (NodePnts.isLeftEdge(myPnt) && (aHeight == sampleHeight1 || aHeight == sampleHeight2 || aHeight == sampleHeight3)) {
            leftSamplePnt = myPnt;
            anEigenValue1 = crrltnCornerFltr.getEigenValue(index);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", left x = " + leftSamplePnt.getX1() + ", eigenValue = " + anEigenValue1);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", left y = " + leftSamplePnt.getY1() + ", eigenValue = " + anEigenValue1);
            eigenValue3 = crrltnCornerFltr.getEigenValue1(index);
            double eigenValue4 = crrltnCornerFltr.getEigenValue2(index);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4);
            foundSamplePnt1 = true;
        }
        if (NodePnts.isRightEdge(myPnt) && (aHeight == sampleHeight1 || aHeight == sampleHeight2 || aHeight == sampleHeight3)) {
            rightSamplePnt = myPnt;
            foundSamplePnt2 = true;
            anEigenValue2 = crrltnCornerFltr.getEigenValue(index);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", right x = " + rightSamplePnt.getX1() + ", eigenValue = " + anEigenValue2);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", right y = " + rightSamplePnt.getY1() + ", eigenValue = " + anEigenValue2);
            eigenValue3 = crrltnCornerFltr.getEigenValue1(index);
            double eigenValue4 = crrltnCornerFltr.getEigenValue2(index);
            System.out.println("CrrltnCornerActn:framenumber = " + frameNmbr + ", eigenValue3 = " + eigenValue3 + ", eigenValue4 = " + eigenValue4);
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
}