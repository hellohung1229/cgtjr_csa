/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */

public class TrnglrGroupPrcss extends DataPrcss
{

   private SceneRoot scnRt;
   private DmnsnVar dimensionVar1[];
   private DmnsnVar dimensionVar2[];
   
   public TrnglrGroupPrcss(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[],SceneRoot mySceneRoot)
   {
      dimensionVar1 = myDmnsnVar1;
      dimensionVar2 = myDmnsnVar2;
      scnRt = mySceneRoot;
   }
   public void prcss()
   {
      //Note: which mesh function is required?! 
      MeshShp crdntShp = new MeshShp(dimensionVar1);

      /*
      CrdntData.prntNodeGlblNmbr(crdntShp.getNodes());
      CrdntData.prntNodeGlblIndex(crdntShp.getNodes());
      CrdntData.prntLineGlblIndex(crdntShp.getLineElmnts());
      CrdntData.prntQuadGlblIndex(crdntShp.getQuadElmnts());

      ClndrclRfnr aClndrclRfnr = new ClndrclRfnr();
      aClndrclRfnr.refine(crdntShp);
      */
      ShpBndry aShpBndryGrp = new ShpBndry();
      aShpBndryGrp.cnnctBndry(dimensionVar2);

      ////NdlElmnts aNdElmnts = new NdlElmnts(crdntShp,aShpBndryGrp);
      
      TrnglrFEMPrcss aTrnglrFEMPrcss = new TrnglrFEMPrcss(crdntShp,aShpBndryGrp);
      aTrnglrFEMPrcss.prcss();
      
      CrdntShpScene aCrdntShpScene = new CrdntShpScene(crdntShp);
      scnRt.addShp3D(aCrdntShpScene);      
   }
}