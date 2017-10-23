/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.bttn;

import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.gui.bttn.ClndrclBttn;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class RdstyClndrclTrnglTpSrfc extends ClndrclBttn
{

   public RdstyClndrclTrnglTpSrfc(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public RdstyClndrclTrnglTpSrfc(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar1[] = new DmnsnVar[1];
      DmnsnVar aDmnsnVar2[] = new DmnsnVar[2];

      aDmnsnVar1[0] = new DmnsnVar(0,0,0,25,0,0,40,2*Math.PI,0,5,Math.PI/32,1,0,0,0);
      aDmnsnVar1[0].setCrdntTpVal(CrdntType.getClndrclCrdntTp());
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,0,2*Math.PI,4);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,4,0,0,4,2*Math.PI,4);

      aDmnsnVar2[0].setValueVal(0);
      aDmnsnVar2[1].setValueVal(100);

      TrnglrGroupPrcss aGroupPrcss = new TrnglrGroupPrcss(aDmnsnVar1,aDmnsnVar2,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGroupPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}