package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.linepnts.LineVector;
import cgtjr.academics.math.geometry.linepnts.AngleLine;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.Atom;
import java.util.*;
import cgtjr.academics.math.geometry.*;
import javax.swing.*;

public class DipoleMoment // extends ShapeVector
{
   private static HashMap dipoleHashMap;
   private static LineVector dipoleMomentVector;
   private HashMap histogramHashMap;
   private JEditorPane outputJEditorPane;
   private static Vector diatomicListing;

   public DipoleMoment()
   {
      dipoleMomentVector = new LineVector();
      histogramHashMap = new HashMap();
      diatomicListing = new Vector();
   }
   public DipoleMoment(LineVector myLineVector)
   {
      //super(myLineVector);
      histogramHashMap = new HashMap();
      diatomicListing = new Vector();
   }
   public Vector retrieveDiatomicListing()
   {
      return diatomicListing;
   }
   public void setOutputJEditorPane(JEditorPane aJEditorPane)
   {
      outputJEditorPane = aJEditorPane;
   }
   public JEditorPane getOutputJEditorPane()
   {
      return outputJEditorPane;
   }
   //Dopole moments in debyes = 10^-18 statC cm
   //Note 1 C m correspond to 2.9979 x 10^11 statC cm
   private static void initializeDipoles()
   {
      
      if(dipoleHashMap == null)
      {
         dipoleHashMap = new HashMap();
      }
      ElctrcChrgData aDipoleMomentFile = new ElctrcChrgData();
      dipoleHashMap = aDipoleMomentFile.rdDipoleFile("data/chemistry/physical/dipoledata.csv");

      /*
      dipoleHashMap.put("H-O",new Float(1.5));
      dipoleHashMap.put("C-N",new Float(.5));
      dipoleHashMap.put("H-C",new Float(.4));
      dipoleHashMap.put("C=O",new Float(2.5));
      dipoleHashMap.put("C-O",new Float(0.8));
      dipoleHashMap.put("H-N",new Float(1.3));
      dipoleHashMap.put("O-S",new Float(1.55));
      dipoleHashMap.put("N-S",new Float(1.81));
      dipoleHashMap.put("C-S",new Float(1.958));
      */
   }
   public static float statCoulombToCoulombMeter(float aStatCoulomb)
   {
      float aValue = (float)Math.pow(10,-11)*aStatCoulomb/(2.9979f);
      return aValue;
   }
   public static float debyeToStatCoulomb(float aDebye)
   {
      float aValue = (float)Math.pow(10,-18)*aDebye;
      return aValue;
   }
   public static LineVector getDipoleMomentVector()
   {
      return dipoleMomentVector;
   }
   public float dipoleValue(Atom myAtom1,Atom myAtom2)
   {
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myAtom2.getName());
      //System.out.println("DipoleMoment.dipoleValue() : atomSymbol1 = "+atomSymbol1);
      //System.out.println("DipoleMoment.dipoleValue() : atomSymbol2 = "+atomSymbol2);

      String aKey1 = atomSymbol1+"-"+atomSymbol2;
      String aKey2 = atomSymbol2+"-"+atomSymbol1;

      LineVector aLineVector = new LineVector(myAtom2,myAtom1);
      aLineVector.normalize();
      
      if(dipoleHashMap == null)
      {
         DipoleMoment.initializeDipoles();
      }
      if(atomSymbol1.equals(atomSymbol2))
      {
         //float aFloatValue = 0.0f;
         //aLineVector.scale(aFloatValue);
         return 0.0f;
      }else if(dipoleHashMap.containsKey(aKey1))
      {
         Float aFloatObject1 = (Float) dipoleHashMap.get(aKey1);
         float aFloatValue = aFloatObject1.floatValue();
         //float aFloatValue1 = debyeToStatCoulomb(aFloatValue);
         //float aFloatValue2 = statCoulombToCoulombMeter(aFloatValue1);
         return aFloatValue;
      }else if(dipoleHashMap.containsKey(aKey2)){
         Float aFloatObject1 = (Float) dipoleHashMap.get(aKey2);
         //float aFloatValue = -1*aFloatObject1.floatValue();
         float aFloatValue = aFloatObject1.floatValue();
         //float aFloatValue1 = debyeToStatCoulomb(aFloatValue);
         //float aFloatValue2 = statCoulombToCoulombMeter(aFloatValue1);
         return aFloatValue;
      }else{
         return 0.0f;
      }
   }
   public static LineVector dipoleVector(Atom myAtom1,Atom myAtom2)
   {
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myAtom2.getName());
      //System.out.println("DipoleMoment.dipoleValue() : atomSymbol1 = "+atomSymbol1);
      //System.out.println("DipoleMoment.dipoleValue() : atomSymbol2 = "+atomSymbol2);

      String aKey1 = atomSymbol1+"-"+atomSymbol2;
      String aKey2 = atomSymbol2+"-"+atomSymbol1;

      AngleLine dipoleLine = null;

      LineVector aLineVector = new LineVector(myAtom2,myAtom1);
      aLineVector.normalize();
      aLineVector.scale((float)Math.pow(10,-10));
      
      if(dipoleHashMap == null)
      {
         DipoleMoment.initializeDipoles();
      }
      if(atomSymbol1.equals(atomSymbol2))
      {
         float aFloatValue = 0.0f;
         aLineVector.scale(aFloatValue);
         dipoleLine = new AngleLine(myAtom1,myAtom2);
         dipoleLine.pointToVertex(myAtom1);
         dipoleLine.pointToVertex(myAtom2);
         diatomicListing.add(dipoleLine);
         return aLineVector;
      }else if(dipoleHashMap.containsKey(aKey1))
      {
         Float aFloatObject1 = (Float) dipoleHashMap.get(aKey1);
         float aFloatValue = aFloatObject1.floatValue();
         //float aFloatValue1 = debyeToStatCoulomb(aFloatValue);
         //float aFloatValue2 = statCoulombToCoulombMeter(aFloatValue1);
         aLineVector.scale(aFloatValue);
         dipoleLine = new LineVector();
         dipoleLine.setX1(myAtom2.getX1()); 
         dipoleLine.setY1(myAtom2.getY1());
         dipoleLine.setZ1(myAtom2.getZ1());
         dipoleLine.setX2(myAtom1.getX1());
         dipoleLine.setY2(myAtom1.getY1());
         dipoleLine.setZ2(myAtom1.getZ1());
         dipoleLine.pointToVertex(myAtom1);
         dipoleLine.pointToVertex(myAtom2);
         diatomicListing.add(dipoleLine);
         assignCharge(myAtom1,myAtom2,aFloatValue);
         return aLineVector;
      }else if(dipoleHashMap.containsKey(aKey2)){
         Float aFloatObject1 = (Float) dipoleHashMap.get(aKey2);
         float aFloatValue = -1*aFloatObject1.floatValue();
         //float aFloatValue1 = debyeToStatCoulomb(aFloatValue);
         //float aFloatValue2 = statCoulombToCoulombMeter(aFloatValue1);                  
         aLineVector.scale(aFloatValue);
         dipoleLine = new LineVector();
         dipoleLine.setX1(myAtom1.getX1()); 
         dipoleLine.setY1(myAtom1.getY1());
         dipoleLine.setZ1(myAtom1.getZ1());
         dipoleLine.setX2(myAtom2.getX1());
         dipoleLine.setY2(myAtom2.getY1());
         dipoleLine.setZ2(myAtom2.getZ1());
         dipoleLine.pointToVertex(myAtom1);
         dipoleLine.pointToVertex(myAtom2);
         diatomicListing.add(dipoleLine);
         assignCharge(myAtom1,myAtom2,aFloatValue);
         return aLineVector;
      }else{
         return null;
      }
   }
   public static void assignCharge(Atom myAtom1,Atom myAtom2,float myValue)
   {
      float aDistance = (float)PntTool.getDistance(myAtom1,myAtom2);
      float aCharge = myValue/aDistance;
      myAtom1.setCharge(aCharge);
      myAtom2.setCharge(-1*aCharge);
      System.out.println("DipoleMoment: charge = "+aCharge);
   }
   public LineVector calculateDipoleByWalk(MoleculeModel myPDBMolecule)
   {
      MoleculeModel aPDBMolecule1 = null;
      MoleculeModel aPDBMolecule2 = null;
      PDBAtom aPDBAtom2 = null;
      
      Enumeration anEnumeration1 = myPDBMolecule.rtrvEnumeration();
      //System.out.println("DipoleMoment.calculateDipole() : starting topological walk ... test 1");

      while(anEnumeration1.hasMoreElements())
      {  
         //System.out.println("DipoleMoment.calculateDipole() : starting topological walk ... test 2");
         aPDBMolecule1 = (MoleculeModel)anEnumeration1.nextElement();
         //aPDBMolecule1.setTopologicalState(0);
         System.out.println("DipoleMoment ... check code ...");
         aPDBMolecule1.topologicalWalk();
      }
      LineVector aLineVector = retrieveDipoleVector();
      return aLineVector;
   }
   public LineVector calculateDipole(MoleculeModel myPDBMolecule1)
   {
      MoleculeModel aPDBMolecule2 = null;
      PDBAtom aPDBAtom1 = null;
      
      Enumeration anEnumeration1 = myPDBMolecule1.rtrvEnumeration();

      Enumeration anEnumeration2 = null;
      while(anEnumeration1.hasMoreElements())
      {  
         aPDBMolecule2 = (MoleculeModel)anEnumeration1.nextElement();
         anEnumeration2 = aPDBMolecule2.rtrvEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            aPDBAtom1 = (PDBAtom)anEnumeration2.nextElement();
            calculateDipoleMoment(aPDBAtom1);
         }
      }
      LineVector aLineVector = retrieveDipoleVector();

      return aLineVector;
   }
   public LineVector calculateDipoleMoment(PDBAtom aPDBAtom)
   {
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      LineVector aLineVector = null;


      //System.out.println("DipoleMoment.calculateDipoleMoment(): performing action for "+aPDBAtom.getName());
      Enumeration anEnumeration = aPDBAtom.rtrvEnumeration();
      //System.out.println("DipoleMoment.calculateDipoleMoment(): number of connections = "+aPDBAtom.getAdjacentVertices().size());
      
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom1 = aPDBAtom;
         aPDBAtom2 = (PDBAtom) anEnumeration.nextElement();
         //System.out.println("DipoleMoment.calculateDipoleMoment(): atom1 = "+aPDBAtom1.getName()+" & atom2 = "+aPDBAtom2.getName());

         
         if(aPDBAtom1 != null && aPDBAtom2 != null)
         {
            aLineVector = dipoleVector(aPDBAtom1,aPDBAtom2);
            calculateHistogram(aPDBAtom1,aPDBAtom2,aLineVector);
            //System.out.println("DipoleMoment.calculateDipoleMoment() : LineVector = "+aLineVector);
            dipoleMomentVector.add(aLineVector);
         }
      }
      return dipoleMomentVector;
   }
   
   public static LineVector calculateObservedDipole(PDBAtom aPDBAtom)
   {
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      LineVector aLineVector = null;

      //System.out.println("DipoleMoment.calculateDipoleMoment(): performing action for "+aPDBAtom.getName());
      Enumeration anEnumeration = aPDBAtom.rtrvEnumeration();
      //System.out.println("DipoleMoment.calculateDipoleMoment(): number of connections = "+aPDBAtom.getAdjacentVertices().size());
      
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom1 = aPDBAtom;
         aPDBAtom2 = (PDBAtom) anEnumeration.nextElement();
         //System.out.println("DipoleMoment.calculateDipoleMoment(): atom1 = "+aPDBAtom1.getName()+" & atom2 = "+aPDBAtom2.getName());

         if(aPDBAtom1 != null && aPDBAtom2 != null) 
         {
            aLineVector = dipoleVector(aPDBAtom1,aPDBAtom2);
            //System.out.println("DipoleMoment.calculateDipoleMoment() : LineVector = "+aLineVector);
            dipoleMomentVector.add(aLineVector);
         }
      }
      return dipoleMomentVector;
   }
   public void calculateHistogram(PDBAtom myPDBAtom1,PDBAtom myPDBAtom2,LineVector myLineVector)
   {
      LineVector aLineVector1 = null;
      Vector aVector = null;
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myPDBAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myPDBAtom2.getName());
      String dipolePair1 = atomSymbol1+"-"+atomSymbol2;
      String dipolePair2 = atomSymbol2+"-"+atomSymbol1;
    
      if(histogramHashMap.containsKey(dipolePair1))
      {
         aLineVector1 = (LineVector)histogramHashMap.get(dipolePair1);
         aLineVector1.add(myLineVector);
         histogramHashMap.put(dipolePair1,aLineVector1);
      }else if(histogramHashMap.containsKey(dipolePair2))
      {
         aLineVector1 = (LineVector)histogramHashMap.get(dipolePair2);
         aLineVector1.add(myLineVector);
         histogramHashMap.put(dipolePair2,aLineVector1);
      }else{     
         histogramHashMap.put(dipolePair1,myLineVector);       
      }

   }

   public String displayHistogram()
   {
      String output = "";
      LineVector aLineVector = null;
      Vector aVector = null;
      String dipolePair = "";
      Set aSet = (Set)histogramHashMap.keySet();
      Iterator anIterator = aSet.iterator();
      while(anIterator.hasNext())
      {
         dipolePair = (String)anIterator.next();
         aLineVector = (LineVector)histogramHashMap.get(dipolePair);
         output += dipolePair+", "+aLineVector.getX2()+", "+aLineVector.getY2()+", "+aLineVector.getZ2()+", "+aLineVector.getMagnitude()+"\n";
      }
      //System.out.println("DipoleMoment.displayHistogram() : "+output);
      output += "Total"+","+getDipoleMomentVector().getX2()+","+getDipoleMomentVector().getY2()+","+getDipoleMomentVector().getZ2()+","+getDipoleMomentVector().getMagnitude()+"\n";
      outputJEditorPane.setText(output);
      return output;
   }

   /*
   public static LineVector calculateDipoleVector(PDBAtom aPDBAtom)
   {
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      LineVector aLineVector = null;
      float x1 = 0.0f;
      float y1 = 0.0f;
      float z1 = 0.0f;
      float x2 = 0.0f;
      float y2 = 0.0f;
      float z2 = 0.0f;
      float magnitude = 0.0f;

      System.out.println("DipoleMoment.calculateDipoleVector(): performing action for "+aPDBAtom.getName());
      Enumeration anEnumeration = aPDBAtom.retrieveEnumeration();
      System.out.println("DipoleMoment.calculateDipoleVector(): number of connections = "+aPDBAtom.getAdjacentVertices().size());
      
      while(anEnumeration.hasMoreElements())
      {
         aPDBAtom1 = aPDBAtom;
         aPDBAtom2 = (PDBAtom) anEnumeration.nextElement();
         System.out.println("DipoleMoment.calculateDipoleMoment(): atom1 = "+aPDBAtom1.getName()+" & atom2 = "+aPDBAtom2.getName());

         if(aPDBAtom1 != null && aPDBAtom2 != null)
         {
            aLineVector = dipoleVector(aPDBAtom1,aPDBAtom2);
            System.out.println("DipoleMoment.calculateDipoleMoment() : LineVector = "+aLineVector);
            x1 = MathBasic.getFloatValue(aPDBAtom.getX1());
            //dipoleMomentVector.addVector(aLineVector);
         }
      }
      return dipoleMomentVector;
   }*/
   public static LineVector calculateDipoleMoment(Atom myAtom1,Atom myAtom2)
   {
      LineVector dipole = dipoleVector(myAtom1,myAtom2);

      //System.out.println("dipole value = "+dipole);
      
      return dipole;
   }
   public LineVector retrieveDipoleVector()
   {
      return dipoleMomentVector;
   }
}