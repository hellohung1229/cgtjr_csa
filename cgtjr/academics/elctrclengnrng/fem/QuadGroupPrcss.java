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

public class QuadGroupPrcss extends DataPrcss
{

   private SceneRoot scnRt;
   private DmnsnVar dimensionVar1[];
   private DmnsnVar dimensionVar2[];

   public QuadGroupPrcss(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[],SceneRoot mySceneRoot)
   {
      dimensionVar1 = myDmnsnVar1;
      dimensionVar2 = myDmnsnVar2;
      scnRt = mySceneRoot;
   }
   public void prcss()
   {
      MeshShp crdntShp = new MeshShp(dimensionVar1);
      Vector aVector = crdntShp.getQuadElmnts();
      Vector aNodeVctr = crdntShp.getNodes();
      int aGlblSize = aNodeVctr.size();
      NdlElmnts aNdElmnts1 = new NdlElmnts(aGlblSize,4);
      aNdElmnts1.insrtElmntLclNds(aVector);

      ShpBndry aShpBndryGrp = new ShpBndry();
      aShpBndryGrp.cnnctBndry(dimensionVar2);

      NdlElmnts aNdElmnts2 = new NdlElmnts(crdntShp,aShpBndryGrp);
      
      QuadFEMPrcss aQuadFEMPrcss = new QuadFEMPrcss(crdntShp,aNdElmnts2);
      aQuadFEMPrcss.prcss();

      ShpScene shapeScene = new ShpScene(crdntShp);
      scnRt.addShp3D(shapeScene);
   }
}