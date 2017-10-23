package cgtjr.academics.elctrclengnrng.videotmp;

public class ColorCode
{
   public static int[] convertRGBToGray(int rgb[])
   {
      int aLength = rgb.length;
      int gray[] = new int[aLength];
      int red = 0;
      int green = 0;
      int blue = 0;

      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      for(int i = 0;i< aLength;i++)
      {
         gray[i]=convertRGBToGray(rgb[i]);
      }
      return gray;
   }
   public static int convertRGBToGray(int rgb)
   {
      int gray = 0;
      int red = 0;
      int green = 0;
      int blue = 0;
      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      red = (rgb >> 16) & 0x000000ff;
      green = (rgb >> 8) & 0x000000ff;
      blue = (rgb & 0x000000ff);
      luminance = (int)(0.299*red + 0.587*green + 0.114*blue);
      gray = ((luminance & 0x000000ff) << 16)  |
                    ((luminance & 0x000000ff) << 8) | 
                    ((luminance & 0x000000ff));
      return gray;
   }
   public static int[] convertRGBToYCbCr(int rgb[])
   {
      int aLength = rgb.length;
      int ycbcr[] = new int[aLength];
      int red = 0;
      int green = 0;
      int blue = 0;

      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      for(int i = 0;i< aLength;i++)
      {
         red = (rgb[i] >> 16) & 0x000000ff;
         green = (rgb[i] >> 8) & 0x000000ff;
         blue = (rgb[i] & 0x000000ff);
         luminance = (int)(0.299*red + 0.587*green + 0.114*blue);
         chrominanceBlue = (int)(-0.16875*red - 0.33126*green + 0.5*blue);
         chrominanceRed = (int)(0.5*red - 0.41869*green - 0.08131*blue);
         ycbcr[i] = ((luminance & 0x000000ff)  << 16)  |
                    ((chrominanceBlue & 0x000000ff) << 8) | 
                    ((chrominanceRed & 0x000000ff));
      }
      return ycbcr;
   }
   public int[] convertYCbCrToRGB(int ycbcr[])
   {
      int aLength = ycbcr.length;
      int rgb[] = new int[aLength];
      int red = 0;
      int green = 0;
      int blue = 0;

      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      for(int i = 0;i< aLength;i++)
      {
         luminance = (ycbcr[i] >> 16) & 0x000000ff;
         chrominanceBlue = (ycbcr[i] >> 8) & 0x000000ff;
         chrominanceRed = (ycbcr[i] & 0x000000ff);

         red = (int)(luminance + 1.140*chrominanceRed);
         green = (int)(luminance - 0.395*chrominanceBlue - 0.581*chrominanceRed);
         blue = (int)(luminance  + 2.032*chrominanceBlue);

         rgb[i] =   ((red & 0x000000ff)  << 16)  | 
                    ((green & 0x000000ff) << 8)   | 
                    ((blue & 0x000000ff));
      }
      return ycbcr;
   }
   public static int convertRGBToY(int rgb)
   {
      int gray = 0;
      int red = 0;
      int green = 0;
      int blue = 0;
      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      red = (rgb >> 16) & 0x000000ff;
      green = (rgb >> 8) & 0x000000ff;
      blue = (rgb & 0x000000ff);
      luminance = (int)(0.299*red + 0.587*green + 0.114*blue);
      //if(luminance <= 10) luminance = 0;
      return luminance;
   }
   public static float rtrvRedf(int rgb)
   {
      float red = 0;
      red = ((rgb >> 16) & 0x000000ff)/255.0f;
      return red;
   }
   public static float rtrvGreenf(int rgb)
   {
      float green = 0;
      green = ((rgb >> 8) & 0x000000ff)/255.0f;
      return green;
   } 
   public static float rtrvBluef(int rgb)
   {
      float blue = 0;
      blue = ((rgb & 0x000000ff))/255.0f;
      return blue;
   }  
   public static int rtrvRed(int rgb)
   {
      int red = 0;
      red = (rgb >> 16) & 0x000000ff;
      return red;
   }  
   public static int rtrvGreen(int rgb)
   {
      int green = 0;
      green = (rgb >> 8) & 0x000000ff;
      return green;
   }    
   public static int rtrvBlue(int rgb)
   {
      int blue = 0;
      blue = (rgb & 0x000000ff);
      return blue;
   }     
   public static int rtrvGrayff(int rgb)
   {
      int gray = 0;
      int red = 0;
      int green = 0;
      int blue = 0;
      int luminance = 0;
      int chrominanceBlue = 0;
      int chrominanceRed = 0;

      red = (rgb >> 16) & 0x000000ff;
      green = (rgb >> 8) & 0x000000ff;
      blue = (rgb & 0x000000ff);
      luminance = (int)(0.299*red + 0.587*green + 0.114*blue);
      return luminance;
      //return blue;
   }
   public static float[] cnvrtHSIToRGB(float myH)
   {
      return cnvrtHSIToRGB(myH,.500f,.500f);
   }
   public static float[] cnvrtHSIToRGB(float myH,float myS,float myI)
   {
      double aRed = 0;
      double aGreen = 0;
      double aBlue = 0;

      double anAngle = myH*2*Math.PI;   
      double anHAngle = 0;

      if(anAngle >= 0 && anAngle < 2*Math.PI/3)
      {
        anHAngle = anAngle;
        aBlue = myI*(1-myS);
        aRed = myI*(1+myS*Math.cos(anHAngle)/Math.cos(Math.PI/3-anHAngle));
        aGreen = 3*myI - (aRed + aBlue);
      }else if(anAngle >= 2*Math.PI/3 && anAngle < 4*Math.PI/3)
      {
        anHAngle = anAngle - 2*Math.PI/3;
        aRed = myI*(1-myS);
        aGreen = myI*(1+myS*Math.cos(anHAngle)/Math.cos(Math.PI/3-anHAngle));
        aBlue = 3*myI - (aRed + aGreen);
      }else if(anAngle >= 4*Math.PI/3 && anAngle <= 2*Math.PI)
      {
        anHAngle = anAngle - 4*Math.PI/3;
        aGreen = myI*(1-myS);
        aBlue = myI*(1+myS*Math.cos(anHAngle)/Math.cos(Math.PI/3-anHAngle));
        aRed = 3*myI - (aGreen + aBlue);
      }
      float rgb[] = new float[3];
      rgb[0] = (float)aRed;
      rgb[1] = (float)aGreen;
      rgb[2] = (float)aBlue;
      return rgb;
   }
}