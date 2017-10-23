/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

import java.util.HashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class MtrlVar extends DataVar {
    //Note ... create AmbientVar, DiffuseVar, ...

    //private LinkedHashMap aDtValMap;
    private static String dataNmKey = "optical prop.";
    private String idKey = "id";
    private String overRideClrKey = "colorperpixel";
    private String mtrlKey = "material";
    private String ambnt1Key = "ambient-r";
    private String ambnt2Key = "abmient-g";
    private String ambnt3Key = "ambient-b";
    private String dffs1Key = "diffuse-r";
    private String dffs2Key = "diffuse-g";
    private String dffs3Key = "diffuse-b";
    private String alphaKey = "alpha";
    private String emssv1Key = "emssv-r";
    private String emssv2Key = "emssv-g";
    private String emssv3Key = "emssv-b";
    private String spclr1Key = "specular-r";
    private String spclr2Key = "specular-g";
    private String spclr3Key = "specular-b";
    /**
     *
     */
    public final String dscrptnKey = "dscrptn";
    private boolean overRideClrVal;
    private String idVal = "";
    private String mtrlVal;
    private float alphaVal;
    private float ambnt1Val;
    private float ambnt2Val;
    private float ambnt3Val;
    private float dffs1Val;
    private float dffs2Val;
    private float dffs3Val;
    private float emssv1Val;
    private float emssv2Val;
    private float emssv3Val;
    private float spclr1Val;
    private float spclr2Val;
    private float spclr3Val;
    private float spclrExpntVal;
    private String dscrptnVal = "";

    /**
     *
     */
    public MtrlVar() {
        this("Default", 0.0, 0.0, 0.0, 1.0, 0.01, 0.01, 0.5, 0.5, 0.5, 32.0, 0.0);
        crtKeyValMap();
    }

    /**
     *
     * @param myMtrlVar
     * @param myA1
     * @param myA2
     * @param myA3
     * @param myD1
     * @param myD2
     * @param myD3
     * @param myS1
     * @param myS2
     * @param myS3
     * @param mySpclrExpntVal
     * @param myD4
     */
    public MtrlVar(String myMtrlVar, double myA1, double myA2, double myA3, double myD1, double myD2, double myD3, double myS1, double myS2, double myS3, double mySpclrExpntVal, double myD4) {
        this(myMtrlVar, (float) myA1, (float) myA2, (float) myA3, (float) myD1, (float) myD2, (float) myD3, (float) myS1, (float) myS2, (float) myS3, (float) mySpclrExpntVal, (float) myD4);
        crtKeyValMap();
    }

    /**
     *
     * @param myMtrlVar
     * @param myA1
     * @param myA2
     * @param myA3
     * @param myD1
     * @param myD2
     * @param myD3
     * @param myS1
     * @param myS2
     * @param myS3
     * @param mySpclrExpntVal
     * @param myD4
     */
    public MtrlVar(String myMtrlVar, float myA1, float myA2, float myA3, float myD1, float myD2, float myD3, float myS1, float myS2, float myS3, float mySpclrExpntVal, float myD4) {
        //aDtValMap = new LinkedHashMap();
        mtrlVal = myMtrlVar;
        ambnt1Val = myA1;
        ambnt2Val = myA2;
        ambnt3Val = myA3;
        dffs1Val = myD1;
        dffs2Val = myD2;
        dffs3Val = myD3;
        spclr1Val = myS1;
        spclr2Val = myS2;
        spclr3Val = myS3;
        spclrExpntVal = mySpclrExpntVal;
        crtKeyValMap();
    }

    public void setOverRideClrVal(boolean myOverRideClrVal) {
        overRideClrVal = myOverRideClrVal;
    }
    public boolean getOverRideClrVal() {
        return overRideClrVal;
    }
    /**
     *
     * @return
     */
    public static String getDataNmKey() {
        return dataNmKey;
    }

    /**
     *
     * @return
     */
    public String getIdKey() {
        return idKey;
    }

    /**
     *
     * @return
     */
    public String getAlphaKey() {
        return alphaKey;
    }

    /**
     *
     * @return
     */
    public String getMtrlKey() {
        return mtrlKey;
    }

    /**
     *
     * @return
     */
    public String getAmbnt1Key() {
        return ambnt1Key;
    }

    /**
     *
     * @return
     */
    public String getAmbnt3Key() {
        return ambnt3Key;
    }

    /**
     *
     * @return
     */
    public String getAmbnt2Key() {
        return ambnt2Key;
    }

    /**
     *
     * @return
     */
    public String getEmssv1Key() {
        return emssv1Key;
    }

    /**
     *
     * @return
     */
    public String getEmssv3Key() {
        return emssv3Key;
    }

    /**
     *
     * @return
     */
    public String getEmssv2Key() {
        return emssv2Key;
    }

    /**
     *
     * @return
     */
    public String getSpclr1Key() {
        return spclr1Key;
    }

    /**
     *
     * @return
     */
    public String getSpclr3Key() {
        return spclr3Key;
    }

    /**
     *
     * @return
     */
    public String getSpclr2Key() {
        return spclr2Key;
    }

    public String getDscrptnKey() {
        return dscrptnKey;
    }

    public void setIdVal(String aIdVal) {
        idVal = aIdVal;
    }

    /**
     *
     * @param anAlphaVal
     */
    public void setAlphaVal(float anAlphaVal) {
        alphaVal = anAlphaVal;
    }

    /**
     *
     * @param anMtrlVal
     */
    public void setMtrlVal(String anMtrlVal) {
        mtrlVal = anMtrlVal;
    }

    /**
     *
     * @param anAmbnt1Val
     */
    public void setAmbnt1Val(float anAmbnt1Val) {
        ambnt1Val = anAmbnt1Val;
    }

    /**
     *
     * @param anAmbnt2Val
     */
    public void setAmbnt2Val(float anAmbnt2Val) {
        ambnt2Val = anAmbnt2Val;
    }

    /**
     *
     * @param anAmbnt3Val
     */
    public void setAmbnt3Val(float anAmbnt3Val) {
        ambnt3Val = anAmbnt3Val;
    }

    public void setDscrptnVal(String aDscrptnVal) {
        dscrptnVal = aDscrptnVal;
    }

    /**
     *
     * @return
     */
    public float getAlphaVal() {
        return alphaVal;
    }

    public String getIdVal() {
        return idVal;
    }

    /**
     *
     * @return
     */
    public String getMtrlVal() {
        return mtrlVal;
    }

    /**
     *
     * @return
     */
    public float getAmbnt1Val() {
        return ambnt1Val;
    }

    /**
     *
     * @return
     */
    public float getAmbnt3Val() {
        return ambnt3Val;
    }

    /**
     *
     * @return
     */
    public float getAmbnt2Val() {
        return ambnt2Val;
    }

    /**
     *
     * @param anEmssv1Val
     */
    public void setEmssv1Val(float anEmssv1Val) {
        emssv1Val = anEmssv1Val;
    }

    /**
     *
     * @param anEmssv2Val
     */
    public void setEmssv2Val(float anEmssv2Val) {
        emssv2Val = anEmssv2Val;
    }

    /**
     *
     * @param anEmssv3Val
     */
    public void setEmssv3Val(float anEmssv3Val) {
        emssv3Val = anEmssv3Val;
    }

    /**
     *
     * @param anSpclr1Val
     */
    public void setSpclr1Val(float anSpclr1Val) {
        spclr1Val = anSpclr1Val;
    }

    /**
     *
     * @param anSpclr1Val
     */
    public void setSpclr2Val(float anSpclr1Val) {
        spclr1Val = anSpclr1Val;
    }

    /**
     *
     * @param anSpclr3Val
     */
    public void setSpclr3Val(float anSpclr3Val) {
        spclr3Val = anSpclr3Val;
    }

    /**
     *
     * @return
     */
    public float getEmssv1Val() {
        return emssv1Val;
    }

    /**
     *
     * @return
     */
    public float getEmssv3Val() {
        return emssv3Val;
    }

    /**
     *
     * @return
     */
    public float getEmssv2Val() {
        return emssv2Val;
    }

    public String getDscrptnVal() {
        return dscrptnVal;
    }

    /**
     *
     * @return
     */
    public String getDffs1Key() {
        return dffs1Key;
    }

    /**
     *
     * @return
     */
    public String getDffs3Key() {
        return dffs3Key;
    }

    /**
     *
     * @return
     */
    public String getDffs2Key() {
        return dffs2Key;
    }

    /**
     *
     * @param anDffs1Val
     */
    public void setDffs1Val(float anDffs1Val) {
        dffs1Val = anDffs1Val;
    }

    /**
     *
     * @param anDffs2Val
     */
    public void setDffs2Val(float anDffs2Val) {
        dffs2Val = anDffs2Val;
    }

    /**
     *
     * @param anDffs3Val
     */
    public void setDffs3Val(float anDffs3Val) {
        dffs3Val = anDffs3Val;
    }

    /**
     *
     * @return
     */
    public float getDffs1Val() {
        return dffs1Val;
    }

    /**
     *
     * @return
     */
    public float getDffs3Val() {
        return dffs3Val;
    }

    /**
     *
     * @return
     */
    public float getDffs2Val() {
        return dffs2Val;
    }

    /**
     *
     * @return
     */
    public float getSpclr1Val() {
        return spclr1Val;
    }

    /**
     *
     * @return
     */
    public float getSpclr3Val() {
        return spclr3Val;
    }

    /**
     *
     * @return
     */
    public float getSpclr2Val() {
        return spclr2Val;
    }

    private HashMap crtKeyValMap() {
        String alphaKey = getAlphaKey();
        String alphaVal = "" + getAlphaVal();
        String ambnt1Key = getAmbnt1Key();
        String ambnt1Val = "" + getAmbnt1Val();
        String ambnt2Key = getAmbnt2Key();
        String ambnt2Val = "" + getAmbnt2Val();
        String ambnt3Key = getAmbnt3Key();
        String ambnt3Val = "" + getAmbnt3Val();
        String emssv1Key = getEmssv1Key();
        String emssv1Val = "" + getEmssv1Val();
        String emssv2Key = getEmssv2Key();
        String emssv2Val = "" + getEmssv2Val();
        String emssv3Key = getEmssv3Key();
        String emssv3Val = "" + getEmssv3Val();
        String spclr1Key = getSpclr1Key();
        String spclr1Val = "" + getSpclr1Val();
        String spclr2Key = getSpclr2Key();
        String spclr2Val = "" + getSpclr2Val();
        String spclr3Key = getSpclr3Key();
        String spclr3Val = "" + getSpclr3Val();
        String dffs1Key = getDffs1Key();
        String dffs1Val = "" + getDffs1Val();
        String dffs2Key = getDffs2Key();
        String dffs2Val = "" + getDffs2Val();
        String dffs3Key = getDffs3Key();
        String dffs3Val = "" + getDffs3Val();

        insrtData(alphaKey, alphaVal);
        insrtData(dffs1Key, dffs1Val);
        insrtData(dffs2Key, dffs2Val);
        insrtData(dffs3Key, dffs3Val);
        insrtData(spclr1Key, spclr1Val);
        insrtData(spclr2Key, spclr2Val);
        insrtData(spclr3Key, spclr3Val);
        insrtData(emssv1Key, emssv1Val);
        insrtData(emssv2Key, emssv2Val);
        insrtData(emssv3Key, emssv3Val);
        insrtData(ambnt1Key, ambnt1Val);
        insrtData(ambnt2Key, ambnt2Val);
        insrtData(ambnt3Key, ambnt3Val);

        return super.getKeyValMap();
    }

    public void updtKeyVals(HashMap aLinkedHashMap) {
        String aKey = getMtrlKey();
        String aVal = (String) aLinkedHashMap.get(aKey);
        System.out.println("MtrlVar key = " + aKey + ", val = " + aVal);
        setMtrlVal(aVal);

        String aKey2 = getAlphaKey();
        String aVal2 = (String) aLinkedHashMap.get(aKey2);
        Float aFlt2 = Float.parseFloat(aVal2);
        setAlphaVal(aFlt2);

        String aKey3 = getAmbnt1Key();
        String aVal3 = (String) aLinkedHashMap.get(aKey3);
        Float aFlt3 = Float.parseFloat(aVal3);
        setAlphaVal(aFlt3);

        String aKey4 = getAmbnt2Key();
        String aVal4 = (String) aLinkedHashMap.get(aKey4);
        Float aFlt4 = Float.parseFloat(aVal4);
        setAmbnt2Val(aFlt4);

        String aKey5 = getAmbnt2Key();
        String aVal5 = (String) aLinkedHashMap.get(aKey5);
        Float aFlt5 = Float.parseFloat(aVal5);
        setAmbnt3Val(aFlt5);

        String aKey6 = getEmssv1Key();
        String aVal6 = (String) aLinkedHashMap.get(aKey6);
        Float aFlt6 = Float.parseFloat(aVal6);
        setEmssv1Val(aFlt6);

        String aKey7 = getEmssv2Key();
        String aVal7 = (String) aLinkedHashMap.get(aKey7);
        Float aFlt7 = Float.parseFloat(aVal7);
        setEmssv2Val(aFlt7);

        String aKey8 = getEmssv3Key();
        String aVal8 = (String) aLinkedHashMap.get(aKey7);
        Float aFlt8 = Float.parseFloat(aVal8);
        setEmssv3Val(aFlt8);

        String aKey9 = getEmssv3Key();
        String aVal9 = (String) aLinkedHashMap.get(aKey9);
        Float aFlt9 = Float.parseFloat(aVal9);
        setEmssv3Val(aFlt9);

        String aKey10 = getDffs1Key();
        String aVal10 = (String) aLinkedHashMap.get(aKey10);
        Float aFlt10 = Float.parseFloat(aVal10);
        setDffs1Val(aFlt10);

        String aKey11 = getDffs2Key();
        String aVal11 = (String) aLinkedHashMap.get(aKey11);
        Float aFlt11 = Float.parseFloat(aVal11);
        setDffs2Val(aFlt11);

        String aKey12 = getDffs3Key();
        String aVal12 = (String) aLinkedHashMap.get(aKey12);
        Float aFlt12 = Float.parseFloat(aVal12);
        setDffs3Val(aFlt12);

        String aKey13 = getSpclr1Key();
        String aVal13 = (String) aLinkedHashMap.get(aKey13);
        Float aFlt13 = Float.parseFloat(aVal13);
        setSpclr1Val(aFlt13);

        String aKey14 = getSpclr2Key();
        String aVal14 = (String) aLinkedHashMap.get(aKey14);
        Float aFlt14 = Float.parseFloat(aVal14);
        setSpclr2Val(aFlt14);

        String aKey15 = getSpclr3Key();
        String aVal15 = (String) aLinkedHashMap.get(aKey15);
        Float aFlt15 = Float.parseFloat(aVal15);
        setSpclr3Val(aFlt15);
    }
}