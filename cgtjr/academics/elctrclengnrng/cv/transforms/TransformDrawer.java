/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.cv.transforms;

import cgtjr.academics.elctrclengnrng.cv.CameraMatrixDLT;
import cgtjr.academics.math.geometry.linepnts.CubeCreator;

/**
 *
 * @author cgthomasjr
 */
public class TransformDrawer {
    
    public static void drawCube(PTransform myPTransform,float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float x5, float y5, float x6, float y6, float x7, float y7, float x8, float y8, int myData[], int myWidth, int myHeight, int myColor1, int myColor2){
    }
    public static void drawCube(PTransform myPTransform,float myP1[], float myP2[], float myP3[], float myP4[], float myP5[], float myP6[], float myP7[], float myP8[], int myData[], int myWidth, int myHeight, int myColor1, int myColor2){
        double PMatrix1[][] = myPTransform.getPMatrix();
        float p1[] = myPTransform.computeTransformation(PMatrix1,myP1);
        float p2[] = myPTransform.computeTransformation(PMatrix1,myP2);        
        float p3[] = myPTransform.computeTransformation(PMatrix1,myP3);                
        float p4[] = myPTransform.computeTransformation(PMatrix1,myP4);                
        float p5[] = myPTransform.computeTransformation(PMatrix1,myP5);                
        float p6[] = myPTransform.computeTransformation(PMatrix1,myP6);                
        float p7[] = myPTransform.computeTransformation(PMatrix1,myP7);                
        float p8[] = myPTransform.computeTransformation(PMatrix1,myP8);                
        CubeCreator.drawCube(p1[0],p1[1],p2[0],p2[1],p3[0],p3[1], p4[0],p4[1], p5[0], p5[1], p6[0],p6[1],p7[0],p7[1],p8[0],p8[1],myData,myWidth,myHeight,myColor1,myColor2);      
    }
    public static void drawCube(CubeCreator myCubeCreater,int myData[], int myWidth, int myHeight, int myColor1, int myColor2){

        float p1[] = {(float)myCubeCreater.getX1(),(float)myCubeCreater.getY1()};
        float p2[] = {(float)myCubeCreater.getX2(),(float)myCubeCreater.getY2()};        
        float p3[] = {(float)myCubeCreater.getX3(),(float)myCubeCreater.getY3()};        
        float p4[] = {(float)myCubeCreater.getX4(),(float)myCubeCreater.getY4()};        
        float p5[] = {(float)myCubeCreater.getX5(),(float)myCubeCreater.getY5()};        
        float p6[] = {(float)myCubeCreater.getX6(),(float)myCubeCreater.getY6()};
        float p7[] = {(float)myCubeCreater.getX7(),(float)myCubeCreater.getY7()};        
        float p8[] = {(float)myCubeCreater.getX8(),(float)myCubeCreater.getY8()};        
                        
        myCubeCreater.drawCube(p1[0],p1[1],p2[0],p2[1],p3[0],p3[1], p4[0],p4[1], p5[0], p5[1], p6[0],p6[1],p7[0],p7[1],p8[0],p8[1],myData,myWidth,myHeight,myColor1,myColor2);      
    }
}
