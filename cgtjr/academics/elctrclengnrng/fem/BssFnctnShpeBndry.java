/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.elctrclengnrng.fem.BssFnctn;
import cgtjr.academics.elctrclengnrng.fem.HxhdrlBss;
import cgtjr.academics.elctrclengnrng.fem.QuadBss;
import cgtjr.academics.elctrclengnrng.fem.QuadBssST;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import static cgtjr.academics.math.geometry.shapebndry.ShpBndry.getLclSize;

/**
 *
 * @author finitesystem
 */
public class BssFnctnShpeBndry{
    
   /* 
   public int getLclSize()
   {
      int aValue = getLclSize(this);
      return aValue;
   }*/
   public static int getLclSize(ShpBndry myShape)
   {
      int lclSize = 1;
      double aWidth  = myShape.getXMax() - myShape.getXMin();
      double aHeight = myShape.getYMax() - myShape.getYMin();
      double aLength = myShape.getZMax() - myShape.getZMin();
      if(aWidth != 0)
      {
         lclSize *= 2;
      }
      if(aHeight != 0)
      {
         lclSize *= 2;
      }
      if(aLength != 0)
      {
         lclSize *= 2;
      }
      return lclSize;
   }
   public static BssFnctn rtrvBssFnctn(ShpBndry myShpBndry)
   {
      BssFnctn aBssFnctn = null;
      if(getLclSize(myShpBndry) == 4 && myShpBndry.getCrdntTp().equals(CrdntType.getClndrclCrdntTp()))
      {
          System.out.println("ShpBndry: basis function = QuadBssST");
          aBssFnctn = new QuadBssST();
      }else if(getLclSize(myShpBndry) == 4 && myShpBndry.getCrdntTp().equals(CrdntType.getSphrclCrdntTp()))
      {
          System.out.println("ShpBndry: basis function = QuadBssST");
          aBssFnctn = new QuadBssST();
      }else if(getLclSize(myShpBndry) == 4)
      {
          System.out.println("ShpBndry: basis function = QuadBss");
          aBssFnctn = new QuadBss();
      }else if(getLclSize(myShpBndry) == 8){
          System.out.println("ShpBndry: basis function = HxhdrlBss");
          aBssFnctn = new HxhdrlBss();
      }else{
          System.err.println("ShpBndry: null basis function!");
      }
      return aBssFnctn;
   }
}
