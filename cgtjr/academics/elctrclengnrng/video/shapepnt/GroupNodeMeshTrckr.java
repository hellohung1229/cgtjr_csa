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
public class GroupNodeMeshTrckr extends ShpTracker{

    ShapePnt meshShapePnt;
    TrffcSensor trffcSensors;
        
    public GroupNodeMeshTrckr(ShpBndry myImageBndry, GridDrawActn myPntPxlInsrtActn) {
        super(myImageBndry, myPntPxlInsrtActn);
        trffcSensors = new TrffcSensor();
    }

    public GroupNodeMeshTrckr(ShpBndry myImageBndry) {
        super(myImageBndry);
        trffcSensors = new TrffcSensor();        
    }
    
    public Image actn(Image myImage, int myWidth, int myHeight) {                
        super.actn(myImage, myWidth, myHeight);
        meshShapePnt = getShapePnt();

            //System.out.println("CnsstntMsh: test2");            
           int inputPixels[] = ImageTool.rtrv1DPxls(myImage);
           int outputPixels[] = ImageTool.rtrv1DPxls(myImage);           
           trffcSensors.setImgPxlData(inputPixels,myWidth,myHeight);                         
           //trffcSensors.updateSensorNodes(crdnteShp);
           reDrawMesh(outputPixels);
        
        return getOutPutImaage();
    }
    
    
}
