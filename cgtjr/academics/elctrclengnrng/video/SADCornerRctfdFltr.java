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

public class SADCornerRctfdFltr extends CornerDetect {

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
    private double sad;
    private int matchingIndex;
    private ImageDrawData imagePixelData;
    private int frm1TmprlGrdntDiff;
    private boolean shouldDraw;

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

        sad = Integer.MAX_VALUE;
        matchingIndex = Integer.MAX_VALUE;

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
        imagePixelData = getImageDrawData();
    }

    public void filter(int dataValues[], int i) {
        
        //super.cornerDetect(dataValues, i);
        imagePixelData.updatePixels(dataValues, i);
        crrntImgData = dataValues;
        setPrevImgData(dataValues, i);
        //updatePixels(dataValues, i);
        //super.cornerDetect(dataValues, i);
        if (frameIndex > 1) {
            crrltnFltr(dataValues, i);
        }
    }

    public void crrltnFltr(int dataValues[], int i) {
        //filter3x3(dataValues, i);  
        int x = ImageTool.rtrvXPstn(i, imgWidth);
        int y = ImageTool.rtrvYPstn(i, imgWidth);

        //if ( x == 130 && y == 70 ) {   
        super.cornerDetect(dataValues, i);
        //updatePixels(dataValues, i);
        //if ( x % 20 == 0 && y % 20 == 0 ) {       
        if(getEigenValue(i) >= getThreshold()){
        cmptSAD27x27Dstnc243x243(i, i);
            //cmptSAD27x27Dstnc27x27(i, i);
        drawData(i);
        sad = Integer.MAX_VALUE;
        matchingIndex = Integer.MAX_VALUE;
        shouldDraw = false;
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
     cmptSAD27x27Dstnc27x27(i,i);     
     cmptVelocity(i);
     sad = Integer.MAX_VALUE;
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

    public void drawData(int myIndex) {
        if (matchingIndex == Integer.MAX_VALUE || shouldDraw == false) {
            return;
        }
        int x1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int y1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int x2 = ImageTool.rtrvXPstn(matchingIndex, imgWidth);
        int y2 = ImageTool.rtrvYPstn(matchingIndex, imgWidth);

        int vx = x2 - x1;
        int vy = y2 - y1;

        int dispX = Math.abs(x2 - x1);
        int dispY = y2 - y1;
        int pixelColor = (dispX * 254 * 2) / 243;

        //System.out.println("CrrltnMatchFltr: count = " + getCornerCount());ss
        //System.out.println("SADCornerRctfdFltr: index = "+myIndex+", matchingIndex = "+matchingIndex+", imgWidth = "+imgWidth+", x2 = "+x2+", x1 = "+x1+", y2 = "+y2+", y1= "+y1+", dispX = "+dispX+", dispY = "+dispY+", pixelColor = "+pixelColor);
        int myPixelData[] = imagePixelData.getImagePixels();
        //if (PntTool.getDistance(x1, y1, x2, y2) < 200) {
        ArrowCrtr.drawArrow(x1, y1, x2, y2, myPixelData, imgWidth, imgHeight, 0x0050e60d, 0x0050e60d);
        imagePixelData.drawData(pixelColor, myIndex);
        //myPixelData[myIndex] = pixelColor;     
        //}
        //System.out.println("CrrltnCrnrFltr: x1 = "+x1+", x2 = "+x2+", vx = "+vx);
        //System.out.println("CrrltnCrnrFltr: y1 = "+y1+", y2 = "+y2+", vy = "+vy);       
    }

    public void cmptSAD9x9Dstnc9x9(int myIndex) {
        cmptSAD9x9Dstnc9x9(myIndex, myIndex);
    }

    public void cmptSAD27x27Dstnc243x243(int myIndex, int myDistance) {
        int aWidthx81 = 81 * getImageWidth();


        int xi1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int yi1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int xi2 = ImageTool.rtrvXPstn(myDistance, imgWidth);
        int yi2 = ImageTool.rtrvYPstn(myDistance, imgWidth);

        int xw = imgWidth - 1;
        int d1 = xw - xi1;
        int dpxi = xi2 + 243 / 2 + 27 / 2;
        //int dmxi  = xi2 - 243/2 - 27/2;
        int dmxi = xi2 - 27 / 2;
        int dpxi81 = xi2 + 243 / 2 + 27 / 2 + 81;
        int dmxi81 = xi2 - 243 / 2 - 27 / 2 - 81;


        //cmptSAD27x27Dstnc81x81(myIndex, myDistance - aWidthx81 - 81);
        //cmptSAD27x27Dstnc81x81(myIndex, myDistance - aWidthx81);
        //cmptSAD27x27Dstnc81x81(myIndex, myDistance - aWidthx81 + 81);
        /*
         if(dmxi81 >0 && dpxi < xw){
         cmptSAD27x27Dstnc81x81(myIndex, myDistance - 81);            
         }*/
        //  if(dmxi >=0 && dpxi81 <= xw){
        cmptSAD27x27Dstnc81x81(myIndex, myDistance - 81);
        cmptSAD27x27Dstnc81x81(myIndex, myDistance);
        cmptSAD27x27Dstnc81x81(myIndex, myDistance + 81);
        //   }
        //cmptSAD27x27Dstnc81x81(myIndex, myDistance + aWidthx81 - 81);
        //cmptSAD27x27Dstnc81x81(myIndex, myDistance + aWidthx81);
        //cmptSAD27x27Dstnc81x81(myIndex, myDistance + aWidthx81 + 81);        
    }

    public void cmptSAD27x27Dstnc81x81(int myIndex, int myDistance) {
        int aWidthx27 = 27 * getImageWidth();
        int xi1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int yi1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int xi2 = ImageTool.rtrvXPstn(myDistance, imgWidth);
        int yi2 = ImageTool.rtrvYPstn(myDistance, imgWidth);

        int xw = imgWidth - 1;
        int d1 = xw - xi1;
        int dpxi = xi2 + 27 / 2;
        //int dmxi  = xi2 - 243/2 - 27/2;
        int dmxi = xi2 - 27 / 2;

        //if (dmxi >= 0 && dpxi <= xw && yi1 == yi2) {
        //if (yi1 == yi2) {
            cmptSAD27x27Dstnc27x27(myIndex, myDistance - 27);
            cmptSAD27x27Dstnc27x27(myIndex, myDistance);
            cmptSAD27x27Dstnc27x27(myIndex, myDistance + 27);
            //shouldDraw = true;
        //} else {
            //shouldDraw = false;
        //}
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance - aWidthx27 - 27);
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance - aWidthx27);
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance - aWidthx27 + 27);
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance + aWidthx27 - 27);
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance + aWidthx27);
        //cmptSAD27x27Dstnc27x27(myIndex, myDistance + aWidthx27 + 27);        
    }

    public void cmptSAD27x27Dstnc27x27(int myIndex, int myDistance) {
        int aWidthx9 = 9 * getImageWidth();

        //cmptSAD27x27Dstnc9x9(myIndex, myDistance - aWidthx9 - 9);
        //cmptSAD27x27Dstnc9x9(myIndex, myDistance - aWidthx9);
        //cmptSAD27x27Dstnc9x9(myIndex, myDistance - aWidthx9 + 9);
        cmptSAD27x27Dstnc9x9(myIndex, myDistance - 9);
        cmptSAD27x27Dstnc9x9(myIndex, myDistance);
        cmptSAD27x27Dstnc9x9(myIndex, myDistance + 9);
        //cmptSAD27x27Dstnc9x9(myIndex, myDistance + aWidthx9 - 9);
        //cmptSAD27x27Dstnc9x9(myIndex, myDistance + aWidthx9);
        //cmptSAD27x27Dstnc9x9(myIndex, myDistance + aWidthx9 + 9);
    }

    public void cmptSAD27x27Dstnc9x9(int myIndex, int myDistance) {
        int aWidthx3 = 3 * getImageWidth();

        //cmptSAD27x27Dstnc3x3(myIndex, myDistance - aWidthx3 - 3);
        //cmptSAD27x27Dstnc3x3(myIndex, myDistance - aWidthx3);
        //cmptSAD27x27Dstnc3x3(myIndex, myDistance - aWidthx3 + 3);
        cmptSAD27x27Dstnc3x3(myIndex, myDistance - 3);
        cmptSAD27x27Dstnc3x3(myIndex, myDistance);
        cmptSAD27x27Dstnc3x3(myIndex, myDistance + 3);
        //cmptSAD27x27Dstnc3x3(myIndex, myDistance + aWidthx3 - 3);
        //cmptSAD27x27Dstnc3x3(myIndex, myDistance + aWidthx3);
        //cmptSAD27x27Dstnc3x3(myIndex, myDistance + aWidthx3 + 3);
    }

    public void cmptSAD27x27Dstnc3x3(int myIndex, int myDistance) {
            double sad3 = 0;
            double sad4 = 0;
            double sad5 = 0;
        /*
         if (!isInBounds3x3(myIndex)||!isInBounds3x3(myDistance)) {
         return;
         }
         */
        int aWidthx3 = getImageWidth();
        int xi1 = ImageTool.rtrvXPstn(myIndex, imgWidth);
        int yi1 = ImageTool.rtrvYPstn(myIndex, imgWidth);

        int xi2 = ImageTool.rtrvXPstn(myDistance, imgWidth);
        int yi2 = ImageTool.rtrvYPstn(myDistance, imgWidth);

        int xw = imgWidth - 1;
        int d1 = xw - xi1;
        int dpxi = xi2 + 27 / 2;
        //int dmxi  = xi2 - 243/2 - 27/2;
        int dmxi = xi2 - 27 / 2;

        //if (dmxi >= 0 && dpxi <= xw && yi1 == yi2) {
        if (dmxi >= 0 && dpxi <= xw && xi1 >=0 && yi1 == yi2) {
            //double sad0 = cmptSAD27x27(myIndex, myDistance - aWidthx3 - 1);
            //double sad1 = cmptSAD27x27(myIndex, myDistance - aWidthx3);
            //double sad2 = cmptSAD27x27(myIndex, myDistance - aWidthx3 + 1);
            sad3 = cmptSAD27x27(myIndex, myDistance - 1);
            sad4 = cmptSAD27x27(myIndex, myDistance);
            sad5 = cmptSAD27x27(myIndex, myDistance + 1);
            //double sad6 = cmptSAD27x27(myIndex, myDistance + aWidthx3 - 1);
            //double sad7 = cmptSAD27x27(myIndex, myDistance + aWidthx3);
            //double sad8 = cmptSAD27x27(myIndex, myDistance + aWidthx3 + 1);
            shouldDraw = true;
        
        /*
         if (sad0 < sad) {
         sad = sad0;
         indexValue = myDistance - aWidthx3 - 1;
         }
         if (sad1 < sad) {
         sad = sad1;
         indexValue = myDistance - aWidthx3;
         }
         if (sad2 < sad) {
         sad = sad2;
         indexValue = myDistance - aWidthx3 + 1;
         }*/

        if (sad3 < sad) {
            sad = sad3;
            matchingIndex = myDistance - 1;
        }
        if (myIndex == myDistance && sad4 <= sad) {
            sad = sad4;
            matchingIndex = myDistance;
        } else if (sad4 < sad) {
            sad = sad4;
            matchingIndex = myDistance;
        }
        if (sad5 < sad) {
            sad = sad5;
            matchingIndex = myDistance + 1;
        }
        //System.out.println("SADCornerRctfdFltr: sad3 = "+sad3+", sad4 = "+sad4+", sad5 = "+sad5+", sad = "+sad+", myDistance = "+myDistance+", matchingIndex = "+matchingIndex);        
        /*
         if (sad6 < sad) {
         sad = sad6;
         indexValue = myDistance + aWidthx3 - 1;
         }
         if (sad7 < sad) {
         sad = sad7;
         indexValue = myDistance + aWidthx3;
         }
         if (sad8 < sad) {
         sad = sad8;
         indexValue = myDistance + aWidthx3 + 1;
         }*/
        //System.out.println("SADCrnrFltr: sad0=" + sad0 + ",sad1=" + sad1 + ",sad2=" + sad2 + ",sad3=" + sad3 + ",sad4=" + sad4 + ",sad5=" + sad5 + ",sad6=" + sad6 + ",sad7=" + sad7 + ",sad8=" + sad8);
        //System.out.println("SADCrnrFltr: myDistance = "+myDistance+", indexValue =" + indexValue + ", sad = " + sad);
        }
    }

    public double cmptSAD27x27(int myIndex1, int myIndex2) {
        int aWidthx9 = 9 * getImageWidth();
        double total[] = new double[2];
        int xi1 = ImageTool.rtrvXPstn(myIndex1, imgWidth);
        int yi1 = ImageTool.rtrvYPstn(myIndex1, imgWidth);

        int xi2 = ImageTool.rtrvXPstn(myIndex2, imgWidth);
        int yi2 = ImageTool.rtrvYPstn(myIndex2, imgWidth);

        int xw = imgWidth - 1;
        int d1 = xw - xi1;
        int dpxi = xi2 + 27 / 2;
        //int dmxi  = xi2 - 243/2 - 27/2;
        int dmxi = xi2 - 27 / 2;

        //if (dmxi >= 0 && dpxi <= xw && yi1 == yi2) {
        if (dmxi >= 0 && dpxi <= xw && yi1 == yi2) {
            
        }        
        double sadTmp = cmptSAD9x9(myIndex1 - aWidthx9 - 9, myIndex2 - aWidthx9 - 9);
        sadTmp += cmptSAD9x9(myIndex1 - aWidthx9, myIndex2 - aWidthx9);
        sadTmp += cmptSAD9x9(myIndex1 - aWidthx9 + 9, myIndex2 - aWidthx9 + 9);
        sadTmp += cmptSAD9x9(myIndex1 - 9, myIndex2 - 9);
        sadTmp += cmptSAD9x9(myIndex1, myIndex2);
        sadTmp += cmptSAD9x9(myIndex1 + 9, myIndex2 + 9);
        sadTmp += cmptSAD9x9(myIndex1 + aWidthx9 - 9, myIndex2 + aWidthx9 - 9);
        sadTmp += cmptSAD9x9(myIndex1 + aWidthx9, myIndex2 + aWidthx9);
        sadTmp += cmptSAD9x9(myIndex1 + aWidthx9 + 9, myIndex2 + aWidthx9 + 9);
        //System.out.println("SADCornerFltr: myIndex1 = "+myIndex1+", myIndex2 = "+myIndex2+", sadTmp = "+sadTmp);
        return sadTmp;
    }

    public void cmptSAD9x9Dstnc9x9(int myIndex, int myDistance) {
        int aWidthx3 = 3 * getImageWidth();
        double total[] = new double[2];

        //cmptSAD9x9Dstnc3x3(myIndex,myDistance - aWidthx3 - 3);
        //cmptSAD9x9Dstnc3x3(myIndex,myDistance - aWidthx3);
        //cmptSAD9x9Dstnc3x3(myIndex,myDistance - aWidthx3 + 3);
        cmptSAD9x9Dstnc3x3(myIndex, myDistance - 3);
        cmptSAD9x9Dstnc3x3(myIndex, myDistance);
        cmptSAD9x9Dstnc3x3(myIndex, myDistance + 3);
        //cmptSAD9x9Dstnc3x3(myIndex,myDistance + aWidthx3 - 3);
        //cmptSAD9x9Dstnc3x3(myIndex,myDistance + aWidthx3);
        //cmptSAD9x9Dstnc3x3(myIndex,myDistance + aWidthx3 + 3);
    }

    public void cmptSAD9x9Dstnc3x3(int myIndex, int myDistance) {
        int aWidthx3 = getImageWidth();
        double total[] = new double[2];

        //double sad0 = cmptSAD9x9(myIndex,myDistance - aWidthx3 - 1);
        //double sad1 = cmptSAD9x9(myIndex,myDistance - aWidthx3);
        //double sad2 = cmptSAD9x9(myIndex,myDistance - aWidthx3 + 1);
        double sad3 = cmptSAD9x9(myIndex, myDistance - 1);
        double sad4 = cmptSAD9x9(myIndex, myDistance);
        double sad5 = cmptSAD9x9(myIndex, myDistance + 1);
        //double sad6 = cmptSAD9x9(myIndex,myDistance + aWidthx3 - 1);
        //double sad7 = cmptSAD9x9(myIndex,myDistance + aWidthx3);
        //double sad8 = cmptSAD9x9(myIndex,myDistance + aWidthx3 + 1);                                  

        /*
         if(sad0 <= sad)
         {
         sad = sad0;
         indexValue = myDistance - aWidthx3 - 1;
         }     
         if(sad1 <= sad)
         {
         sad = sad1;
         indexValue = myDistance - aWidthx3;
         }    
         if(sad2 <= sad)
         {
         sad = sad2;
         indexValue = myDistance - aWidthx3 + 1;
         }*/
        if (sad3 <= sad) {
            sad = sad3;
            matchingIndex = myDistance - 1;
        }
        if (sad4 <= sad) {
            sad = sad4;
            matchingIndex = myDistance;
        }
        if (sad5 <= sad) {
            sad = sad5;
            matchingIndex = myDistance + 1;
        }
        /*
         if(sad6 <= sad)
         {
         sad = sad6;
         indexValue = myDistance + aWidthx3 - 1;
         }    
         if(sad7 <= sad)
         {
         sad = sad7;
         indexValue = myDistance + aWidthx3;
         }  
         if(sad8 <= sad)
         {
         sad = sad8;
         indexValue = myDistance + aWidthx3 + 1;
         }*/
        //System.out.println("CrrltnMatchFltr: sad0="+sad0+",sad1="+sad1+",sad2="+sad2+",sad3="+sad3+",sad4="+sad4+",sad5="+sad5+",sad6="+sad6+",sad7="+sad7+",sad8="+sad8);
        //System.out.println("CrrltnMatchFltr: sad3="+sad3);        
    }

    public double cmptSAD9x9(int myIndex1, int myIndex2) {
        int aWidthx3 = 3 * getImageWidth();
        double total[] = new double[2];
        int sadTmp = cmptSAD(myIndex1 - aWidthx3 - 3, myIndex2 - aWidthx3 - 3);
        sadTmp += cmptSAD(myIndex1 - aWidthx3, myIndex2 - aWidthx3);
        sadTmp += cmptSAD(myIndex1 - aWidthx3 + 3, myIndex2 - aWidthx3 + 3);
        sadTmp += cmptSAD(myIndex1 - 3, myIndex2 - 3);
        sadTmp += cmptSAD(myIndex1, myIndex2);
        sadTmp += cmptSAD(myIndex1 + 3, myIndex2 + 3);
        sadTmp += cmptSAD(myIndex1 + aWidthx3 - 3, myIndex2 + aWidthx3 - 3);
        sadTmp += cmptSAD(myIndex1 + aWidthx3, myIndex2 + aWidthx3);
        sadTmp += cmptSAD(myIndex1 + aWidthx3 + 3, myIndex2 + aWidthx3 + 3);
        return sadTmp;
    }

    public int cmptSAD(int myIndex) {
        return cmptSAD(myIndex, myIndex);
    }
    public int cmptSAD(int myIndex1, int myIndex2) {
        int sad = 0;
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

            sad = Math.abs(tmprlGrdntTmp1) + Math.abs(tmprlGrdntTmp2)
                    + Math.abs(tmprlGrdntTmp3) + Math.abs(tmprlGrdntTmp4)
                    + Math.abs(tmprlGrdntTmp5) + Math.abs(tmprlGrdntTmp6)
                    + Math.abs(tmprlGrdntTmp7) + Math.abs(tmprlGrdntTmp8)
                    + Math.abs(tmprlGrdntTmp9);

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
            
             System.out.println("SADCornerFltr: sad = "+sad);   
             */
        }
        return sad;
    }

    public int getMatchingIndex() {
        return matchingIndex;
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