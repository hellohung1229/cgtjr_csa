package cgtjr.academics.elctrclengnrng.video;

import cgtjr.academics.elctrclengnrng.cv.HOGOrientationFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgParser;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.general.gui.WTSTextField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JTextArea;

public class VideoHOGDetectApplt extends JApplet {

    //private PointParser vdPointParser;
    private FrameParser videoParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;
    private GrdntFilter imgFilter;
    private HOGOrientationFilter imgCDFilter;    

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
        setLayout(new BorderLayout());
    }
    public void start() {

        System.out.println("VideoApplctn.start()");
        //imgFilter = new GrdntFilter();       
        imgCDFilter = new HOGOrientationFilter();
        
        videoParser = new ImgParser(imgCDFilter);

        if ((mediaFile = getParameter("filename0")) == null) {
            System.err.println("Invalid media file parameter!!!");
        }
        try {
            if (mediaFile.indexOf("://", 1) >= 1) {
                url = new java.net.URL(mediaFile);
            } else {
                url = new URL(getDocumentBase(), mediaFile);
            }
            //mediaFile = url.toExternalForm();
        } catch (MalformedURLException mue) {
            System.err.println(mue.getMessage());
        }
        //aVideoAnalyzer = new VideoCntrllrLstnr(mediaFile);
        aVideoAnalyzer = new VideoCntrllrLstnr(url);
        aVideoAnalyzer.setParser(videoParser);

        Panel aPanel = aVideoAnalyzer.strtApplctn();
        //add(aPanel);
        //WTSTextField wtsTextArea = new WTSTextField();
        JTextArea txtArea = WTSTextField.getTxtFld();
        //JTextField aJTextField = new JTextField();
        add(aPanel, BorderLayout.CENTER);
        add(txtArea, BorderLayout.SOUTH);
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