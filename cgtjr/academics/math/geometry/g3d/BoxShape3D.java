/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.g3d;

import cgtjr.academics.math.geometry.crdntepnts.ShapePnt;
import cgtjr.academics.math.geometry.dmnsvar.CrdntType;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;

/**
 *
 * @author clayton g thomas jr
 */
public class BoxShape3D extends Box {

    Appearance app;
    Material mat;
    private float height;
    private float width;
    private float length;

    public Box retrieveBox(ShapePnt myShapePnt) {
        String aCrdntType = myShapePnt.getCrdntTp();
        computeWidthHeightLength(myShapePnt);
        app = new Appearance();
        mat = new Material();
        app.setMaterial(mat);
        Box aBox = new Box(width, height, length, app);
        return aBox;
    }

    public Box retrieveBox(float myHeight, float myWidth, float myLength) {
        app = new Appearance();
        mat = new Material();
        app.setMaterial(mat);
        Box aBox = new Box(height, width, length, app);
        return aBox;
    }
    public void computeWidthHeightLength(ShapePnt myShapePnt) {
        String aCrdntType = myShapePnt.getCrdntTp();
        float minX = (float) myShapePnt.getXMin();
        float maxX = (float) myShapePnt.getXMax();
        float minY = (float) myShapePnt.getYMin();
        float maxY = (float) myShapePnt.getYMax();
        float minZ = (float) myShapePnt.getZMin();
        float maxZ = (float) myShapePnt.getZMax();

        float aX1 = CrdntType.rtrvC1(aCrdntType, minX, 0, 0, 0, 0);
        float aX2 = CrdntType.rtrvC1(aCrdntType, maxX, 0, 0, 0, 0);
        float aY1 = CrdntType.rtrvC2(aCrdntType, 0, minY, 0, 0, 0);
        float aY2 = CrdntType.rtrvC2(aCrdntType, 0, maxY, 0, 0, 0);
        float aZ1 = CrdntType.rtrvC3(aCrdntType, 0, 0, minZ, 0, 0);
        float aZ2 = CrdntType.rtrvC3(aCrdntType, 0, 0, maxZ, 0, 0);

        System.out.println("x1 = "+aX1+", x2 = "+aX2+", y1 = "+aY1 + ", y2 = "+ aY2+", z1 = "+ aZ1+", z2 = "+ aZ2);
        width = Math.abs(aX2 - aX1)/2;
        height = Math.abs(aY2 - aY1)/2;
        length = Math.abs(aZ2 - aZ1)/2;
    }
}
