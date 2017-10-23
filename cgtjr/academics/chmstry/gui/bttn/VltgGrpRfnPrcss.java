/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.PDBMoleculeFile;
import cgtjr.academics.chmstry.physical.VltgDiffPrcss;
import cgtjr.academics.general.DataPrcss;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.general.ShpScene;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.HxhdrlRfndMsh;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;
import java.util.Enumeration;

/**
 *
 * @author clayton g thomas jr
 */

public class VltgGrpRfnPrcss extends DataPrcss
{
   SceneRoot scnRt;
   DmnsnVar shpDmnsnVar;
   ShapePnt crdntShp;
   
   public VltgGrpRfnPrcss(DmnsnVar myDmnsnVar,SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      shpDmnsnVar = myDmnsnVar;
      //crdntShp = PDBMolecule.rtrvShape(shpDmnsnVar);
   }
   public VltgGrpRfnPrcss(ShapePnt myShape,SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      crdntShp = myShape;
   }
   public void prcss()
   {    
      System.out.println("VltgGrpRfnPrcss: test 1 ...");
      MoleculeModel aPDBMolecule = PDBMoleculeFile.getPDBMolecule();
      Enumeration anEnumeration1 = aPDBMolecule.rtrvEnumeration();
      MoleculeModel aPDBMolecule2 = (MoleculeModel)anEnumeration1.nextElement();
       System.out.println("VltgGrpRfnPrcss: test 2 ...");
      //Enumeration anEnumeration2 = aPDBMolecule2.rtrvEnumeration();
  /*
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
      System.out.println("VltgGrpRfnPrcss: type = "+shpDmnsnVar.getCrdntTpVal());
      crdntShp = MoleculeModel.rtrvShape(shpDmnsnVar);
      MtrlVar aMtrlVar = (MtrlVar)MtrlPnlDflt.getDfltVar();
      crdntShp.setMtrlVar(aMtrlVar);
      
       //CrtssnShpPrcss aCrtssnShpPrcss = new CrtssnShpPrcss();
      //aCrtssnShpPrcss.prcss();
      //Shape aCrdntShp = aCrtssnShpPrcss.getCrdntShp();
      System.out.println("VltgGrpRfnPrcss: type = "+shpDmnsnVar.getCrdntTpVal());
      HxhdrlRfndMsh aHxhdrlRfndMsh = new HxhdrlRfndMsh(aPDBMolecule2,crdntShp);
      aHxhdrlRfndMsh.setCrdntTp(shpDmnsnVar.getCrdntTpVal());
      VltgDiffPrcss aVltgDiffPrcss = new VltgDiffPrcss(aHxhdrlRfndMsh);
      aVltgDiffPrcss.prcss();
      
      //Crtssn3DAllPrcss aCrtssn3DAllPrcss = new Crtssn3DAllPrcss(scnRt,crdntShp);
      //aCrtssn3DAllPrcss.prcss();

      ShpScene shapeScene = new ShpScene(aHxhdrlRfndMsh);
      scnRt.addShp3D(shapeScene);
      //System.out.println("VltgGrpPrcss: xMax = "+xMax+"xMin = "+xMin+", yMax="+yMax+",yMin="+yMin+",zMax="+zMax+",zMin="+zMin+",xCenter="+xCenter+",yCenter="+yCenter+",zCenter="+zCenter);
   }
}