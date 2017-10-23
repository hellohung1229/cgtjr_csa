package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.PDBAtom;
import cgtjr.academics.chmstry.general.atoms.MoleculeFile;
import cgtjr.academics.chmstry.general.atoms.MoleculeModel;
import cgtjr.academics.chmstry.general.units.Cnvrsn;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

public class PDBMoleculeFile extends MoleculeFile{

    private static MoleculeModel thePDBMolecule;
    private static URL URLBase;
    private boolean connectStatus = false;
    private String fileName;
    private MoleculeModel thePDBResidueMolecule;
    private String currentResidue = null;

    public PDBMoleculeFile() {
    }

    public static void setURLBase(URL myURL) {
        URLBase = myURL;
    }

    public boolean getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(boolean myConnectStatus) {
        connectStatus = myConnectStatus;
    }

    public MoleculeModel readFile(String myFileName) {
        InputStream anInputStream = null;
        URL aURL = null;
        MoleculeModel.resetPeptideInfo();

        thePDBMolecule = new MoleculeModel();
        thePDBResidueMolecule = new MoleculeModel();
        super.setMoleculeModel(thePDBMolecule);
        Vector aVector = new Vector();
        try {
            if ((myFileName != null && myFileName.toLowerCase().startsWith("http:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("https:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("file:"))
                    ) {
                aURL = new URL(myFileName);
                anInputStream = aURL.openStream();
                System.out.println("PDBMoleculeFile: test 28");
            } else if (URLBase != null) {
                aURL = new URL(URLBase, myFileName);
                anInputStream = aURL.openStream();
                System.out.println("PDBMoleculeFile: test 34");
                //System.out.println("url = "+URLBase);
            } else {
                //FileInputStream aFileInputStream = new FileInputStream(myFileName);
                anInputStream = new FileInputStream(myFileName);
                System.out.println("PDBMoleculeFile: test 38");                
            }
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(anInputStream));
            String aLine = reader.readLine();
            while (aLine != null) {
                processRecord(aLine);
                aLine = reader.readLine();
            }
            anInputStream.close();
        } catch (Exception e) {
            System.out.println("PDBMoleculeFile: message : " + e.getMessage());
            System.out.println("PDBMoleculeFile: stacktrace : " + e.getStackTrace());
        }
        return thePDBMolecule;
    }

    public void parseConnectRecord(String myLine) {
        System.out.println("PDBMoleculeFile.parseConnectRecord() = " + myLine);
        PDBAtom aPDBAtom = null;
        PDBAtom anotherPDBAtom = null;

        int atomNumber = 0;
        int atomConnectNumber = 0;

        String atomNumberStr = null;
        String atomConnectNumberStr = null;
        String recordNameStr = null;

        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);

        if (aStringTokenizer.hasMoreTokens()) {
            recordNameStr = aStringTokenizer.nextToken();
            System.out.println("PDBMoleculeFile.parseConnectRecord() recordNameStr = " + recordNameStr);
        }
        if (aStringTokenizer.hasMoreTokens()) {
            atomNumberStr = aStringTokenizer.nextToken();
            atomNumber = Cnvrsn.getIntValue(atomNumberStr);
            int index = atomNumber - 1;
            aPDBAtom = (PDBAtom) thePDBResidueMolecule.getVertexByIndex(index);
            System.out.println("PDBMoleculeFile.parseConnectRecord() atomNumberStr = " + aPDBAtom + "index = " + index);
        }
        if (aPDBAtom == null) {
            return;
        }
        while (aStringTokenizer.hasMoreTokens()) {
            atomConnectNumberStr = aStringTokenizer.nextToken();
            System.out.println("PDBMoleculeFile.parseConnectRecord() - test 0 ");
            atomNumber = Cnvrsn.getIntValue(atomConnectNumberStr);
            int index = atomNumber - 1;
            System.out.println("PDBMoleculeFile.parseConnectRecord() - test 1 index = " + index);
            anotherPDBAtom = (PDBAtom) thePDBResidueMolecule.getVertexByIndex(index);
            System.out.println("PDBMoleculeFile.parseConnectRecord() - test 2 index = " + index);
            if (anotherPDBAtom != null) {
                aPDBAtom.connectVertices(anotherPDBAtom);
            }
            System.out.println("aPDBAtom = " + aPDBAtom + "anotherPDBAtom = " + anotherPDBAtom);
        }
    }

    public void parseAtomLineByRecord(String myLine) {
        PDBAtom aPDBAtom = null;

        System.out.println("PDBMoleculeFile.parseAtomLineByRecord(): line = " + myLine + " length = " + myLine.length());
        String tempStr = "";

        String recName = myLine.substring(0, 6);
        String serial = myLine.substring(6, 11);
        String atom = myLine.substring(12, 16);
        String altLoc = myLine.substring(16, 17);
        String resName = myLine.substring(17, 20);
        String seqNo = myLine.substring(22, 27);
        String xCoord = myLine.substring(30, 38);
        String yCoord = myLine.substring(38, 46);
        String zCoord = myLine.substring(46, 54);
        String occupancy = null;
        if (myLine.length() >= 60) {
            occupancy = myLine.substring(54, 60);
        }
        String tempFactor = null;
        if (myLine.length() >= 66) {
            tempFactor = myLine.substring(60, 66);
        }

        System.out.println("PDBMoleculeFile.parseAtomByLine() - atom = " + atom);

        aPDBAtom = PDBAtom.generateAtom(atom);
        aPDBAtom.setRecordField(recName);
        aPDBAtom.setAtomNumber(serial);
        aPDBAtom.setAtomType(atom);
        aPDBAtom.setResidueName(resName);
        aPDBAtom.setResidueChain(seqNo);
        aPDBAtom.setXCoordinate(xCoord);
        aPDBAtom.setYCoordinate(yCoord);
        aPDBAtom.setZCoordinate(zCoord);
        aPDBAtom.setColumn9(occupancy);

        thePDBResidueMolecule.connectVertices(aPDBAtom);
    }
    /*
    public static MoleculeModel getPDBMolecule() {
        return thePDBMolecule;
    }*/

    public void processRecord(String myLine) {
        System.out.println("PDBMoleculeFile.processRecord(): " + myLine);

        if (myLine.toLowerCase().startsWith("hetatm")) {

            String aResidue = myLine.substring(17, 20);
            if (currentResidue == null) {
                currentResidue = aResidue;
                thePDBResidueMolecule = new MoleculeModel();
                thePDBResidueMolecule.setName(currentResidue);

                thePDBMolecule.addVertex(thePDBResidueMolecule);
                System.out.println("PDBMolecule.processRecord():current residue = " + currentResidue);
            }

            parseAtomLineByRecord(myLine);
        } else if (myLine.toLowerCase().startsWith("atom")) {
            String aResidue = myLine.substring(17, 20);
            if (currentResidue == null || !currentResidue.equals(aResidue)) {
                currentResidue = aResidue;
                thePDBResidueMolecule = new MoleculeModel();
                thePDBResidueMolecule.setName(currentResidue);

                thePDBMolecule.addVertex(thePDBResidueMolecule);
                //System.out.println("PDBMolecule.processRecord():current residue = "+currentResidue);
            }
            parseAtomLineByRecord(myLine);
        } else if (myLine.toLowerCase().startsWith("conect")) {
            //setConnectStatus(true);
            //parseConnectRecord(myLine);
        }
    }

    public static void main(String args[]) {
        String fileName = "c:\\cthomas\\usda\\pdbfiles\\Alpha.pdb";
        PDBMoleculeFile aPDBMoleculeFile = new PDBMoleculeFile();
        aPDBMoleculeFile.readFile(fileName);
    }
}