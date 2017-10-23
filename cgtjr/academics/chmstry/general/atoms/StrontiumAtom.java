package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class StrontiumAtom extends PDBAtom
{
   public StrontiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xadbecf);
      setAtomicNumber(38);
      setAtomicSymbol("Sr");
      setAtomicName("Strontium");
   }
}