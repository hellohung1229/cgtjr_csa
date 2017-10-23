package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class TitaniumAtom extends PDBAtom
{
   public TitaniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xfedcb);
      setAtomicNumber(22);
      setAtomicSymbol("Ti");
      setAtomicName("Titanium");
   }
}