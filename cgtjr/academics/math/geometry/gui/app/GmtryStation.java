/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.app;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.g3d.*;
import javax.swing.JApplet;

/**
 *
 * @author clayton g thomas jr
 */
public class GmtryStation extends LabStation {

    private static ShapeG3D shpG3D;

    /**
     * 
     * @param mySceneRoot
     */
    public GmtryStation(SceneRoot mySceneRoot) {
        super(mySceneRoot);
    }

    /**
     * 
     * @param mySceneRoot
     * @param myLabWndwPnl
     */
    public GmtryStation(SceneRoot mySceneRoot, JApplet myLabWndwPnl) {
        super(mySceneRoot, myLabWndwPnl);
    }

    /**
     * 
     * @return
     */
    public static ShapeG3D getShpG3D() {
        return shpG3D;
    }

    /**
     * 
     * @param myShpG3D
     */
    public static void setShpG3D(ShapeG3D myShpG3D) {
        shpG3D = myShpG3D;
    }
}