/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class PntCrnrFltr extends CornerDetect
{
   public PntCrnrFltr()
   {      
   }
   public void filter3x3(int pixelData[],Vector myPnts)
   {
      int aSize = myPnts.size();
      for(int i=0;i<aSize;i++)
      {
         Pnt aPoint = (Pnt)myPnts.get(i);
         filter3x3(pixelData,aPoint);
      }
   }
   public void filter3x3(int pixelData[],Pnt aPoint)
   {
      int aX = (int)aPoint.getX1();
      int aY = (int)aPoint.getY1();
      int anIndex = rtrvIndex(aX, aY);
      super.filter3x3(pixelData,anIndex);
   }
   public void filter9x9(int pixelData[],Vector myPnts)
   {
      int aSize = myPnts.size();
      for(int i=0;i<aSize;i++)
      {
         Pnt aPoint = (Pnt)myPnts.get(i);
         filter9x9(pixelData,aPoint);
      }
   }
   public void filter9x9(int pixelData[],Pnt aPoint)
   {
      int aX = (int)aPoint.getX1();
      int aY = (int)aPoint.getY1();
      int anIndex = rtrvIndex(aX, aY);
      super.cornerDetect9x9(pixelData,anIndex);
   }
}