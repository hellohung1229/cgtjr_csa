package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class RadiumAtom extends PDBAtom
{
   public RadiumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x342323);
      setAtomicNumber(88);
      setAtomicSymbol("Ra");
      setAtomicName("Radium");
   }
}