/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng.color;

import cgtjr.academics.elctrclengnrng.imgprcssng.GraySclFltr;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.general.ColorSpectra;

/**
 *
 * @author clayton g thomas jr
 */

public class RGBToHFltr extends GraySclFltr
{
   private int grayValues[];
   private int hsiValues[]; 
   private int intnstyLvls = 256;
   
   //private int imageWidth;

   public RGBToHFltr(){}
   public RGBToHFltr(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      //System.out.println("GrySclFltr: filename = "+myFileName);
   }
   public RGBToHFltr(int myPixelData[],int myImageWidth,int myImageHeight)
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
      hsiValues = new int[aWidth*aHeight];
   }
   public void filter(int myOriginalValues[],int i)
   {
       //super.filter(myOriginalValues, i);
       hsiValues[i] = ColorSpectra.convertRGBToY(myOriginalValues[i]);
   }
   public int cmpteNgtve(int myOriginalValues[],int i)
   {
       int output = intnstyLvls - 1 - myOriginalValues[i];
       return output;
   }
   public int[] getFltrdData()
   {
      return hsiValues;
   }
}