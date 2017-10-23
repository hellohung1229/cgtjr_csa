package cgtjr.academics.elctrclengnrng.gui.pnl;


import java.util.*;

import cgtjr.academics.general.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class WvEqtnPnl extends LblTxtFldPnl implements DocumentListener
{
    public void updtCmpnt(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void updtDmnsnVar(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   WvEqtnVar wvEqtnData;

   public WvEqtnPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      constructer(new WvEqtnVar());
      bldGUI();
   }
   private void constructer(WvEqtnVar myWvEqtnVar)
   {
      wvEqtnData = myWvEqtnVar;
   }
   public void bldGUI()
   {
      HashMap aLinkedHashMap = wvEqtnData.rtrvHshMp();
      insrtInfrmtn(aLinkedHashMap);
      dsplyCmpnts();
   }
   public void changedUpdate(DocumentEvent myDcmntEvnt)
   {
      System.out.println("changedUpdate - Not supported yet.");
   }
   public void insertUpdate(DocumentEvent e) 
   {
      Document aDocument = e.getDocument();
      int aLength = aDocument.getLength();
      String aKey = (String)aDocument.getProperty("name");
      String aVal = "";
      try{
         aVal = (String)aDocument.getText(0,aLength);
      }catch(BadLocationException myBLE){
         System.err.println("WvEqtnPnl: "+myBLE.getMessage());
      }
      wvEqtnData.insrtData(aKey,aVal);
   }
   public void removeUpdate(DocumentEvent e)
   {
      System.out.println(e.getDocument().getProperty("name"));
   }
}