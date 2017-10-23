package cgtjr.academics.elctrclengnrng.video;

import cgtjr.academics.elctrclengnrng.cv.VanishingHeightWidth;
import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterHeightWidthFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgParser;
import cgtjr.academics.elctrclengnrng.video.general.VideoCntrllrLstnr;
import cgtjr.academics.general.ThemeSet;
import cgtjr.academics.general.gui.WTSTextField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JTextArea;

public class VideoClusterHeightWidthApplt extends JApplet {

    //private PointParser vdPointParser;
    private FrameParser videoParser;
    private VideoCntrllrLstnr aVideoAnalyzer;
    private String mediaFile;
    private URL url;
    private GrdntFilter imgFilter;
    private ClusterHeightWidthFltr imageFilters;    

    /**
     * Read the applet file parameter and create the media player.
     */
    public void init() {
        setLayout(new BorderLayout());

        ThemeSet.setDocumentBase(getCodeBase().toString());
        rtrveData();
    }
    public void start() {
        ThemeSet.setDocumentBase(getCodeBase().toString());        
        rtrveData();
        
        String userLogonID = getParameter(ThemeSet.getUserLogonIdKey());
        System.out.println("VideoClusterMapHOGApplt: userlogonid = "+userLogonID);
        
        ThemeSet.setUserLogonIdValue(userLogonID);
        
        System.out.println("VideoApplctn.start()");
        //imgFilter = new GrdntFilter();       
        imageFilters = new ClusterHeightWidthFltr();
        imageFilters.setThreshold(100);
        imageFilters.setTmprlGrdntThrshld(45);
        imageFilters.setClusterMaxDistance(20);
        imageFilters.setMatchDstnceThrshld(20);
        updateFeatureParams();
        //imgCDFilter.setDisplayArrows(false);
        //imgCDFilter.setThreshold(50000);
        videoParser = new ImgParser(imageFilters);

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
    public void updateFeatureParams(){
        boolean displaycorner = getParameter("displaycorner").equalsIgnoreCase("true")?true:false;
        //boolean displaygradient = getParameter("displaygradient").equalsIgnoreCase("true")?true:false;
        //boolean displayhog = getParameter("displayhog").equalsIgnoreCase("true")?true:false;
        boolean displayarrows = getParameter("displayarrows").equalsIgnoreCase("true")?true:false;
        boolean displaydigit = getParameter("displaydigit").equalsIgnoreCase("true")?true:false;
        boolean displayboundary = getParameter("displayboundary").equalsIgnoreCase("true")?true:false;  
        boolean transmitannotation = getParameter("transmitannotation").equalsIgnoreCase("true")?true:false;  
        
        //String clusterdistance = getParameter("clusterdistance"); 
        
        imageFilters.setDisplayCorners(displaycorner);
        imageFilters.setDisplayArrows(displayarrows);
        imageFilters.setDisplayDigit(displaydigit);
        imageFilters.setDisplayBoundary(displayboundary); 
        
        imageFilters.setIsAnnotateOn(transmitannotation);
    }     
    public void rtrveData() {
       float GPL1x1=Float.valueOf(getParameter("GPL1x1"));
       float GPL1y1=Float.valueOf(getParameter("GPL1y1"));
       float GPL1c1=Float.valueOf(getParameter("GPL1c1"));
       float GPL1x2=Float.valueOf(getParameter("GPL1x2"));
       float GPL1y2=Float.valueOf(getParameter("GPL1y2"));
       float GPL1c2=Float.valueOf(getParameter("GPL1c2"));
    
       float GPL2x1=Float.valueOf(getParameter("GPL2x1"));
       float GPL2y1=Float.valueOf(getParameter("GPL2y1"));
       float GPL2c1=Float.valueOf(getParameter("GPL2c1"));
       float GPL2x2=Float.valueOf(getParameter("GPL2x2"));
       float GPL2y2=Float.valueOf(getParameter("GPL2y2"));
       float GPL2c2=Float.valueOf(getParameter("GPL2c2"));
    
       float GPL3x1=Float.valueOf(getParameter("GPL3x1"));
       float GPL3y1=Float.valueOf(getParameter("GPL3y1"));
       float GPL3c1=Float.valueOf(getParameter("GPL3c1"));
       float GPL3x2=Float.valueOf(getParameter("GPL3x2"));
       float GPL3y2=Float.valueOf(getParameter("GPL3y2"));
       float GPL3c2=Float.valueOf(getParameter("GPL3c2"));
    
       float GPL4x1=Float.valueOf(getParameter("GPL4x1"));
       float GPL4y1=Float.valueOf(getParameter("GPL4y1"));
       float GPL4c1=Float.valueOf(getParameter("GPL4c1"));
       float GPL4x2=Float.valueOf(getParameter("GPL4x2"));
       float GPL4y2=Float.valueOf(getParameter("GPL4y2"));
       float GPL4c2=Float.valueOf(getParameter("GPL4c2"));
    
       float VL1x1=Float.valueOf(getParameter("VL1x1"));
       float VL1y1=Float.valueOf(getParameter("VL1y1"));
       float VL1c1=Float.valueOf(getParameter("VL1c1"));
       float VL1x2=Float.valueOf(getParameter("VL1x2"));
       float VL1y2=Float.valueOf(getParameter("VL1y2"));
       float VL1c2=Float.valueOf(getParameter("VL1c2"));
    
       float VL2x1=Float.valueOf(getParameter("VL2x1"));
       float VL2y1=Float.valueOf(getParameter("VL2y1"));
       float VL2c1=Float.valueOf(getParameter("VL2c1"));
       float VL2x2=Float.valueOf(getParameter("VL2x2"));
       float VL2y2=Float.valueOf(getParameter("VL2y2"));
       float VL2c2=Float.valueOf(getParameter("VL2c2"));
       
       float RLx1=Float.valueOf(getParameter("RLx1"));
       float RLy1=Float.valueOf(getParameter("RLy1"));
       float RLc1=Float.valueOf(getParameter("RLc1"));
       float RLx2=Float.valueOf(getParameter("RLx2"));
       float RLy2=Float.valueOf(getParameter("RLy2"));
       float RLc2=Float.valueOf(getParameter("RLc2"));       

       float RHeight=Float.valueOf(getParameter("RHeight"));       
       
       VanishingHeightWidth.setGPL1x1(GPL1x1);
       VanishingHeightWidth.setGPL1y1(GPL1y1);
       VanishingHeightWidth.setGPL1c1(GPL1c1);       
       VanishingHeightWidth.setGPL1x2(GPL1x2);
       VanishingHeightWidth.setGPL1y2(GPL1y2);
       VanishingHeightWidth.setGPL1c2(GPL1c2);
       VanishingHeightWidth.setGPL2x1(GPL2x1);
       VanishingHeightWidth.setGPL2y1(GPL2y1);
       VanishingHeightWidth.setGPL2c1(GPL2c1);       
       VanishingHeightWidth.setGPL2x2(GPL2x2);
       VanishingHeightWidth.setGPL2y2(GPL2y2);
       VanishingHeightWidth.setGPL2c2(GPL2c2);
       
       VanishingHeightWidth.setGPL3x1(GPL3x1);              
       VanishingHeightWidth.setGPL3y1(GPL3y1);
       VanishingHeightWidth.setGPL3c1(GPL3c1);       
       VanishingHeightWidth.setGPL3x2(GPL3x2);
       VanishingHeightWidth.setGPL3y2(GPL3y2);
       VanishingHeightWidth.setGPL3c2(GPL3c2);                     
       
       VanishingHeightWidth.setGPL4x1(GPL4x1);              
       VanishingHeightWidth.setGPL4y1(GPL4y1);
       VanishingHeightWidth.setGPL4c1(GPL4c1);       
       VanishingHeightWidth.setGPL4x2(GPL4x2);
       VanishingHeightWidth.setGPL4y2(GPL4y2);
       VanishingHeightWidth.setGPL4c2(GPL4c2);                            
       
       VanishingHeightWidth.setVL1x1(VL1x1);              
       VanishingHeightWidth.setVL1y1(VL1y1);
       VanishingHeightWidth.setVL1c1(VL1c1);       
       VanishingHeightWidth.setVL1x2(VL1x2);
       VanishingHeightWidth.setVL1y2(VL1y2);
       VanishingHeightWidth.setVL1c2(VL1c2);                     
       
       VanishingHeightWidth.setVL2x1(VL2x1);              
       VanishingHeightWidth.setVL2y1(VL2y1);
       VanishingHeightWidth.setVL2c1(VL2c1);       
       VanishingHeightWidth.setVL2x2(VL2x2);
       VanishingHeightWidth.setVL2y2(VL2y2);
       VanishingHeightWidth.setVL2c2(VL2c2);
       
       VanishingHeightWidth.setRLx1(RLx1);              
       VanishingHeightWidth.setRLy1(RLy1);
       VanishingHeightWidth.setRLc1(RLc1);       
       VanishingHeightWidth.setRLx2(RLx2);
       VanishingHeightWidth.setRLy2(RLy2);
       VanishingHeightWidth.setRLc2(RLc2);       
       
       VanishingHeightWidth.setRHeight(RHeight);       
       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1x1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL1c2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2x1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL2c2());
       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3x1());              
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL3c2());                     
       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4x1());              
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getGPL4c2());                            
       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1x1());              
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL1c2());                     
       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2x1());              
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2y1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2c1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2x2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2y2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getVL2c2());    

System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLx1());              
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLy1());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLc1());       
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLx2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLy2());
System.out.println("VanishingHeightWidth " + VanishingHeightWidth.getRLc2());    
    }    
}