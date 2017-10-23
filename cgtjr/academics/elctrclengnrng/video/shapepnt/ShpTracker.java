/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GlblIndxDrwActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.general.gui.UgotImage;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import java.awt.Image;

/**
 *
 * @author clayton g thomas jr
 */
public class ShpTracker implements ParseAction {

    private GridDrawActn nmbrDrawActn;
    private ShpBndry shapeBndry;
    private ShapePnt crdntShp;
    private boolean isCntrTrckng;
    private Image outputImage;
    private int width;
    private int height;
    //private Image img;
    private TrackerShpPntLstnr trckrShpPntLstnr;
    //private QuadInfoVar quadInfoVar;
    //private QuadInfoVarFctry quadInfoVarFctry;
    private int inputPixels[];

    public ShpTracker(ShpBndry myImageBndry) {
        shapeBndry = myImageBndry;
        nmbrDrawActn = new GlblIndxDrwActn();
        isCntrTrckng = true;
        //quadInfoVar = new QuadInfoVar();
        //quadInfoVarFctry.setQuadInfoVar(quadInfoVar);

    }

    public ShpTracker(ShpBndry myImageBndry, GridDrawActn myPntPxlInsrtActn) {
        shapeBndry = myImageBndry;
        nmbrDrawActn = myPntPxlInsrtActn;
        isCntrTrckng = true;
    }

    public void setIsCntrTrckng(boolean myIsCntrTrckng) {
        isCntrTrckng = myIsCntrTrckng;
    }

    public void updatePixelData() {
    }

    public void updatePixelData(Image myImage) {
        int otptPixels[] = ImageTool.rtrv1DPxls(myImage);
        nmbrDrawActn.setImgPixels(otptPixels);
        nmbrDrawActn.setImgDtWdth(width);
        nmbrDrawActn.setImgDtHght(height);
    }
    public void setDrawActnPxlData(int outputPixels[], int myWidth, int myHeight)
    {
        nmbrDrawActn.setImgPixels(outputPixels);
        nmbrDrawActn.setImgDtWdth(myWidth);
        nmbrDrawActn.setImgDtHght(myHeight);
    }
    public void setShapeBndryPxlData(int inputPixels[], int myWidth, int myHeight)
    {
        //shapeBndry.setImgPxlData(inputPixels, myWidth, myHeight);          
        shapeBndry.setImgPxlData(inputPixels, myWidth, myHeight);                  
    }    
    public void setWidth(int myWidth)
    {
        width = myWidth;
    }
    public void setHeight(int myHeight)
    {
        height = myHeight;
    }
    public Image actn(Image myImage, int myWidth, int myHeight) {
        setWidth(myWidth);
        setHeight(myHeight);        
        //TextArea txtArea = WTSTextField.getTxtFld();
        //WTSTextField.clearTxtArea();
        //width = myWidth;
        //height = myHeight;
        //img = myImage;
        int inputPixels[] = ImageTool.rtrv1DPxls(myImage);
        //int gryOtptPxlData[] = ImageTool.rtrv1DPxls(img);

        //int outputPixels[] = ImageTool.rtrv1DPxls(myImage);

        //nmbrDrawActn.setImgPixels(outputPixels);
        //nmbrDrawActn.setImgDtWdth(width);
        //nmbrDrawActn.setImgDtHght(height);

        //nmbrDrawActn.setImgPxlData(inputPixels, width, height);
        //shapeBndry.setImgPxlData(inputPixels, width, height);
        int outputPixels[] = ImageTool.rtrv1DPxls(myImage);
        setDrawActnPxlData(outputPixels,myWidth,myHeight);
        setShapeBndryPxlData(inputPixels,myWidth,myHeight);
        //System.out.println("ShpTracker: test 101");
        //TODO: modify to use a method call to retrieve the coordinate shape
        //crdntShp = new HmnCmptrShpBndry(shapeBndry, nmbrDrawActn);
        crdntShp = createShapePnt(shapeBndry, nmbrDrawActn);


        trackShpPntActn(crdntShp);


        if (isCntrTrckng == true && (crdntShp.cmptWidth() > 4 && crdntShp.cmptHeight() > 4)) {
            //System.out.println("ShpTracker: x = "+center[0]+", y = "+center[1]+", width = "+shapeBndry.cmptWidth()+", height = "+shapeBndry.cmptHeight());
            //shapeBndry.setInitX(center[0]);
            //shapeBndry.setInitY(center[1]);
        }
        outputImage = UgotImage.createRGBImage(outputPixels, myWidth, myHeight, true);
        return outputImage;
    }

    protected ShapePnt createShapePnt(ShpBndry shapeBndry, GridDrawActn nmbrDrawActn) {
        HmnCmptrShpBndry crdntShp = new HmnCmptrShpBndry(shapeBndry, nmbrDrawActn);
        return crdntShp;
    }

    protected void reDrawMesh(Image myImage) {
        int otptPixels[] = ImageTool.rtrv1DPxls(myImage);
        reDrawMesh(otptPixels);
    }

    protected void reDrawMesh(int[] otptPixels) {
        nmbrDrawActn.setImgPixels(otptPixels);
        nmbrDrawActn.setImgDtWdth(width);
        nmbrDrawActn.setImgDtHght(height);
        nmbrDrawActn.updateMesh(crdntShp);
        outputImage = UgotImage.createRGBImage(otptPixels, width, height, true);
    }

    private void trackShpPntActn(ShapePnt myShapePnt) {
        if (trckrShpPntLstnr != null) {
            trckrShpPntLstnr.shapePntTrackActn(myShapePnt);
        }
    }

    public void setTrackerShpPntLstnr(TrackerShpPntLstnr myTrackerShpPntLstnr) {
        trckrShpPntLstnr = myTrackerShpPntLstnr;
    }

    public Image getOutPutImaage() {
        return outputImage;
    }

    public void setShapeBndry(ImageBndry myShpBndry) {
        shapeBndry = myShpBndry;
    }

    public int[] getInputPixels() {
        return inputPixels;
    }

    public ShapePnt getShapePnt() {
        return crdntShp;
    }
}