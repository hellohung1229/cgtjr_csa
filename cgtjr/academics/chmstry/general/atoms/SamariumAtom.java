package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SamariumAtom extends PDBAtom
{
   public SamariumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x947434);
      setAtomicNumber(62);
      setAtomicSymbol("Sm");
      setAtomicName("Samarium");
   }
}