package cgtjr.academics.elctrclengnrng.videotmp;

import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntKrnl;

public class GrdntFilter_2 extends ClrCnvrtFilter
{
   private static int rgbColors[];
   private static int rgbValues[];
   private static int orgnlValues[];
   private static int grayValues[];
   private static int grdntVrtcl[];
   private static int grdntHrzntl[];
   private static int grdntHVTtl;
   private static int grdntVrtclSqrdTtl;
   private static int grdntHrzntlSqrdTtl;
   private static int grdntVrtclTtl;
   private static int grdntHrzntlTtl;
   private static int grdntMgntd[];
   private static int grdntMgntdSqrdAvg;
   private static int grdntMgntdSqrdTtl;
   private static int grdntMgntdAvg;
   private static int grdntMgntdTtl;
   private static int grayValuesTtl;
   private static int grayValuesSqrdTtl;
   private static double ornttnAngle[];
   //private static int imageWidth;
   //private static int imageHeight;
   private static int count;
   private static int krnlHrzntl[][];
   private static int krnlVrtcl[][];
   private static int imageLength;
   private static double grdHrzUnit[];
   private static double grdVrtUnit[];
   private static int myGrayValues[] ;
   private static int intlzNmbr;
      GenericHVKrnl aGenericHVKrnl ;
   public GrdntFilter_2()
   {
      aGenericHVKrnl = new GenericHVKrnl();
      krnlHrzntl = aGenericHVKrnl.getHrzntlKrnl();
      krnlVrtcl = aGenericHVKrnl.getVrtclKrnl();
   }
   /*
   public GrdntFilter(String myFileName)
   {
      super(myFileName);
   }
   private void cnstrctr()
   {
      initialize(getImageHeight(),getImageWidth());
   }*/
   public void initialize(int myWidth, int myHeight)
   {
      super.initialize(myWidth,  myHeight);
      int aWidth = myWidth;
      int aHeight = myHeight;

      //if(intlzNmbr == 0)
      //{


      orgnlValues = new int[aWidth*aHeight];
      krnlHrzntl = aGenericHVKrnl.getHrzntlKrnl();
      krnlVrtcl = aGenericHVKrnl.getVrtclKrnl();
      rgbColors = new int[aWidth*aHeight];
      rgbValues = new int[aWidth*aHeight];
      grdntVrtcl = new int[aWidth*aHeight];
      grdntHrzntl = new int[aWidth*aHeight];
      grdntMgntd = new int[aWidth*aHeight];
      grdHrzUnit = new double[aWidth*aHeight];
      grdVrtUnit = new double[aWidth*aHeight];
      ornttnAngle = new double[aWidth*aHeight];
      imageLength = aWidth * aHeight;
      ++intlzNmbr;
      //}
      frameIndex++;
   }
   /*
   public void startPrsng(int myImageHeight,int myImageWidth)
   {
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      orgnlValues = new int[myImageWidth*myImageHeight];
      rgbColors = new int[myImageWidth*myImageHeight];
      rgbValues = new int[myImageWidth*myImageHeight];
      //grayValues = new int[myImageWidth*myImageHeight];
      grdntVrtcl = new int[myImageWidth*myImageHeight];
      grdntHrzntl = new int[myImageWidth*myImageHeight];
      grdntMgntd = new int[myImageWidth*myImageHeight];
      ornttnAngle = new double[myImageWidth*myImageHeight];
      grdHrzUnit = new double[myImageWidth*myImageHeight];
      grdVrtUnit = new double[myImageWidth*myImageHeight];
      imageLength = myImageWidth * myImageHeight;
   }*/
   public void filter(int myIntlValues[],int i)
   {
      //super.filter(myIntlValues, i);
      //orgnlValues[i] = myIntlValues[i];
      //grayValues = super.getFltrdData();
      grdntFilter3x3(myIntlValues,i);
   }
   public void filter1x1(int myIntlValues[],int i)
   {
      grdntFilter(myIntlValues,i);
   }
   public void filter3x3(int myX,int myY)
   {
      int anIndex = rtrvIndex(myX,myY);
      filter3x3(anIndex);
   }
   public void filter3x3(int myIndex)
   {
      int pixelData[] = getInptPxlData();
      filter3x3(pixelData,myIndex);
   }
   public void filter3x3(int myIntlValues[],int i)
   {
      grdntFilter3x3(myIntlValues,i);
   }
   public void grdntFilter(int myIntlValues[],int i)
   {
      grdntFilter(myIntlValues,i,getImageWidth(),getImageHeight());
   }
   public void grdntFilter(int myIntlValues[],int i,int imageWidth,int imageHeight)
   {

      //gryFltr3x3(myIntlValues,i);
      super.filter(myIntlValues, i);
      orgnlValues[i] = myIntlValues[i];
      //myGrayValues = getGryVls();
      //myGrayValues = getGrayValues();
      myGrayValues = super.getFltrdData();
      if(isInBounds3x3(i))
      {
         grdntHrzntl[i] = 
            myGrayValues[i-imageWidth-1]*krnlHrzntl[0][0]+
            myGrayValues[i-imageWidth]*krnlHrzntl[0][1]+
            myGrayValues[i-imageWidth+1]*krnlHrzntl[0][2]+
            myGrayValues[i-1]*krnlHrzntl[1][0]+
            myGrayValues[i]*krnlHrzntl[1][1]+
            myGrayValues[i+1]*krnlHrzntl[1][2]+
            myGrayValues[i+imageWidth-1]*krnlHrzntl[2][0]+
            myGrayValues[i+imageWidth]*krnlHrzntl[2][1]+
            myGrayValues[i+imageWidth+1]*krnlHrzntl[2][2];

            //if(grdntHrzntl[i] < 10)
            //{
            //   grdntHrzntl[i] = 0;
            //}
            //System.out.println("grdntHrzntl["+i+"]:"+ColorCode.rtrv000000ff(myGrayValues[i-1])+","+
            //                   ColorCode.rtrv000000ff(myGrayValues[i])+","+
            //                   ColorCode.rtrv000000ff(myGrayValues[i+1]));

         //System.out.println("GrdntFilter: krnlHrzntl : "+krnlHrzntl[1][0]+","+krnlHrzntl[1][1]+","+krnlHrzntl[1][2]);
         grdntVrtcl[i] = 
            myGrayValues[i-imageWidth-1]*krnlVrtcl[0][0] +
            myGrayValues[i-imageWidth]*krnlVrtcl[0][1]  +
            myGrayValues[i-imageWidth+1]*krnlVrtcl[0][2]  +
            myGrayValues[i-1]*krnlVrtcl[1][0]  +
            myGrayValues[i]*krnlVrtcl[1][1]    +
            myGrayValues[i+1]*krnlVrtcl[1][2]  +
            myGrayValues[i+imageWidth-1]*krnlVrtcl[2][0]  +
            myGrayValues[i+imageWidth]*krnlVrtcl[2][1]  +
            myGrayValues[i+imageWidth+1]*krnlVrtcl[2][2];

            //if(grdntVrtcl[i] < 10)
            //{
            //   grdntVrtcl[i] = 0;
            //}
            //System.out.println("grdntVrtcl["+i+"]:"+grdntVrtcl[i]+", "+ColorCode.rtrv000000ff(myGrayValues[i-imageWidth])+
            //                  ","+ColorCode.rtrv000000ff(myGrayValues[i])+","+
            //                  ColorCode.rtrv000000ff(myGrayValues[i+imageWidth]));
            //System.out.println("krnlVrtcl: "+krnlVrtcl[0][1]+","+krnlVrtcl[1][1]+","+krnlVrtcl[2][1]);
            grdntHrzntlSqrdTtl += grdntHrzntl[i]*grdntHrzntl[i];
            grdntVrtclSqrdTtl += grdntVrtcl[i]*grdntVrtcl[i];
            grdntHrzntlTtl += Math.abs(grdntHrzntl[i]);           
            grdntVrtclTtl += Math.abs(grdntVrtcl[i]);

            //System.out.println("GrdntFilter: grdntHrzntlTtl = "+grdntHrzntlTtl );
            //System.out.println("GrdntFilter: grdntVrtclTtl = "+grdntVrtclTtl );

            grdntMgntd[i] = 
            (int) Math.sqrt(Math.abs((grdntHrzntl[i]*grdntHrzntl[i])+(grdntVrtcl[i]*grdntVrtcl[i])));
            /*
            try{
               grdHrzUnit[i] = (double)grdntHrzntl[i]/grdntMgntd[i];
            }catch(ArithmeticException ae){
            }
            try{
               grdVrtUnit[i] = (double)grdntVrtcl[i]/grdntMgntd[i];
            }catch(ArithmeticException ae){
            }*/
            //System.out.println("GrdnFltr: magnitude = "+grdntMgntd[i]);
            //if(grdntMgntd[i] < 1000000000)
            //{
            //  grdntMgntd[i] = 0;
            //}
            //if(grdntMgntd[i] > 255)
            //{
               //grdntMgntd[i] = 255;
            //}
            //grdntMgntd[i] = (int) Math.abs((grdntHrzntl[i]) + (grdntVrtcl[i]));
            //System.out.println("GntFilter: "+grdntHrzntl[i]+","+grdntVrtcl[i]+","+grdntMgntd[i]+", "+i);
            grdntMgntdTtl += grdntMgntd[i];

            grayValuesTtl += (myGrayValues[i] & 0x000000ff);
            grayValuesSqrdTtl += (myGrayValues[i] & 0x000000ff)*(myGrayValues[i] & 0x000000ff);
            grdntMgntdSqrdTtl +=  grdntMgntd[i]*grdntMgntd[i];
            //System.out.println("GrdntFilter: grdntMgntdSqrdTtl = "+grdntMgntdSqrdTtl+", grdntMgntdTtl = "+grdntMgntdTtl);
            grdntHVTtl +=  grdntVrtcl[i]*grdntHrzntl[i];

            ornttnAngle[i] = 
              Math.atan2(grdntHrzntl[i],grdntVrtcl[i]);
            //System.out.println("GradientFilter : ornttnAngle = "+ornttnAngle[i]);
    
            /*               
            if(grdntHrzntlSqrd[i] > 255)
            {
               grdntHrzntlSqrd[i] = 255;
            }    
            if(grdntVrtclSqrd[i] > 255)
            {
               grdntVrtclSqrd[i] = 255;
            }*/
         }            
         rgbValues[i] = (rgbColors[i] & 0x11000000) |
                        (grdntMgntd[i]<<16)   | 
                        (grdntMgntd[i]<<8)    | 
                        (grdntMgntd[i]);
                
      count++;
   }
   public void grdntFilter3x3(int myGrayValues[],int anIndex)
   {
       if(!isInBounds3x3(anIndex))
       {
          return;
       }
       int aWidth = getImageWidth();

       grdntFilter(myGrayValues,anIndex-aWidth-1);
       grdntFilter(myGrayValues,anIndex-aWidth);
       grdntFilter(myGrayValues,anIndex-aWidth+1);
       grdntFilter(myGrayValues,anIndex-1);
       grdntFilter(myGrayValues,anIndex);
       grdntFilter(myGrayValues,anIndex+1);
       grdntFilter(myGrayValues,anIndex+aWidth-1);
       grdntFilter(myGrayValues,anIndex+aWidth);
       grdntFilter(myGrayValues,anIndex+aWidth+1);
   }
   public void grdntFilter9x9(int myGrayValues[],int anIndex)
   {
       int aWidthx3 = 3*getImageWidth();
       grdntFilter3x3(myGrayValues,anIndex-aWidthx3-3);
       grdntFilter3x3(myGrayValues,anIndex-aWidthx3);
       grdntFilter3x3(myGrayValues,anIndex-aWidthx3+3);
       grdntFilter3x3(myGrayValues,anIndex-3);
       grdntFilter3x3(myGrayValues,anIndex);
       grdntFilter3x3(myGrayValues,anIndex+3);
       grdntFilter3x3(myGrayValues,anIndex+aWidthx3-3);
       grdntFilter3x3(myGrayValues,anIndex+aWidthx3);
       grdntFilter3x3(myGrayValues,anIndex+aWidthx3+3);
   }
   public void grdntFilter18x18(int myGrayValues[],int anIndex)
   {
      
      grdntFilter9x9(myGrayValues,anIndex);
      grdntFilter9x9(myGrayValues,anIndex);
      grdntFilter9x9(myGrayValues,anIndex);
      grdntFilter9x9(myGrayValues,anIndex);      
   }
   public void grdntFilter27x27(int myGrayValues[],int anIndex)
   {
       int aWidthx3 = 9*getImageWidth();
       grdntFilter9x9(myGrayValues,anIndex-aWidthx3-9);
       grdntFilter9x9(myGrayValues,anIndex-aWidthx3);
       grdntFilter9x9(myGrayValues,anIndex-aWidthx3+9);
       grdntFilter9x9(myGrayValues,anIndex-9);
       grdntFilter9x9(myGrayValues,anIndex);
       grdntFilter9x9(myGrayValues,anIndex+9);
       grdntFilter9x9(myGrayValues,anIndex+aWidthx3-9);
       grdntFilter9x9(myGrayValues,anIndex+aWidthx3);
       grdntFilter9x9(myGrayValues,anIndex+aWidthx3+9);
   }
   public double getHrzntlSqrdAvg()
   {
      double aValue = grdntHrzntlSqrdTtl/count;
      return aValue;
   }
   public double getVrtclSqrdAvg()
   {
      double aValue = grdntVrtclSqrdTtl/count;
      return aValue;
   }
   public double getHrzntlAvg()
   {
      double aValue = grdntHrzntlTtl/count;
      //System.out.println("GrdnFilter : grdntHrzntlTtl/count = "+aValue+", count = "+count);
      return aValue;
   }
   public double getVrtclAvg()
   {
      double aValue = grdntVrtclTtl/count;
      //System.out.println("GrdnFilter : grdntVrtclTtl/count ="+aValue+"count = "+count);
      return aValue;
   }
   public double rtrvExpcttn()
   {
      //System.out.println("GrdntFilter.rtrvExpcttn(): grdntMgntdTtl = "+grdntMgntdTtl+", grdntMgntdSqrdTtl = "+grdntMgntdSqrdTtl+",count = "+count);
      double aValue = Math.sqrt(grdntMgntdSqrdTtl/count - (grdntMgntdTtl*grdntMgntdTtl)/(count*count));

      return aValue;
   }
   public double rtrvExpctIllmnt()
   {
      double aValue1 = 6*Math.PI*Math.PI*grayValuesSqrdTtl/count;
      double aValue2 = 48*(grayValuesTtl*grayValuesTtl)/(count*count);
      double aValue3 = Math.sqrt(aValue1-aValue2);
      return aValue3;
   }
   public double rtrvGrdntMgntdAvg()
   {
      double aValue = grdntMgntdTtl / count;
      return aValue;
   }
   public double rtrvSlant()
   {
      double aValue1 = grayValuesTtl/count;
      double aValue2 = rtrvExpctIllmnt();
      double cosSigma = 4*aValue1/aValue2;
      double sigma = Math.acos(cosSigma);	
      return sigma;
   }
   public double rtrvTilt()
   {
      double aGrdntYAvg = getHrzntlAvg();
      double aGrdntXAvg = getVrtclAvg();
      
      double tanTau = aGrdntYAvg/aGrdntXAvg;
      double tau = Math.atan(tanTau);
      return tau;
   }
   public void displaySlntTlt()
   {
      double z = Math.sin(rtrvSlant());
      double y = Math.sin(rtrvTilt());
      double x = Math.cos(rtrvTilt());
      //System.out.println("slant = "+rtrvSlant()+", tilt = "+rtrvTilt()+"x = "+x+", y = "+y+", z = "+z);
   }
   public double[] rtrvIllmnt()
   {
      double aTilt = rtrvTilt();
      double aSlant = rtrvSlant();
      double x = Math.cos(aTilt)*Math.sin(aSlant);
      double y = Math.sin(aTilt)*Math.sin(aSlant);
      double z = Math.cos(aSlant);
      //System.out.println("GrdntFilter:x = "+x+", y = "+y+", z = "+z);
      double anIlluminant[] = {x,y,z};
      return anIlluminant;
   }
   public double rtrvAlbedo()
   {
      double aValue = rtrvExpctIllmnt()/Math.PI;
      return aValue;
   }
   public int[] getGrdnt()
   {
      return rgbValues;
   }
   public int[] getGrdntMgntd()
   {
      return grdntMgntd;
   }
   public void setGrdntMgntd(int myGrdntMgntd[])
   {
      grdntMgntd = myGrdntMgntd;
   }

   public int[] getGrdntHrzntl()
   {
      return grdntHrzntl;
   }
   public int[] getGrdntVrtcl()
   {
      return grdntVrtcl;
   }   
   public int[] getFltrdData()
   {
      //return myGrayValues;
      return grdntMgntd;
   }
   public int getGrdntHVTtl()
   {
      return grdntHVTtl;
   }
   public int[] getOrgnlValues()
   {
      return orgnlValues;
   }
   public double[] getGrdHrzUnit()
   {
      return grdHrzUnit;
   }
   public double[] getGrdVrtUnit()
   {
      return grdVrtUnit;
   }
   public void finish() {
      super.finish();
      //System.out.println("GrdntFilter: albedo = "+rtrvExpcttn());
   }

}