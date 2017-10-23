package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;

public class Crtssn2DVar extends DmnsnVar implements ApplctnVar
{
   private static DmnsnVar dmnsnVar;
   private static String dataNmKey		= "crtssn2d";

   public Crtssn2DVar()
   {
      super(4,4,0,1,1,1);
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
         Crtssn2DVar aCrtssn2DVar = new Crtssn2DVar();
         dmnsnVar = aCrtssn2DVar;
      }
      return dmnsnVar;
   }
}