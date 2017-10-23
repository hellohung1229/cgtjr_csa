/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */


public class LineRdlBss extends LineBss
{
   private double radius;
   public LineRdlBss()
   {
      radius = 1;
   }
   public LineRdlBss(double myRadius)
   {
      radius = myRadius;
   }
   public void setBssNds(double myU1[],double myV1[],double myW1[])
   {
      setRadius(myU1[0]);
      setBssNds(myV1[0],myV1[1]);
   }
   public void setRadius(double myRadius)
   {
      radius = myRadius;
   }
   public double cmptWidth()
   {
      //System.out.println("LineRdlBss : radius = "+radius+", width = "+super.cmptWidth());
      double aWidth = super.cmptWidth()*radius;
      return aWidth;
   }
   public double getRadius()
   {
      return radius;
   }
}