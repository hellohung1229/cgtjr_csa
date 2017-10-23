package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;

public class SiliconAtom extends PDBAtom
{
   private int atomicNumber = 14;
   private float electronegativity = 1.9000f;

   public SiliconAtom()
   {
      initialize();
   }
   public void initialize()
   {
      setColor(0x8a4a3a);
      setAtomicNumber(14);
      setAtomicSymbol("Si");
      setAtomicName("Silicon");
      setElectronAffinity(1.24f);
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