/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.lnralgbra.Trnsfrm;

/**
 *
 * @author clayton g thomas jr
 */
public class CrdntTrnsfrm
{
   Trnsfrm shpTrnsfrm;
   String crdntTp;

   public CrdntTrnsfrm(ShapePnt myShape)
   {
      shpTrnsfrm = myShape.getTransform();
      crdntTp = myShape.getCrdntTp();
   }
   public CrdntTrnsfrm(Trnsfrm myTrnsfrm,String myCrdntTp)
   {
      shpTrnsfrm = myTrnsfrm;
      crdntTp = myCrdntTp;
   }
   public float rtrvC1(Pnt myPoint)
   {      
      float x2 = 0;
      float x1 = CrdntType.rtrvC1(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float y1 = CrdntType.rtrvC2(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float z1 = CrdntType.rtrvC3(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);

      //System.out.println("CrdntTrnsfrm:rtrvC1() x = "+x1+", crdntTp = "+crdntTp);
      if(shpTrnsfrm!=null)
      {
         x2 = shpTrnsfrm.transformX(x1,y1,z1);
      }else{
         //System.out.println("CrdntTrnsfrm:rtrvC1() shpTrnsfrm = null x = "+x1);
         x2 = x1;
      }
      return x2;
   }
   public float rtrvC2(Pnt myPoint)
   {
      float y2 = 0;
      float x1 = CrdntType.rtrvC1(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float y1 = CrdntType.rtrvC2(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float z1 = CrdntType.rtrvC3(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      
      //System.out.println("CrdntTrnsfrm:rtrvC1() y = "+y1+", crdntTp = "+crdntTp);
      if(shpTrnsfrm!=null)
      {
         y2 = shpTrnsfrm.transformY(x1,y1,z1);
      }else{
         //System.out.println("CrdntTrnsfrm:rtrvC1() shpTrnsfrm = null y = "+y1);
          y2 = y1;
      }
      return y2;
   }
   public float rtrvC3(Pnt myPoint)
   {
      float z2 = 0;
      float x1 = CrdntType.rtrvC1(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float y1 = CrdntType.rtrvC2(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);
      float z1 = CrdntType.rtrvC3(crdntTp,myPoint.getX1(),myPoint.getY1(),myPoint.getZ1(),0,0);

      //System.out.println("CrdntTrnsfrm:rtrvC1() z = "+z1+", crdntTp = "+crdntTp);
      if(shpTrnsfrm!=null)
      {
         z2 = shpTrnsfrm.transformZ(x1,y1,z1);
      }else{
         //System.out.println("CrdntTrnsfrm:rtrvC1() shpTrnsfrm = null z = "+z1);
          z2 = z1;
      }
      return z2;
   }
}