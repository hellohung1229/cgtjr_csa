/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */

public class GraySclFltrOptmzd extends ImageFilter
{
   private int grayValues[];
   private int orgnlValues[];
   
   //private int imageWidth;

   public GraySclFltrOptmzd(){}
   public GraySclFltrOptmzd(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      orgnlValues = new int[aWidth*aHeight];
      //System.out.println("GrySclFltr: filename = "+myFileName);
   }
   public GraySclFltrOptmzd(int myPixelData[],int myImageWidth,int myImageHeight)
   {
      //super(myFileName);
      super(myPixelData,myImageWidth,myImageHeight);
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      initialize(myImageWidth, myImageHeight);
      //BufferedImage aBufferedImage = ImageTool.rtrvImage(myPixelData, aWidth, aHeight);


      //System.out.println("GrySclFltr: filename = "+myFileName);
   }
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth, myImageHeight);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      orgnlValues = new int[aWidth*aHeight];
      //imageWidth = myImageWidth;
      //grayValues = new int[myImageWidth*myImageHeight];
   }
   public void filter(int grayValues[],int myHData[],int myVData[],int i)
   {
   }
   public void filter(int myOriginalValues[],int i)
   {
      filter3x3(myOriginalValues,i);
   }
   public void filter3x3(int myOriginalValues[],int i)
   {
      orgnlValues[i] = myOriginalValues[i];
      //grayValues[i] = myOrignalValues[i];
      //grdntFilter(grayValues,i);      
      gryFltr3x3(myOriginalValues,i);
   }
   public void gryFltr3x3(int myX,int myY)
   {
      int anIndex = rtrvIndex(myX, myY);
      gryFltr3x3(anIndex);
   }
   public void gryFltr3x3(int i)
   {
      gryFltr3x3(getInptPxlData(),i);
   }
   public void gryFltr3x3(int originalValues[],int i)
   {
      int imageWidth = getImageWidth();
      //grayValues = getOtptPxlData();
      //System.out.println("ClrCnvrtFlter:test1: grayValue = "+grayValues[i]+", i="+i);
      if(isInBounds3x3(i))
      {
         //System.out.println("ClrCnvrtFlter:test2: grayValue = "+grayValues[i]+", i="+i);
         //System.out.println("ClrCnvrtFlter:grayValue = originalValues["+i+"]"+originalValues[i]);
         grayValues[i-imageWidth-1]=ColorSpectra.cnvrtRGBGry(originalValues[i-imageWidth-1]);
         grayValues[i-imageWidth]=ColorSpectra.cnvrtRGBGry(originalValues[i-imageWidth]);
         grayValues[i-imageWidth+1]=ColorSpectra.cnvrtRGBGry(originalValues[i-imageWidth+1]);
         grayValues[i-1]=ColorSpectra.cnvrtRGBGry(originalValues[i-1]);
         grayValues[i]=ColorSpectra.cnvrtRGBGry(originalValues[i]);
         grayValues[i+1]=ColorSpectra.cnvrtRGBGry(originalValues[i+1]);
         grayValues[i+imageWidth-1]=ColorSpectra.cnvrtRGBGry(originalValues[i+imageWidth-1]);
         grayValues[i+imageWidth]=ColorSpectra.cnvrtRGBGry(originalValues[i+imageWidth]);
         grayValues[i+imageWidth+1]=ColorSpectra.cnvrtRGBGry(originalValues[i+imageWidth+1]);
         //grayValues = originalValues;
         //System.out.println("ClrCnvrtFlter:grayValue = grayValues["+i+"]"+grayValues[i]);
      }
   }
   public int[] rtrvFltrdData3x3(int myX,int myY)
   {
      return rtrvPixels3x3(myX,myY,grayValues);
   }
   public int[] getFltrdData()
   {
      return grayValues;
   }
   public int[] getGryVls()
   {
      return grayValues;
   }
   
   public void setGryVls(int myGryVls[])
   {
      grayValues = myGryVls;
   }
   public int[] getOrgnlVls()
   {
       return orgnlValues;
   }
}