/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.imgprcssng;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author clayton g thomas jr
 */

public class InvrsFltr extends ImageFilter
{
   private int grayValues[];
   private int maskValues[];
   private int cmbnValues[]; 
   private int c = 1;
   private float gamma = 3;

   public InvrsFltr(){}
   public InvrsFltr(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      maskValues = new int[aWidth*aHeight];
   }
   public InvrsFltr(int myPixelData[],int myImageWidth,int myImageHeight)
   {
      super(myPixelData,myImageWidth,myImageHeight);
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      initialize(myImageWidth, myImageHeight);
   }
   public void setMaskValues(String myFileName)
   {
      ImageTool anImageTool = new ImageTool(myFileName);
      int inptPxlData[] = anImageTool.getImagePixels();
      int imageWidth = anImageTool.getImageWidth();
      int imageHeight = anImageTool.getImageHeight();
      setMaskValues(inptPxlData);      
   }
   public void setMaskValues(int myMaskValues[])
   {
       maskValues = myMaskValues;
   }
   public int[] getMaskValues()
   {
       return maskValues;
   }
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth, myImageHeight);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight]; 
      cmbnValues = new int[aWidth*aHeight];
   }
   public void filter(int myOriginalValues[],int i)
   {
       cmbnValues[i] = cmptOR(myOriginalValues,maskValues,i);
   }
   public int cmptOR(int myOriginalValues[],int myMaskValues[],int i)
   {       
       int orValue = ~myOriginalValues[i] & 0x00ffffff;
       return orValue;
   }
   public int[] getFltrdData()
   {
      return cmbnValues;
   }
}