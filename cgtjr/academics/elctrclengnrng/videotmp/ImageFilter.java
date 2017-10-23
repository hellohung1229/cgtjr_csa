package cgtjr.academics.elctrclengnrng.videotmp;


public abstract class ImageFilter
{
   private GenericHVKrnl imageKernal;
   private int imageWidth;
   private int imageHeight;
   private int hrzntlData[];
   private int vrtclData[];
   private int fltrdData[];
   private int imageData[];
   protected static int frameNumber = 0;

   public ImageFilter(){}
   public ImageFilter(int myImageWidth,int myImageHeight)
   {
      imageWidth = myImageWidth;
      imageHeight = myImageHeight;
      imageKernal = new GenericHVKrnl();
   }
   public void setImageWidth(int myWidth)
   {
      imageWidth = myWidth;
   }
   public void setImageHeight(int myHeight)
   {
      imageHeight = myHeight;
   }
   public int getImageWidth()
   {
      return imageWidth;
   }
   public int getImageHeight()
   {
      return imageHeight;
   }
   public int[] getFltrdData()
   {
      return fltrdData;
   }
   public int[] getImageData()
   {
      return imageData;
   }
   public int[] getHrzntlData()
   {
      return hrzntlData;
   }
   public int[] getVrtclData()
   {
      return hrzntlData;
   }
   public void initialize(int myImageWidth,int myImageHeight)
   {
      setImageWidth(myImageWidth);
      setImageHeight(myImageHeight);
   }
   public boolean isInBounds3x3(int myIndex)
   {
      boolean inBounds = false;
      int x0 = (myIndex-imageWidth-1)%imageWidth;
      int y0 = (myIndex-imageWidth-1)/imageWidth;
      int x1 = (myIndex-imageWidth)%imageWidth;
      int y1 = (myIndex-imageWidth)/imageWidth;
      int x2 = (myIndex-imageWidth+1)%imageWidth;
      int y2 = (myIndex-imageWidth+1)/imageWidth;
      int x3 = (myIndex-1)%imageWidth;
      int y3 = (myIndex-1)/imageWidth;
      int x4 = (myIndex)%imageWidth;
      int y4 = (myIndex)/imageWidth;
      int x5 = (myIndex+1)%imageWidth;
      int y5 = (myIndex+1)/imageWidth;
      int x6 = (myIndex+imageWidth-1)%imageWidth;
      int y6 = (myIndex+imageWidth-1)/imageWidth;
      int x7 = (myIndex+imageWidth)%imageWidth;
      int y7 = (myIndex+imageWidth)/imageWidth;
      int x8 = (myIndex+imageWidth+1)%imageWidth;
      int y8 = (myIndex+imageWidth+1)/imageWidth;

      if( x0 >= 0 && x0 < imageWidth && y0 >=0 && y0 < imageHeight &&
          x1 >= 0 && x1 < imageWidth && y1 >=0 && y1 < imageHeight &&
          x2 >= 0 && x2 < imageWidth && y2 >=0 && y2 < imageHeight &&
          x3 >= 0 && x3 < imageWidth && y3 >=0 && y3 < imageHeight &&
          x4 >= 0 && x4 < imageWidth && y4 >=0 && y4 < imageHeight &&
          x5 >= 0 && x5 < imageWidth && y5 >=0 && y5 < imageHeight &&
          x6 >= 0 && x6 < imageWidth && y6 >=0 && y6 < imageHeight &&
          x7 >= 0 && x7 < imageWidth && y7 >=0 && y7 < imageHeight &&
          x8 >= 0 && x8 < imageWidth && y8 >=0 && y8 < imageHeight )
      {
         inBounds = true;
      }
      return inBounds;
   }
   public void finishPrsng(){}
   public void startPrsng(int a,int b){}
   public abstract void filter(int myValue[],int i);
   public abstract void filter(int myHVData[],int myHrzntlData[],int myVrtclData[],int i);
}