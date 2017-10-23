package cgtjr.academics.chmstry.gui.pnl;

import cgtjr.academics.chmstry.gui.bttn.*;
import cgtjr.academics.general.LabPnl;
import cgtjr.academics.general.SceneRoot;

public class ChrgeIcnPnl extends LabPnl {

    SceneRoot scnRt;

    public ChrgeIcnPnl(SceneRoot mySceneRoot) {
        super(mySceneRoot);
        scnRt = mySceneRoot;
        addCmpnnts();
    }

    public void addCmpnnts() {
        //SceneRoot scnRt = getScnRt();
        //ChemVltgHxhdrlBttn aChemVltgHxhdrlBttn = new ChemVltgHxhdrlBttn(scnRt);
        ChrgHelixBttn aChrgHelixBttn = new ChrgHelixBttn(scnRt);
        ChrgHelixAutoBttn aChrgHelixAutoBttn = new ChrgHelixAutoBttn(scnRt);        
        //RfndChrgClndrBttn aRfndChrgClndrBttn = new RfndChrgClndrBttn(scnRt);
        //RfndChrgeCrtssnBttn aRfndChrgeCrtssnBttn = new RfndChrgeCrtssnBttn(scnRt);

        //add(aChemVltgHxhdrlBttn);
        add(aChrgHelixAutoBttn);
        add(aChrgHelixBttn);
        //add(aChemVltgClndrBttn);
        //add(aChemVltgSphrBttn);
        //add(aRfndVltgSphrBttn);
        //add(aRfndVltgClndrBttn);
        //add(aRfndChrgClndrBttn);
        //add(aRfndChrgeCrtssnBttn);
    }
}