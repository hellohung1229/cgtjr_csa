package cgtjr.academics.elctrclengnrng.videotmp;

public class GrdntFilter extends ClrCnvrtFilter
{
   private int transparency = 0;
   private int red = 0;
   private int green = 0;
   private int blue = 0;
   private int rgbColors[];
   private int rgbValues[];
   private int orgnlValues[];
   private int grayValues[];
   private int grdntVrtcl[];
   private int grdntHrzntl[];
   private int grdntHVTtl;
   private int grdntVrtclSqrdTtl;
   private int grdntHrzntlSqrdTtl;
   private int grdntVrtclTtl;
   private int grdntHrzntlTtl;
   private int grdntMgntd[];
   private int grdntMgntdSqrdAvg;
   private int grdntMgntdSqrdTtl;
   private int grdntMgntdAvg;
   private int grdntMgntdTtl;
   private int grayValuesTtl;
   private int grayValuesSqrdTtl;
   private double ornttnAngle[];
   private int imageWidth;
   private int imageHeight;
   private int count; 
   private GenericHVKrnl fltrGenericHVKrnl;
   private int krnlHrzntl[][];
   private int krnlVrtcl[][];
   private int imageLength;


   public GrdntFilter()
   {
      GenericHVKrnl aGenericHVKrnl = new GenericHVKrnl();
      krnlHrzntl = aGenericHVKrnl.getHrzntlKrnl();
      krnlVrtcl = aGenericHVKrnl.getVrtclKrnl();
   }
   public GrdntFilter(GenericHVKrnl myGenericHVKrnl,int myImageWidth,int myImageHeight)
   {
      //super(myImageWidth,myImageHeight);
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      orgnlValues = new int[myImageWidth*myImageHeight];
      krnlHrzntl = myGenericHVKrnl.getHrzntlKrnl();
      krnlVrtcl = myGenericHVKrnl.getVrtclKrnl();
      rgbColors = new int[myImageWidth*myImageHeight];
      rgbValues = new int[myImageWidth*myImageHeight];
      //grayValues = new int[myImageWidth*myImageHeight];
      grdntVrtcl = new int[myImageWidth*myImageHeight];
      grdntHrzntl = new int[myImageWidth*myImageHeight];
      grdntMgntd = new int[myImageWidth*myImageHeight];
      ornttnAngle = new double[myImageWidth*myImageHeight];
      imageLength = myImageWidth * myImageHeight;
      transparency = 0;
      red = 0;
      green = 0;
      blue = 0;
   }
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth,myImageHeight);
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      orgnlValues = new int[myImageWidth*myImageHeight];
      rgbColors = new int[myImageWidth*myImageHeight];
      rgbValues = new int[myImageWidth*myImageHeight];
      grayValues = getFltrdData();
      grdntVrtcl = new int[myImageWidth*myImageHeight];
      grdntHrzntl = new int[myImageWidth*myImageHeight];
      grdntMgntd = new int[myImageWidth*myImageHeight];
      ornttnAngle = new double[myImageWidth*myImageHeight];
      imageLength = myImageWidth * myImageHeight;

      frameIndex = frameIndex + 1;
      //System.out.println("GrdntFilter: frame = "+frameIndex);

   }
   public void start(int myImageWidth,int myImageHeight)
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
      imageLength = myImageWidth * myImageHeight;
   }
   public void filter(int myIntlValues[],int i)
   {
      super.filter(myIntlValues, i);
      orgnlValues[i] = myIntlValues[i];
      grayValues = super.getFltrdData();
      grdntFilter(grayValues,i);
   }
   public void grdntFilter(int myGrayValues[],int i)
   {
      if(isInBounds3x3(i))
      {
         //System.out.println("GrdntFilter: myGrayValues["+i+"] = "+myGrayValues[i]);
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


            //if(grdntMgntd[i] < 1000000000)
            //{
            //  grdntMgntd[i] = 0;
            //}
            //if(grdntMgntd[i] > 255)
            //{
               //grdntMgntd[i] = 255;
            //}
            //grdntMgntd[i] = (int) Math.abs((grdntHrzntl[i]) + (grdntVrtcl[i]));
            //System.out.println("GntFilter: grdntHrzntl[i]+","+grdntVrtcl[i]+","+grdntMgntd[i]);
            grdntMgntdTtl += grdntMgntd[i];
            grayValuesTtl += (myGrayValues[i] & 0x000000ff);
            grayValuesSqrdTtl += (myGrayValues[i] & 0x000000ff)*(myGrayValues[i] & 0x000000ff);
            grdntMgntdSqrdTtl +=  grdntMgntd[i]*grdntMgntd[i];

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
      double aValue = Math.sqrt((grdntMgntdSqrdTtl - grdntMgntdTtl*grdntMgntdTtl)/count);
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
      return rgbValues;
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
      return rgbValues;
   }
   public int getGrdntHVTtl()
   {
      return grdntHVTtl;
   }
   public int[] getOrgnlValues()
   {
      return orgnlValues;
   }   
}