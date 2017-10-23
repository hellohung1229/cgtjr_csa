/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.ClndrclVar;
import cgtjr.academics.math.geometry.shapeprcss.ShpScnPrcss;
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
public class HxgnVlmnBttn extends JButton {

    private SceneRoot scnRt;
    private Icon anIcon;

    public HxgnVlmnBttn(SceneRoot mySceneRoot) {
        super();
        scnRt = mySceneRoot;
        String aFileInfo = "./data/images/shapes/hxgnVlm.jpg";
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    public HxgnVlmnBttn(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        super();
        scnRt = mySceneRoot;
        Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/hxgnVlm.jpg", myLabWndwPnl);
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    private void constructor() {
        setIcon(anIcon);
        Dimension aDimension = new Dimension(30, 30);
        setMaximumSize(aDimension);
        setMinimumSize(aDimension);
        setPreferredSize(aDimension);
    }

    public SceneRoot getScnRt() {
        return scnRt;
    }

    private void addActnLstnr() {
        ClndrclVar aClndrclVar = new ClndrclVar(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2 * Math.PI, 2, 1.0, 2 * Math.PI / 6, 1.0);
        ShpScnPrcss aShpScnPrcss = new ShpScnPrcss(aClndrclVar, scnRt);
        DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
        aDtPrcssLstnr.setDtPrcss(aShpScnPrcss);
        addActionListener(aDtPrcssLstnr);
    }
}