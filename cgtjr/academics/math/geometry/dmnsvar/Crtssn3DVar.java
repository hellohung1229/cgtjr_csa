package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;

public class Crtssn3DVar extends DmnsnVar implements ApplctnVar
{
   private static DmnsnVar dmnsnVar;

   private static String dataNmKey		= "cartessian";
   
   private final static String crtsnCrdntTp = "cartessian";
   
   public Crtssn3DVar()
   {
      super(0,0,0,0,0,0,4,4,0,.5,.5,1);
      setCrdntTpVal(dataNmKey);  
      //dmnsnVar = this;
   }
   public static void setDmnsnVar(DmnsnVar myDmnsnVar)
   {
      dmnsnVar = myDmnsnVar;
   }
   public static DmnsnVar getDmnsnVar()
   {
      if(dmnsnVar == null)
      {
         Crtssn3DVar aCrtssn3DVar = new Crtssn3DVar();
         dmnsnVar = aCrtssn3DVar;
      }
      return dmnsnVar;
   }
   public double[] cmptCenter()
   {
      double aWidth  = (getMaxDmnsn1Val() - getMinDmnsn1Val());
      double aHeight = (getMaxDmnsn2Val() - getMinDmnsn2Val());
      double aLength = (getMaxDmnsn3Val() - getMinDmnsn3Val());
      double aCenter[] = {aWidth/2,aHeight/2,aLength/2};
      return aCenter;
   }
}