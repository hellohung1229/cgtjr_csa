package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.*;
import java.util.HashMap;
import javax.media.j3d.Canvas3D;
import javax.swing.*;

/**
 * 
 * @author clayton g thomas jr
 * This class provides the containment for both the tab navigation and visual canvas.
 * 
 */
public class LabStation extends JPanel {

    private static FileTextField aFileTextField;   
    private FileJComboBox aFileJComboBox;
    private static JEditorPane editorPane;
    private MainToolBar mnToolBar;
    private FileMenuBar mainMenuBar;
    private JSplitPane splitPane;
    private Panel canvasPanel;
    private static Applet labWndwPnl;
    private HashMap labHashMap;
    private JPanel maintPanel;
    private JScrollPane aJScrollPane;
    private SceneRoot canvas3DUniv;
    private SceneRoot aSceneRoot;

    /**
     * Creates a new LabStation instance.
     * @param mySceneRoot The mySceneRoot is the reference to the SceneRoot object.
     * @param myEditorPane The parameter is a reference to the editorPane object.
     */
    public LabStation(SceneRoot mySceneRoot, JEditorPane myEditorPane) {
        labHashMap = new HashMap();
        mainMenuBar = new FileMenuBar(aSceneRoot);
        mnToolBar = new MainToolBar();
        initLabStation(mySceneRoot);
    }
    /**
     * Creates a new LabStation instance.
     * @param mySceneRoot The mySceneRoot is the reference to the SceneRoot object.
     * @param myLabWndwPnl The parameter is a reference to the applet that contains the LabStation.
     */
    public LabStation(SceneRoot mySceneRoot, JApplet myLabWndwPnl) {
        labWndwPnl = myLabWndwPnl;
        labHashMap = new HashMap();
        mainMenuBar = new FileMenuBar(aSceneRoot);
        mnToolBar = new MainToolBar(myLabWndwPnl);
        initLabStation(mySceneRoot);
    }
    /**
     * Creates a new LabStation instance.
     * @param mySceneRoot The mySceneRoot is the reference to the SceneRoot object.
     */
    public LabStation(SceneRoot mySceneRoot) {
        labHashMap = new HashMap();
        mainMenuBar = new FileMenuBar(aSceneRoot);
        mnToolBar = new MainToolBar();
        initLabStation(mySceneRoot);
    }
    /**
     * Creates a new LabStation instance.
     */
    public LabStation() {
        aSceneRoot = new SceneRoot();
        labHashMap = new HashMap();
        mainMenuBar = new FileMenuBar(aSceneRoot);
        mnToolBar = new MainToolBar();
        initLabStation(aSceneRoot);
        AppTab anAppTab = new AppTab(aSceneRoot, editorPane);
        String anAppTabNm = anAppTab.getClass().getName();
        insertLabPanel(anAppTabNm, anAppTab);
        displayLabPanel(anAppTabNm);
    }
    /**
     * The method sets a reference to the applet which contains the application.
     * @param myApplet 
     */
    public static void setLabWndwPnl(Applet myApplet) {
        labWndwPnl = myApplet;
    }
    /**
     * The method gets a reference to the applet which contains the application.
     * @return Applet that contains the LabStation
     */
    public static Applet getLabWndwPnl() {
        return labWndwPnl;
    }
    /**
     * This method provides the ability to insert additional panels into the LabStation.
     * @param myName
     * @param myJComponent 
     */
    public void insertLabPanel(String myName, JComponent myJComponent) {
        labHashMap.put(myName, myJComponent);
    }

    private void initLabStation(SceneRoot mySceneRoot) {
        canvas3DUniv = mySceneRoot;
        //aFileTextField = new FileTextField(canvas3DUniv);
        //aFileJComboBox = new FileJComboBox(canvas3DUniv,labWndwPnl);
        aFileJComboBox = new FileJComboBox(canvas3DUniv);      
        aFileJComboBox.processParamInfo(labWndwPnl);
        GridBagLayout aGridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(aGridBagLayout);

        editorPane = new JEditorPane();
        aJScrollPane = new JScrollPane(editorPane);

        aJScrollPane.setPreferredSize(new Dimension(800, 100));
        aJScrollPane.setMinimumSize(new Dimension(800, 100));
        //editorPane.setPreferredSize(new Dimension(800,100));

        Canvas3D myCanvas3D = mySceneRoot.rtrvCanvas3D();

        canvasPanel = new Panel();

        maintPanel = new JPanel(new BorderLayout(1, 1));
        maintPanel.add(new JPanel(), BorderLayout.CENTER);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                maintPanel, myCanvas3D);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(224);

        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .5;
        c.weightx = 1;
        c.gridwidth = 1;
        mainMenuBar.setMinimumSize(new Dimension(800, 20));
        mainMenuBar.setMaximumSize(new Dimension(800, 20));
        mainMenuBar.setPreferredSize(new Dimension(800, 20));
        //aGridBagLayout.setConstraints(mainMenuBar,c);
        //add(mainMenuBar);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = .5;
        c.weightx = 1;
        c.gridwidth = 1;
        mnToolBar.setFloatable(false);
        mnToolBar.setMinimumSize(new Dimension(800, 30));
        mnToolBar.setMaximumSize(new Dimension(800, 30));
        mnToolBar.setPreferredSize(new Dimension(800, 30));
        //aGridBagLayout.setConstraints(mnToolBar,c);
        //add(mnToolBar);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = .5;
        c.weightx = 1;
        c.gridwidth = 1;
        //aFileTextField.setMinimumSize(new Dimension(800, 20));
        //aGridBagLayout.setConstraints(aFileTextField, c);
        //add(aFileTextField);

        aFileJComboBox.setMinimumSize(new Dimension(800, 20));
        aGridBagLayout.setConstraints(aFileJComboBox, c);
        add(aFileJComboBox);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 3;
        c.weightx = 1;
        //c.gridy = 0;
        aGridBagLayout.setConstraints(splitPane, c);
        add(splitPane);
        //add(splitPane,BorderLayout.CENTER );
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.weighty = 1;
        c.weightx = 1;
        aGridBagLayout.setConstraints(aJScrollPane, c);
        add(aJScrollPane);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.weighty = .5;
        c.weightx = 1;
        JPanel aVrtlDvcPnl = mySceneRoot.rtrvVrtlDvcPnl();
        aVrtlDvcPnl.setMinimumSize(new Dimension(800, 75));
        aVrtlDvcPnl.setMaximumSize(new Dimension(800, 75));
        aVrtlDvcPnl.setPreferredSize(new Dimension(800, 75));
        aGridBagLayout.setConstraints(aVrtlDvcPnl, c);
        add(aVrtlDvcPnl);
        //add(aJScrollPane,BorderLayout.SOUTH);
    }
    /**
     * Displays or refreshes the view of the tabs in the LabStation.
     * This function is the same as installLab.
     * @param labName The labName is the name of the panel to be displayed.
     */
    public void displayLabPanel(String labName) {
        installLab(labName);
    }
    /**
     * Displays or refreshes the view of the tabs in the LabStation.
     * The is the same as displayLabPanel.
     * @param labName The labName is the name of the panel to be displayed.
     */
    public void installLab(String labName) {
        JComponent aJComponent1 = (JComponent) labHashMap.get(labName);

        if (maintPanel.getComponentCount() > 1) {
            maintPanel.remove(0);//Try cardlayout
        }

        maintPanel.add(aJComponent1, BorderLayout.CENTER);
        maintPanel.validate();
        maintPanel.repaint();
    }
    /**
     * This function is responsible for returning a reference to the canvas.
     * @return Panel
     */
    public Panel rtrvCanvasPanel() {
        return canvasPanel;
    }
    /**
     * This function is responsible for returning a reference to the JEditoPane,,
     * which is utilized for general data output.
     * @return JEditoPane
     */
    public static JEditorPane getEditorPane() {
        return editorPane;
    }
    public static FileTextField getFileTextField()
    {
        return aFileTextField;
    }
    
}