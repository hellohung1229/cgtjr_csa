package cgtjr.academics.elctrclengnrng.imgprcssng.gui.pnl;

import cgtjr.academics.elctrclengnrng.imgprcssng.gui.bttn.QuadImgBndryBttn;
import cgtjr.academics.general.*;


public class ImgMshPnl extends LabPnl
{
   SceneRoot scnRt;
   public ImgMshPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;
                  System.out.println("FEMIcnPnl: test 8 scnRt="+scnRt);
      addCmpnnts();
   }
   public void addCmpnnts() 
   {
      //SceneRoot scnRt = getScnRt();
      System.out.println("FEMIcnPnl: test 12 scnRt="+scnRt);
      QuadImgBndryBttn aQuadImgBndryBttn = new QuadImgBndryBttn(scnRt);
      add(aQuadImgBndryBttn);

   }
}