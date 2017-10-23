/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.PDBMoleculeFile;
import cgtjr.academics.chmstry.physical.VltgChrgePrcss;
import cgtjr.academics.general.DataPrcss;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.general.ShpScene;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.HelixPitchVar;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnDfltFctry;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;

/**
 *
 * @author clayton g thomas jr
 */
public class ChrgeMnlGrpPrcss extends DataPrcss {

    SceneRoot scnRt;
    DmnsnVar shpDmnsnVar;
    //CrdntShp crdntShp;
    ShapePnt crdntShp;

    public ChrgeMnlGrpPrcss(DmnsnVar myDmnsnVar, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        shpDmnsnVar = myDmnsnVar;
        //crdntShp = PDBMolecule.rtrvShape(shpDmnsnVar);
    }

    public ChrgeMnlGrpPrcss(ShapePnt myShape, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        crdntShp = myShape;
    }

    public void prcss() {
        System.out.println("ChrgGrpPrcss: test 1 ...");
        DmnsnVar aDmnsnVarTmp = (DmnsnVar) DmnsnDfltFctry.getDfltVar(shpDmnsnVar);

        //DmnsnVar aDmnsnVarTmp = (DmnsnVar) DmnsnDfltFctry.getDfltVar();

        System.out.println("ChrgGrpPrcss type : " + aDmnsnVarTmp.getCrdntTpVal());

        MoleculeModel aPDBMolecule = PDBMoleculeFile.getPDBMolecule();
        if (aPDBMolecule == null) {
            return;
        }

        //Enumeration anEnumeration1 = aPDBMolecule.rtrvEnumeration();
        //MoleculeModel aPDBMolecule2 = (MoleculeModel) anEnumeration1.nextElement();
        System.out.println("ChrgGrpPrcss: test 2 ...");
        //Enumeration anEnumeration2 = aPDBMolecule2.rtrvEnumeration();
        MtrlVar aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();

        //if (aDmnsnVarTmp != null && aDmnsnVarTmp.getCrdntTpVal().equals(CrdntType.getHlxCrdntTp())) {
        CrdntType.setPitch((float) HelixPitchVar.getPitchVal());
        crdntShp = new MeshShp(aDmnsnVarTmp, aMtrlVar);
        //} 
        //System.out.println("ChrgGrpPrcss: type = " + shpDmnsnVar.getCrdntTpVal());
        //HxhdrlRfndMsh aHxhdrlRfndMsh = new HxhdrlRfndMsh(aPDBMolecule2, crdntShp);
        //aHxhdrlRfndMsh.setCrdntTp(shpDmnsnVar.getCrdntTpVal());

        VltgChrgePrcss aVltgChrgePrcss = new VltgChrgePrcss(crdntShp);
        aVltgChrgePrcss.prcss();
        //Crtssn3DAllPrcss aCrtssn3DAllPrcss = new Crtssn3DAllPrcss(scnRt,crdntShp);
        //aCrtssn3DAllPrcss.prcss();

        ShpScene shapeScene = new ShpScene(crdntShp);
        scnRt.addShp3D(shapeScene);
        //System.out.println("VltgGrpPrcss: xMax = "+xMax+"xMin = "+xMin+", yMax="+yMax+",yMin="+yMin+",zMax="+zMax+",zMin="+zMin+",xCenter="+xCenter+",yCenter="+yCenter+",zCenter="+zCenter);
    }
}