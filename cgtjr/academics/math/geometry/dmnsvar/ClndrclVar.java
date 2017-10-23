package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.ApplctnVar;

/**
 * 
 * @author clayton g thomas jr
 */
public class ClndrclVar extends DmnsnVar implements ApplctnVar {

    private static DmnsnVar dmnsnVar;
    private final static String varNameVal = "cylindrical";
    private final static String clndrclCrdntTp = "cylindrical";

    /**
     * 
     */
    public ClndrclVar() {
        //super(0, 0, 0, 0, 0, 0, 2, 2 * Math.PI, 2, 1.0f, Math.PI / 16, 1.0f,0,0,0,varNameVal);
        //super(0, 0, 0, 0, 0, 0, 2, 2 * Math.PI, 2, 1.0f, Math.PI / 32, 1.0f,0,0,0,varNameVal);
        super(0, 0, 0, 0, 0, 0, 2.0, 2.0* Math.PI + Math.PI / 100, 8.0, 1.0, Math.PI / 16.0, 1, 0, 0, 0,varNameVal);        
        setCrdntTpVal(varNameVal);
        
        System.out.println("ClndrclVar : test ... type = " + getCrdntTpVal());
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
    public ClndrclVar(double myXOffSet, double myYOffSet, double myZOffSet, double myMinX, double myMinY, double myMinZ, double myDmnsn1, double myDmnsn2, double myDmnsn3) {
        super(myXOffSet, myYOffSet, myZOffSet, myMinX, myMinY, myMinZ, myDmnsn1, myDmnsn2, myDmnsn3);
        setCrdntTpVal(varNameVal);
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
    public ClndrclVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {
        super(myOffSet1, myOffSet2, myOffSet3, myMinDmnsn1, myMinDmnsn2, myMinDmnsn3, myDmnsn1, myDmnsn2, myDmnsn3, myDelta1, myDelta2, myDelta3);
        setCrdntTpVal(varNameVal);
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
    public ClndrclVar(double myXOffSet, double myYOffSet, double myZOffSet, double myMinX, double myMinY, double myMinZ, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3, double myInit1, double myInit2, double myInit3) {
        super(myXOffSet, myYOffSet, myZOffSet, myMinX, myMinY, myMinZ, myDmnsn1, myDmnsn2, myDmnsn3, myDelta1, myDelta2, myDelta3, myInit1, myInit2, myInit3);
        setCrdntTpVal(varNameVal);
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
            ClndrclVar aClndrclVar = new ClndrclVar();
            dmnsnVar = aClndrclVar;
        }
        return dmnsnVar;
    }

    public double[] cmptCenter() {
        double aLength = (getMaxDmnsn3Val() - getMinDmnsn3Val());
        double aCenter[] = {0, 0, aLength / 2};
        return aCenter;
    }
}