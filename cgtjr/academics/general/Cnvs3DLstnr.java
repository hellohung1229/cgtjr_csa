/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

import java.awt.event.*;
/**
 *
 * @author clayton g thomas jr
 */
public class Cnvs3DLstnr implements MouseListener
{
   public void mouseClicked(MouseEvent e) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }
   public void mousePressed(MouseEvent e) {
      int xPos = e.getX();
      int yPos = e.getY();
      System.out.println("Cnvs3DLstnr:  x = "+xPos+",y = "+yPos);
   }
   public void mouseReleased(MouseEvent e) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }
   public void mouseEntered(MouseEvent e) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }
   public void mouseExited(MouseEvent e) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }
}