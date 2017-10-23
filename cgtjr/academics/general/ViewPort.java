/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */

import com.sun.j3d.loaders.lw3d.Lw3dLoader;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.Cylinder;

public class ViewPort
{
   public static Transform3D rtrvViewpoint() {
      Transform3D viewTrans = new Transform3D();
      Transform3D eyeTrans = new Transform3D();
      Vector3f pos = new Vector3f(0,0,10);
      eyeTrans.set(pos);
      viewTrans.mul(eyeTrans);
      return viewTrans;
   }
}