package cgtjr.academics.general.gui;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;

import java.applet.Applet;
import java.net.*;

public class UgotImage implements ImageObserver
{
   private BufferedImage theBufferedImage;

   public UgotImage(){}
   /*
   public UgotImage(Image anImage)
   {
      theBufferedImage = anImage;
   }*/
   public UgotImage(BufferedImage anImage)
   {
      theBufferedImage = anImage;
   }
   public static Image createImage(String anImageName)
   {
      Image anImage1 = null;
      //System.out.println("UgotImage.createImage() : image name = "+anImageName);   
      try{
         anImage1 = Toolkit.getDefaultToolkit().createImage(new URL(anImageName));
      }catch(IOException ioe){
         System.out.println("UgotImage.createImage(): " + ioe.getMessage());
      }
      return anImage1;
   }
   public static BufferedImage createBufferedImage(String anImageName)
   {
      Image anImage1 = null;
      //System.out.println("UgotImage.createImage() : image name = "+anImageName);   
      try{
         anImage1 = Toolkit.getDefaultToolkit().createImage(new URL(anImageName));
      }catch(IOException ioe){
         System.out.println("UgotImage.createImage(): " + ioe.getMessage());
      }
      return createBufferedImage(anImage1);
   }
   public static Image createImage(URL anImageName)
   {
      Image anImage1 = null;
      //System.out.println("UgotImage.createImage() : image name = "+anImageName);
   
      //try{
         anImage1 = Toolkit.getDefaultToolkit().createImage(anImageName);
      //}catch(IOException ioe){
      //   System.out.println("UgotImage.createImage(): " + ioe.getMessage());
      //}
    
      return anImage1;
   }   
   public static Image createImage(Image anImage1) throws NullPointerException
   {
      //BufferedImage aBufferedImage = new BufferedImage(anImage1.getWidth(null),anImage1.getHeight(null),BufferedImage.TYPE_INT_RGB);
      BufferedImage aBufferedImage = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
      Graphics2D aGraphics2D = aBufferedImage.createGraphics();
      aGraphics2D.drawImage(anImage1,0,0,null);
      return aBufferedImage;
   }
   public static BufferedImage createBufferedImage(Image anImage1)
   {
      //BufferedImage aBufferedImage = new BufferedImage(anImage1.getWidth(null),anImage1.getHeight(null),BufferedImage.TYPE_INT_RGB);
      BufferedImage aBufferedImage = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
      Graphics2D aGraphics2D = aBufferedImage.createGraphics();
      aGraphics2D.drawImage(anImage1,0,0,null);
      return aBufferedImage;
   }
   /*
   public static Image createImage(Image image, int x, int y, int width, int height, int transform)
   {
      BufferedImage aBufferedImage = new BufferedImage(130,130,BufferedImage.TYPE_INT_RGB);
      Graphics2D aGraphics2D = (Graphics2D) aBufferedImage.createGraphics();
      aGraphics2D.drawImage(image,0,0,null);
            
      if(transform == UgotSprite.TRANS_ROT90)
      {
         aGraphics2D.rotate(Math.PI / 2);
      }else if(transform == UgotSprite.TRANS_ROT180){
         aGraphics2D.rotate(Math.PI);
      }else if(transform == UgotSprite.TRANS_ROT270){
         aGraphics2D.rotate(Math.PI + Math.PI / 2);
      }
      return aBufferedImage;
   }
   
   public static UgotImage createUgotImage(String anImageName) throws IOException
   {
      return new UgotImage(createImage(anImageName));
   }
   */
   public static UgotImage createUgotImage(Image anImage) throws NullPointerException
   {
      return null;//return new UgotImage(createImage(anImage));
   }
   public static UgotImage createUgotImage(Image image, int x, int y, int width, int height, int transform)
   {
      return null;//return new UgotImage(createImage(image,x,y,width,height,transform));
   }
   /*
   public static UgotImage createUgotImage(String anImageName, int x, int y, int width, int height, int transform)
   {
      
      Image anImage1 = null;//Toolkit.getDefaultToolkit().createImage(imageSubNameStr);
      //Image anImage = null;

      int imageSubNameIndex = anImageName.indexOf("45");
      if(imageSubNameIndex == -1)
      {
         imageSubNameIndex = anImageName.indexOf(".");
      }
      String imageSubNameStr = anImageName.substring(0,imageSubNameIndex);
      System.out.println("UgotImage : anImageName = "+anImageName + " , "+"imageSubNameStr = "+imageSubNameStr);
      if(transform == UgotSprite.TRANS_ROT90 && (anImageName.indexOf("45") < 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"270.png");
      }else if(transform == UgotSprite.TRANS_ROT90 && (anImageName.indexOf("45") > 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"315.png");
      }else if(transform == UgotSprite.TRANS_ROT180 && (anImageName.indexOf("45") < 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"180.png");
      }else if(transform == UgotSprite.TRANS_ROT180 && (anImageName.indexOf("45") > 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"225.png");
      }else if(transform == UgotSprite.TRANS_ROT270 && (anImageName.indexOf("45") < 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"90.png");
      }else if(transform == UgotSprite.TRANS_ROT270 && (anImageName.indexOf("45") > 1) )
      {
         anImage1 = Toolkit.getDefaultToolkit().createImage(imageSubNameStr+"135.png");
      }
      
      return new UgotImage(anImage1);
   } 
   */  
   public int getWidth()
   {
      return theBufferedImage.getWidth(null);
   }
   public int getHeight()
   {
      return theBufferedImage.getHeight(null);
   }
   public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) 
   {

      try{
         Thread.sleep(8000);
      }catch(Exception ioe){

      }
      handlepixels(getTheImage(),x,y,width,height,rgbData);
      /*
      BufferedImage aBufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
            GraphicsDevice gs = ge.getDefaultScreenDevice(); 
            GraphicsConfiguration gc = gs.getDefaultConfiguration(); 
            Graphics2D myGraphics2D = aBufferedImage.createGraphics();
      myGraphics2D.drawImage(theBufferedImage,0,0,null);


      Canvas myCanvas = new Canvas(gc);
      myCanvas.prepareImage(theBufferedImage,null);

      myCanvas.prepareImage(aBufferedImage,null);
     

      Graphics myGraphics = myCanvas.getGraphics();
      myGraphics.drawImage(theBufferedImage,0,0,null);
      myGraphics.drawImage(aBufferedImage,0,0,null);

      //myCanvas.repaint();

      try{
         Thread.sleep(8000);
      }catch(Exception ioe){

      }
                rgbData = aBufferedImage.getRGB(0,0,width,height,null,0,width);
                for(int i = 0;i<rgbData.length;i++)
               {
         System.out.println("rgbData["+i+"]="+rgbData[i]);        
               }

     */
   }
   public static void handlepixels(Image img, int x, int y, int w, int h,int [] pixels) 
   { 
     // int[] pixels = new int[w * h]; 
      PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w); 
      try { 
         pg.grabPixels(); 
      } catch (InterruptedException e) { 
         System.err.println("interrupted waiting for pixels!"); 
         return; 
      } 
      if ((pg.getStatus() & ImageObserver.ABORT) != 0) 
      { 
         System.err.println("image fetch aborted or errored"); 
         return; 
      }
      /*
      for (int j = 0; j < h; j++) 
      { 
         for (int i = 0; i < w; i++) 
         { 
            //handlesinglepixel(x+i, y+j, pixels[j * w + i]); 
            //System.out.println("pixels["+j*w+i+"] = "+pixels[j*w+i]);
         } 
      }*/
   } 

   public void getRGB(int x,int y)
   {
   }
   public static Image createRGBImage(int[] rgb,int width,int height,boolean alphaProcess)
   {
      //System.out.println("UgotImage.createRGBImage() : width = "+width+", height = "+height);
      BufferedImage aBufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      aBufferedImage.setRGB(0,0,width,height,rgb,0,width); 
      return aBufferedImage;
   }
   public Image getTheImage()
   {
      return theBufferedImage;
   }
   
   public void setTheImage(BufferedImage anImage)
   {
      theBufferedImage = anImage;
   }
   public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
      return true;
   }
}