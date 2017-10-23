/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.sfs.gui.app;

import cgtjr.academics.general.LabStation;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.g3d.ShapeG3D;
import javax.swing.JApplet;
/**
 *
 * @author clayton g thomas jr
 */
public class SFSStation extends LabStation
{
   private static ShapeG3D shpG3D;

   public SFSStation(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
   }
   public SFSStation(SceneRoot mySceneRoot,JApplet myLabWndwPnl)
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
