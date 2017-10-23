package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.ApplctnVar;

/**
 * 
 * @author clayton g thomas jr
 */
public class AutomaticDmnsnVar extends DmnsnVar implements ApplctnVar {

    private static DmnsnVar dmnsnVar;
    private static String varNameVal = "automatic";
    private final static String clndrclCrdntTp = "automatic";

    public AutomaticDmnsnVar() {
        super(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        setCrdntTpVal(varNameVal);
    }
}