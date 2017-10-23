package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class ThoriumAtom extends PDBAtom
{
   public ThoriumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x0f1e2d);
      setAtomicNumber(90);
      setAtomicSymbol("Th");
      setAtomicName("Thorium");
   }
}