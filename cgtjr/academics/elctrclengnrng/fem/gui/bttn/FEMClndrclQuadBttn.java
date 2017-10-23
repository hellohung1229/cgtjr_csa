/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.dmnsvar.ClndrclVar;
import cgtjr.academics.math.geometry.gui.bttn.ClndrclSrfcBttn;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class FEMClndrclQuadBttn extends ClndrclSrfcBttn
{

   public FEMClndrclQuadBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public FEMClndrclQuadBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }   
   public void addActnLstnr()
   {
      ClndrclVar aClndrclVar1[] = new ClndrclVar[1];
      ClndrclVar aClndrclVar2[] = new ClndrclVar[2];

      aClndrclVar1[0] = new ClndrclVar(0,0,0,4,0,0,4,2*Math.PI,7,1,Math.PI/16,1,4,0,0);
      aClndrclVar2[0] = new ClndrclVar(0,0,0,4,0,0,4,2*Math.PI,0);
      aClndrclVar2[1] = new ClndrclVar(0,0,0,4,0,7,4,2*Math.PI,7);

      aClndrclVar2[0].setValueVal(100);
      aClndrclVar2[1].setValueVal(0);

      GroupPrcss aQuadGroupPrcss = new GroupPrcss(aClndrclVar1,aClndrclVar2,getScnRt());

      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aQuadGroupPrcss);
      addActionListener(aDtPrcssLstnr);      
   }
}