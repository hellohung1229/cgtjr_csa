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
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GrdntCrrltnBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.NCCGridCrnrActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.video.CrrltnCrnrFltr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.ShpTracker;
import cgtjr.academics.general.FileNameVar;
import cgtjr.academics.general.gui.WTSInfo;
import java.awt.BorderLayout;
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
public class ImgFrmApp4 extends JApplet {

    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ArrayList anImgArryLst;
    private BorderLayout myLayout;
    
    public ImgFrmApp4() {
        myLayout = new BorderLayout();
        setLayout(myLayout);
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        
        FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg", 10,10,0,30,40, 0, 280, 270, 0, 2, 2, 1, 182, 232, 0);

        GrdntCrrltnBndry aShpBndry = new GrdntCrrltnBndry(aDmnsnVar,new CrrltnCrnrFltr());
        aShpBndry.setThreshHold(24);
        CrrltnCrnrFltr anImageFltr = (CrrltnCrnrFltr)aShpBndry.getFilter();
        NCCGridCrnrActn crrltnMtchDrwActn = new NCCGridCrnrActn(anImageFltr);  
        
        ShpTracker aShpTracker = new ShpTracker(aShpBndry,crrltnMtchDrwActn);
        aShpTracker.setIsCntrTrckng(false);
        PointParser vdPointParser = new PointParser(aShpTracker);        
               

        anImgRndrr = new ImageRndrr(vdPointParser);
        anImgPlyrCnvs.setRndrr(anImgRndrr);
    }
    /*
    public void start() {
        String mediaURL = "";
        if ((mediaURL = getParameter("file")) == null) {
            System.err.println("Invalid media file parameter");
        }
        String files[] = mediaURL.split(",");
        anImgArryLst.add(ImageTool.getImgArryLst(files, this));
        startApp1();
    }*/
    public void stop() {
        super.stop();
    }
    public static void main(String args[]) {
        JFrame mediaFrame;
        System.out.println("test1");
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(900, 400);
        mediaFrame.setVisible(true);

        ImgFrmApp4 anImgFrmApp = new ImgFrmApp4();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }
    /*
    public void startApp1() {

        Image offScreenImage = createImage(900, 400);
        //anImageCntrllrLstnr = new ImgCntrllrLstnr(imgArryLst);
        //anImageCntrllrLstnr.start();
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);
        anImgPlyrCnvs.process();
        anImgPlyrCnvs.repaint();

        getContentPane().add(anImgPlyrCnvs);
    }*/
    public void startApp2() {
        
                System.out.println("test2");
        String mediaURL = rtrvMediaFile();
        System.out.println("ImgFrmApp : path name = " + mediaURL);
        BufferedImage aBffrdImg1 = ImageTool.rdImageFile(mediaURL);
        anImgArryLst.add(aBffrdImg1);
        String mediaURL2 = rtrvMediaFile();
        BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
        anImgArryLst.add(aBffrdImg2);
        System.out.println("IMgfrmApp: test 3");
        BufferedImage offScreenImage = new BufferedImage(900, 400, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        
        add(anImgPlyrCnvs,BorderLayout.CENTER);
        add(new WTSInfo(),BorderLayout.SOUTH);        

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