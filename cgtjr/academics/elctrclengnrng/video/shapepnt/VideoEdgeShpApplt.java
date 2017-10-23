package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GrdntBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.general.FileNameVar;
import cgtjr.academics.general.gui.WTSTextField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JTextArea;

public class VideoEdgeShpApplt extends JApplet {

    private PointParser vdPointParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
        setLayout(new BorderLayout());
    }
    public void start() {
        FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg", 10, 10, 0, 30, 40, 0, 300, 270, 0, 4, 4, 1, 200, 250, 0);
        //ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);
        ImageBndry aShpBndry = new GrdntBndry(aDmnsnVar);
        SDCLShpTracker aShpTracker = new SDCLShpTracker(aShpBndry);
        aShpTracker.setIsCntrTrckng(false);
        vdPointParser = new PointParser(aShpTracker);

        //mediaFile = "file:///c:/cthomas/development/us3dacurrent/public_html/us3da/data/video/oldSpiralHwLewistonID.mov";
        //mediaFile = "data/video/oldSpiralHwLewistonID.mov";
        try {
            url = new java.net.URL(mediaFile);
        } catch (MalformedURLException ex) {
            Logger.getLogger(VideoEdgeShpApplt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ((mediaFile = getParameter("FILE")) == null) {
            System.err.println("Invalid media file parameter");
        }
      
        try {
            if ( mediaFile.indexOf("://", 1) >= 1) {
                url = new java.net.URL(mediaFile);
            } else {
                url = new URL(getDocumentBase(), mediaFile);
            }            
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
        //add(aPanel);
        //WTSTextField wtsTextArea = new WTSTextField();
        JTextArea txtArea = WTSTextField.getTxtFld();
        //JTextField aJTextField = new JTextField();
        add(aPanel, BorderLayout.CENTER);
        add(txtArea, BorderLayout.SOUTH);
        //aVideoAnalyzer.start();
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
