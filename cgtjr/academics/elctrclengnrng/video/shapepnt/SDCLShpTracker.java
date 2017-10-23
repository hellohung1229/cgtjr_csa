/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.math.geometry.shapebndry.CrvtrXY;
import cgtjr.academics.math.geometry.linepnts.LineCrtr;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.ImageBndry;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GlblIndxDrwActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ParseAction;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.*;
import cgtjr.academics.general.gui.UgotImage;
import cgtjr.academics.general.gui.WTSTextField;
import cgtjr.academics.math.geometry.elmnt.NodePnts;
import cgtjr.academics.math.geometry.elmnt.QuadInfoVar;
import cgtjr.academics.math.geometry.elmnt.QuadInfoVarFctry;

import java.awt.Image;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JTextArea;

/**
 *
 * @author clayton g thomas jr
 */
public class SDCLShpTracker implements ParseAction {
    //private PntPxlInsrtActn pixelInsrtActn;

    //private GlblIndxDrwActn nmbrDrawActn;
    private GridDrawActn nmbrDrawActn;
    private ShpBndry shapeBndry;
    private HmnCmptrShpBndry crdntShp;
    private boolean isCntrTrckng;
    private PntGrdntFltr aPntGrdntFltr;
    private Image otptImage;
    private int width;
    private int height;
    private Image img;
    private TrackerShpPntLstnr trckrShpPntLstnr;
    private QuadInfoVar quadInfoVar;
    private QuadInfoVarFctry quadInfoVarFctry;
    private SurfaceData aSurfaceData;
    private Pnt obsrvtnPnt;

    public SDCLShpTracker(ShpBndry myImageBndry) {
        shapeBndry = myImageBndry;
        //pixelInsrtActn = new PntPxlInsrtActn();
        nmbrDrawActn = new GlblIndxDrwActn();
        isCntrTrckng = true;
        quadInfoVar = new QuadInfoVar();
        quadInfoVarFctry.setQuadInfoVar(quadInfoVar);
        aSurfaceData = new SurfaceData();
        obsrvtnPnt = new Pnt();
        //aPntGrdntFltr = new PntGrdntFltr();
    }

    public SDCLShpTracker(ShpBndry myImageBndry, GridDrawActn myPntPxlInsrtActn) {
        shapeBndry = myImageBndry;
        nmbrDrawActn = myPntPxlInsrtActn;
        //nmbrDrawActn = new GlblIndxDrwActn();        
        //pixelInsrtActn = myPntPxlInsrtActn;
        isCntrTrckng = true;
        aSurfaceData = new SurfaceData();
    }

    public void setIsCntrTrckng(boolean myIsCntrTrckng) {
        isCntrTrckng = myIsCntrTrckng;
    }

    public Image actn(Image myImage, int myWidth, int myHeight) {
        JTextArea txtArea = WTSTextField.getTxtFld();        
        //WTSTextField.clearTxtArea();
        width = myWidth;
        height = myHeight;
        img = myImage;
        int inputPixels[] = ImageTool.rtrv1DPxls(img);
        //int gryOtptPxlData[] = ImageTool.rtrv1DPxls(img);
        int otptPixels[] = ImageTool.rtrv1DPxls(img);
        //System.out.println("ShpTracker: test 100");
        //pixelInsrtActn.setImgPixels(otptPixels);
        //pixelInsrtActn.setImgDtWdth(width);
        //pixelInsrtActn.setImgDtHght(height);

        
        nmbrDrawActn.setImgPixels(otptPixels);
        nmbrDrawActn.setImgDtWdth(width);
        nmbrDrawActn.setImgDtHght(height);

        //nmbrDrawActn.setImgPxlData(inputPixels, width, height);
        shapeBndry.setImgPxlData(inputPixels, width, height);

        //System.out.println("ShpTracker: test 101");
        crdntShp = new HmnCmptrShpBndry(shapeBndry, nmbrDrawActn);

        int aCrrtQuadCnt = crdntShp.getQuadElmnts().size();
        int aDeltaX = (int) crdntShp.getDeltaX();
        int aDeltaY = (int) crdntShp.getDeltaY();

        int aPrvQuadCnt = quadInfoVar.getQuadCntVal();
        quadInfoVar.setQuadCntVal(aCrrtQuadCnt);
        quadInfoVar.setQuadTtlCntVal(aPrvQuadCnt + aCrrtQuadCnt);

        Vector aNodeVctr = crdntShp.getNodes();
        //double center[] = crdntShp.cmptCenter();
        int count = width / aDeltaX;
        int center = aDeltaX*count/2;
       
        obsrvtnPnt.setX1((float)center);
        obsrvtnPnt.setY1(270);

        aSurfaceData.setObsrvtnPnt(obsrvtnPnt);
        aSurfaceData.insrt(aNodeVctr);
        aSurfaceData.setSurfaceArea(aCrrtQuadCnt, aDeltaX, aDeltaY);

        Vector bndryNds = NodePnts.rtrvBndry2DPnts(aNodeVctr);
        //NodePnts.prntGlblNds(bndryNds, null);
        //System.out.println("EdgeShpTracker: bndryNds = " + bndryNds.size());
        CrvtrXY aCrvtrXY = new CrvtrXY();
        aCrvtrXY.setStrghtLnStrtPnt(obsrvtnPnt);
        aCrvtrXY.insrt(bndryNds);
        aCrvtrXY.cmptCrvtr();

        TreeMap clTreeMap = CrvtrXY.getCLTreeMap();
        SurfaceData.cmptArcRadius(clTreeMap);
        Pnt strghtLnPnt1 = aCrvtrXY.getStrghtLnStrtPnt();
        Pnt strghtLnPnt2 = aCrvtrXY.getStrghtLnEndPnt();        

        Set aKeySet = clTreeMap.descendingKeySet();
        Iterator anIterator = aKeySet.iterator();

        Integer anInteger = (Integer) anIterator.next();
        Pnt aStartPnt1 = (Pnt) clTreeMap.get(anInteger);
        
        //aCrvtrXY.setOffSet((aSurfaceData.getYMax()-aSurfaceData.getYMax())/(2*aDeltaY) );

        Pnt obsPnt = aSurfaceData.getObsrvtnPnt();
        Pnt maxLinePnt = aSurfaceData.getMaxLinePnt();

        Pnt leftLinePnt = aSurfaceData.cmptLeftLinePnt();
        Pnt rightLinePnt = aSurfaceData.cmptRightLinePnt();

        Pnt fovLPnt = aSurfaceData.getFovLeftPnt();
        Pnt fovRPnt = aSurfaceData.getFovRightPnt();


        //LineCrtr.drawLine(obsPnt,leftLinePnt,otptPixels, myWidth, myHeight,0x00f1f100);
        //LineCrtr.drawLine(obsPnt,rightLinePnt,otptPixels, myWidth, myHeight,0x00f1f100);        
        //LineCrtr.drawLine(leftLinePnt,rightLinePnt,otptPixels, myWidth, myHeight,0x00f1f100);            
        LineCrtr.drawLine(aStartPnt1, maxLinePnt, otptPixels, myWidth, myHeight,0x00ffff00);
        LineCrtr.drawLine(fovLPnt, fovRPnt, otptPixels, myWidth, myHeight, 0x000000ff);
        //LineCrtr.drawLine(aStartPnt1,strghtLnPnt2, otptPixels, myWidth, myHeight,0x0000ffff);
        LineCrtr.drawLine(clTreeMap, otptPixels, myWidth, myHeight,0x00ff0000);
        
        //System.out.println("ShpTracker ........................................  ");        

        //txtArea.append(".......................................................................  \n");
        trackShpPntActn(crdntShp);

        
        //crdntShp = new HmnCmptrShpBndry(shapeBndry,pixelInsrtActn);
        //Vector aVector = crdntShp.getNodes();
        //aPntGrdntFltr.initialize(width, height);
        //System.out.println("ShpTracker: test 104 myPixelData2[1]"+myPixelData2[1]);
        //aPntGrdntFltr.setGryVls(gryOtptPxlData);
        //aPntGrdntFltr.setGrdntMgntd(otptPixels);
        //System.out.println("ShpTracker: test 106");
        //aPntGrdntFltr.filter9x9(inputPixels, aVector);

        //TODO: consider a parseAction interface for shpbndry ...
        //System.out.println("PointParser: count = "+crdntShp.getCounter()+", x max = "+crdntShp.getXMax()+",x min="+crdntShp.getXMin()+",y max = "+crdntShp.getYMax()+", y min = "+crdntShp.getYMin());
        //TODO: need to create method for this functionality ... after ensuring shape and point are properly instantiated
        //if(crdntShp.getCounter() > 0)
        //{
        //System.out.println("ShpTracker: x = "+center[0]+", y = "+center[1]);

        if (isCntrTrckng == true && (crdntShp.cmptWidth() > 4 && crdntShp.cmptHeight() > 4)) {
            //System.out.println("ShpTracker: x = "+center[0]+", y = "+center[1]+", width = "+shapeBndry.cmptWidth()+", height = "+shapeBndry.cmptHeight());
            //shapeBndry.setInitX(center[0]);
            //shapeBndry.setInitY(center[1]);
        }
        //System.out.println("ClrCnvrtFlter:grayValue = myPixelData["+i+"]"+myPixelData[i]);
        //}
        otptImage = UgotImage.createRGBImage(otptPixels, width, height, true);
        return otptImage;
    }

    private void trackShpPntActn(ShapePnt myShapePnt) {
        if (trckrShpPntLstnr != null) {
            //trckrShpPntLstnr.shapePntTrackActn(myShapePnt);
        }
    }

    public void setTrackerShpPntLstnr(TrackerShpPntLstnr myTrackerShpPntLstnr) {
        trckrShpPntLstnr = myTrackerShpPntLstnr;
    }

    public Image getOtPtImaage() {
        return otptImage;
    }

    public void setShapeBndry(ImageBndry myShpBndry) {
        shapeBndry = myShpBndry;
    }

    public ShapePnt getShapePnt() {
        return crdntShp;
    }
}