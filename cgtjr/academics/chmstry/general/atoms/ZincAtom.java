package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class ZincAtom extends PDBAtom
{
   public ZincAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xb8ff8b);
      setAtomicNumber(30);
      setAtomicSymbol("Zn");
      setAtomicName("Zinc");
   }
}