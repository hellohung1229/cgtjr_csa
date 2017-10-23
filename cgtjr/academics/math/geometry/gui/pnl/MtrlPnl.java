package cgtjr.academics.math.geometry.gui.pnl;


import java.util.*;

import cgtjr.academics.general.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class MtrlPnl extends LblTxtFldPnl implements ActionListener
{
   private MtrlVar mtrlData;
   private JComboBox aJComboBox;
   private HashMap aLinkedHashMap1;
   private DataVarDocLstnr varDocLstnr;
   
   public MtrlPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      constructer(new MtrlVar());
      bldGUI();
   }
   private void constructer(MtrlVar myMtrlVar)
   {

      mtrlData = myMtrlVar;
      varDocLstnr = new DataVarDocLstnr();      
      setVarDocLstnr(varDocLstnr);

  
   }
   public void bldGUI()
   {
      MtrlVar mtrlDataTmp = (MtrlVar)MtrlPnlDflt.getDfltVar(mtrlData.getMtrlVal());
      MtrlPnlDflt.setDfltVar(mtrlDataTmp);
      aLinkedHashMap1 = mtrlDataTmp.getKeyValMap();

      Object keyObjects[] = MtrlPnlDflt.rtrvDfltKeyVarArry();

      System.out.println("MtrlVar: keyObjects size = "+keyObjects.length);
      aJComboBox = new JComboBox(keyObjects);
      aJComboBox.addActionListener(this);
      aJComboBox.setSelectedIndex(0);

      insrtInfrmtn(aLinkedHashMap1);
      dsplyCmpnt(aJComboBox);
      dsplyCmpnts();
   }
   public void actionPerformed(ActionEvent myActionEvent)
   {      
      String aKey = (String)aJComboBox.getSelectedItem();
      System.out.println("MtrlPnl: key = "+aKey);

      MtrlVar mtrlDataTmp = (MtrlVar)MtrlPnlDflt.getDfltVar(aKey);

      System.out.println("MtrlPnl: "+mtrlDataTmp.getMtrlVal());     
      varDocLstnr.setDataVar(mtrlDataTmp);          

      MtrlPnlDflt.setDfltVar(mtrlDataTmp);
      System.out.println("MtrlPnl: "+mtrlDataTmp.getMtrlVal());
      updtCmpnts();
   }

   public void updtCmpnts()
   {
      HashMap aLinkedHashMapTmp = null;

      String aKey = (String)aJComboBox.getSelectedItem();
      MtrlVar mtrlDataTmp = (MtrlVar)MtrlPnlDflt.getDfltVar(aKey);
      System.out.println("MtrlPnl: "+mtrlDataTmp.getMtrlVal());
      aLinkedHashMapTmp = (LinkedHashMap)mtrlDataTmp.getKeyValMap();

      Set aKeySet = aLinkedHashMapTmp.keySet();
      Iterator anIterator1 = aKeySet.iterator();
      while(anIterator1.hasNext())
      {
         String aString1 = (String)anIterator1.next();
         String aString2 = (String)aLinkedHashMapTmp.get(aString1);
         updtCmpnt(aString1,aString2);
      }
   }
   public void updtCmpnt(String myKey,String myValue)
   {
       
      JTextField aJTextField = null;
      Component smComponents[] = getComponents();
      Component aComponent = null;
      String aCmpnntName = "";
      int aLength = smComponents.length;
      for(int i=0;i<aLength;i++)
      {
         aComponent = smComponents[i];
         aCmpnntName = aComponent.getName();

         System.out.println("MtrlPnl: name = "+aCmpnntName+",keyname = "+myKey+",val = "+myValue);
         if(aCmpnntName != null && aCmpnntName.equals(myKey) )
         {
            aJTextField = (JTextField)aComponent;
            aJTextField.setText(myValue);

            System.out.println("MtrlPnl: setting text ="+myValue);
         }
      }
   }
}