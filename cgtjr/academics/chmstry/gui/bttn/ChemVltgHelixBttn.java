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
public class ChemVltgHelixBttn extends HelixBttn
{

   public ChemVltgHelixBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      VltgGrpPrcss aVltgGrpPrcss = new VltgGrpPrcss(new HelixVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}