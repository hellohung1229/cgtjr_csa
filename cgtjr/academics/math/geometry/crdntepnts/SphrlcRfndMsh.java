package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.shapebndry.RadiusBndry;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.dmnsvar.CrdntTrnsfrm;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.elmnt.HxhdrlPnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;

public class SphrlcRfndMsh extends CrdntShp
{
   private double rBuffer[][];
   private ShapePnt shapeBndry;
   private ShapePnt mshShape;
   private RadiusBndry sphrclSphrBndry1;
   private RadiusBndry sphrclSphrBndry2;
   private Vector hxhdrlVctr;
   private Vector quadVctr;
   private Vector nodeVctr;
   private Vector lineVctr;
   private CrdntTrnsfrm crdntTrnsfrm;

   public SphrlcRfndMsh(ShapePnt myShapeBndry,ShapePnt myMshShape)
   {
      shapeBndry = myShapeBndry;
      mshShape = myMshShape;
      setTransform(myMshShape.getTransform());
      crdntTrnsfrm = new CrdntTrnsfrm(myMshShape);
      sphrclSphrBndry1 = new RadiusBndry(4);
      sphrclSphrBndry2 = new RadiusBndry(.2);
      crtRfndMsh();
   }
   /*
   private double[][] crtZBuffer()
   {
      double delta1 = mshShape.getDeltaX();
      double delta2 = mshShape.getDeltaY();
      double delta3 = mshShape.getDeltaX();
      double maxDmnsn1 = mshShape.getXMax();
      double maxDmnsn2 = mshShape.getYMax();
      double maxDmnsn3 = mshShape.getZMax();
      
      boolean isInBndry = false;

      int aSize = shapeBndry.numberOfVertices();
      int m = 0;
      int n = 0;
      for(double i=0;i<=maxDmnsn2;i=i+delta2)
      {
         for(double j=0;j<=maxDmnsn3;j=j+delta3)
         {
            for(double k=maxDmnsn1;k>=0;k=k-delta1)
            {
               isInBndry = isInAllSphrBndry(k,i,j);
               if(isInBndry == true)
               {
                  rBuffer[m][n] = k;
                  break;
               }
            }
            n=n+1;
         }
         m=m+1;
      }
      return rBuffer;
   }*/
   private void crtRfndMsh()
   {
      lineVctr = new Vector();
      nodeVctr = new Vector();
      quadVctr = new Vector();
      QuadPnts aQuadPnt = null;
      HxhdrlPnts aHxhdrlPnt = null;

      Vector aVector = (Vector)mshShape.getQuadElmnts();
      Vector aHxhdrlVctr = (Vector)mshShape.getHxhdrlElmnts();

      int aSize = aVector.size();
      //System.out.println("SphrlcBndryMsh: size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         aQuadPnt = (QuadPnts)aVector.elementAt(i);
         //System.out.println("SphrlcBndryMsh: test 3...");
         if(isInAllSphrBndry(aQuadPnt))
         {
            //System.out.println("SphrlcBndryMsh: test 4...");
            nodeCrtActn(null, aQuadPnt.getPoint0(),aQuadPnt.getPoint1(),aQuadPnt.getPoint2(),aQuadPnt.getPoint3(), 0, 0, 0);
             /*
            quadVctr.add(aQuadPnt);

            //TODO ... remove duplicate nodes
            nodeVctr.add(aQuadPnt.getPoint0());
            nodeVctr.add(aQuadPnt.getPoint1());
            nodeVctr.add(aQuadPnt.getPoint2());
            nodeVctr.add(aQuadPnt.getPoint3());
            LinePnts aLinePnts1 = new LinePnts(aQuadPnt.getPoint0(),aQuadPnt.getPoint1());
            LinePnts aLinePnts2 = new LinePnts(aQuadPnt.getPoint1(),aQuadPnt.getPoint2());
            LinePnts aLinePnts3 = new LinePnts(aQuadPnt.getPoint2(),aQuadPnt.getPoint3());
            LinePnts aLinePnts4 = new LinePnts(aQuadPnt.getPoint3(),aQuadPnt.getPoint0());
            lineVctr.addElement(aLinePnts1);
            lineVctr.addElement(aLinePnts2);
            lineVctr.addElement(aLinePnts3);
            lineVctr.addElement(aLinePnts4);

            setLineElmnts(lineVctr);
            setQuadElmnts(quadVctr);
            setNodes(nodeVctr);
             */
         }
      }
      System.out.println("SphrlcBndryMsh, size = "+getEdges().size());
   }
   private boolean isInAllSphrBndry(QuadPnts myQuadPnts)
   {
      Pnt smPnts[] = myQuadPnts.getPoints();
      if(isInAllSphrBndry(smPnts[0]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[1]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[2]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[3]))
      {
         return true;
      }
      return false;
   }
   private boolean isInAllSphrBndry(HxhdrlPnts myHxhdrlPnts)
   {
      Pnt smPnts[] = myHxhdrlPnts.getPoints();
      if(isInAllSphrBndry(smPnts[0]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[1]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[2]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[3]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[4]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[5]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[6]))
      {
         return true;
      }else if(isInAllSphrBndry(smPnts[7]))
      {
         return true;
      }
      return false;
   }
   private boolean isInAllSphrBndry(Pnt myPoint)
   {
      Pnt aPoint = null;
      boolean isInBndry1 = false;
      boolean isInBndry2 = false;
      boolean isInBndry = false;

      int aSize = shapeBndry.nmbrOfVertices();
      float x1 = 0;
      float y1 = 0;
      float z1 = 0;
      float x2 = 0;
      float y2 = 0;
      float z2 = 0;

      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)shapeBndry.rtrvVertex(i);
         //double center[] = shapeBndry.cmptCenter();
         x1 = aPoint.getX1();
         y1 = aPoint.getY1();
         z1 = aPoint.getZ1();

         x2 = crdntTrnsfrm.rtrvC1(myPoint);
         y2 = crdntTrnsfrm.rtrvC2(myPoint);
         z2 = crdntTrnsfrm.rtrvC3(myPoint);

         System.out.println("SphrlcBndryMsh:"+myPoint+" , "+aPoint+",distance = "+PntTool.getDistance(x1,y1,z1,x2,y2,z2));
         sphrclSphrBndry1.setEnvrnmntVar(x1,y1,z1);
         isInBndry1 = sphrclSphrBndry1.isInBndry(x2,y2,z2);
         //sphrclSphrBndry2.setEnvrnmntVar((float)center[0],(float)center[1],(float)center[2]);
         //isInBndry2 = sphrclSphrBndry2.isInBndry(x2,y2,z2);

         if(PntTool.getDistance(x1,y1,z1,x2,y2,z2) <= 1.5)
         {
            isInBndry = true;
            return isInBndry;
         }
      }
      return isInBndry;
   }
}