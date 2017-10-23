/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.gui.bttn.Crtssn3DBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class ChemVltgHxhdrlBttn extends Crtssn3DBttn
{

   public ChemVltgHxhdrlBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar = new DmnsnVar();
      aDmnsnVar.setCrdntTpVal(CrdntType.getCrtsnCrdntTp());
      VltgGrpPrcss aVltgGrpPrcss = new VltgGrpPrcss(aDmnsnVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}