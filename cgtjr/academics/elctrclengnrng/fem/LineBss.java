/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.lnralgbra.Matrix;

/**
 *
 * @author clayton g thomas jr
 */


public class LineBss extends BssFnctn
{
   private double u1;
   private double u2;

   public double cmptBss0(double myX)
   {
      double aValue = (myX-u2)/(u1-u2);
      return aValue;
   }
   public double cmptBss1(double myX)
   {
      double aValue = (u2-myX)/(u2-u1);
      return aValue;
   }
   public double cmptIntgrlMltplyBss(int myNmbr1,int myNmbr2)
   {
      double aValue = cmptBssFnctn(myNmbr1)*cmptBssFnctn(myNmbr2);
      return aValue;
   }
   public double cmptIntgrlDvrgncDrvtvBss(int myNmbr1,int myNmbr2)
   {
      double aValue = mltpBssDrvtv(myNmbr1,myNmbr2);
      return aValue;
   }
   public double cmptBssFnctn(int myNmbr)
   {
      double aValue = 0;
      if(myNmbr == 0)
      {
         aValue = cmptBss0(u1);
      }else if(myNmbr == 1){
         aValue = cmptBss1(u2);
      }
      return aValue;
   }
   public double[] cmptBssDrvtv0()
   {
      double aValue[] = new double[2];
      aValue[0] = 1/(u1-u2);
      aValue[1] = 0;
      return aValue;
   }
   public double[] cmptBssDrvtv1()
   {
      double aValue[] = new double[2];
      aValue[0] = 1/(u2-u1);
      aValue[1] = 0;
      return aValue;
   }
   public double[] cmptBssDrvtv(int myNmbr)
   {
      double aValue[] = null;
      if(myNmbr == 0)
      {
         aValue = cmptBssDrvtv0();
      }else if(myNmbr == 1)
      {
         aValue = cmptBssDrvtv1();
      }
      return aValue;
   }
   public double cmptCffcntMtrx(int j,int k)
   {
      double aValue = 0;
      if(j==k)
      {
        aValue = cmptIntgrlDrvtv00(u1,u2);
      }else if((j==0 && k==1)||(j==1 && k==0))
      {
        aValue = cmptIntgrlDrvtv01(u1,u2);
      }
      //System.out.println("LineBss: value = "+aValue);
      return aValue;
   }
   public double cmptCffcntMtrx2(int j,int k)
   {
      double aValue = 0;
      if(j==k)
      {
        aValue = cmptIntgrl00(u1,u2);
      }else if((j==0 && k==1)||(j==1 && k==0))
      {
        aValue = cmptIntgrl01(u1,u2);
      }
      return aValue;
   }
   public double mltpBssDrvtv(int myNmbr1,int myNmbr2)
   {
      double aValue1[] = cmptBssDrvtv(myNmbr1);
      double aValue2[] = cmptBssDrvtv(myNmbr2);
      double aValue = Matrix.mltply2x1x2x1(aValue1,aValue2);
      return aValue;
   }
   public double cmptIntgrl00(double myX0,double myX1)
   {
      double aValue = -1*Math.pow((myX0-myX1),3)/(myX0-myX1);
      return aValue;
   }
   public double cmptIntgrl01(double myX0,double myX1)
   {
      double aLngth = myX1-myX0;
      double aValue0 =  Math.pow(aLngth,3)/3 - myX0*aLngth*aLngth/2 - myX1*aLngth*aLngth/2 + myX1*myX0*aLngth;
      double aValue1 = (myX1-myX0)*(myX1-myX0);
      double aValue2 = aValue0/aValue1;
      return aValue2;
   }
   public double cmptIntgrlDrvtv00(double myX0,double myX1)
   {
      double aValue = (myX1-myX0)/Math.pow((myX0-myX1),2);
      return aValue;
   }
   public double cmptIntgrlDrvtv01(double myX0,double myX1)
   {
      double aValue = 1/(myX0-myX1);
      return aValue;
   }
   public double cmptIntgrlDrvtv11(double myX0,double myX1)
   {
      double aValue = (myX1-myX0)/Math.pow((myX1-myX0),2);
      return aValue;
   }
   public void setLineVls(double myU1,double myU2)
   {
      u1 = myU1;
      u2 = myU2;
   }
   public void setBssNds(double myU1[],double myV1[],double myW1[])
   {
      setBssNds(myU1[0],myU1[1]);
   }
   public void setBssNds(double myU1,double myU2)
   {

         u1 = myU1;
         u2 = myU2;
      //System.out.println("LineBss: u1 = "+u1+", u2 = "+u2);

   }
   public double cmptWidth()
   {
      double width = Math.sqrt((u1-u2)*(u1-u2));
      return width;
   }
   public double getNodeValue(int myIndex)
   {
      if(myIndex==0)
      {
         return u1;
      }else{
         return u2;
      }
   }
}