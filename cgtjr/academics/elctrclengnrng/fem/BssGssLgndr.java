/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */


public class BssGssLgndr
{
   HxhdrlBss aHxhdrlBss;
   private static double smplPnts5[] = {-0.9061798,
                                        -0.5384693,
                                         0.0000000,
                                         0.5384693,
                                         0.9061798};

   private static double smplWghts5[] = {0.2369268,
                                         0.4786286,
                                         0.5688888,
                                         0.4786286,
                                         0.2369268};

   private static double smplPnts3[] = {-0.7745966,
                                         0.0000000,
                                         0.7745966};

   private static double smplWghts3[] = {0.5555555,
                                         0.8888888,
                                         0.5555555};
   private int nmbrPnts = 5;
   private double xMax = 3;
   private double xMin = -1;
   private double yMax = 6;
   private double yMin = 0;
   private double zMax = 4;
   private double zMin = -4;
   private double intlX = 0;
   private double intlY = 0;
   private double intlZ = 0;

   BssGssLgndr()
   {
      aHxhdrlBss = new HxhdrlBss();
   }
   BssGssLgndr(HxhdrlBss myaHxhdrlBss)
   {
      aHxhdrlBss = myaHxhdrlBss;
   }
   public void setIntlX(double myX)
   {
      intlX = myX;
   }
   public void setIntlY(double myY)
   {
      intlY = myY;
   }
   public void setIntlZ(double myZ)
   {
      intlZ = myZ;
   }
   public void setXMax(double myXMax)
   {
      xMax = myXMax;
   }
   public void setXMin(double myXMin)
   {
      xMin = myXMin;      
   }
   public void setYMax(double myYMax)
   {
      yMax = myYMax;      
   }
   public void setYMin(double myYMin)
   {
      yMin = myYMin;      
   }
   public void setZMax(double myZMax)
   {
      zMax = myZMax;      
   }
   public void setZMin(double myZMin)
   {
      zMin = myZMin;      
   }
   public double intgrt()
   {
      double smplPntX = 0;
      double smplWghtX = 0;
      double smplPntY = 0;
      double smplWghtY = 0;
      double smplPntZ = 0;
      double smplWghtZ = 0;
      double rslt = 0;

      for(int i=0;i<3;i++)
      {
         smplPntX = (xMax-xMin)/2-((xMax-xMin)/2) * smplPnts3[i];
         smplWghtX = (xMax-xMin)/2 * smplWghts3[i];

         for(int j=0;j<3;j++)
         {
            smplPntY = (yMax-yMin)/2-((yMax-yMin)/2) * smplPnts3[j];
            smplWghtY = (yMax-yMin)/2 *smplWghts3[j];
            //System.out.println("GaussLgndr: x="+smplPntX+",y="+smplPntY);
            rslt += smplWghtX*smplWghtY*cmpt(smplPntX,smplPntY);
            //for(int k=0;k<nmbrPnts;k++)
            //{
            //   smplPntZ = (zMax-zMin)/2 * smplPnts5[k];
            //   smplWghtZ = (zMax-zMin)/2 * smplWghts5[k];
               //rslt += smplWghtX*smplWghtY*smplWghtZ*cmpt(smplPntX,smplPntY,smplPntZ);
            //}
         }
      }
      //System.out.println("rslt = "+rslt);
      return rslt;
   }
   public double intgrt3(int p,int q)
   {
      double smplPntX = 0;
      double smplWghtX = 0;
      double smplPntY = 0;
      double smplWghtY = 0;
      double smplPntZ = 0;
      double smplWghtZ = 0;
      double rslt1 = 0;

      for(int i=0;i<nmbrPnts;i++)
      {
         smplPntX = intlX+(xMax-xMin)/2-((xMax-xMin)/2) * smplPnts5[i];
         smplWghtX = (xMax-xMin)/2 * smplWghts5[i];
         
         for(int j=0;j<nmbrPnts;j++)
         {
            smplPntY = intlY+(yMax-yMin)/2-((yMax-yMin)/2) * smplPnts5[j];
            smplWghtY = (yMax-yMin)/2 *smplWghts5[j];
            for(int k=0;k<nmbrPnts;k++)
            {
               smplPntZ = intlZ+(zMax-zMin)/2-((zMax-zMin)/2) * smplPnts5[k];
               smplWghtZ = (zMax-zMin)/2 * smplWghts5[k];
               rslt1 += smplWghtX*smplWghtY*smplWghtZ*cmpt(p,q,smplPntX,smplPntY,smplPntZ);
            }
         }
      }
      //System.out.println("BssGssLgndr:intlX="+intlX+",xMax="+xMax+",xMin="+xMin);
      //System.out.println("BssGssLgndr:intlY="+intlY+",yMax="+yMax+",yMin="+yMin);
      //System.out.println("BssGssLgndr:intlZ="+intlZ+",zMax="+zMax+",zMin="+zMin);
      //System.out.println("BssGssLgndr: rslt = "+rslt1);
      return rslt1;
   }
   public double cmpt(double myX,double myY)
   {
      double rslt = (myX*myX*myX*Math.pow(myY,4));
      return rslt;
   }
   public double cmpt(int p,int q,double myX,double myY,double myZ)
   {
      double rslt = aHxhdrlBss.cmptBssDrvtv(p,q,myX,myY,myZ);
      return rslt;
   }
   public static void main(String args[])
   {
      //GaussLgndr gssLgndr = new GaussLgndr();
      //gssLgndr.intgrt3();
   }   
}
