/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;

/**
 *
 * @author clayton g thomas jr
 */
public class TrckrShpBndry extends GridDrawCrdnteShpe
{
   private double xPos1;
   private double xPos2;
   private double yPos1;
   private double yPos2;
   private double time1;
   private double time2;
   private double velocity[];

   public TrckrShpBndry()
   {
      velocity = new double[2];
   }
   public TrckrShpBndry(ShpBndry myShpBndry,GridDrawActn aPntPxlInsrtActn)
   {
      super(myShpBndry,aPntPxlInsrtActn);
      velocity = new double[2];
   }
   public void setXPos1(double myXPos1)
   {
      xPos1 = myXPos1;
   }
   public void setYPos1(double myYPos1)
   {
      yPos1 = myYPos1;      
   }
   public void setXPos2(double myXPos2)
   {
      xPos2 = myXPos2;
   }
   public void setYPos2(double myYPos2)
   {
      yPos2 = myYPos2;
   }
   public double getXPos1()
   {
      return xPos1;
   }
   public double getYPos1()
   {
      return yPos1;
   }
   public double getXPos2()
   {
      return xPos2;
   }
   public double getYPos2()
   {
      return yPos2;
   }
   public void setTime1(long myTime1)
   {
      time1 = myTime1;
   }
   public void setTime2(long myTime2)
   {
      time2 = myTime2;
   }
   public void setTime1()
   {
      long startTime = System.currentTimeMillis();
      setTime1(startTime);
   }
   public void setTime2()
   {
      long endTime = System.currentTimeMillis();
      setTime2(endTime);
   }   
   public double[] cmptVlcty()
   {      
      double xVlcty = (xPos2 - xPos1)/((time2-time1)*.001);
      double yVlcty = (yPos2 - yPos1)/((time2-time1)*.001);
      velocity[0] = xVlcty;
      velocity[1] = yVlcty;
      return velocity;
   }
}
