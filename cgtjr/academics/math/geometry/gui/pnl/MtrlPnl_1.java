package cgtjr.academics.math.geometry.gui.pnl;


import java.util.*;

import cgtjr.academics.general.*;
import javax.swing.event.*;
import javax.swing.text.*;


public class MtrlPnl_1 extends LblSldrPnl implements DocumentListener
{
   MtrlVar mtrlData;

   public MtrlPnl_1(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      constructer(new MtrlVar());
      bldGUI();
   }
   private void constructer(MtrlVar myMtrlVar)
   {
      mtrlData = myMtrlVar;
   }
   public void bldGUI()
   {
       /*
      HashMap aLinkedHashMap = mtrlData.rtrvHshMp();
      insrtInfrmtn(aLinkedHashMap);
      dsplyCmpnts();
       */
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
         System.err.println("DmnsnPnl: "+myBLE.getMessage());
      }
      //mtrlData.insrtData(aKey,aVal);
   }
   public void removeUpdate(DocumentEvent e)
   {
      System.out.println(e.getDocument().getProperty("name"));
   }

    public void updtCmpnt(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updtDmnsnVar(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}