package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.ApplctnVar;

/**
 * 
 * @author clayton g thomas jr
 */
public class ToroidalVar extends DmnsnVar implements ApplctnVar {

    private static DmnsnVar dmnsnVar;
    private static String dataNmKey = "toroidal";
    private final static String trdlCrdntTp = "toroidal";

    /**
     * 
     */
    public ToroidalVar() {
        super(0, 0, 0, Math.PI/8.0, 4, 0.0, 16*Math.PI/8.0, 4,  2.0*Math.PI, Math.PI /16,1.0, Math.PI /16,4*Math.PI/8.0,4,0,trdlCrdntTp);
        setCrdntTpVal(dataNmKey);
    }

    /**
     * 
     * @param myOffSet1
     * @param myOffSet2
     * @param myOffSet3
     * @param myMinDmnsn1
     * @param myMinDmnsn2
     * @param myMinDmnsn3
     * @param myDmnsn1
     * @param myDmnsn2
     * @param myDmnsn3
     * @param myDelta1
     * @param myDelta2
     * @param myDelta3
     */
    public ToroidalVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {
        super(myOffSet1, myOffSet2, myOffSet3, myMinDmnsn1, myMinDmnsn2, myMinDmnsn3, myDmnsn1, myDmnsn2, myDmnsn3, myDelta1, myDelta2, myDelta3);
        setCrdntTpVal(dataNmKey);
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
            ToroidalVar aHelixVar = new ToroidalVar();
        }
        return dmnsnVar;
    }

    public double[] cmptCenter() {
        double aLength = (getMaxDmnsn3Val() - getMinDmnsn3Val());
        double aCenter[] = {0, 0, aLength / 2};
        return aCenter;
    }
}