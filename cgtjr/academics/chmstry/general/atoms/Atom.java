package cgtjr.academics.chmstry.general.atoms;


import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.graph.*;
import cgtjr.academics.math.geometry.*;

public class Atom extends ShapePnt //PointPlace
{
   private float radius = .1f;
   private static final int totalNumberOfAtoms = 105;

   //private ElectronCloud electronCloud;
   private Vertex surfacePnts;

   private int atomicNumber = 0;
   private float electronegativity = 0;
   private float electronAffinity = 0;
   private float charge = 0;

   private String atomicSymbol = "";
   private String atomicName = "";
 
   public Atom()
   {
   }
   public float getCharge()
   {
      return charge;
   }
   public void setCharge(float myCharge)
   {
      charge = myCharge;
   }
   public int getTotalNumberOfAtoms()
   {
      return totalNumberOfAtoms;
   }
   public void setSurfacePnts(Vertex mySurfacePnts)
   {
      surfacePnts = mySurfacePnts;
   }
   public Vertex getSurfacePnts()
   {
      return surfacePnts;
   }
   public void setElectronegativity(float myElectronegativity)
   {
      electronegativity = myElectronegativity;
   }
   public float getElectronegativity()
   {
      return electronegativity;
   }
   public void setElectronAffinity(float myElectronAffinity)
   {
      electronAffinity = myElectronAffinity;
   }   
   public float getElectronAffinity()
   {
      return electronAffinity;
   }
   public void topologicalAction()
   {
   }
   public float getRadius()
   {
      return radius;
   }
   public void setAtomicNumber(int myAtomicNumber)
   {
      atomicNumber = myAtomicNumber;
   }
   public int getAtomicNumber()
   {
      return atomicNumber;
   }
   public void setAtomicName(String myAtomicName)
   {
      atomicName = myAtomicName;
   }
   public String getAtomicName()
   {
      return atomicName;
   }
   public void setAtomicSymbol(String myAtomicSymbol)
   {
      atomicSymbol = myAtomicSymbol;
   }
   public String getAtomicSymbol()
   {
      return atomicSymbol;
   }
   public float cmptRadius()
   {
      return 0;
   }
   public void setRadius(float myRadius)
   {
       radius = myRadius;
   }
   /*
   public void setElectronCloud(ElectronCloud myElectronCloud)
   {
      electronCloud = myElectronCloud;
   }
   public ElectronCloud getElectronCloud()
   {
      return electronCloud;
   }
   public void traverseActn2(int myTrvrsNmbr,int myActnNmbr)
   {
      
   }*/
   public Pnt createDataPoint()
   {
      System.out.println("Atom : createDataPoint()");
      return new OxygenAtom();
   }

}