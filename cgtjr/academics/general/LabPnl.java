package cgtjr.academics.general;

import java.awt.FlowLayout;
import javax.swing.*;


public class LabPnl extends JPanel
{
   private SceneRoot scnRt;
   
   public LabPnl(SceneRoot mySceneRoot)
   {
      FlowLayout aFlowLayout = new FlowLayout(FlowLayout.LEFT,1,1);
      setLayout(aFlowLayout);
      scnRt = mySceneRoot;
   }
   public SceneRoot getScnRt()
   {
      return scnRt;
   }
}