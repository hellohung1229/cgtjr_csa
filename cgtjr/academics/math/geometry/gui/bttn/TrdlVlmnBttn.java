/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.ToroidalVar;
import cgtjr.academics.math.geometry.shapeprcss.GmtryGrpUpdtr;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author clayton g thomas jr
 */
public class TrdlVlmnBttn extends JButton {

    private SceneRoot scnRt;
    private Icon anIcon;

    public TrdlVlmnBttn(SceneRoot mySceneRoot) {
        super();
        scnRt = mySceneRoot;
        String aFileInfo = "./data/images/shapes/toroid3d.gif";
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    public TrdlVlmnBttn(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        super();
        scnRt = mySceneRoot;
        Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/toroid3d.gif", myLabWndwPnl);
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    private void constructor() {
        Dimension aDimension = new Dimension(30, 30);
        setIcon(anIcon);

        setMaximumSize(aDimension);
        setMinimumSize(aDimension);
        setPreferredSize(aDimension);
    }

    public SceneRoot getScnRt() {
        return scnRt;
    }

    private void addActnLstnr() {
        /*
        ToroidalVar aToroidalVar = new ToroidalVar();
        ShpScnPrcss aShpScnPrcss = new ShpScnPrcss(aToroidalVar, scnRt);
        DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
        aDtPrcssLstnr.setDtPrcss(aShpScnPrcss);
        addActionListener(aDtPrcssLstnr);
        */
        
        GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new ToroidalVar(), scnRt);
        DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
        aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
        addActionListener(aDtPrcssLstnr);        
    }
}