/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.elmnt;
/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;

import cgtjr.academics.math.geometry.*;

public class TrnglPnts extends Nodes
{
   Pnt point0;
   Pnt point1;
   Pnt point2;
   Pnt points[];

   public TrnglPnts()
   {
      point0 = new Pnt();
      point1 = new Pnt();
      point2 = new Pnt();
      points = new Pnt[3];


      //point4 = new Point();
      point0.connectVertices(point0);
      point1.connectVertices(point1);
      point2.connectVertices(point2);
      //point4.connectVertices(point1);
      //addVertex(point0);
      //addVertex(point1);
      //addVertex(point2);
      //addVertex(point4);
   }
   public TrnglPnts(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3)
   {
      point0 = myPoint1;
      point1 = myPoint2;
      point2 = myPoint3;
      //point4 = myPoint4;
      points = new Pnt[3];
      points[0] = point0;
      points[1] = point1;
      points[2] = point2;

      //addVertex(point0);
      //addVertex(point1);
      //addVertex(point2);
      //addVertex(point4);
      //System.out.println(point1);
      //System.out.println(point2);
      //System.out.println(point3);
      //System.out.println(point4);
   }
   public Pnt getPoint0()
   {
      return point0;
   }
   public Pnt getPoint1()
   {
      return point1;
   }
   public Pnt getPoint2()
   {
      return point2;
   }
   /*
   public Point getPoint3()
   {
      return point4;
   }*/
   public static Vector cnvrtRctnglToTrngl(Vector myVector)
   {
      Vector trnglElmnts = new Vector();
      QuadPnts aRctnglPnts = null;
      TrnglPnts aTrnglPnts1 = null;
      TrnglPnts aTrnglPnts2 = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      int aSize = myVector.size();
     //System.out.println("TrnglPnts.cnvrtRctnglToTrngl() ... ");
      for(int i=0;i<aSize;i++)
      {
         aRctnglPnts = (QuadPnts)myVector.elementAt(i);

         //TODO change code ...
         aPoint0 = aRctnglPnts.getPoints()[0];
         aPoint1 = aRctnglPnts.getPoints()[1];
         aPoint2 = aRctnglPnts.getPoints()[2];
         aPoint3 = aRctnglPnts.getPoints()[3];

         aTrnglPnts1 = new TrnglPnts(aPoint0,aPoint1,aPoint3);
         aTrnglPnts2 = new TrnglPnts(aPoint1,aPoint2,aPoint3);
         trnglElmnts.add(aTrnglPnts1);
         trnglElmnts.add(aTrnglPnts2);
      }
      return trnglElmnts;
   }
   public static void prntCrdnts(Vector myVector)
   {
      TrnglPnts aTrnglPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;

      int aSize = myVector.size();
      System.out.println("TrnglPnts: printing global coordinates ... aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aTrnglPnts = (TrnglPnts)myVector.elementAt(i);
         aPoint1 = aTrnglPnts.getPoint0();
         aPoint2 = aTrnglPnts.getPoint1();
         aPoint3 = aTrnglPnts.getPoint2();

         System.out.println(i+" , "+aPoint1+" , "+aPoint2+" , "+aPoint3);

      }
   }
   public static void prntGlblNmbrs(Vector myVector)
   {
      TrnglPnts aTrnglPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;

      int aSize = myVector.size();
      System.out.println("TrnglPnts: printing global coordinates ... aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aTrnglPnts = (TrnglPnts)myVector.elementAt(i);
         aPoint1 = aTrnglPnts.getPoint0();
         aPoint2 = aTrnglPnts.getPoint1();
         aPoint3 = aTrnglPnts.getPoint2();

         System.out.println(i+","+aPoint1.getCounter()+","+aPoint2.getCounter()+","+aPoint3.getCounter());

      }
   }

   public Pnt[] getPoints()
   {
      return points;
   }
}