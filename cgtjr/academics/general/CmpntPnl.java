package cgtjr.academics.general;

import cgtjr.academics.math.geometry.gui.pnl.DataVarDocLstnr;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;


public abstract class CmpntPnl extends LabPnl 
{
   private GridBagConstraints aGrdBgCnstrnt;
   private GridBagLayout aGridBagLayout;
   //private SceneRoot scnRt;
   private LinkedHashMap jlblTxtHshMp;
   private int yCrdnt;
   private JLabel prprtyLbl;
   private JLabel valueLbl;
   private JScrollPane scrllPn;
   private JPanel dataPnl;
   private HashMap txtFldMap;
   private DataVarDocLstnr varDocLstnr;

   public CmpntPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      construct(mySceneRoot);
      txtFldMap = new HashMap();
   }
   public void setVarDocLstnr(DataVarDocLstnr myDataVarDocLstnr)
   {
      varDocLstnr = myDataVarDocLstnr;
   }
   private void construct(SceneRoot mySceneRoot)
   {
      intlzLyt();
      //scnRt = mySceneRoot;
      jlblTxtHshMp = new LinkedHashMap();
      prprtyLbl = new JLabel();
      valueLbl = new JLabel();
      dataPnl = new JPanel();

      scrllPn = new JScrollPane();
      //intlzLyt(scrllPn);
      //scrllPn.add(scrllPn);
      //add(scrllPn,BorderLayout.CENTER);
   }
   private void intlzLyt()
   {
      aGridBagLayout = new GridBagLayout();
      setLayout(aGridBagLayout);

      aGrdBgCnstrnt = new GridBagConstraints();
      aGrdBgCnstrnt.weightx = 1;
      aGrdBgCnstrnt.weighty = 1;
      aGrdBgCnstrnt.fill = GridBagConstraints.HORIZONTAL;
      aGrdBgCnstrnt.insets = new Insets(0,0,0,0);

   }
   public void insrtInfrmtn(HashMap myHashMap)
   {
      jlblTxtHshMp.putAll(myHashMap);
   }
   public void insrtInfrmtn(String myStrng1,String  myStrng2)
   {
      jlblTxtHshMp.put(myStrng1,myStrng2);
   }
   public void dsplyCmpnts()
   {
      Set aSet = jlblTxtHshMp.keySet();
      Set anEntrySet = jlblTxtHshMp.entrySet();
      Iterator anIterator1 = aSet.iterator();
      while(anIterator1.hasNext())
      {
         String aString1 = (String)anIterator1.next();
         String aString2 = (String)jlblTxtHshMp.get(aString1);
         dsplyCmpnts(aString1,aString2);
      }    
   }
   public void dsplyCmpnts(String myStrng1,String myStrng2)
   {

         JComponent aJCmpnt1 = crtCmpnnt1(myStrng1,myStrng2);
         JComponent aJCmpnt2 = crtCmpnnt2(myStrng1,myStrng2);
         dsplyCmpnts(aJCmpnt1,aJCmpnt2);
   }
   public JComponent crtCmpnnt1(String myStrng1,String myStrng2)
   {
      JTextField aJTextField1 = new JTextField(myStrng1);
      txtFldMap.put(myStrng1,aJTextField1);
      aJTextField1.setFocusable(false);
      return aJTextField1;
   }
   public JComponent crtCmpnnt2(String myStrng1,String myStrng2)
   {
      PlainDocument aPlainDocument = new PlainDocument();
      aPlainDocument.putProperty("name", myStrng1);
      aPlainDocument.addDocumentListener(varDocLstnr);
      JTextField aJTextField2 = new JTextField(aPlainDocument,myStrng2,20);      
      aJTextField2.setName(myStrng1);
      return aJTextField2;
   }
   public void dsplyCmpnts1(JTextField myJTextField1)
   {
      aGrdBgCnstrnt.weightx = 1;
      aGrdBgCnstrnt.gridx = 0;
      aGrdBgCnstrnt.gridy = yCrdnt;
      aGridBagLayout.setConstraints(myJTextField1,aGrdBgCnstrnt);
   }
 
   public void dsplyCmpnt(JComponent myComponent)
   {
      aGrdBgCnstrnt.gridwidth = 2;
      aGrdBgCnstrnt.weightx = 2;
      aGrdBgCnstrnt.gridx = 0;
      aGrdBgCnstrnt.gridy = yCrdnt;
      aGridBagLayout.setConstraints(myComponent,aGrdBgCnstrnt);
      add(myComponent);
      yCrdnt = yCrdnt + 1;
   }

   public void dsplyCmpnts2(JTextField myJTextField2)
   {
      aGrdBgCnstrnt.weightx = 1;
      aGrdBgCnstrnt.gridx = 1;
      aGrdBgCnstrnt.gridy = yCrdnt;
      aGridBagLayout.setConstraints(myJTextField2,aGrdBgCnstrnt);
   }
   public void dsplyCmpnts(JComponent myJTextField1,JComponent myJTextField2)
   {
      aGrdBgCnstrnt.gridwidth = 1;
      aGrdBgCnstrnt.weightx = 1;
      aGrdBgCnstrnt.gridx = 0;
      aGrdBgCnstrnt.gridy = yCrdnt;
      aGridBagLayout.setConstraints(myJTextField1,aGrdBgCnstrnt);
      aGrdBgCnstrnt.weightx = 1;
      aGrdBgCnstrnt.gridx = 1;
      aGrdBgCnstrnt.gridy = yCrdnt;
      aGridBagLayout.setConstraints(myJTextField2,aGrdBgCnstrnt);

      add(myJTextField1);
      add(myJTextField2);
      yCrdnt = yCrdnt + 1;
   }
   public abstract void updtCmpnt(String myKey,String myValue);
   //public abstract void updtDmnsnVar(String myKey,String myValue);
   /*
   public void dsplyCmpnts(JComponent myJTextField1,JComponent myJTextField2)
   {
   
   }*/
}