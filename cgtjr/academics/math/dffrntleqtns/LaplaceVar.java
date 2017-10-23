package cgtjr.academics.math.dffrntleqtns;


import cgtjr.academics.general.DataVar;
import java.util.*;

import cgtjr.academics.math.geometry.*;

public class LaplaceVar extends DataVar
{
   private LinkedHashMap aDtValMap;

   private static String dataNmKey		= "laplace";
   private String idKey                = "id";


   public final String dscrptnKey      = "dscrptn";

   private String idVal = "";

   private String dscrptnVal	= "";

   public LaplaceVar()
   {
      aDtValMap = new LinkedHashMap();
      //aDtVarVctr = new Vector();
   }
   public LaplaceVar(double myDmnsn1,double myMesh,double myDmnsn3,double myVrtx,double myDelta2,double myDelta3)
   {
      aDtValMap = new LinkedHashMap();
      //aDtVarVctr = new Vector();
   }
   public static String getDataNmKey()
   {
      return dataNmKey;
   }
   public String getIdKey()
   {
      return idKey;
   }
   public String getDscrptnKey()
   {
      return dscrptnKey;
   }
   public void setIdVal(String aIdVal)
   {
      idVal = aIdVal;
   }
   public void setDscrptnVal(String aDscrptnVal)
   {
      dscrptnVal = aDscrptnVal;
   }
   public String getIdVal()
   {
      return idVal;
   }
   public String getDscrptnVal()
   {
      return dscrptnVal;
   }
   public HashMap rtrvHshMp()
   {
      return aDtValMap;
   }
}