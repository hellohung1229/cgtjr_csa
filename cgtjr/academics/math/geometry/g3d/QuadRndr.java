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
public class QuadRndr extends ElmntRndr
{
   //private Trnsfrm transform;
   private QuadArray shpQuadArray = null;
   private int count = 0;


   public QuadRndr(ShapePnt myShape)
   {
      super(myShape);
   }
   public GeometryArray rndrElmnt()
   {
     ShapePnt aShape = getCrdntShp();
     Vector aVector = (Vector)aShape.getQuadElmnts();
     //System.out.println("QuadRndr: crdntTp = "+aShape.getCrdntTp());
     //nmbrPnts = 4*aVector.size();
     //aQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
     return rndrElmnt(aVector);
   }
   public GeometryArray rndrElmnt(Vector myVector)
   {
      //GeometryArray quadElmnts = null;
      QuadPnts aRctnglPnts = null;
      int aSize = myVector.size();
      //System.out.println("QuadRndr: aSize = "+aSize);
      shpQuadArray = new QuadArray(aSize*4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      //shpQuadArray = new QuadArray(aSize*4,GeometryArray.COORDINATES);
      for(int i=0;i<aSize;i++)
      {
         aRctnglPnts = (QuadPnts)myVector.elementAt(i);
         rndrElmnt(aRctnglPnts);
      }
      //elmntShp3D.addGeometry(shpQuadArray);
      return shpQuadArray;
   }
   private void rndrElmnt(QuadPnts myRctnglPnts)
   {
      //QuadArray aQuadArray = null;
      float aX = 0;
      float aY = 0;
      float aZ = 0;

      Pnt smPnts[] = myRctnglPnts.getPoints();
      int aLength = smPnts.length;

      Point3f aPoint3f = new Point3f();
      Pnt aPoint = null;
      Color3f aColor3f = null;
      int aColor = 0;
      //aQuadArray = new QuadArray(4,GeometryArray.COORDINATES);
      for(int i=0;i<4;i++)
      {
         aX = rtrvC1(smPnts[i]);
         aY = rtrvC2(smPnts[i]);
         aZ = rtrvC3(smPnts[i]);
         
         if(aX > -Double.MAX_VALUE && aY > -Double.MAX_VALUE && aZ > -Double.MAX_VALUE)
         {           
            aPoint3f = new Point3f(aX,aY,aZ);
         //aPoint3f = new Point3f(smPnts[i].getX1(),smPnts[i].getY1(),smPnts[i].getZ1());

            aColor = smPnts[i].getColor();
            Color aColor2 = new Color(aColor);
            float aClr[] = aColor2.getComponents(null);
            //aColor3f = new Color3f(aClr[0],aClr[1],aClr[2]);
            aColor3f = new Color3f(aColor2);         
            shpQuadArray.setColor(count, aColor3f);
            shpQuadArray.setCoordinate(count, aPoint3f);
         //System.out.println("QuadRndr: original points count = "+count+", color = "+aColor2+ ", aX = "+smPnts[i].getX1()+",aY = "+smPnts[i].getY1()+",aZ = "+smPnts[i].getZ1());
         //System.out.println("QuadRndr: transform points count ="+count+", aX = "+aX+",aY="+aY+",aZ="+aZ);
            count++;
         }
      }
      //return shpQuadArray;
   }
   public QuadArray getQuadArray()
   {
      return shpQuadArray;
   }
}