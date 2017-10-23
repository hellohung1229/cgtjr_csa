/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectOptmzd;
import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import cgtjr.academics.elctrclengnrng.imgprcssng.annotate.FrameAnnotator;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.DigitPxls;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ClusterMapHOGFltr extends HOGCornerDetectOptmzd {

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
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        
        if(getFrameIndex()<=1) return;
        
        if (getEigenValue(i) >= getEigenThreshold() && getTemporalGradientValues(i) > getTmprlGrdntThrshld()) {
            //System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getEigenThreshold());
            //System.out.println("ClusterFltr: getTemporalGradientValues("+i+") = "+getTemporalGradientValues(i)+", getTmprlGrdntThrshld = "+getTmprlGrdntThrshld());            
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
        }
        //System.out.println("ClusterMapHOGFltr : test ...");

        else {
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
                //System.err.println("ClusterMapHOGFltr: "+cme.getMessage());
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
        if(trackerID >= 100){
            trackerID = 0;
        }
    }
    /*
     public int getMaxGroupVal()
     {
     Point aPoint = null;
     RectBndryHOGTree aRectBndryHOGArrayList = null;
     Set aSet = aTreeMap.keySet();
     Iterator anIterator1 = aSet.iterator();
     Iterator anIterator2 = null;
     Integer aGroupKey = null;

     while(anIterator1.hasNext())
     {
     aGroupKey = (Integer)anIterator1.next();
     System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
     aRectBndryHOGArrayList = (RectBndryHOGTree)aTreeMap.get(aGroupKey);
     anIterator2 = aRectBndryHOGArrayList.iterator();
     while(anIterator2.hasNext())
     {
     aPoint = (Point)anIterator2.next();
     System.out.println("point : "+aPoint);
     }
     }
     }*/

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
/*
    public void updateTrackID() {
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();

        Point aPoint = null;
        RectBndryHOGTree aRectBndryHOGArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        //int pixelData[] = getImageDrawData().getImagePixels();
        int myPixelData[] = imagePixelData.getImagePixels();
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryHOGArrayList = (RectBndryHOGTree) aTreeMap.get(aGroupKey);
            if(displayBoundary == true){
               aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), 0x0000ff00);
            }
            //anIterator2 = aRectBndryHOGArrayList.iterator();
            int x = aRectBndryHOGArrayList.getXAvg();
            int y = aRectBndryHOGArrayList.getYAvg();
            int anImageIndex = ImageTool.rtrvIndex(x, y, anImageWidth);
            int nmbrIndex = 40;
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            //int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aGroupKey.intValue());            
            //DigitPxls.draw9x9(myPixelData,pxlDataX1,anImageWidth,anImageHeight,anImageIndex,nmbrIndex);
            if(getDisplayDigit()){
               DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());
            }
            if(getIsAnnotateOn()){
                //consider putting this code inside process
                theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "traffic analysis");
            }            
        }
    }
*/
    public void printData() {
        
  //System.out.println("ClusterMapHOGFltr : printData() ");
        int anImageWidth = getImageWidth();
        int anImageHeight = getImageHeight();

        Point aPoint = null;
        RectBndryHOGTree aRectBndryHOGArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        //int pixelData[] = getImageDrawData().getImagePixels();
        int myPixelData[] = imagePixelData.getImagePixels();
        
        //System.out.println("ClusterMapHOGFltr : test 2");
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryHOGArrayList = (RectBndryHOGTree) aTreeMap.get(aGroupKey);

            if(getDisplayBoundary()){            
               aRectBndryHOGArrayList.drawSquare(myPixelData, getImageWidth(), getImageHeight(), 0x0000ff00);
            }
            //anIterator2 = aRectBndryHOGArrayList.iterator();
            int x = aRectBndryHOGArrayList.getXAvg();
            int y = aRectBndryHOGArrayList.getYAvg();
            //int anImageIndex = ImageTool.rtrvIndex(x, y, anImageWidth);
          
            //int nmbrIndex = 40;
            Calendar now = Calendar.getInstance();
            String aDateTime = dateFormat.format(now.getTime());
            //int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aGroupKey.intValue());            
            //DigitPxls.draw9x9(myPixelData,pxlDataX1,anImageWidth,anImageHeight,anImageIndex,nmbrIndex);
            aRectBndryHOGArrayList.updateTrackID();
            //DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());
            //theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "traffic analysis");
            if(getDisplayDigit()){
               DigitPxls.drawDigit(myPixelData, anImageWidth, anImageHeight, x, y, aRectBndryHOGArrayList.getBoundaryID());            
            }
            if(getIsAnnotateOn()){
                //consider putting this code inside process
               theFrameAnnotator.process(aDateTime, frameIndex, getImageWidth(), getImageHeight(), aRectBndryHOGArrayList.getBoundaryID(), aRectBndryHOGArrayList.getXMin(), aRectBndryHOGArrayList.getYMin(), aRectBndryHOGArrayList.getXMax(), aRectBndryHOGArrayList.getYMax(), "traffic analysis");
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
        return imagePixelData.getImagePixels();
    }
    /*
    public void finishPrsng() {
        printData();
    }*/

    public void finish() {
        super.finish(); //To change body of generated methods, choose Tools | Templates.
        printData();
    }
}