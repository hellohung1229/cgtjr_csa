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

/**
 *
 * @author clayton g thomas jr
 */

public class GroupPrcss extends DataPrcss
{

   private SceneRoot scnRt;
   private DmnsnVar dimensionVar1[];
   private DmnsnVar dimensionVar2[];
   private SparseMtrxVar sprsMtrxVar;
   
   public GroupPrcss(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[],SceneRoot mySceneRoot)
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
      MeshShp crdntShp = new MeshShp(dimensionVar1);

      ShpBndry aShpBndryGrp = new ShpBndry();
      aShpBndryGrp.cnnctBndry(dimensionVar2);

      ////NdlElmnts aNdElmnts = new NdlElmnts(crdntShp,aShpBndryGrp);
      //TODO : define this in outer function, then pass as a parameter.
      FEMPrcss aFEMPrcss = new FEMPrcss(crdntShp,aShpBndryGrp);
      aFEMPrcss.prcss();

      ShpScene shapeScene = new ShpScene(crdntShp);
      scnRt.addShp3D(shapeScene);
   }
}