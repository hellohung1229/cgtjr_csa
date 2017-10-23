package cgtjr.academics.chmstry.gui.tab;

import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import cgtjr.academics.math.geometry.gui.pnl.RndrngPnl;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnl;
import java.awt.GridLayout;
import javax.swing.*;

import cgtjr.academics.general.*;
import cgtjr.academics.chmstry.gui.pnl.*;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.gui.pnl.HelixDmnsnPnl;
import cgtjr.academics.math.sttstcs.VarianceVar;

public class ChmstryTab extends LabTab {
    
   JEditorPane outputJEditorPane;
   SceneRoot scnRt;

   public ChmstryTab(SceneRoot mySceneRoot)
   {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      System.out.println("ChmstryTab: scnRt = "+scnRt);
      //addChangeListener(new TbPnLstnr());
      addCmpnts();

   }
   /*
   public ChmstryTab(SceneRoot mySceneRoot,JEditorPane myJEditorPane) {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      outputJEditorPane = myJEditorPane;
      addCmpnts();
   }*/
   private void addCmpnts() {
      //ChmstryIcnPnl panell = new ChmstryIcnPnl(scnRt);
      HelixDmnsnPnl panel2 = new HelixDmnsnPnl(scnRt);
      //TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
      RndrngPnl panel4 = new RndrngPnl(scnRt);
      MtrlPnl panel5 = new MtrlPnl(scnRt);
      VrncPnl panel6 = new VrncPnl(scnRt);
      ChrgeIcnPnl aChrgeIcnPnl = new ChrgeIcnPnl(scnRt);

      addPnlToTab(aChrgeIcnPnl,"Potential Map");
      //addPnlToTab(panell,"Dipole");
      addPnlToTab(new JScrollPane(panel2),DmnsnVar.getDataNmKey());
      //addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
      addPnlToTab(panel4,RndrngVar.getDataNmKey());
      //addPnlToTab(panel5,MtrlVar.getDataNmKey());
      addPnlToTab(panel6,VarianceVar.getDataNmKey());
   }
   private void addPnlToTab(JComponent myJComponent,String myInfo)
   {
      addTab(myInfo,null,myJComponent,myInfo);
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
}