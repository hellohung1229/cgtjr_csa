package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.DataVar;
import java.util.HashMap;

/**
 * The DmnsnVar class defines initial conditions and general constraints for the automatically 
 * generated mesh structure.  Future implementations will provide user defined coordinate systems.
 * @author clayton g thomas jr
 */
public class DmnsnVar extends DataVar {

    private static String dataNmKey = "coordinate system";
    private String dmnsnIdKey       = "dmnsnid";
    private String crdntTpKey       = "coord-type";
    private String minDmnsn1Key     = "r min";
    private String minDmnsn2Key     = "s min";
    private String minDmnsn3Key     = "t min";
    private String maxDmnsn1Key     = "r max";
    private String maxDmnsn2Key     = "s max";
    private String maxDmnsn3Key     = "t max";
    private String delta1Key        = "delta-r";
    private String delta2Key        = "delta-s";
    private String delta3Key        = "delta-t";
    private String initXKey         = "init-r";
    private String initYKey         = "init-s";
    private String initZKey         = "init-t";
    private String xOffSetKey       = "x-offset";
    private String yOffSetKey       = "y-offset";
    private String zOffSetKey       = "z-offset";
    private String valueKey         = "value";

    private final String dscrptnKey  = "dscrptn";
    private String idVal            = "";
    private String crdntTpVal;
    private double minDmnsn1Val;
    private double minDmnsn2Val;
    private double minDmnsn3Val;
    private double maxDmnsn1Val;
    private double maxDmnsn2Val;
    private double maxDmnsn3Val;
    private double delta1Val;
    private double delta2Val;
    private double delta3Val;
    private double initXVal;
    private double initYVal;
    private double initZVal;
    private double valueVal;
    private double yOffSetVal;
    private double xOffSetVal;
    private double zOffSetVal;
    private String dscrptnVal           = "";
    
    /**
     * Constructs a default coordinate system variables (4x4x4) and 
     * initial delta values of 1
     */
    public DmnsnVar() {
        //aDtValMap = new LinkedHashMap();
        //aDtVarVctr = new Vector();
        crdntTpVal = dataNmKey;
        maxDmnsn1Val = 4;
        maxDmnsn2Val = 4;
        maxDmnsn3Val = 4;
        delta1Val = 1;
        delta2Val = 1;
        delta3Val = 1;
        crdntTpVal = "cartessian";
        crtKeyValMap();
        //updtData();
    }

    /**
     * Constructs coordinate system variables.  The coordinates start at 0,0,0.
     * The default delta values are 1.
     * @param myXOffSet defines the x offset of the coordinates.
     * @param myYOffSet defines the y offset of the coordinates.
     * @param myZOffSet defines the z offset of the coordinates.
     * @param myDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myDmnsn3 defines the maximum z dimension of the coordinates.
     */
    public DmnsnVar(double myXOffSet, double myYOffSet, double myZOffSet, double myDmnsn1, double myDmnsn2, double myDmnsn3) {
        //aDtValMap = new LinkedHashMap();

        //crdntTpVal = dataNmKey;
        maxDmnsn1Val = myDmnsn1;
        maxDmnsn2Val = myDmnsn2;
        maxDmnsn3Val = myDmnsn3;
        xOffSetVal = myXOffSet;
        yOffSetVal = myYOffSet;
        zOffSetVal = myZOffSet;
        //delta1Val = myDelta1;
        //delta2Val = myDelta2;
        //delta3Val = myDelta3;
        //updtData();
        crdntTpVal = "cartessian";
        crtKeyValMap();
    }

    /**
     * Constructs coordinate system variables.
     * @param myXOffSet defines the x offset of the coordinates.
     * @param myYOffSet defines the y offset of the coordinates.
     * @param myZOffSet defines the z offset of the coordinates.
     * @param myMinDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myMinDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myMinDmnsn3 defines the maximum z dimension of the coordinates.   
     * @param myDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myDmnsn3 defines the maximum z dimension of the coordinates.
     * @param myDelta1 defines the x delta values
     * @param myDelta2 defines the y delta values
     * @param myDelta3 defines the z delta values
     * @param myInitX defines the initial x starting position.  
     * This value should exist between the minimum and maximum x values.
     * @param myInitY defines the initial y starting position.  
     * This value should exist between the minimum and maximum y values.
     * @param myInitZ defines the initial z starting position.
     * This value should exist between the minimum and maximum y values.
     */
    public DmnsnVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3, double myInitX, double myInitY, double myInitZ) {
        //aDtValMap = new LinkedHashMap();
        //crdntTpVal = dataNmKey;
        maxDmnsn1Val = myDmnsn1;
        maxDmnsn2Val = myDmnsn2;
        maxDmnsn3Val = myDmnsn3;
        minDmnsn1Val = myMinDmnsn1;
        minDmnsn2Val = myMinDmnsn2;
        minDmnsn3Val = myMinDmnsn3;
        delta1Val = myDelta1;
        delta2Val = myDelta2;
        delta3Val = myDelta3;
        xOffSetVal = myOffSet1;
        yOffSetVal = myOffSet2;
        zOffSetVal = myOffSet3;
        initXVal = myInitX;
        initYVal = myInitY;
        initZVal = myInitZ;
        crdntTpVal = "cartessian";
        crtKeyValMap();
        //updtData();
    }
    public DmnsnVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3, double myInitX, double myInitY, double myInitZ,String myCrdntType) {
        //aDtValMap = new LinkedHashMap();
        //crdntTpVal = dataNmKey;
        maxDmnsn1Val = myDmnsn1;
        maxDmnsn2Val = myDmnsn2;
        maxDmnsn3Val = myDmnsn3;
        minDmnsn1Val = myMinDmnsn1;
        minDmnsn2Val = myMinDmnsn2;
        minDmnsn3Val = myMinDmnsn3;
        delta1Val = myDelta1;
        delta2Val = myDelta2;
        delta3Val = myDelta3;
        xOffSetVal = myOffSet1;
        yOffSetVal = myOffSet2;
        zOffSetVal = myOffSet3;
        initXVal = myInitX;
        initYVal = myInitY;
        initZVal = myInitZ;
        crdntTpVal = myCrdntType;
        crtKeyValMap();
        //updtData();
    }
    /**
     * Constructs coordinate system variables.
     * @param myXOffSet defines the x offset of the coordinates.
     * @param myYOffSet defines the y offset of the coordinates.
     * @param myZOffSet defines the z offset of the coordinates.
     * @param myMinDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myMinDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myMinDmnsn3 defines the maximum z dimension of the coordinates.   
     * @param myDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myDmnsn3 defines the maximum z dimension of the coordinates.
     * @param myDelta1 defines the x delta values
     * @param myDelta2 defines the y delta values
     * @param myDelta3 defines the z delta values
     */
    public DmnsnVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3, double myDelta1, double myDelta2, double myDelta3) {
        //aDtValMap = new LinkedHashMap();
        //crdntTpVal = dataNmKey;
        maxDmnsn1Val = myDmnsn1;
        maxDmnsn2Val = myDmnsn2;
        maxDmnsn3Val = myDmnsn3;
        minDmnsn1Val = myMinDmnsn1;
        minDmnsn2Val = myMinDmnsn2;
        minDmnsn3Val = myMinDmnsn3;
        delta1Val = myDelta1;
        delta2Val = myDelta2;
        delta3Val = myDelta3;
        xOffSetVal = myOffSet1;
        yOffSetVal = myOffSet2;
        zOffSetVal = myOffSet3;
        crdntTpVal = "cartessian";
        crtKeyValMap();
        //updtData();
    }

    /**
     * Constructs coordinate system variables.
     * @param myXOffSet defines the x offset of the coordinates.
     * @param myYOffSet defines the y offset of the coordinates.
     * @param myZOffSet defines the z offset of the coordinates.
     * @param myMinDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myMinDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myMinDmnsn3 defines the maximum z dimension of the coordinates.   
     * @param myDmnsn1 defines the maximum x dimension of the coordinates.
     * @param myDmnsn2 defines the maximum y dimension of the coordinates.
     * @param myDmnsn3 defines the maximum z dimension of the coordinates.
     */
    public DmnsnVar(double myOffSet1, double myOffSet2, double myOffSet3, double myMinDmnsn1, double myMinDmnsn2, double myMinDmnsn3, double myDmnsn1, double myDmnsn2, double myDmnsn3) {
        //aDtValMap = new LinkedHashMap();
        //crdntTpVal = dataNmKey;
        maxDmnsn1Val = myDmnsn1;
        maxDmnsn2Val = myDmnsn2;
        maxDmnsn3Val = myDmnsn3;
        minDmnsn1Val = myMinDmnsn1;
        minDmnsn2Val = myMinDmnsn2;
        minDmnsn3Val = myMinDmnsn3;
        xOffSetVal = myOffSet1;
        yOffSetVal = myOffSet2;
        zOffSetVal = myOffSet3;
        crdntTpVal = "cartessian";
        crtKeyValMap();
        //updtData();
    }

    /**
     * gets the data name key variable.
     * @return string representation of data name key.
     */
    public static String getDataNmKey() {
        return dataNmKey;
    }

    /**
     * gets the coordinate type key variable.
     * @return string representation of key.
     */
    public String getCrdntTpKey() {
        return crdntTpKey;
    }

    /**
     * gets the dimension id key variable
     * @return string representation of key.
     */
    public String getDmnsnIdKey() {
        return dmnsnIdKey;
    }

    /**
     * gets the minimum x dimension key 
     * @return string representation of key.
     */
    public String getMinDmnsn1Key() {
        return minDmnsn1Key;
    }

    /**
     * gets the minimum y dimension key. 
     * @return string representation of key.
     */
    public String getMinDmnsn2Key() {
        return minDmnsn2Key;
    }

    /**
     * gets the minimum z dimension key.
     * @return string representation.
     */
    public String getMinDmnsn3Key() {
        return minDmnsn3Key;
    }

    /**
     * get the maximum x dimension key
     * @return string value of key
     */
    public String getMaxDmnsn1Key() {
        return maxDmnsn1Key;
    }

    /**
     * get maximum y dimension key
     * @return string representation of key
     */
    public String getMaxDmnsn2Key() {
        return maxDmnsn2Key;
    }

    /**
     * get maximum z dimension key
     * @return string representation of key
     */
    public String getMaxDmnsn3Key() {
        return maxDmnsn3Key;
    }

    /**
     * gets the delta x key
     * @return string representation of key
     */
    public String getDelta1Key() {
        return delta1Key;
    }

    /**
     * gets the delta y key
     * @return string representation of key
     */
    public String getDelta2Key() {
        return delta2Key;
    }

    /**
     * gets delta z key
     * @return string representation of key
     */
    public String getDelta3Key() {
        return delta3Key;
    }

    /**
     * gets initial x position key
     * @return string representation of key
     */
    public String getInitXKey() {
        return initXKey;
    }

    /**
     * gets initial y position key
     * @return string representation of key
     */
    public String getInitYKey() {
        return initYKey;
    }

    /**
     * gets initial z position key
     * @return string representation of key
     */
    public String getInitZKey() {
        return initZKey;
    }

    /**
     * get x offset 
     * @return string representation of key
     */
    public String getXOffSetKey() {
        return xOffSetKey;
    }

    /**
     * get y offset.
     * @return string representation of key.
     */
    public String getYOffSetKey() {
        return yOffSetKey;
    }

    /**
     * get z offset.
     * @return string representation of key.
     */
    public String getZOffSetKey() {
        return zOffSetKey;
    }

    /**
     * get value variable key
     * @return string representation of key
     */
    public String getValueKey() {
        return valueKey;
    }

    /**
     * gets description of variables in this class.
     * @return string representation of key.
     */
    public String getDscrptnKey() {
        return dscrptnKey;
    }

    /**
     * sets the coordinate type value
     * @param myCrdntTpVal coordinate type value
     */
    public void setCrdntTpVal(String myCrdntTpVal) {
        crdntTpVal = myCrdntTpVal;
    }

    /**
     * sets the identification value
     * @param aIdVal id value
     */
    public void setIdVal(String aIdVal) {
        idVal = aIdVal;
    }

    /**
     * sets the minimum x dimension value
     * @param aMinDmnsn1Val minimum x value for the mesh structure
     */
    public void setMinDmnsn1Val(double aMinDmnsn1Val) {
        minDmnsn1Val = aMinDmnsn1Val;
    }

    /**
     * sets the minimum y value for the mesh structure.
     * @param anMinDmnsn2Val double value for y
     */
    public void setMinDmnsn2Val(double anMinDmnsn2Val) {
        minDmnsn2Val = anMinDmnsn2Val;
    }

    /**
     * sets the minimum z value of the mesh structure
     * @param anMinDmnsn3Val represents double value for z parameter.
     */
    public void setMinDmnsn3Val(double anMinDmnsn3Val) {
        minDmnsn3Val = anMinDmnsn3Val;
    }

    /**
     * sets the maximum x value
     * @param aMaxDmnsn1Val double representation of x value.
     */
    public void setMaxDmnsn1Val(double aMaxDmnsn1Val) {
        maxDmnsn1Val = aMaxDmnsn1Val;
    }

    /**
     * sets maximum y dimension
     * @param anMaxDmnsn2Val double representation of maximum value
     */
    public void setMaxDmnsn2Val(double anMaxDmnsn2Val) {
        maxDmnsn2Val = anMaxDmnsn2Val;
    }

    /**
     * sets the maximum z dimension value
     * @param anMaxDmnsn3Val double representation of value
     */
    public void setMaxDmnsn3Val(double anMaxDmnsn3Val) {
        maxDmnsn3Val = anMaxDmnsn3Val;
    }

    /**
     * sets the delta x value. 
     * @param anDelta1Val double representation of value
     */
    public void setDelta1Val(double anDelta1Val) {
        delta1Val = anDelta1Val;
    }

    /**
     * sets the delta y value
     * @param aDelta2Val double representation of value
     */
    public void setDelta2Val(double aDelta2Val) {
        delta2Val = aDelta2Val;
    }

    /**
     * sets delta z value.
     * @param aDelta3Val double representation of value.
     */
    public void setDelta3Val(double aDelta3Val) {
        delta3Val = aDelta3Val;
    }

    /**
     * sets initial x value.  This value should exist between minimum
     * and maximum value.
     * @param anInitXVal double representation of value.
     */
    public void setInitXVal(double anInitXVal) {
        initXVal = anInitXVal;
    }

    /**
     * sets the initial y value.  This value should exist between the 
     * minimum and maximum value.
     * @param aInitYVal double representation of value.
     */
    public void setInitYVal(double aInitYVal) {
        initYVal = aInitYVal;
    }

    /**
     * sets the initial z value.  This value should exist between the
     * minimum and maximum z value.
     * @param aInitZVal
     */
    public void setInitZVal(double aInitZVal) {
        initZVal = aInitZVal;
    }

    /**
     * sets the x offset for all nodes within the mesh.
     * @param anXOffSetVal double representation of value.
     */
    public void setXOffSetVal(double anXOffSetVal) {
        xOffSetVal = anXOffSetVal;
    }

    /**
     * sets the y offset for all nodes within the mesh.
     * @param aYOffSetVal double representation of value.
     */
    public void setYOffSetVal(double aYOffSetVal) {
        yOffSetVal = aYOffSetVal;
    }

    /**
     * sets the z offset value for all nodes within the mesh.
     * @param aZOffSetVal double representation of value.
     */
    public void setZOffSetVal(double aZOffSetVal) {
        zOffSetVal = aZOffSetVal;
    }

    /**
     * sets the value associated with this node; note - other mechanism may
     * exist to associate values with nodes.
     * @param aValueVal double representation of value.
     */
    public void setValueVal(double aValueVal) {
        valueVal = aValueVal;
    }

    /**
     * sets the description value of the object.
     * @param aDscrptnVal string representation of the value.
     */
    public void setDscrptnVal(String aDscrptnVal) {
        dscrptnVal = aDscrptnVal;
    }

    /**
     * gets the coordinate type value.
     * @return string representation of value.
     */
    public String getCrdntTpVal() {
        return crdntTpVal;
    }

    /**
     * gets the id value.
     * @return string representation of value.
     */
    public String getIdVal() {
        return idVal;
    }

    /**
     * gets the minimum x value.
     * @return string representation of value.
     */
    public double getMinDmnsn1Val() {
        return minDmnsn1Val;
    }

    /**
     * get minimum z value.
     * @return double representation of value.
     */
    public double getMinDmnsn3Val() {
        return minDmnsn3Val;
    }

    /**
     * get minimum y value.
     * @return double representation of y value.
     */
    public double getMinDmnsn2Val() {
        return minDmnsn2Val;
    }

    /**
     * get maximum x dimension value.
     * @return double reperentation of x value.
     */
    public double getMaxDmnsn1Val() {
        return maxDmnsn1Val;
    }

    /**
     * get maximum z value.
     * @return double representation of value.
     */
    public double getMaxDmnsn3Val() {
        return maxDmnsn3Val;
    }

    /**
     * gets maximum y dimension value.
     * @return double representation of value.
     */
    public double getMaxDmnsn2Val() {
        return maxDmnsn2Val;
    }

    /**
     * get delta x value.
     * @return double representation of value
     */
    public double getDelta1Val() {
        return delta1Val;
    }

    /**
     * get delta y value.
     * @return double representation of y value.
     */
    public double getDelta2Val() {
        return delta2Val;
    }

    /**
     * get delta z value.
     * @return double representation of value.
     */
    public double getDelta3Val() {
        return delta3Val;
    }

    /**
     * get initial x value.
     * @return double representation of value.
     */
    public double getInitXVal() {
        return initXVal;
    }

    /**
     * get initial y value.
     * @return double representation of value.
     */
    public double getInitYVal() {
        return initYVal;
    }

    /**
     * get initial z value.
     * @return double representation of value.
     */
    public double getInitZVal() {
        return initZVal;
    }

    /**
     * get the x offset value.
     * @return double representation of value.
     */
    public double getXOffSetVal() {
        return xOffSetVal;
    }

    /**
     * get y offset value.
     * @return double representation value.
     */
    public double getYOffSetVal() {
        return yOffSetVal;
    }

    /**
     * get z offset value.
     * @return double representation of value.
     */
    public double getZOffSetVal() {
        return zOffSetVal;
    }

    /**
     * get node value.
     * @return double representation of node value.
     */
    public double getValueVal() {
        return valueVal;
    }

    /**
     * get description of object
     * @return string representation of value.
     */
    public String getDscrptnVal() {
        return dscrptnVal;
    }

    /**
     * update value by utilizing key
     * @param myKey string representation of key value.
     * @param myValue string representation of value.
     */
    public void updtVal(String myKey, String myValue) {
        if (myKey.equals(getMinDmnsn1Key())) {
            setMinDmnsn1Val(Double.parseDouble(myValue));
        }
    }
    /**
     * update values by utilizing has map.  The hashmap contains key/value pairs.
     * @param aLinkedHashMap hashmap representation of key/value pair.
     */
    public void updtKeyVals(HashMap aLinkedHashMap) {

        String aKey = getMinDmnsn1Key();
        String aVal = (String) aLinkedHashMap.get(aKey);
        System.out.println("DmnsnVar key = " + aKey + ", val = " + aVal);
        double aDVal = Double.parseDouble(aVal);
        setMinDmnsn1Val(aDVal);

        String aKey2 = getMinDmnsn2Key();
        String aVal2 = (String) aLinkedHashMap.get(aKey2);
        double aDVal2 = Double.parseDouble(aVal2);
        setMinDmnsn2Val(aDVal2);

        String aKey3 = getMinDmnsn3Key();
        String aVal3 = (String) aLinkedHashMap.get(aKey3);
        double aDVal3 = Double.parseDouble(aVal3);
        setMinDmnsn3Val(aDVal3);

        String aKey4 = getMaxDmnsn1Key();
        String aVal4 = (String) aLinkedHashMap.get(aKey4);
        double aDVal4 = Double.parseDouble(aVal4);
        setMaxDmnsn1Val(aDVal4);

        String aKey5 = getMaxDmnsn2Key();
        String aVal5 = (String) aLinkedHashMap.get(aKey5);
        double aDVal5 = Double.parseDouble(aVal5);
        setMaxDmnsn2Val(aDVal5);

        String aKey6 = getMaxDmnsn3Key();
        String aVal6 = (String) aLinkedHashMap.get(aKey6);
        double aDVal6 = Double.parseDouble(aVal6);
        setMaxDmnsn3Val(aDVal6);

        String aKey7 = getDelta1Key();
        String aVal7 = (String) aLinkedHashMap.get(aKey7);
        double aDVal7 = Double.parseDouble(aVal7);
        setDelta1Val(aDVal7);

        String aKey8 = getDelta2Key();
        String aVal8 = (String) aLinkedHashMap.get(aKey8);
        double aDVal8 = Double.parseDouble(aVal8);
        setDelta2Val(aDVal8);

        String aKey9 = getDelta3Key();
        String aVal9 = (String) aLinkedHashMap.get(aKey9);
        double aDVal9 = Double.parseDouble(aVal9);
        setDelta3Val(aDVal9);

        String aKey10 = getXOffSetKey();
        String aVal10 = (String) aLinkedHashMap.get(aKey10);
        double aDVal0 = Double.parseDouble(aVal10);
        setXOffSetVal(aDVal0);

        String aKey11 = getYOffSetKey();
        String aVal11 = (String) aLinkedHashMap.get(aKey11);
        double aDVal1 = Double.parseDouble(aVal11);
        setYOffSetVal(aDVal1);

        String aKey12 = getZOffSetKey();
        String aVal12 = (String) aLinkedHashMap.get(aKey12);
        double aDVal12 = Double.parseDouble(aVal12);
        setZOffSetVal(aDVal12);

        String aKey13 = getInitXKey();
        String aVal13 = (String) aLinkedHashMap.get(aKey13);
        double aDVal13 = Double.parseDouble(aVal13);
        setInitXVal(aDVal13);

        String aKey14 = getInitYKey();
        String aVal14 = (String) aLinkedHashMap.get(aKey14);
        double aDVal14 = Double.parseDouble(aVal14);
        setInitYVal(aDVal14);

        String aKey15 = getInitZKey();
        String aVal15 = (String) aLinkedHashMap.get(aKey15);
        double aDVal15 = Double.parseDouble(aVal15);
        setInitZVal(aDVal15);
    }

    private HashMap crtKeyValMap() {
        String crdntTpKey = getCrdntTpKey();
        String crdntTpVal = "" + getCrdntTpVal();
        String minDmnsn1Key = getMinDmnsn1Key();
        String minDmnsn1Val = "" + getMinDmnsn1Val();
        String minDmnsn2Key = getMinDmnsn2Key();
        String minDmnsn2Val = "" + getMinDmnsn2Val();
        String minDmnsn3Key = getMinDmnsn3Key();
        String minDmnsn3Val = "" + getMinDmnsn3Val();

        String maxDmnsn1Key = getMaxDmnsn1Key();
        String maxDmnsn1Val = "" + getMaxDmnsn1Val();
        String maxDmnsn2Key = getMaxDmnsn2Key();
        String maxDmnsn2Val = "" + getMaxDmnsn2Val();
        String maxDmnsn3Key = getMaxDmnsn3Key();
        String maxDmnsn3Val = "" + getMaxDmnsn3Val();

        String delta1Key = getDelta1Key();
        String delta1Val = "" + getDelta1Val();
        String delta2Key = getDelta2Key();
        String delta2Val = "" + getDelta2Val();
        String delta3Key = getDelta3Key();
        String delta3Val = "" + getDelta3Val();
        String initXKey = getInitXKey();
        String initXVal = "" + getInitXVal();
        String initYKey = getInitYKey();
        String initYVal = "" + getInitYVal();
        String initZKey = getInitZKey();
        String initZVal = "" + getInitZVal();

        String xOffSetKey = getXOffSetKey();
        String xOffSetVal = "" + getXOffSetVal();
        String yOffSetKey = getYOffSetKey();
        String yOffSetVal = "" + getYOffSetVal();
        String zOffSetKey = getZOffSetKey();
        String zOffSetVal = "" + getZOffSetVal();

        insrtData(crdntTpKey, crdntTpVal);
        insrtData(minDmnsn1Key, minDmnsn1Val);
        insrtData(minDmnsn2Key, minDmnsn2Val);
        insrtData(minDmnsn3Key, minDmnsn3Val);
        insrtData(maxDmnsn1Key, maxDmnsn1Val);
        insrtData(maxDmnsn2Key, maxDmnsn2Val);
        insrtData(maxDmnsn3Key, maxDmnsn3Val);
        insrtData(delta1Key, delta1Val);
        insrtData(delta2Key, delta2Val);
        insrtData(delta3Key, delta3Val);
        insrtData(initXKey, initXVal);
        insrtData(initYKey, initYVal);
        insrtData(initZKey, initZVal);
        insrtData(xOffSetKey, xOffSetVal);
        insrtData(yOffSetKey, yOffSetVal);
        insrtData(zOffSetKey, zOffSetVal);

        return super.getKeyValMap();
    }

    /**
     * computes the center of the dimension values ((xmax-xmin)/2,(ymax-ymin)/2,(zmax-zmin)/2).
     * @return double array containing the three coordinates values.
     */
    public double[] cmptCenter() {
        double aWidth = (getMaxDmnsn1Val() - getMinDmnsn1Val());
        double aHeight = (getMaxDmnsn2Val() - getMinDmnsn2Val());
        double aLength = (getMaxDmnsn3Val() - getMinDmnsn3Val());
        double aCenter[] = {aWidth / 2, aHeight / 2, aLength / 2};
        return aCenter;
    }

    /**
     * retrieves the element size of the associated mesh structure. 
     * @return integer representation of value.
     */
    public int getLclSize() {
        int lclSize = 1;
        double aWidth = getMaxDmnsn1Val() - getMinDmnsn1Val();
        double aHeight = getMaxDmnsn2Val() - getMinDmnsn2Val();
        double aLength = getMaxDmnsn3Val() - getMinDmnsn3Val();
        if (aWidth != 0) {
            lclSize *= 2;
        }
        if (aHeight != 0) {
            lclSize *= 2;
        }
        if (aLength != 0) {
            lclSize *= 2;
        }
        return lclSize;
    }
}