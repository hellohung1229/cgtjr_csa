/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;


import cgtjr.academics.elctrclengnrng.cv.VanishingHeightWidth;
import cgtjr.academics.elctrclengnrng.cv.clustering.SyntheticViewFltr;
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
public class SyntheticViewFrameApp extends JApplet
{
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;       
    private ArrayList anImgArryLst;
    
    
    public SyntheticViewFrameApp()
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
      SyntheticViewFltr aSyntheticViewFltr = new SyntheticViewFltr();          
      anImgRndrr = new ImageRndrr(aSyntheticViewFltr); 
      anImgPlyrCnvs.setRndrr(anImgRndrr);
    }
    public void init() {
        //super.init();
    }
    public void start() 
    {    
       String mediaURL = "";
       if ((mediaURL = getParameter("file")) == null)
       {
          System.err.println("Invalid media file parameter");
       }
       String files[] = mediaURL.split(",");
       anImgArryLst.add(ImageTool.getImgArryLst(files,this));
       startApp1();
    }
   public void stop() {
      super.stop();
   }
   public static void main( String args[] ) 
   {
      rtrveData();
      JFrame mediaFrame;
      mediaFrame = new JFrame("Frame Parser");        
      mediaFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );      
      mediaFrame.setSize( 1000, 500 );
      mediaFrame.setVisible( true );         

      SyntheticViewFrameApp anImgFrmApp = new SyntheticViewFrameApp();
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
        System.out.println("ImgFrmApp : path name = " + mediaURL);

        anImgArryLst.add(aBffrdImg1);
        String mediaURL2 = rtrvMediaFile("filename0");
        try {
            URL filename = new java.net.URL(mediaURL2);
            aBffrdImg2 = ImageIO.read(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(StereoImages1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StereoImages1.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("EuclideanTransFrameApp: param = "+myParam);
        String mediaURL = getParameter(myParam);
        System.out.println("EuclideanTransFrameApp: url = "+mediaURL);
        if (mediaURL == null) {
            System.err.println("Invalid media file parameter");
        }
        return mediaURL;
    }   
    public static void rtrveData() {
        int GPL1x1 = 38;
        int GPL1y1 = 209;
        int GPL1c1 = 1;
        int GPL1x2 = 316;
        int GPL1y2 = 200;
        int GPL1c2 = 1;
        int GPL2x1 = 52;
        int GPL2y1 = 231;
        int GPL2c1 = 1;
        int GPL2x2 = 319;
        int GPL2y2 = 218;
        int GPL2c2 = 1;
        int GPL3x1 = 316;
        int GPL3y1 = 200;
        int GPL3c1 = 1;
        int GPL3x2 = 92;
        int GPL3y2 = 58;
        int GPL3c2 = 1;
        int GPL4x1 = 142;
        int GPL4y1 = 58;
        int GPL4c1 = 1;
        int GPL4x2 = 213;
        int GPL4y2 = 94;
        int GPL4c2 = 1;
        int VL1x1 = 303;
        int VL1y1 = 167;
        int VL1c1 = 1;
        int VL1x2 = 309;
        int VL1y2 = 104;
        int VL1c2 = 1;
        int VL2x1 = 246;
        int VL2y1 = 135;
        int VL2c1 = 1;
        int VL2x2 = 250;
        int VL2y2 = 80;
        int VL2c2 = 1;
        int RLx1 = 135;
        int RLy1 = 215;
        int RLc1 = 1;
        int RLx2 = 138;
        int RLy2 = 146;
        int RLc2 = 1;
        float RHeight = 7.5f;
        
        /*
        float GPL1x1 = Float.valueOf(GPL1x1);
        float GPL1y1 = Float.valueOf(GPL1y1);
        float GPL1c1 = Float.valueOf(GPL1c1);
        float GPL1x2 = Float.valueOf(GPL1x2);
        float GPL1y2 = Float.valueOf(GPL1y2);
        float GPL1c2 = Float.valueOf(GPL1c2);

        float GPL2x1 = Float.valueOf(GPL2x1);
        float GPL2y1 = Float.valueOf(GPL2y1);
        float GPL2c1 = Float.valueOf(GPL2c1);
        float GPL2x2 = Float.valueOf(GPL2x2);
        float GPL2y2 = Float.valueOf(GPL2y2);
        float GPL2c2 = Float.valueOf(GPL2c2);

        float GPL3x1 = Float.valueOf(GPL3x1);
        float GPL3y1 = Float.valueOf(GPL3y1);
        float GPL3c1 = Float.valueOf(GPL3c1);
        float GPL3x2 = Float.valueOf(GPL3x2);
        float GPL3y2 = Float.valueOf(GPL3y2);
        float GPL3c2 = Float.valueOf(GPL3c2);

        float GPL4x1 = Float.valueOf(GPL4x1);
        float GPL4y1 = Float.valueOf(GPL4y1);
        float GPL4c1 = Float.valueOf(GPL4c1);
        float GPL4x2 = Float.valueOf(GPL4x2);
        float GPL4y2 = Float.valueOf(GPL4y2);
        float GPL4c2 = Float.valueOf(GPL4c2);

        float VL1x1 = Float.valueOf(VL1x1);
        float VL1y1 = Float.valueOf(VL1y1);
        float VL1c1 = Float.valueOf(VL1c1);
        float VL1x2 = Float.valueOf(VL1x2);
        float VL1y2 = Float.valueOf(VL1y2);
        float VL1c2 = Float.valueOf(VL1c2);

        float VL2x1 = Float.valueOf(VL2x1);
        float VL2y1 = Float.valueOf(VL2y1);
        float VL2c1 = Float.valueOf(VL2c1);
        float VL2x2 = Float.valueOf(VL2x2);
        float VL2y2 = Float.valueOf(VL2y2);
        float VL2c2 = Float.valueOf(VL2c2);

        float RLx1 = Float.valueOf(RLx1);
        float RLy1 = Float.valueOf(RLy1);
        float RLc1 = Float.valueOf(RLc1);
        float RLx2 = Float.valueOf(RLx2);
        float RLy2 = Float.valueOf(RLy2);
        float RLc2 = Float.valueOf(RLc2);

        float RHeight = Float.valueOf(RHeight);
        */
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