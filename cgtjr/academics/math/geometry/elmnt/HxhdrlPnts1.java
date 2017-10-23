/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.elmnt;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.*;


public class HxhdrlPnts1// extends Nodes
{
/*
   private Point points[];
   private boolean hasNulls;
   public HxhdrlPnts(Point myPoint[])
   {
       points = myPoint;
   }
   public HxhdrlPnts()
   {
   }
   public static HxhdrlPnts crtHxhdrlPnts(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4)
   {        
      HxhdrlPnts aHxhdrlPnt = null;
      if(QuadPnts.isPlaneXY(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = xy");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         Point aPoint[] = addPntsCClckXY(myPoint1,myPoint2,myPoint3,myPoint4);
         aHxhdrlPnt = new HxhdrlPnts(aPoint);
      }else if(QuadPnts.isPlaneYZ(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = yz");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         Point aPoint[] = addPntsCClckYZ(myPoint1,myPoint2,myPoint3,myPoint4);
         aHxhdrlPnt = new HxhdrlPnts(aPoint);
      }else if(QuadPnts.isPlaneZX(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = zx");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         Point aPoint[] = addPntsCClckZX(myPoint1,myPoint2,myPoint3,myPoint4);
         aHxhdrlPnt = new HxhdrlPnts(aPoint);
      }
      return aHxhdrlPnt;
   }
   public boolean getHasNulls()
   {
      return hasNulls;
   }
   public void setHasNulls(boolean myHasNulls)
   {
      hasNulls = myHasNulls;
   }
   public static void extrctPntsCClckXY(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4,Point point[])
   {
      point[4] = myPoint1.getNegZDrctn();
      point[5] = myPoint2.getNegZDrctn();
      point[6] = myPoint3.getNegZDrctn();
      point[7] = myPoint4.getNegZDrctn();
      setHasNulls(point[4],point[5],point[6],point[7]);
   }
   public static void extrctPntsCClckYZ(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4,Point point[])
   {
      point[0] = myPoint1.getNegXDrctn();
      point[4] = myPoint2.getNegXDrctn();
      point[7] = myPoint3.getNegXDrctn();
      point[3] = myPoint4.getNegXDrctn();
      setHasNulls(point[0],point[4],point[7],point[3]);
   }
   public static void extrctPntsCClckZX(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4,Point point[])
   {
      point[0] = myPoint1.getNegYDrctn();
      point[1] = myPoint2.getNegYDrctn();
      point[5] = myPoint3.getNegYDrctn();
      point[4] = myPoint4.getNegYDrctn();
      setHasNulls(point[0],point[1],point[5],point[4]);
      setHasNulls(myPoint1,myPoint2,myPoint3,myPoint4);
   }
   public void setHasNulls(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4)
   {
      if(myPoint1 == null || myPoint2 == null || myPoint3 == null || myPoint4 == null)
      {
         setHasNulls(true);
      }
   }

   public static Point[] addPntsCClckXY(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4)
   { 
      Point point[] = new Point[8];
      Point point0 = myPoint1;
      Point point1 = myPoint2;
      Point point2 = myPoint3;
      Point point3 = myPoint4;

      if(myPoint1.getX1() < myPoint2.getX1() && myPoint1.getY1() < myPoint4.getY1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[0] = point0;
         point[1] = point1;
         point[2] = point2;
         point[3] = point3;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[0]=point0;
         point[1]=point3;
         point[2]=point2;
         point[3]=point1;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[0]=point1;
         point[1]=point0;
         point[2]=point3;
         point[3]=point2;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[0]=point1;
         point[1]=point2;
         point[2]=point3;
         point[3]=point0;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getY1() < myPoint2.getY1() )
      {
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[0]=point2;
         point[1]=point3;
         point[2]=point0;
         point[3]=point1;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getX1() < myPoint2.getX1() )
      {
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[0]=point2;
         point[1]=point1;
         point[2]=point0;
         point[3]=point3;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[0]=point3;
         point[1]=point0;
         point[2]=point1;
         point[3]=point2;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[0]=point3;
         point[1]=point2;
         point[2]=point1;
         point[3]=point0;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3],point);
      }
      return point;
   }
   public static Point[] addPntsCClckZX(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4)
   {
      Point point[] = new Point[8];
      Point point0 = myPoint1;
      Point point1 = myPoint2;
      Point point2 = myPoint3;
      Point point3 = myPoint4;

      if(myPoint1.getX1() < myPoint2.getX1() && myPoint1.getZ1() > myPoint4.getZ1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         //System.out.println("HxhdrlPnts: test1");
         point[3] = point0;
         point[2] = point1;
         point[6] = point2;
         point[7] = point3;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println("HxhdrlPnts: test2");
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[3]=point0;
         point[2]=point3;
         point[6]=point2;
         point[7]=point1;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[3]=point1;
         point[2]=point0;
         point[6]=point3;
         point[7]=point2;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getX1() < myPoint3.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test4");
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[3]=point1;
         point[2]=point2;
         point[6]=point3;
         point[7]=point0;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test5");
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[3]=point2;
         point[2]=point3;
         point[6]=point0;
         point[7]=point1;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getX1() < myPoint2.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test6");
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[3]=point2;
         point[2]=point1;
         point[6]=point0;
         point[7]=point3;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                           //System.out.println("HxhdrlPnts: test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[3]=point3;
         point[2]=point0;
         point[6]=point1;
         point[7]=point2;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getX1() < myPoint3.getX1() )
      {
                          // System.out.println("HxhdrlPnts: test8");
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[3]=point3;
         point[2]=point2;
         point[6]=point1;
         point[7]=point0;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7],point);
      }
      return point;
   }
   public static Point[] addPntsCClckYZ(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4)
   {
      Point point[] = new Point[8];
      if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getZ1() > myPoint4.getZ1())
      {
         //System.out.println("test1");
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[1] = myPoint1;
         point[5] = myPoint4;
         point[6] = myPoint3;
         point[2] = myPoint2;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getY1() < myPoint4.getY1() )
      {
         //System.out.println(myPoint1+","+point4+","+point3+","+point2);
         //System.out.println("test2");
         point[1]=myPoint1;
         point[5]=myPoint2;
         point[6]=myPoint3;
         point[2]=myPoint4;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[1]=myPoint2;
         point[5]=myPoint3;
         point[6]=myPoint4;
         point[2]=myPoint1;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test4");
         //System.out.println(point2+","+point3+","+point4+","+myPoint1);
         point[1]=myPoint2;
         point[5]=myPoint1;
         point[6]=myPoint4;
         point[2]=myPoint3;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("test5");
         //System.out.println(point3+","+point4+","+myPoint1+","+point2);
         point[1]=myPoint3;
         point[5]=myPoint2;
         point[6]=myPoint1;
         point[2]=myPoint4;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getY1() < myPoint2.getY1() )
      {
                  //System.out.println("test6");
         //System.out.println(point3+","+point2+","+myPoint1+","+point4);
         point[1]=myPoint3;
         point[5]=myPoint4;
         point[6]=myPoint1;
         point[2]=myPoint2;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[1]=myPoint4;
         point[5]=myPoint3;
         point[6]=myPoint2;
         point[2]=myPoint1;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test8");
         //System.out.println(point4+","+point3+","+point2+","+myPoint1);
         point[1]=myPoint4;
         point[5]=myPoint1;
         point[6]=myPoint2;
         point[2]=myPoint3;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2],point);
      }
      return point;
   }
   public String toString()
   {
      String info ="HxhdrlPnts -------------------\n"+
              "0="+points[0]+"\n"+
              "1="+points[1]+"\n"+
              "2="+points[2]+"\n"+
              "3="+points[3]+"\n"+
              "4="+points[4]+"\n"+
              "5="+points[5]+"\n"+
              "6="+points[6]+"\n"+
              "7="+points[7];
      return info;
   }
   public Point[] getPoints()
   {
      return points;
   }
   public void setPoints(Point myPoint[])
   {
       points = myPoint;
   }
 */
}