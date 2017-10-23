package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class TechnetiumAtom extends PDBAtom
{
   public TechnetiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x9a8b7c);
      setAtomicNumber(43);
      setAtomicSymbol("Tc");
      setAtomicName("TechnetiumAtom");
   }
}