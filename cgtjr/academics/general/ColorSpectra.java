package cgtjr.academics.general;

import java.awt.Color;

public class ColorSpectra {

    public static int cvrtYToGray(int myY) {
        int gray = ((myY & 0x000000ff) << 16)
                | ((myY & 0x000000ff) << 8)
                | ((myY & 0x000000ff));
        return gray;
    }

    public static int[] convertRGBToGray(int rgb[]) {
        int aLength = rgb.length;
        int gray[] = new int[aLength];
        int red = 0;
        int green = 0;
        int blue = 0;

        int luminance = 0;
        int chrominanceBlue = 0;
        int chrominanceRed = 0;

        for (int i = 0; i < aLength; i++) {
            gray[i] = convertRGBToGray(rgb[i]);
        }
        return gray;
    }

    public static int convertRGBToGray(int rgb) {
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
        luminance = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        gray = ((luminance & 0x000000ff) << 16)
                | ((luminance & 0x000000ff) << 8)
                | ((luminance & 0x000000ff));
        return gray;
    }

    public static int rtrvRed(int rgb) {
        int red = 0;
        red = (rgb >> 16) & 0x000000ff;
        return red;
    }

    public static int rtrvGreen(int rgb) {
        int green = 0;
        green = (rgb >> 8) & 0x000000ff;
        return green;
    }

    public static int rtrvBlue(int rgb) {
        int blue = 0;
        blue = (rgb & 0x000000ff);
        return blue;
    }

    public static int cnvrtRGBGry(int rgb) {
        int gray = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int luminance = 0;

        red = (rgb >> 16) & 0x000000ff;
        green = (rgb >> 8) & 0x000000ff;
        blue = (rgb & 0x000000ff);
        luminance = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        gray = ((luminance & 0x000000ff) << 16)
                | ((luminance & 0x000000ff) << 8)
                | ((luminance & 0x000000ff));
        return gray;
    }

    public static int cnvrtRGBGry8(int rgb) {
        int gray = 0;
        int trns = 0;
        int red = 0;
        int green = 0;
        int blue = 0;

        trns = (rgb >> 24) & 0x000000ff;
        red = (rgb >> 16) & 0x000000ff;
        green = (rgb >> 8) & 0x000000ff;
        blue = (rgb & 0x000000ff);
        gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        //System.out.println("ColorCode: rgb = "+rgb +",red = "+red+", green = "+green+", blue = "+blue+", trans. = "+trns);
        return gray;
    }

    public static int convertRGBToYIQ(int rgb) {
        int red = 0;
        int green = 0;
        int blue = 0;

        int luminance = 0;
        int intensity = 0;
        int chrominance = 0;
        int yiq = 0;

        red = (rgb >> 16) & 0x000000ff;
        green = (rgb >> 8) & 0x000000ff;
        blue = (rgb & 0x000000ff);
        luminance = (int) (0.29 * red + 0.587 * green + 0.114 * blue);
        intensity = (int) (0.60 * red - 0.28 * green + 0.32 * blue);
        chrominance = (int) (0.21 * red - 0.54 * green - 0.31 * blue);
        yiq = ((luminance & 0x000000ff) << 16)
                | ((intensity & 0x000000ff) << 8)
                | ((chrominance & 0x000000ff));

        return yiq;
    }

    public static int updateYIntnsty(int myYUV, float myPercent) {
        int yTmp = 0;
        int uTmp = 0;
        int vTmp = 0;

        int y = 0;
        int u = 0;
        int v = 0;
        int yuv = 0;

        yTmp = (myYUV >> 16) & 0x000000ff;
        uTmp = (myYUV >> 8) & 0x000000ff;
        vTmp = (myYUV & 0x000000ff);
        y = (int) (yTmp * myPercent);
        u = uTmp;
        v = vTmp;
        yuv = ((y & 0x000000ff) << 16)
                | ((u & 0x000000ff) << 8)
                | ((v & 0x000000ff));

        return yuv;
    }

    public static int convertRGBToYUV(int rgb) {

        int red = 0;
        int green = 0;
        int blue = 0;

        int y = 0;
        int u = 0;
        int v = 0;
        int yuv = 0;

        red = (rgb >> 16) & 0x000000ff;
        green = (rgb >> 8) & 0x000000ff;
        blue = (rgb & 0x000000ff);
        y = (int) (0.29 * red + 0.587 * green + 0.114 * blue);
        u = (int) (0.493 * (red - y));
        v = (int) (0.877 * (red - y));
        yuv = ((y & 0x000000ff) << 16)
                | ((u & 0x000000ff) << 8)
                | ((v & 0x000000ff));

        return yuv;
    }

    public static int[] convertRGBToYCbCr(int rgb[]) {
        int aLength = rgb.length;
        int ycbcr[] = new int[aLength];
        int red = 0;
        int green = 0;
        int blue = 0;

        int luminance = 0;
        int chrominanceBlue = 0;
        int chrominanceRed = 0;

        for (int i = 0; i < aLength; i++) {
            red = (rgb[i] >> 16) & 0x000000ff;
            green = (rgb[i] >> 8) & 0x000000ff;
            blue = (rgb[i] & 0x000000ff);
            luminance = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
            chrominanceBlue = (int) (-0.16875 * red - 0.33126 * green + 0.5 * blue);
            chrominanceRed = (int) (0.5 * red - 0.41869 * green - 0.08131 * blue);
            ycbcr[i] = ((luminance & 0x000000ff) << 16)
                    | ((chrominanceBlue & 0x000000ff) << 8)
                    | ((chrominanceRed & 0x000000ff));
        }
        return ycbcr;
    }

    public int[] convertYCbCrToRGB(int ycbcr[]) {
        int aLength = ycbcr.length;
        int rgb[] = new int[aLength];
        int red = 0;
        int green = 0;
        int blue = 0;

        int luminance = 0;
        int chrominanceBlue = 0;
        int chrominanceRed = 0;

        for (int i = 0; i < aLength; i++) {
            luminance = (ycbcr[i] >> 16) & 0x000000ff;
            chrominanceBlue = (ycbcr[i] >> 8) & 0x000000ff;
            chrominanceRed = (ycbcr[i] & 0x000000ff);

            red = (int) (luminance + 1.140 * chrominanceRed);
            green = (int) (luminance - 0.395 * chrominanceBlue - 0.581 * chrominanceRed);
            blue = (int) (luminance + 2.032 * chrominanceBlue);

            rgb[i] = ((red & 0x000000ff) << 16)
                    | ((green & 0x000000ff) << 8)
                    | ((blue & 0x000000ff));
        }
        return rgb;//check 
    }

    public static int convertRGBToY(int rgb) {
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
        luminance = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        return luminance;
    }

    public static int rtrv000000ff(int rgb) {
        int gray = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int trns = 0;
        int luminance = 0;
        int chrominanceBlue = 0;
        int chrominanceRed = 0;

        trns = (rgb >> 24) & 0x000000ff;
        red = (rgb >> 16) & 0x000000ff;
        green = (rgb >> 8) & 0x000000ff;
        blue = (rgb & 0x000000ff);
        luminance = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
        //return luminance;
        //System.out.println("ColorCode: rgb = "+rgb +",red = "+red+", green = "+green+", blue = "+blue+", trans. = "+trns);
        return blue;
    }

    public static float[] cnvrtHSIToRGB(float myH) {
        return cnvrtHSIToRGB(myH, .500f, .500f);
    }
    public static int cnvrtHSIToRGB(int myHSI)
    {
        int h = (myHSI >> 16) & 0x000000ff;
        int s = (myHSI >> 8) & 0x000000ff;
        int i = (myHSI & 0x000000ff);  
        float aRGB[] = cnvrtHSIToRGB((float)h,(float)s,(float)i);
        int r = (int)aRGB[0];
        int g = (int)aRGB[1];
        int b = (int)aRGB[2];
        
        int rgb = ((r & 0x000000ff) << 16)
                | ((g & 0x000000ff) << 8)
                | ((b & 0x000000ff));
        return rgb;        
    }
    public static float[] cnvrtHSIToRGB(float myH, float myS, float myI) {
        double aRed = 0;
        double aGreen = 0;
        double aBlue = 0;

        double anAngle = myH * 2 * Math.PI;
        double anHAngle = 0;

        if (anAngle >= 0 && anAngle < 2 * Math.PI / 3) {
            anHAngle = anAngle;
            aBlue = myI * (1 - myS);
            aRed = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aGreen = 3 * myI - (aRed + aBlue);
        } else if (anAngle >= 2 * Math.PI / 3 && anAngle < 4 * Math.PI / 3) {
            anHAngle = anAngle - 2 * Math.PI / 3;
            aRed = myI * (1 - myS);
            aGreen = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aBlue = 3 * myI - (aRed + aGreen);
        } else if (anAngle >= 4 * Math.PI / 3 && anAngle <= 2 * Math.PI) {
            anHAngle = anAngle - 4 * Math.PI / 3;
            aGreen = myI * (1 - myS);
            aBlue = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aRed = 3 * myI - (aGreen + aBlue);
        }
        float rgb[] = new float[3];
        rgb[0] = (float) aRed;
        rgb[1] = (float) aGreen;
        rgb[2] = (float) aBlue;
        return rgb;
    }
    //This should be modified ...
    public static int cnvrtYToRGB(float myH) {
        int color[] = cnvrtHSBToRGB(myH, .500f, .500f);

        int rgb = ((color[0] & 0x00ff00000))
                | ((color[1] & 0x0000ff00))
                | ((color[2] & 0x000000ff));
        return rgb;
    }

    public static int[] cnvrtHSBToRGB(float myH) {
        return cnvrtHSBToRGB(myH, .500f, .500f);
    }

    public static int[] cnvrtHSBToRGB(float myH, float myS, float myI) {
        double aRed = 0;
        double aGreen = 0;
        double aBlue = 0;

        double anAngle = myH * 2 * Math.PI;
        double anHAngle = 0;

        if (anAngle >= 0 && anAngle < 2 * Math.PI / 3) {
            anHAngle = anAngle;
            aBlue = myI * (1 - myS);
            aRed = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aGreen = 3 * myI - (aRed + aBlue);
        } else if (anAngle >= 2 * Math.PI / 3 && anAngle < 4 * Math.PI / 3) {
            anHAngle = anAngle - 2 * Math.PI / 3;
            aRed = myI * (1 - myS);
            aGreen = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aBlue = 3 * myI - (aRed + aGreen);
        } else if (anAngle >= 4 * Math.PI / 3 && anAngle <= 2 * Math.PI) {
            anHAngle = anAngle - 4 * Math.PI / 3;
            aGreen = myI * (1 - myS);
            aBlue = myI * (1 + myS * Math.cos(anHAngle) / Math.cos(Math.PI / 3 - anHAngle));
            aRed = 3 * myI - (aGreen + aBlue);
        }
        int rgb[] = new int[3];
        rgb[0] = (int) (aRed * 256);
        rgb[1] = (int) (aGreen * 256);
        rgb[2] = (int) (aBlue * 256);
        return rgb;
    }

    public static int cnvrtRGBFLTToRGBINT(float r, float g, float b) {
        Color aColor = new Color(r, g, b);
        int aRGBColor = aColor.getRGB();
        return aRGBColor;
    }
    public static int cnvrtRGBINToRGBINT(int r, int g, int b) {
        Color aColor = new Color(r, g, b);
        int aRGBColor = aColor.getRGB();
        return aRGBColor;
    }    
    public static int cnvrtRGBToHSI(int myRGB)
    {
        int red = (myRGB >> 16) & 0x000000ff;
        int green = (myRGB >> 8) & 0x000000ff;
        int blue = (myRGB & 0x000000ff);  
        float aHSI[] = cnvrtRGBToHSI((float)red,(float)green,(float)blue);
        int h = (int)aHSI[0];
        int s = (int)aHSI[1];
        int i = (int)aHSI[2];
        
        int hsi = ((h & 0x000000ff) << 16)
                | ((s & 0x000000ff) << 8)
                | ((i & 0x000000ff));
        return hsi;        
    }
    public static float[] cnvrtRGBToHSI(float r, float g, float b) {
        float theta = 0;
        float strtn = 0;
        float intnsty = 0;
        float hsi[] = new float[3];
        if (b <= g) {
            double v1 = (float) ((.5 * (r - b) + .5 * (r - b)));
            double v2 = Math.sqrt((r - g) * (r - g) + (r - b) * (g - b));
            theta = (float) Math.acos(v1 / v2);
        } else {
            double v1 = (float) ((.5 * (r - b) + .5 * (r - b)));
            double v2 = Math.sqrt((r - g) * (r - g) + (r - b) * (g - b));
            theta = 360 - (float) Math.acos(v1 / v2);
        }
        strtn = 1 - (3 / (r + g + b)) * Math.min(r, Math.min(g, b));
        intnsty = (r + g + b) / 3;
        hsi[0] = theta;
        hsi[1] = strtn;
        hsi[2] = intnsty;
        return hsi;
    }
    /*
     * public float[] cnvrtHSIToRGB(float h, float s, float i) { float b = 0;
     * float r = 0; float g = 0; float rgb[] = new float[3];
     *
     * b = i * (1 - s); r = i * (1 + (s * Math.cos(h) / Math.cos(60 - h))); if
     * (h >= 0 && h < 120) { g = 3 * i - (r + b); } else if (h >= 120 && h <
     * 240) { g = 3 * i - (r + g); } else if (h >= 240 && h <= 360) { g = 3 * i
     * - (g + b); } rgb[0] = r; rgb[1] = g; rgb[2] = b; return rgb;       
    }
     */
}