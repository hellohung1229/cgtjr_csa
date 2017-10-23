package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.general.gui.WTSTextField;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import java.util.*;
import javax.swing.JTextArea;

public class SurfaceData {

    private int xValue = 170;
    private int yValue = 260;
    private int yMin;
    private int yMax;
    private int xMin;
    private int xMax;
    private int maxLength;
    private int maxWidth;
    private int surfaceArea;
    private double maxDistance;
    private double maxDistanceX;
    private double maxDistanceY;
    private double angle;
    private Pnt obsrvtnPnt;
    private Pnt maxLinePnt;
    private Pnt maxYPnt;
    private Pnt minYPnt;
    private Pnt maxXPnt;
    private Pnt minXPnt;
    private Pnt leftLinePnt;
    private Pnt rightLinePnt;
    private Pnt fovLeftPnt;
    private Pnt fovRightPnt;
    private int width = 352;
    private int height = 288;
    private int fovHght;
    private double fovHalfAngle = .3456;
    private boolean slopeProfile;
    private static JTextArea txtArea;
    
    public SurfaceData() {
        initialize();
        obsrvtnPnt = new Pnt(170, 280, 0);
        leftLinePnt = new Pnt();
        rightLinePnt = new Pnt();
        fovHght = height / 2;
        fovLeftPnt = new Pnt(0, fovHght, 0);
        fovRightPnt = new Pnt(352, fovHght, 0); 
        //txtArea = WTSTextField.getTxtFld();        
    }

    public SurfaceData(int myWidth, int myHeight) {
        initialize();
        obsrvtnPnt = new Pnt(170, 280, 0);
        leftLinePnt = new Pnt();
        rightLinePnt = new Pnt();
        fovHght = myHeight / 2;
    }

    public void setWidth(int myWidth) {
        width = myWidth;
    }

    public void setHeight(int myHeight) {
        height = myHeight;
    }

    public void setFovHght(int myFovHght) {
        fovHght = myFovHght;
    }

    public int getFovHght() {
        return fovHght;
    }

    public void setObsrvtnPnt(Pnt obsrvtnPnt) {
        this.obsrvtnPnt = obsrvtnPnt;
    }

    public Pnt getFovLeftPnt() {
        return fovLeftPnt;
    }

    public Pnt getFovRightPnt() {
        return fovRightPnt;
    }

    public void initialize() {
        yMin = 999999;
        yMax = -999999;
        xMin = 999999;
        xMax = -999999;
        maxDistance = 0;
        angle = 0;
        slopeProfile = false;
    }

    public void insrt(Pnt aPoint) {
        int aY = (int) aPoint.getY1();
        int aX = (int) aPoint.getX1();
        //System.out.println("SurfaceLength x = " + aX + ", y = " + aY);

        if (aY < yMin) {
            yMin = aY;
            minYPnt = aPoint;
        }
        if (aY > yMax) {
            yMax = aY;
            maxYPnt = aPoint;
        }
        maxLength = yMax - yMin;
        //System.out.println("maxLength = " + maxLength + ", yMax = " + yMax + ", yMin = " + yMin);

        if (aY == yValue) {
            if (aX < xMin) {
                xMin = aX;
                minXPnt = aPoint;
            }
            if (aX > xMax) {
                xMax = aX;
                maxXPnt = aPoint;
            }
        }
    }

    public void cmptMaxLine(Pnt myPoint) {
        double maxDistanceTmp = PntTool.getDistance(obsrvtnPnt, myPoint);
        if (maxDistanceTmp > maxDistance) {
            maxDistance = maxDistanceTmp;
            int xDstnce = (int) (myPoint.getX1() - obsrvtnPnt.getX1());
            int yDstnce = (int) (obsrvtnPnt.getY1() - myPoint.getY1());
            maxLinePnt = myPoint;
            
            angle = Math.atan2(yDstnce, xDstnce);
        }
    }

    public void cmptMaxLnPrpdclr() {
    }

    public int getYMin() {
        return yMin;
    }

    public int getYMax() {
        return yMax;
    }

    public void setSurfaceArea(int myNmbrQuads, int myDeltaX, int myDeltaY) {
        surfaceArea = myNmbrQuads * myDeltaX * myDeltaY;
        //System.out.println("SurfaceLength: surface area = " + surfaceArea);
    }

    public void insrt(Vector aVector) {

        initialize();
        Pnt aPoint = null;
        int aSize = aVector.size();

        for (int i = 0; i < aSize; i++) {
            //System.out.println("SurfaceLength size = " + aSize + ", node # = " + i);
            aPoint = (Pnt) aVector.get(i);
            insrt(aPoint);
            cmptMaxLine(aPoint);
            cmptCLType(aPoint);
        }
        maxWidth = xMax - xMin;

        //System.out.println("slope profile = " + slopeProfile);
        //System.out.println("sight distance mag. = " + maxDistance + ", angle = " + angle);

        WTSTextField.append("Slope profile  = " + slopeProfile+"\n");
        WTSTextField.append("Sight distance = " + maxDistance+"\n");        
        WTSTextField.append("Sight angle    = " + angle+"\n");                
        //System.out.println("SurfaceLength maxWidth = " + maxWidth + ", xMax = " + xMax + ", xMin = " + xMin);
        //System.out.println("SurfaceLength x1 = " + obsrvtnPnt.getX1() + ", y1 = " + obsrvtnPnt.getY1() + ", x2 = " + maxLinePnt.getX1() + ", y2 = " + maxLinePnt.getY1() + ", distance = " + maxDistance + ", angle = " + angle);//",  distance = " + maxDistance + ", x = " + maxLinePnt. + ", y = " + maxDistanceY + " , angle = " + angle);
    }

    public void cmptCLType(Pnt myPnt) {
        int pntHght = (int) myPnt.getY1();

        if (pntHght <= fovHght) {
            myPnt.setValue(2);
            slopeProfile = true;
        } else {
            myPnt.setValue(4);
        }
    }

    public Pnt getObsrvtnPnt() {
        return obsrvtnPnt;
    }

    public Pnt getMaxLinePnt() {
        return maxLinePnt;
    }

    public Pnt cmptLeftLinePnt() {
        double aDistance = PntTool.getDistance(obsrvtnPnt, maxLinePnt);
        double leftFOVMag = aDistance / Math.cos(fovHalfAngle);
        double angle2 = angle + fovHalfAngle;
        float leftFOVX_1 = (float) (leftFOVMag * Math.cos(angle2));
        float leftFOVX_2 = obsrvtnPnt.getX1();
        float leftFOVX_3 = leftFOVX_1 + leftFOVX_2;

        float leftFOVY = (float) (obsrvtnPnt.getY1() - (leftFOVMag * Math.sin(angle2)));
        leftLinePnt.setX1(leftFOVX_3);
        leftLinePnt.setY1(leftFOVY);
        //System.out.println("SurfaceData: leftFOVX_1 = "+leftFOVX_1+", leftFOVX_2 = "+leftFOVX_2+", leftFOVX_3 = "+leftFOVX_3);
        //System.out.println("SurfaceData: leftFOVMag = "+leftFOVMag+", angle = "+angle+", fovHalfAngle = "+fovHalfAngle);
        //System.out.println("SurfaceData: obsrvtnPnt = "+obsrvtnPnt+", maxLinePnt = "+maxLinePnt+", leftLinePnt = "+leftLinePnt);        

        return leftLinePnt;
    }

    public Pnt cmptRightLinePnt() {
        double aDistance = PntTool.getDistance(obsrvtnPnt, maxLinePnt);
        double rightFOVMag = aDistance / Math.cos(fovHalfAngle);
        double angle2 = angle - fovHalfAngle;
        float rightFOVX = (float) (rightFOVMag * Math.cos(angle2)) + obsrvtnPnt.getX1();
        float rightFOVY = (float) (obsrvtnPnt.getY1() - (rightFOVMag * Math.sin(angle2)));
        rightLinePnt.setX1(rightFOVX);
        rightLinePnt.setY1(rightFOVY);
        return rightLinePnt;
    }

    public static float cmptArcRadius(TreeMap myTreeMap) {
        Integer anInteger1 = (Integer) myTreeMap.firstKey();
        Pnt aPnt1 = (Pnt) myTreeMap.get(anInteger1);
        Integer anInteger2 = (Integer) myTreeMap.lastKey();
        Pnt aPnt2 = (Pnt) myTreeMap.get(anInteger2);
        return cmptArcRadius(myTreeMap, aPnt1, aPnt2);
    }
    public static float cmptArcRadius(TreeMap myTreeMap, Pnt aStrtPnt, Pnt aEndPnt) {
        double aLength1 = 0;
        double aLength2 = 0;
        double aLength3 = 0;
        double aLength4 = 0;
        
        double arcDiffMax = Integer.MAX_VALUE;
        double arcDiffTmp = 0;

        Set aKeySet = myTreeMap.descendingKeySet();
        Iterator anIterator = aKeySet.iterator();
        Pnt arcMidPnt = null;
        Pnt aPnt2 = null;
        Integer anInteger = null;
        float xMid = aStrtPnt.getX1() + (aEndPnt.getX1() - aStrtPnt.getX1())/2;
        float yMid = aStrtPnt.getY1() + (aEndPnt.getY1() - aStrtPnt.getY1())/2;      
        
        Pnt lineMidPnt = new Pnt(xMid,yMid,0);

        while (anIterator.hasNext()) {
            anInteger = (Integer) anIterator.next();
            aPnt2 = (Pnt) myTreeMap.get(anInteger);
            aLength1 = PntTool.getDistance(aStrtPnt, aPnt2);
            aLength2 = PntTool.getDistance(aPnt2, aEndPnt);
            arcDiffTmp = Math.abs(aLength2 - aLength1);

            if (arcDiffTmp < arcDiffMax) {
                arcDiffMax = arcDiffTmp;
                arcMidPnt = aPnt2;
                aLength3 = aLength1;
                aLength4 = aLength2;
            }
        }
        float chordLngth = (float)PntTool.getDistance(aStrtPnt,aEndPnt);
        float sagitta = (float)PntTool.getDistance(lineMidPnt,arcMidPnt);
        float radius = (sagitta*sagitta + (chordLngth/2)*(chordLngth/2))/(2*sagitta);
        float curvature = 1/radius;

        WTSTextField.append("Radius    = "+radius+"\n");
        WTSTextField.append("Curvature = "+curvature+"\n");
        WTSTextField.append("Sagitta   = "+sagitta+"\n");        
        return radius;
    }
}