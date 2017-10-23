/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.lnralgbra;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.*;

public class Trnsfrm
{
   private double xScale;
   private double yScale;
   private double zScale;
   private double xTrans;
   private double yTrans;
   private double zTrans;
   private double xRotate;
   private double yRotate;
   private double zRotate;
   private float matrix[][];

   public Trnsfrm()
   {
      constructor();
   }
   public void crtIdntty()
   {
       for(int i=0;i<4;i++)
       {
           for(int j=0;j<4;j++)
           {
               if(i==j)
               {
                   matrix[i][j] = 1;
               }else{
                   matrix[i][j] = 0;
               }
           }
       }
   }
   public void rotateX(float mySVal,float myCVal)
   {
       float temp;
       for(int i=0;i<4;i++)
       {
          temp = matrix[1][i]*myCVal - matrix[2][i]*mySVal;
          matrix[2][i] = matrix[1][i]*mySVal + matrix[2][i]*myCVal;
          matrix[1][i] = temp;
       }
   }
   public void rotateY(float mySVal,float myCVal)
   {
       float temp;
       for(int i=0;i<4;i++)
       {
          temp = matrix[0][i]*myCVal + matrix[2][i]*mySVal;
          matrix[2][i] = -matrix[0][i]*mySVal + matrix[2][i]*myCVal;
          matrix[0][i] = temp;
       }
   }
   public void rotateZ(float mySVal,float myCVal)
   {
       float temp;
       for(int i=0;i<4;i++)
       {
          temp = matrix[0][i]*myCVal - matrix[1][i]*mySVal;
          matrix[1][i] = matrix[0][i]*mySVal + matrix[1][i]*myCVal;
          matrix[0][i] = temp;
       }
   }
   public void scale()
   {
       matrix[0][0] *= xScale;
       matrix[1][1] *= yScale;
       matrix[2][2] *= zScale;
   }
   public void translate()
   {
       matrix[0][3] += xTrans;
       matrix[1][3] += yTrans;
       matrix[2][3] += zTrans;
   }
   public void translate(float myXTrans,float myYTrans,float myZTrans)
   {
       matrix[0][3] += myXTrans;
       matrix[1][3] += myYTrans;
       matrix[2][3] += myZTrans;
   }
   private void constructor()
   {
       matrix = new float[4][4];
       xScale = 1;
       yScale = 1;
       zScale = 1;
       xTrans = 0;
       yTrans = 0;
       zTrans = 0;
       xRotate = 0;
       yRotate = 0;
       zRotate = 0;
       crtIdntty();
   }
   public void setXScale(double myXScale)
   {
       xScale = myXScale;
   }
   public void setYScale(double myYScale)
   {
       yScale = myYScale;
   }
   public void setZScale(double myZScale)
   {
       zScale = myZScale;
   }
   public void setScale(double myXScale,double myYScale,double myZScale)
   {
       xScale = myXScale;
       yScale = myZScale;
       zScale = myZScale;
   }
   public void setXTrans(double myXTrans)
   {
       xTrans = myXTrans;
   }
   public void setYTrans(double myYTrans)
   {
       yTrans = myYTrans;
   }
   public void setZTrans(double myZTrans)
   {
       zTrans = myZTrans;
   }
   public void setTrans(double myXTrans,double myYTrans,double myZTrans)
   {
      xTrans = myXTrans;
      yTrans = myYTrans;
      zTrans = myZTrans;
   }
   public void setXRotate(double myXRotate)
   {
       xRotate = myXRotate;
   }
   public void setYRotate(double myYRotate)
   {
       yRotate = myYRotate;
   }
   public void setZRotate(double myZRotate)
   {
       zRotate = myZRotate;
   }
   public void setRotate(double myXRotate,double myYRotate,double myZRotate)
   {
      xRotate = myXRotate;
      yRotate = myYRotate;
      zRotate = myZRotate;
   }
   public void crtTrnsfrm()
   {
      crtIdntty();
      scale();
      rotateX((float)Math.sin(xRotate),(float)Math.cos(xRotate));
      rotateY((float)Math.sin(yRotate),(float)Math.cos(yRotate));
      rotateZ((float)Math.sin(zRotate),(float)Math.cos(zRotate));
      translate();
   }
   public Pnt transform(Pnt myPoint)
   {
      Pnt aPoint = new Pnt();
      aPoint.setX1(matrix[0][0]*myPoint.getX1()+matrix[0][1]*myPoint.getY1()+matrix[0][2]*myPoint.getZ1()+matrix[0][3]);
      aPoint.setY1(matrix[1][0]*myPoint.getX1()+matrix[1][1]*myPoint.getY1()+matrix[1][2]*myPoint.getZ1()+matrix[1][3]);
      aPoint.setZ1(matrix[2][0]*myPoint.getX1()+matrix[2][1]*myPoint.getY1()+matrix[2][2]*myPoint.getZ1()+matrix[2][3]);
      
      myPoint.setX1(aPoint.getX1());
      myPoint.setY1(aPoint.getY1());      
      myPoint.setZ1(aPoint.getZ1());
      //Matrix.print(matrix);
      return myPoint;
   }
   public static float rotateX(float myX,float myY,float myAngle)
   {      
      float x =(float)(Math.cos(myAngle)*myX - Math.sin(myAngle)*myY);    
      return x;
   }   
   public static float rotateY(float myX,float myY,float myAngle)
   {      
      float y =(float)(Math.sin(myAngle)*myX + Math.cos(myAngle)*myY);    
      return y;
   }      
   public float transformX(float myX,float myY,float myZ)
   {
      float x = (matrix[0][0]*myX+matrix[0][1]*myY+matrix[0][2]*myZ+matrix[0][3]);
      //Matrix.print(matrix);
      //System.out.println("Trnsfrm: myX="+myX+",myY="+myY+",myZ="+myZ+",transform x = "+x);
      return x;
   }
   public float transformY(float myX,float myY,float myZ)
   {
      float y = (matrix[1][0]*myX+matrix[1][1]*myY+matrix[1][2]*myZ+matrix[1][3]);
      //Matrix.print(matrix);
      //System.out.println("Trnsfrm: myX="+myX+",myY="+myY+",myZ="+myZ+",transform y = "+y);
      return y;
   }
   public float transformZ(float myX,float myY,float myZ)
   {
      float z = (matrix[2][0]*myX+matrix[2][1]*myY+matrix[2][2]*myZ+matrix[2][3]);
      //Matrix.print(matrix);
      //System.out.println("Trnsfrm: myX="+myX+",myY="+myY+",myZ="+myZ+",transform z = "+z);
      return z;
   }
}