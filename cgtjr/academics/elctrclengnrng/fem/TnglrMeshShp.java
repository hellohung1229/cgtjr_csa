package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.elctrclengnrng.fem.BssFnctn;
import cgtjr.academics.elctrclengnrng.fem.TrnglrBss;
import cgtjr.academics.general.MtrlVar;
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.elmnt.TrnglPnts;
import java.util.Vector;


public class TnglrMeshShp extends CrdntShp
{
   private MtrlVar mtrlVar;

   public TnglrMeshShp(float aWidth,float aHeight,float aLength,MtrlVar myMtrlVar)
   {
      super(aWidth,aHeight,aLength);
      mtrlVar = myMtrlVar;
   }
   public TnglrMeshShp(DmnsnVar myDmnsnVar)
   {
      super(myDmnsnVar);
      mtrlVar = new MtrlVar();
   }
   public TnglrMeshShp(DmnsnVar myDmnsnVar,MtrlVar myMtrlVar)
   {
      super(myDmnsnVar);
      mtrlVar = myMtrlVar;
   }
   public TnglrMeshShp(DmnsnVar myDmnsnVar[])
   {
      super(myDmnsnVar);
      mtrlVar = new MtrlVar();
   }
   public MtrlVar getMtrlVar()
   {
      return mtrlVar;
   }

   public Vector rtrvElmnts() {
      Vector aVector = super.rtrvElmnts();
      TrnglPnts aTrnglPnts = new TrnglPnts();
      Vector aTrnglrVctr = aTrnglPnts.cnvrtRctnglToTrngl(aVector);
      return aTrnglrVctr;
   }
   public BssFnctn rtrvBssFnctn()
   {
      BssFnctn aBssFnctn = new TrnglrBss();
      return aBssFnctn;
   }

}