/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.dmnsvar.CrdntTrnsfrm;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;
import javax.media.j3d.*;

import cgtjr.academics.math.geometry.*;
/**
 *
 * @author clayton g thomas jr
 */
//TODO : consider changing to interface
public abstract class ElmntRndr
{
   private ShapePnt elmntShp;
   private CrdntTrnsfrm shpCrdntTrnsfrm;
   private int gmtryArryPrpts;
   
   protected ElmntRndr(ShapePnt myShape)
   {
      elmntShp = myShape;
      shpCrdntTrnsfrm = new CrdntTrnsfrm(myShape);
      if(myShape.getMtrlVar() != null && myShape.getMtrlVar().getOverRideClrVal() == true)
      {
          gmtryArryPrpts = GeometryArray.COORDINATES;
      }else{
          gmtryArryPrpts = GeometryArray.COORDINATES|GeometryArray.COLOR_3;
      }
   }
   public void setGmtryArryPrpts(int myGmtryArryPrpts)
   {
       gmtryArryPrpts = myGmtryArryPrpts;
   }
   public int getGmtryArryPrpts()
   {
       return gmtryArryPrpts;
   }   
   public ShapePnt getCrdntShp()
   {
       return elmntShp;
   }
   public void setShpCrdntTrnsfrm(CrdntTrnsfrm myCrdntTrnsfrm)
   {
      shpCrdntTrnsfrm = myCrdntTrnsfrm;
   }
   public CrdntTrnsfrm getShpCrdntTrnsfrm()
   {
      return shpCrdntTrnsfrm;
   }
   protected float rtrvC1(Pnt myPoint)
   {
       return shpCrdntTrnsfrm.rtrvC1(myPoint);
   }
   protected float rtrvC2(Pnt myPoint)
   {
       return shpCrdntTrnsfrm.rtrvC2(myPoint);
   }
   protected float rtrvC3(Pnt myPoint)
   {
       return shpCrdntTrnsfrm.rtrvC3(myPoint);
   }
   public abstract GeometryArray rndrElmnt();
   public abstract GeometryArray rndrElmnt(Vector myVector);
}