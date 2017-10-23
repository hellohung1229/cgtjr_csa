package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class ProtactiniumAtom extends PDBAtom
{
   public ProtactiniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x3c7b4a);
      setAtomicNumber(61);
      setAtomicSymbol("Pm");
      setAtomicName("Protactinium");
   }
}