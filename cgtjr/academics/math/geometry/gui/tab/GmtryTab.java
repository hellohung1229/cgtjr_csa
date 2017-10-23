package cgtjr.academics.math.geometry.gui.tab;

import cgtjr.academics.general.LabTab;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import cgtjr.academics.math.geometry.gui.pnl.*;
import java.applet.Applet;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;

/**
 * GmtryTab is used as a container for various panels.  These panels
 * provide application interaction with the user interface and geometric 
 * objects.
 * @author clayton g thomas jr
 */
public class GmtryTab extends LabTab {

    JEditorPane outputJEditorPane;
    SceneRoot scnRt;

    /**
     * Instantiates a GmtryTab (geometry tab), with a scene root (SceneRoot)
     * reference.
     * @param mySceneRoot
     */
    public GmtryTab(SceneRoot mySceneRoot) {
        setLayout(new GridLayout(1, 1));
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        scnRt = mySceneRoot;
        addChangeListener(new TbPnLstnr());
        addCmpnts();
    }

    /**
     * Instantiates a GmtryTab (geometry tab), with a scene root (SceneRoot)
     * reference.
     * @param mySceneRoot represents the scene root object
     * @param myLabWndwPnl Represents the applet container.
     */
    public GmtryTab(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        setLayout(new GridLayout(1, 1));
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        scnRt = mySceneRoot;
        addChangeListener(new TbPnLstnr());
        addCmpnts(myLabWndwPnl);
    }

    /**
     * The constructor instantiates the geometry tab object (GmtryTab) with
     * the scene root object (SceneRoot) and applet panel.
     * @param mySceneRoot represents the scene root object
     * @param myLabWndwPnl Represents the applet container.
     */
    public GmtryTab(SceneRoot mySceneRoot, JEditorPane myJEditorPane) {
        setLayout(new GridLayout(1, 1));
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        scnRt = mySceneRoot;
        outputJEditorPane = myJEditorPane;
        addCmpnts();
    }

    /**
     * The constructor instantiates the geometry tab object (GmtryTab) with
     * the scene root object (SceneRoot) and applet panel.
     * @param mySceneRoot represents the scene root object (SceneRoot).
     * @param myJEditorPane represents the JEditorPane object.
     * @param myLabWndwPnl represents a reference to the applet container.
     */
    public GmtryTab(SceneRoot mySceneRoot, JEditorPane myJEditorPane, Applet myLabWndwPnl) {
        setLayout(new GridLayout(1, 1));
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        scnRt = mySceneRoot;
        outputJEditorPane = myJEditorPane;
        addCmpnts(myLabWndwPnl);
    }

    private void addCmpnts() {
        ImgIcnPnl panell = new ImgIcnPnl(scnRt);
        DmnsnPnl panel2 = new DmnsnPnl(scnRt);
        HelixPitchPnl panel6 = new HelixPitchPnl(scnRt);
        //TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
        RndrngPnl panel4 = new RndrngPnl(scnRt);
        MtrlPnl panel5 = new MtrlPnl(scnRt);
        addPnlToTab(panell, "primitives");
        addPnlToTab(panel2, DmnsnVar.getDataNmKey());
        //addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
        addPnlToTab(panel4, RndrngVar.getDataNmKey());
        addPnlToTab(panel5, MtrlVar.getDataNmKey());
        addPnlToTab(panel6, "pitch");
    }

    private void addCmpnts(Applet myLabWndwPnl) {
        ImgIcnPnl panell = new ImgIcnPnl(scnRt, myLabWndwPnl);
        DmnsnPnl panel2 = new DmnsnPnl(scnRt);
        HelixPitchPnl panel6 = new HelixPitchPnl(scnRt);
        //TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
        RndrngPnl panel4 = new RndrngPnl(scnRt);
        MtrlPnl panel5 = new MtrlPnl(scnRt);
        addPnlToTab(panell, "primitives");
        addPnlToTab(panel2, DmnsnVar.getDataNmKey());
        //addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
        addPnlToTab(panel4, RndrngVar.getDataNmKey());
        addPnlToTab(panel5, MtrlVar.getDataNmKey());
        addPnlToTab(panel6, "pitch");
    }

    private void addPnlToTab(JComponent myJComponent, String myInfo) {
        addTab(myInfo, null, myJComponent, myInfo);
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
}