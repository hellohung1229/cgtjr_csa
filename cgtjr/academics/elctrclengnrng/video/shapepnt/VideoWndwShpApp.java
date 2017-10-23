package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.video.shapepnt.EdgeShpTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import javax.swing.JApplet;


public class VideoWndwShpApp extends JApplet{
    
   private PointParser vdPointParser;
   private VideoCntrllrLstnr aVideoAnalyzer;
   private String mediaFile;
   private URL url;

   /**
     * Read the applet file parameter and create the media 
     * player.
     */
   public void init() 
   {


   }
   public void start() {

      GridDrawActn pixelInsrtActn = new GridDrawActn();
      DmnsnVar aDmnsnVar = new DmnsnVar(20,10,0,30,40,0,270,250,0,9,9,1,170,120,0);
      ShpBndry aShpBndry = new ShpBndry(aDmnsnVar);
      EdgeShpTracker aShpTracker = new EdgeShpTracker(aShpBndry);
      
      vdPointParser = new PointParser(aShpTracker);
      
      if ((mediaFile = getParameter("FILE")) == null)
      {
	      System.err.println("Invalid media file parameter");
      }
	   try {
	      url = new URL(getDocumentBase(), mediaFile);
	      mediaFile = url.toExternalForm();
	   }catch(MalformedURLException mue){
         System.err.println(mue.getMessage());
	   }
        //aVideoAnalyzer = new VideoCntrllrLstnr(mediaFile);
        aVideoAnalyzer = new VideoCntrllrLstnr(url);

      aVideoAnalyzer.setParser(vdPointParser);
      //aVideoAnalyzer.setMediaFile(mediaFile);
      System.out.println("VideoApplctn.init()");
      Panel aPanel = aVideoAnalyzer.strtApplctn();
	   add(aPanel);
      aVideoAnalyzer.start();
   }
   /**
     * Stop media file playback and release resource before
     * leaving the page.
     */
   public void stop() {
      aVideoAnalyzer.stop();
   }

   public void destroy() {
      aVideoAnalyzer.close();
   }

}
