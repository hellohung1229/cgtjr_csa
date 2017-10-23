package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import javax.media.j3d.Canvas3D;

public class SceneApp extends Applet implements ActionListener
{
   VirtualInputDevice theDevice;
   FileTextField aFileTextField;

   public SceneApp() {}
   
   public void init() 
   {   
      crtUI();
   }
   public void crtUI()
   {
      //setBackground(new Color(0x00cc00));
      //setForeground(new Color(0x003300));
      Panel aTopToolBarPanel = new Panel();
      aTopToolBarPanel.setLayout(new BorderLayout());

	   setLayout(new BorderLayout());

      theDevice = new VirtualInputDevice();
      theDevice.initialize();
      JPanel aDevicePanel = theDevice.retrieveVirtualPanel();
      SceneRoot aSceneRoot = new SceneRoot(theDevice);
      Canvas3D myCanvas3D = aSceneRoot.crtScnCnvs3D();
      aSceneRoot.crtScene(); //before labstation !!!

      FileTextField aFileTextField = new FileTextField(aSceneRoot);
      //PDBMoleculeFile.setURLBase(getCodeBase());
      LabStation aLabStation = new LabStation(aSceneRoot);
      JEditorPane aJEditorPane = aLabStation.getEditorPane();
      AppTab anAppTab = new AppTab(aSceneRoot,aJEditorPane);
      String anAppTabNm = anAppTab.getClass().getName();
      aLabStation.insertLabPanel(anAppTabNm,anAppTab);
      aLabStation.displayLabPanel(anAppTabNm);
      aLabStation.setPreferredSize(new Dimension(800,400));
      aLabStation.setMinimumSize(new Dimension(800,400));   
      //FileMenuBar aFileMenuBar = new FileMenuBar(aLabStation);
	   //aTopToolBarPanel.add("North", aFileMenuBar);

      add(BorderLayout.NORTH,aFileTextField);
      add(BorderLayout.CENTER,aLabStation);
      add(BorderLayout.SOUTH,aDevicePanel);
      setVisible(true);
   }
   public void actionPerformed(ActionEvent e)
   {
   }
   public void destroy() {
   }
}