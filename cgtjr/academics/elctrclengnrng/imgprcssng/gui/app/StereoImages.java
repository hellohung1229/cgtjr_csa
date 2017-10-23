/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.gui.app;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.*;
import cgtjr.academics.elctrclengnrng.video.SADCornerRctfdFltr;
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
public class StereoImages extends JApplet
{
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;       
    private ArrayList anImgArryLst;
    
    public StereoImages()
    {
      anImgArryLst = new ArrayList();  
      anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
      //GrdntFilter aGrdntFilter = new GrdntFilter();
      //CornerDetect aCornerDetect = new CornerDetect();
      //OpticalFlowFltr aOpticalFlowFltr = new OpticalFlowFltr();      
      //SSDCornerFltr aSSDCornerFltr = new SSDCornerFltr();
      //SADCornerFltr aSADCornerFltr = new SADCornerFltr();      
      SADCornerRctfdFltr aSADCornerRctfdFltr = new SADCornerRctfdFltr();           
      //SSDRGBCornerFltr aSSDRGBCornerFltr = new SSDRGBCornerFltr();      
      //SSDRctfdCornerFltr aSSDRctfdCornerFltr = new SSDRctfdCornerFltr();         
      //CrrltnCrnrFltr aCrrltnCrnrFltr = new CrrltnCrnrFltr();
      //anImgRndrr = new ImageRndrr(aCrrltnMatchFltr);    
      //anImgRndrr = new ImageRndrr(aGrdntFilter); 
      //SimpleStereoFltr aSimpleStereoFltr = new SimpleStereoFltr();
      //DisparityRctfdFltr aDisparityRctfdFltr = new DisparityRctfdFltr();
      anImgRndrr = new ImageRndrr(aSADCornerRctfdFltr); 
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
      StereoImages anImgFrmApp = new StereoImages();
      anImgFrmApp.startApp2();
      mediaFrame.add(anImgFrmApp);
      anImgFrmApp.repaint();
      mediaFrame.repaint();
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
      System.out.println("StereoImages: aWidth = "+aWidth+", aHeight = "+aHeight);
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
}