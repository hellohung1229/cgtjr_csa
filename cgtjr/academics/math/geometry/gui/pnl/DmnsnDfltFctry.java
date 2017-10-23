/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.DataVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVarFctry;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.util.LinkedHashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class DmnsnDfltFctry //extends DmnsnFctry
{
    private static DataVar dfltDatatVar;
    private static DmnsnVarFctry dfltFctry;

    public static void setDfltVar(DataVar myDataVar)
    {
       dfltDatatVar = myDataVar;
    }
    public static DataVar getDfltVar()
    {
       return dfltDatatVar;
    }
    public static LinkedHashMap getDfltKeyVarMap()
    {
       if(dfltFctry == null)
       {
          dfltFctry = new DmnsnVarFctry();
       }
       return dfltFctry.getKeyVarMap();
    }
    public static DataVar getDfltVar(DmnsnVar myDmnsnVar)
    {
       String aKey = myDmnsnVar.getCrdntTpVal();
       DataVar dataVarTmp = (DataVar)getDfltVar(aKey);
       return dataVarTmp;
    }
    public static DataVar getDfltVar(String myKey)
    {
       if(dfltFctry == null)
       {
          dfltFctry = new DmnsnVarFctry();
       }
       return (DataVar)dfltFctry.get(myKey);
    }
    public static Object[] rtrvDfltKeyVarArry()
    {
       if(dfltFctry == null)
       {
          dfltFctry = new DmnsnVarFctry();
       }
       return dfltFctry.rtrvKeyVarArry();
    }
}