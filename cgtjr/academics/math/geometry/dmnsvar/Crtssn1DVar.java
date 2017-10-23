package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;

public class Crtssn1DVar extends DmnsnVar implements ApplctnVar
{
   private static DmnsnVar dmnsnVar;
   private static String dataNmKey		= "crtssn1d";

   public Crtssn1DVar()
   {
      super(4,0,0,1,1,1);
      setCrdntTpVal(dataNmKey);
   }
   public static void setDmnsnVar(DmnsnVar myDmnsnVar)
   {
      dmnsnVar = myDmnsnVar;
   }
   public static DmnsnVar getDmnsnVar()
   {
      if(dmnsnVar == null)
      {
         Crtssn1DVar aCrtssn1DVar = new Crtssn1DVar();
         dmnsnVar = aCrtssn1DVar;
      }
      return dmnsnVar;
   }
}