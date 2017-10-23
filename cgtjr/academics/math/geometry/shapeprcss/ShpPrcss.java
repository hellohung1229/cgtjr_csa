/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;

/**
 *
 * @author clayton g thomas jr
 */

public class ShpPrcss extends DataPrcss
{
   MeshShp crdntShp;
   DmnsnVar dmnsnVar;
   public ShpPrcss(DmnsnVar myDmnsnVar)
   {
       dmnsnVar = myDmnsnVar;
   }
   public void prcss()
   {
      crdntShp = new MeshShp(dmnsnVar);
   }
   public ShapePnt getCrdntShp()
   {
      return crdntShp;
   }
}