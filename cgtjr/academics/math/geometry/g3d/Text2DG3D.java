/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;


import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;


/**
 *
 * @author clayton g thomas jr
 */

public abstract class Text2DG3D extends BranchGroup
{ 
   private Text2DRndr textRender;
   private int count;
   private Vector pntVctr;
   private String crdntType;
   private BranchGroup txtBrnchGrp;
   private float xOffSet;
   private float yOffSet;
   private float zOffSet;
   private Color3f aColor3f;
   private Transform3D aTransform3D = new Transform3D();
   private Vector3f aVector3f;
   //private aTransform3D.setTranslation(new Vector3f(aX,aY,aZ));
      
   public Text2DG3D(ShapePnt myShape)
   {

     txtBrnchGrp = new BranchGroup();

     pntVctr = (Vector)myShape.getNodes();
     crdntType = myShape.getCrdntTp();
     aColor3f = new Color3f();
     aTransform3D = new Transform3D();
     
     aVector3f = new Vector3f();     
   }
   public void setXOffSet(float myXOffSet)
   {
       xOffSet = myXOffSet;
   }
   public void setYOffSet(float myYOffSet)
   {
       yOffSet = myYOffSet;
   }
   public void setZOffSet(float myZOffSet)
   {
       zOffSet = myZOffSet;
   }
   public void rndrElmnt()
   {
       BranchGroup aBranchGroup = rndrElmnt(pntVctr);
       addChild(aBranchGroup);
   }
   public BranchGroup rndrElmnt(Vector myVector)
   {
      Pnt aPoint = null;
      int aSize = myVector.size();
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)myVector.elementAt(i);
         rndrElmnt(aPoint);
      }
      return txtBrnchGrp;
   }
   private BranchGroup rndrElmnt(Pnt myPoint)
   {
      float aX1 = myPoint.getX1();
      float aY1 = myPoint.getY1();
      float aZ1 = myPoint.getZ1();

      //Point3f aPoint3f = new Point3f();
      float yOffSetVrble = 0;
      float c1 = CrdntType.rtrvC1(crdntType,aX1,aY1,aZ1,0,0);
      float c2 = CrdntType.rtrvC2(crdntType,aX1,aY1,aZ1,0,0);
      float c3 = CrdntType.rtrvC3(crdntType,aX1,aY1,aZ1,0,0);
      if(c2 >= 0){
        yOffSetVrble = -0.07f;
      }else{
        yOffSetVrble = -0.63f;
      }
      float aX = xOffSet + c1;
      float aY = yOffSetVrble + c2;
      float aZ = zOffSet + c3;

      //float aPoint3f[] = {aX,aY,aZ};
      //aPoint3f = new Point3f(aX,aY,aZ);
      //Transform3D aTransform3D = new Transform3D();
      //aTransform3D.setTranslation(new Vector3f(aX,aY,aZ));
      aVector3f.set(aX,aY,aZ);
      aTransform3D.setTranslation(aVector3f);

      TransformGroup txtTrnsfrmGrp = new TransformGroup();
      txtTrnsfrmGrp.setTransform(aTransform3D);

      int aColor = myPoint.getColor();
      //Color3f aColor3f = new Color3f(new Color(aColor));
      //Color3f aColor3f = new Color3f(new Color(0x00000000));      

      String info = rtrvString(myPoint);
      Text2D text2D = new Text2D(info,aColor3f,
                                "Helvetica",24, Font.PLAIN);
      txtTrnsfrmGrp.addChild(text2D);
      txtBrnchGrp.addChild(txtTrnsfrmGrp);
      //System.out.println("Text2DG3D: shape type = "+crdntType+", x = "+aX+",aY="+aY+",z="+aZ+", info = "+info);

      return txtBrnchGrp;
   }
   public abstract String rtrvString(Pnt myPoint);
}