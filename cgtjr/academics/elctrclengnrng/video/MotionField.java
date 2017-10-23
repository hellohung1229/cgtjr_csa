/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video;

/**
 *
 * @author clayton g thomas jr
 */
public class MotionField extends OpticalFlowFltr
{
   private int arrowBoxSize = 10;
   private int mtnFldData[];

   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth, myImageHeight);
      int aLength = myImageHeight*myImageWidth;
      mtnFldData = new int[aLength];
   }
   public void filter(int grayValues[],int i)
   {
      super.filter(grayValues, i);
      drawArrow(i);      
   }
   public void drawArrow(int i)
   {
      double anUntVlcty[] = getUntVlcty();         
      int x1 = rtrvXPstn(i);
      int y1 = rtrvYPstn(i);      
      int vx = (int)anUntVlcty[0]*arrowBoxSize;
      int vy = (int)anUntVlcty[1]*arrowBoxSize;
      int x2= x1 + vx;
      int y2= y1 + vy;
      
      drawArrow(x1,y1,x2,y2,0x00ffffff);
   } 
   /*
   public void drawArrows(int i)
   {
      crrntVelocity=getCrrntVelocity();
      if(i<getImageWidth())
      {
         return;
      }
      double arrowMgntd = Math.sqrt(crrntVelocity[i][0]*crrntVelocity[i][0] +
                       crrntVelocity[i][1]*crrntVelocity[i][1]);
      double unitXLength =  crrntVelocity[i][0]/arrowMgntd;
      double unitYLength =  crrntVelocity[i][1]/arrowMgntd;
      if(arrowMgntd == 0)
      {
         unitXLength = 0;
         unitYLength = 0;
      }
      double m = crrntVelocity[i][1]/crrntVelocity[i][0];
      int x1 = i%getImageWidth();
      int y1 = i/getImageWidth();
      int x2 = (int)(x1+unitXLength*arrowBoxSize);
      int y2 = (int)(y1+unitYLength*arrowBoxSize);
      int anIndex = 0;

      double d = getDistance(x1,y1,x2,y2);

      if(x1%arrowBoxSize == 0 && y1%arrowBoxSize == 0)
      {
         drawArrow(x1,y1,x2,y2,0x00ffffff);
      }
   }*/

   public void drawArrow(int x0,int y0,int x3,int y3,int myColor)
   {
      lineBresenham(x0,y0,x3,y3,myColor);

    }
    public void lineBresenham(int x0, int y0, int x1, int y1, int myColor)
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
   public void drawPoint(int x1,int y1,int myColor)
   {
      //System.out.println("OpticalFlow: x1 = "+x1+",y1 = "+y1);
      mtnFldData = getCornerData();
      int anIndex = y1*getImageWidth()+x1;

      if(anIndex >=0 && anIndex < getImageWidth()*getImageHeight())
      {
         mtnFldData[anIndex] = myColor;
      }
   }
   public void insertPoint(int myIndex)
   {
      //System.out.println("index = "+myIndex);
      if(myIndex >= 0 && myIndex < getImageWidth()*getImageHeight())
      {
         mtnFldData[myIndex] = 0x0000ff00;
      }
   }
   public int cnstrctArrwCntrBsX(int x1,int y1,int x2,int y2,int myLength)
   {
      int x = 0;

      if(x1 > x2){
         x = (int)(0.25*(x1-x2)+x2);
      }else if(x1 < x2){
         x = (int)(-0.25*(x2-x1)+x2);
      }else if(x1 == x2)
      {
         x = x2;
      }
      return x;
   }
   public int cnstrctArrwCntrBsY(int x1,int y1,int x2,int y2,int myLength)
   {
      int y = 0;

      if(y2>y1)
      {
         y = (int)(-0.25*(y2-y1)+y2);
      }else if(y2<y1){
         y = (int)(0.25*(y1-y2)+y2);
      }else if(y2 == y1)
      {
         y = y2;
      }                       // System.out.println("-0.25*(y2-y1) = "+(-0.25*(y2-y1))+" y = "+y);
      return y;
   }
   public int cnstrctArrwSdBsX1(int x1,int y1,int x2,int y2,int x3,int y3,int myLength)
   {
      double numerator = y2-y1;
      double denominator = x2-x1;
      double slope = 0;
      double slope2 = 0;
      int x4 = 0;

      if(denominator != 0)
      {
         slope = numerator/denominator;
         slope2 = slope*slope;
         x4 = (int)(myLength*Math.sqrt(slope2/(slope2+1))+x3);
      }else{
         x4 = x3+myLength;
      }
      return x4;
   }
   public int cnstrctArrwSdBsY1(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int myLength)
   {
      double numerator = y2-y1;
      double denominator = x2-x1;
      double slope1 = 0;
      double slope2 = 0;
      int y4 = 0;

      if(denominator != 0)
      {
         slope1 = numerator/denominator;
         if(x1 > x2)
         {
            slope1 = -1*slope1;
            y4 = (int)(1*(x4-x3)/slope1+y3);
         }else{
            y4 = (int)(-1*(x4-x3)/slope1+y3);
         }
         //System.out.println("OpticalFlow : y4 = "+y4+",x4="+x4+",x3="+x3+",y3="+y3);
      }else{
         y4 = y3;
      }
      return y4;
   }
   public int cnstrctArrwSdBsX2(int x1,int y1,int x2,int y2,int x3,int y3,int myLength)
   {
      double numerator = y2-y1;
      double denominator = x2-x1;
      double slope = 0;
      double slope2 = 0;
      int x4 = 0;

      if(denominator != 0)
      {
         slope = numerator/denominator;
         slope2 = slope*slope;
         x4 = (int)(-myLength*Math.sqrt(slope2/(slope2+1))+x3);
      }else{
         x4 = x3-myLength;
      }
      return x4;
   }
   public int cnstrctArrwSdBsY2(int x1,int y1,int x2,int y2,int x3,int y3,int myLength)
   {
      double numerator = y2-y1;
      double denominator = x2-x1;
      double slope = 0;
      double slope2 = 0;
      int y4 = 0;

      if(denominator != 0)
      {
         slope = numerator/denominator;
         slope2 = slope*slope;
         if(slope < 0 && y2 > y1 && x2 > x1)
         {
            myLength = -1*myLength;
         }
         if(slope > 0 && y2 > y1 && x2 > x1)
         {
            myLength = -1*myLength;
         }
         if(slope > 0 && y2 < y1 && x2 < x1)
         {
            myLength = -1*myLength;
         }
         if(numerator == 0)
         {
            y4 = y3 - myLength;
         }else if(y2 > y1 && x2 > x1){
            y4 = (int)(-1*myLength*Math.sqrt(slope2/(slope2+1))+y3);
         }else if(y2 < y1 && x2 < x1){
            y4 = (int)(-1*myLength*Math.sqrt(slope2/(slope2+1))+y3);
         }else if(y2 > y1 && x2 < x1){
            y4 = (int)(-1*myLength*Math.sqrt(slope2/(slope2+1))+y3);
         }else if(y2 < y1 && x2 > x1){
            y4 = (int)(-1*myLength*Math.sqrt(slope2/(slope2+1))+y3);
         }
      }else{
         y4 = y3;
      }
      return y4;
   }
   public double getDistance(int x0,int y0,int x3,int y3)
   {
      double d = Math.sqrt((y3-y0)*(y3-y0)+(x3-x0)*(x3-x0));
      return d;
   }
}