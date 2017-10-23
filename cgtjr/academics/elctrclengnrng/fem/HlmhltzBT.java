/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.complex.CmplxIntgrtn;
import cgtjr.academics.math.complex.CmplxNmbr;

/**
 *
 * @author clayton g thomas jr
 */
public class HlmhltzBT implements CmplxIntgrtn
{
   //private CmplxGssLgndr guassLgndr;
   private static double lambda = 1000.0;
   private static double waveNmbr = 2*Math.PI/lambda;;
   private static double theta;

   /////public HlmhltzBT()
   {
      lambda = 1000.0;
      //guassLgndr = new CmplxGssLgndr(this);
      waveNmbr= 2*Math.PI/lambda;
      theta = 0;
   }
   public static double[] cmptIncdntDrvtv(double myRho,double myPhi)
   {
      //System.out.println("HlmhltzBR: rho = "+myRho+", phi="+myPhi+", k="+waveNmbr+", lambda="+lambda);
      double one[] = CmplxNmbr.getOne();
      double iOne[] = CmplxNmbr.getIOne();
      double incdntRl = Math.cos(waveNmbr*myRho*Math.cos(theta-myPhi));
      double incdntImgnry = -1*Math.sin(waveNmbr*myRho*Math.cos(theta-myPhi));
      double incdntWv[] = {incdntRl,incdntImgnry};

      double term1[] = AlphaBeta.cmptAlpha(myRho,waveNmbr);
      double term2[] = AlphaBeta.cmptBeta(myRho,waveNmbr);
      //System.out.println("HlmhltzBT: alpha = "+term1[0]+", j"+term1[1]);
      //System.out.println("HlmhltzBT: beta = "+term2[0]+", j"+term2[1]);
      double term3[] = CmplxNmbr.scale(term2,myRho);
      double term4[] = CmplxNmbr.add(term3,one);
      double term5[] = CmplxNmbr.scale(iOne,waveNmbr*Math.cos(theta-myPhi));
      double term6[] = CmplxNmbr.mltply(term4, term5);
      double term7 = -1*waveNmbr*waveNmbr*myRho*myRho*Math.sin(theta-myPhi)*Math.sin(theta-myPhi);
      double term8[] = CmplxNmbr.scale(term2,term7);

      double term9[] = CmplxNmbr.mltply(term1, incdntWv);
      double term10[] = CmplxNmbr.mltply(term6, incdntWv);
      double term11[] = CmplxNmbr.mltply(term8, incdntWv);

      double term12[] = CmplxNmbr.add(term9,term10);
      double term13[] = CmplxNmbr.add(term12, term11);
      double term14[] = CmplxNmbr.scale(term13, -1);
      //System.exit(0);
      return term14;

   }
   public double cmptReIntgrl(double myX,double myY,double myZ)
   {
      return 0;
   }
   public double cmptImIntgrl(double myX, double myY,double myZ)
   {
      return 0;
   }
}