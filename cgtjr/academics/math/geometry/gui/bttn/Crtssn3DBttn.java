/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.math.geometry.shapeprcss.GmtryGrpUpdtr;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.ImageTool;
import javax.swing.*;
import java.awt.*;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.general.*;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class Crtssn3DBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   public Crtssn3DBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      String aFileInfo = "./data/images/shapes/hxhdrl3D.jpg";
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   public Crtssn3DBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/hxhdrl3D.jpg",myLabWndwPnl);
      anIcon = new ImageIcon(aFileInfo);      
      constructor();
      addActnLstnr();
   }
   public void constructor()
   {
      setIcon(anIcon);
      Dimension aDimension = new Dimension(30,30);
      setMaximumSize(aDimension);
      setMinimumSize(aDimension);
      setPreferredSize(aDimension);      
   }
   public SceneRoot getScnRt()
   {
      return scnRt;
   }
   public void addActnLstnr()
   {
      GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new DmnsnVar(),scnRt);
      aGmtryGrpUpdtr.setOverRideClr(true);
      DtPrcssLstnr aDtPrcssLstnr= new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
      addActionListener(aDtPrcssLstnr);
       
   }
}