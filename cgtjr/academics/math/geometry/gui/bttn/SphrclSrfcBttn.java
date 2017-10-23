/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapeprcss.ShpScnPrcss;
import cgtjr.academics.math.geometry.dmnsvar.SphrclVar;
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
public class SphrclSrfcBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   public SphrclSrfcBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      String aFileInfo = "./data/images/shapes/sphrclSrfc.jpg";
      anIcon = new ImageIcon(aFileInfo);
      constructor();
      addActnLstnr();
   }
   public SphrclSrfcBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/sphrclSrfc.jpg",myLabWndwPnl);
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
      DmnsnVar aSphrclVar = new SphrclVar(0,0,0,4,0,0,4,Math.PI,2*Math.PI,1,Math.PI/16,Math.PI/16,4,0,0);
      ShpScnPrcss aShpScnPrcss = new ShpScnPrcss(aSphrclVar,scnRt);
      DtPrcssLstnr aDtPrcssLstnr= new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aShpScnPrcss);
      addActionListener(aDtPrcssLstnr);      
   }
}