/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.bttn;

import cgtjr.academics.math.geometry.gui.bttn.Crtssn3DBttn;
import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class RdstyQuadBttn extends Crtssn3DBttn
{

   public RdstyQuadBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public RdstyQuadBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar1[] = new DmnsnVar[1];
      DmnsnVar aDmnsnVar2[] = new DmnsnVar[4];

      /*
      aDmnsnVar1[0] = new DmnsnVar(0,0,0,0,0,0,6,4,0,0.5,0.50,1.0);
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,0,4,0,0,0,0);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,0,0,0,6,0,0);
      aDmnsnVar2[2] = new DmnsnVar(0,0,0,6,0,0,6,4,0);      
      aDmnsnVar2[3] = new DmnsnVar(0,0,0,0,0,0,6,4,0);      
      */
      aDmnsnVar1[0] = new DmnsnVar(0,0,0,0,0,0,4,4,0,1.0,1.0,1.0);
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,0,4,0,0,0,0);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,0,0,0,4,0,0);
      aDmnsnVar2[2] = new DmnsnVar(0,0,0,4,0,0,4,4,0);      
      aDmnsnVar2[3] = new DmnsnVar(0,0,0,1,4,0,3,4,0);            
      
      aDmnsnVar2[0].setValueVal(0);
      aDmnsnVar2[1].setValueVal(0);
      aDmnsnVar2[2].setValueVal(0);
      aDmnsnVar2[3].setValueVal(10);
      GroupPrcss aQuadGroupPrcss = new GroupPrcss(aDmnsnVar1,aDmnsnVar2,getScnRt());

      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aQuadGroupPrcss);
      addActionListener(aDtPrcssLstnr);      
   }
}