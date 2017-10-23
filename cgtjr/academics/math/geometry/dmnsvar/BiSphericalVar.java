package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.general.ApplctnVar;

/**
 * 
 * @author clayton g thomas jr
 */
public class BiSphericalVar extends DmnsnVar implements ApplctnVar {

    private static DmnsnVar dmnsnVar;
    private static String dataNmKey = "bispherical";
    private final static String biSphericalCrdntTpKey = "bispherical";
    private static DmnsnVar defaultData;

    /**
     * 
     */
    public BiSphericalVar() {
        super(0, 0, 0, 0, 0, 0, Math.PI, Math.PI, Math.PI , Math.PI / 8, Math.PI / 8, Math.PI / 8, 0, 0, 0,biSphericalCrdntTpKey);
        setCrdntTpVal(biSphericalCrdntTpKey);
    }

    /**
     * 
     * @param myXOffSet
     * @param myYOffSet
     * @param myZOffSet
     * @param myMinX
     * @param myMinY
     * @param myMinZ
     * @param myDmnsn1
     * @param myDmnsn2
     * @param myDmnsn3
     * @param myDelta1
     * @param myDelta2
     * @param myDelta3
     */
    public BiSphericalVar(double myXOffSet, double myYOffSet, double myZOffSet, double myMinX, double myMinY, double myMinZ, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {
        super(myXOffSet, myYOffSet, myZOffSet, myMinX, myMinY, myMinZ, myDmnsn1, myDmnsn2, myDmnsn3, myDelta1, myDelta2, myDelta3);
        setCrdntTpVal(biSphericalCrdntTpKey);
    }

    /**
     * 
     * @param myXOffSet
     * @param myYOffSet
     * @param myZOffSet
     * @param myMinX
     * @param myMinY
     * @param myMinZ
     * @param myDmnsn1
     * @param myDmnsn2
     * @param myDmnsn3
     */
    public BiSphericalVar(double myXOffSet, double myYOffSet, double myZOffSet, double myMinX, double myMinY, double myMinZ, double myDmnsn1, double myDmnsn2, double myDmnsn3) {
        super(myXOffSet, myYOffSet, myZOffSet, myMinX, myMinY, myMinZ, myDmnsn1, myDmnsn2, myDmnsn3);
        setCrdntTpVal(biSphericalCrdntTpKey);
    }

    /**
     * 
     * @param myXOffSet
     * @param myYOffSet
     * @param myZOffSet
     * @param myMinX
     * @param myMinY
     * @param myMinZ
     * @param myDmnsn1
     * @param myDmnsn2
     * @param myDmnsn3
     * @param myDelta1
     * @param myDelta2
     * @param myDelta3
     * @param myInit1
     * @param myInit2
     * @param myInit3
     */
    public BiSphericalVar(double myXOffSet, double myYOffSet, double myZOffSet, double myMinX, double myMinY, double myMinZ, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3, double myInit1, double myInit2, double myInit3) {
        super(myXOffSet, myYOffSet, myZOffSet, myMinX, myMinY, myMinZ, myDmnsn1, myDmnsn2, myDmnsn3, myDelta1, myDelta2, myDelta3, myInit1, myInit2, myInit3);
        setCrdntTpVal(biSphericalCrdntTpKey);
    }

    /**
     * 
     * @param myDmnsnVar
     */
    public void setDefaultData(DmnsnVar myDmnsnVar) {
        defaultData = myDmnsnVar;
    }

    /**
     * 
     * @return
     */
    public DmnsnVar getDefaultData() {
        return defaultData;
    }

    /**
     * 
     * @param myDmnsnVar
     */
    public static void setDmnsnVar(DmnsnVar myDmnsnVar) {
        dmnsnVar = myDmnsnVar;
    }

    /**
     * 
     * @return
     */
    public static DmnsnVar getDmnsnVar() {
        if (dmnsnVar == null) {
            BiSphericalVar aSphrclVar = new BiSphericalVar();
            dmnsnVar = aSphrclVar;
        }
        return dmnsnVar;
    }
    public double[] cmptCenter() {
        double aCenter[] = {0, 0, 0};
        return aCenter;
    }
}