/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video.shapepnt;

import cgtjr.academics.general.ImageTool;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.elctrclengnrng.transengnrng.TrffcSensor;
import java.awt.Image;

/**
 *
 * @author clayton g thomas jr
 */
public class CnsstntMshTrckr extends ShpTracker {

    TrffcSensor trffcSensors;

    public CnsstntMshTrckr(ShpBndry myImageBndry, GridDrawActn myPntPxlInsrtActn) {
        super(myImageBndry, myPntPxlInsrtActn);
        trffcSensors = new TrffcSensor();
    }

    public CnsstntMshTrckr(ShpBndry myImageBndry) {
        super(myImageBndry);
        trffcSensors = new TrffcSensor();

    }

    public Image actn(Image myImage, int myWidth, int myHeight) {
        ShapePnt crdnteShp = (ShapePnt)getShapePnt();
        if (crdnteShp == null) {
            //System.out.println("CnsstntMsh: test1");
            super.actn(myImage, myWidth, myHeight);
        } else {
            //System.out.println("CnsstntMsh: test2");            
            int inputPixels[] = ImageTool.rtrv1DPxls(myImage);
            int outputPixels[] = ImageTool.rtrv1DPxls(myImage);
            trffcSensors.setImgPxlData(inputPixels, myWidth, myHeight);
            trffcSensors.updateSensorNodes(crdnteShp);
            trffcSensors.setImagePixels(outputPixels);
            trffcSensors.printData();
            //trffcSensors.updateClusterNodes(crdnteShp);
            //System.out.println("CnsstntMshTrcker: test 1");
            reDrawMesh(outputPixels);
        }

        return getOutPutImaage();
    }  
}
