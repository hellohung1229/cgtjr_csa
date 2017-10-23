/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.math.geometry.shapebndry.CrvtrXY;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.*;
import cgtjr.academics.general.gui.UgotImage;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.elmnt.SurfaceArea;
import cgtjr.academics.math.geometry.elmnt.NodePnts;
import java.awt.Image;
import java.util.Vector;

/**
 *
 * @author clayton g thomas jr
 */
public class EdgeShpTracker implements ParseAction
{
   private GridDrawActn pixelInsrtActn;
   private SSDDrwActn crdntInsrt;    
   private ShpBndry shapeBndry;
   private GridDrawCrdnteShpe crdntShp;
   private boolean isSttnryWndw;
   private PntGrdntFltr aPntGrdntFltr;
   private Image otptImage;
   private int width;
   private int height;
   private Image img;
   
   public EdgeShpTracker(ShpBndry myImageBndry)
   {
      shapeBndry = myImageBndry;
      pixelInsrtActn = new GridDrawActn();
      crdntInsrt = new SSDDrwActn();      
      isSttnryWndw = false;
      aPntGrdntFltr = new PntGrdntFltr();
   }
   /*
   public EdgeShpTracker(ShpBndry myImageBndry,PntPxlInsrtActn myPntPxlInsrtActn)
   {
      shapeBndry = myImageBndry;
      pixelInsrtActn = myPntPxlInsrtActn;
      isSttnryWndw = false;
   }*/
   public void setIsSttnryWndw(boolean myIsSttnryWndw)
   {
      isSttnryWndw = myIsSttnryWndw;
   }
   public Image actn(Image myImage, int myWidth, int myHeight)
   {
      width = myWidth;
      height = myHeight;
      img = myImage;
      System.out.println("EdgeShpTracker width = "+width);
      //Thread aThread = new Thread(this);
      //aThread.start();
      int inputPixels[] = ImageTool.rtrv1DPxls(img);
      int gryOtptPxlData[] = ImageTool.rtrv1DPxls(img);
      int otptPixels[] = ImageTool.rtrv1DPxls(img);
      //System.out.println("ShpTracker: test 100");
      pixelInsrtActn.setImgPixels(otptPixels);
      pixelInsrtActn.setImgDtWdth(width);
      pixelInsrtActn.setImgDtHght(height);      
      
      crdntInsrt.setImgPixels(otptPixels);
      crdntInsrt.setImgDtWdth(width);
      crdntInsrt.setImgDtHght(height);  
      
      shapeBndry.setImgPxlData(inputPixels,width,height);
      //System.out.println("ShpTracker: test 101");
      crdntShp = new GridDrawCrdnteShpe(shapeBndry,pixelInsrtActn);
      Vector aVector = crdntShp.getQuadElmnts();
      Vector nodes = crdntShp.getNodes();

      //NodePnts.prntGlblNds(nodes,null);
      
      
      crdntInsrt.drawDigit(nodes);
      double area = SurfaceArea.cmptAreaByCrssPrdct(aVector);
      System.out.println("EdgeShpTracker: area = "+area);
      
      //NodePnts.prntGlblNds(nodes,null);

      //ZSinXY aZSinXY = new ZSinXY();
      //aZSinXY.updtZVls(nodes);
      Vector bndryNds = NodePnts.rtrvBndry2DPnts(nodes);
      //NodePnts.prntGlblNds(bndryNds, null);
      //System.out.println("EdgeShpTracker: bndryNds = "+bndryNds.size());
      CrvtrXY aCrvtrXY = new CrvtrXY();
      aCrvtrXY.insrt(bndryNds);
      aCrvtrXY.cmptCrvtr();     
            
      NodePnts.cmptWidth(bndryNds, (int)(shapeBndry.getYMax()));
      
      //String quadoutput = QuadPnts.rtrvQuadElmntsStrng(aVector);
      //System.out.println(quadoutput);
      
      
      //ImageTool.wrtDataFile("quadelmntlst.txt",quadoutput);
      //Vector aVector = crdntShp.getNodes();
      //aPntGrdntFltr.initialize(width, height);
      //System.out.println("ShpTracker: test 104 myPixelData2[1]"+myPixelData2[1]);
      //aPntGrdntFltr.setGryVls(gryOtptPxlData);
      //aPntGrdntFltr.setGrdntMgntd(otptPixels);
      //System.out.println("ShpTracker: test 106");
      //aPntGrdntFltr.filter9x9(inputPixels, aVector);
      
      //TODO: consider a parseAction interface for shpbndry ...
      //double center[] = crdntShp.cmptCenter();
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
     return otptImage;
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