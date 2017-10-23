package cgtjr.academics.chmstry.general.atoms;


public class NitrogenAtom extends PDBAtom
{
   private int atomicNumber = 0;
   private float electronegativity = 3.0400f;

   public NitrogenAtom()
   {
      initializeColor();
      setRadius(0.70f);
   }
   public void initializeColor()
   {
      setColor(0x00ff00);
   }  
   public void setElectronegativity(float myElectronegativity)
   {
      electronegativity = myElectronegativity;
   }   
   public float getElectronegativity()
   {
      return electronegativity;
   }
   /*
   public void topologicalAction()
   {

   }*/
}