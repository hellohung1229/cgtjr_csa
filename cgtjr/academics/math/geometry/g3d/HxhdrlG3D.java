/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import javax.media.j3d.GeometryArray;

/**
 *
 * @author clayton g thomas jr
 */

public class HxhdrlG3D extends ShapeG3D
{ 
   private HxhdrlRndr hxhdrlRender;
   private GeometryArray shpGmtryArry;

   public HxhdrlG3D(ShapePnt myShape)
   {
     super(myShape);
     hxhdrlRender = new HxhdrlRndr(myShape);
     shpGmtryArry = hxhdrlRender.rndrElmnt();
     setGeometry(shpGmtryArry);

     /*
	Appearance look = new Appearance();
	Color3f objColor = new Color3f(0.0f, 0.2f, 0.8f);
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	look.setMaterial(new Material(objColor, black,
				      objColor, white, 100.0f));
     //etAppearance(new ApprncG3D());
   setAppearance(look);
      PolygonAttributes polyAppear = new PolygonAttributes();
      polyAppear.setCullFace(PolygonAttributes.CULL_NONE);
      look.setPolygonAttributes(polyAppear);

      		//Color3f objColor = new Color3f(0.2f, 0.2f, 1.0f);
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(objColor);
		look.setColoringAttributes(ca);
      */
/*
     hxhdrlRender.rndrElmnt();
     QuadArray aQuadArray = hxhdrlRender.getQuadArray();
     aShape3D = new Shape3D(aQuadArray,look);
      */
   }
}