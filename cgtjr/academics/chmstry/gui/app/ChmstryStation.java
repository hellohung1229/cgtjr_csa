/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.chmstry.gui.app;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.g3d.*;
import javax.swing.JApplet;
/**
 *
 * @author clayton g thomas jr
 */
public class ChmstryStation extends LabStation
{
   private static ShapeG3D shpG3D;

   public ChmstryStation(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public ChmstryStation(SceneRoot mySceneRoot,JApplet myLabWndwPnl)
   {
      super(mySceneRoot,myLabWndwPnl);
   }
   public static ShapeG3D getShpG3D()
   {
      return shpG3D;
   }
   public static void setShpG3D(ShapeG3D myShpG3D)
   {
      shpG3D = myShpG3D;
   }
}
