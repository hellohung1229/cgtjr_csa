/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
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


public class ScnLghtng
{
  public static BranchGroup crtLghtng() {

    BranchGroup objRoot = new BranchGroup();
    objRoot.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);
    Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
    DirectionalLight light1 = new DirectionalLight(light1Color,
        light1Direction);
    light1.setInfluencingBounds(bounds);
    objRoot.addChild(light1);

    PointLight pointLightNode1 = new PointLight();
    pointLightNode1.setPosition(100,4,4);
    pointLightNode1.setInfluencingBounds(bounds);
    objRoot.addChild(pointLightNode1);

    PointLight pointLightNode2 = new PointLight();
    pointLightNode2.setPosition(-100,4,4);
    pointLightNode2.setInfluencingBounds(bounds);
    objRoot.addChild(pointLightNode2);

    return objRoot;
  }
}
