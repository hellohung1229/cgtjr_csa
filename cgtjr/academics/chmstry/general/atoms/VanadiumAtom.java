package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class VanadiumAtom extends PDBAtom
{
   public VanadiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xd1c3b5);
      setAtomicNumber(23);
      setAtomicSymbol("V");
      setAtomicName("Vanadium");
   }
}