package cgtjr.academics.math.geometry.crdntepnts;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.MtrlVar;


/**
 * 
 * @author clayton g thomas jr
 */
public class MeshShp extends CrdntShp
{
    /**
     * 
     * @param aWidth
     * @param aHeight
     * @param aLength
     * @param myMtrlVar
     */
   public MeshShp(float aWidth,float aHeight,float aLength,MtrlVar myMtrlVar)
   {
      super(aWidth,aHeight,aLength);
      setMtrlVar(myMtrlVar);
   }
   /**
    * 
    * @param myDmnsnVar
    */
   public MeshShp(DmnsnVar myDmnsnVar)
   {
      super(myDmnsnVar);
      setMtrlVar(new MtrlVar());
   }
   /**
    * 
    * @param myDmnsnVar
    * @param myMtrlVar
    */
   public MeshShp(DmnsnVar myDmnsnVar,MtrlVar myMtrlVar)
   {
      super(myDmnsnVar,myMtrlVar);
   }
   /**
    * 
    * @param myDmnsnVar
    */
   public MeshShp(DmnsnVar myDmnsnVar[])
   {
      super(myDmnsnVar);
      setMtrlVar(new MtrlVar());
   }
}