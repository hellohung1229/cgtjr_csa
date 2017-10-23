package cgtjr.academics.general.loaders;

import cgtjr.academics.general.LabStation;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.SceneBase;
import java.applet.Applet;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Box;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.URL;
import javax.media.j3d.*;
import javax.vecmath.*;

public class ImageDataLoader implements Loader {

    private java.net.URL texImage = null;
    private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {
        // Create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();
        objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ );
        objRoot.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE );
        // Create the transform group node and initialize it to the
        // identity.  Enable the TRANSFORM_WRITE capability so that
        // our behavior code can modify it at runtime.  Add it to the
        // root of the subgraph.
        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(BranchGroup.ALLOW_CHILDREN_READ );
        objTrans.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE );
        
        objRoot.addChild(objTrans);

        // Create appearance object for textured cube
        Appearance app = new Appearance();
        app.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        app.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        app.setCapability(Appearance.ALLOW_TEXTURE_READ);
        app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
      
        Applet anApplet = LabStation.getLabWndwPnl();
        Texture tex = new TextureLoader(texImage, anApplet).getTexture();
        tex.setCapability(Texture.ALLOW_IMAGE_READ);
        tex.setCapability(Appearance.ALLOW_TEXTURE_READ);
        
        app.setTexture(tex);
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        texAttr.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);

        app.setTextureAttributes(texAttr);

        // Create textured cube and add it to the scene graph.
        Box textureCube = new Box(0.4f, 0.4f, 0.4f,
                Box.GENERATE_TEXTURE_COORDS, app);
        objTrans.addChild(textureCube);

        // Create a new Behavior object that will perform the desired
        // operation on the specified transform object and add it into
        // the scene graph.
        Transform3D yAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE,
                0, 0,
                4000, 0, 0,
                0, 0, 0);

        RotationInterpolator rotator =
                new RotationInterpolator(rotationAlpha, objTrans, yAxis,
                0.0f, (float) Math.PI * 2.0f);
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        rotator.setSchedulingBounds(bounds);
        objTrans.addChild(rotator);

        // Have Java 3D perform optimizations on this scene graph.
        //objRoot.compile();
        return objRoot;
    }

    public ImageDataLoader() {
    }

    public ImageDataLoader(java.net.URL url) {
        texImage = url;
    }

    public void setURL(java.net.URL myURL) {
        texImage = myURL;
    }

    /*
     public void init() {
     if (texImage == null) {
     // the path to the image for an applet
     try {
     texImage = new java.net.URL(getCodeBase().toString() +
     "../images/stone.jpg");
     }
     catch (java.net.MalformedURLException ex) {
     System.out.println(ex.getMessage());
     System.exit(1);
     }
     }
     setLayout(new BorderLayout());
     GraphicsConfiguration config =
     SimpleUniverse.getPreferredConfiguration();

     Canvas3D c = new Canvas3D(config);
     add("Center", c);

     // Create a simple scene and attach it to the virtual universe
     BranchGroup scene = createSceneGraph();
     u = new SimpleUniverse(c);

     // This will move the ViewPlatform back a bit so the
     // objects in the scene can be viewed.
     u.getViewingPlatform().setNominalViewingTransform();

     u.addBranchGraph(scene);
     }
     */
    public void destroy() {
        u.cleanup();
    }

    //
    // The following allows TextureImage to be run as an application
    // as well as an applet
    //
    /*
     public static void main(String[] args) {
     java.net.URL url = null;
     if (args.length > 0) {
     try {
     url = new java.net.URL("file:" + args[0]);
     }
     catch (java.net.MalformedURLException ex) {
     System.out.println(ex.getMessage());
     System.exit(1);
     }
     }
     else {
     // the path to the image for an application
     try {
     url = new java.net.URL("file:../images/stone.jpg");
     }
     catch (java.net.MalformedURLException ex) {
     System.out.println(ex.getMessage());
     System.exit(1);
     }
     }
     new MainFrame(new TextureImage(url), 256, 256);
     }*/
    public Scene load(URL myURL) {
        setURL(myURL);
        return loadscene();
    }

    public Scene loadscene() {
        SceneBase aSceneBase = new SceneBase();
        BranchGroup objRoot = createSceneGraph();
        aSceneBase.setSceneGroup(objRoot);
        return aSceneBase;
    }

    @Override
    public Scene load(String string) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Scene load(Reader reader) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBaseUrl(URL url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBasePath(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public URL getBaseUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBasePath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFlags(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFlags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
