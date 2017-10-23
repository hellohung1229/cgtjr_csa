package cgtjr.academics.elctrclengnrng.radiosity.gui.pnl;

import cgtjr.academics.general.DataVar;
import java.util.*;
import cgtjr.academics.math.geometry.*;


public class WvEqtnVar extends DataVar
{
   private LinkedHashMap aDtValMap;

   private static String dataNmKey		= "wave equation";
   private String idKey                = "id";
   
   private String wvNmbrKey              = "wvNmbr";
   private String sourceKey              = "render";

   public final String dscrptnKey      = "dscrptn";

   private String idVal = "";
   private double wvNmbrVal;
   private double sourceVal;

   private String dscrptnVal	= "";

   public WvEqtnVar()
   {
      aDtValMap = new LinkedHashMap();
      //aDtVarVctr = new Vector();
      wvNmbrVal = 0;
      sourceVal = 0;
   }
   public WvEqtnVar(double myDmnsn1,double myWvNmbr,double myDmnsn3,double myVrtx,double myDelta2,double myDelta3)
   {
      aDtValMap = new LinkedHashMap();
      //aDtVarVctr = new Vector();
      wvNmbrVal = 0;
      sourceVal = 0;
   }
   public static String getDataNmKey()
   {
      return dataNmKey;
   }
   public String getIdKey()
   {
      return idKey;
   }
   public String getWvNmbrKey()
   {
      return wvNmbrKey;
   }
   public String getSourceKey()
   {
      return sourceKey;
   }
   public String getDscrptnKey()
   {
      return dscrptnKey;
   }
   public void setIdVal(String aIdVal)
   {
      idVal = aIdVal;
   }
   public void setWvNmbrVal(double anWvNmbrVal)
   {
      wvNmbrVal = anWvNmbrVal;
   }
   public void setDscrptnVal(String aDscrptnVal)
   {
      dscrptnVal = aDscrptnVal;
   }
   public String getIdVal()
   {
      return idVal;
   }
   public double getWvNmbrVal()
   {
      return wvNmbrVal;
   }
   public double getSourceVal()
   {
      return sourceVal;
   }
   public String getDscrptnVal()
   {
      return dscrptnVal;
   }
   public HashMap rtrvHshMp()
   {
      String wvNmbrKey = getWvNmbrKey();
      String wvNmbrVal = ""+getWvNmbrVal();
      String sourceKey = getSourceKey();
      String sourceVal = ""+getSourceVal();

      aDtValMap.put(wvNmbrKey,wvNmbrVal);
      aDtValMap.put(sourceKey,sourceVal);
      return aDtValMap;
   }
}