/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.elmnt;

/**
 *
 * @author clayton g thomas jr
 */
public class QuadInfoVar {
    private static String dataNmKey = "quadinfo";
    private String quadInfoIdKey    = "quadinfoid";

    private String quadCntKey       = "quad #";
    private String ttlQuadCntKey    = "total quad #";    
    private String surfaceAreaKey   = "surface area";
            

    private int quadCntVal;
    private int quadTtlCntVal;
    private int surfaceAreaVal;

    public void setSurfaceAreaVal(int surfaceAreaVal) {
        this.surfaceAreaVal = surfaceAreaVal;
    }

    public int getSurfaceAreaVal() {
        return surfaceAreaVal;
    }
    public void setSurfaceAreaVal(int myNmbrQuad,int myDeltaX,int myDeltaY) {
        surfaceAreaVal = myNmbrQuad*myDeltaX*myDeltaY;
    }    

    public void setSurfaceAreaKey(String surfaceAreaKey) {
        this.surfaceAreaKey = surfaceAreaKey;
    }

    public String getSurfaceAreaKey() {
        return surfaceAreaKey;
    }

    public static String getDataNmKey() {
        return dataNmKey;
    }

    public String getQuadCntKey() {
        return quadCntKey;
    }

    public int getQuadCntVal() {
        return quadCntVal;
    }

    public String getQuadInfoIdKey() {
        return quadInfoIdKey;
    }

    public int getQuadTtlCntVal() {
        return quadTtlCntVal;
    }

    public String getTtlQuadCntKey() {
        return ttlQuadCntKey;
    }

    public static void setDataNmKey(String dataNmKey) {
        QuadInfoVar.dataNmKey = dataNmKey;
    }

    public void setQuadCntKey(String quadCntKey) {
        this.quadCntKey = quadCntKey;
    }

    public void setQuadCntVal(int quadCntVal) {
        this.quadCntVal = quadCntVal;
    }

    public void setQuadInfoIdKey(String quadInfoIdKey) {
        this.quadInfoIdKey = quadInfoIdKey;
    }

    public void setQuadTtlCntVal(int quadTtlCntVal) {
        this.quadTtlCntVal = quadTtlCntVal;
    }

    public void setTtlQuadCntKey(String ttlQuadCntKey) {
        this.ttlQuadCntKey = ttlQuadCntKey;
    }    
}
