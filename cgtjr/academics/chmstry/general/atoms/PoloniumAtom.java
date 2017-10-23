package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PoloniumAtom extends PDBAtom
{
   public PoloniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xef34ce);
      setAtomicNumber(84);
      setAtomicSymbol("Po");
      setAtomicName("Polonium");
   }
}