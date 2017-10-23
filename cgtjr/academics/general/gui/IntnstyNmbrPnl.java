package cgtjr.academics.general.gui;

import cgtjr.academics.general.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;


public class IntnstyNmbrPnl extends CmpntPnl
{
   private SceneRoot scnRt;
   private IntnstyNmbrVar clrRngVar;
   private IntnstyClrPnl colorRngCnvs;

   JTextField maxTextField1;
   JTextField maxTextField2;
   JTextField minTextField1;
   JTextField minTextField2;

   public IntnstyNmbrPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;
      colorRngCnvs = new IntnstyClrPnl();
      clrRngVar = new IntnstyNmbrVar();
      IntnstyNmbrDflt.setDfltVar(clrRngVar);
      this.addCmpnnts();
   }  
   public void addCmpnnts() 
   {
      String minClrKey = clrRngVar.getMinIntnstyKey();
      float minClrVal = clrRngVar.getMinIntnstyVal();
      String maxClrKey = clrRngVar.getMaxIntnstyKey();
      float maxClrVal = clrRngVar.getMaxIntnstyVal();

      maxTextField1 = new JTextField();
      maxTextField2 = new JTextField();
      minTextField1 = new JTextField();
      minTextField2 = new JTextField();

      maxTextField1.setText(maxClrKey);
      maxTextField2.setName(maxClrKey);
      maxTextField2.setText(""+maxClrVal);
      minTextField1.setText(minClrKey);
      minTextField2.setName(""+minClrKey);
      minTextField2.setText(""+minClrVal);
      
      dsplyCmpnts(maxTextField1,maxTextField2);
      dsplyCmpnt(colorRngCnvs);
      dsplyCmpnts(minTextField1,minTextField2);
      
   }
   public void paintComponent(Graphics myGraphics)
   {
      super.paintComponent(myGraphics);
      clrRngVar = IntnstyNmbrDflt.getDfltVar();
      System.out.println("IntnstyNmbrPnl: max intensity = "+clrRngVar.getMaxIntnstyVal()+", clrRngVar.getMinIntnstyVal()"+clrRngVar.getMinIntnstyVal());
      if(clrRngVar.getMaxIntnstyVal() == 0 && clrRngVar.getMinIntnstyVal() == 0)
      {
         Color aColor = getBackground();
         colorRngCnvs.setMaxColor(aColor);
         colorRngCnvs.setMinColor(aColor);
      }else{
         colorRngCnvs.setMaxColor(Color.blue);
         colorRngCnvs.setMinColor(Color.red);
      }

      updtCmpnt(clrRngVar.getMaxIntnstyKey(),""+clrRngVar.getMaxIntnstyVal());
      updtCmpnt(clrRngVar.getMinIntnstyKey(),""+clrRngVar.getMinIntnstyVal());
   }
   public void updtCmpnt(String myKey,String myValue)
   {
      if(myKey.equals(clrRngVar.getMaxIntnstyKey()))
      {
         maxTextField2.setText(myValue);
      }else if(myKey.equals(clrRngVar.getMinIntnstyKey()))
      {
         minTextField2.setText(myValue);
      }
   }
}