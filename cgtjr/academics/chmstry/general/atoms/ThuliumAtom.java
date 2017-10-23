package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class ThuliumAtom extends PDBAtom
{
   public ThuliumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xc7f6e5);
      setAtomicNumber(69);
      setAtomicSymbol("Tm");
      setAtomicName("Thulium");
   }
}