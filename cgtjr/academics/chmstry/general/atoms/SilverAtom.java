package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SilverAtom extends PDBAtom
{
   public SilverAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xf4e6d8);
      setAtomicNumber(47);
      setAtomicSymbol("Ag");
      setAtomicName("Silver");
   }
}