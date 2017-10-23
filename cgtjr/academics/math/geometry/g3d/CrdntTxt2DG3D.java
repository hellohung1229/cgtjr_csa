/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author clayton g thomas jr
 */
public class CrdntTxt2DG3D extends Text2DG3D
{ 
   public CrdntTxt2DG3D(ShapePnt myShape)
   {
      super(myShape);
      //setYOffSet(-.07f);
      rndrElmnt();
   }
   public String rtrvString(Pnt myPoint)
   {
      String xStrng = retrveStrng(""+myPoint.getX1());
      String yStrng = retrveStrng(""+myPoint.getY1());
      String zStrng = retrveStrng(""+myPoint.getZ1());
      
      String info = xStrng+","+yStrng+","+zStrng;
      return info;
   }
   public String retrveStrng(String myString){
       int decIndex=myString.indexOf('.');
       int endIndex=myString.length();
       int diff = endIndex - decIndex;
       String nwStrng = "";
       if(diff > 4 ){
           nwStrng = myString.substring(0,decIndex+3);
           return nwStrng;
       }else{
           return myString;
       }
       
   }
}