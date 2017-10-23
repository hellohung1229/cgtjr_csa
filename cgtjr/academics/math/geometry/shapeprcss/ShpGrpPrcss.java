/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;

/**
 *
 * @author clayton g thomas jr
 */

public class ShpGrpPrcss extends DataPrcss
{

   SceneRoot scnRt;

   public ShpGrpPrcss(SceneRoot mySceneRoot)
   {
      scnRt = mySceneRoot;
   }
   public void prcss()
   {
      CrtssnShpPrcss aCrtssnShpPrcss = new CrtssnShpPrcss();
      aCrtssnShpPrcss.prcss();
      ShapePnt aCrdntShp = aCrtssnShpPrcss.getCrdntShp();
      //Crtssn3DAllPrcss aCrtssn3DAllPrcss = new Crtssn3DAllPrcss(scnRt,aCrdntShp);
      //aCrtssn3DAllPrcss.prcss();
   }
}