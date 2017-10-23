package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SodiumAtom extends PDBAtom
{
   public SodiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xe4996);
      setAtomicNumber(11);
      setAtomicSymbol("Na");
      setAtomicName("Sodium");
   }
}