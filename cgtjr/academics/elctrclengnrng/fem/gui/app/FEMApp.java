package cgtjr.academics.elctrclengnrng.fem.gui.app;

import cgtjr.academics.elctrclengnrng.fem.gui.tab.FEMTab;
import java.awt.*;
import javax.swing.*;

import cgtjr.academics.general.*;

public class FEMApp extends JApplet
{
   VirtualInputDevice theDevice;
   FileTextField aFileTextField;

   public FEMApp() {}
   
   public void init() 
   {   
      crtUI();
   }
   public void crtUI()
   {
      setLayout(new BorderLayout());

      SceneRoot aSceneRoot = new SceneRoot();
      FEMStation aFEMStation = new FEMStation(aSceneRoot,this);
      FEMTab anFEMTab = new FEMTab(aSceneRoot,this);
      String anAppTabNm = anFEMTab.getClass().getName();

      aFEMStation.insertLabPanel(anAppTabNm,anFEMTab);
      aFEMStation.displayLabPanel(anAppTabNm);
      
      add(BorderLayout.CENTER,aFEMStation);
      setVisible(true);
   }
   public void destroy() {
   }
   public static void main(String args[])
   {
      JFrame aJFrame = new JFrame();
      FEMApp aFEMApp = new FEMApp();
      //aJFrame.addWindowFocusListener(new WL());
      aJFrame.add(aFEMApp,BorderLayout.CENTER);
      aJFrame.setSize(500,600);
      aFEMApp.init();
      aJFrame.setVisible(true);
   }
}