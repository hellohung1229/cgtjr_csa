package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SeleniumAtom extends PDBAtom
{
   public SeleniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xd8d2d6);
      setAtomicNumber(34);
      setAtomicSymbol("Se");
      setAtomicName("Selenium");
   }
}