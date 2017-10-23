/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PntCrnrFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.general.gui.UgotImage;
import java.awt.Image;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class CrnrShpTracker implements ParseAction, Runnable
{
   private GridDrawActn pixelInsrtActn;
   private ShpBndry shapeBndry;
   private GridDrawCrdnteShpe crdntShp;
   private boolean isSttnryWndw;
   private PntCrnrFltr aPntCrnrFltr;
   private Image otptImage;
   private int width;
   private int height;
   private Image img;
   
   public CrnrShpTracker(ShpBndry myImageBndry)
   {
      shapeBndry = myImageBndry;
      pixelInsrtActn = new GridDrawActn();
      isSttnryWndw = false;
      aPntCrnrFltr = new PntCrnrFltr();
   }
   public CrnrShpTracker(ShpBndry myImageBndry,GridDrawActn myPntPxlInsrtActn)
   {
      shapeBndry = myImageBndry;
      pixelInsrtActn = myPntPxlInsrtActn;
      isSttnryWndw = false;
   }
   public void setIsSttnryWndw(boolean myIsSttnryWndw)
   {
      isSttnryWndw = myIsSttnryWndw;
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
      //System.out.println("ShpTracker: test 100");
      pixelInsrtActn.setImgPixels(otptPixels);
      pixelInsrtActn.setImgDtWdth(width);

      shapeBndry.setImgPxlData(inputPixels,width,height);
      //System.out.println("ShpTracker: test 101");
      crdntShp = new GridDrawCrdnteShpe(shapeBndry,pixelInsrtActn);
      Vector aVector = crdntShp.getNodes();
      aPntCrnrFltr.initialize(width, height);
      //System.out.println("ShpTracker: test 104 myPixelData2[1]"+myPixelData2[1]);
      
      aPntCrnrFltr.setGryVls(null);//aPntCrnrFltr.setGryVls(gryOtptPxlData);
      
      
      //aPntCrnrFltr.setGrdntMgntd(otptPixels);
      //System.out.println("ShpTracker: test 106");
      aPntCrnrFltr.setCornerData(otptPixels);
      aPntCrnrFltr.filter9x9(inputPixels, aVector);
      
      //TODO: consider a parseAction interface for shpbndry ...
      double center[] = crdntShp.cmptCenter();
      //System.out.println("PointParser: count = "+crdntShp.getCounter()+", x max = "+crdntShp.getXMax()+",x min="+crdntShp.getXMin()+",y max = "+crdntShp.getYMax()+", y min = "+crdntShp.getYMin());
      //TODO: need to create method for this functionality ... after ensuring shape and point are properly instantiated
      //if(crdntShp.getCounter() > 0)
      //{
      //System.out.println("ShpTracker: x = "+center[0]+", y = "+center[1]);
      if(isSttnryWndw == false)
      {
        // shapeBndry.setInitX(center[0]);
        // shapeBndry.setInitY(center[1]);
      }
      //System.out.println("ClrCnvrtFlter:grayValue = myPixelData["+i+"]"+myPixelData[i]);
      //}
      otptImage = UgotImage.createRGBImage(otptPixels,width,height,true);

   }
   public Image getOtPtImaage()
   {
      return otptImage;
   }
   public void setShapeBndry(ImageBndry myShpBndry)
   {
      shapeBndry = myShpBndry;
   }
}