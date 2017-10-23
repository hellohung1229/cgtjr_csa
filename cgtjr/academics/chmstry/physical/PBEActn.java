package cgtjr.academics.chmstry.physical;


import java.awt.event.*;
import java.util.*;

import cgtjr.academics.math.geometry.*;
import cgtjr.academics.general.*;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.PDBMoleculeFile;

public class PBEActn //extends GeneralPnlActn implements ActionListener
{
   private GeneralCanvas theGeneralCanvas;
   private PntSampler pMapPntSampler;
   /*
   public PBEActn(GeneralCanvas myGeneralCanvas)
   {
      theGeneralCanvas = myGeneralCanvas;   
      pMapPntSampler = new PntSampler();   
   }
   public void actionPerformed(ActionEvent e) 
   {
      //performActn();
   }
   public void doAction(HashMap aHashMap)
   {
      NdTrvrslDspl aNdTrvrslDspl = new NdTrvrslDspl(theGeneralCanvas.retrieveTransformGroup());
      aNdTrvrslDspl.setCrdntType(Point.getCrtsnCrdntNm());

      PDBMolecule aPDBResMolecule = PDBMoleculeFile.getPDBMolecule();
      Enumeration myResEnumeration = aPDBResMolecule.retrieveEnumeration();
      PDBMolecule myPDBMolecule = (PDBMolecule)myResEnumeration.nextElement();

      BoxShape aBoxShape1 = new BoxShape();
      Vector aBoxShapeVector = aBoxShape1.retrieveBoxes(myPDBMolecule,1);
      BoxShape aBoxShape2 = (BoxShape) aBoxShapeVector.elementAt(0);

      float aX1 = 1.000f*aBoxShape2.getEnvironmentX();
      float aY1 = 1.000f*aBoxShape2.getEnvironmentY();
      float aZ1 = 1.000f*aBoxShape2.getEnvironmentZ();
      float aW1 = 1.000f*aBoxShape2.getWidth();
      float aH1 = 1.000f*aBoxShape2.getHeight();
      float aL1 = 1.000f*aBoxShape2.getLength();

      MeshSmplr aMeshSmplr = new MeshSmplr(theGeneralCanvas.retrieveTransformGroup());
      aMeshSmplr.setCrdntType(Point.getCrtsnCrdntNm());
      MlclBndrySmplr aMlclBndrySmplr = new MlclBndrySmplr(myPDBMolecule);
      MshBndrySmplr aMshBndrySmplr = new MshBndrySmplr(aW1,aH1,aL1,aX1,aY1,aZ1);
      MshSrfcSmplr aMshSrfcSmplr = new MshSrfcSmplr(myPDBMolecule);
      
      pMapPntSampler.insrtSampler(aMlclBndrySmplr);
      pMapPntSampler.insrtSampler(aMshBndrySmplr);
      pMapPntSampler.insrtSampler(aMshSrfcSmplr);

      VltgPnt aVltgPnt = new VltgPnt();

      Point aPoint = aBoxShape2.getMidPoint();

      aVltgPnt.setX1(aPoint.getX1());
      aVltgPnt.setY1(aPoint.getY1());
      aVltgPnt.setZ1(aPoint.getZ1());
      aVltgPnt.setDeltaX(.2000f);
      aVltgPnt.setDeltaY(.2000f);
      aVltgPnt.setDeltaZ(.2000f);

      aVltgPnt.setBoundaryShape(new Boundary(new BoxShape(aW1,aH1,aL1,aX1,aY1,aZ1)));

      Point aPoint1 = aVltgPnt.createInitCoordinates(0.00f,0.00f,0.00f);
      Point aPoint2 = aVltgPnt.createCoordinateMesh(pMapPntSampler);

      Point aMshSrfcPoint = aMshSrfcSmplr.getSrfcPoints();
      aVltgPnt.setNodeTrvrslActn(aNdTrvrslDspl);
      aVltgPnt.strtNodeTrvrsl();
   }*/
}