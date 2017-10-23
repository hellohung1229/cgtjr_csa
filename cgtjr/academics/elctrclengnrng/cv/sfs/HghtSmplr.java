package cgtjr.academics.elctrclengnrng.cv.sfs;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.Img2DYTrns;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;
import java.util.Vector;


public class HghtSmplr implements PntInsrtActn
{
   private Img2DYTrns image2DYTrns;
   private float zScale;
   private int xOffSet;
   private int yOffSet;
   
   public HghtSmplr(float[][] myHghtData,int myWidth,int myHeight)
   {
      image2DYTrns = new Img2DYTrns(myHghtData,myWidth,myHeight);
      zScale = .25f;
   }
   public void setXOffSet(int myX)
   {
       xOffSet = myX;
   }
   public void setYOffSet(int myY)
   {
       yOffSet = myY;
   }
   public int getXOffSet()
   {
      return xOffSet;
   }
   public int getYOffSet()
   {
       return yOffSet;
   }
   public void nodeCmpltAction(Pnt myPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {   
   }
   public void nodeOriginAction(Pnt aPnt,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(aPnt);
   }
   public void nodeInitAction(Pnt aPnt,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(myPnt7);
   }
   public void nodeCreateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      updtHght(myPnt7);
   }
   public void updtHght(Pnt aPnt)
   {
      int anIndex1 = (int)aPnt.getX1()-xOffSet;
      int anIndex2 = (int)aPnt.getY1()-yOffSet;

      float myHght = image2DYTrns.getData(anIndex1, anIndex2);

      aPnt.setZ1(myHght*zScale);
      System.out.println("SFS: x = "+anIndex1+", y = "+anIndex2+", z = "+ aPnt.getZ1());
   }
   public void nodeUpdateAction(Pnt aPnt,Pnt myPnt4,Pnt myPnt6,Pnt myPnt7,int myDepthCounter,int myDepth,int myTotalNumber)
   {
      //System.out.println("ImgSmplr.nodeUpdateAction() : ... ");
   }
   public void updtHght(Vector myVector)
   {
       int size = myVector.size();
       for(int i=0;i<size;i++)
       {
          Pnt aPnt = (Pnt)myVector.get(i);
          updtHght(aPnt);
       }
   }
}