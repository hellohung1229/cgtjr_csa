/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import cgtjr.academics.general.upload.FileDirUrlUpload;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//import javax.media.rtp.SessionManager;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Nit
 */
public class CornerFeatureUpload extends JApplet {

    private FileDirUrlUpload fileDirUpload;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ArrayList anImgArryLst;
    private String picturePath;
    private String urlPath;
    private BufferedImage offScreenImage;
    //private SessionManager session;

    public CornerFeatureUpload() {
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        //GrdntFilter aGrdntFilter = new GrdntFilter();
        fileDirUpload = new FileDirUrlUpload();
        CornerDetect aCornerDetect = new CornerDetect();
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
        anImgRndrr = new ImageRndrr(aCornerDetect);
        anImgPlyrCnvs.setRndrr(anImgRndrr);
    }

    public void init() {
        //super.init();
    }

    public void start() {
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
        CornerFeatureUpload anImgFrmApp = new CornerFeatureUpload();
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
            Logger.getLogger(CornerFeatureUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CornerFeatureUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("ImgFrmApp : path name = " + mediaURL);

        anImgArryLst.add(aBffrdImg1);

        /*
         String mediaURL2 = rtrvMediaFile("filename0");
         try {
         URL filename = new java.net.URL(mediaURL2);
         aBffrdImg2 = ImageIO.read(filename);
         } catch (MalformedURLException ex) {
         Logger.getLogger(GradientFeatures.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
         Logger.getLogger(GradientFeatures.class.getName()).log(Level.SEVERE, null, ex);
         }

         anImgArryLst.add(aBffrdImg2);
         */
        int aWidth = aBffrdImg1.getWidth();
        int aHeight = aBffrdImg1.getHeight();
        System.out.println("GradientFeatureUpload: aWidth = " + aWidth + ", aHeight = " + aHeight);
        offScreenImage = new BufferedImage(aWidth, aHeight, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        add(anImgPlyrCnvs);
        transmitData();
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
        offScreenImage = new BufferedImage(2 * aWidth, aHeight, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        add(anImgPlyrCnvs);
        transmitData();
    }

    public void transmitData() {
        //int userlogonid = session.getUserLoginID();
        int userlogonid = 1;
        HashMap<String, String> aHashMap = new HashMap<String, String>();
        aHashMap.put("userlogonid", "" + userlogonid);
        //urlPath = "http://vtechconnect.appspot.com/service/admin/filedirectory/filediruploadservlet";
        urlPath = "http://featuredetection.appspot.com/service/admin/filedirectory/filediruploadservlet";        
        try {
            URL aURL = new URL(urlPath);
            //File aFile = new File(picturePath);
            fileDirUpload.uploadData(aURL, offScreenImage, "file6", aHashMap);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.err.println("" + (e.getMessage()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("MultiDataUploadActivity: " + (e.getMessage()));
        }
    }


    public String rtrvMediaFile(String myParam) {
        System.out.println("StereoImages1: param = " + myParam);
        String mediaURL = getParameter(myParam);
        System.out.println("StereoImages1: url = " + mediaURL);
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