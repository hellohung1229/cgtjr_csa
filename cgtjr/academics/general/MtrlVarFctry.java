/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import cgtjr.academics.math.geometry.dmnsvar.DataVarFctry;
import java.util.LinkedHashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class MtrlVarFctry extends DataVarFctry{

   /*
    private static DataVar dfltVar;

    public static void setDfltVar(DataVar myDmnsnVar)
    {
        dfltVar = myDmnsnVar;
    }
    public static DataVar getDfltVar()
    {
        return dfltVar;
    }*/

   protected LinkedHashMap crtKeyVarMap(LinkedHashMap myLinkedHashMap)
   {
         //mtrlVarHshSt = new LinkedHashMap();
         MtrlVar aMtrlVar0 = new MtrlVar("Default",0.0,0.0,0.0,1.0,0.01,0.01,0.5,0.5,0.5,32.0,0.0);
         MtrlVar aMtrlVar1 = new MtrlVar("Black Polyethylene",0.0,0.0,0.0,0.01,0.01,0.01,0.5,0.5,0.5,32.0,0.0);
         MtrlVar aMtrlVar2 = new MtrlVar("Brass",0.329412,0.223529,0.027451,0.780392,0.568627,0.113725,0.992157,0.941176,0.807843,27.8974,0);
         MtrlVar aMtrlVar3 = new MtrlVar("Bronze",0.2125,0.1275,0.054,0.714,0.4284,0.18144,0.393548,0.271906,0.166721,25.6,0);
         MtrlVar aMtrlVar4 = new MtrlVar("Aluminum",0.25,0.25,0.25,0.4,0.4,0.4,0.774597,0.774597,0.774597,76.8,0);
         MtrlVar aMtrlVar5 = new MtrlVar("Gold",0.24725,0.058824,0.113725,0.427451,0.470588,0.541176,0.3333,0.3333,0.521569,9.84615,0);
         MtrlVar aMtrlVar6 = new MtrlVar("Peweter",0.10588,0.058824,0.113725,0.427451,0.470588,0.541176,0.3333,0.3333,0.521569,9.84615,0);
         MtrlVar aMtrlVar7 = new MtrlVar("Silver",0.19225,0.19225,0.19225,0.50754,0.50754,0.50754,0.508273,0.508273,0.508273,51.2,0);
         MtrlVar aMtrlVar8 = new MtrlVar("Copper",0.19125,0.0735,0.0225,0.7038,0.27048,0.0828 ,0.256777,0.137622,0.086014,12.8,0);

         myLinkedHashMap.put(aMtrlVar0.getMtrlVal(),aMtrlVar0);
         myLinkedHashMap.put(aMtrlVar1.getMtrlVal(),aMtrlVar1);
         myLinkedHashMap.put(aMtrlVar2.getMtrlVal(),aMtrlVar2);
         myLinkedHashMap.put(aMtrlVar3.getMtrlVal(),aMtrlVar3);
         myLinkedHashMap.put(aMtrlVar4.getMtrlVal(),aMtrlVar4);
         myLinkedHashMap.put(aMtrlVar5.getMtrlVal(),aMtrlVar5);
         myLinkedHashMap.put(aMtrlVar6.getMtrlVal(),aMtrlVar6);
         myLinkedHashMap.put(aMtrlVar7.getMtrlVal(),aMtrlVar7);

      return myLinkedHashMap;
   }
}