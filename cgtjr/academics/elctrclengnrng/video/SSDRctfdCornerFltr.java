/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.cv.CornerDetect;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.math.geometry.crdntepnts.PntTool;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;

public class SSDRctfdCornerFltr extends CornerDetect {

    private double crrntVelocity[][];
    private double prvsUntVlcty[][];
    private double prvsUntVlcty1[][];
    private double prvsUntVlcty2[][];
    private double unitVelocity[][];
    private int prevImgData[];
    private int crrntImgData[];
    private int imgWidth;
    private int imgHeight;       
    private int firstFrame[];
    private int prvsData1[];
    private int prvsData2[];
    private int grayValues[];
    private double velocity[];
    private double untVlcty[];
    private int imageArrowData[];
    private double untVlctyTlt[];
    private double untVlctyAvrg[];
    private int nmbrOfCrnrs;
    private int windowDmnsn;
    private double ssd;
    private int indexValue;
    private ImageDrawData imagePixelData;     
    private int frm1TmprlGrdntDiff;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;
        imgWidth = myImageWidth;        
        imgHeight = myImageHeight;
        crrntImgData = new int[aLength];
        imageArrowData = new int[aLength];
        untVlctyTlt = new double[2];
        untVlctyAvrg = new double[2];
        nmbrOfCrnrs = 0;

        imgWidth = myImageWidth;

        grayValues = getGryVls();
        
        ssd = Integer.MAX_VALUE;
        indexValue = Integer.MAX_VALUE;
           
        if (prevImgData == null) {
            prevImgData = new int[aLength];
        }
        if (firstFrame == null) {
            firstFrame = new int[aLength];
        }
        if (prvsData1 == null) {
            prvsData1 = new int[aLength];
        }
        if (prvsData2 == null) {
            prvsData2 = new int[aLength];
        }
        imagePixelData = new ImageDrawData(myImageWidth, myImageHeight);        
    }
    public void filter(int dataValues[], int i) {
        crrntImgData = dataValues;
        setPrevImgData(dataValues,i);
        imagePixelData.updatePixels(dataValues,i);
        //super.cornerDetect(dataValues, i);
        if (frameIndex > 1) {
            crrltnFltr(dataValues,i);            
        }        
    }     
    public void crrltnFltr(int dataValues[], int i) {
        //filter3x3(dataValues, i);  
        int x = ImageTool.rtrvXPstn(i, imgWidth);
        int y = ImageTool.rtrvYPstn(i, imgWidth);        
        
        super.cornerDetect(dataValues, i);  
        if(getEigenValue(i) >= getThreshold()){
        //if ( x % 30 == 0 && y % 30 == 0 ) {         
        //if ( x == 130 && y == 70 ) {   
           
        

           cmptSSD27x27Dstnc27x27(i,i);     
           cmptVelocity(i);
           ssd = Integer.MAX_VALUE;
           indexValue = Integer.MAX_VALUE;
        }
    }
    /*
    @Override
    public void filter(int[] grayValues, int i) {
        super.cornerDetect(grayValues, i); //To change body of generated methods, choose Tools | Templates.
        imagePixelData.updatePixels(grayValues,i);        
        setPrevImgData(i);
        if(getEigenValue(i) >= getThreshold())
        {
           cmptSSD27x27Dstnc27x27(i,i);     
           cmptVelocity(i);
           ssd = Integer.MAX_VALUE;
           indexValue = Integer.MAX_VALUE;
        }            
    }*/
    /*
    public void cmptVelocity(int myIndex)
    {       
       int x1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
       int y1 = ImageTool.rtrvYPstn(myIndex, imgWidth);   
       
       int x2 = ImageTool.rtrvXPstn(indexValue, imgWidth);   
       int y2 = ImageTool.rtrvYPstn(indexValue, imgWidth);     
       
       int vx = x2-x1;
       int vy = y2-y1;       
       
       //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());
       //System.out.println("CrrltnMatch: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
       //System.out.println("CrrltnMatch: Y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }*/
    public void cmptVelocity(int myIndex) {

        int x1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int x2 = ImageTool.rtrvXPstn(indexValue, imgWidth);
        int y2 = ImageTool.rtrvYPstn(indexValue, imgWidth);

        int vx = x2 - x1;
        int vy = y2 - y1;

        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());
        int myPixelData[] = imagePixelData.getImagePixels();
        if(PntTool.getDistance(x1, y1, x2, y2) < 2*27)
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, imgWidth, imgHeight,0x0050e60d,0x0050e60d);
        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }    
    public void cmptSSD9x9Dstnc9x9(int myIndex) {
        cmptSSD9x9Dstnc9x9(myIndex,myIndex);
    }    
    public void cmptSSD9x9Dstnc9x9(int myIndex,int myDistance) {
        int aWidthx3 = 3*getImageWidth();
        double total[] = new double[2];

        //cmptSSD9x9Dstnc3x3(myIndex,myDistance - aWidthx3 - 3);
        //cmptSSD9x9Dstnc3x3(myIndex,myDistance - aWidthx3);
        //cmptSSD9x9Dstnc3x3(myIndex,myDistance - aWidthx3 + 3);
        cmptSSD9x9Dstnc3x3(myIndex,myDistance - 3);
        cmptSSD9x9Dstnc3x3(myIndex,myDistance);
        cmptSSD9x9Dstnc3x3(myIndex,myDistance + 3);
        //cmptSSD9x9Dstnc3x3(myIndex,myDistance + aWidthx3 - 3);
        //cmptSSD9x9Dstnc3x3(myIndex,myDistance + aWidthx3);
        //cmptSSD9x9Dstnc3x3(myIndex,myDistance + aWidthx3 + 3);
    }    
    public void cmptSSD9x9Dstnc3x3(int myIndex,int myDistance) {
        int aWidthx3 = getImageWidth();
        double total[] = new double[2];

        
        //double ssd0 = cmptSSD9x9(myIndex,myDistance - aWidthx3 - 1);
        //double ssd1 = cmptSSD9x9(myIndex,myDistance - aWidthx3);
        //double ssd2 = cmptSSD9x9(myIndex,myDistance - aWidthx3 + 1);
        double ssd3 = cmptSSD9x9(myIndex,myDistance - 1);
        double ssd4 = cmptSSD9x9(myIndex,myDistance);
        double ssd5 = cmptSSD9x9(myIndex,myDistance + 1);
        //double ssd6 = cmptSSD9x9(myIndex,myDistance + aWidthx3 - 1);
        //double ssd7 = cmptSSD9x9(myIndex,myDistance + aWidthx3);
        //double ssd8 = cmptSSD9x9(myIndex,myDistance + aWidthx3 + 1);                                  
      
        /*
        if(ssd0 <= ssd)
        {
            ssd = ssd0;
            indexValue = myDistance - aWidthx3 - 1;
        }     
        if(ssd1 <= ssd)
        {
            ssd = ssd1;
            indexValue = myDistance - aWidthx3;
        }    
        if(ssd2 <= ssd)
        {
            ssd = ssd2;
            indexValue = myDistance - aWidthx3 + 1;
        }*/    
        if(ssd3 <= ssd)
        {
            ssd = ssd3;
            indexValue = myDistance - 1;
        }  
        if(ssd4 <= ssd)
        {
            ssd = ssd4;
            indexValue = myDistance;
        } 
        if(ssd5 <= ssd)
        {
            ssd = ssd5;
            indexValue = myDistance + 1;
        } 
        /*
        if(ssd6 <= ssd)
        {
            ssd = ssd6;
            indexValue = myDistance + aWidthx3 - 1;
        }    
        if(ssd7 <= ssd)
        {
            ssd = ssd7;
            indexValue = myDistance + aWidthx3;
        }  
        if(ssd8 <= ssd)
        {
            ssd = ssd8;
            indexValue = myDistance + aWidthx3 + 1;
        }*/
        //System.out.println("CrrltnMatchFltr: ssd0="+ssd0+",ssd1="+ssd1+",ssd2="+ssd2+",ssd3="+ssd3+",ssd4="+ssd4+",ssd5="+ssd5+",ssd6="+ssd6+",ssd7="+ssd7+",ssd8="+ssd8);
        //System.out.println("CrrltnMatchFltr: ssd3="+ssd3);        
    }   
    public double cmptSSD9x9(int myIndex1,int myIndex2) {
        int aWidthx3 = 3 * getImageWidth();
        double total[] = new double[2];
        int ssdTmp = cmptSSD(myIndex1 - aWidthx3 - 3,myIndex2 - aWidthx3 - 3);
        ssdTmp += cmptSSD(myIndex1 - aWidthx3,myIndex2 - aWidthx3);
        ssdTmp += cmptSSD(myIndex1 - aWidthx3 + 3,myIndex2 - aWidthx3 + 3);
        ssdTmp += cmptSSD(myIndex1 - 3,myIndex2 - 3);
        ssdTmp += cmptSSD(myIndex1,myIndex2);
        ssdTmp += cmptSSD(myIndex1 + 3,myIndex2 + 3);
        ssdTmp += cmptSSD(myIndex1 + aWidthx3 - 3,myIndex2 + aWidthx3 - 3);
        ssdTmp += cmptSSD(myIndex1 + aWidthx3,myIndex2 + aWidthx3);
        ssdTmp += cmptSSD(myIndex1 + aWidthx3 + 3,myIndex2 + aWidthx3 + 3);
        return ssdTmp;
    }   
    public void cmptSSD27x27Dstnc27x27(int myIndex, int myDistance) {
        int aWidthx9 = 9 * getImageWidth();

        //cmptSSD27x27Dstnc9x9(myIndex, myDistance - aWidthx9 - 9);
        //cmptSSD27x27Dstnc9x9(myIndex, myDistance - aWidthx9);
        //cmptSSD27x27Dstnc9x9(myIndex, myDistance - aWidthx9 + 9);
        cmptSSD27x27Dstnc9x9(myIndex, myDistance - 9);
        cmptSSD27x27Dstnc9x9(myIndex, myDistance);
        cmptSSD27x27Dstnc9x9(myIndex, myDistance + 9);
        //cmptSSD27x27Dstnc9x9(myIndex, myDistance + aWidthx9 - 9);
        //cmptSSD27x27Dstnc9x9(myIndex, myDistance + aWidthx9);
        //cmptSSD27x27Dstnc9x9(myIndex, myDistance + aWidthx9 + 9);
        
    }    
    public void cmptSSD27x27Dstnc9x9(int myIndex, int myDistance) {
        int aWidthx3 = 3 * getImageWidth();

        //cmptSSD27x27Dstnc3x3(myIndex, myDistance - aWidthx3 - 3);
        //cmptSSD27x27Dstnc3x3(myIndex, myDistance - aWidthx3);
        //cmptSSD27x27Dstnc3x3(myIndex, myDistance - aWidthx3 + 3);
        cmptSSD27x27Dstnc3x3(myIndex, myDistance - 3);
        cmptSSD27x27Dstnc3x3(myIndex, myDistance);
        cmptSSD27x27Dstnc3x3(myIndex, myDistance + 3);
        //cmptSSD27x27Dstnc3x3(myIndex, myDistance + aWidthx3 - 3);
        //cmptSSD27x27Dstnc3x3(myIndex, myDistance + aWidthx3);
        //cmptSSD27x27Dstnc3x3(myIndex, myDistance + aWidthx3 + 3);
    }
    public void cmptSSD27x27Dstnc3x3(int myIndex, int myDistance) {

        if (!isInBounds3x3(myIndex)) {
           return;
        }
        int aWidthx3 = getImageWidth();

        //double ssd0 = cmptSSD27x27(myIndex, myDistance - aWidthx3 - 1);
        //double ssd1 = cmptSSD27x27(myIndex, myDistance - aWidthx3);
        //double ssd2 = cmptSSD27x27(myIndex, myDistance - aWidthx3 + 1);
        double ssd3 = cmptSSD27x27(myIndex, myDistance - 1);
        double ssd4 = cmptSSD27x27(myIndex, myDistance);
        double ssd5 = cmptSSD27x27(myIndex, myDistance + 1);
        //double ssd6 = cmptSSD27x27(myIndex, myDistance + aWidthx3 - 1);
        //double ssd7 = cmptSSD27x27(myIndex, myDistance + aWidthx3);
        //double ssd8 = cmptSSD27x27(myIndex, myDistance + aWidthx3 + 1);

        /*
        if (ssd0 < ssd) {
            ssd = ssd0;
            indexValue = myDistance - aWidthx3 - 1;
        }
        if (ssd1 < ssd) {
            ssd = ssd1;
            indexValue = myDistance - aWidthx3;
        }
        if (ssd2 < ssd) {
            ssd = ssd2;
            indexValue = myDistance - aWidthx3 + 1;
        }*/
        if (ssd3 < ssd) {
            ssd = ssd3;
            indexValue = myDistance - 1;
        }
        if (myIndex == myDistance && ssd4 <= ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }else if (ssd4 < ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }
        if (ssd5 < ssd) {
            ssd = ssd5;
            indexValue = myDistance + 1;
        }
        /*
        if (ssd6 < ssd) {
            ssd = ssd6;
            indexValue = myDistance + aWidthx3 - 1;
        }
        if (ssd7 < ssd) {
            ssd = ssd7;
            indexValue = myDistance + aWidthx3;
        }
        if (ssd8 < ssd) {
            ssd = ssd8;
            indexValue = myDistance + aWidthx3 + 1;
        }*/
        //System.out.println("SSDCrnrFltr: ssd0=" + ssd0 + ",ssd1=" + ssd1 + ",ssd2=" + ssd2 + ",ssd3=" + ssd3 + ",ssd4=" + ssd4 + ",ssd5=" + ssd5 + ",ssd6=" + ssd6 + ",ssd7=" + ssd7 + ",ssd8=" + ssd8);
        //System.out.println("SSDCrnrFltr: myDistance = "+myDistance+", indexValue =" + indexValue + ", ssd = " + ssd);
    }        
    public double cmptSSD27x27(int myIndex1,int myIndex2) {
        int aWidthx9 = 9 * getImageWidth();
        double total[] = new double[2];
        double ssdTmp = cmptSSD9x9(myIndex1 - aWidthx9 - 9,myIndex2 - aWidthx9 - 9);
        ssdTmp += cmptSSD9x9(myIndex1 - aWidthx9,myIndex2 - aWidthx9);
        ssdTmp += cmptSSD9x9(myIndex1 - aWidthx9 + 9,myIndex2 - aWidthx9 + 9);
        ssdTmp += cmptSSD9x9(myIndex1 - 9,myIndex2 - 9);
        ssdTmp += cmptSSD9x9(myIndex1,myIndex2);
        ssdTmp += cmptSSD9x9(myIndex1 + 9,myIndex2 + 9);
        ssdTmp += cmptSSD9x9(myIndex1 + aWidthx9 - 9,myIndex2 + aWidthx9 - 9);
        ssdTmp += cmptSSD9x9(myIndex1 + aWidthx9,myIndex2 + aWidthx9);
        ssdTmp += cmptSSD9x9(myIndex1 + aWidthx9 + 9,myIndex2 + aWidthx9 + 9);
        //System.out.println("SSDCornerFltr: myIndex1 = "+myIndex1+", myIndex2 = "+myIndex2+", ssdTmp = "+ssdTmp);
        return ssdTmp;
    }    
    public int cmptSSD(int myIndex) {
        return cmptSSD(myIndex,myIndex);
    }
    public int cmptSSD(int myIndex1,int myIndex2) {        
        int ssd = 0;
        if (isInBounds3x3(myIndex1) && isInBounds3x3(myIndex2)) {

            int tmprlGrdntTmp1 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth - 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth - 1]);
            int tmprlGrdntTmp2 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth]);
            int tmprlGrdntTmp3 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth + 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth + 1]);
            int tmprlGrdntTmp4 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 - 1]);
            int tmprlGrdntTmp5 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2]);
            int tmprlGrdntTmp6 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 + 1]);
            int tmprlGrdntTmp7 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth - 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth - 1]);
            int tmprlGrdntTmp8 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth]);
            int tmprlGrdntTmp9 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth + 1]) - ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth + 1]);

            ssd = tmprlGrdntTmp1*tmprlGrdntTmp1 + tmprlGrdntTmp2*tmprlGrdntTmp2 +
                  tmprlGrdntTmp3*tmprlGrdntTmp3 + tmprlGrdntTmp4*tmprlGrdntTmp4 +
                  tmprlGrdntTmp5*tmprlGrdntTmp5 + tmprlGrdntTmp6*tmprlGrdntTmp6 +
                  tmprlGrdntTmp7*tmprlGrdntTmp7 + tmprlGrdntTmp8*tmprlGrdntTmp8 +
                  tmprlGrdntTmp9*tmprlGrdntTmp9;            
           
            /*
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth - 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth - 1]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth + 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 - imgWidth + 1]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 - 1]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 + 1]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth - 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth - 1]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth]));
            System.out.println("index1 = "+myIndex1+",myIndex2 = "+myIndex2+", crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth + 1]) + " - " + ColorSpectra.convertRGBToY(prevImgData[myIndex2 + imgWidth + 1]));
            
            System.out.println("tmprlGrdntTmp1=" + tmprlGrdntTmp1);
            System.out.println("tmprlGrdntTmp2=" + tmprlGrdntTmp2);
            System.out.println("tmprlGrdntTmp3=" + tmprlGrdntTmp3);
            System.out.println("tmprlGrdntTmp4=" + tmprlGrdntTmp4);
            System.out.println("tmprlGrdntTmp5=" + tmprlGrdntTmp5);
            System.out.println("tmprlGrdntTmp6=" + tmprlGrdntTmp6);
            System.out.println("tmprlGrdntTmp7=" + tmprlGrdntTmp7);
            System.out.println("tmprlGrdntTmp8=" + tmprlGrdntTmp8);
            System.out.println("tmprlGrdntTmp9=" + tmprlGrdntTmp9);
            
            System.out.println("SSDCornerFltr: ssd = "+ssd);   
            */
        }
        return ssd;
    }    
    public void setFirstFrameData(int myValues[], int i) {
        //System.out.println("OpticalFlowFltr: frameIndex = "+frameIndex+", i = "+i);
        if (frameIndex == 3) {
            firstFrame[i] = myValues[i];
            //System.out.println("OpticalFlowFltr: setting first frame = " + frameIndex + ", data i = " + i);
        }
    }
    public void setCrrntImgData(int i) {
        crrntImgData[i] = grayValues[i];
    }
    public void setCrrntImgData(int myValues[], int i) {
        crrntImgData[i] = ColorSpectra.convertRGBToY(myValues[i]);
    }
    public void cmptLclPntFlw(int myIndex) {
    }
    public double[] getVelcity() {
        return velocity;
    }
    public double[] getUntVlcty() {
        return untVlcty;
    }
    public void updateThreshold(int myDataValues[], int myIndex, int myThreshold) {
        myDataValues[myIndex] = (myDataValues[myIndex] < myThreshold) ? 0 : myDataValues[myIndex];
    }
    public static double[] cmptUnitVctr2x1(double myVlcty[]) {
        double unitVctr[] = new double[2];
        double unitVlctyX = 0;
        double unitVlctyY = 0;

        double vlctyMgntd = Math.sqrt(myVlcty[0] * myVlcty[0]
                + myVlcty[1] * myVlcty[1]);
        if (vlctyMgntd != 0) {
            unitVlctyX = myVlcty[0] / vlctyMgntd;
            unitVlctyY = myVlcty[1] / vlctyMgntd;
        }
        unitVctr[0] = unitVlctyX;
        unitVctr[1] = unitVlctyY;
        return unitVctr;
    }
    public double[][] getCrrntVelocity() {
        return crrntVelocity;
    }

    public double[][] getUnitVelocity() {
        return unitVelocity;
    }

    public void setPrvsUntVlcty(double myVelocity[][], int i) {
        if (frameIndex % 2 == 0) {
            prvsUntVlcty2[i][0] = myVelocity[i][0];
            prvsUntVlcty2[i][1] = myVelocity[i][1];
        } else {
            prvsUntVlcty1[i][0] = myVelocity[i][0];
            prvsUntVlcty1[i][1] = myVelocity[i][0];
        }
    }

    public double[][] getPrvsUntVlcty() {
        if (frameIndex % 2 == 0) {
            return prvsUntVlcty2;
        } else {
            return prvsUntVlcty1;
        }
    }
    public void setPrevImgData(int i) {
        if (frameIndex % 2 == 0) {
            prvsData2[i] = grayValues[i];
        } else {
            if (grayValues == null) {
                //System.out.println("grayValues = null");
            }
            if (prvsData1 == null) {
                //System.out.println("prvsData1 = null");
            }
            prvsData1[i] = grayValues[i];
        }
    }

    public void setPrevImgData(int grayValues[], int i) {
        if (frameIndex % 2 == 0) {
            prvsData2[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        } else {
            if (grayValues == null) {
                //System.out.println("grayValues = null");
            }
            if (prvsData1 == null) {
                //System.out.println("prvsData1 = null");
            }
            prvsData1[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        }
    }
    public int[] getPrvsData() {
        if (frameIndex % 2 == 0) {
            return prvsData2;
        } else {
            return prvsData1;
        }
    }

    public void setFrameNumber(int myFrameNumber) {
        frameIndex = myFrameNumber;
    }

    public int getFrameNumber() {
        return frameIndex;
    }

    public void incrmtFrmNmbr() {
        frameIndex++;
    }
    public void dcrmntFrmNmbr() {
        frameIndex--;
    } 
    /*
    public int[] getFltrdData() { 
        return this.crrntImgData; 
    }*/    
    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }    
    public void startPrsng() {
    }
    public void finish() {
        System.out.println("CrrltnCornerFltr: finish parsing frame = " + frameIndex);
        //prevImgData = getPrvsData();
        prevImgData = crrntImgData;
        prvsUntVlcty = getPrvsUntVlcty();
    }
}