package cgtjr.academics.chmstry.general.atoms;



public class AluminumAtom extends PDBAtom
{
   private int atomicNumber = 13;
   private float electronegativity = 0;
   
   public AluminumAtom()
   {
      setColor(0xffcc23);
      setAtomicNumber(19);
      setAtomicSymbol("Al");
      setAtomicName("Aluminum");
   }
   
   public void initialize()
   {
      setColor(0xeeee23);
      setAtomicNumber(13);
   }  
}