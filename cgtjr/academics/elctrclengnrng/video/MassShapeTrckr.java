/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.elctrclengnrng.imgprcssng.pntvertex.GridDrawActn;
import cgtjr.academics.elctrclengnrng.video.shapepnt.TrckrShpBndry;
import cgtjr.academics.math.geometry.crdntepnts.Pnt;
import cgtjr.academics.math.geometry.shapebndry.ShpBndry;
import cgtjr.academics.physics.MassPnt;

/**
 *
 * @author clayton g thomas jr
 */
public class MassShapeTrckr extends TrckrShpBndry {

    public MassShapeTrckr(ShpBndry myShpBndry, GridDrawActn aPntPxlInsrtActn) {
        super(myShpBndry, aPntPxlInsrtActn);
    }

    public Pnt createDataPoint() {
        MassPnt aMassPnt = new MassPnt();
        aMassPnt.setColor(0xffffff);
        aMassPnt.setDeltaX(getDeltaX());
        aMassPnt.setDeltaY(getDeltaY());
        aMassPnt.setDeltaZ(getDeltaZ());
        return (Pnt) aMassPnt;
    }

    public Pnt createPoint() {
        //System.out.println("MassPnt: createMassPnt()");
        MassPnt aMassPnt = new MassPnt();
        aMassPnt.setColor(0xffffff);
        aMassPnt.setDeltaX(getDeltaX());
        aMassPnt.setDeltaY(getDeltaY());
        aMassPnt.setDeltaZ(getDeltaZ());

        return (Pnt) aMassPnt;
    }
}
