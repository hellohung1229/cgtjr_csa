/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.gui;

import java.awt.TextArea;
import javax.swing.JTextArea;


/**
 *
 * @author clayton g thomas jr
 */
public class WTSTextField {

    private static JTextArea txtFld;

    /*
    public WTSTextField() {
    }*/
    public static JTextArea getTxtFld() {
        if (txtFld == null) {
            txtFld = new JTextArea(10,10);            
        }
        return txtFld;
    }
    public static void append(String myString)
    {
        /*
        if (txtFld != null) {
            txtFld.append(myString);
        }        
        */
    }
    public static void clearTxtArea() {
        if (txtFld != null) {
            txtFld.setText("");
        }
    }
}