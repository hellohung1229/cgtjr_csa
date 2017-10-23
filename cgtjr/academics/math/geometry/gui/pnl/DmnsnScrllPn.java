/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;

import javax.swing.*;
import java.awt.*;

import cgtjr.academics.general.*;
/**
 *
 * @author clayton g thomas jr
 */
public class DmnsnScrllPn extends JScrollPane
{
   public DmnsnScrllPn(SceneRoot mySceneRoot)
   {
      setLayout(new BorderLayout());
      DmnsnPnl aDmnsnPnl = new DmnsnPnl(mySceneRoot);
      add(aDmnsnPnl,BorderLayout.CENTER);
   }
}
