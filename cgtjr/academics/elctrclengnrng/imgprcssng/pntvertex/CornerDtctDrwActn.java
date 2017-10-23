/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.video.OpticalFlowFltr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author clayton g thomas jr
 */
public class CornerDtctDrwActn extends GlblIndxDrwActn {

    private OpticalFlowFltr opticalFlowFltr;

    public CornerDtctDrwActn(OpticalFlowFltr myOpticalFlowFltr) {
        opticalFlowFltr = myOpticalFlowFltr;
    }

    public void setImgPixels(int myImgPixels[]) {
        super.setImgPixels(myImgPixels);
        opticalFlowFltr.setCornerData(myImgPixels);
    }

    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //updateCornerPixels(aPoint);
    }

    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
        updateCornerPixels(myPoint7);
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

    public void updateCornerPixels(Pnt myPnt) {

        int x1 = (int) myPnt.getX1();
        int y1 = (int) myPnt.getY1();
        int width = getImgDtWdth();
        int height = getImgDtHght();
        int index = ImageTool.rtrvIndex(x1, y1, width);
        //double eigenValue = opticalFlowFltr.getEigenValue(index);
        int dataPixels[] = getImgPixels();
        opticalFlowFltr.updtCornerPixels(dataPixels, index);
    }
}