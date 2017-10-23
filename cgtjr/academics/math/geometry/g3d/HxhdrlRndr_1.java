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
public class HxhdrlRndr_1 extends ElmntRndr
{
   //private Trnsfrm transform;
   private QuadArray shpQuadArray = null;
   private int count = 0;
   private ArrayList gmtryArryLst;

   public HxhdrlRndr_1(ShapePnt myShape)
   {
      super(myShape);
      gmtryArryLst = new ArrayList();
   }
   public ArrayList rtrvElmnt()
   {
     ShapePnt aShape = getCrdntShp();
     Vector aVector = (Vector)aShape.getHxhdrlElmnts();
     //System.out.println("QuadRndr: crdntTp = "+aShape.getCrdntTp());
     //nmbrPnts = 4*aVector.size();
     //aQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
     return rtrvElmnt(aVector);
   }   
   public ArrayList rtrvElmnt(Vector myVector)
   {
      GeometryArray quadElmnts = null;
      HxhdrlPnts aRctnglPnts = null;
      int aSize = myVector.size();
      //shpQuadArray = new QuadArray(aSize*4,GeometryArray.COORDINATES|GeometryArray.COLOR_3|GeometryArray.NORMALS);
      //shpQuadArray = new QuadArray(aSize*4,GeometryArray.COORDINATES);

      for(int i=0;i<aSize;i++)
      {
         aRctnglPnts = (HxhdrlPnts)myVector.elementAt(i);
         rtrvQuadArray(aRctnglPnts);

      //elmntShp3D.addGeometry(shpQuadArray);
      }
      return gmtryArryLst;
   }

   private void rtrvQuadArray(HxhdrlPnts myHxhdrlPnts)
   {
      QuadPnts aQuadPnts0 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint2(),myHxhdrlPnts.getPoint3());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts0));
      QuadPnts aQuadPnts1 = new QuadPnts(myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint2());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts1));
      QuadPnts aQuadPnts2 = new QuadPnts(myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint7(),myHxhdrlPnts.getPoint6());
      //QuadPnts aQuadPnts2 = new QuadPnts(myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint7());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts2));
      QuadPnts aQuadPnts3 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint3(),myHxhdrlPnts.getPoint7(),myHxhdrlPnts.getPoint4());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts3));
      //QuadPnts aQuadPnts4 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint4());
      QuadPnts aQuadPnts4 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint1());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts4));
      QuadPnts aQuadPnts5 = new QuadPnts(myHxhdrlPnts.getPoint3(),myHxhdrlPnts.getPoint2(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint7());
      gmtryArryLst.add(rtrvQuadArray(aQuadPnts5));
   }
   private QuadArray rtrvQuadArray(QuadPnts myRctnglPnts)
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
      shpQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      for(int i=0;i<4;i++)
      {
         aX = rtrvC1(smPnts[i]);
         aY = rtrvC2(smPnts[i]);
         aZ = rtrvC3(smPnts[i]);
         aPoint3f = new Point3f(aX,aY,aZ);
         //aPoint3f = new Point3f(smPnts[i].getX1(),smPnts[i].getY1(),smPnts[i].getZ1());

         aColor = smPnts[i].getColor();
         Color aColor2 = new Color(aColor);
         float aClr[] = aColor2.getComponents(null);
         aColor3f = new Color3f(aClr[0],aClr[1],aClr[2]);
         
         shpQuadArray.setColor(i, aColor3f);
         shpQuadArray.setCoordinate(i, aPoint3f);

         //System.out.println("QuadRndr: original points count = "+count+", aX = "+smPnts[i].getX1()+",aY="+smPnts[i].getY1()+",aZ="+smPnts[i].getZ1());
         //System.out.println("QuadRndr: transform points count ="+count+", aX = "+aX+",aY="+aY+",aZ="+aZ);
         count++;
      }
      return shpQuadArray;
   }
   public QuadArray getQuadArray()
   {
      return shpQuadArray;
   }
   public GeometryArray rndrElmnt(){return null;}
   public GeometryArray rndrElmnt(Vector myVector){return null;}
}