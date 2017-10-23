package cgtjr.academics.general;

import cgtjr.academics.math.geometry.gui.app.GmtryStation;
import javax.media.j3d.*;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.behaviors.PickMouseBehavior;

import javax.vecmath.*;

import cgtjr.academics.math.geometry.g3d.*;
import cgtjr.academics.math.geometry.*;


public class PckSlctBhvr extends PickMouseBehavior {
  Appearance savedAppearance = null;
  Shape3D oldShape = null;
  Appearance highlightAppearance;
  BranchGroup brnchGrp;
  ShapeG3D shpVlm;

  public PckSlctBhvr(Canvas3D canvas, BranchGroup root,
			       Bounds bounds) {
      super(canvas, root, bounds);
      
      this.setSchedulingBounds(bounds);
      //root.addChild(this);
      Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
      Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
      Color3f highlightColor = new Color3f(0.0f, 1.0f, 0.0f);
      Material highlightMaterial = new Material(highlightColor, black,
						highlightColor, white,
						80.0f);
      highlightAppearance = new Appearance();
      highlightAppearance.setMaterial(new Material(highlightColor, black,
						   highlightColor, white,
						   80.0f));
      pickCanvas.setMode(PickTool.BOUNDS);
      
  }

    public void updateScene(int xpos, int ypos) {
        /*
	PickResult pickResult = null;
	Shape3D shape3D = null;
        System.out.println("PckSlctBhvr: test1a ...");
   
        if(pickCanvas != null)
        {
           pickCanvas.setShapeLocation(xpos, ypos);
	   pickResult = pickCanvas.pickClosest();
        }
        System.out.println("PckSlctBhvr: test1b ...");
        if (pickResult != null) {
      System.out.println("PckSlctBhvr: test2...");
	    shape3D = (Shape3D) pickResult.getNode(PickResult.SHAPE3D);
	}

	if (oldShape != null){
      System.out.println("PckSlctBhvr: test 3...");
	    oldShape.setAppearance(savedAppearance);
	}
	if (shape3D != null) {
      System.out.println("PckSlctBhvr: test 4...");
	    savedAppearance = shape3D.getAppearance();
	    oldShape = shape3D;
	    shape3D.setAppearance(highlightAppearance);
       shpVlm = (ShapeG3D) shape3D;
       Shape aShape = shpVlm.getShape();
       aShape.getX1();
       aShape.getY1();
       aShape.getZ1();
      System.out.println("PckSlctBhvr: x="+aShape.getX1()+",y="+aShape.getY1()+",z="+aShape.getZ1());
      System.out.println("PckSlctBhvr: dx="+aShape.getDeltaX()+",dy="+aShape.getDeltaY()+",dz="+aShape.getDeltaZ());
       GmtryStation.setShpG3D(shpVlm);
      
	}*/
   //brnchGrp.addChild(shape);
    }
    public BranchGroup getBrnchGrp()
    {
       return brnchGrp;
    }
}
