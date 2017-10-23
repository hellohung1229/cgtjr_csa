package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;


public class PlatinumAtom extends PDBAtom
{
   public PlatinumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x342234);
      setAtomicNumber(78);
      setAtomicSymbol("Pt");
      setAtomicName("Platinum");
   }
}