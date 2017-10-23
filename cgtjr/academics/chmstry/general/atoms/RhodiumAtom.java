package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class RhodiumAtom extends PDBAtom
{
   public RhodiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x45ecba);
      setAtomicNumber(45);
      setAtomicSymbol("Rh");
      setAtomicName("Rhodium");
   }
}