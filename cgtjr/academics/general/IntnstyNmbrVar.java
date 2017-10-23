/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
public class IntnstyNmbrVar
{
   private final String varNameKey = "intnsty range";
   private final static String dataNmKey = "intnsty range";

   private String maxIntnstyKey = "max intensity";
   private String minIntnstyKey = "min intensity";

   private float maxIntnstyVal;
   private float minIntnstyVal;

   public IntnstyNmbrVar()
   {
   }
   public static String getDataNmKey() {
      return dataNmKey;
   }
   
   public String getVarNameKey() {
      return varNameKey;
   }


   public String getMaxIntnstyKey() {
      return maxIntnstyKey;
   }

   public float getMaxIntnstyVal() {
      return maxIntnstyVal;
   }

   public float getMinIntnstyVal() {
      return minIntnstyVal;
   }
   public String getMinIntnstyKey() {
      return minIntnstyKey;
   }

   public void setMaxIntnstyVal(float maxClrVal) {
      this.maxIntnstyVal = maxClrVal;
   }

   public void setMinIntnstyVal(float minClrVal) {
      this.minIntnstyVal = minClrVal;
   }
}
