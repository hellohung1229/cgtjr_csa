/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.CornerDetectOptmzd;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.AnnotateFeatureVar;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryArrayList;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.DigitPxls;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ThemeSet;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class DataClusterMapFltr extends CornerDetectOptmzd {

    private FrameAnnotator theFrameAnnotator;
    private ImageDrawData imagePixelData;
    double clusterDistance = 10;
    RectBndryArrayList groupRectBndryArrayList;
    TreeMap aTreeMap;
    TreeMap aKMeanTreeMap;
    Point meshPoint;
    //private int nmbrOfPnts;
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight); //To change body of generated methods, choose Tools | Templates.
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();
        aKMeanTreeMap = new TreeMap();
        meshPoint = new Point();
        
        //imagePixelData = getDrawOverlap()?getImageDrawData():new ImageDrawData(myImageWidth,myImageHeight);
        imagePixelData = getImageDrawData();        
        
        theFrameAnnotator = new FrameAnnotator();
        //setTmprlGrdntThrshld(5);
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        if (getEigenValue(i) >= getEigenThreshold() && getTemporalGradientValues(i) > getTmprlGrdntThrshld()) {
            //System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getEigenThreshold());
            //System.out.println("ClusterFltr: getTemporalGradientValues("+i+") = "+getTemporalGradientValues(i)+", getTmprlGrdntThrshld = "+getTmprlGrdntThrshld());            
            insert(i);
        }
    }

    public double getClusterDistance() {
        return clusterDistance;
    }

    public void setClusterDistance(double clusterDistance) {
        this.clusterDistance = clusterDistance;
    }

    public void insert(int i) {
        meshPoint = new Point();
        int x = getXPstn(i);
        int y = getYPstn(i);
        meshPoint.setLocation(x, y);
        insert(meshPoint);
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

    public void insert(Point meshPoint) {
        RectBndryArrayList aRectBndryArrayList = null;
        Set aSet = aTreeMap.keySet();
        RectBndryArrayList tmpRectBndryArrayList = null;

        if (aSet.isEmpty()) {
            aRectBndryArrayList = new RectBndryArrayList(meshPoint);
            //aRectBndryArrayList.add(meshPoint);
            //Integer aGroupKey = new Integer(0);
            //int nmbr = aGroupKey.intValue();
            Integer anIteger = new Integer(0);
            aRectBndryArrayList.setBoxID(0);
            //System.out.println("ClusterFltr: inserting firts point "+meshPoint.getX()+","+meshPoint.getY()+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryArrayList);
            return;
        } else {
            Iterator anIterator = aSet.iterator();
            //System.out.println("Cluster: test1");
            try {
                while (anIterator.hasNext()) {
                    Integer aGroupKey = (Integer) anIterator.next();
                    aRectBndryArrayList = (RectBndryArrayList) aTreeMap.get(aGroupKey);
                    if (aRectBndryArrayList != null && isInDistance(aRectBndryArrayList, meshPoint)) {
                        //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+aGroupKey);
                        if (tmpRectBndryArrayList == null) {
                            aRectBndryArrayList.add(meshPoint);
                            tmpRectBndryArrayList = aRectBndryArrayList;
                        } else {
                            //aRectBndryArrayList.add(meshPoint);
                            //tmpRectBndryArrayList.addAll(aRectBndryArrayList);
                            aTreeMap.remove(aGroupKey);
                        }
                        //return;
                    }
                }
            } catch (java.util.ConcurrentModificationException cme) {
            }
        }
        if (tmpRectBndryArrayList == null) {
            Integer aLastKey = (Integer) aTreeMap.lastKey();
            //System.out.println("Cluster: test2");
            aRectBndryArrayList = new RectBndryArrayList(meshPoint);
            //aRectBndryArrayList.add(meshPoint);
            int nmbr = aLastKey.intValue() + 1;
            Integer anIteger = new Integer(nmbr);
            aRectBndryArrayList.setBoxID(nmbr);
            //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryArrayList);
        }


    }
    /*
     public int getMaxGroupVal()
     {
     Point aPoint = null;
     RectBndryArrayList aRectBndryArrayList = null;
     Set aSet = aTreeMap.keySet();
     Iterator anIterator1 = aSet.iterator();
     Iterator anIterator2 = null;
     Integer aGroupKey = null;

     while(anIterator1.hasNext())
     {
     aGroupKey = (Integer)anIterator1.next();
     System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
     aRectBndryArrayList = (RectBndryArrayList)aTreeMap.get(aGroupKey);
     anIterator2 = aRectBndryArrayList.iterator();
     while(anIterator2.hasNext())
     {
     aPoint = (Point)anIterator2.next();
     System.out.println("point : "+aPoint);
     }
     }
     }*/

    public boolean isInDistance(RectBndryArrayList myRectBndryArrayList, Point aPoint2) {
        Point aPoint = null;
        Iterator anIterator = myRectBndryArrayList.iterator();
        while (anIterator.hasNext()) {
            aPoint = (Point) anIterator.next();
            if (isInDistance(aPoint, aPoint2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInAvrgeDistance(RectBndryArrayList myRectBndryArrayList, Point aPoint2) {

        //Iterator anIterator = myRectBndryArrayList.iterator();        
        int xAvg = myRectBndryArrayList.getXAvg();
        int yAvg = myRectBndryArrayList.getYAvg();
        Point aPoint = new Point(xAvg, yAvg);

        if (isInDistance(aPoint, aPoint2)) {
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
        if (aDistance <= clusterDistance) {
            inDistance = true;
        }
        return inDistance;
    }

    public void printData() {
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();   
        
        Point aPoint = null;
        RectBndryArrayList aRectBndryArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        //int pixelData[] = getImageDrawData().getImagePixels();
        int myPixelData[] = imagePixelData.getImagePixels();
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryArrayList = (RectBndryArrayList) aTreeMap.get(aGroupKey);
            aRectBndryArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), 0x0000ff00);
            anIterator2 = aRectBndryArrayList.iterator();
            /*
             while (anIterator2.hasNext()) {
             aPoint = (Point) anIterator2.next();
             System.out.println("feature location : " + aPoint.getX() + "," + aPoint.getY());
             }*/
            int x = aRectBndryArrayList.getXAvg();
            int y = aRectBndryArrayList.getYAvg();            
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            DigitPxls.drawDigit(myPixelData,anImageWidth,anImageHeight,x,y,aRectBndryArrayList.getBoxID());
            AnnotateFeatureVar anAnnotateFeatureVar = new AnnotateFeatureVar();
            
            anAnnotateFeatureVar.setUserLogonIdValue(ThemeSet.getUserLogonIdValue());
            anAnnotateFeatureVar.setUrlValue(ThemeSet.getDocURL());            
            anAnnotateFeatureVar.setDateTimeValue(aDateTime);
            anAnnotateFeatureVar.setWidthValue(""+getImageWidth());
            anAnnotateFeatureVar.setHeightValue(""+getImageHeight());   
            anAnnotateFeatureVar.setTrackNumberValue(""+aRectBndryArrayList.getBoxID());
            anAnnotateFeatureVar.setWidthValue(""+getImageWidth());
            anAnnotateFeatureVar.setHeightValue(""+getImageHeight());
            anAnnotateFeatureVar.setXMinCoordValue(""+aRectBndryArrayList.getXMin());            
            anAnnotateFeatureVar.setYMinCoordValue(""+aRectBndryArrayList.getYMin());                        
            anAnnotateFeatureVar.setXMaxCoordValue(""+aRectBndryArrayList.getXMax());            
            anAnnotateFeatureVar.setYMaxCoordValue(""+aRectBndryArrayList.getYMax());                        
            anAnnotateFeatureVar.setDescriptionValue("traffic analysis");  
            anAnnotateFeatureVar.setEigenThresholdValue(""+getEigenThreshold());
            anAnnotateFeatureVar.setFeatureCountValue(""+aRectBndryArrayList.size());            
                   
            theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryArrayList.getBoxID(),aRectBndryArrayList.getXMin(),aRectBndryArrayList.getYMin(),aRectBndryArrayList.getXMax(),aRectBndryArrayList.getYMax(), "traffic analysis");
            //theFrameAnnotator.process(anAnnotateFeatureVar);            
        }
    }
    public int getXPstn(int myIndex) {
        int x = myIndex % getImageWidth();
        return x;
    }
    public int getYPstn(int myIndex) {
        int y = myIndex / getImageWidth();
        return y;
    }
    public void finishPrsng() {
        //printData();
    }
    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        printData();
    }
}