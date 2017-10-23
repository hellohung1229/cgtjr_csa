/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
import cgtjr.academics.math.geometry.gui.bttn.Sphrcl3DBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class RfndVltgSphrBttn extends Sphrcl3DBttn
{
   public RfndVltgSphrBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      VltgGrpRfnPrcss aVltgGrpRfnPrcss = new VltgGrpRfnPrcss(new SphrclVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpRfnPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}