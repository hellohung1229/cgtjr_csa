/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.imgprcssng.color;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.videotmp.ColorCode;
import cgtjr.academics.math.geometry.dmnsvar.DmnsnVar;

/**
 *
 * @author clayton g thomas jr
 */
public class Color3DiffBndry extends ImageBndry {

    private int frstFrmPxls[];
    private int frameCount;
    private float clrDistance;
    
    public Color3DiffBndry() {
        super(new DmnsnVar()); 
        clrDistance = 40;
    }
    public Color3DiffBndry(DmnsnVar myDmnsnVar) {
        super(myDmnsnVar);
        clrDistance = 40;
    }
    public void setFrstFrmPxls(int myFrstFrmPxls[]) {
        frstFrmPxls = myFrstFrmPxls;
    }
    public int[] getFrstFrmPxls() {
        return frstFrmPxls;
    }
    public void setImgPxlData(int[] myImgPixels, int myImgWidth, int myImgHeight) {
        if (frameCount == 3) {
            frstFrmPxls = myImgPixels;
        }        
        super.setImgPxlData(myImgPixels, myImgWidth, myImgHeight);
        frameCount++;                
    }   
    public boolean isInBndry(double r, double t, double p) {
        
        boolean isInBndry = false;
        
        if(frameCount > 3 && p == 0 && super.isInBndry(r, t, p) && isInDstnce(r,t,p) )
        {
           isInBndry = true;
        }
        return isInBndry;
    }
    public boolean isInDstnce(double r, double t, double p)
    {
        boolean inBndry = false;
        int x = (int)r;
        int y = (int)t;
        int z = (int)p;
        
        int aWidth = getImgWidth();
        
        int anIndex = ImageTool.rtrvIndex(x,y,aWidth);
        
        int framePixels[] = getImgPixels();
        int aColor1 = framePixels[anIndex];
        int aColor2 = frstFrmPxls[anIndex];        
        
        int r1 = ColorCode.rtrvRed(aColor1);
        int r2 = ColorCode.rtrvRed(aColor2);   
        int g1 = ColorCode.rtrvGreen(aColor1);
        int g2 = ColorCode.rtrvGreen(aColor2);   
        int b1 = ColorCode.rtrvBlue(aColor1);
        int b2 = ColorCode.rtrvBlue(aColor2);   
        
        double distance1 = Math.sqrt((r2-r1)*(r2-r1));
        double distance2 = Math.sqrt((g2-g1)*(g2-g1));
        double distance3 = Math.sqrt((b2-b1)*(b2-b1));
        
        //System.out.println("ColorDiffBndry frame count = "+frameCount+", x = "+x+", y = "+y +", z = "+z);
        //System.out.println("ColorDiffBndry r1 = "+r1+", g1 = "+g1 +", b1 = "+b1);        
        //System.out.println("ColorDiffBndry r2 = "+r2+", g2 = "+g2 +", b2 = "+b2);
        //System.out.println("ColorDiffBndry difference = "+distance+" , clr distance = "+clrDistance);
        if(distance1 <= clrDistance && distance2 <= clrDistance && distance3 <= clrDistance)
        {
            inBndry = false;
        }else{
            inBndry = true;
        }
        return inBndry;                
    }
    /*
    public void cmptPhase(int r1, int g1, int b1,double )
    {
        double value1 = r*r+t*t
    }*/
    
}
