/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.cv.activemesh;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.graph.*;



public class ImageTrvsl implements NdTrvrslActn
{
   private int imageDataVal[];
   private int imageWidth;
   private int imageHeight;
   private GrdntEnrgyFltr grdntEngyFltr;
   public ImageTrvsl()
   {
      grdntEngyFltr =  new GrdntEnrgyFltr();
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
   public void setImageDataVal(int myImageDataVal[])
   {
      imageDataVal = myImageDataVal;
   }
   public int[] getImageDataVal()
   {
      return imageDataVal;
   }
   public void action1() {
      //System.out.println("ImageTrvsl.action1");
   }
   public void action2(Vertex myVertex, int myActnNmbr) {
      //System.out.println("ImageTrvsl.action2");
   }
   public void action3(Vertex myVertex1, Vertex myVertex2, int myActnNmbr) {
            //System.out.println("ImageTrvsl.action3");
   }
   public void action4(Vertex myVertex1, Vertex myVertex2, int myActnNmbr) {
      Pnt aPnt = (Pnt)myVertex2;
      Pnt aPnt1 = (Pnt)myVertex1;
      int x1 = (int)aPnt.getX1();
      int y1 = (int)aPnt.getY1();
      int anIndex1 = rtrvImgIndx(x1,y1);
      int anIndex2 = anIndex1;
      //////////grdntEngyFltr.initialize(getImageHeight(),getImageWidth());

      boolean inBound = false;////////////grdntEngyFltr.isInBounds3x3(anIndex1);
      if(inBound == true)
      {
         ///////grdntEngyFltr.grdntFilter3x3(imageDataVal,anIndex1);
         ////////grdntEngyFltr.filter(imageDataVal, anIndex1);
         anIndex2 = grdntEngyFltr.getGrdntIndex();
         //System.out.println("ImageTrvsl: anIndex2 = "+anIndex2);
         int x2 = getXPstn(anIndex2);
         int y2 = getYPstn(anIndex2);
         //System.out.println("action4: x1 = "+x1+",x2 = "+x2+",y1 = "+y1+", y2 = "+y2);
         aPnt.setX1(x2);
         aPnt.setY1(y2);
      }
      imageDataVal[anIndex2] = 0x00ff00;
      LineCrtr.drawLine(aPnt1, aPnt, imageDataVal, imageWidth, imageHeight);
      //System.out.println("ImageTrvsl.action4");
   }
   public void action5()
   {
      //System.out.println("ImageTrvsl.action5");
   }
   public int rtrvImgIndx(int myX,int myY)
   {
      int aWidth = getImageWidth();
      int anIndex = myY*aWidth+myX;
      return anIndex;
   }
   public int getXPstn(int myIndex)
   {
      int x = myIndex % getImageWidth();
      return x;
   }
   public int getYPstn(int myIndex)
   {
      int y = myIndex / getImageWidth();
      return y;
   }
}