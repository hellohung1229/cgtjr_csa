/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.graph;

/**
 *
 * @author cgthomasjr
 */
public class DrctnIterator {
    
    Edge posXDrctn;
    Edge negXDrctn;
    Edge posYDrctn;
    Edge negYDrctn;
    Edge posZDrctn;
    Edge negZDrctn;

    private static  DrctnIterator glbDrctnIterator;
    
    public Edge getPosXDrctn() {
        return posXDrctn;
    }

    public void setPosXDrctn(Edge myPosXDrctn) {
        this.posXDrctn = myPosXDrctn;
    }

    public Edge getNegXDrctn() {
        return negXDrctn;
    }

    public void setNegXDrctn(Edge myNegXDrctn) {
        this.negXDrctn = myNegXDrctn;
    }

    public Edge getPosYDrctn() {
        return posYDrctn;
    }

    public void setPosYDrctn(Edge myPosYDrctn) {
        this.posYDrctn = myPosYDrctn;
    }

    public Edge getNegYDrctn() {
        return negYDrctn;
    }

    public void setNegYDrctn(Edge myNegYDrctn) {
        this.negYDrctn = myNegYDrctn;
    }

    public Edge getPosZDrctn() {
        return posZDrctn;
    }

    public void setPosZDrctn(Edge myPosZDrctn) {
        this.posZDrctn = myPosZDrctn;
    }

    public Edge getNegZDrctn() {
        return negZDrctn;
    }

    public void setNegZDrctn(Edge myNegZDrctn) {
        this.negZDrctn = myNegZDrctn;
    }

    public static DrctnIterator getGlbDrctnIterator() {
        return glbDrctnIterator;
    }

    public static void setGlbDrctnIterator(DrctnIterator myGlbDrctnIterator) {
        DrctnIterator.glbDrctnIterator = myGlbDrctnIterator;
    }
}