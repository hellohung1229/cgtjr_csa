/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class LineIntgrlDrvtvBss extends LineRdlBss
{
   public double cmptCffcntMtrx(int m,int n)
   {
      double width = cmptWidth();
      double ce = 0;
      if(m == n)
      {
         ce = 1/width;
      }else{
         ce = -1/width;
      }
      //System.out.println("LineIntgrlDrvtvBss: width = "+width+", ce = "+ce);
      return ce;
   }
}