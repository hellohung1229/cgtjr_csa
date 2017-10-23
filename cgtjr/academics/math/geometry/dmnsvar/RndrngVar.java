package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.DataVar;
import java.util.*;

public class RndrngVar extends DataVar
{
   private static String dataNmKey     = "render prop.";
   private String idKey                = "id";
   
   private String rndrKey              = "render";
   private String quadKey              = "quad";
   private String hxhdrlKey            = "hexahedral";
   private String lineKey              = "line";   
   private String vrtxKey              = "nodes";
   private String glblIndxKey          = "global Index";   
   private String glblCrdntsKey        = "global Coord.";  
      

   public final String dscrptnKey      = "dscrptn";

   private String idVal = "";
   private boolean rndrVal;
   private boolean lineVal;   
   private boolean quadVal;
   private boolean hxhdrlVal;
   private boolean vrtxVal;
   private boolean glblIndxVal;   
   private boolean glblCrdntsVal;
   private boolean boundingBoxVal;
   
   private String dscrptnVal	= "";
   
   public RndrngVar()
   {
      lineVal = true;
      rndrVal = false;
      quadVal = false;
      vrtxVal = false;
      crtKeyValMap();
   }
   public RndrngVar(double myDmnsn1,double myMtrl,double myDmnsn3,double myVrtx,double myDelta2,double myDelta3)
   {
      lineVal = true;
      rndrVal = false;
      quadVal = false;
      vrtxVal = false;
      crtKeyValMap();
   }

   public String getHxhdrlKey() {
      return hxhdrlKey;
   }

   public boolean getHxhdrlVal() {
      return hxhdrlVal;
   }

   public void setHxhdrlVal(boolean hxhdrlVal) {
      this.hxhdrlVal = hxhdrlVal;
   }
   
    public static void setDataNmKey(String dataNmKey) {
        RndrngVar.dataNmKey = dataNmKey;
    }

    public void setGlblCrdntsKey(String glblCrdntsKey) {
        this.glblCrdntsKey = glblCrdntsKey;
    }

    public void setGlblCrdntsVal(boolean glblCrdntsVal) {
        this.glblCrdntsVal = glblCrdntsVal;
    }

    public void setGlblIndxKey(String glblIndxKey) {
        this.glblIndxKey = glblIndxKey;
    }

    public void setGlblIndxVal(boolean glblIndxVal) {
        this.glblIndxVal = glblIndxVal;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public void setLineKey(String lineKey) {
        this.lineKey = lineKey;
    }

    public void setLineVal(boolean lineVal) {
        this.lineVal = lineVal;
    }

    public void setRndrKey(String mtrlKey) {
        this.rndrKey = mtrlKey;
    }

    public void setQuadKey(String quadKey) {
        this.quadKey = quadKey;
    }

    public void setQuadVal(boolean quadVal) {
        this.quadVal = quadVal;
    }

    public void setVrtxKey(String vrtxKey) {
        this.vrtxKey = vrtxKey;
    }

    public String getGlblCrdntsKey() {
        return glblCrdntsKey;
    }

    public boolean getGlblCrdntsVal() {
        return glblCrdntsVal;
    }

    public String getGlblIndxKey() {
        return glblIndxKey;
    }

    public boolean getGlblIndxVal() {
        return glblIndxVal;
    }

    public String getLineKey() {
        return lineKey;
    }

    public boolean getLineVal() {
        return lineVal;
    }

   public static String getDataNmKey()
   {
      return dataNmKey;
   }
   public String getIdKey()
   {
      return idKey;
   }
   public String getRndrKey()
   {
      return rndrKey;
   }
   public String getVrtxKey()
   {
      return vrtxKey;
   }
   public String getQuadKey()
   {
      return quadKey;
   }
   public String getDscrptnKey()
   {
      return dscrptnKey;
   }
   public void setIdVal(String aIdVal)
   {
      idVal = aIdVal;
   }
   public void setRndrVal(boolean myMtrlVal)
   {
      rndrVal = myMtrlVal;
   }
   public void setVrtxVal(boolean anVrtxVal)
   {
      vrtxVal = anVrtxVal;
   }
   public void setDscrptnVal(String aDscrptnVal)
   {
      dscrptnVal = aDscrptnVal;
   }
   public String getIdVal()
   {
      return idVal;
   }
   public boolean getRndrVal()
   {
      return rndrVal;
   }
   public boolean getVrtxVal()
   {
      return vrtxVal;
   }
   public boolean getQuadVal()
   {
      return quadVal;
   }
   public String getDscrptnVal()
   {
      return dscrptnVal;
   }
   public HashMap crtKeyValMap()
   {
      String mtrlKey = getRndrKey();
      String mtrlVal = ""+getRndrVal();
      String quadKey = getQuadKey();
      String quadVal = ""+getQuadVal();
      String vrtxKey = getVrtxKey();
      String vrtxVal = ""+getVrtxVal();
      String lineKey = getLineKey();
      String lineVal = ""+getLineVal();
      String hxhdrlKey = getHxhdrlKey();
      String hxhdrlVal = ""+getHxhdrlVal();
     
      String glblIndxKey = getGlblIndxKey();
      String glblIndxVal = ""+getGlblIndxVal();
      String glblCrdntsKey = getGlblCrdntsKey();
      String glblCrdntsVal = ""+getGlblCrdntsVal();
      
      insrtKeyVal(lineKey,lineVal);
      insrtKeyVal(mtrlKey,mtrlVal);
      insrtKeyVal(quadKey,quadVal);
      insrtKeyVal(hxhdrlKey,hxhdrlVal);
      insrtKeyVal(vrtxKey,vrtxVal);  
      insrtKeyVal(glblIndxKey,glblIndxVal);     
      insrtKeyVal(glblCrdntsKey,glblCrdntsVal);         
      return super.getKeyValMap();
   }
    public void updtKeyVals(HashMap aLinkedHashMap) {
        
      
      String aKey = getRndrKey();
      String aVal = (String)aLinkedHashMap.get(aKey);
      boolean aBln = Boolean.parseBoolean(aVal);
      setRndrVal(aBln);
      
      String aKey1 = getQuadKey();
      String aVal1 = (String)aLinkedHashMap.get(aKey1);
      boolean aBln1 = Boolean.parseBoolean(aVal1);
      setQuadVal(aBln1); 
      
      String aKey3 = getVrtxKey();
      String aVal3 = (String)aLinkedHashMap.get(aKey3);
      boolean aBln3 = Boolean.parseBoolean(aVal3);
      setVrtxVal(aBln3);       

      String aKey4 = getGlblIndxKey();
      String aVal4 = (String)aLinkedHashMap.get(aKey4);
      boolean aBln4 = Boolean.parseBoolean(aVal4);
      setGlblIndxVal(aBln4);       

      String aKey5 = getGlblCrdntsKey();
      String aVal5 = (String)aLinkedHashMap.get(aKey5);
      boolean aBln5 = Boolean.parseBoolean(aVal5);
      setGlblCrdntsVal(aBln5);                         

      String aKey6 = getLineKey();
      String aVal6 = (String)aLinkedHashMap.get(aKey6);
      boolean aBln6 = Boolean.parseBoolean(aVal6);
      setLineVal(aBln6);

      String aKey7 = getHxhdrlKey();
      String aVal7 = (String)aLinkedHashMap.get(aKey7);
      boolean aBln7 = Boolean.parseBoolean(aVal7);
      setHxhdrlVal(aBln7);

    }

}