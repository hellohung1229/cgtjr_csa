/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activecontour;

/**
 *
 * @author cthomas
 */

import cgtjr.academics.elctrclengnrng.cv.CornerDetect;


public class ActiveContour extends CornerDetect//GrdntFilter
{
   private int contourData[];
   private int contourImage[];
   private int energyCntr[];
   private int energyCrv[];
   private int energyImg[];
   private int contourPntX[];
   private int contourPntY[];
   private boolean isContourPnt[];
   private int contourPnts[];
   private double alpha[];
   private double beta[];
   private double gamma[];
   private int itrtnIndex;
   private int contourIndex;
   private int contourCount;
   private int contourCount1;
   private int contourCount2;
   private int contourCount3;
   private int contourCount4;
   private int drvtv1X[];
   private int drvtv1Y[];
   private int drvtv2X[];
   private int drvtv2Y[];
   private int gradient[];
   private int krnlHrzntl1[][];
   private int krnlVrtcl1[][];
   private int krnlHrzntl2[][];
   private int krnlVrtcl2[][];
   private int minCrvE;
   private int crvIndex;
   private int minCntrE;
   private int cntrIndex;
   private int minGrdntE;
   private int grdntIndex;
   private double energyTtl;
   private int energyIndex;
   private boolean isInitiated;
   private CrclrQueCrdnt cntrCrclrQueCrdnt;
   private CrclrQueGrph crclrQueGrphCrdnts;
   private int state = 0;
   private double curveThreshold = .5;

   public ActiveContour()
   {
      Drvtv1Krnl aDrvtv1Krnl = new Drvtv1Krnl();
      krnlHrzntl1 = aDrvtv1Krnl.getHrzntlKrnl();
      krnlVrtcl1 = aDrvtv1Krnl.getVrtclKrnl();
      Drvtv2Krnl aDrvtv2Krnl = new Drvtv2Krnl();
      krnlHrzntl2 = aDrvtv2Krnl.getHrzntlKrnl();
      krnlVrtcl2 = aDrvtv2Krnl.getVrtclKrnl();
      //alpha = 1;
      //beta  = 1;
      //gamma = 1;
      itrtnIndex = -1;
   }
   public void setEnergyTtl(double myEnergyTtl)
   {
      energyTtl = myEnergyTtl;
   }
   public void setEnergyIndex(int myEnergyIndex)
   {
      energyIndex = myEnergyIndex;
   }
   public void setMinCrvE(int myMinCrvE)
   {
      minCrvE = myMinCrvE;
   }
   public void setCrvIndex(int myCrvIndex)
   {
      crvIndex = myCrvIndex;
   }
   public void setMinCntrE(int myMinCntrE)
   {
      minCntrE = myMinCntrE;
   }
   public void setCntrIndex(int myCntrIndex)
   {
      cntrIndex = myCntrIndex;
   }
   public void setMinGrdntE(int myMinGrdntE)
   {
      minGrdntE = myMinGrdntE;
   }
   public void setGrdntIndex(int myGrdntIndex)
   {
      grdntIndex = myGrdntIndex;
   }
   public int getEnergyIndex()
   {
      return energyIndex;
   }
   public int[] getFltrdData()
   {
      return contourImage;
   }
   public int getState()
   {
      return state;
   }
   public void setState(int myState)
   {
      state = myState;
   }
   public void updtTtlEnergy(double myEnergy,int myIndex)
   {
       if(myEnergy < energyTtl)
       {
           energyTtl = myEnergy;
           energyIndex = myIndex;
           crvIndex = myIndex;
           cntrIndex = myIndex;
       }
   }
   public void initialize(int myImageHeight,int myImageWidth)
   {
      super.initialize(myImageHeight,myImageWidth);
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      int aLength = myImageWidth*myImageHeight;

      contourPntX = contourPntX == null ? new int[aLength]: contourPntX;
      contourPntY = contourPntY == null ? new int[aLength]: contourPntY;
      isContourPnt = isContourPnt == null ? new boolean[aLength]:isContourPnt;
      contourData = contourData == null ? new int[aLength]:contourData;
      //contourData = super.getFltrdData();
      contourData = new int[aLength];
      contourPnts  = contourPnts == null ? new int[aLength]:contourPnts;
      cntrCrclrQueCrdnt = cntrCrclrQueCrdnt == null ? new CrclrQueCrdnt(contourPntX,contourPntY) : cntrCrclrQueCrdnt;
      crclrQueGrphCrdnts  = crclrQueGrphCrdnts == null ? new CrclrQueGrph(cntrCrclrQueCrdnt,contourData) : crclrQueGrphCrdnts;
      //contourImage  = new int[aLength];
      contourImage  = super.getFltrdData();

      alpha = (alpha == null) ? new double[aLength]: alpha;
      beta = (beta == null) ? new double[aLength]: beta;
      gamma = (gamma == null) ? new double[aLength] : gamma;
      state = 0;

      if(isInitiated == false)
      {
          createCircle();
          //createPoints();
          cntrCrclrQueCrdnt.setCount(contourCount);
          crclrQueGrphCrdnts.setWidth(myImageWidth);
          isInitiated = true;
      }
      contourIndex = 0;
      itrtnIndex++;
   }
   public void filter(int dataValues[],int i)
   {
      super.filter(dataValues,i);
      contourFilter(dataValues,i);
   }
   public void contourFilter(int dataValues[],int myDataIndex)
   {
      contourImage[myDataIndex] = dataValues[myDataIndex];
      if(isInBounds3x3(myDataIndex))
      {
         process(dataValues,myDataIndex);
         updateContourImage(dataValues,myDataIndex);
      }

   }
   public void updateContourImage(int myImageData[],int myDataIndex)
   {
      if(contourData[myDataIndex] == 0x000000)
      {
          contourImage[myDataIndex] = myImageData[myDataIndex];
      }else{
          contourImage[myDataIndex] = contourData[myDataIndex];
      }
          //System.out.println("ActiveContour : contourData = "+contourData[myDataIndex]+", imageData = "+myImageData[myDataIndex]+", contourImage = "+contourImage[myDataIndex]);
   }
   public void process(int myImageData[],int myImageIndex)
   {
      //if(state == 0 && itrtnIndex > 0 && getIsContourPnt(myDataIndex))
      if( itrtnIndex > 0 && updateCntrIndex(myImageIndex))
      {
         cmptTtlEnergy(contourIndex);

         updtcontourData(contourIndex);
         checkCurvature();
         increaseIndex();
/*
               try{
Thread.sleep(100);
          }catch(Exception e){
              
          }
 */
      }
   }
   public boolean updateCntrIndex(int myImageIndex)
   {
      boolean isUpdated = false;
      int x1 = contourPntX[contourIndex];
      int y1 = contourPntY[contourIndex];
      int anIndex = y1 * getImageWidth() + x1;

      if(anIndex < myImageIndex)
      {
         //contourIndex++;
         //if(contourIndex == contourCount)
         //{
             //contourIndex = 0;
         //}
         isUpdated = true;
      }
      return isUpdated;
   }
   public void increaseIndex()
   {
      contourIndex++;
      if(contourIndex == contourCount)
      {
         contourIndex = 0;
      }
   }
   public void setAlpha(int myIndex,int myValue)
   {
      alpha[myIndex] = myValue;
   }
   public void setBeta(int myIndex,int myValue)
   {
      beta[myIndex] = myValue;
   }
   public void setGamma(int myIndex,int myValue)
   {
      gamma[myIndex] = myValue;
   }
   public void createPoints()
   {
      int x0 = 40;
      int y0 = 50;
      int x1 = 140;
      int y1 = 50;
      int x2 = 140;
      int y2 = 200;
      int x3 = 40;
      int y3 = 200;

      inttcontourData(0,x0,y0);
      inttcontourData(1,x1,y1);
      inttcontourData(2,x2,y2);
      inttcontourData(3,x3,y3);
   }
   public void createCircle()
   {
      int centerX = getImageWidth()/2+20;
      int centerY = getImageHeight()/2-5;
      int xScale = centerX;
      int yScale = centerY-1;
      int theta = 0;
      int x = 0;
      int y = 0;
      int i = 0;
      for(double j=0;j<2*Math.PI;j = j + .5)
      {
         x = (int)(30*Math.sin(j));
         y = (int)(60*Math.cos(j));
         inttcontourData(i,x+centerX,y+centerY);
         i++;
      }
   }
   public void createSquare(int myWidth,int myHeight)
   {
      int x=30;
      int y=30;
      int anIndex = 0;
      while(x<=myWidth-1-30)
      {
         inttcontourData(anIndex,x,y);
         x++;
         anIndex++;
      }
      x = myWidth -1-30;
      y = 31;
      while(y<=myHeight-1-31)
      {
         inttcontourData(anIndex,x,y);
         y++;
         anIndex++;
      }
      x = myWidth-1-30;
      y = myHeight-1-30;
      while(x>=30)
      {
         inttcontourData(anIndex,x,y);
         x--;
         anIndex++;
      }
      x = 30;
      y = myHeight-1-31;
      while(y>=31)
      {
         inttcontourData(anIndex,x,y);
         y--;
         anIndex++;
      }
      contourCount = anIndex;
   }
   public boolean getIsContourPnt(int myDataIndex)
   {
      int x1 = myDataIndex%getImageWidth();
      int y1 = myDataIndex/getImageWidth();
      //System.out.println("Activecontour.getIsContourPnt() :  itrtnIndex = "+itrtnIndex+" x,y="+x1+","+y1+", data index = "+myDataIndex+", is contour pnt = "+isContourPnt[myDataIndex]);
      return isContourPnt[myDataIndex];
   }
   public void inttcontourData(int myContourIndex,int myDataIndex)
   {
      int x1 = myDataIndex%getImageWidth();
      int y1 = myDataIndex/getImageWidth();
      contourPntX[myContourIndex] = x1;
      contourPntY[myContourIndex] = y1;
      contourData[myDataIndex] = 0x0000ff00;
      //isContourPnt[myDataIndex] = true;
      contourPnts[myDataIndex] = myContourIndex;

      //System.out.println("Activecontour.inttcontourData(): data index = "+myDataIndex+", contour index = "+myContourIndex+" , is contour pnt = "+isContourPnt[myDataIndex]+", contour count = "+contourCount);
   }
   public void inttcontourData(int myContourIndex,int myX,int myY)
   {
      int myDataIndex = myY * getImageWidth() + myX;
      contourPntX[myContourIndex] = myX;
      contourPntY[myContourIndex] = myY;
      contourData[myDataIndex] = 0x0000ff00;

      isContourPnt[myDataIndex] = true;
      contourPnts[myDataIndex] = myContourIndex;
      alpha[myContourIndex] = 1;
      beta[myContourIndex] = 1;
      gamma[myContourIndex] = 1;
      contourCount++;
      //System.out.println("ActiveContour.inttcontourData() x,y = "+myX+","+myY);
      //System.out.println("Activecontour.inttcontourData(): data index = "+myDataIndex+", contour index = "+myContourIndex+" , is contour pnt = "+isContourPnt[myDataIndex]+", contour count = "+contourCount);
   }
   /*
   public void drawContour()
   {
         LineGraph cntrLines = new LineGraph();
         cntrLines.setStartX(minCrvE);
         int anIndex = getLineDrwIndex();
         int aX = contourPntX[myContourIndex];
         int aY = contourPntY[myContourIndex];


   }*/
   public void updtcontourData(int myCntrIndex)
   {
      int anEnergyIndex = getEnergyIndex();
      int x1 = anEnergyIndex % getImageWidth();
      int y1 = anEnergyIndex / getImageWidth();
      contourPntX[myCntrIndex] = x1;
      contourPntY[myCntrIndex] = y1;
      //contourData[anEnergyIndex] = 0x0000ff00;
      contourImage[anEnergyIndex] = 0x0000ff00;
      alpha[myCntrIndex] = 1;
      beta[myCntrIndex] = 1;
      gamma[myCntrIndex] = 1;

      isContourPnt[anEnergyIndex] = true;
      //System.out.println("ActiveContour: contour index = "+myCntrIndex+", new x,y = "+x1+","+y1);
   }
   public void cmptDrvtv1(int myCntrIndex)
   {
       int aWidth = getImageWidth();
       int anIndex = cmptImageIndex(myCntrIndex);
       int energyCntr1 = cmptDrvtv1(myCntrIndex,anIndex-aWidth-1);
       setMinCntrE(energyCntr1);
       setCntrIndex(anIndex-aWidth-1);
       int energyCntr2 = cmptDrvtv1(myCntrIndex,anIndex-aWidth);
       updtCntrEnergy(energyCntr2,anIndex-aWidth);
       int energyCntr3 = cmptDrvtv1(myCntrIndex,anIndex-aWidth+1);
       updtCntrEnergy(energyCntr3,anIndex-aWidth+1);
       int energyCntr4 = cmptDrvtv1(myCntrIndex,anIndex-1);
       updtCntrEnergy(energyCntr4,anIndex-1);
       int energyCntr5 = cmptDrvtv1(myCntrIndex,anIndex);
       updtCntrEnergy(energyCntr5,anIndex);
       int energyCntr6 = cmptDrvtv1(myCntrIndex,anIndex+1);
       updtCntrEnergy(energyCntr6,anIndex+1);
       int energyCntr7 = cmptDrvtv1(myCntrIndex,anIndex+aWidth-1);
       updtCntrEnergy(energyCntr7,anIndex+aWidth-1);
       int energyCntr8 = cmptDrvtv1(myCntrIndex,anIndex+aWidth);
       updtCntrEnergy(energyCntr8,anIndex+aWidth);
       int energyCntr9 = cmptDrvtv1(myCntrIndex,anIndex+aWidth+1);
       updtCntrEnergy(energyCntr9,anIndex+aWidth+1);
   }
   public int getCrrntCntrIndx()
   {
      return contourIndex;
   }
   public int getPrvsCntrIndx()
   {
      int index = 0;
      if(contourIndex == 0)
      {
          index = contourCount - 1;
      }else{
          index = contourIndex - 1;
      }
      return index;
   }
   public int getNxtCntrIndx()
   {
      int index = 0;
      if(contourIndex == contourCount - 1)
      {
          index = 0;
      }else{
          index = contourIndex + 1;
      }
      return index;
   }
   public int cmptDrvtv1(int myCntrIndex,int myDataIndex)
   {      
       int imageWidth = getImageWidth();
       int x = myDataIndex%imageWidth;
       int y = myDataIndex/imageWidth;
       int prvsCntrIndex = getPrvsCntrIndx();
       int nxtCntrIndex = getNxtCntrIndx();
       int drvtv1X = 
            contourPntX[prvsCntrIndex]*krnlHrzntl1[1][0]+
            x*krnlHrzntl1[1][1]+
            contourPntX[nxtCntrIndex+1]*krnlHrzntl1[1][2];
       int drvtv1Y =
            contourPntY[prvsCntrIndex]*krnlVrtcl1[0][1]  +
            y*krnlVrtcl1[1][1] +
            contourPntY[nxtCntrIndex]*krnlVrtcl1[2][1];
       int magnitude2 = drvtv1X*drvtv1X + drvtv1Y*drvtv1Y;

       return magnitude2;
   }
   public void displayContourInfo(int myCntrIndex,int myDataIndex)
   {
       int imageWidth = getImageWidth();
       int x = myDataIndex%imageWidth;
       int y = myDataIndex/imageWidth;
       int prvsCntrIndex = getPrvsCntrIndx();
       int nxtCntrIndex = getNxtCntrIndx();
       //System.out.println("ActiveContour.displayContourInfo(): data index = "+myDataIndex+", cntrIndex = "+myCntrIndex+", prvsCntrIndex = "+prvsCntrIndex+", nxtCntrIndex = "+nxtCntrIndex+", x,y = "+x+","+y);
       //System.out.println("ActiveContour.displayContourInfo(): xp,yp = "+contourPntX[prvsCntrIndex]+","+contourPntY[prvsCntrIndex]);
       //System.out.println("ActiveContour.displayContourInfo(): x,y = "+contourPntX[myCntrIndex]+","+contourPntY[myCntrIndex]);
       //System.out.println("ActiveContour.displayContourInfo(): xn,yn = "+contourPntX[nxtCntrIndex]+","+contourPntY[nxtCntrIndex]);
   }
   public void updtGrdntEnergy(int myEnergy,int myIndex)
   {
       if(myEnergy < minCntrE)
       {
           minGrdntE = myEnergy;
           grdntIndex = myIndex;
       }
   }
   public void updtCrvEnergy(int myEnergy,int myIndex)
   {
       if(myEnergy < minCrvE)
       {
           minCrvE = myEnergy;
           crvIndex = myIndex;           
       }
       //System.out.println("ActiveContour: crvIndex = "+crvIndex);
   }
   public void updtCntrEnergy(int myEnergy,int myIndex)
   {
       if(myEnergy < minCntrE)
       {
           minCntrE = myEnergy;
           cntrIndex = myIndex;
       }
   }
   public int cmptImageIndex(int myCntrIndex)
   {
       int x = contourPntX[myCntrIndex];
       int y = contourPntY[myCntrIndex];
       int aWidth = getImageWidth();
       int anIndex = y*aWidth+x;
       return anIndex;
   }
   public void cmptDrvtv2(int myCntrIndex)
   {
       int aWidth = getImageWidth();
       int anIndex = cmptImageIndex(myCntrIndex);
       int energyCrv1 = cmptDrvtv2(myCntrIndex,anIndex-aWidth-1);
       setMinCrvE(energyCrv1);
       setCrvIndex(anIndex-aWidth-1);
       int energyCrv2 = cmptDrvtv2(myCntrIndex,anIndex-aWidth);
       updtCrvEnergy(energyCrv2,anIndex-aWidth);
       int energyCrv3 = cmptDrvtv2(myCntrIndex,anIndex-aWidth+1);
       updtCrvEnergy(energyCrv3,anIndex-aWidth+1);
       int energyCrv4 = cmptDrvtv2(myCntrIndex,anIndex-1);
       updtCrvEnergy(energyCrv4,anIndex-1);
       int energyCrv5 = cmptDrvtv2(myCntrIndex,anIndex);
       updtCrvEnergy(energyCrv5,anIndex);
       int energyCrv6 = cmptDrvtv2(myCntrIndex,anIndex+1);
       updtCrvEnergy(energyCrv6,anIndex+1);
       int energyCrv7 = cmptDrvtv2(myCntrIndex,anIndex+aWidth-1);
       updtCrvEnergy(energyCrv7,anIndex+aWidth-1);
       int energyCrv8 = cmptDrvtv2(myCntrIndex,anIndex+aWidth);
       updtCrvEnergy(energyCrv8,anIndex+aWidth);
       int energyCrv9 = cmptDrvtv2(myCntrIndex,anIndex+aWidth+1);
       updtCrvEnergy(energyCrv9,anIndex+aWidth+1);
   }
   public int cmptDrvtv2(int myCntrIndex,int myDataIndex)
   {
       int imageWidth = getImageWidth();
       int x = myDataIndex%imageWidth;
       int y = myDataIndex/imageWidth;
       int prvsCntrIndex = getPrvsCntrIndx();
       int nxtCntrIndex = getNxtCntrIndx();

       int drvtv2X =
            contourPntX[prvsCntrIndex]*krnlHrzntl2[1][0]+
            x*krnlHrzntl2[1][1]+
            contourPntX[nxtCntrIndex]*krnlHrzntl2[1][2];

       int drvtv2Y =
            contourPntY[prvsCntrIndex]*krnlVrtcl2[0][1] +
            y*krnlVrtcl2[1][1]   +
            contourPntY[nxtCntrIndex]*krnlVrtcl2[2][1] ;
        int magnitude2 = drvtv2X*drvtv2X + drvtv2Y*drvtv2Y;
        return magnitude2;
   }
   public void cmptAllGrdnts(int myCntrIndex)
   {
       int aWidth = getImageWidth();
       int anIndex = cmptImageIndex(myCntrIndex);
       int energyCrv1 = cmptGrdnt(anIndex-aWidth-1);
       setMinGrdntE(energyCrv1);
       setGrdntIndex(anIndex-aWidth-1);
       int energyCrv2 = cmptGrdnt(anIndex-aWidth);
       updtGrdntEnergy(energyCrv2,anIndex-aWidth);
       int energyCrv3 = cmptGrdnt(anIndex-aWidth+1);
       updtGrdntEnergy(energyCrv3,anIndex-aWidth+1);
       int energyCrv4 = cmptGrdnt(anIndex-1);
       updtGrdntEnergy(energyCrv4,anIndex-1);
       int energyCrv5 = cmptGrdnt(anIndex);
       updtGrdntEnergy(energyCrv5,anIndex);
       int energyCrv6 = cmptGrdnt(anIndex+1);
       updtGrdntEnergy(energyCrv6,anIndex+1);
       int energyCrv7 = cmptGrdnt(anIndex+aWidth-1);
       updtGrdntEnergy(energyCrv7,anIndex+aWidth-1);
       int energyCrv8 = cmptGrdnt(anIndex+aWidth);
       updtGrdntEnergy(energyCrv8,anIndex+aWidth);
       int energyCrv9 = cmptGrdnt(anIndex+aWidth+1);
       updtGrdntEnergy(energyCrv9,anIndex+aWidth+1);
   }
   public int cmptGrdnt(int myIndex)
   {
      int aGradient[] = getGrdntMgntd();
      return -1*aGradient[myIndex];
   }
   public double cmptTtlEnergy(int myCntrIndex,int myDataIndex)
   {
       displayContourInfo(myCntrIndex,myDataIndex);
       double energyCntr = alpha[myCntrIndex]*cmptDrvtv1(myCntrIndex, myDataIndex);
       double energyCrv = beta[myCntrIndex]*cmptDrvtv2(myCntrIndex, myDataIndex);
       double energyGrdnt =  gamma[myCntrIndex]*cmptGrdnt(myDataIndex);
       double energyTtl = energyCntr + energyCrv + energyGrdnt;
       //System.out.println("ActiveContour.cmptTtlEnergy() : "+ itrtnIndex +" contour index = "+myCntrIndex+". data index = "+myDataIndex+", energy contour = "+energyCntr+", energyCrv = "+energyCrv+", energy gradient = "+energyGrdnt+", total energy = "+energyTtl);
       return energyTtl;       
   }
   public boolean checkCurvature()
   {
      boolean withinThreshold = false;
      double aValue = cmptCrvtr();
      //System.out.println("ActiveContour: curvature = "+aValue+" ,threshold = "+curveThreshold);
      if(aValue > curveThreshold )
      {
          //beta[contourIndex] = 0;
      }
      return withinThreshold;
   }
   public double cmptCrvtr()
   {
       int u1j = 0;
       int u1k = 0;
       int u2j = 0;
       int u2k = 0;
       double u1u2j = 0;
       double u1u2k = 0;
       double mag1 = 0;
       double mag2 = 0;
       double mag3 = 0;
       double mag4 = 0;

       int myDataIndex = energyIndex;
       int imageWidth = getImageWidth();

       int x = myDataIndex%imageWidth;
       int y = myDataIndex/imageWidth;
       int prvsCntrIndex = getPrvsCntrIndx();
       int nxtCntrIndex = getNxtCntrIndx();


       u1j = x - contourPntX[prvsCntrIndex];
       u2j = contourPntX[nxtCntrIndex] - x;
       u1k = y - contourPntY[prvsCntrIndex];
       u2k = contourPntY[nxtCntrIndex] - y;

       //System.out.println("ActiveContour: x = "+x+", y = "+y+", index = "+myDataIndex);
       //System.out.println("ActiveContour: next x = "+contourPntX[nxtCntrIndex]);
       //System.out.println("ActiveContour: next y = "+contourPntY[nxtCntrIndex]);
       //System.out.println("ActiveContour: previous x = "+contourPntX[prvsCntrIndex]);
       //System.out.println("ActiveContour: previous y = "+contourPntY[prvsCntrIndex]);

       //System.out.println("ActiveContour : u1j = "+u1j+", u2j = "+u2j+", u1k = "+u1k+", u2k = "+u2k);
       mag1 = Math.sqrt(u1j*u1j + u1k*u1k);
       mag2 = Math.sqrt(u2j*u2j + u2k*u2k);
       //System.out.println("ActiveContour : mag1 = "+mag1+", mag2 = "+mag2);
       u1u2j = u1j/mag1 - u2j/mag2;
       u1u2k = u1k/mag1 - u2k/mag2;
       //System.out.println("ActiveContour : u1u2j = "+u1u2j+", u1u2k = "+u1u2k);
       mag3 = Math.sqrt(u1u2j*u1u2j + u1u2k*u1u2k);

       mag4 = mag3*mag3;
       //System.out.println("ActiveContour : mag3 = "+mag3+", mag4 = "+mag4);

       return mag4;
   }
   public void cmptTtlEnergy(int myCntrIndex)
   {
       int aWidth = getImageWidth();
       int anIndex = cmptImageIndex(myCntrIndex);
       //System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);

       double energyTtl1 = cmptTtlEnergy(myCntrIndex,anIndex-aWidth-1);
       setEnergyTtl(energyTtl1);
       setEnergyIndex(anIndex-aWidth-1);
       updtTtlEnergy(energyTtl1,anIndex-aWidth-1);
       //System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);
       double energyTtl2 = cmptTtlEnergy(myCntrIndex,anIndex-aWidth);
       updtTtlEnergy(energyTtl2,anIndex-aWidth);
              //System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);
       double energyTtl3 = cmptTtlEnergy(myCntrIndex,anIndex-aWidth+1);
       updtTtlEnergy(energyTtl3,anIndex-aWidth+1);
              //System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);
       double energyTtl4 = cmptTtlEnergy(myCntrIndex,anIndex-1);
       updtTtlEnergy(energyTtl4,anIndex-1);
             // System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);
       double energyTtl5 = cmptTtlEnergy(myCntrIndex,anIndex);
       updtTtlEnergy(energyTtl5,anIndex);
              //System.out.println("ActiveContour : myCntrIndex = "+myCntrIndex);
       double energyTtl6 = cmptTtlEnergy(myCntrIndex,anIndex+1);
       updtTtlEnergy(energyTtl6,anIndex+1);
       double energyTtl7 = cmptTtlEnergy(myCntrIndex,anIndex+aWidth-1);
       updtTtlEnergy(energyTtl7,anIndex+aWidth-1);
       double energyTtl8 = cmptTtlEnergy(myCntrIndex,anIndex+aWidth);
       updtTtlEnergy(energyTtl8,anIndex+aWidth);
       double energyTtl9 = cmptTtlEnergy(myCntrIndex,anIndex+aWidth+1);
       updtTtlEnergy(energyTtl9,anIndex+aWidth+1);
   }
}
