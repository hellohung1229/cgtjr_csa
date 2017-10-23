/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng;

import cgtjr.academics.chmstry.general.*;
/**
 *
 * @author clayton g thomas jr
 */

public class EMMtrl extends ChemMtrl
{
   private double prmtvty;
   private double prmblty;
   private double cndctvty;

   public EMMtrl()
   {
      prmtvty = 0;
      prmblty = 0;
      cndctvty = 0;
   }
   public EMMtrl(double myPrmtvty,double myPrmblty,double myCndctvty)
   {
      prmtvty = myPrmtvty;
      prmblty = myPrmblty;
      cndctvty = myCndctvty;
   }
   public void setPrmtvty(double anPrmtvty)
   {
      prmtvty = anPrmtvty;
   }
   public void setCndctvty(double anCndctvty)
   {
      cndctvty = anCndctvty;
   }
   public double getPrmtvty()
   {
      return prmtvty;
   }
   public double getCndctvty()
   {
      return cndctvty;
   }
   public double getPrmblty()
   {
      return prmblty;
   }
}