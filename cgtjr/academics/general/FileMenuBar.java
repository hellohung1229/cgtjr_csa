package cgtjr.academics.general;

import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.*;
import javax.vecmath.*;


public class FileMenuBar extends JMenuBar implements ActionListener
{
   //JFileChooser aJFileChooser = null;
   SceneRoot theSceneRoot = null;

   JMenuItem aJMenuItemDFT = null;

   JMenuItem aJMenuItemQMT = null;
   JMenuItem aJMenuItemTEM = null;
   JMenuItem aJMenuItemMOLECULAR = null;
   
   JMenuItem aJMenuItemSpatial = null;
   JMenuItem aJMenuItemFrequency = null;

   JMenuItem aJMenuItemHoughKerman = null;
   JMenuItem aJMenuItemShadeShape = null;
   JMenuItem aJMenuItemCornerDetect = null;

   JMenuItem aJMenuItemOpen = null;
   JMenuItem aJMenuItemExit = null;
   JMenuItem aJMenuItemFolder = null;
   JMenuItem aJMenuItemAbout = null;

   TextArea theOutputTextArea;
   LabStation theLabStation;
   File aFile = null;

   public FileMenuBar(SceneRoot mySceneRoot)
   {
      theSceneRoot = mySceneRoot;
      createFileMenuBar();
   }
   public void setOutputTextArea(TextArea myTextArea)
   {
      theOutputTextArea = myTextArea;
   }
   public JMenuBar createFileMenuBar()
   {
    
    JMenu filemenu = new JMenu("File");
    JMenu modulemenu = new JMenu("Modules");
    /*
    JMenu transformmenu = new JMenu("Transform");

    JMenu geometricmenu = new JMenu("Geometric");

    JMenu filtermenu = new JMenu("Filters");
    JMenu helpmenu = new JMenu("Help");
    */
    aJMenuItemTEM = new JMenuItem("TEM");
    aJMenuItemTEM.setName("tem");
    aJMenuItemTEM.addActionListener(this);

    aJMenuItemExit = new JMenuItem("Exit");
    aJMenuItemExit.setName("exit");
    aJMenuItemExit.addActionListener(this);

    /*
    aJMenuItemOpen = new JMenuItem("Open");
    aJMenuItemOpen.setName("open");
    aJMenuItemOpen.addActionListener(this);

    aJMenuItemDFT = new JMenuItem("DFT");
    aJMenuItemDFT.setName("dft");
    aJMenuItemDFT.addActionListener(this);

    aJMenuItemFolder = new JMenuItem("Folders");
    aJMenuItemFolder.setName("folders");
    aJMenuItemFolder.addActionListener(this);

    aJMenuItemMOLECULAR = new JMenuItem("Molecular Display");
    aJMenuItemMOLECULAR.setName("molecular");
    aJMenuItemMOLECULAR.addActionListener(this);

    aJMenuItemSpatial = new JMenuItem("Spatial/Temporal");
    aJMenuItemSpatial.setName("spatial");
    aJMenuItemSpatial.addActionListener(this);

    aJMenuItemFrequency = new JMenuItem("Frequency");
    aJMenuItemFrequency.setName("frequency");
    aJMenuItemFrequency.addActionListener(this);

    aJMenuItemHoughKerman = new JMenuItem("HoughKerman");
    aJMenuItemHoughKerman.setName("HoughKerman");
    aJMenuItemHoughKerman.addActionListener(this);

    aJMenuItemCornerDetect= new JMenuItem("CornerDetect");
    aJMenuItemCornerDetect.setName("CornerDetect");
    aJMenuItemCornerDetect.addActionListener(this);

    aJMenuItemShadeShape = new JMenuItem("Shade-Shape");
    aJMenuItemShadeShape.setName("Shade-Shape");
    aJMenuItemShadeShape.addActionListener(this);

    aJMenuItemAbout = new JMenuItem("About US3DA");
    aJMenuItemAbout.setName("aboutus3da");
    aJMenuItemAbout.addActionListener(this);

    filemenu.add(aJMenuItemOpen);
    filemenu.add(aJMenuItemFolder);
    filemenu.add(aJMenuItemExit);

    filtermenu.add(aJMenuItemSpatial);
    filtermenu.add(aJMenuItemFrequency);
    geometricmenu.add(aJMenuItemHoughKerman);
    geometricmenu.add(aJMenuItemShadeShape);
    geometricmenu.add(aJMenuItemCornerDetect);
    transformmenu.add(aJMenuItemDFT);
    modulemenu.add(aJMenuItemMOLECULAR);
    helpmenu.add(aJMenuItemAbout);


    add(filtermenu);
    add(transformmenu);
    add(geometricmenu);

    add(helpmenu);
    */
    modulemenu.add(aJMenuItemTEM);
    add(filemenu);
    add(modulemenu);

    return this;
   }
   
   public void actionPerformed(ActionEvent e)
   {

      JMenuItem source = (JMenuItem)(e.getSource());
      String aMenuName = source.getName();
      //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
      if(aJMenuItemTEM != null && aMenuName.equals(aJMenuItemTEM.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabbedPaneTEM");
      }    
      /*
      if(aJMenuItemMOLECULAR != null && aMenuName.equals(aJMenuItemMOLECULAR.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabbedPane3DView");
      }else if(aJMenuItemAbout != null && aMenuName.equals(aJMenuItemAbout.getName()))
      {
         JOptionPane.showMessageDialog(null, "Unified Structural 3D Analysis (US3DA)\nVersion 1.1 (Beta)\nCopyright - 2008", "Message",JOptionPane.INFORMATION_MESSAGE );   
      }else if(aJMenuItemOpen != null && aMenuName.equals(aJMenuItemOpen.getName()))
      {
         aJFileChooser.showOpenDialog(null);
         aFile = aJFileChooser.getSelectedFile();

         //System.out.println("name = "+aFile.getAbsolutePath()+","+aFile.getPath()+","+aFile.getName());
         theLabStation.loadData(aFile.getAbsolutePath());
      }else if(aJMenuItemHoughKerman != null && aMenuName.equals(aJMenuItemHoughKerman.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabbedPaneHK");
      }else if(aJMenuItemShadeShape != null && aMenuName.equals(aJMenuItemShadeShape.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabShadeShape");
      }else if(aJMenuItemTEM != null && aMenuName.equals(aJMenuItemTEM.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabbedPaneTEM");
      }else if(aJMenuItemCornerDetect != null && aMenuName.equals(aJMenuItemCornerDetect.getName()))
      {
         //System.out.println("FileMenuBar.actionPerformed(): performing action for : "+aMenuName);
         theLabStation.installLab("TabCornerDetect");
      }else if(aJMenuItemExit != null && aMenuName.equals(aJMenuItemExit.getName()))
      {
         System.exit(0);
      }*/
   }
 }