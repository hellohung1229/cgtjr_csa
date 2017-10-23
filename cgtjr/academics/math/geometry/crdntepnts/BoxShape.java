package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.shapebndry.BoundaryShape_I;
import cgtjr.academics.math.geometry.linepnts.Line;
import java.util.*;

public class BoxShape extends ShapePnt implements BoundaryShape_I
{
   private Pnt point1;
   private Pnt point2;
   private Pnt point3;
   private Pnt point4;
   private Pnt point5;
   private Pnt point6;
   private Pnt point7;
   private Pnt point8;
   private float length;    

   public BoxShape()
   {
      point1 = new Pnt(-0.594f,0.628f,-0.046f);
      point1.setColor(0xff00ff);
      point1.setRadius(.1f);
      point2 = new Pnt(0.338f,0.689f,-0.400f );
      point2.setColor(0xff00ff);
      point2.setRadius(.1f);
      point3 = new Pnt(-0.687f,-0.284f,-0.444f);
      point3.setColor(0xff00ff);
      point3.setRadius(.1f);
      point4 = new Pnt(0.246f,  -0.224f,  -0.800f);
      point4.setColor(0xff00ff);
      point4.setRadius(.1f);
      point5 = new Pnt(-0.246f,0.224f,0.800f);
      point5.setColor(0xff00ff);
      point5.setRadius(.1f);
      point6 = new Pnt(0.687f,0.284f,0.444f);
      point6.setColor(0xff00ff);
      point6.setRadius(.1f);
      point7 = new Pnt(-0.339f,-0.689f,0.402f);
      point7.setColor(0xff00ff);
      point7.setRadius(.1f);
      point8 = new Pnt(0.594f,-0.628f,0.046f);
      point8.setColor(0xff00ff);
      point8.setRadius(.1f);
      addVertex(point1);
      addVertex(point2);
      addVertex(point3);
      addVertex(point4);
      addVertex(point5);
      addVertex(point6);
      addVertex(point7);
      addVertex(point8);
   }
   public BoxShape(float myLength)
   {
      length = myLength;
      point1 = new Pnt(0f,0f,0f);
      point2 = new Pnt(myLength,0f,0f);
      point3 = new Pnt(0,myLength,0f);
      point4 = new Pnt(myLength,myLength,0f);
      point5 = new Pnt(0f,0f,myLength);
      point6 = new Pnt(myLength,0f,myLength);
      point7 = new Pnt(0f,myLength,myLength);
      point8 = new Pnt(myLength,myLength,myLength);
      point1.setColor(0xff00ff);
      point1.setRadius(.1f);
      point2.setColor(0xff00ff);
      point2.setRadius(.1f);
      point3.setColor(0xff00ff);
      point3.setRadius(.1f);
      point4.setColor(0xff00ff);
      point4.setRadius(.1f);
      point5.setColor(0xff00ff);
      point5.setRadius(.1f);
      point6.setColor(0xff00ff);
      point6.setRadius(.1f);
      point7.setColor(0xff00ff);
      point7.setRadius(.1f);
      point8.setColor(0xff00ff);
      point8.setRadius(.1f);
      addVertex(point1);
      addVertex(point2);
      addVertex(point3);
      addVertex(point4);
      addVertex(point5);
      addVertex(point6);
      addVertex(point7);
      addVertex(point8);
      //setWidth(myLength);
      //setHeight(myLength);
      //setLength(myLength);
   }
   public BoxShape(float myLengthX,float myLengthY,float myLengthZ)
   {
      point1 = new Pnt(0f,0f,0f);
      point2 = new Pnt(myLengthX,0f,0f);
      point3 = new Pnt(0,myLengthY,0f);
      point4 = new Pnt(myLengthX,myLengthY,0f);
      point5 = new Pnt(0f,0f,myLengthZ);
      point6 = new Pnt(myLengthX,0f,myLengthZ);
      point7 = new Pnt(0f,myLengthY,myLengthZ);
      point8 = new Pnt(myLengthX,myLengthY,myLengthZ);
      point1.setColor(0xff00ff);
      point1.setRadius(.1f);
      point2.setColor(0xff00ff);
      point2.setRadius(.1f);
      point3.setColor(0xff00ff);
      point3.setRadius(.1f);
      point4.setColor(0xff00ff);
      point4.setRadius(.1f);
      point5.setColor(0xff00ff);
      point5.setRadius(.1f);
      point6.setColor(0xff00ff);
      point6.setRadius(.1f);
      point7.setColor(0xff00ff);
      point7.setRadius(.1f);
      point8.setColor(0xff00ff);
      point8.setRadius(.1f);
      addVertex(point1);
      addVertex(point2);
      addVertex(point3);
      addVertex(point4);
      addVertex(point5);
      addVertex(point6);
      addVertex(point7);
      addVertex(point8);
      //setWidth(myLengthX);
      //setHeight(myLengthY);
      //setLength(myLengthZ);
   }
   public BoxShape(float myLengthX,float myLengthY,float myLengthZ,float myOffsetLengthX,float myOffsetLengthY,float myOffsetLengthZ)
   {
      setEnvrnmntVar(myOffsetLengthX,myOffsetLengthY,myOffsetLengthZ);
      point1 = new Pnt(0f+myOffsetLengthX,0f+myOffsetLengthY,0f+myOffsetLengthZ);
      point2 = new Pnt(myLengthX+myOffsetLengthX,0f+myOffsetLengthY,0f+myOffsetLengthZ);
      point3 = new Pnt(0f+myOffsetLengthX,myLengthY+myOffsetLengthY,0f+myOffsetLengthZ);
      point4 = new Pnt(myLengthX+myOffsetLengthX,myLengthY+myOffsetLengthY,0f+myOffsetLengthZ);
      point5 = new Pnt(0f+myOffsetLengthX,0f+myOffsetLengthY,myLengthZ+myOffsetLengthZ);
      point6 = new Pnt(myLengthX+myOffsetLengthX,0f+myOffsetLengthY,myLengthZ+myOffsetLengthZ);
      point7 = new Pnt(0f+myOffsetLengthX,myLengthY+myOffsetLengthY,myLengthZ+myOffsetLengthZ);
      point8 = new Pnt(myLengthX+myOffsetLengthX,myLengthY+myOffsetLengthY,myLengthZ+myOffsetLengthZ);
      point1.setColor(0xff00ff);
      point1.setRadius(.1f);
      point2.setColor(0xff00ff);
      point2.setRadius(.1f);
      point3.setColor(0xff00ff);
      point3.setRadius(.1f);
      point4.setColor(0xff00ff);
      point4.setRadius(.1f);
      point5.setColor(0xff00ff);
      point5.setRadius(.1f);
      point6.setColor(0xff00ff);
      point6.setRadius(.1f);
      point7.setColor(0xff00ff);
      point7.setRadius(.1f);
      point8.setColor(0xff00ff);
      point8.setRadius(.1f);
      addVertex(point1);
      addVertex(point2);
      addVertex(point3);
      addVertex(point4);
      addVertex(point5);
      addVertex(point6);
      addVertex(point7);
      addVertex(point8);
      //setWidth(myLengthX);
      //setHeight(myLengthY);
      //setLength(myLengthZ);
   }
   public Vector retrieveBoxes(ShapePnt myShape,int myCount)
   {
      Line aLine = new Line();//myShape.longAxisLine(); ... repair later
      float aLengthX = Math.abs(aLine.getX2()-aLine.getX1());
      float aLengthY = Math.abs(aLine.getY2()-aLine.getY1());
      float aLengthZ = Math.abs(aLine.getZ2()-aLine.getZ1());
      int aXCount = 0;
      int aYCount = 0;
      int aZCount = 0;
      if(aLengthX != 0)
      {
         aXCount = myCount;
      }else{
         aXCount = 1;
      }
      if(aLengthY != 0)
      {
         aYCount = myCount;
      }else{
         aYCount = 1;
      }
      if(aLengthZ != 0)
      {
         aZCount = myCount;
      }else{
         aZCount = 1;
      }
      //System.out.println("BoxShape: minx = "+myShape.getXMin()+"xmax = "+myShape.getXMax());
      //System.out.println("BoxShape: miny = "+myShape.getYMin()+"ymax = "+myShape.getYMax());
      //System.out.println("BoxShape: minz = "+myShape.getZMin()+"zmax = "+myShape.getZMax());
      //System.out.println("BoxShape ... width = "+myShape.getWidth()+",height = "+myShape.getHeight()+",length = "+myShape.getLength()+",x = "+aXCount+", y = "+aYCount+", z = "+aZCount);
      Vector aVector = retrieveBoxes(myShape,aXCount,aYCount,aZCount);
      return aVector;
   }
   public Vector retrieveBoxes(int myXCount,int myYCount,int myZCount)
   {
      return retrieveBoxes(this,myXCount,myYCount,myZCount);
   }
   public Vector retrieveBoxes(ShapePnt myShape,int myXCount,int myYCount,int myZCount)
   {

      Vector boxShapeVector = new Vector();
      BoxShape aBoxShape = null;

      float boxX1 = 0;
      float boxX2 = 0;
      float boxY1 = 0;
      float boxY2 = 0;
      float boxZ1 = 0;
      float boxZ2 = 0;

      float boxLength = 0;

      float aLength = (float)(myShape.getZMax() - myShape.getZMin());
      float aWidth = (float)(myShape.getXMax() - myShape.getXMin());
      float aHeight = (float)(myShape.getYMax() - myShape.getYMin());
      
      float aDeltaX = aWidth/myXCount;
      float aDeltaY = aHeight/myYCount;
      float aDeltaZ = aLength/myZCount;
      //System.out.println("BoxShape : count = "+myXCount);
      for(int f=0;f<myXCount;f=f+1)
      {
         boxX1 =(float) (myShape.getXMin() + f*aDeltaX);
         for(int g=0;g<myYCount;g=g+1)
         {
            boxY1 = (float)( myShape.getYMin() + g*aDeltaY);
            for(int h=0;h<myZCount;h=h+1)
            {
               boxZ1 = (float)(myShape.getZMin() + h*aDeltaZ);
               aBoxShape = new BoxShape(aDeltaX,aDeltaY,aDeltaZ,boxX1,boxY1,boxZ1);
               //System.out.println("BoxShape ... creating boxes : "+aDeltaX+","+aDeltaY+","+aDeltaZ+","+boxX1+","+boxY1+","+boxZ1);
               boxShapeVector.add(aBoxShape);
            }
         }
 
      }
      return boxShapeVector;
   }
   public Pnt getMidPoint()
   {
      float x1 = (getPoint2().getX1() + getPoint1().getX1())/2;
      float y1 = (getPoint3().getY1() + getPoint1().getY1())/2;
      float z1 = (getPoint5().getZ1() + getPoint1().getZ1())/2;
      Pnt aPoint = new Pnt(x1,y1,z1);
      return aPoint;
   }
   public float getLength()
   {
      return length;
   }
   public void setLength(float myLength)
   {
      length = myLength;
   }
   public Pnt getPoint1()
   {
      return point1;
   } 
   public Pnt getPoint2()
   {
      return point2;
   } 
   public Pnt getPoint3()
   {
      return point3;
   }
   public Pnt getPoint4()
   {
      return point4;
   }
   public Pnt getPoint5()
   {
      return point5;
   }
   public Pnt getPoint6()
   {
      return point6 ;
   }
   public Pnt getPoint7()
   {
      return point7;
   }
   public Pnt getPoint8()
   {
      return point8;
   }
   public void setPoint1(Pnt myPoint)
   {
      point1 = myPoint;
      point1.setPoint(0f,0f,0f);
      setVertex(point1,0);
   } 
   public void setPoint2(Pnt myPoint)
   {
      point2 = myPoint;
      point2.setPoint(length,0f,0f);
      setVertex(point2,1);
   } 
   public void setPoint3(Pnt myPoint)
   {
      point3 = myPoint;
      point3.setPoint(0,length,0f);
      setVertex(point3,2);
   }
   public void setPoint4(Pnt myPoint)
   {
      point4 = myPoint;
      point4.setPoint(length,length,0f);
      setVertex(point4,3);
   }
   public void setPoint5(Pnt myPoint)
   {
      point5 = myPoint;
      point5.setPoint(0f,0f,length);
      setVertex(point5,4);
   }
   public void setPoint6(Pnt myPoint)
   {
      point6 = myPoint;
      point6.setPoint(length,0f,length);
      setVertex(point6,5);
   }
   public void setPoint7(Pnt myPoint)
   {
      point7 = myPoint;
      point7.setPoint(0f,length,length);
      setVertex(point7,6);
   }
   public void setPoint8(Pnt myPoint)
   {
      point8 = myPoint;
      point8.setPoint(length,length,length);
      setVertex(point8,7);
   }
   public boolean isInXBoundary(double myX,double y,double z)
   {
      boolean isInXBoundary = false;
      //float myOffsetLengthX = getEnvironmentOffSetX();

      if((myX >= point1.getX1()) && (myX <= point8.getX1()) )
      {
   
         isInXBoundary = true;
      }
      //System.out.println("BoxShape: x = "+myX+", x min = "+(point1.getX1())+", x max = "+(point8.getX1())+", isInXBoundary = "+isInXBoundary);
      return isInXBoundary;
   }
   public boolean isInYBoundary(double x,double myY,double z)
   {
      boolean isInYBoundary = false;

      if( (myY >= point1.getY1()) && (myY <= point8.getY1()) )
      {
         isInYBoundary = true;
      }
      //System.out.println("BoxShape: y = "+myY+", y min = "+(point1.getY1())+", y max = "+(point8.getY1())+", isInYBoundary = "+isInYBoundary);
      return isInYBoundary;
   }
   public boolean isInZBoundary(double x,double y,double myZ)
   {
      boolean isInZBoundary = false;

      if( (myZ >= point1.getZ1()) && (myZ <= point8.getZ1()) )
      {
         isInZBoundary = true;
      }
      //System.out.println("BoxShape: z = "+myZ+", z min = "+(point1.getZ1())+", z max = "+(point8.getZ1())+",isInZBoundary = "+isInZBoundary);
      return isInZBoundary;
   }
   public boolean isInBoundary(double x,double y,double z)
   {
      boolean isInABoundary = false;

      if(isInXBoundary(x,y,z) && isInYBoundary(x,y,z) && isInZBoundary(x,y,z))
      {
         isInABoundary = true;
      }
      return isInABoundary;
   }
   public static void main(String args[])
   {

   }
}