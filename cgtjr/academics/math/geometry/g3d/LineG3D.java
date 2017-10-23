/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.*;

import javax.media.j3d.*;

/**
 *
 * @author clayton g thomas jr
 */

public class LineG3D extends ShapeG3D
{ 
   private LineRndr lineRender;

   public LineG3D(ShapePnt myShape)
   {
     super(myShape);
     lineRender = new LineRndr(myShape);
     GeometryArray aGeometryArray = lineRender.rndrElmnt();
     setGeometry(aGeometryArray);
   }
}