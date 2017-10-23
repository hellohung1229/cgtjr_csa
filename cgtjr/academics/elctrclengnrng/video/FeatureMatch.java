/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.elctrclengnrng.video;

/**
 *
 * @author clayton g thomas jr
 */


public class FeatureMatch extends OpticalFlowFltr {

   private int blockSize;
   private int patch1[];
   private int patch2[];
   private int patchTmp[];
   private int displacement[][];
   private Object dsplcmntFrm[];
   private int intlDsplcmnt[];
   private double threshold;
   private int prvsData[];
   private int maxDsplcmnt;

   
   public FeatureMatch()
   {
      threshold = 1;

      //patch1 = new int[blockSize*blockSize];
      //patch2 = new int[blockSize*blockSize];
      //patchTmp = new int[blockSize*blockSize];
   }
   public void initialize(int myImageWidth, int myImageHeight)
   {
      super.initialize(myImageWidth, myImageHeight);
      displacement = new int[myImageHeight*myImageWidth][2];
      if(dsplcmntFrm == null)
      {
         dsplcmntFrm = new Object[myImageHeight*myImageWidth];
      }
      dsplcmntFrm[getFrameNumber()] = displacement;
   }
   public void FtrMtchFilter(int grayValues[],int myIndex)
   {
      int imageWidth2 = 2*getImageWidth();
      int anIndex = 0;
      
      if(myIndex-imageWidth2+2>=0)
      {
         anIndex = myIndex-imageWidth2+2;
         patchFilter(grayValues,myIndex);
      }
   }
   public int[] cmptPatch1(int myIndex)
   {
      int imageData[] = getPrvsData();
      return cmptPatch(imageData,myIndex);
   }
   public int[] cmptPatch(int grayValues[],int myIndex)
   {
      int imgWidth = getImageWidth();
      if(isInBounds3x3(myIndex))
      {
         patch1[0] = grayValues[myIndex-imgWidth-1];
         patch1[1] = grayValues[myIndex-imgWidth];
         patch1[2] = grayValues[myIndex-imgWidth+1];
         patch1[3] = grayValues[myIndex-1];
         patch1[4] = grayValues[myIndex];
         patch1[5] = grayValues[myIndex+1];
         patch1[6] = grayValues[myIndex-imgWidth-1];
         patch1[7] = grayValues[myIndex+imgWidth];
         patch1[8] = grayValues[myIndex+imgWidth+1];
      }
      return patch1;
   }
   /*
   public int[] cmptPatch2(int grayValues[],int myIndex)
   {
      int imgWidth = getImageWidth();
      if(isInBounds3x3(myIndex))
      {
         patchTmp[0] = grayValues[myIndex-imgWidth-1];
         patchTmp[1] = grayValues[myIndex-imgWidth];
         patchTmp[2] = grayValues[myIndex-imgWidth+1];
         patchTmp[3] = grayValues[myIndex-1];
         patchTmp[4] = grayValues[myIndex];
         patchTmp[5] = grayValues[myIndex+1];
         patchTmp[6] = grayValues[myIndex-imgWidth-1];
         patchTmp[7] = grayValues[myIndex+imgWidth];
         patchTmp[8] = grayValues[myIndex+imgWidth+1];
      }
      return patchTmp;
   }*/
   public void patchFilter(int grayValues[],int myIndex)
   {
      double aSum = 0;
      int prvsImageData[] = getPrvsData();
      double aVelocity[][] = getPrvsUntVlcty();
      int aDisplacement[] = null;
      int anIndex = 0;
      //int aPatchTmp[] = null;
      //int aPatch2[] = null;
      int xDsplmnt = 0;
      int yDsplmnt = 0;

      patch1 = cmptPatch(prvsImageData,myIndex);

      do{
         aDisplacement = cmptDisplacement(aVelocity,myIndex);
         anIndex = cmptIndex(aDisplacement);
         patch2 = cmptPatch(grayValues,anIndex);
         copyPatch(patchTmp,patch1);
         aSum = sumSqurdDffrnc(patch2,patchTmp);
         copyPatch(patch1,patchTmp);
         myIndex = anIndex;
         maxDsplcmnt++;
         xDsplmnt += aDisplacement[0];
         yDsplmnt += aDisplacement[1];
      }while(aSum > threshold && maxDsplcmnt <= 1);
      displacement[myIndex][0] = xDsplmnt;
      displacement[myIndex][1] = yDsplmnt;

   }
   public void copyPatch(int myPatch1[],int myPatch2[])
   {
      myPatch1[0] = myPatch2[0];
      myPatch1[1] = myPatch2[1];
      myPatch1[2] = myPatch2[2];
      myPatch1[3] = myPatch2[3];
      myPatch1[4] = myPatch2[4];
      myPatch1[5] = myPatch2[5];
      myPatch1[6] = myPatch2[6];
      myPatch1[7] = myPatch2[7];
      myPatch1[8] = myPatch2[8];
   }
   public int[] cmptDisplacement(int myIndex)
   {
      double aVelocity[][] = getPrvsUntVlcty();
      return cmptDisplacement(aVelocity,myIndex);
   }
   public int[] cmptDisplacement(double myUnitVlcty[][],int myIndex)
   {
      int aDisplacement[] = new int[2];
      int aWidth = getImageWidth();
      int initialX = myIndex % aWidth;
      int initialY = myIndex / aWidth;
      double unitX = myUnitVlcty[myIndex][0];
      double unitY = myUnitVlcty[myIndex][1];
      int distanceX = (int)unitX*blockSize;
      int distanceY = (int)unitY*blockSize;
      int finalX = initialX + distanceX;
      int finalY = initialY + distanceY;

      aDisplacement[0] = finalX;
      aDisplacement[1] = finalY;

      return aDisplacement;
   }
   public int cmptIndex(int myDisplacement[])
   {
      int aX = myDisplacement[0];
      int aY = myDisplacement[1];
      int aWidth = getImageWidth();
      int anIndex = aY * aWidth + aX;
      return anIndex;
   }
   public double sumSqurdDffrnc(int myPatch1[],int myPatchTmp[])
   {
      int aSum = (myPatch1[0]*myPatch1[0] - myPatchTmp[0]*myPatchTmp[0])+
                 (myPatch1[1]*myPatch1[1] - myPatchTmp[1]*myPatchTmp[1])+
                 (myPatch1[2]*myPatch1[2] - myPatchTmp[2]*myPatchTmp[2])+
                 (myPatch1[3]*myPatch1[3] - myPatchTmp[3]*myPatchTmp[3])+
                 (myPatch1[4]*myPatch1[4] - myPatchTmp[4]*myPatchTmp[4])+
                 (myPatch1[5]*myPatch1[5] - myPatchTmp[5]*myPatchTmp[5])+
                 (myPatch1[6]*myPatch1[6] - myPatchTmp[6]*myPatchTmp[6])+
                 (myPatch1[7]*myPatch1[7] - myPatchTmp[7]*myPatchTmp[7])+
                 (myPatch1[8]*myPatch1[8] - myPatchTmp[8]*myPatchTmp[8]);
      return aSum;
   }
}