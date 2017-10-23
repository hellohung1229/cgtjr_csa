/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.elmnt.HxhdrlPnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.awt.Color;
import java.util.Vector;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.QuadArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

/**
 *
 * @author clayton g thomas jr
 */
public class HxhdrlRndr extends ElmntRndr
{
   //private Trnsfrm transform;
   private QuadArray shpQuadArray = null;
   private int count = 0;
   
   public HxhdrlRndr(ShapePnt myShape)
   {
      super(myShape);
   }
   public GeometryArray rndrElmnt()
   {
     ShapePnt aShape = getCrdntShp();
     Vector aVector = (Vector)aShape.getHxhdrlElmnts();
     return rndrElmnt(aVector);
   }   
   public GeometryArray rndrElmnt(Vector myVector)
   {
      HxhdrlPnts aHxhdrlPnts = null;
      int aSize = myVector.size();
      //MtrlVar aMtrlVar = getCrdntShp().getMtrlVar();
      //boolean overRideClr = aMtrlVar.getOverRideClrVal();

      int gmtryArryPrpts = getGmtryArryPrpts();
      shpQuadArray = new QuadArray(aSize*24,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
      //shpQuadArray = new QuadArray(aSize*24,gmtryArryPrpts);

      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnts = (HxhdrlPnts)myVector.elementAt(i);
         rtrvQuadArray(aHxhdrlPnts);

      //elmntShp3D.addGeometry(shpQuadArray);
      }
      return shpQuadArray;
   }

   private void rtrvQuadArray(HxhdrlPnts myHxhdrlPnts)
   {
      QuadPnts aQuadPnts0 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint2(),myHxhdrlPnts.getPoint3());
      rtrvQuadArray(aQuadPnts0);
      QuadPnts aQuadPnts1 = new QuadPnts(myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint2());
      rtrvQuadArray(aQuadPnts1);
      QuadPnts aQuadPnts2 = new QuadPnts(myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint7(),myHxhdrlPnts.getPoint6());
      //QuadPnts aQuadPnts2 = new QuadPnts(myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint7());
      rtrvQuadArray(aQuadPnts2);
      QuadPnts aQuadPnts3 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint3(),myHxhdrlPnts.getPoint7(),myHxhdrlPnts.getPoint4());
      rtrvQuadArray(aQuadPnts3);
      //QuadPnts aQuadPnts4 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint1(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint4());
      QuadPnts aQuadPnts4 = new QuadPnts(myHxhdrlPnts.getPoint0(),myHxhdrlPnts.getPoint4(),myHxhdrlPnts.getPoint5(),myHxhdrlPnts.getPoint1());
      rtrvQuadArray(aQuadPnts4);
      QuadPnts aQuadPnts5 = new QuadPnts(myHxhdrlPnts.getPoint3(),myHxhdrlPnts.getPoint2(),myHxhdrlPnts.getPoint6(),myHxhdrlPnts.getPoint7());
      rtrvQuadArray(aQuadPnts5);
   }
   private void rtrvQuadArray(QuadPnts myRctnglPnts)
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
      //shpQuadArray = new QuadArray(4,GeometryArray.COORDINATES|GeometryArray.COLOR_3);
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
            aColor3f = new Color3f(aClr[0],aClr[1],aClr[2]);
            //TODO: modify code
            if(getCrdntShp().getMtrlVar() != null && getCrdntShp().getMtrlVar().getOverRideClrVal()==false)
            {
               shpQuadArray.setColor(count, aColor3f);                
            }

            shpQuadArray.setCoordinate(count, aPoint3f);

         //System.out.println("QuadRndr: original points count = "+count+", aX = "+smPnts[i].getX1()+",aY="+smPnts[i].getY1()+",aZ="+smPnts[i].getZ1());
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
   //public GeometryArray rndrElmnt(){return null;}
   //public GeometryArray rndrElmnt(Vector myVector){return null;}
}