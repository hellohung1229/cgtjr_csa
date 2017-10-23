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

public class SuppressFltr extends YSclFltr
{
   private int grayValues[];
   private int ngtvValues[]; 
   private int intnstyLvls = 256;
   private int lowerBound = 100;
   private int upperBound = 150;
   
   //private int imageWidth;

   public SuppressFltr(){}
   public SuppressFltr(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      //System.out.println("GrySclFltr: filename = "+myFileName);
   }
   public SuppressFltr(int myPixelData[],int myImageWidth,int myImageHeight)
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
      ngtvValues = new int[aWidth*aHeight];
   }
   public void filter(int myOriginalValues[],int i)
   {
       super.filter(myOriginalValues, i);
       grayValues = super.getFltrdData();
       int spprsBlue = cmptSpprssn(grayValues,i);
       ngtvValues[i] = ColorSpectra.cvrtYToGray(spprsBlue);
   }
   public int cmptSpprssn(int myOriginalValues[],int i)
   {
       int output = 0;       
       if(myOriginalValues[i] >= lowerBound && myOriginalValues[i] <= upperBound)
       {
           output = 0;
       }else{
           output = myOriginalValues[i];           
       }

       return output;
   }
   public int[] getFltrdData()
   {
      return ngtvValues;
   }
}