/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.general.*;

/**
 *
 * @author clayton g thomas jr
 */

public class CrtssnShpPrcss extends DataPrcss
{
   MeshShp crdntShp;

   public void prcss()
   {
      DmnsnVar aDmnsnVar = Crtssn3DVar.getDmnsnVar();
      crdntShp = new MeshShp(aDmnsnVar);
   }
   public ShapePnt getCrdntShp()
   {
      return crdntShp;
   }
}