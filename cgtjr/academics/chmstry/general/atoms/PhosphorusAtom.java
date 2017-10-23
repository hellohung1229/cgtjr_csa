package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PhosphorusAtom extends PDBAtom
{
   public PhosphorusAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xc345ec);
      setAtomicNumber(15);
      setAtomicSymbol("P");
      setAtomicName("Phosphorus");
   }
}