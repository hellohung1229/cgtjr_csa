/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.general.colorspace.ColorUpdtr;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import java.util.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;

/**
 *
 * @author clayton g thomas jr
 */

public class QuadFEMPrcss extends DataPrcss
{
   private ShapePnt shp;
   private NdlElmnts anNdlElmnts;
   private PrscrbNdUpdtr aPrscrbNdUpdtr;
   private Vector aShpBndryGrp;;

   /*
   public FEMPrcss(Shape myShape)
   {
      shp = myShape;
      anNdlElmnts = new NdlElmnts(400,8);
      aShpBndryGrp = new Vector();

   }*/
   public QuadFEMPrcss(ShapePnt myShape,NdlElmnts myNdlElmnts)
   {
      shp = myShape;
      anNdlElmnts = myNdlElmnts;

      /*
      ShpBndry aShpBndry1 = new ShpBndry(0,0,0,4,0,4);
      ShpBndry aShpBndry2 = new ShpBndry(0,4,0,4,4,4);

      aShpBndryGrp.addElement(aShpBndry1);
      aShpBndryGrp.addElement(aShpBndry2);
       */
   }

   public void prcss()
   {
      //Enumerate through shapes to get total width,length,&height or update boudnary information
      //int nmbrPnts = (int)((getParam1()/dltX+1)*(getParam2()/dltY+1)*(getParam3()/dltZ+1));

      Vector aVector = shp.getQuadElmnts();
      anNdlElmnts.insrtElmntLclNds(aVector);

      QuadSolver aQuadSolver = new QuadSolver(anNdlElmnts,new QuadBss());
      aQuadSolver.prcss();

      double myMaxValue = aQuadSolver.getMaxValue();
      double aValue[] = aQuadSolver.getValue();

      ValueUpdtr aValueUpdtr = new ValueUpdtr(aValue);
      aValueUpdtr.update(shp);
      ColorUpdtr aColorUpdtr = new ColorUpdtr(aValue,100);
      aColorUpdtr.update(shp);
   }
}