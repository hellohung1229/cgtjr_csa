/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activecontour;
/**
 *
 * @author cthomas
 */
public class CrclrQueGrph
{
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    private int color = 0x0000ff00;

    int pix;
    int dy;
    int dx;
    int stepx, stepy;
    int anotherIndex;
    private CrclrQueCrdnt crclrQueCrdntGrph;
    private int state;
    private int imageData[];
    private int imageWidth;
    private int imageHeight;
    private int imageLength;

    private int fraction;
    public CrclrQueGrph()
    {}
    public CrclrQueGrph(CrclrQueCrdnt myCrclrQueCrdntGrph,int myImageData[])
    {
       crclrQueCrdntGrph = myCrclrQueCrdntGrph;
       imageData = myImageData;
    }
    public void setImageData(int myImageData[])
    {
       imageData = myImageData;    
    }
    public void setStartX(int myX)
    {
       x0 = myX;
    }
    public void setStartY(int myY)
    {
       y0 = myY;
    }
    public void setEndX(int myX)
    {
       x1 = myX;
    }
    public void setEndY(int myY)
    {
       y1 = myY;
    }
    public void setColor(int myColor)
    {
       color = myColor;
    }
    public int getState()
    {
      return state;
    }
    public void setState(int myState)
    {
      state = myState;
    }
    public void setWidth(int myImageWidth)
    {
       imageWidth = myImageWidth;
    }
    public void drawLine(int x0, int y0, int x1, int y1, int myColor)
    {
        int pix = myColor;
        int dy = y1 - y0;
        int dx = x1 - x0;
        int stepx, stepy;

        if (dy < 0) { dy = -dy;  stepy = -1; } else { stepy = 1; }
        if (dx < 0) { dx = -dx;  stepx = -1; } else { stepx = 1; }
        dy <<= 1;
        dx <<= 1;

        if (dx > dy) {
            int fraction = dy - (dx >> 1);
            while (x0 != x1) {
                if (fraction >= 0) {
                    y0 += stepy;
                    fraction -= dx;
                }
                x0 += stepx;
                fraction += dy;
                drawPoint(x0,y0, myColor);
            }
        } else {
            int fraction = dx - (dy >> 1);
            while (y0 != y1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }
                y0 += stepy;
                fraction += dx;
                drawPoint(x0,y0, myColor);
            }
        }
   }
   public void itrtGraphDrw()
   {
       if(state == 0 || state == 3)
       {
           x0 = crclrQueCrdntGrph.getXCrdnt();
           y0 = crclrQueCrdntGrph.getYCrdnt();
           int anIndex = crclrQueCrdntGrph.getNxtIndex();
           x1 = crclrQueCrdntGrph.getXCrdnt(anIndex);
           y1 = crclrQueCrdntGrph.getYCrdnt(anIndex);

           int aCount = crclrQueCrdntGrph.getCount();
           //System.out.println("CrdrQueGrph: x0,y0 = "+x0+","+y0);
           //System.out.println("CrdrQueGrph: x1,y1 = "+x1+","+y1);
           //System.out.println("CrdrQueGrph: anIndex = "+anIndex);
           //System.out.println("CrdrQueGrph: count = "+aCount);
           anotherIndex++;
           if(anotherIndex == aCount+1 )
           {
              state = 3;
              anotherIndex = 0;
           }else{
              dy = y1 - y0;
              dx = x1 - x0;
              if (dy < 0) { dy = -dy;  stepy = -1; } else { stepy = 1; }
              if (dx < 0) { dx = -dx;  stepx = -1; } else { stepx = 1; }
              dy <<= 1;
              dx <<= 1;
              if (dx > dy) {
                 fraction = dy - (dx >> 1);
              } else {
                  fraction = dx - (dy >> 1);
              }
              state = 1;
           }
       }else if(state == 1){
           itrtLineDrw();
       }
    }
    public void itrtLineDrw()
    {
        if (dx > dy) {
            //int fraction = dy - (dx >> 1);
            if (x0 != x1) {
                if (fraction >= 0) {
                    y0 += stepy;
                    fraction -= dx;
                }
                x0 += stepx;
                fraction += dy;
                drawPoint(x0,y0, color);
            }else{
                state = 0;
            }
        } else {
            //int fraction = dx - (dy >> 1);
            if (y0 != y1) {
                if (fraction >= 0) {
                    x0 += stepx;
                    fraction -= dy;
                }
                y0 += stepy;
                fraction += dx;
                drawPoint(x0,y0, color);
            }else{
               state = 0;
            }
        }
    }
    public void drawPoint(int myX,int myY,int myColor)
    {
        //System.out.println("CrdlQueGrph: x0 = "+myX+", y0 = "+myY+", width = "+imageWidth);
        //System.out.println("CrdlQueGrph: x1 = "+x1+", y1 = "+y1+", width = "+imageWidth);
        int anIndex = myY*imageWidth + myX;
        imageData[anIndex] = myColor;
    }
}
