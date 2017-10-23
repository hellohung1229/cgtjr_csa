/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.shapebndry.ClndrCrtssnShp;
import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.shapebndry.CrvtrXY;
import cgtjr.academics.general.*;
import cgtjr.academics.math.algebra.zequations.ZSinXY;
import cgtjr.academics.math.geometry.elmnt.NodePnts;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnlDflt;
import java.util.Vector;


/**
 *
 * @author clayton g thomas jr
 */

public class ZShpScnPrcss extends DataPrcss
{

   SceneRoot scnRt;
   DmnsnVar dmnsnVar;
   
   public ZShpScnPrcss(SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      dmnsnVar = Crtssn3DVar.getDmnsnVar();
   }
   public ZShpScnPrcss(DmnsnVar myDmnsnVar,SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
      dmnsnVar = myDmnsnVar;
   } 
   public void prcss()
   {
      //ShpPrcss aShpPrcss = new ShpPrcss(dmnsnVar);
      //aShpPrcss.prcss();
      MtrlVar aMtrlVar = (MtrlVar)MtrlPnlDflt.getDfltVar();
      aMtrlVar.prntKeyValMap();
      //RadiusBndry aRadiusBndry = new RadiusBndry(10,dmnsnVar);
      ClndrCrtssnShp aClndrCrtssnShp = new ClndrCrtssnShp(6,2*Math.PI,0);
      aClndrCrtssnShp.setDeltaX(0.25f);
      aClndrCrtssnShp.setDeltaY(0.25f);      
      CrdntShp crdntShp = new CrdntShp(aClndrCrtssnShp);

      //crdntShp.setBoundaryShape(aRadiusBndry);
      Vector aVector = crdntShp.getNodes();
      ZSinXY aZSinXY = new ZSinXY();
      aZSinXY.updtZVls(aVector);
      Vector bndryNds = NodePnts.rtrvBndry2DPnts(aVector);
      
      CrvtrXY aCrvtrXY = new CrvtrXY();
      aCrvtrXY.insrt(bndryNds);
      aCrvtrXY.cmptCrvtr();
      ShpScene shapeScene = new ShpScene(crdntShp);
      scnRt.addShp3D(shapeScene);
      //Shape aCrdntShp = aShpPrcss.getCrdntShp();
   }
}