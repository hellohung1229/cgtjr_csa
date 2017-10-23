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
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryArrayList;
import java.awt.Point;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ClusterTreeMapFltr extends HOGCornerDetectOptmzd{
        //CornerDetect {

    double maxDistance;
    RectBndryArrayList groupRectBndryArrayList;
    TreeMap aTreeMap;
    Point meshPoint;
    //private int nmbrOfPnts;

    ClusterTreeMapFltr() {
        //groupRectBndryArrayList = new RectBndryArrayList();
        aTreeMap = new TreeMap();
        meshPoint = new Point();
        maxDistance = 5;
        setThreshold(30000);
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        insert(i);
    }

    public void insert(int i) {
        //System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getEigenThreshold());
        if (getEigenValue(i) < getEigenThreshold()) {
            return;
        }

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
            //System.out.println("ClusterFltr: inserting firts point "+meshPoint.getX()+","+meshPoint.getY()+" into group "+anIteger);
            aTreeMap.put(anIteger, aRectBndryArrayList);
            return;
        } else {
            Iterator anIterator = aSet.iterator();
            //System.out.println("Cluster: test1");
            try{    
            while (anIterator.hasNext()) {
                Integer aGroupKey = (Integer) anIterator.next();
                aRectBndryArrayList = (RectBndryArrayList) aTreeMap.get(aGroupKey);
                if (aRectBndryArrayList !=null && isInDistance(aRectBndryArrayList, meshPoint)) {
                    //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+aGroupKey);
                    if (tmpRectBndryArrayList == null) {
                        aRectBndryArrayList.add(meshPoint);
                        tmpRectBndryArrayList = aRectBndryArrayList;
                    } else {
                        aRectBndryArrayList.add(meshPoint);
                        tmpRectBndryArrayList.addAll(aRectBndryArrayList);
                        aTreeMap.remove(aGroupKey);
                    }
                    //return;
                }
            }
        }catch(java.util.ConcurrentModificationException cme){

        }
    
        }
        if (tmpRectBndryArrayList == null) {
            Integer aLastKey = (Integer) aTreeMap.lastKey();
            //System.out.println("Cluster: test2");
            aRectBndryArrayList = new RectBndryArrayList(meshPoint);
            //aRectBndryArrayList.add(meshPoint);
            int nmbr = aLastKey.intValue() + 1;
            Integer anIteger = new Integer(nmbr);
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

    public boolean isInDistance(Point aPoint1, Point aPoint2) {
        boolean inDistance = false;
        double dx = aPoint2.getX() - aPoint1.getX();
        double dy = aPoint2.getY() - aPoint1.getY();
        double aDistance = Math.sqrt(dx * dx + dy * dy);
        if (aDistance <= maxDistance) {
            inDistance = true;
        }
        return inDistance;
    }

    public void printData() {
        Point aPoint = null;
        RectBndryArrayList aRectBndryArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aRectBndryArrayList = (RectBndryArrayList) aTreeMap.get(aGroupKey);
            
            aRectBndryArrayList.drawSquare(getFltrdData(),getImageWidth(),getImageHeight(),0x0000ff00);
            anIterator2 = aRectBndryArrayList.iterator();
            while (anIterator2.hasNext()) {
                aPoint = (Point) anIterator2.next();
                //System.out.println("feature location : " + aPoint.getX() + "," + aPoint.getY());
            }
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
        printData();
    }

   
    public void finish() {
        //super.finish(); //To change body of generated methods, choose Tools | Templates.
        printData();
    }
   
}