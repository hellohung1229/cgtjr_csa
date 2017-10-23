package cgtjr.academics.elctrclengnrng.transengnrng;

import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.CnsstntMshTrckr;
import cgtjr.academics.general.gui.WTSTextField;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JTextArea;

public class IntrstnEdgeShpApplt extends JApplet {

    private PointParser vdPointParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init()
    {        
        setLayout(new BorderLayout());        
    }
    public void start() {
        //FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg", 10, 10, 0, 10, 40, 0, 340, 260, 0, 4, 4, 1, 40, 180, 0);
        //GrdntBndry aShpBndry = new GrdntBndry(aDmnsnVar);
        
        DmnsnVar aDmnsnVar = new DmnsnVar(0, 0, 0, 4, 160, 0, 190, 200, 0, 4, 4, 1, 4, 160, 0);        
        //ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);//(190-4)*(200-160)
        ShpBndry aShpBndry = new ShpBndry(aDmnsnVar);
        //aShpBndry.setThreshHold(34);
        //ArrowGridDrawActn aArrowGridDrawActn = new ArrowGridDrawActn();
        ClusterActn aClusterActn = new ClusterActn();        
        CnsstntMshTrckr aCnsstntMshTrckr = new CnsstntMshTrckr(aShpBndry,aClusterActn);
        aCnsstntMshTrckr.setIsCntrTrckng(false);
        vdPointParser = new PointParser(aCnsstntMshTrckr);

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
