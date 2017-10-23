/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.complabel;

import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import java.awt.Color;

/**
 *
 * @author finitesystem
 */
public class CnnctCmpntFillFltr extends ImageFilter {
    
    private static int imgWidth;
    private static int imgHeight;
    
    private int indexValues[]; 
    
    private static ImageDrawData ccLabelInfo;
    private boolean[][] mark;
    
    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        imgWidth = getImageWidth();
        imgHeight = getImageHeight();
        indexValues = new int[imgWidth * imgHeight];
        
        ImageFilter anImageFilter = getInputFilter();
        ccLabelInfo = new ImageDrawData(myImageWidth, myImageHeight); 
        mark = new boolean[imgHeight][imgWidth];
        //imageDrawData.setReservedColor1(reservedColor1);
        //imageDrawData.setReservedColor2(reservedColor2);   
    }    
    public void filter(int myOriginalValues[], int i) {

        int col = ImageTool.rtrvXPstn(i, imgWidth);        
        int row = ImageTool.rtrvYPstn(i, imgWidth);
        
        flood(myOriginalValues, mark, row, col, Color.BLACK, Color.RED);
    }    
    private static void sleep(int msec) {
        try {
            Thread.currentThread().sleep(msec);
        } catch (InterruptedException e) { }
    }
    
    private static void flood(int img[], boolean[][] mark,
                             int row, int col, Color srcColor, Color tgtColor) {
        // make sure row and col are inside the image
        if (row < 0) return;
        if (col < 0) return;
        if (row >= imgHeight) return;
        if (col >= imgWidth) return;
        
        // make sure this pixel hasn't been visited yet
        if (mark[row][col]) return;
        
        // make sure this pixel is the right color to fill
        int imgIndex = ImageTool.rtrvIndex(col, row, imgWidth);

        int imgIndexColor = img[imgIndex];
        Color imgColor = new Color(imgIndexColor);
        
        if (!imgColor.equals(srcColor)) return;
        
        // fill pixel with target color and mark it as visited
        //img.set(col, row, tgtColor);
        
        img[imgIndex] = tgtColor.getRGB();
        int c = img[imgIndex];
        ccLabelInfo.drawData(c, col, row);
        
        mark[row][col] = true;

        // animate
        //img.show();
        //sleep(25);
        
        // recursively fill surrounding pixels
        // (this is equivelant to depth-first search)
        flood(img, mark, row - 1, col, srcColor, tgtColor);
        flood(img, mark, row + 1, col, srcColor, tgtColor);
        flood(img, mark, row, col - 1, srcColor, tgtColor);
        flood(img, mark, row, col + 1, srcColor, tgtColor);
    }    
    public int[] getFltrdData() {
        //return dataValues;
        return ccLabelInfo.getImagePixels();
    }
}