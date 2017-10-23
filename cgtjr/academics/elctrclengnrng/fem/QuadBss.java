/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */

public class QuadBss extends BssFnctn
{
   double u1;
   double u2;
   double v1;
   double v2;

   public double cmptBss1(double myX,double myY)
   {
      double aValue = (u2-myX)*(v2-myY)/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double cmptBss2(double myX,double myY)
   {
      double aValue = ((myX-u1)*(v2-myY))/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double cmptBss3(double myX,double myY)
   {
      double aValue = ((myX-u1)*(myY-v1))/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double cmptBss4(double myX,double myY)
   {
      double aValue = ((u2-myX)*(myY-v1))/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double cmptBssFnctn(int myNmbr)
   {
      double aValue = 0;
      if(myNmbr == 0)
      {
         aValue = cmptBss1(u1,v1);
      }else if(myNmbr == 1){
         aValue = cmptBss2(u2,v1);
      }else if(myNmbr == 2){
         aValue = cmptBss3(u2,v2);
      }else if(myNmbr == 3){
         aValue = cmptBss4(u1,v2);
      }
      return aValue;
   }
   public double[] cmptBssDrvtv1(double myX,double myY)
   {
      double aValue[] = new double[2];
      aValue[0] = (v2-myY)/(Math.abs(u2-u1)*(v2-v1));
      aValue[1] = (u2-myX)/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double[] cmptBssDrvtv2(double myX,double myY)
   {
      double aValue[] = new double[2];
      aValue[0] = (v2-myY)/(Math.abs(u2-u1)*(v2-v1));
      aValue[1] = (myX-u1)/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double[] cmptBssDrvtv3(double myX,double myY)
   {
      double aValue[] = new double[2];
      aValue[0] = (myY-v1)/(Math.abs(u2-u1)*(v2-v1));
      aValue[1] = (myX-u1)/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double[] cmptBssDrvtv4(double myX,double myY)
   {
      double aValue[] = new double[2];
      aValue[0] = (myY-v1)/(Math.abs(u2-u1)*(v2-v1));
      aValue[1] = (u2-myX)/(Math.abs(u2-u1)*(v2-v1));
      return aValue;
   }
   public double[] cmptBssDrvtv(int myNmbr)
   {
      double aValue[] = null;
      if(myNmbr == 0)
      {
         aValue = cmptBssDrvtv1(u1,v1);
      }else if(myNmbr == 1)
      {
         aValue = cmptBssDrvtv2(u2,v1);

      }else if(myNmbr == 2)
      {
         aValue = cmptBssDrvtv3(u2,v2);
      }else if(myNmbr == 3)
      {
         aValue = cmptBssDrvtv4(u2,v1);
      }
      return aValue;
   }
   public double cmptIntglDrvtv00(double myX0,double myX1,double myY0,double myY1)
   {
      double aLengthY = Math.abs(myY1-myY0);
      double aLengthX = Math.abs(myX1-myX0);
      double aValue = ((myX1-myX0)*(myX1-myX0) + (myY1-myY0)*(myY1-myY0))/(3*aLengthX*aLengthY);
      //System.out.println("QuadBss.cmptIntglDrvtv00(): value = "+aValue);
      return aValue;
   }
   public double cmptIntglDrvtv01(double myX0,double myX1,double myY0,double myY1)
   {
      double aLengthY = Math.abs(myY1-myY0);
      double aLengthX = Math.abs(myX1-myX0);
      double aValue = ((myX1-myX0)*(myX1-myX0)- 2*(myY1-myY0)*(myY1-myY0))/(6*aLengthX*aLengthY);
      //System.out.println("QuadBss.cmptIntglDrvtv01(): value = "+aValue);
      return aValue;
   }
   public double cmptIntglDrvtv02(double myX0,double myX1,double myY0,double myY1)
   {
      double aLengthY = Math.abs(myY1-myY0);
      double aLengthX = Math.abs(myX1-myX0);
      double aValue = -1*((myX1-myX0)*(myX1-myX0) + (myY1-myY0)*(myY1-myY0))/(6*aLengthX*aLengthY);
      //System.out.println("QuadBss.cmptIntglDrvtv02(): value = "+aValue);
      return aValue;
   }
   public double cmptIntglDrvtv03(double myX0,double myX1,double myY0,double myY1)
   {
      double aLengthY = Math.abs(myY1-myY0);
      double aLengthX = Math.abs(myX1-myX0);
      double aValue = (-2*(myX1-myX0)*(myX1-myX0) + (myY1-myY0)*(myY1-myY0))/(6*aLengthX*aLengthY);
      //System.out.println("QuadBss.cmptIntglDrvtv03(): value = "+aValue);
      return aValue;
   }
   public double cmptCffcntMtrx(int j,int k)
   {
      System.out.println("QuadBss.cmptCffcntMtrx1() j = "+j+",k="+k);
      double aValue = 0;
      if(j==k)
      {
        aValue = cmptIntglDrvtv00(u2,u1,v2,v1);
      }else if((j==1&&k==0) || (j==0&&k==1) || (j==3&&k==2) || (j==2&&k==3))
      {
        aValue = cmptIntglDrvtv01(u2,u1,v2,v1);
      }else if((j==2&&k==0) || (j==0&&k==2) || (j==3&&k==1) || (j==1&&k==3))
      {
        aValue = cmptIntglDrvtv02(u2,u1,v2,v1);
      }else if((j==2&&k==1) || (j==1&&k==2) || (j==3&&k==0) || (j==0&&k==3))
      {
        aValue = cmptIntglDrvtv03(u2,u1,v2,v1);
      }
      return aValue;
      //return 1;
   }
   public double cmptIntgl00(double myX0,double myX1,double myY0,double myY1)
   {
      double aValue1 = Math.pow((myX1-myX0),3)*Math.pow((myY1-myY0),3)/9;
      double aValue2 = (myX1-myX0)*(myX1-myX0)*(myY1-myY0)*(myY1-myY0);
      double aValue3 = aValue1/aValue2;
      //System.out.println("QuadBss.cmptIntgl00(): value = "+aValue3);
      return aValue3;
   }

   public double cmptIntgl01(double myX0,double myX1,double myY0,double myY1)
   {

      double aLngth = myX1-myX0;
      double aValue0 = myX1*aLngth/2 - myX1*myX0*aLngth - Math.pow(aLngth,3)/3 + myX0*aLngth*aLngth/2;
      double aValue1 = Math.pow((myY1-myY0),3)/3;
      double aValue2 = aValue0*aValue1;
      double aValue3 = (myX1-myX0)*(myX1-myX0)*(myY1-myY0)*(myY1-myY0);
      double aValue4 = aValue2/aValue3;
      //System.out.println("QuadBss.cmptIntgl01(): value = "+aValue4);
      return aValue4;
   }
   public double cmptIntgl02(double myX0,double myX1,double myY0,double myY1)
   {
      double aLngth1 = myX1-myX0;
      double aLngth2 = myY1-myY0;
      double aValue0 = myX1*aLngth1/2 - myX1*myX0*aLngth1 - Math.pow(aLngth1,3)/3 + myX0*aLngth1*aLngth1/2;
      double aValue1 = myY1*aLngth2/2 - myY1*myY0*aLngth2 - Math.pow(aLngth2,3)/3 + myY0*aLngth2*aLngth2/2;
      double aValue2 = aValue0*aValue1;
      double aValue3 = (myX1-myX0)*(myX1-myX0)*(myY1-myY0)*(myY1-myY0);
      double aValue4 = aValue2/aValue3;
      //System.out.println("QuadBss.cmptIntgl02(): value = "+aValue4);
      return aValue4;
   }
   public double cmptIntgl03(double myX0,double myX1,double myY0,double myY1)
   {
      double aLngth = myY1-myY0;
      double aValue0 = myY1*aLngth/2 - myY1*myY0*aLngth - Math.pow(aLngth,3)/3 + myY0*aLngth*aLngth/2;
      double aValue1 = Math.pow((myX1-myX0),3)/3;
      double aValue2 = aValue0*aValue1;
      double aValue3 = (myX1-myX0)*(myX1-myX0)*(myY1-myY0)*(myY1-myY0);
      double aValue4 = aValue2/aValue3;
      //System.out.println("QuadBss.cmptIntgl03(): value = "+aValue4);
      return aValue4;
   }
   public double cmptCffcntMtrx2(int j,int k)
   {
      //System.out.println("QuadBss.cmptCffcntMtrx2() j = "+j+",k="+k);
      double aValue = 0;
      if(j==k)
      {
        aValue = cmptIntgl00(u2,u1,v2,v1);
      }else if((j==1&&k==0) || (j==0&&k==1) || (j==3&&k==2) || (j==2&&k==3))
      {
        aValue = cmptIntgl01(u2,u1,v2,v1);
      }else if((j==2&&k==0) || (j==0&&k==2) || (j==3&&k==1) || (j==1&&k==3))
      {
        aValue = cmptIntgl02(u2,u1,v2,v1);
      }else if((j==2&&k==1) || (j==1&&k==2) || (j==3&&k==0) || (j==0&&k==3))
      {
        aValue = cmptIntgl03(u2,u1,v2,v1);
      }
      return aValue;
   }
   public double cmptSrcMtrx(int j,int k)
   {
      double aLngthX = u2 - u1;
      double aLngthY = v2 - v1;
      double anArea = aLngthX*aLngthY;

      double rslt = 0;
      if(j==k)
      {
         rslt = anArea/9;
      }else{
         rslt = anArea/18;
      }
      //System.out.println("QuadBss:u2="+u2+",u1="+u1+",v2="+v2+",v1="+v1+",rslt="+rslt);
      return rslt;
   }
   public void setBssNds(double myX[],double myY[],double myZ[])
   {
      setBssNds(myX[0],myX[1],myY[1],myY[2],myZ[0],myZ[0]);
   }
   public void setBssNds(double myU1,double myU2,double myV1,double myV2,double myW1,double myW2)
   {
      u1 = myU1;
      u2 = myU2;
      v1 = myV1;
      v2 = myV2;
      //System.out.println("QuadBss:myU2="+myU2+",myU1="+myU1+",myV1="+myV1+",myV2="+myV2);
   }
}