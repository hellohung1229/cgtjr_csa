package cgtjr.academics.chmstry.general.atoms;




public class ActiniumAtom extends PDBAtom
{
   private int atomicNumber = 0;
   private float electronegativity = 0;
   
   public ActiniumAtom()
   {
      setColor(0xffcc23);
      setAtomicNumber(227);
      setAtomicSymbol("Ac");
      setAtomicName("Actinium");
   }
   public void initializeColor()
   {
      setColor(0xeeee23);
   }  
   public void setElectronegativity(float myElectronegativity)
   {
      electronegativity = myElectronegativity;
   }   
   public float getElectronegativity()
   {
      return electronegativity;
   }
   public void topologicalAction()
   {
   }
}