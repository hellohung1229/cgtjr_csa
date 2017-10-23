package cgtjr.academics.chmstry.general.atoms;


public class LeadAtom extends PDBAtom
{
   public LeadAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xf2dd00);
      setAtomicNumber(82);
      setAtomicSymbol("Pb");
      setAtomicName("Lead");
   }
}