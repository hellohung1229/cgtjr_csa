/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.tab;

import javax.swing.event.*;
import javax.swing.*;
/**
 *
 * @author clayton g thomas jr
 */
public class TbPnLstnr implements ChangeListener
{
   JTabbedPane tabpane;

   public void stateChanged(ChangeEvent myChangeEvent)
   {
      tabpane = (JTabbedPane)myChangeEvent.getSource();
      System.out.println("TbPnLstnr : index = "+tabpane.getSelectedIndex());
   }
}
