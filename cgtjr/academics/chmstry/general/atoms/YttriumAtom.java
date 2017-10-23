package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class YttriumAtom extends PDBAtom
{
   public YttriumAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xfc11cf);
      setAtomicNumber(39);
      setAtomicSymbol("Y");
      setAtomicName("Yttrium");
   }
}