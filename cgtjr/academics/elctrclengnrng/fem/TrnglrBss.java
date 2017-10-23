/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
public class TrnglrBss extends BssFnctn
{
   private double p[];
   private double q[];

   public TrnglrBss()
   {

      p = new double[3];
      q = new double[3];
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
   public double cmptArea()
   {
      double area = 0.5*Math.abs(p[1]*q[2] - q[1]*p[2]);
      return area;
   }
   public double cmptCffcntMtrx(int m,int n)
   {
      double area = cmptArea();
      double ce = (p[m]*p[n] + q[m]*q[n])/(4.0 * area);
      //System.out.println("TrnglrBss: area = "+area+", ce = "+ce);
      return ce;
   }
}