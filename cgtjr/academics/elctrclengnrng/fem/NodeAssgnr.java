/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.shapebndry.BndryActn;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author clayton g thomas jr
 */
public class NodeAssgnr implements BndryActn
{
   private double nodeValue;

   public NodeAssgnr()
   {
      nodeValue = 0;
   }
   public NodeAssgnr(double myNdValue)
   {
      nodeValue = myNdValue;
   }
   public void prfrmActn(boolean isInABndry, Pnt myPoint)
   {
      if(isInABndry == true)
      {
         myPoint.setValue(nodeValue);
      }
   }

}
