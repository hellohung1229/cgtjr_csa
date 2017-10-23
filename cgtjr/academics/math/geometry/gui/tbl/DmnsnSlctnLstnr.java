/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.tbl;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import javax.swing.event.*;
import javax.swing.*;

import cgtjr.academics.math.geometry.*;
/**
 *
 * @author clayton g thomas jr
 */

public class DmnsnSlctnLstnr implements ListSelectionListener
{
   JTable table;
   String slctdData;
   DmnsnVar dmnsnDt;

   public DmnsnSlctnLstnr(JTable myJTable)
   {
      table = myJTable;
      dmnsnDt = new DmnsnVar();
   }
   public void valueChanged(ListSelectionEvent myLstSltnEvnt)
   {
      int aSlctdRow = table.getSelectedRow();
      int aSlctdCol = table.getSelectedColumn();
      slctdData  = (String)table.getValueAt(aSlctdRow,aSlctdCol);
      System.out.println("DmnsnSlctLstnr: data = "+slctdData);
   }
   public String getSlctdData()
   {
      return slctdData;
   }
}