/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.chmstry.physical;

import cgtjr.academics.general.colorspace.ColorUpdtr;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;

import cgtjr.academics.general.gui.IntnstyNmbrDflt;
import cgtjr.academics.general.*;
import cgtjr.academics.math.sttstcs.VarianceDflt;
import cgtjr.academics.math.sttstcs.VarianceVar;

/**
 *
 * @author clayton g thomas jr
 */

public class ElctrcChrgPrcss extends DataPrcss
{
   ShapePnt shp;

   public ElctrcChrgPrcss(ShapePnt myShape)
   {
      shp = myShape;
   }
   public void prcss()
   {
      //Enumerate through shapes to get total width,length,&height or update boudnary information
      //int nmbrPnts = (int)((getParam1()/dltX+1)*(getParam2()/dltY+1)*(getParam3()/dltZ+1));
      //Vector aVector = shp.getNodes();
      //PrscrbUpdtr aPrscrbUpdtr = new PrscrbUpdtr(anNdlElmnts,shp);
      //aPrscrbUpdtr.updt(shp.getNodes());

      ElctrcChrgUptr aElctrcChrgUptr = new ElctrcChrgUptr();
      aElctrcChrgUptr.update(shp);
      //float maxValue = (float)aElctrcChrgUptr.getMaxValue();
      //float minValue = (float)aElctrcChrgUptr.getMinValue();

      float stndrdDvtn = aElctrcChrgUptr.cmptStdrdDvtn();
      float average =  aElctrcChrgUptr.getAverage();

      float maxValue = (float)aElctrcChrgUptr.getAverage()+stndrdDvtn;
      float minValue = (float)aElctrcChrgUptr.getAverage()-stndrdDvtn;
      IntnstyNmbrVar anIntnstyNmbrVar = (IntnstyNmbrVar)IntnstyNmbrDflt.getDfltVar();
      anIntnstyNmbrVar.setMaxIntnstyVal(maxValue);
      anIntnstyNmbrVar.setMinIntnstyVal(minValue);
      VarianceVar aVarianceVar = (VarianceVar)VarianceDflt.getDfltVar();
      aVarianceVar.setAverageVal(average);
      aVarianceVar.setStndrdDvtnVal(stndrdDvtn);
      
      ColorUpdtr aColorUpdtr = new ColorUpdtr(shp,minValue,maxValue);
      aColorUpdtr.update();
              /*
      System.out.println("Crtssn3DPrcss.prcss() ... new CrtssnShp()");
      DmnsnVar aDmnsnVar = Crtssn3DVar.getDmnsnVar();
      CrtssnShp aCrtssnShp = new CrtssnShp(aDmnsnVar);
      ShpScene shapeScene = new ShpScene(aCrtssnShp);      
      scnRt.addShp3D(shapeScene);
*/
               }
}