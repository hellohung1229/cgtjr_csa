package cgtjr.academics.elctrclengnrng.videotmp;


import cgtjr.academics.math.lnralgbra.Matrix;

public class CornerDetect_2 extends GrdntFilter_2
{
   private int gradientXSqrd[];
   private int gradientYSqrd[];
   private int gradientXY[];
   //private int greyStructure[][];
   private int crnrAndOrgnl[];
   private int cornerData[];

   private int threshold = 300;

   private int neighborHood[][];

   //double cornerMatrix[][];
   //double crnrMtrx9x9[][];
   private double cornerMtrx[][][];

   //private double answer[];
   //private Object anObject[] = new Object[3];
   private boolean isCornerRegion[];
   private int cornerRegionIndx[];
   private int originalData[];
   private boolean checkRegion=true;
   private int cornerIndex[];
   private int cornerCount;
   private int fltrIndex;
   private int fltrGryVls[];

   private double eigenValues[];
   private double crnrMtrx[][];

   public void initialize(int myImageWidth, int myImageHeight)
   {
      //super(new SobelKernel());
      super.initialize(myImageWidth,myImageHeight );
      int aLength = myImageHeight*myImageWidth;
      //cornerMatrix = new double[2][2];
      //crnrMtrx9x9 = new double[2][2];
      cornerData = new int[aLength];
      crnrAndOrgnl = new int[aLength];
      gradientXSqrd = new int[aLength];
      gradientYSqrd = new int[aLength];
      gradientXY = new int[aLength];
      ///eigenVector = new Vector[3];
      isCornerRegion = new boolean[aLength];
      cornerRegionIndx = new int[aLength];
      eigenValues = new double[aLength];
      cornerIndex = new int[aLength];
      originalData = getOrgnlValues();
      ///cornerIndex = new int[aLength];
      cornerCount = 0;
      cornerMtrx = new double[aLength][2][2];
      ///eigenValues = new double[aLength];
      crnrMtrx = new double[2][2];
   }
   
   public void setThreshold(int myThreshold)
   {
      threshold = myThreshold;
   }
   public int getThreshold()
   {
      return threshold;
   }
   /*
   public double[][] getCornerMatrix()
   {
      return this.cornerMatrix;
   }*/
   public int[] getFltrdData()
   {
      return crnrAndOrgnl;
   }
   public int[] getCornerData()
   {
      return cornerData;
   }
   public void setCornerData(int myPixelData[])
   {
      cornerData = myPixelData;
   }
   public int getCornerRegionIndx(int myIndex1)
   {
      return cornerRegionIndx[myIndex1];
   }
   public int getCornerCount()
   {
      return cornerCount;
   }
   public void setCornerRegion(int i)
   {
      setCornerRegion(i,true);
   }
   public void setCornerRegionIndx(int i)
   {
     setCornerRegionIndx(i,i);
   }
   public void setCornerRegionIndx(int i,int myValue)
   {
      int imageWidth = getImageWidth();
      if(isInBounds3x3(i))
      {
         cornerRegionIndx[i-imageWidth-1] = myValue;
         cornerRegionIndx[i-imageWidth] = myValue;
         cornerRegionIndx[i-imageWidth+1] = myValue;
         cornerRegionIndx[i-1] = myValue;
         cornerRegionIndx[i] = myValue;
         cornerRegionIndx[i+1] = myValue;
         cornerRegionIndx[i+imageWidth-1] = myValue;
         cornerRegionIndx[i+imageWidth] = myValue;
         cornerRegionIndx[i+imageWidth+1] = myValue;
      }
   }
   public void filter(int myGrayValues[],int i)
   {
      //fltrIndex = i;
      //fltrGryVls = myGrayValues;

      this.crnrAndOrgnl[i] = myGrayValues[i];
     //crnrAndOrgnl = grayValues;
      cornerDetect(myGrayValues,i);


   }

   /*
   public void cornerDetect9x9(int myGrayValues[],int anIndex)
   {
      int aWidthx3 = 3*getImageWidth();
      cornerDetect3x3(myGrayValues,anIndex-aWidthx3-3);//change 1 to 3 ??!
      cornerDetect3x3(myGrayValues,anIndex-aWidthx3);
      cornerDetect3x3(myGrayValues,anIndex-aWidthx3+3);
      cornerDetect3x3(myGrayValues,anIndex-3);
      cornerDetect3x3(myGrayValues,anIndex);
      cornerDetect3x3(myGrayValues,anIndex+3);
      cornerDetect3x3(myGrayValues,anIndex+aWidthx3-3);
      cornerDetect3x3(myGrayValues,anIndex+aWidthx3);
      cornerDetect3x3(myGrayValues,anIndex+aWidthx3+3);
   }
   public void cornerDetect3x3(int myGrayValues[],int anIndex)
   {
      int aWidth = getImageWidth();
      cornerDetect(myGrayValues,anIndex-aWidth-1);
      cornerDetect(myGrayValues,anIndex-aWidth);
      cornerDetect(myGrayValues,anIndex-aWidth+1);
      cornerDetect(myGrayValues,anIndex-1);
      cornerDetect(myGrayValues,anIndex);
      cornerDetect(myGrayValues,anIndex+1);
      cornerDetect(myGrayValues,anIndex+aWidth-1);
      cornerDetect(myGrayValues,anIndex+aWidth);
      cornerDetect(myGrayValues,anIndex+aWidth+1);
   }*/

   public void cornerDetect(int grayValues[],int i)
   {
      
      if(isInBounds3x3(i) == true)
      {
         //super.filter(grayValues,i);
         cornerMtrx[i] = updateCornerMatrix(grayValues,i);
         solveEigenValues(cornerMtrx[i],i);
      }
   }
   public void cornerDetect9x9(int grayValues[],int i)
   {
      if(isInBounds3x3(i) == true)
      {
         //super.grdntFilter9x9(grayValues,i);
         cornerMtrx[i] = updtCrnrMtrx9x9(grayValues,i);
         solveEigenValues(cornerMtrx[i],i);
      }
   }
   public double[][] updtCrnrMtrx9x9(int myGryVls[],int anIndex)
   {
      double crnrMtrxTmp[][] = new double[2][2];
      double crnrMtrx9x9[][] = new double[2][2];
      int aWidthx3 = 3*getImageWidth();
      //crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex-aWidthx3-3);
      //crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9,crnrMtrxTmp);
      crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex-aWidthx3);
      crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      //crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex-aWidthx3+3);
      //crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex-3);
      crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex);
      crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex+3);
      crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      //crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex+aWidthx3-3);
      //crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex+aWidthx3);
      crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);
      //crnrMtrxTmp = updateCornerMatrix(myGryVls,anIndex+aWidthx3+3);
      //crnrMtrx9x9 = Matrix.add2x2(crnrMtrx9x9, crnrMtrxTmp);

      //int x = rtrvXPstn(anIndex);
      //int y = rtrvYPstn(anIndex);
      //System.out.println("CornerDetect: x = "+x+", y = "+y);
      //Matrix.print(crnrMtrx9x9);
      //cornerMtrx[anIndex][0][0] = crnrMtrx9x9[0][0];
      //cornerMtrx[anIndex][0][1] = crnrMtrx9x9[0][1];
      //cornerMtrx[anIndex][1][0] = crnrMtrx9x9[1][0];
      //cornerMtrx[anIndex][1][1] = crnrMtrx9x9[1][1];

      return crnrMtrx9x9;
   }
   /*
   public double[][] updateCornerMatrix(int myGryVls[],int j)
   {
      fltrIndex = j;
      fltrGryVls = myGryVls;
      //Thread aThread = new Thread(this);
      //aThread.start();
      run();
      return crnrMtrx;
   }
   public void run()
   {
      crnrMtrx = updateCornerMatrix1(fltrGryVls,fltrIndex);
   }*/
   public double[][] updateCornerMatrix(int myGryVls[],int j)
   {
      super.grdntFilter(myGryVls, j);
      double cornerMatrix[][] = new double[2][2];
      int imageWidth = getImageWidth();
      int imageHeight = getImageHeight();
      int aGradientHor[] = this.getGrdntHrzntl();
      int aGradientVer[] = this.getGrdntVrtcl();
      //int imageLengthI = imageWidth*imageHeight;

      if(isInBounds3x3(j))
      {
         gradientXSqrd[j] =
            aGradientHor[j-imageWidth-1]*aGradientHor[j-imageWidth-1]+
            aGradientHor[j-imageWidth]*aGradientHor[j-imageWidth]+
            aGradientHor[j-imageWidth+1]*aGradientHor[j-imageWidth+1]+
            aGradientHor[j-1]*aGradientHor[j-1]+
            aGradientHor[j]*aGradientHor[j]+
            aGradientHor[j+1]*aGradientHor[j+1]+
            aGradientHor[j+imageWidth-1]*aGradientHor[j+imageWidth-1]+
            aGradientHor[j+imageWidth]*aGradientHor[j+imageWidth]+
            aGradientHor[j+imageWidth+1]*aGradientHor[j+imageWidth+1];

         gradientYSqrd[j] =
            aGradientVer[j-imageWidth-1]*aGradientVer[j-imageWidth-1]+
            aGradientVer[j-imageWidth]*aGradientVer[j-imageWidth]+
            aGradientVer[j-imageWidth+1]*aGradientVer[j-imageWidth+1]+
            aGradientVer[j-1]*aGradientVer[j-1]+
            aGradientVer[j]*aGradientVer[j]+
            aGradientVer[j+1]*aGradientVer[j+1]+
            aGradientVer[j+imageWidth-1]*aGradientVer[j+imageWidth-1]+
            aGradientVer[j+imageWidth]*aGradientVer[j+imageWidth]+
            aGradientVer[j+imageWidth+1]*aGradientVer[j+imageWidth+1];

         gradientXY[j] =
            aGradientVer[j-imageWidth-1]*aGradientHor[j-imageWidth-1]+
            aGradientVer[j-imageWidth]*aGradientHor[j-imageWidth]+
            aGradientVer[j-imageWidth+1]*aGradientHor[j-imageWidth+1]+
            aGradientVer[j-1]*aGradientHor[j-1]+
            aGradientVer[j]*aGradientHor[j]+
            aGradientVer[j+1]*aGradientHor[j+1]+
            aGradientVer[j+imageWidth-1]*aGradientHor[j+imageWidth-1]+
            aGradientVer[j+imageWidth]*aGradientHor[j+imageWidth]+
            aGradientVer[j+imageWidth+1]*aGradientHor[j+imageWidth+1];

            cornerMatrix[0][0] = gradientXSqrd[j];
            cornerMatrix[0][1] = gradientXY[j];
            cornerMatrix[1][0] = gradientXY[j];
            cornerMatrix[1][1] = gradientYSqrd[j];
            /*
            System.out.println("CornerDetect: cornerMatrix[j][0]"+
                                              cornerMatrix[j][0]+","+
                                              cornerMatrix[j][1]+","+
                                              cornerMatrix[j][2]+","+
                                              cornerMatrix[j][3]);
            */
         }
      //cornerMtrx[j][0][0] = cornerMatrix[0][0];
      //cornerMtrx[j][0][1] = cornerMatrix[0][1];
      //cornerMtrx[j][1][0] = cornerMatrix[1][0];
      //cornerMtrx[j][1][1] = cornerMatrix[1][1];

      return cornerMatrix;
   }
   public double[][][] getCornerMtrx()
   {
      return cornerMtrx;
   }
   public double[][] getCornerMtrx(int myIndex)
   {
      return cornerMtrx[myIndex];
   }

   /*
   public void slvEgenVls(int i)
   {
      solveEigenValues(cornerMatrix,i);
   }
   public void solveEigenValues(int i)
   {
      solveEigenValues(cornerMatrix,i);
   }*/
   public void solveEigenValues(double myCrnrMtrx[][],int i)
   {
         double anAnswer[] = solveEigenValues(myCrnrMtrx);
         //System.out.println("CornerDetect: answer[1] = "+anAnswer[1]+",i="+i+",threshold = "+threshold+",checkregion="+checkRegion);

         if(anAnswer[1] >= threshold)
         {
            //if(checkRegion == false)
            //{
               eigenValues[i] = anAnswer[1];
               //updtCornerPixels(i);
               //System.out.println("CornerDetect: eigenValues[i] = "+eigenValues[i]+",i="+i+",threshold = "+threshold+",checkregion="+checkRegion);
               //cornerIndex[cornerCount] = i;
               updtCrnrAndOrgnlPxls(i);
               //cornerCount++;
            //}else if(!isInCornerRegion(i))
            //{
               //updtCrnrAndOrgnlPxls(i);
               //setCornerRegion(i);
               //eigenValues[i] = anAnswer[1];
               //System.out.println("CornerDetect: eigenValues[i] = "+eigenValues[i]+",i="+i+",threshold = "+threshold+",checkregion="+checkRegion);
               //cornerIndex[cornerCount] = i;
               //cornerCount++;
            //}
            //updtCrnrAndOrgnlPxls(i);
            //setCornerRegion(i);
            //updtCornerPixels(i);
         }
   }
   public void setCheckRegion(boolean myCheckRegion)
   {
      checkRegion = myCheckRegion;
   }
   public int getCornerIndex(int myIndex)
   {
      return cornerIndex[myIndex];
   }
   public void setEigenValue(int myIndex,double myValue)
   {
      eigenValues[myIndex] = myValue;
   }
   public double getEigenValue(int myIndex)
   {
      return eigenValues[myIndex];
   }
   public void setCornerRegion(int i,boolean myTorF)
   {
      if(isInBounds3x3(i))
      {
         int imageWidth = getImageWidth();
         isCornerRegion[i-imageWidth-1] = myTorF;
         isCornerRegion[i-imageWidth] = myTorF;
         isCornerRegion[i-imageWidth+1] = myTorF;
         isCornerRegion[i-1] = myTorF;
         isCornerRegion[i] = myTorF;
         isCornerRegion[i+1] = myTorF;
         isCornerRegion[i+imageWidth-1] = myTorF;
         isCornerRegion[i+imageWidth] = myTorF;
         isCornerRegion[i+imageWidth+1] = myTorF;
      }
   }
   public boolean isInCornerRegion(int myIndex1)
   {
      boolean inBounds = false;

      if(isCornerRegion[myIndex1] == true)
      {
         inBounds = true;;
      }
      return inBounds;
   }
   public double[] solveEigenValues(double aCornerMatrix[][])
   {
      double answer[] = new double[2];
      double answerTmp1 = 0.0;
      double answerTmp2 = 0.0;
      int secOrdPart = 1;
      double firstOrdPart = -1*(aCornerMatrix[0][0]+aCornerMatrix[1][1]);
      double constPart  = aCornerMatrix[0][0]*aCornerMatrix[1][1] - aCornerMatrix[0][1]*aCornerMatrix[1][0];
      double sqrtPart = Math.sqrt(firstOrdPart*firstOrdPart - 4*secOrdPart*constPart);
      answerTmp1 = (-1*firstOrdPart + sqrtPart)/(2*secOrdPart);
      answerTmp2 = (-1*firstOrdPart - sqrtPart)/(2*secOrdPart);
      if(answerTmp1 >= answerTmp2)
      {
          answer[0] = answerTmp1;
          answer[1] = answerTmp2;
      }else{
          answer[0] = answerTmp2;
          answer[1] = answerTmp1;
      }
      //System.out.println("answer1 = "+answer[0]+" answer2 = "+answer[1]);
      return answer;
   }
   /*
   public double[] solveEigenValues(double aCornerMatrix[])
   {
      double answer[] = new double[2];
      double answerTmp1 = 0.0;
      double answerTmp2 = 0.0;
      int secOrdPart = 1;
      double firstOrdPart = -1*(aCornerMatrix[0]+aCornerMatrix[3]);
      double constPart  = aCornerMatrix[0]*aCornerMatrix[3] - aCornerMatrix[1]*aCornerMatrix[2];
      double sqrtPart = Math.sqrt(firstOrdPart*firstOrdPart - 4*secOrdPart*constPart);
      answerTmp1 = (-1*firstOrdPart + sqrtPart)/(2*secOrdPart);
      answerTmp2 = (-1*firstOrdPart - sqrtPart)/(2*secOrdPart);
      if(answerTmp1 >= answerTmp2)
      {
          answer[0] = answerTmp1;
          answer[1] = answerTmp2;
      }else{
          answer[0] = answerTmp2;
          answer[1] = answerTmp1;
      }
      //System.out.println("answer1 = "+answer[0]+" answer2 = "+answer[1]);
      return answer;
   }*/
   public void updtCrnrAndOrgnlPxls(int i)
   {
   
      int imageWidth = getImageWidth();
      if(isInBounds3x3(i))
      {
         crnrAndOrgnl[i-imageWidth-1] = 0x00ff0000;
         crnrAndOrgnl[i-imageWidth] = 0x00ff0000;
         crnrAndOrgnl[i-imageWidth+1] = 0x00ff0000;
         crnrAndOrgnl[i-1] = 0x00ff0000;
         crnrAndOrgnl[i] = 0x0000ff00;
         crnrAndOrgnl[i+1] = 0x00ff0000;
         crnrAndOrgnl[i+imageWidth-1] = 0x00ff0000;
         crnrAndOrgnl[i+imageWidth] = 0x00ff00ff;
         crnrAndOrgnl[i+imageWidth+1] = 0x00ff00ff;
      }
   }
   public void updtCornerPixels(int i)
   {
      int imageWidth = getImageWidth();
      if(isInBounds3x3(i))
      {
         cornerData[i-imageWidth-1] = 0x00ff0000;
         cornerData[i-imageWidth] = 0x00ff0000;
         cornerData[i-imageWidth+1] = 0x00ff0000;
         cornerData[i-1] = 0x00ff0000;
         cornerData[i] = 0x0000ff00;
         cornerData[i+1] = 0x00ff0000;
         cornerData[i+imageWidth-1] = 0x00ff0000;
         cornerData[i+imageWidth] = 0x00ff00ff;
         cornerData[i+imageWidth+1] = 0x00ff00ff;
      }
   }

   /*
   public void finishPrsng()
   {
      prvsEigenValues = getPrvsEigenValues();
      setPrvsCrnrCount(0);
   }*/
}