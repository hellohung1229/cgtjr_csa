package cgtjr.academics.chmstry.general.atoms;



public class CesiumAtom extends PDBAtom
{
   public CesiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xce7711);
      setAtomicNumber(55);
      setAtomicSymbol("Cs");
      setAtomicName("Cesium");
   }  
}