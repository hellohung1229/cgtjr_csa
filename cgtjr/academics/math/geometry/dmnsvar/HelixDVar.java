package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;

public class HelixDVar extends DmnsnVar implements ApplctnVar
{
   private static DmnsnVar dmnsnVar;
   private static String dataNmKey		= "crtssn3d";

   public HelixDVar()
   {
      super(4,4,4,1,1,1);
      setCrdntTpVal(dataNmKey);  
      dmnsnVar = this;
   }
   public static void setDmnsnVar(DmnsnVar myDmnsnVar)
   {
      dmnsnVar = myDmnsnVar;
   }
   public static DmnsnVar getDmnsnVar()
   {
      if(dmnsnVar == null)
      {
         HelixDVar aCrtssn3DVar = new HelixDVar();
         dmnsnVar = aCrtssn3DVar;
      }
      return dmnsnVar;
   }
}