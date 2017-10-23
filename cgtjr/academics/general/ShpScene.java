/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.dmnsvar.RndrngVar;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.g3d.*;
import cgtjr.academics.math.geometry.gui.pnl.RndrngDflt;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import java.util.Vector;
import javax.media.j3d.*;
import javax.swing.JEditorPane;

/**
 *
 * @author clayton g thomas jr
 */
public class ShpScene extends BranchGroup {

    private JEditorPane editorPane;
    private LineG3D crdntLineG3D;
    private NodeG3D crdntNodeG3D;
    private QuadG3D crdntQdG3D;
    private CrdntTxt2DG3D crdntTxt2DG3D;
    private GlblNmbrTxt2DG3D1 glblNmbrTxt2DG3D;
    private ShapePnt shp;
    private Material aMaterial;
    private MtrlVar aMtrlVar;
    private PolygonAttributes pa;
    private Appearance anAppearance;
    private TransparencyAttributes ta;

    /**
     *
     * @param myShape
     */
    public ShpScene(CrdntShp myShape) {
        shp = myShape;
        editorPane = LabStation.getEditorPane();
        setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        //contructor(myShape);
        crtScene();
    }

    public ShpScene(ShapePnt myShape) {
        shp = myShape;
        editorPane = LabStation.getEditorPane();
        setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        //contructor(myShape);
        crtScene();
    }

    /**
     *
     * @param myMeshShp
     */
    public ShpScene(MeshShp myMeshShp) {
        shp = myMeshShp;
        editorPane = LabStation.getEditorPane();
        setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        crtScene();
    }

    private void contructor(CrdntShp myMeshShp) {
        shp = myMeshShp;
    }

    /**
     *
     */
    private void crtScene() {
        //TODO: add global numbers ...
        RndrngVar aRndrngVar = (RndrngVar) RndrngDflt.getDfltVar();

        //glblNmbrTxt2DG3D = new GlblNmbrTxt2DG3D1(shp);
        if (aRndrngVar.getRndrVal() && aRndrngVar.getHxhdrlVal()) {
            System.out.println("ShpScene: hxdrldata ...");
            HxhdrlG3D crdntQdG3D = new HxhdrlG3D(shp);
            GeometryArray aGeometryArray = (GeometryArray) crdntQdG3D.getGeometry();
            GeometryInfo aGeometryInfo = new GeometryInfo(aGeometryArray);
            NormalGenerator ng = new NormalGenerator();
            ng.generateNormals(aGeometryInfo);
            Shape3D aShape3D = new Shape3D();
            aShape3D.setGeometry(aGeometryInfo.getGeometryArray());

            Appearance anApprnce = rtrvApprnc();
            aShape3D.setAppearance(anApprnce);
            addChild(aShape3D);
            //Vector aVector = shp.getHxhdrlElmnts();
            //HxhdrlPnts.prntGlblNmbrs(aVector,editorPane);
        } else if (!aRndrngVar.getRndrVal() && aRndrngVar.getHxhdrlVal()) {
            System.out.println("ShpScene: hxhdrl data ...");
            HxhdrlG3D crdntQdG3D = new HxhdrlG3D(shp);
            addChild(crdntQdG3D);
            Vector aVector = shp.getHxhdrlElmnts();
            shp.cmptWidth();
            //HxhdrlPnts.prntGlblNmbrs(aVector,editorPane);
        } else if (aRndrngVar.getRndrVal() && aRndrngVar.getQuadVal()) {
            System.out.println("ShpScene: test quad data...");
            QuadG3D crdntQdG3D = new QuadG3D(shp);
            GeometryArray aGeometryArray = (GeometryArray) crdntQdG3D.getGeometry();
            GeometryInfo aGeometryInfo = new GeometryInfo(aGeometryArray);
            NormalGenerator ng = new NormalGenerator();
            ng.generateNormals(aGeometryInfo);
            Shape3D aShape3D = new Shape3D();
            aShape3D.setGeometry(aGeometryInfo.getGeometryArray());

            pa = new PolygonAttributes();
            pa.setCullFace(PolygonAttributes.CULL_NONE);
            pa.setBackFaceNormalFlip(true);
            Appearance anApprnce = rtrvApprnc();
            anApprnce.setPolygonAttributes(pa);

            aShape3D.setAppearance(anApprnce);
            addChild(aShape3D);
            Vector aVector = shp.getQuadElmnts();
            //QuadPnts.prntGlblNmbrs(aVector,editorPane);
            //KMLExport.prntVRML(aVector,editorPane);
        } else if (!aRndrngVar.getRndrVal() && aRndrngVar.getQuadVal()) {
            System.out.println("ShpScene: test quad data...");
            QuadG3D crdntQdG3D = new QuadG3D(shp);
            addChild(crdntQdG3D);
            Vector aVector = shp.getQuadElmnts();
            //QuadPnts.prntGlblNmbrs(aVector,editorPane);
        }
        if (aRndrngVar.getLineVal()) {
            //System.out.println("ShpScene: test line data...");   
            crdntLineG3D = new LineG3D(shp);
            addChild(crdntLineG3D);
            //Vector aVector = shp.getEdges();
            //LinePnts.prntGlblNmbrs(aVector,editorPane);
            Vector aVector = shp.getNodes();
            //NodePnts.prntGlblCrdnts(aVector, editorPane);         
            //DaeExport aDaeExport = new DaeExport();
            //aDaeExport.displayDae(aVector, editorPane);
        }
        if (aRndrngVar.getVrtxVal()) {
            crdntNodeG3D = new NodeG3D(shp);
            addChild(crdntNodeG3D);
        }
        if (aRndrngVar.getGlblIndxVal()) {
            glblNmbrTxt2DG3D = new GlblNmbrTxt2DG3D1(shp);
            addChild(glblNmbrTxt2DG3D);
        }
        if (aRndrngVar.getGlblCrdntsVal()) {
            crdntTxt2DG3D = new CrdntTxt2DG3D(shp);
            addChild(crdntTxt2DG3D);
        }
        //if(aRndrngVar.getBoundingBoxVal() ){
        /*
        double center[] = shp.cmptCenter();
        BoxShape3D aBoxShape3D = new BoxShape3D();
        Box aBox = aBoxShape3D.retrieveBox(shp);
        BranchGroup aBranchGroup = new BranchGroup();
        aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        aBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        aBranchGroup.addChild(aBoxShape3D);
        addChild(aBox);
        */ 
        //{
    }

    /**
     *
     * @return
     */
    public Appearance rtrvApprnc() {
        aMtrlVar = shp.getMtrlVar();
        //aMtrlVar.prntKeyValMap();

        aMaterial = new Material();
        aMaterial.setAmbientColor(aMtrlVar.getAmbnt1Val(), aMtrlVar.getAmbnt2Val(), aMtrlVar.getAmbnt3Val());
        aMaterial.setDiffuseColor(aMtrlVar.getDffs1Val(), aMtrlVar.getDffs2Val(), aMtrlVar.getDffs3Val());
        aMaterial.setSpecularColor(aMtrlVar.getSpclr1Val(), aMtrlVar.getSpclr2Val(), aMtrlVar.getSpclr3Val());
        //aMaterial.setShininess(aMtrlVar.get);
        ta = new TransparencyAttributes();
        ta.setTransparencyMode(TransparencyAttributes.BLENDED);
        //ta.setTransparency(aMtrlVar.getAlphaVal());
        ta.setTransparency(0.5f);

        anAppearance = new Appearance();
        anAppearance.setMaterial(aMaterial);
        anAppearance.setTransparencyAttributes(ta);

        return anAppearance;
    }
}