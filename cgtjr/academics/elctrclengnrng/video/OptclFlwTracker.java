/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video;


import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.general.gui.UgotImage;
import java.awt.Image;

/**
 *
 * @author clayton g thomas jr
 */
public class OptclFlwTracker implements ParseAction, Runnable
{
   private static OpticalFlowFltr aOpticalFlowFltr;
   private static Image otptImage;
   private static int width;
   private static int height;
   private static Image img;
   
   public OptclFlwTracker()
   {
      aOpticalFlowFltr = new OpticalFlowFltr();
   }
   public Image actn(Image myImage, int myWidth, int myHeight)
   {
      width = myWidth;
      height = myHeight;
      img = myImage;
      //Thread aThread = new Thread(this);
      //aThread.start();
      run();
      return otptImage;
   }
   public void run() {
      int inputPixels[] = ImageTool.rtrv1DPxls(img);
      int gryOtptPxlData[] = ImageTool.rtrv1DPxls(img);
      int otptPixels[] = ImageTool.rtrv1DPxls(img);

      aOpticalFlowFltr.initialize(width, height);
      aOpticalFlowFltr.setGrdntMgntd(otptPixels);
      
      for(int i=0;i<otptPixels.length;i++)
      aOpticalFlowFltr.filter(otptPixels, i);

      //aOpticalFlowFltr.setCornerData(otptPixels);
      //aOpticalFlowFltr.optclFlwFltr9x9(inputPixels);
      
      otptImage = UgotImage.createRGBImage(otptPixels,width,height,true);
   }
   public Image getOtPtImaage()
   {
      return otptImage;
   }
}