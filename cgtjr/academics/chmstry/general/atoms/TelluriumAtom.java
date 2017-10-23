package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class TelluriumAtom extends PDBAtom
{
   public TelluriumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xc3b7a9);
      setAtomicNumber(52);
      setAtomicSymbol("Te");
      setAtomicName("TelluriumAtom");
   }
}