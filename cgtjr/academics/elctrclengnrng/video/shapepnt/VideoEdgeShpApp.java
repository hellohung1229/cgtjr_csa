package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.video.shapepnt.EdgeShpTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.general.FileNameVar;
import javax.swing.JApplet;

public class VideoEdgeShpApp extends JApplet {

    private PointParser vdPointParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
    }

    public void start() {
        SumSqrDiff aSumSqrDiff2 = new SumSqrDiff("data/images/textures/brwn7_3x3.jpg");
        aSumSqrDiff2.setTolerance(5);
        FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white1_3x3.jpg", 20, 10, 0, 30, 40, 0, 270, 250, 0, 20, 20, 1, 170, 120, 0);
        ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);

        EdgeShpTracker aShpTracker = new EdgeShpTracker(aShpBndry);
        vdPointParser = new PointParser(aShpTracker);

        if ((mediaFile = getParameter("FILE")) == null) {
            System.err.println("Invalid media file parameter");
        }
        try {
            url = new URL(getDocumentBase(), mediaFile);
            mediaFile = url.toExternalForm();
        } catch (MalformedURLException mue) {
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
     * Stop media file playback and release resource before leaving the page.
     */
    public void stop() {
        aVideoAnalyzer.stop();
    }

    public void destroy() {
        aVideoAnalyzer.close();
    }
}
