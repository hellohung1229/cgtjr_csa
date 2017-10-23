/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.fem;

import cgtjr.academics.general.ArrayTool;
import cgtjr.academics.general.ImageTool;

/**
 *
 * @author clayton g thomas jr
 */
public class MtrxImage
{

   public void createImage(double myMtrx[][])
   {
      int aHeight = myMtrx.length;
      int aWidth = myMtrx[0].length;
      
      int myPxlData[] = ArrayTool.rtrv1DIntArry(myMtrx, .00001);
      ImageTool.wrtImageFile(myPxlData,aWidth, aHeight,"sprsemtrx.jpg");
      
   }
}
