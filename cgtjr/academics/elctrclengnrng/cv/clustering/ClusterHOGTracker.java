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
import cgtjr.academics.elctrclengnrng.cv.CentroidHG;
import cgtjr.academics.elctrclengnrng.cv.CentroidHGPstn;
import cgtjr.academics.elctrclengnrng.cv.DLTPlaneTransform;
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetector;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.cv.HistogramOG;
import cgtjr.academics.elctrclengnrng.cv.VanishingHeight;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.AnnotateFeatureVar;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.TxtSrvrImageAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryGlblHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ThemeSet;
import cgtjr.academics.math.geometry.linepnts.CubeCreator;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.awt.Point;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ClusterHOGTracker extends HOGCornerDetector {

    private TxtSrvrImageAnnotator theTxtSrvrImageAnnotator;
    private ImageDrawData imagePixelData;
    private double clusterMaxDistance = 1;
    private TreeMap aTreeMap;
    //final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private int trackerID;
    private boolean displayDigit = true;
    private boolean isAnnotateOn = false;
    private boolean displayBoundary = true;
    private VanishingHeight objectWidthHeight;
    private DLTPlaneTransform planeTransform;
    private CameraMatrixDLT cmraMatrixDLT;
    private CubeCreator cbeCreator;
    private int colorList[] = {0x000000ff,0x00ff0000, 0x0000ff00, 0x00ff00ff,0x00ffff00, 0x0000ffff,0x00ff4000,0x0000ff40,0x004000ff};
    private boolean displayHOG;
    private TreeMap globalHogTreeMapPast;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();

        imagePixelData = getImageDrawData();

        if (theTxtSrvrImageAnnotator == null) {
            theTxtSrvrImageAnnotator = new TxtSrvrImageAnnotator();
        }

        /*
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
         //setDisplayCorners(true);
         //setIsDrawArrow(false);
         */
        System.out.println("ClusterHOGTracker : ... initialize");
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        clusterHOG(dataValues, i);
    }

    public void clusterHOG(int dataValues[], int i) {

        //if (getFrameIndex() <= 1) {
        //    return;
        //}
        //System.out.println("ClusterHOGTracker: test 1: frame index = "+getFrameIndex());
        //if (isInBound(i, getImageWidth(), getImageHeight(), 1, 1, getImageWidth() - 1, getImageHeight() - 10)) {
            //System.out.println("ClusterHOGTracker: test 2: frame index = "+getFrameIndex());
            if (getEigenValue(i) >= getEigenThreshold() && getTemporalGradientValues(i) > getTmprlGrdntThrshld()) {
                //System.out.println("ClusterHOGTracker: test 3: frame index = "+getFrameIndex());
        //super.createHOG(dataValues, i);
                insertHOG(i);
            }
        //}
    }

    public double getClusterMaxDistance() {
        return clusterMaxDistance;
    }

    public void setMaxClusterDistance(double myClusterMaxDistance) {
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

    public void insertHOG(int i) {
        int x = getXPstn(i);
        int y = getYPstn(i);
        HOGPosition aHOGPosition = getHOGPosition();
        aHOGPosition.setX(x);
        aHOGPosition.setY(y);
        insertHOG(aHOGPosition);
    }

    public void computeMean() {
    }

    public void insertHOG(HOGPosition myHOGPosition) {
        RectBndryGlblHOGTree aRectBndryHOGArrayList = null;
        Set aSet = aTreeMap.keySet();
        RectBndryGlblHOGTree tmpRectBndryHOGArrayList = null;
        boolean isInDstnce = false;

        if (aSet.isEmpty()) {
            aRectBndryHOGArrayList = new RectBndryGlblHOGTree(myHOGPosition, getImageWidth(), getImageHeight());
            Integer anIteger = new Integer(trackerID);
            aRectBndryHOGArrayList.setBoundaryID(trackerID);
            //int anID = aRectBndryHOGArrayList.getBoundaryID();
            int colorIndex = trackerID % (colorList.length);
            int aColor = colorList[colorIndex];
            aRectBndryHOGArrayList.setColor(aColor);
            isInDstnce = true;
            //System.out.println("ClusterHOGTracker:setcolor: ID = " + trackerID + ", color index = " + colorIndex + ", color = " + colorIndex);
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
                    aRectBndryHOGArrayList = (RectBndryGlblHOGTree) aTreeMap.get(aGroupKey);
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
                            tmpRectBndryHOGArrayList.mergeBndry(aRectBndryHOGArrayList);
                            aTreeMap.remove(aGroupKey);
                        }
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
            //System.out.println("Cluster: test2");C:\cthomas\multimedia\clipart\planes
            aRectBndryHOGArrayList = new RectBndryGlblHOGTree(myHOGPosition, getImageWidth(), getImageHeight());
            //aRectBndryHOGArrayList.add(meshPoint);
            //int nmbr = aLastKey.intValue() + 1;
            Integer anIteger = new Integer(trackerID);
            aRectBndryHOGArrayList.setBoundaryID(trackerID);

            int colorIndex = trackerID % (colorList.length);
            int aColor = colorList[colorIndex];
            aRectBndryHOGArrayList.setColor(aColor);
            //System.out.println("ClusterHOGTracker:setcolor: ID = " + trackerID + ", color index = " + colorIndex + ", color = " + colorIndex);
            //System.out.println("ClusterFltr: inserting "+myHOGPosition+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryHOGArrayList);
            trackerID++;
        }
        //TODO: create parameter and constant for max ID ...
        if (trackerID >= 99999) {
            trackerID = 0;
        }
    }

    public void mergeBoundaries(RectBndryGlblHOGTree myRectBndryGlblHOGTree1, RectBndryGlblHOGTree myRectBndryGlblHOGTree2) {
        /*
         Consider checking size prior to merge
         myRectBndryGlblHOGTree1.size();
         myRectBndryGlblHOGTree2.size();
         */
    }

    public boolean isInDistance(RectBndryGlblHOGTree myRectBndryHOGArrayList, HOGPosition myHOGPosition) {
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

    public boolean isInDistance(RectBndryGlblHOGTree myRectBndryHOGArrayList, Point aPoint2) {
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

    public boolean isInAvrgeDistance(RectBndryGlblHOGTree myRectBndryHOGArrayList, Point aPoint2) {

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

    public boolean isInAvrgeDistance(RectBndryGlblHOGTree myRectBndryHOGArrayList, HOGPosition aHOGPosition2) {

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
        RectBndryGlblHOGTree aRectBndryHOGArrayList = null;

        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;

        int myPixelData[] = imagePixelData.getImagePixels();

        //System.out.println("ClusterHOGTracker : aTreeMap size = "+aTreeMap.size());
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");

            aRectBndryHOGArrayList = (RectBndryGlblHOGTree) aTreeMap.get(aGroupKey);

            boolean aBndrySizeChck = aRectBndryHOGArrayList.bndrySizeCheck(aRectBndryHOGArrayList.getMinWidth(), aRectBndryHOGArrayList.getMinHeight());
            if (aBndrySizeChck == true) {
                //objectWidthHeight.computeWidthHeight(aRectBndryHOGArrayList);

                //System.out.println("Cluster3DTrackFltr : width = "+aRectBndryHOGArrayList.getXWidth()+" height = "+aRectBndryHOGArrayList.getYHeight());

                //MathVector aBasePoint1 = objectWidthHeight.getBasePoint1();

                //MathVector aTopPoint2 = objectWidthHeight.getTopPoint2();
                //MathVector aBasePoint2 = objectWidthHeight.getBasePoint2();

                //double objectWidth = objectWidthHeight.getObjectWidth();
                //double objectHeight = objectWidthHeight.getObjectHeight();           

                //double aPMatrix[][] = cmraMatrixDLT.getCameraMatrix();
                //PTransform aPTransform = new PTransform(aPMatrix);            
                //TransformDrawer.drawCube(cmraMatrixDLT, a2DCube, myPixelData, anImageWidth, anImageHeight, aColor, aColor);


                float x1 = aRectBndryHOGArrayList.getXMin() + (aRectBndryHOGArrayList.getXMax() - aRectBndryHOGArrayList.getXMin()) / 2;
                float y1 = aRectBndryHOGArrayList.getYMax();
                float z1 = 1;

                float x2 = aRectBndryHOGArrayList.getXMin() + (aRectBndryHOGArrayList.getXMax() - aRectBndryHOGArrayList.getXMin()) / 2;
                float y2 = aRectBndryHOGArrayList.getYMin();
                float z2 = 1;


                //float xBottom = aRectBndryHOGArrayList.getXMin()+(aRectBndryHOGArrayList.getXMax()-aRectBndryHOGArrayList.getXMin())/2;
                //float yBottom = aRectBndryHOGArrayList.getYMin();            

                MathVector aBasePoint1 = new MathVector(x1, y1, z1);

                MathVector aTopPoint1 = new MathVector(x2, y2, z2);

                /////LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);

                //LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
                //LineCrtr.drawLine(aBasePoint2, aTopPoint2, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
                double B1[] = {aBasePoint1.getX2(), aBasePoint1.getY2(), aBasePoint1.getZ2()};
                //double X2a[] = cmraMatrixDLT.computeTransformation(X1a);

                double T1[] = {aTopPoint1.getX2(), aTopPoint1.getY2(), aTopPoint1.getZ2()};
                //double B1[] = {bx,by,bz};            

                //double X1[] = {52,250,1};  
            /*
                 double t1[] = {52.0, 231.0, 1.0};
                 double t2[] = {316.0, 200.0, 1.0};
                 double t3[] = {92.0, 58.0, 1.0};
                 double t4[] = {142.0, 58.0, 1.0};
                 */
                ////double B2[] = planeTransform.computeTransformation(B1);
                ////String imageData = "\u25A1" + " " + "Ix = " + B1[0] + ", Iy = " + B1[1] + ", Wx = " + B2[0] + ", Wy = " + B2[1] + ", Wz = " + B2[2] + "\n";
                //System.out.println("Cluster3DTrackFltr: data = "+imageData);            

                //double aLength = objectWidth;
                //double X1b[] = cmraMatrixDLT.computeTransformation(B1);

                //double aTransform[][] = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};

                //System.out.println("Cluster3DBoundFltr: .... transform ... ");
                //Matrix.print(aTransform);

                //double P1[] = {X2[0],X2[1],0,1};

                ////double B3[] = cmraMatrixDLT.computeLocation(B1);
                ////double T3[] = cmraMatrixDLT.computeZ(T1, B3[0], B3[1]);
                ////double objectHeight = T3[2];

                //!!!!Object width should be recalculated based on endpoints of boundinhg box, instead of ratio!!!!
                ////double objectWidth = cmraMatrixDLT.computeWidth(T3[2], aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getYMax());
                ////double trnslteX = B3[0] - (2.0 / 10.0) * objectWidth / 2.0;
                ////double trnslteY = B3[1];

                /////System.out.println("Cluster3DTrackFltr : B3[0] = "+B3[0]+", B3[1] = "+B3[1]+", B3[2] = "+B3[2]+", T3[0] = "+T3[0]+", T3[1] = "+T3[1]+", T3[2] = "+T3[2]);
                /////double aTransform[][] = {{1, 0, 0, trnslteX}, {0, 1, 0, trnslteY}, {0, 0, 1, 0}, {0, 0, 0, 1}};
                //double aTransform[][] = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};            
                //cbeCreator = new CubeCreator(0.25*objectWidth,0.33*objectWidth,0.5*objectHeight);
            /*
                 cbeCreator = new CubeCreator((2.1/5.0)*objectWidth,0.8*objectWidth,0.7*objectHeight);
                 CubeCreator another2DCube = cmraMatrixDLT.computeTransform(aTransform, cbeCreator);                
                 CubeCreator a2DCube = cmraMatrixDLT.cameraTransform(another2DCube);           
                 a2DCube.drawCube(myPixelData, anImageWidth, anImageHeight, aColor, aColor);            
                 */
                int x = aRectBndryHOGArrayList.getXAvg();
                int y = aRectBndryHOGArrayList.getYAvg();

                Calendar now = Calendar.getInstance();
                String aDateTime = dateFormat.format(now.getTime());

                NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);

                //aRectBndryHOGArrayList.updateTrackID();
                aRectBndryHOGArrayList.updataCentroidHG();

                aRectBndryHOGArrayList.drawCentroidPnt(myPixelData, anImageWidth, 0x00454857);
                aRectBndryHOGArrayList.updateTrackIDViaGlblHOG(globalHogTreeMapPast, anImageWidth, anImageHeight);
                
                //aRectBndryHOGArrayList.updateTrackIDViaCHG(globalHogTreeMapPast);

                //Should have code to update centroid prior to tracking

                //System.out.println("ClusterHOGTracker test 1 .........................................");
                //aRectBndryHOGArrayList.drawGlblHOGTrnsltn(myPixelData, anImageWidth, anImageHeight, 0x008F002E);
                
                
                //aRectBndryHOGArrayList.drawCntrdTrnsltn(myPixelData, anImageWidth, anImageHeight, 0x008F002E);

                //System.out.println("ClusterHOGTracker test 2 .........................................");
                //aRectBndryHOGArrayList.drawGlblHOGOrnttn(myPixelData, anImageWidth, anImageHeight);

                //aRectBndryHOGArrayList.drawCentroidPnt(myPixelData, anImageWidth, 0x00454857); 

                drawCentroidHG(aRectBndryHOGArrayList);
                drawCentroidHOG(aRectBndryHOGArrayList);

                aRectBndryHOGArrayList.drawMaxCntrdHOG(myPixelData, anImageWidth, anImageHeight); 

                ////aRectBndryHOGArrayList.drawMaxGrdntAngle(myPixelData, anImageWidth, anImageHeight,0x008F002E); 
                if (isDisplayHOG()) {
                    drawHOG(aRectBndryHOGArrayList);
                }

                aRectBndryHOGArrayList.cmpteChrctrstcScale(anImageWidth, anImageHeight);
                double aScaleSum = aRectBndryHOGArrayList.computeScaleSum(anImageWidth, anImageHeight);
                //+", Iy = "+aTopPoint2.getY2()+", Wx = "+X2[0]+", Wy = "+X2[1]+", Wz = "+X2[2]+"\n"
                //String output = "" + aDateTime + ", " + frameIndex + ", " + anID + ", [" + (int) aTopPoint2.getX2() + "," + (int) aTopPoint2.getY2() + "]" + ", [" + nf.format(X2[0]) + "," + nf.format(X2[1]) + ",0.00" + "]" + "," + aRectBndryHOGArrayList.getXMin() + "," + aRectBndryHOGArrayList.getYMin() + "," + aRectBndryHOGArrayList.getXMax() + "," + aRectBndryHOGArrayList.getYMax() + "\n";
                //DataJEditorPane180.insertString(output, new Color(aColor));
                //DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());
                //System.out.println("ClusterHOGTracker ... test 3");

                double aTrnsltnSum = aRectBndryHOGArrayList.getDistanceSum();
                double aChrctrstcScle = aRectBndryHOGArrayList.getChrctrstcScale();
                double aTrnsltnMgntde = aRectBndryHOGArrayList.getTrnsltnMgntde();
                double aTrnsltnPhase = aRectBndryHOGArrayList.getTrnsltnPhase();
                double aRotationAngle = aRectBndryHOGArrayList.getRotationAngle();
                //double aRotationSum = aRectBndryHOGArrayList.computeRotationSum2();


                //System.out.println("ClusterHOGTracker : aChrctrstcScle = "+aChrctrstcScle+", aTrnsltnMgntde = "+aTrnsltnMgntde+", aTrnsltnPhase = "+aTrnsltnPhase+", aRotationAngle = "+aRotationAngle+", aRotationDiff = "+aRotationDiff);
                //Note: check atan2 between 0 and 2PI
                //System.out.println(aDateTime+","+aScaleSum+","+aTrnsltnSum+","+aRotationSum);    

                //System.out.println(aDateTime + "," + aChrctrstcScle + "," + aScaleSum + "," + aTrnsltnMgntde + "," + aTrnsltnSum + "," + aRotationAngle );

                //AnnotateFeatureVar aVar = rtrveAnntteFtreVar(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), aChrctrstcScle, aTrnsltnMgntde, aTrnsltnPhase, aRotationSum, "tracking analysis");
                //theTxtSrvrImageAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "tracking analysis");
                //theTxtSrvrImageAnnotator.process(aVar);
                if (getDisplayBoundary()) {
                    //System.out.println("ClusterLocationFltr: colorINdex = "+colorIndex+", id = "+anID+", length = "+colorList.length)
                    //int anID = aRectBndryHOGArrayList.getBoundaryID();
                    //int colorIndex = anID % (colorList.length);
                    //int aColor = colorList[colorIndex];
                    int aColor = aRectBndryHOGArrayList.getColor();
                    //System.out.println("ClusterHOGTracker:getcolor: color = " + aColor);
                    aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), aColor);
                }
                if (getDisplayDigit()) {
                    //DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());            
                }
                if (getIsAnnotateOn()) {
                    //consider putting this code inside process
                    //theTxtSrvrImageAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "tracking analysis");
                }
            }

        }
    }


    public AnnotateFeatureVar rtrveAnntteFtreVar(String myDate, int myFrameNumber, int myWidth, int myHeight, int myIDNmbr, int myXMin, int myYMin, int myXMax, int myYMax, double myChrctrstcScle, double myTrnsltnMgntde, double myTrnsltnPhase, double myRotationAngle, String myDescription) {
        AnnotateFeatureVar theAnnotationBoxVar = new AnnotateFeatureVar();
        //System.out.println("TxtSrvrImageAnnotator ... test 9");
        int frameNumber = getFrameIndex();
        theAnnotationBoxVar.setUserLogonIdValue(ThemeSet.getUserLogonIdValue());
        theAnnotationBoxVar.setDateTimeValue(myDate);
        theAnnotationBoxVar.setFrameNumberValue("" + myFrameNumber);
        theAnnotationBoxVar.setWidthValue("" + myWidth);
        theAnnotationBoxVar.setHeightValue("" + myHeight);
        theAnnotationBoxVar.setTrackNumberValue("" + myIDNmbr);
        theAnnotationBoxVar.setXMinCoordValue("" + myXMin);
        theAnnotationBoxVar.setYMinCoordValue("" + myYMin);
        theAnnotationBoxVar.setYMinCoordValue("0");
        theAnnotationBoxVar.setXMaxCoordValue("" + myXMax);
        theAnnotationBoxVar.setYMaxCoordValue("" + myYMax);
        theAnnotationBoxVar.setZMaxCoordValue("0");
        theAnnotationBoxVar.setDescriptionValue("" + myDescription);
        theAnnotationBoxVar.setChrctrstcScaleValue("" + myChrctrstcScle);
        theAnnotationBoxVar.setTrnsltnMgntdeValue("" + myTrnsltnMgntde);
        theAnnotationBoxVar.setTrnsltnPhaseValue("" + myTrnsltnPhase);
        theAnnotationBoxVar.setRotationAngleValue("" + myRotationAngle);

        //theAnnotationBoxVar.setObjectHeightValue(""+myObjectHeight);
        //theAnnotationBoxVar.setObjectWidthValue(""+myObjectWidth);        
        theAnnotationBoxVar.setUrlValue("localhost");
        return theAnnotationBoxVar;
    }

    public void drawHOG(RectBndryGlblHOGTree aRectBndryGlblHOGTree) {

        HOGPosition myHOGPosition = aRectBndryGlblHOGTree.getCntrHOGPosition();
        HistogramOG aHOG[][] = myHOGPosition.getHog();

        if (aHOG == null || aHOG[0][0] == null) {
            return;
        }
        //System.out.println("ClusterGlblTrackFltr : test 3");


        int myPixelData[] = imagePixelData.getImagePixels();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();

        int xPos1 = (int) myHOGPosition.getX();
        int yPos1 = (int) myHOGPosition.getY();
        int binSize = aHOG[0][0].getNumberOfBins();

        //System.out.println("ClusterGlblTrackFltr : bin size = "+binSize);
        for (int i = 0; i < binSize; i++) {
            double angle = aHOG[0][0].getAngleByIndex(i);
            double gradient = aHOG[0][0].getGradientByIndex(i);

            float xPos2 = xPos1 + (float) (gradient * Math.cos(angle));
            float yPos2 = yPos1 + (float) (gradient * Math.sin(angle));
            //System.out.println("ClusterGlblTrackFltr : bin size ="+ binSize +", gradient = "+gradient+", angle = "+angle+", xPos1 = "+xPos1+",yPos1 = "+yPos1+", xPos2 = "+xPos2+", yPos2 = "+yPos2);            
            LineCrtr.drawLine(xPos1, yPos1, xPos2, yPos2, myPixelData, aWidth, aHeight, 0x000000ff, 0x000000ff);
        }
    }

    public void drawCentroidHG(RectBndryGlblHOGTree aRectBndryGlblHOGTree) {

        CentroidHGPstn myCentroidHGPstn = aRectBndryGlblHOGTree.getCentroidHGPstn();
        CentroidHG aCentroidHG = myCentroidHGPstn.getCentroidHG();

        if (myCentroidHGPstn == null || aCentroidHG == null) {
            return;
        }
        //System.out.println("ClusterGlblTrackFltr : test 3");


        int myPixelData[] = imagePixelData.getImagePixels();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();

        int aCentroid[] = aCentroidHG.getCentroid();
        int xPos1 = (int) aCentroid[0];
        int yPos1 = (int) aCentroid[1];
        int binSize = aCentroidHG.getNumberOfBins();

        //System.out.println("ClusterGlblTrackFltr : bin size = "+binSize);
        for (int i = 0; i < binSize; i++) {
            double angle = aCentroidHG.getAngleByIndex(i);
            double magnitude = aCentroidHG.getDistMagByIndex(i);
            float xPos2 = xPos1 + (float) (magnitude * Math.cos(angle));
            float yPos2 = yPos1 + (float) (magnitude * Math.sin(angle));
            //System.out.println("ClusterGlblTrackFltr : bin size ="+ binSize +", gradient = "+gradient+", angle = "+angle+", xPos1 = "+xPos1+",yPos1 = "+yPos1+", xPos2 = "+xPos2+", yPos2 = "+yPos2);            
            LineCrtr.drawLine(xPos1, yPos1, xPos2, yPos2, myPixelData, aWidth, aHeight, 0x00ff0000, 0x00ff0000);
        }
    }

    public void drawCentroidHOG(RectBndryGlblHOGTree aRectBndryGlblHOGTree) {

        CentroidHGPstn myCentroidHGPstn = aRectBndryGlblHOGTree.getCentroidHGPstn();
        CentroidHG aCentroidHG = myCentroidHGPstn.getCentroidHG();

        if (myCentroidHGPstn == null || aCentroidHG == null) {
            return;
        }
        //System.out.println("ClusterGlblTrackFltr : test 3");


        int myPixelData[] = imagePixelData.getImagePixels();
        int aWidth = getImageWidth();
        int aHeight = getImageHeight();

        int xPos1 = (int) aRectBndryGlblHOGTree.retrieveCenterX();
        int yPos1 = (int) aRectBndryGlblHOGTree.retrieveCenterY();
        int binSize = aCentroidHG.getNumberOfBins();

        //System.out.println("ClusterGlblTrackFltr : bin size = "+binSize);
        for (int i = 0; i < binSize; i++) {
            double angle = aCentroidHG.getAngleByIndex(i);
            double magnitude = aCentroidHG.getGrdntMagByIndex(i);
            float xPos2 = xPos1 + (float) (magnitude * Math.cos(angle));
            float yPos2 = yPos1 + (float) (magnitude * Math.sin(angle));
            //System.out.println("ClusterGlblTrackFltr : bin size ="+ binSize +", gradient = "+gradient+", angle = "+angle+", xPos1 = "+xPos1+",yPos1 = "+yPos1+", xPos2 = "+xPos2+", yPos2 = "+yPos2);            
            LineCrtr.drawLine(xPos1, yPos1, xPos2, yPos2, myPixelData, aWidth, aHeight, 0x000000ff, 0x000000ff);
        }
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

    public boolean isDisplayHOG() {
        return displayHOG;
    }

    public void setDisplayHOG(boolean myDisplayHOG) {
        this.displayHOG = myDisplayHOG;
    }

    /*
    public int[] getFltrdData() {
        //System.out.println("HOGCornerDetectOptmzd : getFltrData()");
        //displayData();
        return imagePixelData.getImagePixels();
    }*/

    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        displayData();
        globalHogTreeMapPast = aTreeMap;
    }
}