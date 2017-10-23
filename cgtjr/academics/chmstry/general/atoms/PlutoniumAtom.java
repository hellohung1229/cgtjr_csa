package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PlutoniumAtom extends PDBAtom
{
   public PlutoniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x4e8510);
      setAtomicNumber(94);
      setAtomicSymbol("Pu");
      setAtomicName("Plutonium");
   }
}