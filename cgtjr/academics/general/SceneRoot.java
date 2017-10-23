package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.g3d.AxisShape3D;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;


import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.GraphicsConfiguration;
import java.io.IOException;
import javax.media.j3d.*;
import javax.swing.JPanel;
import javax.vecmath.Point3d;

/**
 *
 * @author clayton g thomas jr
 */
public class SceneRoot {

    private BranchGroup sceneGroup;
    private BranchGroup scnRtTGBG;
    private BranchGroup sceneRootBG;
    private BranchGroup axisBG;
    private TransformGroup mouseTG;
    private SimpleUniverse u;
    private TransformGroup platformTransform;
    private Canvas3D scnCnvs3D;
    private VirtualInputDevice vrtlInptDvc;
    private BranchGroup shpBrnchGrp;
    //private BranchGroup fileBrnchGrp;
    private boolean slctnMd;
    //private BranchGroup mouseBG;
    private TransformGroup sceneTG;
    private ViewingPlatform viewingPlatform;
    private boolean isPickRotatable = true;
    private AxisShape3D axis3D;

    /**
     *
     */
    public SceneRoot() {
        ViewingPlatform viewingPlatform;
        axis3D = new AxisShape3D();
        
        scnRtTGBG = new BranchGroup();
        scnRtTGBG.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        scnRtTGBG.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        scnRtTGBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        scnRtTGBG.setCapability(BranchGroup.ALLOW_DETACH);
       

        axisBG = new BranchGroup();     
        axisBG.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        axisBG.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        axisBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        axisBG.setCapability(BranchGroup.ALLOW_DETACH);
        
        sceneRootBG = new BranchGroup();
        sceneRootBG.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        sceneRootBG.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        sceneRootBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        sceneRootBG.setCapability(BranchGroup.ALLOW_DETACH);
        sceneRootBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        sceneRootBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        sceneRootBG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        vrtlInptDvc = new VirtualInputDevice();
        shpBrnchGrp = new BranchGroup();
        //fileBrnchGrp = new BranchGroup();
        sceneTG = new TransformGroup();
        sceneRootBG.addChild(sceneTG);

        shpBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        shpBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        shpBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        shpBrnchGrp.setCapability(BranchGroup.ALLOW_DETACH);
        //fileBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        //fileBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        //fileBrnchGrp.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        sceneTG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        sceneTG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        sceneTG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        sceneTG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        sceneTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        sceneTG.setCapability(BranchGroup.ALLOW_DETACH);

        vrtlInptDvc.initialize();

        crtScnCnvs3D();
        crtScene();
        //crtMsRtt();
        sceneTG.addChild(scnRtTGBG);
        axisBG.addChild(axis3D);
        scnRtTGBG.addChild(axisBG);
        //platformTransform.setTransform(ViewPort.rtrvViewpoint());
    }

    /**
     *
     * @param myDevice
     */
    public SceneRoot(VirtualInputDevice myDevice) {
        vrtlInptDvc = myDevice;
        shpBrnchGrp = new BranchGroup();
        //fileBrnchGrp = new BranchGroup();
        //crtMsRtt();
        crtScnCnvs3D();
        crtScene();
        //Transform3D t3d = new Transform3D();

        //platformTransform.setTransform(ViewPort.rtrvViewpoint());
    }

    public void setIsPickable(boolean mineIsPickable) {
        isPickRotatable = mineIsPickable;
    }

    /**
     *
     */
    /*
     public void crtMsRtt() {

     BoundingSphere bounds =
     new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

     mouseBG = new BranchGroup();
     mouseBG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
     mouseBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
     mouseBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
     mouseBG.setCapability(BranchGroup.ALLOW_DETACH);
     mouseTG = MsRtt.rtrvMsRtt(bounds);
     mouseBG.addChild(mouseTG);
     }*/
    /**
     *
     * @return
     */
    public boolean getSlctnMd() {
        return slctnMd;
    }

    /**
     *
     */
    public void crtScene() {
        crtScene(scnCnvs3D, vrtlInptDvc);
    }

    /**
     *
     * @param myCanvas3D
     * @param myDevice
     */
    public void crtScene(Canvas3D myCanvas3D, VirtualInputDevice myDevice) {
        myDevice.setShpeBranchGroup(shpBrnchGrp);
        
        TransformGroup objScaleTG = new TransformGroup();
        Transform3D t3d = new Transform3D();


        t3d.setScale(1.0);
        objScaleTG.setTransform(t3d);
        //shpBrnchGrp.addChild(objScaleTG);

        u = new SimpleUniverse(myCanvas3D);

        u.getViewer().getPhysicalEnvironment().addInputDevice(myDevice);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        viewingPlatform = u.getViewingPlatform();
        platformTransform = viewingPlatform.getViewPlatformTransform();

        MouseRotate behavior1 = new MouseRotate(platformTransform);
        sceneRootBG.addChild(behavior1);
        behavior1.setSchedulingBounds(bounds);

        //MouseZoom behavior2 = new MouseZoom(platformTransform);
        //sceneRootBG.addChild(behavior2);
        //behavior2.setSchedulingBounds(bounds);

        //MouseTranslate behavior3 = new MouseTranslate(platformTransform);
        //sceneRootBG.addChild(behavior3);
        //behavior3.setSchedulingBounds(bounds);  

        //SensorBehavior s = new SensorBehavior(platformTransform, myDevice.getSensor(0));
        SensorBehavior s = new SensorBehavior(sceneTG, myDevice.getSensor(0));
        s.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Float.MAX_VALUE));

        Background aBackgroundLeaf = BGNode.rtrvBGNode(bounds);
        scnRtTGBG.addChild(aBackgroundLeaf);

        scnRtTGBG.addChild(s);

        BranchGroup lightGroup = ScnLghtng.crtLghtng();
        scnRtTGBG.addChild(lightGroup);

        scnRtTGBG.addChild(shpBrnchGrp);
        //scnRtTGBG.addChild(fileBrnchGrp);

        View theView = u.getViewer().getView();
        theView.setBackClipDistance(1000);

        //u.addBranchGraph(scnRtTGBG);
        u.addBranchGraph(sceneRootBG);
    }

    /**
     *
     * @param myNode
     */
    public void addShp3D(Node myNode) {
        if (isPickRotatable == true) {
            PickRotate aPickRotate = new PickRotate(scnCnvs3D, myNode);
            BranchGroup pickRotateBG = aPickRotate.rtrvBrnchGrp();
            pickRotateBG.setCapability(BranchGroup.ALLOW_DETACH);
            pickRotateBG.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
            pickRotateBG.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
            pickRotateBG.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
            pickRotateBG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            pickRotateBG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        
            //pickRotateBG.setName(null);
            shpBrnchGrp.addChild(pickRotateBG);
        } else {
            BranchGroup aBranchGroup2 = new BranchGroup();
            aBranchGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            aBranchGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            aBranchGroup2.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
            aBranchGroup2.addChild(myNode);
            shpBrnchGrp.addChild(aBranchGroup2);
        }
    }

    /**
     *
     * @param myNode
     */
    /**
     * public void addFileShp3D(Node myNode) { fileBrnchGrp.removeAllChildren();
     * fileBrnchGrp.addChild(myNode); }
     */
    /**
     *
     * @param loaderScene
     */
    public void renderScene(Scene loaderScene) {
        System.out.println("SceneRoot: test 8");
        if (sceneGroup != null) {
            //mouseBG.removeChild(sceneGroup);
        }
        System.out.println("SceneRoot: test 12");
        if (loaderScene.getSceneGroup() != null) {
            sceneGroup = loaderScene.getSceneGroup();
            System.out.println("SceneRoot: test 18");
            sceneGroup.setCapability(BranchGroup.ALLOW_DETACH);
            sceneGroup.setCapability(BranchGroup.ALLOW_BOUNDS_READ);
            sceneGroup.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            sceneGroup.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            //mouseBG.addChild(sceneGroup);
            //addFileShp3D(mouseBG);
            addShp3D(sceneGroup);
        }
    }

    /**
     *
     * @param myFileName
     */
    public void loadScene(String myFileName) {
        Scene aScene = null;
        System.out.println("SceneRoot: test1");
        Loader aLoader = DataLdrs.rtrvDtLdr(myFileName);
        System.out.println("SceneRoot: test3");
        //java.net.URL path = "";//GmtryApplt.getCodeBaseURL();
        
        java.net.URL filename = null;
        System.out.println("SceneRoot.loadScene: filename = " + myFileName);
        try {
            if ((myFileName != null && myFileName.toLowerCase().startsWith("http:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("https:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("file:"))
                    ) {
                filename = new java.net.URL(myFileName);
            }else{
                filename = this.getClass().getClassLoader().getResource(myFileName);
            }
            System.out.println("SceneRoot: test 7: filename = " + filename.toString());
            //aScene = aLoader.load(myFileName);
            aScene = aLoader.load(filename);
        } catch (IOException io) {
            System.err.println("SceneRoot.loadScene(): file error = " + myFileName);
        }
        renderScene(aScene);

        /**
         *
         * @return
         */
    }

    public Canvas3D crtScnCnvs3D() {
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();
        scnCnvs3D = new Canvas3D(config);
        return scnCnvs3D;
    }

    /**
     *
     * @return
     */
    public Canvas3D rtrvCanvas3D() {
        return scnCnvs3D;
    }

    /**
     *
     * @return
     */
    public JPanel rtrvVrtlDvcPnl() {
        JPanel aPanel = vrtlInptDvc.retrieveVirtualPanel();
        return aPanel;
    }
    /**
     *
     * @return
     */
    public BranchGroup getScnRtBG() {
        return scnRtTGBG;
    }
}