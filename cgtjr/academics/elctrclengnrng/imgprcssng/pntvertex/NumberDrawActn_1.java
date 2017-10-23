/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.elctrclengnrng.imgprcssng.Round;
import cgtjr.academics.math.geometry.*;

/**
 *
 * @author clayton g thomas jr
 */
public class NumberDrawActn_1 extends GridDrawActn
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
   private int numbers[][];

   private int imgNmbrWdth = 9;
   private int imgNmbrHght = 9;
   private int nmbrIndex = 40;
   private int imgDtWdth;
   private int imgDtHght;
   private int imgPixels[];

   public NumberDrawActn_1()
   {
      numbers = new int[9][];
      numbers[0] = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
      numbers[1] = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
      numbers[2] = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
      numbers[3] = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
      numbers[4] = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
      numbers[5] = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
      numbers[6] = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
      numbers[7] = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
      numbers[8] = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
   }
   public NumberDrawActn_1(int myImgPixels[],int myImgWdth)
   {

      imgPixels = myImgPixels;
      imgDtWdth = myImgWdth;
      
      numbers = new int[9][];
      numbers[0] = ImageTool.rtrv1DPxls("data/images/numbers/zero.png");
      numbers[1] = ImageTool.rtrv1DPxls("data/images/numbers/one.png");
      numbers[2] = ImageTool.rtrv1DPxls("data/images/numbers/two.png");
      numbers[3] = ImageTool.rtrv1DPxls("data/images/numbers/three.png");
      numbers[4] = ImageTool.rtrv1DPxls("data/images/numbers/four.png");
      numbers[5] = ImageTool.rtrv1DPxls("data/images/numbers/five.png");
      numbers[6] = ImageTool.rtrv1DPxls("data/images/numbers/six.png");
      numbers[7] = ImageTool.rtrv1DPxls("data/images/numbers/seven.png");
      numbers[8] = ImageTool.rtrv1DPxls("data/images/numbers/eight.png");
   }   
   public void setImgPixels(int myImgPixels[])
   {
      super.setImgPixels(myImgPixels);
      imgPixels = myImgPixels;
   }
   public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
   }

   public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber)
   {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       int x = (int)myPoint7.getX1();
       int y = (int)myPoint7.getY1();       
       
       int x1 = Round.getDgtPxlPstn(1,2);
       int x2 = Round.getDgtPxlPstn(2,2);       
       
       if(x%40!=0 || y%40!=0 || x+x1 < 0 || x+x1 > imgDtWdth || x+x2 < 0 || x + x2 > imgDtWdth)
       {
          return;
       }       
       int glblIndx = myPoint7.getCounter();       
       int aLength = Round.getDgtLngth(glblIndx);     

       //System.out.println("NumberDrawActn: image width = "+imgDtWdth+", x = "+x+", x1 = "+x1+", x2 = "+x2);              
       int aDigit3 = Round.getLength3Digit(glblIndx,2);

       int aDigit4 = Round.getLength3Digit(glblIndx,1);


       
       int pxlData3[] = rtrvNmbr(aDigit3);
       int pxlData4[] = rtrvNmbr(aDigit4);       
       //System.out.println("NumberDrawActn: aDigit0 = "+aDigit0+", aDigit1 = "+aDigit1);       
       //System.out.println("NumberDrawActn: global index = "+glblIndx+", digit length = "+aLength);
       
       
       int myIndex1 = ImageTool.rtrvIndex(x+x1, y, imgDtWdth);
       int myIndex2 = ImageTool.rtrvIndex(x+x2, y, imgDtWdth);       

       //draw9x9(imgPixels,pxlData,myIndex1,aDigit0);
       draw9x9(imgPixels,pxlData3,myIndex1,nmbrIndex);
       draw9x9(imgPixels,pxlData4,myIndex2,nmbrIndex);       
       //System.out.println("NumberDrawActn: index1 = "+myIndex+", nmbrIndex = "+nmbrIndex);       
    }

    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) 
    {
       super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       int x = (int)myPoint7.getX1();
       int y = (int)myPoint7.getY1();
       int glblIndx = myPoint7.getCounter();
       int pxlData[] = rtrvNmbr(glblIndx);
       int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);
       draw9x9(imgPixels,pxlData,myIndex,nmbrIndex);
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber)
    {
       super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);

       int x = (int)aPoint.getX1();
       int y = (int)aPoint.getY1();
       int glblIndx = aPoint.getCounter();
       int pxlData[] = rtrvNmbr(glblIndx);
       int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);       
       draw9x9(imgPixels,pxlData,myIndex,nmbrIndex);
    }
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
       super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
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
       //int aColor = 0x00ee0000;
       int anIndex = ImageTool.rtrvIndex(aX,aY,imgDtWdth);
       //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
       ImageTool.updtImgPixel(anIndex, aColor, imgPixels);
    }
    public void draw27x27(int myImgPxls[],int myPxls[],int myIndex1,int myIndex2)
    {
       int aWidth9 = 9*imgDtWdth;
       int aNmbrWidth9 = 9*imgNmbrWdth;
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
       int aWidth3 = 3*imgDtWdth;
       int aNmbrWidth3 = 3*imgNmbrWdth;
       //System.out.println("NumberDrawActn: index2 = "+myIndex2+" , nmbr width = "+aNmbrWidth3);
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
    public int[] rtrvNmbr(int myNmbr)
    {
       if(myNmbr >=0 && myNmbr < 9)
       {
          return numbers[myNmbr];
       }else{
          return numbers[0];
       }
    }
    public void draw3x3(int myImgPxls[],int myPxls[],int myIndex1,int myIndex2)
    {
       //check boundary for borth image and numbers
       int aColor= 0;
       //int anIndex = ImageTool.rtrvIndex(aX,aY,width);
       //System.out.println("PntPxlInsrtActn: x = "+aX+", y="+aY+", old color["+anIndex+"] = "+imgPixels[anIndex]+", new color = "+aColor);
       
       if(isPxlOn(myPxls,myIndex2-imgNmbrWdth-1))
       {
          aColor = getPixel(myPxls,myIndex2-imgNmbrWdth-1);
          updtImgPxls(myIndex1-imgDtWdth-1,aColor,imgPixels);
       }
       if(isPxlOn(myPxls,myIndex2-imgNmbrWdth))
       {
          aColor = getPixel(myPxls,myIndex2-imgNmbrWdth);           
          updtImgPxls(myIndex1-imgDtWdth, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2-imgNmbrWdth+1)))
       {
          aColor = getPixel(myPxls,myIndex2-imgNmbrWdth+1);               
          updtImgPxls(myIndex1-imgDtWdth+1, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2-1)))
       {
          aColor = getPixel(myPxls,myIndex2-1);             
          updtImgPxls(myIndex1-1, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2)))
       {
          aColor = getPixel(myPxls,myIndex2);              
          updtImgPxls(myIndex1, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2+1)))
       {
          aColor = getPixel(myPxls,myIndex2+1);              
          updtImgPxls(myIndex1+1, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2+imgNmbrWdth-1)))
       {
          aColor = getPixel(myPxls,myIndex2+imgNmbrWdth-1); 
          updtImgPxls(myIndex1+imgDtWdth-1, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2+imgNmbrWdth)))
       {
          aColor = getPixel(myPxls,myIndex2+imgNmbrWdth);            
          updtImgPxls(myIndex1+imgDtWdth, aColor, imgPixels);
       }
       if((isPxlOn(myPxls,myIndex2+imgNmbrWdth+1)))
       {
          aColor = getPixel(myPxls,myIndex2+imgNmbrWdth+1);  
          updtImgPxls(myIndex1+imgDtWdth+1,aColor, imgPixels);
       }
    }
    public void updtImgPxls(int myIndex1,int myColor,int myImgPixels[])
    {
       int x = ImageTool.rtrvXPstn(myIndex1, imgDtWdth);
       int y = ImageTool.rtrvYPstn(myIndex1, imgDtWdth);

       if(ImageFilter.isInBounds1x1(x,y, imgDtWdth, imgDtHght))
       {
          
           //System.out.println("NumberDrawActn: x = "+x+", y = "+y+", index = "+myIndex1+", color = "+myColor);
           myImgPixels[myIndex1] = myColor;
           //myImgPixels[myIndex1] = 0x000000dd;           
       }
    }
    public int getPixel(int myPxls[],int myIndex1)
    {
       if(isPxlOn(myPxls,myIndex1))
       {
          //System.out.println("NumberDrawActn: myPxls["+myIndex1+"]="+myPxls[myIndex1]);
          return myPxls[myIndex1];
       }
       return 0;
    }
    public boolean isPxlOn(int myPxls[],int myIndex)
    {
       boolean isPixelOn = false;
       int x = ImageTool.rtrvXPstn(myIndex,imgNmbrWdth);
       int y = ImageTool.rtrvYPstn(myIndex,imgNmbrWdth);
             
       //System.out.println("NumberDrawActn.isPxlOn(): index = "+myIndex+", x = "+x+",y="+y);
       boolean isInBnds = ImageFilter.isInBounds1x1(x,y, imgNmbrWdth, imgNmbrHght);
       //int pxlClr = ColorSpectra.rtrv000000ff(myPxls[myIndex]);
       //System.out.println("NumberDrawActn: myIndex="+myIndex+", isInBnds = "+isInBnds+", myPxls["+123+"]="+pxlClr);
       if(myIndex < 0)
       {
          isPixelOn = false;
       }else if(isInBnds == true && ColorSpectra.rtrv000000ff(myPxls[myIndex]) >1)
       {
          //System.out.println("NumberDrawActn: x = "+x+",y="+y+", myIndex="+myIndex+", isInBnds = "+isInBnds+", myPxls["+myIndex+"]="+pxlClr);
          isPixelOn = true;
       }
       return isPixelOn;
    }
    public void setImgDtWdth(int myWidth)
    {
       super.setImgDtWdth(myWidth);
       imgDtWdth = myWidth;
    }
    public void setImgDtHght(int myHght)
    {
       super.setImgDtHght(myHght);
       imgDtHght = myHght;
    }    
    public void setImgNmbrWdth(int myWidth)
    {
        imgNmbrWdth = myWidth;
    }    
}
