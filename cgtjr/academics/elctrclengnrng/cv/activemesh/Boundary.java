package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;

public class Boundary extends Pnt
{
   Pnt boundaryPnt;
   public Boundary(Pnt myPnt)
   {
      boundaryPnt = myPnt;
   }
   public boolean isInBoundary(double x,double y,double z)
   {
      boolean isInBndry = boundaryPnt.isInBndry(x, y, z);
      if(isInBndry == true)
      {
        inBoundaryAction(x,y,z);
      }else{
        notInBoundaryAction(x,y,z);
      }
      boundaryAction(x,y,z);
      return isInBndry;
   }
   public boolean isOnBoundary(double x,double y,double z)
   {
      boolean isOnBndry = boundaryPnt.isInBndry(x, y, z);//isonboundry !!!
      if(isOnBndry == true)
      {
        //onBoundaryAction(x,y,z);
      }else{
        //notOnBoundaryAction(x,y,z);
      }
      boundaryAction(x,y,z);
      return isOnBndry;
   }
   public double inBoundaryAction(double x,double y,double z)
   {
      return 0;
   }
   public double notInBoundaryAction(double x,double y,double z)
   {
      return 0;
   }
   public double boundaryAction(double x,double y,double z)
   {
      return 0;
   }
}