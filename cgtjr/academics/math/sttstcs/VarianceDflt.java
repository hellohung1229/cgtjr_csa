/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.sttstcs;

import cgtjr.academics.general.DataVar;

/**
 * A class for encapsulating the default variance variables.
 * @author clayton g thomas jr
 */
public class VarianceDflt
{
    private static DataVar dfltDatatVar;
    /**
     * This method sets the default DataVar class the the specified DataVar
     * @param myDataVar
     */
    public static void setDfltVar(DataVar myDataVar)
    {
       dfltDatatVar = myDataVar;
    }
    /**
     * This method gets the default DataVar variable
     * @return the default DataVar variable
     */
    public static DataVar getDfltVar()
    {
       return dfltDatatVar;
    }
}
