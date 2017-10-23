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
import java.awt.Color;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.elmnt.*;

/**
 *
 * @author clayton g thomas jr
 */
public class LineRndr extends ElmntRndr
{
   GeometryArray lnGmtryArry = null;
   private int count;
   private Vector lineVctr;
   private int arraySz;

   public LineRndr(ShapePnt myShape)
   {
      super(myShape);
      lineVctr = (Vector)myShape.getEdges();
      arraySz = 2*lineVctr.size();
   }
   public GeometryArray rndrElmnt()
   {
     rndrElmnt(lineVctr);
     return lnGmtryArry;
   }
   public GeometryArray rndrElmnt(Vector myVector)
   {
      LinePnts aLinePnts = null;
      int aSize = myVector.size();
      //System.out.println("LineRndr: array size = "+arraySz );
      lnGmtryArry = new LineArray(arraySz,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      for(int i=0;i<aSize;i++)
      {
         aLinePnts = (LinePnts)myVector.elementAt(i);
         rndrElmnt(aLinePnts);
      }
      return lnGmtryArry;
   }
   private GeometryArray rndrElmnt(LinePnts myLinePnts)
   {
      //LineArray aLineArray = null;
      float aX = 0;
      float aY = 0;
      float aZ = 0;

      Pnt smPnts[] = myLinePnts.getPoints();
      int aLength = smPnts.length;

      Point3f aPoint3f = new Point3f();
      Color3f aColor3f = null;
      int aColor = 0;

      for(int i=0;i<aLength;i++)
      {
         aX = rtrvC1(smPnts[i]);
         aY = rtrvC2(smPnts[i]);
         aZ = rtrvC3(smPnts[i]);
         if(aX >= Double.NEGATIVE_INFINITY && aY > Double.NEGATIVE_INFINITY && aZ > Double.NEGATIVE_INFINITY)
         {
            aColor = smPnts[i].getColor();
            aColor3f = new Color3f(new Color(aColor));
            aPoint3f = new Point3f(aX,aY,aZ);
         
            //System.out.println("LineRndr: x = "+aX+",aY="+aY+",z="+aZ+",color="+aColor+",count="+count);
            lnGmtryArry.setCoordinate(count, aPoint3f);
            lnGmtryArry.setColor(count, aColor3f);
            count++;
         }
      }
      return lnGmtryArry;
   }
}