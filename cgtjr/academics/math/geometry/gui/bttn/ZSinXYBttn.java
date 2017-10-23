/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapeprcss.ZShpScnPrcss;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author clayton g thomas jr
 */
public class ZSinXYBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   public ZSinXYBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      String aFileInfo = "./data/images/shapes/hxhdrl3D.jpg";
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   public ZSinXYBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/trngl3D.jpg",myLabWndwPnl);
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   private void constructor()
   {
      Dimension aDimension = new Dimension(30,30);
      setIcon(anIcon);
      setMaximumSize(aDimension);
      setMinimumSize(aDimension);
      setPreferredSize(aDimension);      
   }
   public SceneRoot getScnRt()
   {
      return scnRt;
   }
   private void addActnLstnr()
   {       
      DmnsnVar aDmnsnVar = new DmnsnVar(0,0,0,-20,-20,0,20,20,0,0.01,0.01,1,0,0,0);
      ZShpScnPrcss aShpScnPrcss = new ZShpScnPrcss(aDmnsnVar,scnRt);
      DtPrcssLstnr aDtPrcssLstnr= new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aShpScnPrcss);
      addActionListener(aDtPrcssLstnr);
       
   }
}