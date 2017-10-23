package cgtjr.academics.general.loaders;

import com.sun.j3d.loaders.*;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.*;
import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.*;
import cgtjr.academics.general.gui.*;
import java.net.*; 

/*
public class ImageDataLoaderTmp  implements Loader
{
   
   private String theFileName;
   private Image theInputImage;
   private MediaTracker mt; 
   private int flags; 
   private int aWidth;
   private int aHeight;
   private int aLength;

   private java.net.URL texImage = null;

   private SimpleUniverse u = null;
   private boolean filterFlag;

   public ImageDataLoaderTmp()
   {
   }
   public ImageDataLoaderTmp(String myFileName)
   {
      if(myFileName != null && myFileName.startsWith("file:"))
      {
         theFileName = myFileName;
      }else {
         theFileName = "file:"+myFileName;
      }
      theInputImage = UgotImage.createImage(theFileName);

      aWidth = theInputImage.getWidth(null);
      aHeight = theInputImage.getHeight(null);

      while(aWidth < 1 || aHeight < 1)
      {
         aWidth = theInputImage.getWidth(null);  
         aHeight = theInputImage.getHeight(null);
      }

      aLength = theInputImage.getWidth(null);

   }
   public void setFilterFlag(boolean aFilterFlag)
   {
      filterFlag = aFilterFlag;
   }
   public boolean getFilterFlag()
   {
      return filterFlag;
   }
   public void setImage(String myFileName)
   {
      if(myFileName != null && myFileName.startsWith("file:"))
      {
         theFileName = myFileName;
      }else {
         theFileName = "file:"+myFileName;
      }
      theInputImage = UgotImage.createImage(myFileName);

      aWidth = theInputImage.getWidth(null);
      aHeight = theInputImage.getHeight(null);
      while(aWidth < 1 || aHeight < 1)
      {
         aWidth = theInputImage.getWidth(null);  
         aHeight = theInputImage.getHeight(null);
      }
      aLength = theInputImage.getWidth(null);
   }
   public Image getImage()
   {
      return theInputImage;
   }
   public void setImage(Image myImage)
   {
      theInputImage = myImage;
   }
   public BranchGroup createSceneGraph() {
	BranchGroup objRoot = new BranchGroup();
      objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ );
      objRoot.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE );

	TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      objTrans.setCapability(BranchGroup.ALLOW_CHILDREN_READ );
      objTrans.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE );
	objRoot.addChild(objTrans);

	Appearance app = new Appearance();
      app.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
      app.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
      app.setCapability(Appearance.ALLOW_TEXTURE_READ);
      app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
      
      //Image anImage = performFilter(texImage);
      System.out.println("ImageDataLoader.createSceneGraph() : test 7 : flag = "+getFilterFlag());
      Image anImage = null;

      anImage = theInputImage;
      System.out.println("ImageDataLoader.createSceneGraph() : test 8 : flag = "+getFilterFlag());   
      if(anImage == null) System.out.println("ImageDataLoader : image = null");   
	Texture tex = new TextureLoader(anImage).getTexture();
      tex.setCapability(Texture.ALLOW_IMAGE_READ);
      tex.setCapability(Appearance.ALLOW_TEXTURE_READ);
      System.out.println("ImageDataLoader.createSceneGraph() : test 9 : flag = "+getFilterFlag());
      //Texture tex = new TextureLoader(texImage, this).getTexture();
	app.setTexture(tex);
	TextureAttributes texAttr = new TextureAttributes();
      texAttr.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
	texAttr.setTextureMode(TextureAttributes.MODULATE);
	app.setTextureAttributes(texAttr);
      System.out.println("ImageDataLoader.createSceneGraph() : test 10 : flag = "+getFilterFlag());
	// Create textured cube and add it to the scene graph.
	Box textureCube = new Box((float)aWidth/1000,(float)aHeight/1000,(float)aWidth/1000,
				  Box.GENERATE_TEXTURE_COORDS, app);
      textureCube.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
      textureCube.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      textureCube.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
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

	return objRoot;
    }

   public BranchGroup createBranchGroup()
   {
      BranchGroup aBranchGroup = new BranchGroup();
      aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ );
      Transform3D aTransTransform3D = new Transform3D();
      aTransTransform3D.setTranslation(new Vector3f(.7f,0f,0f));
      
      TransformGroup aTransTransform = new TransformGroup();
      TransformGroup aScaleTransform = new TransformGroup();
      TransformGroup aRotateTransform = new TransformGroup();
      
      aTransTransform.setTransform(aTransTransform3D);
      
      Texture tex = new TextureLoader(theInputImage,this).getTexture();
      Appearance anAppearance = retrieveAppearance();
      anAppearance.setTexture(tex);
	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.MODULATE);
	anAppearance.setTextureAttributes(texAttr);
      System.out.println("ImageDataLoader.createScene() aLength = "+(float)aLength/1000+" aHeight = "+(float)aHeight/1000+" aWidth = "+(float)aWidth/1000);
      Box aBox = new Box((float)aWidth/1000,(float)aHeight/1000,(float)aWidth/1000,anAppearance);
      aTransTransform.addChild(aBox);
      aBranchGroup.addChild(aTransTransform);
      return aBranchGroup;
   }
   public Appearance retrieveAppearance()
   {
      Material m2 = null;
      Appearance a2 = null;

      Color3f ambientColor3f = new Color3f(0.0f,0.0f,0.0f);
      Color3f emmisiveColor3f = new Color3f(0.0f,0.0f,0.0f);
      Color3f specularColor3f = new Color3f(1.0f,1.0f,1.0f);
      Color3f diffuseColor3f = new Color3f(0.0f,0.0f,0.0f);

      diffuseColor3f  = new Color3f(new Color(0x8a1e00));               
      ambientColor3f  = new Color3f(new Color(0x8a1e00));               
      emmisiveColor3f = new Color3f(new Color(0x8a1e00));               
      specularColor3f = new Color3f(new Color(0x8a1e00));               
      m2 = new Material(ambientColor3f , emmisiveColor3f , diffuseColor3f , specularColor3f, 100.0f);
      a2 = new Appearance();
      a2.setMaterial(m2);
      return a2;
   }
   public Scene load()
   {
      return load(theFileName);
   }
   public Scene load(String myFileName)
   {
      if(myFileName != null && myFileName.startsWith("file:"))
      {
         theFileName = myFileName;
      }else {
         theFileName = "file:"+myFileName;
      }
      setImage(theFileName);
      return loadscene();
   }
   public Scene load(Image myImage)
   {
      setImage(myImage);
      return loadscene();
   }
   public Scene loadscene()
   {
      SceneBase aSceneBase = new SceneBase();
      BranchGroup objRoot = createSceneGraph();
      aSceneBase.setSceneGroup(objRoot);
      return aSceneBase;
   }
   public String getBasePath() {return null;}
   public URL getBaseUrl() {return null;}
   public int getFlags() {return flags;}
   public Scene load(java.io.Reader reader){return null;}      
   public Scene load(java.net.URL url) {return null;}
   public void setBasePath(java.lang.String pathName) {}
   public void setBaseUrl(java.net.URL url){}
   public void setFlags(int myFlags) {flags = myFlags;}

   public void init() {
      if (texImage == null) {
	}
      setLayout(new BorderLayout());
      GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

      Canvas3D c = new Canvas3D(config);
      add("Center", c);
      BranchGroup scene = createSceneGraph();
      scene.compile();
      u = new SimpleUniverse(c);
      u.getViewingPlatform().setNominalViewingTransform();

      u.addBranchGraph(scene);
   }
   public void destroy() {
      u.cleanup();
   }
}*/