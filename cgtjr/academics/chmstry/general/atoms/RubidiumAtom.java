package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class RubidiumAtom extends PDBAtom
{
   public RubidiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x307050);
      setAtomicNumber(37);
      setAtomicSymbol("Rb");
      setAtomicName("Rubidium");
   }
}