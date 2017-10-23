/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class ArrowGridDrawActn extends GridDrawActn {

    @Override
    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //super.nodeCmpltAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }

    @Override
    public void setImgPxlData(int[] myImgPixels, int myImgWidth, int myImgHeight) {
        super.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
    }

    public void drawArrow() {
        //ArrowCrtr.drawArrow(null, null, myData, myWidth, myHeight, myColor);
    }

    public void updateMesh(ShapePnt myShapePnt) {
        Vector quadVector = myShapePnt.getQuadElmnts();
        //////////////////!!!!!!check   ... drawArrows(quadVector);
        drawArrowGrid(quadVector);
    }

    public void drawArrowGrid(Vector myQuadVector)
    {
        QuadPnts smQuadPnts;

        int size = myQuadVector.size();
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) myQuadVector.elementAt(i);
            drawMesh(smQuadPnts);
            drawArrows(smQuadPnts);            
        }        
    }
    public void drawMesh(Vector myVector) {
        QuadPnts smQuadPnts;

        int size = myVector.size();
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) myVector.elementAt(i);
            drawMesh(smQuadPnts);
            
        }
    }

    public void drawMesh(QuadPnts smQuadPnts) {
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Pnt aPnt3 = null;
        Pnt aPnt4 = null;

        int imgPixels[] = getImgPixels();
        int width = getImgDtWdth();
        int height = getImgDtHght();
        
        aPnt1 = smQuadPnts.getPoint0();
        aPnt2 = smQuadPnts.getPoint1();
        aPnt3 = smQuadPnts.getPoint2();
        aPnt4 = smQuadPnts.getPoint3();

        
        //if(aPnt1.getColor() != 0x0000ff00)
        LineCrtr.drawLine(aPnt1, aPnt2, imgPixels, width, height);
        //if(aPnt2.getColor() != 0x0000ff00)
        LineCrtr.drawLine(aPnt2, aPnt3, imgPixels, width, height);
        //if(aPnt3.getColor() != 0x0000ff00)
        LineCrtr.drawLine(aPnt3, aPnt4, imgPixels, width, height);
        //if(aPnt4.getColor() != 0x0000ff00)        
        LineCrtr.drawLine(aPnt4, aPnt1, imgPixels, width, height);
        
        //LineCrtr.drawTest(imgPixels, width, height);
    }

    public void drawArrows(Vector myVector) {
        QuadPnts smQuadPnts;

        int size = myVector.size();
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) myVector.elementAt(i);
            drawArrows(smQuadPnts);
        }

    }

    public void drawArrows(QuadPnts smQuadPnts) {
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Pnt aPnt3 = null;
        Pnt aPnt4 = null;

        float xVel1 = 0;
        float yVel1 = 0;
        float xVel2 = 0;
        float yVel2 = 0;
        float xVel3 = 0;
        float yVel3 = 0;
        float xVel4 = 0;
        float yVel4 = 0;

        float pnt1X1 = 0;
        float pnt1Y1 = 0;
        float pnt2X1 = 0;
        float pnt2Y1 = 0;
        float pnt3X1 = 0;
        float pnt3Y1 = 0;
        float pnt4X1 = 0;
        float pnt4Y1 = 0;

        float pnt1X2 = 0;
        float pnt1Y2 = 0;
        float pnt2X2 = 0;
        float pnt2Y2 = 0;
        float pnt3X2 = 0;
        float pnt3Y2 = 0;
        float pnt4X2 = 0;
        float pnt4Y2 = 0;

        int imgPixels[] = getImgPixels();
        int width = getImgDtWdth();
        int height = getImgDtHght();

        aPnt1 = smQuadPnts.getPoint0();
        aPnt2 = smQuadPnts.getPoint1();
        aPnt3 = smQuadPnts.getPoint2();
        aPnt4 = smQuadPnts.getPoint3();
        xVel1 = aPnt1.getNormXVlcty() * aPnt1.getDeltaX() * 8;
        yVel1 = aPnt1.getNormYVlcty() * aPnt1.getDeltaY() * 8;
        xVel2 = aPnt2.getNormXVlcty() * aPnt2.getDeltaX() * 8;
        yVel2 = aPnt2.getNormYVlcty() * aPnt2.getDeltaY() * 8;
        xVel3 = aPnt3.getNormXVlcty() * aPnt3.getDeltaX() * 8;
        yVel3 = aPnt3.getNormYVlcty() * aPnt3.getDeltaY() * 8;
        xVel4 = aPnt4.getNormXVlcty() * aPnt4.getDeltaX() * 8;
        yVel4 = aPnt4.getNormYVlcty() * aPnt4.getDeltaY() * 8;

        pnt1X1 = aPnt1.getX1();
        pnt1Y1 = aPnt1.getY1();
        pnt2X1 = aPnt2.getX1();
        pnt2Y1 = aPnt2.getY1();
        pnt3X1 = aPnt3.getX1();
        pnt3Y1 = aPnt3.getY1();
        pnt4X1 = aPnt4.getX1();
        pnt4Y1 = aPnt4.getY1();

        pnt1X2 = xVel1 + pnt1X1;
        pnt1Y2 = yVel1 + pnt1Y1;
        pnt2X2 = xVel2 + pnt2X1;
        pnt2Y2 = yVel2 + pnt2Y1;
        pnt3X2 = xVel3 + pnt3X1;
        pnt3Y2 = yVel3 + pnt3Y1;
        pnt4X2 = xVel4 + pnt4X1;
        pnt4Y2 = yVel4 + pnt4Y1;

        //drawArrow(pnt1X1, pnt1Y1, pnt1X2, pnt1Y2, imgPixels, width, height, 0x0000ff00, 0x0000ff00);
        //drawArrow(pnt2X1, pnt2Y1, pnt2X2, pnt2Y2, imgPixels, width, height, 0x0000ff00, 0x0000ff00);
        //drawArrow(pnt3X1, pnt3Y1, pnt3X2, pnt3Y2, imgPixels, width, height, 0x0000ff00, 0x0000ff00);
        //drawArrow(pnt4X1, pnt4Y1, pnt4X2, pnt4Y2, imgPixels, width, height, 0x0000ff00, 0x0000ff00);
    }

    public static void drawArrow(float x1, float y1, float x2, float y2, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        LineCrtr.drawLine(x1, y1, x2, y2, myData, myWidth, myHeight, 0x0000ff00, 0x0000ff00);
        ArrowCrtr.drawArrowTip(x1, y1, x2, y2, myData, myWidth, myHeight, 0x0000ff00, 0x0000ff00);
    }
}
