package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.shapebndry.BoundaryShape_I;


public class BoxBndry extends ShapePnt implements BoundaryShape_I
{
   Pnt boxPnt;

   
   public BoxBndry()
   {

   }

   public BoxBndry(double myWidth,double myHeight,double myLength)
   {
      ////////boxPntsetLength((float)myLength);
      setWidth((float)myWidth);
      setHeight((float)myHeight);
   }
   

   float width;
   float height;
   float length;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
   
   public boolean isInXBoundary(double myX,double y,double z)
   {
      boolean isInXBoundary = false;
      if(myX >= 0  && myX <= getWidth())
      {   
         isInXBoundary = true;
      }
      //System.out.println("BoxBndry: x = "+myX+", x min = 0 "+", x max = "+getWidth()+", isInXBoundary = "+isInXBoundary);
      return isInXBoundary;
   }
   public boolean isInYBoundary(double x,double myY,double z)
   {
      boolean isInYBoundary = false;

      if( (myY >= 0) && (myY <= getHeight()) )
      {
         isInYBoundary = true;
      }
      //System.out.println("BoxBndry: y = "+myY+", y min = 0"+" y max = "+getHeight()+", isInYBoundary = "+isInYBoundary);
      return isInYBoundary;
   }
   public boolean isInZBoundary(double x,double y,double myZ)
   {
      boolean isInZBoundary = false;

      if( (myZ >= 0) && (myZ <= getLength()) )
      {
         isInZBoundary = true;
      }
      //System.out.println("BoxBndry: is boundary z = "+myZ);
      return isInZBoundary;
   }
   public boolean isInBoundary(double x,double y,double z)
   {
      boolean isInABoundary = false;

      if(isInXBoundary(x,y,z) && isInYBoundary(x,y,z) && isInZBoundary(x,y,z))
      {
         isInABoundary = true;
      }
      return isInABoundary;
   }
}