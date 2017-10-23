package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.linepnts.LineVector;
import cgtjr.academics.math.geometry.shapebndry.BoundaryShape_I;
import cgtjr.academics.math.geometry.crdntepnts.BoxShape;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.chmstry.general.units.Cnvrsn;
import cgtjr.academics.math.geometry.*;
import java.util.*;

public class Polarization extends DipoleMoment
{
   private LineVector polarizationVector;
   private float thePolarizationVolume = 0;
   private HashMap dipoleListHashMap;
   private HashMap histogramVector;
   private HashMap histogramHashMap;
   private HashMap coordHashMap;
   private HashMap coordHashMap1;
   private ShpBndry shapeBndry;

   public Polarization()
   {
      polarizationVector = new LineVector();
      thePolarizationVolume = 0;
      dipoleListHashMap = new HashMap();
      coordHashMap = new HashMap();
      coordHashMap1 = new HashMap();
      shapeBndry = new ShpBndry();
   }
   public Polarization(LineVector myLineVector)
   {
      super(myLineVector);
      polarizationVector = new LineVector();
      thePolarizationVolume = 0;
      dipoleListHashMap = new HashMap();
      coordHashMap = new HashMap();
      coordHashMap1 = new HashMap();
   }
   public void resetAllValues()
   {
      polarizationVector = new LineVector();
      thePolarizationVolume = 0;
      dipoleListHashMap = new HashMap();
      coordHashMap = new HashMap();
   }
   public Vector calculatePolarization(MoleculeModel myPDBMolecule1,Vector myVector)
   {
      Vector aLineVectorVector = new Vector();
      LineVector aLineVector = null;
      LineVector aLineVector2 = null;
      int aSize = myVector.size();
      BoxShape aBoxShape = null;
      System.out.println("Polarization : size = "+aSize);
      for(int i=0;i<aSize;i++)
      {
         System.out.println("Polarization test1, i = "+i);         
         aBoxShape = (BoxShape) myVector.elementAt(i);
         aLineVector = calculatePolarization(myPDBMolecule1,aBoxShape);
         aLineVector.normalize();
         aLineVector.scale(4);
         System.exit(0);
         System.out.println("Polarization: check code ..");
         //aLineVector.translate(aBoxShape);
         aLineVectorVector.add(aLineVector);
         myPDBMolecule1.resetDegreeVisited();
         resetAllValues();
      }   
      return aLineVectorVector;
   }
   public LineVector calculatePolarization(MoleculeModel myPDBMolecule1,BoundaryShape_I myBoundary)
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
            if(myBoundary.isInBoundary(aPDBAtom1.getX1(),aPDBAtom1.getY1(),aPDBAtom1.getZ1()))
            {
               calculatePolarization(aPDBAtom1);
            }         
         }
      }
      LineVector aLineVector = getPolarizationVector();
      aLineVector.scale(1/thePolarizationVolume);
      return aLineVector;
   }
   public LineVector calculatePolarization(MoleculeModel myPDBMolecule1)
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
            calculatePolarization(aPDBAtom1);
         }
      }
      LineVector aLineVector = getPolarizationVector();
      aLineVector.scale(1/thePolarizationVolume);
      return aLineVector;
   }
   public LineVector calculatePolarization(Atom anAtom)
   {
      Atom anAtom1 = null;
      Atom anAtom2 = null;
      LineVector aLineVector = null;
      LineVector aDipoleMomentVector = new LineVector();

      DipoleMoment aDipoleMoment = new DipoleMoment();

      System.out.println("Polarization.calculatePolarization(): performing action for "+anAtom.getName());
      Enumeration anEnumeration = anAtom.rtrvEnumeration();
      System.out.println("Polarization.calculatePolarization(): number of connections = "+anAtom.getAdjcntVertices().size());

      updatePolarizationBoxVolume(anAtom);

      while(anEnumeration.hasMoreElements())
      {
         anAtom1 = anAtom;
         anAtom2 = (Atom) anEnumeration.nextElement();
         updatePolarizationBoxVolume(anAtom2);
         System.out.println("Polarization.calculatePolarization() : atom1 = "+anAtom1.getName()+" & atom2 = "+anAtom2.getName());

         if(anAtom1 != null && anAtom2 != null && anAtom1 != anAtom2)
         {
            aLineVector = aDipoleMoment.dipoleVector(anAtom1,anAtom2);
            calculateDipoleList(anAtom1,anAtom2,aLineVector);
            createDipoleGraph(anAtom1,anAtom2,aLineVector);
            System.out.println("Polarization.calculatePolarization() : LineVector = "+aLineVector);
            //aDipoleMomentVector.addVector(aLineVector);
            polarizationVector.add(aLineVector);
            System.out.println("Polarization.calculatePolarization() : polarizationVector = "+polarizationVector);
         }
      }
      /*
      polarizationVector.setX2(aDipoleMomentVector.getX2());
      polarizationVector.setY2(aDipoleMomentVector.getY2());
      polarizationVector.setZ2(aDipoleMomentVector.getZ2());
      */
      return polarizationVector;
   }
   public HashMap calculateDipoleList(Atom myPDBAtom1,Atom myPDBAtom2,LineVector myLineVector)
   {
      LineVector aLineVector1 = null;
      LineVector aLineVector2 = new LineVector(myPDBAtom1,myPDBAtom2);
      String lineVectorData = "";
      Vector aVector = null;
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myPDBAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myPDBAtom2.getName());
      String dipolePair = atomSymbol1+"-"+atomSymbol2;

      cgtjr.academics.math.geometry.crdntepnts.Pnt midPoint = aLineVector2.getMidPoint();
      String dipolePairWithCoord = dipolePair+","+midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1();

      if(dipoleListHashMap.containsKey(dipolePair))
      {
         aVector = (Vector)dipoleListHashMap.get(dipolePair);
         lineVectorData = midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1()+","+myLineVector.getX2()+","+myLineVector.getY2()+","+myLineVector.getZ2();
         aVector.add(lineVectorData);
         //aVector.add(midPoint);
         dipoleListHashMap.put(dipolePair,aVector);
      }else{
         aVector = new Vector();
         lineVectorData = midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1()+","+myLineVector.getX2()+","+myLineVector.getY2()+","+myLineVector.getZ2();
         aVector.add(lineVectorData);
         //aVector.add(midPoint);
         dipoleListHashMap.put(dipolePair,aVector);
      }
      return dipoleListHashMap;
   }
   public HashMap createDipoleGraph(Atom myPDBAtom1,Atom myPDBAtom2,LineVector myLineVector)
   {
      System.out.println("Polarization : createDipoleGraph()");

      LineVector aLineVector1 = null;
      LineVector aLineVector2 = new LineVector(myPDBAtom1,myPDBAtom2);
      String lineVectorData = "";

      Vector aVector = null;
      String atomSymbol1 = MoleculeModel.retrieveAtomSymbol(myPDBAtom1.getName());
      String atomSymbol2 = MoleculeModel.retrieveAtomSymbol(myPDBAtom2.getName());
      String dipolePair = atomSymbol1+"-"+atomSymbol2;

      cgtjr.academics.math.geometry.crdntepnts.Pnt midPoint = aLineVector2.getMidPoint();

      String mes = "Polarization : inserting midpoint - "+dipolePair+","+midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1();
      System.out.println(mes);
      coordHashMap1.put(midPoint,myLineVector);
      System.out.println("Polarization : size = "+coordHashMap1.size());
      return coordHashMap1;
   }
   public String displayDipoleGraph(char aChar1,char aChar2)
   {
      System.out.println("Polarization : displaying dipole graph");
      LineVector aLineVector = null;
      String output = "";
      String aLineVectorData = null;
      Vector aVector = null;
      int vectorSize = 0;
      String dipolePair = "";
      cgtjr.academics.math.geometry.crdntepnts.Pnt midPoint = null;

      Set aSet = (Set)coordHashMap1.keySet();
      //System.out.println("Polarization : size = "+coordHashMap1.size());
      Iterator anIterator = aSet.iterator();
      //System.out.println("test 0 ...aChar1 = "+aChar1+" aChar 2 = "+aChar2 +"output = "+output);
      while(anIterator.hasNext())
      {
         midPoint = (cgtjr.academics.math.geometry.crdntepnts.Pnt)anIterator.next();
         aLineVector = (LineVector)coordHashMap1.get(midPoint);
         
         if(aChar1 == 'z' && aChar2 == 'x')
         {
           output += midPoint.getZ1()+","+aLineVector.getX2()+"\n";
         }
         else if(aChar1 == 'z' && aChar2 == 'y')
         {
           output += midPoint.getZ1()+","+aLineVector.getY2()+"\n";
         }
         else if(aChar1 == 'z' && aChar2 == 'z')
         {
           output += midPoint.getZ1()+","+aLineVector.getZ2()+"\n";
         }
         System.out.println("test 1 ...aChar1 = "+aChar1+" aChar 2 = "+aChar2 +"output = "+output);
      }
      output += "\n-----------------------------------\n";
      output += "Total"+","+getDipoleMomentVector().getX2()+","+getDipoleMomentVector().getY2()+","+getDipoleMomentVector().getZ2()+","+getDipoleMomentVector().getMagnitude()+"\n";
      System.out.println("Polarization: test 3 ...aChar1 = "+aChar1+" aChar 2 = "+aChar2 +"output = "+output);      
      getOutputJEditorPane().setText(output);
      return output;
   }
   /*
   public HashMap createDipoleGraph(Atom myPDBAtom1,Atom myPDBAtom2,LineVector myLineVector)
   {
      LineVector aLineVector1 = null;
      LineVector aLineVector2 = new LineVector(myPDBAtom1,myPDBAtom2);
      String lineVectorData = "";
      Vector aVector = null;
      String atomSymbol1 = PDBMolecule.retrieveAtomSymbol(myPDBAtom1.getName());
      String atomSymbol2 = PDBMolecule.retrieveAtomSymbol(myPDBAtom2.getName());
      String dipolePair = atomSymbol1+"-"+atomSymbol2;

      cgtjr.academics.math.geometry.Point midPoint = aLineVector2.getMidPoint();
      String dipolePairWithCoord = dipolePair+","+midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1();

      if(dipoleGraphHashMap.containsKey(dipolePair))
      {
         aVector = (Vector)dipoleListHashMap.get(dipolePair);
         lineVectorData = midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1()+","+myLineVector.getX2()+","+myLineVector.getY2()+","+myLineVector.getZ2();
         aVector.add(lineVectorData);
         dipoleGraphHashMap.put(dipolePair,aVector);
      }else{
         aVector = new Vector();
         lineVectorData = midPoint.getX1()+","+midPoint.getY1()+","+midPoint.getZ1()+","+myLineVector.getX2()+","+myLineVector.getY2()+","+myLineVector.getZ2();
         aVector.add(lineVectorData);
         xDipoleGraphTreeMap.put(dipolePair,aVector);
      }
      return dipoleListHashMap;
   }
   public String createPolarizationGraph()
   {
      TreeMap xTreeMap = new TreeMap();
      TreeMap yTreeMap = new TreeMap();
      TreeMap zTreeMap = new TreeMap();

      cgtjr.academics.math.geometry.Point aPoint1 = null;

      String output = "";
      String aLineVectorData = null;
      Vector aVector = null;
      int vectorSize = 0;
      String dipolePair = "";
      Set aSet = (Set)dipoleListHashMap.keySet();
      Iterator anIterator = aSet.iterator();
      while(anIterator.hasNext())
      {
         dipolePair = (String)anIterator.next();
         aVector = (Vector)dipoleListHashMap.get(dipolePair);
         vectorSize = aVector.size();
         for(int i=0;i<vectorSize;i++)
         {
            aPoint1 = (cgtjr.academics.math.geometry.Point)aVector.elementAt(i);
            xTree
            output += dipolePair+","+aLineVectorData+"\n";
            System.out.println(output);
         }
      }
      output += "Total"+","+getDipoleMomentVector().getX2()+","+getDipoleMomentVector().getY2()+","+getDipoleMomentVector().getZ2()+","+getDipoleMomentVector().getMagnitude()+"\n";
      getOutputJEditorPane().setText(output);
      return output;
   }*/
   public String displayDipoleList()
   {
      String output = "";
      String aLineVectorData = null;
      Vector aVector = null;
      int vectorSize = 0;
      String dipolePair = "";
      Set aSet = (Set)dipoleListHashMap.keySet();
      Iterator anIterator = aSet.iterator();
      cgtjr.academics.math.geometry.crdntepnts.Pnt aPoint1 = null;

      while(anIterator.hasNext())
      {
         dipolePair = (String)anIterator.next();
         aVector = (Vector)dipoleListHashMap.get(dipolePair);
         vectorSize = aVector.size();
         for(int i=0;i<vectorSize;i++)
         {
            aLineVectorData = (String)aVector.elementAt(i);
            output += dipolePair+","+aLineVectorData+"\n";
            System.out.println(output);
         }
      }
      System.out.println("dipoleListHashMap size = "+dipoleListHashMap.size());
      output += "Total"+","+getDipoleMomentVector().getX2()+","+getDipoleMomentVector().getY2()+","+getDipoleMomentVector().getZ2()+","+getDipoleMomentVector().getMagnitude()+"\n";
      output += "\n";
      getOutputJEditorPane().setText(output);
      return output;
   }
   private void resetPolarizationBoxVolume()
   {
      shapeBndry.setXMax(Float.NEGATIVE_INFINITY);
      shapeBndry.setYMax(Float.NEGATIVE_INFINITY);
      shapeBndry.setZMax(Float.NEGATIVE_INFINITY);
      shapeBndry.setXMin(Float.NEGATIVE_INFINITY);
      shapeBndry.setYMin(Float.NEGATIVE_INFINITY);
      shapeBndry.setZMin(Float.NEGATIVE_INFINITY);
      //shapeBndry.setLength(0);
      //shapeBndry.setWidth(0);
      //shapeBndry.setHeight(0);
   }
   private float updatePolarizationBoxVolume(Atom anAtom)
   {
      float length = 0;
      float width  = 0;
      float height = 0;

      float xMax = (float)shapeBndry.getXMax();
      float yMax = (float)shapeBndry.getYMax();
      float zMax = (float)shapeBndry.getZMax();
      float xMin = (float)shapeBndry.getXMin();
      float yMin = (float)shapeBndry.getYMin();
      float zMin = (float)shapeBndry.getZMin();

      float xPosAtom = anAtom.getX1();
      float yPosAtom = anAtom.getY1();
      float zPosAtom = anAtom.getZ1();

      System.out.println("Polarization.updatePolarizationBoxVolume() : xPosition = "+xPosAtom+" yPosition = "+yPosAtom+" zPosition = "+zPosAtom);
      if(xMax == Float.NEGATIVE_INFINITY || yMax == Float.NEGATIVE_INFINITY || zMax == Float.NEGATIVE_INFINITY || 
         xMin == Float.NEGATIVE_INFINITY || yMin == Float.NEGATIVE_INFINITY || zMin == Float.NEGATIVE_INFINITY)
      {
         shapeBndry.setXMax(xPosAtom);
         shapeBndry.setYMax(yPosAtom);
         shapeBndry.setZMax(zPosAtom);
         shapeBndry.setXMin(xPosAtom);
         shapeBndry.setYMin(yPosAtom);
         shapeBndry.setZMin(zPosAtom);
      }

      if(xPosAtom >= xMax)
      {
         shapeBndry.setXMax(xPosAtom);
      }
      if(yPosAtom >= yMax)
      {
         shapeBndry.setYMax(yPosAtom);
      }
      if(zPosAtom >= zMax)
      {
         shapeBndry.setZMax(zPosAtom);
      }
      if(xPosAtom <= xMin)
      {
         shapeBndry.setXMin(xPosAtom);
      }
      if(yPosAtom <= yMin)
      {
         shapeBndry.setYMin(yPosAtom);
      }
      if(zPosAtom <= zMin)
      {
         shapeBndry.setZMin(zPosAtom);
      }   

      xMax = (float)shapeBndry.getXMax();
      yMax = (float)shapeBndry.getYMax();
      zMax = (float)shapeBndry.getZMax();
      xMin = (float)shapeBndry.getXMin();
      yMin = (float)shapeBndry.getYMin();
      zMin = (float)shapeBndry.getZMin();

      length = xMax - xMin;
      width  = yMax - yMin;
      height = zMax - zMin;

      //thePolarizationVolume = (length+2*Atom.getBohrRadius())*(width+2*Atom.getBohrRadius())*(height+2*Atom.getBohrRadius());
      float aVolume = (length)*(width)*(height);
      thePolarizationVolume = Cnvrsn.angstromToMeter(aVolume);

      System.out.println("Polarization.updatePolarizationBoxVolume() : length = "+length+" width = "+width+" height = "+height+" volume = "+thePolarizationVolume );
      System.out.println("Polarization.updatePolarizationBoxVolume() : xMax = "+xMax+" yMax = "+yMax+" zMax = "+zMax);
      System.out.println("Polarization.updatePolarizationBoxVolume() : xMin = "+xMin+" yMin = "+yMin+" zMin = "+zMin);
      
      return thePolarizationVolume;
   }
   private void calculatePolarizationCylindricalVolume(Atom anAtom)
   {
      float length = 0;
      float width  = 0;
      float height = 0;

      float xMaxAtom = anAtom.getX1();
      float yMaxAtom = anAtom.getY1();
      float zMaxAtom = anAtom.getZ1();
      float xMinAtom = anAtom.getX1();
      float yMinAtom = anAtom.getY1();
      float zMinAtom = anAtom.getZ1();

      float xMax = (float)shapeBndry.getXMax();
      float yMax = (float)shapeBndry.getYMax();
      float zMax = (float)shapeBndry.getZMax();
      float xMin = (float)shapeBndry.getXMin();
      float yMin = (float)shapeBndry.getYMin();
      float zMin = (float)shapeBndry.getZMin();

      if(xMaxAtom >= xMax)
      {
         shapeBndry.setXMax(xMaxAtom);
      }
      if(yMaxAtom >= yMax)
      {
         shapeBndry.setYMax(yMaxAtom);
      }
      if(zMaxAtom >= zMax)
      {
         shapeBndry.setZMax(zMaxAtom);
      }
      if(xMaxAtom <= xMax)
      {
         shapeBndry.setXMin(xMinAtom);
      }
      if(yMinAtom >= yMin)
      {
         shapeBndry.setYMin(yMinAtom);
      }
      if(zMinAtom >= zMin)
      {
         shapeBndry.setZMin(zMinAtom);
      }   
      length = xMax - xMin;
      width  = yMax - yMin;
      height = zMax - zMin;

      thePolarizationVolume = length*width*height;
   }
   private void calculatePolarizationRadiusVolume(Atom anAtom)
   {
      float length = 0;
      float width  = 0;
      float height = 0;

      float xMaxAtom = anAtom.getX1();
      float yMaxAtom = anAtom.getY1();
      float zMaxAtom = anAtom.getZ1();
      float xMinAtom = anAtom.getX1();
      float yMinAtom = anAtom.getY1();
      float zMinAtom = anAtom.getZ1();

      float xMax = (float)shapeBndry.getXMax();
      float yMax = (float)shapeBndry.getYMax();
      float zMax = (float)shapeBndry.getZMax();
      float xMin = (float)shapeBndry.getXMin();
      float yMin = (float)shapeBndry.getYMin();
      float zMin = (float)shapeBndry.getZMin();

      if(xMaxAtom >= xMax)
      {
         shapeBndry.setXMax(xMaxAtom);
      }
      if(yMaxAtom >= yMax)
      {
         shapeBndry.setYMax(yMaxAtom);
      }
      if(zMaxAtom >= zMax)
      {
         shapeBndry.setZMax(zMaxAtom);
      }
      if(xMaxAtom <= xMax)
      {
         shapeBndry.setXMin(xMinAtom);
      }
      if(yMinAtom >= yMin)
      {
         shapeBndry.setYMin(yMinAtom);
      }
      if(zMinAtom >= zMin)
      {
         shapeBndry.setZMin(zMinAtom);
      }   
      length = xMax - xMin;
      width  = yMax - yMin;
      height = zMax - zMin;

      thePolarizationVolume = length*width*height;
   }
   public LineVector getPolarizationVector()
   {
      return polarizationVector;
   }
}