package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.video.shapepnt.SDCLShpTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.color.ColorDiffBndry;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import javax.swing.JApplet;

public class ClrDiffShpTrckrApplt extends JApplet {

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
        DmnsnVar aDmnsnVar = new DmnsnVar(10, 10, 0, 40, 10, 0, 320, 170, 0, 5, 5, 1, 160, 140, 0);
        ImageBndry aShpBndry = new ColorDiffBndry(aDmnsnVar);
        SDCLShpTracker aShpTracker = new SDCLShpTracker(aShpBndry);

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
