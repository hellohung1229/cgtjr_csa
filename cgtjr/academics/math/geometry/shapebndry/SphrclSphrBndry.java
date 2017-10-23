package cgtjr.academics.math.geometry.shapebndry;

import cgtjr.academics.math.geometry.dmnsvar.CrdntType;


public class SphrclSphrBndry extends CrtssnSphrBndry
{

   public SphrclSphrBndry(double myXMax,double myYMax,double myZMax)
   {
       super(myXMax,myYMax,myZMax);
   }
   public boolean isInBndry(float myX,float myY,float myZ)
   {
       double r = CrdntType.sphericalC1(myX, myY, myZ,0,0);
       double t = CrdntType.sphericalC2(myX, myY, myZ,0,0);
       double p = CrdntType.sphericalC3(myX, myY, myZ,0,0);
       return super.isInBndry(r,t,p);
   }
}