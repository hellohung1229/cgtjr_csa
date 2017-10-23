package cgtjr.academics.elctrclengnrng.cv.houghtransform;


import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.net.*;


public class HoughDisplay extends Applet {
  
    private java.net.URL texImage = null;

    private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {
	BranchGroup objRoot = new BranchGroup();

	TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objRoot.addChild(objTrans);

	Appearance app = new Appearance();
      URL base = null;
      try{
         base = getDocumentBase(); 
      }catch(Exception e) {}
      Image theImage1 = getImage(base,"data/images/shapes/square1.jpg"); 
      Image anImage = performFilter(theImage1);

	Texture tex = new TextureLoader(anImage , this).getTexture();

      //Texture tex = new TextureLoader(texImage, this).getTexture();
	app.setTexture(tex);
	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.MODULATE);
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
				     0.0f, (float) Math.PI*2.0f);
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	rotator.setSchedulingBounds(bounds);
	objTrans.addChild(rotator);

        // Have Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    }

    public HoughDisplay() {
    }

    public HoughDisplay(java.net.URL url) {
        texImage = url;
    }
    public void init() {
        if (texImage == null) {
  	    // the path to the image for an applet
          /*
  	    try {
	        //texImage = new java.net.URL(getCodeBase().toString() +
	        texImage = new java.net.URL("data/images/stones/stone.jpg");
              System.out.println("getCodeBase().toString() = "+getCodeBase().toString());
	    }
	    catch (java.net.MalformedURLException ex) {
	      System.out.println(ex.getMessage());
		System.exit(1);
	    }*/
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

    public void destroy() {
	u.cleanup();
    }

    public Image performFilter(Image myImageFileName)
    {
       //GenericFilter aGenericFilter = new GenericFilter(new MedianKernel());
       //aGenericFilter.setImage(myImageFileName);
       //Image anImage1 = aGenericFilter.performFiltering();
       
       /*Note ... this code needs modification !!!!
       GradientFilter aGradientFilter = new GradientFilter(new SobelKernal());
       aGradientFilter.setImage(myImageFileName);
       Image anImage2 = aGradientFilter.performFiltering();
       */
        
       //HoughKerman aHoughKerman = new HoughKerman(anImage2);
       
       //return anImage2;
        return null;
    }
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
	else{
	    // the path to the image for an application
          /*
	    try {
	        url = new java.net.URL("file:data/images/people/Lena1.jpg");
	    }
	    catch (java.net.MalformedURLException ex) {
	        System.out.println(ex.getMessage());
		System.exit(1);
	    }*/
	}
	new MainFrame(new HoughDisplay(), 256, 256);
    }
}