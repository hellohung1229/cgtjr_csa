/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activemesh;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;

public class ClstrMshFltr extends CrnrEnrgyFltr
{
   double maxDistance;
   Vector groupVector;
   HashMap aHashMap;
   Pnt meshPoint;
   
   ClstrMshFltr()
   {
      groupVector = new Vector();
      aHashMap = new HashMap();
      meshPoint = new Pnt();
      maxDistance = 20;
   }
   public void filter3x3(int dataValues[],int i)
   {
      insert(i);
   }
   public void insert(int i)
   {
      int x = getXPstn(i);
      int y = getYPstn(i);
     /////////////// meshPoint.setLocation(x,y);
      Vector aVector = null;
      Set aSet = aHashMap.keySet();
      Iterator anIterator = aSet.iterator();
      Integer aGroupKey = null;
      while(anIterator.hasNext())
      {
         aGroupKey = (Integer)anIterator.next();
         aVector = (Vector)aHashMap.get(aGroupKey);
         
         ////////if(isInDistance(aVector,meshPoint) && getEigenValue(i) >= getThreshold())
        ///// {
        ////    aVector.add(meshPoint);
        ////    return;
        ///////// }
      }
      aVector = new Vector();
      aVector.add(meshPoint);
      int nmbr = aGroupKey.intValue() + 1;
      Integer anIteger = new Integer(nmbr);
      aHashMap.put(anIteger,aVector);
   }
   public boolean isInDistance(Vector myVector,Pnt aPnt2)
   {
      Pnt aPnt = null;
      Iterator anIterator = myVector.iterator();
      while(anIterator.hasNext())
      {
         aPnt = (Pnt)anIterator.next();
         if(isInDistance(aPnt,aPnt2))
         {
            return true;
         }
      }
      return false;
   }
   public boolean isInDistance(Pnt aPnt1,Pnt aPnt2)
   {
      boolean inDistance = false;
      double dx = aPnt2.getX1() - aPnt1.getX1();
      double dy = aPnt2.getY1() - aPnt1.getY1();
      double aDistance = Math.sqrt(dx*dx + dy*dy);
      if(aDistance <= maxDistance)
      {
         inDistance = true;
      }
      return inDistance;
   }
   public void printData()
   {
      Pnt aPnt = null;
      Vector aVector = null;
      Set aSet = aHashMap.keySet();
      Iterator anIterator1 = aSet.iterator();
      Iterator anIterator2 = null;
      Integer aGroupKey = null;
      while(anIterator1.hasNext())
      {
         aGroupKey = (Integer)anIterator1.next();
         //System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
         aVector = (Vector)aHashMap.get(aGroupKey);
         anIterator2 = aVector.iterator();
         while(anIterator2.hasNext())
         {
            aPnt = (Pnt)anIterator2.next();
            System.out.println("point : "+aPnt);
         }
      }
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex;/////// % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex;///////         / getImageWidth();
      return y;
   }
}