package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.chmstry.physical.*;


import java.util.*;

public class Molecule extends Atom
{
   private DipoleMoment moleculeDipoleMoment;
   private Polarization moleculePolarization;
   private ShapePnt surfaceShape;

   public Molecule()
   {
      moleculeDipoleMoment = new DipoleMoment();
      moleculePolarization = new Polarization();
      surfaceShape = new ShapePnt();
      setVisibility(false);
   }

   /*May need this later ...
   public void trvrsActn1(Vertex myVertex,int myActnNmbr)
   {
      FDMNode aFDMNode = new FDMNode();
      Point aPoint1 = (Point) myVertex;
      Shape aPoint2 = new Shape();;
      float aX1 = aPoint1.getX1();
      float aY1 = aPoint1.getY1();
      float aZ1 = aPoint1.getZ1();
      aPoint2.setEnvrnmntVar(aX1,aY1,aZ1);
      aPoint2.setDeltaX(1);
      aPoint2.setDeltaY((float)Math.PI/4);
      aPoint2.setDeltaZ((float)Math.PI/4);
      aPoint2.setBoundaryShape(new Boundary(new SphereTR(1,2*Math.PI,2*Math.PI)));
      Point aPoint3 = aPoint2.createInitCoordinates(0,0,0,aFDMNode);
      Point aPoint4 = aPoint2.createCoordinateMesh(aFDMNode); 
      surfaceShape.pointToVertex(aPoint4);
   }*/
   public ShapePnt retrieveSurface()
   {
      return surfaceShape;
   }
   /*
   public int traverse()
   {
      return traverse(1);
   }*/
   public void setX1(float myX1)
   {
      super.setX1(myX1);
      Vector aVector = getAdjcntVertices();    
      int aSize = aVector.size();
      //System.out.println("Molecule: aSize = "+aSize);  
      Pnt aPoint = null;
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
         aPoint.setX1(aPoint.getX1()+myX1);
      }
   }
   public void setY1(float myY1)
   {
      super.setY1(myY1);
      Vector aVector = getAdjcntVertices();      
      int aSize = aVector.size();
      //System.out.println("Molecule: aSize = "+aSize);
      Pnt aPoint = null;
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
         aPoint.setY1(aPoint.getY1()+myY1);
      }
   }
   public void setZ1(float myZ1)
   {
      super.setZ1(myZ1);
      Vector aVector = getAdjcntVertices();      
      int aSize = aVector.size();
      //System.out.println("Molecule: aSize = "+aSize);  
      Pnt aPoint = null;
      for(int i=0;i<aSize;i++)
      {
         aPoint = (Pnt)aVector.elementAt(i);
         aPoint.setZ1(aPoint.getZ1()+myZ1);
      }
   }
   /*May modify later
   public Point createDataPoint()
   {     
      //System.out.println("Molecule.createDataPoint()");
      MlclTrvrslCrtn aMlclTrvrslCrtn = new MlclTrvrslCrtn();
      Molecule aMolecule = aMlclTrvrslCrtn.createMlcl();
      return aMolecule;
   }
   public Point createPoint()
   {
      return new Molecule();
   }*/
   /*
   public Point createDataPoint()
   {
      Vector aVector = getAdjacentVertices();
      int aSize = aVector.size();
      Atom anAtom = null;
      for(int i=0;i<aSize;i++)
      {
         anAtom = aVector.elementAt(0);
      }
   }*/
}