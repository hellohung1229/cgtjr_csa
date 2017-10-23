/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general.gui;

import cgtjr.academics.general.IntnstyNmbrVar;

/**
 *
 * @author clayton g thomas jr
 */
public class IntnstyNmbrDflt
{
    private static IntnstyNmbrVar dfltDatatVar;

    public static void setDfltVar(IntnstyNmbrVar myDataVar)
    {
       dfltDatatVar = myDataVar;
    }
    public static IntnstyNmbrVar getDfltVar()
    {
       return dfltDatatVar;
    }
}
