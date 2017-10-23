/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.gui.bttn.Sphrcl3DBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class RfndChrgeCrtssnBttn extends Sphrcl3DBttn
{

   public RfndChrgeCrtssnBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar = new DmnsnVar(0,0,0,0,0,0,4,4,4,1,1,1);
      VltgChrgGrpRfnPrcss aVltgGrpRfnPrcss = new VltgChrgGrpRfnPrcss(aDmnsnVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpRfnPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}