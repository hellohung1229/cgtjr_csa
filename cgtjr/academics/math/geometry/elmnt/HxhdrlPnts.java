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

public class HxhdrlPnts extends Nodes
{
   QuadPnts rctnglPnts0;
   QuadPnts rctnglPnts1;
   QuadPnts rctnglPnts2;
   QuadPnts rctnglPnts3;
   QuadPnts rctnglPnts4;
   QuadPnts rctnglPnts5;

   private Pnt point[];
   private Pnt point0;
   private Pnt point1;
   private Pnt point2;
   private Pnt point3;
   private Pnt point4;
   private Pnt point5;
   private Pnt point6;
   private Pnt point7;
   private boolean hasNulls;

   public HxhdrlPnts()
   {
    
      point = new Pnt[8];
      /*
      rctnglPnts0 = new QuadPnts();
      rctnglPnts1 = new QuadPnts();
      rctnglPnts2 = new QuadPnts();
      rctnglPnts3 = new QuadPnts();
      rctnglPnts4 = new QuadPnts();
      rctnglPnts5 = new QuadPnts();
      rctnglPnts0.addPntsCClck(point[0],point[1],point[2],point[3]);
      rctnglPnts1.addPntsCClck(point[4],point[5],point[6],point[7]);
      rctnglPnts2.addPntsCClck(point[5],point[6],point[2],point[1]);
      rctnglPnts3.addPntsCClck(point[0],point[3],point[7],point[4]);
      rctnglPnts4.addPntsCClck(point[7],point[3],point[2],point[6]);
      rctnglPnts5.addPntsCClck(point[4],point[0],point[1],point[5]);
      */
   }
   public void crtHxhdrlPnts(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {        
      if(QuadPnts.isPlaneXY(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = xy");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckXY(myPoint1,myPoint2,myPoint3,myPoint4);
      }else if(QuadPnts.isPlaneYZ(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = yz");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckYZ(myPoint1,myPoint2,myPoint3,myPoint4);
      }else if(QuadPnts.isPlaneZX(myPoint1, myPoint2, myPoint3, myPoint4))
      {
         //System.out.println("Hxhdrl: plane = zx");
         //System.out.println(myPoint1+","+myPoint2+","+myPoint3+","+myPoint4);
         addPntsCClckZX(myPoint1,myPoint2,myPoint3,myPoint4);
      }      
   }
   public boolean getHasNulls()
   {
      return hasNulls;
   }
   public void setHasNulls(boolean myHasNulls)
   {
      hasNulls = myHasNulls;
   }
   public void extrctPntsCClckXY(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point[4] = myPoint1.getNegZDrctn();
      point[5] = myPoint2.getNegZDrctn();
      point[6] = myPoint3.getNegZDrctn();
      point[7] = myPoint4.getNegZDrctn();
      setHasNulls(point[4],point[5],point[6],point[7]);
   }
   public void extrctPntsCClckYZ(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point[0] = myPoint1.getNegXDrctn();
      point[4] = myPoint2.getNegXDrctn();
      point[7] = myPoint3.getNegXDrctn();
      point[3] = myPoint4.getNegXDrctn();
      setHasNulls(point[0],point[4],point[7],point[3]);
   }
   public void extrctPntsCClckZX(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point[0] = myPoint1.getNegYDrctn();
      point[1] = myPoint2.getNegYDrctn();
      point[5] = myPoint3.getNegYDrctn();
      point[4] = myPoint4.getNegYDrctn();
      setHasNulls(point[0],point[1],point[5],point[4]);
      setHasNulls(myPoint1,myPoint2,myPoint3,myPoint4);
   }
   public void setHasNulls(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      if(myPoint1 == null || myPoint2 == null || myPoint3 == null || myPoint4 == null)
      {
         setHasNulls(true);
      }
   }

   public void addPntsCClckXY(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      point0 = myPoint1;
      point1 = myPoint2;
      point2 = myPoint3;
      point3 = myPoint4;

      if(myPoint1.getX1() < myPoint2.getX1() && myPoint1.getY1() < myPoint4.getY1())
      {
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[0] = point0;
         point[1] = point1;
         point[2] = point2;
         point[3] = point3;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[0]=point0;
         point[1]=point3;
         point[2]=point2;
         point[3]=point1;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[0]=point1;
         point[1]=point0;
         point[2]=point3;
         point[3]=point2;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[0]=point1;
         point[1]=point2;
         point[2]=point3;
         point[3]=point0;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getY1() < myPoint2.getY1() )
      {
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[0]=point2;
         point[1]=point3;
         point[2]=point0;
         point[3]=point1;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getX1() < myPoint2.getX1() )
      {
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[0]=point2;
         point[1]=point1;
         point[2]=point0;
         point[3]=point3;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getY1() < myPoint3.getY1() )
      {
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[0]=point3;
         point[1]=point0;
         point[2]=point1;
         point[3]=point2;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getX1() < myPoint3.getX1() )
      {
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[0]=point3;
         point[1]=point2;
         point[2]=point1;
         point[3]=point0;
         extrctPntsCClckXY(point[0],point[1],point[2],point[3]);
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
         point[3] = point0;
         point[2] = point1;
         point[6] = point2;
         point[7] = point3;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getX1() < myPoint4.getX1() )
      {
         //System.out.println("HxhdrlPnts: test2");
         //System.out.println(point1+","+point4+","+point3+","+point2);
         point[3]=point0;
         point[2]=point3;
         point[6]=point2;
         point[7]=point1;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint2.getX1() < myPoint1.getX1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[3]=point1;
         point[2]=point0;
         point[6]=point3;
         point[7]=point2;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getX1() < myPoint3.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test4");
         //System.out.println(point2+","+point3+","+point4+","+point1);
         point[3]=point1;
         point[2]=point2;
         point[6]=point3;
         point[7]=point0;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint3.getX1() < myPoint4.getX1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("HxhdrlPnts: test5");
         //System.out.println(point3+","+point4+","+point1+","+point2);
         point[3]=point2;
         point[2]=point3;
         point[6]=point0;
         point[7]=point1;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getX1() < myPoint2.getX1() )
      {
                  //System.out.println("HxhdrlPnts: test6");
         //System.out.println(point3+","+point2+","+point1+","+point4);
         point[3]=point2;
         point[2]=point1;
         point[6]=point0;
         point[7]=point3;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint4.getX1() < myPoint1.getX1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                           //System.out.println("HxhdrlPnts: test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[3]=point3;
         point[2]=point0;
         point[6]=point1;
         point[7]=point2;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getX1() < myPoint3.getX1() )
      {
                          // System.out.println("HxhdrlPnts: test8");
         //System.out.println(point4+","+point3+","+point2+","+point1);
         point[3]=point3;
         point[2]=point2;
         point[6]=point1;
         point[7]=point0;
         extrctPntsCClckZX(point[3],point[2],point[6],point[7]);
      }
   }
   /*
   public Point rtrvLeastY(Point myPoint1,Point myPoint2,Point myPoint3,Point myPoint4))
   {
      rtrvLeastY(myPoint1,myPoint2);
   }
   public Point rtrvLeastY(Point myPoint1,Point myPoint2)
   {
      Point aPoint = null;
      if(myPoint1.getY1() < myPoint2.getY1())
      {
         aPoint = myPoint1;
      }else if(myPoint1.getY1() > myPoint2.getY1())
      {
         aPoint = myPoint2;
      }
      return aPoint;
   }*/
   public void addPntsCClckYZ(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {

      if(myPoint1.getY1() < myPoint2.getY1() && myPoint1.getZ1() > myPoint4.getZ1())
      {
         //System.out.println("test1");
         //System.out.println(point1+","+point2+","+point3+","+point4);
         point[1] = myPoint1;
         point[5] = myPoint4;
         point[6] = myPoint3;
         point[2] = myPoint2;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint1.getZ1() > myPoint2.getZ1() && myPoint1.getY1() < myPoint4.getY1() )
      {
         //System.out.println(myPoint1+","+point4+","+point3+","+point2);
         //System.out.println("test2");
         point[1]=myPoint1;
         point[5]=myPoint2;
         point[6]=myPoint3;
         point[2]=myPoint4;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint2.getY1() < myPoint1.getY1() && myPoint2.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test3");
         //System.out.println(point2+","+point1+","+point4+","+point3);
         point[1]=myPoint2;
         point[5]=myPoint3;
         point[6]=myPoint4;
         point[2]=myPoint1;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint2.getZ1() > myPoint1.getZ1() && myPoint2.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test4");
         //System.out.println(point2+","+point3+","+point4+","+myPoint1);
         point[1]=myPoint2;
         point[5]=myPoint1;
         point[6]=myPoint4;
         point[2]=myPoint3;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint3.getY1() < myPoint4.getY1() && myPoint3.getZ1() > myPoint2.getZ1() )
      {
                  //System.out.println("test5");
         //System.out.println(point3+","+point4+","+myPoint1+","+point2);
         point[1]=myPoint3;
         point[5]=myPoint2;
         point[6]=myPoint1;
         point[2]=myPoint4;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint3.getZ1() > myPoint4.getZ1() && myPoint3.getY1() < myPoint2.getY1() )
      {
                  //System.out.println("test6");
         //System.out.println(point3+","+point2+","+myPoint1+","+point4);
         point[1]=myPoint3;
         point[5]=myPoint4;
         point[6]=myPoint1;
         point[2]=myPoint2;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint4.getY1() < myPoint1.getY1() && myPoint4.getZ1() > myPoint3.getZ1() )
      {
                  //System.out.println("test7");
         //System.out.println(point4+","+point1+","+point2+","+point3);
         point[1]=myPoint4;
         point[5]=myPoint3;
         point[6]=myPoint2;
         point[2]=myPoint1;
         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }else if(myPoint4.getZ1() > myPoint1.getZ1() && myPoint4.getY1() < myPoint3.getY1() )
      {
                  //System.out.println("test8");
         //System.out.println(point4+","+point3+","+point2+","+myPoint1);
         point[1]=myPoint4;
         point[5]=myPoint1;
         point[6]=myPoint2;
         point[2]=myPoint3;

         extrctPntsCClckYZ(point[1],point[5],point[6],point[2]);
      }
   }
   public String toString()
   {
      String info ="HxhdrlPnts -------------------\n"+
              "0="+point[0]+"\n"+
              "1="+point[1]+"\n"+
              "2="+point[2]+"\n"+
              "3="+point[3]+"\n"+
              "4="+point[4]+"\n"+
              "5="+point[5]+"\n"+
              "6="+point[6]+"\n"+
              "7="+point[7];
      return info;
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
   public Pnt getPoint4()
   {
      return point[4];
   }
   public Pnt getPoint5()
   {
      return point[5];
   }
   public Pnt getPoint6()
   {
      return point[6];
   }   
   public Pnt getPoint7()
   {
      return point[7];
   }
   public Pnt[] getPoints()
   {
      return point;
   }
   public static void prntGlblNmbrs(Vector myVector)
   {
      prntGlblNmbrs(myVector,null);
   }
   public static void prntGlblNmbrs(Vector myVector,JEditorPane myJEditorPane)
   {
      HxhdrlPnts aHxhdrlPnts = null;
      Pnt aPoint0 = null;
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;
      Pnt aPoint3 = null;
      Pnt aPoint4 = null;
      Pnt aPoint5 = null;
      Pnt aPoint6 = null;
      Pnt aPoint7 = null;
      String info = "";
      
      //System.out.println("HxhdrlPnts.prntGlblNmbrs(): test = ");
      int aSize = myVector.size();
      //System.out.println("HxhdrlPnts.prntGlblNmbrs(): size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnts = (HxhdrlPnts)myVector.elementAt(i);
         aPoint0 = aHxhdrlPnts.getPoint0();
         aPoint1 = aHxhdrlPnts.getPoint1();
         aPoint2 = aHxhdrlPnts.getPoint2();
         aPoint3 = aHxhdrlPnts.getPoint3();
         aPoint4 = aHxhdrlPnts.getPoint4();
         aPoint5 = aHxhdrlPnts.getPoint5();
         aPoint6 = aHxhdrlPnts.getPoint6();
         aPoint7 = aHxhdrlPnts.getPoint7();
         if(QuadPnts.isNullVl(aPoint0,aPoint1,aPoint1,aPoint3) == false ||
            QuadPnts.isNullVl(aPoint4,aPoint5,aPoint6,aPoint7) == false )
         {
            info = i+","+aPoint0.getCounter()+","+aPoint1.getCounter()+","+aPoint2.getCounter()+","+aPoint3.getCounter()+","+aPoint4.getCounter()+","+aPoint5.getCounter()+","+aPoint6.getCounter()+","+aPoint7.getCounter();
            if(myJEditorPane != null)
            {
               myJEditorPane.setText(myJEditorPane.getText()+info+"\n");
            }else{
               System.out.println(info);
            }
         }

      }
   }
   /*
   public QuadPnts rtrvRctnglPnts0()
   {
      return rctnglPnts0;
   }
   public QuadPnts rtrvRctnglPnts1()
   {
      return rctnglPnts1;
   }
   public QuadPnts rtrvRctnglPnts2()
   {
      return rctnglPnts2;
   }
   public QuadPnts rtrvRctnglPnts3()
   {
      return rctnglPnts3;
   }
   public QuadPnts rtrvRctnglPnts4()
   {
      return rctnglPnts4;
   }
   public QuadPnts rtrvRctnglPnts5()
   {
      return rctnglPnts5;
   }*/
   /*
   public Vector rtrvRctnglPnts(Vector myHxhdrlVctr)
   {
      Vector rctnglVctr = new Vector();
      QuadPnts aRctnglPnts0 = null;
      QuadPnts aRctnglPnts1 = null;
      QuadPnts aRctnglPnts2 = null;
      QuadPnts aRctnglPnts3 = null;
      QuadPnts aRctnglPnts4 = null;
      QuadPnts aRctnglPnts5 = null;

      HxhdrlPnts aHxhdrlPnts = null;
      int aSize = myHxhdrlVctr.size();
      
      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnts = (HxhdrlPnts)myHxhdrlVctr.elementAt(i);
         aRctnglPnts0 = aHxhdrlPnts.rtrvRctnglPnts0();
         aRctnglPnts1 = aHxhdrlPnts.rtrvRctnglPnts1();
         aRctnglPnts2 = aHxhdrlPnts.rtrvRctnglPnts2();         
         aRctnglPnts3 = aHxhdrlPnts.rtrvRctnglPnts3();
         aRctnglPnts4 = aHxhdrlPnts.rtrvRctnglPnts4();
         aRctnglPnts5 = aHxhdrlPnts.rtrvRctnglPnts5();
         rctnglVctr.add(aRctnglPnts0);
         rctnglVctr.add(aRctnglPnts1);
         rctnglVctr.add(aRctnglPnts2);
         rctnglVctr.add(aRctnglPnts3);
         rctnglVctr.add(aRctnglPnts4);
         rctnglVctr.add(aRctnglPnts5);
      }
      return rctnglVctr;
   }*/
   /*
   public Vector rtrvSrfcRctngls(Vector myHxhdrlVctr)
   {
      Vector rctnglVctr = new Vector();
      QuadPnts aRctnglPnts0 = null;
      QuadPnts aRctnglPnts1 = null;
      QuadPnts aRctnglPnts2 = null;
      QuadPnts aRctnglPnts3 = null;
      QuadPnts aRctnglPnts4 = null;
      QuadPnts aRctnglPnts5 = null;

      HxhdrlPnts aHxhdrlPnts = null;
      int aSize = myHxhdrlVctr.size();

      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnts = (HxhdrlPnts)myHxhdrlVctr.elementAt(i);
         aRctnglPnts0 = aHxhdrlPnts.rtrvRctnglPnts0();
         aRctnglPnts1 = aHxhdrlPnts.rtrvRctnglPnts1();
         aRctnglPnts2 = aHxhdrlPnts.rtrvRctnglPnts2();
         aRctnglPnts3 = aHxhdrlPnts.rtrvRctnglPnts3();
         aRctnglPnts4 = aHxhdrlPnts.rtrvRctnglPnts4();
         aRctnglPnts5 = aHxhdrlPnts.rtrvRctnglPnts5();
         if(aRctnglPnts0.isSrfcRctngl())
         {
            rctnglVctr.add(aRctnglPnts0);
         }
         if(aRctnglPnts1.isSrfcRctngl())
         {
            rctnglVctr.add(aRctnglPnts1);
         }
         if(aRctnglPnts2.isSrfcRctngl())
         {
             rctnglVctr.add(aRctnglPnts2);
         }
         if(aRctnglPnts3.isSrfcRctngl())
         {
             rctnglVctr.add(aRctnglPnts3);
         }
         if(aRctnglPnts4.isSrfcRctngl())
         {
            rctnglVctr.add(aRctnglPnts4);
         }
         if(aRctnglPnts5.isSrfcRctngl())
         {
            rctnglVctr.add(aRctnglPnts5);
         }
      }
      return rctnglVctr;
   }*/
   /*
   public Vector rtrvIntrrHxhdrl(Vector myHxhdrlVctr)
   {
      Vector hxhdrlVctr = new Vector();
      QuadPnts aRctnglPnts0 = null;
      QuadPnts aRctnglPnts1 = null;
      QuadPnts aRctnglPnts2 = null;
      QuadPnts aRctnglPnts3 = null;
      QuadPnts aRctnglPnts4 = null;
      QuadPnts aRctnglPnts5 = null;

      HxhdrlPnts aHxhdrlPnts = null;
      int aSize = myHxhdrlVctr.size();

      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnts = (HxhdrlPnts)myHxhdrlVctr.elementAt(i);
         aRctnglPnts0 = aHxhdrlPnts.rtrvRctnglPnts0();
         aRctnglPnts1 = aHxhdrlPnts.rtrvRctnglPnts1();
         aRctnglPnts2 = aHxhdrlPnts.rtrvRctnglPnts2();
         aRctnglPnts3 = aHxhdrlPnts.rtrvRctnglPnts3();
         aRctnglPnts4 = aHxhdrlPnts.rtrvRctnglPnts4();
         aRctnglPnts5 = aHxhdrlPnts.rtrvRctnglPnts5();
         if(!aRctnglPnts0.isSrfcRctngl() && !aRctnglPnts1.isSrfcRctngl() &&
            !aRctnglPnts2.isSrfcRctngl() && !aRctnglPnts3.isSrfcRctngl() &&
            !aRctnglPnts4.isSrfcRctngl() && !aRctnglPnts5.isSrfcRctngl())
         {
             hxhdrlVctr.add(aHxhdrlPnts);
         }
      }
      return hxhdrlVctr;
   }*/
}