/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.ParabolicVar;
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
public class ParabolicBttn extends JButton {

    private SceneRoot scnRt;
    private Icon anIcon;

    public ParabolicBttn(SceneRoot mySceneRoot) {
        super();
        scnRt = mySceneRoot;
        String aFileInfo = "./data/images/shapes/parabolic.gif";
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    public ParabolicBttn(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        super();
        scnRt = mySceneRoot;
        Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/parabolic.gif", myLabWndwPnl);
        anIcon = new ImageIcon(aFileInfo);
        constructor();
        addActnLstnr();
    }

    public void constructor() {

        setIcon(anIcon);
        Dimension aDimension = new Dimension(30, 30);
        setMaximumSize(aDimension);
        setMinimumSize(aDimension);
        setPreferredSize(aDimension);
    }

    public SceneRoot getScnRt() {
        return scnRt;
    }

    public void addActnLstnr() {
        GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new ParabolicVar(), scnRt);
        DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
        aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
        addActionListener(aDtPrcssLstnr);
    }
}