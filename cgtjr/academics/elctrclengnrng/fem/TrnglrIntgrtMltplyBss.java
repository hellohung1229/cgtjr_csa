/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
public class TrnglrIntgrtMltplyBss extends BssFnctn
{
   double p[];
   double q[];

   public TrnglrIntgrtMltplyBss()
   {
      p = new double[2];
      q = new double[2];
   }
   public void setBssNds(double myX[],double myY[],double myZ[])
   {
     // setBssNds(myX[1],myY[2],myZ[0],myZ[0],myX[0],myX[1]);
      p[0] = myY[1] - myY[2];
      p[1] = myY[2] - myY[0];
      p[2] = myY[0] - myY[1];
      q[0] = myX[2] - myX[1];
      q[1] = myX[0] - myX[2];
      q[2] = myX[1] - myX[0];
   }
   public double cmptCffcntMtrx(int m,int n)
   {
      double area = 0.5*Math.abs(p[1]*q[2] - q[1]*p[2]);
      double ce = (p[m]*p[n] + q[m]*q[n])/(4.0 * area);
      return ce;
   }
}