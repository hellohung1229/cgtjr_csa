package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class XenonAtom extends PDBAtom
{
   public XenonAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x44aabb);
      setAtomicNumber(54);
      setAtomicSymbol("Xe");
      setAtomicName("Xenon");
   }
}