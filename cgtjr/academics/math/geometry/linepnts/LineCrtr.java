/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author clayton g thomas jr
 */
public class LineCrtr extends Line {

    //private static Pnt thePoint;

    //TODO: move this into drawLine
    static {
        //thePoint = new Pnt();
    }

    //public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight) {
        //drawLine(aPoint1, aPoint2, myData, myWidth, myHeight, 0x00c8c8c8);
    //}
    public static void drawLine(MathVector aPoint1, MathVector aPoint2, int myData[], int myWidth, int myHeight,int myColor) {
        drawLine(aPoint1.getX2(),aPoint1.getY2(),aPoint2.getX2(),aPoint2.getY2(),myData,myWidth,myHeight,myColor,myColor);
    }
    public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight,int myColor) {
        drawLine(aPoint1.getX1(),aPoint1.getY1(),aPoint2.getX1(),aPoint2.getY1(),myData,myWidth,myHeight,myColor,myColor);
    }
    public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight,int myColor1,int myColor2) {
        drawLine(aPoint1.getX1(),aPoint1.getY1(),aPoint2.getX1(),aPoint2.getY1(),myData,myWidth,myHeight,myColor1,myColor2);
    }    
    public static void drawLine(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight) {
        drawLine(aPoint1.getX1(),aPoint1.getY1(),aPoint2.getX1(),aPoint2.getY1(),myData,myWidth,myHeight,aPoint1.getColor(),aPoint2.getColor());
    }
    public static void drawTest( int myData[], int myWidth, int myHeight)
    {
       drawLine(10,20,150,140,myData,myWidth,myHeight,0x0000ff00,0x00ff0000);               
       drawLine(100,120,30,10,myData,myWidth,myHeight,0x00ff00ff,0x000000ff);          
       drawLine(130,140,14,20,myData,myWidth,myHeight,0x0000ff00,0x0000ffff);                 
       drawLine(10,240,244,20,myData,myWidth,myHeight,0x00ff0000,0x000000ff);         
       drawLine(130,180,132,144,myData,myWidth,myHeight,0x00ffff00,0x00ffffff);        
       drawLine(216,210,202,204,myData,myWidth,myHeight,0x0000ff00,0x0000ffff);        
    }
    public static void drawLine(float x1, float y1, float x2, float y2, int myData[], int myWidth, int myHeight, int myColor1,int myColor2) {

        int i;
        int j;

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
        //int aColor1 = aPoint1.getColor();
        //int aColor2 = aPoint2.getColor();
        int aColor1 = myColor1;
        int aColor2 = myColor2;        
        float colorTime = 0;


        //thePoint.setColor(myColor);

        //if (myColor == 0x00ffff00) {
            //System.out.println("LineCrtr: obsPnt = " + aPoint1 + ", maxLinePnt = " + aPoint2 + ", x2MinusX1 = " + x2MinusX1 + ", y2MinusY1 = " + y2MinusY1);
        //}
        if (x2MinusX1 == 0 && aPnt1Y1 <= aPnt2Y1) {
            for (j = (int)aPnt1Y1; j <= aPnt2Y1; j++) {
                //thePoint.setY1(j);
                //thePoint.setX1(aPnt1X1);
                int aY1 = j;
                int aX1 = (int)aPnt1X1;
                colorTime =  (j-aPnt1Y1) / (aPnt2Y1 - aPnt1Y1);
                //System.out.println("LineCrtr: test colorTime = "+colorTime);
                int aColor = interpolate(aColor1, aColor2, colorTime);
                drawPoint(myData, myWidth, myHeight,aX1,aY1, aColor);
            }
        } else if (x2MinusX1 == 0 && aPnt2Y1 <= aPnt1Y1) {
            for (j = (int)aPnt2Y1; j <= aPnt1Y1; j++) {
                //thePoint.setY1(j);
                //thePoint.setX1(aPnt1X1);
                int aY1 = j;
                int aX1 = (int)aPnt1X1;

                colorTime = (j - aPnt2Y1) / (aPnt1Y1 - aPnt2Y1);
                int aColor = interpolate(aColor1, aColor2, colorTime);
                drawPoint(myData, myWidth, myHeight, aX1, aY1, aColor);
            }
        } else if (y2MinusY1 == 0 && aPnt1X1 <= aPnt2X1) {
            for (j = (int)aPnt1X1; j <= aPnt2X1; j++) {
                //thePoint.setX1(j);
                //thePoint.setY1(aPnt1Y1);
                int aX1 = j;
                int aY1 = (int)aPnt1Y1;                
                colorTime = (j - aPnt1X1) / (aPnt2X1 - aPnt1X1);
                int aColor = interpolate(aColor1, aColor2, colorTime);
                drawPoint(myData, myWidth, myHeight,aX1,aY1, aColor);
            }
        } else if (y2MinusY1 == 0 && aPnt2X1 <= aPnt1X1) {
            for (j = (int)aPnt2X1; j <= aPnt1X1; j++) {
                //thePoint.setX1((float) j);
                //thePoint.setY1(aPnt1Y1);
                int aX1 = j;
                int aY1 = (int)aPnt1Y1;                
                colorTime = (j-aPnt2X1) / (aPnt1X1 - aPnt2X1);
                int aColor = interpolate(aColor1, aColor2, colorTime);
                drawPoint(myData, myWidth, myHeight, aX1,aY1, aColor);
            }
        } else if (Math.abs(x2MinusX1) >= Math.abs(y2MinusY1)) {

            double slope = (aPnt2Y1 - aPnt1Y1) / (aPnt2X1 - aPnt1X1);
            //if (myColor == 0x00ffff00) {
                //System.out.println("LineCrtr: x2MinusX1, slope = " + slope);
            //}
            if (aPnt2X1 >= aPnt1X1) {
                for (i = (int)aPnt1X1; i <= aPnt2X1; i++) {
                    xDifference = i - aPnt1X1;
                    y = getYCoordinate(slope, xDifference, aPnt1Y1);
                    //thePoint.setY1((float) y);
                    //thePoint.setX1((float) i);
                    float aY1 = (float)y;
                    float aX1 = i;
                    
                    //if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: i = " + i + ", y = " + y);
                    //}

                    colorTime = (i-aPnt1X1) / (aPnt2X1 - aPnt1X1);
                    
                    int aColor = interpolate(aColor1, aColor2, colorTime);
                    //System.out.println("LineCrtr: colorTime = "+colorTime);
                    drawPoint(myData, myWidth, myHeight, (int) aX1, (int)aY1, aColor);
                }
            } else {
                for (i = (int)aPnt2X1; i <= aPnt1X1; i++) {
                    xDifference = i - aPnt2X1;
                    y = getYCoordinate(slope, xDifference, aPnt2Y1);
                    //thePoint.setY1((float) y);
                    //thePoint.setX1((float) i);
                    int aY1 = (int)y;
                    int aX1 = i;
                    
                    colorTime = (i-aPnt2X1) / (aPnt1X1 - aPnt2X1);
                    int aColor = interpolate(aColor1, aColor2, colorTime);
                    //System.out.println("LineCrtr: colorTime = "+colorTime);                    
                    drawPoint(myData, myWidth, myHeight, aX1,aY1, aColor);
                }
            }
        } else if (Math.abs(y2MinusY1) > Math.abs(x2MinusX1)) {
            double slope = (aPnt2Y1 - aPnt1Y1) / (aPnt2X1 - aPnt1X1);
            //if (myColor == 0x00ffff00) {
                //System.out.println("LineCrtr: y2MinusY1, slope = " + slope);
           // }
            if (aPnt2Y1 >= aPnt1Y1) {
                for (i = (int)aPnt1Y1; i <= aPnt2Y1; i++) {
                    yDifference = i - aPnt1Y1;
                    x = getXCoordinate(slope, yDifference, aPnt1X1);
                    //thePoint.setY1((float) i);
                    //thePoint.setX1((float) x);

                    int aY1 = i;
                    int aX1 = (int)x;
                    
                    //if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: x = " + x + ", y = " + i);
                    //}
                    colorTime = (i-aPnt1Y1) / (aPnt2Y1 - aPnt1Y1);
                    int aColor = interpolate(aColor1, aColor2, colorTime);
                    drawPoint(myData, myWidth, myHeight, aX1,aY1, aColor);
                }
            } else {
                //TOTO: souhld this be less than & equal?
                for (i = (int)aPnt2Y1; i <= aPnt1Y1; i++) {
                    yDifference = i - aPnt2Y1;
                    x = getXCoordinate(slope, yDifference, aPnt2X1);
                    //thePoint.setY1((float) i);
                    //thePoint.setX1((float) x);
                    int aY1 = i;
                    int aX1 = (int)x;
                    
                    //if (myColor == 0x00ffff00) {
                        //System.out.println("LineCrtr: x = " + x + ", y = " + i);
                    //}
                    colorTime = (i-aPnt2Y1) / (aPnt1Y1 - aPnt2Y1);
                    int aColor = interpolate(aColor1, aColor2, colorTime);
                    //System.out.println("LineCrtr: colorTime = "+colorTime);                    
                    drawPoint(myData, myWidth, myHeight, aX1,aY1, aColor);
                    
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
        if(myX < 0 || myX > myWidth || myY < 0 || myY > myHeight ){
            return;
        }
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
            drawLine(aPnt1, aPnt2, myData, myWidth, myHeight,myColor);
            aPnt1 = aPnt2;
        }
    }
    public static void drawLineByAngle(){
        
    }
    public static int interpolate(int myRGB1, int myRGB2, float myIndex) {
        int red1 = (myRGB1 >> 16) & 0x000000ff;
        int green1 = (myRGB1 >> 8) & 0x000000ff;
        int blue1 = (myRGB1 & 0x000000ff);

        int red2 = (myRGB2 >> 16) & 0x000000ff;
        int green2 = (myRGB2 >> 8) & 0x000000ff;
        int blue2 = (myRGB2 & 0x000000ff);

        int red = (int)(red1 + (red2 - red1) * myIndex);
        int green = (int)(green1 + (green2 - green1) * myIndex);
        int blue = (int)(blue1 + (blue2 - blue1) * myIndex);

        int rgb = ((red & 0x000000ff) << 16)
                | ((green & 0x000000ff) << 8)
                | ((blue & 0x000000ff));
        //return 0x00c8c8c8;
        return rgb;        
    }
}