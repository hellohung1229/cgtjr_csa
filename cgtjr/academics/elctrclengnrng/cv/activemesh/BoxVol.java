package cgtjr.academics.elctrclengnrng.cv.activemesh;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;


public class BoxVol extends ShapePnt
{
   BoxBndry boxBoundary;
   BndryNodeAction_I nodeActn;
   
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

   public BoxVol()
   {      
      setLength(1.00f);
      setWidth(1.00f);
      //setHeight(1.00f);
      setDeltaX(25);
      setDeltaY(25);
      setDeltaZ(1);
   }
   public BoxVol(double myWidth,double myHeight,double myLength)
   {
      /////////nodeActn = new Smplr();
      boxBoundary = new BoxBndry(myWidth,myHeight,0);
      setDeltaX(25);
      setDeltaY(25);
      setDeltaZ(1);
      setBoundaryShape(new Boundary(boxBoundary));
      ///////createInitCoordinates(nodeActn);
      ///////createCoordinateMesh(nodeActn);
   }
   public BoxVol(double myWidth,double myHeight,double myLength,BndryNodeAction_I myNodeActn)
   {
      boxBoundary = new BoxBndry(myWidth,myHeight,0);
      setDeltaX(25);
      setDeltaY(25);
      setDeltaZ(1);
      setBoundaryShape(new Boundary(boxBoundary));
      //////createInitCoordinates(myNodeActn);
      //.../createCoordinateMesh(myNodeActn);
   }
   public void setNodeActn(BndryNodeAction_I myNodeActn)
   {
      nodeActn = myNodeActn;
   }
}