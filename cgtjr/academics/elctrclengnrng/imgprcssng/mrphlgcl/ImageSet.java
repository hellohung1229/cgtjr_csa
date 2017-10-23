/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import java.awt.image.BufferedImage;

/**
 *
 * @author clayton g thomas jr
 */
public class ImageSet {
    private int imageData[];
    private int width;
    private int height;
    private int threshold = 0xff;
    
    private BufferedImage elmntBffrdImage;
            
    public ImageSet()
    {         
    }
    public ImageSet(int myImgData[],int myWidth,int myHeight)
    {        
        imageData = myImgData;
        width = myWidth;
        height = myHeight;
    }
    public ImageSet(String aFileName)
    {
        elmntBffrdImage = ImageTool.rdImageFile(aFileName);
        width = elmntBffrdImage.getWidth();
        height = elmntBffrdImage.getHeight();
        imageData = ImageTool.rtrv1DPxls(elmntBffrdImage);
    }
    public void setImageData(int myImageData[])
    {
        imageData = myImageData;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public void setWidth(int myWidth)
    {
        width = myWidth;
    }
    public void setHeight(int myHeight)
    {
        height = myHeight;
    }    
    public int[] getImageData()
    {
        return imageData;
    }

    public boolean isPxlOn(int x,int y)
    {
        boolean isPixel = false;
        
        if(x < 0 || x>= width || y < 0 || y >= height)
        {
            return isPixel;
        }

        int anIndex = ImageTool.rtrvIndex(x, y, width);
        int aColor = imageData[anIndex];

        int aBinColor = ColorSpectra.cnvrtRGBGry8(aColor);
        //System.out.println("ImageSet.isPxlOn(): color = "+aColor+", aBinColor = "+aBinColor +", threshold = "+threshold);
        if(aBinColor == threshold)
        {
           isPixel = true;
        }        
        return isPixel;
    }
}
