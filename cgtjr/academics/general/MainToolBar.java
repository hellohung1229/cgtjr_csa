/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import java.applet.Applet;
import java.awt.Image;
import javax.swing.*;
/**
 *
 * @author clayton g thomas jr
 */
public class MainToolBar extends JToolBar
{
   private JApplet labWndwPnl;

   private Icon newIcon;
   private Icon openIcon;
   private Icon saveIcon;
   private Icon copyIcon;
   private Icon pasteIcon;

   private JButton nwIcnBttn;
   private JButton opnIcnBttn;
   private JButton svIcnBttn;
   private JButton cpyIcnBttn;
   private JButton pstIcnBttn;

   private Image nwImage;
   private Image opnImage;
   private Image svImage;
   private Image pstImage;
   private Image cpyImage;

   public MainToolBar()
   {
      System.out.println("MainToolBar : load images");
      nwImage = ImageTool.rdImageFile("./data/images/toolbar/new.gif");
      opnImage = ImageTool.rdImageFile("./data/images/toolbar/open.gif");
      svImage = ImageTool.rdImageFile("./data/images/toolbar/save.gif");
      cpyImage = ImageTool.rdImageFile("./data/images/toolbar/copy.gif");
      pstImage = ImageTool.rdImageFile("./data/images/toolbar/paste.gif");
      initialize();
   }
   public MainToolBar(JApplet myLabWndwPnl)
   {
      labWndwPnl = myLabWndwPnl;
      //if(labWndwPnl != null && labWndwPnl.getDocumentBase() != null){
      //System.out.println("MainToolBar: test = null ... "+labWndwPnl.getDocumentBase());          
      //nwImage = ImageTool.rdImageFile("./data/images/toolbar/new.gif",labWndwPnl);
      //opnImage = ImageTool.rdImageFile("./data/images/toolbar/open.gif",labWndwPnl);
      //svImage = ImageTool.rdImageFile("./data/images/toolbar/save.gif",labWndwPnl);
      //cpyImage = ImageTool.rdImageFile("./data/images/toolbar/copy.gif",labWndwPnl);
      //pstImage = ImageTool.rdImageFile("./data/images/toolbar/paste.gif",labWndwPnl);
      //}else if(myLabWndwPnl == null){
          nwImage = ImageTool.rdImageFile("./data/images/toolbar/new.gif");
      opnImage = ImageTool.rdImageFile("./data/images/toolbar/open.gif");
      svImage = ImageTool.rdImageFile("./data/images/toolbar/save.gif");
      cpyImage = ImageTool.rdImageFile("./data/images/toolbar/copy.gif");
      pstImage = ImageTool.rdImageFile("./data/images/toolbar/paste.gif");          
      //}
      initialize();
   }
   public void initialize()
   {
      System.out.println("MainToolBar: test14");
      newIcon = new ImageIcon(nwImage);
      openIcon = new ImageIcon(opnImage);
      saveIcon = new ImageIcon(svImage);
      copyIcon = new ImageIcon(cpyImage);
      pasteIcon = new ImageIcon(pstImage);

      nwIcnBttn = new JButton(newIcon);
      opnIcnBttn = new JButton(openIcon);
      svIcnBttn = new JButton(saveIcon);
      cpyIcnBttn = new JButton(copyIcon);
      pstIcnBttn = new JButton(pasteIcon);

      add(nwIcnBttn);
      add(opnIcnBttn);
      add(svIcnBttn);
      add(cpyIcnBttn);
      add(pstIcnBttn);
   }
   public void setLabWndwPnl(JApplet myApplet)
   {
      labWndwPnl = myApplet;
   }
   public Applet getLabWndwPnl()
   {
     return labWndwPnl;
   }
}
