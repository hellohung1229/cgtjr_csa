package cgtjr.academics.elctrclengnrng.video.shapepnt;

import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.video.general.CptrCntrllrLstnr;
import cgtjr.academics.general.FileNameVar;
import javax.swing.JApplet;

public class CptreShpApplt extends JApplet{   
   private CptrCntrllrLstnr aVideoAnalyzer;

   /**
     * Read the applet file parameter and create the media 
     * player.
     */
   public void init() 
   {
   }
   public void start() {
      
      aVideoAnalyzer = new CptrCntrllrLstnr();
      Thread aThread = new Thread(aVideoAnalyzer);
      aThread.start();
      Panel aPanel = aVideoAnalyzer.getPanel();
	   add(aPanel);
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
