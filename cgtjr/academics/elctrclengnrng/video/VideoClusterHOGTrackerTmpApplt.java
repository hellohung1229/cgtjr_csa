package cgtjr.academics.elctrclengnrng.video;

import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterHOGTracker;
import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgParserArrayList;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.video.general.VideoRndrrThread;
import cgtjr.academics.general.ThemeSet;
import cgtjr.academics.general.gui.DataJEditorPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class VideoClusterHOGTrackerTmpApplt extends JApplet {

    //private PointParser vdPointParser;
    private FrameParser videoParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;
    private GrdntFilter imgFilter;
    private ClusterHOGTracker imageFilters;

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
        setLayout(new BorderLayout());
        ThemeSet.setDocumentBase(getCodeBase().toString());
    }

    public void start() {
        ThemeSet.setDocumentBase(getCodeBase().toString());

        String userLogonID = getParameter(ThemeSet.getUserLogonIdKey());
        System.out.println("VideoClusterMapHOGApplt: userlogonid = " + userLogonID);

        ThemeSet.setUserLogonIdValue(userLogonID);

        System.out.println("VideoApplctn.start()");
        //imgFilter = new GrdntFilter();       
        //imageFilters = new ClusterHOGTracker();
        //imageFilters.setEigenThreshold(1);
        //imageFilters.setTmprlGrdntThrshld(25);
        //imageFilters.setMaxClusterDistance(25);
        //imageFilters.setMaxMatchDstnce(20);
        
        //imageFilters = new ClusterHOGTracker();
        imageFilters = new ClusterHOGTracker();
        //imageFilters = new TmprlGrdntFltr(); 
      
        //TODO: consider creating a setParameter function, example setParameter("displaycorners",true);
        imageFilters.setDisplayCorners(true);
        imageFilters.setDisplayBoundary(true);
        imageFilters.setMaxClusterDistance(20);
        imageFilters.setEigenThreshold(500);
        imageFilters.setTmprlGrdntThrshld(20);
        imageFilters.setDisplayHOG(true);        
        /*  imageFilters.setDisplayArrows(false);
        imageFilters.setTmprlGrdntThrshld(5);
        imageFilters.setMaxMatchDstnce(5);   
        */
        //updateFeatureParams();                
        //imgCDFilter.setDisplayArrows(false);
        //imgCDFilter.setThreshold(50000);
        System.out.println("VideoClusterHOGTrackerApplet ... test -2");
        videoParser = new ImgParserArrayList(imageFilters);

        //videoParser.setTopOffset(80);
        //videoParser.setBottomOffset(20);  
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
        aVideoAnalyzer = new VideoCntrllrLstnr(new VideoRndrrThread(),videoParser,url);
        //aVideoAnalyzer.setParser(videoParser);
        Panel aPanel = aVideoAnalyzer.strtApplctn();
        //add(aPanel);
        //WTSTextField wtsTextArea = new WTSTextField();
        //JTextArea txtArea = WTSTextField.getTxtFld();
        JTextPane textpane = DataJEditorPane.getTxtFld();
        JScrollPane areaScrollPane = new JScrollPane(textpane);
        areaScrollPane.setPreferredSize(new Dimension(210, 210));
        areaScrollPane.setMinimumSize(new Dimension(10, 10));         
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        /*
         DefaultStyledDocument document = new DefaultStyledDocument();
         JTextPane textpane = new JTextPane(document);
         StyleContext context = new StyleContext();
         // build a style
         Style style = context.addStyle("test", null);
         // set some style properties
         StyleConstants.setForeground(style, Color.BLUE);
         try {
         // add some data to the document
         document.insertString(0, "this is a test", style);
         } catch (BadLocationException ex) {
         Logger.getLogger(VideoClusterLocationApplt.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        //JTextField aJTextField = new JTextField();
        add(aPanel, BorderLayout.CENTER);
        add(areaScrollPane, BorderLayout.SOUTH);
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

    public void updateFeatureParams() {
        boolean displaycorner = getParameter("displaycorner").equalsIgnoreCase("true") ? true : false;
        //boolean displaygradient = getParameter("displaygradient").equalsIgnoreCase("true")?true:false;
        //boolean displayhog = getParameter("displayhog").equalsIgnoreCase("true")?true:false;
        boolean displayarrows = getParameter("displayarrows").equalsIgnoreCase("true") ? true : false;
        boolean displaydigit = getParameter("displaydigit").equalsIgnoreCase("true") ? true : false;
        boolean displayboundary = getParameter("displayboundary").equalsIgnoreCase("true") ? true : false;
        boolean transmitannotation = getParameter("transmitannotation").equalsIgnoreCase("true") ? true : false;

        //String clusterdistance = getParameter("clusterdistance"); 

        /*
        imageFilters.setDisplayCorners(displaycorner);
        imageFilters.setDisplayArrows(displayarrows);
        imageFilters.setDisplayDigit(displaydigit);
        imageFilters.setDisplayBoundary(displayboundary);
        imageFilters.setIsAnnotateOn(transmitannotation);
        */ 
    }
}