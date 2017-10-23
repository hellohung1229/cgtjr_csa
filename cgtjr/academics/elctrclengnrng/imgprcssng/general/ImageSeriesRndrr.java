/*
 * @(#)VideoRndrr.java
 *
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.general;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Renderer for RGB images using AWT Image.
 */
public class ImageSeriesRndrr implements RndrrPrcss {

    private ArrayList rndrrArrayList;
    private RndrrPrcss imageRndrr;
    private Image anImage;

    public ImageSeriesRndrr() {
        rndrrArrayList = new ArrayList();
    }

    public void addRndrr(ImageRndrr myImageRndrr) {
        rndrrArrayList.add(myImageRndrr);
    }

    public void process(Image myImage) {

        //destImage = component.createImage(sourceImage); 
        int aSize = rndrrArrayList.size();

        for (int i = 0; i < aSize; i++) {
            imageRndrr = (RndrrPrcss) rndrrArrayList.get(i);
            imageRndrr.process(myImage);
            myImage = imageRndrr.getOutputImage();
            System.out.println("ImageSeriesRndrr: width = "+myImage.getWidth(null));
        }
        anImage = myImage;        
    }
    public Image getOutputImage() {
                    System.out.println("ImageSeriesRndrr: width = "+anImage.getWidth(null));
        return anImage;
    }
}
