/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.CameraMatrixDLT;
import cgtjr.academics.elctrclengnrng.cv.DLTPlaneTransform;
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectOptmzd;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.cv.VanishingHeight;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.lnralgbra.MathVector;
import cgtjr.academics.math.lnralgbra.Matrix;
import java.awt.Point;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Cluster3DBoundFltr extends HOGCornerDetectOptmzd {

    private FrameAnnotator theFrameAnnotator;
    private ImageDrawData imagePixelData;
    private double clusterMaxDistance = 5;
    private TreeMap aTreeMap;
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private int trackerID;
    private boolean displayDigit = true;
    private boolean isAnnotateOn = false;
    private boolean displayBoundary = true;
    private VanishingHeight objectWidthHeight;
    private DLTPlaneTransform planeTransform;
    private CameraMatrixDLT cmraMatrixDLT;
    private CubeCreator cbeCreator;
    
    private int colorList[] = {0x00ff0000, 0x0000ff00, 0x000000ff, 0x00ffff00, 0x0000ffff, 0x00ff00ff};

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();

        imagePixelData = getImageDrawData();

        theFrameAnnotator = new FrameAnnotator();

        objectWidthHeight = new VanishingHeight();

        planeTransform = new DLTPlaneTransform(myImageWidth, myImageHeight);
        /*
        planeTransform.setP1x1(52.0);
        planeTransform.setP1y1(231.0);
        planeTransform.setP1x2(316.0);
        planeTransform.setP1y2(200.0);
        planeTransform.setP1x3(92.0);
        planeTransform.setP1y3(58.0);
        planeTransform.setP1x4(142.0);
        planeTransform.setP1y4(58.0);

        planeTransform.setP2x1(0.0);
        planeTransform.setP2y1(0.0);
        planeTransform.setP2x2(6.1);
        planeTransform.setP2y2(0.76);
        planeTransform.setP2x3(6.1);
        planeTransform.setP2y3(42.67);
        planeTransform.setP2x4(8.53);
        planeTransform.setP2y4(42.67);
        */
        
         planeTransform.setP1x1(134);
         planeTransform.setP1y1(215);
         planeTransform.setP1x2(316);
         planeTransform.setP1y2(200);
         planeTransform.setP1x3(92);
         planeTransform.setP1y3(58);
         planeTransform.setP1x4(99);
         planeTransform.setP1y4(52);

         planeTransform.setP2x1(0.0);
         planeTransform.setP2y1(0.0);
         planeTransform.setP2x2(9.51);
         planeTransform.setP2y2(0.5);
         planeTransform.setP2x3(9.51);
         planeTransform.setP2y3(44.39);
         planeTransform.setP2x4(10.51);
         planeTransform.setP2y4(44.89);        
         
        planeTransform.compute();
        
        cmraMatrixDLT = new CameraMatrixDLT();
        cmraMatrixDLT.compute();   
        setDisplayCorners(true);
        setDisplayArrows(false);
        setDisplayBoundary(false);
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);

        if (getFrameIndex() <= 1) {
            return;
        }
        if (getEigenValue(i) >= getEigenThreshold() && getTemporalGradientValues(i) > getTmprlGrdntThrshld()) {
            //System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getEigenThreshold());
            //System.out.println("ClusterFltr: getTemporalGradientValues(" + i + ") = " + getTemporalGradientValues(i) + ", getTmprlGrdntThrshld = " + getTmprlGrdntThrshld());
            insert(i);
        }
    }

    public double getClusterMaxDistance() {
        return clusterMaxDistance;
    }

    public void setClusterMaxDistance(double myClusterMaxDistance) {
        this.clusterMaxDistance = myClusterMaxDistance;
    }

    public boolean getDisplayDigit() {
        return displayDigit;
    }

    public boolean getIsAnnotateOn() {
        return isAnnotateOn;
    }

    public void setIsAnnotateOn(boolean myIsAnnotateOn) {
        this.isAnnotateOn = myIsAnnotateOn;
    }

    public void setDisplayDigit(boolean myDisplayDigit) {
        this.displayDigit = myDisplayDigit;
    }

    public void insert(int i) {

        int x = getXPstn(i);
        int y = getYPstn(i);
        HOGPosition aHOGPosition = getHOGPosition();
        aHOGPosition.setX(x);
        aHOGPosition.setY(y);

        insert(aHOGPosition);
    }

    public void computeMean() {
    }

    public void insert(HOGPosition myHOGPosition) {
        RectBndryHOGTree aRectBndryHOGArrayList = null;
        Set aSet = aTreeMap.keySet();
        RectBndryHOGTree tmpRectBndryHOGArrayList = null;
        boolean isInDstnce = false;

        if (aSet.isEmpty()) {
            aRectBndryHOGArrayList = new RectBndryHOGTree(myHOGPosition);
            Integer anIteger = new Integer(trackerID);
            aRectBndryHOGArrayList.setBoundaryID(trackerID);
            isInDstnce = true;
            //System.out.println("ClusterFltr: inserting firts point "+meshPoint.getX()+","+meshPoint.getY()+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryHOGArrayList);
            trackerID++;
            return;
        } else {
            Iterator anIterator = aSet.iterator();

            try {
                boolean hasNext = false;
                while (hasNext = anIterator.hasNext()) {
                    //System.out.println("Cluster: hasNext = "+hasNext);                    
                    Integer aGroupKey = (Integer) anIterator.next();
                    aRectBndryHOGArrayList = (RectBndryHOGTree) aTreeMap.get(aGroupKey);
                    isInDstnce = isInDistance(aRectBndryHOGArrayList, myHOGPosition);
                    if (aRectBndryHOGArrayList != null && isInDstnce == true) {
                        //System.out.println("ClusterFltr: inserting "+myHOGPosition+" into group "+aGroupKey);
                        if (tmpRectBndryHOGArrayList == null) {
                            aRectBndryHOGArrayList.add(myHOGPosition);
                            tmpRectBndryHOGArrayList = aRectBndryHOGArrayList;
                        } else {
                            //aRectBndryHOGArrayList.add(myHOGPosition);
                            //tmpRectBndryHOGArrayList.addAll(aRectBndryHOGArrayList);
                            //aTreeMap.remove(aGroupKey);
                        }
                        //return;
                        isInDstnce = false;
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
                //System.err.println("ClusterMapHOGFltr: " + cme.getMessage());
            }
        }
        //if (tmpRectBndryHOGArrayList == null) {
        if (tmpRectBndryHOGArrayList == null) {
            Integer aLastKey = (Integer) aTreeMap.lastKey();
            //System.out.println("Cluster: test2");
            aRectBndryHOGArrayList = new RectBndryHOGTree(myHOGPosition);
            //aRectBndryHOGArrayList.add(meshPoint);
            //int nmbr = aLastKey.intValue() + 1;
            Integer anIteger = new Integer(trackerID);
            aRectBndryHOGArrayList.setBoundaryID(trackerID);
            //System.out.println("ClusterFltr: inserting "+myHOGPosition+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryHOGArrayList);
            trackerID++;
        }
        if (trackerID >= 100) {
            trackerID = 0;
        }
    }

    public boolean isInDistance(RectBndryHOGTree myRectBndryHOGArrayList, HOGPosition myHOGPosition) {
        HOGPosition aHOGPosition = null;
        Iterator anIterator = myRectBndryHOGArrayList.iterator();
        while (anIterator.hasNext()) {
            aHOGPosition = (HOGPosition) anIterator.next();
            if (isInDistance(aHOGPosition, myHOGPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInDistance(RectBndryHOGTree myRectBndryHOGArrayList, Point aPoint2) {
        Point aPoint = null;
        Iterator anIterator = myRectBndryHOGArrayList.iterator();
        while (anIterator.hasNext()) {
            aPoint = (Point) anIterator.next();
            if (isInDistance(aPoint, aPoint2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInAvrgeDistance(RectBndryHOGTree myRectBndryHOGArrayList, Point aPoint2) {

        //Iterator anIterator = myRectBndryHOGArrayList.iterator();        
        int xAvg = myRectBndryHOGArrayList.getXAvg();
        int yAvg = myRectBndryHOGArrayList.getYAvg();
        Point aPoint = new Point(xAvg, yAvg);

        if (isInDistance(aPoint, aPoint2)) {
            //System.out.println("test");
            return true;
        }
        return false;
    }

    public boolean isInAvrgeDistance(RectBndryHOGTree myRectBndryHOGArrayList, HOGPosition aHOGPosition2) {

        //Iterator anIterator = myRectBndryHOGArrayList.iterator();        
        int xAvg = myRectBndryHOGArrayList.getXAvg();
        int yAvg = myRectBndryHOGArrayList.getYAvg();
        Point aPoint = new Point(xAvg, yAvg);

        if (isInDistance(aPoint, aHOGPosition2)) {
            //System.out.println("test");
            return true;
        }
        return false;
    }

    public boolean isInDistance(Point aPoint1, Point aPoint2) {
        boolean inDistance = false;
        double dx = aPoint2.getX() - aPoint1.getX();
        double dy = aPoint2.getY() - aPoint1.getY();
        double aDistance = Math.sqrt(dx * dx + dy * dy);

        //System.out.println("ClusterMapOptmzdFltr: point 1 = "+aPoint1+", point 2 = "+aPoint2+", distance = "+aDistance);
        if (aDistance <= clusterMaxDistance) {
            inDistance = true;
        }
        return inDistance;
    }

    public boolean isInDistance(Point aPoint1, HOGPosition aHOGPosition2) {
        boolean inDistance = false;
        double dx = aHOGPosition2.getX() - aPoint1.getX();
        double dy = aHOGPosition2.getY() - aPoint1.getY();
        double aDistance = Math.sqrt(dx * dx + dy * dy);

        //System.out.println("ClusterMapOptmzdFltr: point 1 = "+aPoint1+", point 2 = "+aPoint2+", distance = "+aDistance);
        if (aDistance <= clusterMaxDistance) {
            inDistance = true;
        }
        return inDistance;
    }

    public boolean isInDistance(HOGPosition aHOGPosition1, HOGPosition aHOGPosition2) {
        boolean inDistance = false;
        double dx = aHOGPosition2.getX() - aHOGPosition1.getX();
        double dy = aHOGPosition2.getY() - aHOGPosition1.getY();
        double aDistance = Math.sqrt(dx * dx + dy * dy);

        //System.out.println("ClusterMapOptmzdFltr: point 1 = "+aPoint1+", point 2 = "+aPoint2+", distance = "+aDistance);
        if (aDistance <= clusterMaxDistance) {
            inDistance = true;
        }
        return inDistance;
    }

    public void displayData() {
        //DataJEditorPane.clearText();
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();

        Point aPoint = null;
        RectBndryHOGTree aRectBndryHOGArrayList = null;

        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;

        int myPixelData[] = imagePixelData.getImagePixels();
        int aColor = 0;
        
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryHOGArrayList = (RectBndryHOGTree) aTreeMap.get(aGroupKey);
            objectWidthHeight.computeWidthHeight(aRectBndryHOGArrayList);
            int anID = 0;
            if (getDisplayBoundary()) {
                anID = aRectBndryHOGArrayList.getBoundaryID();
                int colorIndex = anID % (colorList.length);
                //System.out.println("ClusterLocationFltr: colorINdex = "+colorIndex+", id = "+anID+", length = "+colorList.length);
                aColor = colorList[colorIndex];
                aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), aColor);
            }
            
            MathVector aTopPoint1 = objectWidthHeight.getTopPoint1();
            MathVector aBasePoint1 = objectWidthHeight.getBasePoint1();

            MathVector aTopPoint2 = objectWidthHeight.getTopPoint2();
            MathVector aBasePoint2 = objectWidthHeight.getBasePoint2();
            
            double objectWidth = objectWidthHeight.getObjectWidth();
            double objectHeight = objectWidthHeight.getObjectHeight();           
            
            //double aPMatrix[][] = cmraMatrixDLT.getCameraMatrix();
            //PTransform aPTransform = new PTransform(aPMatrix);            
            //TransformDrawer.drawCube(cmraMatrixDLT, a2DCube, myPixelData, anImageWidth, anImageHeight, aColor, aColor);
            LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
            LineCrtr.drawLine(aBasePoint2, aTopPoint2, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);

            double X1[] = {aTopPoint2.getX2(), aTopPoint2.getY2(), aTopPoint2.getZ2()};

            //double X1[] = {52,250,1};  

            double t1[] = {52.0, 231.0, 1.0};
            double t2[] = {316.0, 200.0, 1.0};
            double t3[] = {92.0, 58.0, 1.0};
            double t4[] = {142.0, 58.0, 1.0};

            double X2[] = planeTransform.computeTransformation(X1);

            String imageData = "\u25A1" + " " + "Ix = " + aTopPoint2.getX2() + ", Iy = " + aTopPoint2.getY2() + ", Wx = " + X2[0] + ", Wy = " + X2[1] + ", Wz = " + X2[2] + "\n";
            System.out.println("Cluster3DBoundFltr: data = "+imageData);
            
            double aLength = objectWidth;
            double trnslteX = X2[0]-objectWidth/2;
            double trnslteY = X2[1];            
            double aTransform[][] = {{1,0,0,trnslteX},{0,1,0,trnslteY},{0,0,1,0},{0,0,0,1}};
            System.out.println("Cluster3DBoundFltr: .... transform ... ");
            //Matrix.print(aTransform);
            cbeCreator = new CubeCreator(objectWidth,objectWidth,.5*objectHeight);
            CubeCreator another2DCube = cmraMatrixDLT.computeTransform(aTransform, cbeCreator);    
            
            CubeCreator a2DCube = cmraMatrixDLT.cameraTransform(another2DCube);
           
            a2DCube.drawCube(myPixelData, anImageWidth, anImageHeight, aColor, aColor);            
            
            int x = aRectBndryHOGArrayList.getXAvg();
            int y = aRectBndryHOGArrayList.getYAvg();

            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);

            aRectBndryHOGArrayList.updateTrackID();
            //+", Iy = "+aTopPoint2.getY2()+", Wx = "+X2[0]+", Wy = "+X2[1]+", Wz = "+X2[2]+"\n"
            String output = "" + aDateTime + ", " + frameIndex + ", " + anID + ", [" + (int) aTopPoint2.getX2() + "," + (int) aTopPoint2.getY2() + "]" + ", [" + nf.format(X2[0]) + "," + nf.format(X2[1]) + ",0.00" + "]" + "," + aRectBndryHOGArrayList.getXMin() + "," + aRectBndryHOGArrayList.getYMin() + "," + aRectBndryHOGArrayList.getXMax() + "," + aRectBndryHOGArrayList.getYMax() + "\n";
            //DataJEditorPane.insertString(output, new Color(aColor));
            //DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());
            //theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "tracking analysis");
            if (getDisplayDigit()) {
                //DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());            
            }
            if (getIsAnnotateOn()) {
                //consider putting this code inside process
                theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "tracking analysis");
            }
        }
        double xc2 = 135;
        double yc2 = 215;
        double uc2 = 0;//meters
        double vc2 = 3.81;
        double wc2 = 0;
        double aX1[] = {uc2,vc2,wc2,1};
        double X2[] = cmraMatrixDLT.computeTransformation(aX1);
        //Matrix.print(X2);

    }

    public boolean getDisplayBoundary() {
        return displayBoundary;
    }

    public void setDisplayBoundary(boolean myDisplayBoundary) {
        this.displayBoundary = myDisplayBoundary;
    }

    public int getXPstn(int myIndex) {
        int x = myIndex % getImageWidth();
        return x;
    }

    public int getYPstn(int myIndex) {
        int y = myIndex / getImageWidth();
        return y;
    }

    public int[] getFltrdData() {
        //System.out.println("HOGCornerDetectOptmzd : getFltrData()");
        displayData();
        return imagePixelData.getImagePixels();
    }

    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
    }
}