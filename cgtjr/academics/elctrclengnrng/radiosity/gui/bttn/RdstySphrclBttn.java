/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.bttn;

import cgtjr.academics.elctrclengnrng.radiosity.RdstyGroupPrcss;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.gui.bttn.Sphrcl3DBttn;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class RdstySphrclBttn extends Sphrcl3DBttn
{

   public RdstySphrclBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public RdstySphrclBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar1[] = new DmnsnVar[1];
      DmnsnVar aDmnsnVar2[] = new DmnsnVar[2];

      aDmnsnVar1[0] = new DmnsnVar(0,0,0,0,0,0,4,Math.PI,Math.PI/2,1,Math.PI/8,Math.PI/8,0,0,0);
      aDmnsnVar1[0].setCrdntTpVal(CrdntType.getSphrclCrdntTp());
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,0,Math.PI,2*Math.PI);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,4,0,0,4,Math.PI,2*Math.PI);

      aDmnsnVar2[0].setValueVal(0);
      aDmnsnVar2[1].setValueVal(100);

      RdstyGroupPrcss aGroupPrcss = new RdstyGroupPrcss(aDmnsnVar1,aDmnsnVar2,getScnRt());
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGroupPrcss);
      addActionListener(aDtPrcssLstnr);
   }
}