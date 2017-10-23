package cgtjr.academics.elctrclengnrng.radiosity.gui.pnl;

import cgtjr.academics.elctrclengnrng.fem.SparseMtrxVar;
import cgtjr.academics.general.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;


public class SprsMtrxPnl extends CmpntPnl
{
   private SceneRoot scnRt;
   private SparseMtrxVar sprsMtrxVar;
   private SprsMtrxImgPnl sprsMtrxData;

   JTextField bandWidthTextField1;
   JTextField bandWidthTextField2;

   public SprsMtrxPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      setBackground(Color.white);
      scnRt = mySceneRoot;
      sprsMtrxData = new SprsMtrxImgPnl();
      SparseMtrxVar sprsMtrxVar = new SparseMtrxVar();
      SparseMtrxDflt.setDfltVar(sprsMtrxVar);  
      this.addCmpnnts();
   }  
   public void addCmpnnts() 
   {
      dsplyCmpnt(sprsMtrxData);
      
   }
   public void paintComponent(Graphics myGraphics)
   {
      super.paintComponent(myGraphics);
      //clrRngVar = SparseMtrxDflt.getDfltVar();
      //updtCmpnt(clrRngVar.getbandWidthIntnstyKey(),""+clrRngVar.getbandWidthIntnstyVal());
   }
   public void updtCmpnt(String myKey,String myValue)
   {
      if(myKey.equals(sprsMtrxVar.getBandWidthKey()))
      {
         bandWidthTextField2.setText(myValue);
      }
   }
}