package cgtjr.academics.math.geometry.gui.app;

import cgtjr.academics.math.geometry.gui.tab.GmtryTab;

import java.awt.*;
import javax.swing.*;

import cgtjr.academics.general.*;

public class GmtryApplctn
{    
   private VirtualInputDevice theDevice;

   public GmtryApplctn() {}
   
   public static void main(String args[])
   {
      JFrame aJFrame = new JFrame();
      aJFrame.setLayout(new BorderLayout());
      GmtryApp aGmtryApp = new GmtryApp();
      Component aComponent = aGmtryApp.crtUI();
      //aJFrame.addWindowFocusListener(new WL());
      aJFrame.add(aComponent,BorderLayout.CENTER);
      aJFrame.setSize(500,600);
      aJFrame.setVisible(true);
   }
}