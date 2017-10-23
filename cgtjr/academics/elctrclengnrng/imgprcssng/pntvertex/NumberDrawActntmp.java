/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.*;

/**
 *
 * @author clayton g thomas jr
 */
public class NumberDrawActntmp implements PntInsrtActn
{
   private int zeroPixels[];
   private int onePixels[];
   private int twoPixels[];
   private int threePixels[];
   private int fourPixels[];
   private int fivePixels[];
   private int sixPixels[];
   private int sevenPixels[];
   private int eightPixels[];
   private int ninePixels[];

   private int nmbrWidth = 15;
   private int nmbrIndex = 5;
   private int width;
   private int height;
   private int imgPixels[];

   public NumberDrawActntmp()
   {
      zeroPixels = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
      onePixels = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
      twoPixels = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
      threePixels = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
      fourPixels = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
      fivePixels = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
      sixPixels = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
      sevenPixels = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
      eightPixels = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
      ninePixels = ImageTool.rtrv1DPxls("data/images/numbers/nine.png");
   }
   public void setImgPixels(int myImgPixels[])
   {
      imgPixels = myImgPixels;
   }
    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       drawLines(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);

    }

    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
       //update(myPoint7);
        //aPoint.setColor(0x0000ff);
        LineCrtr.drawLine(aPoint, myPoint7, imgPixels, width, height);
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
       aPoint.setColor(0x000000ff);
       //update3x3(aPoint,0x000000ff);

    }

    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        drawLines(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }
    public void drwNmbr3x3(int myNmbr)
    {
       
    }
    public void draw1(){
       
    }
    public void update(Pnt myPoint7)
    {
       //TODO: look for number formats
       int aX = (int)myPoint7.getX1();
       int aY = (int)myPoint7.getY1();
       int aColor = myPoint7.getColor();
       int anIndex = ImageTool.rtrvIndex(aX,aY,width);
       //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
       ImageTool.updtImgPixel(anIndex, aColor, imgPixels);
    }
    public void draw27x27(int myImgPxls[],int myPxls[],int myIndex1,int myIndex2)
    {
       int aWidth9 = 9*width;
       int aNmbrWidth9 = 9*nmbrWidth;
       draw9x9(myImgPxls,myPxls,myIndex1-aWidth9-9,myIndex2-aNmbrWidth9-9);
       draw9x9(myImgPxls,myPxls,myIndex1-aWidth9,myIndex2-aNmbrWidth9);       
       draw9x9(myImgPxls,myPxls,myIndex1-aWidth9+9,myIndex2-aNmbrWidth9+9);
       draw9x9(myImgPxls,myPxls,myIndex1-9,myIndex2-9); 
       draw9x9(myImgPxls,myPxls,myIndex1,myIndex2);
       draw9x9(myImgPxls,myPxls,myIndex1+9,myIndex2+9);
       draw9x9(myImgPxls,myPxls,myIndex1+aWidth9-9,myIndex2+aNmbrWidth9-9);              
       draw9x9(myImgPxls,myPxls,myIndex1+aWidth9,myIndex2+aNmbrWidth9);
       draw9x9(myImgPxls,myPxls,myIndex1+aWidth9+9,myIndex2+aNmbrWidth9+9);       
    }
    public void draw9x9(int myImgPxls[],int myPxls[],int myIndex1,int myIndex2)
    {
       int aWidth3 = 3*width;
       int aNmbrWidth3 = 3*nmbrWidth;
       draw3x3(myImgPxls,myPxls,myIndex1-aWidth3-3,myIndex2-aNmbrWidth3-3);
       draw3x3(myImgPxls,myPxls,myIndex1-aWidth3,myIndex2-aNmbrWidth3);       
       draw3x3(myImgPxls,myPxls,myIndex1-aWidth3+3,myIndex2-aNmbrWidth3+3);
       draw3x3(myImgPxls,myPxls,myIndex1-3,myIndex2-3); 
       draw3x3(myImgPxls,myPxls,myIndex1,myIndex2);
       draw3x3(myImgPxls,myPxls,myIndex1+3,myIndex2+3);
       draw3x3(myImgPxls,myPxls,myIndex1+aWidth3-3,myIndex2+aNmbrWidth3-3);              
       draw3x3(myImgPxls,myPxls,myIndex1+aWidth3,myIndex2+aNmbrWidth3);
       draw3x3(myImgPxls,myPxls,myIndex1+aWidth3+3,myIndex2+aNmbrWidth3+3);       
    }
    public void draw3x3(int myImgPxls[],int myPxls[],int myIndex1,int myIndex2)
    {
       //check boundary for borth image and numbers
       int aColor= 0;
       //int anIndex = ImageTool.rtrvIndex(aX,aY,width);
       //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
       if((aColor = getPixel(myPxls,myIndex2-nmbrWidth-1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1-width-1,aColor,imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2-nmbrWidth)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1-width, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2-nmbrWidth+1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1-width+1, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2-1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1-1, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2+1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1+1, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2+nmbrWidth-1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1+width-1, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2+nmbrWidth)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1+width, aColor, imgPixels);
       }
       if((aColor = getPixel(myPxls,myIndex2+nmbrWidth+1)) >= 0)
       {
          ImageTool.updtImgPixel(myIndex1+width+1, aColor, imgPixels);
       }
    }
    public int getPixel(int myPxls[],int myIndex1)
    {
       if(isPxlOn(myPxls,myIndex1))
       {
          return myPxls[myIndex1];
       }
       return 0;
    }
    public boolean isPxlOn(int myPxls[],int myIndex)
    {
       boolean isPixelOn = false;
       if(myPxls[myIndex] >= 0)
       {
          isPixelOn = true;
       }
       return isPixelOn;
    }
    public void drawLines(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) 
    {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       LineCrtr.drawLine(aPoint, myPoint4, imgPixels, width, height);
       LineCrtr.drawLine(myPoint4, myPoint6, imgPixels, width, height);
       LineCrtr.drawLine(myPoint6, myPoint7, imgPixels, width, height);
       LineCrtr.drawLine(myPoint7, myPoint4, imgPixels, width, height);
    }
    public void setWidth(int myWidth)
    {
        width = myWidth;
    }
}
