/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import java.util.*;

/**
 *
 * @author clayton g thomas jr
 */
public class TstPrcss extends DataPrcss
{
   HashMap varHshMp;
   public TstPrcss()
   {
      varHshMp = new HashMap();
   }
   public void setVarHshMp(HashMap myVarHshMp)
   {
      varHshMp = myVarHshMp;
   }
   public HashMap getVarHshMp()
   {
      return varHshMp;
   }
   public void prcss()
   {
      System.out.println("TstPnlPrcss: this is a test");
   }
}