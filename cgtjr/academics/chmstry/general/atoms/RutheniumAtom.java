package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class RutheniumAtom extends PDBAtom
{
   public RutheniumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x3445ea);
      setAtomicNumber(44);
      setAtomicSymbol("Ru");
      setAtomicName("Ruthenium");
   }
}