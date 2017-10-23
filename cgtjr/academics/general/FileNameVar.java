/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.general;

import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.util.HashMap;

/**
 *
 * @author clayton g thomas jr
 */
public class FileNameVar extends DmnsnVar
{
   private final static String dataNmKey		= "filename";
   private String fileNameKey                   = "filename";

   private String fileNameVal;

   public FileNameVar(String myFileName,double myOffSet1,double myOffSet2,double myOffSet3,double myMinDmnsn1,double myMinDmnsn2,double myMinDmnsn3,double myDmnsn1,double myDmnsn2,double myDmnsn3,double myDelta1,double myDelta2,double myDelta3,double myInitX,double myInitY,double myInitZ)
   {
      super(myOffSet1,myOffSet2,myOffSet3,myMinDmnsn1,myMinDmnsn2,myMinDmnsn3,myDmnsn1,myDmnsn2,myDmnsn3,myDelta1,myDelta2,myDelta3,myInitX,myInitY,myInitZ);
      fileNameVal = myFileName;
   }
   public String getFileNameKey()
   {
      return fileNameKey;
   }
   public String getFileNameVal()
   {
      return fileNameVal;
   }
   public void setFileNameVal(String myFileName)
   {
      fileNameVal = myFileName;
   }
   public HashMap crtKeyValMap()
   {
      insrtData(getFileNameKey(),getFileNameVal());
      return super.getKeyValMap();
   }
}
