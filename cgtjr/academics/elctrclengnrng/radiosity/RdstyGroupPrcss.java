/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity;

import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnDfltFctry;

/**
 *
 * @author clayton g thomas jr
 */

public class RdstyGroupPrcss extends DataPrcss
{

   private SceneRoot scnRt;
   private DmnsnVar dimensionVar1[];
   private DmnsnVar dimensionVar2[];
   private SparseMtrxVar sprsMtrxVar;
   
   public RdstyGroupPrcss(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[],SceneRoot mySceneRoot)
   {
      dimensionVar1 = myDmnsnVar1;
      dimensionVar2 = myDmnsnVar2;
      scnRt = mySceneRoot;
   }
   public void rndrType()
   {

   }
   public void prcss()
   {
      DmnsnVar aDmnsnVarTmp = (DmnsnVar) DmnsnDfltFctry.getDfltVar(dimensionVar1[0]);
      MeshShp crdntShp = new MeshShp(aDmnsnVarTmp);

      ShpBndry aShpBndryGrp = new ShpBndry();
      aShpBndryGrp.cnnctBndry(dimensionVar2);

      ////NdlElmnts aNdElmnts = new NdlElmnts(crdntShp,aShpBndryGrp);
      //TODO : define this in outer function, then pass as a parameter.
      RayCastPrcss aRayCastPrcss = new RayCastPrcss(crdntShp,aShpBndryGrp);
      aRayCastPrcss.prcss();

      ShpScene shapeScene = new ShpScene(crdntShp);
      scnRt.addShp3D(shapeScene);
      System.out.println("RdstyGroupPrcss ... ");
   }
}