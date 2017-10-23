package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.general.MtrlVar;

public class CrdntShp extends ShapePnt
{
   //private MtrlVar mtrlVar;
      
   public CrdntShp()
   {       
   }
   public CrdntShp(float aWidth,float aHeight,float aLength)
   {
      setBoundaryShape(new ShpBndry(aWidth,aHeight,aLength));
      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      crtInitCoordinates();
      crtMeshByOrigin(null);
   }
   public CrdntShp(double aWidth,double aHeight,double aLength)
   {
      setBoundaryShape(new ShpBndry(aWidth,aHeight,aLength));
      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      crtInitCoordinates();
      crtMeshByOrigin(null);
   }
   public CrdntShp(DmnsnVar myDmnsnVar)
   {
      float aWidth = (float)myDmnsnVar.getMaxDmnsn1Val();
      float aHeight =(float)myDmnsnVar.getMaxDmnsn2Val();
      float aLength =(float)myDmnsnVar.getMaxDmnsn3Val();
      float aDelta1 = (float)myDmnsnVar.getDelta1Val();
      float aDelta2 = (float)myDmnsnVar.getDelta2Val();
      float aDelta3 = (float)myDmnsnVar.getDelta3Val();
      setDeltaX(aDelta1);
      setDeltaY(aDelta2);
      setDeltaZ(aDelta3);
      float anInitX = (float)myDmnsnVar.getInitXVal();
      float anInitY = (float)myDmnsnVar.getInitYVal();
      float anInitZ = (float)myDmnsnVar.getInitZVal();
      String aCrdntTp = myDmnsnVar.getCrdntTpVal();
      setCrdntTp(aCrdntTp);

      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      //System.out.println("CrdntShp: aCrdntTp ="+aCrdntTp+",width = "+aWidth+",height="+aHeight+",length="+aLength+",delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3+",initx="+anInitX+",initY="+anInitY+",initZ="+anInitZ);
      //setBoundaryShape(new ShpBndry(0,0,0,aWidth,aHeight,aLength));
      setBoundaryShape(new ShpBndry(myDmnsnVar));
      EmptyPntInsert aTestClass = new EmptyPntInsert();
      crtInitCoordinates(anInitX,anInitY,anInitZ,aTestClass);
      crtMeshByBndry(aTestClass);
   }
   public CrdntShp(DmnsnVar myDmnsnVar[])
   {
      float aDelta1 = (float)myDmnsnVar[0].getDelta1Val();
      float aDelta2 = (float)myDmnsnVar[0].getDelta2Val();
      float aDelta3 = (float)myDmnsnVar[0].getDelta3Val();
      setDeltaX(aDelta1);
      setDeltaY(aDelta2);
      setDeltaZ(aDelta3);
      setXMax((float)myDmnsnVar[0].getMaxDmnsn1Val());
      setYMax((float)myDmnsnVar[0].getMaxDmnsn2Val());
      setZMax((float)myDmnsnVar[0].getMaxDmnsn3Val());
      setXMin((float)myDmnsnVar[0].getMinDmnsn1Val());
      setYMin((float)myDmnsnVar[0].getMinDmnsn2Val());
      setZMin((float)myDmnsnVar[0].getMinDmnsn3Val());
      float anInitX = (float)myDmnsnVar[0].getInitXVal();
      float anInitY = (float)myDmnsnVar[0].getInitYVal();
      float anInitZ = (float)myDmnsnVar[0].getInitZVal();

      String aCrdntTp = myDmnsnVar[0].getCrdntTpVal();
      setCrdntTp(aCrdntTp);

      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      //System.out.println("CrdntShp: aCrdntTp delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3);
      //ShpBndry aShpBndry = new ShpBndry();
      cnnctBndry(myDmnsnVar);
      //setBoundaryShape(aShpBndry);
      EmptyPntInsert aTestClass = new EmptyPntInsert();
      crtInitCoordinates(anInitX,anInitY,anInitZ,aTestClass);
      crtMeshByBndry(aTestClass);

   }
   /*
   public CrdntShp(ShpBndry myShpBndry,GridDrawActn aPntPxlInsrtActn)
   {
      //System.out.println("CrdntShp: aCrdntTp delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3);
      setName("crdntshp");
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
   */
   public CrdntShp(ShpBndry myShpBndry)
   {
      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      //System.out.println("CrdntShp: aCrdntTp delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3);
      setName("crdntshp");
      setBoundaryShape(myShpBndry);
      setDeltaX((float)myShpBndry.getDeltaX());
      setDeltaY((float)myShpBndry.getDeltaY());
      setDeltaZ((float)myShpBndry.getDeltaZ());      
      float initX = (float)myShpBndry.getInitX();
      float initY = (float)myShpBndry.getInitY();
      float initZ = (float)myShpBndry.getInitZ();
      //System.out.println("CrdntShp: init x="+initX+", init y = "+initY+", init z = "+initZ);
      crtInitCoordinates(initX,initY,initZ,null);
      crtMeshByBndry(null);

   }   
 
   /*
   public CrdntShp(ShpBndry myShpBndry)
   {

      cnnctBndry(myShpBndry);

      TestClass aTestClass = new TestClass();
      createInitCoordinates(35,50,0,aTestClass);
      createCoordinateMesh4(aTestClass);
   }*/   
   public CrdntShp(DmnsnVar myDmnsnVar,MtrlVar myMtrlVar)
   {
      float aWidth = (float)myDmnsnVar.getMaxDmnsn1Val();
      float aHeight =(float)myDmnsnVar.getMaxDmnsn2Val();
      float aLength =(float)myDmnsnVar.getMaxDmnsn3Val();
      float aDelta1 = (float)myDmnsnVar.getDelta1Val();
      float aDelta2 = (float)myDmnsnVar.getDelta2Val();
      float aDelta3 = (float)myDmnsnVar.getDelta3Val();
      setDeltaX(aDelta1);
      setDeltaY(aDelta2);
      setDeltaZ(aDelta3);
      float anInitX = (float)myDmnsnVar.getInitXVal();
      float anInitY = (float)myDmnsnVar.getInitYVal();
      float anInitZ = (float)myDmnsnVar.getInitZVal();
      String aCrdntTp = myDmnsnVar.getCrdntTpVal();
      setCrdntTp(aCrdntTp);
      setMtrlVar(myMtrlVar);

      /*
      myMtrlVar = getMtrlVar();
      if(myMtrlVar == null)
      {
          myMtrlVar = new MtrlVar();
      }*/
      //mtrlVar = myMtrlVar;
      /*
      Trnsfrm aTrnsfrm = new Trnsfrm();
      aTrnsfrm.translate(-aWidth/2,-aHeight/2,-aLength/2);
      setTransform(aTrnsfrm);
      */
      //System.out.println("CrdntShp: aCrdntTp ="+aCrdntTp+",width = "+aWidth+",height="+aHeight+",length="+aLength+",delta1="+aDelta1+",delta2="+aDelta2+",delta3="+aDelta3+",initx="+anInitX+",initY="+anInitY+",initZ="+anInitZ);
      //setBoundaryShape(new ShpBndry(0,0,0,aWidth,aHeight,aLength));
      setBoundaryShape(new ShpBndry(myDmnsnVar));
      EmptyPntInsert aTestClass = new EmptyPntInsert();
      crtInitCoordinates(anInitX,anInitY,anInitZ,aTestClass);
      crtMeshByBndry(aTestClass);

   }  
   public void crtCrdntMsh(ShpBndry myShpBndry)
   {
      setBoundaryShape(myShpBndry);
      crtInitCoordinates(35,50,0,null);
      crtMeshByBndry(null);
   }
 
}