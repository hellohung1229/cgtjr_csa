package cgtjr.academics.general;

import java.awt.GridLayout;
import javax.swing.*;

public class AppTab extends LabTab {
    
   JEditorPane outputJEditorPane;
   SceneRoot theSceneRoot;

   public AppTab(SceneRoot mySceneRoot,JEditorPane myJEditorPane) {
      setLayout(new GridLayout(1,1));
      theSceneRoot = mySceneRoot;
      outputJEditorPane = myJEditorPane;
      addCmpnts();
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
   private void addCmpnts() {
      LabPnl panel = new LabPnl(theSceneRoot);
      addPnlToTab(panel,"Primiives");
   }
   private void addPnlToTab(JPanel myJPanel,String myInfo)
   {
      addTab(myInfo,null,myJPanel,myInfo);
      setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
}