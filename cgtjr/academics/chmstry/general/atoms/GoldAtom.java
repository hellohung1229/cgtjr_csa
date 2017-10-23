package cgtjr.academics.chmstry.general.atoms;


public class GoldAtom extends PDBAtom
{
   public GoldAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0xdd3433);
      setAtomicNumber(79);
      setAtomicSymbol("Au");
      setAtomicName("GoldAtom");
   }  
}