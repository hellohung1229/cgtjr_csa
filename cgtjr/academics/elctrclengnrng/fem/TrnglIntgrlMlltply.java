/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class TrnglIntgrlMlltply extends TrnglrBss
{

   public double cmptCffcntMtrx(int m,int n)
   {
      double area = cmptArea();
      double ce = 0;
      if(m == n)
      {
         ce = area/6;
      }else{
         ce = area/12;
      }
      System.out.println("TrnglIntgrlMltply: area = "+area+", ce = "+ce);
      return ce;
   }
}