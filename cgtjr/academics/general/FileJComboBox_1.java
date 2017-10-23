package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JComboBox;

public class FileJComboBox_1 extends JComboBox implements ActionListener {

    SceneRoot theSceneRoot;
    private static String currentItem;

    /*
     public FileJComboBox(SceneRoot mySceneRoot)
     {
     super();
     theSceneRoot = mySceneRoot;
     //setText("moleculewts.jar!/data/pdb/glyhelix1.ml2");
     addActionListener(this);
     }*/
    public FileJComboBox_1(SceneRoot mySceneRoot) {
        super();
        theSceneRoot = mySceneRoot;
        //setText("moleculewts.jar!/data/pdb/glyhelix1.ml2");
        setEditable(true);
        addActionListener(this);
        // processParamInfo(myApplet);        
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
            //System.out.println("FileJComboBox: filename = " + aFileName);
            if (theSceneRoot == null) {
                System.err.println("theSceneRoot is null");
            }
            theSceneRoot.loadScene(aFileName);
        }
    }
    public static String getCurrentItem()
    {
        return currentItem;
    }
}