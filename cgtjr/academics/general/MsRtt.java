/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Matrix4d;

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

public class MsRtt
{
   public static TransformGroup rtrvMsRtt(BoundingSphere myBounds)
   {
      TransformGroup objTrans = new TransformGroup();
      objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
      objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
      objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
         //objTrans.setCapability(TransformGroup.ALLOW_APPEARANCE_READ);
         //objTrans.setCapability(TransformGroup.ALLOW_APPEARANCE_WRITE);
      //objTrans.setCapability(BranchGroup.ALLOW_DETACH);
      objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
      MouseRotate behavior = new MouseRotate(objTrans);
      objTrans.addChild(behavior);
      behavior.setSchedulingBounds(myBounds);
      /*
      BranchGroup aBranchGroup = new BranchGroup();
      aBranchGroup.addChild(objTrans);
      aBranchGroup.setCapability(aBranchGroup.ALLOW_CHILDREN_EXTEND);
      aBranchGroup.setCapability(aBranchGroup.ALLOW_CHILDREN_WRITE );
      aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      aBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
       */
      //aBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
      //aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      //aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
      return objTrans;
   }
}