/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem.gui.pnl;

import cgtjr.academics.elctrclengnrng.fem.SparseMtrxVar;
import cgtjr.academics.general.ArrayTool;
import cgtjr.academics.general.ImageTool;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author clayton g thomas jr
 */
public class SprsMtrxImgPnl extends JPanel
{
   public SprsMtrxImgPnl()
   {

   }
   public void paintComponent(Graphics myGraphics)
   {
      setBackground(Color.white);
      super.paintComponent(myGraphics);    
      Graphics2D g2d = (Graphics2D) myGraphics;

      SparseMtrxVar sprsMtrxVar = (SparseMtrxVar)SparseMtrxDflt.getDfltVar();
      double aMatrix[][] = sprsMtrxVar.getSprsMtrx();
      //Matrix.print(aMatrix);      
      int aHeight = aMatrix.length;
      int aWidth = aMatrix[0].length;      
      int myPxlData[] = ArrayTool.rtrv1DIntArry(aMatrix,.00001); 
      //System.out.println("SprsMtrxImgPnl: width = "+aWidth+", height = "+aHeight);
      //System.out.println("SprsMtrxImgPnl: bandwidth = "+sprsMtrxVar.updteBandWidthVal());
      BufferedImage anImage = ImageTool.rtrvImage(myPxlData, aWidth, aHeight);
      
      g2d.drawImage(anImage,0,0,null);
   }
   public Dimension getMinimumSize()
   {
      return getPreferredSize();
   }
   public Dimension getPreferredSize()
   {
      return new Dimension(300,600);
   }
}