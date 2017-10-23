/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.elmnt.LinePnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class CrdntData {

   public static void prntNodeGlblNmbr(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();
      System.out.println("CrdntData.prntNodeGlblIndex(): printing node global coord. size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         System.out.println(i+" , "+aPoint.getCounter()+" , "+aPoint);
      }
   }
   public static void prntNodeGlblIndex(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();
      System.out.println("CrdntData.prntNodeGlblIndex(): printing node global coord. size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         System.out.println(i+","+aPoint.getCounter());
      }
   }
   public static void prntLineGlblIndex(Vector myVector)
   {
      LinePnts aLinePnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      int aSize = myVector.size();
      System.out.println("CrdntData.prntGlblIndex() printing line glbl coord. : size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aLinePnts = (LinePnts)myVector.elementAt(i);
         aPoint0 = aLinePnts.getPoint0();
         aPoint1 = aLinePnts.getPoint1();
         System.out.println(i+","+aPoint0.getCounter()+","+aPoint1.getCounter());
      }
   }
   public static void prntQuadGlblIndex(Vector myVector)
   {
      QuadPnts aQuadPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      Pnt aPoint4 = null;
      int aSize = myVector.size();
      System.out.println("CrdntData: printing quad global coordinates ... aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aQuadPnts = (QuadPnts)myVector.elementAt(i);
         aPoint1 = aQuadPnts.getPoint0();
         aPoint2 = aQuadPnts.getPoint1();
         aPoint3 = aQuadPnts.getPoint2();
         aPoint4 = aQuadPnts.getPoint3();
         if(isNullVl(aPoint1,aPoint2,aPoint3,aPoint4) == false)
         {
            System.out.println(i+","+aPoint1.getCounter()+","+aPoint2.getCounter()+","+aPoint3.getCounter()+","+aPoint4.getCounter());
         }
      }
   }
   public static boolean isNullVl(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      boolean isNllVl = false;
      if(myPoint1 == null || myPoint2 == null ||
         myPoint3 == null || myPoint4 == null)
      {
         isNllVl = true;
      }
      return isNllVl;
   }

}
