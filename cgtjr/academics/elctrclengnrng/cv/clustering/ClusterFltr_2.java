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
import java.util.Vector;

public class ClusterFltr_2 extends CornerDetect
{
   double maxDistance;
   Vector groupVector;
   TreeMap aTreeMap;
   Point meshPoint;
   private int nmbrOfPnts;
   
   ClusterFltr_2()
   {
      groupVector = new Vector();
      aTreeMap = new TreeMap();
      meshPoint = new Point();
      maxDistance = 20;
   }
   public void filter(int dataValues[],int i)
   {
      super.filter(dataValues,i);
      insert(i);
   }

   public void insert(int i)
   {
      System.out.println("ClusterFltr: eigenvalue = "+getEigenValue(i)+", threshold = "+getThreshold());
      if(getEigenValue(i) < getThreshold() )
      {
         return;
      }

      meshPoint = new Point();
      int x = getXPstn(i);
      int y = getYPstn(i);
      meshPoint.setLocation(x,y);

      Vector aVector = null;
      Set aSet = aTreeMap.keySet();

      if(aSet.isEmpty())
      {
         aVector = new Vector();
         aVector.add(meshPoint);
         Integer aGroupKey = new Integer(0);
         int nmbr = aGroupKey.intValue();
         Integer anIteger = new Integer(nmbr);
         System.out.println("ClusterFltr: inserting firts point "+meshPoint+" into group "+anIteger);
         aTreeMap.put(anIteger,aVector);
         return;
      }
      Iterator anIterator = aSet.iterator();
      //System.out.println("Cluster: test1");
      while(anIterator.hasNext())
      {
         Integer aGroupKey = (Integer)anIterator.next();
         aVector = (Vector)aTreeMap.get(aGroupKey);
         if(aVector != null && isInDistance(aVector,meshPoint) && getEigenValue(i) >= getThreshold())
         {
            //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+aGroupKey);
            aVector.add(meshPoint);
            return;
         }
      }
      Integer aLastKey = (Integer)aTreeMap.lastKey();
      //System.out.println("Cluster: test2");
      aVector = new Vector();
      aVector.add(meshPoint);
      int nmbr = aLastKey.intValue() + 1;
      Integer anIteger = new Integer(nmbr);
      //System.out.println("ClusterFltr: inserting "+meshPoint+" into group "+anIteger);
      aTreeMap.put(anIteger,aVector);
   }
   /*
   public int getMaxGroupVal()
   {
      Point aPoint = null;
      Vector aVector = null;
      Set aSet = aTreeMap.keySet();
      Iterator anIterator1 = aSet.iterator();
      Iterator anIterator2 = null;
      Integer aGroupKey = null;

      while(anIterator1.hasNext())
      {
         aGroupKey = (Integer)anIterator1.next();
         System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
         aVector = (Vector)aTreeMap.get(aGroupKey);
         anIterator2 = aVector.iterator();
         while(anIterator2.hasNext())
         {
            aPoint = (Point)anIterator2.next();
            System.out.println("point : "+aPoint);
         }
      }
   }*/
   public boolean isInDistance(Vector myVector,Point aPoint2)
   {
      Point aPoint = null;
      Iterator anIterator = myVector.iterator();
      while(anIterator.hasNext())
      {
         aPoint = (Point)anIterator.next();
         if(isInDistance(aPoint,aPoint2))
         {
            return true;
         }
      }
      return false;
   }
   public boolean isInDistance(Point aPoint1,Point aPoint2)
   {
      boolean inDistance = false;
      double dx = aPoint2.getX() - aPoint1.getX();
      double dy = aPoint2.getY() - aPoint1.getY();
      double aDistance = Math.sqrt(dx*dx + dy*dy);
      if(aDistance <= maxDistance)
      {
         inDistance = true;
      }
      return inDistance;
   }
   public void printData()
   {
      Point aPoint = null;
      Vector aVector = null;
      Set aSet = aTreeMap.keySet();
      Iterator anIterator1 = aSet.iterator();
      Iterator anIterator2 = null;
      Integer aGroupKey = null;
      while(anIterator1.hasNext())
      {
         aGroupKey = (Integer)anIterator1.next();
         //System.out.println("----------GROUP : "+aGroupKey.intValue()+"------------");
         aVector = (Vector)aTreeMap.get(aGroupKey);
         anIterator2 = aVector.iterator();
         while(anIterator2.hasNext())
         {
            aPoint = (Point)anIterator2.next();
            System.out.println("point : "+aPoint);
         }
      }
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getImageWidth();
      return y;
   }
   public void finishPrsng()
   {
      printData();
   }
}