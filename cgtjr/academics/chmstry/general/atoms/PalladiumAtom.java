package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PalladiumAtom extends PDBAtom
{
   public PalladiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xc345ec);
      setAtomicNumber(46);
      setAtomicSymbol("Pd");
      setAtomicName("Palladium");
   }
}