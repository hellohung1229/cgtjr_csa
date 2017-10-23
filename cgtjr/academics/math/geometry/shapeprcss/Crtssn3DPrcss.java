/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.geometry.shapeprcss;

import cgtjr.academics.math.geometry.dmnsvar.Crtssn3DVar;
import cgtjr.academics.math.geometry.crdntepnts.CrtssnShp;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import java.util.*;

import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.g3d.*;
/**
 *
 * @author clayton g thomas jr
 */
public class Crtssn3DPrcss extends DataPrcss
{
   HashMap varHshMp;
   SceneRoot scnRt;

   public Crtssn3DPrcss()
   {
      varHshMp = new HashMap();
   }
   public Crtssn3DPrcss(SceneRoot mySceneRoot)
   {
      varHshMp = new HashMap();
      scnRt = mySceneRoot;
   }
   public void setVarHshMp(HashMap myVarHshMp)
   {
      varHshMp = myVarHshMp;
   }
   public HashMap getVarHshMp()
   {
      return varHshMp;
   }
   public void prcss()
   {
      DmnsnVar aDmnsnVar = Crtssn3DVar.getDmnsnVar();
      CrtssnShp aCrtssnShp = new CrtssnShp(aDmnsnVar);
      CrtssnShpG3D aCrtssnShpG3D = new CrtssnShpG3D(aCrtssnShp);
      //CrtssnShpG3D aCrtssnShpG3D = new CrtssnPntG3D(aCrtssnShp);
      //CrtssnShpG3D aCrtssnShpG3D = new CrtssnLnG3D(aCrtssnShp);      
      //CrtssnShpG3D aCrtssnShpG3D = new CrtssnRctnglG3D(aCrtssnShp);      
      //CrtssnShpG3D aCrtssnShpG3D = new CrtssnHxhdrlG3D(aCrtssnShp);
      scnRt.addShp3D(aCrtssnShpG3D);
   }
}