/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.dmnsvar;

import java.util.LinkedHashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class DmnsnVarFctry extends DataVarFctry {

    public LinkedHashMap crtKeyVarMap(LinkedHashMap aLinkedHashMap) {
        DmnsnVar aDmnsnVar = new DmnsnVar();
        aLinkedHashMap.put(aDmnsnVar.getCrdntTpVal(), aDmnsnVar);
        SphrclVar aSphrclVar = new SphrclVar();
        aLinkedHashMap.put(aSphrclVar.getCrdntTpVal(), aSphrclVar);
        ClndrclVar aClndrclVar = new ClndrclVar();
        aLinkedHashMap.put(aClndrclVar.getCrdntTpVal(), aClndrclVar);
        KnotVar aKnotVar = new KnotVar();
        aLinkedHashMap.put(aKnotVar.getCrdntTpVal(), aKnotVar);
        OblateSphrdlVar aOblateSphrdlVar = new OblateSphrdlVar();
        aLinkedHashMap.put(aOblateSphrdlVar.getCrdntTpVal(), aOblateSphrdlVar);        
        HelixVar aHelixVar = new HelixVar();
        aLinkedHashMap.put(aHelixVar.getCrdntTpVal(), aHelixVar);
        ToroidalVar aToroidalVar = new ToroidalVar();
        aLinkedHashMap.put(aToroidalVar.getCrdntTpVal(), aToroidalVar);
        ConfocalEllpsdlVar aConfocalEllpsdlVar = new ConfocalEllpsdlVar();
        aLinkedHashMap.put(aConfocalEllpsdlVar.getCrdntTpVal(), aConfocalEllpsdlVar); 
        ConicalVar aConicalVar = new ConicalVar();
        aLinkedHashMap.put(aConicalVar.getCrdntTpVal(), aConicalVar);         
        EllptcClndrclVar aEllptcClndrclVar = new EllptcClndrclVar();
        aLinkedHashMap.put(aEllptcClndrclVar.getCrdntTpVal(), aEllptcClndrclVar);                
        ParabolicVar aParabolicVar = new ParabolicVar();
        aLinkedHashMap.put(aParabolicVar.getCrdntTpVal(), aParabolicVar);                        
        BiSphericalVar aBiSphericalVar = new BiSphericalVar();
        aLinkedHashMap.put(aBiSphericalVar.getCrdntTpVal(), aBiSphericalVar);                               
        return aLinkedHashMap;
    }
    public LinkedHashMap crtHelixKeyVarMap(LinkedHashMap aLinkedHashMap) {
        HelixVar aHelixVar = new HelixVar();
        aLinkedHashMap.put(aHelixVar.getCrdntTpVal(), aHelixVar);
        return aLinkedHashMap;
    }    
    public static LinkedHashMap crtHelixAutoMap() {
        LinkedHashMap aLinkedHashMap = new LinkedHashMap();
        HelixVar aHelixVar = new HelixVar();
        AutomaticDmnsnVar aAutomaticDmsnsnVar = new AutomaticDmnsnVar();        
        aLinkedHashMap.put(aAutomaticDmsnsnVar.getCrdntTpVal(), aAutomaticDmsnsnVar);
        aLinkedHashMap.put(aHelixVar.getCrdntTpVal(), aHelixVar);
        return aLinkedHashMap;
    }
}