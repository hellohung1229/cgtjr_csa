/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.math.geometry.crdntepnts.CrdntShp;
import cgtjr.academics.elctrclengnrng.imgprcssng.SSDImgBndry;
import cgtjr.academics.general.*;
import cgtjr.academics.math.geometry.*;

/**
 *
 * @author clayton g thomas jr
 */

public class ImgGrpPrcss extends DataPrcss
{

   private SceneRoot scnRt;
   private FileNameVar fileNameVar1;
   private FileNameVar fileNameVar2;
   
   public ImgGrpPrcss(FileNameVar myFileNameVar1, FileNameVar myFileNameVar2,SceneRoot mySceneRoot)
   {
      fileNameVar1 = myFileNameVar1;
      fileNameVar2 = myFileNameVar2;
      scnRt = mySceneRoot;
   }
   public void prcss()
   {

      SSDImgBndry aImageBndry = new SSDImgBndry(fileNameVar1,fileNameVar2);

      CrdntShp crdntShp = new CrdntShp(aImageBndry);
      ////NdlElmnts aNdElmnts = new NdlElmnts(crdntShp,aShpBndryGrp);
      MshShpScene aMshShpScene = new MshShpScene(crdntShp);
      scnRt.addShp3D(aMshShpScene);
   }
}