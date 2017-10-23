/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class TrnglIntgrlMtlplyBss extends TrnglRdlBss
{

   public double cmptCffcntMtrx(int j,int k)
   {
      //System.out.println("QuadBss.cmptCffcntMtrx1() j = "+j+",k="+k);
      double aValue = 0;
      double area = cmptArea();
      if(j==k)
      {
          aValue = (1.0/6.0)*area;
      }else{
          aValue = (1.0/12.0)*area;
      }
      //System.out.println("TrnglIntgrlMtlplyBss: area = "+area+", ce["+j+"]["+k+"] = "+aValue);
      return aValue;
   }
}