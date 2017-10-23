/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.gui;

import cgtjr.academics.elctrclengnrng.video.VideoClusterLocationApplt;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author clayton g thomas jr
 */
public class DataJEditorPane {

    private static JTextPane textpane;
    static DefaultStyledDocument document;
    static Style style;

    public DataJEditorPane() {

    }

    public static JTextPane getTxtFld() {
        if (textpane == null) {
            document = new DefaultStyledDocument();
            textpane = new JTextPane(document);
            StyleContext context = new StyleContext();
            style = context.addStyle("test", null);
            StyleConstants.setForeground(style, Color.BLUE);
            //jEdtrPane = new JEditorPane();
            try {
                // add some data to the document
                document.insertString(0, "", style);
            } catch (BadLocationException ex) {
                Logger.getLogger(VideoClusterLocationApplt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return textpane;
    }

    public static void setColor(Color myColor) {
        StyleConstants.setForeground(style, myColor);
    }

    public static void insertString(String myText, Color myColor) {
        if(myText == null || myColor == null || style == null){
            return;
        }
        StyleConstants.setForeground(style, myColor);
        insertString(myText);
    }

    public static void insertString(String myText) {
        int aLength = document.getLength();
        try {
            document.insertString(aLength, myText, style);
        } catch (BadLocationException ex) {
            //Logger.getLogger(DataJEditorPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void clearText() {
        if(document == null){
            return;
        }
        int aLength = document.getLength();
        try {
            document.remove(0,aLength);
        } catch (BadLocationException ex) {
            //Logger.getLogger(DataJEditorPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}