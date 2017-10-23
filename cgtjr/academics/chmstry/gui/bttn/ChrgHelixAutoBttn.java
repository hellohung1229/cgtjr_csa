/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.AutomaticDmnsnVar;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import cgtjr.academics.math.geometry.gui.bttn.HelixAutoBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class ChrgHelixAutoBttn extends HelixAutoBttn
{
   /*
   public ChrgHelixBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      ChrgGrpPrcss aVltgGrpPrcss = new ChrgGrpPrcss(new HelixVar(),getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aVltgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }*/
   public ChrgHelixAutoBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      HelixVar aHelixVar = new HelixVar();
      System.out.println("ChrgHelixBttn: type = "+aHelixVar.getCrdntTpVal());
      //ChrgeAutoGrpPrcss aChrgGrpPrcss = new ChrgeAutoGrpPrcss(aHelixVar,getScnRt());
      ChrgAutoGrpPrcss aChrgGrpPrcss = new ChrgAutoGrpPrcss(aHelixVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aChrgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }    
}