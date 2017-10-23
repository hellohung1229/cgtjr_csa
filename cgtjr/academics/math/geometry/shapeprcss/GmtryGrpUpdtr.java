package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.dmnsvar.HelixPitchVar;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.general.DataPrcss;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.general.ShpScene;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnDfltFctry;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;

/**
 * The GmtryGrpUpdtr (geometry group updater) is responsible for applying  
 * the material to the mesh, then adding the mesh structure to the scene root 
 * object.
 * @author clayton g thomas jr
 */
public class GmtryGrpUpdtr extends DataPrcss {

    private SceneRoot scnRt;
    private DmnsnVar dimensionVar1[];
    private DmnsnVar dimensionVar2[];
    private MtrlVar aMtrlVar;
    private boolean overRideClr;
    /**
     * Instantiates a geometry object with a reference to the scene root (SceneRoot)
     * object.
     * @param mySceneRoot represents the scene root object.
     */
    public GmtryGrpUpdtr(SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
    }

    /**
     * Instantiates a geometry object with a reference to a dimension variable and
     * scene root (SceneRoot) object.
     * @param myDmnsnVar1 represents dimension variable object.
     * @param mySceneRoot represents a scene root object.
     */
    public GmtryGrpUpdtr(DmnsnVar myDmnsnVar1, SceneRoot mySceneRoot) {
        dimensionVar1 = new DmnsnVar[1];
        dimensionVar1[0] = myDmnsnVar1;
        aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
        scnRt = mySceneRoot;
    }

    /**
     * Instantiates a geometry object with a reference to a dimension variables and
     * scene root (SceneRoot) object.
     * @param myDmnsnVar1 represents dimension variable object.
     * @param myDmnsnVar1 represents dimension variable object.
     * @param mySceneRoot represents a scene root object.
     */
    public GmtryGrpUpdtr(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[], SceneRoot mySceneRoot) {
        dimensionVar1 = myDmnsnVar1;
        dimensionVar2 = myDmnsnVar2;
        aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
        scnRt = mySceneRoot;      
    }
    public GmtryGrpUpdtr(DmnsnVar myDmnsnVar1[],MtrlVar myMtrlVar, SceneRoot mySceneRoot) {
        dimensionVar1 = myDmnsnVar1;
        aMtrlVar = myMtrlVar;
        scnRt = mySceneRoot;      
    }  
    public void setOverRideClr(boolean myClrPerPxlVal) {
        overRideClr = myClrPerPxlVal;
    }    
    /**
     *  The method should be called by the data process listener (DtPrcssLstnr).  
     */
    public void prcss() {
   
        aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
        aMtrlVar.prntKeyValMap();
        aMtrlVar.setOverRideClrVal(overRideClr);
        DmnsnVar aDmnsnVarTmp = (DmnsnVar) DmnsnDfltFctry.getDfltVar(dimensionVar1[0]);

        if (aDmnsnVarTmp.getCrdntTpVal().equals(CrdntType.getHlxCrdntTp())) {
            CrdntType.setPitch((float) HelixPitchVar.getPitchVal());
        }
        MeshShp meshShp = new MeshShp(aDmnsnVarTmp, aMtrlVar);
        ShpScene shapeScene = new ShpScene(meshShp);
        scnRt.addShp3D(shapeScene);
    }
}