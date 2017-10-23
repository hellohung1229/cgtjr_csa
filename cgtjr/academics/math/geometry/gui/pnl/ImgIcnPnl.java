package cgtjr.academics.math.geometry.gui.pnl;

import cgtjr.academics.general.LabPnl;
import cgtjr.academics.general.SceneRoot;
import cgtjr.academics.math.geometry.gui.bttn.*;
import java.applet.Applet;

/**
 * The ImgIcnPnl (image icon panel) is responsible for containing the icon
 * buttons.  Each button corresponds to a difference coordinate object or 
 * primitive.
 * @author clayton g thomas jr
 */
public class ImgIcnPnl extends LabPnl {

    private Crtssn3DBttn aCrtssn3DJBttn;
    private Sphrcl3DBttn aSphrcl3DBttn;
    private HelixBttn aHelixBttn;
    private ClndrclBttn aClndrclBttn;
    private OblateSphrdlBttn aOblateSphrdlBttn;    
    private KnotBttn aKnotBttn;    
    private TrnglVlmnBttn aTrnglVlmnBttn;
    private HxgnVlmnBttn aHxgnVlmnBttn;
    private TrdlVlmnBttn aTrdlVlmnBttn;
    private SphrclSrfcBttn aSphrclSrfcBttn;
    private ZSinXYBttn aZSinXYBttn;
    private ConfocalEllpsdlBttn aConfocalEllpsdlBttn;
    private ConicalBttn aConicalBttn;
    private EllptcClndrclBttn aEllptcClndrclBttn;    
    private ParabolicBttn aParabolicBttn;        
    private BiSphericalBttn aBiSphericalBttn;      
    /**
     * Instantiates an image icon panel with a reference to the scene root.
     * @param mySceneRoot represents  and SceneRot (scene root) object.
     */
    public ImgIcnPnl(SceneRoot mySceneRoot) {
        super(mySceneRoot);
        crtBttns();
        addCmpnnts();
    }

    /**
     * Instantiates an image icon panel (ImgIcnPnl) with a reference to 
     * the scene root and applet container.
     * @param mySceneRoot represents a scene root object
     * @param myLabWndwPnl represents the applet container
     */
    public ImgIcnPnl(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        super(mySceneRoot);
        crtBttns(mySceneRoot, myLabWndwPnl);
        addCmpnnts();
    }
    private void crtBttns(SceneRoot mySceneRoot, Applet myLabWndwPnl) {
        SceneRoot scnRt = mySceneRoot;
        aCrtssn3DJBttn = new Crtssn3DBttn(scnRt, myLabWndwPnl);
        aSphrcl3DBttn = new Sphrcl3DBttn(scnRt, myLabWndwPnl);
        aHelixBttn = new HelixBttn(scnRt, myLabWndwPnl);
        aClndrclBttn = new ClndrclBttn(scnRt, myLabWndwPnl);
        aOblateSphrdlBttn = new OblateSphrdlBttn(scnRt, myLabWndwPnl); 
        aConfocalEllpsdlBttn = new ConfocalEllpsdlBttn(scnRt, myLabWndwPnl);         
        aKnotBttn = new KnotBttn(scnRt);    
        //aTrnglVlmnBttn = new TrnglVlmnBttn(scnRt, myLabWndwPnl);
        aHxgnVlmnBttn = new HxgnVlmnBttn(scnRt, myLabWndwPnl);
        aTrdlVlmnBttn = new TrdlVlmnBttn(scnRt, myLabWndwPnl);
        aSphrclSrfcBttn = new SphrclSrfcBttn(scnRt, myLabWndwPnl);
        aZSinXYBttn = new ZSinXYBttn(scnRt, myLabWndwPnl);
        aConicalBttn = new ConicalBttn(scnRt, myLabWndwPnl);        
        aEllptcClndrclBttn = new EllptcClndrclBttn(scnRt, myLabWndwPnl);                
        aParabolicBttn = new ParabolicBttn(scnRt, myLabWndwPnl);
        aBiSphericalBttn = new BiSphericalBttn(scnRt, myLabWndwPnl);        
    }
    private void crtBttns() {
        SceneRoot scnRt = getScnRt();
        aCrtssn3DJBttn = new Crtssn3DBttn(scnRt);
        aSphrcl3DBttn = new Sphrcl3DBttn(scnRt);
        aHelixBttn = new HelixBttn(scnRt);
        aClndrclBttn = new ClndrclBttn(scnRt);
        aKnotBttn = new KnotBttn(scnRt);        
        //aTrnglVlmnBttn = new TrnglVlmnBttn(scnRt);
        aHxgnVlmnBttn = new HxgnVlmnBttn(scnRt);
        aTrdlVlmnBttn = new TrdlVlmnBttn(scnRt);
        aSphrclSrfcBttn = new SphrclSrfcBttn(scnRt);
        aZSinXYBttn = new ZSinXYBttn(scnRt);
        aOblateSphrdlBttn = new OblateSphrdlBttn(scnRt);        
        aConicalBttn = new ConicalBttn(scnRt);         
        aEllptcClndrclBttn = new EllptcClndrclBttn(scnRt);                     
        aParabolicBttn = new ParabolicBttn(scnRt);
    }
    private void addCmpnnts() {
        add(aCrtssn3DJBttn);
        add(aSphrcl3DBttn);
        add(aHelixBttn);
        add(aClndrclBttn);
        //add(aTrnglVlmnBttn);
        //add(aHxgnVlmnBttn);
        add(aTrdlVlmnBttn);
        //add(aKnotBttn);        
        add(aOblateSphrdlBttn);                
        //add(aConfocalEllpsdlBttn);           
        add(aConicalBttn);        
        add(aEllptcClndrclBttn);
        add(aParabolicBttn);        
        add(aBiSphericalBttn);                
        //add(aSphrclSrfcBttn);
        //add(aZSinXYBttn);
    }
}