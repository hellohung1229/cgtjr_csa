/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.complex;

import cgtjr.academics.math.quadrature.GssLgndr;

/**
 *
 * @author clayton g thomas jr
 */
public class CmplxGssLgndr extends GssLgndr
{
   private CmplxIntgrtn aCmplxIntgrtn;
   private double smplPnts5[];
   private double smplWghts5[];
   
   public CmplxGssLgndr(CmplxIntgrtn myReIntegration)
   {
      super(null);
      aCmplxIntgrtn = myReIntegration;
      smplPnts5 = getSmplPnts5();
      smplWghts5 = getSmplWghts5();            
   }
   public double[] intgrtCmplx1D()
   {
      double smplPntX = 0;
      double smplWghtX = 0;

      //double rslt1 = 0;
      double rslt1[] = null;
      double rslt2[] = CmplxNmbr.rtvCmplxNmbr(0,0);
      for(int i=0;i<getNmbrPnts();i++)
      {
         smplPntX = getIntlX()+(getXMax()-getXMin())/2-((getXMax()-getXMin())/2) * smplPnts5[i];
         smplWghtX = (getXMax()-getXMin())/2 * smplWghts5[i];
         //rslt1 += smplWghtX*cmptImFnctn(smplPntX);
         rslt1 = cmptCmplxFnctn(smplPntX,0,0);
         rslt1 = CmplxNmbr.scale(rslt1, smplWghtX);
         rslt2 = CmplxNmbr.add(rslt2,rslt1);
      }
      //System.out.println("rslt = "+rslt);
      return rslt2;
   }
   protected double cmptFnctn(double smplPntX)
   {
      return cmptReFnctn(smplPntX);
   }
   protected double cmptImFnctn(double smplPntX)
   {
      return aCmplxIntgrtn.cmptImIntgrl(smplPntX,0,0);
   }
   private double cmptReFnctn(double smplPntX)
   {
      return aCmplxIntgrtn.cmptReIntgrl(smplPntX,0,0);      
   }
   private double[] cmptCmplxFnctn(double myX,double myY,double myZ)
   {
      return null;
   }
   /*
   public void setIntlX(double myX)
   {
      super.setIntlY(myX);
      aImGssLgndr.setIntlY(myX);
   }
   public void setIntlY(double myY)
   {
      super.setIntlY(myY);
      aImGssLgndr.setIntlY(myY);
   }
   public void setIntlZ(double myZ)
   {
      super.setIntlZ(myZ);
      aImGssLgndr.setIntlZ(myZ);
   }
   public void setXMax(double myXMax)
   {
      super.setXMax(myXMax);
      aImGssLgndr.setXMax(myXMax);
   }
   public void setXMin(double myXMin)
   {
      super.setXMin(myXMin);
      aImGssLgndr.setXMin(myXMin);
   }
   public void setYMax(double myYMax)
   {
      super.setYMax(myYMax);
      aImGssLgndr.setYMax(myYMax);
   }
   public void setYMin(double myYMin)
   {
      super.setYMin(myYMin);
      aImGssLgndr.setYMin(myYMin);
   }
   public void setZMax(double myZMax)
   {
      super.setZMax(myZMax);
      aImGssLgndr.setZMin(myZMax);
   }
   public void setZMin(double myZMin)
   {
      super.setZMin(myZMin);
      aImGssLgndr.setZMin(myZMin);
   }*/
}