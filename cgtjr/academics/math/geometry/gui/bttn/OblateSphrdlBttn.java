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
import cgtjr.academics.math.geometry.dmnsvar.OblateSphrdlVar;
import java.applet.Applet;

/**
 *
 * @author clayton g thomas jr
 */
public class OblateSphrdlBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   public OblateSphrdlBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      String aFileInfo = "./data/images/shapes/oblatesphrdl.gif";
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   public OblateSphrdlBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/oblatesphrdl.gif",myLabWndwPnl);
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
      GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new OblateSphrdlVar(),scnRt);
      DtPrcssLstnr aDtPrcssLstnr = new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
      addActionListener(aDtPrcssLstnr);      
   }
}