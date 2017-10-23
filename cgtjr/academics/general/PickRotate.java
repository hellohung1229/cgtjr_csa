/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.picking.behaviors.*;

/**
 *
 * @author clayton g thomas jr
 */
public class PickRotate implements PickingCallback {

    Canvas3D cnvs3D;
    Node shp3D;

    public PickRotate(Canvas3D myCanvas3D, Node myNode) {
        cnvs3D = myCanvas3D;
        shp3D = myNode;
    }

    public BranchGroup rtrvBrnchGrp() {
        return rtrvPckRttBG(cnvs3D, shp3D);
    }

    public BranchGroup rtrvPckRttBG(Canvas3D myCanvas3D, Node myNode) {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup objNodeTG = null;
        PickRotateBehavior pickRotateBhvr = null;
        PickTranslateBehavior pickTranslateBhvr = null;
        Transform3D transform = new Transform3D();
        BoundingSphere behaveBounds = new BoundingSphere();
        //transform.setTranslation(new Vector3f(-0.6f, 0.0f, -0.6f));
        objNodeTG = new TransformGroup(transform);
        objNodeTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objNodeTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objNodeTG.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        objRoot.addChild(objNodeTG);
        objNodeTG.addChild(myNode);
        pickRotateBhvr = new PickRotateBehavior(objRoot, myCanvas3D, behaveBounds);
        objRoot.addChild(pickRotateBhvr);
        pickTranslateBhvr = new PickTranslateBehavior(objRoot, myCanvas3D, behaveBounds);
        objRoot.addChild(pickTranslateBhvr);
        PckSlctBhvr aPckSlctBhvr = new PckSlctBhvr(myCanvas3D, objRoot, behaveBounds);
        objRoot.addChild(aPckSlctBhvr);
        pickRotateBhvr.setupCallback(this);
        pickTranslateBhvr.setupCallback(this);

        //transform.setTranslation(new Vector3f( 0.6f, 0.0f, -0.6f));
        //objRoot.compile();
        return objRoot;
    }

    public void transformChanged(int type, TransformGroup tg) {
        System.out.println("PickRotate ... type = " + type);
        if (type == 1) {
            System.out.println("PIckRotate: test 1a");
            Transform3D aTransform3D = new Transform3D();
            System.out.println("PIckRotate: test 1b");
            tg.getTransform(aTransform3D);
            System.out.println("PIckRotate: test 1c");
            Vector3d aVector3d = new Vector3d();
            System.out.println("PIckRotate: test 1d");
            aTransform3D.get(aVector3d);
        }
        //System.out.println("PickRotate: "+aVector3d.toString());

    }
}