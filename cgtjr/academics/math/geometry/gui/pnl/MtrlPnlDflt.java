/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.DataVar;
import cgtjr.academics.general.MtrlVarFctry;
import java.util.LinkedHashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class MtrlPnlDflt // extends MtrlFctry
{
    private static DataVar dfltDatatVar;
    private static MtrlVarFctry dfltFctry;

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
          dfltFctry = new MtrlVarFctry();
       }
       return dfltFctry.getKeyVarMap();
    }
    public static DataVar getDfltVar(String myKey)
    {
       if(dfltFctry == null)
       {
          dfltFctry = new MtrlVarFctry();
       }
       return (DataVar)dfltFctry.get(myKey);
    }
    public static Object[] rtrvDfltKeyVarArry()
    {
       if(dfltFctry == null)
       {
          dfltFctry = new MtrlVarFctry();
       }
       return dfltFctry.rtrvKeyVarArry();
    }
}
