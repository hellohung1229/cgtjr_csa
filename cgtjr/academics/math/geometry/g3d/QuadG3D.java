/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import javax.media.j3d.*;

/**
 *
 * @author clayton g thomas jr
 */

public class QuadG3D extends ShapeG3D
{ 
   private QuadRndr quadRender;
   private GeometryArray shpGmtryArry;

   public QuadG3D(ShapePnt myShape)
   {
     super(myShape);
     quadRender = new QuadRndr(myShape);
     shpGmtryArry = quadRender.rndrElmnt();
     setGeometry(shpGmtryArry);     
   }
}