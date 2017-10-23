package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;

public class ClndrCrtssnShp extends ShpBndry
{
   private float radiusMin = 0;
   private float radiusMax = 5;
   private float zMin = 0;
   private float zMax = 0;
   private float phiMin = 0;
   private float phiMax = (float)(2*Math.PI);
   
   public ClndrCrtssnShp(double myRadius)
   {
      radiusMax = (float)myRadius;
   }
   public ClndrCrtssnShp(double myRadius,double myPhi,double myZ)
   {
      radiusMax = (float)myRadius;
      phiMax = (float)myPhi;
      zMax = (float)myZ;
   }
   public boolean isInBndry(double myX,double myY,double myZ)
   {
      float x = (float)myX - getEnvrnmntX();
      float y = (float)myY - getEnvrnmntY();
      float z = (float)myZ - getEnvrnmntZ();

      double myRadius = PntTool.getDistance(0,0,0,x,y,z);
      double myPhi = 0;
      double myTheta = 0;
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
      return isInClndrclCrdnt(myRadius,myTheta,z);
   }
   
   public boolean isInRadiusBoundary(double myRadius)
   {
      boolean isInRBoundary = false;
      //System.out.println("ClndrCrtssnShp.isInRadiusBoundary() : radius = "+myRadius+" min radius = "+radiusMin+", max radius = "+radiusMax);
      if(myRadius >= radiusMin && myRadius <= radiusMax )
      {
         isInRBoundary = true;
      }
      return isInRBoundary;
   }
   public boolean isInPhiBoundary(double myPhi)
   {
      boolean isInPhiBoundary = false;
      //System.out.println("ClndrCrtssnShp.isInPhiBoundary() : phi = "+myPhi+" min phi = "+phiMin+", max phi = "+phiMax);
      if( (myPhi >= phiMin) && (myPhi <= phiMax) )
      {
         isInPhiBoundary = true;
      }
      return isInPhiBoundary;
   }
   public boolean isInZBoundary(double myZ)
   {
      boolean isInZBoundary = false;
      //System.out.println("ClndrCrtssnShp.isInZBoundary() : z = "+myZ+" min Z = "+zMin+", max Z = "+zMax);
      if( (myZ >= zMin) && (myZ <= zMax) )
      {
         isInZBoundary = true;
      }
      return isInZBoundary;
   }
   public boolean isInClndrclCrdnt(double r,double t,double p)
   {
      boolean isInABoundary = false;

      if(isInRadiusBoundary(r) && isInPhiBoundary(t) && isInZBoundary(p))
      {
         isInABoundary = true;
      }
      //System.out.println("ClndrCrtssnShp : isInABoundary = "+isInABoundary);
      return isInABoundary;
   }
}