/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.SSDCornerActn;
import cgtjr.academics.elctrclengnrng.video.SSDCornerFltr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.CrrltnCrnrBndry;
import cgtjr.academics.elctrclengnrng.video.shapepnt.SDCLShpTracker;
import cgtjr.academics.general.FileNameVar;
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
public class ImgFrmApp2 extends JApplet {

    private static ImgFrmScrllPnl imgImgFrmScrllPnl;
    private ImgCntrllrLstnr anImageCntrllrLstnr;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ArrayList anImgArryLst;

    public ImgFrmApp2() {
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        //GrdntFilter aGrdntFilter = new GrdntFilter();
        //OpticalFlowFltr aOpticalFlowFltr = new OpticalFlowFltr();  

        FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg", 0, 0, 0, 30, 40, 0, 330, 270, 0, 8, 8, 1, 182, 260, 0);
        //ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);
        System.out.println("test1");
        CrrltnCrnrBndry aShpBndry = new CrrltnCrnrBndry(aDmnsnVar);
        SSDCornerFltr optclFlwFltr = (SSDCornerFltr) aShpBndry.getFilter();
        optclFlwFltr.setThreshold(125);
        SSDCornerActn crrltnMtchDrwActn = new SSDCornerActn(optclFlwFltr);
        SDCLShpTracker aShpTracker = new SDCLShpTracker(aShpBndry, crrltnMtchDrwActn);
        aShpTracker.setIsCntrTrckng(false);

        PointParser vdPointParser = new PointParser(aShpTracker);

        SSDCornerFltr aCrrltnMatchFltr = new SSDCornerFltr();
        //anImgRndrr = new ImageRndrr(aCrrltnMatchFltr);    
        anImgRndrr = new ImageRndrr(vdPointParser);
        anImgPlyrCnvs.setRndrr(anImgRndrr);
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
        System.out.println("test1");
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(900, 400);
        mediaFrame.setVisible(true);
        System.out.println("test2");
        ImgFrmApp2 anImgFrmApp = new ImgFrmApp2();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }

    public void startApp1() {
        System.out.println("IMgfrmApp: test");
        Image offScreenImage = createImage(900, 400);

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
        String mediaURL2 = rtrvMediaFile();
        BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
        anImgArryLst.add(aBffrdImg2);

        BufferedImage offScreenImage = new BufferedImage(900, 400, BufferedImage.TYPE_INT_ARGB);
        anImgPlyrCnvs.setOffScrnImg(offScreenImage);

        anImgPlyrCnvs.process();

        anImgPlyrCnvs.repaint();
        add(anImgPlyrCnvs);
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
