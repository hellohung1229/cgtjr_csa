package cgtjr.academics.chmstry.general.atoms;

public class OxygenAtom extends PDBAtom
{
   private int atomicNumber = 0;
   private float electronegativity = 3.4400f;

   public OxygenAtom()
   {
      initializeColor();
      setRadius(.66f);
   }
   public void initializeColor()
   {
      setColor(0xff0000);
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