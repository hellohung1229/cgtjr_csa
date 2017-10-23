/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.chmstry.gui.pnl;

import cgtjr.academics.general.gui.IntnstyNmbrPnl;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.sttstcs.VarianceDflt;
import cgtjr.academics.math.sttstcs.VarianceVar;
import java.awt.Graphics;
import javax.swing.JTextField;

/**
 *
 * @author clayton g thomas jr
 */
public class VrncPnl extends IntnstyNmbrPnl
{
   VarianceVar vrncVar;

   JTextField avrgTextField1;
   JTextField avrgTextField2;
   JTextField vrncTextField1;
   JTextField vrncTextField2;
   JTextField stndrdDvtnTextField1;
   JTextField stndrdDvtnTextField2;

   public VrncPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);

      vrncVar = new VarianceVar();
      VarianceDflt.setDfltVar(vrncVar);

      avrgTextField1 = new JTextField();
      avrgTextField2 = new JTextField();
      vrncTextField1 = new JTextField();
      vrncTextField2 = new JTextField();
      stndrdDvtnTextField1 = new JTextField();
      stndrdDvtnTextField2 = new JTextField();
      this.addCmpnnts2();
   }
   private void addCmpnnts2()
   {
      String avrgKey = vrncVar.getAverageKey();
      double avrgVal = vrncVar.getAverageVal();
      String varianceKey = vrncVar.getVarianceKey();
      double varianceVal = vrncVar.getVarianceVal();
      String stndrdDvtnKey = vrncVar.getStndrdDvtnKey();
      double stndrdDvtnVal = vrncVar.getStndrdDvtnVal();

      avrgTextField1.setText(avrgKey);
      avrgTextField2.setName(avrgKey);
      avrgTextField2.setText(""+avrgVal);
      vrncTextField1.setText(varianceKey);
      vrncTextField2.setText(varianceKey);
      vrncTextField2.setName(""+varianceVal);
      stndrdDvtnTextField1.setText(stndrdDvtnKey);
      stndrdDvtnTextField2.setName(stndrdDvtnKey);
      stndrdDvtnTextField2.setText(""+stndrdDvtnVal);

      dsplyCmpnts(avrgTextField1,avrgTextField2);
      dsplyCmpnts(stndrdDvtnTextField1,stndrdDvtnTextField2);
   }
   public void paintComponent(Graphics myGraphics)
   {
      super.paintComponent(myGraphics);
      vrncVar = (VarianceVar)VarianceDflt.getDfltVar();
      updtCmpnt2(vrncVar.getAverageKey(),""+vrncVar.getAverageVal());
      updtCmpnt2(vrncVar.getStndrdDvtnKey(),""+vrncVar.getStndrdDvtnVal());
   }
   public void updtCmpnt2(String myKey,String myValue)
   {
      //super.updtCmpnt(myKey, myValue);
      if(myKey.equals(vrncVar.getAverageKey()))
      {
         avrgTextField2.setText(myValue);
      }else if(myKey.equals(vrncVar.getStndrdDvtnKey()))
      {
         stndrdDvtnTextField2.setText(myValue);
      }
   }
}
