package cgtjr.academics.general;

import javax.swing.*;

public abstract class LblBttnPnl_1 extends CmpntPnl
{
   public LblBttnPnl_1(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public JComponent crtCmpnnt2(String myStrng1,String myStrng2)
   {
      JCheckBox aJTextField2 = new JCheckBox();
      aJTextField2.setName(myStrng1);
      return aJTextField2;
   }
}