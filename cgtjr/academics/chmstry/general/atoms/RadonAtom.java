package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class RadonAtom extends PDBAtom
{
   public RadonAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xcfefaf);
      setAtomicNumber(86);
      setAtomicSymbol("Rn");
      setAtomicName("Radon");
   }
}