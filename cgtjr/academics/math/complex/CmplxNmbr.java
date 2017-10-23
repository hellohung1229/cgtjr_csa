package cgtjr.academics.math.complex;


public class CmplxNmbr //extends Line
{
   private static final double one[] = {1,0};
   private static final double iOne[] = {0,1};
   double x1;
   double y1;

   public CmplxNmbr()
   {
      x1 = 0;
      y1 = 0;
   }
   public CmplxNmbr(float myX,float myY)
   {
      x1 = myX;
      y1 = myY;
   }
   public CmplxNmbr(double myX,double myY)
   {
      x1 = myX;
      y1 = myY;
   }
   public CmplxNmbr(CmplxNmbr myCmplxNmbr)
   {
      setX1(myCmplxNmbr.getX1());
      setY1(myCmplxNmbr.getY1());
   }
   public static double[] getOne()
   {
       return one;
   }
   public static double[] getIOne()
   {
       return iOne;
   }
   /*
   public static float[] cmptNrmlztn(float aVctr[])
   {
      float unit[] = new float[3];

      unit[0] = (aVctr[0])/
              (float)Math.sqrt(aVctr[0]*aVctr[0]+aVctr[1]*aVctr[1]);
      unit[1] = (aVctr[1])/
              (float)Math.sqrt(aVctr[0]*aVctr[0]+aVctr[1]*aVctr[1]);
      return unit;
   }
   public void normalize()
   {
      double unitX = 0;
      double unitY = 0;
      
      unitX = (getX1())/
              (float)Math.sqrt((getX1())*(getX1())+(getY1())*(getY1()));
      unitY = (getY1())/
              (float)Math.sqrt((getX1())*(getX1())+(getY1())*(getY1()));

      setX1(unitX);
      setY1(unitY);
   }*/
   public void scale(float aValue)
   {
      setX1(aValue*getX1());
      setY1(aValue*getY1());
   }
   public void scale(double aValue)
   {
      setX1(aValue*getX1());
      setY1(aValue*getY1());
   }
   public static double[] scale(double myCN[],double aValue)
   {
      double x = myCN[0]*aValue;
      double y = myCN[1]*aValue;
      double cn[] = {x,y};
      return cn;
   }
   public void add(CmplxNmbr aCmplxNmbr)
   {      
      setX1(getX1()+aCmplxNmbr.getX1());
      setY1(getY1()+aCmplxNmbr.getY1());
   }
   public static double[] add(double cn1[],double cn2[])
   {
      double sum[] = {cn1[0]+cn2[0],cn1[1]+cn2[1]};
      return sum;   
   }
   public static double[] sub(double cn1[],double cn2[])
   {
      double sum[] = {cn1[0]-cn2[0],cn1[1]-cn2[1]};
      return sum;
   }
   public float getMagnitude()
   {  
      return (float) Math.sqrt(getX1()*getX1()+getY1()*getY1());
   }
   public static float getMagnitude(float myFloat[])
   {
      return (float) Math.sqrt(myFloat[0]*myFloat[0]+myFloat[1]*myFloat[1]);
   }
   public static double getMagnitude(double myDouble[])
   {
      return Math.sqrt(myDouble[0]*myDouble[0]+myDouble[1]*myDouble[1]);
   }
   public String toString() 
   { 
      return "[" + getX1() + ", " + getY1() + "]";
   }
   public static CmplxNmbr mltplyCN(double myX1,double myY1,double myX2,double myY2)
   {
      double cn[] = mltply(myX1,myY1,myX2,myY2);
      CmplxNmbr aCmplxNmbr = new CmplxNmbr(cn[0],cn[1]);
      return aCmplxNmbr;
   }
   public static double[] mltply(double c1[],double c2[])
   {
       return mltply(c1[0],c1[1],c2[0],c2[1]);
   }
   public static double[] mltply(double myX1,double myY1,double myX2,double myY2)
   {
      double x = (float)((myX1*myX2)-(myY1*myY2));
      double y = (float)((myX1*myY2)+(myY1*myX2));
      double aVector[] = {x,y};
      return aVector;
   }
   public double[] mltply(CmplxNmbr myCmplxNmbr)
   {
      double x1 = getX1();
      double x2 = myCmplxNmbr.getX1();
      double y1 = getY1();
      double y2 = myCmplxNmbr.getY1();
      double aCmplxNmbr[] = mltply(x1,y1,x2,y2);
      return aCmplxNmbr;
   }
   public double[] mltply(float myFlt[])
   {
      double x = getX1()*myFlt[0]-getY1()*myFlt[1];
      double y = getX1()*myFlt[0]+getX1()*myFlt[0];
      double aCmplxNmbr[] = {x,y};
      return aCmplxNmbr;
   }
   public double[] divide(double myCN2[])
   {
      double aCmplxNmbr[] = divide(getX1(),getY1(),myCN2[0],myCN2[1]);
      return aCmplxNmbr;
   }
   public static double[] divide(double myCN1[],double myCN2[])
   {
      double aCmplxNmbr[] = divide(myCN1[0],myCN1[1],myCN2[0],myCN2[1]);
      return aCmplxNmbr;
   }
   public static double[] divide(double myX1,double myY1,double myX2,double myY2)
   {
      double dnmntr = myX2*myX2 + myY2*myY2;
      double x = (myX1*myX2+myY1*myY2)/dnmntr;
      double y = (-myX1*myY2+myX2*myY1)/dnmntr;
      double aCmplxNmbr[] = {x,y};
      return aCmplxNmbr;
   }
   public double cmptAngle(float myVector[])
   {
      System.out.println("CmplxNmbr.cmptAngle() a="+myVector[0]+", b="+myVector[1]);
      float x = myVector[0];
      float y = myVector[1];
      double aValue2 = Math.atan(x/y);
      return aValue2;
   }
   public double cmptAngle(CmplxNmbr myCmplxNmbr)
   {
      double x = myCmplxNmbr.getX1();
      double y = myCmplxNmbr.getY1();
      double aValue2 = Math.atan(x/y);
      return aValue2;
   }
   public static double[] rtvCmplxNmbr(double myX,double myY)
   {
      double cn[] = {myX,myY};
      return cn;
   }
   public void setX1(double aX1)
   {
      x1 = aX1;
   }
   public void setY1(double aY1)
   {
      y1 = aY1;
   }
   public double getX1()
   {
      return x1;
   }
   public double getY1()
   {
      return y1;
   }
   public static String toString(double myCN[])
   {
      return myCN[0]+","+myCN[1]+"j";
   }
   public static void print(double myCN[])
   {
      System.out.println("CmplxNmbr: x="+myCN[0]+", y="+myCN[1]+"j");
   }
}