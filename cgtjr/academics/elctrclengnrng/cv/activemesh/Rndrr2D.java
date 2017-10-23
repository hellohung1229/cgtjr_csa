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
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.elmnt.LinePnts;


public class Rndrr2D
{
   private int width;
   private int height;
   private int imageData[];

   public Rndrr2D()
   {      
   }
   public Rndrr2D(int myImageData[],int myWidth,int myHeight)
   {
      imageData = myImageData;
      width = myWidth;
      height = myHeight;
   }
   public void setWidth(int myWidth)
   {
      width = myWidth;
   }
   public void setHeight(int myHeight)
   {
      height = myHeight;
   }
   public void setImageData(int myImageData[])
   {
      imageData = myImageData;
   }
   public int getWidth()
   {
      return width;
   }
   public int getHeight()
   {
      return height;
   }
   public int[] getImageData()
   {
      return imageData;
   }
   public void drawTest()
   {
      Pnt aPnt1 = new Pnt(10,20,0);
      Pnt aPnt2 = new Pnt(50,70,0);
      Pnt aPnt3 = new Pnt(60,70,0);
      Pnt aPnt4 = new Pnt(25,120,0);

      Pnt aPnt5 = new Pnt(90,170,0);
      Pnt aPnt6 = new Pnt(125,120,0);

      Pnt aPnt7 = new Pnt(80,130,0);
      Pnt aPnt8 = new Pnt(80,40,0);

      LinePnts aLinePnts1 = new LinePnts(aPnt1,aPnt2);
      LinePnts aLinePnts2 = new LinePnts(aPnt3,aPnt4);
      LinePnts aLinePnts3 = new LinePnts(aPnt5,aPnt6);
      LinePnts aLinePnts4 = new LinePnts(aPnt7,aPnt8);

      Pnt aPnt12 = new Pnt(100,130,0);
      Pnt aPnt13 = new Pnt(100,40,0);
      LinePnts aLinePnts5 = new LinePnts(aPnt12,aPnt13);

      draw(aLinePnts1);
      draw(aLinePnts2);
      draw(aLinePnts3);
      draw(aLinePnts4);
      draw(aLinePnts5);
   }
   public void draw(LinePnts myLinePnts)
   {
      Pnt aPnt = new Pnt();
      double i;
      double j;
      Pnt aPnt1 = myLinePnts.getPoint0();
      Pnt aPnt2 = myLinePnts.getPoint1();
      double aDistance = PntTool.getDistance(aPnt1,aPnt2);
      if(aDistance > 0)
      {
         //System.out.println("p1 = "+aPnt1.toString()+", p2 = "+aPnt2.toString()+", distance = "+aDistance);
         //System.out.println("p1 name = "+aPnt1.getName()+", p2 name = "+aPnt2.getName());
      }
      double x2MinusX1 = aPnt2.getX1() - aPnt1.getX1();
      //System.out.println("Rndrr2D x2 - x1 = "+x2MinusX1);
      double y = 0;
      aPnt.setColor(0x00ff00);

      if(x2MinusX1 == 0)
      {
         if(aPnt2.getY1() >= aPnt1.getY1())
         {
            for(j = aPnt1.getY1();j<=aPnt2.getY1();j++)
            {
               aPnt.setY1((float)j);
               aPnt.setX1(aPnt1.getX1());
               draw(aPnt);
               //aPnt.drawPnt(myData,myWidth,myHeight);
            }
         }else{
            for(j = aPnt2.getY1(); j<=aPnt1.getY1();j++)
            {
               aPnt.setY1((float)j);
               aPnt.setX1(aPnt1.getX1());
               draw(aPnt);
            }
         }
      }else{
         double anIncrmnt = x2MinusX1/aDistance;
         double slope = (aPnt2.getY1() - aPnt1.getY1())/(aPnt2.getX1() - aPnt1.getX1());
         //System.out.println("Rndrr2D: slope = "+slope);
         if(aPnt2.getX1() >= aPnt1.getX1())
         {
            for(i = aPnt1.getX1(); i<= aPnt2.getX1(); i=i+anIncrmnt)
            {
               //xDifference = i - aPnt1.getX1();
               y = getYCoordinate(slope,i-aPnt1.getX1(),aPnt1.getY1());
               aPnt.setY1((float)y);
               aPnt.setX1((float)i);
               draw(aPnt);
            }
         }else{
            for(i = aPnt1.getX1(); i>= aPnt2.getX1(); i=i+anIncrmnt)
            {
               //xDifference = i - aPnt1.getX1();
               y = getYCoordinate(slope,i-aPnt1.getX1(),aPnt1.getY1());
               aPnt.setY1((float)y);
               aPnt.setX1((float)i);
               draw(aPnt);
            }
         }
      }
   }
   public double getYCoordinate(double mySlope,double myX,double myB)
   {
      double y = mySlope*myX+myB;
      return y;
   }
   public void draw(Pnt myPnt)
   {
      int aColor = myPnt.getColor();
      int aXPos = (int)myPnt.getX1();
      int aYPos = (int)myPnt.getY1();
      int anIndex = rtrvImgIndx(aXPos,aYPos);
      //System.out.println("Rndrr2D: x = "+aXPos+", aYPos = "+aYPos+", index = "+anIndex);
      try
      {
         imageData[anIndex] = aColor;
      }catch(ArrayIndexOutOfBoundsException aio){
         System.out.println(aio.getMessage());
      }
   }
   public int rtrvImgIndx(int myX,int myY)
   {
      int aWidth = getWidth();
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getWidth();
      return y;
   }
}