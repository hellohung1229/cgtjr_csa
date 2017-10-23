package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class WolframAtom extends PDBAtom
{
   public WolframAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xa6b7c9);
      setAtomicNumber(74);
      setAtomicSymbol("W");
      setAtomicName("Wolfram");
   }
}