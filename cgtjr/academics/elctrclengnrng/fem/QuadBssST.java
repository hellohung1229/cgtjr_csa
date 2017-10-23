/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
public class QuadBssST extends QuadBss
{
   public void setBssNds(double myX[],double myY[],double myZ[])
   {
      setBssNds(myY[1],myY[2],myZ[0],myZ[0],myX[0],myX[1]);
   }
}
