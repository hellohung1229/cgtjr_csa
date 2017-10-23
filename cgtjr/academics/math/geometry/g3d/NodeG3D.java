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

public class NodeG3D extends ShapeG3D
{
 
   private NodeRndr nodeRender;

   public NodeG3D(ShapePnt myShape)
   {
     super(myShape);
     nodeRender = new NodeRndr(myShape);
     GeometryArray aGeometryArray = nodeRender.rndrElmnt();
     setGeometry(aGeometryArray);
   }
}