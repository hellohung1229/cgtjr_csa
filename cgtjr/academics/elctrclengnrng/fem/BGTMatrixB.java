/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
public class BGTMatrixB extends CmplxMatrixBCrtr
{
   public BGTMatrixB(NdlElmnts myNdlElmnts,BssFnctn myBssFnctn)
   {
      super(myNdlElmnts,myBssFnctn);
   }
   public double[] cmptFnctn(int m)
   {
      //double term1[] = CmplxNmbr.rtvCmplxNmbr(0,0);
      //double term = aLineRdlBss.cmptBssFnctn(m);
      //TODO: make a radial line bases with radius attribute or Modify Hlmhltz with radius attribute
      LineRdlBss aLineRdlBss = (LineRdlBss)getBssFnctn();
      double phi = aLineRdlBss.getNodeValue(m);
      double rho = aLineRdlBss.getRadius();

      double term2[] = HlmhltzBT.cmptIncdntDrvtv(rho,phi);
      return term2;
   }
}
