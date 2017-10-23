/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.math.geometry.shapeprcss.GmtryGrpUpdtr;
import cgtjr.academics.general.ImageTool;
import javax.swing.*;
import java.awt.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.ConfocalEllpsdlVar;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class ConfocalEllpsdlBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   public ConfocalEllpsdlBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      String aFileInfo = "./data/images/shapes/cylinder3D.gif";
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   public ConfocalEllpsdlBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/cylinder3D.gif",myLabWndwPnl);
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   private void constructor()
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
      GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new ConfocalEllpsdlVar(),scnRt);
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
      addActionListener(aDtPrcssLstnr);      
   }
}