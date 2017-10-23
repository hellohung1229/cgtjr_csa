package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;


public class CrtssnShp extends ShapePnt
{
   
   public CrtssnShp(float aWidth,float aHeight,float aLength)
   {
      setBoundaryShape(new ShpBndry(aWidth,aHeight,aLength));
      crtInitCoordinates();
      crtMeshByOrigin(null);
   }
   public CrtssnShp(DmnsnVar myDmnsnVar)
   {
      double aWidth = myDmnsnVar.getMaxDmnsn1Val();
      double aHeight = myDmnsnVar.getMaxDmnsn2Val();
      double aLength = myDmnsnVar.getMaxDmnsn3Val();
      float aDelta1 = (float)myDmnsnVar.getDelta1Val();
      float aDelta2 = (float)myDmnsnVar.getDelta2Val();
      float aDelta3 = (float)myDmnsnVar.getDelta3Val();
      setDeltaX(aDelta1);
      setDeltaY(aDelta2);
      setDeltaZ(aDelta3);
      float initX = (float)myDmnsnVar.getInitXVal();
      float initY = (float)myDmnsnVar.getInitYVal();
      float initZ = (float)myDmnsnVar.getInitZVal();
      System.out.println("CrtssnShp: width = "+aWidth+",height="+aHeight+",length="+aLength+",delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3);
      setBoundaryShape(new ShpBndry(aWidth,aHeight,aLength));
      crtInitCoordinates(initX,initY,initZ);
      crtMeshByOrigin(null);
   }

}