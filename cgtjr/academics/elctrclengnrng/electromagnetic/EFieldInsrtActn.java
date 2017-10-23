/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.electromagnetic;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.crdntepnts.PntInsrtActn;

/**b
 *
 * @author clayton g thomas jr
 */
public class EFieldInsrtActn implements PntInsrtActn{

    private EField emWave;
    
    public EFieldInsrtActn()
    {
        emWave = new EField();
    }    
    public void nodeCmpltAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public void nodeCreateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
        float aZValue = myPoint7.getX1();
        float aMag = emWave.cmptMgntdeByZ(aZValue);
        float mgntde = emWave.getMgntde();
        
        myPoint7.setValue(aMag/mgntde);
        myPoint7.setColor((int)(aMag/mgntde));   
        System.out.println("EFieldInsrtActn: E Mag = "+aMag/mgntde);
    }
    public void nodeInitAction(Pnt aPoint, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public void nodeOriginAction(Pnt aPoint, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    public void nodeUpdateAction(Pnt aPoint, Pnt myPoint4, Pnt myPoint6, Pnt myPoint7, int myDepthCounter, int myDepth, int myTotalNumber) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}