/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.elmnt.*;
import cgtjr.academics.math.lnralgbra.Trnsfrm;
/**
 *
 * @author clayton g thomas jr
 */

public class CrtssnShpG3D extends ShapeG3D
{
   private int nmbrPnts;
   
   private ShapePnt crtssnShp;


   //public CrtssnShpG3D(){}
   //TODO ... remove this class
   public CrtssnShpG3D(ShapePnt myShape)
   {
     super(myShape);
     crtssnShp = myShape;
     rndrQuadElmnt(myShape);
   }
   public void rndrQuadElmnt(ShapePnt myShape)
   {
     Vector aVector = (Vector)myShape.getQuadElmnts();
     //nmbrPnts = 4*aVector.size();
     //aQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
     rndrQuadElmnts(aVector);
     /*
      Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
      Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
      Color3f highlightColor = new Color3f(0.0f, 1.0f, 0.0f);
     Appearance highlightAppearance = new Appearance();
     highlightAppearance.setMaterial(new Material(highlightColor, black,
						   highlightColor, white,
						   80.0f));
     setAppearance(highlightAppearance);
     */
   }
   public void rndrQuadElmnts(Vector myVector)
   {
      QuadArray quadElmnts = null;
      QuadPnts aRctnglPnts = null;
      int aSize = myVector.size();

      for(int i=0;i<aSize;i++)
      {
         aRctnglPnts = (QuadPnts)myVector.elementAt(i);
         quadElmnts = rndrQuadElmnts(aRctnglPnts);
         addGeometry(quadElmnts);
      }
   }
   public QuadArray rndrQuadElmnts(QuadPnts myRctnglPnts)
   {
      QuadArray aQuadArray = null;
      float aX = 0;
      float aY = 0;
      float aZ = 0;

      Pnt smPnts[] = myRctnglPnts.getPoints();
      int aLength = smPnts.length;

      Point3f aPoint3f = new Point3f();
      Color3f aColor3f = null;
      int aColor = 0;
      aQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      for(int i=0;i<aLength;i++)
      {
         aX = smPnts[i].getX1();
         aY = smPnts[i].getY1();
         aZ = smPnts[i].getZ1();
         aColor = smPnts[i].getColor();
         aPoint3f = new Point3f(aX,aY,aZ);
         aQuadArray.setCoordinate(i, aPoint3f);
         //aColor3f = new Color3f((Color.red));
         //aQuadArray.setColor(i, aColor3f);
         //System.out.println("crtssnshpg3d: x = "+aX+",aY="+aY+",z="+aZ);
      } 
     //addGeometry(aQuadArray);
      return aQuadArray;
   }
   public ShapePnt getShape()
   {
      return crtssnShp;
   }
}