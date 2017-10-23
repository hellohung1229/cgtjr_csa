package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class ZirconiumAtom extends PDBAtom
{
   public ZirconiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xdc55cd);
      setAtomicNumber(40);
      setAtomicSymbol("Zr");
      setAtomicName("Zirconium");
   }
}