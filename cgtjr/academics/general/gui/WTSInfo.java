/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author clayton g thomas jr
 */
public class WTSInfo extends JPanel {

    JLabel nmbrElmntsJLbl;
    JLabel ttlNmbrElmntsJLbl;
    JLabel srfceAreaJLbl;
    JLabel ttlSrfcAreaJLbl;
    JTextField nmbrElmntsJTxtFld;
    JTextField ttlNmbrElmntsJTxtFld;
    JTextField srfceAreaJTxtFld;
    JTextField ttlSrfcAreaJTxtFld;
    GridBagLayout aGridBagLayout;
    GridBagConstraints c;

    public WTSInfo() {
        nmbrElmntsJLbl = new JLabel();
        ttlNmbrElmntsJLbl = new JLabel();
        srfceAreaJLbl = new JLabel();
        ttlSrfcAreaJLbl = new JLabel();

        nmbrElmntsJTxtFld = new JTextField(12);
        ttlNmbrElmntsJTxtFld = new JTextField(12);
        srfceAreaJTxtFld = new JTextField(12);
        ttlSrfcAreaJTxtFld = new JTextField(12);

        nmbrElmntsJLbl.setText("Number of Elements");
        ttlNmbrElmntsJLbl.setText("Total Nmbr Elmnts");
        srfceAreaJLbl.setText("Surface Area");
        ttlSrfcAreaJLbl.setText("Total Surface Area");
        
        //aGridBagLayout = new GridBagLayout();
        //setLayout(aGridBagLayout);
        
        c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.WEST; //bottom of space
        //c.ipadx = 0;       //reset to default
        //c.ipady = 0;       //reset to default        
        //c.weighty = 1.0;   //request any extra vertical space

        //c.insets = new Insets(10, 0, 0, 0);  //top padding
        //c.gridx = 1;       //aligned with button 2
        //c.gridwidth = 2;   //2 columns wide
        //c.gridy = 2;       //third row
        //aGridBagLayout.setConstraints(button, c);

        //aGridBagLayout.setConstraints(nmbrElmntsJLbl, c);        
        add(nmbrElmntsJLbl);
        c.ipadx = 1;       //reset to default
        c.ipady = 0;       //reset to default                
        //aGridBagLayout.setConstraints(nmbrElmntsJTxtFld, c);          
        add(nmbrElmntsJTxtFld);
        c.ipadx = 2;       //reset to default
        c.ipady = 0;       //reset to default                
        //aGridBagLayout.setConstraints(ttlNmbrElmntsJLbl, c);    
        add(ttlNmbrElmntsJLbl);
        c.ipadx = 3;       //reset to default
        c.ipady = 0;       //reset to default 
        //aGridBagLayout.setConstraints(ttlNmbrElmntsJTxtFld,c);           
        add(ttlNmbrElmntsJTxtFld);
        /*
        add(ttlNmbrElmntsJLbl);
        add(ttlNmbrElmntsJTxtFld);

        add(srfceAreaJLbl);
        add(srfceAreaJTxtFld);

        add(ttlSrfcAreaJLbl);
        add(ttlSrfcAreaJTxtFld);
        * 
        */
    }
}