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
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;
import cgtjr.academics.math.lnralgbra.Matrix;

public class OpticalFlowFltr extends CornerDetect {

    private double crrntVelocity[][];
    private double prvsUntVlcty[][];
    private double prvsUntVlcty1[][];
    private double prvsUntVlcty2[][];
    private double unitVelocity[][];
    private int prevImgData[];
    private int crrntImgData[];
    private int imgWidth;
    private int firstFrame[];
    private int prvsData1[];
    private int prvsData2[];
    private int grayValues[];
    private double velocity[];
    private double untVlcty[];
    private double untVlctyTlt[];
    private double untVlctyAvrg[];
    private int nmbrOfCrnrs;
    private int frm1TmprlGrdntDiff;

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        int aLength = myImageHeight * myImageWidth;

        crrntImgData = new int[aLength];
        untVlctyTlt = new double[2];
        untVlctyAvrg = new double[2];
        nmbrOfCrnrs = 0;

        imgWidth = myImageWidth;

        grayValues = getGryVls();
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
    }

    public void bckGrndFrmFltr(int dataValues[], int i) {
        grdntFilter(dataValues, i);
        setCrrntImgData(dataValues, i);
        setFirstFrameData(dataValues, i);
        cmpt1stFrmTmprlGrdnt(i);
    }

    public void filter(int dataValues[], int i) {
        int x = ImageTool.rtrvXPstn(i, imgWidth);
        int y = ImageTool.rtrvYPstn(i, imgWidth);

        //System.out.println("OpticalFlowFltr: framenumber = " + frameIndex + ", i = " + i + ", x = " + x + ", y = " + y);
        //setCrrntImgData(dataValues, i);
        crrntImgData = dataValues;
        setPrevImgData(dataValues, i);
        if (frameIndex >= 1) {
            //cmptTmprlGrdnt(i);
            //setCrrntImgData(dataValues, i);
            //setPrevImgData(dataValues, i);
            cornerDetect9x9(dataValues, i);
            optclFlwFilter(dataValues, i);
        }
    }

    private void optclFlwFilter(int dataValues[], int i) {
        //Note should call super filter ?!

        int anX = rtrvXPstn(i);
        int anY = rtrvYPstn(i);

        int lclDmnsn = getLclWndwDmnsn();
        int lclWndwWdth = getGlblWndwWidth();

        int wndwX = anX / lclDmnsn - 1;
        int wndwY = anY / lclDmnsn - 1;
        int wndwIndx = ImageTool.rtrvIndex(wndwX, wndwY, lclWndwWdth);
        if (wndwX < 0 || wndwY < 0) {
            return;
        }

        int anIndex = getLclMaxIndex(wndwIndx);
        //System.out.println("OpticalFlwFltr: anX = "+anX+", anY="+anY+", wndwX = "+wndwX+", wndwY = "+wndwY+", lclDmsn = "+lclDmnsn+", wndwIndx = "+wndwIndx+", getLclMaxEgnVls()"+getEigenValue(anIndex));
        if (anX % lclDmnsn == 0 && anY % lclDmnsn == 0 && getEigenValue(anIndex) >= getThreshold()) {
            //System.out.println("OpticalFlowFltr:start ..................................................................");;
            //System.out.println("OpticalFlowFltr:framenumber = " + frameIndex + ",i=" + i + ",anotherIndex=" + anotherIndex);
            //this.cmptSptlGrdnt(anIndex);
            this.cmptFlow(anIndex);
            //drawArrows(anotherIndex);
            //System.out.println("OpticalFlowFltr:end ..................................................................");
        }
    }

    public void optclFlwFltr9x9(int dataValues[]) {
        int aLength = dataValues.length;
        for (int i = 0; i < aLength; i++) {
            optclFlwFltr9x9(dataValues, i);
        }
    }

    public void optclFlwFltr9x9(int dataValues[], int i) {
        filter3x3(dataValues, i);
        /*
         * setCrrntImgData(i); setPrevData(i);
         *
         * if(getEigenValue(i) >= getThreshold()) {
         * System.out.println("OpticalFlowFltr:start
         * ..................................................................");;
         * System.out.println("OpticalFlowFltr:framenumber =
         * "+frameIndex+",i="+i+",anotherIndex="+anotherIndex); double aTb9x9[]
         * = cmptATB9x9(i); double aMatrix[][] = getCornerMtrx(i); double
         * anInvrsMtrx[][] = Matrix.cmptInvrs2x2(aMatrix); double flow[] =
         * Matrix.mltply2x2x2x1(anInvrsMtrx, aTb9x9); unitVelocity[i] =
         * cmptUnitVctr2x1(flow); //drawArrows(anotherIndex);
         * System.out.println("OpticalFlowFltr:end
         * ..................................................................");
         * }
         */
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
    public double[] cmptATB9x9(int myIndex) {
        int aWidthx3 = 3 * getImageWidth();
        double total[] = new double[2];

        double sptlGrdnt0[][] = cmptSptlGrdnt(myIndex - aWidthx3 - 3);
        int tmprlGrdnt0[] = cmptTmprlGrdnt(myIndex - aWidthx3 - 3);
        double aTb0[] = Matrix.mltply9x2x9x1(sptlGrdnt0, tmprlGrdnt0);
        total = Matrix.add2x1x2x1(aTb0, total);
        //System.out.println("OpticalFlowFltr: aTb0[0] = " + aTb0[0] + ",aTb0[1]=" + aTb0[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt1[][] = cmptSptlGrdnt(myIndex - aWidthx3);
        int tmprlGrdnt1[] = cmptTmprlGrdnt(myIndex - aWidthx3);
        double aTb1[] = Matrix.mltply9x2x9x1(sptlGrdnt1, tmprlGrdnt1);
        total = Matrix.add2x1x2x1(aTb1, total);
        //System.out.println("OpticalFlowFltr: aTb1[0] = " + aTb1[0] + ",aTb1[1]=" + aTb1[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt2[][] = cmptSptlGrdnt(myIndex - aWidthx3 + 3);
        int tmprlGrdnt2[] = cmptTmprlGrdnt(myIndex - aWidthx3 + 3);
        double aTb2[] = Matrix.mltply9x2x9x1(sptlGrdnt2, tmprlGrdnt2);
        total = Matrix.add2x1x2x1(aTb2, total);
        //System.out.println("OpticalFlowFltr: aTb2[0] = " + aTb2[0] + ",aTb2[1]=" + aTb0[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt3[][] = cmptSptlGrdnt(myIndex - 3);
        int tmprlGrdnt3[] = cmptTmprlGrdnt(myIndex - 3);
        double aTb3[] = Matrix.mltply9x2x9x1(sptlGrdnt3, tmprlGrdnt3);
        total = Matrix.add2x1x2x1(aTb3, total);
        //System.out.println("OpticalFlowFltr: aTb3[0] = " + aTb3[0] + ",aTb3[1]=" + aTb3[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt4[][] = cmptSptlGrdnt(myIndex);
        int tmprlGrdnt4[] = cmptTmprlGrdnt(myIndex);
        double aTb4[] = Matrix.mltply9x2x9x1(sptlGrdnt4, tmprlGrdnt4);
        total = Matrix.add2x1x2x1(aTb4, total);
        //System.out.println("OpticalFlowFltr: aTb4[0] = " + aTb4[0] + ",aTb4[1]=" + aTb0[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt5[][] = cmptSptlGrdnt(myIndex + 3);
        int tmprlGrdnt5[] = cmptTmprlGrdnt(myIndex + 3);
        double aTb5[] = Matrix.mltply9x2x9x1(sptlGrdnt5, tmprlGrdnt5);
        total = Matrix.add2x1x2x1(aTb5, total);
        //System.out.println("OpticalFlowFltr: aTb5[0] = " + aTb5[0] + ",aTb5[1]=" + aTb5[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt6[][] = cmptSptlGrdnt(myIndex + aWidthx3 - 3);
        int tmprlGrdnt6[] = cmptTmprlGrdnt(myIndex + aWidthx3 - 3);
        double aTb6[] = Matrix.mltply9x2x9x1(sptlGrdnt6, tmprlGrdnt6);
        total = Matrix.add2x1x2x1(aTb6, total);
        //System.out.println("OpticalFlowFltr: aTb6[0] = " + aTb6[0] + ",aTb6[1]=" + aTb6[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt7[][] = cmptSptlGrdnt(myIndex + aWidthx3);
        int tmprlGrdnt7[] = cmptTmprlGrdnt(myIndex + aWidthx3);
        double aTb7[] = Matrix.mltply9x2x9x1(sptlGrdnt7, tmprlGrdnt7);
        total = Matrix.add2x1x2x1(aTb7, total);
        //System.out.println("OpticalFlowFltr: aTb7[0] = " + aTb7[0] + ",aTb7[1]=" + aTb7[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);

        double sptlGrdnt8[][] = cmptSptlGrdnt(myIndex + aWidthx3 + 3);
        int tmprlGrdnt8[] = cmptTmprlGrdnt(myIndex + aWidthx3 + 3);
        double aTb8[] = Matrix.mltply9x2x9x1(sptlGrdnt8, tmprlGrdnt8);
        total = Matrix.add2x1x2x1(aTb8, total);
        //System.out.println("OpticalFlowFltr: aTb8[0] = " + aTb8[0] + ",aTb8[1]=" + aTb8[1] + ", total[0]=" + total[0] + ", total[1]" + total[1]);
        return total;
    }
    public double[][] cmptSptlGrdnt(int myIndex) {
        int hrzntlGrdnt[] = getGrdntHrzntl();
        int vrtclGrdnt[] = getGrdntVrtcl();

        double spatialGrdnt[][] = new double[9][2];
        if (isInBounds3x3(myIndex)) {
            spatialGrdnt[0][0] = hrzntlGrdnt[myIndex - imgWidth - 1];
            spatialGrdnt[1][0] = hrzntlGrdnt[myIndex - imgWidth];
            spatialGrdnt[2][0] = hrzntlGrdnt[myIndex - imgWidth + 1];
            spatialGrdnt[3][0] = hrzntlGrdnt[myIndex - 1];
            spatialGrdnt[4][0] = hrzntlGrdnt[myIndex];
            spatialGrdnt[5][0] = hrzntlGrdnt[myIndex + 1];
            spatialGrdnt[6][0] = hrzntlGrdnt[myIndex + imgWidth - 1];
            spatialGrdnt[7][0] = hrzntlGrdnt[myIndex + imgWidth];
            spatialGrdnt[8][0] = hrzntlGrdnt[myIndex + imgWidth + 1];

            spatialGrdnt[0][1] = vrtclGrdnt[myIndex - imgWidth - 1];
            spatialGrdnt[1][1] = vrtclGrdnt[myIndex - imgWidth];
            spatialGrdnt[2][1] = vrtclGrdnt[myIndex - imgWidth + 1];
            spatialGrdnt[3][1] = vrtclGrdnt[myIndex - 1];
            spatialGrdnt[4][1] = vrtclGrdnt[myIndex];
            spatialGrdnt[5][1] = vrtclGrdnt[myIndex + 1];
            spatialGrdnt[6][1] = vrtclGrdnt[myIndex + imgWidth - 1];
            spatialGrdnt[7][1] = vrtclGrdnt[myIndex + imgWidth];
            spatialGrdnt[8][1] = vrtclGrdnt[myIndex + imgWidth + 1];

            /*
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex-imgWidth-1)+"]="+hrzntlGrdnt[myIndex-imgWidth-1]+"x,y=");
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex-imgWidth)+"]="+hrzntlGrdnt[myIndex-imgWidth]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex-imgWidth+1)+"]="+hrzntlGrdnt[myIndex-imgWidth+1]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex-1)+"]="+hrzntlGrdnt[myIndex-1]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex)+"]="+hrzntlGrdnt[myIndex]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex+1)+"]="+hrzntlGrdnt[myIndex+1]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex+imgWidth-1)+"]="+hrzntlGrdnt[myIndex+imgWidth-1]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex+imgWidth)+"]="+hrzntlGrdnt[myIndex+imgWidth]);
             * System.out.println("OpticalFlow:
             * hrzntlGrdnt["+(myIndex+imgWidth+1)+"]="+hrzntlGrdnt[myIndex+imgWidth+1]);
             *
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex-imgWidth-1)+"]="+vrtclGrdnt[myIndex-imgWidth-1]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex-imgWidth)+"]="+vrtclGrdnt[myIndex-imgWidth]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex-imgWidth+1)+"]="+vrtclGrdnt[myIndex-imgWidth+1]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex-1)+"]="+vrtclGrdnt[myIndex-1]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex)+"]="+vrtclGrdnt[myIndex]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex+1)+"]="+vrtclGrdnt[myIndex+1]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex+imgWidth-1)+"]="+vrtclGrdnt[myIndex+imgWidth-1]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex+imgWidth)+"]="+vrtclGrdnt[myIndex+imgWidth]);
             * System.out.println("OpticalFlow:
             * vrtclGrdnt["+(myIndex+imgWidth+1)+"]="+vrtclGrdnt[myIndex+imgWidth+1]);
             */
            //}catch(java.lang.ArrayIndexOutOfBoundsException aiobe){
            //aiobe.getCause();
        }
        return spatialGrdnt;
    }

    public void cmptLclPntFlw(int myIndex) {
    }

    public void cmptFlow(int myIndex) {
        if (isInBounds3x3(myIndex)) {
            double aMatrix[][] = getCornerMtrx(myIndex);
            double anInvrsMtrx[][] = Matrix.cmptInvrs2x2(aMatrix);
            double anATB[] = cmptATB9x9(myIndex);
            //double aSptlMatrix[][] = cmptSptlMatrix(anInvrsMtrx);
            //cmptTmprlGrdnt(myIndex);
            //Matrix.print(anInvrsMtrx);
            //Matrix.print(anATB);
            velocity = Matrix.mltply2x2x2x1(anInvrsMtrx, anATB);
            untVlcty = cmptUnitVctr2x1(velocity);
            untVlctyTlt[0] = untVlctyTlt[0] + untVlcty[0];
            untVlctyTlt[1] = untVlctyTlt[1] + untVlcty[1];
            nmbrOfCrnrs += 1;
            untVlctyAvrg[0] = untVlctyTlt[0] / nmbrOfCrnrs;
            untVlctyAvrg[1] = untVlctyTlt[1] / nmbrOfCrnrs;
            //System.out.println("OpticalFlwFltr: nmbrOfCrnrs = " + nmbrOfCrnrs + ", untVlctyTlt[" + 1 + "]=" + untVlctyTlt[1]);
            int aX = rtrvXPstn(myIndex);
            int aY = rtrvYPstn(myIndex);
            //System.out.println("OpticalFlowFltr: x = " + aX + ", y = " + aY + ", velocity = " + velocity[0] + "," + velocity[1]);
            //System.out.println("OpticalFlowFltr: x = " + aX + ", y = " + aY + ", unit vel. = " + untVlcty[0] + "," + untVlcty[1]);
            //System.out.println("OpticalFlowFltr: x = " + aX + ", y = " + aY + ", avrg unit vel. = " + untVlctyAvrg[0] + "," + untVlctyAvrg[1]);
        }
    }

    public double[] getVelcity() {
        return velocity;
    }

    public double[] getUntVlcty() {
        return untVlcty;
    }
    /*
     * public void cmptFlow(int myIndex) { if(isInBounds3x3(myIndex)) { double
     * aMatrix[][] = getCornerMtrx(myIndex); double anInvrsMtrx[][] =
     * Matrix.cmptInvrs2x2(aMatrix); //double aSptlMatrix[][] =
     * cmptSptlMatrix(anInvrsMtrx); //cmptTmprlGrdnt(myIndex);
     * cmptVelocity(aSptlMatrix, myIndex); } }
     */
    /*
     * public static double[] cmptInvrs2x2(double myMatrix[][]) { double
     * invrsMatrix[] = new double[4];
     *
     * double a11 = myMatrix[0][0]; double a12 = myMatrix[0][1]; double a21 =
     * myMatrix[1][0]; double a22 = myMatrix[1][1];
     *
     * double det = a11*a22-a12*a21;
     *
     * if(det == 0) { return invrsMatrix; } double b11 = a22/det; double b12 =
     * -a12/det; double b21 = -a21/det; double b22 = a11/det; invrsMatrix[0] =
     * b11; invrsMatrix[1] = b12; invrsMatrix[2] = b21; invrsMatrix[3] = b22;
     * return invrsMatrix; }
     */
    /*
     * public double[][] cmptSptlMtrx9x9(double myInvrsMatrix[]) { return null;
     * } public double[][] cmptSptlMatrix(double myInvrsMatrix[]) { return
     * cmptSptlMatrix(myInvrsMatrix,0); } public double[][]
     * cmptSptlMatrix(double myInvrsMatrix[],int myIndex) { double b11 =
     * myInvrsMatrix[0]; double b12 = myInvrsMatrix[1]; double b21 =
     * myInvrsMatrix[2]; double b22 = myInvrsMatrix[3];
     *
     * double aTaIaT[][] = new double[2][9];
     *
     * double aTaIaT11 = spatialGrdnt[0][0] * b11+ spatialGrdnt[0][1] * b12;
     * double aTaIaT12 = spatialGrdnt[1][0] * b11+ spatialGrdnt[1][1] * b12;
     * double aTaIaT13 = spatialGrdnt[2][0] * b11+ spatialGrdnt[2][1] * b12;
     * double aTaIaT14 = spatialGrdnt[3][0] * b11+ spatialGrdnt[3][1] * b12;
     * double aTaIaT15 = spatialGrdnt[4][0] * b11+ spatialGrdnt[4][1] * b12;
     * double aTaIaT16 = spatialGrdnt[5][0] * b11+ spatialGrdnt[5][1] * b12;
     * double aTaIaT17 = spatialGrdnt[6][0] * b11+ spatialGrdnt[6][1] * b12;
     * double aTaIaT18 = spatialGrdnt[7][0] * b11+ spatialGrdnt[7][1] * b12;
     * double aTaIaT19 = spatialGrdnt[8][0] * b11+ spatialGrdnt[8][1] * b12;
     *
     * double aTaIaT21 = spatialGrdnt[0][0] * b21+ spatialGrdnt[0][1] * b22;
     * double aTaIaT22 = spatialGrdnt[1][0] * b21+ spatialGrdnt[1][1] * b22;
     * double aTaIaT23 = spatialGrdnt[2][0] * b21+ spatialGrdnt[2][1] * b22;
     * double aTaIaT24 = spatialGrdnt[3][0] * b21+ spatialGrdnt[3][1] * b22;
     * double aTaIaT25 = spatialGrdnt[4][0] * b21+ spatialGrdnt[4][1] * b22;
     * double aTaIaT26 = spatialGrdnt[5][0] * b21+ spatialGrdnt[5][1] * b22;
     * double aTaIaT27 = spatialGrdnt[6][0] * b21+ spatialGrdnt[6][1] * b22;
     * double aTaIaT28 = spatialGrdnt[7][0] * b21+ spatialGrdnt[7][1] * b22;
     * double aTaIaT29 = spatialGrdnt[8][0] * b21+ spatialGrdnt[8][1] * b22;
     *
     * aTaIaT[0][0] = aTaIaT11; aTaIaT[0][1] = aTaIaT12; aTaIaT[0][2] =
     * aTaIaT13; aTaIaT[0][3] = aTaIaT14; aTaIaT[0][4] = aTaIaT15; aTaIaT[0][5]
     * = aTaIaT16; aTaIaT[0][6] = aTaIaT17; aTaIaT[0][7] = aTaIaT18;
     * aTaIaT[0][8] = aTaIaT19;
     *
     * aTaIaT[1][0] = aTaIaT21; aTaIaT[1][1] = aTaIaT22; aTaIaT[1][2] =
     * aTaIaT23; aTaIaT[1][3] = aTaIaT24; aTaIaT[1][4] = aTaIaT25; aTaIaT[1][5]
     * = aTaIaT26; aTaIaT[1][6] = aTaIaT27; aTaIaT[1][7] = aTaIaT28;
     * aTaIaT[1][8] = aTaIaT29;
     *
     * return aTaIaT; }
     */

    public void updateThreshold(int myDataValues[], int myIndex, int myThreshold) {
        myDataValues[myIndex] = (myDataValues[myIndex] < myThreshold) ? 0 : myDataValues[myIndex];
    }

    public int[] cmptTmprlGrdnt(int myIndex) {
        int tmprlGrdntTmp[] = new int[9];
        if (isInBounds3x3(myIndex)) {

            tmprlGrdntTmp[0] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth - 1]) - prevImgData[myIndex - imgWidth - 1];
            tmprlGrdntTmp[1] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth]) - prevImgData[myIndex - imgWidth];
            tmprlGrdntTmp[2] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth + 1]) - prevImgData[myIndex - imgWidth + 1];
            tmprlGrdntTmp[3] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - 1]) - prevImgData[myIndex - 1];
            tmprlGrdntTmp[4] = ColorSpectra.convertRGBToY(crrntImgData[myIndex]) - prevImgData[myIndex];
            tmprlGrdntTmp[5] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + 1]) - prevImgData[myIndex + 1];
            tmprlGrdntTmp[6] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth - 1]) - prevImgData[myIndex + imgWidth - 1];
            tmprlGrdntTmp[7] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth]) - prevImgData[myIndex + imgWidth];
            tmprlGrdntTmp[8] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth + 1]) - prevImgData[myIndex + imgWidth + 1];

            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth - 1]) + " - " + prevImgData[myIndex - imgWidth - 1]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth]) + " - " + prevImgData[myIndex - imgWidth]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth + 1]) + " - " + prevImgData[myIndex - imgWidth + 1]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - 1]) + " - " + prevImgData[myIndex - 1]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex]) + " - " + prevImgData[myIndex]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + 1]) + " - " + prevImgData[myIndex + 1]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth - 1]) + " - " + prevImgData[myIndex + imgWidth - 1]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth]) + " - " + prevImgData[myIndex + imgWidth]);
            //System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth + 1]) + " - " + prevImgData[myIndex + imgWidth + 1]);

            //System.out.println("tmprlGrdntTmp[" + 0 + "]=" + tmprlGrdntTmp[0]);
            //System.out.println("tmprlGrdntTmp[" + 1 + "]=" + tmprlGrdntTmp[1]);
            //System.out.println("tmprlGrdntTmp[" + 2 + "]=" + tmprlGrdntTmp[2]);
            //System.out.println("tmprlGrdntTmp[" + 3 + "]=" + tmprlGrdntTmp[3]);
            //System.out.println("tmprlGrdntTmp[" + 4 + "]=" + tmprlGrdntTmp[4]);
            //System.out.println("tmprlGrdntTmp[" + 5 + "]=" + tmprlGrdntTmp[5]);
            //System.out.println("tmprlGrdntTmp[" + 6 + "]=" + tmprlGrdntTmp[6]);
            //System.out.println("tmprlGrdntTmp[" + 7 + "]=" + tmprlGrdntTmp[7]);
            //System.out.println("tmprlGrdntTmp[" + 8 + "]=" + tmprlGrdntTmp[8]);

        }
        /*
         * updateThreshold(tmprlGrdnt,myIndex-imgWidth-1,0);
         * updateThreshold(tmprlGrdnt,myIndex-imgWidth,0);
         * updateThreshold(tmprlGrdnt,myIndex-imgWidth+1,0);
         * updateThreshold(tmprlGrdnt,myIndex-1,0);
         * updateThreshold(tmprlGrdnt,myIndex,0);
         * updateThreshold(tmprlGrdnt,myIndex+1,0);
         * updateThreshold(tmprlGrdnt,myIndex+imgWidth-1,0);
         * updateThreshold(tmprlGrdnt,myIndex+imgWidth,0);
         * updateThreshold(tmprlGrdnt,myIndex+imgWidth+1,0);
         */
        //System.out.println("famenumber "+frameIndex+"-------------------------------------------");
          /*
         * System.out.println("crrntImgData - prevImgData =
         * "+crrntImgData[myIndex-imgWidth-1]+" -
         * "+prevImgData[myIndex-imgWidth-1]); System.out.println("crrntImgData
         * - prevImgData = "+crrntImgData[myIndex-imgWidth]+" -
         * "+prevImgData[myIndex-imgWidth]); System.out.println("crrntImgData -
         * prevImgData = "+crrntImgData[myIndex-imgWidth+1]+" -
         * "+prevImgData[myIndex-imgWidth+1]); System.out.println("crrntImgData
         * - prevImgData = "+crrntImgData[myIndex-1]+" -
         * "+prevImgData[myIndex-1]); System.out.println("crrntImgData -
         * prevImgData = "+crrntImgData[myIndex]+" - "+prevImgData[myIndex]);
         * System.out.println("crrntImgData - prevImgData =
         * "+crrntImgData[myIndex+1]+" - "+prevImgData[myIndex+1]);
         * System.out.println("crrntImgData - prevImgData =
         * "+crrntImgData[myIndex+imgWidth-1]+" -
         * "+prevImgData[myIndex+imgWidth-1]); System.out.println("crrntImgData
         * - prevImgData = "+crrntImgData[myIndex+imgWidth]+" -
         * "+prevImgData[myIndex+imgWidth]); System.out.println("crrntImgData -
         * prevImgData = "+crrntImgData[myIndex+imgWidth+1]+" -
         * "+prevImgData[myIndex+imgWidth+1]);
         */
        /*
         * System.out.println("tmprlGrdntTmp["+(myIndex-imgWidth-1)+"]="+tmprlGrdntTmp[myIndex-imgWidth-1]);
         * System.out.println("tmprlGrdntTmp["+(myIndex-imgWidth)+"]="+tmprlGrdntTmp[myIndex-imgWidth]);
         * System.out.println("tmprlGrdntTmp["+(myIndex-imgWidth+1)+"]="+tmprlGrdntTmp[myIndex-imgWidth+1]);
         * System.out.println("tmprlGrdntTmp["+(myIndex-1)+"]="+tmprlGrdntTmp[myIndex-1]);
         * System.out.println("tmprlGrdntTmp["+(myIndex)+"]="+tmprlGrdntTmp[myIndex]);
         * System.out.println("tmprlGrdntTmp["+(myIndex+1)+"]="+tmprlGrdntTmp[myIndex+1]);
         * System.out.println("tmprlGrdntTmp["+(myIndex+imgWidth-1)+"]="+tmprlGrdntTmp[myIndex+imgWidth-1]);
         * System.out.println("tmprlGrdntTmp["+(myIndex+imgWidth)+"]="+tmprlGrdntTmp[myIndex+imgWidth]);
         * System.out.println("tmprlGrdntTmp["+(myIndex+imgWidth+1)+"]="+tmprlGrdntTmp[myIndex+imgWidth+1]);
         */



        //System.out.println("-------------------------------------------");
      /*
         *
         */
        return tmprlGrdntTmp;
    }

    public float getFrm1TmprGrdntDiff() {
        return frm1TmprlGrdntDiff;
    }

    public int cmpt1stFrmTmprlGrdnt(int myIndex) {
        frm1TmprlGrdntDiff = crrntImgData[myIndex] - firstFrame[myIndex];
        //System.out.println("OpticalFlwFltr: index = " + myIndex + ", frm1TmprlGrdntDiff = " + frm1TmprlGrdntDiff);
        return frm1TmprlGrdntDiff;
    }
    /*
     * public int[] cmptTmprlGrdnt(int crrntImgData[], int prevImgData[], int
     * myIndex) { int tmprlGrdntTmp[] = new int[9]; if (isInBounds3x3(myIndex))
     * {
     *
     * tmprlGrdntTmp[0] = crrntImgData[myIndex - imgWidth - 1] -
     * prevImgData[myIndex - imgWidth - 1]; tmprlGrdntTmp[1] =
     * crrntImgData[myIndex - imgWidth] - prevImgData[myIndex - imgWidth];
     * tmprlGrdntTmp[2] = crrntImgData[myIndex - imgWidth + 1] -
     * prevImgData[myIndex - imgWidth + 1]; tmprlGrdntTmp[3] =
     * crrntImgData[myIndex - 1] - prevImgData[myIndex - 1]; tmprlGrdntTmp[4] =
     * crrntImgData[myIndex] - prevImgData[myIndex]; tmprlGrdntTmp[5] =
     * crrntImgData[myIndex + 1] - prevImgData[myIndex + 1]; tmprlGrdntTmp[6] =
     * crrntImgData[myIndex + imgWidth - 1] - prevImgData[myIndex + imgWidth -
     * 1]; tmprlGrdntTmp[7] = crrntImgData[myIndex + imgWidth] -
     * prevImgData[myIndex + imgWidth]; tmprlGrdntTmp[8] = crrntImgData[myIndex
     * + imgWidth + 1] - prevImgData[myIndex + imgWidth + 1];
     *
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * - imgWidth - 1] + " - " + prevImgData[myIndex - imgWidth - 1]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * - imgWidth] + " - " + prevImgData[myIndex - imgWidth]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * - imgWidth + 1] + " - " + prevImgData[myIndex - imgWidth + 1]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * - 1] + " - " + prevImgData[myIndex - 1]);
     * System.out.println("crrntImgData - prevImgData = " +
     * crrntImgData[myIndex] + " - " + prevImgData[myIndex]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * + 1] + " - " + prevImgData[myIndex + 1]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * + imgWidth - 1] + " - " + prevImgData[myIndex + imgWidth - 1]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * + imgWidth] + " - " + prevImgData[myIndex + imgWidth]);
     * System.out.println("crrntImgData - prevImgData = " + crrntImgData[myIndex
     * + imgWidth + 1] + " - " + prevImgData[myIndex + imgWidth + 1]);
     *
     * System.out.println("tmprlGrdnt[" + (myIndex - imgWidth - 1) + "]=" +
     * tmprlGrdnt[myIndex - imgWidth - 1]); System.out.println("tmprlGrdnt[" +
     * (myIndex - imgWidth) + "]=" + tmprlGrdnt[myIndex - imgWidth]);
     * System.out.println("tmprlGrdnt[" + (myIndex - imgWidth + 1) + "]=" +
     * tmprlGrdnt[myIndex - imgWidth + 1]); System.out.println("tmprlGrdnt[" +
     * (myIndex - 1) + "]=" + tmprlGrdnt[myIndex - 1]);
     * System.out.println("tmprlGrdnt[" + (myIndex) + "]=" +
     * tmprlGrdnt[myIndex]); System.out.println("tmprlGrdnt[" + (myIndex + 1) +
     * "]=" + tmprlGrdnt[myIndex + 1]); System.out.println("tmprlGrdnt[" +
     * (myIndex + imgWidth - 1) + "]=" + tmprlGrdnt[myIndex + imgWidth - 1]);
     * System.out.println("tmprlGrdnt[" + (myIndex + imgWidth) + "]=" +
     * tmprlGrdnt[myIndex + imgWidth]); System.out.println("tmprlGrdnt[" +
     * (myIndex + imgWidth + 1) + "]=" + tmprlGrdnt[myIndex + imgWidth + 1]);
     *
     * }
     * return tmprlGrdntTmp; }
     */

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
    /*
     * public void cmptVelocity(double myATaIaT[][],int myIndex) { double
     * aTaIaT11 = myATaIaT[0][0]; double aTaIaT12 = myATaIaT[0][1]; double
     * aTaIaT13 = myATaIaT[0][2]; double aTaIaT14 = myATaIaT[0][3]; double
     * aTaIaT15 = myATaIaT[0][4]; double aTaIaT16 = myATaIaT[0][5]; double
     * aTaIaT17 = myATaIaT[0][6]; double aTaIaT18 = myATaIaT[0][7]; double
     * aTaIaT19 = myATaIaT[0][8];
     *
     * double aTaIaT21 = myATaIaT[1][0]; double aTaIaT22 = myATaIaT[1][1];
     * double aTaIaT23 = myATaIaT[1][2]; double aTaIaT24 = myATaIaT[1][3];
     * double aTaIaT25 = myATaIaT[1][4]; double aTaIaT26 = myATaIaT[1][5];
     * double aTaIaT27 = myATaIaT[1][6]; double aTaIaT28 = myATaIaT[1][7];
     * double aTaIaT29 = myATaIaT[1][8];
     *
     * //System.out.println("OpticalFlow: frameIndex ="+frameIndex+",
     * crrntImgData["+myIndex+"]="+crrntImgData[myIndex]+",prevImgData["+myIndex+"]="+prevImgData[myIndex]+",tmprlGrdnt["+myIndex+"]="+tmprlGrdnt[myIndex]);
     *
     * crrntVelocity[myIndex][0] = aTaIaT11*tmprlGrdnt[myIndex-imgWidth-1]+
     * aTaIaT12*tmprlGrdnt[myIndex-imgWidth] +
     * aTaIaT13*tmprlGrdnt[myIndex-imgWidth+1]+ aTaIaT14*tmprlGrdnt[myIndex-1] +
     * aTaIaT15*tmprlGrdnt[myIndex] + aTaIaT16*tmprlGrdnt[myIndex+1] +
     * aTaIaT17*tmprlGrdnt[myIndex+imgWidth-1]+
     * aTaIaT18*tmprlGrdnt[myIndex+imgWidth] +
     * aTaIaT19*tmprlGrdnt[myIndex+imgWidth+1];
     *
     * crrntVelocity[myIndex][1] = aTaIaT21*tmprlGrdnt[myIndex-imgWidth-1]+
     * aTaIaT22*tmprlGrdnt[myIndex-imgWidth] +
     * aTaIaT23*tmprlGrdnt[myIndex-imgWidth+1]+ aTaIaT24*tmprlGrdnt[myIndex-1] +
     * aTaIaT25*tmprlGrdnt[myIndex] + aTaIaT26*tmprlGrdnt[myIndex+1] +
     * aTaIaT27*tmprlGrdnt[myIndex+imgWidth-1]+
     * aTaIaT28*tmprlGrdnt[myIndex+imgWidth] +
     * aTaIaT29*tmprlGrdnt[myIndex+imgWidth+1];
     *
     * double vlctyMgntd =
     * Math.sqrt(crrntVelocity[myIndex][0]*crrntVelocity[myIndex][0]+
     * crrntVelocity[myIndex][1]*crrntVelocity[myIndex][1]); double unitVlctyX =
     * crrntVelocity[myIndex][0]/vlctyMgntd; double unitVlctyY =
     * crrntVelocity[myIndex][1]/vlctyMgntd;
     *
     * unitVelocity[myIndex][0] = unitVlctyX; unitVelocity[myIndex][1] =
     * unitVlctyY;
     *
     * System.out.println("OpticalFlowFltr:
     * vlctyMgntd="+vlctyMgntd+",crrntVelocity["+myIndex+"][0]="+crrntVelocity[myIndex][0]+",crrntVelocity["+myIndex+"][1]="+crrntVelocity[myIndex][1]);
     * System.out.println("OpticalFlowFltr:
     * unitVlctyX="+unitVlctyX+",unitVlctyY="+unitVlctyY);
     * //if(unitVelocity[myIndex][0] <= 1) unitVelocity[myIndex][0] = 0;
     * //if(unitVelocity[myIndex][1] <= 1) unitVelocity[myIndex][1] = 0;
     *
     * setPrvsUntVlcty(unitVelocity,myIndex); //} }
     */

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
     * public int[] getFltrdData() { return this.crrntImgData; }
     */

    public void startPrsng() {
    }

    public void finish() {
        //System.out.println("Optical Flow : finish parsing frame = " + frameIndex);
        prevImgData = getPrvsData();
        prvsUntVlcty = getPrvsUntVlcty();
    }
}