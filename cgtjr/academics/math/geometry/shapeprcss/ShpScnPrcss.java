/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.DataPrcss;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.general.ShpScene;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;

/**
 *
 * @author clayton g thomas jr
 */
public class ShpScnPrcss extends DataPrcss {

    SceneRoot scnRt;
    DmnsnVar dmnsnVar;
    MtrlVar aMtrlVar;
    
    /**
     * 
     * @param mySceneRoot
     */
    public ShpScnPrcss(SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        dmnsnVar = Crtssn3DVar.getDmnsnVar();
    }

    /**
     * 
     * @param myDmnsnVar
     * @param mySceneRoot
     */
    public ShpScnPrcss(DmnsnVar myDmnsnVar, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        dmnsnVar = myDmnsnVar;
        aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
    }
    public ShpScnPrcss(DmnsnVar myDmnsnVar, MtrlVar myMtrlVar, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        dmnsnVar = myDmnsnVar;
        aMtrlVar = myMtrlVar;
    }
    /**
     * 
     */
    public void prcss() {
        //ShpPrcss aShpPrcss = new ShpPrcss(dmnsnVar);
        //aShpPrcss.prcss();
        //MtrlVar aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
        aMtrlVar.prntKeyValMap();
        MeshShp crdntShp = new MeshShp(dmnsnVar, aMtrlVar);

        ShpScene shapeScene = new ShpScene(crdntShp);
        scnRt.addShp3D(shapeScene);
    }
}