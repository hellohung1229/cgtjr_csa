/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageRndrr;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.FrameParser;
import java.awt.Canvas;
import java.util.ArrayList;

/**
 *
 * @author Nit
 */
public class ImgCntrllrLstnr 
{
   ImgPlyrCnvs anImgPlyr;  
   ImageRndrr anImgRndrr; 
   
   public ImgCntrllrLstnr(ArrayList myImgArryLst)
   {
      anImgPlyr = new ImgPlyrCnvs(myImgArryLst);
      anImgRndrr = new ImageRndrr();    
      anImgPlyr.setRndrr(anImgRndrr);       
   }  
   public void setParser(FrameParser myFrmPrsr)
   {
      anImgRndrr.setParser(myFrmPrsr);
   }
   public Canvas getImgPlyr()
   {
       return anImgPlyr;
   }
   public Canvas start()
   {

      anImgPlyr.process();
      anImgPlyr.repaint();
      return anImgPlyr;
   }    

    
}
