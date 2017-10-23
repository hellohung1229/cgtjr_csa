/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class TrnglRdlBss extends BssFnctn
{
   private double p[];
   private double q[];

   public TrnglRdlBss()
   {
      p = new double[3];
      q = new double[3];
   }

   public void setBssNds(double myR[],double myPhi[],double myZ[])
   {
      double x0 = myR[0]*Math.cos(myPhi[0]);
      double x1 = myR[1]*Math.cos(myPhi[1]);
      double x2 = myR[2]*Math.cos(myPhi[2]);

      double y0 = myR[0]*Math.sin(myPhi[0]);
      double y1 = myR[1]*Math.sin(myPhi[1]);
      double y2 = myR[2]*Math.sin(myPhi[2]);

      p[0] = y1 - y2;
      p[1] = y2 - y0;
      p[2] = y0 - y1;
      q[0] = x2 - x1;
      q[1] = x0 - x2;
      q[2] = x1 - x0;
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
      //System.out.println("TrnglRdlBss: area = "+area+", ce = "+ce);
      return ce;
   }
}