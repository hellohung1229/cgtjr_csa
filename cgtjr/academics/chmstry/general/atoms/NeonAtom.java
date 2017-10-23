package cgtjr.academics.chmstry.general.atoms;


public class NeonAtom extends PDBAtom
{
   public NeonAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x324282);
      setAtomicNumber(10);
      setAtomicSymbol("Ne");
      setAtomicName("Neon");
   }
}