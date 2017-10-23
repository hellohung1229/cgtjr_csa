/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgPlyrCnvs;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgFrmScrllPnl;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageRndrr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageSeriesRndrr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.color.RedThreshold;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.BinXOrMaskFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.BinAndMaskFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.BinOrMaskFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.CnnctCmpnntLbl;
import cgtjr.academics.elctrclengnrng.imgprcssng.mrphlgcl.DialateFltr;
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
public class Gear2App extends JApplet {

    //private static ArrayList imgArryLst;
    private static ImgFrmScrllPnl imgImgFrmScrllPnl;
    private ImgCntrllrLstnr anImageCntrllrLstnr;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ImageRndrr anImgRndrr2;
    private ImageRndrr anImgRndrr3;
    private ImageRndrr anImgRndrr4;
    private ImageRndrr anImgRndrr5;
    private ImageRndrr anImgRndrr6;
    private ImageRndrr anImgRndrr7;
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

    public Gear2App() {
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        RedThreshold rdThrshld = new RedThreshold();

        anImgRndrr = new ImageRndrr(rdThrshld);
        anImgRndrr2 = new ImageRndrr(new ErodeFltr());

        DialateFltr aDialateFltr1 = new DialateFltr();

        anImgRndrr3 = new ImageRndrr(aDialateFltr1);
        int thrshldOutput[] = rdThrshld.getFltrdData();
        BinOrMaskFltr binOrMskFltr = new BinOrMaskFltr();
        binOrMskFltr.setMaskValues(thrshldOutput);
        anImgRndrr4 = new ImageRndrr(binOrMskFltr);

        ErodeFltr anErodeFltr = new ErodeFltr("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\circleelmnt_lrg_1.gif");
        anImgRndrr5 = new ImageRndrr(anErodeFltr);
        DialateFltr aDialateFltr2 = new DialateFltr("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\circleelmnt2_lrg_1.gif");
        anImgRndrr6 = new ImageRndrr(aDialateFltr2);
        BinOrMaskFltr binOrMskFltr2 = new BinOrMaskFltr();
        binOrMskFltr2.setMaskValues(thrshldOutput);
        anImgRndrr7 = new ImageRndrr(binOrMskFltr2);

        int dltOutput[] = aDialateFltr2.getFltrdData();
        BinXOrMaskFltr binXOrMskFltr = new BinXOrMaskFltr();
        binXOrMskFltr.setMaskValues(dltOutput);
        anImgRndrr8 = new ImageRndrr(binXOrMskFltr);

        DialateFltr aDialateFltr3 = new DialateFltr(new StrctElmnt());
        anImgRndrr9 = new ImageRndrr(aDialateFltr3);

        DialateFltr aDialateFltr4 = new DialateFltr("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\rngelmnt170x170.gif");
        anImgRndrr11 = new ImageRndrr(aDialateFltr4);

        InvrsFltr anInvrsFltr = new InvrsFltr();
        anImgRndrr12 = new ImageRndrr(anInvrsFltr);

        ErodeFltr anErodeFltr2 = new ErodeFltr("C:\\cthomas\\developement\\us3da_crrnt\\src\\public_html\\us3da\\data\\images\\shapes\\circleelmnt135x135.gif");
        anImgRndrr13 = new ImageRndrr(anErodeFltr2);

        BinAndMaskFltr binAndMskFltr = new BinAndMaskFltr();
        binAndMskFltr.setInputFilter(anInvrsFltr);
        anImgRndrr10 = new ImageRndrr(binAndMskFltr);

        ErodeFltr anErodeFltr3 = new ErodeFltr(new StrctElmnt(7, 7));
        anImgRndrr14 = new ImageRndrr(anErodeFltr3);

        DialateFltr aDialateFltr5 = new DialateFltr(new StrctElmnt(7, 7));
        anImgRndrr15 = new ImageRndrr(aDialateFltr5);

        CnnctCmpnntLbl aCnnctCmpnntLbl = new CnnctCmpnntLbl();
        anImgRndrr16 = new ImageRndrr(aCnnctCmpnntLbl);

        BinOrMaskFltr binOrMskFltr4 = new BinOrMaskFltr();
        binOrMskFltr4.setMaskValues(thrshldOutput);
        anImgRndrr17 = new ImageRndrr(binOrMskFltr4);

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

        imgSrsRndrr.addRndrr(anImgRndrr12);
        imgSrsRndrr.addRndrr(anImgRndrr13);
        imgSrsRndrr.addRndrr(anImgRndrr11);
        imgSrsRndrr.addRndrr(anImgRndrr10);
        imgSrsRndrr.addRndrr(anImgRndrr14);
        imgSrsRndrr.addRndrr(anImgRndrr15);
        imgSrsRndrr.addRndrr(anImgRndrr16);
        imgSrsRndrr.addRndrr(anImgRndrr17);
        
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
        mediaFrame.setSize(900, 400);
        mediaFrame.setVisible(true);

        Gear2App anImgFrmApp = new Gear2App();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }

    public void startApp1() {
        System.out.println("IMgfrmApp: test");
        Image offScreenImage = createImage(900, 400);
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

        BufferedImage offScreenImage = new BufferedImage(900, 400, BufferedImage.TYPE_INT_ARGB);
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