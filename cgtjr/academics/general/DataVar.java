package cgtjr.academics.general;

import java.util.*;

/**
 * 
 * @author clayton g thomas jr
 */
public abstract class DataVar implements ApplctnVar {

    private LinkedHashMap keyValMap;
    private ArrayList keyArryLst;
    private static String dataNmKey = "data";
    private String dscrptnKey = "dscrptn";
    private String idKey = "id";
    private static String dataNmVal = "data1";
    private String idVal = "";
    private String dscrptnVal = "";

    /**
     * 
     */
    public DataVar() {
        keyValMap = new LinkedHashMap();
    }

    /**
     * 
     * @param myDmnsn1
     * @param myDmnsn2
     * @param myDmnsn3
     * @param myDelta1
     * @param myDelta2
     * @param myDelta3
     */
    public DataVar(double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {
        keyValMap = new LinkedHashMap();
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
    public String getDscrptnKey() {
        return dscrptnKey;
    }

    /**
     * 
     * @return
     */
    public String getIdVal() {
        return idVal;
    }

    /**
     * 
     * @param aIdVal
     */
    public void setIdVal(String aIdVal) {
        idVal = aIdVal;
    }

    /**
     * 
     * @param aDscrptnVal
     */
    public void setDscrptnVal(String aDscrptnVal) {
        dscrptnVal = aDscrptnVal;
    }

    /**
     * 
     * @return
     */
    public String getDscrptnVal() {
        return dscrptnVal;
    }

    /**
     * 
     * @return
     */
    public HashMap getKeyValMap() {
        return keyValMap;
    }

    /**
     * 
     * @param myLinkedHashMap
     */
    public void setKeyValMap(LinkedHashMap myLinkedHashMap) {
        keyValMap = myLinkedHashMap;
    }

    /**
     * 
     * @param myObject1
     * @param myObject2
     */
    public void insrtKeyVal(Object myObject1, Object myObject2) {
        insrtData(myObject1, myObject2);
    }

    /**
     * 
     * @param myObject1
     * @param myObject2
     */
    public void insrtData(Object myObject1, Object myObject2) {
        keyValMap.put(myObject1, myObject2);
    }

    /**
     * 
     * @param myKey
     * @param myVal
     * @return
     */
    public HashMap updtKeyValMap(String myKey, String myVal) {
        System.out.println("DataVar: key = " + myKey + ", val = " + myVal);
        HashMap aLinkedHashMap = (HashMap) getKeyValMap();
        aLinkedHashMap.put(myKey, myVal);
        return aLinkedHashMap;
    }

    /**
     * 
     * @param myHashMap
     */
    public void updtKeyVals(HashMap myHashMap) {
        System.out.println("Should overide this method !!!");
    }

    /**
     * 
     */
    public void prntKeyValMap() {
        Set aKetSet = keyValMap.keySet();

        Iterator anIterator = aKetSet.iterator();
        String aKey = "";
        String aVal = "";

        while (anIterator.hasNext()) {
            aKey = (String) anIterator.next();
            aVal = (String) keyValMap.get(aKey);
            System.out.println("DataVar: key = " + aKey + ", aVal = " + aVal);
        }
    }
}