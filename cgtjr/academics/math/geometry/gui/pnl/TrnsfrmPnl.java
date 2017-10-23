/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.gui.pnl;


import cgtjr.academics.math.geometry.dmnsvar.TrnsfrmVar;
import cgtjr.academics.math.geometry.gui.app.GmtryStation;
import cgtjr.academics.math.geometry.*;
import cgtjr.academics.math.geometry.g3d.*;

import cgtjr.academics.general.*;

/**
 *
 * @author clayton g thomas jr
 */
public class TrnsfrmPnl extends TablePnl
{
   TrnsfrmVar trnsfrmDt;
   ShapeG3D shpG3D;

   public TrnsfrmPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      trnsfrmDt = new TrnsfrmVar();
      shpG3D = GmtryStation.getShpG3D();
      bldGUI();
   }
   public void bldGUI()
   {
      String rows[][] = rtrvRows();
      String cols[] = rtrvCols();
      crtScrllTbl(rows, cols);
      /*
      JTable table = new JTable(rows,cols);
      setTable(table);
      JScrollPane scrllPn = new JScrollPane(table);
      add(BorderLayout.CENTER,scrllPn);
      */
   }
   public String[] rtrvCols()
   {
      String colTitle[] = {"property","value"};
      return colTitle;
   }
   public String[][] rtrvRows()
   {
      String trnsltXKey = trnsfrmDt.getTrnsltXKey();
      String trnsltXVal = ""+trnsfrmDt.getTrnsltXVal();
      String trnsltYKey = trnsfrmDt.getTrnsltYKey();
      String trnsltYVal = ""+trnsfrmDt.getTrnsltYVal();
      String trnsltZKey = trnsfrmDt.getTrnsltZKey();
      String trnsltZVal = ""+trnsfrmDt.getTrnsltZVal();
      String rotateXKey = trnsfrmDt.getRotateXKey();
      String rotateXVal = ""+trnsfrmDt.getRotateXVal();
      String rotateYKey = trnsfrmDt.getRotateYKey();
      String rotateYVal = ""+trnsfrmDt.getRotateYVal(); 
      String rotateZKey = trnsfrmDt.getRotateZKey();
      String rotateZVal = ""+trnsfrmDt.getRotateZVal();       
      String scaleXKey = trnsfrmDt.getScaleXKey();
      String scaleXVal = ""+trnsfrmDt.getScaleXVal();
      String scaleYKey = trnsfrmDt.getScaleYKey();
      String scaleYVal = ""+trnsfrmDt.getScaleYVal();
      String scaleZKey = trnsfrmDt.getScaleZKey();
      String scaleZVal = ""+trnsfrmDt.getScaleZVal();

      String colTitle[][] = {{trnsltXKey,trnsltXVal},
                             {trnsltYKey,trnsltYVal},
                             {trnsltZKey,trnsltZVal},
                             {rotateXKey,rotateXVal},
                             {rotateYKey,rotateYVal},
                             {rotateZKey,rotateZVal},
                             {scaleXKey,scaleXVal},
                             {scaleYKey,scaleYVal},
                             {scaleZKey,scaleZVal}};
      return colTitle;
   }
}