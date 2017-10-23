package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.dmnsvar.CrdntTrnsfrm;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.chmstry.general.atoms.MlclDataLoader;
import cgtjr.academics.math.geometry.elmnt.HxhdrlPnts;
import cgtjr.academics.math.geometry.elmnt.QuadPnts;
import java.util.Vector;

public class HxhdrlRfndMsh extends CrdntShp
{
   private ShapePnt shapeBndry;
   private ShapePnt mshShape;
   private float radius;
   private Vector hxhdrlVector;

   private CrdntTrnsfrm crdntTrnsfrm;

   public HxhdrlRfndMsh(ShapePnt myShapeBndry,ShapePnt myMshShape)
   {
      radius = MlclDataLoader.getLargeRadius();
      hxhdrlVector = new Vector();
      shapeBndry = myShapeBndry;
      mshShape = myMshShape;
      setTransform(myMshShape.getTransform());
      crdntTrnsfrm = new CrdntTrnsfrm(myMshShape);
      setQuadElmnts(myMshShape.getQuadElmnts());
      setEdges(myMshShape.getEdges());      
      setNodes(myMshShape.getNodes());    
      setMtrlVar(myMshShape.getMtrlVar());            
      
      crtRfndMsh();
      
   }
   private void crtRfndMsh()
   {
      HxhdrlPnts aHxhdrlPnt = null;
      //TODO: change vector to Vector
      Vector aHxhdrlVctr = (Vector)mshShape.getHxhdrlElmnts();

      int aSize = aHxhdrlVctr.size();
      //System.out.println("HxhdrlRfndMsh size = "+aSize+", delta x = "+mshShape.getDeltaX()+", delta y = "+ mshShape.getDeltaY()+", delta z = "+mshShape.getDeltaZ());
      for(int i=0;i<aSize;i++)
      {
         aHxhdrlPnt = (HxhdrlPnts)aHxhdrlVctr.elementAt(i);

         if(isInRadius(aHxhdrlPnt))
         {             
             hxhdrlVector.add(aHxhdrlPnt);   
             //System.out.println("HxhdrlRfndMsh i = "+i);
         }
      }      
   }
   public Vector getHxhdrlElmnts()
   {       
       return hxhdrlVector;
   }
   private boolean isInRadius(HxhdrlPnts myHxhdrlPnts)
   {
      Pnt smPnts[] = myHxhdrlPnts.getPoints();
      if(isInRadius(smPnts[0]))
      {
         return true;
      }else if(isInRadius(smPnts[1]))
      {
         return true;
      }else if(isInRadius(smPnts[2]))
      {
         return true;
      }else if(isInRadius(smPnts[3]))
      {
         return true;
      }else if(isInRadius(smPnts[4]))
      {
         return true;
      }else if(isInRadius(smPnts[5]))
      {
         return true;
      }else if(isInRadius(smPnts[6]))
      {
         return true;
      }else if(isInRadius(smPnts[7]))
      {
         return true;
      }
      return false;
   }
   private boolean isInRadius(Pnt myPoint)
   {
      Pnt aPoint = null;
      boolean isInBndry = false;

      float x1 = 0;
      float y1 = 0;
      float z1 = 0;
      float x2 = 0;
      float y2 = 0;
      float z2 = 0;
      

      int aSize = shapeBndry.nmbrOfVertices();      
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)shapeBndry.rtrvVertex(i);

         x1 = aPoint.getX1();
         y1 = aPoint.getY1();
         z1 = aPoint.getZ1();

         x2 = crdntTrnsfrm.rtrvC1(myPoint);
         y2 = crdntTrnsfrm.rtrvC2(myPoint);
         z2 = crdntTrnsfrm.rtrvC3(myPoint);

         //System.out.println("SphrlcBndryMsh:"+myPoint+" , "+aPoint+",distance = "+PntTool.getDistance(x1,y1,z1,x2,y2,z2));

         if(PntTool.getDistance(x1,y1,z1,x2,y2,z2) <= radius)
         {
            isInBndry = true;
            return isInBndry;
         }
      }             
      return isInBndry;
   }
}