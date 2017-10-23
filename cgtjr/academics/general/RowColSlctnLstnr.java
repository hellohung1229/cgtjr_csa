/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import javax.swing.event.*;
import javax.swing.*;
/**
 *
 * @author clayton g thomas jr
 */

public class RowColSlctnLstnr implements ListSelectionListener
{
   JTable table;
   String slctdData;

   public RowColSlctnLstnr(JTable myJTable)
   {
      table = myJTable;

      /*This code is used in the calling class
      JTable table = getTable();
      table.setCellSelectionEnabled(true);
      ListSelectionModel cellSelectionModel = table.getSelectionModel();
      cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      DmnsnSlctnLstnr aDmnsnSlctnLstnr = new DmnsnSlctnLstnr(table);
      cellSelectionModel.addListSelectionListener(aDmnsnSlctnLstnr);
       */
   }
   public void valueChanged(ListSelectionEvent myLstSltnEvnt)
   {
      int aSlctdRow = table.getSelectedRow();
      int aSlctdCol = table.getSelectedColumn();
      slctdData  = (String)table.getValueAt(aSlctdRow,aSlctdCol);
   }
   public String getSlctdData()
   {
      return slctdData;
   }
}