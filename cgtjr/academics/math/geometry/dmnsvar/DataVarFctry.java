/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.dmnsvar;

import cgtjr.academics.general.DataVar;
import java.util.LinkedHashMap;

/**
 *
 * @author clayton g thomas jr
 */
public abstract class DataVarFctry {

   private LinkedHashMap keyVarMap;

   public DataVarFctry()
   {
      System.out.println("DataVarFctry: test...");
      crtKeyVarMap();
   }
   protected LinkedHashMap crtKeyVarMap()
   {
      keyVarMap = new LinkedHashMap();
      return crtKeyVarMap(keyVarMap);
   }
   protected abstract LinkedHashMap crtKeyVarMap(LinkedHashMap myLinkedHashMap);

   public void insrtKeyVar(String myKey,DataVar myDataVar)
   {
      keyVarMap.put(myKey,myDataVar);
   }
   public LinkedHashMap getKeyVarMap()
   {
      return keyVarMap;
   }
   public Object[] rtrvKeyVarArry()
   {
      return keyVarMap.keySet().toArray();
   }
   public DataVar get(String myKey)
   {
      System.out.println("DataVarFctry: key = "+myKey);
      return (DataVar)keyVarMap.get(myKey);
   }
}