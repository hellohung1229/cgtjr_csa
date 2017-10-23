package cgtjr.academics.elctrclengnrng.gui.pnl;


import java.util.*;

import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.*;


public class EMMtrlPnl extends LblBttnPnl
{
   EMMtrlVar rndrData;

   public EMMtrlPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      constructer(new EMMtrlVar());
      bldGUI();
   }
   private void constructer(EMMtrlVar myRndrVar)
   {
      rndrData = myRndrVar;
   }
   public void bldGUI()
   {
      HashMap aLinkedHashMap = rndrData.rtrvHshMp();
      insrtInfrmtn(aLinkedHashMap);
      dsplyCmpnts();
   }

    public void updtCmpnt(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void updtDmnsnVar(String myKey, String myValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}