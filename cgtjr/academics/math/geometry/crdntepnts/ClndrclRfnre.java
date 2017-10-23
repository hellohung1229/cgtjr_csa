/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class ClndrclRfnre
{
   private ArrayList theta0ArrayList;
   private ArrayList theta2PIArrayList;

   public ClndrclRfnre()
   {
      theta0ArrayList = new ArrayList();
      theta2PIArrayList = new ArrayList();
   }
   public void refine(ShapePnt myShape)
   {
      Vector aVector = myShape.getNodes();
      int aSize = aVector.size();
      for(int i=0;i<aSize;i++)
      {
         Pnt aPoint = (Pnt)aVector.get(i);
         if(aPoint.getY1() == 0 )
         {
            System.out.println("ClndrRfnr: adding theta = 0, point = "+aPoint);
            theta0ArrayList.add(aPoint);

         }else if(aPoint.getY1() >= 2*Math.PI-myShape.getDeltaY()/2 && aPoint.getY1() <= 2*Math.PI+myShape.getDeltaY()/2){
            System.out.println("ClndrRfnr: adding theta = 2PI, point = "+aPoint);
            theta2PIArrayList.add(aPoint);
         }
      }
      merge(theta0ArrayList,theta2PIArrayList);
   }
   public void merge(ArrayList myTheta0ArrayList,ArrayList myTheta2PIArrayList)
   {
      int size1 = myTheta0ArrayList.size();
      int size2 = myTheta2PIArrayList.size();
      Pnt aPoint1 = null;
      Pnt aPoint2 = null;

      for(int i=0;i<size1;i++)
      {
         aPoint1 = (Pnt)myTheta0ArrayList.get(i);
         for(int j=0;j<size2;j++)
         {
            aPoint2 = (Pnt)myTheta2PIArrayList.get(j);
            if(isEqualRadius(aPoint1,aPoint2))
            {
               aPoint1.connectVertices2(aPoint2);
            }
         }
      }
   }
   public boolean isEqualRadius(Pnt aPoint1,Pnt aPoint2)
   {
      boolean equalRadius = false;

      if(aPoint1.getX1() == aPoint2.getX1())
      {
         equalRadius = true;
      }
      return equalRadius;
   }
}
