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
public class ChemVltgClndrBttn extends ClndrclBttn
{
   public ChemVltgClndrBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      VltgGrpPrcss aVltgGrpPrcss = new VltgGrpPrcss(new ClndrclVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}