/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general.gui;

import cgtjr.academics.general.IntnstyNmbrVar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author clayton g thomas jr
 */
public class IntnstyClrPnl extends JPanel
{
   IntnstyNmbrVar clrRngVar;
   Color maxColor = Color.gray;
   Color minColor = Color.gray;

   public IntnstyClrPnl()
   {
      //setSize(100,100);
      //setVisible(true);
   }
   public void paintComponent(Graphics myGraphics)
   {
      super.paintComponent(myGraphics);
      
      Graphics2D g2d = (Graphics2D) myGraphics;

      //Rectangle2D.Double aShape = new Rectangle2D.Double(0,0,230,100);
      //GradientPaint aGradientPaint = new GradientPaint(0,100,minColor,0,0,maxColor,true);
      //g2d.setPaint(aGradientPaint);
      //g2d.fill(aShape);
      int pixelY = 0;
      for(float i = 1;i>=0;i=i-.01f){
          g2d.setColor(Color.getHSBColor(i, .300f, .700f));
          g2d.drawLine(80,pixelY,120,pixelY);
          pixelY++;
      }
   }
   public void setMaxColor(Color myColor)
   {
      maxColor = myColor;
   }
   public void setMinColor(Color myColor)
   {
      minColor = myColor;
   }
   public Dimension getMinimumSize()
   {
      return getPreferredSize();
   }
   public Dimension getPreferredSize()
   {
      return new Dimension(300,200);
   }
}