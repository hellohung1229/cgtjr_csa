/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activemesh;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;


public class ActiveNode extends Pnt
{
   private int prcssNmbr;

   public void setPrcssNmbr(int myPrcssNmbr)
   {
      prcssNmbr = myPrcssNmbr;
   }
   public int getPrcssNmbr()
   {
      return prcssNmbr;
   }
   public void incPrcssNmbr()
   {
      setPrcssNmbr(getPrcssNmbr()+1);
   }
   public Pnt createPoint()
   {
      return new ActiveNode();
   }
   public Pnt createDataPoint()
   {
      return new ActiveNode();
   }
}
