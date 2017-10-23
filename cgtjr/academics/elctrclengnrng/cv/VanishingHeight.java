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
public class VanishingHeight {
    
    private ImageDrawData imagePixelData;
    
    private float heightA = 7.5f;
    private float heightB;    
    private float width;
    
    private float v1x = 49.58f;
    private float v1y = 2785.68f;
    private float v1c = 1;
    
    private float v2x = -107.8f;
    private float v2y = -68.66f;
    private float v2c = 1;
    
    private float v3x = 1428.23f;
    private float v3y = 163.99f;
    private float v3c = 1;    

    private float top1x = 138;
    private float top1y = 146;
    private float top1c = 1;    
    
    private float base1x = 135;
    private float base1y = 215;
    private float base1c = 1;    

    private float base2x;
    private float base2y;
    private float base2c;        
    private float top2x;
    private float top2y;
    private float top2c; 
        
    private MathVector vanishPoint1;
    private MathVector vanishPoint2;        
    private MathVector vanishPoint3;
    private MathVector vLineGround;
    private MathVector basePoint1;
    //private MathVector basePoint2;           
    private MathVector topPoint1;
    //private MathVector topPoint2;           
        
    private MathVector vanishLine_;
    private MathVector vanishPointU;  

    private MathVector vanishLine2;
        
    private MathVector transferPoint_;
    private MathVector transferPoint;
    
    private MathVector transferPointScaled;
    
    private MathVector topPoint2;           
    private MathVector basePoint2;         

    private float widthHeightRatio; 
    private float objectWidth;
                
    public void initialize(int myImageWidth, int myImageHeight) {

    }

    public double computeWidthHeight(RectBndryHOGTree myRectBndryHOGArrayList) {
        
        float aB2y = myRectBndryHOGArrayList.getYMin();
        float aT2y = myRectBndryHOGArrayList.getYMax();        
        float aB2x = myRectBndryHOGArrayList.getXMin()+(myRectBndryHOGArrayList.getXMax()-myRectBndryHOGArrayList.getXMin())/2;
        float aT2x = aB2x;
        float aB2z = 1;
        float aT2z = 1;
        
        float x1 = myRectBndryHOGArrayList.getXMin();
        float x2 = myRectBndryHOGArrayList.getXMax(); 
        widthHeightRatio = (x2-x1)/(aT2y-aB2y);
        
        
        return computeHeight(aB2x,aB2y,aB2z,aT2x,aT2y,aT2z);        
    }
    private float computeWidth(float myHeight){
       objectWidth = widthHeightRatio*myHeight;
       return objectWidth;
    }
    
    private double computeHeight(float myB2x,float myB2y,float myB2z,float myT2x,float myT2y,float myT2z) {
        base2x = myB2x;
        base2y = myB2y;
        base2c = myB2z;        
        top2x = myT2x;
        top2y = myT2y;
        top2c = myT2z;                

        topPoint2 = new MathVector(top2x,top2y,top2c);           
        basePoint2 = new MathVector(base2x,base2y,base2c);   
        
        vanishPoint1 = new MathVector(v1x,v1y,v1c);
        vanishPoint2 = new MathVector(v2x,v2y,v2c);        
        vanishPoint3 = new MathVector(v3x,v3y,v3c);
        vLineGround = MathVector.crssPrdct(vanishPoint2, vanishPoint3);
        
        basePoint1 = new MathVector(base1x,base1y,base1c);
        basePoint2 = new MathVector(base2x,base2y,base2c);           
        
        topPoint1 = new MathVector(top1x,top1y,top1c);
        topPoint2 = new MathVector(top2x,top2y,top2c);           
        
        vanishLine_ = MathVector.crssPrdct(basePoint1, basePoint2);
        vanishPointU = MathVector.crssPrdct(vanishLine_, vLineGround);  

        vanishLine2 = MathVector.crssPrdct(vanishPoint1, basePoint2);
        
        transferPoint_ = MathVector.crssPrdct(topPoint1, vanishPointU);
        transferPoint = MathVector.crssPrdct(transferPoint_, vanishLine2);

        MathVector vLineGroundScaled = new MathVector(vLineGround);
        vLineGroundScaled.scale((float)(1.0/vLineGround.getZ2()));        
        //System.out.println("SceneLengthHOG : vLineGroundScaled , x = "+vLineGroundScaled.getX2()+", y = "+vLineGroundScaled.getY2()+", z = "+vLineGroundScaled.getZ2());        
        
        MathVector vanishLineScaled_ = new MathVector(vanishLine_);
        vanishLineScaled_.scale((float)(1.0/vanishLine_.getZ2()));        
        //System.out.println("SceneLengthHOG : vanishLineScaled_ , x = "+vanishLineScaled_.getX2()+", y = "+vanishLineScaled_.getY2()+", z = "+vanishLineScaled_.getZ2());

        MathVector vanishPointScaleU = new MathVector(vanishPointU);
        vanishPointScaleU.scale((float)(1.0/vanishPointU.getZ2()));
        //System.out.println("SceneLengthHOG : vanishPointScaleU , x = "+vanishPointScaleU.getX2()+", y = "+vanishPointScaleU.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishLine2Scaled_ = new MathVector(vanishLine_);
        vanishLine2Scaled_.scale((float)(1.0/vanishLine_.getZ2()));
        //System.out.println("SceneLengthHOG : vanishLine2Scaled_ , x = "+vanishLine2Scaled_.getX2()+", y = "+vanishLine2Scaled_.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishPointScaleU2 = MathVector.crssPrdct(vanishLine2Scaled_, vLineGround); 
        vanishPointScaleU.scale((float)(1.0/vanishPointScaleU2.getZ2()));
        //System.out.println("SceneLengthHOG : vanishPointScaleU2 , x = "+vanishPointScaleU2.getX2()+", y = "+vanishPointScaleU2.getY2()+", z = "+vanishPointScaleU2.getZ2());
        
        MathVector transferPointScaled_ = MathVector.crssPrdct(topPoint1, vanishPointScaleU); 
        transferPointScaled_.scale((float)(1.0/transferPointScaled_.getZ2()));
        //System.out.println("SceneLengthHOG : transferPointScaled_ , x = "+transferPointScaled_.getX2()+", y = "+transferPointScaled_.getY2()+", z = "+transferPointScaled_.getZ2());
        
        MathVector transferPointScaled = MathVector.crssPrdct(transferPointScaled_,vanishLine2); 
        transferPointScaled.scale((float)(1.0/transferPointScaled.getZ2()));
        //System.out.println("SceneLengthHOG : transferPointScaled , x = "+transferPointScaled.getX2()+", y = "+transferPointScaled.getY2()+", y = "+transferPointScaled.getZ2());
        
        
        float b2 = 0;
        float t1 = MathVector.getDistance(basePoint2,transferPoint);
        float t1Scaled = MathVector.getDistance(basePoint2,transferPointScaled);        
        float t2 = MathVector.getDistance(basePoint2,topPoint2);
        float v = MathVector.getDistance(basePoint2,vanishPoint1);        


        /*
        System.out.println("SceneLengthHOG : base x2 = "+base2x);
        System.out.println("SceneLengthHOG : base y2 = "+base2y);        
        System.out.println("SceneLengthHOG : base c2 = "+base2c);        

        System.out.println("SceneLengthHOG : top x2 = "+top2x);
        System.out.println("SceneLengthHOG : top y2 = "+top2y);        
        System.out.println("SceneLengthHOG : top c2 = "+top2c);                
        
        System.out.println("SceneLengthHOG : t1 = "+t1);
        System.out.println("SceneLengthHOG : t1Scale = "+t1Scaled);
        System.out.println("SceneLengthHOG : b2 = "+b2);        
        System.out.println("SceneLengthHOG : t2 = "+t2);
        System.out.println("SceneLengthHOG : v = "+v);
        */
        float H2x2[][] = {{1,0},{1,-v}};
        
        float distanceT10 = H2x2[0][0]*t1Scaled+H2x2[0][1]*1;
        float distanceT11 = H2x2[1][0]*t1Scaled+H2x2[1][1]*1;
        float distanceT1 = distanceT10/distanceT11;
        
        float distanceT20 = H2x2[0][0]*t2+H2x2[0][1]*1;
        float distanceT21 = H2x2[1][0]*t2+H2x2[1][1]*1;                
        float distanceT2 = distanceT20/distanceT21;        
        float ratioD1ToD2 = distanceT2/distanceT1;        
        heightB = heightA*(distanceT2/distanceT1);        
        //System.out.println("VanishingHeight: ratioD1ToD2 = "+ratioD1ToD2);
        //System.out.println("VanishingHeight: height b = "+heightB);
        
        float n = t1Scaled*(v-t2);
        float d = t2*(v-t1Scaled);
        float ratio = d/n;                         
        heightB = heightA*(d/n);

        //System.out.println("VanishingHeight: ratio = "+ratio);        
        //System.out.println("VanishingHeight: reference height = "+heightA);     
        //System.out.println("VanishingHeight: object height = "+heightB);
        float aWidth = computeWidth(heightB);
        //System.out.println("VanishingHeight : object width = "+aWidth);
        return heightB;
    }
    public float getBase2x() {
        return base2x;
    }
    public void setBase2x(float myBase2x) {
        this.base2x = myBase2x;
    }
    public float getBase2y() {
        return base2y;
    }
    public void setBase2y(float myBase2y) {
        this.base2y = myBase2y;
    }

    public float getBase2c() {
        return base2c;
    }

    public void setBase2c(float myBase2c) {
        this.base2c = myBase2c;
    }

    public float getTop2x() {
        return top2x;
    }

    public void setTop2x(float myTop2x) {
        this.top2x = myTop2x;
    }

    public float getTop2y() {
        return top2y;
    }

    public void setTop2y(float myTop2y) {
        this.top2y = myTop2y;
    }

    public float getTop2c() {
        return top2c;
    }

    public void setTop2c(float myTop2c) {
        this.top2c = myTop2c;
    }  

    public float getTop1x() {
        return top1x;
    }

    public void setTop1x(float myTop1x) {
        this.top1x = myTop1x;
    }

    public float getTop1y() {
        return top1y;
    }

    public void setTop1y(float myTop1y) {
        this.top1y = myTop1y;
    }

    public float getTop1c() {
        return top1c;
    }

    public void setTop1c(float myTop1c) {
        this.top1c = myTop1c;
    }
    public float getBase1x() {
        return base1x;
    }
    public void setBase1x(float myBase1x) {
        this.base1x = myBase1x;
    }
    public float getBase1y() {
        return base1y;
    }
    public void setBase1y(float myBase1y) {
        this.base1y = myBase1y;
    }
    public float getBase1c() {
        return base1c;
    }
    public void setBase1c(float myBase1c) {
        this.base1c = myBase1c;
    }    

    public MathVector getBasePoint1() {
        return basePoint1;
    }

    public void setBasePoint1(MathVector myBasePoint1) {
        this.basePoint1 = myBasePoint1;
    }

    public MathVector getTopPoint1() {
        return topPoint1;
    }

    public void setTopPoint1(MathVector myTopPoint1) {
        this.topPoint1 = myTopPoint1;
    }

    public MathVector getTopPoint2() {
        return topPoint2;
    }

    public void setTopPoint2(MathVector myTopPoint2) {
        this.topPoint2 = myTopPoint2;
    }

    public MathVector getBasePoint2() {
        return basePoint2;
    }

    public void setBasePoint2(MathVector myBasePoint2) {
        this.basePoint2 = myBasePoint2;
    }

    public float getObjectWidth() {
        return objectWidth;
    }
    public float getObjectHeight() {
        return heightB;
    }
    public void setObjectWidth(float myObjectWidth) {
        this.objectWidth = myObjectWidth;
    }
    
    public static MathVector computeMVLine(float myX1,float myY1,float myZ1,float myX2,float myY2,float myZ2){
        
        MathVector mv1 = new MathVector(myX1,myY1,myZ1);        
        MathVector mv2 = new MathVector(myX2,myY2,myZ2);
        MathVector mv3 = MathVector.crssPrdct(mv1,mv2);
        return mv3;
    }
}