package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SulfurAtom extends PDBAtom
{
   private int atomicNumber = 16;
   private float electronegativity = 2.5800f;

   public SulfurAtom()
   {
      initializeColor();
      setRadius(1.04f);
   }
   public void initializeColor()
   {
      setColor(0x990099);
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