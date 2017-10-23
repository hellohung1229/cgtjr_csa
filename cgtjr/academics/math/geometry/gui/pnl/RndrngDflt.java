/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.DataVar;

/**
 *
 * @author clayton g thomas jr
 */
public class RndrngDflt // extends MtrlFctry
{
    private static DataVar dfltDatatVar;

    public static void setDfltVar(DataVar myDataVar)
    {
       dfltDatatVar = myDataVar;
    }
    public static DataVar getDfltVar()
    {
       return dfltDatatVar;
    }
}
