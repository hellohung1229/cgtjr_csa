/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.cv.HOGCornerDetectOptmzd;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import cgtjr.academics.elctrclengnrng.video.SADCornerRctfdFltr;
import cgtjr.academics.general.ThemeSet;
import java.awt.BorderLayout;
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
public class HOG1x1x3x3Web extends JApplet {

    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ArrayList anImgArryLst;

    public HOG1x1x3x3Web() {
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
        HOGCornerDetectOptmzd aHOGCornerDetectOptmzd = new HOGCornerDetectOptmzd(); 
        aHOGCornerDetectOptmzd.setThreshold(10000);    
        aHOGCornerDetectOptmzd.setMatchDstnceThrshld(40);          
        aHOGCornerDetectOptmzd.setDisplayArrows(true);
        anImgRndrr = new ImageRndrr(aHOGCornerDetectOptmzd);
        anImgPlyrCnvs.setRndrr(anImgRndrr);
    }
    public void init() {
        //super.init();
        ThemeSet.setDocumentBase(getCodeBase().toString());         
    }
    public void start() {
        ThemeSet.setDocumentBase(getCodeBase().toString());         
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
    public static void main(String args[]) {
        JFrame mediaFrame;
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(1000, 500);
        mediaFrame.setVisible(true);
        HOG1x1x3x3Web anImgFrmApp = new HOG1x1x3x3Web();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }
    public void startApp1() {
        String mediaURL = rtrvMediaFile("filename0");
        BufferedImage aBffrdImg1 = null;
        BufferedImage aBffrdImg2 = null;
        
        try {
            URL filename = new java.net.URL(mediaURL);
             aBffrdImg1 = ImageIO.read(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HOG1x1x3x3Web.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HOG1x1x3x3Web.class.getName()).log(Level.SEVERE, null, ex);
        }        
        System.out.println("ImgFrmApp : path name = " + mediaURL);

        anImgArryLst.add(aBffrdImg1);
        String mediaURL2 = rtrvMediaFile("filename1");
        try {
            URL filename = new java.net.URL(mediaURL2);
            aBffrdImg2 = ImageIO.read(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HOG1x1x3x3Web.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HOG1x1x3x3Web.class.getName()).log(Level.SEVERE, null, ex);
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

    public void startApp2() {
        String mediaURL = rtrvMediaFile();
        System.out.println("ImgFrmApp : path name = " + mediaURL);
        BufferedImage aBffrdImg1 = ImageTool.rdImageFile(mediaURL);

        anImgArryLst.add(aBffrdImg1);
        String mediaURL2 = rtrvMediaFile();
        BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
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

    public String rtrvMediaFile(String myParam) {
        System.out.println("StereoImages1: param = "+myParam);
        String mediaURL = getParameter(myParam);
        System.out.println("StereoImages1: url = "+mediaURL);
        if (mediaURL == null) {
            System.err.println("Invalid media file parameter");
        }
        return mediaURL;
    }

    public static String rtrvMediaFile() {
        JFileChooser fileChooser = new JFileChooser();
        File mediaURL = null;

        int result = fileChooser.showOpenDialog(null);
        String pathName = "";

        if (result == JFileChooser.APPROVE_OPTION) // User chose a file
        {
            try {
                // Get the file as URL:
                mediaURL = fileChooser.getSelectedFile();
                pathName = mediaURL.getPath();

            } // end try
            catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        }
        return pathName;
    }
}