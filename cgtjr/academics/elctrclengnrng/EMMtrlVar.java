/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng;

import cgtjr.academics.general.DataVar;
import java.util.*;

import cgtjr.academics.math.geometry.*;
/**
 *
 * @author clayton g thomas jr
 */
public class EMMtrlVar extends DataVar
{
   private LinkedHashMap aDtValMap;
   private HashSet mtrlVarHshSt;
   private static String dataNmKey		= "constitutive";
   private String idKey                = "id";

   private String mtrlKey              = "material";
   private String prmtvtyKey              = "permitivity";
   private String prmbltyKey              = "permeability";
   private String cndctvtyKey              = "conductivity";

   public final String dscrptnKey      = "dscrptn";

   private String idVal = "";
   private String mtrlVal;
   private double prmtvtyVal;
   private double prmbltyVal;
   private double cndctvtyVal;

   private String dscrptnVal	= "";

   public EMMtrlVar()
   {
      aDtValMap = new LinkedHashMap();
      mtrlVarHshSt = new HashSet();
      //aDtVarVctr = new Vector();
      prmtvtyVal = 1;
      prmbltyVal = 1;
      cndctvtyVal = 1;
   }
   public EMMtrlVar(double myPrmtvty,double myPrmblty,double myCndctvty)
   {
      aDtValMap = new LinkedHashMap();
      mtrlVarHshSt = new HashSet();
      prmtvtyVal = myPrmtvty;
      prmbltyVal = myPrmblty;
      cndctvtyVal = myCndctvty;
   }
   public EMMtrlVar(String myMtrlVal,double myPrmtvty,double myPrmblty,double myCndctvty)
   {
      aDtValMap = new LinkedHashMap();
      mtrlVal = myMtrlVal;
      prmtvtyVal = myPrmtvty;
      prmbltyVal = myPrmblty;
      cndctvtyVal = myCndctvty;
   }
   public static String getDataNmKey()
   {
      return dataNmKey;
   }
   public String getIdKey()
   {
      return idKey;
   }
   public String getMtrlVal()
   {
      return mtrlVal;
   }
   public String getPrmtvtyKey()
   {
      return prmtvtyKey;
   }
   public String getCndctvtyKey()
   {
      return cndctvtyKey;
   }
   public String getPrmbltyKey()
   {
      return prmbltyKey;
   }
   public String getDscrptnKey()
   {
      return dscrptnKey;
   }
   public void setIdVal(String aIdVal)
   {
      idVal = aIdVal;
   }
   public void setMtrlVal(String anMtrlVal)
   {
      mtrlVal = anMtrlVal;
   }
   public void setPrmtvtyVal(double anPrmtvtyVal)
   {
      prmtvtyVal = anPrmtvtyVal;
   }
   public void setCndctvtyVal(double anCndctvtyVal)
   {
      cndctvtyVal = anCndctvtyVal;
   }
   public void setDscrptnVal(String aDscrptnVal)
   {
      dscrptnVal = aDscrptnVal;
   }
   public String getIdVal()
   {
      return idVal;
   }

   public double getPrmtvtyVal()
   {
      return prmtvtyVal;
   }
   public double getCndctvtyVal()
   {
      return cndctvtyVal;
   }
   public double getPrmbltyVal()
   {
      return prmbltyVal;
   }
   public String getDscrptnVal()
   {
      return dscrptnVal;
   }
   public HashMap rtrvHshMp()
   {
      String prmtvtyKey = getPrmtvtyKey();
      String prmtvtyVal = ""+getPrmtvtyVal();
      String prmbltyKey = getPrmbltyKey();
      String prmbltyVal = ""+getPrmbltyVal();
      String cndctvtyKey = getCndctvtyKey();
      String cndctvtyVal = ""+getCndctvtyVal();

      aDtValMap.put(prmtvtyKey,prmtvtyVal);
      aDtValMap.put(prmbltyKey,prmbltyVal);
      aDtValMap.put(cndctvtyKey,cndctvtyVal);
      return aDtValMap;
   }
   public HashSet rtrvHshSt()
   {
      if(mtrlVarHshSt == null)
      {
         mtrlVarHshSt = new HashSet();
       EMMtrlVar anEMMtrlVar1 = new EMMtrlVar("Silver",1,0.99998,6.17*Math.pow(10,7));
       EMMtrlVar anEMMtrlVar2 = new EMMtrlVar("Copper",1,0.99999,5.80*Math.pow(10,7));
       EMMtrlVar anEMMtrlVar3 = new EMMtrlVar("Gold",1,0.99996,4.10*Math.pow(10,7));
       EMMtrlVar anEMMtrlVar4 = new EMMtrlVar("Black Polyethylene",2.3,0,1);
       EMMtrlVar anEMMtrlVar5 = new EMMtrlVar("Aluminum",2.3,1.000021,3.54*Math.pow(10,7));
       EMMtrlVar anEMMtrlVar6 = new EMMtrlVar("Glass",5.0,1,1*Math.pow(10,-12));
       EMMtrlVar anEMMtrlVar7 = new EMMtrlVar("Porcelain",5.7,1,2*Math.pow(10,-13));
       EMMtrlVar anEMMtrlVar8 = new EMMtrlVar("sea water",72,1,4);
       EMMtrlVar anEMMtrlVar9 = new EMMtrlVar("water",80,1,1*Math.pow(10,-3));
       EMMtrlVar anEMMtrlVar10 = new EMMtrlVar("iron",1,4000,1*Math.pow(10,7));
       //EMMtrlVar anEMMtrlVar4 = new EMMtrlVar("Brass",1,?,1.57*Math.pow(10,7));
         mtrlVarHshSt.add(anEMMtrlVar1);
         mtrlVarHshSt.add(anEMMtrlVar2);
         mtrlVarHshSt.add(anEMMtrlVar3);
         mtrlVarHshSt.add(anEMMtrlVar4);
         mtrlVarHshSt.add(anEMMtrlVar5);
         mtrlVarHshSt.add(anEMMtrlVar6);
         mtrlVarHshSt.add(anEMMtrlVar7);
         mtrlVarHshSt.add(anEMMtrlVar8);
         mtrlVarHshSt.add(anEMMtrlVar9);
         mtrlVarHshSt.add(anEMMtrlVar10);   
      }
      return mtrlVarHshSt;
   }
}