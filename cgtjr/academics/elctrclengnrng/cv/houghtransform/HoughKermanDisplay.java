package cgtjr.academics.elctrclengnrng.cv.houghtransform;

import java.awt.*; 
import java.applet.*; 
import java.net.*; 

public class HoughKermanDisplay extends Applet 
{ 
   Image theImage1; 
   Image theImage2; 
   URL base; 
   MediaTracker mt; 
   public void init() 
   { 
      mt = new MediaTracker(this); 
      try { 
         base = getDocumentBase(); 
      }catch (Exception e) {} 
         theImage1 = getImage(base,"data/images/shapes/square1.png"); 
         theImage2 = performFilter(theImage1); 
         mt.addImage(theImage1,1);
         mt.addImage(theImage2,2);
      try { 
         mt.waitForAll(); 
      } 
      catch (InterruptedException e) {} 
    } 
   /*
    public Image performFilter(String myImageFileName)
    {
       try{
          return performFilter(new URL(myImageFileName));
       }catch(java.net.MalformedURLException mfe){
          System.out.println(mfe.getMessage());
       }
       return null;
    }*/
    public Image performFilter(Image myImage)
    {

       //GenericFilter aGenericFilter = new GenericFilter(new GausianKernel());
       //aGenericFilter.setImage(myImage);
       //Image anImage1 = aGenericFilter.performFiltering();
       //GradientFilter aGradientFilter = new GradientFilter(new SobelKernel());
       //aGradientFilter.setImage(myImage);
       //Image anImage2 = aGradientFilter.performFiltering();
       //double anOrientation[] = aGradientFilter.getOrientationAngle();
       //NonMaxFilter aNonMaxFilter = new NonMaxFilter(anImage2,anOrientation);
       //Image anImage3 = aNonMaxFilter.performFiltering();
       //int discreteAngles[] = aNonMaxFilter.getDiscreteOrientationAngle();
       //HysteresisFilter aHysteresisFilter = new HysteresisFilter(anImage3,discreteAngles);
       //Image anImage4 = aHysteresisFilter.performFiltering();
       HoughKerman aHoughKerman = new HoughKerman(myImage);
       aHoughKerman.detectRectangles();
       return myImage;
    }
   public void paint(Graphics g) 
   { 
      g.drawImage(theImage1,20,20,this); 
      g.drawImage(theImage2,theImage1.getWidth(null)+50,20,this); 
   }
}