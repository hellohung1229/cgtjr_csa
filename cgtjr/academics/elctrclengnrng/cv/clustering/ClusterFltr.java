/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import java.awt.Point;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;

public class ClusterFltr extends CornerDetect {

    double maxDistance;
    ArrayList groupArrayList;
    TreeMap aTreeMap;
    Point meshPoint;
    //private int nmbrOfPnts;

    ClusterFltr() {
        groupArrayList = new ArrayList();
        aTreeMap = new TreeMap();
        meshPoint = new Point();
        maxDistance = 30;
    }

    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);
        insert(i);
    }

    public void insert(int i) {
        //System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getThreshold());
        if (getEigenValue(i) < getThreshold()) {
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
     ArrayList aArrayList = new ArrayList();
     aArrayList.add(meshPoint);
     //Integer aGroupKey = new Integer(0);
     //int nmbr = aGroupKey.intValue();
     //Integer anIteger = new Integer(nmbr);
     System.out.println("ClusterFltr: inserting firts point "+meshPoint+" into group "+anIteger);
     //aTreeMap.put(anIteger,aArrayList);
     return;
      
     }*/

    public void insert(Point meshPoint) {
        ArrayList aArrayList = null;
        Set aSet = aTreeMap.keySet();
        ArrayList tmpArrayList = null;

        if (aSet.isEmpty()) {
            aArrayList = new ArrayList();
            aArrayList.add(meshPoint);
            //Integer aGroupKey = new Integer(0);
            //int nmbr = aGroupKey.intValue();
            Integer anIteger = new Integer(0);
            //System.out.println("ClusterFltr: inserting firts point "+meshPoint.getX()+","+meshPoint.getY()+" into group "+anIteger);
            aTreeMap.put(anIteger, aArrayList);
            return;
        } else {
            Iterator anIterator = aSet.iterator();
            //System.out.println("Cluster: test1");

            while (anIterator.hasNext()) {
                Integer aGroupKey = (Integer) anIterator.next();
                aArrayList = (ArrayList) aTreeMap.get(aGroupKey);
                if (isInDistance(aArrayList, meshPoint)) {
                    //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+aGroupKey);
                    if (tmpArrayList == null) {
                        aArrayList.add(meshPoint);
                        tmpArrayList = aArrayList;
                    } else {
                        tmpArrayList.addAll(aArrayList);
                        aTreeMap.remove(aGroupKey);
                    }
                    //return;
                }
            }
        }
        if (tmpArrayList == null) {
            Integer aLastKey = (Integer) aTreeMap.lastKey();
            //System.out.println("Cluster: test2");
            aArrayList = new ArrayList();
            aArrayList.add(meshPoint);
            int nmbr = aLastKey.intValue() + 1;
            Integer anIteger = new Integer(nmbr);
            //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+anIteger);
            aTreeMap.put(anIteger, aArrayList);
        }
    }
    /*
     public int getMaxGroupVal()
     {
     Point aPoint = null;
     ArrayList aArrayList = null;
     Set aSet = aTreeMap.keySet();
     Iterator anIterator1 = aSet.iterator();
     Iterator anIterator2 = null;
     Integer aGroupKey = null;

     while(anIterator1.hasNext())
     {
     aGroupKey = (Integer)anIterator1.next();
     System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
     aArrayList = (ArrayList)aTreeMap.get(aGroupKey);
     anIterator2 = aArrayList.iterator();
     while(anIterator2.hasNext())
     {
     aPoint = (Point)anIterator2.next();
     System.out.println("point : "+aPoint);
     }
     }
     }*/

    public boolean isInDistance(ArrayList myArrayList, Point aPoint2) {
        Point aPoint = null;
        Iterator anIterator = myArrayList.iterator();
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
        ArrayList aArrayList = null;
        Set aSet = aTreeMap.keySet();
        Iterator anIterator1 = aSet.iterator();
        Iterator anIterator2 = null;
        Integer aGroupKey = null;
        while (anIterator1.hasNext()) {
            aGroupKey = (Integer) anIterator1.next();
            //System.out.println("----------GROUP : " + aGroupKey.intValue() + "------------");
            aArrayList = (ArrayList) aTreeMap.get(aGroupKey);
            anIterator2 = aArrayList.iterator();
            while (anIterator2.hasNext()) {
                aPoint = (Point) anIterator2.next();
                System.out.println("feature location : " + aPoint.getX() + "," + aPoint.getY());
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
}