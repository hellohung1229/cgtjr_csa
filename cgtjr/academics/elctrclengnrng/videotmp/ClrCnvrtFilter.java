/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.videotmp;

/**
 *
 * @author clayton g thomas jr
 */


public class ClrCnvrtFilter extends ImgHVKrnlFltr
{
   private static int grayValues[];
   private int imageWidth;
   private static int intlzNmbr;

   public ClrCnvrtFilter()
   {
   }
   /*
   ClrCnvrtFilter(int myImageWidth,int myImageHeight)
   {
      super(myImageWidth,myImageHeight);
   }*/
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth,myImageHeight );
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
      imageWidth = myImageWidth;

      if(intlzNmbr == 0)
      {
         grayValues = new int[myImageWidth*myImageHeight];
         ++intlzNmbr;
      }
   }
   public void filter(int grayValues[],int myHData[],int myVData[],int i)
   {
   }
   public void filter(int myOriginalValues[],int i)
   {
      //grayValues[i] = myOrignalValues[i];
      //grdntFilter(grayValues,i);
      
      colorFilter(myOriginalValues,i);
   }
   public void colorFilter(int originalValues[],int i)
   {
      if(isInBounds3x3(i))
      {
         grayValues[i-imageWidth-1]=ColorCode.convertRGBToY(originalValues[i-imageWidth-1]);
         grayValues[i-imageWidth]=ColorCode.convertRGBToY(originalValues[i-imageWidth]);
         grayValues[i-imageWidth+1]=ColorCode.convertRGBToY(originalValues[i-imageWidth+1]);
         grayValues[i-1]=ColorCode.convertRGBToY(originalValues[i-1]);
         grayValues[i]=ColorCode.convertRGBToY(originalValues[i]);
         grayValues[i+1]=ColorCode.convertRGBToY(originalValues[i+1]);
         grayValues[i+imageWidth-1]=ColorCode.convertRGBToY(originalValues[i+imageWidth-1]);
         grayValues[i+imageWidth]=ColorCode.convertRGBToY(originalValues[i+imageWidth]);
         grayValues[i+imageWidth+1]=ColorCode.convertRGBToY(originalValues[i+imageWidth+1]);
      }
   }
   public int[] getFltrdData()
   {
      return grayValues;
   }
   public int[] getGrayValues()
   {
      return grayValues;
   }
}