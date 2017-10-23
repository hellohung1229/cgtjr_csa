package cgtjr.academics.chmstry.general.atoms;

public class CarbonAtom extends PDBAtom {

    private int atomicNumber = 6;
    private float electronegativity = 2.5500f;

    public CarbonAtom() {
        initializeColor();
    }

    public void initializeColor() {
        setColor(0x00232323);
    }

    public void setElectronegativity(float myElectronegativity) {
        electronegativity = myElectronegativity;
    }

    public float getElectronegativity() {
        return electronegativity;
    }

    public void topologicalAction() {
    }
}