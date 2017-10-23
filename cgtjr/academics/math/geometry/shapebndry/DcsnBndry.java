package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.ClndrclVar;
import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.graph.*;
import java.util.Vector;

/**
 * 
 * @author clayton g thomas jr
 */
public class DcsnBndry implements Bndry
{
   //Could this be change to ShpBndry ... for dynamic boundary insertion?!

   //Never add getters or setters for initX,initY,initZ
   private double initX;
   private double initY;
   private double initZ;
   private float deltaX = 1;
   private float deltaY = 1;
   private float deltaZ = 1;
   private double xMax;
   private double xMin;
   private double yMax;
   private double yMin;
   private double zMax;
   private double zMin;
   private float envrnmntX;
   private float envrnmntY;
   private float envrnmntZ;
   
   private boolean invrtBndryDcsn;
   private boolean checkBoundary;  
   private String crdntTp;
   private boolean isInABndry;

   private int imgPixels[];
   private int imgWidth;
   private int imgHeight;

   public DcsnBndry()
   {

      checkBoundary = true;
      initX=0;
      initY=0;
      initZ=0;
      xMax = -Float.MAX_VALUE;
      yMax = -Float.MAX_VALUE;
      zMax = -Float.MAX_VALUE;
      xMin = Float.MAX_VALUE;
      yMin = Float.MAX_VALUE;
      zMin = Float.MAX_VALUE;
      //System.out.println("ShpBndry: constructor xmax =" + xMax+", x min = "+xMin);
   }
   public void setImgPxlData(int myImgPixels[],int myImgWidth,int myImgHeight)
   {
      imgPixels = myImgPixels;
      imgWidth = myImgWidth;
      imgHeight = myImgHeight;
   }
   public DcsnBndry(double myXMax,double myYMax,double myZMax)
   {
      checkBoundary = true;
      xMax = myXMax;
      yMax = myYMax;
      zMax = myZMax;
      initX=0;
      initY=0;
      initZ=0;
   }
   public DcsnBndry(double myXMin,double myYMin,double myZMin,double myXMax,double myYMax,double myZMax)
   {
      checkBoundary = true;
      xMax = myXMax;
      yMax = myYMax;
      zMax = myZMax;
      xMin = myXMin;
      yMin = myYMin;
      zMin = myZMin;
      initX=0;
      initY=0;
      initZ=0;
   }
   public DcsnBndry(double myXMin,double myYMin,double myZMin,double myXMax,double myYMax,double myZMax,double myInitX,double myInitY,double myInitZ)
   {
      checkBoundary = true;
      xMax = myXMax;
      yMax = myYMax;
      zMax = myZMax;
      xMin = myXMin;
      yMin = myYMin;
      zMin = myZMin;
      initX = myInitX;
      initY = myInitY;
      initZ = myInitZ;
   }
   public DcsnBndry(DmnsnVar myDmnsnVar)
   {
      checkBoundary = true;
      xMax = myDmnsnVar.getMaxDmnsn1Val();
      yMax = myDmnsnVar.getMaxDmnsn2Val();
      zMax = myDmnsnVar.getMaxDmnsn3Val();
      xMin = myDmnsnVar.getMinDmnsn1Val();
      yMin = myDmnsnVar.getMinDmnsn2Val();
      zMin = myDmnsnVar.getMinDmnsn3Val();
      initX = myDmnsnVar.getInitXVal();
      initY = myDmnsnVar.getInitYVal();
      initZ = myDmnsnVar.getInitZVal();
      deltaX = (float)myDmnsnVar.getDelta1Val();
      deltaY = (float)myDmnsnVar.getDelta2Val();
      deltaZ = (float)myDmnsnVar.getDelta3Val();
      envrnmntX = (float)myDmnsnVar.getXOffSetVal();
      envrnmntY = (float)myDmnsnVar.getYOffSetVal();
      envrnmntZ = (float)myDmnsnVar.getZOffSetVal();
   }
   public boolean getIsInABndry()
   {
      return isInABndry;
   }
   public String getCrdntTp()
   {
       return crdntTp;
   }
   public void setCrdntTp(String myCrdntTp)
   {
      crdntTp = myCrdntTp;
   }
   public void setXMax(double myXMax)
   {
      xMax = myXMax;
   }
   public void setYMax(double myYMax)
   {
      yMax = myYMax;
   }
   public void setZMax(double myZMax)
   {
      zMax = myZMax;
   }   
   public void setXMin(double myXMin)
   {
      xMin = myXMin;
   }
   public void setYMin(double myYMin)
   {
      yMin = myYMin;
   }
   public void setZMin(double myZMin)
   {
      zMin = myZMin;
   }
   public double getXMax()
   {
      return xMax;
   }
   public double getYMax()
   {
      return yMax;
   }
   public double getZMax()
   {
      return zMax;
   }
   public double getXMin()
   {
      return xMin;
   }
   public double getYMin()
   {
      return yMin;
   }
   public double getZMin()
   {
      return zMin;
   }

   public boolean isInBndry(double r,double t,double p)
   {
      isInABndry = false;

      if(isInXBndry(r) && isInYBndry(t) && isInZBndry(p))
      {
         isInABndry = true;
      }

      //System.out.println("ShpBndry : isInABndry = "+isInABndry);
      return isInABndry;
   }
   public boolean isInXBndry(double myX)
   {
      boolean isInRBndry = false;
      //System.out.println("ShpBndry.isInXBndry() : x = "+myX+" min x = "+xMin+", max x = "+xMax+", envrnmntX = "+envrnmntX);
      if(myX >= xMin+envrnmntX && myX <= xMax+envrnmntX )
      {
         isInRBndry = true;
      }
      return isInRBndry;
   }
   public boolean isInYBndry(double myY)
   {
      boolean isInYBndry = false;
      //System.out.println("ShpBndry.isInYBndry() : Y = "+myY+" min Y = "+yMin+", max Y = "+yMax+", = "+envrnmntY);
      if( (myY >= yMin+envrnmntY) && (myY <= yMax+envrnmntY) )
      {
         isInYBndry = true;
      }
      return isInYBndry;
   }
   public boolean isInZBndry(double myZ)
   {
      boolean isInZBndry = false;
      //System.out.println("ShpBndry.isInZBndry() : z = "+myZ+" min Z = "+zMin+", max Z = "+zMax);
      if( (myZ >= zMin+envrnmntZ) && (myZ <= zMax+envrnmntZ))
      {
         isInZBndry = true;
      }
      return isInZBndry;
   }
   public boolean isOnXBndry(double myX,double myY,double myZ)
   {
      boolean isOnRBndry = false;
      //System.out.println("CylinderShape.isInXBndry() : x = "+myX+" min x = "+xMin+", max x = "+xMax);
      if(isOnXMinBndry(myX,myY,myZ) || isOnXMaxBndry(myX,myY,myZ))
      {
         isOnRBndry = true;
      }
      return isOnRBndry;
   }
   public boolean isOnXMinBndry(double myX,double myY,double myZ)
   {
      boolean isOnRBndry = false;
      //System.out.println("CylinderShape.isInXBndry() : x = "+myX+" min x = "+xMin+", max x = "+xMax);
      if(myX == xMin)
      {
         isOnRBndry = true;
      }
      return isOnRBndry;
   }
   public boolean isOnXMaxBndry(double myX,double myY,double myZ)
   {
      boolean isOnRBndry = false;
      //System.out.println("CylinderShape.isInXBndry() : x = "+myX+" min x = "+xMin+", max x = "+xMax);
      if(myX == xMax)
      {
         isOnRBndry = true;
      }
      return isOnRBndry;
   }
   public boolean isOnYMinBndry(double myX,double myY,double myZ)
   {
      boolean isOnYBndry = false;
      //System.out.println("CylinderShape.isInYBndry() : Y = "+myY+" min Y = "+yMin+", max Y = "+yMax);
      if( (myY == yMin))
      {
         isOnYBndry = true;
      }
      return isOnYBndry;
   }
   public boolean isOnYMaxBndry(double myX,double myY,double myZ)
   {
      boolean isOnYBndry = false;
      //System.out.println("CylinderShape.isInYBndry() : Y = "+myY+" min Y = "+yMin+", max Y = "+yMax);
      if( ( myY == yMax) )
      {
         isOnYBndry = true;
      }
      return isOnYBndry;
   }
   public boolean isOnYBndry(double myX,double myY,double myZ)
   {
      boolean isInYBndry = false;
      //System.out.println("CylinderShape.isInYBndry() : Y = "+myY+" min Y = "+yMin+", max Y = "+yMax);
      if(isOnYMinBndry(myX,myY,myZ) || isOnYMaxBndry(myX,myY,myZ))
      {
         isInYBndry = true;
      }
      return isInYBndry;
   }
   public boolean isOnZMinBndry(double myX,double myY,double myZ)
   {
      boolean isOnZBndry = false;
      //System.out.println("CylinderShape.isInZBndry() : z = "+myZ+" min Z = "+zMin+", max Z = "+zMax);
      if( (myZ == zMin) )
      {
         isOnZBndry = true;
      }
      return isOnZBndry;
   }
   public boolean isOnZMaxBndry(double myX,double myY,double myZ)
   {
      boolean isOnZBndry = false;
      //System.out.println("CylinderShape.isInZBndry() : z = "+myZ+" min Z = "+zMin+", max Z = "+zMax);
      if( (myZ == zMax) )
      {
         isOnZBndry = true;
      }
      return isOnZBndry;
   }
   public boolean isOnZBndry(double myX,double myY,double myZ)
   {
      boolean isOnZBndry = false;
      //System.out.println("CylinderShape.isInZBndry() : z = "+myZ+" min Z = "+zMin+", max Z = "+zMax);
      if( isOnZMinBndry(myX,myY,myZ) || isOnZMaxBndry(myX,myY,myZ) )
      {
         isOnZBndry = true;
      }
      return isOnZBndry;
   }
   public void updtBndry(float xPos,float yPos,float zPos)
   {

      //System.out.println("Shape.updateBoundaries() "+getName()+": x pos = "+xPos+" y pos = "+yPos+" z pos = "+zPos);
      //System.out.println("Shape.updateBoundaries() "+getName()+": xMax = "+xMax+" yMax = "+yMax+" zMax = "+zMax);
      //System.out.println("Shape.updateBoundaries() "+getName()+": xMin = "+xMin+" yMin = "+yMin+" zMin = "+zMin);

      //System.out.println("Shape.updateBoundaries() : xPosition = "+xPos+" yPosition = "+yPos+" zPosition = "+zPos);
      /*
      if(xMax == Float.NEGATIVE_INFINITY || yMax == Float.NEGATIVE_INFINITY || zMax == Float.NEGATIVE_INFINITY ||
         xMin == Float.NEGATIVE_INFINITY || yMin == Float.NEGATIVE_INFINITY || zMin == Float.NEGATIVE_INFINITY)
      {
         setXMax(xPos);
         setYMax(yPos);
         setZMax(zPos);
         setXMin(xPos);
         setYMin(yPos);
         setZMin(zPos);
      }*/
      if(xPos >= xMax)
      {
         setXMax(xPos);
      }
      if(yPos >= yMax)
      {
         setYMax(yPos);
      }
      if(zPos >= zMax)
      {
         setZMax(zPos);
      }
      if(xPos <= xMin)
      {
         setXMin(xPos);
      }
      if(yPos <= yMin)
      {
         setYMin(yPos);
      }
      if(zPos <= zMin)
      {
         setZMin(zPos);
      }
      /*
      xMax = getXMax();
      yMax = getYMax();
      zMax = getZMax();
      xMin = getXMin();
      yMin = getYMin();
      zMin = getZMin();
      */
   }
   public double cmptWidth()
   {
      double aWidth = getXMax()-getXMin();
      return aWidth;
   }
   public double cmptHeight()
   {
      double aHeight = getYMax()-getYMin();
      return aHeight;
   }
   public double cmptLength()
   {
      double aLength = getZMax()-getZMin();
      return aLength;
   }
   public double[] cmptCenter()
   {
       double xCenter = getXMin()+(getXMax()-getXMin())/2;
       double yCenter = getYMin()+(getYMax()-getYMin())/2;
       double zCenter = getZMin()+(getZMax()-getZMin())/2;
       double aCenter[] = new double[3];
       aCenter[0] = xCenter;
       aCenter[1] = yCenter;
       aCenter[2] = zCenter;
       return aCenter;
   }
   public DmnsnVar rtrvDmnsnVar(String myCrdntType2)
   {
      double aWidth  = getXMax()-getXMin();
      double aHeight = getYMax()-getYMin();
      double aLength = getZMax()-getZMin();
      double aDeltaX = getDeltaX();
      double aDeltaY = getDeltaY();
      double aDeltaZ = getDeltaZ();
      double anInitX = getInitX();
      double anInitY = getInitY();
      double anInitZ = getInitZ();
      DmnsnVar aDmnsnVar = new DmnsnVar(0,0,0,0,0,0,aWidth,aHeight,aLength);
      return rtrvDmnsnVar(aDmnsnVar,myCrdntType2);
   }
   public DmnsnVar rtrvDmnsnVar(DmnsnVar myDmnsnVar,String myCrdntType2)
   {
      DmnsnVar aDmnsnVar = null;
      String aCrdntType = myDmnsnVar.getCrdntTpVal();
      //System.out.println("ShpBndry: aCrdntType = "+aCrdntType);
      if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getSphrclCrdntTp()))
      {
         aDmnsnVar = new SphrclVar();
         aDmnsnVar.setMaxDmnsn1Val(cmptMaxDmnsn());
      }else if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getClndrclCrdntTp()))
      {
         aDmnsnVar = new ClndrclVar();
         aDmnsnVar.setMaxDmnsn1Val(cmptMddlDmnsn());
         aDmnsnVar.setMaxDmnsn3Val(cmptMaxDmnsn());
      }else if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getHlxCrdntTp()))
      {
         aDmnsnVar = new HelixVar();
         double aRadius = cmptMddlDmnsn();
         double aLength = Math.sqrt(1+1)*cmptMaxDmnsn();
         aDmnsnVar.setMaxDmnsn1Val(aRadius);
         aDmnsnVar.setMaxDmnsn2Val(aLength);
         aDmnsnVar.setMaxDmnsn3Val(myDmnsnVar.getMaxDmnsn3Val());   
         aDmnsnVar.setMinDmnsn1Val(myDmnsnVar.getMinDmnsn1Val());
         aDmnsnVar.setInitXVal(myDmnsnVar.getInitXVal());         
         //aDmnsnVar.setMaxDmnsn3Val(0);
      }else if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getCrtsnCrdntTp()))
      {
         aDmnsnVar = new Crtssn3DVar();
         aDmnsnVar.setMaxDmnsn1Val(myDmnsnVar.getMaxDmnsn1Val());
         aDmnsnVar.setMaxDmnsn2Val(myDmnsnVar.getMaxDmnsn2Val());
         aDmnsnVar.setMaxDmnsn3Val(myDmnsnVar.getMaxDmnsn3Val());

      }
      return aDmnsnVar;
   }
   public double cmptMaxDmnsn()
   {
       double aMax = 0;
       double aWidth  = getXMax() - getXMin();
       double aHeight = getYMax() - getYMin();
       double aLength = getZMax() - getZMin();
       if(aWidth >= aHeight && aHeight >=aLength)
       {
           aMax = aWidth;
       }else if(aHeight >= aWidth && aWidth >=aLength)
       {
           aMax = aHeight;
       }else
       {
           aMax = aLength;;
       }
       return aMax;
   }
   public double cmptMinDmnsn()
   {
       double aMin = 0;
       double aWidth  = getXMax() - getXMin();
       double aHeight = getYMax() - getYMin();
       double aLength = getZMax() - getZMin();
       if(aWidth <= aHeight && aHeight <=aLength)
       {
           aMin = aWidth;
       }else if(aHeight <= aWidth && aWidth <=aLength)
       {
           aMin = aHeight;
       }else
       {
           aMin = aLength;;
       }
       return aMin;
   }
   public double cmptMddlDmnsn()
   {
       double aMin = 0;
       double aWidth  = getXMax() - getXMin();
       double aHeight = getYMax() - getYMin();
       double aLength = getZMax() - getZMin();
       if(aWidth >= aHeight && aHeight <=aLength)
       {
           aMin = aHeight;
       }else if(aHeight >= aWidth && aWidth <=aLength)
       {
           aMin = aWidth;
       }else
       {
           aMin = aLength;;
       }
       return aMin;
   }
   public void setDeltaX(float myDeltaX)
   {
      deltaX = myDeltaX;
   }
   public void setDeltaY(float myDeltaY)
   {
      deltaY = myDeltaY;
   }
   public void setDeltaZ(float myDeltaZ)
   {
      deltaZ = myDeltaZ;
   }
   public float getDeltaX()
   {
      return deltaX;
   }
   public float getDeltaY()
   {
      return deltaY;
   }
   public float getDeltaZ()
   {
      return deltaZ;
   }
   public double getInitX()
   {
       return initX;
   }
   public double getInitY()
   {
       return initY;
   }
   public double getInitZ()
   {
       return initZ;
   }
   public void setInitX(double myInitX)
   {
       initX = myInitX;
   }
   public void setInitY(double myInitY)
   {
       initY = myInitY;
   }
   public void setInitZ(double myInitZ)
   {
       initZ = myInitZ;
   }
   public void setEnvrnmntVar( float x1Coordinate, float yCoordinate,float zCoordinate )
   {
      envrnmntX = x1Coordinate;
      envrnmntY = yCoordinate;
      envrnmntZ = zCoordinate;
   }
   public float getEnvrnmntX()
   {
      return envrnmntX;
   }
   public float getEnvrnmntY()
   {
      return envrnmntY;
   }
   public float getEnvrnmntZ()
   {
      return envrnmntZ;
   }

 
}