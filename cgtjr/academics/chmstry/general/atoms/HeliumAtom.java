package cgtjr.academics.chmstry.general.atoms;


public class HeliumAtom extends PDBAtom
{
   public HeliumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xdd3333);
      setAtomicNumber(2);
      setAtomicSymbol("He");
      setAtomicName("Helium");
   }  
}