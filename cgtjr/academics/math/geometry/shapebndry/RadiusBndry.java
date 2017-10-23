package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;


public class RadiusBndry extends ShpBndry
{
   double radius;
   public RadiusBndry(double myRadius)
   {
      radius = myRadius;
   }
   public RadiusBndry(double myRadius,DmnsnVar myDmnsnVar)
   {
      super(myDmnsnVar);
      radius = myRadius;
   }
   public boolean isInBndry(double myX,double myY,double myZ)
   {
      boolean isInBoundary = false;
      float aX = (float)myX;
      float aY = (float)myY;
      float aZ = (float)myZ;

      double r = aX;
      double t = aY;
      double p = aZ;

      float x = (float)getEnvrnmntX();
      float y = (float)getEnvrnmntY();
      float z = (float)getEnvrnmntZ();

      double myRadius1 = PntTool.getDistance(r,t,p,x,y,z);
      
      if(myRadius1 <= radius & z == 0)
      {
          isInBoundary = true;
      }else{
          isInBoundary = false;
      }
      //System.out.println("RadiusBndry: r="+r+",t="+t+",p="+p+",x="+x+",y="+y+",z="+z+",distance="+myRadius1+"inboundary = "+isInBoundary);
      return isInBoundary;
   }
}