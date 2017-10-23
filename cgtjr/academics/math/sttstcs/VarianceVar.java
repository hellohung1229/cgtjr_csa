package cgtjr.academics.math.sttstcs;

import cgtjr.academics.general.DataVar;

/**
 * A class to provide basic get and set methods for variance values.
 * The class provides key / val functions for managing attributes.
 * The keys-val functions are suitable for maintaining data in conjunction
 * with classes that implement the map interface
 * @author clayton g thomas jr
 */
public class VarianceVar extends DataVar
{
   private static String dataNmKey	= "variance";
   private String stndrdDvtnKey        = "standard dev.";
   private String varianceKey          = "variance";

   private double minValueVal;
   private double maxValueVal;
   private double meanVal;
   private int countVal;
   private double sumVal;
   private double averageVal;
   private double stndrdDvtnVal;
   private double varianceVal;

   private String minValueKey;
   private String maxValuekey;
   private String meanKey;
   private String countKey;
   private String sumKey;
   private String averageKey = "average";

   /**
    * A method to retrieve the data name key
    * @return String value of the data name key
    */
   public static String getDataNmKey()
   {
      return dataNmKey;
   }
   /**
    * A method to retrieve the average key
    * @return String value for average key
    */
   public String getAverageKey() {
      return averageKey;
   }
   /**
    * A method to retrieve key associated with the count
    * @return String value
    */
   public String getCountKey() {
      return countKey;
   }
   /**
    * A method associated with retrieving the key value associated
    * with the max value
    * @return String key
    */
   public String getMaxValuekey() {
      return maxValuekey;
   }
   /**
    * A method for retrieving the key associated with the mean
    * @return String key
    */
   public String getMeanKey() {
      return meanKey;
   }
   /**
    * A method for retrieving the key associated with the min
    * @return
    */
   public String getMinValueKey() {
      return minValueKey;
   }
   /**
    * A method for retrieving the key associated with the standard deviation
    * @return standard deviation key
    */
   public String getStndrdDvtnKey() {
      return stndrdDvtnKey;
   }
   /**
    * A method for retrieving the key associated with the sum
    * @return sum key
    */
   public String getSumKey() {
      return sumKey;
   }
   /**
    * A method for retrieving the key associated with the variance
    * @return
    */
   public String getVarianceKey() {
      return varianceKey;
   }

   /**
    * A method for retrieving the value associated the average
    * @return
    */
   public double getAverageVal() {
      return averageVal;
   }
   /**
    * A method for retrieving the value associated with the count
    * @return
    */
   public int getCountVal() {
      return countVal;
   }
   /**
    * A method associated with retrieving the max value
    * @return maximum value
    */
   public double getMaxValueVal() {
      return maxValueVal;
   }
   /**
    * A method associated with retrieving the mean value
    * @return
    */
   public double getMeanVal() {
      return meanVal;
   }
   /**
    * A method for retrieving the min value
    * @return min value
    */
   public double getMinValueVal() {
      return minValueVal;
   }
   /**
    * A method for retrieving the standard deviation
    * @return standard deviation
    */
   public double getStndrdDvtnVal() {
      return stndrdDvtnVal;
   }
   /**
    * A method for retrieving the sum
    * @return
    */
   public double getSumVal() {
      return sumVal;
   }
   /**
    * A method for getting the variance value
    * @return variance value
    */
   public double getVarianceVal() {
      return varianceVal;
   }
   /**
    * A method for setting the variance values
    * @param averageVal - avarage value
    */
   public void setAverageVal(double averageVal) {
      this.averageVal = averageVal;
   }
   /**
    * 
    * @param countVal
    */
   public void setCountVal(int countVal) {
      this.countVal = countVal;
   }
   public void setMaxValueVal(double maxValueVal) {
      this.maxValueVal = maxValueVal;
   }
   public void setMeanVal(double meanVal) {
      this.meanVal = meanVal;
   }

   public void setMinValueVal(double minValueVal) {
      this.minValueVal = minValueVal;
   }
   public void setStndrdDvtnVal(double stndrdDvtnVal) {
      this.stndrdDvtnVal = stndrdDvtnVal;
   }
   public void setSumVal(double sumVal) {
      this.sumVal = sumVal;
   }
   public void setVarianceVal(double varianceVal) {
      this.varianceVal = varianceVal;
   }
}