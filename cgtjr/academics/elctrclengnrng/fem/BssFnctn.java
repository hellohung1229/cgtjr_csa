/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
public abstract class BssFnctn
{   
   //public abstract double cmptBssDrvtv(int p,int q,double x,double y,double z);
   public abstract void setBssNds(double x[],double y[],double z[]);
   public abstract double cmptCffcntMtrx(int i,int j);
   //public abstract void setBssNds1(double x[],double y[],double z[]);


}
