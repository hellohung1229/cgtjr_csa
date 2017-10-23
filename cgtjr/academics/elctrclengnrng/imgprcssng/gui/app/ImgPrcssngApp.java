package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.imgprcssng.gui.tab.ImgPrcssngTab;
import java.awt.*;
import javax.swing.*;

import cgtjr.academics.general.*;

public class ImgPrcssngApp extends JApplet
{
   VirtualInputDevice theDevice;
   FileTextField aFileTextField;

   public ImgPrcssngApp() {}
   
   public void init() 
   {   
      crtUI();
   }
   public void crtUI()
   {
      setLayout(new BorderLayout());

      SceneRoot aSceneRoot = new SceneRoot();
      ImgPrcssngSttn aFEMStation = new ImgPrcssngSttn(aSceneRoot);
      ImgPrcssngTab anImgPrcssngTab = new ImgPrcssngTab(aSceneRoot);
      String anAppTabNm = anImgPrcssngTab.getClass().getName();

      aFEMStation.insertLabPanel(anAppTabNm,anImgPrcssngTab);
      aFEMStation.displayLabPanel(anAppTabNm);
      
      add(BorderLayout.CENTER,aFEMStation);
      setVisible(true);
   }
   public void destroy() {
   }
   public static void main(String args[])
   {
      JFrame aJFrame = new JFrame();
      ImgPrcssngApp aFEMApp = new ImgPrcssngApp();
      //aJFrame.addWindowFocusListener(new WL());
      aJFrame.add(aFEMApp,BorderLayout.CENTER);
      aJFrame.setSize(500,600);
      aFEMApp.init();
      aJFrame.setVisible(true);
   }
}