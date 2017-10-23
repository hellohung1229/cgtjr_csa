package cgtjr.academics.general;

import javax.swing.*;
import java.awt.*;

public abstract class LblSldrPnl extends CmpntPnl
{
   public LblSldrPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public JComponent crtCmpnnt2(String myStrng1,String myStrng2)
   {
      JPanel aJPanel = new JPanel();
      JSlider aJSlider = new JSlider(0,1);
      aJSlider.setMaximumSize(new Dimension(80,20));
      aJSlider.setPreferredSize(new Dimension(80,20));
      JTextField aJTextField2 = new JTextField(4);
      aJTextField2.setName(myStrng1);
      aJPanel.add(aJSlider);
      aJPanel.add(aJTextField2);
      return aJPanel;
   }
}