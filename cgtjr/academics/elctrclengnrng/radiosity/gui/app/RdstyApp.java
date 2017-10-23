package cgtjr.academics.elctrclengnrng.radiosity.gui.app;

import cgtjr.academics.elctrclengnrng.radiosity.gui.tab.RadiosityTab;
import java.awt.*;
import javax.swing.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.crdntepnts.PrimaryRoot;

public class RdstyApp extends JApplet
{
   VirtualInputDevice theDevice;
   FileTextField aFileTextField;

   public RdstyApp() {}
   
   public void init() 
   {   
      crtUI();
   }
   public void crtUI()
   {
      setLayout(new BorderLayout());

      SceneRoot aSceneRoot = new SceneRoot();
      PrimaryRoot aPrimaryOrigin = new PrimaryRoot();
      PrimaryRoot.setPrimaryRoot(aPrimaryOrigin);
      RdstyStation aRdstyStation = new RdstyStation(aSceneRoot,this);
      RadiosityTab anRadiosityTab = new RadiosityTab(aSceneRoot,this);
      String anAppTabNm = anRadiosityTab.getClass().getName();

      aRdstyStation.insertLabPanel(anAppTabNm,anRadiosityTab);
      aRdstyStation.displayLabPanel(anAppTabNm);
      
      add(BorderLayout.CENTER,aRdstyStation);
      setVisible(true);
   }
   public void destroy() {
   }
   public static void main(String args[])
   {
      JFrame aJFrame = new JFrame();
      RdstyApp aRdstyApp = new RdstyApp();
      //aJFrame.addWindowFocusListener(new WL());
      aJFrame.add(aRdstyApp,BorderLayout.CENTER);
      aJFrame.setSize(500,600);
      aRdstyApp.init();
      aJFrame.setVisible(true);
   }
}