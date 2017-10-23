/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import static cgtjr.academics.math.geometry.linepnts.LineCrtr.drawLine;

/**
 *
 * @author clayton g thomas jr
 */
public class ArrowCrtr extends LineCrtr {

    private static Pnt thePoint;
    private static float arrowTipHalfAngle = (float) Math.PI / 4;
    private static float arrowTipSideLength = 4;

    public static void drawArrow(float x1, float y1, float x2, float y2, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        drawLine(x1, y1, x2, y2, myData, myWidth, myHeight, myColor1, myColor2);
        if (Math.round(PntTool.getDistance(x1, y1, x2, y2)) > 0) {
            drawArrowTip(x1, y1, x2, y2, myData, myWidth, myHeight, myColor1, myColor2);
        }
    }    

    public static void drawArrow(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight, int myColor) {
        drawLine(aPoint1, aPoint2, myData, myWidth, myHeight, myColor);
        if (Math.round(PntTool.getDistance(aPoint1, aPoint2)) > 0) {
            drawArrowTip(aPoint1, aPoint2, myData, myWidth, myHeight);
        }
    }

    public static void drawArrow(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        drawLine(aPoint1, aPoint2, myData, myWidth, myHeight, myColor1, myColor2);
        if (Math.round(PntTool.getDistance(aPoint1, aPoint2)) > 0) {
            drawArrowTip(aPoint1, aPoint2, myData, myWidth, myHeight, myColor1, myColor2);
        }
    }

    public static void drawArrowTip(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight) {
        drawArrowTip(aPoint1.getX1(), aPoint1.getY1(), aPoint2.getX1(), aPoint2.getY1(), myData, myWidth, myHeight, aPoint1.getColor(), aPoint2.getColor());
    }

    public static void drawArrowTip(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        drawArrowTip(aPoint1.getX1(), aPoint1.getY1(), aPoint2.getX1(), aPoint2.getY1(), myData, myWidth, myHeight, myColor1, myColor2);
    }

    public static void drawArrowTip(float x1, float y1, float x2, float y2, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        thePoint = new Pnt();

        double i;
        double j;

        float aPnt1X1 = x1;
        float aPnt1Y1 = y1;
        float aPnt2X1 = x2;
        float aPnt2Y1 = y2;

        double xDifference;
        double yDifference;
        double x2MinusX1 = aPnt2X1 - aPnt1X1;
        double y2MinusY1 = aPnt2Y1 - aPnt1Y1;
        double y = 0;
        double x = 0;

        int aColor1 = myColor1;
        int aColor2 = myColor2;
        int colorTime = 0;

        double angle1 = 0;
        double angle2 = 0;

        int arrowTipSideX1 = 0;
        int arrowTipSideY1 = 0;
        int arrowTipSideX2 = 0;
        int arrowTipSideY2 = 0;
        int arrowTipHeight = 0;
        int arrowTipHalfWidth = 0;

        if (x2MinusX1 == 0 && aPnt1Y1 <= aPnt2Y1) {
            angle1 = Math.PI / 2;
            angle2 = angle1 - arrowTipHalfAngle;
            arrowTipHalfWidth = (int) (arrowTipSideLength * Math.cos(angle2));
            arrowTipSideX1 = (int) x1 - arrowTipHalfWidth;
            arrowTipSideX2 = (int) x2 + arrowTipHalfWidth;
            arrowTipHeight = (int) (arrowTipSideLength * Math.sin(angle2));
            arrowTipSideY1 = (int) aPnt2Y1 - arrowTipHeight;
            arrowTipSideY2 = arrowTipSideY1;
            LineCrtr.drawLine(x2, y2, arrowTipSideX1, arrowTipSideY1, myData, myWidth, myHeight, myColor1, myColor2);
            LineCrtr.drawLine(x2, y2, arrowTipSideX2, arrowTipSideY2, myData, myWidth, myHeight, myColor1, myColor2);
        } else if (x2MinusX1 == 0 && aPnt2Y1 <= aPnt1Y1) {
            angle1 = Math.PI / 2;
            angle2 = angle1 - arrowTipHalfAngle;
            arrowTipHalfWidth = (int) (arrowTipSideLength * Math.cos(angle2));
            arrowTipSideX1 = (int) x1 - arrowTipHalfWidth;
            arrowTipSideX2 = (int) x2 + arrowTipHalfWidth;
            arrowTipHeight = (int) (arrowTipSideLength * Math.sin(angle2));
            arrowTipSideY1 = (int) aPnt2Y1 + arrowTipHeight;
            arrowTipSideY2 = arrowTipSideY1;
            LineCrtr.drawLine(x2, y2, arrowTipSideX1, arrowTipSideY1, myData, myWidth, myHeight, myColor1, myColor2);
            LineCrtr.drawLine(x2, y2, arrowTipSideX2, arrowTipSideY2, myData, myWidth, myHeight, myColor1, myColor2);
        } else if (y2MinusY1 == 0 && aPnt1X1 <= aPnt2X1) {
            angle1 = Math.PI;
            angle2 = angle1 - arrowTipHalfAngle;
            arrowTipHeight = (int) (arrowTipSideLength * Math.cos(angle2));
            arrowTipSideX1 = (int) aPnt2X1 - Math.abs(arrowTipHeight);
            arrowTipSideX2 = (int) arrowTipSideX1;
            arrowTipHalfWidth = (int) (arrowTipSideLength * Math.sin(angle2));
            arrowTipSideY1 = (int) aPnt2Y1 - arrowTipHalfWidth;
            arrowTipSideY2 = (int) aPnt2Y1 + arrowTipHalfWidth;
            LineCrtr.drawLine(x2, y2, arrowTipSideX1, arrowTipSideY1, myData, myWidth, myHeight, myColor1, myColor2);
            LineCrtr.drawLine(x2, y2, arrowTipSideX2, arrowTipSideY2, myData, myWidth, myHeight, myColor1, myColor2);
        } else if (y2MinusY1 == 0 && aPnt2X1 <= aPnt1X1) {
            angle1 = Math.PI;
            angle2 = angle1 - arrowTipHalfAngle;
            arrowTipHeight = (int) (arrowTipSideLength * Math.cos(angle2));
            arrowTipSideX1 = (int) aPnt2X1 + Math.abs(arrowTipHeight);
            arrowTipSideX2 = (int) arrowTipSideX1;
            arrowTipHalfWidth = (int) (arrowTipSideLength * Math.sin(angle2));
            arrowTipSideY1 = (int) aPnt2Y1 - arrowTipHalfWidth;
            arrowTipSideY2 = (int) aPnt2Y1 + arrowTipHalfWidth;
            LineCrtr.drawLine(x2, y2, arrowTipSideX1, arrowTipSideY1, myData, myWidth, myHeight, myColor1, myColor2);
            LineCrtr.drawLine(x2, y2, arrowTipSideX2, arrowTipSideY2, myData, myWidth, myHeight, myColor1, myColor2);
        } else {
            angle1 = Math.abs(Math.atan(y2MinusY1 / x2MinusX1));
            angle2 = Math.abs(angle1) + arrowTipHalfAngle;
            arrowTipHeight = (int) (arrowTipSideLength * Math.cos(angle2));
            arrowTipSideX1 = (int) (aPnt2X1 - (x2MinusX1 / Math.abs(x2MinusX1)) * arrowTipHeight);
            arrowTipHeight = (int) (arrowTipSideLength * Math.cos(angle1 - arrowTipHalfAngle));
            arrowTipSideX2 = (int) (aPnt2X1 - (x2MinusX1 / Math.abs(x2MinusX1)) * arrowTipHeight);

            arrowTipHalfWidth = (int) (arrowTipSideLength * Math.sin(angle2));
            arrowTipSideY1 = (int) (aPnt2Y1 - (y2MinusY1 / Math.abs(y2MinusY1)) * arrowTipHalfWidth);
            arrowTipHalfWidth = (int) (arrowTipSideLength * (Math.sin(angle1 - arrowTipHalfAngle)));
            arrowTipSideY2 = (int) (aPnt2Y1 - (y2MinusY1 / Math.abs(y2MinusY1)) * arrowTipHalfWidth);

            LineCrtr.drawLine(x2, y2, arrowTipSideX1, arrowTipSideY1, myData, myWidth, myHeight, myColor1, myColor2);
            LineCrtr.drawLine(x2, y2, arrowTipSideX2, arrowTipSideY2, myData, myWidth, myHeight, myColor1, myColor2);
        }


    }

    public int cmpteArrwTipCntrBsX(int x1, int y1, int x2, int y2) {
        int x = 0;

        if (x1 > x2) {
            x = (int) (0.25 * (x1 - x2) + x2);
        } else if (x1 < x2) {
            x = (int) (-0.25 * (x2 - x1) + x2);
        } else if (x1 == x2) {
            x = x2;
        }
        return x;
    }

    public int cmpteArrwTipCntrBsY(int x1, int y1, int x2, int y2, int myLength) {
        int y = 0;

        if (y2 > y1) {
            y = (int) (-0.25 * (y2 - y1) + y2);
        } else if (y2 < y1) {
            y = (int) (0.25 * (y1 - y2) + y2);
        } else if (y2 == y1) {
            y = y2;
        }                       // System.out.println("-0.25*(y2-y1) = "+(-0.25*(y2-y1))+" y = "+y);
        return y;
    }

    public int cmpteArrwTipSdBsX1(int x1, int y1, int x2, int y2, int x3, int y3, int myLength) {
        double numerator = y2 - y1;
        double denominator = x2 - x1;
        double slope = 0;
        double slope2 = 0;
        int x4 = 0;

        if (denominator != 0) {
            slope = numerator / denominator;
            slope2 = slope * slope;
            x4 = (int) (myLength * Math.sqrt(slope2 / (slope2 + 1)) + x3);
        } else {
            x4 = x3 + myLength;
        }
        return x4;
    }
}
