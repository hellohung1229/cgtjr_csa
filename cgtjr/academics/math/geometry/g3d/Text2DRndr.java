/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import cgtjr.academics.math.geometry.*;
import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author clayton g thomas jr
 */
public class Text2DRndr //extends ElmntRndr
{

   private int count;
   private Vector pntVctr;
   private String crdntType;
   private BranchGroup txtBrnchGrp;
   private int infoType;

   public Text2DRndr(ShapePnt myShape)
   {
      txtBrnchGrp = new BranchGroup();

      pntVctr = (Vector)myShape.getNodes();
      crdntType = myShape.getCrdntTp();
   }
   public void setInfoType(int myInfoType)
   {
       infoType =  myInfoType;
   }
   public BranchGroup rndrElmnt()
   {
     return rndrElmnt(pntVctr);
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

      float aX = CrdntType.rtrvC1(crdntType,aX1,aY1,aZ1,0,0);
      float aY = CrdntType.rtrvC2(crdntType,aX1,aY1,aZ1,0,0);
      float aZ = CrdntType.rtrvC3(crdntType,aX1,aY1,aZ1,0,0);

      float aPoint3f[] = {aX,aY,aZ};
      //aPoint3f = new Point3f(aX,aY,aZ);
      Transform3D aTransform3D = new Transform3D();
      aTransform3D.setTranslation(new Vector3f(aX,aY,aZ));
      TransformGroup txtTrnsfrmGrp = new TransformGroup();
      txtTrnsfrmGrp.setTransform(aTransform3D);

      int aColor = myPoint.getColor();
      Color3f aColor3f = new Color3f(new Color(aColor));
      String info = rtrvString(myPoint);
      Text2D text2D = new Text2D(info,aColor3f,
                                "Helvetica",12, Font.PLAIN);
      txtTrnsfrmGrp.addChild(text2D);
      txtBrnchGrp.addChild(txtTrnsfrmGrp);
      //System.out.println("Text2DRndr: x = "+aX+",aY="+aY+",z="+aZ);

      return txtBrnchGrp;

   }
   
   public String rtrvString(Pnt myPoint)
   {
      String info = null;
      if(infoType == 0)
      {
         info = myPoint.getX1()+","+myPoint.getY1()+","+myPoint.getZ1();
      }
      return info;
   }
}