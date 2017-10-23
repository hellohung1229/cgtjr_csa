package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.units.Cnvrsn;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

public class Mol2File extends MoleculeFile{

    private static MoleculeModel thePDBMolecule;
    private static URL URLBase;
    private boolean connectStatus = false;
    private String fileName;
    private MoleculeModel thePDBResidueMolecule;
    private String currentResidue = null;
    private String recordSection;

    public Mol2File() {
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
                    || (myFileName != null && myFileName.toLowerCase().startsWith("jar:http:"))                                        
                    || (myFileName != null && myFileName.toLowerCase().startsWith("ftp:"))
                    || (myFileName != null && myFileName.toLowerCase().startsWith("file:"))
                    ) {
                aURL = new URL(myFileName);
                anInputStream = aURL.openStream();
            } else if (URLBase != null) {
                aURL = new URL(URLBase, myFileName);
                anInputStream = aURL.openStream();
                //System.out.println("url = "+URLBase);
            } else {
                //FileInputStream aFileInputStream = new FileInputStream(myFileName);
                anInputStream = new FileInputStream(myFileName);
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
            System.out.println("Mol2File: message : " + e.getMessage());
            System.out.println("Mol2File: stacktrace : " + e.getStackTrace());
        }
        return thePDBMolecule;
    }
    public void parseConnectRecord(String myLine) {
        System.out.println("Mol2File.parseConnectRecord() = " + myLine);
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
            System.out.println("Mol2File.parseConnectRecord() recordNameStr = " + recordNameStr);
        }
        if (aStringTokenizer.hasMoreTokens()) {
            atomNumberStr = aStringTokenizer.nextToken();
            atomNumber = Cnvrsn.getIntValue(atomNumberStr);
            int index = atomNumber - 1;
            aPDBAtom = (PDBAtom) thePDBResidueMolecule.getVertexByIndex(index);
            System.out.println("Mol2File.parseConnectRecord() atomNumberStr = " + aPDBAtom + "index = " + index);
        }
        if (aPDBAtom == null) {
            return;
        }
        if (aStringTokenizer.hasMoreTokens()) {
            atomConnectNumberStr = aStringTokenizer.nextToken();
            System.out.println("Mol2File.parseConnectRecord() - test 0 ");
            atomNumber = Cnvrsn.getIntValue(atomConnectNumberStr);
            int index = atomNumber - 1;
            System.out.println("Mol2File.parseConnectRecord() - test 1 index = " + index);
            anotherPDBAtom = (PDBAtom) thePDBResidueMolecule.getVertexByIndex(index);
            System.out.println("Mol2File.parseConnectRecord() - test 2 index = " + index);
            if (anotherPDBAtom != null) {
                aPDBAtom.connectVertices(anotherPDBAtom);
            }
            System.out.println("aPDBAtom = " + aPDBAtom + "anotherPDBAtom = " + anotherPDBAtom);
        }
    }
    public void parseAtomLineByRecord(String myLine) {
        PDBAtom aPDBAtom = null;

        System.out.println("Mol2File.parseAtomLineByRecord(): line = " + myLine + " length = " + myLine.length());
        StringTokenizer aStringTokenizer = new StringTokenizer(myLine);        

        String tempStr = "";
        String serial = "";
        String atom = "";
        String xCoord = "";
        String yCoord = "";
        String zCoord = "";
        String atomType = "";        
        String substId = "";             
        String substName = "";             
        String charge = "";            
        
        if (aStringTokenizer.hasMoreTokens()) {
            serial = aStringTokenizer.nextToken();
        }
        if (aStringTokenizer.hasMoreTokens()) {
            atom = aStringTokenizer.nextToken();
        }        
        if (aStringTokenizer.hasMoreTokens()) {
            xCoord = aStringTokenizer.nextToken();
        }
        if (aStringTokenizer.hasMoreTokens()) {
            yCoord = aStringTokenizer.nextToken();
        }   
        if (aStringTokenizer.hasMoreTokens()) {
            zCoord = aStringTokenizer.nextToken();
        }           
        if (aStringTokenizer.hasMoreTokens()) {
            atomType = aStringTokenizer.nextToken();
        }    
        if (aStringTokenizer.hasMoreTokens()) {
            substId = aStringTokenizer.nextToken();
        }           
        if (aStringTokenizer.hasMoreTokens()) {
            substName = aStringTokenizer.nextToken();
        }                 
        if (aStringTokenizer.hasMoreTokens()) {
            charge = aStringTokenizer.nextToken();
        }                         
        //String recName = myLine.substring(0, 6);
        //String serial = myLine.substring(0,2);
        //String atom = myLine.substring(3, 3);
        //String altLoc = myLine.substring();
        //String resName = myLine.substring(17, 20);
        //String seqNo = myLine.substring(22, 27);
        //String xCoord = myLine.substring(11,21);
        //String yCoord = myLine.substring(22,31);
        //String zCoord = myLine.substring(33,41);
        //String occupancy = null;
        

        System.out.println("Mol2File.parseAtomByLine() - atom = " + atom);

        aPDBAtom = PDBAtom.generateAtom(rtrvAtomName(atom));
        //aPDBAtom.setRecordField(recName);
        aPDBAtom.setAtomNumber(serial);
        aPDBAtom.setAtomType(atomType);
        aPDBAtom.setResidueName(substName);
        aPDBAtom.setResidueChain(substId);
        aPDBAtom.setXCoordinate(xCoord);
        aPDBAtom.setYCoordinate(yCoord);
        aPDBAtom.setZCoordinate(zCoord);
        //aPDBAtom.setColumn9(occupancy);
        aPDBAtom.setCharge(charge);        

        thePDBResidueMolecule.connectVertices(aPDBAtom);
    }
    public String rtrvAtomName(String myAtom)
    {
        boolean isLetter = false;     
        char lttr2 = ' ';    
        String atomName = "";
        String lttr1 = ""+myAtom.charAt(0);
        if(myAtom.length() >=2){
           lttr2 = myAtom.charAt(1);        
           isLetter = Character.isLetter(lttr2);
        }
        if(isLetter == true){
            atomName = lttr1+lttr2;
        }else{
            atomName = lttr1;
        }
        return atomName;        
    }
    /*
    public static MoleculeModel getPDBMolecule() {
        return thePDBMolecule;
    }*/
    public void processRecord(String myLine) {
        System.out.println("Mol2File.processRecord(): " + myLine);

        if (myLine.toLowerCase().indexOf("atom")>0) {
            recordSection = "atom";
            String aResidue = "unk";
            if (currentResidue == null) {
                currentResidue = aResidue;
                thePDBResidueMolecule = new MoleculeModel();
                thePDBResidueMolecule.setName(currentResidue);
                thePDBMolecule.addVertex(thePDBResidueMolecule);
                System.out.println("PDBMolecule.processRecord():current residue = " + currentResidue);
            }
        }else if (myLine.toLowerCase().indexOf("bond")>0) {
            recordSection = "bond";
        }else if (myLine.toLowerCase().indexOf("<tripos>")>0) {
            recordSection = "";
        } else if(recordSection != null && recordSection.equals("atom"))
        {
            parseAtomLineByRecord(myLine);         
        }else if(recordSection != null && recordSection.equals("bond")){
            //setConnectStatus(true);            
            //parseConnectRecord(myLine);             
        }
    }
    public static void main(String args[]) {
        String fileName = "c:\\cthomas\\usda\\pdbfiles\\Alpha.pdb";
        Mol2File aMol2File = new Mol2File();
        aMol2File.readFile(fileName);
    }
}