/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.general.ImageTool;

/**
 *
 * @author clayton g thomas jr
 */
public class LineDraw //extends Line
{

    private int dataValues[];
    private static int x;
    private static int y;

    public static void drawLine(int x1, int y1, int x2, int y2, int myData[], int myWidth, int myHeight,int myColor) {
        int i;
        int j;
        int xDifference;
        int x2MinusX1 = x2 - x1;
        int yTmp = 0;
        //thePoint.setColor(0x0000ce00);
        //System.out.println("LineDraw: x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2 = "+y2);
        if (x2MinusX1 == 0) {
            for (j = y1; j <= y2; j++) {
                y = j;
                x = x1;
                drawPoint(myData, myWidth, myHeight, x, y, myColor);
            }
        } else {
            double slope = (y2 - y1) / (x2 - x1);
            if (x2 >= x1) {
                for (i = x1; i <= x2; i++) {
                    xDifference = i - x1;
                    yTmp = (int) getYCoordinate(slope, x2MinusX1, y1);
                    y = ((int) yTmp);
                    x = ((int) i);
                    drawPoint(myData, myWidth, myHeight, x, y, myColor);
                }
            } else {
                for (i = x1; i >= x2; i--) {
                    xDifference = i - x1;
                    yTmp = (int) getYCoordinate(slope, x2MinusX1, y1);
                    y = ((int) yTmp);
                    x = ((int) i);
                    drawPoint(myData, myWidth, myHeight, x, y, myColor);
                }
            }
        }
    }

    public static double getYCoordinate(double mySlope, double myX, double myB) {
        double y = mySlope * myX + myB;
        return y;
    }

    public static void drawPoint(int myData[], int myWidth, int myHeight, int myX, int myY, int myColor) {
        int aX = (int) myX;
        int aY = (int) myY;
        int myIndex = ImageTool.rtrvIndex(aX, aY, myWidth);
        myData[myIndex] = myColor;
    }
    
    public static void drawRectangle(int myData[], int myWidth, int myHeight, int myX, int myY, int myColor)
    {
        
    }
}