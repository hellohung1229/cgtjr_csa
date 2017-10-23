/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.gui.bttn;

import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import cgtjr.academics.math.geometry.gui.bttn.HelixBttn;

/**
 *
 * @author clayton g thomas jr
 */
public class ChrgHelixBttn extends HelixBttn
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
   public ChrgHelixBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public void addActnLstnr()
   {
      HelixVar aHelixVar = new HelixVar();
      System.out.println("ChrgHelixBttn: type = "+aHelixVar.getCrdntTpVal());
      ChrgeMnlGrpPrcss aChrgGrpPrcss = new ChrgeMnlGrpPrcss(aHelixVar,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aChrgGrpPrcss);
      addActionListener(aDtPrcssLstnr);
   }    
}