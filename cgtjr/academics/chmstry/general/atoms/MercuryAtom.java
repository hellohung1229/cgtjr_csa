package cgtjr.academics.chmstry.general.atoms;


public class MercuryAtom extends PDBAtom
{
   public MercuryAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x473483);
      setAtomicNumber(80);
      setAtomicSymbol("Hg");
      setAtomicName("Mercury");
   }
}