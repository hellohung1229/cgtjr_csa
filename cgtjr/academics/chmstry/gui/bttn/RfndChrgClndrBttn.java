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
public class RfndChrgClndrBttn extends ClndrclBttn
{

   public RfndChrgClndrBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      ClndrclVar aClndrclVar = new ClndrclVar(0, 0, 0, 0, 0, 0, 2, 2 * Math.PI, 2, 0.2f, Math.PI / 16, 0.20f);
      //System.out.println("RfnVltgClndrBttn: type = "+aClndrclVar.getCrdntTpVal());
      VltgChrgGrpRfnPrcss aVltgChrgGrpPrcss = new VltgChrgGrpRfnPrcss(aClndrclVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgChrgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}