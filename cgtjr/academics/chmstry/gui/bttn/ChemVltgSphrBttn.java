/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
import cgtjr.academics.math.geometry.gui.bttn.Sphrcl3DBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class ChemVltgSphrBttn extends Sphrcl3DBttn
{

   public ChemVltgSphrBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      VltgGrpPrcss aVltgGrpPrcss = new VltgGrpPrcss(new SphrclVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}