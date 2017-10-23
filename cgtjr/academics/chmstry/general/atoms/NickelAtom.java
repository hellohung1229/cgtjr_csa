package cgtjr.academics.chmstry.general.atoms;


public class NickelAtom extends PDBAtom
{
   public NickelAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x2343eb);
      setAtomicNumber(28);
      setAtomicSymbol("Ni");
      setAtomicName("Nickel");
   }
}