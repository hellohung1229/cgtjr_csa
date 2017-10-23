package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HelixPitchVar extends DmnsnVar implements ApplctnVar
{
   private static DmnsnVar dmnsnVar;
   private static String dataNmKey        = "pitch";
   private final static String hlxCrdntTp = "helix";
   private static String pitchKey         = "pitch";

   private static double pitchVal         = 5.4;



   public HelixPitchVar()
   {
      super(0,0,0,0,0,0,4,2*Math.PI,1,0.5f,Math.PI/8,0.5f);
      HashMap aLinkedHashMap = getKeyValMap();
      aLinkedHashMap.clear();
      pitchVal = 5.4;
      insrtData(pitchKey,""+pitchVal);
      setCrdntTpVal(hlxCrdntTp);
   }
   
   public static String getPitchKey()
   {
      return pitchKey;
   }
   public static void setPitchVal(double myPitchVal)
   {
      pitchVal = myPitchVal;
   }
   public static double getPitchVal()
   {
      return pitchVal;
   }
   /*
   public HelixVar(double myDmnsn1,double myDmnsn2,double myDmnsn3,double myDelta1,double myDelta2,double myDelta3,double myInit1,double myInit2,double myInit3)
   {
      super(myDmnsn1,myDmnsn2,myDmnsn3,myDelta1,myDelta2,myDelta3,myInit1,myInit2,myInit3);
      setCrdntTpVal(dataNmKey);
   }*/
   public static void setDmnsnVar(DmnsnVar myDmnsnVar)
   {
      dmnsnVar = myDmnsnVar;
   }
   public static DmnsnVar getDmnsnVar()
   {
      if(dmnsnVar == null)
      {
         HelixPitchVar aHelixVar = new HelixPitchVar();
      }
      return dmnsnVar;
   }
   public double[] cmptCenter()
   {
      double aLength = (getMaxDmnsn2Val() - getMinDmnsn2Val())/2;
      double aCenter[] = {0,0,aLength};
      return aCenter;
   }
   private HashMap crtKeyValMap()
   {
      String pitchKey = getPitchKey();
      String pitchVal = ""+getPitchVal();
      insrtData(pitchKey,pitchVal);

      return super.getKeyValMap();
   }
   public void updtKeyVals(HashMap aLinkedHashMap)
   {
      //super.updtKeyVals(aLinkedHashMap);
         String aKey = getPitchKey();
         String aVal = (String)aLinkedHashMap.get(aKey);
         System.out.println("DmnsnVar key = "+aKey+", val = "+aVal);
         double aDVal = Double.parseDouble(aVal);
         setPitchVal(aDVal);
   }
}