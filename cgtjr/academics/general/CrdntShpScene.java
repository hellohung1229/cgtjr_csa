/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.general;

/**
 *
 * @author clayton g thomas jr
 */

import cgtjr.academics.math.geometry.crdntepnts.MeshShp;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import javax.media.j3d.*;
import cgtjr.academics.math.geometry.g3d.*;
import cgtjr.academics.math.geometry.*;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;


public class CrdntShpScene extends BranchGroup
{
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
   
   public CrdntShpScene(ShapePnt myShape)
   {
      shp = myShape;
      aMaterial = new Material();
      setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
      setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
      crtScene();
   }
   public CrdntShpScene(MeshShp myMeshShp)
   {
      setCapability(BranchGroup.ALLOW_CHILDREN_READ);
      setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
      setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
      contructor(myMeshShp);
      crtScene();
   }
   public void contructor(MeshShp myMeshShp)
   {
      shp = (MeshShp)myMeshShp;
      aMtrlVar = myMeshShp.getMtrlVar();
      
      aMaterial = new Material();
      aMaterial.setAmbientColor(aMtrlVar.getAmbnt1Val(),aMtrlVar.getAmbnt2Val(),aMtrlVar.getAmbnt3Val());
      aMaterial.setDiffuseColor(aMtrlVar.getDffs1Val(),aMtrlVar.getDffs2Val(),aMtrlVar.getDffs3Val());
      aMaterial.setSpecularColor(aMtrlVar.getSpclr1Val(),aMtrlVar.getSpclr2Val(),aMtrlVar.getSpclr3Val());
      // aMaterial.setShininess(aMtrlVar.gets);
      ta = new TransparencyAttributes();
      ta.setTransparencyMode(TransparencyAttributes.BLENDED);
      ta.setTransparency(aMtrlVar.getAlphaVal());
      
      System.out.println("CrdntShpScene: alpha = "+aMtrlVar.getAlphaVal());
              
      pa = new PolygonAttributes();
      pa.setCullFace(PolygonAttributes.CULL_NONE);
      //pa.setBackFaceNormalFlip(true);

      anAppearance = new Appearance();
      anAppearance.setMaterial(aMaterial);
      anAppearance.setPolygonAttributes(pa);
      anAppearance.setTransparencyAttributes(ta);
   }
   public void crtScene()
   {
      //TODO: add global numbers ...

      crdntLineG3D = new LineG3D(shp);
      crdntNodeG3D = new NodeG3D(shp);
      crdntQdG3D = new QuadG3D(shp);
      //crdntTxt2DG3D = new CrdntTxt2DG3D(shp);
      //glblNmbrTxt2DG3D = new GlblNmbrTxt2DG3D1(shp);
      GeometryArray aGeometryArray = (GeometryArray)crdntQdG3D.getGeometry();
      GeometryInfo aGeometryInfo  = new GeometryInfo(aGeometryArray);
      NormalGenerator ng = new NormalGenerator();
      ng.generateNormals(aGeometryInfo);
      
      Shape3D aShape3D = new Shape3D();
      aShape3D.setGeometry(aGeometryInfo.getGeometryArray());
      aShape3D.setAppearance(anAppearance);
      
      crdntQdG3D.setAppearance(anAppearance);
      addChild(crdntLineG3D);
      //addChild(crdntNodeG3D);
      
      //addChild(crdntTxt2DG3D);      
      //addChild(glblNmbrTxt2DG3D);
      
      addChild(crdntQdG3D);
      
      //addChild(aShape3D);
   }
}