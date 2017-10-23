package cgtjr.academics.elctrclengnrng.imgprcssng.gui.tab;

import cgtjr.academics.math.geometry.dmnsvar.TrnsfrmVar;
import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.elctrclengnrng.imgprcssng.gui.pnl.ImgMshPnl;
import cgtjr.academics.math.geometry.gui.pnl.RndrngPnl;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnPnl;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnl;
import cgtjr.academics.math.geometry.gui.pnl.TrnsfrmPnl;
import java.awt.GridLayout;
import javax.swing.*;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.general.*;

public class ImgPrcssngTab extends LabTab {
    
   JEditorPane outputJEditorPane;
   SceneRoot scnRt;

   public ImgPrcssngTab(SceneRoot mySceneRoot)
   {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      System.out.println("FEMTab: scnRt = "+scnRt);
      //addChangeListener(new TbPnLstnr());
      addCmpnts();

   }
   /*
   public ImgPrcssngTab(SceneRoot mySceneRoot,JEditorPane myJEditorPane) {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      outputJEditorPane = myJEditorPane;
      addCmpnts();
   }*/
   private void addCmpnts() {
      ImgMshPnl panell = new ImgMshPnl(scnRt);
      DmnsnPnl panel2 = new DmnsnPnl(scnRt);
      TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
      RndrngPnl panel4 = new RndrngPnl(scnRt);
      MtrlPnl panel5 = new MtrlPnl(scnRt);
      addPnlToTab(panell,"Primiives");
      addPnlToTab(new JScrollPane(panel2),DmnsnVar.getDataNmKey());
      addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
      addPnlToTab(panel4,RndrngVar.getDataNmKey());
      addPnlToTab(panel5,MtrlVar.getDataNmKey());
   }
   private void addPnlToTab(JComponent myJComponent,String myInfo)
   {
      addTab(myInfo,null,myJComponent,myInfo);
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
}