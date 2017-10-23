package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class FileImageSelector extends JComboBox implements ActionListener {

    private static String currentItem;

    public FileImageSelector() {
        super();
        setEditable(true);
        addActionListener(this);      
    }
    public void processParamInfo(Applet myJApplet) {
        String filename = "filename";
        int fileIndex = 0;
        String fileUrl = "";
        //addItem("moleculewts.jar!/data/pdb/glyhelix1.ml2");
        fileUrl = myJApplet.getParameter(filename + fileIndex);
        while (fileUrl != null) {
            addItem(fileUrl);
            ++fileIndex;
            fileUrl = myJApplet.getParameter(filename + fileIndex);
        }
    }
    public void actionPerformed(ActionEvent e) {
        //System.out.println("event command = " + e.getActionCommand() + " type = " + e.getID());
        //TODO: comboBoxChanged ... change to variable
        if (e.getActionCommand() != null && !e.getActionCommand().equals("comboBoxChanged")) {
            //String aFileName = getText();
            //System.out.println("FileJComboBox: test 1 ");
            String aFileName = (String) getSelectedItem();
            currentItem = aFileName;
        }
    }
    public static String getCurrentItem()
    {
        return currentItem;
    }
}