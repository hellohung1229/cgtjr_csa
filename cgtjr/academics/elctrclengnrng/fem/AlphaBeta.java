/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.complex.CmplxNmbr;

/**
 *
 * @author clayton g thomas jr
 */
public class AlphaBeta
{
   private static double waveNmbr = 2*Math.PI;

   public static double[] cmptAlpha(double rho)
   {
      return cmptAlpha(rho,waveNmbr);
   }
   public static double[] cmptAlpha(double rho,double myWvNmbr)
   {
      double x1 = 3/(2*rho);
      double y1 = -1*myWvNmbr+3/(8*myWvNmbr*rho*rho);
      double x2 = 1;
      double y2 = 1/(-1*myWvNmbr*rho);
      return CmplxNmbr.divide(x1, y1, x2, y2);
   }
   public static double[] cmptBeta(double rho)
   {
      return cmptBeta(rho,waveNmbr);
   }
   public static double[] cmptBeta(double rho,double myWvNmbr)
   {
      double x1 = 0;
      double y1 = -1/(2*myWvNmbr*rho*rho);
      //System.out.println("AlphaBeta: y1 = "+y1);
      double x2 = 1;
      double y2 = -1/(myWvNmbr*rho);
      return CmplxNmbr.divide(x1, y1, x2, y2);
   }

}
