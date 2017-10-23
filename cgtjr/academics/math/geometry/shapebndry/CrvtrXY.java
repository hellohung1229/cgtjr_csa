package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.general.gui.WTSTextField;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JTextArea;

public class CrvtrXY {

    private ArrayList line1;
    private ArrayList line2;
    private int xCenter = 320;
    private int yMin = 200;
    private int yMax = 340;
    private int offSet = 10;
    private static TreeMap clTreeMap;
    private HashMap cntHashMap;
    private Pnt strghtLnStrtPnt;
    private Pnt strghtLnEndPnt;
    private float deltaX;
    private static JTextArea txtArea;
    
    public CrvtrXY() {
        line1 = new ArrayList();
        line2 = new ArrayList();
        clTreeMap = new TreeMap();
        strghtLnStrtPnt = new Pnt();
        strghtLnEndPnt = new Pnt();
        //txtArea = WTSTextField.getTxtFld();           
    }

    public void setOffSet(int myOffSet) {
        offSet = myOffSet;
    }

    public static double cmptCrvtr(double x, double y) {
        return 2 * Math.sin(.4 * ((x) * (x) + y * y));
        //return Math.sqrt(Math.abs(x)+Math.abs(y));       
    }

    public void insrt(Pnt aPoint) {
        double aX = aPoint.getX1();
        double aY = aPoint.getY1();

        if (aX < xCenter && aY > yMin && aY < yMax) {
            //System.out.println("Left Crvtr: aX = "+aPoint);
            insrt(aPoint, 0);
        }
        if (aX > xCenter && aY > yMin && aY < yMax) {
            //System.out.println("Right Crvtr: aX = "+aPoint);
            insrt(aPoint, 1);
        }
    }

    public void insrt(Vector aVector) {

        clTreeMap.clear();
        clTreeMap = new TreeMap();
        Pnt aPoint = null;
        int aSize = aVector.size();
        for (int i = 0; i < aSize; i++) {
            aPoint = (Pnt) aVector.get(i);
            insrt(aPoint);
            cmptCLElmnts(aPoint);
            cmptStrghtDstnce(aPoint);
        }
        double aDistance = PntTool.getDistance(clTreeMap);
        //System.out.println("CL length = " + aDistance);
        //WTSTextField.append("CL length = " + aDistance+"\n");
    }

    public void insrt(Pnt myPoint, int myLnNmbr) {
        if (myLnNmbr == 0) {
            int size = line1.size();
            for (int i = 0; i < size; i++) {
                Pnt aPoint1 = (Pnt) line1.get(i);
                if (myPoint.getY1() < aPoint1.getY1()) {
                    line1.add(i, myPoint);
                    return;
                }
            }
            line1.add(myPoint);
        } else if (myLnNmbr == 1) {
            int size = line2.size();

            for (int i = 0; i < size; i++) {
                Pnt aPoint1 = (Pnt) line2.get(i);

                if (myPoint.getY1() < aPoint1.getY1()) {
                    line2.add(i, myPoint);
                    return;
                }
            }
            line2.add(myPoint);
        }
        return;
    }

    public void cmptCrvtr() {
        cmptCrvtr(0);
        cmptCrvtr(1);
    }

    public void cmptCrvtr(int myLnNmbr) {
        if (myLnNmbr == 0) {
            cmptCrvtr(line1);
        } else if (myLnNmbr == 1) {
            cmptCrvtr(line2);
        }
    }

    public void cmptCrvtr(ArrayList myArrayList) {
        int aSize = myArrayList.size();
        for (int i = offSet; i < aSize - offSet; i++) {
            cmptSqrtCrvtr(myArrayList, i);
        }
    }

    public double cmptSqrtCrvtr(ArrayList myArrayList, int myIndex) {
        int aSize = myArrayList.size();
        if (myIndex - 1 < 0 || myIndex + 1 >= aSize) {
            return 0;
        }
        Pnt aPoint0 = (Pnt) myArrayList.get(myIndex);
        Pnt aPoint1 = (Pnt) myArrayList.get(myIndex - offSet);
        Pnt aPoint2 = (Pnt) myArrayList.get(myIndex + offSet);
        double xDrvtv = aPoint2.getX1() - 2 * aPoint0.getX1() + aPoint1.getX1();
        double yDrvtv = aPoint2.getY1() - 2 * aPoint0.getY1() + aPoint1.getY1();
        double mgntde = Math.sqrt(xDrvtv * xDrvtv + yDrvtv * yDrvtv);

        //System.out.println(aPoint0.getX1()+", "+aPoint0.getY1()+", "+aPoint0.getZ1()+", "+mgntde);
        //System.out.println(aPoint0.getX1()+", "+aPoint0.getY1()+", "+aPoint0.getZ1()+", "+mgntde);

        return mgntde * mgntde;
    }

    public static TreeMap getCLTreeMap() {
        return clTreeMap;
    }

    public void cmptCLElmnts(Pnt myPnt) {

        int aHeight = (int) myPnt.getY1();
        int aX = (int) myPnt.getX1();

        Integer hghtInteger = new Integer(aHeight);

        Pnt aPnt = (Pnt) clTreeMap.get(hghtInteger);

        if (aPnt == null) {
            Pnt clPnt = new Pnt();
            clPnt.setNegXDrctn(myPnt);
            clPnt.setPosXDrctn(myPnt);
            clPnt.setX1(aX);
            clPnt.setY1(aHeight);
            clTreeMap.put(hghtInteger, clPnt);
            //System.out.println("CrvtrXY: inserting, clPnt = "+clPnt+", myPnt = "+myPnt);
        } else {
            Pnt clPnt = aPnt;//clTreeMap.get(hghtInteger);
            Pnt negPnt = clPnt.getNegXDrctn();
            Pnt posPnt = clPnt.getPosXDrctn();

            if (myPnt.getX1() < negPnt.getX1()) {
                clPnt.setNegXDrctn(myPnt);
            }
            if (myPnt.getX1() > posPnt.getX1()) {
                clPnt.setPosXDrctn(myPnt);
            }
            negPnt = clPnt.getNegXDrctn();
            posPnt = clPnt.getPosXDrctn();

            int centerX = (int) (negPnt.getX1() + posPnt.getX1()) / 2;
            clPnt.setX1(centerX);
            //System.out.println("CrvtrXY: retrieving, clPnt = "+clPnt+", myPnt = "+myPnt+", negPnt = "+clPnt.getNegXDrctn()+", posPnt = "+clPnt.getPosXDrctn());
        }
    }

    public void cmptStrghtDstnce(Pnt myPnt) {

        int xStrt = (int) strghtLnStrtPnt.getX1();
        int yStrt = (int) strghtLnStrtPnt.getY1();

        int xEndTmp = (int) myPnt.getX1();
        int yEndTmp = (int) myPnt.getY1();

        int xEnd = (int) strghtLnEndPnt.getX1();
        int yEnd = (int) strghtLnEndPnt.getY1();

        //System.out.println("CrvtrXY: start pnt = " + strghtLnStrtPnt + ", end pnt = " + strghtLnEndPnt + ", point = " + myPnt);

        if (xStrt == xEndTmp && yEndTmp < yStrt && yEndTmp > yEnd) {
            strghtLnEndPnt.setX1(xEndTmp);
            strghtLnEndPnt.setY1(yEndTmp);
        }
    }

    public Pnt getStrghtLnStrtPnt() {
        return strghtLnStrtPnt;
    }

    public Pnt getStrghtLnEndPnt() {
        return strghtLnEndPnt;
    }

    public void setStrghtLnStrtPnt(Pnt myPnt) {
        strghtLnStrtPnt = myPnt;
    }
    public void setDeltaX(float myX)
    {
        deltaX = myX;
    }
}