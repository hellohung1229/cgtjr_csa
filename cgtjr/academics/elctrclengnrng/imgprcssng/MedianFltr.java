/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */

public class MedianFltr extends YSclFltr
{
   private int grayValues[];
   private int medianValues[];   
   
   private int imageWidth;
   private int imageHeight;
   private int medianKrnl[][];
   private double cnvlvsum;
   private double masksum;   
   
   public MedianFltr(){
      MedianKrnl aMedianKrnl = new MedianKrnl();
      medianKrnl = aMedianKrnl.getKrnlMask();       
   }
   
   public MedianFltr(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      medianValues = new int[aWidth*aHeight];
      imageWidth = aWidth;
      imageHeight = aHeight;
      MedianKrnl aMedianKrnl = new MedianKrnl();
      medianKrnl = aMedianKrnl.getKrnlMask();
      //System.out.println("GrySclFltr: filename = "+myFileName);
      //grayValues = getFltrdData();      
   }
   public MedianFltr(int myPixelData[],int myImageWidth,int myImageHeight)
   {
      //super(myFileName);
      super(myPixelData,myImageWidth,myImageHeight);
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      //grayValues = getFltrdData();
      MedianKrnl aMedianKrnl = new MedianKrnl();
      medianKrnl = aMedianKrnl.getKrnlMask(); 
      //initialize(myImageWidth, myImageHeight);
   }
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth, myImageHeight);     
      medianValues = new int[myImageWidth*myImageHeight];
   }
   public void filter(int myOriginalValues[],int i)
   {   
      filter3x3(myOriginalValues,i);
   }
   public void filter3x3(int myOriginalValues[],int i)
   {   
      super.filter3x3(myOriginalValues, i);

      grayValues = super.getFltrdData();
      
      if(isInBounds3x3(i))
      {
         //System.out.println("MedianFilter: orignalValues["+i+"]="+myOriginalValues[i]+", grayValues["+i+"] = "+grayValues[i]+", medianKrnl[1][1] = "+medianKrnl[1][1]);
         cnvlvsum = 
            grayValues[i-imageWidth-1]*medianKrnl[0][0]+
            grayValues[i-imageWidth]*medianKrnl[0][1]+
            grayValues[i-imageWidth+1]*medianKrnl[0][2]+
            grayValues[i-1]*medianKrnl[1][0]+
            grayValues[i]*medianKrnl[1][1]+
            grayValues[i+1]*medianKrnl[1][2]+
            grayValues[i+imageWidth-1]*medianKrnl[2][0]+
            grayValues[i+imageWidth]*medianKrnl[2][1]+
            grayValues[i+imageWidth+1]*medianKrnl[2][2];
         masksum = medianKrnl[0][0]+medianKrnl[0][1]+medianKrnl[0][2]+
               medianKrnl[1][0]+medianKrnl[1][1]+medianKrnl[1][2]+
               medianKrnl[2][0]+medianKrnl[2][1]+medianKrnl[2][2];
         medianValues[i] = ColorSpectra.cvrtYToGray((int)(cnvlvsum/masksum));                 
      }
   }
   
   public void filter9x9(int myGrayValues[],int anIndex)
   {
       int aWidthx3 = 3*getImageWidth();
       filter3x3(myGrayValues,anIndex-aWidthx3-3);
       filter3x3(myGrayValues,anIndex-aWidthx3);
       filter3x3(myGrayValues,anIndex-aWidthx3+3);
       filter3x3(myGrayValues,anIndex-3);
       filter3x3(myGrayValues,anIndex);
       filter3x3(myGrayValues,anIndex+3);
       filter3x3(myGrayValues,anIndex+aWidthx3-3);
       filter3x3(myGrayValues,anIndex+aWidthx3);
       filter3x3(myGrayValues,anIndex+aWidthx3+3);
   }   
   public void filter5x5(int myOriginalValues[],int i)
   {
      super.filter(myOriginalValues, i);

      grayValues = super.getFltrdData();
      
      if(isInBounds3x3(i))
      {
         //System.out.println("MedianFilter: orignalValues["+i+"]="+myOriginalValues[i]+", grayValues["+i+"] = "+grayValues[i]+", medianKrnl[1][1] = "+medianKrnl[1][1]);
         cnvlvsum = 
            grayValues[i-imageWidth-1]*medianKrnl[0][0]+
            grayValues[i-imageWidth]*medianKrnl[0][1]+
            grayValues[i-imageWidth+1]*medianKrnl[0][2]+
            grayValues[i-1]*medianKrnl[1][0]+
            grayValues[i]*medianKrnl[1][1]+
            grayValues[i+1]*medianKrnl[1][2]+
            grayValues[i+imageWidth-1]*medianKrnl[2][0]+
            grayValues[i+imageWidth]*medianKrnl[2][1]+
            grayValues[i+imageWidth+1]*medianKrnl[2][2];
         masksum = medianKrnl[0][0]+medianKrnl[0][1]+medianKrnl[0][2]+
               medianKrnl[1][0]+medianKrnl[1][1]+medianKrnl[1][2]+
               medianKrnl[2][0]+medianKrnl[2][1]+medianKrnl[2][2];
         medianValues[i] = ColorSpectra.cvrtYToGray((int)(cnvlvsum/masksum));                 
      }       
   }
   public int[] getFltrdData()
   {
      return medianValues;
   }
   public int[] getGryVls()
   {
      return grayValues;
   }
   public void setGryVls(int myGryVls[])
   {
      grayValues = myGryVls;
   }
}