/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;

/**
 *
 * @author clayton g thomas jr
 */

public class GlblNmbrTxt2DG3D1 extends Text2DG3D
{ 

   public GlblNmbrTxt2DG3D1(ShapePnt myShape)
   {
      super(myShape);
      setYOffSet(.02f);
      rndrElmnt();
   }
   public String rtrvString(Pnt myPoint)
   {
      String info = ""+myPoint.getCounter();
      //System.out.println("GlblNmbrTxt: counter = "+ myPoint.getCounter());
      return info;
   }
}