/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.general.colorspace.ColorUpdtr;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import java.util.*;

import cgtjr.academics.general.*;

/**
 *
 * @author clayton g thomas jr
 */

public class TrnglrFEMPrcss1 extends DataPrcss
{
   private ShapePnt shp;
   private NdlElmnts anNdlElmnts;
   private BssFnctn basisFnctn;

   public TrnglrFEMPrcss1(MeshShp myShape,NdlElmnts myNdlElmnts)
   {
      shp = myShape;
      anNdlElmnts = myNdlElmnts;
      //basisFnctn = shp.rtrvBssFnctn();
      basisFnctn = BssFnctnShpeBndry.rtrvBssFnctn(shp);
   }
   public TrnglrFEMPrcss1(ShapePnt myShape,ShpBndry myShpBndry)
   {       
      shp = myShape;
      anNdlElmnts = new NdlElmnts(myShape,myShpBndry);
      anNdlElmnts.setLclSize(3);
      //basisFnctn = shp.rtrvBssFnctn();
      basisFnctn = BssFnctnShpeBndry.rtrvBssFnctn(shp);
   }

   public void prcss()
   {
      //Enumerate through shapes to get total width,length,&height or update boudnary information
      //int nmbrPnts = (int)((getParam1()/dltX+1)*(getParam2()/dltY+1)*(getParam3()/dltZ+1));

      Vector aVector = shp.rtrvElmnts();
      anNdlElmnts.insrtElmntLclNds(aVector);

      Solver aSolver = new Solver(anNdlElmnts,basisFnctn);
      aSolver.prcss();

      double myMaxValue = aSolver.getMaxValue();
      double aValue[] = aSolver.getValue();

      ValueUpdtr aValueUpdtr = new ValueUpdtr(aValue);
      aValueUpdtr.update(shp);
      ColorUpdtr aColorUpdtr = new ColorUpdtr(aValue,100);
      aColorUpdtr.update(shp);
      System.out.println("TrnglrFEMPrcss1: test ... ");
   }
}