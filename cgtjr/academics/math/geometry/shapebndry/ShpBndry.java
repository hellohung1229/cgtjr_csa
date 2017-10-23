package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
import cgtjr.academics.math.geometry.shapebndry.Bndry;
import cgtjr.academics.math.geometry.dmnsvar.ClndrclVar;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.elctrclengnrng.fem.BssFnctn;
import cgtjr.academics.elctrclengnrng.fem.HxhdrlBss;
import cgtjr.academics.elctrclengnrng.fem.QuadBss;
import cgtjr.academics.elctrclengnrng.fem.QuadBssST;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.graph.*;
import java.util.Vector;

/**
 * 
 * @author clayton g thomas jr
 */
public class ShpBndry extends Graph implements Bndry
{
   //Could this be change to ShpBndry ... for dynamic boundary insertion?!
   private Vector shapeBoundary;

   //Never add getters or setters for initX,initY,initZ
   private Object objValue;
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

   public ShpBndry()
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
   public int getImgWidth()
   {
       return imgWidth;
   }
   public int getImgHeight()
   {
       return imgHeight;
   }
   public int[] getImgPixels()
   {
      return imgPixels;
   }
   public ShpBndry(double myXMax,double myYMax,double myZMax)
   {
      checkBoundary = true;
      xMax = myXMax;
      yMax = myYMax;
      zMax = myZMax;
      initX=0;
      initY=0;
      initZ=0;
   }
   public ShpBndry(double myXMin,double myYMin,double myZMin,double myXMax,double myYMax,double myZMax)
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
   public ShpBndry(double myXMin,double myYMin,double myZMin,double myXMax,double myYMax,double myZMax,double myInitX,double myInitY,double myInitZ)
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
   public ShpBndry(DmnsnVar myDmnsnVar)
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
      setValue(myDmnsnVar.getValueVal());
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
   public Object getObjValue()
   {
       return objValue;
   }
   public void setObjValue(Object myObjValue)
   {
       objValue = myObjValue;
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
   public void setCheckBoundary(boolean aCheck)
   {
      checkBoundary = aCheck;
   }
   public boolean getCheckBoundary()
   {
      return checkBoundary;
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
   public void updtBndry(Pnt myPoint)
   {
      float xPos = myPoint.getX1();
      float yPos = myPoint.getY1();
      float zPos = myPoint.getZ1();
      updtBndry(xPos,yPos,zPos);    
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
      //System.out.println("ShpBndry: xPos = "+xPos+",yPos = "+yPos+", zPos = "+zPos);
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
   public boolean checkBoundary(Pnt myPoint)
   {
      double x = myPoint.getX1();
      double y = myPoint.getY1();
      double z = myPoint.getZ1();
      return checkBoundary(x,y,z);
   }
   public boolean checkBoundary(double myX1,double myY1,double myZ1)
   {
      Vector aBoundaryShape = getBoundaryShape();
      boolean inBounds = isInBoundaries(myX1,myY1,myZ1,aBoundaryShape);
      return inBounds;
   }
   public boolean isInBoundaries(double x1,double y1,double z1,Vector myPoint2)
   {
      boolean isBoundaryOK1 = false;
      boolean isBoundaryOK2 = false;
      boolean isBoundaryOK3 = false;

      ShpBndry anotherBoundary = null;

      //isBoundaryOK1 = myPoint2.isInBndry(x1,y1,z1);
      //isBoundaryOK1 = myPoint2.isInBndry(x1,y1,z1);
      int aSize = myPoint2.size();

      if(aSize == 0)
      {
         //isBoundaryOK3 = isBoundaryOK1;
         isBoundaryOK3 = true;
         return true;
      }
      for(int i=0;i<aSize;i++)
      {
         anotherBoundary = (ShpBndry)myPoint2.elementAt(i);
         isBoundaryOK2 = anotherBoundary.isInBndry(x1,y1,z1);
         //System.out.println("Point: isInBoundaries size = "+aSize+" i = "+i+"x,y,z = "+x1+","+y1+","+z1);
         /*
         if(myPoint2.getInvrtBndryDcsn())
         {
             isBoundaryOK3 = isBoundaryOK3 || (isBoundaryOK2 && !isBoundaryOK1);
         }else*/
         if(anotherBoundary.getInvrtBndryDcsn())
         {
             isBoundaryOK3 = isBoundaryOK3 || (!isBoundaryOK2 && isBoundaryOK1);
         }else {
             isBoundaryOK3 = isBoundaryOK3 || (isBoundaryOK2 || isBoundaryOK1);
         }
      }
      return isBoundaryOK3;
   }
   public void setBoundaryShape(ShpBndry myBoundaryShape)
   {
      //shapeBoundary = myBoundaryShape;
      cnnctBndry(myBoundaryShape);
   }
   public Vector getBoundaryShape()
   {
      return shapeBoundary;
   }
   public void rmvBoundary(int myIndex)
   {
      shapeBoundary.remove(myIndex);
   }
   public void cnnctBndry(ShpBndry myBoundaryShape)
   {
      //System.out.println("ShpBndry: min x="+myBoundaryShape.getXMin()+", min y = "+myBoundaryShape.getYMin()+", min z = "+myBoundaryShape.getZMin());
      //System.out.println("ShpBndry: max x="+myBoundaryShape.getXMax()+", max y = "+myBoundaryShape.getYMax()+", max z = "+myBoundaryShape.getZMax());      
      if(shapeBoundary == null)
      {
            //System.out.println("ShpBndry ... adding boundary ... null");
          //shapeBoundary = myBoundaryShape;
         shapeBoundary = new Vector();
         //shapeBoundary = new ShpBndry();//update to this version ... & replace shapeBoundary with vertex!?
         //shapeBoundary.addVertex(myBoundaryShape);
         shapeBoundary.addElement(myBoundaryShape);
      }else{
         //shapeBoundary.addVertex(myBoundaryShape);
            //    System.out.println("ShpBndry ... adding boundary ... not null");
         shapeBoundary.addElement(myBoundaryShape);
      }

   }
   public Vector retrieveBoundaries()
   {
      return shapeBoundary;
   }
   public void setInvtBndryDcsn(boolean myCndtn)
   {
      invrtBndryDcsn= myCndtn;
   }
   public boolean getInvrtBndryDcsn()
   {
      return invrtBndryDcsn;
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
      //NOTE: Negative coordinates are problematic?!
      double aWidth  = getXMax()-getXMin();
      double aHeight = getYMax()-getYMin();
      double aLength = getZMax()-getZMin();
      double aDeltaX = getDeltaX();
      double aDeltaY = getDeltaY();
      double aDeltaZ = getDeltaZ();
      double anInitX = getInitX();
      double anInitY = getInitY();
      double anInitZ = getInitZ();
      DmnsnVar aDmnsnVar = new DmnsnVar(0,0,0,getXMin(),getYMin(),getZMin(),getXMax(),getYMax(),getZMax());
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
         aDmnsnVar.setMinDmnsn3Val(myDmnsnVar.getMinDmnsn3Val());         
         aDmnsnVar.setMaxDmnsn1Val(cmptMddlDmnsn()+1.5f);
         aDmnsnVar.setMaxDmnsn2Val(2*Math.PI);         
         aDmnsnVar.setMaxDmnsn3Val(cmptMaxDmnsn()+2.0f);
      }else if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getHlxCrdntTp()))
      {
         aDmnsnVar = new HelixVar();
         //double aLength = Math.sqrt(1+1)*cmptMaxDmnsn();
         double aLength = cmptMaxDmnsn()*2*Math.PI/(CrdntType.getPitch());         
         aDmnsnVar.setMaxDmnsn2Val(aLength);           
         /*
         double aRadius = cmptMddlDmnsn();
         double aLength = Math.sqrt(1+1)*cmptMaxDmnsn();
         aDmnsnVar.setMaxDmnsn1Val(aRadius);
         //aDmnsnVar.setMaxDmnsn1Val(3);         
         aDmnsnVar.setMaxDmnsn2Val(aLength);        
         //aDmnsnVar.setMaxDmnsn2Val(4*Math.PI);                 
         //aDmnsnVar.setMaxDmnsn3Val(myDmnsnVar.getMaxDmnsn3Val());   
         aDmnsnVar.setMaxDmnsn3Val(1);    
         
         aDmnsnVar.setMinDmnsn1Val(0.5f);
         aDmnsnVar.setInitXVal(0.5f);         
         //aDmnsnVar.setInitXVal(myDmnsnVar.getInitXVal());  
         * 
         */
         System.out.println("ShpBndry: dmnsn 3  = "+aDmnsnVar.getMaxDmnsn3Val()+", dmnsn2 = "+aDmnsnVar.getMaxDmnsn2Val()+", min val = "+aDmnsnVar.getDelta1Val());
                 
         //aDmnsnVar.setMaxDmnsn3Val(0);
      }else if(aCrdntType.equals(CrdntType.getCrtsnCrdntTp()) && myCrdntType2.equals(CrdntType.getCrtsnCrdntTp()))
      {
         aDmnsnVar = new Crtssn3DVar();
         
         aDmnsnVar.setMinDmnsn1Val(myDmnsnVar.getMinDmnsn1Val()-2.0);
         aDmnsnVar.setMinDmnsn2Val(myDmnsnVar.getMinDmnsn2Val()-2.0);
         aDmnsnVar.setMinDmnsn3Val(myDmnsnVar.getMinDmnsn3Val()-2.0);
         aDmnsnVar.setMaxDmnsn1Val(myDmnsnVar.getMaxDmnsn1Val()+2.0);
         aDmnsnVar.setMaxDmnsn2Val(myDmnsnVar.getMaxDmnsn2Val()+2.0);
         aDmnsnVar.setMaxDmnsn3Val(myDmnsnVar.getMaxDmnsn3Val()+2.0);
          
         /*
         aDmnsnVar.setMinDmnsn1Val(0.0);
         aDmnsnVar.setMinDmnsn2Val(0.0);
         aDmnsnVar.setMinDmnsn3Val(0.0);
         aDmnsnVar.setMaxDmnsn1Val(+4.0);
         aDmnsnVar.setMaxDmnsn2Val(+4.0);
         aDmnsnVar.setMaxDmnsn3Val(+4.0);
         */
         System.out.println("ShpBndry: min x = "+aDmnsnVar.getMinDmnsn1Val()+",min y = "+aDmnsnVar.getMinDmnsn2Val()+", min z = "+aDmnsnVar.getMinDmnsn3Val());
         System.out.println("ShpBndry: max x = "+aDmnsnVar.getMaxDmnsn1Val()+",max y = "+aDmnsnVar.getMaxDmnsn2Val()+", max z = "+aDmnsnVar.getMaxDmnsn3Val());         
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
       System.out.println("ShpBndry: aMax = "+aMax);
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
       System.out.println("ShpBndry: aMin = "+aMin);
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
   public void cnnctBndry(DmnsnVar myDmnsnVar[])
   {
      int aLength = myDmnsnVar.length;
      for(int i=0;i<aLength;i++)
      {
         cnnctBndry(new ShpBndry(myDmnsnVar[i]));

      }
   }
   
   public int getLclSize()
   {
      int aValue = getLclSize(this);
      return aValue;
   }
   public static int getLclSize(ShpBndry myShape)
   {
      int lclSize = 1;
      double aWidth  = myShape.getXMax() - myShape.getXMin();
      double aHeight = myShape.getYMax() - myShape.getYMin();
      double aLength = myShape.getZMax() - myShape.getZMin();
      if(aWidth != 0)
      {
         lclSize *= 2;
      }
      if(aHeight != 0)
      {
         lclSize *= 2;
      }
      if(aLength != 0)
      {
         lclSize *= 2;
      }
      return lclSize;
   }
   /*
   public BssFnctn rtrvBssFnctn()
   {
      BssFnctn aBssFnctn = null;
      if(getLclSize() == 4 && crdntTp.equals(CrdntType.getClndrclCrdntTp()))
      {
          System.out.println("ShpBndry: test 1");
          aBssFnctn = new QuadBssST();
      }else if(getLclSize() == 4 && crdntTp.equals(CrdntType.getSphrclCrdntTp()))
      {
          System.out.println("ShpBndry: test 2");
          aBssFnctn = new QuadBssST();
      }else if(getLclSize() == 4)
      {
          System.out.println("ShpBndry: test 3");
          aBssFnctn = new QuadBss();
      }else if(getLclSize() == 8){
          System.out.println("ShpBndry: test 4");
          aBssFnctn = new HxhdrlBss();
      }else{
          System.err.println("SphrclVar: null basis function!");
      }
      return aBssFnctn;
   }*/
 
}