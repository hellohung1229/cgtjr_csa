/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgtjr.academics.elctrclengnrng.video;

/**
 *
 * @author clayton g thomas jr
 */
import cgtjr.academics.elctrclengnrng.imgprcssng.YSclFltr;
import cgtjr.academics.elctrclengnrng.imgprcssng.draw.ImageDrawData;
import cgtjr.academics.elctrclengnrng.imgprcssng.general.ImageFilter;
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.math.geometry.linepnts.ArrowCrtr;

public class CrrltnFltr extends ImageFilter {

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
    private double untVlctyTlt[];
    private double untVlctyAvrg[];
    private int nmbrOfCrnrs;
    private int windowDmnsn;
    private double ssd = -Integer.MAX_VALUE;
    private int indexValue;
    private int frm1TmprlGrdntDiff;
    private ImageDrawData imagePixelData;    

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;

        crrntImgData = new int[aLength];
        untVlctyTlt = new double[2];
        untVlctyAvrg = new double[2];
        nmbrOfCrnrs = 0;

        imgWidth = myImageWidth;
        imgHeight = myImageHeight;

        //grayValues = getGryVls();
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
        //setPrevImgData(dataValues,i);
        imagePixelData.updatePixels(dataValues,i);
        //super.cornerDetect(dataValues, i);
        if (frameIndex > 1) {
            crrltnFltr(dataValues,i);            
        }        
    }    
    /*
    public void cornerDetect(int dataValues[], int i) {
        crrntImgData = dataValues;
        setPrevImgData(dataValues, i);
        super.filter(dataValues, i);
    }*/    
    public void crrltnFltr(int dataValues[], int i) {
        //filter3x3(dataValues, i);  
        //super.filter(dataValues, i);
        //if (getEigenValue(i) >= getThreshold()) {
            //cmptNCC9x9Dstnc9x9(i);
            cmptNCC27x27Dstnc27x27(i);            
            cmptVelocity(i);
            ssd = -Integer.MAX_VALUE;
            indexValue = Integer.MAX_VALUE;
        //}
    }
    public void cmptVelocity(int myIndex) {

        int x1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int x2 = ImageTool.rtrvXPstn(indexValue, imgWidth);
        int y2 = ImageTool.rtrvYPstn(indexValue, imgWidth);

        int vx = x2 - x1;
        int vy = y2 - y1;

        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());
        int myPixelData[] = imagePixelData.getImagePixels();
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, imgWidth, imgHeight,0x0050e60d,0x0050e60d);
        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }
    public int getIndexValue() {
        return indexValue;
    }
    public void cmptNCC9x9Dstnc9x9(int myIndex) {
        ssd = -Integer.MAX_VALUE;
        cmptNCC9x9Dstnc9x9(myIndex, myIndex);
    }
    public double getNCCValue()
    {
        return ssd;
    }
    public void cmptNCC9x9Dstnc9x9(int myIndex, int myDistance) {
        int aWidthx3 = 3 * getImageWidth();

        cmptNCC9x9Dstnc3x3(myIndex, myDistance - aWidthx3 - 3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance - aWidthx3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance - aWidthx3 + 3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance - 3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance + 3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance + aWidthx3 - 3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance + aWidthx3);
        cmptNCC9x9Dstnc3x3(myIndex, myDistance + aWidthx3 + 3);
    }
    public void cmptNCC9x9Dstnc3x3(int myIndex, int myDistance) {

        int aWidthx3 = getImageWidth();
        double ssd0 = cmptNCC9x9(myIndex, myDistance - aWidthx3 - 1);
        double ssd1 = cmptNCC9x9(myIndex, myDistance - aWidthx3);
        double ssd2 = cmptNCC9x9(myIndex, myDistance - aWidthx3 + 1);
        double ssd3 = cmptNCC9x9(myIndex, myDistance - 1);
        double ssd4 = cmptNCC9x9(myIndex, myDistance);
        double ssd5 = cmptNCC9x9(myIndex, myDistance + 1);
        double ssd6 = cmptNCC9x9(myIndex, myDistance + aWidthx3 - 1);
        double ssd7 = cmptNCC9x9(myIndex, myDistance + aWidthx3);
        double ssd8 = cmptNCC9x9(myIndex, myDistance + aWidthx3 + 1);

        if (ssd0 > ssd) {
            ssd = ssd0;
            indexValue = myDistance - aWidthx3 - 1;
        }
        if (ssd1 > ssd) {
            ssd = ssd1;
            indexValue = myDistance - aWidthx3;
        }
        if (ssd2 > ssd) {
            ssd = ssd2;
            indexValue = myDistance - aWidthx3 + 1;
        }
        if (ssd3 > ssd) {
            ssd = ssd3;
            indexValue = myDistance - 1;
        }
        if (myIndex == myDistance && ssd4 >= ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }else if (ssd4 > ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }
        if (ssd5 > ssd) {
            ssd = ssd5;
            indexValue = myDistance + 1;
        }
        if (ssd6 > ssd) {
            ssd = ssd6;
            indexValue = myDistance + aWidthx3 - 1;
        }
        if (ssd7 > ssd) {
            ssd = ssd7;
            indexValue = myDistance + aWidthx3;
        }
        if (ssd8 > ssd) {
            ssd = ssd8;
            indexValue = myDistance + aWidthx3 + 1;
        }
        //System.out.println("CrrltnCrnrFltr: ssd0=" + ssd0 + ",ssd1=" + ssd1 + ",ssd2=" + ssd2 + ",ssd3=" + ssd3 + ",ssd4=" + ssd4 + ",ssd5=" + ssd5 + ",ssd6=" + ssd6 + ",ssd7=" + ssd7 + ",ssd8=" + ssd8);
        //System.out.println("CrrltnCrnrFltr: indexValue =" + indexValue + ", ssd = " + ssd);
    }

    public double cmptNCC9x9(int myIndex1, int myIndex2) {
        double nmbrOfPnts = 81.0;
        double aSumL9x9 = cmptSumL9x9(myIndex1);
        double aSumR9x9 = cmptSumR9x9(myIndex2);
        double aSumLAvg = aSumL9x9 / nmbrOfPnts;
        double aSumRAvg = aSumR9x9 / nmbrOfPnts;

        double aSum2L9x9 = cmptSum2L9x9(myIndex1, aSumLAvg);
        double aSum2R9x9 = cmptSum2R9x9(myIndex2, aSumRAvg);

        double aSqrtSum2L = Math.sqrt(aSum2L9x9);
        double aSqrtSum2R = Math.sqrt(aSum2R9x9);

        double product = cmptPrdct9x9(myIndex1, myIndex2, aSumLAvg, aSumRAvg);

        double value = product / (aSqrtSum2L * aSqrtSum2R);

        int x1 = ImageTool.rtrvXPstn(myIndex1, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex1, imgWidth);
        int x2 = ImageTool.rtrvXPstn(myIndex2, imgWidth);
        int y2 = ImageTool.rtrvYPstn(myIndex2, imgWidth);

        //System.out.println("CrrltnCornerFltr: x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2 = "+y2+" , value = " + value);
        return value;
    }
    public void cmptNCC27x27Dstnc9x9(int myIndex) {
        ssd = -Integer.MAX_VALUE;        
        cmptNCC27x27Dstnc9x9(myIndex,myIndex);        
    }
    public void cmptNCC27x27Dstnc27x27(int myIndex) {
        ssd = -Integer.MAX_VALUE;      
        cmptNCC27x27Dstnc27x27(myIndex,myIndex);
    }
    public void cmptNCC27x27Dstnc27x27(int myIndex, int myDistance) {
        int aWidthx9 = 9 * getImageWidth();

        cmptNCC27x27Dstnc9x9(myIndex, myDistance - aWidthx9 - 9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance - aWidthx9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance - aWidthx9 + 9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance - 9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance + 9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance + aWidthx9 - 9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance + aWidthx9);
        cmptNCC27x27Dstnc9x9(myIndex, myDistance + aWidthx9 + 9);
        
    }
    public void cmptNCC27x27Dstnc9x9(int myIndex, int myDistance) {
        int aWidthx3 = 3 * getImageWidth();

        cmptNCC27x27Dstnc3x3(myIndex, myDistance - aWidthx3 - 3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance - aWidthx3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance - aWidthx3 + 3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance - 3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance + 3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance + aWidthx3 - 3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance + aWidthx3);
        cmptNCC27x27Dstnc3x3(myIndex, myDistance + aWidthx3 + 3);
    }
    public void cmptNCC27x27Dstnc3x3(int myIndex, int myDistance) {

        int aWidthx3 = getImageWidth();

        double ssd0 = cmptNCC27x27(myIndex, myDistance - aWidthx3 - 1);
        double ssd1 = cmptNCC27x27(myIndex, myDistance - aWidthx3);
        double ssd2 = cmptNCC27x27(myIndex, myDistance - aWidthx3 + 1);
        double ssd3 = cmptNCC27x27(myIndex, myDistance - 1);
        double ssd4 = cmptNCC27x27(myIndex, myDistance);
        double ssd5 = cmptNCC27x27(myIndex, myDistance + 1);
        double ssd6 = cmptNCC27x27(myIndex, myDistance + aWidthx3 - 1);
        double ssd7 = cmptNCC27x27(myIndex, myDistance + aWidthx3);
        double ssd8 = cmptNCC27x27(myIndex, myDistance + aWidthx3 + 1);


        if (ssd0 > ssd) {
            ssd = ssd0;
            indexValue = myDistance - aWidthx3 - 1;
        }
        if (ssd1 > ssd) {
            ssd = ssd1;
            indexValue = myDistance - aWidthx3;
        }
        if (ssd2 > ssd) {
            ssd = ssd2;
            indexValue = myDistance - aWidthx3 + 1;
        }
        if (ssd3 > ssd) {
            ssd = ssd3;
            indexValue = myDistance - 1;
        }
        if (myIndex == myDistance && ssd4 >= ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }else if (ssd4 > ssd) {
            ssd = ssd4;
            indexValue = myDistance;
        }
        if (ssd5 > ssd) {
            ssd = ssd5;
            indexValue = myDistance + 1;
        }
        if (ssd6 > ssd) {
            ssd = ssd6;
            indexValue = myDistance + aWidthx3 - 1;
        }
        if (ssd7 > ssd) {
            ssd = ssd7;
            indexValue = myDistance + aWidthx3;
        }
        if (ssd8 > ssd) {
            ssd = ssd8;
            indexValue = myDistance + aWidthx3 + 1;
        }
        //System.out.println("CrrltnCrnrFltr: ssd0=" + ssd0 + ",ssd1=" + ssd1 + ",ssd2=" + ssd2 + ",ssd3=" + ssd3 + ",ssd4=" + ssd4 + ",ssd5=" + ssd5 + ",ssd6=" + ssd6 + ",ssd7=" + ssd7 + ",ssd8=" + ssd8);
        //System.out.println("CrrltnCrnrFltr: myDistance = "+myDistance+", indexValue =" + indexValue + ", ssd = " + ssd);
    }    
    public double cmptNCC27x27(int myIndex1, int myIndex2) {
        double nmbrOfPnts = 729.0;
        double aSumL27x27 = cmptSumL27x27(myIndex1);
        double aSumR27x27 = cmptSumR27x27(myIndex2);
        double aSumLAvg = aSumL27x27 / nmbrOfPnts;
        double aSumRAvg = aSumR27x27 / nmbrOfPnts;

        double product = cmptPrdct27x27(myIndex1, myIndex2, aSumLAvg, aSumRAvg);
        double aSum2L27x27 = cmptSum2L27x27(myIndex1, aSumLAvg);
        double aSum2R27x27 = cmptSum2R27x27(myIndex2, aSumRAvg);

        double aSqrtSum2L = Math.sqrt(aSum2L27x27);
        double aSqrtSum2R = Math.sqrt(aSum2R27x27);

        double value = product / (aSqrtSum2L * aSqrtSum2R);
        //int x1 = ImageTool.rtrvXPstn(myIndex1, imgWidth);
        //int y1 = ImageTool.rtrvYPstn(myIndex1, imgWidth);
        //int x2 = ImageTool.rtrvXPstn(myIndex2, imgWidth);
        //int y2 = ImageTool.rtrvYPstn(myIndex2, imgWidth);

        //System.out.println("CrrltnCornerFltr: x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2 = "+y2+" , value = " + value);
        return value;
    }

    public double cmptNCC(int myIndex1, int myIndex2) {

        double nmbrOfPnts = 9.0;
        int aSumL = cmptSumL(myIndex1);
        int aSumR = cmptSumR(myIndex2);
        double aSumLAvg = aSumL / nmbrOfPnts;
        double aSumRAvg = aSumR / nmbrOfPnts;

        double product = cmptPrdct(myIndex1, myIndex2, aSumLAvg, aSumRAvg);
        double aSum2L = cmptSum2L(myIndex1, aSumLAvg);
        double aSum2R = cmptSum2R(myIndex2, aSumRAvg);

        double aSqrtSum2L = Math.sqrt(aSum2L);
        double aSqrtSum2R = Math.sqrt(aSum2R);

        double answer = product / (aSqrtSum2L * aSqrtSum2R);
        int x1 = ImageTool.rtrvXPstn(myIndex1, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex1, imgWidth);
        int x2 = ImageTool.rtrvXPstn(myIndex2, imgWidth);
        int y2 = ImageTool.rtrvYPstn(myIndex2, imgWidth);

        //System.out.println("CrrltnCornerFltr: x1 = "+x1+", y1 = "+y1+", x2 = "+x2+", y2 = "+y2+" , value = " + answer);
        return answer;
    }

    public double cmptSumL27x27(int myIndex1) {
        int aWidthx9 = 9 * getImageWidth();

        double ssdTmp = cmptSumL9x9(myIndex1 - aWidthx9 - 9);
        ssdTmp += cmptSumL9x9(myIndex1 - aWidthx9);
        ssdTmp += cmptSumL9x9(myIndex1 - aWidthx9 + 9);
        ssdTmp += cmptSumL9x9(myIndex1 - 9);
        ssdTmp += cmptSumL9x9(myIndex1);
        ssdTmp += cmptSumL9x9(myIndex1 + 9);
        ssdTmp += cmptSumL9x9(myIndex1 + aWidthx9 - 9);
        ssdTmp += cmptSumL9x9(myIndex1 + aWidthx9);
        ssdTmp += cmptSumL9x9(myIndex1 + aWidthx9 + 9);
        //System.out.println("CrrltnCornerFltr: myIndex1 = " + myIndex1 + ", myIndex2 = " + myIndex2 + ", ssdTmp = " + ssdTmp);
        return ssdTmp;
    }

    public double cmptSumR27x27(int myIndex1) {
        int aWidthx9 = 9 * getImageWidth();
        double ssdTmp = cmptSumR9x9(myIndex1 - aWidthx9 - 9);
        ssdTmp += cmptSumR9x9(myIndex1 - aWidthx9);
        ssdTmp += cmptSumR9x9(myIndex1 - aWidthx9 + 9);
        ssdTmp += cmptSumR9x9(myIndex1 - 9);
        ssdTmp += cmptSumR9x9(myIndex1);
        ssdTmp += cmptSumR9x9(myIndex1 + 9);
        ssdTmp += cmptSumR9x9(myIndex1 + aWidthx9 - 9);
        ssdTmp += cmptSumR9x9(myIndex1 + aWidthx9);
        ssdTmp += cmptSumR9x9(myIndex1 + aWidthx9 + 9);
        //System.out.println("CrrltnCornerFltr: myIndex1 = " + myIndex1 + ", myIndex2 = " + myIndex2 + ", ssdTmp = " + ssdTmp);
        return ssdTmp;
    }

    public double cmptSumL9x9(int myIndex) {
        int aWidthx3 = 3 * getImageWidth();
        double sumL = cmptSumL(myIndex - aWidthx3 - 3);
        sumL += cmptSumL(myIndex - aWidthx3);
        sumL += cmptSumL(myIndex - aWidthx3 + 3);
        sumL += cmptSumL(myIndex - 3);
        sumL += cmptSumL(myIndex);
        sumL += cmptSumL(myIndex + 3);
        sumL += cmptSumL(myIndex + aWidthx3 - 3);
        sumL += cmptSumL(myIndex + aWidthx3);
        sumL += cmptSumL(myIndex + aWidthx3 + 3);
        return sumL;
    }

    public double cmptSumR9x9(int myIndex) {
        int aWidthx3 = 3 * getImageWidth();
        double sumR = cmptSumR(myIndex - aWidthx3 - 3);
        sumR += cmptSumR(myIndex - aWidthx3);
        sumR += cmptSumR(myIndex - aWidthx3 + 3);
        sumR += cmptSumR(myIndex - 3);
        sumR += cmptSumR(myIndex);
        sumR += cmptSumR(myIndex + 3);
        sumR += cmptSumR(myIndex + aWidthx3 - 3);
        sumR += cmptSumR(myIndex + aWidthx3);
        sumR += cmptSumR(myIndex + aWidthx3 + 3);
        return sumR;
    }

    public double cmptPrdct9x9(int myIndex1, int myIndex2, double aSumLAvg, double aSumRAvg) {
        //double aSumL = cmptSumL9x9(myIndex1);
        //double aSumR = cmptSumR9x9(myIndex2);
        //double aSumLAvg = aSumL / 81;
        //double aSumRAvg = aSumR / 81;

        int aWidthx3 = 3 * getImageWidth();

        double product = cmptPrdct(myIndex1 - aWidthx3 - 3, myIndex2 - aWidthx3 - 3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 - aWidthx3, myIndex2 - aWidthx3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 - aWidthx3 + 3, myIndex2 - aWidthx3 + 3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 - 3, myIndex2 - 3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1, myIndex2, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 + 3, myIndex2 + 3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 + aWidthx3 - 3, myIndex2 + aWidthx3 - 3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 + aWidthx3, myIndex2 + aWidthx3, aSumLAvg, aSumRAvg);
        product += cmptPrdct(myIndex1 + aWidthx3 + 3, myIndex2 + aWidthx3 + 3, aSumLAvg, aSumRAvg);

        return product;
    }

    public double cmptPrdct27x27(int myIndex1, int myIndex2, double aSumLAvg, double aSumRAvg) {
        //double aSumL = cmptSumL27x27(myIndex1);
        //double aSumR = cmptSumR27x27(myIndex2);
        //double aSumLAvg = aSumL / 729;
        //double aSumRAvg = aSumR / 729;
        //double aSum2L = cmptSum2L27x27(myIndex1);
        //double aSum2R = cmptSum2R27x27(myIndex2);        
        //double aSqrtSum2L = Math.sqrt(aSum2L);
        //double aSqrtSum2R = Math.sqrt(aSum2R);

        int aWidthx9 = 9 * getImageWidth();

        double product = cmptPrdct9x9(myIndex1 - aWidthx9 - 9, myIndex2 - aWidthx9 - 9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 - aWidthx9, myIndex2 - aWidthx9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 - aWidthx9 + 9, myIndex2 - aWidthx9 + 9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 - 9, myIndex2 - 9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1, myIndex2, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 + 9, myIndex2 + 9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 + aWidthx9 - 9, myIndex2 + aWidthx9 - 9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 + aWidthx9, myIndex2 + aWidthx9, aSumLAvg, aSumRAvg);
        product += cmptPrdct9x9(myIndex1 + aWidthx9 + 9, myIndex2 + aWidthx9 + 9, aSumLAvg, aSumRAvg);
        return product;
    }

    public double cmptPrdct(int myIndex1, int myIndex2, double aSumLAvg, double aSumRAvg) {

        double sum = 0;
        //double aSumLAvg = cmptSumL(myIndex1)/9;
        //double aSumRAvg = cmptSumR(myIndex1)/9;        
        if (isInBounds3x3(myIndex1) && isInBounds3x3(myIndex2)) {
            int pixelValueL1 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth - 1]);
            int pixelValueL2 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth]);
            int pixelValueL3 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth + 1]);
            int pixelValueL4 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - 1]);
            int pixelValueL5 = ColorSpectra.convertRGBToY(prevImgData[myIndex1]);
            int pixelValueL6 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + 1]);
            int pixelValueL7 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth - 1]);
            int pixelValueL8 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth]);
            int pixelValueL9 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth + 1]);

            int pixelValueR1 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 - imgWidth - 1]);
            int pixelValueR2 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 - imgWidth]);
            int pixelValueR3 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 - imgWidth + 1]);
            int pixelValueR4 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 - 1]);
            int pixelValueR5 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2]);
            int pixelValueR6 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 + 1]);
            int pixelValueR7 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 + imgWidth - 1]);
            int pixelValueR8 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 + imgWidth]);
            int pixelValueR9 = ColorSpectra.convertRGBToY(crrntImgData[myIndex2 + imgWidth + 1]);

            sum = (pixelValueL1 - aSumLAvg) * (pixelValueR1 - aSumRAvg) + (pixelValueL2 - aSumLAvg) * (pixelValueR2 - aSumRAvg)
                    + (pixelValueL3 - aSumLAvg) * (pixelValueR3 - aSumRAvg) + (pixelValueL4 - aSumLAvg) * (pixelValueR4 - aSumRAvg)
                    + (pixelValueL5 - aSumLAvg) * (pixelValueR5 - aSumRAvg) + (pixelValueL6 - aSumLAvg) * (pixelValueR6 - aSumRAvg)
                    + (pixelValueL7 - aSumLAvg) * (pixelValueR7 - aSumRAvg) + (pixelValueL8 - aSumLAvg) * (pixelValueR8 - aSumRAvg)
                    + (pixelValueL9 - aSumLAvg) * (pixelValueR9 - aSumRAvg);
        }
        return sum;
    }
    public int cmptSumL(int myIndex1) {
        int ssd = 0;
        
        if (isInBounds3x3(myIndex1)) {

            int pixelValue1 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth - 1]);
            int pixelValue2 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth]);
            int pixelValue3 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth + 1]);
            int pixelValue4 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - 1]);
            int pixelValue5 = ColorSpectra.convertRGBToY(prevImgData[myIndex1]);
            int pixelValue6 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + 1]);
            int pixelValue7 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth - 1]);
            int pixelValue8 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth]);
            int pixelValue9 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth + 1]);

            ssd = pixelValue1 + pixelValue2
                    + pixelValue3 + pixelValue4
                    + pixelValue5 + pixelValue6
                    + pixelValue7 + pixelValue8
                    + pixelValue9;
        }
        return ssd;
    }

    public double cmptSum2L(int myIndex1, double anAvg) {

        double ssd2 = 0;
        if (isInBounds3x3(myIndex1)) {
            int pixelValue1 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth - 1]);
            int pixelValue2 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth]);
            int pixelValue3 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - imgWidth + 1]);
            int pixelValue4 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 - 1]);
            int pixelValue5 = ColorSpectra.convertRGBToY(prevImgData[myIndex1]);
            int pixelValue6 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + 1]);
            int pixelValue7 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth - 1]);
            int pixelValue8 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth]);
            int pixelValue9 = ColorSpectra.convertRGBToY(prevImgData[myIndex1 + imgWidth + 1]);
            ssd2 = (pixelValue1 - anAvg) * (pixelValue1 - anAvg) + (pixelValue2 - anAvg) * (pixelValue2 - anAvg)
                    + (pixelValue3 - anAvg) * (pixelValue3 - anAvg) + (pixelValue4 - anAvg) * (pixelValue4 - anAvg)
                    + (pixelValue5 - anAvg) * (pixelValue5 - anAvg) + (pixelValue6 - anAvg) * (pixelValue6 - anAvg)
                    + (pixelValue7 - anAvg) * (pixelValue7 - anAvg) + (pixelValue8 - anAvg) * (pixelValue8 - anAvg)
                    + (pixelValue9 - anAvg) * (pixelValue9 - anAvg);
        }
        return ssd2;
    }

    public int cmptSumR(int myIndex1) {
        int ssd = 0;
        if (isInBounds3x3(myIndex1)) {
            int pixelValue1 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth - 1]);
            int pixelValue2 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth]);
            int pixelValue3 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth + 1]);
            int pixelValue4 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - 1]);
            int pixelValue5 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1]);
            int pixelValue6 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + 1]);
            int pixelValue7 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth - 1]);
            int pixelValue8 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth]);
            int pixelValue9 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth + 1]);

            ssd = pixelValue1 + pixelValue2
                    + pixelValue3 + pixelValue4
                    + pixelValue5 + pixelValue6
                    + pixelValue7 + pixelValue8
                    + pixelValue9;
        }
        return ssd;
    }

    public double cmptSum2R(int myIndex1, double anAvg) {
        double ssd = 0;
        double ssd2 = 0;
        if (isInBounds3x3(myIndex1)) {

            int pixelValue1 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth - 1]);
            int pixelValue2 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth]);
            int pixelValue3 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - imgWidth + 1]);
            int pixelValue4 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 - 1]);
            int pixelValue5 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1]);
            int pixelValue6 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + 1]);
            int pixelValue7 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth - 1]);
            int pixelValue8 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth]);
            int pixelValue9 = ColorSpectra.convertRGBToY(crrntImgData[myIndex1 + imgWidth + 1]);

            ssd = (pixelValue1 - anAvg) * (pixelValue1 - anAvg) + (pixelValue2 - anAvg) * (pixelValue2 - anAvg)
                    + (pixelValue3 - anAvg) * (pixelValue3 - anAvg) + (pixelValue4 - anAvg) * (pixelValue4 - anAvg)
                    + (pixelValue5 - anAvg) * (pixelValue5 - anAvg) + (pixelValue6 - anAvg) * (pixelValue6 - anAvg)
                    + (pixelValue7 - anAvg) * (pixelValue7 - anAvg) + (pixelValue8 - anAvg) * (pixelValue8 - anAvg)
                    + (pixelValue9 - anAvg) * (pixelValue9 - anAvg);

            ssd2 = ssd;
        }
        return ssd2;
    }

    public double cmptSum2R9x9(int myIndex, double aSumRAvg) {
        int aWidthx3 = 3 * getImageWidth();

        double sumR = cmptSum2R(myIndex - aWidthx3 - 3, aSumRAvg);
        sumR += cmptSum2R(myIndex - aWidthx3, aSumRAvg);
        sumR += cmptSum2R(myIndex - aWidthx3 + 3, aSumRAvg);
        sumR += cmptSum2R(myIndex - 3, aSumRAvg);
        sumR += cmptSum2R(myIndex, aSumRAvg);
        sumR += cmptSum2R(myIndex + 3, aSumRAvg);
        sumR += cmptSum2R(myIndex + aWidthx3 - 3, aSumRAvg);
        sumR += cmptSum2R(myIndex + aWidthx3, aSumRAvg);
        sumR += cmptSum2R(myIndex + aWidthx3 + 3, aSumRAvg);
        return sumR;
    }

    public double cmptSum2L9x9(int myIndex, double aSumLAvg) {
        int aWidthx3 = 3 * getImageWidth();

        double sumL = cmptSum2L(myIndex - aWidthx3 - 3, aSumLAvg);
        sumL += cmptSum2L(myIndex - aWidthx3, aSumLAvg);
        sumL += cmptSum2L(myIndex - aWidthx3 + 3, aSumLAvg);
        sumL += cmptSum2L(myIndex - 3, aSumLAvg);
        sumL += cmptSum2L(myIndex, aSumLAvg);
        sumL += cmptSum2L(myIndex + 3, aSumLAvg);
        sumL += cmptSum2L(myIndex + aWidthx3 - 3, aSumLAvg);
        sumL += cmptSum2L(myIndex + aWidthx3, aSumLAvg);
        sumL += cmptSum2L(myIndex + aWidthx3 + 3, aSumLAvg);
        return sumL;
    }

    public double cmptSum2R27x27(int myIndex, double anAvg) {
        int aWidthx9 = 9 * getImageWidth();

        double sumR = cmptSum2R9x9(myIndex - aWidthx9 - 9, anAvg);
        sumR += cmptSum2R9x9(myIndex - aWidthx9, anAvg);
        sumR += cmptSum2R9x9(myIndex - aWidthx9 + 9, anAvg);
        sumR += cmptSum2R9x9(myIndex - 9, anAvg);
        sumR += cmptSum2R9x9(myIndex, anAvg);
        sumR += cmptSum2R9x9(myIndex + 9, anAvg);
        sumR += cmptSum2R9x9(myIndex + aWidthx9 - 9, anAvg);
        sumR += cmptSum2R9x9(myIndex + aWidthx9, anAvg);
        sumR += cmptSum2R9x9(myIndex + aWidthx9 + 9, anAvg);
        return sumR;
    }

    public double cmptSum2L27x27(int myIndex, double anAvg) {
        int aWidthx9 = 9 * getImageWidth();
        double sumL = cmptSum2L9x9(myIndex - aWidthx9 - 9, anAvg);
        sumL += cmptSum2L9x9(myIndex - aWidthx9, anAvg);
        sumL += cmptSum2L9x9(myIndex - aWidthx9 + 9, anAvg);
        sumL += cmptSum2L9x9(myIndex - 9, anAvg);
        sumL += cmptSum2L9x9(myIndex, anAvg);
        sumL += cmptSum2L9x9(myIndex + 9, anAvg);
        sumL += cmptSum2L9x9(myIndex + aWidthx9 - 9, anAvg);
        sumL += cmptSum2L9x9(myIndex + aWidthx9, anAvg);
        sumL += cmptSum2L9x9(myIndex + aWidthx9 + 9, anAvg);
        return sumL;
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
    /*
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
    */
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
     * public int[] getFltrdData() { return this.crrntImgData; }
     */

    public void startPrsng() {
    }
    public int[] getFltrdData() {
        return imagePixelData.getImagePixels();
    }
    public void finish() {
        System.out.println("CrrltnCornerFltr: finish parsing frame = " + frameIndex);
        //prevImgData = getPrvsData();
        prevImgData = crrntImgData;
        prvsUntVlcty = getPrvsUntVlcty();
    }
}