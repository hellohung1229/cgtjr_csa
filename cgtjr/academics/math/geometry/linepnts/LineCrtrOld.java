/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.general.ImageTool;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author clayton g thomas jr
 */
public class LineCrtrOld extends Line {

    private static Pnt thePoint;

    static {
        thePoint = new Pnt();
    }

    public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight) {
        drawLine(aPoint1, aPoint2, myData, myWidth, myHeight, 0x00c8c8c8);
    }

    public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight, int myColor) {
        double i;
        double j;
        double xDifference;
        double yDifference;
        double x2MinusX1 = aPoint2.getX1() - aPoint1.getX1();
        double y2MinusY1 = aPoint2.getY1() - aPoint1.getY1();
        double y = 0;
        double x = 0;
        thePoint.setColor(myColor);

        if (myColor == 0x00ffff00) {
            //System.out.println("LineCrtr: obsPnt = " + aPoint1 + ", maxLinePnt = " + aPoint2 + ", x2MinusX1 = " + x2MinusX1 + ", y2MinusY1 = " + y2MinusY1);
        }
        if (x2MinusX1 == 0 && aPoint1.getY1() <= aPoint2.getY1()) {
            for (j = aPoint1.getY1(); j <= aPoint2.getY1(); j++) {
                thePoint.setY1((float) j);
                thePoint.setX1(aPoint1.getX1());
                drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
            }
        } else if (x2MinusX1 == 0 && aPoint2.getY1() <= aPoint1.getY1()) {
            for (j = aPoint2.getY1(); j <= aPoint1.getY1(); j++) {
                thePoint.setY1((float) j);
                thePoint.setX1(aPoint1.getX1());
                drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
            }
        } else if (y2MinusY1 == 0 && aPoint1.getX1() <= aPoint2.getX1()) {
            for (j = aPoint1.getX1(); j <= aPoint2.getX1(); j++) {
                thePoint.setX1((float) j);
                thePoint.setY1(aPoint1.getY1());
                drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
            }
        } else if (y2MinusY1 == 0 && aPoint2.getX1() <= aPoint1.getX1()) {
            for (j = aPoint2.getX1(); j <= aPoint1.getX1(); j++) {
                thePoint.setX1((float) j);
                thePoint.setY1(aPoint1.getY1());
                drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
            }
        } else if (Math.abs(x2MinusX1) >= Math.abs(y2MinusY1)) {

            double slope = (aPoint2.getY1() - aPoint1.getY1()) / (aPoint2.getX1() - aPoint1.getX1());
            if (myColor == 0x00ffff00) {
                //System.out.println("LineCrtr: x2MinusX1, slope = " + slope);
            }
            if (aPoint2.getX1() >= aPoint1.getX1()) {
                for (i = aPoint1.getX1(); i <= aPoint2.getX1(); i++) {
                    xDifference = i - aPoint1.getX1();
                    y = getYCoordinate(slope, xDifference, aPoint1.getY1());
                    thePoint.setY1((float) y);
                    thePoint.setX1((float) i);
                    if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: i = " + i + ", y = " + y);
                    }
                    drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
                }
            } else {
                for (i = aPoint2.getX1(); i <= aPoint1.getX1(); i++) {
                    xDifference = i - aPoint2.getX1();
                    y = getYCoordinate(slope, xDifference, aPoint2.getY1());
                    thePoint.setY1((float) y);
                    thePoint.setX1((float) i);
                    drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
                }
            }
        } else if (Math.abs(y2MinusY1) > Math.abs(x2MinusX1)) {
            double slope = (aPoint2.getY1() - aPoint1.getY1()) / (aPoint2.getX1() - aPoint1.getX1());
            if (myColor == 0x00ffff00) {
                //System.out.println("LineCrtr: y2MinusY1, slope = " + slope);
            }
            if (aPoint2.getY1() >= aPoint1.getY1()) {
                for (i = aPoint1.getY1(); i <= aPoint2.getY1(); i++) {
                    yDifference = i - aPoint1.getY1();
                    x = getXCoordinate(slope, yDifference, aPoint1.getX1());
                    thePoint.setY1((float) i);
                    thePoint.setX1((float) x);
                    if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: x = " + x + ", y = " + i);
                    }
                    drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
                }
            } else {
                for (i = aPoint2.getY1(); i < aPoint1.getY1(); i++) {
                    yDifference = i - aPoint2.getY1();
                    x = getXCoordinate(slope, yDifference, aPoint2.getX1());
                    thePoint.setY1((float) i);
                    thePoint.setX1((float) x);
                    if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: x = " + x + ", y = " + i);
                    }
                    drawPoint(myData, myWidth, myHeight, (int) thePoint.getX1(), (int) thePoint.getY1(), thePoint.getColor());
                }
            }
        }
    }

    public static double getYCoordinate(double mySlope, double myX, double myB) {
        double y = mySlope * myX + myB;
        return y;
    }

    public static double getXCoordinate(double mySlope, double myY, double myB) {
        double x = myY / mySlope + myB;
        return x;
    }

    public static void drawPoint(int myData[], int myWidth, int myHeight, int myX, int myY, int myColor) {
        int aX = (int) myX;
        int aY = (int) myY;
        int myIndex = ImageTool.rtrvIndex(aX, aY, myWidth);
        try {
            myData[myIndex] = myColor;
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            //System.err.println("LineCrtr: error !!! " + aiobe.getMessage());
        }
    }

    public static void drawLine(TreeMap myTreeMap, int myData[], int myWidth, int myHeight, int myColor) {
        Set aKeySet = myTreeMap.descendingKeySet();
        Iterator anIterator = aKeySet.iterator();
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Integer anInteger = null;

        if (anIterator.hasNext()) {
            anInteger = (Integer) anIterator.next();
            aPnt1 = (Pnt) myTreeMap.get(anInteger);
            //System.out.println("LineCrtr: aPnt1 = "+aPnt1);            
        }
        while (anIterator.hasNext()) {
            anInteger = (Integer) anIterator.next();
            aPnt2 = (Pnt) myTreeMap.get(anInteger);
            //System.out.println("LineCrtr: aPnt2 = "+aPnt2);
            drawLine(aPnt1, aPnt2, myData, myWidth, myHeight, myColor);
            aPnt1 = aPnt2;
        }
    }
    public int interpolate(Pnt myPnt1,Pnt myPnt2,int myIndex)
    {
        int myRGB1 = myPnt1.getColor();
        int myRGB2 = myPnt2.getColor();
        
        int red1 = (myRGB1 >> 16) & 0x000000ff;
        int green1 = (myRGB1 >> 8) & 0x000000ff;
        int blue1 = (myRGB1 & 0x000000ff);        
        
        int red2 = (myRGB2 >> 16) & 0x000000ff;
        int green2 = (myRGB2 >> 8) & 0x000000ff;
        int blue2 = (myRGB2 & 0x000000ff);                
        
       int red = red1 + (red2-red1)*myIndex;
       int green = red1 + (green2-green1)*myIndex;
       int blue = blue1 + (blue2-blue1)*myIndex; 
       
        int rgb = ((red & 0x000000ff) << 16)
                | ((green & 0x000000ff) << 8)
                | ((blue & 0x000000ff));       
        return rgb;
    }
}