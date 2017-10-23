package cgtjr.academics.chmstry.general.atoms;

import cgtjr.academics.chmstry.general.atoms.NeodymiumAtom;
import cgtjr.academics.chmstry.general.atoms.MercuryAtom;
import cgtjr.academics.chmstry.general.atoms.MolybdenumAtom;
import cgtjr.academics.chmstry.general.atoms.MendeleviumAtom;
import cgtjr.academics.chmstry.general.atoms.ManganeseAtom;
import cgtjr.academics.chmstry.general.atoms.KryptonAtom;
import cgtjr.academics.chmstry.general.atoms.LawrenciumAtom;
import cgtjr.academics.chmstry.general.atoms.IndiumAtom;
import cgtjr.academics.chmstry.general.atoms.LutetiumAtom;
import cgtjr.academics.chmstry.general.atoms.LanthanumAtom;
import cgtjr.academics.chmstry.general.atoms.IronAtom;
import cgtjr.academics.chmstry.general.atoms.IridiumAtom;
import cgtjr.academics.chmstry.general.atoms.LithiumAtom;
import cgtjr.academics.chmstry.general.atoms.IodineAtom;
import cgtjr.academics.chmstry.general.atoms.MagnesiumAtom;
import cgtjr.academics.chmstry.general.atoms.LeadAtom;
import cgtjr.academics.chmstry.general.atoms.CuriumAtom;
import cgtjr.academics.chmstry.general.atoms.HolmiumAtom;
import cgtjr.academics.chmstry.general.atoms.HeliumAtom;
import cgtjr.academics.chmstry.general.atoms.EuropiumAtom;
import cgtjr.academics.chmstry.general.atoms.FranciumAtom;
import cgtjr.academics.chmstry.general.atoms.DysprosiumAtom;
import cgtjr.academics.chmstry.general.atoms.FermiumAtom;
import cgtjr.academics.chmstry.general.atoms.GalliumAtom;
import cgtjr.academics.chmstry.general.atoms.HafniumAtom;
import cgtjr.academics.chmstry.general.atoms.FlourineAtom;
import cgtjr.academics.chmstry.general.atoms.EinsteinniumAtom;
import cgtjr.academics.chmstry.general.atoms.GoldAtom;
import cgtjr.academics.chmstry.general.atoms.ErbiumAtom;
import cgtjr.academics.chmstry.general.atoms.ChromiumAtom;
import cgtjr.academics.chmstry.general.atoms.GadoliniumAtom;
import cgtjr.academics.chmstry.general.atoms.CobaltAtom;
import cgtjr.academics.chmstry.general.atoms.CopperAtom;
import cgtjr.academics.chmstry.general.atoms.HydrogenAtom;
import cgtjr.academics.chmstry.general.atoms.BerkeliumAtom;
import cgtjr.academics.chmstry.general.atoms.CalciumAtom;
import cgtjr.academics.chmstry.general.atoms.CadmiumAtom;
import cgtjr.academics.chmstry.general.atoms.CesiumAtom;
import cgtjr.academics.chmstry.general.atoms.BerylliumAtom;
import cgtjr.academics.chmstry.general.atoms.CaliforniumAtom;
import cgtjr.academics.chmstry.general.atoms.ChlorineAtom;
import cgtjr.academics.chmstry.general.atoms.BoronAtom;
import cgtjr.academics.chmstry.general.atoms.BismuthAtom;
import cgtjr.academics.chmstry.general.atoms.CarbonAtom;
import cgtjr.academics.chmstry.general.atoms.BariumAtom;
import cgtjr.academics.chmstry.general.atoms.CeriumAtom;
import cgtjr.academics.chmstry.general.atoms.BromineAtom;
import cgtjr.academics.chmstry.general.atoms.ArgonAtom;
import cgtjr.academics.chmstry.general.atoms.AluminumAtom;
import cgtjr.academics.chmstry.general.atoms.Atom;
import cgtjr.academics.chmstry.general.atoms.ArsenicAtom;
import cgtjr.academics.chmstry.general.atoms.AstatineAtom;
import cgtjr.academics.chmstry.general.atoms.AntimonyAtom;
import cgtjr.academics.chmstry.general.atoms.AmericiumAtom;
import cgtjr.academics.chmstry.general.atoms.ActiniumAtom;

public class PDBAtom extends Atom {

    private String recordField;
    private String atomNumber;
    private String atomType;
    private String residueName;
    private String residueChain;
    private String xCoordinate;
    private String yCoordinate;
    private String zCoordinate;
    private String column9;
    private String column10;
    private String charge;

    public PDBAtom() {
        initializeColor();
    }

    public void initializeColor() {
        setColor(0x4e3a2b);
    }

    public void setCharge(String myCharge) {
        charge = myCharge;
        try {
            float aCharge = Float.parseFloat(charge);
            super.setCharge(aCharge);
        } catch (java.lang.ArithmeticException ae) {
        }
        
    }

    public void setRecordField(String aRecordField) {
        recordField = aRecordField;
    }

    public String getRecordField() {
        return recordField;
    }

    public void setAtomNumber(String anAtomNumber) {
        atomNumber = anAtomNumber;
    }

    public void setAtomType(String anAtomType) {
        atomType = anAtomType;
        setName(anAtomType);
    }

    public void setResidueName(String myResidueName) {
        residueName = myResidueName;
    }

    public void setResidueChain(String myResidueChain) {
        residueChain = myResidueChain;
    }

    public void setColumn9(String myColumn9) {
        column9 = myColumn9;
    }

    public void setColumn10(String myColumn10) {
        column10 = myColumn10;
    }

    public void setXCoordinate(String myXCoordinate) {
        xCoordinate = myXCoordinate;
        setX1(Float.parseFloat(xCoordinate));
    }

    public void setYCoordinate(String myYCoordinate) {
        yCoordinate = myYCoordinate;
        setY1(Float.parseFloat(yCoordinate));
    }

    public void setZCoordinate(String myZCoordinate) {
        zCoordinate = myZCoordinate;
        setZ1(Float.parseFloat(zCoordinate));
    }

    public String getXCoordinate() {
        return xCoordinate;
    }

    public String getYCoordinate() {
        return yCoordinate;
    }

    public String getZCoordinate() {
        return zCoordinate;
    }

    public static PDBAtom generateAtom(String aPDBAtomStr) {
        char aChar1 = aPDBAtomStr.charAt(0);
        char aChar2 = ' ';
        if (aPDBAtomStr.length() >= 2) {
            aChar2 = aPDBAtomStr.charAt(1);
        }
        System.out.println("PDBAtom : char 1 = " + aChar1 + " char 2 = " + aChar2);
        String atomName = null;
        PDBAtom aPDBAtom = null;

        if (Character.isDigit(aChar1)) {
            atomName = "" + aChar2;
        } else if (Character.isLetter(aChar1) || Character.isLetter(aChar2)) {
            atomName = "" + aChar1 + "" + aChar2;
        }
        System.out.println("PDBAtom : generatingAtom() = " + atomName);

        if (atomName.trim().equals("C")) {
            aPDBAtom = new CarbonAtom();
        } else if (atomName.trim().equals("N")) {
            aPDBAtom = new NitrogenAtom();
        } else if (atomName.trim().equals("O")) {
            aPDBAtom = new OxygenAtom();
        } else if (atomName.trim().equals("H")) {
            aPDBAtom = new HydrogenAtom();
        } else if (atomName.trim().equals("S")) {
            aPDBAtom = new SulfurAtom();
        } else if (atomName.trim().equals("Al")) {
            aPDBAtom = new AluminumAtom();
        } else if (atomName.trim().equals("Am")) {
            aPDBAtom = new AmericiumAtom();
        } else if (atomName.trim().equals("Sb")) {
            aPDBAtom = new AntimonyAtom();
        } else if (atomName.trim().equals("Ar")) {
            aPDBAtom = new ArgonAtom();
        } else if (atomName.trim().equals("As")) {
            aPDBAtom = new ArsenicAtom();
        } else if (atomName.trim().equals("At")) {
            aPDBAtom = new AstatineAtom();
        } else if (atomName.trim().equals("Ac")) {
            aPDBAtom = new ActiniumAtom();
        } else if (atomName.trim().equals("Ba")) {
            aPDBAtom = new BariumAtom();
        } else if (atomName.trim().equals("Bk")) {
            aPDBAtom = new BerkeliumAtom();
        } else if (atomName.trim().equals("Be")) {
            aPDBAtom = new BerylliumAtom();
        } else if (atomName.trim().equals("Bi")) {
            aPDBAtom = new BismuthAtom();
        } else if (atomName.trim().equals("B")) {
            aPDBAtom = new BoronAtom();
        } else if (atomName.trim().equals("Br")) {
            aPDBAtom = new BromineAtom();
        } else if (atomName.trim().equals("Cd")) {
            aPDBAtom = new CadmiumAtom();
        } else if (atomName.trim().equals("Ca")) {
            aPDBAtom = new CalciumAtom();
        } else if (atomName.trim().equals("Ca")) {
            aPDBAtom = new CaliforniumAtom();
        } else if (atomName.trim().equals("Cf")) {
            aPDBAtom = new CarbonAtom();
        } else if (atomName.trim().equals("Ce")) {
            aPDBAtom = new CeriumAtom();
        } else if (atomName.trim().equals("Cs")) {
            aPDBAtom = new CesiumAtom();
        } else if (atomName.trim().equals("Cl")) {
            aPDBAtom = new ChlorineAtom();
        } else if (atomName.trim().equals("Cr")) {
            aPDBAtom = new ChromiumAtom();
        } else if (atomName.trim().equals("Co")) {
            aPDBAtom = new CobaltAtom();
        } else if (atomName.trim().equals("Cu")) {
            aPDBAtom = new CopperAtom();
        } else if (atomName.trim().equals("Cm")) {
            aPDBAtom = new CuriumAtom();
        } else if (atomName.trim().equals("Dy")) {
            aPDBAtom = new DysprosiumAtom();
        } else if (atomName.trim().equals("Es")) {
            aPDBAtom = new EinsteinniumAtom();
        } else if (atomName.trim().equals("Er")) {
            aPDBAtom = new ErbiumAtom();
        } else if (atomName.trim().equals("Eu")) {
            aPDBAtom = new EuropiumAtom();
        } else if (atomName.trim().equals("Fm")) {
            aPDBAtom = new FermiumAtom();
        } else if (atomName.trim().equals("F")) {
            aPDBAtom = new FlourineAtom();
        } else if (atomName.trim().equals("Fr")) {
            aPDBAtom = new FranciumAtom();
        } else if (atomName.trim().equals("Gd")) {
            aPDBAtom = new GadoliniumAtom();
        } else if (atomName.trim().equals("Ga")) {
            aPDBAtom = new GalliumAtom();
        } else if (atomName.trim().equals("Au")) {
            aPDBAtom = new GoldAtom();
        } else if (atomName.trim().equals("Hf")) {
            aPDBAtom = new HafniumAtom();
        } else if (atomName.trim().equals("Hfe")) {
            aPDBAtom = new HeliumAtom();
        } else if (atomName.trim().equals("Ho")) {
            aPDBAtom = new HolmiumAtom();
        } else if (atomName.trim().equals("H")) {
            aPDBAtom = new HydrogenAtom();
        } else if (atomName.trim().equals("In")) {
            aPDBAtom = new IndiumAtom();
        } else if (atomName.trim().equals("I")) {
            aPDBAtom = new IodineAtom();
        } else if (atomName.trim().equals("Ir")) {
            aPDBAtom = new IridiumAtom();
        } else if (atomName.trim().equals("Fe")) {
            aPDBAtom = new IronAtom();
        } else if (atomName.trim().equals("Kr")) {
            aPDBAtom = new KryptonAtom();
        } else if (atomName.trim().equals("La")) {
            aPDBAtom = new LanthanumAtom();
        } else if (atomName.trim().equals("Lr")) {
            aPDBAtom = new LawrenciumAtom();
        } else if (atomName.trim().equals("Pd")) {
            aPDBAtom = new LeadAtom();
        } else if (atomName.trim().equals("Li")) {
            aPDBAtom = new LithiumAtom();
        } else if (atomName.trim().equals("Lu")) {
            aPDBAtom = new LutetiumAtom();
        } else if (atomName.trim().equals("Mg")) {
            aPDBAtom = new MagnesiumAtom();
        } else if (atomName.trim().equals("Mn")) {
            aPDBAtom = new ManganeseAtom();
        } else if (atomName.trim().equals("Md")) {
            aPDBAtom = new MendeleviumAtom();
        } else if (atomName.trim().equals("Hg")) {
            aPDBAtom = new MercuryAtom();
        } else if (atomName.trim().equals("Mo")) {
            aPDBAtom = new MolybdenumAtom();
        } else if (atomName.trim().equals("Nd")) {
            aPDBAtom = new NeodymiumAtom();
        } else if (atomName.trim().equals("Ne")) {
            aPDBAtom = new NeonAtom();
        } else if (atomName.trim().equals("Np")) {
            aPDBAtom = new NeptuniumAtom();
        } else if (atomName.trim().equals("Ni")) {
            aPDBAtom = new NickelAtom();
        } else if (atomName.trim().equals("Nb")) {
            aPDBAtom = new NiobiumAtom();
        } else if (atomName.trim().equals("N")) {
            aPDBAtom = new NitrogenAtom();
        } else if (atomName.trim().equals("No")) {
            aPDBAtom = new NobeliumAtom();
        } else if (atomName.trim().equals("Os")) {
            aPDBAtom = new OsmiumAtom();
        } else if (atomName.trim().equals("O")) {
            aPDBAtom = new OxygenAtom();
        } else if (atomName.trim().equals("Pd")) {
            aPDBAtom = new PalladiumAtom();
        } else if (atomName.trim().equals("P")) {
            aPDBAtom = new PhosphorusAtom();
        } else if (atomName.trim().equals("Pt")) {
            aPDBAtom = new PlatinumAtom();
        } else if (atomName.trim().equals("Pu")) {
            aPDBAtom = new PlutoniumAtom();
        } else if (atomName.trim().equals("Po")) {
            aPDBAtom = new PoloniumAtom();
        } else if (atomName.trim().equals("K")) {
            aPDBAtom = new PotassiumAtom();
        } else if (atomName.trim().equals("Pr")) {
            aPDBAtom = new PraseodymiumAtom();
        } else if (atomName.trim().equals("Pm")) {
            aPDBAtom = new PromethiumAtom();
        } else if (atomName.trim().equals("Pa")) {
            aPDBAtom = new ProtactiniumAtom();
        } else if (atomName.trim().equals("Ra")) {
            aPDBAtom = new RadiumAtom();
        } else if (atomName.trim().equals("Rn")) {
            aPDBAtom = new RadonAtom();
        } else if (atomName.trim().equals("Re")) {
            aPDBAtom = new RheniumAtom();
        } else if (atomName.trim().equals("Rh")) {
            aPDBAtom = new RhodiumAtom();
        } else if (atomName.trim().equals("Rb")) {
            aPDBAtom = new RubidiumAtom();
        } else if (atomName.trim().equals("Ru")) {
            aPDBAtom = new RutheniumAtom();
        } else if (atomName.trim().equals("Sm")) {
            aPDBAtom = new SamariumAtom();
        } else if (atomName.trim().equals("Sc")) {
            aPDBAtom = new ScandiumAtom();
        } else if (atomName.trim().equals("Se")) {
            aPDBAtom = new SeleniumAtom();
        } else if (atomName.trim().equals("Si")) {
            aPDBAtom = new SiliconAtom();
        } else if (atomName.trim().equals("Ag")) {
            aPDBAtom = new SilverAtom();
        } else if (atomName.trim().equals("Na")) {
            aPDBAtom = new SodiumAtom();
        } else if (atomName.trim().equals("Sr")) {
            aPDBAtom = new StrontiumAtom();
        } else if (atomName.trim().equals("S")) {
            aPDBAtom = new SulfurAtom();
        } else if (atomName.trim().equals("Ta")) {
            aPDBAtom = new TantalumAtom();
        } else if (atomName.trim().equals("Tc")) {
            aPDBAtom = new TechnetiumAtom();
        } else if (atomName.trim().equals("Te")) {
            aPDBAtom = new TelluriumAtom();
        } else if (atomName.trim().equals("Tb")) {
            aPDBAtom = new TerbiumAtom();
        } else if (atomName.trim().equals("Tl")) {
            aPDBAtom = new ThalliumAtom();
        } else if (atomName.trim().equals("Th")) {
            aPDBAtom = new ThoriumAtom();
        } else if (atomName.trim().equals("Tm")) {
            aPDBAtom = new ThuliumAtom();
        } else if (atomName.trim().equals("Sn")) {
            aPDBAtom = new TinAtom();
        } else if (atomName.trim().equals("Ti")) {
            aPDBAtom = new TitaniumAtom();
        } else if (atomName.trim().equals("U")) {
            aPDBAtom = new UraniumAtom();
        } else if (atomName.trim().equals("V")) {
            aPDBAtom = new VanadiumAtom();
        } else if (atomName.trim().equals("W")) {
            aPDBAtom = new WolframAtom();
        } else if (atomName.trim().equals("Xe")) {
            aPDBAtom = new XenonAtom();
        } else if (atomName.trim().equals("Yb")) {
            aPDBAtom = new YtterbiumAtom();
        } else if (atomName.trim().equals("Y")) {
            aPDBAtom = new YttriumAtom();
        } else if (atomName.trim().equals("Zn")) {
            aPDBAtom = new ZincAtom();
        } else if (atomName.trim().equals("Zr")) {
            aPDBAtom = new ZirconiumAtom();
        } else {
            aPDBAtom = new PDBAtom();
        }
        return aPDBAtom;
    }
}