package cgtjr.academics.chmstry.general.atoms;

public class HydrogenAtom extends PDBAtom
{
   private int atomicNumber = 1;
   private float electronegativity = 2.2000f;

   public HydrogenAtom()
   {
      initializeColor();
      setRadius(0.3f);
   }
   public void initializeColor()
   {
      setColor(0x0000cc);
   }  
   public void setElectronegativity(float myElectronegativity)
   {
      electronegativity = myElectronegativity;
   }   
   public float getElectronegativity()
   {
      return electronegativity;
   }
}