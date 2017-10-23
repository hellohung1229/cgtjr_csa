package cgtjr.academics.chmstry.general.atoms;


public class IronAtom extends PDBAtom
{
   public IronAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xb3e431);
      setAtomicNumber(26);
      setAtomicSymbol("Fe");
      setAtomicName("Iron");
   }
}