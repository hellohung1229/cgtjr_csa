/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity.gui.RadMode;

import java.util.ArrayList;

/**
 *
 * @author cgthomasjr
 */
public class RadVertex {
   private float x;
   private float y;
   private float z;
   
   public RadVertex(){
       
   }
   public RadVertex(float myX,float myY,float myZ){
       x = myX;
       y = myY;
       z = myZ;
   }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }  
}
