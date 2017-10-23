package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class TantalumAtom extends PDBAtom
{
   public TantalumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x010409);
      setAtomicNumber(73);
      setAtomicSymbol("Ta");
      setAtomicName("Tantalum");
   }
}