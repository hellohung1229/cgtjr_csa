package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.math.geometry.linepnts.Line;
import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import com.sun.j3d.loaders.*;
import java.net.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Cylinder;
import cgtjr.academics.math.geometry.*;
import java.awt.Color;

public class PDBDataLoader_2 implements Loader
{

   private static final float smallRadius  = .05f; 
   private static final float mediumRadius = .1f;
   //private static final float largeRadius  = .35f;
   private static final float largeRadius  = .5f;
   private float sphereRadius   = largeRadius;
   private float cylinderRadius = smallRadius;
   private String theFileName;

   private PDBMoleculeFile thePDBMoleculeFile;
   private MoleculeModel thePDBMolecule;
   private PDBAtom thePDBAtom;
   private TransformGroup objTrans;
   private TransformGroup objCylTrans;
   private int flags; 

   public static float getSmallRadius()
   {
      return smallRadius;
   }
   public static float getMediumRadius()
   {
      return mediumRadius;
   }
   public static float getLargeRadius()
   {
      return largeRadius;
   }
   public void setSphereRadius(float mySphereRadius)
   {
      sphereRadius = mySphereRadius;
   }
   public void setCylinderRadius(float myCylinderRadius)
   {
      cylinderRadius = myCylinderRadius;
   }
   public Scene load()
   {
      return load(theFileName);
   }
   public Scene load(String fileName)
   {
      return load(fileName,getLargeRadius(),getSmallRadius());
   }
   public Scene load(float mySphereRadius,float myCylinderRadius)
   {
      return load(theFileName,mySphereRadius,myCylinderRadius);
   }
   public Scene load(String fileName,float mySphereRadius,float myCylinderRadius)
   {
      theFileName = fileName;
      SceneBase aSceneBase = new SceneBase();
      BranchGroup objRoot = new BranchGroup();
      Sphere sphere = new Sphere(sphereRadius);
      Cylinder aCylinder = null;
      Transform3D tran1 = new Transform3D();
      Transform3D tran2 = new Transform3D();
      Transform3D rot = new Transform3D();
      Transform3D rotx = new Transform3D();
      Transform3D roty = new Transform3D();
      Transform3D rotz = new Transform3D();

      /*
      BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
	   					   100.0);
      Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
      AmbientLight ambientLightNode = new AmbientLight(ambientColor);
      ambientLightNode.setInfluencingBounds(bounds);
      objRoot.addChild(ambientLightNode);

      Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
      Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
      DirectionalLight light1 = new DirectionalLight(light1Color,light1Direction);
      light1.setInfluencingBounds(bounds);
      
      objRoot.addChild(light1);
      */
      objRoot.addChild(objTrans);

      float xCoordf = 0.0f; 
      float yCoordf = 0.0f;
      float zCoordf = 0.0f;
         
      thePDBMoleculeFile = new PDBMoleculeFile();

      thePDBMolecule = thePDBMoleculeFile.readFile(fileName);
      //System.out.println("PDBDataLoader.readFile() : size = "+ thePDBMolecule.getAdjacentVertices().size());
      Line aLine = null;

      Enumeration anEnumeration1 = thePDBMolecule.rtrvEnumeration();
      while(anEnumeration1.hasMoreElements())
      {
         MoleculeModel aPDBMolecule1 = (MoleculeModel)anEnumeration1.nextElement();
         Enumeration anEnumeration2 = thePDBMolecule.rtrvEnumeration();
         boolean aConnectStatus = thePDBMoleculeFile.getConnectStatus();
         Enumeration lineEnumeration = aPDBMolecule1.processConnections(aConnectStatus);
            while(lineEnumeration.hasMoreElements())
            {
               aLine = (Line)lineEnumeration.nextElement();
               float aHeight = (float) aLine.getLength();///10;
               float pointX1 = (float)aLine.getX1();
               float pointY1 = (float)aLine.getY1();
               float pointZ1 = (float)aLine.getZ1();
               float pointX2 = (float)aLine.getX2();
               float pointY2 = (float)aLine.getY2();
               float pointZ2 = (float)aLine.getZ2();

               float midXPoint = (float) aLine.getMidPoint().getX1();
               float midYPoint = (float) aLine.getMidPoint().getY1();
               float midZPoint = (float) aLine.getMidPoint().getZ1();
               float aXAngle = (float) aLine.getXAngle();
               float aYAngle = (float) aLine.getYAngle();
               float aZAngle = (float) aLine.getZAngle((float)Math.PI/2);
            
               //System.out.println("PDBDataLoader.load() : Cylinder height="+aHeight+",x="+midXPoint+",y="+midYPoint+",z="+midZPoint
               //                 +"x angle="+aXAngle+"y angle="+aYAngle+" z angle="+aZAngle);
            
               aCylinder = new Cylinder(myCylinderRadius,aHeight);

               //TransformGroup objCylRot  = new TransformGroup();

               //TransformGroup objCylRotx = new TransformGroup();
               //TransformGroup objCylRotz = new TransformGroup();

               Vector3f axis = new Vector3f();
               Vector3f direction = new Vector3f();

               direction = new Vector3f(); 
               direction.sub(new Point3f(pointX1,pointY1,pointZ1),new Point3f(pointX2,pointY2,pointZ2)); 
               direction.normalize(); 

               axis.cross(new Vector3f(0,1,0),direction); 

               tran1.setTranslation(new Vector3f(midXPoint,midYPoint,midZPoint));
               tran1.setRotation(new AxisAngle4f(axis.x,axis.y,axis.z,(float)Math.acos(direction.y))); 

               objCylTrans = new TransformGroup();
     
               objCylTrans.addChild(aCylinder);
               objCylTrans.setTransform(tran1);
               
               objRoot.addChild(objCylTrans);
            }   
      }
	Material m2 = null;
	Appearance a2 = null;
      Color aColor = null;
      Color3f ambientColor3f = new Color3f(0.0f,0.0f,0.0f);
      Color3f emmisiveColor3f = new Color3f(0.0f,0.0f,0.0f);
      Color3f specularColor3f = new Color3f(1.0f,1.0f,1.0f);
      Color3f diffuseColor3f = new Color3f(0.0f,0.0f,0.0f);
      float aColorFloat[] = new float[3];

      Enumeration anEnumeration3 = thePDBMolecule.rtrvEnumeration();

         while(anEnumeration3.hasMoreElements())
         {
            MoleculeModel aPDBMolecule = (MoleculeModel)anEnumeration3.nextElement();
            Enumeration anEnumeration2 = aPDBMolecule.rtrvEnumeration();
            while(anEnumeration2.hasMoreElements())
            {
               thePDBAtom = (PDBAtom)anEnumeration2.nextElement();
               xCoordf = Float.parseFloat(thePDBAtom.getXCoordinate());///10f;
               yCoordf = Float.parseFloat(thePDBAtom.getYCoordinate());///10f;
               zCoordf = Float.parseFloat(thePDBAtom.getZCoordinate());///10f;
               aColor = new Color(thePDBAtom.getColor());
               aColorFloat = aColor.getRGBComponents(null);
               System.out.println("PDBDataLoader.load() : atom = "+thePDBAtom.getClass().getName()+" color = "+thePDBAtom.getColor());
               
               diffuseColor3f = new Color3f(new Color(thePDBAtom.getColor()));               
               ambientColor3f = new Color3f(new Color(thePDBAtom.getColor()));               
               emmisiveColor3f = new Color3f(new Color(thePDBAtom.getColor()));               
               diffuseColor3f = new Color3f(new Color(thePDBAtom.getColor()));               
               m2 = new Material(ambientColor3f , emmisiveColor3f , diffuseColor3f , specularColor3f, 100.0f);
               
               a2 = new Appearance();
               
               thePDBAtom.setRadius(mySphereRadius);
               //a2 = GeneralAppearance.createAppearance(aColorFloat[0],aColorFloat[1],aColorFloat[2]);
               
               //System.out.println("new Vector3f = "+xCoordf+","+yCoordf+","+zCoordf);
               tran2 = new Transform3D();
               tran2.setTranslation(new Vector3f(xCoordf,yCoordf,zCoordf));
               sphere = new Sphere(mySphereRadius);
               sphere.setAppearance(a2);
               objTrans = new TransformGroup();
               objTrans.setTransform(tran2);
               objTrans.addChild(sphere);
               objRoot.addChild(objTrans);
            }
         }
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
}