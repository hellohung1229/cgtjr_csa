/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

/**
 *
 * @author clayton g thomas jr
 */
//note ... must create tree manipulations insert, delete, modify, ...

public class HxhdrlBss_1 //extends BssFnctn
{
    /*
   private double x0;
   private double x1;
   private double x2;
   private double x3;
   private double x4;
   private double x5;
   private double x6;
   private double x7;
   private double y0;
   private double y1;
   private double y2;
   private double y3;
   private double y4;
   private double y6;
   private double y7;
   private double z0;
   private double z1;
   private double z2;
   private double z3;
   private double z4;
   private double z5;
   private double z6;
   private double z7;
   private double y100;
   private double y101;
   private int zeta[] = {-1,1,1,-1,-1,1,1,-1};
   private int nu[]   = {-1,-1,1,1,-1,-1,1,1};
   private int epsilon[] = {-1,-1,-1,-1,1,1,1,1};
   private BssGssLgndr aBssGssLgndr;

   HxhdrlBss_1()
   {
      aBssGssLgndr = new BssGssLgndr(this);
   }
   public double[] cmptBssDrvtv0(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] = -1*(y1-y)*(z1-z)/vlm;
      drvtv[1] = -1*(x1-x)*(z1-z)/vlm;
      drvtv[2] = -1*(x1-x)*(y1-y)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv0: y1="++","+drvtv[1]+","+drvtv[2]);
      //System.out.println("HxhdrlBss.cmptBssDrvtv0: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv1(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] = (y1-y)*(z1-z)/vlm;
      drvtv[1] = -1*(x-x0)*(z1-z)/vlm;
      drvtv[2] = -1*(x-x0)*(y1-y)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv1: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv2(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);      
      drvtv[0] = (y-y0)*(z1-z)/vlm;
      drvtv[1] = (x-x0)*(z1-z)/vlm;
      drvtv[2] = -1*(x-x0)*(y-y0)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv2: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv3(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);      
      drvtv[0] = -1*(y-y0)*(z1-z)/vlm;
      drvtv[1] = (x1-x)*(z1-z)/vlm;
      drvtv[2] = -1*(x1-x)*(y-y0)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv3: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv4(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] = -1*(y1-y)*(z-z0)/vlm;
      drvtv[1] = -1*(x1-x)*(z-z0)/vlm;
      drvtv[2] =  1*(x1-x)*(y1-y)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv4: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv5(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] =  1*(y1-y)*(z-z0)/vlm;
      drvtv[1] = -1*(x-x0)*(z-z0)/vlm;
      drvtv[2] =  1*(x-x0)*(y1-y)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv5: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv6(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] =  1*(y-y0)*(z-z0)/vlm;
      drvtv[1] =  1*(x-x0)*(z-z0)/vlm;
      drvtv[2] =  1*(x-x0)*(y-y0)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv6: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double[] cmptBssDrvtv7(double x,double y,double z)
   {
      double drvtv[] = new double[3];
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      drvtv[0] =  -1*(y-y0)*(z-z0)/vlm;
      drvtv[1] =   1*(x1-x)*(z-z0)/vlm;
      drvtv[2] =   1*(x1-x)*(y-y0)/vlm;
      //System.out.println("HxhdrlBss.cmptBssDrvtv7: drvtv="+drvtv[0]+","+drvtv[1]+","+drvtv[2]);
      return drvtv;
   }
   public double cmptBssDrvtv(int p,int q,double x,double y,double z)
   {
      double rslt = 0;
      double bd1[] = cmptBssDrvtv(p,x,y,z);
      double bd2[] = cmptBssDrvtv(q,x,y,z);
      rslt = bd1[0]*bd2[0];
      rslt += bd1[1]*bd2[1];
      rslt += bd1[2]*bd2[2];
      return rslt;
   }
   public double[] cmptBssDrvtv(int p,double x,double y,double z)
   {
      double rslt[] = {-9999999,-9999999,-9999999};
      if(p==0)
      {
         rslt = cmptBssDrvtv0(x,y,z);
      }else if(p==1)
      {
         rslt = cmptBssDrvtv1(x,y,z);
      }else if(p==2)
      {
         rslt = cmptBssDrvtv2(x,y,z);
      }else if(p==3)
      {
         rslt = cmptBssDrvtv3(x,y,z);
      }else if(p==4)
      {
         rslt = cmptBssDrvtv4(x,y,z);
      }else if(p==5)
      {
         rslt = cmptBssDrvtv5(x,y,z);
      }else if(p==6)
      {
         rslt = cmptBssDrvtv6(x,y,z);
      }else if(p==7)
      {
         rslt = cmptBssDrvtv7(x,y,z);
      }
      return rslt;
   }
   public double cmptCffcntMtrx(int m,int n)
   {
      aBssGssLgndr.setXMax(x1);
      aBssGssLgndr.setXMin(x0);
      aBssGssLgndr.setYMax(y1);
      aBssGssLgndr.setYMin(y0);
      aBssGssLgndr.setZMax(z1);
      aBssGssLgndr.setZMin(z0);
      //aBssGssLgndr.setIntlX(x0);
      //aBssGssLgndr.setIntlY(y0);
      //aBssGssLgndr.setIntlZ(z0);
      double rslt = aBssGssLgndr.intgrt3(m,n);
      return rslt;
   }
   public int rtrvSnOprtr1(int mySn1,int mySn2)
   {
      int rslt = mySn1 * mySn2;
      return rslt;
   }
   public int rtrvSnOprtr2(int mySn1,int mySn2)
   {
      int rslt = 0;
      if(mySn1 == 1 && mySn2 == -1)
      {
         rslt = 1;
      }else if(mySn1 == -1 && mySn2 == 1)
      {
         rslt = -1;
      }else if(mySn1 == 1 && mySn2 == 1)
      {
         rslt = 1;
      }else if(mySn1 == -1 && mySn2 == -1)
      {
         rslt = 1;
      }
      return rslt;
   }
   public int rtrvSnOprtr(int myZtSn1,int myNuSn1,int myEpslnSn1,int myZtSn2,int myNuSn2,int myEpslnSn2)
   {
      int rslt = 0;
      int rslt1 =  rtrvSnOprtr1(myZtSn1,myZtSn2);
      int rslt2 =  rtrvSnOprtr2(myNuSn1,myNuSn1);     
      int rslt3 =  rtrvSnOprtr2(myEpslnSn1,myEpslnSn1);
      rslt = rslt1*rslt2*rslt3;
      return rslt;
   }
   public void setBssVls(double myX0,double myX1,double myY0,double myY1,double myZ0,double myZ1)
   {
      x0 = myX0;
      x1 = myX1;
      y0 = myY0;
      y1 = myY1;
      y100 = myY0;
      y101 = myY1;
      z0 = myZ0;
      z1 = myZ1;
      //System.out.println("Hxhdrl.setBssVls() y100 = "+y100+",y101="+y101);
      //System.out.println("HxhdrlBss.setBssVls() x0="+x0+",x1="+x1+",y0="+y0+",y1="+y1+",z0="+z0+",z1="+z1);
   }
   public static double cmptBss(int myLclNdNmbr)
   {
      double aValue = 0;
      if(myLclNdNmbr == 0)
      {
         aValue = cmptBss(1,-1,1,1,-1,1);
      }else if(myLclNdNmbr == 1)
      {
         aValue = cmptBss(1,-1,1,1,-1,1);
      }else if(myLclNdNmbr == 2)
      {
         aValue = cmptBss(1,1,1,1,1,1);
      }else if(myLclNdNmbr == 3)
      {
         aValue = cmptBss(-1,1,1,-1,1,1);

      }else if(myLclNdNmbr == 4)
      {
         aValue = cmptBss(-1,-1,-1,-1,-1,-1);
      }else if(myLclNdNmbr == 5)
      {
         aValue = cmptBss(1,-1,-1,1,-1,-1);
      }else if(myLclNdNmbr == 6)
      {
         aValue = cmptBss(1,1,-1,1,1,-1);
      }else if(myLclNdNmbr == 7)
      {
         aValue = cmptBss(-1,1,-1,-1,1,-1);
      }
      return aValue;
   }
   public static double cmptBss(int p,int q,int r)
   {
      return cmptBss(1,1,1,p,q,r);
   }
   public static double cmptBss(double myXi,double myEta,double myLmbd,int p,int q,int r)
   {
      double aValue = 0;
      aValue = (1+p*myXi)*(1+q*myEta)*(1+r*myLmbd)/8;
      return aValue;
   }
   public double cmpBss00()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm = lx *ly*lz;
      double c1 = 36;
      double sm1 = 4*lx*ly*ly*ly*lz*lz*lz +
                   4*lx*lx*lx*ly*lz*lz*lz +
                   4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss11()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = 4*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss22()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -4*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss33()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -4*lx*ly*ly*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss44()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -4*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss55()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  4*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss66()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  4*lx*ly*ly*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmpBss77()
   {
      double lx = x1 - x0;
      double ly = y1 - y0;
      double lz = z1 - z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  4*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss00() lx = "+lx+",ly="+ly+",lz="+lz+",rslt = "+rslt);
      return rslt;
   }
   public double cmptCffcntMtrx1(int j,int k)
   {
      double rslt = -9999999;
      if(j==0&&k==0)
      {
         rslt =  cmpBss00();
      }else if(j==1&&k==1)
      {
         rslt =  cmpBss11();
      }else if(j==2&&k==2)
      {
         rslt =  cmpBss22();
      }else if(j==3&&k==3)
      {
         rslt =  cmpBss33();
      }else if(j==4&&k==4)
      {
         rslt =  cmpBss44();
      }else if(j==5&&k==5)
      {
         rslt =  cmpBss55();
      }else if(j==6&&k==6)
      {
         rslt =  cmpBss66();
      }else if(j==7&&k==7)
      {
         rslt =  cmpBss77();
      }else if((j==0&&k==1)||(j==1&&k==0))
      {
         rslt =  cmpBss01();
      }else if((j==3&&k==2)||(j==2&&k==3))
      {
         rslt =  cmpBss32();
      }else if((j==4&&k==5)||(j==5&&k==4))
      {
         rslt =  cmpBss45();
      }else if((j==7&&k==6)||(j==6&&k==7))
      {
         rslt =  cmpBss76();
      }else if((j==0&&k==2)||(j==2&&k==0))
      {
         rslt =  cmpBss02();
      }else if((j==4&&k==6)||(j==6&&k==4))
      {
         rslt =  cmpBss46();
      }else if((j==1&&k==3)||(j==3&&k==1))
      {
         rslt =  cmpBss13();
      }else if((j==5&&k==7)||(j==7&&k==5))
      {
         rslt =  cmpBss57();
      }else if((j==0&&k==3)||(j==3&&k==0))
      {
         rslt =  cmpBss03();
      }else if((j==1&&k==2)||(j==2&&k==1))
      {
         rslt =  cmpBss12();
      }else if((j==4&&k==7)||(j==7&&k==4))
      {
         rslt =  cmpBss47();
      }else if((j==5&&k==6)||(j==6&&k==5))
      {
         rslt =  cmpBss56();
      }else if((j==0&&k==4)||(j==4&&k==0))
      {
         rslt =  cmpBss04();
      }else if((j==1&&k==5)||(j==5&&k==1))
      {
         rslt =  cmpBss15();
      }else if((j==3&&k==7)||(j==7&&k==3))
      {
         rslt =  cmpBss37();
      }else if((j==2&&k==6)||(j==6&&k==2))
      {
         rslt =  cmpBss26();
      }else if((j==0&&k==5)||(j==5&&k==0))
      {
         rslt =  cmpBss05();
      }else if((j==3&&k==6)||(j==6&&k==3))
      {
         rslt =  cmpBss36();
      }else if((j==1&&k==4)||(j==4&&k==1))
      {
         rslt =  cmpBss14();
      }else if((j==2&&k==7)||(j==7&&k==2))
      {
         rslt =  cmpBss27();
      }else if((j==0&&k==6)||(j==6&&k==0))
      {
         rslt =  cmpBss06();
      }else if((j==3&&k==5)||(j==5&&k==3))
      {
         rslt =  cmpBss35();
      }else if((j==1&&k==7)||(j==7&&k==1))
      {
         rslt =  cmpBss17();
      }else if((j==4&&k==2)||(j==2&&k==4))
      {
         rslt =  cmpBss24();
      }else if((j==0&&k==7)||(j==7&&k==0))
      {
         rslt =  cmpBss07();
      }else if((j==1&&k==6)||(j==6&&k==1))
      {
         rslt =  cmpBss16();
      }else if((j==4&&k==3)||(j==3&&k==4))
      {
         rslt =  cmpBss34();
      }else if((j==2&&k==5)||(j==5&&k==2))
      {
         rslt =  cmpBss25();
      }
      return rslt;
   }
   public double cmpBss01()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -4*lx*ly*ly*ly*lz*lz*lz -
                    2*lx*lx*lx*ly*lz*lz*lz -
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss01() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss32()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  4*lx*ly*ly*ly*lz*lz*lz -
                   -2*lx*lx*lx*ly*lz*lz*lz -
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss01() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss45()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  4*lx*ly*ly*ly*lz*lz*lz -
                    2*lx*lx*lx*ly*lz*lz*lz -
                   -2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss01() rslt = "+rslt);
      return rslt;
   }

   public double cmpBss76()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  -4*lx*ly*ly*ly*lz*lz*lz -
                     2*lx*lx*lx*ly*lz*lz*lz -
                     2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss01() rslt = "+rslt);
      return rslt;
   }

   public double cmpBss02()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                    lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss02() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss46()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  -2*lx*ly*ly*ly*lz*lz*lz +
                    -2*lx*lx*lx*ly*lz*lz*lz +
                    lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss02() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss13()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                    lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss02() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss57()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  -2*lx*ly*ly*ly*lz*lz*lz +
                    -2*lx*lx*lx*ly*lz*lz*lz +
                    lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss02() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss03()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -2*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                   -2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss03() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss12()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -2*lx*ly*ly*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss03() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss36()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -2*lx*ly*ly*ly*lz*lz*lz +
                    1*lx*lx*lx*ly*lz*lz*lz +
                   -2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss03() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss56()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss56() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss47()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*lz*lz*lz +
                   -2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss03() rslt = "+rslt);
      return rslt;
   } 
   public double cmpBss04()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -2*lx*ly*ly*ly*lz*lz*lz +
                   -2*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss04() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss37()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                   -2*lx*lx*lx*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss04() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss15()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -2*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                    4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss04() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss26()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                   -4*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss04() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss05()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    1*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss05() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss14()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  2*lx*ly*ly*ly*lz*lz*lz +
                    1*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss05() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss27()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  -2*lx*ly*ly*ly*lz*lz*lz +
                    1*lx*lx*lx*ly*lz*lz*lz +
                    -2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss05() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss06()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -1*lx*ly*ly*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss06() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss07()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  1*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss07() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss34()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  1*lx*ly*ly*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss07() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss16()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  1*lx*ly*ly*ly*lz*lz*lz +
                    -2*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss07() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss25()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 =  1*lx*ly*ly*ly*lz*lz*lz +
                    -2*lx*lx*lx*ly*lz*lz*lz +
                    2*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss07() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss35()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -1*lx*ly*ly*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss06() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss17()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -1*lx*ly*ly*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss06() rslt = "+rslt);
      return rslt;
   }
   public double cmpBss24()
   {
      double lx = x1-x0;
      double ly = y1-y0;
      double lz = z1-z0;
      double vlm=Math.abs(lx*ly*lz);
      double c1 = 36;
      double sm1 = -1*lx*ly*ly*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*lz*lz*lz +
                   -1*lx*lx*lx*ly*ly*ly*lz;
      double rslt = sm1/(c1*vlm);
      //System.out.println("HxhdrlBss.cmpBss06() rslt = "+rslt);
      return rslt;
   }*/
}