/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.imgprcssng.SumSqrDiff;
import cgtjr.academics.general.FileNameVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;

/**
 *
 * @author clayton g thomas jr
 */
public class VideoBndry extends ShpBndry
{
   private SumSqrDiff ssd;
   private int pixelData[];
   
   VideoBndry(String myFileName1,String myFileName2)
   {
      ssd = new SumSqrDiff(myFileName1,myFileName2);
   }
   VideoBndry(FileNameVar myFileNameVar1,FileNameVar myFileNameVar2)
   {
      String aFileName1 = myFileNameVar1.getFileNameVal();
      String aFileName2 = myFileNameVar2.getFileNameVal();
      ssd = new SumSqrDiff(aFileName1,aFileName2);
      //myFileNameVar2.

   }
   public boolean isInBndry(double r, double t, double p) 
   {
      boolean inBndry = false;
      int aX = (int)r;
      int aY = (int)t;
      ssd.filter3x3(aX,aY);
      //System.out.println("SSDImgBndry: r = "+r+",t="+t+", p = "+p);
      if(ssd.isInTlrnc() && p == 0)
      //if(r> 1 && t > 1 && r < 80 && t < 70 && p == 0)
      {
         inBndry = true;
      }
      return inBndry;
   }   
}
