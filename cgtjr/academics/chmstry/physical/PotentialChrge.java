package cgtjr.academics.chmstry.physical;

import cgtjr.academics.math.geometry.linepnts.LineVector;
import cgtjr.academics.math.geometry.dmnsvar.CrdntTrnsfrm;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.general.*;

import java.util.*;
import cgtjr.academics.math.geometry.*;

//TODO code cleanup
public class PotentialChrge //extends ShapeVector
{

    private static LineVector potentialFieldVector;
    private GeneralCanvas theGeneralCanvas;
    private ShapePnt aShape1;
    private DipoleMoment aDipoleMoment;
    private String crdntType;
    private CrdntTrnsfrm aCrdntTrsfrm;

    public PotentialChrge() {
        potentialFieldVector = new LineVector();
        aShape1 = new ShapePnt();
        aDipoleMoment = new DipoleMoment();
    }
    public PotentialChrge(ShapePnt myShapePnt) {
        aCrdntTrsfrm = new CrdntTrnsfrm(myShapePnt);

        potentialFieldVector = new LineVector();
        aShape1 = new ShapePnt();
        aDipoleMoment = new DipoleMoment();
    }
    public PotentialChrge(GeneralCanvas myGeneralCanvas) {
        //theGeneralCanvas = myGeneralCanvas;
        aShape1 = new ShapePnt();
        aDipoleMoment = new DipoleMoment();
    }

    public void setCrdntType(String myCrdntType) {
        crdntType = myCrdntType;
    }

    public static LineVector retrievePotentialFieldVector() {
        return potentialFieldVector;
    }
    public float potentialDiffValue(MoleculeModel myPDBMolecule, Pnt myPoint) {
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

        while (anEnumeration1.hasMoreElements()) {
            aPDBMolecule2 = (MoleculeModel) anEnumeration1.nextElement();
            anEnumeration2 = aPDBMolecule2.rtrvEnumeration();
            while (anEnumeration2.hasMoreElements()) {
                aPDBAtom1 = (PDBAtom) anEnumeration2.nextElement();
                floatValue1 += potentialDiffValue(aPDBAtom1, myPoint);

            }
        }
        return floatValue1;
    }

    public float potentialDiffValue(Atom myAtom1, Pnt myPoint) {
        float pi = (float) Math.PI;
        float perm = 1 / (36 * ((float) Math.PI)) * ((float) Math.pow(10, -19));

        float ptX = myPoint.getX1();
        float ptY = myPoint.getY1();
        float ptZ = myPoint.getZ1();

        float obsPtX = aCrdntTrsfrm.rtrvC1(myPoint);
        float obsPtY = aCrdntTrsfrm.rtrvC2(myPoint);
        float obsPtZ = aCrdntTrsfrm.rtrvC3(myPoint);

        float atm1X = myAtom1.getX1();
        float atm1Y = myAtom1.getY1();
        float atm1Z = myAtom1.getZ1();

        float atm1ObsMag1 = (float) Math.sqrt((obsPtX - atm1X) * (obsPtX - atm1X) + (obsPtY - atm1Y) * (obsPtY - atm1Y) + (obsPtZ - atm1Z) * (obsPtZ - atm1Z));
        float aCharge = myAtom1.getCharge();

        float potentialField = aCharge / atm1ObsMag1;

        return potentialField;
    }
}