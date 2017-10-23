/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.math.geometry.linepnts;

import cgtjr.academics.math.geometry.crdntepnts.Pnt;

/**
 *
 * @author cgthomasjr
 */
public class CubeCreator {

    private double height;
    private double length;
    private double width;

    private double x1;
    private double y1;
    private double z1;
    private double x2;
    private double y2;
    private double z2;
    private double x3;
    private double y3;
    private double z3;
    private double x4;
    private double y4;
    private double z4;
    private double x5;
    private double y5;
    private double z5;
    private double x6;
    private double y6;
    private double z6;
    private double x7;
    private double y7;
    private double z7;
    private double x8;
    private double y8;
    private double z8;

    public CubeCreator(double myWidth,double myLength,double myHeight){
        width = myWidth;
        height = myHeight;
        length = myLength;
        setX1(0);
        setY1(0);        
        setZ1(0);        
        setX2(width);
        setY2(0);        
        setZ2(0);
        setX3(width);
        setY3(length);        
        setZ3(0);        
        setX4(0);
        setY4(length);        
        setZ4(0);                
        setX5(0);
        setY5(0);        
        setZ5(height);
        setX6(width);
        setY6(0);        
        setZ6(height);
        setX7(width);
        setY7(length);        
        setZ7(height);
        setX8(0);
        setY8(length);        
        setZ8(height);          
    }
    public CubeCreator(double myXMin,double myXMax,double myYMin,double myYMax,double myZMin,double myZMax){
        width  = myXMax - myXMin;
        height = myYMax - myYMin;
        length = myZMax - myZMin;
        setX1(myXMin);
        setY1(myYMin);        
        setZ1(myZMin);        
        setX2(myXMax);
        setY2(myYMin);        
        setZ2(myZMin);
        setX3(myXMax);
        setY3(myYMax);        
        setZ3(myZMin);        
        setX4(myXMin);
        setY4(myYMax);        
        setZ4(myZMin);                
        setX5(myXMin);
        setY5(myYMin);        
        setZ5(myZMax);
        setX6(myXMax);
        setY6(myYMin);        
        setZ6(myZMax);
        setX7(myXMax);
        setY7(myYMin);        
        setZ7(myZMax);
        setX8(myXMax);
        setY8(myYMax);        
        setZ8(myZMax);          
    }    
    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getX4() {
        return x4;
    }

    public void setX4(double x4) {
        this.x4 = x4;
    }

    public double getY4() {
        return y4;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    public double getX5() {
        return x5;
    }

    public void setX5(double x5) {
        this.x5 = x5;
    }

    public double getY5() {
        return y5;
    }

    public void setY5(double y5) {
        this.y5 = y5;
    }

    public double getX6() {
        return x6;
    }

    public void setX6(double x6) {
        this.x6 = x6;
    }

    public double getY6() {
        return y6;
    }

    public void setY6(double y6) {
        this.y6 = y6;
    }

    public double getX7() {
        return x7;
    }

    public void setX7(double x7) {
        this.x7 = x7;
    }

    public double getY7() {
        return y7;
    }

    public void setY7(double y7) {
        this.y7 = y7;
    }

    public double getX8() {
        return x8;
    }

    public void setX8(double x8) {
        this.x8 = x8;
    }

    public double getY8() {
        return y8;
    }

    public void setY8(double y8) {
        this.y8 = y8;
    }

    public double getZ1() {
        return z1;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public double getZ2() {
        return z2;
    }

    public void setZ2(double z2) {
        this.z2 = z2;
    }

    public double getZ3() {
        return z3;
    }

    public void setZ3(double z3) {
        this.z3 = z3;
    }

    public double getZ4() {
        return z4;
    }

    public void setZ4(double z4) {
        this.z4 = z4;
    }

    public double getZ5() {
        return z5;
    }

    public void setZ5(double z5) {
        this.z5 = z5;
    }

    public double getZ6() {
        return z6;
    }

    public void setZ6(double z6) {
        this.z6 = z6;
    }

    public double getZ7() {
        return z7;
    }

    public void setZ7(double z7) {
        this.z7 = z7;
    }

    public double getZ8() {
        return z8;
    }

    public void setZ8(double z8) {
        this.z8 = z8;
    }
        
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public static void drawCube(Pnt aPoint1, Pnt aPoint2, int myData[], int myWidth, int myHeight, int myColor) {
        LineCrtr.drawLine(aPoint1, aPoint2, myData, myWidth, myHeight, myColor);
    }

    public static void drawCube(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float x5, float y5, float x6, float y6, float x7, float y7, float x8, float y8, int myData[], int myWidth, int myHeight, int myColor1, int myColor2) {
        LineCrtr.drawLine(x1, y1, x2, y2, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x2, y2, x3, y3, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x3, y3, x4, y4, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x4, y4, x1, y1, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x2, y2, x3, y3, myData, myWidth, myHeight, myColor1, myColor2);

        LineCrtr.drawLine(x5, y5, x6, y6, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x6, y6, x7, y7, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x7, y7, x8, y8, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x8, y8, x5, y5, myData, myWidth, myHeight, myColor1, myColor2);

        LineCrtr.drawLine(x1, y1, x5, y5, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x6, y6, x2, y2, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x4, y4, x8, y8, myData, myWidth, myHeight, myColor1, myColor2);
        LineCrtr.drawLine(x7, y7, x3, y3, myData, myWidth, myHeight, myColor1, myColor2);
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
                        
        drawCube(p1[0],p1[1],p2[0],p2[1],p3[0],p3[1], p4[0],p4[1], p5[0], p5[1], p6[0],p6[1],p7[0],p7[1],p8[0],p8[1],myData,myWidth,myHeight,myColor1,myColor2);      
    }    
    public void drawCube(int myData[], int myWidth, int myHeight, int myColor1, int myColor2){

        float p1[] = {(float)getX1(),(float)getY1()};
        float p2[] = {(float)getX2(),(float)getY2()};        
        float p3[] = {(float)getX3(),(float)getY3()};        
        float p4[] = {(float)getX4(),(float)getY4()};        
        float p5[] = {(float)getX5(),(float)getY5()};        
        float p6[] = {(float)getX6(),(float)getY6()};
        float p7[] = {(float)getX7(),(float)getY7()};        
        float p8[] = {(float)getX8(),(float)getY8()};        
                        
        drawCube(p1[0],p1[1],p2[0],p2[1],p3[0],p3[1], p4[0],p4[1], p5[0], p5[1], p6[0],p6[1],p7[0],p7[1],p8[0],p8[1],myData,myWidth,myHeight,myColor1,myColor2);      
    }        
}