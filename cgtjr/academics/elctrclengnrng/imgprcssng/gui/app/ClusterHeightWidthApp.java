/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.cv.VanishingHeightWidth;
import cgtjr.academics.elctrclengnrng.cv.clustering.ClusterHeightWidthFltr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Nit
 */
public class ClusterHeightWidthApp extends JApplet
{
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;       
    private ArrayList anImgArryLst;
        
    public ClusterHeightWidthApp()
    {
      anImgArryLst = new ArrayList();  
      anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
      //GrdntFilter aGrdntFilter = new GrdntFilter();
      //CornerDetect aCornerDetect = new CornerDetect();
      //OpticalFlowFltr aOpticalFlowFltr = new OpticalFlowFltr();      
      //SSDCornerFltr aSSDCornerFltr = new SSDCornerFltr();
      //SADCornerFltr aSADCornerFltr = new SADCornerFltr();      
      //SADCornerRctfdFltr aSADCornerRctfdFltr = new SADCornerRctfdFltr();           
      //SSDRGBCornerFltr aSSDRGBCornerFltr = new SSDRGBCornerFltr();      
      //SSDRctfdCornerFltr aSSDRctfdCornerFltr = new SSDRctfdCornerFltr();         
      //CrrltnCrnrFltr aCrrltnCrnrFltr = new CrrltnCrnrFltr();
      //anImgRndrr = new ImageRndrr(aCrrltnMatchFltr);    
      //anImgRndrr = new ImageRndrr(aGrdntFilter); 
      //SimpleStereoFltr aSimpleStereoFltr = new SimpleStereoFltr();
      //DisparityRctfdFltr aDisparityRctfdFltr = new DisparityRctfdFltr();
      ClusterHeightWidthFltr aClusterHeightWidthFltr = new ClusterHeightWidthFltr();          
      aClusterHeightWidthFltr.setThreshold(100);
      aClusterHeightWidthFltr.setClusterMaxDistance(20);      
      aClusterHeightWidthFltr.setMatchDstnceThrshld(40);   
      aClusterHeightWidthFltr.setDisplayArrows(false);
      aClusterHeightWidthFltr.setDisplayBoundary(true);      
      aClusterHeightWidthFltr.setDisplayDigit(true);    
      
      anImgRndrr = new ImageRndrr(aClusterHeightWidthFltr); 
      anImgPlyrCnvs.setRndrr(anImgRndrr);
    }
    public void init() {
        //super.init();
    }
    public void start() 
    {    
        /*
         String mediaURL = "";
         if ((mediaURL = getParameter("file1")) == null)
         {
         System.err.println("Invalid media file parameter");
         }
         String files[] = mediaURL.split(",");
         anImgArryLst.add(ImageTool.getImgArryLst(files,this));
         startApp1();
         */

        //StereoImages1 anImgFrmApp = new StereoImages1();
        startApp1();

        //getContentPane().add(anImgFrmApp, BorderLayout.CENTER);
        repaint();
    }
   public void stop() {
      super.stop();
   }
   public static void main( String args[] ) 
   {
      JFrame mediaFrame;
      mediaFrame = new JFrame("Frame Parser");        
      mediaFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );      
      mediaFrame.setSize( 1000, 500 );
      mediaFrame.setVisible( true );         
      ClusterHeightWidthApp anImgFrmApp = new ClusterHeightWidthApp();
      anImgFrmApp.startApp2();
      mediaFrame.add(anImgFrmApp);
      anImgFrmApp.repaint();
      mediaFrame.repaint();
   }
    public void retrieveMedia() {
        String mediaURL = rtrvMediaFile("filename0");
        BufferedImage aBffrdImg1 = null;
        BufferedImage aBffrdImg2 = null;
        
        try {
            URL filename = new java.net.URL(mediaURL);
             aBffrdImg1 = ImageIO.read(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(StereoImages1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StereoImages1.class.getName()).log(Level.SEVERE, null, ex);
        }        
        System.out.println("ClusterHOG1x1x3x3App : path name = " + mediaURL);

        anImgArryLst.add(aBffrdImg1);
        String mediaURL2 = rtrvMediaFile("filename1");
        try {
            URL filename = new java.net.URL(mediaURL2);
            aBffrdImg2 = ImageIO.read(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClusterHeightWidthApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClusterHeightWidthApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        anImgArryLst.add(aBffrdImg2);

        int aWidth = aBffrdImg1.getWidth();
        int aHeight = aBffrdImg1.getHeight();
        System.out.println("StereoImages: aWidth = " + aWidth + ", aHeight = " + aHeight);
        BufferedImage offScreenImage = new BufferedImage(2 * aWidth, aHeight, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        add(anImgPlyrCnvs);
    }   
   public void startApp1()
   {     
      Image offScreenImage = createImage(900,400);

      anImgPlyrCnvs.setOffScrnImg(offScreenImage);
      anImgPlyrCnvs.process();
      anImgPlyrCnvs.repaint();
      
      getContentPane().add(anImgPlyrCnvs);
   }
   public void startApp2()
   {     
      String mediaURL = rtrvMediaFile();
      System.out.println("ImgFrmApp : path name = "+mediaURL); 
      BufferedImage aBffrdImg1 = ImageTool.rdImageFile(mediaURL);

      anImgArryLst.add(aBffrdImg1);
      String mediaURL2 = rtrvMediaFile();      
      BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
      anImgArryLst.add(aBffrdImg2);
      
      int aWidth = aBffrdImg1.getWidth();
      int aHeight = aBffrdImg1.getHeight();
      //System.out.println("StereoImages: aWidth = "+aWidth+", aHeight = "+aHeight);
      BufferedImage offScreenImage = new BufferedImage(2*aWidth,aHeight,BufferedImage.TYPE_INT_ARGB);
      anImgPlyrCnvs.setOffScrnImg(offScreenImage);
           
      anImgPlyrCnvs.process();
    
      anImgPlyrCnvs.repaint();
      add(anImgPlyrCnvs);
   }   
   public static String rtrvMediaFile() 
   {
    JFileChooser fileChooser = new JFileChooser();
    File mediaURL = null;

    int result = fileChooser.showOpenDialog( null );
    String pathName = "";
    
    if ( result == JFileChooser.APPROVE_OPTION ) // User chose a file
    {
        try {
            // Get the file as URL:
            mediaURL = fileChooser.getSelectedFile();
            pathName = mediaURL.getPath();
            
        } // end try
        catch ( Exception exception ) {
            System.err.println(exception.getMessage());
        }
    } 
    return pathName;
   }  
    public String rtrvMediaFile(String myParam) {
        System.out.println("ClusterHOG1x1x3x3App: param = "+myParam);
        String mediaURL = getParameter(myParam);
        System.out.println("ClusterHOG1x1x3x3App: url = "+mediaURL);
        if (mediaURL == null) {
            System.err.println("Invalid media file parameter");
        }
        return mediaURL;
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
    }
}