package cgtjr.academics.math.geometry.gui.pnl;


import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import java.util.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RndrngPnl extends LblBttnPnl implements ActionListener
{
   RndrngVar rndrData;
   DataVarActnLstnr aDataVarActnLstnr;
   
   public RndrngPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      constructer(new RndrngVar());
      bldGUI();
   }
   private void constructer(RndrngVar myRndrVar)
   {
      rndrData = myRndrVar;
      RndrngDflt.setDfltVar(myRndrVar);
      aDataVarActnLstnr = new DataVarActnLstnr(myRndrVar);
      setActnLstnr(aDataVarActnLstnr);
   }
   public void bldGUI()
   {
      HashMap aLinkedHashMap = rndrData.crtKeyValMap();
      insrtInfrmtn(aLinkedHashMap);
      dsplyCmpnts();
   }
   
   public void actionPerformed(ActionEvent myActionEvent)
   {
      aDataVarActnLstnr.actionPerformed(myActionEvent);
   }
   //TODO: need to remove these methods
   public void updtCmpnt(String myKey,String myValue)
   {
       
   }
    public void updtDmnsnVar(String myKey, String myValue) {

    }
}