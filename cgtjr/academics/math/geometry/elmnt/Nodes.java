/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.elmnt;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.lnralgbra.MathVector;

public abstract class Nodes //extends Point
{
   private float normal[];
   public abstract Pnt[] getPoints();
   private float center[];
   
   
   public static boolean isIntrrSrfcNd(Pnt myPoint)
   {
      boolean isInterior = false;
      if(myPoint.nmbrOfVertices() == 4)
      {
         isInterior = true;
      }
      return isInterior;
   }   

    public float[] getNormal() {
        return normal;
    }

    public void setNormal(float[] myNormal) {
        this.normal = myNormal;
    }

    public float[] getCenter() {
        return center;
    }

    public void setCenter(float myCenter[]) {
        this.center = myCenter;
    }
   
}