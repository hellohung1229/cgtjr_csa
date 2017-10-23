package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;

public class AngleLine extends Pnt
{
   private Pnt thePoint2;

   private double theta;
   private double phi;

   public AngleLine()
   {
      thePoint2 = new Pnt();
   }
   
   public AngleLine(float xPos, float yPos,float zPos)
   {      
      thePoint2 = new Pnt(xPos,yPos,zPos);
      initialize(0,0,0,xPos,yPos,zPos);
   }
   public AngleLine(float xStartPos, float yStartPos,float zStartPos,float xEndPos , float yEndPos,float zEndPos)
   {
      super(xStartPos,yStartPos,zStartPos);
      thePoint2 = new Pnt(xEndPos,yEndPos,zEndPos); 
      initialize(xStartPos,yStartPos,zStartPos,xEndPos,yEndPos,zEndPos);  
      //System.out.println("AngleLine ... this constructor needs modification to support correct theta and phi variables!!!");
      //System.out.println("AngleLine.AngleLine() : xEndPos = "+xEndPos+" yEndPos = "+yEndPos+" zEndPos = "+zEndPos);
   }
   public AngleLine(Pnt myPoint1,Pnt myPoint2)
   {
      thePoint2 = new Pnt();
      thePoint2.setX1(myPoint2.getX1());
      setX1(myPoint1.getX1());
      thePoint2.setY1(myPoint2.getY1());
      setY1(myPoint1.getY1());
      thePoint2.setZ1(myPoint2.getZ1());
      setZ1(myPoint1.getZ1());
      System.out.println("AngleLine ... this constructor needs modification to support correct theta and phi variables!!!");
      initialize(myPoint1.getX1(),myPoint1.getY1(),myPoint1.getZ1(),myPoint2.getX1(),myPoint2.getY1(),myPoint2.getZ1());
   } 
   public void setX2(float aX2)
   {
      thePoint2.setX1(aX2);
   }
   public void setY2(float aY2)
   {
      thePoint2.setY1(aY2);
   }
   public void setZ2(float aZ2)
   {
      thePoint2.setZ1(aZ2);
   }
   public float getX2() 
   { 
      return thePoint2.getX1(); 
   }
   public float getY2() 
   { 
      return thePoint2.getY1();
   }
   public float getZ2() 
   { 
      return thePoint2.getZ1(); 
   }
   public Pnt getPoint2()
   {
      return thePoint2;
   }
   public float getLength()
   {
      return (float) PntTool.getDistance(getX1(),getY1(),getZ1(),getX2(),getY2(),getZ2());
   }
   public Pnt getMidPoint()
   {
      return new Pnt(getX1()+(getX2()-getX1())/2,getY1()+(getY2()-getY1())/2,getZ1()+(getZ2()-getZ1())/2);
   }
   public static Pnt getMidPoint(Pnt myPoint1,Pnt myPoint2)
   {
      return new Pnt(myPoint1.getX1()+(myPoint2.getX1()-myPoint1.getX1())/2,myPoint1.getY1()+(myPoint2.getY1()-myPoint1.getY1())/2,myPoint1.getZ1()+(myPoint2.getZ1()-myPoint1.getZ1())/2);
   }
   public float getXAngle()
   {
      return getXAngle(0.0f);
   }
   public float getXAngle(float myOffSet)
   {
      float anAngle = 0;
      float denominator = (getY2()-getY1());

      if(denominator != 0)
      {
         //anAngle = (float) Math.atan((getY2()-getY1())/(getZ2()-getZ1()))+ myOffSet;

         anAngle = (float) Math.asin((getZ2()-getZ1())/( Math.sqrt( 
                                                       (getY2()-getY1())*(getY2()-getY1()) + 
                                                       (getZ2()-getZ1())*(getZ2()-getZ1())  )));
      }
      //System.out.println("getY2()-getY1()="+(getY2()-getY1()));
      //System.out.println("getZ2()-getZ1()="+(getZ2()-getZ1()));
      //System.out.println("(getZ2()-getZ1())/(getY2()-getY1())"+(getZ2()-getZ1())/(getY2()-getY1()) );
      return anAngle;
   }
   public float getYAngle()
   {
      return getYAngle(0.0f);
   }
   public float getYAngle(float myOffSet)
   {
      float anAngle = 0;
      float denominator = getZ2()-getZ1();
      if(denominator != 0)
      {
         anAngle = (float) Math.atan((getX2()-getX1())/(getZ2()-getZ1()))+ myOffSet;
      }
      //System.out.println("getX2()-getX1() = "+(getX2()-getX1()));
      //System.out.println("getZ2()-getZ1() ="+(getZ2()-getZ1()));
      //System.out.println("(getX2()-getX1())/(getZ2()-getZ1())"+(getX2()-getX1())/(getZ2()-getZ1()) );
      return anAngle;
   }
   public float getZAngle()
   {
      return getZAngle(0.0f);
   }
   public float getZAngle(float myOffSet)
   {
      float anAngle = 0;
      float denominator = getX2()-getX1();
      if(denominator != 0)
      {
         anAngle = (float) Math.atan((getY2()-getY1())/(getX2()-getX1())) + myOffSet;
      }
      //System.out.println("getY2()-getY1()="+(getY2()-getY1()));
      //System.out.println("getX2()-getX1()="+(getZ2()-getZ1()));
      //System.out.println("getY2()-getY1())/(getX2()-getX1())"+(getY2()-getY1())/(getX2()-getX1()));
      return anAngle;
   }
   public float getAngle()
   {
      return getZAngle();
   }
   public AngleLine retrieveLines()
   {
      return retrieveLines(this);
   }
   public AngleLine retrieveLines(AngleLine myAngleLine)
   {
      int anIndex = 0;
      Pnt aPoint = null;
      AngleLine theConnections = new Line();

      Enumeration anEnumeration1 = myAngleLine.rtrvEnumeration();
      //System.out.println("PDBMolecule.processConnections() size = "+getAdjacentVertices().size());
      Enumeration anEnumeration2 = null;
      Pnt anotherPoint = null;
      while(anEnumeration1.hasMoreElements())
      {
         aPoint = (Pnt)anEnumeration1.nextElement();
         anEnumeration2 = aPoint.rtrvEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            anotherPoint = (Pnt)anEnumeration2.nextElement();

            theConnections.addVertex(new Line(aPoint.getX1(),aPoint.getY1(),aPoint.getZ1(),
                                     anotherPoint.getX1(),anotherPoint.getY1(),anotherPoint.getZ1()));  
         }
      }
      return theConnections;
    }
    public void initialize(float myX1,float myY1,float myZ1,float myX2,float myY2,float myZ2)
    {
      float myPhi = 0;
      double myTheta = 0;

      float x = myX2 - myX1;
      float y = myY2 - myY1;
      float z = myZ2 - myZ1;

      float myRadius = PntTool.getDistance(0,0,0,x,y,z);
      setRadius(myRadius);

      if(y == 0 && x >= 0)
      {
         theta = Math.PI/2;
      }else if(y == 0 && x < 0)
      {
         theta = 3*Math.PI/2;
      }else if(y != 0 && x >= 0){
         theta = Math.atan2(myRadius,y);
      }else if(y != 0 && x < 0){
         theta = 2*Math.PI-Math.atan2(myRadius,y);
      }
      if(z == 0 && x >= 0)
      {
         phi = Math.PI/2;
      }else if(z == 0 && x < 0)
      {
         phi = 3*Math.PI/2;
      }else if(z != 0 && x >= 0){
         phi = Math.atan2(x,z);
      }else if(z != 0 && x < 0){
         phi = Math.PI+(-1*Math.atan2(x,z));
      }
    }
    public boolean isInBoundary(double x,double y,double z)
    {
      double myRadius = PntTool.getDistance(0,0,0,x,y,z);
      double myPhi = phi;
      double myTheta = theta;
      if(y == 0 && x >= 0)
      {
         myTheta = Math.PI/2;
      }else if(y == 0 && x < 0)
      {
         myTheta = 3*Math.PI/2;
      }else if(y != 0 && x >= 0){
         myTheta = Math.atan2(myRadius,y);
      }else if(y != 0 && x < 0){
         myTheta = 2*Math.PI-Math.atan2(myRadius,y);
      }
      if(z == 0 && x >= 0)
      {
         myPhi = Math.PI/2;
      }else if(z == 0 && x < 0)
      {
         myPhi = 3*Math.PI/2;
      }else if(z != 0 && x >= 0){
         myPhi = Math.atan2(x,z);
      }else if(z != 0 && x < 0){
         myPhi = Math.PI+(-1*Math.atan2(x,z));
      }
      return isOnLine(myRadius,myTheta,myPhi);
   }

   public boolean isInRadiusBoundary(double myRadius)
   {
      boolean isInRBoundary = false;
      System.out.println("AngleLine.isInRadiusBoundary() : radius = "+myRadius+" min radius = "+0+", max radius = "+getRadius());
      double aRadius = getRadius();
      if(myRadius >= 0 && myRadius <= aRadius)
      {
         isInRBoundary = true;
      }
      return isInRBoundary;
   }
   public boolean isInPhiBoundary(double myPhi)
   {
      boolean isInPhiBoundary = false;
      System.out.println("AngleLine.isInPhiBoundary() : phi = "+myPhi+" min phi = "+phi+", max phi = "+phi);
      if( (myPhi >= phi) && (myPhi <= phi) )
      {
         isInPhiBoundary = true;
      }
      return isInPhiBoundary;
   }
   public boolean isInThetaBoundary(double myTheta)
   {
      boolean isInThetaBoundary = false;
      System.out.println("AngleLine.isInThetaBoundary() : theta = "+myTheta+" min theta = "+theta+", max theta = "+theta);
      if( (myTheta >= theta) && (myTheta <= theta) )
      {
         isInThetaBoundary = true;
      }
      return isInThetaBoundary;
   }
   public boolean isOnLine(double r,double t,double p)
   {
      boolean isInABoundary = false;

      if(isInRadiusBoundary(r) && isInPhiBoundary(p) && isInThetaBoundary(t))
      {
         isInABoundary = true;
      }
      //System.out.println("SphereShape : isInABoundary = "+isInABoundary);
      return isInABoundary;
   }
   /*
   public void translate(Point myBoxShape)
   {
      Point aPoint1 = myBoxShape.getMidPoint();
      float x1 = aPoint1.getX1();
      float y1 = aPoint1.getY1();
      float z1 = aPoint1.getZ1();
      Point aPoint2 = getMidPoint();
      float x2 = aPoint2.getX1();
      float y2 = aPoint2.getY1();
      float z2 = aPoint2.getZ1();
      float xDelta = x1-x2;
      float yDelta = y1-y2;
      float zDelta = z1-z2;

      float x3 = getX1();
      float y3 = getY1();
      float z3 = getZ1();
      float x4 = getX2();
      float y4 = getY2();
      float z4 = getZ2();

      setX1(x3+xDelta);
      setY1(y3+yDelta);
      setZ1(z3+zDelta);

      setX2(x4+xDelta);
      setY2(y4+yDelta);
      setZ2(z4+zDelta);
      System.out.println("AngleLine.translate(): before center = "+aPoint2+", after center ="+getMidPoint());
   }*/
}