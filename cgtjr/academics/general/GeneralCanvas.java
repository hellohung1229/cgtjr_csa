package cgtjr.academics.general;

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


public class GeneralCanvas extends Canvas3DUniverse
{
   /*
   private PDBDataLoader pdbloader;
   private ImageDataLoader imageloader;
   private String theFileName;   
   private Scene loaderScene;
   
   public GeneralCanvas(){}
   public GeneralCanvas(VirtualInputDevice myDevice) 
   {      
      super(myDevice);
   }
   public void loadScene(String myFileName)
   {
      theFileName = myFileName;
	try {
         if(myFileName.toLowerCase().endsWith(".pdb"))
         {      
            pdbloader = new PDBDataLoader();
	      loaderScene = pdbloader.load(myFileName);
         }else if(myFileName.toLowerCase().endsWith(".jpg")||
                  myFileName.toLowerCase().endsWith(".bmp")||
                  myFileName.toLowerCase().endsWith(".png")||
                  myFileName.toLowerCase().endsWith(".tif"))
         {
            imageloader = new ImageDataLoader(myFileName);
            imageloader.setFilterFlag(false);
	      loaderScene = imageloader.load(myFileName);
         }

	}
	catch (Exception e) {
	   e.printStackTrace();
	}
      renderScene(loaderScene);
    }
    public void loadScene()
    {
       loadScene(theFileName);
    }
    public void loadScene(float mySphereRadius,float myCylinderRadius)
    {
      pdbloader = new PDBDataLoader();
      
	try {
	   loaderScene = pdbloader.load(theFileName,mySphereRadius,myCylinderRadius);
	}
	catch (Exception e) {
	   e.printStackTrace();
	}
      renderScene(loaderScene);      
    }
    public void reLoadScene()
    {
      pdbloader = new PDBDataLoader();
	try {
	   loaderScene = pdbloader.load(theFileName);
	}
	catch (Exception e) {
	   e.printStackTrace();
	}
    }
    public void reLoadScene(Image myImage)
    {
	try {
            imageloader = new ImageDataLoader();
	      loaderScene = imageloader.load(myImage);
	}catch (Exception e) {
	   e.printStackTrace();
	}
      renderScene(loaderScene);
    }
    public String retrieveFileName()
    {
       System.out.println("GeneralCanvas : filename = "+theFileName);
       return theFileName;
    }*/
}