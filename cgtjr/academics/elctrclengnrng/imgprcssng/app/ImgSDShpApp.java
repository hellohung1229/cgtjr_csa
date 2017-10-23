package cgtjr.academics.elctrclengnrng.imgprcssng.app;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GrdntBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.video.shapepnt.SDShpTracker;
import cgtjr.academics.general.FileNameVar;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JTextField;

public class ImgSDShpApp extends JApplet {

    private PointParser vdPointParser;
    private ImgCntrllrLstnr aVideoAnalyzer;
    private static String mediaURL;
    private URL url;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {

    }
    public void start() {
        FileNameVar aDmnsnVar = new FileNameVar("", 10, 10, 0, 10, 10, 0, 580, 276, 0, 4, 4, 1, 300, 276, 0);
        GrdntBndry aShpBndry = new GrdntBndry(aDmnsnVar);
        aShpBndry.setThreshHold(24);
        SDShpTracker aShpTracker = new SDShpTracker(aShpBndry);

        vdPointParser = new PointParser(aShpTracker);

        if ((mediaURL = getParameter("file")) == null) {
            System.err.println("Invalid media file parameter");
        }
        String files[] = mediaURL.split(",");
        ArrayList anArrayList = ImageTool.getImgArryLst(files, this);

        aVideoAnalyzer = new ImgCntrllrLstnr(anArrayList);
        aVideoAnalyzer.setParser(vdPointParser);
        //aVideoAnalyzer.setMediaFile(mediaFile);
        System.out.println("ImgEdgeShpApp: array size = " + anArrayList.size());
        //Panel aPanel = aVideoAnalyzer.strtApplctn();
        //add(aPanel);
        add(aVideoAnalyzer.getImgPlyr());
 
        aVideoAnalyzer.start();
    }

    /**
     * Stop media file playback and release resource before leaving the page.
     */
    public void stop() {
        //aVideoAnalyzer.stop();
    }

    public void destroy() {
        //aVideoAnalyzer.close();
    }
}
