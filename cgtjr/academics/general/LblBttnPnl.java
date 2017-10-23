package cgtjr.academics.general;

import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class LblBttnPnl extends CmpntPnl
{
   private int defaultChckd = 0;
   private int defaultChckdIndx = 0;
   
   private ActionListener actnLstnr;
   
   public LblBttnPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   //TODO: change this to the super classes actionlister
   public void setActnLstnr(ActionListener myActnLstnr)
   {
      actnLstnr = myActnLstnr;
   }
   public JComponent crtCmpnnt2(String myStrng1,String myStrng2)
   {

      JCheckBox aJCheckBox = new JCheckBox();
      aJCheckBox.setName(myStrng1);
      aJCheckBox.addActionListener(actnLstnr);
      if(defaultChckd == defaultChckdIndx)
      {
          aJCheckBox.setSelected(true);
      }
      defaultChckdIndx++;
      return aJCheckBox;
   }
}