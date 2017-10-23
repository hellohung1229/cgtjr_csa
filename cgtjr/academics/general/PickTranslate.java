/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import javax.media.j3d.*;
import com.sun.j3d.utils.picking.behaviors.*;

/**
 *
 * @author clayton g thomas jr
 */

public class PickTranslate implements PickingCallback
{
   Canvas3D cnvs3D;
   Shape3D shp3D;
   BranchGroup brnchGrp;

   public PickTranslate(Canvas3D myCanvas3D,Shape3D myShape3D)
   {
      cnvs3D = myCanvas3D;
      shp3D = myShape3D;
      //brnchGrp = myBranchGroup;
   }
   /*
   public BranchGroup rtrvPckTrnsltBG(Canvas3D myCanvas3D,BranchGroup myBranchGroup)
   {
      //myShape3D.setPickable(true);
      BranchGroup objRoot = new BranchGroup();
      TransformGroup objRotate = null;
      PickTranslateBehavior pickTranslate = null;
      Transform3D transform = new Transform3D();
      BoundingSphere behaveBounds = new BoundingSphere(new Point3d(0,0,0),100);
      //transform.setTranslation(new Vector3f(0.5f, 0.5f, 0.0f));

      objRoot.addChild(myBranchGroup);
      pickTranslate = new PickTranslateBehavior(objRoot,myCanvas3D, behaveBounds);
      pickTranslate.setupCallback(this);
      objRoot.addChild(pickTranslate);
      //objRoot.compile();
      return objRoot;
   }*/
   public BranchGroup rtrvBrnchGrp()
   {
      return rtrvPckTrnsltBG(cnvs3D,shp3D);
   }
   public BranchGroup rtrvPckTrnsltBG(Canvas3D myCanvas3D,Shape3D myShape3D)
   {
      BranchGroup objRoot = new BranchGroup();
      TransformGroup objRotate = null;
      PickTranslateBehavior pickRotate = null;
      Transform3D transform = new Transform3D();
      BoundingSphere behaveBounds = new BoundingSphere();
      //transform.setTranslation(new Vector3f(-0.6f, 0.0f, -0.6f));
      objRotate = new TransformGroup(transform);
      objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
      objRotate.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
      objRoot.addChild(objRotate);
      objRotate.addChild(myShape3D);
      pickRotate = new PickTranslateBehavior(objRoot,myCanvas3D, behaveBounds);
      pickRotate.setupCallback(this);
      objRoot.addChild(pickRotate);
      //transform.setTranslation(new Vector3f( 0.6f, 0.0f, -0.6f));
      objRoot.compile();
      return objRoot;
   }
   public void transformChanged(int type, TransformGroup tg)
   {
      System.out.println("PickTranlate ... type = "+type);
      if(type == 1)
      {
         Transform3D aTransform3D = new Transform3D();
         tg.getTransform(aTransform3D);
      }      
   }

}