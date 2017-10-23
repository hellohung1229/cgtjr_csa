/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author clayton g thomas jr
 */
public class PntTool
{
   public static double getDistance(TreeMap myTreeMap)
   {
       double total = 0;
       Set aKeySet = myTreeMap.descendingKeySet();
        Iterator anIterator = aKeySet.iterator();    
        Pnt aPnt1 = null;
        Pnt aPnt2 = null;
        Integer anInteger = null;
        
        if (anIterator.hasNext()) {
            anInteger = (Integer)anIterator.next();
            aPnt1 = (Pnt)myTreeMap.get(anInteger);          
        }
        while(anIterator.hasNext()) {
            anInteger = (Integer) anIterator.next();
            aPnt2 = (Pnt)myTreeMap.get(anInteger);
            total += getDistance(aPnt1,aPnt2);
            aPnt1 = aPnt2;
        }       
        return total;
   }
   public static double getDistance(Pnt myPoint1,Pnt myPoint2)
   {
       //System.out.println(myPoint1+" , "+myPoint2);
      double x1 = myPoint1.getX1();
      double y1 = myPoint1.getY1();
      double z1 = myPoint1.getZ1();
      double x2 = myPoint2.getX1();
      double y2 = myPoint2.getY1();
      double z2 = myPoint2.getZ1();

      return getDistance(x1,y1,z1,x2,y2,z2);
   }
   public static float getDistance(float x1,float y1,float x2,float y2)
   {
      float distance = 0.0f;

      distance = (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
         //System.out.println("Point.getDistance(): x1="+x1+" x2 = "+x2+"y1="+y1+" y2 = "+y2+"z1="+z1+" z2 = "+z2);
      return distance;
   }
   public static float getDistance(float x1,float y1,float z1,float x2,float y2,float z2)
   {
      float distance = 0.0f;
      distance = (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z2-z1)*(z2-z1));
         //System.out.println("Point.getDistance(): x1="+x1+" x2 = "+x2+"y1="+y1+" y2 = "+y2+"z1="+z1+" z2 = "+z2);
      return distance;
   }
   public static double getDistance(double x1,double y1,double z1,double x2,double y2,double z2)
   {
      double distance = 0.0;
      try{
         distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z2-z1)*(z2-z1));
         //System.out.println("Point.getDistance(): x1="+x1+" x2 = "+x2+"y1="+y1+" y2 = "+y2+"z1="+z1+" z2 = "+z2);
         return distance;
      }catch(ArithmeticException ae){
         //System.out.println("Point.getDistance() : ArithmeticException" );
         return (float)Integer.MAX_VALUE;
      }
   }
   public static double getDistance(double x1,double y1,double x2,double y2)
   {
      double distance = 0.0f;
      distance = (float)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
         //System.out.println("Point.getDistance(): x1="+x1+" x2 = "+x2+"y1="+y1+" y2 = "+y2+"z1="+z1+" z2 = "+z2);
      return distance;  
   }
   /*
   public static float getDistanceSquared(Point aPoint1,Point aPoint2)
   {
      float distanceSquare = 0.0f;//(x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1);
      try{
         distanceSquare =((aPoint2.getX1()-aPoint1.getMidPoint().getXMidPoint())*
                       (aPoint2.getX1()-aPoint1.getMidPoint().getXMidPoint())+
                       (aPoint2.getY1()-aPoint1.getMidPoint().getYMidPoint())*
                       (aPoint2.getY1()-aPoint1.getMidPoint().getYMidPoint())+
                       (aPoint2.getZ1()-aPoint1.getMidPoint().getZMidPoint())*
                       (aPoint2.getZ1()-aPoint1.getMidPoint().getZMidPoint()));

         return distanceSquare;
      }catch(ArithmeticException ae){
         //System.out.println("Point: ArithmeticException" );
         return Integer.MAX_VALUE;
      }
   }*/
   public static boolean withinDistance(Pnt aPoint1,Pnt aPoint2,double aDistance)
   {
      double x1 = aPoint1.getX1();
      double y1 = aPoint1.getY1();
      double z1 = aPoint1.getZ1();
      double x2 = aPoint2.getX1();
      double y2 = aPoint2.getY1();
      double z2 = aPoint2.getZ1();
      return withinDistance(x1,y1,z1,x2,y2,z2,aDistance);
   }
   public static boolean equalDstnce(Pnt aPoint1,Pnt aPoint2)
   {
      boolean isEqual = false;
      double x1 = aPoint1.getX1();
      double y1 = aPoint1.getY1();
      double z1 = aPoint1.getZ1();
      double x2 = aPoint2.getX1();
      double y2 = aPoint2.getY1();
      double z2 = aPoint2.getZ1();
      if(x1 == x2 && y1 == y2 && z1 == z2)
      {
         isEqual = true;
      }
      return isEqual;
   }
   public static boolean equalDstnceXY(Pnt aPoint1,Pnt aPoint2)
   {
      boolean isEqual = false;
      double x1 = aPoint1.getX1();
      double y1 = aPoint1.getY1();

      double x2 = aPoint2.getX1();
      double y2 = aPoint2.getY1();
      if(x1 == x2 && y1 == y2)
      {
         isEqual = true;
      }
      return isEqual;
   }   
   public static boolean withinDistance(double x1,double y1,double z1,double x2,double y2,double z2,double myRadius)
   {
      boolean hasCollision = false;
      double distance = getDistance(x1,y1,z1,x2,y2,z2);
      if(distance  <= myRadius)
      {
         hasCollision = true;
      }
      return hasCollision;
   }
   /*
   public boolean withinDistance(double x1,double y1,double z1,double x2,double y2,double z2,double aDistance)
   {
      boolean hasCollision = false;
      double distance = getDistance(this,aPoint);
      double radius = aDistance;
      if(distance  <= radius )
      {
         hasCollision = true;
      }
      return hasCollision;
   }*/
   /*
   public static boolean withinDistanceSqrd(Point aPoint1,Point aPoint2,double aDistance)
   {
      boolean hasCollision = false;
      float distanceSquared = getDistanceSquared(aPoint1,aPoint2);
      double radiusSquared = aDistance * aDistance;
      if(distanceSquared  <= radiusSquared )
      {
         hasCollision = true;
      }
      return hasCollision;
   }*/
   public static boolean withinDistance(Pnt aPoint1,Pnt aPoint2)
   {
      float distance = (aPoint1.getRadius() + aPoint2.getRadius());
      return withinDistance(aPoint1,aPoint2,distance);
   }

}
