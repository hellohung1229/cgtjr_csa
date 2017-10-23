/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.FileNameVar;

/**
 *
 * @author clayton g thomas jr
 */
public class GrdntCrrltnBndry extends GrdntBndry {

    private CornerDetect imageFltr;

    public GrdntCrrltnBndry(FileNameVar myDmnsnVar) {
        super(myDmnsnVar);
    }
    public GrdntCrrltnBndry(FileNameVar myDmnsnVar, CornerDetect myImageFltr) {
        super(myDmnsnVar);
        imageFltr = myImageFltr;
    }
    public void setImgPxlData(int myPixelData[], int myWidth, int myHeight) {
        super.setImgPxlData(myPixelData, myWidth, myHeight);
        imageFltr.initialize(myWidth, myHeight);
    }    
    public boolean isInBndry(double r, double t, double p) {
        if (imageFltr != null) {
            int aX1 = (int) r;
            int aY1 = (int) t;
            int aWidth1 = getImgWidth();
            int anIndex1 = ImageTool.rtrvIndex(aX1, aY1, aWidth1);
            int imgPxlData1[] = getImgPixels();
            imageFltr.cornerDetect(imgPxlData1, anIndex1);
        }
        boolean inBndry = super.isInBndry(r, t, p);
        return inBndry;
    }
    public ImageFilter getFilter() {
        return imageFltr;
    }
}