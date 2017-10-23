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
import cgtjr.academics.math.geometry.*;
import java.util.*;
import javax.swing.JEditorPane;

public class LinePnts extends Nodes
{
   Pnt point[];
   Pnt point0;
   Pnt point1;
   Vector bndryElmnts;

   public LinePnts()
   {
      point = new Pnt[2];
      point0 = new Pnt();
      point1 = new Pnt();
      point[0] = point0;
      point[1] = point1;
      //bndryElmnts = new Vector();
      point0.connectVertices(point1);
      //addVertex(point0);
      //addVertex(point1);
   }
   public LinePnts(Pnt myPoint1,Pnt myPoint2)
   {
      point = new Pnt[2];
      point0 = myPoint1;
      point1 = myPoint2;
      point[0] = point0;
      point[1] = point1;
      //bndryElmnts = new Vector();
      point0.connectVertices(point1);
      //addVertex(point0);
      //addVertex(point1);
   }
   public static void prntGlblNmbrs(Vector myVector)
   {
      prntGlblNmbrs(myVector,null);
   }
   public static void prntGlblNmbrs(Vector myVector,JEditorPane myJEditorPane)
   {
      String info ="";
      LinePnts aLinePnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      int aSize = myVector.size();
      //System.out.println("LinePnts.prntGlblNmbrs(): size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aLinePnts = (LinePnts)myVector.elementAt(i);
         aPoint0 = aLinePnts.getPoint0();
         aPoint1 = aLinePnts.getPoint1();
         info = i+", "+aPoint0.getCounter()+" ,"+aPoint1.getCounter()+", "+aPoint0.getX1()+", "+aPoint0.getY1()+", "+aPoint0.getZ1()+", "+aPoint1.getX1()+", "+aPoint1.getY1()+", "+aPoint1.getZ1();
         if(myJEditorPane != null)
         {
            myJEditorPane.setText(myJEditorPane.getText()+info+"\n");
         }else{
            System.out.println(info);
         }
      }
   }
   public static Vector rtrvSrfcBndryElmnts(Vector myVector)
   {
      Vector bndryElmnts = new Vector();
      LinePnts aLinePnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      int aSize = myVector.size();
      //System.out.println("LinePnts.rtrvSrfcBndryElmnts() ... ");
      for(int i=0;i<aSize;i++)
      {
         aLinePnts = (LinePnts)myVector.elementAt(i);
         aPoint0 = aLinePnts.getPoint0();
         aPoint1 = aLinePnts.getPoint1();
         if(isIntrrSrfcNd(aPoint0) == false || isIntrrSrfcNd(aPoint1) == false)
         {
            bndryElmnts.add(aLinePnts);
            //System.out.println(i+","+aPoint0.getCounter()+","+aPoint1.getCounter());
         }
      }
      return bndryElmnts;
   }

   public void setPoint0(double aX1,double aY1,double aZ1)
   {
      point0.setX1((float)aX1);
      point0.setY1((float)aY1);
      point0.setZ1((float)aZ1);
   }
   public void setPoint1(double aX1,double aY1,double aZ1)
   {
      point1.setX1((float)aX1);
      point1.setY1((float)aY1);
      point1.setZ1((float)aZ1);
   }
   public Pnt getPoint0()
   {
      return point0;
   }
   public Pnt getPoint1()
   {
      return point1;
   }
   public Pnt[] getPoints()
   {
      return point;
   }
}
