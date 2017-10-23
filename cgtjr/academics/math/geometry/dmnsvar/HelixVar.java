package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.ApplctnVar;

/**
 * The helix variable (HelixVar) class extends the functionality of the DmsnVar class. It
 * provides constructors that initialize the class with the corresponding
 * coordinate system parameters.
 *
 * @author clayton g thomas jr
 */
public class HelixVar extends DmnsnVar implements ApplctnVar {

    private static DmnsnVar dmnsnVar;
    private final static String hlxCrdntTp = "helix";
    private static String pitchKey = "pitch";
    private double pitchVal;

    /**
     * Instantiates a helix variable class with default values: (0, 0, 0, 0, 0,
     * 0, 4, 2 * Math.PI, 1, 0.5f, Math.PI / 8, 0.5f) The default pitch is 5.4.
     */
    public HelixVar() {
        super(0, 0, 0, 1.5f, 0, 0, 4, 2 * Math.PI, 1.0, 0.5f, Math.PI / 8, 0.5f,1.5f,0.0f,0.0f,hlxCrdntTp);
        //pitchVal = 5.4;
        //insrtData(pitchKey, "" + pitchVal);
        setCrdntTpVal(hlxCrdntTp);
    }
    public HelixVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {    

        super(myOffSet1,myOffSet2,myOffSet3,myMinDmnsn1,myMinDmnsn2,myMinDmnsn3,myDmnsn1,myDmnsn2,myDmnsn3,myDelta1,myDelta2,myDelta3);
        //pitchVal = 5.4;
        //insrtData(pitchKey, "" + pitchVal);
        setCrdntTpVal(hlxCrdntTp);
    }    
    /**
     * get pitch key associated wit the helix.
     *
     * @return string representation of key
     */
    public String getPitchKey() {
        return pitchKey;
    }

    /**
     * set pitch value associated with the helix.
     *
     * @param myPitchVal double representation of value.
     */
    public void setPitchVal(double myPitchVal) {
        pitchVal = myPitchVal;
    }
    /**
     * get pitch value of the helix.
     *
     * @return double representation of value.
     */
    public double getPitchVal() {
        return pitchVal;
    }
    /**
     * set dimension variables corresponding to the helical structure.
     *
     * @param myDmnsnVar represents a dimension variable.
     */
    public static void setDmnsnVar(DmnsnVar myDmnsnVar) {
        dmnsnVar = myDmnsnVar;
    }

    /**
     * get dimension variables associated with the helical mesh.
     *
     * @return DmnsnVar class representation.
     */
    public static DmnsnVar getDmnsnVar() {
        if (dmnsnVar == null) {
            HelixVar aHelixVar = new HelixVar();
        }
        return dmnsnVar;
    }
    /**
     * compute center of helical mesh in helical (cylindrical) coordinates (initialized coordinates).
     *
     * @return double[] representation of x,y,z coordinates.
     */
    public double[] cmptCenter() {
        double aLength = (getMaxDmnsn2Val() - getMinDmnsn2Val()) / 2;
        double aCenter[] = {0, 0, aLength};
        return aCenter;
    }
}