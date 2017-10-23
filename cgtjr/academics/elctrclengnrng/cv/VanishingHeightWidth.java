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
public class VanishingHeightWidth {
    
    private static ImageDrawData imagePixelData;
    
    private static float GPL1x1;
    private static float GPL1y1;
    private static float GPL1c1;
    private static float GPL1x2;
    private static float GPL1y2;
    private static float GPL1c2;
    
    private static float GPL2x1;
    private static float GPL2y1;
    private static float GPL2c1;
    private static float GPL2x2;
    private static float GPL2y2;
    private static float GPL2c2;
    
    private static float GPL3x1;
    private static float GPL3y1;
    private static float GPL3c1;
    private static float GPL3x2;
    private static float GPL3y2;
    private static float GPL3c2;
    
    private static float GPL4x1;
    private static float GPL4y1;
    private static float GPL4c1;
    private static float GPL4x2;
    private static float GPL4y2;
    private static float GPL4c2;
    
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

    private static float RLx1;
    private static float RLy1;
    private static float RLc1;
    private static float RLx2;
    private static float RLy2;
    private static float RLc2;
    private static float RHeight;
            
    private float heightA = 7.5f;
    private float heightB;    
    private float width;
    
    private float top1x;
    private float top1y;
    private float top1c;    
    
    private float base1x;
    private float base1y;
    private float base1c;    

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
        float aB2x = (myRectBndryHOGArrayList.getXMin()+myRectBndryHOGArrayList.getXMax())/2; 
        float aB2z = 1;
        float aT2x = aB2x;
        float aT2y = myRectBndryHOGArrayList.getYMax();         
        float aT2z = 1;
        
        float x1 = myRectBndryHOGArrayList.getXMin();
        float x2 = myRectBndryHOGArrayList.getXMax(); 
        widthHeightRatio = (x2-x1)/(aT2y-aB2y);
        computeVP();
        return computeHeight(aB2x,aB2y,aB2z,aT2x,aT2y,aT2z);        
    }
    private float computeWidth(float myHeight){
       objectWidth = widthHeightRatio*myHeight;
       return objectWidth;
    }
    public double computeWidthHeight() {
        float top2x = 156;
        float top2y = 113;
        float top2c = 1;
        
        float base2x = 156;
        float base2y = 139;        
        float base2c = 1;
        
        widthHeightRatio = 1;
        computeVP();
        return  computeHeight(base2x,base2y,base2c,top2x,top2y,top2c);
    }
    public void computeVP() {

        top1x = VanishingHeightWidth.getRLx2();
        top1y = VanishingHeightWidth.getRLy2();
        top1c = VanishingHeightWidth.getRLc2();
        
        base1x = VanishingHeightWidth.getRLx1();
        base1y = VanishingHeightWidth.getRLy1();        
        base1c = VanishingHeightWidth.getRLc1();
        
        
        vanishPoint1 = computeVVP();
        vanishPoint1.scale((float)(1.0/vanishPoint1.getZ2()));
        vLineGround = computeGPVL();

    }
    private double computeHeight(float myB2x,float myB2y,float myB2z,float myT2x,float myT2y,float myT2z) {
        base2x = myB2x;
        base2y = myB2y;
        base2c = myB2z;        
        top2x = myT2x;
        top2y = myT2y;
        top2c = myT2z;                
    
                heightA = VanishingHeightWidth.getRHeight();

        basePoint1 = new MathVector(base1x,base1y,base1c);
        topPoint1 = new MathVector(top1x,top1y,top1c);        
        
        basePoint2 = new MathVector(base2x,base2y,base2c);           
        topPoint2 = new MathVector(top2x,top2y,top2c);           

        System.out.println("VanishingHeightWidth basePoint 1 = "+basePoint1);        
        System.out.println("VanishingHeightWidth basePoint 2 = "+basePoint2);
        System.out.println("VanishingHeightWidth basePoint topPoint1 = "+topPoint1);        
        System.out.println("VanishingHeightWidth basePoint topPoint1 = "+topPoint1);   
        
        System.out.println("VanishingHeightWidth vanishPoint1 = "+vanishPoint1);
        
        vanishLine_ = MathVector.crssPrdct(basePoint1, basePoint2);
        vanishPointU = MathVector.crssPrdct(vanishLine_, vLineGround);  

        vanishLine2 = MathVector.crssPrdct(vanishPoint1, basePoint2);
        
        transferPoint_ = MathVector.crssPrdct(topPoint1, vanishPointU);

        System.out.println("VanishingHeightWidth vanishLine_ = "+vanishLine_);        
        System.out.println("VanishingHeightWidth vanishLine2 = "+vanishLine2);
        transferPoint = MathVector.crssPrdct(transferPoint_, vanishLine2);
        System.out.println("VanishingHeightWidth transferPoint_ = "+transferPoint_);
        System.out.println("VanishingHeightWidth transferPoint = "+transferPoint);
        
        MathVector vLineGroundScaled = new MathVector(vLineGround);
        vLineGroundScaled.scale((float)(1.0/vLineGround.getZ2()));        
        //System.out.println("SceneLengthHOG : vLineGroundScaled , x = "+vLineGroundScaled.getX2()+", y = "+vLineGroundScaled.getY2()+", z = "+vLineGroundScaled.getZ2());        
        
        MathVector vanishLineScaled_ = new MathVector(vanishLine_);
        vanishLineScaled_.scale((float)(1.0/vanishLine_.getZ2()));        
        System.out.println("SceneLengthHOG : vanishLineScaled_ , x = "+vanishLineScaled_.getX2()+", y = "+vanishLineScaled_.getY2()+", z = "+vanishLineScaled_.getZ2());

        MathVector vanishPointScaleU = new MathVector(vanishPointU);
        vanishPointScaleU.scale((float)(1.0/vanishPointU.getZ2()));
        System.out.println("SceneLengthHOG : vanishPointScaleU , x = "+vanishPointScaleU.getX2()+", y = "+vanishPointScaleU.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishLine2Scaled_ = new MathVector(vanishLine_);
        vanishLine2Scaled_.scale((float)(1.0/vanishLine_.getZ2()));
        System.out.println("SceneLengthHOG : vanishLine2Scaled_ , x = "+vanishLine2Scaled_.getX2()+", y = "+vanishLine2Scaled_.getY2()+", z = "+vanishPointScaleU.getZ2());
                
        MathVector vanishPointScaleU2 = MathVector.crssPrdct(vanishLine2Scaled_, vLineGround); 
        vanishPointScaleU.scale((float)(1.0/vanishPointScaleU2.getZ2()));
        System.out.println("SceneLengthHOG : vanishPointScaleU2 , x = "+vanishPointScaleU2.getX2()+", y = "+vanishPointScaleU2.getY2()+", z = "+vanishPointScaleU2.getZ2());
        
        MathVector transferPointScaled_ = MathVector.crssPrdct(topPoint1, vanishPointScaleU); 
        transferPointScaled_.scale((float)(1.0/transferPointScaled_.getZ2()));
        System.out.println("SceneLengthHOG : transferPointScaled_ , x = "+transferPointScaled_.getX2()+", y = "+transferPointScaled_.getY2()+", z = "+transferPointScaled_.getZ2());
        
        MathVector transferPointScaled = MathVector.crssPrdct(transferPointScaled_,vanishLine2); 
        transferPointScaled.scale((float)(1.0/transferPointScaled.getZ2()));
        System.out.println("SceneLengthHOG : transferPointScaled , x = "+transferPointScaled.getX2()+", y = "+transferPointScaled.getY2()+", y = "+transferPointScaled.getZ2());
                
        float b2 = 0;
        float t1 = MathVector.getDistance(basePoint2,transferPoint);
        float t1Scaled = MathVector.getDistance(basePoint2,transferPointScaled);        
        float t2 = MathVector.getDistance(basePoint2,topPoint2);
        float v = MathVector.getDistance(basePoint2,vanishPoint1);        

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
        
        
        float H2x2[][] = {{1,0},{1,-v}};
        
        float distanceT10 = H2x2[0][0]*t1Scaled+H2x2[0][1]*1;
        float distanceT11 = H2x2[1][0]*t1Scaled+H2x2[1][1]*1;
        float distanceT1 = distanceT10/distanceT11;
        
        float distanceT20 = H2x2[0][0]*t2+H2x2[0][1]*1;
        float distanceT21 = H2x2[1][0]*t2+H2x2[1][1]*1;                
        float distanceT2 = distanceT20/distanceT21;        
        float ratioD1ToD2 = distanceT2/distanceT1; 
        
        
        
        heightB = heightA*(distanceT2/distanceT1);        
        System.out.println("VanishingHeight: ratioD1ToD2 = "+ratioD1ToD2);
        System.out.println("VanishingHeight: height b = "+heightB);
        
        float n = t1Scaled*(v-t2);
        float d = t2*(v-t1Scaled);
        float ratio = d/n;                         
        heightB = heightA*(d/n);

        System.out.println("VanishingHeight: ratio = "+ratio);        
        System.out.println("VanishingHeight: reference height = "+heightA);     
        System.out.println("VanishingHeight: object height = "+heightB);
        float aWidth = computeWidth(heightB);
        System.out.println("VanishingHeight : object width = "+aWidth);
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

    public static float getGPL1x1() {
        return GPL1x1;
    }

    public static void setGPL1x1(float myGPL1x1) {
        GPL1x1 = myGPL1x1;
    }

    public static float getGPL1y1() {
        return GPL1y1;
    }

    public static void setGPL1y1(float myGPL1y1) {
        GPL1y1 = myGPL1y1;
    }

    public static float getGPL1c1() {
        return GPL1c1;
    }

    public static void setGPL1c1(float myGPL1c1) {
        GPL1c1 = myGPL1c1;
    }

    public static float getGPL1x2() {
        return GPL1x2;
    }

    public static void setGPL1x2(float myGPL1x2) {
        GPL1x2 = myGPL1x2;
    }

    public static float getGPL1y2() {
        return GPL1y2;
    }

    public static void setGPL1y2(float myGPL1y2) {
        GPL1y2 = myGPL1y2;
    }

    public static float getGPL1c2() {
        return GPL1c2;
    }

    public static void setGPL1c2(float myGPL1c2) {
        GPL1c2 = myGPL1c2;
    }

    public static float getGPL2x1() {
        return GPL2x1;
    }

    public static void setGPL2x1(float myGPL2x1) {
        GPL2x1 = myGPL2x1;
    }

    public static float getGPL2y1() {
        return GPL2y1;
    }

    public static void setGPL2y1(float myGPL2y1) {
        GPL2y1 = myGPL2y1;
    }

    public static float getGPL2c1() {
        return GPL2c1;
    }

    public static void setGPL2c1(float myGPL2c1) {
        GPL2c1 = myGPL2c1;
    }

    public static float getGPL2x2() {
        return GPL2x2;
    }

    public static void setGPL2x2(float myGPL2x2) {
        GPL2x2 = myGPL2x2;
    }

    public static float getGPL2y2() {
        return GPL2y2;
    }

    public static void setGPL2y2(float myGPL2y2) {
        GPL2y2 = myGPL2y2;
    }

    public static float getGPL2c2() {
        return GPL2c2;
    }

    public static void setGPL2c2(float myGPL2c2) {
        GPL2c2 = myGPL2c2;
    }

    public static float getGPL3x1() {
        return GPL3x1;
    }

    public static void setGPL3x1(float myGPL3x1) {
        GPL3x1 = myGPL3x1;
    }

    public static float getGPL3y1() {
        return GPL3y1;
    }

    public static void setGPL3y1(float myGPL3y1) {
        GPL3y1 = myGPL3y1;
    }

    public static float getGPL3c1() {
        return GPL3c1;
    }

    public static void setGPL3c1(float myGPL3c1) {
        GPL3c1 = myGPL3c1;
    }

    public static float getGPL3x2() {
        return GPL3x2;
    }

    public static void setGPL3x2(float myGPL3x2) {
        GPL3x2 = myGPL3x2;
    }

    public static float getGPL3y2() {
        return GPL3y2;
    }

    public static void setGPL3y2(float myGPL3y2) {
        GPL3y2 = myGPL3y2;
    }

    public static float getGPL3c2() {
        return GPL3c2;
    }

    public static void setGPL3c2(float myGPL3c2) {
        GPL3c2 = myGPL3c2;
    }

    public static float getGPL4x1() {
        return GPL4x1;
    }

    public static void setGPL4x1(float myGPL4x1) {
        GPL4x1 = myGPL4x1;
    }

    public static float getGPL4y1() {
        return GPL4y1;
    }

    public static void setGPL4y1(float myGPL4y1) {
        GPL4y1 = myGPL4y1;
    }

    public static float getGPL4c1() {
        return GPL4c1;
    }

    public static void setGPL4c1(float myGPL4c1) {
        GPL4c1 = myGPL4c1;
    }

    public static float getGPL4x2() {
        return GPL4x2;
    }

    public static void setGPL4x2(float myGPL4x2) {
        GPL4x2 = myGPL4x2;
    }

    public static float getGPL4y2() {
        return GPL4y2;
    }

    public static void setGPL4y2(float myGPL4y2) {
        GPL4y2 = myGPL4y2;
    }

    public static float getGPL4c2() {
        return GPL4c2;
    }

    public static void setGPL4c2(float myGPL4c2) {
        GPL4c2 = myGPL4c2;
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

    public static float getRLx1() {
        return RLx1;
    }

    public static void setRLx1(float myRLx1) {
        VanishingHeightWidth.RLx1 = myRLx1;
    }

    public static float getRLy1() {
        return RLy1;
    }

    public static void setRLy1(float myRLy1) {
        VanishingHeightWidth.RLy1 = myRLy1;
    }

    public static float getRLc1() {
        return RLc1;
    }

    public static void setRLc1(float myRLc1) {
        VanishingHeightWidth.RLc1 = myRLc1;
    }

    public static float getRLx2() {
        return RLx2;
    }

    public static void setRLx2(float myRLx2) {
        VanishingHeightWidth.RLx2 = myRLx2;
    }

    public static float getRLy2() {
        return RLy2;
    }

    public static void setRLy2(float myRLy2) {
        VanishingHeightWidth.RLy2 = myRLy2;
    }

    public static float getRLc2() {
        return RLc2;
    }

    public static void setRLc2(float myRLc2) {
        VanishingHeightWidth.RLc2 = myRLc2;
    }

    public static float getRHeight() {
        return RHeight;
    }

    public static void setRHeight(float myRHeight) {
        VanishingHeightWidth.RHeight = myRHeight;
    }
    public MathVector getVanishPoint1(){
        return vanishPoint1;
    }
    public MathVector getVanishPoint2(){
        return vanishPoint2;
    }
    public MathVector getVanishPoint3(){
        return vanishPoint3;
    }    
    public MathVector getVLineGround(){
        return vLineGround;
    }
    
    public static MathVector computeMVLine(float myX1,float myY1,float myZ1,float myX2,float myY2,float myZ2){
        
        MathVector mv1 = new MathVector(myX1,myY1,myZ1);        
        MathVector mv2 = new MathVector(myX2,myY2,myZ2);
        MathVector mv3 = MathVector.crssPrdct(mv1,mv2);
        return mv3;
    }
    public MathVector computeVVP(){
        MathVector vl1p1 = new MathVector(VL1x1,VL1y1,VL1c1);        
        MathVector vl1p2 = new MathVector(VL1x2,VL1y2,VL1c2);
        MathVector vl1 = MathVector.crssPrdct(vl1p1,vl1p2);
        MathVector vl2p1 = new MathVector(VL2x1,VL2y1,VL2c1);
        MathVector vl2p2 = new MathVector(VL2x2,VL2y2,VL2c2);
        MathVector vl2 = MathVector.crssPrdct(vl2p1,vl2p2);
        MathVector vvp = MathVector.crssPrdct(vl1,vl2);
        System.out.println("VanishingHeightWidth: vvp calculations ......");
        System.out.println("VanishingHeightWidth : vl1"+vl1);
        System.out.println("VanishingHeightWidth : vl2"+vl2);
        System.out.println("VanishingHeightWidth : vvp"+vvp);
        return vvp;
    }
    public MathVector computeGPVL(){        
        System.out.println("VanishingHeightWidth: gpvl calculations ......");
        MathVector gpl1p1 = new MathVector(GPL1x1,GPL1y1,GPL1c1);        
        MathVector gpl1p2 = new MathVector(GPL1x2,GPL1y2,GPL1c2);
        MathVector gpl1 = MathVector.crssPrdct(gpl1p1,gpl1p2);
        System.out.println("VanishingHeightWidth gpl1 = "+gpl1);
        
        MathVector gpl2p1 = new MathVector(GPL2x1,GPL2y1,GPL2c1);
        MathVector gpl2p2 = new MathVector(GPL2x2,GPL2y2,GPL2c2);
        MathVector gpl2 = MathVector.crssPrdct(gpl2p1,gpl2p2);
        System.out.println("VanishingHeightWidth gpl2 = "+gpl2);
        
        MathVector gpvp1 = MathVector.crssPrdct(gpl1,gpl2);
        vanishPoint2 = gpvp1;
        System.out.println("VanishingHeightWidth gpvp1 = "+gpvp1);
        
        MathVector gpl3p1 = new MathVector(GPL3x1,GPL3y1,GPL3c1);        
        MathVector gpl3p2 = new MathVector(GPL3x2,GPL3y2,GPL3c2);
        MathVector gpl3 = MathVector.crssPrdct(gpl3p1,gpl3p2);
        System.out.println("VanishingHeightWidth gpl3 = "+gpl3);
        
        MathVector gpl4p1 = new MathVector(GPL4x1,GPL4y1,GPL4c1);
        MathVector gpl4p2 = new MathVector(GPL4x2,GPL4y2,GPL4c2);
        MathVector gpl4 = MathVector.crssPrdct(gpl4p1,gpl4p2);
        System.out.println("VanishingHeightWidth gpl4 = "+gpl4);
        
        MathVector gpvp2 = MathVector.crssPrdct(gpl3,gpl4);
        vanishPoint3 = gpvp2;
        System.out.println("VanishingHeightWidth gpvp2 = "+gpvp2);
        
        MathVector gpvl = MathVector.crssPrdct(gpvp1,gpvp2);
        System.out.println("VanishingHeightWidth gpvl = "+gpvl);
        return gpvl;
    }    
}