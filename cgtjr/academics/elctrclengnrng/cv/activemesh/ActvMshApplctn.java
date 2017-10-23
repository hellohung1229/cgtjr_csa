package cgtjr.academics.elctrclengnrng.cv.activemesh;

import java.applet.Applet;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
//import javax.media.*;

public class ActvMshApplctn extends Applet {

    private PointParser vdPointParser;
    /////////private VideoAnalyzer aVideoAnalyzer;
    private String mediaFile;
    private URL url;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
        vdPointParser = new PointParser();
        if ((mediaFile = getParameter("FILE")) == null) {
            System.err.println("Invalid media file parameter");
        }
        try {
            url = new URL(getDocumentBase(), mediaFile);
            mediaFile = url.toExternalForm();
        } catch (MalformedURLException mue) {
            System.err.println(mue.getMessage());
        }
        //////aVideoAnalyzer = null;////////////new VideoAnalyzer(mediaFile);
        ////////aVideoAnalyzer.setParser(vdPointParser);
        //aVideoAnalyzer.setMediaFile(mediaFile);
        System.out.println("VideoApplctn.init()");
        /////////Panel aPanel = aVideoAnalyzer.strtApplctn();
        ////////// add(aPanel);
    }
    public void start() {
        /////////aVideoAnalyzer.start();
    }
    /**
     * Stop media file playback and release resource before leaving the page.
     */
    public void stop() {
        ////////// aVideoAnalyzer.stop();
    }
    public void destroy() {
        ///////// aVideoAnalyzer.close();
    }
}