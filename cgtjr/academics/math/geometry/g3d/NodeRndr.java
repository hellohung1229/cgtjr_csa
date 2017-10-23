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
import java.awt.Color;

/**
 *
 * @author clayton g thomas jr
 */
public class NodeRndr extends ElmntRndr
{
   PointArray aPointArray = null;
   private int count;
   private Vector pntVctr;

   public NodeRndr(ShapePnt myShape)
   {
      super(myShape);
      pntVctr = (Vector)myShape.getNodes();
   }
   public GeometryArray rndrElmnt()
   {
     return rndrElmnt(pntVctr);
   }
   public GeometryArray rndrElmnt(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();
      aPointArray = new PointArray(aSize,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         rndrElmnt(aPoint);
      }
      return aPointArray;
   }
   private void rndrElmnt(Pnt myPoint)
   {
      float aX = 0;
      float aY = 0;
      float aZ = 0;

      Point3f aPoint3f = new Point3f();
      Color3f aColor3f = null;
      int aColor = 0;

      aX = rtrvC1(myPoint);
      aY = rtrvC2(myPoint);
      aZ = rtrvC3(myPoint);
      if(aX > -Double.MAX_VALUE && aY > -Double.MAX_VALUE && aZ > -Double.MAX_VALUE)
      {         
         aPoint3f = new Point3f(aX,aY,aZ);
         aPointArray.setCoordinate(count, aPoint3f);

         aColor = myPoint.getColor();
         aColor3f = new Color3f(new Color(aColor));
         aPointArray.setColor(count, aColor3f);
         count++;
      }
      //System.out.println("NodeRndr: x = "+aX+",aY="+aY+",z="+aZ);
      //return aPointArray;
   }
}