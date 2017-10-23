/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgPlyrCnvs;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageRndrr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageSeriesRndrr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.texture.LawTxtrFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.texture.LawTxtrKrnl;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.BinAndMaskFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RcrsvCnnctFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.ErodeFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.StrctElmnt;
import java.awt.Image;
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
public class ObjctDtctnApp extends JApplet {

    //private static ArrayList imgArryLst;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ImageRndrr anImgRndrr2;
    private ImageRndrr anImgRndrr3;
    private ImageRndrr anImgRndrr4;
    private ImageRndrr anImgRndrr5;
    private ImageRndrr anImgRndrr6;
    private ImageRndrr anImgRndrr7;
    private ImageRndrr anImgRndrr7b;    
    private ImageRndrr anImgRndrr7c;      
    private ImageRndrr anImgRndrr8;
    private ImageRndrr anImgRndrr9;
    private ImageRndrr anImgRndrr10;
    private ImageRndrr anImgRndrr11;
    private ImageRndrr anImgRndrr12;
    private ImageRndrr anImgRndrr13;
    private ImageRndrr anImgRndrr14;
    private ImageRndrr anImgRndrr15;
    private ImageRndrr anImgRndrr16;
    private ImageRndrr anImgRndrr17;
    private ArrayList anImgArryLst;
    private ImageSeriesRndrr imgSrsRndrr;

    public ObjctDtctnApp() {
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        GraySclFltr aGraySclFltr = new GraySclFltr();
        
        anImgRndrr = new ImageRndrr(aGraySclFltr);
       
        LawTxtrFltr aLawTxtrFltr1 = new LawTxtrFltr(LawTxtrKrnl.getE5(),LawTxtrKrnl.getE5());
        anImgRndrr2 = new ImageRndrr(aLawTxtrFltr1);
        
        NghbrThreshold aNghbrThreshold1 = new NghbrThreshold();
        anImgRndrr3 = new ImageRndrr(aNghbrThreshold1);      
       
        LawTxtrFltr aLawTxtrFltr2 = new LawTxtrFltr(LawTxtrKrnl.getE5(),LawTxtrKrnl.getE5());
        aLawTxtrFltr2.setInputFilter(aGraySclFltr);
        anImgRndrr4 = new ImageRndrr(aLawTxtrFltr2);

        NghbrThreshold aNghbrThreshold2 = new NghbrThreshold();
        anImgRndrr5 = new ImageRndrr(aNghbrThreshold2); 
      
        BinAndMaskFltr binAndMskFltr = new BinAndMaskFltr();
        binAndMskFltr.setInputFilter(aNghbrThreshold1);        
        anImgRndrr6 = new ImageRndrr(binAndMskFltr);
     
        ErodeFltr anErodeFltr = new ErodeFltr(new StrctElmnt(4,4));
        anImgRndrr7 = new ImageRndrr(anErodeFltr);     
     
        
        YIntnstyFltr anYIntnstyFltr = new YIntnstyFltr();
        anYIntnstyFltr.setIntnstyFltr(aGraySclFltr);
        anImgRndrr8 = new ImageRndrr(anYIntnstyFltr); 
                
        RcrsvCnnctFltr aRcrsvCnnctFltr = new RcrsvCnnctFltr();
        aRcrsvCnnctFltr.setInputFilter(anErodeFltr);              
        anImgRndrr9 = new ImageRndrr(aRcrsvCnnctFltr);   
        
        imgSrsRndrr = new ImageSeriesRndrr();
        imgSrsRndrr.addRndrr(anImgRndrr); 
        
        
        imgSrsRndrr.addRndrr(anImgRndrr2); 
        imgSrsRndrr.addRndrr(anImgRndrr3);          
        imgSrsRndrr.addRndrr(anImgRndrr4);
        imgSrsRndrr.addRndrr(anImgRndrr5);         
        imgSrsRndrr.addRndrr(anImgRndrr6); 
        imgSrsRndrr.addRndrr(anImgRndrr7);

        imgSrsRndrr.addRndrr(anImgRndrr8); 
        imgSrsRndrr.addRndrr(anImgRndrr9);         

        anImgPlyrCnvs.setRndrr(imgSrsRndrr);
    }

    public void init() {
        //super.init();
    }

    public void start() {
        String mediaURL = "";
        if ((mediaURL = getParameter("file")) == null) {
            System.err.println("Invalid media file parameter");
        }
        String files[] = mediaURL.split(",");
        anImgArryLst.add(ImageTool.getImgArryLst(files, this));
        startApp1();
    }
    public void stop() {
        super.stop();
    }
    public static void main(String args[]) {
        JFrame mediaFrame;
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(500, 700);
        mediaFrame.setVisible(true);

        ObjctDtctnApp anImgFrmApp = new ObjctDtctnApp();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }

    public void startApp1() {
        System.out.println("IMgfrmApp: test");
        Image offScreenImage = createImage(500, 700);
        //anImageCntrllrLstnr = new ImgCntrllrLstnr(imgArryLst);
        //anImageCntrllrLstnr.start();
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);
        anImgPlyrCnvs.process();
        anImgPlyrCnvs.repaint();

        getContentPane().add(anImgPlyrCnvs);
    }

    public void startApp2() {
        String mediaURL = rtrvMediaFile();
        System.out.println("ImgFrmApp : path name = " + mediaURL);
        BufferedImage aBffrdImg1 = ImageTool.rdImageFile(mediaURL);
        anImgArryLst.add(aBffrdImg1);

        //String mediaURL2 = rtrvMediaFile();      
        //BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
        //anImgArryLst.add(aBffrdImg2);

        BufferedImage offScreenImage = new BufferedImage(500, 700, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        add(anImgPlyrCnvs);
        //anImageCntrllrLstnr = new ImgCntrllrLstnr(anImgArryLst);
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