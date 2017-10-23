/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;

/**
 *
 * @author clayton g thomas jr
 */
public class DnmcShp extends CrdntShp
{
   public void crtCrdntMsh(ShpBndry myShpBndry,PntInsrtActn myPntInsrtActn)
   {
      setBoundaryShape(myShpBndry);
      float aX = (float)myShpBndry.getInitX();
      float aY = (float)myShpBndry.getInitY();
      crtInitCoordinates(aX,aY,0,myPntInsrtActn);
      crtMeshByBndry(myPntInsrtActn);
   }
}
