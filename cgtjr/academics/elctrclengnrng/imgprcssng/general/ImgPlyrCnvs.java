/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Nit
 */
public class ImgPlyrCnvs extends Canvas {

    private int xPadding;
    private int xLocation;
    private RndrrPrcss imgRndrr;
    private ArrayList imgArryLst;
    private Image offScrnImg;
    private Graphics offScrnGrphcs;
    private Image anImage = null;
    private int initialX;
    
    public ImgPlyrCnvs(ArrayList myArrayList) {
        imgArryLst = myArrayList;

        if (imgArryLst != null && imgArryLst.size() > 0) {
            xPadding = 0;
            anImage = (Image) imgArryLst.get(0);
            int width = anImage.getWidth(null);
            int height = anImage.getHeight(null);
            offScrnImg = (new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
        }
    }
    public void setInitialX(int myInitialX)
    {
        initialX = myInitialX;
    }
    public void setOffScrnImg(Image myOffScrnImg) {
        offScrnImg = myOffScrnImg;
    }

    public void setRndrr(RndrrPrcss myRndrr) {
        imgRndrr = myRndrr;
    }

    public void paint(Graphics g) {
        g.drawImage(offScrnImg, 0, 0, this);
    }

    public void process() {
        //super.paint(g);
        int aSize = imgArryLst.size();
        offScrnGrphcs = offScrnImg.getGraphics();

        xLocation = initialX;

        //frmParser.strtPrsng();
        for (int i = 0; i < aSize; i++) {
            System.out.println("IMgPlyrCnvs: processing image # = "+i+"size = "+aSize);
            anImage = (Image) imgArryLst.get(i);
            imgRndrr.process(anImage);

            int imgWidth = anImage.getWidth(null);
            Image anImage2 = imgRndrr.getOutputImage();

            offScrnGrphcs.drawImage(anImage2, xLocation, 0, this);
            //System.out.println("ImgPlyr: xLocation = "+xLocation);
            
            xLocation = xLocation + imgWidth + xPadding;
        }
    }

    public void start() {
        repaint();
    }
}