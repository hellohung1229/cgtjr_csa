package cgtjr.academics.elctrclengnrng.videotmp;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;


public class VideoDisplay extends JApplet
{
   URL aURL1 = null;
   Image anImage1 = null;
   MediaTracker mt;
   URL base;
   public void init()
   {
      String filename1 = getParameter("filename1");
      mt = new MediaTracker(this);
      try {
         base = getDocumentBase();
      }catch (Exception e) {}
      try{
         aURL1 = new URL(base,filename1);
      }catch(MalformedURLException mfe){
         System.out.println(mfe.getMessage());
      }
      try{
         anImage1 = ImageIO.read(aURL1);
      }catch(IOException ioe){
         ioe.getMessage();
      }
      VideoCanvas aVideoCanvas = new VideoCanvas();
      VideoCompRenderer aVideoCompRenderer = new VideoCompRenderer();
      aVideoCompRenderer.setComponent(aVideoCanvas);
      //getContentPane().add(aVideoCanvas);
   }
   /*
   public void paint(Graphics g)
   {
      g.drawImage(anImage1,0,0,this);
   }*/
}