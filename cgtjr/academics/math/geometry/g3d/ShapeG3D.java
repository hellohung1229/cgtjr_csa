/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.g3d;


import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import javax.media.j3d.*;

import cgtjr.academics.math.geometry.*;
/**
 *
 * @author clayton g thomas jr
 */

public abstract class ShapeG3D extends Shape3D
{
   private ShapePnt crdntShp;
   
   public ShapeG3D(ShapePnt myShape)
   {
      crdntShp = myShape;
      setCapability(Shape3D.ALLOW_APPEARANCE_READ);
      setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
   }
   public ShapePnt getShape()
   {
       return crdntShp;
   }
}
