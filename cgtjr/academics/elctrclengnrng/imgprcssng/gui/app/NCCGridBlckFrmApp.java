/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GrdntCrrltnBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.NCCGridBlckActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.PointParser;
import cgtjr.academics.elctrclengnrng.video.CrrltnCrnrFltr;
import cgtjr.academics.elctrclengnrng.video.shapepnt.ShpTracker;
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
public class NCCGridBlckFrmApp extends JApplet {

    private static ImgFrmScrllPnl imgImgFrmScrllPnl;
    private ImgCntrllrLstnr anImageCntrllrLstnr;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;
    private ArrayList anImgArryLst;
    private NCCGridBlckActn crrltnMtchDrwActn;
    
    
    public NCCGridBlckFrmApp() {
        anImgArryLst = new ArrayList();
        anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
        
        FileNameVar aDmnsnVar = new FileNameVar("data/images/textures/white3_3x3.jpg", 0, 0, 0, 10, 62, 0, 330, 204, 0, 4, 4, 1, 165, 155, 0);
        //ImageBndry aShpBndry = new SSDImgBndry(aDmnsnVar);
        GrdntCrrltnBndry aShpBndry = new GrdntCrrltnBndry(aDmnsnVar,new CrrltnCrnrFltr());
        aShpBndry.setThreshHold(64);
        //SSDCornerFltr anImageFltr = (SSDCornerFltr)aShpBndry.getFilter();
        //SSDCornerActn crrltnMtchDrwActn = new SSDCornerActn(anImageFltr);  

        CrrltnCrnrFltr anImageFltr = (CrrltnCrnrFltr)aShpBndry.getFilter();
        crrltnMtchDrwActn = new NCCGridBlckActn(anImageFltr); 
        
        //SDCLShpTracker aShpTracker = new SDCLShpTracker(aShpBndry,crrltnMtchDrwActn);
        ShpTracker aShpTracker = new ShpTracker(aShpBndry,crrltnMtchDrwActn);        
        aShpTracker.setIsCntrTrckng(false);
        aShpTracker.setTrackerShpPntLstnr(crrltnMtchDrwActn);
        PointParser vdPointParser = new PointParser(aShpTracker);        
               
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
        mediaFrame = new JFrame("Frame Parser");
        mediaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediaFrame.setSize(900, 400);
        mediaFrame.setVisible(true);
        NCCGridBlckFrmApp anImgFrmApp = new NCCGridBlckFrmApp();
        anImgFrmApp.startApp2();
        mediaFrame.add(anImgFrmApp);
        anImgFrmApp.repaint();
        mediaFrame.repaint();
    }
    public void startApp1() {        
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