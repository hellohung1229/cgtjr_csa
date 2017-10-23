/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;

/**
 *
 * @author clayton g thomas jr
 */
public class DmnsnVarTl
{
   public static DmnsnVar getDmnsnVar(ShapePnt myShape)
   {
      DmnsnVar dmnsn = new DmnsnVar();
      dmnsn.setInitXVal(myShape.getEnvrnmntX());
      dmnsn.setInitYVal(myShape.getEnvrnmntY());
      dmnsn.setInitZVal(myShape.getEnvrnmntZ());
      dmnsn.setMaxDmnsn1Val(myShape.getX1());
      dmnsn.setMaxDmnsn2Val(myShape.getY1());
      dmnsn.setMaxDmnsn3Val(myShape.getZ1());
      dmnsn.setDelta1Val(myShape.getDeltaX());
      dmnsn.setDelta2Val(myShape.getDeltaX());
      dmnsn.setDelta3Val(myShape.getDeltaY());
      return dmnsn;
   }
}
