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
import cgtjr.academics.general.ImageTool;
import cgtjr.academics.general.ColorSpectra;

public class TmprlGrdntFltr extends YSclFltr {

    private int prevImgData[];
    //private int crrntImgData[];
    private int imgWidth;
    private int firstFrame[];
    private int prvsData1[];
    private int prvsData2[];
    //private int grayValues[];
    private int frm1TmprlGrdntDiff;
    private int tmprlGrdntValue;
    private double tmprlGrdntThrshld = 25;
    private int temporalGradientValues[];
    private int aLength;
    

    public void initialize(int myImageWidth, int myImageHeight) {
        super.initialize(myImageWidth, myImageHeight);
        this.intlzeTmprlGrndnt(myImageWidth, myImageHeight);        
    }
    public void intlzeTmprlGrndnt(int myImageWidth, int myImageHeight) {
        aLength = myImageHeight * myImageWidth;
        imgWidth = myImageWidth;
        //tmprlGrdntThrshld = 25;
        
        //if(crrntImgData == null){
            //crrntImgData = new int[aLength];
        //}
        
        /*
        if (prevImgData == null) {
            prevImgData = new int[aLength];
        }*/
        
        if (firstFrame == null) {
            //May use for initializing a permanent background
            firstFrame = new int[aLength];
        }
        if (prvsData1 == null) {
            prvsData1 = new int[aLength];
        }
        if (prvsData2 == null) {
            prvsData2 = new int[aLength];
            prevImgData = prvsData2;
        }
        if(temporalGradientValues == null){
            temporalGradientValues = new int[aLength];            
        }
    }    
    public void setTemporalGradientValues(int myIndex,int myValue){
        temporalGradientValues[myIndex] = (short)myValue;
    }
    public int getTemporalGradientValues(int myIndex){
        return temporalGradientValues[myIndex];
    }
    public int[] getTemporalGradientValues(){
        return temporalGradientValues;
    }
    public void setTmprlGrdntValue(int myTmprlGrdntValue){
        tmprlGrdntValue = myTmprlGrdntValue;
    }
    public int getTmprlGrdntValue(){
        return tmprlGrdntValue;
    }
    public void setTmprlGrdntThrshld(double myTmprlGrdntThrshld){
        tmprlGrdntThrshld = myTmprlGrdntThrshld;
    }
    public double getTmprlGrdntThrshld(){
        return tmprlGrdntThrshld;
    }   
    /*
    public void bckGrndFrmFltr(int dataValues[], int i) {
        //grdntFilter(dataValues, i);
        setCrrntImgData(dataValues, i);
        setFirstFrameData(dataValues, i);
        cmpt1stFrmTmprlGrdnt(i);
    }*/
    public void tmprlFilter(int dataValues[], int i){

        if (isInBounds3x3(i)) {


        //setCrrntImgData(dataValues, i);
        //These two lines should be in gray scale converter function
        //crrntImgData = dataValues;

        if (frameIndex > 0) {
            //System.out.println("TmprGrdntFltr: framenumber = " + frameIndex + ", i = " + i + ", x = " + x + ", y = " + y);
            if(i>8*getImageWidth()&&i<aLength-8*getImageWidth()){      
               cmptTmprlGrdntSSD(i);
            }      
           }
        }        
        setPrevImgData(i);
    }
    public void filter(int dataValues[], int i) {
        super.filter(dataValues, i);                
        tmprlFilter(dataValues,i);
    }
    private void setFirstFrameData(int myValues[], int i) {
        //System.out.println("OpticalFlowFltr: frameIndex = "+frameIndex+", i = "+i);
        if (frameIndex == 3) {
            firstFrame[i] = myValues[i];
            //System.out.println("OpticalFlowFltr: setting first frame = " + frameIndex + ", data i = " + i);
        }
    }
    /*
    public void setCrrntImgData(int i) {
        crrntImgData[i] = grayValues[i];
    }
    public void setCrrntImgData(int myValues[], int i) {
        crrntImgData[i] = ColorSpectra.convertRGBToY(myValues[i]);
    }*/
    /*
    public void updateThreshold(int myDataValues[], int myIndex, int myThreshold) {
        myDataValues[myIndex] = (myDataValues[myIndex] < myThreshold) ? 0 : myDataValues[myIndex];
    }*/
    /*
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
        return tmprlGrdntTmp;
    }*/

    public double cmptTmprlGrdntSSD(int myIndex) {
        double ssd = -Float.MAX_VALUE;
        int tmprlGrdntTmp[] = new int[9];
        //if (isInBounds3x3(myIndex)) {

            tmprlGrdntTmp[0] = getGryVls(myIndex - imgWidth - 1) - prevImgData[myIndex - imgWidth - 1];
            tmprlGrdntTmp[1] = getGryVls(myIndex - imgWidth) - prevImgData[myIndex - imgWidth];
            tmprlGrdntTmp[2] = getGryVls(myIndex - imgWidth + 1) - prevImgData[myIndex - imgWidth + 1];
            tmprlGrdntTmp[3] = getGryVls(myIndex - 1) - prevImgData[myIndex - 1];
            tmprlGrdntTmp[4] = getGryVls(myIndex) - prevImgData[myIndex];
            tmprlGrdntTmp[5] = getGryVls(myIndex + 1) - prevImgData[myIndex + 1];
            tmprlGrdntTmp[6] = getGryVls(myIndex + imgWidth - 1) - prevImgData[myIndex + imgWidth - 1];
            tmprlGrdntTmp[7] = getGryVls(myIndex + imgWidth) - prevImgData[myIndex + imgWidth];
            tmprlGrdntTmp[8] = getGryVls(myIndex + imgWidth + 1) - prevImgData[myIndex + imgWidth + 1];
            
            
        
            
            /*
            tmprlGrdntTmp[0] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth - 1]) - prevImgData[myIndex - imgWidth - 1];
            tmprlGrdntTmp[1] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth]) - prevImgData[myIndex - imgWidth];
            tmprlGrdntTmp[2] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth + 1]) - prevImgData[myIndex - imgWidth + 1];
            tmprlGrdntTmp[3] = ColorSpectra.convertRGBToY(crrntImgData[myIndex - 1]) - prevImgData[myIndex - 1];
            tmprlGrdntTmp[4] = ColorSpectra.convertRGBToY(crrntImgData[myIndex]) - prevImgData[myIndex];
            tmprlGrdntTmp[5] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + 1]) - prevImgData[myIndex + 1];
            tmprlGrdntTmp[6] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth - 1]) - prevImgData[myIndex + imgWidth - 1];
            tmprlGrdntTmp[7] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth]) - prevImgData[myIndex + imgWidth];
            tmprlGrdntTmp[8] = ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth + 1]) - prevImgData[myIndex + imgWidth + 1];
            */
            /*
            System.out.println("TmprlGrdntFltr: frameIndex = "+frameIndex);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth - 1]) + " - " + prevImgData[myIndex - imgWidth - 1]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth]) + " - " + prevImgData[myIndex - imgWidth]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - imgWidth + 1]) + " - " + prevImgData[myIndex - imgWidth + 1]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex - 1]) + " - " + prevImgData[myIndex - 1]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex]) + " - " + prevImgData[myIndex]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + 1]) + " - " + prevImgData[myIndex + 1]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth - 1]) + " - " + prevImgData[myIndex + imgWidth - 1]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth]) + " - " + prevImgData[myIndex + imgWidth]);
            System.out.println("crrntImgData - prevImgData = " + ColorSpectra.convertRGBToY(crrntImgData[myIndex + imgWidth + 1]) + " - " + prevImgData[myIndex + imgWidth + 1]);
            */
            //System.out.println("tmprlGrdntTmp[" + 0 + "]=" + tmprlGrdntTmp[0]);
            //System.out.println("tmprlGrdntTmp[" + 1 + "]=" + tmprlGrdntTmp[1]);
            //System.out.println("tmprlGrdntTmp[" + 2 + "]=" + tmprlGrdntTmp[2]);
            //System.out.println("tmprlGrdntTmp[" + 3 + "]=" + tmprlGrdntTmp[3]);
            //System.out.println("tmprlGrdntTmp[" + 4 + "]=" + tmprlGrdntTmp[4]);
            //System.out.println("tmprlGrdntTmp[" + 5 + "]=" + tmprlGrdntTmp[5]);
            //System.out.println("tmprlGrdntTmp[" + 6 + "]=" + tmprlGrdntTmp[6]);
            //System.out.println("tmprlGrdntTmp[" + 7 + "]=" + tmprlGrdntTmp[7]);
            //System.out.println("tmprlGrdntTmp[" + 8 + "]=" + tmprlGrdntTmp[8]);
            ssd = tmprlGrdntTmp[0]*tmprlGrdntTmp[0]+tmprlGrdntTmp[1]*tmprlGrdntTmp[1]+tmprlGrdntTmp[2]*tmprlGrdntTmp[2]+tmprlGrdntTmp[3]*tmprlGrdntTmp[3]+
            tmprlGrdntTmp[4]*tmprlGrdntTmp[4]+tmprlGrdntTmp[5]*tmprlGrdntTmp[5]+tmprlGrdntTmp[6]*tmprlGrdntTmp[6]+tmprlGrdntTmp[7]*tmprlGrdntTmp[7];
            tmprlGrdntValue = (int)Math.sqrt(ssd);
            temporalGradientValues[myIndex] = tmprlGrdntValue;
            //System.out.println("temporalGradientValues[myIndex] = (short)tmprlGrdntValue"+tmprlGrdntValue);            
        //}

        //System.out.println("TmprlGrdntFltr : Math.sqrt(ssd) = "+Math.sqrt(ssd)+", tmprlGrdntValue = "+tmprlGrdntValue+", temporalGradientValues["+myIndex+"]="+temporalGradientValues[myIndex]);
        return ssd;
    }
    public float getFrm1TmprGrdntDiff() {
        return frm1TmprlGrdntDiff;
    }
    public int cmpt1stFrmTmprlGrdnt(int myIndex) {
        //frm1TmprlGrdntDiff = crrntImgData[myIndex] - firstFrame[myIndex];
        //System.out.println("OpticalFlwFltr: index = " + myIndex + ", frm1TmprlGrdntDiff = " + frm1TmprlGrdntDiff);
        //return frm1TmprlGrdntDiff;
        return 0;
    }
    private void setPrevImgData(int i) {
        if (frameIndex % 2 == 0) {
            prvsData2[i] = getGryVls(i);
        } else {
            prvsData1[i] = getGryVls(i);
        }
    }
    /*
    public void setPrevImgData(int grayValues[], int i) {
        if (frameIndex % 2 == 0) {
            prvsData2[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        } else {
            prvsData1[i] = ColorSpectra.convertRGBToY(grayValues[i]);
        }
    }*/
    private int[] getPrvsData() {
        if (frameIndex % 2 == 0) {
            return prvsData2;
        } else {
            return prvsData1;
        }
    }
    /*
    public void setFrameIndex(int myFrameIndex) {
        frameIndex = myFrameIndex;
    }
    public int getFrameIndex() {
        //Ensure index starts at zero
        return frameIndex;
    }*/
    public void incrmtFrmIndex() {
        frameIndex++;
    }
    public void dcrmntFrmIndex() {
        frameIndex--;
    }
    public void startPrsng() {
    }
    public void finish() {
        super.finish();
        //System.out.println("Optical Flow : finish parsing frame = " + frameIndex);
        
    }
    public void finishTmprlGrdnt(){
        prevImgData = getPrvsData();
    } 
}