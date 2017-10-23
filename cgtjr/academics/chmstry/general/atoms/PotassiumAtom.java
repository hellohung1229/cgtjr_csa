package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PotassiumAtom extends PDBAtom
{
   public PotassiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xe3343e);
      setAtomicNumber(19);
      setAtomicSymbol("K");
      setAtomicName("Potassium");
   }
}