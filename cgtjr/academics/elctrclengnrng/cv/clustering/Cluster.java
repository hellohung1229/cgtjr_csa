/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

/**
 *
 * @author clayton g thomas jr
 */
import java.util.*;
import java.awt.*;

public class Cluster {

    private double maxDistance;
    private Vector groupVector;
    private HashMap aHashMap;

    

    

    Cluster() {
        groupVector = new Vector();
        aHashMap = new HashMap();

    }

    public void insert(Point myPoint1) {
        Vector aVector = null;
        Set aSet = aHashMap.keySet();
        Iterator anIterator = aSet.iterator();
        Integer aGroupKey = null;
        while (anIterator.hasNext()) {
            aGroupKey = (Integer) anIterator.next();
            aVector = (Vector) aHashMap.get(aGroupKey);
            if (isInDistance(aVector, myPoint1)) {
                aVector.add(myPoint1);
                return;
            }
        }
        aVector = new Vector();
        aVector.add(myPoint1);
        int nmbr = aGroupKey.intValue() + 1;
        Integer anIteger = new Integer(nmbr);
        aHashMap.put(anIteger, aVector);
    }

    public boolean isInDistance(Vector myVector, Point aPoint2) {
        Point aPoint = null;
        Iterator anIterator = myVector.iterator();
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

    public void insert(Point myPoint1, String myGroupName) {
    }
}