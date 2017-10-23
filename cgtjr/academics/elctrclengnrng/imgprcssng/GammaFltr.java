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

public class GammaFltr extends YSclFltr
{
   private int grayValues[];
   private int ngtvValues[]; 
   private int c = 1;
   private float gamma = 3;
   
   //private int imageWidth;

   public GammaFltr(){}
   public GammaFltr(String myFileName)
   {
      super(myFileName);
      int aWidth = getImageWidth();
      int aHeight = getImageHeight();
      grayValues = new int[aWidth*aHeight];
      //System.out.println("GrySclFltr: filename = "+myFileName);
   }
   public GammaFltr(int myPixelData[],int myImageWidth,int myImageHeight)
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
       int negBlue = cmptGamma(grayValues,i);
       ngtvValues[i] = ColorSpectra.cvrtYToGray(negBlue);
   }
   public int cmptGamma(int myOriginalValues[],int i)
   {
       int output = 0;
       double input = myOriginalValues[i]/255.0;
       double s = c*Math.pow(input,gamma);
       output = (int)(255.0*s);
       return output;
   }
   public int[] getFltrdData()
   {
      return ngtvValues;
   }
}