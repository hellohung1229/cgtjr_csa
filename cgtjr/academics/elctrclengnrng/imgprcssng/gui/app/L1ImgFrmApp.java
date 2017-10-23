/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgPlyrCnvs;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgFrmScrllPnl;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageRndrr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.histogram.Histogram;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Nit
 */
public class L1ImgFrmApp extends JApplet {

    private static ImgFrmScrllPnl imgImgFrmScrllPnl;
    private ImgCntrllrLstnr anImageCntrllrLstnr;
    
    private ImgPlyrCnvs anImgPlyrCnvs1;
    private ImgPlyrCnvs anImgPlyrCnvs2;
    
    private ImageRndrr anImgRndrr1;
    private ImageRndrr anImgRndrr2;
    
    private ArrayList anImgArryLst1;
    private ArrayList anImgArryLst2;    
    private Histogram aHistogram;
    private Histogram aHistogram2;
    
    public L1ImgFrmApp() {
        anImgArryLst1 = new ArrayList();
        anImgArryLst2 = new ArrayList();        
        
        anImgPlyrCnvs1 = new ImgPlyrCnvs(anImgArryLst1);
        anImgPlyrCnvs2 = new ImgPlyrCnvs(anImgArryLst2);
        
        aHistogram = new Histogram();
        aHistogram2 = new Histogram();
        
        anImgRndrr1 = new ImageRndrr(aHistogram);
        anImgRndrr2 = new ImageRndrr(aHistogram2);
        
        anImgPlyrCnvs1.setRndrr(anImgRndrr1);
        anImgPlyrCnvs2.setRndrr(anImgRndrr2);        
    }
    public static void main(String args[]) {
        JFrame mediaFrame;
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(900,400);
        mediaFrame.setVisible(true);
        L1ImgFrmApp anImgFrmApp = new L1ImgFrmApp();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        mediaFrame.repaint();
    }
    public void startApp2() {
        String mediaURL = rtrvMediaFile();
        BufferedImage aBffrdImg1 = ImageTool.rdImageFile(mediaURL);         
        anImgArryLst1.add(aBffrdImg1);
                
        String mediaURL2 = rtrvMediaFile();
        BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
        anImgArryLst2.add(aBffrdImg2);
        
        BufferedImage offScreenImage1 = new BufferedImage(900, 400, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs1.setOffScrnImg(offScreenImage1);
        BufferedImage offScreenImage2 = new BufferedImage(900, 400, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs2.setOffScrnImg(offScreenImage1);
        

                
        anImgPlyrCnvs1.process();
        anImgPlyrCnvs2.setInitialX(360);
        anImgPlyrCnvs2.process();        
        //anImgPlyrCnvs1.repaint();
        add(anImgPlyrCnvs1);
        add(anImgPlyrCnvs2);
        
        int hstgrm1[] = aHistogram.getHistogram();
        int hstgrm2[] = aHistogram2.getHistogram();    
        aHistogram.cmptL1(hstgrm1,hstgrm2);
        
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
