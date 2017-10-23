package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class RheniumAtom extends PDBAtom
{
   public RheniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x473432);
      setAtomicNumber(75);
      setAtomicSymbol("Re");
      setAtomicName("Rhenium");
   }
}