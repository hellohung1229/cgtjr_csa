/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.general.*;

/**
 *
 * @author clayton g thomas jr
 */

public class Crtssn3DAllPrcss extends DataPrcss
{
   SceneRoot scnRt;
   MeshShp crdntShp;

   public Crtssn3DAllPrcss(SceneRoot mySceneRoot,MeshShp myShape)
   {
      scnRt = mySceneRoot;
      crdntShp = myShape;
   }
   public void prcss()
   {
      ShpScene shapeScene = new ShpScene(crdntShp);
      scnRt.addShp3D(shapeScene);
   }
   public ShapePnt getCrdntShp()
   {
      return crdntShp;
   }
}