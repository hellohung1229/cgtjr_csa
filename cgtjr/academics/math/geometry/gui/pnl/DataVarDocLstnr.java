/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.DataVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author clayton g thomas jr
 */
public class DataVarDocLstnr implements DocumentListener
{
   private DataVar dataVar;

   public DataVarDocLstnr()
   {   
   }
   public DataVarDocLstnr(DmnsnVar myDmnsnVar)
   {
      dataVar = myDmnsnVar;
   }
   public void setDataVar(DataVar myDataVar)
   {
      dataVar = myDataVar;
   }
   public void documentUpdate(DocumentEvent myDcmntEvnt)
   {
      Document aDocument = myDcmntEvnt.getDocument();
      int aLength = aDocument.getLength();
      String aKey = (String)aDocument.getProperty("name");
      String aVal = "";
      try{
         aVal = (String)aDocument.getText(0,aLength);
      }catch(BadLocationException myBLE){
         System.err.println("DmnsnPnl: "+myBLE.getMessage());
      }
      //dmnsnData.insrtData(aKey,aVal);
      updtDmnsnVar(aKey,aVal);
   }
   public void changedUpdate(DocumentEvent myDcmntEvnt)
   {
      documentUpdate(myDcmntEvnt);
   }
   public void insertUpdate(DocumentEvent myDcmntEvnt)
   {
      documentUpdate(myDcmntEvnt);
   }
   public void removeUpdate(DocumentEvent e)
   {
      System.out.println(e.getDocument().getProperty("name"));
   }
   public void updtDmnsnVar(String myKey,String myVal)
   {
      //String aKey = (String)pnlComboBox.getSelectedItem();
      //aJTextField.setText(myValue);
      //HashMap aHashMap2 = dataVar.getKeyVarMap();
      //DmnsnVar dmnsnDataTmp = (DmnsnVar)aHashMap2.get(aKey);
      System.out.println("DataVarDocLstnr: updating type = "+dataVar.getDataNmKey()+", key = "+myKey+", val = "+myVal);
      dataVar.updtKeyValMap(myKey,myVal);
      HashMap aHashMap = dataVar.getKeyValMap();
      dataVar.updtKeyVals(aHashMap);
   }
}