/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.radiosity;

import cgtjr.academics.elctrclengnrng.fem.*;
import cgtjr.academics.general.colorspace.ColorUpdtr;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.general.gui.IntnstyNmbrDflt;
import cgtjr.academics.elctrclengnrng.fem.gui.pnl.SparseMtrxDflt;
import java.util.*;

import cgtjr.academics.general.*;

/**
 *
 * @author clayton g thomas jr
 */

public class RadiosityPrcss extends DataPrcss
{
   private ShapePnt shp;
   private NdlElmnts anNdlElmnts;
   private BssFnctn basisFnctn;
   private SparseMtrxVar sprsMtrxVar;
   
   
   
   public RadiosityPrcss(MeshShp myShape,NdlElmnts myNdlElmnts)
   {
      shp = myShape;
      anNdlElmnts = myNdlElmnts;
      //basisFnctn = shp.rtrvBssFnctn();
      basisFnctn = BssFnctnShpeBndry.rtrvBssFnctn(shp);      
      sprsMtrxVar = new SparseMtrxVar();
   }
   public RadiosityPrcss(ShapePnt myShape,ShpBndry myShpBndry)
   {       
      shp = myShape;
      anNdlElmnts = new NdlElmnts(myShape,myShpBndry);
      //basisFnctn = shp.rtrvBssFnctn();
      basisFnctn = BssFnctnShpeBndry.rtrvBssFnctn(shp);      
      sprsMtrxVar = new SparseMtrxVar();      
   }

   public void prcss()
   {
      //Enumerate through shapes to get total width,length,&height or update boudnary information
      //int nmbrPnts = (int)((getParam1()/dltX+1)*(getParam2()/dltY+1)*(getParam3()/dltZ+1));

      //TODO: should declare vector type <>
      Vector aVector = shp.rtrvElmnts();
      anNdlElmnts.insrtElmntLclNds(aVector);

      Solver aSolver = new Solver(anNdlElmnts,basisFnctn);
      aSolver.prcss();
      double aMatrix[][] = aSolver.getSprstyMtrx();
            
      float myMaxValue = (float)aSolver.getMaxValue();
      float myMinValue = (float)aSolver.getMinValue();
      IntnstyNmbrVar aClrRngVar = IntnstyNmbrDflt.getDfltVar();
      aClrRngVar.setMaxIntnstyVal(myMaxValue);
      aClrRngVar.setMinIntnstyVal(myMinValue);
      double aValue[] = aSolver.getValue();

      ValueUpdtr aValueUpdtr = new ValueUpdtr(aValue);
      aValueUpdtr.update(shp);

      sprsMtrxVar.setSprsMtrx(aMatrix);
      SparseMtrxDflt.setDfltVar(sprsMtrxVar);  

      //ColorUpdtr aColorUpdtr = new ColorUpdtr(aValue,100);
      ColorUpdtr aColorUpdtr = new ColorUpdtr(aValue,myMinValue,myMaxValue);      
      aColorUpdtr.update(shp);
   }
}