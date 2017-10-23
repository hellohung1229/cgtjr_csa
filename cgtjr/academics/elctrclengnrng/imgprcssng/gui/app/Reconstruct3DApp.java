/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.elctrclengnrng.cv.FndmntlMatrixDLTHOG;
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
 * @author 
 */
public class Reconstruct3DApp extends JApplet
{
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;       
    private ArrayList anImgArryLst;
    
    
    public Reconstruct3DApp()
    {
      anImgArryLst = new ArrayList();  
      anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
      FndmntlMatrixDLTHOG aFndmntlMatrixDLTHOG = new FndmntlMatrixDLTHOG(); 
      //ClusterHOGTracker aFndmntlMatrixDLTHOG = new ClusterHOGTracker();

      //FndmntlMatrixDLTHOG.setMatchDstnceThrshld(100);    

      //aFndmntlMatrixDLTHOG.setEigenThreshold(4000);
      aFndmntlMatrixDLTHOG.setTmprlGrdntThrshld(15);        
      //aFndmntlMatrixDLTHOG.setHogDstnceThrshld(30.0);        
      aFndmntlMatrixDLTHOG.setDisplayArrows(true);
      aFndmntlMatrixDLTHOG.setDisplayCorners(true);
      //aFndmntlMatrixDLTHOG.setMaxClusterDistance(10);
        
      anImgRndrr = new ImageRndrr(aFndmntlMatrixDLTHOG); 
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
      JFrame mediaFrame;
      mediaFrame = new JFrame("Frame Parser");        
      mediaFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );      
      mediaFrame.setSize( 1000, 500 );
      mediaFrame.setVisible( true );         
      Reconstruct3DApp anImgFrmApp = new Reconstruct3DApp();
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
}