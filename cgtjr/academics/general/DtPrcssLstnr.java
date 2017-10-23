/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */

import java.awt.event.*;

public class DtPrcssLstnr implements ActionListener
{
   DataPrcss dtPrcss;
   public void setDtPrcss(DataPrcss myDataPrcss)
   {
      dtPrcss = myDataPrcss;
   }
   public void actionPerformed(ActionEvent myActionEvent)
   {
      if(dtPrcss != null)
      {
         dtPrcss.prcss();
      }
   }
}