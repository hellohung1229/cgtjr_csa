/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex;

import cgtjr.academics.elctrclengnrng.imgprcssng.GrdntFilter;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.FileNameVar;

/**
 *
 * @author clayton g thomas jr
 */
public class GrdntBndry extends ImageBndry {

    private GrdntFilter grdntFltr;
    private int threshHold;

    public GrdntBndry(FileNameVar myDmnsnVar) {
        super(myDmnsnVar);
        grdntFltr = new GrdntFilter();
        threshHold = 12;
    }
    public void initialize(int myWidth, int myHeight) {
        grdntFltr.initialize(myWidth, myHeight);
    }

    public void setThreshHold(int myThreshHold) {
        threshHold = myThreshHold;
    }

    public void setImgPxlData(int myPixelData[], int myWidth, int myHeight) {
        super.setImgPxlData(myPixelData, myWidth, myHeight);
        grdntFltr.initialize(myWidth, myHeight);
    }

    public boolean isInBndry(double r, double t, double p) {
        boolean inBndry = false;
        int aX = (int) r;
        int aY = (int) t;
        int aWidth = getImgWidth();
        int anIndex = ImageTool.rtrvIndex(aX, aY, aWidth);
        int imgPxlData[] = getImgPixels();
        grdntFltr.grdntFilter9x9(imgPxlData, anIndex);

        if (p == 0 && super.isInBndry(r, t, p) && (grdntFltr.getGrdntMgntdAvg3x3(anIndex) < threshHold)) {
            inBndry = true;
        }
        //System.out.println("GrdntBndry: x = "+aX+", aY = "+aY+", isInBndry = "+inBndry+", gradient = "+grdntFltr.getGrdntMgntdAvg9x9(anIndex));
        return inBndry;
    }
    public ImageFilter getFilter() {
        return grdntFltr;
    }
}
