/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;

/**
 *
 * @author finitesystem
 */
public class GridDrawCrdnteShpe extends CrdntShp{
    
   public GridDrawCrdnteShpe(){
       
   }
    
   public GridDrawCrdnteShpe(ShpBndry myShpBndry,GridDrawActn aPntPxlInsrtActn)
   {
      //System.out.println("CrdntShp: aCrdntTp delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3);
      ///////////////TODO: Check .... setName("crdntshp");
      cnnctBndry(myShpBndry);
      setDeltaX((float)myShpBndry.getDeltaX());
      setDeltaY((float)myShpBndry.getDeltaY());
      float initX = (float)myShpBndry.getInitX();
      float initY = (float)myShpBndry.getInitY();
      float initZ = (float)myShpBndry.getInitZ();
      //System.out.println("CrdntShp: init x="+initX+", init y = "+initY+", init z = "+initZ);
      crtInitCoordinates(initX,initY,initZ,aPntPxlInsrtActn);
      crtMeshByBndry(aPntPxlInsrtActn);
   }    
}
