package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.complex.CmplxMatrix;
import cgtjr.academics.math.complex.CmplxNmbr;

public class MtrxSlvr
{
   double rho = 30;
   double lambda = 1000;
   double waveNmbr = 2*Math.PI/lambda;


   public double[][] solve(double myMatrix1[][],double myMatrix2[][],double myMatrix3[][],double myMatrix4[][],double myMatrixB[][])
   {
      double cn1[] = CmplxNmbr.rtvCmplxNmbr(1, 0);
      double alpha[] = AlphaBeta.cmptAlpha(rho,waveNmbr);
      double beta[] = AlphaBeta.cmptBeta(rho,waveNmbr);

      double m1plus2[][] = CmplxMatrix.add(myMatrix1, myMatrix2);
      double alphaMtrx[][][] = CmplxMatrix.scale(alpha, myMatrix3);
      double betaMtrx[][][] = CmplxMatrix.scale(beta, myMatrix4);
      double aplusb[][][] = CmplxMatrix.add(alphaMtrx, betaMtrx);
      double cm1plus2[][][] = CmplxMatrix.scale(cn1, m1plus2);
      double mtrxSum[][][] = CmplxMatrix.add(aplusb, cm1plus2);

      double mtrxInv[][][] = CmplxMatrix.inverse(mtrxSum);
      //CmplxMatrix.print(mtrxSum);
      double x[][] = CmplxMatrix.mltplyMMxM(mtrxInv, myMatrixB);
      //Matrix.print(x);
      return x;
   }
}