package cgtjr.academics.chmstry.physical;

import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.Atom;
import java.util.*;


public class Electronegativity
{
   private static HashMap electronegativeHashMap;

   public Electronegativity()
   {
      initializeElectroNegativity();
   }
   private static void initializeElectroNegativity()
   {
      electronegativeHashMap = new HashMap();
      electronegativeHashMap.put("H",new Float(2.2));
      electronegativeHashMap.put("C",new Float(2.5));
      electronegativeHashMap.put("N",new Float(3.0));
      electronegativeHashMap.put("O",new Float(3.4));
      electronegativeHashMap.put("S",new Float(4.4));
   }
   public static Float retrieveElectroNegativity(String myAtomName)
   {
      System.out.println("Molecule.retrieveElectronegative() : electronegative test1" +" atom name = "+myAtomName);
      if(electronegativeHashMap == null)
      {
         initializeElectroNegativity();
      }
      Float aFloat = (Float)electronegativeHashMap.get(myAtomName.trim());
      System.out.println("Molecule.retrieveElectronegative() : electronegative test2");
      //System.out.println("electronegativity value = "+aFloat);
      if(aFloat == null) 
      {
         aFloat = new Float(0);
      }
      return aFloat;
   }
   public static void electroNegativityConnect(Atom myAtom1,Atom myAtom2)
   {
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myAtom2.getName());
      System.out.println("Electronegativity.electronegativeConnect() : atomSymbol1 "+atomSymbol1);
      System.out.println("Electronegativity.electronegativeConnect() : atomSymbol2 "+atomSymbol2); 
      Float electroNegFloat1 = retrieveElectroNegativity(atomSymbol1);
      Float electroNegFloat2 = retrieveElectroNegativity(atomSymbol2);
      float electroNegValue1 = electroNegFloat1.floatValue();
      float electroNegValue2 = electroNegFloat2.floatValue();
      
      if(electroNegValue1 >= electroNegValue2)
      {
         myAtom1.pointToVertex(myAtom2);
      }else{
         myAtom2.pointToVertex(myAtom1);
      }
   }
}