/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class GridDrawActn implements PntInsrtActn {

    private int width;
    private int height;
    private int imgPixels[];

    public void setImgPxlData(int myImgPixels[], int myImgWidth, int myImgHeight) {
        imgPixels = myImgPixels;
        width = myImgWidth;
        height = myImgHeight;
    }

    public void setImgPixels(int myImgPixels[]) {
        imgPixels = myImgPixels;
    }

    public int[] getImgPixels() {
        return imgPixels;
    }

    public void updateMesh(ShapePnt myShapePnt) {
        Vector quadVector = myShapePnt.getQuadElmnts();
        drawMesh(quadVector);
    }

    public void drawMesh(Vector myVector) {
        QuadPnts smQuadPnts;
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Pnt aPnt3 = null;
        Pnt aPnt4 = null;

        int size = myVector.size();
        //System.out.println("PntPxlInsrtActn: size = "+size);
        for (int i = 0; i < size; i++) {
            smQuadPnts = (QuadPnts) myVector.elementAt(i);
            aPnt1 = smQuadPnts.getPoint0();
            aPnt2 = smQuadPnts.getPoint1();
            aPnt3 = smQuadPnts.getPoint2();
            aPnt4 = smQuadPnts.getPoint3();

            LineCrtr.drawLine(aPnt1, aPnt2, imgPixels, width, height);
            LineCrtr.drawLine(aPnt2, aPnt3, imgPixels, width, height);
            LineCrtr.drawLine(aPnt3, aPnt4, imgPixels, width, height);
            LineCrtr.drawLine(aPnt4, aPnt1, imgPixels, width, height);
            //LineCrtr.drawTest(imgPixels, width, height);
        }
    }

    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nodeCreateAction(Pnt aPoint, Pnt myNodeCmpltePnt, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
        //update(aPoint);
        //update(myPoint4);
        //update(myPoint6);
        //System.out.println("PntPxlInsrtActn: test 1");
        //update(myPoint7);
        drawLines(aPoint, myNodeCmpltePnt, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
        //myPoint7.setColor(0xff0000);
    }

    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //update(myPoint7);
        //aPoint.setColor(0x0000ff);
        //update(aPoint);
        //update(myPoint7);
        LineCrtr.drawLine(aPoint, myPoint7, imgPixels, width, height);
        //update(myPoint7);
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //aPoint.setColor(0x000000ff);

        //update(aPoint);        
        //update3x3(aPoint, 0x000000ff);
    }

    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //update(aPoint);
        //update(myPoint4);
        //update(myPoint6);
        //update(myPoint7);        
        drawLines(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);

        //myPoint7.setColor(0xff0000);
    }

    public void update(Pnt myPoint7) {

        //TODO: look for number formats
        int aX = (int) myPoint7.getX1();
        int aY = (int) myPoint7.getY1();
        //int aColor = myPoint7.getColor();
        //int aColor = myPoint7.getColor();       

        //int anIndex = ImageTool.rtrvIndex(aX, aY, width);
        //int aColor = imgPixels[anIndex];
        //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor+", index = "+anIndex);
        //ImageTool.updtImgPixel(anIndex, 0x0000ff00, imgPixels);
        //myPoint7.setColor(aColor);
    }

    public void update3x3(Pnt myPoint7, int myColor) {
        int aX = (int) myPoint7.getX1();
        int aY = (int) myPoint7.getY1();
        int aColor = myPoint7.getColor();
        int anIndex = ImageTool.rtrvIndex(aX, aY, width);
        //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
        try {
            ImageTool.updtImgPixel(anIndex - width - 1, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex - width, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex - width + 1, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex - 1, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex + 1, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex + width - 1, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex + width, aColor, imgPixels);
            ImageTool.updtImgPixel(anIndex + width + 1, aColor, imgPixels);
        } catch (ArrayIndexOutOfBoundsException aiobe) {
        }
    }

    public void drawLines(Pnt myIntlPoint, Pnt myNodeCmpltePnt, Pnt aSlctdPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
        LineCrtr.drawLine(myNodeCmpltePnt,myIntlPoint, imgPixels, width, height);        
        LineCrtr.drawLine(myNodeCmpltePnt, aSlctdPoint, imgPixels, width, height);
        //LineCrtr.drawLine(myIntlPoint, myPoint6, imgPixels, width, height);        
        LineCrtr.drawLine(aSlctdPoint, myPoint7, imgPixels, width, height);
        LineCrtr.drawLine(myIntlPoint,myPoint7, imgPixels, width, height);
    }

    public void setImgDtWdth(int myWidth) {
        width = myWidth;
    }

    public void setImgDtHght(int myHeight) {
        height = myHeight;
    }

    public int getImgDtWdth() {
        return width;
    }

    public int getImgDtHght() {
        return height;
    }
}