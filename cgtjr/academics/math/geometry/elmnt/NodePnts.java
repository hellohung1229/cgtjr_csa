/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.elmnt;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.Vector;
import javax.swing.JEditorPane;

/**
 *
 * @author Nit
 */
public class NodePnts extends Nodes {

    public Pnt[] getPoints() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }
    public static Pnt rtrvMaxXMaxYPnt(Vector myVector, int maxY) {
        Vector aVector = rtrvBndry2DPnts(myVector);
        int aSize = aVector.size();
        Pnt aNode = null;
        Pnt aPnt = new Pnt();
        int x = 0;
        int xTmp = -Integer.MAX_VALUE;

        for (int i = 0; i < aSize; i++) {
            aNode = (Pnt) aVector.get(i);
            if (aNode.getY1() == maxY) {
                x = (int) (aNode.getX1());
                if (x > xTmp) {
                    xTmp = x;
                    //System.out.println("NodePnts.rtrvMaxXMaxYPnt: x = " + x);
                }
            }
        }

        aPnt.setX1(xTmp);
        aPnt.setY1(maxY);        
        //System.out.println("NodePnts.rtrvMaxXMaxYPnt: xTmp = " + xTmp+", aNode.getX1()="+aNode.getX1());        
        return aPnt;
    }

    public static Pnt rtrvMinXMaxYPnt(Vector myVector, int maxY) {
        Vector aVector = rtrvBndry2DPnts(myVector);
        int aSize = aVector.size();
        Pnt aPnt = new Pnt();
        Pnt aNode = null;
        int x = 0;
        int xTmp = Integer.MAX_VALUE;

        for (int i = 0; i < aSize; i++) {
            aNode = (Pnt) aVector.get(i);
            if (aNode.getY1() == maxY) {
                x = (int) (aNode.getX1());
                if (x <= xTmp) {
                    xTmp = x;
                    //System.out.println("NodePnts.rtrvMinXMaxYPnt: x = " + x);

                }
            }
        }
        aPnt.setX1(xTmp);
        aPnt.setY1(maxY);
        return aPnt;
    }
    public static int cmptWidth(Vector myVector, int maxY) {
        Pnt aPnt1 = rtrvMaxXMaxYPnt(myVector, maxY);
        Pnt aPnt2 = rtrvMinXMaxYPnt(myVector, maxY);

        int x1 = (int) (aPnt1.getX1());
        int x2 = (int) (aPnt2.getX1());

        int aWidth = Math.abs(x2 - x1);

        System.out.println("NodePnts: x1 = " + x1 + ", x2 = " + x2 + ", width = " + aWidth);

        return aWidth;
    }

    public static Vector rtrvBndry2DPnts(Vector myVector) {
        Vector bndryElmnts = new Vector();
        Pnt aPoint = null;
        int aSize = myVector.size();
        //System.out.println("Point.rtrvSrfcBndryElmnts() ... ");
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);

            if (isIntrrSrfcNd(aPoint) == false) {
                bndryElmnts.add(aPoint);
                //System.out.println(i+","+aPoint0.getCounter()+","+aPoint1.getCounter());
            }
        }
        return bndryElmnts;
    }
    public static boolean isIntrrSrfcNd(Pnt myPoint) {
        boolean isInterior = false;
        if (myPoint.nmbrOfVertices() == 4) {
            isInterior = true;
        }
        return isInterior;
    }
    public static boolean isDegree2(Pnt myPoint) {
        boolean isInterior = false;
        if (myPoint.nmbrOfVertices() == 2) {
            isInterior = true;
        }
        return isInterior;
    }
    public static boolean isRightEdge(Pnt myPoint) {
        boolean isInterior = false;
        Pnt aRightPnt = myPoint.getPosXDrctn();
        Pnt aLeftPnt = myPoint.getNegXDrctn();  

        if (aRightPnt == null && aLeftPnt != null) {
            isInterior = true;
            //System.out.println("NodePnts.isRightEdge: right = "+aRightPnt+", left = "+aLeftPnt+", nmbr vertices = "+myPoint.nmbrOfVertices());        
        }
        return isInterior; 
    }
   
    public static boolean isLeftEdge(Pnt myPoint) {
        boolean isInterior = false;
        Pnt aRightPnt = myPoint.getPosXDrctn();
        Pnt aLeftPnt = myPoint.getNegXDrctn();  
        //if(myPoint.getY1() == 208 || myPoint.getY1() == 212 || myPoint.getY1() == 204 )
        //System.out.println("NodePnts: rtpnt = "+aRightPnt+", lfPnt = "+aLeftPnt);
                
        if (aRightPnt != null && aLeftPnt == null) {
            isInterior = true;
            //System.out.println("NodePnts.isLeftEdge: right = "+aRightPnt+", left = "+aLeftPnt+", nmbr vertices = "+myPoint.nmbrOfVertices());
        }
        return isInterior;
    }      
    public static void prntGlblVls(Vector myVector) {
        prntGlblVls(myVector, null);
    }

    public static void prntGlblVls(Vector myVector, JEditorPane myJEditorPane) {
        String info = "";
        Pnt aPoint = null;
        int aSize = myVector.size();
        //System.out.println("Point.prntGlblNmbrs(): size = "+aSize);
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            info = aPoint.getX1() + ", " + aPoint.getY1() + ", " + aPoint.getZ1() + ", " + aPoint.getValue();
            if (myJEditorPane != null) {
                myJEditorPane.setText(myJEditorPane.getText() + info + "\n");
            } else {
                System.out.println(info);
            }
        }
    }

    public static void prntGlblNds(Vector myVector, JEditorPane myJEditorPane) {
        String info = "";
        Pnt aPoint = null;
        int aSize = myVector.size();
        //System.out.println("Point.prntGlblNmbrs(): size = "+aSize);
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            info = i + "," + aPoint.getX1() + ", " + aPoint.getY1() + ", " + aPoint.getZ1();
            if (myJEditorPane != null) {
                myJEditorPane.setText(myJEditorPane.getText() + info + "\n");
            } else {
                System.out.println(info);
            }
        }
    }

    public static void prntGlblCrdnts(Vector myVector, JEditorPane myJEditorPane) {
        String info = "";
        Pnt aPoint = null;
        int aSize = myVector.size();
        //System.out.println("Point.prntGlblNmbrs(): size = "+aSize);
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) myVector.elementAt(i);
            info = i + "," + aPoint.getX1() + ", " + aPoint.getY1() + ", " + aPoint.getZ1();
            if (myJEditorPane != null) {
                myJEditorPane.setText(myJEditorPane.getText() + info + "\n");
            } else {
                System.out.println(info);
            }
        }
    }
}
