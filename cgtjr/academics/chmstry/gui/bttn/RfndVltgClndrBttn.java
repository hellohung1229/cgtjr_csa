/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.ClndrclVar;
import cgtjr.academics.math.geometry.gui.bttn.ClndrclBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class RfndVltgClndrBttn extends ClndrclBttn
{

   public RfndVltgClndrBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      ClndrclVar aClndrclVar = new ClndrclVar();
      //System.out.println("RfnVltgClndrBttn: type = "+aClndrclVar.getCrdntTpVal());
      VltgGrpRfnPrcss aVltgGrpRfnPrcss = new VltgGrpRfnPrcss(aClndrclVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpRfnPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}