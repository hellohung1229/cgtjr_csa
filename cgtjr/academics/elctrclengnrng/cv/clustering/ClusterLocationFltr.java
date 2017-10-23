/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.DLTPlaneTransform;
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectOptmzd;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.cv.VanishingHeight;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.gui.DataJEditorPane;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.awt.Color;
import java.awt.Point;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ClusterLocationFltr extends HOGCornerDetectOptmzd {

    private FrameAnnotator theFrameAnnotator;
    private ImageDrawData imagePixelData;
    private double clusterMaxDistance = 5;
    private RectBndryHOGTree groupRectBndryArrayList;
    private TreeMap aTreeMap;
    private TreeMap aKMeanTreeMap;
    private Point meshPoint;
    //private int nmbrOfPnts;
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private int trackerID;
    private boolean displayDigit = true;
    private boolean isAnnotateOn = false;
    private boolean displayBoundary = true;
    private VanishingHeight objectHeight;
    private DLTPlaneTransform planeTransform;
    private int colorList[] = {0x00ff0000, 0x0000ff00, 0x000000ff, 0x00ffff00, 0x0000ffff, 0x00ff00ff};

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();
        aKMeanTreeMap = new TreeMap();
        meshPoint = new Point();

        imagePixelData = getImageDrawData();

        //setThreshold(20000);
        theFrameAnnotator = new FrameAnnotator();
        //setTmprlGrdntThrshld(5);
        //setIsDrawArrow(false);

        //System.out.println("ClusterMapHOGFltr: test frame# = "+getFrameIndex());
        objectHeight = new VanishingHeight();
        planeTransform = new DLTPlaneTransform(myImageWidth, myImageHeight);
        
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
        

        
        /*
        planeTransform.setP1x1(0.0);
        planeTransform.setP1y1(0.0);
        planeTransform.setP1x2(0.0);
        planeTransform.setP1y2(4.0);
        planeTransform.setP1x3(4.0);
        planeTransform.setP1y3(4.0);
        planeTransform.setP1x4(4.0);
        planeTransform.setP1y4(0.0);

        planeTransform.setP2x1(4.0);
        planeTransform.setP2y1(2.0);
        planeTransform.setP2x2(4.0);
        planeTransform.setP2y2(6.0);
        planeTransform.setP2x3(8.0);
        planeTransform.setP2y3(6.0);
        planeTransform.setP2x4(9.0);
        planeTransform.setP2y4(2.0);        
        */ 
        planeTransform.compute();         
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

    /*
     public void insert(int i) {
     meshPoint = new Point();
     int x = getXPstn(i);
     int y = getYPstn(i);
     meshPoint.setLocation(x, y);
     insert(meshPoint);
     }*/
    public void insert(int i) {
        //meshPoint = new Point();
        //System.out.println("ClusterMapHOGFltr: i = "+i);
        int x = getXPstn(i);
        int y = getYPstn(i);
        //meshPoint.setLocation(x, y);

        HOGPosition aHOGPosition = getHOGPosition();
        aHOGPosition.setX(x);
        aHOGPosition.setY(y);

        insert(aHOGPosition);
    }
    /*
     public void insert(Point meshPoint)
     {
     RectBndryArrayList aRectBndryArrayList = new RectBndryArrayList();
     aRectBndryArrayList.add(meshPoint);
     //Integer aGroupKey = new Integer(0);
     //int nmbr = aGroupKey.intValue();
     //Integer anIteger = new Integer(nmbr);
     System.out.println("ClusterFltr: inserting firts point "+meshPoint+" into group "+anIteger);
     //aTreeMap.put(anIteger,aRectBndryArrayList);
     return;
      
     }*/

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

    public void printData() {
        DataJEditorPane.clearText();
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
            objectHeight.computeWidthHeight(aRectBndryHOGArrayList);
            int anID = 0;            
            if (getDisplayBoundary()) {
                anID = aRectBndryHOGArrayList.getBoundaryID();
                int colorIndex =  anID%(colorList.length);
                //System.out.println("ClusterLocationFltr: colorINdex = "+colorIndex+", id = "+anID+", length = "+colorList.length);
                aColor = colorList[colorIndex];
                aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), aColor);
            }

            MathVector aTopPoint1 = objectHeight.getTopPoint1();
            MathVector aBasePoint1 = objectHeight.getBasePoint1();

            MathVector aTopPoint2 = objectHeight.getTopPoint2();
            MathVector aBasePoint2 = objectHeight.getBasePoint2();

            //LineCrtr.drawLine(aBasePoint1, aTopPoint1, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);
            //LineCrtr.drawLine(aBasePoint2, aTopPoint2, myPixelData, getImageWidth(), getImageHeight(), 0x000000ff);

            double X1[] = {aTopPoint2.getX2(), aTopPoint2.getY2(), aTopPoint2.getZ2()};

        //double X1[] = {52,250,1};  
        
        double t1[] = {52.0, 231.0, 1.0};
        double t2[] = {316.0, 200.0, 1.0};
        double t3[] = {92.0, 58.0, 1.0};
        double t4[] = {142.0, 58.0, 1.0};
        
        /*
        double t1[] = {4.0, 2.0, 0.0};
        double t2[] = {316.0, 200.0, 0.0};
        double t3[] = {92.0, 58.0, 0.0};
        double t4[] = {142.0, 58.0, 0.0};
        
        System.out.println("ClusterLocation Test ... ");
        double t12[] = planeTransform.computeTransformation(t1);
        double t22[] = planeTransform.computeTransformation(t2);
        double t32[] = planeTransform.computeTransformation(t3);        
        double t42[] = planeTransform.computeTransformation(t4);                
        */      
        
            double X2[] = planeTransform.computeTransformation(X1);

            String imageData = "\u25A1" + " " + "Ix = " + aTopPoint2.getX2() + ", Iy = " + aTopPoint2.getY2() + ", Wx = " + X2[0] + ", Wy = " + X2[1] + ", Wz = " + X2[2] + "\n";

            //JTextPane outTextField = DataJEditorPane.getTxtFld();
            //DataJEditorPane.insertString(imageData,new Color(aColor));

            //outTextField.setText(imageData);

            //System.out.println("top: x = "+aTopPoint2.getX2()+", y = "+aTopPoint2.getY2()+", c ="+aTopPoint2.getZ2());
            //System.out.println("top: x = "+aTopPoint2.getX2()+", y = "+aTopPoint2.getY2()+", c ="+aTopPoint2.getZ2());
            //System.out.println("X = "+X2[0]+", Y = "+X2[1]+", Z = "+X2[2]);

            //anIterator2 = aRectBndryHOGArrayList.iterator();
            int x = aRectBndryHOGArrayList.getXAvg();
            int y = aRectBndryHOGArrayList.getYAvg();
            //int anImageIndex = ImageTool.rtrvIndex(x, y, anImageWidth);        
            //int nmbrIndex = 40;
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
             
                    
                    //int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aGroupKey.intValue());            
                    //DigitPxls.draw9x9(myPixelData,pxlDataX1,anImageWidth,anImageHeight,anImageIndex,nmbrIndex);
            aRectBndryHOGArrayList.updateTrackID();
            //+", Iy = "+aTopPoint2.getY2()+", Wx = "+X2[0]+", Wy = "+X2[1]+", Wz = "+X2[2]+"\n"
            String output = "" + aDateTime + ", " + frameIndex + ", " +  anID  + ", [" + (int)aTopPoint2.getX2() + "," +(int)aTopPoint2.getY2() + "]" + ", [" + nf.format(X2[0]) + "," + nf.format(X2[1]) + ",0.00" + "]" + "," + aRectBndryHOGArrayList.getXMin() + "," + aRectBndryHOGArrayList.getYMin() + "," + aRectBndryHOGArrayList.getXMax() + "," + aRectBndryHOGArrayList.getYMax()+"\n";
            DataJEditorPane.insertString(output, new Color(aColor));
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
        printData();
        return imagePixelData.getImagePixels();
    }

    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        //printData();
    }
}