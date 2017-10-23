/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.math.geometry.gui.bttn.HelixBttn;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;

/**
 *
 * @author clayton g thomas jr
 */
public class ChemChrgeHelixBttn extends HelixBttn
{

   public ChemChrgeHelixBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      VltgChrgGrpRfnPrcss aVltgChrgGrpPrcss = new VltgChrgGrpRfnPrcss(new HelixVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgChrgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}