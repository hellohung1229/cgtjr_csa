/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.gui.bttn;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.DtPrcssLstnr;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.shapeprcss.GmtryGrpUpdtr;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The helix button class extends a JButton.  In addition to JButton it 
 * a default image provides.  An action listener is added to monitor user 
 * interaction with the button.  When the user clicks the button then the 
 * corresponding button, the helix mesh is generated/rendered and added 
 * to the scene root.
 * @author clayton g thomas jr
 */
public class HelixAutoBttn extends JButton
{
   private SceneRoot scnRt;
   private Icon anIcon;

   
   /**
    * The constructor instantiates a helix button class.
    * @param mySceneRoot represents SceneRoot object.
    */
   public HelixAutoBttn(SceneRoot mySceneRoot)
   {
      super();
      scnRt = mySceneRoot;
      //Toolkit tk = Toolkit.getDefaultToolkit();
      
      String aFileInfo = "/data/images/shapes/hlx1_3D_A.gif";

      //Image image = tk.createImage(aFileInfo);      
      
      //anIcon = new ImageIcon(aFileInfo);
      //anIcon = new ImageIcon(image);
      anIcon = new ImageIcon(getClass().getResource(aFileInfo));
      
      constructor();
      addActnLstnr();
   }
   /**
    * Instantiates a helix button.
    * @param mySceneRoot represents the scene root object
    * @param myLabWndwPnl Represents the applet container.
    */
   public HelixAutoBttn(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super();
      scnRt = mySceneRoot;
      Image aFileInfo = ImageTool.rdImageFile("./data/images/shapes/hlx1_3D_A.gif",myLabWndwPnl);
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
   /**
    * get scene root (SceneRoot) object. 
    * @return Scene Root (SceneRoot) object.
    */
   public SceneRoot getScnRt()
   {
      return scnRt;
   }
   public void addActnLstnr()
   {
      
      GmtryGrpUpdtr aGmtryGrpUpdtr = new GmtryGrpUpdtr(new HelixVar(),scnRt);
      DtPrcssLstnr aDtPrcssLstnr= new DtPrcssLstnr();
      aDtPrcssLstnr.setDtPrcss(aGmtryGrpUpdtr);
      addActionListener(aDtPrcssLstnr);
   }
}