/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
 *
 * @author Nit
 */
public class ImgFrmScrllPnl extends JScrollPane
{
   public ImgFrmScrllPnl(ArrayList myArrayList)
   {
      //setLayout(ScrollPaneLayout());
      ImgPlyrCnvs aImgFrmCnvs = new ImgPlyrCnvs(myArrayList);
      //add(aImgFrmCnvs,BorderLayout.CENTER);
   }
}
