/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class ImgPad {
    private int padColor;
    private int subRgnWdth;
    private int subRgnHght;
    private int xPadLft;
    private int xPadRght;    
    private int yPadTop;
    private int yPadBttm;
    private int data[];
    private int imgTtlWdth;
    private int imgTtlHght;
    private int subImgWdth;
    private int subImgHght;
    private int subImgXMin;
    private int subImgYMin;
    
    
    public ImgPad(int myImageData[],int myWidth1,int myHeight1)
    {
        data = myImageData;
        imgTtlWdth = myWidth1;
        imgTtlHght = myHeight1;        
    }
    public ImgPad(int myMinX,int myMinY,int myWidth2,int myHeight2)
    {
        //data = myImageData;
        subImgXMin = myMinX;
        subImgYMin = myMinY;
        subImgWdth = myWidth2;
        subImgHght = myHeight2;
    }
    public int[] crtSubImg(Vector myVector)
    {
       Pnt aPnt = null;
       int aSize = myVector.size();
       int x1 = 0;
       int y1 = 0;
       int x2 = 0;
       int y2 = 0;
       
       int width = subImgWdth/2;
       int height = subImgHght/2;
       
       //int imgData[][] = new int[subImgWdth+1][subImgHght+1];

       int imgData1D[] = new int[(subImgWdth+1)*(subImgHght+1)];

       for(int i=0;i<aSize;i++)
       {
          aPnt = (Pnt)myVector.get(i);
          x1 = (int)aPnt.getX1();
          y1 = (int)aPnt.getY1();
          x2 = x1 - subImgXMin;
          y2 = y1 - subImgYMin;
          System.out.println("ImgPadding: minX = "+subImgXMin+", minY = "+subImgYMin+", subImgWdth = "+subImgWdth+", subImgHght = "+subImgHght);
          System.out.println("ImgPadding: x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2="+y2+", color = "+aPnt.getColor());

          int anIndex = ImageTool.rtrvIndex(x2, y2, width);

          imgData1D[anIndex] = aPnt.getColor();
       }
       return imgData1D;
    }
    public void setPadColor(int myColor)
    {
        padColor = myColor;
    }
    public int getPadColor()
    {
        return padColor;
    }        
    
    /*
    public int[] rtrvGrtrThrshld(int myData[],int myWidth,int myLength,int myValue)
    {       
        int aLength = myData.length;
        
        for(int i=0;i<aLength;i++)
        {
            if(myData[i] >= myValue)
            {
               
            }
        }
    }*/
    public void cmptSubRgnWdth(int myData[],int myWidth,int myLength)
    {
        int aWidth = 0;
        int xMid = myWidth/2;
        int yMid = myLength/2;
        for(int i=0;i<myWidth;i++)
        {
           int aColor = ImageTool.rtrvPixel(myData, myWidth, myLength, i, yMid);
           if(aColor != 255)
           {
               subRgnWdth++;
           }else if(i<xMid){
               xPadLft++;
           }else if(i<xMid){
               xPadRght++;
           }           
        }        
    }                
    public void cmptSubRgnHght(int myData[],int myWidth,int myHeight)
    {
        int aWidth = 0;
        int xMid = myWidth/2;
        int yMid = myHeight/2;
        for(int i=0;i<myHeight;i++)
        {
           int aColor = ImageTool.rtrvPixel(myData, myWidth, myHeight, xMid, i);
           if(aColor != 255)
           {
               subRgnHght++;
           }else if(i<yMid){
               yPadTop++;            
           }else if(i>yMid){
               yPadBttm++;            
           }
        } 
    }             
}