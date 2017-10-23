package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class TinAtom extends PDBAtom
{
   public TinAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x987654);
      setAtomicNumber(50);
      setAtomicSymbol("Sn");
      setAtomicName("Tin");
   }
}