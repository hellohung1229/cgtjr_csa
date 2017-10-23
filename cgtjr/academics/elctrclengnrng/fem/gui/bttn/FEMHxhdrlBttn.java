/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem.gui.bttn;

import cgtjr.academics.math.geometry.gui.bttn.Crtssn3DBttn;
import cgtjr.academics.general.*;
import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class FEMHxhdrlBttn extends Crtssn3DBttn
{

   public FEMHxhdrlBttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public FEMHxhdrlBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public void addActnLstnr()
   {
      DmnsnVar aDmnsnVar1[] = new DmnsnVar[1];
      DmnsnVar aDmnsnVar2[] = new DmnsnVar[2];

      aDmnsnVar1[0] = new DmnsnVar(0,0,0,0,0,0,2,2,2,0.5,0.5,0.5,0,0,0);
      /*
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,0,0,0);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,4,0,0,4,0,0);
      aDmnsnVar2[2] = new DmnsnVar(0,0,0,0,4,0,0,4,0);
      aDmnsnVar2[3] = new DmnsnVar(0,0,0,0,0,4,0,0,4);
      aDmnsnVar2[4] = new DmnsnVar(0,0,0,4,4,0,4,4,0);           
      aDmnsnVar2[5] = new DmnsnVar(0,0,0,0,4,4,0,4,4);      
      aDmnsnVar2[6] = new DmnsnVar(0,0,0,4,0,4,4,0,4);            
      aDmnsnVar2[7] = new DmnsnVar(0,0,0,4,4,4,4,4,4);            
      //aDmnsnVar2[8] = new DmnsnVar(0,0,0,2,2,2,2,2,2);                  
      */
      aDmnsnVar2[0] = new DmnsnVar(0,0,0,0,0,0,2,2,0);
      aDmnsnVar2[1] = new DmnsnVar(0,0,0,0,0,2,2,2,2);

      aDmnsnVar2[0].setValueVal(0);
      aDmnsnVar2[1].setValueVal(100);      

      /*
      aDmnsnVar2[0].setValueVal(20);
      aDmnsnVar2[1].setValueVal(20);
      aDmnsnVar2[2].setValueVal(20);
      aDmnsnVar2[3].setValueVal(20);
      aDmnsnVar2[4].setValueVal(20);
      aDmnsnVar2[5].setValueVal(20);
      aDmnsnVar2[6].setValueVal(20);
      aDmnsnVar2[7].setValueVal(20);
      //aDmnsnVar2[8].setValueVal(0);      
      */
      
      GroupPrcss aGroupPrcss = new GroupPrcss(aDmnsnVar1,aDmnsnVar2,getScnRt());

      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGroupPrcss);
      addActionListener(aDtPrcssLstnr);
      
   }
}