/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.*;
import cgtjr.academics.math.algebra.zequations.ZSinXY;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnDfltFctry;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */

public class ZGmtryGrpUpdtr extends DataPrcss
{
   private SceneRoot scnRt;
   private DmnsnVar dimensionVar1[];
   private DmnsnVar dimensionVar2[];

   public ZGmtryGrpUpdtr(SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
   }
   public ZGmtryGrpUpdtr(DmnsnVar myDmnsnVar1,SceneRoot mySceneRoot)
   {
      dimensionVar1 = new DmnsnVar[1];
      dimensionVar1[0] = myDmnsnVar1;
      scnRt = mySceneRoot;
   }
   public ZGmtryGrpUpdtr(DmnsnVar myDmnsnVar1[], DmnsnVar myDmnsnVar2[],SceneRoot mySceneRoot)
   {
      dimensionVar1 = myDmnsnVar1;
      dimensionVar2 = myDmnsnVar2;
      scnRt = mySceneRoot;
   }
   public void prcss()
   {
      //DmnsnVar aDmnsnVar = DmnsnFctry.getDfltVar();
      MtrlVar aMtrlVar = (MtrlVar)MtrlPnlDflt.getDfltVar();
      //HashMap aLinkedHashMap = aDmnsnVar.getKeyVarMap();
      //DmnsnVar aDmnsnVarTmp = (DmnsnVar)aLinkedHashMap.get(dimensionVar1[0].getCrdntTpVal());
      DmnsnVar aDmnsnVarTmp = (DmnsnVar)DmnsnDfltFctry.getDfltVar(dimensionVar1[0]);

      
      System.out.println("GmtryGrpUptr: type = "+ aDmnsnVarTmp.getCrdntTpVal());
      MeshShp meshShp = new MeshShp(aDmnsnVarTmp,aMtrlVar);

      ZSinXY aZSinXY = new ZSinXY();
      Vector aVctor = meshShp.getNodes();
      aZSinXY.updtZVls(aVctor);
      ShpScene shapeScene = new ShpScene(meshShp);
      scnRt.addShp3D(shapeScene);
   }
}