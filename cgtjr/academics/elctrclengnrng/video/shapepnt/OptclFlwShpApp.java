package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgCntrllrLstnr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.video.shapepnt.OptclFlwShpTracker;
import java.awt.*;
import java.net.URL;
import cgtjr.academics.general.FileNameVar;
import java.util.ArrayList;
import javax.swing.JApplet;


public class OptclFlwShpApp extends JApplet{
    
   private PointParser vdPointParser;
   private ImgCntrllrLstnr aVideoAnalyzer;
   private static String mediaURL;
   private URL url;

   /**
     * Read the applet file parameter and create the media 
     * player.
     */
   public void init() 
   {

   }
   public void start() {
      FileNameVar aDmnsnVar = new FileNameVar("",0,0,0,0,40,0,530,330,0,5,5,1,340,320,0);
      ImageBndry aShpBndry = new OptclFlwBndry(aDmnsnVar);
      OptclFlwShpTracker aShpTracker = new OptclFlwShpTracker(aShpBndry);
      
      vdPointParser = new PointParser(aShpTracker);
      
       if ((mediaURL = getParameter("file")) == null)
       {
          System.err.println("Invalid media file parameter");
       }
       String files[] = mediaURL.split(",");
       ArrayList anArrayList = ImageTool.getImgArryLst(files,this);
        
      aVideoAnalyzer = new ImgCntrllrLstnr(anArrayList);
      aVideoAnalyzer.setParser(vdPointParser);
      //aVideoAnalyzer.setMediaFile(mediaFile);
      System.out.println("ImgEdgeShpApp: array size = "+anArrayList.size());
      //Panel aPanel = aVideoAnalyzer.strtApplctn();
	   //add(aPanel);
      add(aVideoAnalyzer.getImgPlyr());      
      aVideoAnalyzer.start();
   }
   /**
     * Stop media file playback and release resource before
     * leaving the page.
     */
   public void stop() {
      //aVideoAnalyzer.stop();
   }

   public void destroy() {
      //aVideoAnalyzer.close();
   }

}
