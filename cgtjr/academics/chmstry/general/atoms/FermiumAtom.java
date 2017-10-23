package cgtjr.academics.chmstry.general.atoms;



public class FermiumAtom extends PDBAtom
{
   public FermiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xdd3433);
      setAtomicNumber(100);
      setAtomicSymbol("Fm");
      setAtomicName("Fermium");
   }  
}