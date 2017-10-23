/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.color;

import cgtjr.academics.elctrclengnrng.imgprcssng.MedianFltr;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;

/**
 *
 * @author clayton g thomas jr
 */
public class ColorDiffBndry extends ImageBndry {

    private int frstFrmPxls[];
    private int frameCount;
    private float clrDistance;
    private MedianFltr mdnFltr1;
    private MedianFltr mdnFltr2;
    private int startFrame = 6;

    public ColorDiffBndry() {
        super(new DmnsnVar());
        mdnFltr1 = new MedianFltr();
        mdnFltr2 = new MedianFltr();
        clrDistance = 30;
    }

    public ColorDiffBndry(DmnsnVar myDmnsnVar) {
        super(myDmnsnVar);
        mdnFltr1 = new MedianFltr();
        mdnFltr2 = new MedianFltr();
        clrDistance = 68;
    }
    
    public void setClrDistance(float myClrDistance)
    {
       clrDistance = myClrDistance;
    }
    public float getClrDistance()
    {
        return clrDistance;
    }
    public void setFrstFrmPxls(int myFrstFrmPxls[]) {
        frstFrmPxls = myFrstFrmPxls;
    }

    public int[] getFrstFrmPxls() {
        return frstFrmPxls;
    }

    public void setImgPxlData(int[] myImgPixels, int myImgWidth, int myImgHeight) {
        if (frameCount == startFrame) {
            frstFrmPxls = myImgPixels;

        }
        super.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
        frameCount++;
        mdnFltr1.initialize(myImgWidth, myImgHeight);
        mdnFltr2.initialize(myImgWidth, myImgHeight);
    }

    public boolean isInBndry(double r, double t, double p) {

        boolean isInBndry = false;
        //System.out.println("ColorDiffBndry: framecount = "+frameCount+", z = "+p+",");
        //System.out.println("ColorDiffBndry: clrDistance = "+cmptClrDstnce(r, t, p) +", threshold = "+clrDistance);
        if (frameCount > startFrame && p == 0 && super.isInBndry(r, t, p) && cmptClrDstnce(r, t, p) >= clrDistance) {
            isInBndry = true;
        }
        return isInBndry;
    }

    public double cmptClrDstnce(double r, double t, double p) {
        int x = (int) r;
        int y = (int) t;
        int z = (int) p;

        int aWidth = getImgWidth();
        int anIndex = ImageTool.rtrvIndex(x, y, aWidth);


        mdnFltr1.filter3x3(frstFrmPxls, anIndex);
        int smthFrstFrmPxls[] = mdnFltr1.getFltrdData();

        int framePixels[] = getImgPixels();
        mdnFltr2.filter3x3(framePixels, anIndex);
        int smthFrmPxls[] = mdnFltr2.getFltrdData();

        int aColor1 = smthFrstFrmPxls[anIndex];
        int aColor2 = smthFrmPxls[anIndex];

        int r1 = ColorCode.rtrvRed(aColor1);
        int r2 = ColorCode.rtrvRed(aColor2);
        int g1 = ColorCode.rtrvGreen(aColor1);
        int g2 = ColorCode.rtrvGreen(aColor2);
        int b1 = ColorCode.rtrvBlue(aColor1);
        int b2 = ColorCode.rtrvBlue(aColor2);

        double distance = Math.sqrt((r2 - r1) * (r2 - r1) + (g2 - g1) * (g2 - g1) + (b2 - b1) * (b2 - b1));
        /*
        System.out.println("ColorDiffBndry frame count = "+frameCount+", x = "+x+", y = "+y +", z = "+z);
        System.out.println("ColorDiffBndry r1 = "+r1+", g1 = "+g1 +", b1 = "+b1);        
        System.out.println("ColorDiffBndry r2 = "+r2+", g2 = "+g2 +", b2 = "+b2);
        System.out.println("ColorDiffBndry difference = "+distance+" , clr distance = "+clrDistance);
        * */
        
        return distance;
    }
    /*
     * public void cmptPhase(int r1, int g1, int b1,double ) { double value1 =
     * r*r+t*t }
     */
}
