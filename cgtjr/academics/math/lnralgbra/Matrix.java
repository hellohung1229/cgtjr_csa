/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cgtjr.academics.math.lnralgbra;


/**
 * @author clayton g thomas jr
 */
public class Matrix {

   public static double[] copy(double myMatrix[])
   {
      int aLength = myMatrix.length;
      double aMatrix[] = new double[aLength];
      for(int i=0;i<aLength;i++)
      {
         aMatrix[i] = myMatrix[i];
      }
      return aMatrix;
   }
   public static double[][] copy(double myMatrix[][])
   {
       return copy(myMatrix,1);
   }
   public static double[][] copy(double myMatrix[][],double aScale)
   {
      double aMatrix[][] = new double[myMatrix.length][myMatrix[0].length];
      for(int i=0;i<myMatrix.length;i++)
      {
         for(int j=0;j<myMatrix[0].length;j++)
         {
            aMatrix[i][j] = aScale*myMatrix[i][j];
         }
      }
      return aMatrix;
   }
   public static double[][] copy8x3(double myMatrix[][],double myScale)
   {
      double aMatrix[][] = new double[8][3];
      for(int i=0;i<myMatrix.length;i++)
      {
         for(int j=0;j<myMatrix[0].length;j++)
         {
            aMatrix[i][j] = myScale*myMatrix[i][j];
         }
      }
      aMatrix[0][0] = myScale*myMatrix[0][0];

      return aMatrix;
   }
   public static float[][] copy(float myMatrix[][])
   {
      int aLength = myMatrix.length;
      float aMatrix[][] = new float[aLength][aLength];
      for(int i=0;i<myMatrix.length;i++)
      {
         for(int j=0;j<myMatrix[0].length;j++)
         {
            aMatrix[i][j] = myMatrix[i][j];
         }
      }
      aMatrix[0][0] = myMatrix[0][0];

      return aMatrix;
   }
   public static double[] scale3x1(double myMatrix[],double myScale)
   {
      double aMatrix[] = new double[3];
      aMatrix[0] = myScale*myMatrix[0];
      aMatrix[1] = myScale*myMatrix[1];
      aMatrix[2] = myScale*myMatrix[2];
      return aMatrix;
   }
   public static double[] scale1x4(double myMatrix[],double myScale)
   {
      double aMatrix[] = new double[4];
      aMatrix[0] = myScale*myMatrix[0];
      aMatrix[1] = myScale*myMatrix[1];
      aMatrix[2] = myScale*myMatrix[2];
      aMatrix[3] = myScale*myMatrix[3];      
      return aMatrix;
   }
   public static double[][] scale(double myMatrix[][],double myScale)
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = myScale*myMatrix[0][0];
      aMatrix[1][0] = myScale*myMatrix[1][0];
      aMatrix[2][0] = myScale*myMatrix[2][0];
      aMatrix[0][1] = myScale*myMatrix[0][1];
      aMatrix[1][1] = myScale*myMatrix[1][1];
      aMatrix[2][1] = myScale*myMatrix[2][1];
      aMatrix[0][2] = myScale*myMatrix[0][2];
      aMatrix[1][2] = myScale*myMatrix[1][2];
      aMatrix[2][2] = myScale*myMatrix[2][2];
      return aMatrix;
   }
   //public static double[][] cmptMTM(double myMatrix)
   public static double[][] rtrvRttnMtrx()
   {
      double matrix1[][] = new double[3][3];
      matrix1[0][0] = 0;
      matrix1[0][1] = -1;
      matrix1[0][2] = 0;
      matrix1[1][0] = 1;
      matrix1[1][1] = 0;
      matrix1[1][2] = 0;
      matrix1[2][0] = 0;
      matrix1[2][1] = 0;
      matrix1[2][2] = 1;
      return matrix1;
   }
   public static double[][] rtrvRttnMtrx(double myTheta)
   {
      double matrix1[][] = new double[3][3];
      matrix1[0][0] = Math.cos(myTheta);
      matrix1[0][1] = -Math.sin(myTheta);
      matrix1[0][2] = 0;
      matrix1[1][0] = Math.sin(myTheta);
      matrix1[1][1] = Math.cos(myTheta);
      matrix1[1][2] = 0;
      matrix1[2][0] = 0;
      matrix1[2][1] = 0;
      matrix1[2][2] = 1;
      return matrix1;
   }
   public static double[][] rtrvIMtrx3x3()
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = 1;
      aMatrix[1][1] = 1;
      aMatrix[2][2] = 1;
      return aMatrix;
   }
   public static double[][] I3x3()
   {
      double I[][] = new double[3][3];
      I[0][0] = 1;
      I[1][1] = 1;
      I[2][2] = 1;
      return I;
   }
   public static double[][] I4x4()
   {
      double I[][] = new double[4][4];
      I[0][0] = 1;
      I[1][1] = 1;
      I[2][2] = 1;
      I[3][3] = 1;
      return I;
   }
   public static double[][] cmptCrssPrdct(double myV1[][],double myV2[])
   {
      double aVector[][] = new double[3][3];
      
      aVector[0] = crssPrdct3x1x3x1(myV1[0],myV2);
      aVector[1] = crssPrdct3x1x3x1(myV1[1],myV2);
      aVector[2] = crssPrdct3x1x3x1(myV1[2],myV2);
      return aVector;
   }
   public static double[] crssPrdct3x1x3x1(double myV1[],double myV2[])
   {
      double aVector[] = new double[3];
      aVector[0] = myV1[1]*myV2[2] - myV1[2]*myV2[1];
      aVector[1] = myV1[2]*myV2[0] - myV1[0]*myV2[2];
      aVector[2] = myV1[0]*myV2[1] - myV1[1]*myV2[0];
      return aVector;
   }
   /*
   public static int[][] rtrv0Mtrx3x1()
   {
      int aMatrix[] = new int[3];
      return aMatrix;
   }*/
   public static double[][] rtrvDgnl3x3(double myMatrix[])
   {
     double aMatrix3x3[][] = new double[3][3];
     double a = myMatrix[0];
     double b = myMatrix[1];
     double c = myMatrix[2];
     if(a>=b && b>=c)
     {
        aMatrix3x3[0][0] = a;
        aMatrix3x3[1][1] = b;
        aMatrix3x3[2][2] = c;
     }else if(a>=c && c>=b){
        aMatrix3x3[0][0] = a;
        aMatrix3x3[1][1] = c;
        aMatrix3x3[2][2] = b;
     }else if(b>=a && a>=c){
        aMatrix3x3[0][0] = b;
        aMatrix3x3[1][1] = a;
        aMatrix3x3[2][2] = c;
     }else if(b>=c && c>=a){
        aMatrix3x3[0][0] = b;
        aMatrix3x3[1][1] = c;
        aMatrix3x3[2][2] = a;
     }else if(c>=a && a>=b){
        aMatrix3x3[0][0] = c;
        aMatrix3x3[1][1] = a;
        aMatrix3x3[2][2] = b;
     }else if(c>=b && b>=a){
        aMatrix3x3[0][0] = c;
        aMatrix3x3[1][1] = b;
        aMatrix3x3[2][2] = a;
     }
     return aMatrix3x3;
   }
   /*
   private double[][] mltply9x9x9x1_(double myM0[][],double myM1[][],double myM2[][],int myIndex1,int myIndex2)
   {
      myM0[myIndex1][myIndex2] =
                     myM1[0][0]*myM2[0][myIndex2]+myM1[0][1]*myM2[1][myIndex2]+
                     myM1[0][2]*myM2[2][myIndex2]+myM1[0][3]*myM2[3][myIndex2]+
                     myM1[0][4]*myM2[4][myIndex2]+myM1[0][5]*myM2[5][myIndex2]+
                     myM1[0][6]*myM2[6][myIndex2]+myM1[0][7]*myM2[7][myIndex2]+
                     myM1[0][8]*myM2[8][myIndex2]+myM1[0][9]*myM2[9][myIndex2];
      return myM0;
   }
   public double[][] mltply9x1x9x9(double myM1[][],double myM2[][])
   {
      double aMatrix[][] = new double[9][9];
      int aLength1 = aMatrix.length;
      int aLength2 = aMatrix[0].length;
      for(int i=0;i<1;i++)
      {
         for(int j=0;j<aLength2;j++)
         {
            mltply9x1x9x9_(aMatrix,myM1,myM2,i,j);
         }
      }
      return aMatrix;
   }*/
   public static double[] subtract4x1(double myM1[],double myM2[])
   {
       double aMatrix[] = new double[4];
       aMatrix[0] = myM1[0] - myM2[0];
       aMatrix[1] = myM1[1] - myM2[1];
       aMatrix[2] = myM1[2] - myM2[2];
       aMatrix[3] = myM1[3] - myM2[3];       
       return aMatrix;
   }
   public static double[][] subtract3x3(double myM1[][],double myM2[][])
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = myM1[0][0] - myM2[0][0];
      aMatrix[0][1] = myM1[0][1] - myM2[0][1];
      aMatrix[0][2] = myM1[0][2] - myM2[0][2];
      aMatrix[1][0] = myM1[1][0] - myM2[1][0];
      aMatrix[1][1] = myM1[1][1] - myM2[1][1];
      aMatrix[1][2] = myM1[1][2] - myM2[1][2];
      aMatrix[2][0] = myM1[2][0] - myM2[2][0];
      aMatrix[2][1] = myM1[2][1] - myM2[2][1];
      aMatrix[2][2] = myM1[2][2] - myM2[2][2];
      return aMatrix;
   }
   private static double[][] multiply3x3_(double myM0[][],double myM1[][],double myM2[][],int myIndex1,int myIndex2)
   {
      myM0[myIndex1][myIndex2] =
         myM1[myIndex1][0]*myM2[0][myIndex2]+myM1[myIndex1][1]*myM2[1][myIndex2]+myM1[myIndex1][2]*myM2[2][myIndex2];
      return myM0;
   }

   public static double[][] multiply3x3(double myM1[][],double myM2[][])
   {
      double aM0[][] = new double[3][3];
      for(int i=0;i<3;i++)
      {
         for(int j=0;j<3;j++)
         {
            multiply3x3_(aM0,myM1,myM2,i,j);
         }
      }
      return aM0;
   }
   public static double[] mltply3x1x3x3(double myM1[],double myM2[][])
   {
      double aM0[] = new double[3];
      aM0[0] = myM1[0]*myM2[0][0]+myM1[1]*myM2[1][0]+myM1[2]*myM2[2][0];
      aM0[1] = myM1[0]*myM2[0][1]+myM1[1]*myM2[1][1]+myM1[2]*myM2[2][1];
      aM0[2] = myM1[0]*myM2[0][2]+myM1[1]*myM2[1][2]+myM1[2]*myM2[2][2];
      return aM0;
   }
   public static double[] mltply3x3x3x1(double myM1[][],double myM2[])
   {
      double aM0[] = new double[3];
      aM0[0] = myM1[0][0]*myM2[0]+myM1[0][1]*myM2[1]+myM1[0][2]*myM2[2];
      aM0[1] = myM1[1][0]*myM2[0]+myM1[1][1]*myM2[1]+myM1[1][2]*myM2[2];
      aM0[2] = myM1[2][0]*myM2[0]+myM1[2][1]*myM2[1]+myM1[2][2]*myM2[2];
      return aM0;
   }
   public static double[] mltply8x8x8x1(double myM1[][],double myM2[])
   {
      double aM0[] = new double[8];
      aM0[0] = myM1[0][0]*myM2[0]+myM1[0][1]*myM2[1]+myM1[0][2]*myM2[2]+myM1[0][3]*myM2[3]+myM1[0][4]*myM2[4]+myM1[0][5]*myM2[5]+myM1[0][6]*myM2[6]+myM1[0][7]*myM2[7];
      aM0[1] = myM1[1][0]*myM2[0]+myM1[1][1]*myM2[1]+myM1[1][2]*myM2[2]+myM1[1][3]*myM2[3]+myM1[1][4]*myM2[4]+myM1[1][5]*myM2[5]+myM1[1][6]*myM2[6]+myM1[1][7]*myM2[7];
      aM0[2] = myM1[2][0]*myM2[0]+myM1[2][1]*myM2[1]+myM1[2][2]*myM2[2]+myM1[2][3]*myM2[3]+myM1[2][4]*myM2[4]+myM1[2][5]*myM2[5]+myM1[2][6]*myM2[6]+myM1[2][7]*myM2[7];
      aM0[3] = myM1[3][0]*myM2[0]+myM1[3][1]*myM2[1]+myM1[3][2]*myM2[2]+myM1[3][3]*myM2[3]+myM1[3][4]*myM2[4]+myM1[3][5]*myM2[5]+myM1[3][6]*myM2[6]+myM1[3][7]*myM2[7];
      aM0[4] = myM1[4][0]*myM2[0]+myM1[4][1]*myM2[1]+myM1[4][2]*myM2[2]+myM1[4][3]*myM2[3]+myM1[4][4]*myM2[4]+myM1[4][5]*myM2[5]+myM1[4][6]*myM2[6]+myM1[4][7]*myM2[7];
      aM0[5] = myM1[5][0]*myM2[0]+myM1[5][1]*myM2[1]+myM1[5][2]*myM2[2]+myM1[5][3]*myM2[3]+myM1[5][4]*myM2[4]+myM1[5][5]*myM2[5]+myM1[5][6]*myM2[6]+myM1[5][7]*myM2[7];      
      aM0[6] = myM1[6][0]*myM2[0]+myM1[6][1]*myM2[1]+myM1[6][2]*myM2[2]+myM1[6][3]*myM2[3]+myM1[6][4]*myM2[4]+myM1[6][5]*myM2[5]+myM1[6][6]*myM2[6]+myM1[6][7]*myM2[7];      
      aM0[7] = myM1[7][0]*myM2[0]+myM1[7][1]*myM2[1]+myM1[7][2]*myM2[2]+myM1[7][3]*myM2[3]+myM1[7][4]*myM2[4]+myM1[7][5]*myM2[5]+myM1[7][6]*myM2[6]+myM1[7][7]*myM2[7];      
      return aM0;
   }   
   public static double[] mltply3x4x4x1(double myM1[][],double myM2[])
   {
      double aM0[] = new double[3];
      aM0[0] = myM1[0][0]*myM2[0]+myM1[0][1]*myM2[1]+myM1[0][2]*myM2[2]+myM1[0][3]*myM2[3];
      aM0[1] = myM1[1][0]*myM2[0]+myM1[1][1]*myM2[1]+myM1[1][2]*myM2[2]+myM1[1][3]*myM2[3];
      aM0[2] = myM1[2][0]*myM2[0]+myM1[2][1]*myM2[1]+myM1[2][2]*myM2[2]+myM1[2][3]*myM2[3];
      return aM0;
   }   
   public static double[] mltply9x2x9x1(double myM1[][],int myM2[])
   {
      double aM0[] = new double[2];
      aM0[0] = myM1[0][0]*myM2[0]+myM1[1][0]*myM2[1]+myM1[2][0]*myM2[2]+
               myM1[3][0]*myM2[3]+myM1[4][0]*myM2[4]+myM1[5][0]*myM2[5]+
               myM1[6][0]*myM2[6]+myM1[7][0]*myM2[7]+myM1[8][0]*myM2[8];
      aM0[1] = myM1[0][1]*myM2[0]+myM1[1][1]*myM2[1]+myM1[2][1]*myM2[2]+
               myM1[3][1]*myM2[3]+myM1[4][1]*myM2[4]+myM1[5][1]*myM2[5]+
               myM1[6][1]*myM2[6]+myM1[7][1]*myM2[7]+myM1[8][1]*myM2[8];
      return aM0;
   }
   public static double[][] mltply8x9Tx8x9(double myM1[][],double myM2[][])
   {
      double aM0[][] = new double[9][9];
      aM0[0] = mltply1x9x9x1(myM1,myM2,0);      
      aM0[1] = mltply1x9x9x1(myM1,myM2,1);
      aM0[2] = mltply1x9x9x1(myM1,myM2,2);
      aM0[3] = mltply1x9x9x1(myM1,myM2,3);
      aM0[4] = mltply1x9x9x1(myM1,myM2,4);      
      aM0[5] = mltply1x9x9x1(myM1,myM2,5);
      aM0[6] = mltply1x9x9x1(myM1,myM2,6);      
      aM0[7] = mltply1x9x9x1(myM1,myM2,7);      
      aM0[8] = mltply1x9x9x1(myM1,myM2,8);     
      return aM0;
   }

   public static double[] mltply1x9x9x1(double myM1[][],double myM2[][],int r)
   {
      double aM0[] = new double[9];
      aM0[0] = mltply1x9x9x1(myM1,myM2,r,0);      
      aM0[1] = mltply1x9x9x1(myM1,myM2,r,1);
      aM0[2] = mltply1x9x9x1(myM1,myM2,r,2);
      aM0[3] = mltply1x9x9x1(myM1,myM2,r,3);
      aM0[4] = mltply1x9x9x1(myM1,myM2,r,4);      
      aM0[5] = mltply1x9x9x1(myM1,myM2,r,5);
      aM0[6] = mltply1x9x9x1(myM1,myM2,r,6);      
      aM0[7] = mltply1x9x9x1(myM1,myM2,r,7);      
      aM0[8] = mltply1x9x9x1(myM1,myM2,r,8);     
      //System.exit(0);
      return aM0;
   }
   public static double mltply1x9x9x1(double myM1[][],double myM2[][],int r,int c)
   {
      double aM0 = 0;
      aM0 = myM1[0][r]*myM2[0][c]+myM1[1][r]*myM2[1][c]+myM1[2][r]*myM2[2][c]+
               myM1[3][r]*myM2[3][c]+myM1[4][r]*myM2[4][c]+myM1[5][r]*myM2[5][c]+
               myM1[6][r]*myM2[6][c]+myM1[7][r]*myM2[7][c];
      //System.out.println("DLTHOG : "+(myM1[0][r]+"*"+myM2[0][c])+"+"+(myM1[1][r]+"*"+myM2[1][c])+"+"+(myM1[2][r]+"*"+myM2[2][c])+"+"+(myM1[3][r]+"*"+myM2[3][c])+
      //        "+"+(myM1[4][r]+"*"+myM2[4][c])+"+"+(myM1[5][r]+"*"+myM2[5][c])+"+"+(myM1[6][r]*myM2[6][c])+"+"+(myM1[7][r]+"*"+myM2[7][c])+"="+aM0);
      return aM0;
   }   
   public static double mltply3x1x3x1(double myM1[],double myM2[])
   {
      double aM0 = 0;
      aM0 = myM1[0]*myM2[0]+myM1[1]*myM2[1]+myM1[2]*myM2[2];
      return aM0;
   }
   public static double mltply2x1x2x1(double myM1[],double myM2[])
   {
      double aM0 = 0;
      aM0 = myM1[0]*myM2[0]+myM1[1]*myM2[1];
      return aM0;
   }
   public static double[] mltply2x2x2x1(double myM1[][],double myM2[])
   {
      double aM[] = new double[2];
      aM[0] = myM1[0][0]*myM2[0]+myM1[0][1]*myM2[1];
      aM[1] = myM1[1][0]*myM2[0]+myM1[1][1]*myM2[1];
      return aM;
   }
   private static double[][] multiply3x3T_(double myM0[][],double myM1[][],double myM2[][],int myIndex1,int myIndex2)
   {

      myM0[myIndex1][myIndex2] =
         myM1[myIndex1][0]*myM2[myIndex2][0]+myM1[myIndex1][1]*myM2[myIndex2][1]+myM1[myIndex1][2]*myM2[myIndex2][2];
      return myM0;
   }
   public static double[][] multiply3x3T(double myM1[][],double myM2[][])
   {
      double aM0[][] = new double[3][3];
      for(int i=0;i<3;i++)
      {
         for(int j=0;j<3;j++)
         {
            multiply3x3T_(aM0,myM1,myM2,i,j);
         }
      }
      return aM0;
   }
   public static double[][] copy3x3(double myMatrix[][])
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = myMatrix[0][0];
      aMatrix[1][0] = myMatrix[1][0];
      aMatrix[2][0] = myMatrix[2][0];
      aMatrix[0][1] = myMatrix[0][1];
      aMatrix[1][1] = myMatrix[1][1];
      aMatrix[2][1] = myMatrix[2][1];
      aMatrix[0][2] = myMatrix[0][2];
      aMatrix[1][2] = myMatrix[1][2];
      aMatrix[2][2] = myMatrix[2][2];
      return aMatrix;
   }
   public static double[][] createSkewMatrix3x3(double myMatrix[])
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = 0;
      aMatrix[1][0] = -myMatrix[2];
      aMatrix[2][0] = myMatrix[1];
      aMatrix[0][1] = myMatrix[2];
      aMatrix[1][1] = 0;
      aMatrix[2][1] = -myMatrix[0];
      aMatrix[0][2] = -myMatrix[1];
      aMatrix[1][2] = myMatrix[0];
      aMatrix[2][2] = 0;
      return aMatrix;
   }   
   public static double[][] concatenate3x3To3x1(double myMatrix1[][],double myMatrix2[])
   {
      double aMatrix[][] = new double[3][4];
      aMatrix[0][0] = myMatrix1[0][0];
      aMatrix[1][0] = myMatrix1[1][0];
      aMatrix[2][0] = myMatrix1[2][0];
      aMatrix[0][1] = myMatrix1[0][1];
      aMatrix[1][1] = myMatrix1[1][1];
      aMatrix[2][1] = myMatrix1[2][1];
      aMatrix[0][2] = myMatrix1[0][2];
      aMatrix[1][2] = myMatrix1[1][2];
      aMatrix[2][2] = myMatrix1[2][2];
      
      aMatrix[0][3] = myMatrix2[0];
      aMatrix[1][3] = myMatrix2[1];
      aMatrix[2][3] = myMatrix2[2];
      
      return aMatrix;
   }
   public static float[][] copyDoubleToFloat3x4(double myMatrix[][])
   {
      float aMatrix[][] = new float[3][4];
      aMatrix[0][0] = (float)myMatrix[0][0];
      aMatrix[1][0] = (float)myMatrix[1][0];
      aMatrix[2][0] = (float)myMatrix[2][0];      
      aMatrix[0][1] = (float)myMatrix[0][1];
      aMatrix[1][1] = (float)myMatrix[1][1];
      aMatrix[2][1] = (float)myMatrix[2][1];
      aMatrix[0][2] = (float)myMatrix[0][2];
      aMatrix[1][2] = (float)myMatrix[1][2];
      aMatrix[2][2] = (float)myMatrix[2][2];      
      aMatrix[0][3] = (float)myMatrix[0][3];
      aMatrix[1][3] = (float)myMatrix[1][3];
      aMatrix[2][3] = (float)myMatrix[2][3];      
      return aMatrix;
   }   
   public static double[][] copy8x3(double myMatrix[][])
   {
      double aMatrix[][] = new double[8][3];
      aMatrix[0][0] = myMatrix[0][0];
      aMatrix[0][1] = myMatrix[0][1];
      aMatrix[0][2] = myMatrix[0][2];
      aMatrix[1][0] = myMatrix[1][0];
      aMatrix[1][1] = myMatrix[1][1];
      aMatrix[1][2] = myMatrix[1][2];
      aMatrix[2][0] = myMatrix[2][0];
      aMatrix[2][1] = myMatrix[2][1];
      aMatrix[2][2] = myMatrix[2][2];
      aMatrix[3][0] = myMatrix[3][0];
      aMatrix[3][1] = myMatrix[3][1];
      aMatrix[3][2] = myMatrix[3][2];
      aMatrix[4][0] = myMatrix[4][0];
      aMatrix[4][1] = myMatrix[4][1];
      aMatrix[4][2] = myMatrix[4][2];
      aMatrix[5][0] = myMatrix[5][0];
      aMatrix[5][1] = myMatrix[5][1];
      aMatrix[5][2] = myMatrix[5][2];
      aMatrix[6][0] = myMatrix[6][0];
      aMatrix[6][1] = myMatrix[6][1];
      aMatrix[6][2] = myMatrix[6][2];
      aMatrix[7][0] = myMatrix[7][0];
      aMatrix[7][1] = myMatrix[7][1];
      aMatrix[7][2] = myMatrix[7][2];
      return aMatrix;
   }
   public static double[][] round3x3(double myMatrix[][])
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = Math.round(myMatrix[0][0]);
      aMatrix[1][0] = Math.round(myMatrix[1][0]);
      aMatrix[2][0] = Math.round(myMatrix[2][0]);
      aMatrix[0][1] = Math.round(myMatrix[0][1]);
      aMatrix[1][1] = Math.round(myMatrix[1][1]);
      aMatrix[2][1] = Math.round(myMatrix[2][1]);
      aMatrix[0][2] = Math.round(myMatrix[0][2]);
      aMatrix[1][2] = Math.round(myMatrix[1][2]);
      aMatrix[2][2] = Math.round(myMatrix[2][2]);
      return aMatrix;
   }
   /*Note: This is the transpose ...
   public static double[][] inverse3x3(double myM1[][])
   {
      double aM0[][] = new double[3][3];
      aM0[0][0] = myM1[0][0];
      aM0[0][1] = myM1[1][0];
      aM0[0][2] = myM1[2][0];
      aM0[1][0] = myM1[0][1];
      aM0[1][1] = myM1[1][1];
      aM0[1][2] = myM1[2][1];
      aM0[2][0] = myM1[0][2];
      aM0[2][1] = myM1[1][2];
      aM0[2][2] = myM1[2][2];
      return aM0;
   }*/
   public static float[][] inverse(float sx[][])
   {
       return inverse(sx,sx.length,0);
   }   
   public static float[][] inverse(float sx[][],int n,int idim)
   {
      //System.out.println("n = "+n+", idim = "+idim);
      double esp = Math.pow(1,-9);
      for(int k=1;k<=n;k++)
      {
         for(int j=1;j<=n;j++)
         {
            if(j != k)
            {
               sx[k][j] = sx[k][j] / sx[k][k];
               //System.out.println("k,j sx["+k+"]["+j+"] = "+sx[k][j]+", sx[k][k] = "+sx[k][k]);
            }
         }
         sx[k][k] = (1.0f / sx[k][k]);
         //System.out.println("k,k sx["+k+"]["+k+"] = "+sx[k][k]);
         for(int i=1;i<=n;i++)
         {
            if(i != k)
            {
               for(int j=1;j<=n;j++)
               {
                  if(j != k)
                  {
                     sx[i][j] = sx[i][j] - sx[k][j]*sx[i][k];
                     //System.out.println("i,j sx["+i+"]["+j+"] = "+sx[i][j]);
                  }
               }
            }
         }
         for(int i=1;i<=n;i++)
         {
            if(i != k)
            {
               sx[i][k]  = -sx[i][k]*sx[k][k];
               //System.out.println("i,k sx["+i+"]["+k+"] = "+sx[i][k]);
            }
         }
      }
      return sx;
   }
   public static double[][] inverse(double sx[][])
   {
       return inverse(sx,sx.length,0);
   }
   public static double[][] inverse(double sx[][],int n,int idim)
   {
      //System.out.println("n = "+n+", idim = "+idim);
      double esp = Math.pow(1,-9);
      for(int k=0;k<n;k++)
      {
         for(int j=0;j<n;j++)
         {
            if(j != k)
            {
               sx[k][j] = sx[k][j] / sx[k][k];
               //System.out.println("k,j sx["+k+"]["+j+"] = "+sx[k][j]+", sx[k][k] = "+sx[k][k]);
            }
         }
         sx[k][k] = (1.0f / sx[k][k]);
         //System.out.println("k,k sx["+k+"]["+k+"] = "+sx[k][k]);
         for(int i=0;i<n;i++)
         {
            if(i != k)
            {
               for(int j=0;j<n;j++)
               {
                  if(j != k)
                  {
                     sx[i][j] = sx[i][j] - sx[k][j]*sx[i][k];
                     //System.out.println("i,j sx["+i+"]["+j+"] = "+sx[i][j]);
                  }
               }
            }
         }
         for(int i=0;i<n;i++)
         {
            if(i != k)
            {
               sx[i][k]  = -sx[i][k]*sx[k][k];
               //System.out.println("i,k sx["+i+"]["+k+"] = "+sx[i][k]);
            }
         }
      }
      return sx;
   }
   public static double[][] rtrvSkewMtrx(double myMatrix[])
   {
      double aM0[][] = new double[3][3];
      aM0[0][0] = 0;
      aM0[0][1] = -myMatrix[2];
      aM0[0][2] = myMatrix[1];
      aM0[1][0] = myMatrix[2];
      aM0[1][1] = 0;
      aM0[1][2] = -myMatrix[0];
      aM0[2][0] = -myMatrix[1];
      aM0[2][1] = myMatrix[0];
      aM0[2][2] = 0;
      return aM0;
   }
   public static double[][] append3x4(double myM1[][],double myM2[])
   {
      double aM0[][] = new double[3][4];
      aM0[0][0] = myM1[0][0];
      aM0[0][1] = myM1[0][1];
      aM0[0][2] = myM1[0][2];
      aM0[1][0] = myM1[1][0];
      aM0[1][1] = myM1[1][1];
      aM0[1][2] = myM1[1][2];
      aM0[2][0] = myM1[2][0];
      aM0[2][1] = myM1[2][1];
      aM0[2][2] = myM1[2][2];
      aM0[0][3] = myM2[0];
      aM0[1][3] = myM2[1];
      aM0[2][3] = myM2[2];      
      return aM0;
   }
   public static double cmptMgntd7x1(double v2[],double v1[])
   {
      double v3[] = new double[7];
      v3[0] = v2[0] - v1[0];
      v3[1] = v2[1] - v1[1];
      v3[2] = v2[2] - v1[2];
      v3[3] = v2[3] - v1[3];
      v3[4] = v2[4] - v1[4];
      v3[5] = v2[5] - v1[5];
      v3[6] = v2[6] - v1[6];
      double mag2 = v3[0]*v3[0]+v3[1]*v3[1]+v3[2]*v3[2]+
                    v3[3]*v3[3]+v3[4]*v3[4]+v3[5]*v3[5]+
                    v3[6]*v3[6];
      double mag = Math.sqrt(mag2);
      return mag;
   }
   public static double[][] transpose3x3(double myMatrix[][])
   {
      double aMatrix[][] = new double[3][3];
      aMatrix[0][0] = myMatrix[0][0];
      aMatrix[1][0] = myMatrix[0][1];
      aMatrix[2][0] = myMatrix[0][2];
      aMatrix[0][1] = myMatrix[1][0];
      aMatrix[1][1] = myMatrix[1][1];
      aMatrix[2][1] = myMatrix[1][2];
      aMatrix[0][2] = myMatrix[2][0];
      aMatrix[1][2] = myMatrix[2][1];
      aMatrix[2][2] = myMatrix[2][2];
      return aMatrix;
   }
   public static double[][] transpose3x4(double myMatrix[][])
   {
      double aMatrix[][] = new double[4][3];
      aMatrix[0][0] = myMatrix[0][0];
      aMatrix[1][0] = myMatrix[0][1];
      aMatrix[2][0] = myMatrix[0][2];
      aMatrix[3][0] = myMatrix[0][3];      
      aMatrix[0][1] = myMatrix[1][0];
      aMatrix[1][1] = myMatrix[1][1];
      aMatrix[2][1] = myMatrix[1][2];
      aMatrix[3][1] = myMatrix[1][3];      
      aMatrix[0][2] = myMatrix[2][0];
      aMatrix[1][2] = myMatrix[2][1];
      aMatrix[2][2] = myMatrix[2][2];
      aMatrix[3][2] = myMatrix[2][3];      
      return aMatrix;
   }
   public static double[][] transpose4x3(double myMatrix[][])
   {
      double aMatrix[][] = new double[3][4];
      aMatrix[0][0] = myMatrix[0][0];
      aMatrix[0][1] = myMatrix[1][0];
      aMatrix[0][2] = myMatrix[2][0];
      aMatrix[0][3] = myMatrix[3][0];      
      aMatrix[1][0] = myMatrix[0][1];
      aMatrix[1][1] = myMatrix[1][1];
      aMatrix[1][2] = myMatrix[2][1];
      aMatrix[1][3] = myMatrix[3][1];      
      aMatrix[2][0] = myMatrix[0][2];
      aMatrix[2][1] = myMatrix[1][2];
      aMatrix[2][2] = myMatrix[2][2];
      aMatrix[2][3] = myMatrix[3][2];      
      return aMatrix;
   }   
   public static double[][] transpose(double myMatrix[][])
   {
      double aMatrix[][] = new double[myMatrix[0].length][myMatrix.length];
      for(int i=0;i<myMatrix.length;i++)
      {
         for(int j=0;j<myMatrix[0].length;j++)
         {
            aMatrix[j][i] = myMatrix[i][j];
         }
      }
      return aMatrix;
   }
   public static double[] mltplMxMxM(double m1[][],double m2[])
   {
      int aLength = m1.length;
      double m3[] = new double[aLength];
      for(int i=0;i<aLength;i++)
      {
         for(int t=0;t<aLength;t++)
         {
            for(int j=0;j<aLength;j++)
            {
               m3[i] = m3[i] + m1[i][j]*m2[j];
               //System.out.println("m1[i][j]="+m1[i][j]+"m2[j][t]+"+m2[j][t]+",m3[i][t]="+m3[i][t]);
            }
         }
      }
      return m3;
   }
   public static double[][] mltplyMxMxMxM(double m1[][],double m2[][])
   {
      int aLength1 = m1.length;
      int aLength2 = m1[0].length;
      int aLength3 = m2.length;
      int aLength4 = m2[0].length;
      
      double m3[][] = new double[aLength1][aLength4];
      
      for(int i=0;i<aLength1;i++)
      {
         for(int g=0;g<aLength4;g++)
         {
            for(int t=0;t<aLength1;t++)
            {
               //for(int j=0;j<aLength1;j++)
               //{
               m3[i][g] = m3[i][g] + m1[i][t]*m2[t][g];
               //System.out.println("m1[i][j]="+m1[i][j]+"m2[j][t]+"+m2[j][t]+",m3[i][t]="+m3[i][t]);
               //}
            }
         }
      }
      return m3;
   }   
   public static int[][] mltplyMxMxMxM(int m1[][],int m2[][])
   {
      int aLength1 = m1.length;
      int aLength2 = m1[0].length;
      int aLength3 = m2.length;
      int aLength4 = m2[0].length;
      
      int m3[][] = new int[aLength1][aLength4];
      
      for(int i=0;i<aLength1;i++)
      {
         for(int g=0;g<aLength4;g++)
         {
            for(int t=0;t<aLength1;t++)
            {
               //for(int j=0;j<aLength1;j++)
               //{
               m3[i][g] = m3[i][g] + m1[i][t]*m2[t][g];
               //System.out.println("m1[i][j]="+m1[i][j]+"m2[j][t]+"+m2[j][t]+",m3[i][t]="+m3[i][t]);
               //}
            }
         }
      }
      return m3;
   }    
   public static double[] add2x1x2x1(double m1[],double m2[])
   {
      int aLength = m1.length;
      double m3[] = new double[aLength];
      m3[0] = m1[0]+m2[0];
      m3[1] = m1[1]+m2[1];
      return m3;
   }
   public static double[] add(double m1[],double m2[])
   {
      int aLength = m1.length;
      double m3[] = new double[aLength];
      for(int i=0;i<aLength;i++)
      {
         m3[i] = m1[i]+m2[i];
      }
      return m3;
   }
   public static double[][] add2x2(double m1[][],double m2[][])
   {
      double m3[][] = new double[2][2];
      m3[0][0] = m1[0][0] + m2[0][0];
      m3[0][1] = m1[0][1] + m2[0][1];
      m3[1][0] = m1[1][0] + m2[1][0];
      m3[1][1] = m1[1][1] + m2[1][1];
      return m3;
   }
   public static double[][] add9x2(double m1[][],double m2[][])
   {
      double m3[][] = new double[9][2];
      m3[0][0] = m1[0][0] + m2[0][0];
      m3[0][1] = m1[0][1] + m2[0][1];
      m3[1][0] = m1[1][0] + m2[1][0];
      m3[1][1] = m1[1][1] + m2[1][1];
      m3[2][0] = m1[2][0] + m2[2][0];
      m3[2][1] = m1[2][1] + m2[2][1];      
      m3[3][0] = m1[3][0] + m2[3][0];
      m3[3][1] = m1[3][1] + m2[3][1];      
      m3[4][0] = m1[4][0] + m2[4][0];
      m3[4][1] = m1[4][1] + m2[4][1];            
      m3[5][0] = m1[5][0] + m2[5][0];
      m3[5][1] = m1[5][1] + m2[5][1];
      m3[6][0] = m1[6][0] + m2[6][0];
      m3[6][1] = m1[6][1] + m2[6][1];
      m3[7][0] = m1[7][0] + m2[7][0];
      m3[7][1] = m1[7][1] + m2[7][1];
      m3[8][0] = m1[8][0] + m2[8][0];
      m3[8][1] = m1[8][1] + m2[8][1];            
      return m3;
   }
   public static double[][] cmptInvrs2x2(double myMatrix[][])
   {
      double invrsMatrix[][] = new double[2][2];

      double a11 = myMatrix[0][0];
      double a12 = myMatrix[0][1];
      double a21 = myMatrix[1][0];
      double a22 = myMatrix[1][1];

      double det = a11*a22-a12*a21;

      if(det == 0)
      {
         return invrsMatrix;
      }
      double b11 = a22/det;
      double b12 = -a12/det;
      double b21 = -a21/det;
      double b22 = a11/det;
      invrsMatrix[0][0] = b11;
      invrsMatrix[1][0] = b12;
      invrsMatrix[0][1] = b21;
      invrsMatrix[1][1] = b22;
      return invrsMatrix;
   }
   public static double[][] flipud3x3(double m1[][]){
      double m2[][] = new double[3][3];
      m2[0][0] = m1[2][0];
      m2[0][1] = m1[2][1];
      m2[0][2] = m1[2][2];
      m2[1][0] = m1[1][0];
      m2[1][1] = m1[1][1];
      m2[1][2] = m1[1][2];
      m2[2][0] = m1[0][0];
      m2[2][1] = m1[0][1];
      m2[2][2] = m1[0][2];   
      return m2;
   }
   public static double[][] flipud3x4(double m1[][]){
      double m2[][] = new double[3][4];
      System.out.println("Matrix.flipud3x4 ... ");
      //Matrix.print(m1);
      
      m2[0][0] = m1[2][0];
      m2[0][1] = m1[2][1];
      m2[0][2] = m1[2][2];
      m2[0][3] = m1[2][3];      
      m2[1][0] = m1[1][0];
      m2[1][1] = m1[1][1];
      m2[1][2] = m1[1][2];
      m2[1][3] = m1[1][3];      
      m2[2][0] = m1[0][0];
      m2[2][1] = m1[0][1];
      m2[2][2] = m1[0][2];   
      m2[2][3] = m1[0][3];      
      return m2;
   }   
   public static double[][] retrieve3x3(double m1[][]){
      double m2[][] = new double[3][3];
      //Matrix.print(m1);
      
      m2[0][0] = m1[0][0];
      m2[0][1] = m1[0][1];
      m2[0][2] = m1[0][2];
      m2[1][0] = m1[1][0];      
      m2[1][1] = m1[1][1];
      m2[1][2] = m1[1][2];
      m2[2][0] = m1[2][0];
      m2[2][1] = m1[2][1];      
      m2[2][2] = m1[2][2];
      return m2;
   }   
   
    public static double[][] convert9x1To3x3(double myMatrix[]){
        double aHMatrix[][] = new double[3][3];
        aHMatrix[0][0] = myMatrix[0];
        aHMatrix[0][1] = myMatrix[1];        
        aHMatrix[0][2] = myMatrix[2];        
        aHMatrix[1][0] = myMatrix[3];
        aHMatrix[1][1] = myMatrix[4];        
        aHMatrix[1][2] = myMatrix[5];
        aHMatrix[2][0] = myMatrix[6];
        aHMatrix[2][1] = myMatrix[7];        
        aHMatrix[2][2] = myMatrix[8];
        return aHMatrix;
    }
    public static double[][] convert9x1To3x3Add1(double myMatrix[]){
        double aHMatrix[][] = new double[3][3];
        aHMatrix[0][0] = myMatrix[0];
        aHMatrix[0][1] = myMatrix[1];        
        aHMatrix[0][2] = myMatrix[2];        
        aHMatrix[1][0] = myMatrix[3];
        aHMatrix[1][1] = myMatrix[4];        
        aHMatrix[1][2] = myMatrix[5];
        aHMatrix[2][0] = myMatrix[6];
        aHMatrix[2][1] = myMatrix[7];        
        aHMatrix[2][2] = 1;
        return aHMatrix;
    }    
    public static double[][] convert11x1To3x4(double myMatrix[]){
        double aHMatrix[][] = new double[3][4];
        aHMatrix[0][0] = myMatrix[0];
        aHMatrix[0][1] = myMatrix[1];        
        aHMatrix[0][2] = myMatrix[2];        
        aHMatrix[0][3] = myMatrix[3];        
        aHMatrix[1][0] = myMatrix[4];
        aHMatrix[1][1] = myMatrix[5];        
        aHMatrix[1][2] = myMatrix[6];
        aHMatrix[1][3] = myMatrix[7];        
        aHMatrix[2][0] = myMatrix[8];
        aHMatrix[2][1] = myMatrix[9];        
        aHMatrix[2][2] = myMatrix[10];
        aHMatrix[2][3] = 1;                        
        return aHMatrix;
    }    
   public static void print(double m[][])
   {
     System.out.println("...... MATRIX PRINT ......");
     int aLength1 = m.length;
     int aLength2 = m[0].length;
     print(m,aLength1,aLength2);
   }
   public static void print(double m[][],int aLength1,int aLength2)
   {
     for(int i=0;i<aLength1;i++)
     {
        System.out.println("");
        for(int j=0;j<aLength2;j++)
        {
           System.out.print(m[i][j]+" ");
        }
     }
      System.out.println("");
   }
   public static void print(float m[][])
   {
     System.out.println("...... MATRIX PRINT ......");
     int aLength1 = m.length;
     int aLength2 = m[0].length;
     print(m,aLength1,aLength2);
   }
   public static void print(float m[][],int aLength1,int aLength2)
   {
     for(int i=0;i<aLength1;i++)
     {
        System.out.println("");
        for(int j=0;j<aLength2;j++)
        {
           System.out.print(m[i][j]+" ");
        }
     }
      System.out.println("");
   }
   /*
   public static void print(double m[][])
   {
     for(int i=0;i<aLength1;i++)
     {
        System.out.println("");
        for(int j=0;j<aLength2;j++)
        {
           System.out.print(m[i][j]+" ");
        }
     }
      System.out.println("");
   }*/
   public static void print(int m[][])
   {
      System.out.println("...... MATRIX PRINT ......");
      int aLength1 = m.length;
      int aLength2 = m[0].length;
      print(m,aLength1,aLength2);
   }
   public static void print(int m[][],int aLength1,int aLength2)
   {
     for(int i=0;i<aLength1;i++)
     {
        System.out.println("");
        for(int j=0;j<aLength2;j++)
        {
           System.out.print(m[i][j]+" ");
        }
     }
     System.out.println("");      
   }
   public static void print(double m[])
   {
     System.out.println("...... MATRIX PRINT ......");
     int aLength1 = m.length;
     for(int i=0;i<aLength1;i++)
     {
        System.out.print(m[i]+" ");
     }
     System.out.println("");
   }
   public static void print(int m[])
   {
     System.out.println("...... MATRIX PRINT ......");
     int aLength1 = m.length;
     for(int i=0;i<aLength1;i++)
     {
        System.out.print(m[i]+" ");
     }
     System.out.println("");
   }  
    public static double[][] insertRowNx9(double aMatrixA2nx9[][], double myMatrixA[][], int myRow) {
        //double aMatrixA2nx9[][] = new double[8][9];        
        aMatrixA2nx9[myRow][0] = myMatrixA[0][0];
        aMatrixA2nx9[myRow][1] = myMatrixA[0][1];
        aMatrixA2nx9[myRow][2] = myMatrixA[0][2];
        aMatrixA2nx9[myRow][3] = myMatrixA[0][3];
        aMatrixA2nx9[myRow][4] = myMatrixA[0][4];
        aMatrixA2nx9[myRow][5] = myMatrixA[0][5];
        aMatrixA2nx9[myRow][6] = myMatrixA[0][6];
        aMatrixA2nx9[myRow][7] = myMatrixA[0][7];
        aMatrixA2nx9[myRow][8] = myMatrixA[0][8];
        return aMatrixA2nx9;
    }   
    public static double[][] insertRowNx8(double aMatrixA2nx9[][], double myMatrixA[][], int myRow) {
        //double aMatrixA2nx9[][] = new double[8][9];        
        aMatrixA2nx9[myRow][0] = myMatrixA[0][0];
        aMatrixA2nx9[myRow][1] = myMatrixA[0][1];
        aMatrixA2nx9[myRow][2] = myMatrixA[0][2];
        aMatrixA2nx9[myRow][3] = myMatrixA[0][3];
        aMatrixA2nx9[myRow][4] = myMatrixA[0][4];
        aMatrixA2nx9[myRow][5] = myMatrixA[0][5];
        aMatrixA2nx9[myRow][6] = myMatrixA[0][6];
        aMatrixA2nx9[myRow][7] = myMatrixA[0][7];
        return aMatrixA2nx9;
    }     
}
