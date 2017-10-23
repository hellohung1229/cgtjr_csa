/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;
 
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.g3d.*;
/**
 *
 * @author clayton g thomas jr
 */
public class ImgPrcssngSttn extends LabStation
{
   private static ShapeG3D shpG3D;

   public ImgPrcssngSttn(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
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
