package cgtjr.academics.elctrclengnrng.radiosity.gui.pnl;

import cgtjr.academics.elctrclengnrng.fem.gui.bttn.ClndrclTrnglTpSrfcBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstyClndrclBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstyClndrclQuadBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstyHxhdrlBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstyQuadBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstySphrclBttn;
import cgtjr.academics.elctrclengnrng.radiosity.gui.bttn.RdstySphrclQuadBttn;
import cgtjr.academics.general.*;
import java.applet.Applet;


public class RdstyBttnPnl extends LabPnl
{
   SceneRoot scnRt;
   public RdstyBttnPnl(SceneRoot mySceneRoot)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;     
      addCmpnnts();
   }
   public RdstyBttnPnl(SceneRoot mySceneRoot,Applet myLabWndwPnl)
   {
      super(mySceneRoot);
      scnRt = mySceneRoot;     
      addCmpnnts(myLabWndwPnl);
   }
   public void addCmpnnts() 
   {
      //SceneRoot scnRt = getScnRt();
      System.out.println("RdstyIcnPnl: test 12 scnRt="+scnRt);
      RdstyHxhdrlBttn aRdstyHxhdrlBttn = new RdstyHxhdrlBttn(scnRt);
      RdstySphrclBttn aRdstySphrclBttn = new RdstySphrclBttn(scnRt);
      RdstyClndrclBttn aRdstyClndrclBttn = new RdstyClndrclBttn(scnRt);
      RdstyQuadBttn aRdstyQuadBttn = new RdstyQuadBttn(scnRt);
      RdstySphrclQuadBttn aRdstySphrclQuadBttn = new RdstySphrclQuadBttn(scnRt);
      RdstyClndrclQuadBttn aRdstyClndrclQuadBttn = new RdstyClndrclQuadBttn(scnRt);
      ClndrclTrnglTpSrfcBttn aClndrclTrnglTpSrfcBttn = new ClndrclTrnglTpSrfcBttn(scnRt);
      //ClndrclTrnglTpSrfc aClndrclTrnglTpSrfc = new ClndrclTrnglTpSrfc(scnRt);
      add(aRdstyHxhdrlBttn);
      add(aRdstySphrclBttn);
      add(aRdstyClndrclBttn);
      add(aRdstyQuadBttn);
      //add(aRdstySphrclQuadBttn);
      //add(aRdstyClndrclQuadBttn);
      add(aClndrclTrnglTpSrfcBttn);
      //add(aClndrclTrnglTpSrfc);
   }
   public void addCmpnnts(Applet myLabWndwPnl)
   {
      //SceneRoot scnRt = getScnRt();
      RdstyHxhdrlBttn aRdstyHxhdrlBttn = new RdstyHxhdrlBttn(scnRt,myLabWndwPnl);
      RdstySphrclBttn aRdstySphrclBttn = new RdstySphrclBttn(scnRt,myLabWndwPnl);
      RdstyClndrclBttn aRdstyClndrclBttn = new RdstyClndrclBttn(scnRt,myLabWndwPnl);
      RdstyQuadBttn aRdstyQuadBttn = new RdstyQuadBttn(scnRt,myLabWndwPnl);
      RdstySphrclQuadBttn aRdstySphrclQuadBttn = new RdstySphrclQuadBttn(scnRt,myLabWndwPnl);
      RdstyClndrclQuadBttn aRdstyClndrclQuadBttn = new RdstyClndrclQuadBttn(scnRt,myLabWndwPnl);
      ClndrclTrnglTpSrfcBttn aClndrclTrnglTpSrfcBttn = new ClndrclTrnglTpSrfcBttn(scnRt,myLabWndwPnl);
      //ClndrclTrnglTpSrfc aClndrclTrnglTpSrfc = new ClndrclTrnglTpSrfc(scnRt);
      add(aRdstyHxhdrlBttn);
      add(aRdstySphrclBttn);
      add(aRdstyClndrclBttn);
      add(aRdstyQuadBttn);
      add(aRdstySphrclQuadBttn);
      add(aRdstyClndrclQuadBttn);
      add(aClndrclTrnglTpSrfcBttn);
      //add(aClndrclTrnglTpSrfc);
   }
}