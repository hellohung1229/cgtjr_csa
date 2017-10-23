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
import cgtjr.academics.math.geometry.crdntepnts.HxhdrlRfndMsh;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;
import java.util.Enumeration;

/**
 *
 * @author clayton g thomas jr
 */
public class ChrgAutoGrpPrcss extends DataPrcss {

    SceneRoot scnRt;
    DmnsnVar shpDmnsnVar;
    //CrdntShp crdntShp;
    ShapePnt crdntShp;

    public ChrgAutoGrpPrcss(DmnsnVar myDmnsnVar, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        shpDmnsnVar = myDmnsnVar;
        //crdntShp = PDBMolecule.rtrvShape(shpDmnsnVar);
    }

    public ChrgAutoGrpPrcss(ShapePnt myShape, SceneRoot mySceneRoot) {
        scnRt = mySceneRoot;
        crdntShp = myShape;
    }

    public void prcss() {
        System.out.println("VltgGrpRfnPrcss: test 1 ...");
        MoleculeModel aPDBMolecule = PDBMoleculeFile.getPDBMolecule();
        Enumeration anEnumeration1 = aPDBMolecule.rtrvEnumeration();
        MoleculeModel aPDBMolecule2 = (MoleculeModel) anEnumeration1.nextElement();
        System.out.println("VltgGrpRfnPrcss: test 2 ...");
        //Enumeration anEnumeration2 = aPDBMolecule2.rtrvEnumeration();

        System.out.println("VltgGrpRfnPrcss: type = " + shpDmnsnVar.getCrdntTpVal());
            crdntShp = MoleculeModel.rtrvShape(shpDmnsnVar);
        MtrlVar aMtrlVar = (MtrlVar) MtrlPnlDflt.getDfltVar();
            crdntShp.setMtrlVar(aMtrlVar);

        System.out.println("VltgGrpRfnPrcss: type = " + shpDmnsnVar.getCrdntTpVal());
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