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
import cgtjr.academics.math.geometry.elmnt.LinePnts;


public class ActiveMesh extends BoxVol{

   ImageSmplr mshImageSmplr;
   ImageTrvsl mshImageTrvsl;
   Rndrr2D renderer;
   //private GrdntEngyFltr grdntEngyFltr;
   //private CrnrEngyFltr grdntEngyFltr;
   private ClstrMshFltr grdntEngyFltr;
   private int imageData[];
   private int prcssNmbr;
   private int cntrlNmbr;
   private int imgDtCpy[];
   
   public ActiveMesh()
   {      
   }
   public ActiveMesh(double myWidth,double myHeight,double myLength)
   {
      super(myWidth,myHeight,myLength);
      mshImageTrvsl = new ImageTrvsl();
      setNodeTrvrslActn(mshImageTrvsl);
      renderer = new Rndrr2D();
      renderer.setWidth((int)myWidth);
      renderer.setHeight((int)myHeight);
      //grdntEngyFltr =  new GrdntEngyFltr();
      //grdntEngyFltr =  new CrnrEngyFltr();
      grdntEngyFltr =  new ClstrMshFltr();
      ////////grdntEngyFltr.i((int)myHeight,(int)myWidth);//check
   }
   public ActiveMesh(double myWidth,double myHeight,double myLength,BndryNodeAction_I myBndryNodeAction_I)
   {
      super(myWidth,myHeight,myLength,myBndryNodeAction_I);
      mshImageTrvsl = new ImageTrvsl();
      setNodeTrvrslActn(mshImageTrvsl);
      renderer = new Rndrr2D();
      renderer.setWidth((int)myWidth);
      renderer.setHeight((int)myHeight);
      //grdntEngyFltr =  new GrdntEngyFltr();
      //grdntEngyFltr =  new CrnrEngyFltr();
      grdntEngyFltr =  new ClstrMshFltr();
      //grdntEngyFltr.initialize((int)myHeight,(int)myWidth);//check 
   }
   public void setImgDtCpy(int myImgDtCpy[],int myWidth,int myHeight)
   {
      imgDtCpy = myImgDtCpy;
      renderer.setWidth(myWidth);
      renderer.setHeight(myHeight);
      renderer.setImageData(myImgDtCpy);
   }
   public int[] getImgDtCpy()
   {
      return imgDtCpy;
   }
   public void setImageData(int rgbColors[],int myWidth,int myHeight)
   {
      //mshImageTrvsl.setImageDataVal(rgbColors);
      //mshImageTrvsl.setImageWidth(myWidth);
      //mshImageTrvsl.setImageHeight(myHeight);
      //renderer.setWidth(myWidth);
      //renderer.setHeight(myHeight);
      imageData = rgbColors;
      //renderer.setImageData(imageData);
   }
   public void cmptEnrgy(Pnt myPnt)
   {
      int aX = (int)myPnt.getX1();
      int aY = (int)myPnt.getY1();
      int anIndex1 = renderer.rtrvImgIndx(aX, aY);
      int anIndex2 = anIndex1;
      boolean inBound = false;//grdntEngyFltr.isInBounds3x3(anIndex1);check
      if(inBound == true)
      {         
         grdntEngyFltr.filter3x3(imageData, anIndex1);
         anIndex2 = grdntEngyFltr.getCrnrIndex();
         int x2 = renderer.getXPstn(anIndex2);
         int y2 = renderer.getYPstn(anIndex2);
         //System.out.println("ActiveMesh : action4: index1 = "+anIndex1+", index2 = "+anIndex2+" ,x1 = "+aX+",x2 = "+x2+",y1 = "+aY+", y2 = "+y2);
         myPnt.setX1(x2);
         myPnt.setY1(y2);         
      }
   }
   public void cmptEnrgy(LinePnts myLinePnts)
   {
      Pnt aPnt1 = myLinePnts.getPoint0();
      Pnt aPnt2 = myLinePnts.getPoint1();
      cmptEnrgy(aPnt1);
      cmptEnrgy(aPnt2);
   }
   public void trvrsNodeActn(Pnt myPnt)
   {
      ActiveMesh anActiveNode1 = (ActiveMesh)myPnt;
      //System.out.println("ActiveMesh: this.cntrlNmbr = " +getCntrlNmbr()+", node1 nmbr = "+anActiveNode1.getPrcssNmbr());
      if(anActiveNode1.getPrcssNmbr() == this.getCntrlNmbr())
      {
         cmptEnrgy(anActiveNode1);
         anActiveNode1.setColor(0x00ff00);
         draw(anActiveNode1);
         anActiveNode1.incPrcssNmbr();
         //renderer.draw(anActiveNode1);
         //renderer.drawTest();
      }
   }
   public void draw(Pnt myPnt)
   {
      ActiveMesh aPnt = null;
      int aSize = myPnt.nmbrOfVertices();
      for(int anIndex = 0 ;anIndex < aSize;anIndex++)
      {
         aPnt = (ActiveMesh)myPnt.getVertexByIndex(anIndex);
         if(aPnt.getPrcssNmbr() == this.getCntrlNmbr())
         {
            aPnt.setColor(0x00ff00);
            LinePnts aLinePnts = new LinePnts(myPnt,aPnt);
            renderer.draw(aLinePnts);
         }
      }
   }
   public void trvrsEdgActn(LinePnts myLinePnts)
   {
      ActiveMesh anActiveNode1 = (ActiveMesh)myLinePnts.getPoint0();
      ActiveMesh anActiveNode2 = (ActiveMesh)myLinePnts.getPoint1();
      //System.out.println("ActiveMesh: this.cntrlNmbr = " +getCntrlNmbr()+", node1 nmbr = "+anActiveNode1.getPrcssNmbr());
      //System.out.println("ActiveMesh: this.cntrlNmbr = " +getCntrlNmbr()+", node2 nmbr = "+anActiveNode2.getPrcssNmbr());
      if(anActiveNode1.getPrcssNmbr() == this.getCntrlNmbr())
      {
         cmptEnrgy(anActiveNode1);
         anActiveNode1.incPrcssNmbr();
      }
      if(anActiveNode2.getPrcssNmbr() == this.getCntrlNmbr())
      {
         cmptEnrgy(anActiveNode2);
         anActiveNode2.incPrcssNmbr();
      }
      renderer.draw(myLinePnts);
   }
   public int[] getFltrdData()
   {
      //return mshImageTrvsl.getImageDataVal();
      return renderer.getImageData();
   }
   public void setCntrlNmbr(int myCntrlNmbr)
   {
      cntrlNmbr = myCntrlNmbr;
   }
   public int getCntrlNmbr()
   {
      return cntrlNmbr;
   }
   public void setPrcssNmbr(int myPrcssNmbr)
   {
      prcssNmbr = myPrcssNmbr;
   }
   public int getPrcssNmbr()
   {
      return prcssNmbr;
   }
   public void incPrcssNmbr()
   {
      setPrcssNmbr(getPrcssNmbr()+1);
   }
   public Pnt createPoint()
   {
      return new ActiveMesh();
   }
   public Pnt createDataPoint()
   {
      return new ActiveMesh();
   }
}