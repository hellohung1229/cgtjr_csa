package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.util.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.dmnsvar.AutomaticDmnsnVar;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVarFctry;
import cgtjr.academics.math.geometry.dmnsvar.HelixVar;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class HelixDmnsnPnl1 extends LblTxtFldPnl implements ActionListener {

    private DmnsnVar dmnsnData;
    private JComboBox aJComboBox;
    private HashMap aLinkedHashMap1;
    private HashMap aLinkedHashMap2;
    private DataVarDocLstnr varDocLstnr;

    public HelixDmnsnPnl1(SceneRoot mySceneRoot) {
        super(mySceneRoot);
        constructer(new Crtssn3DVar());
    }

    private void constructer(DmnsnVar myDmnsnVar) {
        dmnsnData = myDmnsnVar;

        varDocLstnr = new DataVarDocLstnr();
        //aLinkedHashMap1 = dmnsnData.crtKeyValMap();
        //aLinkedHashMap2 = dmnsnData.crtKeyVarMap();

        setVarDocLstnr(varDocLstnr);
        //DmnsnFctry.setDfltDmnsnVar(dmnsnData);
        bldGUI();
    }

    public void bldGUI() {
        HelixVar aHelixVar = new HelixVar();
        AutomaticDmnsnVar aAutomaticDmnsnVar = new AutomaticDmnsnVar();        

        //Object keyObjects[] = DmnsnDfltFctry.rtrvDfltKeyVarArry();
        Object keyObjects[] = new Object[2];
        keyObjects[0] = aAutomaticDmnsnVar.getCrdntTpVal();
        keyObjects[1] = aHelixVar.getCrdntTpVal();

        System.out.println("HelixDmnsnPnl:t1 keyObjects size = " + keyObjects.length);
        DmnsnVar aDmnsnVar = (DmnsnVar) DmnsnDfltFctry.getDfltVar(dmnsnData.getCrdntTpVal());
        
        System.out.println("HelixDmnsnPnl:t2 keyObjects[0] = " + keyObjects[0]);
        varDocLstnr.setDataVar(aDmnsnVar);
        System.out.println("HelixDmnsnPnl:t3 keyObjects[1] = " + keyObjects[1]);
        
        LinkedHashMap aLinkedHashMap1 = (LinkedHashMap) aDmnsnVar.getKeyValMap();
        System.out.println("HelixDmnsnPnl:t4 keyObjects size = " + keyObjects.length);

        aJComboBox = new JComboBox(keyObjects);
        aJComboBox.addActionListener(this);
        //aJComboBox.setSelectedIndex(2);
        //aJComboBox.getItemAt(2);
        //JTextField aJTextField = new JTextField(dmnsnData.getDataNmKey());
        insrtInfrmtn(aLinkedHashMap1);
        dsplyCmpnt(aJComboBox);
        dsplyCmpnts();
    }

    public void actionPerformed(ActionEvent myActionEvent) {
        String aKey = (String) aJComboBox.getSelectedItem();
        //aJTextField.setText(myValue);
        System.out.println("HelixDmnsnPnl: key = "+aKey);
        //if(aKey != null && aKey.equalsIgnoreCase("Automatic")){
        //    return;
        //}
        LinkedHashMap aHashMap2 = DmnsnVarFctry.crtHelixAutoMap();
        DmnsnVar dmnsnDataTmp = (DmnsnVar) aHashMap2.get(aKey);
        DmnsnDfltFctry.setDfltVar(dmnsnDataTmp);
        varDocLstnr.setDataVar(dmnsnDataTmp);
        updtCmpnts(dmnsnDataTmp);
        System.out.println("HelixDmnsnPnl: " + dmnsnDataTmp.getCrdntTpVal());
    }

    public void updtCmpnts(DmnsnVar dmnsnDataTmp) {
        LinkedHashMap aLinkedHashMapTmp = null;
        //String aKey = (String)aJComboBox.getSelectedItem();
        //DmnsnVar dmnsnDataTmp = (DmnsnVar)aLinkedHashMap2.get(aKey);
        //aLinkedHashMapTmp = (LinkedHashMap)dmnsnDataTmp.crtKeyValMap();
        aLinkedHashMapTmp = (LinkedHashMap) dmnsnDataTmp.getKeyValMap();
        //Set aKeySet = aLinkedHashMap1.keySet();
        Set aKeySet = aLinkedHashMapTmp.keySet();
        Iterator anIterator1 = aKeySet.iterator();
        while (anIterator1.hasNext()) {
            String aString1 = (String) anIterator1.next();
            String aString2 = (String) aLinkedHashMapTmp.get(aString1);
            updtCmpnt(aString1, aString2);
        }
    }

    public void updtCmpnt(String myKey, String myValue) {
        JTextField aJTextField = null;
        Component smComponents[] = getComponents();
        Component aComponent = null;
        String aCmpnntName = "";
        int aLength = smComponents.length;
        for (int i = 0; i < aLength; i++) {
            aComponent = smComponents[i];
            aCmpnntName = aComponent.getName();
            //aDmnsnValName = (String)aLinkedHashMap1.get(aCmpnntName);
            System.out.println("HelixDmnsnPnl: name = " + aCmpnntName + ",keyname = " + myKey + ",val = " + myValue);
            if (aCmpnntName != null && aCmpnntName.equals(myKey)) {
                aJTextField = (JTextField) aComponent;
                aJTextField.setText(myValue);
                //DmnsnVar dmnsnDataTmp = (DmnsnVar)aLinkedHashMap2.get(aKey);
                //dmnsnDataTmp.updtVals(myKey, myValue);
                System.out.println("HelixDmnsnPnl: setting text =" + myValue);
            }
        }
    }
    /*
     public void updtDmnsnVar(String myKey,String myVal)
     {
     String aKey = (String)aJComboBox.getSelectedItem();
     //aJTextField.setText(myValue);
     DmnsnVar dmnsnDataTmp = (DmnsnVar)aLinkedHashMap2.get(aKey);
     System.out.println("HelixDmnsnPnl: updating coord type = "+dmnsnDataTmp.getCrdntTpVal()+", key = "+myKey+", val = "+myVal);
     dmnsnDataTmp.updtVals(myKey, myVal);
     }*/
}