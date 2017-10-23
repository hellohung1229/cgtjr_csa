package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.linepnts.LineVector;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.general.*;

import java.util.*;
import cgtjr.academics.math.geometry.*;


//TODO code cleanup
public class PotentialDiff //extends ShapeVector
{
   private static LineVector potentialFieldVector;
   private GeneralCanvas theGeneralCanvas;
   private ShapePnt aShape1;
   private DipoleMoment aDipoleMoment;
   private String crdntType;

   public PotentialDiff()
   {
      potentialFieldVector = new LineVector();
      aShape1 = new ShapePnt();
      aDipoleMoment = new DipoleMoment();
   }
   public PotentialDiff(GeneralCanvas myGeneralCanvas) 
   {
      //theGeneralCanvas = myGeneralCanvas;
      aShape1 = new ShapePnt();  
      aDipoleMoment = new DipoleMoment();
   }
   public void setCrdntType(String myCrdntType)
   {
       crdntType = myCrdntType;
   }
   public static LineVector retrievePotentialFieldVector()
   {
      return potentialFieldVector;
   }
   /*
   public float potentialDiffValue(PDBMolecule myPDBMolecule)
   {
      PDBMolecule aPDBMolecule2 = null;
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;

      //Shape aShape2 = aShape1.calculateBoundaries(myPDBMolecule);
      //Point axisPoint = aShape2.longAxisPoint();
      //System.out.println("PotentialDiff.potentialFieldVector(): axisPoint = "+axisPoint);
      float floatValue = 0.0f;
    
      Enumeration anEnumeration1 = myPDBMolecule.retrieveEnumeration();
      Enumeration anEnumeration2 = null;
      Enumeration anEnumeration3 = null;

      while(anEnumeration1.hasMoreElements())
      {
         aPDBMolecule2 = (PDBMolecule)anEnumeration1.nextElement();
         anEnumeration2 = aPDBMolecule2.retrieveEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            aPDBAtom1 = (PDBAtom)anEnumeration2.nextElement();
            anEnumeration3 = aPDBAtom1.retrieveEnumeration();
            while(anEnumeration3.hasMoreElements())
            {
               aPDBAtom2 = (PDBAtom)anEnumeration3.nextElement();
               floatValue += potentialDiffValue(aPDBAtom1,aPDBAtom2,axisPoint);
               //aLineVector2.addVector(aLineVector1);
            }
         }
      }
      System.out.println("PotentialDiff: value = "+floatValue);
      return floatValue;
   }*/
   /*
   public float potentialDiffValue2(PDBMolecule myPDBMolecule)
   {
      PDBMolecule aPDBMolecule2 = null;
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      //Shape aShape1 = new Shape();
      Shape aShape2 = aShape1.calculateBoundaries(myPDBMolecule);
      Line axisLine = aShape2.longAxisLine();
      //System.out.println("PotentialDiff.potentialFieldVector(): axisLine = "+axisLine);
      float floatValue1 = 0.0f;
      float floatValue2 = 0.0f;
      float floatValue = 0.0f;
    
      Enumeration anEnumeration1 = myPDBMolecule.retrieveEnumeration();
      Enumeration anEnumeration2 = null;
      Enumeration anEnumeration3 = null;

      while(anEnumeration1.hasMoreElements())
      {
         aPDBMolecule2 = (PDBMolecule)anEnumeration1.nextElement();
         anEnumeration2 = aPDBMolecule2.retrieveEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            aPDBAtom1 = (PDBAtom)anEnumeration2.nextElement();
            anEnumeration3 = aPDBAtom1.retrieveEnumeration();
            while(anEnumeration3.hasMoreElements())
            {
               aPDBAtom2 = (PDBAtom)anEnumeration3.nextElement();
               floatValue1 += potentialDiffValue(aPDBAtom1,aPDBAtom2,axisLine);
               floatValue2 += potentialDiffValue(aPDBAtom1,aPDBAtom2,axisLine.getPoint2());
               floatValue = floatValue1-floatValue2;
               //aLineVector2.addVector(aLineVector1);
            }
         }
      }
      return floatValue;
   }*/
   public float potentialDiffValue(MoleculeModel myPDBMolecule,Pnt myPoint)
   {
      MoleculeModel aPDBMolecule2 = null;
      PDBAtom aPDBAtom1 = null;
      PDBAtom aPDBAtom2 = null;
      //Shape aShape2 = aShape1.calculateBoundaries(myPDBMolecule);
      //Line axisLine = aShape2.longAxisLine();
      //System.out.println("PotentialDiff.potentialFieldVector(): axisLine = "+axisLine);
      float floatValue1 = 0.0f;
      float floatValue2 = 0.0f;
      float floatValue = 0.0f;
    
      Enumeration anEnumeration1 = myPDBMolecule.rtrvEnumeration();
      Enumeration anEnumeration2 = null;
      Enumeration anEnumeration3 = null;

      while(anEnumeration1.hasMoreElements())
      {
         aPDBMolecule2 = (MoleculeModel)anEnumeration1.nextElement();
         anEnumeration2 = aPDBMolecule2.rtrvEnumeration();
         while(anEnumeration2.hasMoreElements())
         {
            aPDBAtom1 = (PDBAtom)anEnumeration2.nextElement();
            anEnumeration3 = aPDBAtom1.rtrvEnumeration();
            while(anEnumeration3.hasMoreElements())
            {
               aPDBAtom2 = (PDBAtom)anEnumeration3.nextElement();
               floatValue1 += potentialDiffValue(aPDBAtom1,aPDBAtom2,myPoint);
               //floatValue2 += potentialDiffValue(aPDBAtom1,aPDBAtom2,axisLine.getPoint2());
               //floatValue = floatValue1-floatValue2;
               //aLineVector2.addVector(aLineVector1);
            }
         }
      }
      return floatValue1;
   }
   public float potentialDiffValue(Atom myAtom1,Atom myAtom2,Pnt myPoint)
   {
      float pi = (float)Math.PI;
      float perm = 1/(36*((float)Math.PI))*((float)Math.pow(10,-19));

      float ptX = myPoint.getX1();
      float ptY = myPoint.getY1();
      float ptZ = myPoint.getZ1();
      //System.out.println("PotentialDiff: crdntType = "+crdntType);
      float obsPtX = CrdntType.rtrvC1(crdntType, ptX, ptY, ptZ,0,0 );
      float obsPtY = CrdntType.rtrvC2(crdntType, ptX, ptY, ptZ,0,0 );
      float obsPtZ = CrdntType.rtrvC3(crdntType, ptX, ptY, ptZ,0,0 );

      float atm1X = myAtom1.getX1();
      float atm1Y = myAtom1.getY1();
      float atm1Z = myAtom1.getZ1();

      float atm2X = myAtom2.getX1();
      float atm2Y = myAtom2.getY1();
      float atm2Z = myAtom2.getZ1();

      float distance = (float)Math.sqrt(Math.pow(atm2X-atm1X,2) + Math.pow(atm2Y-atm1Y,2)+Math.pow(atm2Z-atm1Z,2));
      //float atm1atm2Mag = (float)Math.sqrt((obsPtX-(atm1X+atm2X)/2)*(obsPtX-(atm1X+atm2X)/2)+(obsPtY-(atm1Y+atm2Y)/2)*(obsPtY-(atm1Y+atm2Y)/2)+(obsPtZ-(atm1Z+atm2Z)/2)*(obsPtZ-(atm1Z+atm2Z)/2));
      float atm1atm2Mag1 = (float)Math.sqrt((obsPtX-atm1X)*(obsPtX-atm1X)+(obsPtY-atm1Y)*(obsPtY-atm1Y)+(obsPtZ-atm1Z)*(obsPtZ-atm1Z));
      float atm1atm2Mag2 = (float)Math.sqrt((obsPtX-atm2X)*(obsPtX-atm2X)+(obsPtY-atm2Y)*(obsPtY-atm2Y)+(obsPtZ-atm2Z)*(obsPtZ-atm2Z));

      //DipoleMoment aDipoleMoment = new DipoleMoment();
      float dipole = aDipoleMoment.dipoleValue(myAtom1,myAtom2);

      float potentialField1 = dipole/(distance*atm1atm2Mag1);
      float potentialField2 = -dipole/(distance*atm1atm2Mag2);
      float potentialField = potentialField1 + potentialField2;
      ////String atomSymbol1 = PDBMolecule.retrieveAtomSymbol(myAtom1.getName());
      ////String atomSymbol2 = PDBMolecule.retrieveAtomSymbol(myAtom2.getName());
      //System.out.println("PotentialDiff.potentialFieldVector() : atomSymbol1 = "+atomSymbol1);
      //System.out.println("PotentialDiff.potentialFieldVector() : atomSymbol2 = "+atomSymbol2);

      ////String aKey1 = atomSymbol1+"-"+atomSymbol2;
      ////String aKey2 = atomSymbol2+"-"+atomSymbol1;

      return potentialField;
   }
}