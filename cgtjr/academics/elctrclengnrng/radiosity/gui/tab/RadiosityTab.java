package cgtjr.academics.elctrclengnrng.radiosity.gui.tab;

import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.elctrclengnrng.fem.SparseMtrxVar;
import cgtjr.academics.general.gui.IntnstyNmbrPnl;
import cgtjr.academics.elctrclengnrng.fem.gui.pnl.SprsMtrxPnl;
import cgtjr.academics.elctrclengnrng.radiosity.gui.pnl.RdstyBttnPnl;
import cgtjr.academics.math.geometry.gui.pnl.RndrngPnl;
import cgtjr.academics.math.geometry.gui.pnl.DmnsnPnl;
import cgtjr.academics.math.geometry.gui.pnl.MtrlPnl;
import java.awt.GridLayout;
import javax.swing.*;

import cgtjr.academics.general.*;
import java.applet.Applet;

public class RadiosityTab extends LabTab {
    
   JEditorPane outputJEditorPane;
   SceneRoot scnRt;

   public RadiosityTab(SceneRoot mySceneRoot)
   {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      //System.out.println("FEMTab: scnRt = "+scnRt);
      addCmpnts();
   }
   public RadiosityTab(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      //System.out.println("FEMTab: scnRt = "+scnRt);
      addCmpnts(myLabWndwPnl);
   }
   /*
   public FEMTab(SceneRoot mySceneRoot,JEditorPane myJEditorPane) {
      setLayout(new GridLayout(1,1));
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
      scnRt = mySceneRoot;
      outputJEditorPane = myJEditorPane;
      addCmpnts();
   }*/
   private void addCmpnts() {
      RdstyBttnPnl panell = new RdstyBttnPnl(scnRt);
      DmnsnPnl panel2 = new DmnsnPnl(scnRt);
      //TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
      RndrngPnl panel4 = new RndrngPnl(scnRt);
      MtrlPnl panel5 = new MtrlPnl(scnRt);
      IntnstyNmbrPnl panel6 = new IntnstyNmbrPnl(scnRt);
      SprsMtrxPnl panel7 = new SprsMtrxPnl(scnRt);      

      addPnlToTab(panell,"Primiives");
      addPnlToTab(new JScrollPane(panel2),DmnsnVar.getDataNmKey());
      //addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
      addPnlToTab(panel4,RndrngVar.getDataNmKey());
      addPnlToTab(panel5,MtrlVar.getDataNmKey());
      addPnlToTab(panel6,IntnstyNmbrVar.getDataNmKey());
      addPnlToTab(panel7,SparseMtrxVar.getDataNmKey());      
   }
   private void addCmpnts(Applet myLabWndwPnl) {
      RdstyBttnPnl panell = new RdstyBttnPnl(scnRt,myLabWndwPnl);
      DmnsnPnl panel2 = new DmnsnPnl(scnRt);
      //TrnsfrmPnl panel3 = new TrnsfrmPnl(scnRt);
      RndrngPnl panel4 = new RndrngPnl(scnRt);
      MtrlPnl panel5 = new MtrlPnl(scnRt);
      IntnstyNmbrPnl panel6 = new IntnstyNmbrPnl(scnRt);
      SprsMtrxPnl panel7 = new SprsMtrxPnl(scnRt);      

      addPnlToTab(panell,"Primiives");
      addPnlToTab(new JScrollPane(panel2),DmnsnVar.getDataNmKey());
      //addPnlToTab(panel3,TrnsfrmVar.getDataNmKey());
      addPnlToTab(panel4,RndrngVar.getDataNmKey());
      addPnlToTab(panel5,MtrlVar.getDataNmKey());
      addPnlToTab(panel6,IntnstyNmbrVar.getDataNmKey());
      addPnlToTab(panel7,SparseMtrxVar.getDataNmKey());      
   }
   private void addPnlToTab(JComponent myJComponent,String myInfo)
   {
      addTab(myInfo,null,myJComponent,myInfo);
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
}