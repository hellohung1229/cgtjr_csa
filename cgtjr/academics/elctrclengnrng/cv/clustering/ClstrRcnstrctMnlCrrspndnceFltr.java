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
import cgtjr.academics.elctrclengnrng.cv.ReconstructBndry;
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectOptmzd;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.cv.VanishingHeight;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.awt.Point;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ClstrRcnstrctMnlCrrspndnceFltr extends HOGCornerDetectOptmzd {

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
    private ReconstructBndry rcnstrctDLT;
    private double clbrtnMtrx[][];
    private double essntlMtrx[][];
    
    private int colorList[] = {0x00ff0000, 0x0000ff00, 0x000000ff, 0x00ffff00, 0x0000ffff, 0x00ff00ff};

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();

        imagePixelData = getImageDrawData();

        theFrameAnnotator = new FrameAnnotator();

        objectWidthHeight = new VanishingHeight();

        planeTransform = new DLTPlaneTransform(myImageWidth, myImageHeight);

        planeTransform.setP1x1(303);
        planeTransform.setP1y1(167);
        planeTransform.setP1x2(246);
        planeTransform.setP1y2(135);
        planeTransform.setP1x3(110);
        planeTransform.setP1y3(58);
        planeTransform.setP1x4(264);
        planeTransform.setP1y4(167);

        planeTransform.setP2x1(0.0);
        planeTransform.setP2y1(0.0);
        planeTransform.setP2x2(0.0);
        planeTransform.setP2y2(7.32);
        planeTransform.setP2x3(0.0);
        planeTransform.setP2y3(34.14);
        planeTransform.setP2x4(-0.61);
        planeTransform.setP2y4(0.0);

        planeTransform.compute();

        cmraMatrixDLT = new CameraMatrixDLT();
        cmraMatrixDLT.compute();
        clbrtnMtrx = cmraMatrixDLT.getCalibration();
 
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        //Make sure index starts at zero
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
                System.out.println("Cluster3DRcnstrctFltr: " + cme.getMessage());
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
        int aColor = 0xcecece;

        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");

            aRectBndryHOGArrayList = (RectBndryHOGTree) aTreeMap.get(aGroupKey);
            objectWidthHeight.computeWidthHeight(aRectBndryHOGArrayList);
            int anID = 0;
            anID = aRectBndryHOGArrayList.getBoundaryID();
            int colorIndex = anID % (colorList.length);

            if (getDisplayBoundary()) {
                //System.out.println("ClusterLocationFltr: colorINdex = "+colorIndex+", id = "+anID+", length = "+colorList.length)
                aColor = colorList[colorIndex];
                //aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), aColor);
            }

            float x1 = aRectBndryHOGArrayList.getXMin() + (aRectBndryHOGArrayList.getXMax() - aRectBndryHOGArrayList.getXMin()) / 2;
            float y1 = aRectBndryHOGArrayList.getYMax();
            float z1 = 1;

            float x2 = aRectBndryHOGArrayList.getXMin() + (aRectBndryHOGArrayList.getXMax() - aRectBndryHOGArrayList.getXMin()) / 2;
            float y2 = aRectBndryHOGArrayList.getYMin();
            float z2 = 1;

            MathVector aBasePoint1 = new MathVector(x1, y1, z1);

            MathVector aTopPoint1 = new MathVector(x2, y2, z2);

            //LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);            
            //LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
            //LineCrtr.drawLine(aBasePoint2, aTopPoint2, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
            double B1[] = {aBasePoint1.getX2(), aBasePoint1.getY2(), aBasePoint1.getZ2()};
            //double X2a[] = cmraMatrixDLT.computeTransformation(X1a);

            double T1[] = {aTopPoint1.getX2(), aTopPoint1.getY2(), aTopPoint1.getZ2()};
            //double B1[] = {bx,by,bz};            

            double B2[] = planeTransform.computeTransformation(B1);
            String imageData = "\u25A1" + " " + "Ix = " + B1[0] + ", Iy = " + B1[1] + ", Wx = " + B2[0] + ", Wy = " + B2[1] + ", Wz = " + B2[2] + "\n";
            //System.out.println("Cluster3DTrackFltr: data = "+imageData);            

            double B3[] = cmraMatrixDLT.computeLocation(B1);
            double T3[] = cmraMatrixDLT.computeZ(T1, B3[0], B3[1]);
            double objectHeight = T3[2];

            double objectWidth = cmraMatrixDLT.computeWidth(T3[2], aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getYMax());
            double trnslteX = B3[0] - (2.0 / 10.0) * objectWidth / 2.0;
            double trnslteY = B3[1];

            //System.out.println("Cluster3DTrackFltr : B3[0] = "+B3[0]+", B3[1] = "+B3[1]+", B3[2] = "+B3[2]+", T3[0] = "+T3[0]+", T3[1] = "+T3[1]+", T3[2] = "+T3[2]);
            double aTransform[][] = {{1, 0, 0, trnslteX}, {0, 1, 0, trnslteY}, {0, 0, 1, 0}, {0, 0, 0, 1}};
            //double aTransform[][] = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};            
            //cbeCreator = new CubeCreator(0.25*objectWidth,0.33*objectWidth,0.5*objectHeight);
            cbeCreator = new CubeCreator((2.1 / 5.0) * objectWidth, 0.8 * objectWidth, 0.7 * objectHeight);
            CubeCreator another2DCube = cmraMatrixDLT.computeTransform(aTransform, cbeCreator);
            CubeCreator a2DCube = cmraMatrixDLT.cameraTransform(another2DCube);
            //a2DCube.drawCube(myPixelData, anImageWidth, anImageHeight, aColor, aColor);            

            int x = aRectBndryHOGArrayList.getXAvg();
            int y = aRectBndryHOGArrayList.getYAvg();

            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);

            aRectBndryHOGArrayList.updateTrackID();
            rcnstrctDLT = new ReconstructBndry(getImageWidth(), getImageHeight());
            rcnstrctDLT.setImagePixelData(imagePixelData);
            RectBndryHOGArrayList aRectBndryHOGArrayList2 = retrieveHOGArrayList();
            rcnstrctDLT.computePrjctveTrnsfrmtn(aRectBndryHOGArrayList2,clbrtnMtrx);
            CubeCreator a3DCube3 = rcnstrctDLT.rtrve3DBox();
            //a3DCube3.drawCube(myPixelData, anImageWidth, anImageHeight, aColor, aColor);             
            //+", Iy = "+aTopPoint2.getY2()+", Wx = "+X2[0]+", Wy = "+X2[1]+", Wz = "+X2[2]+"\n"
            //String output = "" + aDateTime + ", " + frameIndex + ", " + anID + ", [" + (int) aTopPoint2.getX2() + "," + (int) aTopPoint2.getY2() + "]" + ", [" + nf.format(X2[0]) + "," + nf.format(X2[1]) + ",0.00" + "]" + "," + aRectBndryHOGArrayList.getXMin() + "," + aRectBndryHOGArrayList.getYMin() + "," + aRectBndryHOGArrayList.getXMax() + "," + aRectBndryHOGArrayList.getYMax() + "\n";
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
    }

    private RectBndryHOGArrayList retrieveHOGArrayList() {

        /*
        int p1x1 = 222;
        int p1y1 = 163;
        int p1x2 = 195;
        int p1y2 = 155;
        int p1x3 = 230;
        int p1y3 = 173;
        int p1x4 = 241;
        int p1y4 = 167;
        int p1x5 = 217;
        int p1y5 = 168;
        int p1x6 = 192;
        int p1y6 = 162;
        int p1x7 = 242;
        int p1y7 = 166;
        int p1x8 = 192;
        int p1y8 = 154;
        
        int p2x1 = 223;
        int p2y1 = 164;
        int p2x2 = 196;
        int p2y2 = 156;
        int p2x3 = 233;
        int p2y3 = 174;
        int p2x4 = 243;
        int p2y4 = 168;
        int p2x5 = 221;
        int p2y5 = 169;
        int p2x6 = 194;
        int p2y6 = 163;
        int p2x7 = 243;
        int p2y7 = 167;
        int p2x8 = 195;
        int p2y8 = 156;
        */
        /*
        int p1x1 = 76;
        int p1y1 = 132;
        int p1x2 = 104;
        int p1y2 = 185;
        int p1x3 = 79;
        int p1y3 = 184;
        int p1x4 = 138;
        int p1y4 = 167;
        int p1x5 = 138;
        int p1y5 = 222;
        
        int p1x6 = 192;
        int p1y6 = 51;
        
        int p1x7 = 220;
        int p1y7 = 117;
        int p1x8 = 215;
        int p1y8 = 167;
        
        int p2x1 = 33;
        int p2y1 = 132;
        int p2x2 = 54;
        int p2y2 = 185;
        int p2x3 = 39;
        int p2y3 = 184;
        int p2x4 = 90;
        int p2y4 = 167;        
        int p2x5 = 93;
        int p2y5 = 222;
        
        int p2x6 = 152;
        int p2y6 = 51;
        
        int p2x7 = 180;
        int p2y7 = 117;
        int p2x8 = 176;
        int p2y8 = 167;
        */
        int p1x1 = 31;
        int p1y1 = 14;
        int p1x2 = 16;
        int p1y2 = 48;
        int p1x3 = 34;
        int p1y3 = 62;
        int p1x4 = 20;
        int p1y4 = 85;        
        int p1x5 = 156;
        int p1y5 = 86;        
        int p1x6 = 120;
        int p1y6 = 62;        
        int p1x7 = 160;
        int p1y7 = 49;
        
        int p1x8 = 154;
        int p1y8 = 14;
        
        int p2x1 = 53;
        int p2y1 = 10;
        int p2x2 = 27;
        int p2y2 = 39;        
        int p2x3 = 49;
        int p2y3 = 52;
        int p2x4 = 32;
        int p2y4 = 72;              
        int p2x5 = 138;
        int p2y5 = 90;
        
        int p2x6 = 114;
        int p2y6 = 60;
        
        int p2x7 = 139;
        int p2y7 = 48;
        int p2x8 = 158;
        int p2y8 = 10;
        
        HOGPosition aHOGPosition1 = null;
        HOGPosition aHOGPosition2 = null;
        HOGPosition aHOGPosition3 = null;
        HOGPosition aHOGPosition4 = null;
        HOGPosition aHOGPosition5 = null;
        HOGPosition aHOGPosition6 = null;
        HOGPosition aHOGPosition7 = null;
        HOGPosition aHOGPosition8 = null;

        HOGPosition aHOGPosMatch1 = null;
        HOGPosition aHOGPosMatch2 = null;
        HOGPosition aHOGPosMatch3 = null;
        HOGPosition aHOGPosMatch4 = null;
        HOGPosition aHOGPosMatch5 = null;
        HOGPosition aHOGPosMatch6 = null;
        HOGPosition aHOGPosMatch7 = null;
        HOGPosition aHOGPosMatch8 = null;

        if (aHOGPosition1 == null) {
            int anIndex1 = ImageTool.rtrvIndex(p1x1, p1y1, getImageWidth(), getImageHeight());
            aHOGPosition1 = new HOGPosition(null, anIndex1, getImageWidth());

        }
        if (aHOGPosition2 == null) {
            int anIndex2 = ImageTool.rtrvIndex(p1x2, p1y2, getImageWidth(), getImageHeight());
            aHOGPosition2 = new HOGPosition(null, anIndex2, getImageWidth());

        }
        if (aHOGPosition3 == null) {
            int anIndex3 = ImageTool.rtrvIndex(p1x3, p1y3, getImageWidth(), getImageHeight());
            aHOGPosition3 = new HOGPosition(null, anIndex3, getImageWidth());

        }
        if (aHOGPosition4 == null) {
            int anIndex4 = ImageTool.rtrvIndex(p1x4, p1y4, getImageWidth(), getImageHeight());
            aHOGPosition4 = new HOGPosition(null, anIndex4, getImageWidth());

        }
        if (aHOGPosition5 == null) {
            int anIndex5 = ImageTool.rtrvIndex(p1x5, p1y5, getImageWidth(), getImageHeight());
            aHOGPosition5 = new HOGPosition(null, anIndex5, getImageWidth());
        }
        if (aHOGPosition6 == null) {
            int anIndex6 = ImageTool.rtrvIndex(p1x6, p1y6, getImageWidth(), getImageHeight());
            aHOGPosition6 = new HOGPosition(null, anIndex6, getImageWidth());
        }
        if (aHOGPosition7 == null) {
            int anIndex7 = ImageTool.rtrvIndex(p1x7, p1y7, getImageWidth(), getImageHeight());
            aHOGPosition7 = new HOGPosition(null, anIndex7, getImageWidth());
        }
        if (aHOGPosition8 == null) {
            int anIndex8 = ImageTool.rtrvIndex(p1x8, p1y8, getImageWidth(), getImageHeight());
            aHOGPosition8 = new HOGPosition(null, anIndex8, getImageWidth());
        }
        if (aHOGPosMatch1 == null) {
            int anIndex1 = ImageTool.rtrvIndex(p2x1, p2y1, getImageWidth(), getImageHeight());
            aHOGPosMatch1 = new HOGPosition(null, anIndex1, getImageWidth());
            aHOGPosMatch1.connectHOGMatch(aHOGPosition1);
        }
        if (aHOGPosMatch2 == null) {
            int anIndex2 = ImageTool.rtrvIndex(p2x2, p2y2, getImageWidth(), getImageHeight());
            aHOGPosMatch2 = new HOGPosition(null, anIndex2, getImageWidth());
            aHOGPosMatch2.connectHOGMatch(aHOGPosition2);
        }
        if (aHOGPosMatch3 == null) {
            int anIndex3 = ImageTool.rtrvIndex(p2x3, p2y3, getImageWidth(), getImageHeight());
            aHOGPosMatch3 = new HOGPosition(null, anIndex3, getImageWidth());
            aHOGPosMatch3.connectHOGMatch(aHOGPosition3);

        }
        if (aHOGPosMatch4 == null) {
            int anIndex4 = ImageTool.rtrvIndex(p2x4, p2y4, getImageWidth(), getImageHeight());
            aHOGPosMatch4 = new HOGPosition(null, anIndex4, getImageWidth());
            aHOGPosMatch4.connectHOGMatch(aHOGPosition4);
        }

        if (aHOGPosMatch5 == null) {
            int anIndex5 = ImageTool.rtrvIndex(p2x5, p2y5, getImageWidth(), getImageHeight());
            aHOGPosMatch5 = new HOGPosition(null, anIndex5, getImageWidth());
            aHOGPosMatch5.connectHOGMatch(aHOGPosition5);
        }
        if (aHOGPosMatch6 == null) {
            int anIndex6 = ImageTool.rtrvIndex(p2x6, p2y6, getImageWidth(), getImageHeight());
            aHOGPosMatch6 = new HOGPosition(null, anIndex6, getImageWidth());
            aHOGPosMatch6.connectHOGMatch(aHOGPosition6);
        }
        if (aHOGPosMatch7 == null) {
            int anIndex7 = ImageTool.rtrvIndex(p2x7, p2y7, getImageWidth(), getImageHeight());
            aHOGPosMatch7 = new HOGPosition(null, anIndex7, getImageWidth());
            aHOGPosMatch7.connectHOGMatch(aHOGPosition7);
        }
        if (aHOGPosMatch8 == null) {
            int anIndex8 = ImageTool.rtrvIndex(p2x8, p2y8, getImageWidth(), getImageHeight());
            aHOGPosMatch8 = new HOGPosition(null, anIndex8, getImageWidth());
            aHOGPosMatch8.connectHOGMatch(aHOGPosition8);
        }
        
        RectBndryHOGArrayList aRectBndryHOGArrayList = new RectBndryHOGArrayList();
        aRectBndryHOGArrayList.add(aHOGPosMatch1);      
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );
        aRectBndryHOGArrayList.add(aHOGPosMatch2);
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );      
        aRectBndryHOGArrayList.add(aHOGPosMatch3);
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        aRectBndryHOGArrayList.add(aHOGPosMatch4);
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        aRectBndryHOGArrayList.add(aHOGPosMatch5);
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        aRectBndryHOGArrayList.add(aHOGPosMatch6);
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        aRectBndryHOGArrayList.add(aHOGPosMatch7);                
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        aRectBndryHOGArrayList.add(aHOGPosMatch8);    
        System.out.println("RectBndryHOGArrayList.size() = "+ aRectBndryHOGArrayList.size() );        
        
        return aRectBndryHOGArrayList;
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