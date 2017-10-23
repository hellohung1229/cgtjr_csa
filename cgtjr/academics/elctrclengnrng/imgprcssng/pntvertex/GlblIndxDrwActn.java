/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author clayton g thomas jr
 */
public class GlblIndxDrwActn extends GridDrawActn
{   
   private int numbers[][];

   private int imgNmbrWdth = 9;
   private int imgNmbrHght = 9;
   private int nmbrIndex = 40;
   private int imgDtWdth;
   private int imgDtHght;
   private int imgPixels[];

   public GlblIndxDrwActn()
   {       
   }   
   public GlblIndxDrwActn(int myImgPixels[],int myImgWdth,int myImgHght)
   {
      imgPixels = myImgPixels;
      imgDtWdth = myImgWdth;
      imgDtHght = myImgHght;
   }   
   public void setImgPixels(int myImgPixels[])
   {
      super.setImgPixels(myImgPixels);
      imgPixels = myImgPixels;
   }
   public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
       super.nodeCmpltAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
       //throw new UnsupportedOperationException("Not supported yet.");
   }

   public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber)
   {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
      /**
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
       int aDigit3 = Round.getDigit(glblIndx,2);

       int aDigit4 = Round.getDigit(glblIndx,1);

       
       int pxlData3[] = DigitPixels.rtrvNmbr(aDigit3);
       int pxlData4[] = DigitPixels.rtrvNmbr(aDigit4);       
       //System.out.println("NumberDrawActn: aDigit0 = "+aDigit0+", aDigit1 = "+aDigit1);       
       //System.out.println("NumberDrawActn: global index = "+glblIndx+", digit length = "+aLength);
              
       int myIndex1 = ImageTool.rtrvIndex(x+x1, y, imgDtWdth);
       int myIndex2 = ImageTool.rtrvIndex(x+x2, y, imgDtWdth);       

       //draw9x9(imgPixels,pxlData,myIndex1,aDigit0);
       DigitPixels.draw9x9(imgPixels,pxlData3,imgDtWdth,imgDtHght,myIndex1,nmbrIndex);
       DigitPixels.draw9x9(imgPixels,pxlData4,imgDtWdth,imgDtHght,myIndex2,nmbrIndex);       
       //System.out.println("NumberDrawActn: index1 = "+myIndex+", nmbrIndex = "+nmbrIndex);       
        
       */
    }
    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) 
    {
       super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       /*
       int x = (int)myPoint7.getX1();
       int y = (int)myPoint7.getY1();
       int glblIndx = myPoint7.getCounter();
       int pxlData[] = DigitPixels.rtrvNmbr(glblIndx);
       int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);
       //
       //System.out.println("NumberDrawactn: imgDtWdth = "+imgDtWdth+", imgDtHght = "+imgDtHght);
       DigitPixels.draw9x9(imgPixels,pxlData,imgDtWdth,imgDtHght,myIndex,nmbrIndex);
       * */
       
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber)
    {
       super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);
       /*
       int x = (int)aPoint.getX1();
       int y = (int)aPoint.getY1();
       int glblIndx = aPoint.getCounter();
       int pxlData[] = DigitPixels.rtrvNmbr(glblIndx);
       int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);      
       //System.out.println("NumberDrawactn: imgDtWdth = "+imgDtWdth+", imgDtHght = "+imgDtHght);       
       DigitPixels.draw9x9(imgPixels,pxlData,imgDtWdth,imgDtHght,myIndex,nmbrIndex);
       * */
       
    }
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
       super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
    }
    public void setImgDtWdth(int myWdth)
    {
       super.setImgDtWdth(myWdth);
       imgDtWdth = myWdth;
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