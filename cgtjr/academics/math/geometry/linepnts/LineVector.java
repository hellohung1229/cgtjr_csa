package cgtjr.academics.math.geometry.linepnts;

//import java.lang.Math;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;


public class LineVector extends Line
{
   public LineVector()
   {
      super();
   }
   public LineVector(LineVector myLineVector)
   {
      setX2(myLineVector.getX2());
      setY2(myLineVector.getY2());
      setZ2(myLineVector.getZ2());
   }
   public LineVector(float x2,float y2,float z2)
   {
      //super(0,0,0,x2,y2,z2);
      setX2(x2);
      setY2(y2);
      setZ2(z2);
   }
   public LineVector(Pnt myPoint1)
   {
      super(0,0,0,myPoint1.getX1(),myPoint1.getY1(),myPoint1.getZ1());
   }
   public LineVector(Pnt myPoint1,Pnt myPoint2)
   {
      setX2(myPoint2.getX1()-myPoint1.getX1());
      setY2(myPoint2.getY1()-myPoint1.getY1());
      setZ2(myPoint2.getZ1()-myPoint1.getZ1());
   }
   public void setPoints(Pnt myPoint1,Pnt myPoint2)
   {
      setX2(myPoint2.getX1()-myPoint1.getX1());
      setY2(myPoint2.getY1()-myPoint1.getY1());
      setZ2(myPoint2.getZ1()-myPoint1.getZ1());
   }
   /*
   public LineVector(LineAngle myLineAngle)
   {
      setX2(myLineAngle.getX2()-myLineAngle.getX1());
      setY2(myLineAngle.getY2()-myLineAngle.getY1());
      setZ2(myLineAngle.getZ2()-myLineAngle.getZ1());
   }*/
   public LineVector getUnitVector()
   {
      float unitX = 0;
      float unitY = 0;
      float unitZ = 0;
      
      unitX = (getX2()-getX1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));
      unitY = (getY2()-getY1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));
      unitZ = (getZ2()-getZ1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));

      return new LineVector(unitX,unitY,unitZ);
   }
   public static float[] cmptNrmlztn(float aVctr[])
   {
      float unit[] = new float[3];

      unit[0] = (aVctr[0])/
              (float)Math.sqrt(aVctr[0]*aVctr[0]+aVctr[1]*aVctr[1]+aVctr[2]*aVctr[2]);
      unit[1] = (aVctr[1])/
              (float)Math.sqrt(aVctr[0]*aVctr[0]+aVctr[1]*aVctr[1]+aVctr[2]*aVctr[2]);
      unit[2] = (aVctr[2])/
              (float)Math.sqrt(aVctr[0]*aVctr[0]+aVctr[1]*aVctr[1]+aVctr[2]*aVctr[2]);
      return unit;
   }
   public void normalize()
   {
      float unitX = 0;
      float unitY = 0;
      float unitZ = 0;
      
      unitX = (getX2()-getX1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));
      unitY = (getY2()-getY1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));
      unitZ = (getZ2()-getZ1())/
              (float)Math.sqrt((getX2()-getX1())*(getX2()-getX1())+(getY2()-getY1())*(getY2()-getY1())+(getZ2()-getZ1())*(getZ2()-getZ1()));

      setX2(unitX);
      setY2(unitY);
      setZ2(unitZ);
   }
   public void scale(float aValue)
   {
      setX2(aValue*getX2());
      setY2(aValue*getY2());
      setZ2(aValue*getZ2());
   }
   public void add(LineVector aLineVector)
   {      
      setX2(getX2()+aLineVector.getX2());
      setY2(getY2()+aLineVector.getY2());
      setZ2(getZ2()+aLineVector.getZ2());
   }
   public float getMagnitude()
   {  
      return (float) PntTool.getDistance(getX1(),getY1(),getZ1(),getX2(),getY2(),getZ2());
   }
   public String toString() 
   { 
      return "[" + getX2() + ", " + getY2() + "," + getZ2() + "]";
   }
   /*
   public float cmptPntToLn(Point myPoint1,LineAngle myLineAngle)
   {
      LineVector aLineVector1 = new LineVector(myLineAngle);
      LineVector aLineVector2 = new LineVector(myPoint1,myLineAngle.getPoint2());
      LineVector aLineVector3 = crssPrdct(aLineVector1,aLineVector2);
      float aMagnitude = aLineVector3.getMagnitude();
      return aMagnitude;
   }*/
   public static LineVector crssPrdct(LineVector myLineVector1,LineVector myLineVector2)
   {
      float iComp = myLineVector1.getY2()*myLineVector2.getZ2() - myLineVector1.getZ2()*myLineVector2.getY2();
      float jComp = myLineVector1.getZ2()*myLineVector2.getX2() - myLineVector1.getX2()*myLineVector2.getZ2();
      float kComp = myLineVector1.getY2()*myLineVector2.getX2() - myLineVector1.getX2()*myLineVector2.getY2();
      LineVector aLineVector = new LineVector(iComp,jComp,kComp);
      return aLineVector;
   }
   public static LineVector crssPrdct(Pnt myPoint1,Pnt myPoint2,Pnt myPoint3,Pnt myPoint4)
   {
      float aX1 = myPoint1.getX1();
      float aY1 = myPoint1.getY1();
      float aZ1 = myPoint1.getZ1();
      float aX2 = myPoint2.getX1();
      float aY2 = myPoint2.getY1();
      float aZ2 = myPoint2.getZ1();
      float aX3 = myPoint3.getX1();
      float aY3 = myPoint3.getY1();
      float aZ3 = myPoint3.getZ1();
      float aX4 = myPoint4.getX1();
      float aY4 = myPoint4.getY1();
      float aZ4 = myPoint4.getZ1();
      return crssPrdct(aX1,aY1,aZ1,aX2,aY2,aZ2,aX3,aY3,aZ3,aX4,aY4,aZ4);
   }
   public static LineVector crssPrdct(float myX1,float myY1,float myZ1,float myX2,float myY2,float myZ2,
                                  float myX3,float myY3,float myZ3,float myX4,float myY4,float myZ4)
   {
      float iComp = (myY2-myY1)*(myZ4-myZ3) - (myZ2-myZ1)*(myY4-myY3);
      float jComp = (myZ2-myZ1)*(myX4-myX3) - (myX2-myX1)*(myZ4-myZ3);
      float kComp = (myX2-myX1)*(myY4-myY3) - (myY2-myY1)*(myX4-myX3);
      LineVector aLineVector = new LineVector(iComp,jComp,kComp);
      return aLineVector;
   }
   public static LineVector crssPrdct(double myX1,double myY1,double myZ1,double myX2,double myY2,double myZ2,
                                  double myX3,double myY3,double myZ3,double myX4,double myY4,double myZ4)
   {
      float iComp = (float)((myY2-myY1)*(myZ4-myZ3) - (myZ2-myZ1)*(myY4-myY3));
      float jComp = (float)((myZ2-myZ1)*(myX4-myX3) - (myX2-myX1)*(myZ4-myZ3));
      float kComp = (float)((myX2-myX1)*(myY4-myY3) - (myY2-myY1)*(myX4-myX3));
      LineVector aLineVector = new LineVector(iComp,jComp,kComp);
      return aLineVector;
   }   
   public double dotPrdct(LineVector myLineVector)
   {
      float iComp = getX2()*myLineVector.getX2();
      float jComp = getY2()*myLineVector.getY2();
      float kComp = getZ2()*myLineVector.getZ2();
      float dotValue = Math.abs(iComp+jComp+kComp);
      System.out.println("LineVector.dotPrdct(linevector): i = "+iComp+",j="+jComp+",k="+kComp);
      return dotValue;
   }
   public double dotPrdct(float myFlt[])
   {
      float iComp = getX2()*myFlt[0];
      float jComp = getY2()*myFlt[1];
      float kComp = getZ2()*myFlt[2];
      float dotValue = Math.abs(iComp+jComp+kComp);
      System.out.println("LineVector.dotPrdct(float): i = "+iComp+",j="+jComp+",k="+kComp);
      return dotValue;
   }
   public double cmptAngle(float myVector[])
   {
      System.out.println("LineVector.cmptAngle() a="+myVector[0]+", b="+myVector[1]+", c="+myVector[2]);
      double aValue1 = dotPrdct(myVector);
      double aValue2 = Math.acos(aValue1);
      System.out.println("LineVector.cmptAngle(): angle = "+aValue2);
      return aValue2;
   }
   public double cmptAngle(LineVector myLineVector)
   {
      double aValue1 = dotPrdct(myLineVector);
      double aValue2 = Math.acos(aValue1);
      System.out.println("LineVector.cmptAngle(): angle = "+aValue2);
      return aValue2;
   }
   public static double cmptAngle(LineVector myLineVector,LineVector myLineVector2)
   {
      double aValue1 = myLineVector.dotPrdct(myLineVector);
      double aValue2 = Math.acos(aValue1);
      return aValue2;
   }
}