/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.clustering;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgPlyrCnvs;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgCntrllrLstnr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImgFrmScrllPnl;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageRndrr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.gui.app.*;
import cgtjr.academics.elctrclengnrng.imgprcssng.*;
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
public class ClusterApp extends JApplet
{

    //private static ArrayList imgArryLst;
    private static ImgFrmScrllPnl imgImgFrmScrllPnl;

    private ImgCntrllrLstnr anImageCntrllrLstnr;
    private ImgPlyrCnvs anImgPlyrCnvs;
    private ImageRndrr anImgRndrr;       
    private ArrayList anImgArryLst;
    
    public ClusterApp()
    {
      anImgArryLst = new ArrayList();      
      anImgPlyrCnvs = new ImgPlyrCnvs(anImgArryLst);
      anImgRndrr = new ImageRndrr(new ClusterFltr());
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
      JFrame mediaFrame ;     
      mediaFrame = new JFrame( "Frame Parser" );        
      mediaFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );      
      mediaFrame.setSize( 900, 400 );
      mediaFrame.setVisible( true );         

      ClusterApp anImgFrmApp = new ClusterApp();
      anImgFrmApp.startApp2();
      mediaFrame.add(anImgFrmApp);
      anImgFrmApp.repaint();
      mediaFrame.repaint();
   }
   public void startApp1()
   {     
      System.out.println("IMgfrmApp: test");
      Image offScreenImage = createImage(900,400);
      //anImageCntrllrLstnr = new ImgCntrllrLstnr(imgArryLst);
      //anImageCntrllrLstnr.start();
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
      
      //String mediaURL2 = rtrvMediaFile();      
      //BufferedImage aBffrdImg2 = ImageTool.rdImageFile(mediaURL2);
      //anImgArryLst.add(aBffrdImg2);

      BufferedImage offScreenImage = new BufferedImage(900,400,BufferedImage.TYPE_INT_ARGB);
      anImgPlyrCnvs.setOffScrnImg(offScreenImage);
     
      anImgPlyrCnvs.process();
      
      anImgPlyrCnvs.repaint();
      add(anImgPlyrCnvs);
      //anImageCntrllrLstnr = new ImgCntrllrLstnr(anImgArryLst);
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
