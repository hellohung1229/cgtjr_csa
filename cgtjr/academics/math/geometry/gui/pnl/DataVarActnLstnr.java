/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.DataVar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JCheckBox;

/**
 *
 * @author clayton g thomas jr
 */
public class DataVarActnLstnr implements ActionListener
{
   private DataVar dataVar;


   public DataVarActnLstnr(DataVar myDmnsnVar)
   {
      dataVar = myDmnsnVar;
   }
   public void setDataVar(DataVar myDataVar)
   {
      dataVar = myDataVar;
   }
   public void actionPerformed(ActionEvent myActionEvent)
   {
      String aVal = "";
      JCheckBox aJCheckBox = (JCheckBox)myActionEvent.getSource();
      
      String bxName = aJCheckBox.getName();
      boolean isSlctd = aJCheckBox.isSelected();
      
      updtDmnsnVar(bxName,isSlctd);
   }
   public void updtDmnsnVar(String myKey,boolean myVal)
   {
      //String aKey = (String)pnlComboBox.getSelectedItem();
      //aJTextField.setText(myValue);
      //HashMap aHashMap2 = dataVar.getKeyVarMap();
      //DmnsnVar dmnsnDataTmp = (DmnsnVar)aHashMap2.get(aKey);
      System.out.println("DataVarActnLstnr: updating type = "+dataVar.getDataNmKey()+", key = "+myKey+", val = "+myVal);
      dataVar.updtKeyValMap(myKey,""+myVal);
      HashMap aHashMap = dataVar.getKeyValMap();
      dataVar.updtKeyVals(aHashMap);
   }
}