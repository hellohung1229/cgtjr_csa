/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class LineIntgrlMltlplyBss extends LineRdlBss
{
   public void setBssNds(double myU1[],double myV1[],double myW1[])
   {
      setBssNds(myV1[0],myV1[1]);
      setRadius(myU1[0]);
   }
   public double cmptCffcntMtrx(int m,int n)
   {
      double width = cmptWidth();
      double ce = 0;
      if(m == n)
      {
         ce = width/3;
      }else{
         ce = width/6;
      }
      //System.out.println("LineIntgrlMltlplyBss: width = "+width+", ce = "+ce);
      return ce;
   }
}