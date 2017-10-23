/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.DigitPntPixels;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.Round;
import cgtjr.academics.math.geometry.*;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class SSDDrwActn extends GridDrawActn
{   
   private int numbers[][];

   private int imgNmbrWdth = 9;
   private int imgNmbrHght = 9;
   private int nmbrIndex = 40;
   private int imgDtWdth;
   private int imgDtHght;
   private int imgPixels[];

   public SSDDrwActn()
   {       
   }
   public SSDDrwActn(int myImgPixels[],int myImgWdth,int myImgHght)
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
      //throw new UnsupportedOperationException("Not supported yet.");
   }
   public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber)
   {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       super.nodeCreateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       drawDigit(myPoint7);
    }
   public void drawDigit(Vector aVector)
   {
      int aSize = aVector.size();
      for(int i=0;i<aSize;i++)
      {
         Pnt aPoint = (Pnt)aVector.get(i);
         drawDigit(aPoint);
      }
   }
   public void drawDigit(Pnt myPoint7)
   {
        //update3x3(myPoint7,0x00ff00);Point,myCrdntCmpltPnt,aSlctdPoint,aCrt
       int x = (int)myPoint7.getX1();
       int y = (int)myPoint7.getY1();       
       
       int xLength = Round.getDgtLngth(x);
       int yLength = Round.getDgtLngth(y);  
       
       int x1 = Round.getXDgtPxlPstn(1,xLength);
       int x2 = Round.getXDgtPxlPstn(2,xLength);       
       int x3 = Round.getXDgtPxlPstn(3,xLength);

       int x4 = Round.getYDgtPxlPstn(1,yLength);
       int x5 = Round.getYDgtPxlPstn(2,yLength);       
       int x6 = Round.getYDgtPxlPstn(3,yLength);       
       
       if(x%80!=0 || (y)%30!=0 || x+x1 < 0 || x+x1 > imgDtWdth || x+x2 < 0 || x + x2 > imgDtWdth)
       {
          return;
       }       
       int glblIndx = myPoint7.getCounter();       
       int aLength = Round.getDgtLngth(glblIndx);     

       //System.out.println("NumberDrawActn: image width = "+imgDtWdth+", x = "+x+", x1 = "+x1+", x2 = "+x2);              
       int aDigitX1 = Round.getLength3Digit(x,1);
       int aDigitX2 = Round.getLength3Digit(x,2);
       int aDigitX3 = Round.getLength3Digit(x,3); 
       
       int aDigitY1 = Round.getLength3Digit(y,1);
       int aDigitY2 = Round.getLength3Digit(y,2);
       int aDigitY3 = Round.getLength3Digit(y,3);
      
       
       int pxlDataX1[] = DigitPntPixels.rtrvNmbr(aDigitX1);
       int pxlDataX2[] = DigitPntPixels.rtrvNmbr(aDigitX2);
       int pxlDataX3[] = DigitPntPixels.rtrvNmbr(aDigitX3);
       
       int pxlDataY1[] = DigitPntPixels.rtrvNmbr(aDigitY1);       
       int pxlDataY2[] = DigitPntPixels.rtrvNmbr(aDigitY2);
       int pxlDataY3[] = DigitPntPixels.rtrvNmbr(aDigitY3);       
       int comma[] = DigitPntPixels.getComma();
       
       //System.out.println("NumberDrawActn: aDigit0 = "+aDigit0+", aDigit1 = "+aDigit1);       
       //System.out.println("NumberDrawActn: global index = "+glblIndx+", digit length = "+aLength);
              
       int myIndexX1 = ImageTool.rtrvIndex(x+x1, y, imgDtWdth);
       int myIndexX2 = ImageTool.rtrvIndex(x+x2, y, imgDtWdth);       
       int myIndexX3 = ImageTool.rtrvIndex(x+x3, y, imgDtWdth);              

       int myIndexY1 = ImageTool.rtrvIndex(x+x4, y, imgDtWdth);
       int myIndexY2 = ImageTool.rtrvIndex(x+x5, y, imgDtWdth);       
       int myIndexY3 = ImageTool.rtrvIndex(x+x6, y, imgDtWdth);
       int pxlData[] = DigitPntPixels.rtrvNmbr(glblIndx);
       int myIndex = ImageTool.rtrvIndex(x, y, imgDtWdth);
       //draw9x9(imgPixels,pxlData,myIndex1,aDigit0);
       
       DigitPntPixels.draw9x9(imgPixels,pxlDataX1,imgDtWdth,imgDtHght,myIndexX1,nmbrIndex);
       DigitPntPixels.draw9x9(imgPixels,pxlDataX2,imgDtWdth,imgDtHght,myIndexX2,nmbrIndex);       
       DigitPntPixels.draw9x9(imgPixels,pxlDataX3,imgDtWdth,imgDtHght,myIndexX3,nmbrIndex);   
       DigitPntPixels.draw9x9(imgPixels,comma,imgDtWdth,imgDtHght,myIndex,nmbrIndex);        
       DigitPntPixels.draw9x9(imgPixels,pxlDataY1,imgDtWdth,imgDtHght,myIndexY1,nmbrIndex);
       DigitPntPixels.draw9x9(imgPixels,pxlDataY2,imgDtWdth,imgDtHght,myIndexY2,nmbrIndex);       
       DigitPntPixels.draw9x9(imgPixels,pxlDataY3,imgDtWdth,imgDtHght,myIndexY3,nmbrIndex);            
       
       //System.out.println("NumberDrawActn: index1 = "+myIndex+", nmbrIndex = "+nmbrIndex);       
   }
   public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) 
    {
       super.nodeInitAction(aPoint, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       drawDigit(myPoint7);       
    }

    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber)
    {
       super.nodeOriginAction(aPoint, myDepthCounter, myDepth, myTotalNumber);

       drawDigit(aPoint);       
    }
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
       super.nodeUpdateAction(aPoint, myPoint4, myPoint6, myPoint7, myDepthCounter, myDepth, myTotalNumber);
       drawDigit(aPoint);           
       drawDigit(myPoint4);   
       drawDigit(myPoint6);   
       drawDigit(myPoint7);          
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