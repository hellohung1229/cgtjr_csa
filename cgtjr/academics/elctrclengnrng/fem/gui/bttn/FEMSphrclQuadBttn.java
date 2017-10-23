/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
import cgtjr.academics.math.geometry.gui.bttn.SphrclSrfcBttn;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class FEMSphrclQuadBttn extends SphrclSrfcBttn
{

   public FEMSphrclQuadBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public FEMSphrclQuadBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public void addActnLstnr()
   {
      SphrclVar aSphrclVar1[] = new SphrclVar[1];
      SphrclVar aSphrclVar2[] = new SphrclVar[2];

      aSphrclVar1[0] = new SphrclVar(0,0,0,4,0,0,4,Math.PI,2*Math.PI,1,Math.PI/16,Math.PI/16,4,0,0);
      aSphrclVar2[0] = new SphrclVar(0,0,0,4,0,0,4,Math.PI/8,2*Math.PI);
      aSphrclVar2[1] = new SphrclVar(0,0,0,4,Math.PI-Math.PI/8,0,4,Math.PI,2*Math.PI);

      aSphrclVar2[0].setValueVal(100);
      aSphrclVar2[1].setValueVal(0);

      GroupPrcss aQuadGroupPrcss = new GroupPrcss(aSphrclVar1,aSphrclVar2,getScnRt());

      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aQuadGroupPrcss);
      addActionListener(aDtPrcssLstnr);      
   }
}