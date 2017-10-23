package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class ThalliumAtom extends PDBAtom
{
   public ThalliumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xc2b1a0);
      setAtomicNumber(81);
      setAtomicSymbol("Tl");
      setAtomicName("Thallium");
   }
}