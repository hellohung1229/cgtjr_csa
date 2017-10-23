package cgtjr.academics.elctrclengnrng.radiosity.gui.bttn;

import cgtjr.academics.elctrclengnrng.fem.gui.pnl.*;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.ClndrclTrnglTpSrfcBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMClndrclBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMClndrclQuadBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMHxhdrlBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMQuadBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMSphrclBttn;
import cgtjr.academics.elctrclengnrng.fem.gui.bttn.FEMSphrclQuadBttn;
import cgtjr.academics.general.*;
import java.applet.Applet;


public class RdstyIcnPnl extends LabPnl
{
   SceneRoot scnRt;
   public RdstyIcnPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;     
      addCmpnnts();
   }
   public RdstyIcnPnl(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;     
      addCmpnnts(myLabWndwPnl);
   }
   public void addCmpnnts() 
   {
      //SceneRoot scnRt = getScnRt();
      System.out.println("FEMIcnPnl: test 12 scnRt="+scnRt);
      FEMHxhdrlBttn aFEMHxhdrlBttn = new FEMHxhdrlBttn(scnRt);
      FEMSphrclBttn aFEMSphrclBttn = new FEMSphrclBttn(scnRt);
      FEMClndrclBttn aFEMClndrclBttn = new FEMClndrclBttn(scnRt);
      FEMQuadBttn aFEMQuadBttn = new FEMQuadBttn(scnRt);
      FEMSphrclQuadBttn aFEMSphrclQuadBttn = new FEMSphrclQuadBttn(scnRt);
      FEMClndrclQuadBttn aFEMClndrclQuadBttn = new FEMClndrclQuadBttn(scnRt);
      ClndrclTrnglTpSrfcBttn aClndrclTrnglTpSrfcBttn = new ClndrclTrnglTpSrfcBttn(scnRt);
      //ClndrclTrnglTpSrfc aClndrclTrnglTpSrfc = new ClndrclTrnglTpSrfc(scnRt);
      add(aFEMHxhdrlBttn);
      add(aFEMSphrclBttn);
      add(aFEMClndrclBttn);
      add(aFEMQuadBttn);
      //add(aFEMSphrclQuadBttn);
      //add(aFEMClndrclQuadBttn);
      add(aClndrclTrnglTpSrfcBttn);
      //add(aClndrclTrnglTpSrfc);
   }
   public void addCmpnnts(Applet myLabWndwPnl)
   {
      //SceneRoot scnRt = getScnRt();
      FEMHxhdrlBttn aFEMHxhdrlBttn = new FEMHxhdrlBttn(scnRt,myLabWndwPnl);
      FEMSphrclBttn aFEMSphrclBttn = new FEMSphrclBttn(scnRt,myLabWndwPnl);
      FEMClndrclBttn aFEMClndrclBttn = new FEMClndrclBttn(scnRt,myLabWndwPnl);
      FEMQuadBttn aFEMQuadBttn = new FEMQuadBttn(scnRt,myLabWndwPnl);
      FEMSphrclQuadBttn aFEMSphrclQuadBttn = new FEMSphrclQuadBttn(scnRt,myLabWndwPnl);
      FEMClndrclQuadBttn aFEMClndrclQuadBttn = new FEMClndrclQuadBttn(scnRt,myLabWndwPnl);
      ClndrclTrnglTpSrfcBttn aClndrclTrnglTpSrfcBttn = new ClndrclTrnglTpSrfcBttn(scnRt,myLabWndwPnl);
      //ClndrclTrnglTpSrfc aClndrclTrnglTpSrfc = new ClndrclTrnglTpSrfc(scnRt);
      add(aFEMHxhdrlBttn);
      add(aFEMSphrclBttn);
      add(aFEMClndrclBttn);
      add(aFEMQuadBttn);
      add(aFEMSphrclQuadBttn);
      add(aFEMClndrclQuadBttn);
      add(aClndrclTrnglTpSrfcBttn);
      //add(aClndrclTrnglTpSrfc);
   }
}