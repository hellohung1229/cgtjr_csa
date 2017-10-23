/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.elmnt;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.lnralgbra.MathVector;
import java.util.Vector;
import javax.swing.JEditorPane;

public class QuadPnts extends Nodes
{
   Pnt point[];
   Pnt point0;
   Pnt point1;
   Pnt point2;
   Pnt point3;

   
   public QuadPnts()
   {
      point = new Pnt[4];
      point[0] = point0;
      point[1] = point1;
      point[2] = point2;
      point[3] = point3;
      /*
      point1 = new Point();
      point2 = new Point();
      point3 = new Point();
      point4 = new Point();
      point1.connectVertices(point2);
      point2.connectVertices(point3);
      point3.connectVertices(point4);
      point4.connectVertices(point1);
      addVertex(point1);
      addVertex(point2);
      addVertex(point3);
      addVertex(point4);
      */
   }
   public QuadPnts(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point0 = myPoint1;
      point1 = myPoint2;
      point2 = myPoint3;
      point3 = myPoint4;
      point = new Pnt[4];
      point[0] = point0;
      point[1] = point1;
      point[2] = point2;
      point[3] = point3;

      //addVertex(point1);
      //addVertex(point2);
      //addVertex(point3);
      //addVertex(point4);
      //System.out.println(point1);
      //System.out.println(point2);
      //System.out.println(point3);
      //System.out.println(point4);
   }
   public void addPntsCClck(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      if(QuadPnts.isPlaneXY(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("QuadPnts: plane = xy");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckXY(myPoint1,myPoint2,myPoint3,myPoint4);
      }else if(QuadPnts.isPlaneYZ(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("QuadPnts: plane = yz");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckYZ(myPoint1,myPoint2,myPoint3,myPoint4);
      }else if(QuadPnts.isPlaneZX(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("QuadPnts: plane = zx");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckZX(myPoint1,myPoint2,myPoint3,myPoint4);
      }
      float aNormal[] = MathVector.crssPrdctFlt(myPoint1,myPoint2,myPoint1,myPoint3);
      setNormal(aNormal);
   }
   public void addPntsCClckYZ(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
      if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getZ1() > myPoint4.getZ1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[0] = myPoint1;
         point[1] = myPoint4;
         point[2] = myPoint3;
         point[3] = myPoint2;
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getY1() < myPoint4.getY1() )
      {
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         point[0]=myPoint1;
         point[1]=myPoint2;
         point[2]=myPoint3;
         point[3]=myPoint4;
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[0]=myPoint2;
         point[1]=myPoint3;
         point[2]=myPoint4;
         point[3]=myPoint1;
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test4");
         //System.out.println(point2+","+point3+","+point4+","+myPoint1);
         point[0]=myPoint2;
         point[1]=myPoint1;
         point[2]=myPoint4;
         point[3]=myPoint3;
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("test5");
         //System.out.println(point3+","+point4+","+myPoint1+","+point2);
         point[0]=myPoint3;
         point[1]=myPoint2;
         point[2]=myPoint1;
         point[3]=myPoint4;
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getY1() < myPoint2.getY1() )
      {
                  //System.out.println("test6");
         //System.out.println(point3+","+point2+","+myPoint1+","+point4);
         point[0]=myPoint3;
         point[1]=myPoint4;
         point[2]=myPoint1;
         point[3]=myPoint2;
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[0]=myPoint4;
         point[1]=myPoint3;
         point[2]=myPoint2;
         point[3]=myPoint1;
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test8");
         //System.out.println(point4+","+point3+","+point2+","+myPoint1);
         point[0]=myPoint4;
         point[1]=myPoint1;
         point[2]=myPoint2;
         point[3]=myPoint3;
      }
   }

   public void addPntsCClckXY(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point0 = myPoint1;
      point1 = myPoint2;
      point2 = myPoint3;
      point3 = myPoint4;
      //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
      if(myPoint1.getX1() < myPoint2.getX1() && myPoint1.getY1() < myPoint4.getY1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[0] = point0;
         point[1] = point1;
         point[2] = point2;
         point[3] = point3;
      }else if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[0]=point0;
         point[1]=point3;
         point[2]=point2;
         point[3]=point1;
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[0]=point1;
         point[1]=point0;
         point[2]=point3;
         point[3]=point2;
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[0]=point1;
         point[1]=point2;
         point[2]=point3;
         point[3]=point0;
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getY1() < myPoint2.getY1() )
      {
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[0]=point2;
         point[1]=point3;
         point[2]=point0;
         point[3]=point1;
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getX1() < myPoint2.getX1() )
      {
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[0]=point2;
         point[1]=point1;
         point[2]=point0;
         point[3]=point3;
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[0]=point3;
         point[1]=point0;
         point[2]=point1;
         point[3]=point2;
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[0]=point3;
         point[1]=point2;
         point[2]=point1;
         point[3]=point0;
      }
   }
   public void addPntsCClckZX(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point0 = myPoint1;
      point1 = myPoint2;
      point2 = myPoint3;
      point3 = myPoint4;

      if(myPoint1.getX1() < myPoint2.getX1() && myPoint1.getZ1() > myPoint4.getZ1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         //System.out.println("HxhdrlPnts: test1");
         point[0] = point0;
         point[1] = point1;
         point[2] = point2;
         point[3] = point3;
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println("HxhdrlPnts: test2");
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[0]=point0;
         point[1]=point3;
         point[2]=point2;
         point[3]=point1;
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[0]=point1;
         point[1]=point0;
         point[2]=point3;
         point[3]=point2;
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getX1() < myPoint3.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test4");
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[0]=point1;
         point[1]=point2;
         point[2]=point3;
         point[3]=point0;
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test5");
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[0]=point2;
         point[1]=point3;
         point[2]=point0;
         point[3]=point1;
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getX1() < myPoint2.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test6");
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[0]=point2;
         point[1]=point1;
         point[2]=point0;
         point[3]=point3;
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                           //System.out.println("HxhdrlPnts: test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[0]=point3;
         point[1]=point0;
         point[2]=point1;
         point[3]=point2;
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getX1() < myPoint3.getX1() )
      {
                          // System.out.println("HxhdrlPnts: test8");
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[0]=point3;
         point[1]=point2;
         point[2]=point1;
         point[3]=point0;
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
   public static boolean isPlaneXY(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      boolean isPlnXY = false;
      if(isNullVl(myPoint1,myPoint2,myPoint3,myPoint4)){
         isPlnXY = false;
         return isPlnXY;
      }
      //LineVector aLineVector1 = new LineVector(myPoint1,myPoint2);
      //LineVector aLineVector2 = new LineVector(myPoint1,myPoint3);
      float aLineVector3[] = MathVector.crssPrdctFlt(myPoint1,myPoint2,myPoint1,myPoint3);
      double aMgntd = MathVector.getMagnitude(aLineVector3);
      double aZVl = aLineVector3[2];

      if(aMgntd != 0 && aZVl != 0)
      {
         isPlnXY = true;
      }
      
      return isPlnXY;
   }
   public static boolean isPlaneZX(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      boolean isPlnZX = false;
      if(isNullVl(myPoint1,myPoint2,myPoint3,myPoint4)){
         isPlnZX = false;
         return isPlnZX;
      }
      //LineVector aLineVector1 = new LineVector(myPoint1,myPoint2);
      //LineVector aLineVector2 = new LineVector(myPoint1,myPoint3);
      //LineVector aLineVector3 = LineVector.crssPrdctFlt(aLineVector1,aLineVector2);
      float aLineVector3[] = MathVector.crssPrdctFlt(myPoint1,myPoint2,myPoint1,myPoint3);
      double aMgntd = MathVector.getMagnitude(aLineVector3);
      double aYVl = aLineVector3[1];
      
      if(aMgntd != 0 && aYVl != 0)
      {
         isPlnZX = true;
      }
      return isPlnZX;
   }
   public static boolean isPlaneYZ(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      boolean isPlnYZ = false;
      if(isNullVl(myPoint1,myPoint2,myPoint3,myPoint4)){
         isPlnYZ = false;
         return isPlnYZ;
      }
      //LineVector aLineVector1 = new LineVector(myPoint1,myPoint2);
      //LineVector aLineVector2 = new LineVector(myPoint1,myPoint3);
      //LineVector aLineVector3 = LineVector.crssPrdctFlt(aLineVector1,aLineVector2);
      float aLineVector3[] = MathVector.crssPrdctFlt(myPoint1,myPoint2,myPoint1,myPoint3);
      
      double aMgntd = MathVector.getMagnitude(aLineVector3);
      double aXVl = aLineVector3[0];
      if(aMgntd != 0 && aXVl != 0)
      {
         isPlnYZ = true;
      }
      return isPlnYZ;
   }
   public static boolean isCrdntPln(Pnt myPoint1,Pnt myPoint4,Pnt myPoint6,Pnt myPoint5)
   {
      boolean isPlane = false;
      if(PntTool.equalDstnce(myPoint1,myPoint5) || PntTool.equalDstnce(myPoint4,myPoint6) || PntTool.equalDstnce(myPoint1,myPoint6) )
      {
         isPlane = true;
      }
      return isPlane;
   }
   public static String rtrvQuadElmntsStrng(Vector myVector)
   {
      Vector quadElmnts = new Vector();
      String output = "";
      QuadPnts aQuadPnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;      
      Pnt aPoint3 = null;      
      int aSize = myVector.size();
      //System.out.println("QuadPnts.rtrvSrfcBndryElmnts() ... ");
      for(int i=0;i<aSize;i++)
      {
         aQuadPnts = (QuadPnts)myVector.elementAt(i);
         aPoint0 = aQuadPnts.getPoint0();
         aPoint1 = aQuadPnts.getPoint1();
         aPoint2 = aQuadPnts.getPoint2();
         aPoint3 = aQuadPnts.getPoint3();

         output += (i+","+aPoint0.getCounter()+","+aPoint1.getCounter()+","+aPoint2.getCounter()+","+aPoint3.getCounter()+"\n");
      }
      return output;
   }   
   public static void prntGlblNmbrs(Vector myVector)
   {
      prntGlblNmbrs(myVector,null);
   }
   public static void prntGlblNmbrs(Vector myVector,JEditorPane myJEditorPane)
   {
      String info = "";
      QuadPnts aQuadPnts = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      Pnt aPoint4 = null;
      int aSize = myVector.size();
      //System.out.println("QuadPnts: printing global coordinates ... aSize = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aQuadPnts = (QuadPnts)myVector.elementAt(i);
         aPoint1 = aQuadPnts.getPoint0();
         aPoint2 = aQuadPnts.getPoint1();
         aPoint3 = aQuadPnts.getPoint2();
         aPoint4 = aQuadPnts.getPoint3();
         if(isNullVl(aPoint1,aPoint2,aPoint3,aPoint4) == false)
         {
            info = i+","+aPoint1.getCounter()+","+aPoint2.getCounter()+","+aPoint3.getCounter()+","+aPoint4.getCounter();
            if(myJEditorPane != null)
            {
               myJEditorPane.setText(myJEditorPane.getText()+info+"\n");
            }else{
               System.out.println(info);
            }
         }
      }
   }
   public float[] getCenter(){
       float x1 = point[0].getX1();
       float x2 = point[2].getX1();
       float xc = (x2 + x1)/2;
       float y1 = point[0].getY1();
       float y2 = point[2].getY1();
       float yc = (y2 + y1)/2;
       float z1 = point[0].getZ1();
       float z2 = point[2].getZ1();
       float zc = (z2 + z1)/2;
       float c[] = {xc,yc,zc};
       super.setCenter(c);
       return super.getCenter();              
   }
   public Pnt getPoint0()
   {
      return point[0];
   }
   public Pnt getPoint1()
   {
      return point[1];
   }
   public Pnt getPoint2()
   {
      return point[2];
   }   
   public Pnt getPoint3()
   {
      return point[3];
   }
   public Pnt[] getPoints()
   {
      return point;
   }
}