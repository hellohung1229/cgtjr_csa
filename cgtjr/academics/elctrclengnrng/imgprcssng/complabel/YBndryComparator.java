/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.cv.HOGPosition;
import java.util.Comparator;

/**
 *
 * @author cgthomasjr
 */
public class YBndryComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        HOGPosition aHOGPosition1 = (HOGPosition)o1;
        HOGPosition aHOGPosition2 = (HOGPosition)o2;
        int aYDistance = (int)(aHOGPosition2.getY()-aHOGPosition1.getY());
        return aYDistance;
    }
    
}
