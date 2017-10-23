package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;


public class CrtssnSphrBndry extends ShpBndry
{
   public CrtssnSphrBndry(double myXMax,double myYMax,double myZMax)
   {
       super(myXMax,myYMax,myZMax);
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
      return super.isInBndry(myRadius,myTheta,myPhi);
   }
}