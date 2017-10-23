package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class UraniumAtom extends PDBAtom
{
   public UraniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xaaaaaa);
      setAtomicNumber(92);
      setAtomicSymbol("U");
      setAtomicName("Uranium");
   }
}