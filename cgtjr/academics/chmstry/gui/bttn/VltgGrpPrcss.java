/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.physical.*;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;

/**
 *
 * @author clayton g thomas jr
 */

public class VltgGrpPrcss extends DataPrcss
{
   SceneRoot scnRt;
   DmnsnVar shpDmnsnVar;
   MeshShp crdntShp;
   public VltgGrpPrcss(DmnsnVar myDmnsnVar,SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      shpDmnsnVar = myDmnsnVar;

   }
   public VltgGrpPrcss(MeshShp myMeshShp,SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      crdntShp = myMeshShp;
   }
   public void prcss()
   {
      /*
      PDBMolecule aPDBMolecule = PDBMoleculeFile.getPDBMolecule();
      Enumeration anEnumeration1 = aPDBMolecule.retrieveEnumeration();
      PDBMolecule aPDBMolecule2 = (PDBMolecule)anEnumeration1.nextElement();
      Enumeration anEnumeration2 = aPDBMolecule2.retrieveEnumeration();

      double aDiameter = 4*aPDBMolecule2.getRadius();
      
      double xDiff = aPDBMolecule2.cmptWidth()+aDiameter;
      double yDiff = aPDBMolecule2.cmptHeight()+aDiameter;
      double zDiff = aPDBMolecule2.cmptLength()+aDiameter;
      
      //HelixVar aHelixVar = new HelixVar(xDiff,yDiff,zDiff,1.0,1.0,1.0,0,0,0);
      DmnsnVar aDmnsnVar = aPDBMolecule2.rtrvDmnsnVar(shpDmnsnVar.getCrdntTpVal());
      double aCenter1[] = aPDBMolecule2.cmptCenter();
      double aCenter2[] = aDmnsnVar.cmptCenter();
      
      float xDistance = (float)(aCenter1[0]-aCenter2[0]);
      float yDistance = (float)(aCenter1[1]-aCenter2[1]);
      float zDistance = (float)(aCenter1[2]-aCenter2[2]);
      
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(xDistance, yDistance, zDistance);
      //System.out.println("VltgGrpPrcss: xdistance = "+xDistance+",yDistance="+yDistance+",zDistance="+zDistance);
      Shape crdntShp = new CrdntShp(aDmnsnVar);

      crdntShp.setTransform(aTrnsfrm);
      */

       //CrtssnShpPrcss aCrtssnShpPrcss = new CrtssnShpPrcss();
      //aCrtssnShpPrcss.prcss();
      //Shape aCrdntShp = aCrtssnShpPrcss.getCrdntShp();
      MtrlVar aMtrlVar = (MtrlVar)MtrlPnlDflt.getDfltVar();
      //HashMap aLinkedHashMap = aDmnsnVar.getKeyVarMap();
      //DmnsnVar aDmnsnVarTmp = (DmnsnVar)aLinkedHashMap.get(dimensionVar1[0].getCrdntTpVal());      
      //DmnsnVar aDmnsnVarTmp = DmnsnFctry.getDfltVar();

      MeshShp meshShp = MoleculeModel.rtrvShape(shpDmnsnVar,aMtrlVar);
      //MeshShp meshShp = new MeshShp(aDmnsnVarTmp,aMtrlVar);

      VltgDiffPrcss aVltgDiffPrcss = new VltgDiffPrcss(meshShp);
      aVltgDiffPrcss.prcss();
      
      
      //Crtssn3DAllPrcss aCrtssn3DAllPrcss = new Crtssn3DAllPrcss(scnRt,crdntShp);
      //aCrtssn3DAllPrcss.prcss();
      //ShpScene shapeScene = new ShpScene(crdntShp);
      //scnRt.addShp3D(shapeScene);


      ShpScene meshShapeScene = new ShpScene(meshShp);
      scnRt.addShp3D(meshShapeScene);
      //System.out.println("VltgGrpPrcss: xMax = "+xMax+"xMin = "+xMin+", yMax="+yMax+",yMin="+yMin+",zMax="+zMax+",zMin="+zMin+",xCenter="+xCenter+",yCenter="+yCenter+",zCenter="+zCenter);
   }
}