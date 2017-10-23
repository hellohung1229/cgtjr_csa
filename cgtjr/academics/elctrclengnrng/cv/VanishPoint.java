/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv;

import cgtjr.academics.elctrclengnrng.imgprcssng.complabel.RectBndryHOGTree;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.math.lnralgbra.MathVector;

/**
 *
 * @author clayton g thomas jr
 */
public class VanishPoint {

    private static ImageDrawData imagePixelData;
    private static float VL1x1;
    private static float VL1y1;
    private static float VL1c1;
    private static float VL1x2;
    private static float VL1y2;
    private static float VL1c2;
    private static float VL2x1;
    private static float VL2y1;
    private static float VL2c1;
    private static float VL2x2;
    private static float VL2y2;
    private static float VL2c2;
    private MathVector vanishPoint1;

    public void initialize(int myImageWidth, int myImageHeight) {
    }
    public void computeVanishingPoint(RectBndryHOGTree myRectBndryHOGArrayList) {
        vanishPoint1 = computeVP();
        vanishPoint1.scale((float) (1.0 / vanishPoint1.getZ2()));
    }
    public static float getVL1x1() {
        return VL1x1;
    }
    public static void setVL1x1(float myVL1x1) {
        VL1x1 = myVL1x1;
    }
    public static float getVL1y1() {
        return VL1y1;
    }
    public static void setVL1y1(float myVL1y1) {
        VL1y1 = myVL1y1;
    }

    public static float getVL1c1() {
        return VL1c1;
    }

    public static void setVL1c1(float myVL1c1) {
        VL1c1 = myVL1c1;
    }

    public static float getVL1x2() {
        return VL1x2;
    }

    public static void setVL1x2(float myVL1x2) {
        VL1x2 = myVL1x2;
    }

    public static float getVL1y2() {
        return VL1y2;
    }

    public static void setVL1y2(float myVL1y2) {
        VL1y2 = myVL1y2;
    }

    public static float getVL1c2() {
        return VL1c2;
    }

    public static void setVL1c2(float myVL1c2) {
        VL1c2 = myVL1c2;
    }

    public static float getVL2x1() {
        return VL2x1;
    }

    public static void setVL2x1(float myVL2x1) {
        VL2x1 = myVL2x1;
    }

    public static float getVL2y1() {
        return VL2y1;
    }

    public static void setVL2y1(float myVL2y1) {
        VL2y1 = myVL2y1;
    }

    public static float getVL2c1() {
        return VL2c1;
    }

    public static void setVL2c1(float myVL2c1) {
        VL2c1 = myVL2c1;
    }

    public static float getVL2x2() {
        return VL2x2;
    }

    public static void setVL2x2(float myVL2x2) {
        VL2x2 = myVL2x2;
    }

    public static float getVL2y2() {
        return VL2y2;
    }

    public static void setVL2y2(float myVL2y2) {
        VL2y2 = myVL2y2;
    }

    public static float getVL2c2() {
        return VL2c2;
    }

    public static void setVL2c2(float myVL2c2) {
        VL2c2 = myVL2c2;
    }

    public MathVector getVanishPoint1() {
        return vanishPoint1;
    }

    public static MathVector computeMVLine(float myX1, float myY1, float myZ1, float myX2, float myY2, float myZ2) {

        MathVector mv1 = new MathVector(myX1, myY1, myZ1);
        MathVector mv2 = new MathVector(myX2, myY2, myZ2);
        MathVector mv3 = MathVector.crssPrdct(mv1, mv2);
        return mv3;
    }

    public MathVector computeVP() {
        MathVector vl1p1 = new MathVector(VL1x1, VL1y1, VL1c1);
        MathVector vl1p2 = new MathVector(VL1x2, VL1y2, VL1c2);
        MathVector vl1 = MathVector.crssPrdct(vl1p1, vl1p2);
        MathVector vl2p1 = new MathVector(VL2x1, VL2y1, VL2c1);
        MathVector vl2p2 = new MathVector(VL2x2, VL2y2, VL2c2);
        MathVector vl2 = MathVector.crssPrdct(vl2p1, vl2p2);
        MathVector vvp = MathVector.crssPrdct(vl1, vl2);
        System.out.println("VanishingHeightWidth: vvp calculations ......");
        System.out.println("VanishingHeightWidth : vl1" + vl1);
        System.out.println("VanishingHeightWidth : vl2" + vl2);
        System.out.println("VanishingHeightWidth : vvp" + vvp);
        return vvp;
    }
}