/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author clayton g thomas jr
 */
public class TablePnl extends LabPnl
{
   private JTable table;
   private JScrollPane scrllPn;

   public TablePnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      setLayout(new BorderLayout());
   }
   /*
   public TablePnl()
   {
      table = new JTable(myRow,myColumn);
      scrllPn = new JScrollPane();
   }
   public TablePnl(String myRows[][],String myCols[])
   {
      table = new JTable(myRows,myCols);
      scrllPn = new JScrollPane();
   }*/
   public void crtScrllTbl(String myRows[][],String myCols[])
   {
      table = new JTable(myRows,myCols);
      setTable(table);
      scrllPn = new JScrollPane(table);
      add(BorderLayout.CENTER,scrllPn);
   }
   public JScrollPane getScrllPn()
   {
      return scrllPn;
   }
   public JTable getTable()
   {
      return table;
   }
   public void setTable(JTable myJTable)
   {
      table = myJTable;
   }
}