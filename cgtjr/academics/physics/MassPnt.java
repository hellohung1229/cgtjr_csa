/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.physics;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author clayton g thomas jr
 */
public class MassPnt extends Pnt{
    private float mass;
    private float xVlcty;
    private float yVlcty;
    private float zVlcty;

    public float getMass() {
        return mass;
    }

    public float getxVlcty() {
        return xVlcty;
    }

    public float getyVlcty() {
        return yVlcty;
    }

    public float getzVlcty() {
        return zVlcty;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setXVlcty(float xVlcty) {
        this.xVlcty = xVlcty;
    }

    public void setYVlcty(float yVlcty) {
        this.yVlcty = yVlcty;
    }

    public void setzVlcty(float zVlcty) {
        this.zVlcty = zVlcty;
    }     
   public Pnt createDataPoint()
   {
      MassPnt aMassPnt = new MassPnt();
      aMassPnt.setColor(0xffffff);
      aMassPnt.setDeltaX(getDeltaX());
      aMassPnt.setDeltaY(getDeltaY());
      aMassPnt.setDeltaZ(getDeltaZ());
      return (Pnt)aMassPnt;
   }
   public Pnt createPoint()
   {
      //System.out.println("MassPnt: createMassPnt()");
      MassPnt aMassPnt = new MassPnt();
      aMassPnt.setColor(0xffffff);
      aMassPnt.setDeltaX(getDeltaX());
      aMassPnt.setDeltaY(getDeltaY());
      aMassPnt.setDeltaZ(getDeltaZ());

      return (Pnt)aMassPnt;
   }    
}
