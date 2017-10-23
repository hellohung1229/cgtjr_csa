package cgtjr.academics.general;

import java.util.*;

public abstract class GeneralPnlActn{

   public float rtrvFloatValue(String myString1)
   {
      Float aFloat = new Float(myString1);
      float aFloatValue = aFloat.floatValue();
      return aFloatValue;
   }
   public float rtrvInt(String myString1)
   {
      Integer anInteger = new Integer(myString1);
      int aIntValue = anInteger.intValue();
      return aIntValue;
   }
   public abstract void doAction(HashMap aHashMap);

}